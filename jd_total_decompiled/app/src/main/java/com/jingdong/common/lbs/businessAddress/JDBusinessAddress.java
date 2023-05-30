package com.jingdong.common.lbs.businessAddress;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDBusinessAddress {
    public static final String TYPE_BACKUP = "backup";
    public static final String TYPE_GIS = "gis";
    public static final String TYPE_JSF = "jsf";
    private boolean addressDefault;
    private long addressID;
    private String addressName;
    private String addressTitle;
    private boolean addressValid = true;
    private String city;
    private int cityCode;
    private int code;
    private String detailAddress;
    private String district;
    private int districtCode;
    private String fullAddress;
    private long gridId;
    private double lat;
    private double lng;
    private String message;
    private String mobile;
    private String name;
    private boolean overseas;
    private String province;
    private int provinceCode;
    private String saveBusiness;
    private String sceneId;
    private boolean showToast;
    private String source;
    private String tag;
    private String title;
    private String toast;
    private String town;
    private int townCode;
    private String type;
    private boolean updateGlobal;
    private boolean useGlobal;

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

    public String getBusinessAddressJson() {
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
            jSONObject.put("gridId", this.gridId);
            jSONObject.put("addressTitle", getAddressTitle());
            jSONObject.put("saveBusiness", getSaveBusiness());
            jSONObject.put("source", getSource());
            jSONObject.put("addressName", getAddressName());
            jSONObject.put("mobile", this.mobile);
            jSONObject.put(XView2Constants.XVIEW2_ACTION_TOAST, this.toast);
            jSONObject.put("showToast", this.showToast);
            jSONObject.put("sceneId", getSceneId());
            jSONObject.put("isSceneAllowed", DeviceUtil.isSceneAllowed(getSceneId()));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getCity() {
        return TextUtils.isEmpty(this.city) ? "" : this.city;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public int getCode() {
        return this.code;
    }

    public String getDetailAddress() {
        return (!isSceneAllowed(getSceneId()) || TextUtils.isEmpty(this.detailAddress)) ? "" : this.detailAddress;
    }

    public String getDistrict() {
        return TextUtils.isEmpty(this.district) ? "" : this.district;
    }

    public int getDistrictCode() {
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
            jSONObject.put("gridId", this.gridId);
            jSONObject.put("addressTitle", getAddressTitle());
            jSONObject.put("saveBusiness", getSaveBusiness());
            jSONObject.put("source", getSource());
            jSONObject.put("addressName", getAddressName());
            jSONObject.put("mobile", this.mobile);
            jSONObject.put(XView2Constants.XVIEW2_ACTION_TOAST, this.toast);
            jSONObject.put("showToast", this.showToast);
            jSONObject.put("name", this.name);
            jSONObject.put("addressDefault", this.addressDefault);
            jSONObject.put("overseas", this.overseas);
            jSONObject.put("tag", this.tag);
            jSONObject.put("useGlobal", this.useGlobal);
            jSONObject.put("updateGlobal", this.updateGlobal);
            jSONObject.put("addressValid", this.addressValid);
            jSONObject.put("title", this.title);
            jSONObject.put("sceneId", getSceneId());
            jSONObject.put("isSceneAllowed", DeviceUtil.isSceneAllowed(getSceneId()));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public double getLat() {
        if (isSceneAllowed(getSceneId())) {
            if (Double.isNaN(this.lat)) {
                return 0.0d;
            }
            return this.lat;
        }
        return this.lat;
    }

    public double getLng() {
        if (isSceneAllowed(getSceneId())) {
            if (Double.isNaN(this.lng)) {
                return 0.0d;
            }
            return this.lng;
        }
        return this.lng;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public String getMobile() {
        String str = this.mobile;
        return str == null ? "" : str;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getProvince() {
        return TextUtils.isEmpty(this.province) ? "" : this.province;
    }

    public int getProvinceCode() {
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

    public String getTag() {
        return TextUtils.isEmpty(this.tag) ? "" : this.tag;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public String getToast() {
        return TextUtils.isEmpty(this.toast) ? "" : this.toast;
    }

    public String getTown() {
        return TextUtils.isEmpty(this.town) ? "" : this.town;
    }

    public int getTownCode() {
        return this.townCode;
    }

    public String getType() {
        String str = this.type;
        return str == null ? "" : str;
    }

    public boolean isAddressDefault() {
        return this.addressDefault;
    }

    public boolean isAddressValid() {
        return this.addressValid;
    }

    public boolean isOverseas() {
        return this.overseas;
    }

    public boolean isSceneAllowed(String str) {
        if ("gis".equalsIgnoreCase(this.type)) {
            if (TextUtils.isEmpty(str)) {
                str = "basicShoppingProcess";
            }
            return LBSSceneSwitchHelper.getLbsSceneSwitch(str);
        }
        return true;
    }

    public boolean isShowToast() {
        return this.showToast;
    }

    public boolean isUpdateGlobal() {
        return this.updateGlobal;
    }

    public boolean isUseGlobal() {
        return this.useGlobal;
    }

    public void setAddressDefault(boolean z) {
        this.addressDefault = z;
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

    public void setAddressValid(boolean z) {
        this.addressValid = z;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityCode(int i2) {
        this.cityCode = i2;
    }

    public void setCode(int i2) {
        this.code = i2;
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

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOverseas(boolean z) {
        this.overseas = z;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setProvinceCode(int i2) {
        this.provinceCode = i2;
    }

    public void setSaveBusiness(String str) {
        this.saveBusiness = str;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    public void setShowToast(boolean z) {
        this.showToast = z;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setToast(String str) {
        this.toast = str;
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

    public void setUpdateGlobal(boolean z) {
        this.updateGlobal = z;
    }

    public void setUseGlobal(boolean z) {
        this.useGlobal = z;
    }
}
