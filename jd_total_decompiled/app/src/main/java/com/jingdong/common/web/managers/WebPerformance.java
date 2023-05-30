package com.jingdong.common.web.managers;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.sdk.log.Log;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class WebPerformance {
    private long createTime;
    private volatile Map<String, String> data;
    private volatile JDJSONObject extraData;
    private String failingUrl;
    private JDJSONArray httpErrJsonArray;
    private ConcurrentHashMap<String, Long> ioDetailMap;
    private int mBingoCount;
    private int mCBingoCount;
    private JDJSONArray sslErrJsonArray;
    private String url;
    private volatile boolean isError = false;
    private volatile boolean isNetError = false;
    private volatile boolean reported = false;
    private volatile String interruptMsg = null;
    private boolean combineWithNext = false;
    private boolean directReport = false;

    public WebPerformance(long j2) {
        this.createTime = j2;
    }

    public void addHttpErr(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        if (this.httpErrJsonArray == null) {
            this.httpErrJsonArray = new JDJSONArray();
        }
        this.httpErrJsonArray.add(jDJSONObject);
        appendData(WebPerfManager.SOURCE_ERR, this.httpErrJsonArray.toJSONString());
    }

    public void addIoDetail(String str, long j2) {
        if (TextUtils.isEmpty(str) || j2 <= 0) {
            return;
        }
        if (this.ioDetailMap == null) {
            this.ioDetailMap = new ConcurrentHashMap<>();
        }
        this.ioDetailMap.put(str, Long.valueOf(j2));
    }

    public void addSslErr(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        if (this.sslErrJsonArray == null) {
            this.sslErrJsonArray = new JDJSONArray();
        }
        this.sslErrJsonArray.add(jDJSONObject);
        appendData(WebPerfManager.SSL_ERR, this.sslErrJsonArray.toJSONString());
    }

    public void appendData(String str, String str2) {
        if (WebPerfManager.BINGO_COUNT.equals(str)) {
            this.mBingoCount++;
        } else if (WebPerfManager.C_BINGO_COUNT.equals(str)) {
            this.mCBingoCount++;
        } else if (isReported() || TextUtils.isEmpty(str) || str2 == null) {
        } else {
            if (this.data == null) {
                this.data = new ConcurrentHashMap();
            }
            this.data.put(str, str2);
            if ("url".equals(str)) {
                this.url = str2;
            }
        }
    }

    public void appendExtra(String str, Object obj) {
        if (isReported() || TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        if (this.extraData == null) {
            this.extraData = new JDJSONObject();
        }
        if (this.data == null) {
            this.data = new ConcurrentHashMap();
        }
        try {
            if (obj instanceof String) {
                this.extraData.put(str, (Object) ((String) obj));
            } else if (obj instanceof JDJSONObject) {
                this.extraData.put(str, (Object) ((JDJSONObject) obj));
            } else if (obj instanceof JDJSONArray) {
                this.extraData.put(str, (Object) ((JDJSONArray) obj));
            } else if (obj instanceof Map) {
                this.extraData.put(str, (Object) ((Map) obj));
            } else {
                if (!(obj instanceof JSONObject) && !(obj instanceof JSONArray)) {
                    this.extraData.put(str, obj);
                }
                this.extraData.put(str, (Object) obj.toString());
            }
            this.data.put("extra", this.extraData.toJSONString());
        } catch (Exception e2) {
            Log.e("WebPerformance", e2.getMessage(), e2);
        }
    }

    public void appendH5Data(String str) {
        if (isReported() || TextUtils.isEmpty(str) || this.data == null) {
            return;
        }
        try {
            JDJSONArray parseArray = JDJSON.parseArray(this.data.get(WebPerfManager.H5_DATA));
            if (parseArray == null) {
                parseArray = new JDJSONArray();
            }
            parseArray.add(str);
            this.data.put(WebPerfManager.H5_DATA, parseArray.toJSONString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void appendToExtraJsonObj(String str, String str2, Object obj) {
        if (isReported() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || obj == null) {
            return;
        }
        if (this.extraData == null) {
            this.extraData = new JDJSONObject();
        }
        if (this.data == null) {
            this.data = new ConcurrentHashMap();
        }
        try {
            Object obj2 = this.extraData.get(str);
            if (!(obj2 instanceof JDJSONObject)) {
                obj2 = new JDJSONObject(5);
            }
            JDJSONObject jDJSONObject = (JDJSONObject) obj2;
            jDJSONObject.put(str2, obj);
            this.extraData.put(str, (Object) jDJSONObject);
            this.data.put("extra", this.extraData.toJSONString());
        } catch (Exception e2) {
            Log.e("WebPerformance", e2.getMessage(), e2);
        }
    }

    public boolean containKeyExact(String str) {
        if (TextUtils.isEmpty(str) || this.data == null || this.data.isEmpty()) {
            return false;
        }
        return this.data.containsKey(str);
    }

    public List<String> containKeyInclude(String str) {
        if (TextUtils.isEmpty(str) || this.data == null || this.data.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : this.data.keySet()) {
            if (str2.contains(str)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public int getBingoCount() {
        return this.mBingoCount;
    }

    public int getCBingoCount() {
        return this.mCBingoCount;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public String getFailingUrl() {
        return this.failingUrl;
    }

    public String getInterruptedMsg() {
        return this.interruptMsg;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isCombineWithNext() {
        return this.combineWithNext;
    }

    public boolean isDirectReport() {
        return this.directReport;
    }

    public boolean isError() {
        return this.isError;
    }

    public boolean isInterrupted() {
        return !TextUtils.isEmpty(this.interruptMsg);
    }

    public boolean isNetError() {
        return this.isNetError;
    }

    public boolean isReported() {
        return this.reported;
    }

    public void preReport() {
        if (isReported() || this.data == null) {
            return;
        }
        this.data.put(WebPerfManager.BINGO_COUNT, String.valueOf(this.mBingoCount));
        this.data.put(WebPerfManager.C_BINGO_COUNT, String.valueOf(this.mCBingoCount));
    }

    public void recordIoDetail() {
        ConcurrentHashMap<String, Long> concurrentHashMap = this.ioDetailMap;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
            return;
        }
        JDJSONArray jDJSONArray = new JDJSONArray();
        for (String str : this.ioDetailMap.keySet()) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("url", (Object) str);
            jDJSONObject.put("time", (Object) this.ioDetailMap.get(str));
            jDJSONArray.add(jDJSONObject);
        }
        appendData(WebPerfManager.IO_DETAIL, jDJSONArray.toJSONString());
    }

    public void removeData(String str) {
        if (isReported() || TextUtils.isEmpty(str) || this.data == null || !this.data.containsKey(str)) {
            return;
        }
        this.data.remove(str);
    }

    public void setCombineWithNext(boolean z) {
        this.combineWithNext = z;
    }

    public void setDirectReport(boolean z) {
        this.directReport = z;
    }

    public void setError(boolean z) {
        this.isError = z;
    }

    public void setExtra(String str) {
        if (isReported() || TextUtils.isEmpty(str)) {
            return;
        }
        if (this.data == null) {
            this.data = new ConcurrentHashMap();
        }
        this.data.put("extra", str);
    }

    public void setFailingUrl(String str) {
        this.failingUrl = str;
    }

    public void setInterrupted(String str) {
        if (isReported()) {
            return;
        }
        this.interruptMsg = str;
    }

    public void setNetError(boolean z) {
        this.isNetError = z;
        if (z) {
            setError(true);
        }
    }

    public void setOccurTime(long j2) {
        if (j2 <= 0) {
            return;
        }
        double d = j2;
        Double.isNaN(d);
        try {
            appendData("occurTime", new DecimalFormat("0.000000").format(d / 1000.0d));
        } catch (Exception e2) {
            Log.e("WebPerformance", e2.getMessage(), e2);
        }
    }

    public void setPath(List<String> list) {
        if (isReported()) {
            return;
        }
        if (this.data == null) {
            this.data = new ConcurrentHashMap();
        }
        this.data.put("path", WebUtils.stringifyUrlHistory(list));
    }

    public void setReported(boolean z) {
        this.reported = z;
    }

    @NonNull
    public String toString() {
        return this.data != null ? this.data.toString() : "";
    }

    public String getData(String str) {
        if (TextUtils.isEmpty(str) || this.data == null) {
            return null;
        }
        return this.data.get(str);
    }

    public void appendData(String str, Map<String, String> map) {
        if (isReported() || TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return;
        }
        if (this.data == null) {
            this.data = new ConcurrentHashMap();
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        for (String str2 : map.keySet()) {
            jDJSONObject.put(str2, (Object) map.get(str2));
        }
        this.data.put(str, jDJSONObject.toJSONString());
    }
}
