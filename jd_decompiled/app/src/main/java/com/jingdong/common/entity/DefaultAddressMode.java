package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class DefaultAddressMode implements Serializable {
    public static final int DIRECT_WARE = 0;
    private static final String TAG = "DefaultAddressMode";
    private static final long serialVersionUID = -2414434762700652342L;
    private String cityId;
    private String cityName;
    private String countyId;
    private String countyName;
    private String functionId;
    private String provinceId;
    private String provinceName;
    private String skuId;
    private String townId;
    private String townName;

    public DefaultAddressMode() {
    }

    public static ArrayList<DefaultAddressMode> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2);
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 == 0 || i2 == 1112) {
            setProvinceId(jDJSONObject.getString(JDWeatherActionKey.PROVINCE_ID));
            setProvinceName(jDJSONObject.getString("provinceName"));
            setCityId(jDJSONObject.getString(JDWeatherActionKey.CITY_ID));
            setCityName(jDJSONObject.getString("cityName"));
            setCountyId(jDJSONObject.getString("countyId"));
            setCountyName(jDJSONObject.getString("countyName"));
            setTownId(jDJSONObject.getString(JDWeatherActionKey.TOWN_ID));
            setTownName(jDJSONObject.getString("townName"));
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "skuId")) {
                setSkuId(jDJSONObject.getString("skuId"));
            }
            if (JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "functionId")) {
                return;
            }
            setFunctionId(jDJSONObject.getString("functionId"));
        }
    }

    public String getCityId() {
        return TextUtils.isEmpty(this.cityId) ? "0" : this.cityId;
    }

    public String getCityName() {
        return TextUtils.isEmpty(this.cityName) ? "" : this.cityName;
    }

    public String getCountyId() {
        return TextUtils.isEmpty(this.countyId) ? "0" : this.countyId;
    }

    public String getCountyName() {
        return TextUtils.isEmpty(this.countyName) ? "" : this.countyName;
    }

    public String getFunctionId() {
        return TextUtils.isEmpty(this.functionId) ? "" : this.functionId;
    }

    public String getProvinceId() {
        return TextUtils.isEmpty(this.provinceId) ? "0" : this.provinceId;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.provinceName) ? "" : this.provinceName;
    }

    public String getSkuId() {
        return TextUtils.isEmpty(this.skuId) ? "" : this.skuId;
    }

    public String getTownId() {
        return TextUtils.isEmpty(this.townId) ? "0" : this.townId;
    }

    public String getTownName() {
        return TextUtils.isEmpty(this.townName) ? "" : this.townName;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setCountyId(String str) {
        this.countyId = str;
    }

    public void setCountyName(String str) {
        this.countyName = str;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setProvinceId(String str) {
        this.provinceId = str;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setTownId(String str) {
        this.townId = str;
    }

    public void setTownName(String str) {
        this.townName = str;
    }

    public DefaultAddressMode(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public static ArrayList<DefaultAddressMode> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<DefaultAddressMode> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return null;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                DefaultAddressMode defaultAddressMode = new DefaultAddressMode(jDJSONArray.getJSONObject(i3), i2);
                if (!TextUtils.isEmpty(defaultAddressMode.getProvinceName())) {
                    arrayList.add(defaultAddressMode);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }

    public DefaultAddressMode(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
