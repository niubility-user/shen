package com.jingdong.common.web.managers;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.DeviceInfoUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.net.InetAddress;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class WebPerfManager {
    public static final String BINGO_COUNT = "bingoCount";
    public static final String BIZ_MSG = "businessMsg";
    public static final String BIZ_OFFLINE_BINGO = "businessBingo";
    public static final String BIZ_TYPE = "businessType";
    public static final String COMBINED_PAGE_FINISH = "combinedPageFinish";
    public static final String COMBINED_PAGE_START = "combinedPageStart";
    public static final String COMBINE_DATA = "combined";
    public static final String CORE_TYPE = "coreType";
    public static final String CORE_VER = "coreVersion";
    public static final String C_BINGO_COUNT = "cBingoCount";
    public static final String ERR_CODE = "errorCode";
    public static final String ERR_MSG = "errorMsg";
    public static final String EXTRA = "extra";
    public static final String FCP = "fcp";
    public static final String FIRST_TIME = "firsttime";
    public static final String FP = "fp";
    public static final String GENTOKEN_FINISH = "gentokenFinish";
    public static final String GENTOKEN_START = "gentokenStart";
    public static final String GENTOKEN_TYPE = "gentokenType";
    public static final String H5_DATA = "h5data";
    public static final String HAS_XRENDER_CFG = "hasXrenderCfg";
    public static final String HEADER = "header";
    public static final String HTML_PRE_DOWNLOAD_END = "htmlPreEnd";
    public static final String HTML_PRE_DOWNLOAD_START = "htmlPreStart";
    public static final String HTML_PRE_DOWNLOAD_STATUS = "htmlPreDownload";
    public static final String HYBRID_CONFIG_VERSION = "c_version";
    public static final String HYBRID_FILE_VERSION = "f_version";
    public static final String HYBRID_ID = "hybridId";
    public static final String HYBRID_SHARED_CONFIG_VERSION = "cf_version";
    public static final String HYBRID_SHARED_FILE_VERSION = "cc_version";
    public static final String IMG_NUM = "imgNum";
    public static final String IMG_SIZE = "imgSize";
    public static final String INIT_FINISH = "initFinish";
    public static final String INIT_START = "initStart";
    public static final String INTERRUPT = "interrupt";
    public static final String IO_DETAIL = "ioDetail";
    public static final String IP = "resolvedIp";
    public static final String IS_PRE_RENDER = "isPreRender";
    public static final String JS_LISTEN_LCP = "javascript:try{const po = new PerformanceObserver((entryList) => {const entries = entryList.getEntries();const lastEntry = entries[entries.length - 1];window.jdhybrid_performance_lcp = lastEntry.renderTime || lastEntry.loadTime;});po.observe({type: 'largest-contentful-paint', buffered: true});}catch (e) {}";
    public static final String JS_PERFORMANCE_FORMAT = "javascript:try{window.%s&&%s.sendResource('%s',JSON.stringify(window.performance.timing),%s,JSON.stringify(window.performance.getEntriesByType('resource')),JSON.stringify(window.performance.getEntriesByType('paint')),window.jdhybrid_performance_lcp ? window.jdhybrid_performance_lcp.toString():'');}catch (e) {}";
    public static final String JS_PERFORMANCE_MEMORY = "JSON.stringify((function (window) {\n            var memory = {};\n            if (window.performance.memory) {\n                for (var key in window.performance.memory) {\n                    memory[key] = window.performance.memory[key];\n                }\n            }\n            return Object.keys(memory).length ? memory : '';\n        })(window))";
    public static final String JS_PERFORMANCE_NORESOURCE_FORMAT = "javascript:try{window.%s&&%s.sendResource('%s',JSON.stringify(window.performance.timing),%s,'',JSON.stringify(window.performance.getEntriesByType('paint')),window.jdhybrid_performance_lcp ? window.jdhybrid_performance_lcp.toString():'');}catch (e) {}";
    public static final String LAST_PAGE = "lastPage";
    public static final String LCP = "lcp";
    public static final String LOAD_START = "loadStart";
    public static final String MEMORY = "memory";
    public static final String NAME = "bName";
    public static final String NATIVE_REQUEST_STATUS = "nativeNetStatus";
    public static final String OCCUR_TIME = "occurTime";
    public static final String PAGE_COMMIT = "pageCommit";
    public static final String PAGE_FINISH = "pageFinish";
    public static final String PAGE_FINISH100 = "pageFinish100";
    public static final String PAGE_FROM = "from";
    public static final String PAGE_NAME = "pageName";
    public static final String PAGE_START = "pageStart";
    public static final String PAGE_TYPE = "pageType";
    public static final String PAINT = "paint";
    public static final String PATH = "path";
    public static final String PRELOAD_STATUS = "preloadStatus";
    public static final String RENDER = "render";
    private static final String REPORT_CHILD_ID = "4";
    private static final String REPORT_TYPE_ID = "7";
    public static final String RESOURCE_SIZE = "resourceSize";
    public static final String RESOURCE_TIMING = "resourceTiming";
    public static final String RESOURCE_TIMING_TMP = "resourceTiming_temp";
    private static final String SESSION = "hbdsession";
    public static final String SHARED_HYBRID_ID = "comhybridId";
    public static final String SHARED_OFFLINE_BINGO = "combingo";
    public static final String SOURCE_ERR = "sourceError";
    public static final String SSL_ERR = "sslError";
    private static final String TAG = "WebPerfManager";
    public static final String TIMING = "timing";
    public static final String UA = "useragent";
    public static final String URL = "url";
    public static final String XRENDER_CLICK = "xrenderClick";
    public static final String XRENDER_READY = "xrenderReady";
    public static final String X_B_TYPE = "b_type";
    private static WebPerfManager sInstance;
    private ExecutorService mThreadPool;
    private boolean mEnable = true;
    private volatile boolean mReportDetail = false;
    private int mMaxResourceNum = 0;
    private String[] mResourceWhiteList = null;
    private String[] mHostBlackList = null;
    private ConcurrentHashMap<String, Integer> mLoadTimes = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ReportRunnable implements Runnable {
        private String[] blackList;
        HashMap<String, String> data;
        boolean error;
        String failingUrl;

        ReportRunnable(String[] strArr, Map<String, String> map, boolean z, String str) {
            this.data = new HashMap<>(map);
            this.error = z;
            this.failingUrl = str;
            this.blackList = strArr;
        }

        private void compressPaint(Map<String, String> map) {
            JDJSONArray parseArray;
            String str = map.get(WebPerfManager.PAINT);
            if (TextUtils.isEmpty(str) || (parseArray = JDJSON.parseArray(str)) == null) {
                return;
            }
            for (int i2 = 0; i2 < parseArray.size(); i2++) {
                JDJSONObject jSONObject = parseArray.getJSONObject(i2);
                String d2S = d2S(jSONObject.getDoubleValue("startTime"));
                if ("first-paint".equals(jSONObject.getString("name"))) {
                    map.put(WebPerfManager.FP, d2S);
                } else if ("first-contentful-paint".equals(jSONObject.getString("name"))) {
                    map.put(WebPerfManager.FCP, d2S);
                }
            }
            map.remove(WebPerfManager.PAINT);
        }

        private void compressResourceTiming(Map<String, String> map) {
            JDJSONArray parseArray;
            String str;
            JDJSONArray jDJSONArray;
            long j2;
            long j3;
            int i2;
            String str2;
            int i3;
            int indexOf;
            int maxResourceNum = WebPerfManager.getInstance().getMaxResourceNum();
            String str3 = WebPerfManager.RESOURCE_TIMING_TMP;
            String str4 = map.get(WebPerfManager.RESOURCE_TIMING_TMP);
            if (TextUtils.isEmpty(str4) || maxResourceNum <= 0 || (parseArray = JDJSON.parseArray(str4)) == null) {
                return;
            }
            JDJSONArray jDJSONArray2 = new JDJSONArray();
            int i4 = 0;
            long j4 = 0;
            long j5 = 0;
            long j6 = 0;
            int i5 = 0;
            while (true) {
                if (i4 >= parseArray.size()) {
                    str = str3;
                    jDJSONArray = jDJSONArray2;
                    j2 = j4;
                    j3 = j5;
                    break;
                }
                JDJSONObject jSONObject = parseArray.getJSONObject(i4);
                String string = jSONObject.getString("initiatorType");
                JDJSONArray jDJSONArray3 = parseArray;
                str = str3;
                String string2 = jSONObject.getString("name");
                if ("xmlhttprequest".equals(string) && isBlackUrl(string2)) {
                    i2 = i4;
                } else {
                    i2 = i4;
                    if ("img".equals(string)) {
                        if (jSONObject.containsKey("encodedBodySize") && jSONObject.getLongValue("encodedBodySize") > 0) {
                            j4++;
                            j5 += jSONObject.getLongValue("encodedBodySize");
                        }
                    } else {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        j3 = j5;
                        String string3 = jSONObject.getString("entryType");
                        j2 = j4;
                        String string4 = jSONObject.getString("nextHopProtocol");
                        int i6 = maxResourceNum;
                        double doubleValue = jSONObject.getDoubleValue("startTime");
                        JDJSONArray jDJSONArray4 = jDJSONArray2;
                        double doubleValue2 = jSONObject.getDoubleValue("duration");
                        double doubleValue3 = jSONObject.getDoubleValue("redirectStart");
                        double doubleValue4 = jSONObject.getDoubleValue("redirectStart");
                        double doubleValue5 = jSONObject.getDoubleValue("redirectEnd");
                        double doubleValue6 = jSONObject.getDoubleValue("fetchStart");
                        double doubleValue7 = jSONObject.getDoubleValue("domainLookupStart");
                        double doubleValue8 = jSONObject.getDoubleValue("domainLookupEnd");
                        double doubleValue9 = jSONObject.getDoubleValue("connectStart");
                        double doubleValue10 = jSONObject.getDoubleValue("connectEnd");
                        double doubleValue11 = jSONObject.getDoubleValue("secureConnectionStart");
                        double doubleValue12 = jSONObject.getDoubleValue("requestStart");
                        double doubleValue13 = jSONObject.getDoubleValue("responseStart");
                        double doubleValue14 = jSONObject.getDoubleValue("responseEnd");
                        double longValue = jSONObject.getLongValue("transferSize");
                        long longValue2 = jSONObject.getLongValue("encodedBodySize");
                        long longValue3 = jSONObject.getLongValue("decodedBodySize");
                        if (string2 == null || (indexOf = string2.indexOf("?")) <= 0) {
                            str2 = "transferSize";
                        } else {
                            str2 = "transferSize";
                            String queryParameter = Uri.parse(string2).getQueryParameter("functionId");
                            string2 = string2.substring(0, indexOf);
                            if (!TextUtils.isEmpty(queryParameter)) {
                                string2 = string2 + "?functionId=" + queryParameter;
                            }
                        }
                        if (longValue2 > 0) {
                            j6 += longValue2;
                        }
                        jDJSONObject.put("name", (Object) string2);
                        jDJSONObject.put("entryType", (Object) string3);
                        jDJSONObject.put("nextHopProtocol", (Object) string4);
                        jDJSONObject.put("startTime", (Object) d2S(doubleValue));
                        jDJSONObject.put("duration", (Object) d2S(doubleValue2));
                        jDJSONObject.put("initiatorType", (Object) string);
                        jDJSONObject.put("workerStart", (Object) d2S(doubleValue3));
                        jDJSONObject.put("redirectStart", (Object) d2S(doubleValue4));
                        jDJSONObject.put("redirectEnd", (Object) d2S(doubleValue5));
                        jDJSONObject.put("fetchStart", (Object) d2S(doubleValue6));
                        jDJSONObject.put("domainLookupStart", (Object) d2S(doubleValue7));
                        jDJSONObject.put("domainLookupEnd", (Object) d2S(doubleValue8));
                        jDJSONObject.put("connectStart", (Object) d2S(doubleValue9));
                        jDJSONObject.put("connectEnd", (Object) d2S(doubleValue10));
                        jDJSONObject.put("secureConnectionStart", (Object) d2S(doubleValue11));
                        jDJSONObject.put("requestStart", (Object) d2S(doubleValue12));
                        jDJSONObject.put("responseStart", (Object) d2S(doubleValue13));
                        jDJSONObject.put("responseEnd", (Object) d2S(doubleValue14));
                        jDJSONObject.put(str2, (Object) d2S(longValue));
                        jDJSONObject.put("encodedBodySize", (Object) String.valueOf(longValue2));
                        jDJSONObject.put("decodedBodySize", (Object) String.valueOf(longValue3));
                        jDJSONArray = jDJSONArray4;
                        jDJSONArray.add(jDJSONObject);
                        int i7 = i5 + 1;
                        i3 = i6;
                        if (i7 >= i3) {
                            break;
                        }
                        i5 = i7;
                        j5 = j3;
                        j4 = j2;
                        i4 = i2 + 1;
                        jDJSONArray2 = jDJSONArray;
                        maxResourceNum = i3;
                        parseArray = jDJSONArray3;
                        str3 = str;
                    }
                }
                i3 = maxResourceNum;
                jDJSONArray = jDJSONArray2;
                i4 = i2 + 1;
                jDJSONArray2 = jDJSONArray;
                maxResourceNum = i3;
                parseArray = jDJSONArray3;
                str3 = str;
            }
            map.put(WebPerfManager.RESOURCE_TIMING, jDJSONArray.toJSONString());
            map.put(WebPerfManager.IMG_NUM, String.valueOf(j2));
            map.put(WebPerfManager.IMG_SIZE, String.valueOf(j3));
            map.put(WebPerfManager.RESOURCE_SIZE, String.valueOf(j6));
            map.remove(str);
        }

        private String d2S(double d) {
            return d == 0.0d ? "0" : String.format(Locale.getDefault(), "%.2f", Double.valueOf(d));
        }

        private boolean isBlackUrl(String str) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (this.blackList == null) {
                return false;
            }
            Uri parse = Uri.parse(str);
            if (parse.getHost() == null) {
                return true;
            }
            for (String str2 : this.blackList) {
                if (parse.getHost().endsWith(str2)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (WebPerfManager.getInstance().needReportDetail()) {
                    this.data.put(PerformanceManager.CUP, DeviceInfoUtil.getCpuInfo());
                    this.data.put(PerformanceManager.MEM_USED, DeviceInfoUtil.getMemAppUsed());
                    this.data.put(PerformanceManager.MEM_TOTAL, DeviceInfoUtil.getMemTotal());
                    this.data.put(PerformanceManager.IS_MEM_LOW, DeviceInfoUtil.isLowMem());
                }
                if (this.error && !this.data.containsKey("resolvedIp")) {
                    this.data.put("resolvedIp", WebPerfManager.getResolvedIpByHost(new URL(this.failingUrl).getHost()));
                }
                compressResourceTiming(this.data);
                compressPaint(this.data);
                this.data.put(WebPerfManager.FIRST_TIME, String.valueOf(WebPerfManager.getInstance().getLocalPV(this.data.get("url"))));
                this.data.put(WebPerfManager.SESSION, WebHybridUtils.HDB_SESSION);
            } catch (Exception e2) {
                Log.e(WebPerfManager.TAG, e2.getMessage(), e2);
            }
            WebPerfManager.getInstance().report(this.data);
        }
    }

    private WebPerfManager() {
        init();
    }

    public static WebPerfManager getInstance() {
        if (sInstance == null) {
            synchronized (WebPerfManager.class) {
                if (sInstance == null) {
                    sInstance = new WebPerfManager();
                }
            }
        }
        return sInstance;
    }

    private String getKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme() != null ? parse.getScheme() : "";
            if (parse.getHost() != null) {
                scheme = scheme + parse.getHost();
            }
            if (parse.getPath() != null) {
                return scheme + parse.getPath();
            }
            return scheme;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getResolvedIpByHost(String str) {
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

    private void init() {
        try {
            StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "7", "4");
            if (stategyEntitiy == null) {
                return;
            }
            boolean z = true;
            if (this.mEnable) {
                this.mThreadPool = Executors.newFixedThreadPool(1);
            }
            JDJSONObject parseObject = JDJSON.parseObject(stategyEntitiy.param);
            if (parseObject != null) {
                if (1 != parseObject.optInt("detail", 0)) {
                    z = false;
                }
                this.mReportDetail = z;
                this.mMaxResourceNum = parseObject.optInt("maxResourceNum", 0);
                String string = parseObject.getString("whiteList");
                if (!TextUtils.isEmpty(string)) {
                    this.mResourceWhiteList = string.split(";");
                }
                String string2 = parseObject.getString("hbl");
                if (!TextUtils.isEmpty(string2)) {
                    this.mHostBlackList = string2.split(";");
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "\u65b0\u7248\u6027\u80fd\u76d1\u63a7\u521d\u59cb\u5316, enable = " + this.mEnable + ", param = " + parseObject.toJSONString());
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void report(HashMap<String, String> hashMap) {
        if (!this.mEnable || hashMap == null || hashMap.isEmpty()) {
            return;
        }
        try {
            if (!hashMap.containsKey("occurTime")) {
                double currentTimeMillis = System.currentTimeMillis();
                Double.isNaN(currentTimeMillis);
                hashMap.put("occurTime", new DecimalFormat("0.000000").format(currentTimeMillis / 1000.0d));
            }
            hashMap.put("typeId", "7");
            hashMap.put("chId", "4");
            if (OKLog.D) {
                OKLog.d(TAG, "\u4e0a\u62a5\u6027\u80fd\u6570\u636e = " + hashMap.toString());
            }
            PerformanceReporter.reportData(hashMap);
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
            ExceptionReporter.reportWebViewCommonError("WebPerfV2ReportErr", "", "", e2.toString());
        }
    }

    private boolean resourceEnable(String str) {
        String[] strArr;
        if (!TextUtils.isEmpty(str) && (strArr = this.mResourceWhiteList) != null && strArr.length != 0 && this.mMaxResourceNum > 0) {
            Uri parse = Uri.parse(str);
            if (parse.getHost() != null) {
                return WebUtils.hostListWithKeyWord(str, parse.getHost(), this.mResourceWhiteList);
            }
        }
        return false;
    }

    public int getLocalPV(String str) {
        Integer num;
        String key = getKey(str);
        if (TextUtils.isEmpty(str) || (num = this.mLoadTimes.get(key)) == null) {
            return 1;
        }
        return num.intValue();
    }

    public int getMaxResourceNum() {
        return this.mMaxResourceNum;
    }

    public void getPerformance(WebView webView, String str, String str2) {
        String format;
        if (webView != null) {
            String str3 = WebUiConstans.JavaInterfaceNames.PERFORMANCE;
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("memoryReport", false);
            boolean resourceEnable = resourceEnable(str);
            String str4 = JS_PERFORMANCE_MEMORY;
            if (resourceEnable) {
                Object[] objArr = new Object[4];
                objArr[0] = str3;
                objArr[1] = str3;
                objArr[2] = str2;
                if (!switchBooleanValue) {
                    str4 = "''";
                }
                objArr[3] = str4;
                format = String.format(JS_PERFORMANCE_FORMAT, objArr);
            } else {
                Object[] objArr2 = new Object[4];
                objArr2[0] = str3;
                objArr2[1] = str3;
                objArr2[2] = str2;
                if (!switchBooleanValue) {
                    str4 = "''";
                }
                objArr2[3] = str4;
                format = String.format(JS_PERFORMANCE_NORESOURCE_FORMAT, objArr2);
            }
            webView.loadUrl(format);
        }
    }

    public boolean isEnable() {
        return this.mEnable;
    }

    public boolean needReportDetail() {
        return this.mReportDetail;
    }

    public void onLCPListening(WebView webView) {
        if (webView == null || !SwitchQueryFetcher.getSwitchBooleanValue("webview_lcp", false)) {
            return;
        }
        webView.loadUrl(JS_LISTEN_LCP);
    }

    public void onLoad(String str) {
        String key = getKey(str);
        if (TextUtils.isEmpty(key)) {
            return;
        }
        Integer num = this.mLoadTimes.get(key);
        this.mLoadTimes.put(key, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public synchronized void reportRecord(WebPerformance webPerformance, boolean z) {
        if (this.mEnable && webPerformance != null && !webPerformance.isReported()) {
            if (webPerformance.getData() != null && !webPerformance.getData().isEmpty()) {
                if (this.mThreadPool != null) {
                    try {
                        if (!webPerformance.containKeyExact("occurTime")) {
                            double currentTimeMillis = System.currentTimeMillis();
                            Double.isNaN(currentTimeMillis);
                            webPerformance.appendData("occurTime", new DecimalFormat("0.000000").format(currentTimeMillis / 1000.0d));
                        }
                        if (!z) {
                            webPerformance.setReported(true);
                        }
                        this.mThreadPool.submit(new ReportRunnable(this.mHostBlackList, webPerformance.getData(), webPerformance.isError(), !TextUtils.isEmpty(webPerformance.getFailingUrl()) ? webPerformance.getFailingUrl() : webPerformance.getUrl()));
                    } catch (Exception e2) {
                        Log.e(TAG, e2.getMessage(), e2);
                    }
                }
            }
        }
    }
}
