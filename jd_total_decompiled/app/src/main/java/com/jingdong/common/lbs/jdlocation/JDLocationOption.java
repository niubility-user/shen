package com.jingdong.common.lbs.jdlocation;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDLocationOption {
    private boolean isEncrypt;
    private boolean isNeedDetail;
    private boolean isNeedIP;
    private double lat;
    private double lng;
    private long requestTime;
    private String sceneId;
    private String businessId = JDLocationSDK.LBS_BUSINESS_ID;
    private boolean isManto = false;

    public String getBusinessId() {
        return this.businessId;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("businessId", this.businessId);
            jSONObject.put("sceneId", getSceneId());
            StringBuilder sb = new StringBuilder();
            sb.append(this.lat);
            jSONObject.put("lat", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.lng);
            jSONObject.put(HybridSDK.LNG, sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.isNeedDetail);
            jSONObject.put("isNeedDetail", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.isNeedIP);
            jSONObject.put("isNeedIP", sb4.toString());
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public long getRequestTime() {
        return this.requestTime;
    }

    public String getSceneId() {
        return TextUtils.isEmpty(this.sceneId) ? "" : this.sceneId;
    }

    public boolean isEncrypt() {
        return this.isEncrypt;
    }

    public boolean isManto() {
        return this.isManto;
    }

    public boolean isNeedDetail() {
        return this.isNeedDetail;
    }

    public boolean isNeedIP() {
        return this.isNeedIP;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setEncrypt(boolean z) {
        this.isEncrypt = z;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setManto(boolean z) {
        this.isManto = z;
    }

    public void setNeedDetail(boolean z) {
        this.isNeedDetail = z;
    }

    public void setNeedIP(boolean z) {
        this.isNeedIP = z;
    }

    public void setRequestTime(long j2) {
        this.requestTime = j2;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }
}
