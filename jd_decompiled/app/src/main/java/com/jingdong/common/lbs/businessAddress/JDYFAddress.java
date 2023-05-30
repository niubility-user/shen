package com.jingdong.common.lbs.businessAddress;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDYFAddress {
    public static final String TYPE_CHOOSE = "choose";
    public static final String TYPE_DEFAULT = "default";
    public static final String TYPE_GIS = "gis";
    public static final String TYPE_JSF = "jsf";
    private long addressID;
    private String addressName;
    private String addressTitle;
    private String city;
    private long cityCode;
    private String code;
    private String detailAddress;
    private String district;
    private long districtCode;
    private String fullAddress;
    private long gridId;
    private boolean isNearby;
    private boolean isPermission;
    private double lat;
    private double lng;
    private String message;
    private String province;
    private long provinceCode;
    private String saveBusiness;
    private String sceneId;
    private String source;
    private String town;
    private long townCode;
    private String type;

    public String getAddressGlobalJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", this.code);
            jSONObject.put("message", this.message);
            jSONObject.put("addressID", this.addressID);
            jSONObject.put("provinceCode", this.provinceCode);
            jSONObject.put("province", this.province);
            jSONObject.put("cityCode", this.cityCode);
            jSONObject.put("city", this.city);
            jSONObject.put(Constant.KEY_DISTRICT_CODE, this.districtCode);
            jSONObject.put("district", this.district);
            jSONObject.put("townCode", this.townCode);
            jSONObject.put("town", this.town);
            jSONObject.put("fullAddress", getFullAddress());
            jSONObject.put("detailAddress", getDetailAddress());
            jSONObject.put(HybridSDK.LNG, getLng());
            jSONObject.put("lat", getLat());
            jSONObject.put("type", this.type);
            jSONObject.put("addressTitle", getAddressTitle());
            jSONObject.put("saveBusiness", getSaveBusiness());
            jSONObject.put("source", getSource());
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public long getAddressID() {
        return this.addressID;
    }

    public String getAddressName() {
        if (TextUtils.isEmpty(getDetailAddress())) {
            return getFullAddress();
        }
        return getDetailAddress();
    }

    public String getAddressTitle() {
        return TextUtils.isEmpty(this.addressTitle) ? "" : this.addressTitle;
    }

    public String getCity() {
        return TextUtils.isEmpty(this.city) ? "" : this.city;
    }

    public long getCityCode() {
        return this.cityCode;
    }

    public String getCode() {
        return this.code;
    }

    public String getDetailAddress() {
        return (!isSceneAllowed(getSceneId()) || TextUtils.isEmpty(this.detailAddress)) ? "" : this.detailAddress;
    }

    public String getDistrict() {
        return TextUtils.isEmpty(this.district) ? "" : this.district;
    }

    public long getDistrictCode() {
        return this.districtCode;
    }

    public String getFullAddress() {
        return TextUtils.isEmpty(this.fullAddress) ? "" : this.fullAddress;
    }

    public long getGridId() {
        return this.gridId;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", this.code);
            jSONObject.put("message", this.message);
            jSONObject.put("addressID", this.addressID);
            jSONObject.put("provinceCode", this.provinceCode);
            jSONObject.put("province", this.province);
            jSONObject.put("cityCode", this.cityCode);
            jSONObject.put("city", this.city);
            jSONObject.put(Constant.KEY_DISTRICT_CODE, this.districtCode);
            jSONObject.put("district", this.district);
            jSONObject.put("townCode", this.townCode);
            jSONObject.put("town", this.town);
            jSONObject.put("fullAddress", getFullAddress());
            jSONObject.put("detailAddress", getDetailAddress());
            jSONObject.put(HybridSDK.LNG, getLng());
            jSONObject.put("lat", getLat());
            jSONObject.put("type", this.type);
            jSONObject.put("addressName", getAddressName());
            jSONObject.put("isPermission", this.isPermission);
            jSONObject.put("isNearby", this.isNearby);
            jSONObject.put("gridId", this.gridId);
            jSONObject.put("addressTitle", getAddressTitle());
            jSONObject.put("saveBusiness", getSaveBusiness());
            jSONObject.put("source", getSource());
            jSONObject.put("sceneId", getSceneId());
            jSONObject.put("isSceneAllowed", DeviceUtil.isSceneAllowed(getSceneId()));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public double getLat() {
        if (Double.isNaN(this.lat)) {
            return 0.0d;
        }
        return this.lat;
    }

    public double getLng() {
        if (Double.isNaN(this.lng)) {
            return 0.0d;
        }
        return this.lng;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public String getProvince() {
        return TextUtils.isEmpty(this.province) ? "" : this.province;
    }

    public long getProvinceCode() {
        return this.provinceCode;
    }

    public String getSaveBusiness() {
        return TextUtils.isEmpty(this.saveBusiness) ? "" : this.saveBusiness;
    }

    public String getSceneId() {
        return TextUtils.isEmpty(this.sceneId) ? "" : this.sceneId;
    }

    public String getSource() {
        return TextUtils.isEmpty(this.source) ? "" : this.source;
    }

    public String getTown() {
        return TextUtils.isEmpty(this.town) ? "" : this.town;
    }

    public long getTownCode() {
        return this.townCode;
    }

    public String getType() {
        String str = this.type;
        return str == null ? "" : str;
    }

    public boolean isNearby() {
        return this.isNearby;
    }

    public boolean isPermission() {
        return this.isPermission;
    }

    public boolean isSceneAllowed(String str) {
        if ("gis".equalsIgnoreCase(this.type)) {
            if (TextUtils.isEmpty(str)) {
                str = "locService";
            }
            return LBSSceneSwitchHelper.getLbsSceneSwitch(str);
        }
        return true;
    }

    public void setAddressID(long j2) {
        this.addressID = j2;
    }

    public void setAddressName(String str) {
        this.addressName = str;
    }

    public void setAddressTitle(String str) {
        this.addressTitle = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityCode(long j2) {
        this.cityCode = j2;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setDetailAddress(String str) {
        this.detailAddress = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setDistrictCode(long j2) {
        this.districtCode = j2;
    }

    public void setFullAddress(String str) {
        this.fullAddress = str;
    }

    public void setGridId(long j2) {
        this.gridId = j2;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNearby(boolean z) {
        this.isNearby = z;
    }

    public void setPermission(boolean z) {
        this.isPermission = z;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setProvinceCode(long j2) {
        this.provinceCode = j2;
    }

    public void setSaveBusiness(String str) {
        this.saveBusiness = str;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public void setTownCode(long j2) {
        this.townCode = j2;
    }

    public void setType(String str) {
        this.type = str;
    }
}
