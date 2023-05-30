package com.jd.verify.model;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class IninVerifyInfo {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f7191c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7192e;

    /* renamed from: f  reason: collision with root package name */
    private int f7193f;

    /* renamed from: g  reason: collision with root package name */
    private HashMap<String, byte[]> f7194g;

    /* renamed from: h  reason: collision with root package name */
    private String f7195h;

    /* renamed from: i  reason: collision with root package name */
    private String f7196i;

    /* renamed from: j  reason: collision with root package name */
    private String f7197j;

    /* renamed from: k  reason: collision with root package name */
    private int f7198k;

    public IninVerifyInfo(JSONObject jSONObject, String str) {
        this.a = jSONObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT);
        this.b = jSONObject.optInt("code");
        this.d = jSONObject.optString("msg");
        this.f7192e = jSONObject.optString(WebPerfManager.FP);
        this.f7193f = jSONObject.optInt("tp");
        this.f7195h = jSONObject.optString("st");
        this.f7196i = jSONObject.optString("vt");
        this.f7198k = jSONObject.optInt("type");
        this.f7194g = a(jSONObject.optString("img"), this.f7193f);
        this.f7191c = jSONObject.optInt("s_code");
        this.f7197j = str;
    }

    private HashMap<String, byte[]> a(String str, int i2) {
        HashMap<String, byte[]> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (i2 == 2) {
                    hashMap.put("b1", a(jSONObject.optString("b1")));
                    hashMap.put("b2", a(jSONObject.optString("b2")));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    public byte[] getBgImg() {
        return this.f7194g.get("b1");
    }

    public byte[] getClickImg() {
        return this.f7194g.get("b2");
    }

    public int getCode() {
        return this.b;
    }

    public String getErrorMsg() {
        return this.d;
    }

    public int getErrorType() {
        return this.f7198k;
    }

    public String getFp() {
        return this.f7192e;
    }

    public HashMap<String, byte[]> getImgs() {
        return this.f7194g;
    }

    public String getSession_id() {
        return this.f7197j;
    }

    public String getSt() {
        return this.f7195h;
    }

    public int getStatusCode() {
        return this.a;
    }

    public int getTp() {
        return this.f7193f;
    }

    public String getVt() {
        return this.f7196i;
    }

    public int getsCode() {
        return this.f7191c;
    }

    public boolean isSuccess() {
        return this.b == 0;
    }

    public void setCode(int i2) {
        this.b = i2;
    }

    public void setErrorMsg(String str) {
        this.d = str;
    }

    public void setFp(String str) {
        this.f7192e = str;
    }

    public void setImgs(HashMap<String, byte[]> hashMap) {
        this.f7194g = hashMap;
    }

    public void setSession_id(String str) {
        this.f7197j = str;
    }

    public void setSt(String str) {
        this.f7195h = str;
    }

    public void setStatusCode(int i2) {
        this.a = i2;
    }

    public void setTp(int i2) {
        this.f7193f = i2;
    }

    public void setVt(String str) {
        this.f7196i = str;
    }

    public void setsCode(int i2) {
        this.f7191c = i2;
    }

    private byte[] a(String str) {
        String substring = str.substring(22);
        Base64.decode(substring, 0).toString();
        return Base64.decode(substring, 0);
    }

    public IninVerifyInfo(JSONObject jSONObject) {
        this.a = jSONObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT);
        this.f7198k = jSONObject.optInt("type");
        this.b = jSONObject.optInt("code");
        this.f7191c = jSONObject.optInt("s_code");
        this.d = jSONObject.optString("msg");
        this.f7192e = jSONObject.optString(WebPerfManager.FP);
        this.f7193f = jSONObject.optInt("tp");
        this.f7195h = jSONObject.optString("st");
        this.f7196i = jSONObject.optString("vt");
        this.f7194g = a(jSONObject.optString("img"), this.f7193f);
        this.f7197j = "";
    }
}
