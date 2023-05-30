package com.jingdong.common.web.managers;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.DeviceInfoUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import java.net.InetAddress;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class PerformanceManager {
    public static final String CUP = "cpu";
    public static final String ERR_MSG = "errMsg";
    public static final String ERR_TYPE = "errType";
    public static final String EXTRA = "extra";
    public static final String GET_TIMING_TIME = "getTimingTime";
    public static final String HEADER = "header";
    public static final String IS_ERROR = "isError";
    public static final String IS_MEM_LOW = "isLowMem";
    public static final String LOADING_TIME = "mloadingTime";
    public static final String LOAD_TYPE = "mloadType";
    public static final String LOAD_URL = "mloadUrl";
    public static final String MEM_TOTAL = "mem";
    public static final String MEM_USED = "usedMem";
    public static final String MERROR_CODE = "mErrCode";
    public static final String OCCUR_TIME = "occurTime";
    public static final String PATH = "path";
    public static final String RESOLVED_IP = "resolvedIp";
    public static final String SECOND_TYPE = "secondType";
    public static final String SEESIONID = "sessionId";
    public static final String TIMING = "timing";
    public static final String TYPE_CORE = "kernelType";
    public static final String USER_AGENT = "userAgent";
    private static PerformanceManager ourInstance;
    private static ExecutorService sExecutorService = Executors.newFixedThreadPool(1);
    private JDJSONObject mPerformanceJson;
    private long timingStartTime;
    private Map<String, String> mData = new ConcurrentHashMap();
    private boolean mReportPerformance = false;
    private boolean mReportDetail = false;
    private String mUrl = "";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ReportRunable implements Runnable {
        HashMap<String, String> mData;
        boolean mReportDetail;
        String mUrl;

        ReportRunable(Map<String, String> map, String str, boolean z) {
            HashMap<String, String> hashMap = new HashMap<>();
            this.mData = hashMap;
            hashMap.putAll(map);
            this.mUrl = str;
            this.mReportDetail = z;
        }

        public String getIpByHost(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            try {
                InetAddress byName = InetAddress.getByName(str);
                if (byName != null) {
                    return byName.getHostAddress();
                }
            } catch (Exception unused) {
            }
            return "";
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.mReportDetail) {
                    this.mData.put(PerformanceManager.CUP, DeviceInfoUtil.getCpuInfo());
                    this.mData.put(PerformanceManager.MEM_USED, DeviceInfoUtil.getMemUsed());
                    this.mData.put(PerformanceManager.MEM_TOTAL, DeviceInfoUtil.getMemTotal());
                    this.mData.put(PerformanceManager.IS_MEM_LOW, DeviceInfoUtil.isLowMem());
                }
                if ("1".equals(this.mData.get("isError"))) {
                    this.mData.put("resolvedIp", getIpByHost(new URL(this.mUrl).getHost()));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (!this.mData.containsKey("occurTime")) {
                    this.mData.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
                }
                this.mData.put("typeId", "7");
                this.mData.put("chId", "2");
                PerformanceReporter.reportData(this.mData);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private PerformanceManager() {
    }

    public static PerformanceManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new PerformanceManager();
        }
        return ourInstance;
    }

    public void appendData(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        if (this.mData == null) {
            this.mData = new ConcurrentHashMap();
        }
        this.mData.put(str, str2);
        if (LOAD_URL.equals(str)) {
            this.mUrl = str2;
        }
    }

    public void appendPath(List<String> list) {
        if (this.mData == null) {
            this.mData = new ConcurrentHashMap();
        }
        this.mData.put("path", WebUtils.stringifyUrlHistory(list));
    }

    public String getLoadType() {
        Map<String, String> map = this.mData;
        return (map == null || TextUtils.isEmpty(map.get("mloadType"))) ? "webview" : this.mData.get("mloadType");
    }

    public void init() {
        try {
            StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "7", "2");
            if (stategyEntitiy == null) {
                return;
            }
            this.mReportPerformance = "1".equals(stategyEntitiy.ret);
            JDJSONObject parseObject = JDJSON.parseObject(stategyEntitiy.param);
            this.mPerformanceJson = parseObject;
            boolean z = true;
            if (1 != parseObject.getIntValue("detail")) {
                z = false;
            }
            this.mReportDetail = z;
        } catch (Exception unused) {
        }
    }

    public void report() {
        Map<String, String> map = this.mData;
        if (map == null) {
            return;
        }
        if (map.get("isError") == null) {
            this.mData.put("isError", "0");
        }
        sExecutorService.submit(new ReportRunable(this.mData, this.mUrl, this.mReportDetail));
        this.mData.clear();
    }

    public void reportData(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        String str = hashMap.containsKey(LOAD_URL) ? hashMap.get(LOAD_URL) : "";
        if (hashMap.get("isError") == null) {
            hashMap.put("isError", "0");
        }
        sExecutorService.submit(new ReportRunable(hashMap, str, this.mReportDetail));
    }

    public void reportSimpleData(HashMap<String, String> hashMap) {
        hashMap.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
        hashMap.put("typeId", "7");
        hashMap.put("chId", "2");
        PerformanceReporter.reportData(hashMap);
    }

    public void setOccurTime(long j2) {
        if (this.mData == null || j2 <= 0) {
            return;
        }
        try {
            this.mData.put("occurTime", new DecimalFormat("0.000000").format(j2 / 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean shouldReport() {
        return this.mReportPerformance;
    }

    public void timingEnd() {
        this.mData.put(GET_TIMING_TIME, String.valueOf(System.currentTimeMillis() - this.timingStartTime));
    }

    public void timingStart() {
        this.timingStartTime = System.currentTimeMillis();
    }

    public void appendData(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return;
        }
        if (this.mData == null) {
            this.mData = new ConcurrentHashMap();
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        for (String str2 : map.keySet()) {
            jDJSONObject.put(str2, (Object) map.get(str2));
        }
        this.mData.put(str, jDJSONObject.toJSONString());
    }
}
