package com.jingdong.common.lbs.search;

import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDLbsSearchAddress {
    private String address;
    private String category;
    private String city;
    private String district;
    private double lat;
    private double lng;
    private String province;
    private String title;
    private String town;

    public String getAddress() {
        String str = this.address;
        return str == null ? "" : str;
    }

    public String getCategory() {
        String str = this.category;
        return str == null ? "" : str;
    }

    public String getCity() {
        String str = this.city;
        return str == null ? "" : str;
    }

    public String getDistrict() {
        String str = this.district;
        return str == null ? "" : str;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("province", this.province);
            jSONObject.put("city", this.city);
            jSONObject.put("district", this.district);
            jSONObject.put("town", this.town);
            jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, this.address);
            jSONObject.put("title", this.title);
            jSONObject.put("category", this.category);
            jSONObject.put("lat", this.lat);
            jSONObject.put(HybridSDK.LNG, this.lng);
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

    public String getProvince() {
        String str = this.province;
        return str == null ? "" : str;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public String getTown() {
        String str = this.town;
        return str == null ? "" : str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistrict(String str) {
        this.district = str;
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

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTown(String str) {
        this.town = str;
    }
}
