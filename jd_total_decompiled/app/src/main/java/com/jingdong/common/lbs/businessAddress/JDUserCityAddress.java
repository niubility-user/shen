package com.jingdong.common.lbs.businessAddress;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDUserCityAddress {
    public static final String TYPE_CHOOSE = "choose";
    public static final String TYPE_GIS = "gis";
    private String addressTitle;
    private String city;
    private int cityCode;
    private String code;
    private String district;
    private int districtCode;
    private boolean isDifferent;
    private double lat;
    private int level;
    private double lng;
    private String message;
    private String province;
    private int provinceCode;
    private String town;
    private int townCode;
    private String type;

    public String getAddressTitle() {
        return TextUtils.isEmpty(this.addressTitle) ? "" : this.addressTitle;
    }

    public String getCity() {
        return TextUtils.isEmpty(this.city) ? "" : this.city;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public String getCode() {
        return TextUtils.isEmpty(this.code) ? "" : this.code;
    }

    public String getDistrict() {
        return TextUtils.isEmpty(this.district) ? "" : this.district;
    }

    public int getDistrictCode() {
        return this.districtCode;
    }

    public double getLat() {
        if (Double.isNaN(this.lat)) {
            return 0.0d;
        }
        return this.lat;
    }

    public int getLevel() {
        return this.level;
    }

    public double getLng() {
        if (Double.isNaN(this.lng)) {
            return 0.0d;
        }
        return this.lng;
    }

    public String getMessage() {
        return TextUtils.isEmpty(this.message) ? "" : this.message;
    }

    public String getProvince() {
        return TextUtils.isEmpty(this.province) ? "" : this.province;
    }

    public int getProvinceCode() {
        return this.provinceCode;
    }

    public String getTown() {
        return TextUtils.isEmpty(this.town) ? "" : this.town;
    }

    public int getTownCode() {
        return this.townCode;
    }

    public String getType() {
        return TextUtils.isEmpty(this.type) ? "" : this.type;
    }

    public String getUserCityAddressJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", getCode());
            jSONObject.put("message", getMessage());
            jSONObject.put(HybridSDK.LNG, getLng());
            jSONObject.put("lat", getLat());
            jSONObject.put("provinceCode", this.provinceCode);
            jSONObject.put("province", getProvince());
            jSONObject.put("cityCode", this.cityCode);
            jSONObject.put("city", getCity());
            jSONObject.put(Constant.KEY_DISTRICT_CODE, this.districtCode);
            jSONObject.put("district", getDistrict());
            jSONObject.put("townCode", this.townCode);
            jSONObject.put("town", getTown());
            jSONObject.put("type", getType());
            jSONObject.put("addressTitle", getAddressTitle());
            jSONObject.put("isDifferent", this.isDifferent);
            jSONObject.put("level", this.level);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public boolean isDifferent() {
        return this.isDifferent;
    }

    public void setAddressTitle(String str) {
        this.addressTitle = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityCode(int i2) {
        this.cityCode = i2;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setDifferent(boolean z) {
        this.isDifferent = z;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setDistrictCode(int i2) {
        this.districtCode = i2;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLevel(int i2) {
        this.level = i2;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setProvinceCode(int i2) {
        this.provinceCode = i2;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public void setTownCode(int i2) {
        this.townCode = i2;
    }

    public void setType(String str) {
        this.type = str;
    }
}
