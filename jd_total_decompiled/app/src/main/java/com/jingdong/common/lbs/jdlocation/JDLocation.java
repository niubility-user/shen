package com.jingdong.common.lbs.jdlocation;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jingdong.common.lbs.R;
import com.jingdong.common.lbs.proxy.a;
import com.jingdong.common.lbs.utils.AESUtil;
import com.jingdong.common.lbs.utils.DeviceUtil;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDLocation implements Cloneable {
    public static final String GIS_SERVICE = "GisService";
    public static final String IP_SERVICE = "IpService";
    private float accuracy;
    private double altitude;
    private String businessId;
    private int cityId;
    private String coord;
    private int districtId;
    private double geohashLat;
    private double geohashLng;
    private long gridId;
    private String key;
    private double lat;
    private double lng;
    private String provider;
    private int provinceId;
    private int regionId;
    private String sceneId;
    private int townId;
    private int type;
    private String regionName = "";
    private String provinceName = "";
    private String cityName = "";
    private String districtName = "";
    private String townName = "";
    private String detailAddress = "";
    private String oversea = "";
    private String callType = "";
    private long requestTime = 0;
    private long updateTime = 0;

    private String getEncryptLatNoReport() {
        if (TextUtils.isEmpty(this.key)) {
            this.key = a.a.getString(R.string.lng_lat_key);
        }
        return AESUtil.encrypt(String.valueOf(this.lat), this.key);
    }

    private String getEncryptLngNoReport() {
        if (TextUtils.isEmpty(this.key)) {
            this.key = a.a.getString(R.string.lng_lat_key);
        }
        return AESUtil.encrypt(String.valueOf(this.lng), this.key);
    }

    public Object clone() {
        return super.clone();
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public String getBusinessId() {
        return TextUtils.isEmpty(this.businessId) ? "" : this.businessId;
    }

    public String getCallType() {
        return this.callType;
    }

    public int getCityId() {
        return this.cityId;
    }

    public String getCityName() {
        String str = this.cityName;
        return str == null ? "" : str;
    }

    public String getCoord() {
        return this.coord;
    }

    public String getDetailAddress() {
        String str;
        return (!DeviceUtil.isSceneAllowed(getSceneId()) || (str = this.detailAddress) == null) ? "" : str;
    }

    public int getDistrictId() {
        return this.districtId;
    }

    public String getDistrictName() {
        String str = this.districtName;
        return str == null ? "" : str;
    }

    public String getEncryptLat() {
        if (TextUtils.isEmpty(this.key)) {
            this.key = a.a.getString(R.string.lng_lat_key);
        }
        return AESUtil.encrypt(String.valueOf(this.lat), this.key);
    }

    public String getEncryptLng() {
        if (TextUtils.isEmpty(this.key)) {
            this.key = a.a.getString(R.string.lng_lat_key);
        }
        return AESUtil.encrypt(String.valueOf(this.lng), this.key);
    }

    public double getGeohashLat() {
        if (Double.isNaN(this.geohashLat)) {
            return 0.0d;
        }
        return this.geohashLat;
    }

    public double getGeohashLng() {
        if (Double.isNaN(this.geohashLng)) {
            return 0.0d;
        }
        return this.geohashLng;
    }

    public long getGridId() {
        return this.gridId;
    }

    public String getJsonForMta() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", 0);
            jSONObject.put("message", "OK");
            jSONObject.put("type", this.type);
            jSONObject.put("requestTime", this.requestTime);
            jSONObject.put("lat", getLat());
            jSONObject.put(HybridSDK.LNG, getLng());
            jSONObject.put("coord", this.coord);
            jSONObject.put("provider", TextUtils.isEmpty(this.provider) ? "" : this.provider);
            jSONObject.put("accuracy", this.accuracy);
            jSONObject.put(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, this.updateTime);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getJsonForWeb() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", 0);
            jSONObject.put("message", "OK");
            jSONObject.put("region", this.regionName);
            StringBuilder sb = new StringBuilder();
            sb.append(this.regionId);
            jSONObject.put("regionid", sb.toString());
            jSONObject.put("province", this.provinceName);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.provinceId);
            jSONObject.put("provinceid", sb2.toString());
            jSONObject.put("city", this.cityName);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.cityId);
            jSONObject.put("cityid", sb3.toString());
            jSONObject.put("district", this.districtName);
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.districtId);
            jSONObject.put("districtid", sb4.toString());
            jSONObject.put("town", this.townName);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.townId);
            jSONObject.put("townid", sb5.toString());
            jSONObject.put("detailaddr", getDetailAddress());
            jSONObject.put("oversea", this.oversea);
            jSONObject.put("callType", this.callType);
            jSONObject.put("srclng", getLng());
            jSONObject.put("srclat", getLat());
            jSONObject.put("gridId", this.gridId);
            jSONObject.put(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, this.updateTime);
            jSONObject.put("encryptLng", getEncryptLngNoReport());
            jSONObject.put("encryptLat", getEncryptLatNoReport());
            jSONObject.put("sceneId", getSceneId());
            jSONObject.put("isSceneAllowed", DeviceUtil.isSceneAllowed(getSceneId()));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public double getLat() {
        if (DeviceUtil.isSceneAllowed(getSceneId())) {
            if (Double.isNaN(this.lat)) {
                return 0.0d;
            }
            return this.lat;
        }
        return getGeohashLat();
    }

    public double getLng() {
        if (DeviceUtil.isSceneAllowed(getSceneId())) {
            if (Double.isNaN(this.lng)) {
                return 0.0d;
            }
            return this.lng;
        }
        return getGeohashLng();
    }

    public String getOversea() {
        return this.oversea;
    }

    public String getProvider() {
        return this.provider;
    }

    public int getProvinceId() {
        return this.provinceId;
    }

    public String getProvinceName() {
        String str = this.provinceName;
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getRealLat() {
        if (Double.isNaN(this.lat)) {
            return 0.0d;
        }
        return this.lat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getRealLng() {
        if (Double.isNaN(this.lng)) {
            return 0.0d;
        }
        return this.lng;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public long getRequestTime() {
        return this.requestTime;
    }

    public String getSceneId() {
        return TextUtils.isEmpty(this.sceneId) ? "" : this.sceneId;
    }

    public int getTownId() {
        return this.townId;
    }

    public String getTownName() {
        String str = this.townName;
        return str == null ? "" : str;
    }

    public int getType() {
        return this.type;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccuracy(float f2) {
        this.accuracy = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAltitude(double d) {
        this.altitude = d;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setCallType(String str) {
        this.callType = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCityId(int i2) {
        this.cityId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCityName(String str) {
        this.cityName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCoord(String str) {
        this.coord = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDetailAddress(String str) {
        this.detailAddress = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDistrictId(int i2) {
        this.districtId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDistrictName(String str) {
        this.districtName = str;
    }

    public void setGeohashLat(double d) {
        this.geohashLat = d;
    }

    public void setGeohashLng(double d) {
        this.geohashLng = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGridId(long j2) {
        this.gridId = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLat(double d) {
        this.lat = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLng(double d) {
        this.lng = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOversea(String str) {
        this.oversea = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProvider(String str) {
        this.provider = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProvinceId(int i2) {
        this.provinceId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRegionId(int i2) {
        this.regionId = i2;
    }

    public void setRegionName(String str) {
        this.regionName = str;
    }

    public void setRequestTime(long j2) {
        this.requestTime = j2;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTownId(int i2) {
        this.townId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTownName(String str) {
        this.townName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setType(int i2) {
        this.type = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUpdateTime(long j2) {
        this.updateTime = j2;
    }
}
