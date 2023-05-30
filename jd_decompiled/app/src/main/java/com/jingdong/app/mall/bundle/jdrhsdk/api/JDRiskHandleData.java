package com.jingdong.app.mall.bundle.jdrhsdk.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JDRiskHandleData {
    private int code;
    private String data;

    public int getCode() {
        return this.code;
    }

    public String getData() {
        return TextUtils.isEmpty(this.data) ? "" : this.data;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", getCode());
            jSONObject.put("data", getData());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setData(String str) {
        this.data = str;
    }
}
