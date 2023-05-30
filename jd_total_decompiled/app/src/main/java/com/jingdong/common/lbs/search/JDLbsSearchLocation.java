package com.jingdong.common.lbs.search;

import com.jd.libs.hybrid.HybridSDK;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDLbsSearchLocation {
    private String city;
    private int cityCode;
    private String detailAddress;
    private String district;
    private int districtCode;
    private double lat;
    private double lng;
    private String province;
    private int provinceCode;
    private String town;
    private int townCode;

    public String getCity() {
        String str = this.city;
        return str == null ? "" : str;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public String getDetailAddress() {
        String str = this.detailAddress;
        return str == null ? "" : str;
    }

    public String getDistrict() {
        String str = this.district;
        return str == null ? "" : str;
    }

    public int getDistrictCode() {
        return this.districtCode;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("province", this.province);
            jSONObject.put("city", this.city);
            jSONObject.put("district", this.district);
            jSONObject.put("town", this.town);
            jSONObject.put("detailAddress", this.detailAddress);
            jSONObject.put("provinceCode", this.provinceCode);
            jSONObject.put("cityCode", this.cityCode);
            jSONObject.put(Constant.KEY_DISTRICT_CODE, this.districtCode);
            jSONObject.put("townCode", this.townCode);
            jSONObject.put("lat", this.lat);
            jSONObject.put(HybridSDK.LNG, this.lng);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return e2.getMessage();
        }
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String getProvince() {
        String str = this.province;
        return str == null ? "" : str;
    }

    public int getProvinceCode() {
        return this.provinceCode;
    }

    public String getTown() {
        String str = this.town;
        return str == null ? "" : str;
    }

    public int getTownCode() {
        return this.townCode;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityCode(int i2) {
        this.cityCode = i2;
    }

    public void setDetailAddress(String str) {
        this.detailAddress = str;
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

    public void setLng(double d) {
        this.lng = d;
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
}
