package com.jd.lib.babel.task.servicekit;

import com.jd.lib.babel.servicekit.networkkit.Request;
import com.jd.lib.babel.task.TaskInfo;
import com.jd.lib.babel.task.common.CommonUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class RequestInner {
    private int attempts;
    private int diskCacheTime;
    private String finalUrl;
    private String functionId;
    private String functionIdVersion;
    private Map<String, String> headers;
    private String host;
    private boolean isUseDiskCache;
    private JSONObject jsonParams;
    private Request.Listener mListener;
    private int mMethod;
    private Request mRequest;
    private Map<String, String> mapParams;

    /* loaded from: classes14.dex */
    public interface Listener extends Request.Listener {
    }

    public void cancel() {
        Request request = this.mRequest;
        if (request != null) {
            request.cacel();
        }
    }

    public int getAttempts() {
        return this.attempts;
    }

    public Map<String, String> getBody() {
        JSONObject jSONObject = this.jsonParams;
        if (jSONObject == null) {
            return null;
        }
        Map<String, String> jsonToMap = CommonUtil.jsonToMap(jSONObject.toString());
        jsonToMap.put("taskSDKVersion", TaskInfo.getBabelVersion());
        jsonToMap.put("clientLanguage", TaskInfo.getBabelLag());
        return jsonToMap;
    }

    public int getDiskCacheTime() {
        return this.diskCacheTime;
    }

    public String getFinalUrl() {
        return this.finalUrl;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getFunctionIdVersion() {
        return this.functionIdVersion;
    }

    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        return this.headers;
    }

    public String getHost() {
        return this.host;
    }

    public JSONObject getJsonParams() {
        return this.jsonParams;
    }

    public Request.Listener getListener() {
        return this.mListener;
    }

    public Map<String, String> getMapParams() {
        return this.mapParams;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public boolean isUseDiskCache() {
        return this.isUseDiskCache;
    }

    public void putJsonParam(String str, Object obj) {
        if (this.jsonParams == null) {
            this.jsonParams = new JSONObject();
        }
        try {
            this.jsonParams.put(str, obj);
        } catch (JSONException unused) {
        }
    }

    public void putMapParams(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (this.mapParams == null) {
            this.mapParams = new HashMap();
        }
        this.mapParams.put(str, str2);
    }

    public Request request() {
        if (this.mRequest == null) {
            this.mRequest = new Request(getMethod(), getHost(), getFunctionId(), getFunctionIdVersion(), getHeaders(), getBody(), getFinalUrl(), getAttempts(), getListener(), false);
        }
        return this.mRequest;
    }

    public void setAttempts(int i2) {
        this.attempts = i2;
    }

    public void setDiskCacheTime(int i2) {
        this.diskCacheTime = i2;
    }

    public void setFinalUrl(String str) {
        this.finalUrl = str;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setFunctionIdVersion(String str) {
        this.functionIdVersion = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setListener(Request.Listener listener) {
        this.mListener = listener;
    }

    public void setMethod(int i2) {
        this.mMethod = i2;
    }

    public void setUseDiskCache(boolean z) {
        this.isUseDiskCache = z;
    }
}
