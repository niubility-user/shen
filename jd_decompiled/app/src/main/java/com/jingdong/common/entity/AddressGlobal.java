package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.entity.settlement.AddressOverSea;
import com.jingdong.common.entity.settlement.AddressTagInfo;
import com.jingdong.common.ui.address.eu.EuShippingUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AddressGlobal extends AddressOverSea implements Parcelable, Serializable {
    public static final int ADDRESS_TYPE_DEFAULT = 0;
    public static final int ADDRESS_TYPE_DISTRICTS = 4;
    public static final int ADDRESS_TYPE_IP = 1;
    public static final int ADDRESS_TYPE_LOCATION = 3;
    public static final int ADDRESS_TYPE_UNIFICATION_LOCATION = 5;
    public static final int ADDRESS_TYPE_USER = 2;
    public static final Parcelable.Creator<AddressGlobal> CREATOR = new Parcelable.Creator<AddressGlobal>() { // from class: com.jingdong.common.entity.AddressGlobal.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressGlobal createFromParcel(Parcel parcel) {
            return new AddressGlobal(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressGlobal[] newArray(int i2) {
            return new AddressGlobal[i2];
        }
    };
    private static final String TAG = "AddressGlobal";
    private String CoordType;
    private Boolean addressDefault;
    private String addressDetail;
    private Boolean addressNotUserChecked;
    public AddressTagInfo addressTagMap;
    private String addressTitle;
    private int addressType;
    private String addressUUID;
    private String areaName;
    private String cityName;
    private String geohashLat;
    private String geohashLng;
    private long id;
    private int idArea;
    private int idCity;
    private int idProvince;
    private int idTown;
    private Boolean isUserAddress;
    private String latitude;
    private String longitude;
    private String mobile;
    private String mobileReal;
    private String name;
    private String provinceName;
    private String saveBusiness;
    private long serviceId;
    private String source;
    private String subAddressDetail;
    private long timeStamp;
    private String townName;
    private String where;

    public AddressGlobal() {
    }

    public static AddressGlobal cloneAddressGlobal(AddressGlobal addressGlobal, AddressGlobal addressGlobal2) {
        if (addressGlobal != null && addressGlobal2 != null) {
            addressGlobal2.setIdProvince(addressGlobal.getIdProvince());
            addressGlobal2.setIdCity(addressGlobal.getIdCity());
            addressGlobal2.setIdTown(addressGlobal.getIdTown());
            addressGlobal2.setIdArea(addressGlobal.getIdArea());
            addressGlobal2.setProvinceName(addressGlobal.getProvinceName());
            addressGlobal2.setCityName(addressGlobal.getCityName());
            addressGlobal2.setTownName(addressGlobal.getTownName());
            addressGlobal2.setAreaName(addressGlobal.getAreaName());
            addressGlobal2.setLatitude(addressGlobal.getLatitude());
            addressGlobal2.setLongitude(addressGlobal.getLongitude());
            addressGlobal2.setGeohashLat(addressGlobal.getGeohashLat());
            addressGlobal2.setGeohashLng(addressGlobal.getGeohashLng());
            addressGlobal2.setAddressDetail("");
        }
        return addressGlobal2;
    }

    private String verificationText(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Boolean getAddressDefault() {
        Boolean bool = this.addressDefault;
        return Boolean.valueOf(bool != null && bool.booleanValue());
    }

    public String getAddressDetail() {
        return verificationText(this.addressDetail);
    }

    public Boolean getAddressNotUserChecked() {
        Boolean bool = this.addressNotUserChecked;
        return Boolean.valueOf(bool != null && bool.booleanValue());
    }

    public String getAddressTitle() {
        return verificationText(this.addressTitle);
    }

    public int getAddressType() {
        return this.addressType;
    }

    public String getAddressUUID() {
        return this.addressUUID;
    }

    public String getAreaName() {
        return verificationText(this.areaName);
    }

    public String getCityName() {
        return verificationText(this.cityName);
    }

    public String getCoordType() {
        return verificationText(this.CoordType);
    }

    public String getGeohashLat() {
        return this.geohashLat;
    }

    public String getGeohashLng() {
        return this.geohashLng;
    }

    public long getId() {
        return this.id;
    }

    public int getIdArea() {
        return this.idArea;
    }

    public int getIdCity() {
        return this.idCity;
    }

    public int getIdProvince() {
        return this.idProvince;
    }

    public int getIdTown() {
        return this.idTown;
    }

    public Boolean getIsUserAddress() {
        Boolean bool = this.isUserAddress;
        return Boolean.valueOf(bool != null && bool.booleanValue());
    }

    public String getLatitude() {
        return verificationText(this.latitude);
    }

    public String getLongitude() {
        return verificationText(this.longitude);
    }

    public String getMobile() {
        return verificationText(this.mobile);
    }

    public String getMobileReal() {
        return this.mobileReal;
    }

    public String getName() {
        return verificationText(this.name);
    }

    public String getProvinceName() {
        return verificationText(this.provinceName);
    }

    public String getSaveBusiness() {
        return verificationText(this.saveBusiness);
    }

    public long getServiceId() {
        return this.serviceId;
    }

    public String getSource() {
        return verificationText(this.source);
    }

    public String getSubAddressDetail() {
        return this.subAddressDetail;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTownName() {
        return verificationText(this.townName);
    }

    public String getWhere() {
        return verificationText(this.where);
    }

    public boolean isEUCountry() {
        return EuShippingUtils.isEUCountry(this.idCity);
    }

    public boolean isIp() {
        return this.addressType == 1;
    }

    public boolean isLocation() {
        if (this.id > 0) {
            return false;
        }
        int i2 = this.addressType;
        if (i2 == 3) {
            return true;
        }
        if (i2 != 5 && !TextUtils.isEmpty(this.longitude) && !TextUtils.isEmpty(this.latitude)) {
            try {
                double abs = Math.abs(Double.parseDouble(this.longitude));
                double abs2 = Math.abs(Double.parseDouble(this.latitude));
                if (abs <= 180.0d && abs > 0.0d && abs2 <= 180.0d && abs2 > 0.0d) {
                    return true;
                }
            } catch (NumberFormatException e2) {
                OKLog.e(TAG, e2);
            }
        }
        return false;
    }

    public boolean isUserAddress() {
        return this.addressType == 2 || this.id > 0;
    }

    public void parseNewCurrendOrderAddress(NewCurrentOrderAddress newCurrentOrderAddress) {
        if (newCurrentOrderAddress == null) {
            return;
        }
        setId(newCurrentOrderAddress.id);
        setIdProvince(newCurrentOrderAddress.getIdProvince().intValue());
        setIdCity(newCurrentOrderAddress.getIdCity().intValue());
        setIdTown(newCurrentOrderAddress.getIdTown().intValue());
        setIdArea(newCurrentOrderAddress.getIdArea().intValue());
        setProvinceName(newCurrentOrderAddress.getProvinceName());
        setCityName(newCurrentOrderAddress.getCityName());
        setAreaName(newCurrentOrderAddress.getCountryName());
        setTownName(newCurrentOrderAddress.getTownName());
        setWhere(newCurrentOrderAddress.getWhere());
        setName(newCurrentOrderAddress.getName());
        this.email = newCurrentOrderAddress.email;
        this.areaCode = newCurrentOrderAddress.areaCode;
        this.postCode = newCurrentOrderAddress.postCode;
        this.nameCode = newCurrentOrderAddress.nameCode;
        this.longitude = newCurrentOrderAddress.longitudeString;
        this.latitude = newCurrentOrderAddress.latitudeString;
        this.isForeignOverSea = newCurrentOrderAddress.isForeignOverSea;
        this.addressTagMap = newCurrentOrderAddress.addressTagMap;
        this.addressType = 2;
    }

    public void parseUserAddress(UserAddress userAddress) {
        if (userAddress == null) {
            return;
        }
        setId(userAddress.id);
        setIdProvince(userAddress.getIdProvince().intValue());
        setIdCity(userAddress.getIdCity().intValue());
        setIdTown(userAddress.getIdTown().intValue());
        setIdArea(userAddress.getIdArea().intValue());
        setProvinceName(userAddress.getProvinceName());
        setCityName(userAddress.getCityName());
        setTownName(userAddress.getTownName());
        setAreaName(userAddress.getCountryName());
        setWhere(userAddress.getWhere());
        setName(userAddress.getName());
        this.email = userAddress.email;
        this.areaCode = userAddress.areaCode;
        this.postCode = userAddress.postCode;
        this.nameCode = userAddress.nameCode;
        this.longitude = userAddress.longitudeString;
        this.latitude = userAddress.latitudeString;
        this.isForeignOverSea = userAddress.isForeignOverSea;
        this.addressTagMap = userAddress.addressTagMap;
        this.addressType = 2;
    }

    public void setAddressDefault(Boolean bool) {
        this.addressDefault = bool;
    }

    public void setAddressDetail(String str) {
        this.addressDetail = str;
    }

    public void setAddressNotUserChecked(Boolean bool) {
        this.addressNotUserChecked = bool;
    }

    public void setAddressTitle(String str) {
        this.addressTitle = str;
    }

    public void setAddressType(int i2) {
        this.addressType = i2;
    }

    public void setAddressUUID(String str) {
        this.addressUUID = str;
    }

    public void setAreaName(String str) {
        this.areaName = str;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setCoordType(String str) {
        this.CoordType = str;
    }

    public void setGeohashLat(String str) {
        this.geohashLat = str;
    }

    public void setGeohashLng(String str) {
        this.geohashLng = str;
    }

    public void setId(long j2) {
        this.id = j2;
    }

    public void setIdArea(int i2) {
        this.idArea = i2;
    }

    public void setIdCity(int i2) {
        this.idCity = i2;
    }

    public void setIdProvince(int i2) {
        this.idProvince = i2;
    }

    public void setIdTown(int i2) {
        this.idTown = i2;
    }

    public void setIsUserAddress(Boolean bool) {
        this.isUserAddress = bool;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setMobileReal(String str) {
        this.mobileReal = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public void setSaveBusiness(String str) {
        this.saveBusiness = str;
    }

    public void setServiceId(long j2) {
        this.serviceId = j2;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setSubAddressDetail(String str) {
        this.subAddressDetail = str;
    }

    public void setTimeStamp(long j2) {
        this.timeStamp = j2;
    }

    public void setTownName(String str) {
        this.townName = str;
    }

    public void setWhere(String str) {
        this.where = str;
    }

    public JSONObject toOrderStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Id", getId());
            jSONObject.put("IdProvince", getIdProvince());
            jSONObject.put("IdCity", getIdCity());
            jSONObject.put("IdTown", getIdTown());
            jSONObject.put("IdArea", getIdArea());
            jSONObject.put("Name", getName());
            jSONObject.put("Mobile", getMobileReal());
            jSONObject.put("Where", getWhere());
            jSONObject.put("addressDetail", getAddressDetail());
            jSONObject.put(PdLVBody.LONGITUDE, getLongitude());
            jSONObject.put(PdLVBody.LATITUDE, getLatitude());
            jSONObject.put("CoordType", getCoordType());
            jSONObject.put("addressDefault", getAddressDefault());
            jSONObject.put("Email", this.email);
            jSONObject.put("email", this.email);
            jSONObject.put("areaCode", this.areaCode);
            jSONObject.put("postCode", this.postCode);
            jSONObject.put("nameCode", this.nameCode);
            AddressTagInfo addressTagInfo = this.addressTagMap;
            if (addressTagInfo != null) {
                int i2 = addressTagInfo.addressTagType;
                if (i2 == 1) {
                    jSONObject.put("retTag", addressTagInfo.addressTagId);
                    jSONObject.put("tagSource", this.addressTagMap.addressTagType);
                } else if (i2 == 2) {
                    jSONObject.put("tagSource", i2);
                    jSONObject.put("userDefinedTag", this.addressTagMap.addressTagName);
                }
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
        return jSONObject;
    }

    public String toString() {
        return toString(System.currentTimeMillis());
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeLong(this.id);
        parcel.writeInt(this.idProvince);
        parcel.writeInt(this.idCity);
        parcel.writeInt(this.idTown);
        parcel.writeInt(this.idArea);
        parcel.writeString(this.where);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.cityName);
        parcel.writeString(this.townName);
        parcel.writeString(this.areaName);
        parcel.writeValue(this.isUserAddress);
        parcel.writeString(this.name);
        parcel.writeString(this.mobile);
        parcel.writeString(this.mobileReal);
        parcel.writeString(this.addressDetail);
        parcel.writeString(this.longitude);
        parcel.writeString(this.latitude);
        parcel.writeString(this.CoordType);
        parcel.writeLong(this.timeStamp);
        parcel.writeValue(this.addressDefault);
        parcel.writeSerializable(this.addressTagMap);
        parcel.writeString(this.email);
        parcel.writeString(this.areaCode);
        parcel.writeString(this.postCode);
        parcel.writeString(this.addressUUID);
        parcel.writeInt(this.addressType);
        parcel.writeString(this.saveBusiness);
        parcel.writeString(this.addressTitle);
        parcel.writeString(this.source);
        parcel.writeString(this.subAddressDetail);
        parcel.writeValue(this.addressNotUserChecked);
        parcel.writeLong(this.serviceId);
        parcel.writeString(this.geohashLat);
        parcel.writeString(this.geohashLng);
    }

    protected AddressGlobal(Parcel parcel) {
        super(parcel);
        this.id = parcel.readLong();
        this.idProvince = parcel.readInt();
        this.idCity = parcel.readInt();
        this.idTown = parcel.readInt();
        this.idArea = parcel.readInt();
        this.where = parcel.readString();
        this.provinceName = parcel.readString();
        this.cityName = parcel.readString();
        this.townName = parcel.readString();
        this.areaName = parcel.readString();
        this.isUserAddress = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.name = parcel.readString();
        this.mobile = parcel.readString();
        this.mobileReal = parcel.readString();
        this.addressDetail = parcel.readString();
        this.longitude = parcel.readString();
        this.latitude = parcel.readString();
        this.CoordType = parcel.readString();
        this.timeStamp = parcel.readLong();
        this.addressDefault = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.addressTagMap = (AddressTagInfo) parcel.readSerializable();
        this.email = parcel.readString();
        this.areaCode = parcel.readString();
        this.postCode = parcel.readString();
        this.addressUUID = parcel.readString();
        this.addressType = parcel.readInt();
        this.saveBusiness = parcel.readString();
        this.addressTitle = parcel.readString();
        this.source = parcel.readString();
        this.subAddressDetail = parcel.readString();
        this.addressNotUserChecked = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.serviceId = parcel.readLong();
        this.geohashLat = parcel.readString();
        this.geohashLng = parcel.readString();
    }

    public String toString(long j2) {
        try {
            JSONObject jSONObject = new JSONObject(JDJSON.toJSONString(this));
            jSONObject.put("timeStamp", j2);
            jSONObject.put("Email", this.email);
            jSONObject.put("email", this.email);
            AddressTagInfo addressTagInfo = this.addressTagMap;
            if (addressTagInfo != null) {
                int i2 = addressTagInfo.addressTagType;
                if (i2 == 1) {
                    jSONObject.put("retTag", addressTagInfo.addressTagId);
                    jSONObject.put("tagSource", this.addressTagMap.addressTagType);
                } else if (i2 == 2) {
                    jSONObject.put("tagSource", i2);
                    jSONObject.put("userDefinedTag", this.addressTagMap.addressTagName);
                }
            }
            if (OKLog.D) {
                OKLog.d("\u66f4\u65b0\u5168\u5c40\u5730\u5740\u7f13\u5b58 toString", jSONObject.toString());
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            return "";
        }
    }
}
