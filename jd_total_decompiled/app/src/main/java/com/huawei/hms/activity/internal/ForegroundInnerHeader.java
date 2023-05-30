package com.huawei.hms.activity.internal;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class ForegroundInnerHeader {
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1180c;

    public void fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = JsonUtil.getIntValue(jSONObject, "apkVersion");
            this.b = JsonUtil.getStringValue(jSONObject, "action");
            this.f1180c = JsonUtil.getStringValue(jSONObject, "responseCallbackKey");
        } catch (JSONException e2) {
            HMSLog.e("ForegroundInnerHeader", "fromJson failed: " + e2.getMessage());
        }
    }

    public String getAction() {
        return this.b;
    }

    public int getApkVersion() {
        return this.a;
    }

    public String getResponseCallbackKey() {
        return this.f1180c;
    }

    public void setAction(String str) {
        this.b = str;
    }

    public void setApkVersion(int i2) {
        this.a = i2;
    }

    public void setResponseCallbackKey(String str) {
        this.f1180c = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("apkVersion", this.a);
            jSONObject.put("action", this.b);
            jSONObject.put("responseCallbackKey", this.f1180c);
        } catch (JSONException e2) {
            HMSLog.e("ForegroundInnerHeader", "ForegroundInnerHeader toJson failed: " + e2.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "apkVersion:" + this.a + ", action:" + this.b + ", responseCallbackKey:" + this.f1180c;
    }
}
