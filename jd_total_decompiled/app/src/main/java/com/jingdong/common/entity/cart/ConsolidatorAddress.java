package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;

/* loaded from: classes5.dex */
public class ConsolidatorAddress {
    private int cityId;
    private int countryId;
    private String detailAddr;
    private String name;
    private int provinceId;
    private String tel;
    private int townId;
    private long venderId;

    public ConsolidatorAddress(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.venderId = jDJSONObject.optLong("venderId");
        this.provinceId = jDJSONObject.optInt(JDWeatherActionKey.PROVINCE_ID);
        this.cityId = jDJSONObject.optInt(JDWeatherActionKey.CITY_ID);
        this.countryId = jDJSONObject.optInt(JDWeatherActionKey.COUNTRY_ID);
        this.townId = jDJSONObject.optInt(JDWeatherActionKey.TOWN_ID);
        this.detailAddr = jDJSONObject.optString("detailAddr");
        this.name = jDJSONObject.optString("name");
        this.tel = jDJSONObject.optString("tel");
    }

    public int getCityId() {
        return this.cityId;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public String getDetailAddr() {
        return this.detailAddr;
    }

    public String getName() {
        return this.name;
    }

    public int getProvinceId() {
        return this.provinceId;
    }

    public String getTel() {
        return this.tel;
    }

    public int getTownId() {
        return this.townId;
    }

    public long getVenderId() {
        return this.venderId;
    }

    public void setCityId(int i2) {
        this.cityId = i2;
    }

    public void setCountryId(int i2) {
        this.countryId = i2;
    }

    public void setDetailAddr(String str) {
        this.detailAddr = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProvinceId(int i2) {
        this.provinceId = i2;
    }

    public void setTel(String str) {
        this.tel = str;
    }

    public void setTownId(int i2) {
        this.townId = i2;
    }

    public void setVenderId(long j2) {
        this.venderId = j2;
    }
}
