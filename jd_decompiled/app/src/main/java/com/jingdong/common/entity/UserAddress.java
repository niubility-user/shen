package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.entity.settlement.AddressTagInfo;
import com.jingdong.common.utils.BaiduMapUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UserAddress extends UserInfoCommon implements Parcelable, Serializable {
    public static final int ADDRESS_TYPE_LOCATION = 1;
    public static final int ADDRESS_TYPE_USER = -1;
    public static final int COORD_TYPE_BAIDU = 4;
    public static final int COORD_TYPE_GOOGLE = 3;
    public static final int COORD_TYPE_PHONE = 1;
    public static final int COORD_TYPE_QQ = 2;
    public static final Parcelable.Creator<UserAddress> CREATOR = new Parcelable.Creator<UserAddress>() { // from class: com.jingdong.common.entity.UserAddress.1
        @Override // android.os.Parcelable.Creator
        public UserAddress createFromParcel(Parcel parcel) {
            return new UserAddress(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UserAddress[] newArray(int i2) {
            return new UserAddress[i2];
        }
    };
    private static final String TAG = "UserAddress";
    public static final int TYPE_ID_EASY_GO = 0;
    public String CityName;
    public String CountryName;
    public String ProvinceName;
    public String TownName;
    public Integer UserLevel;
    public String addressDetail;
    public AddressTagInfo addressTagMap;
    public int addressType;
    public String addressUUID;
    public String clickAble;
    public int coordType;
    public String email;
    private GeoPoint geoPoint;
    public long id;
    public Integer idArea;
    public Integer idCity;
    public Integer idProvince;
    public Integer idTown;
    public String identityCard;
    public boolean isAreaWrong;
    @JSONField(name = "addressDefault")
    public Boolean isDefaultAddr;
    @JSONField(name = "isIdTown")
    public Boolean isNoIdTown;
    public boolean isOperationVisible;
    public boolean isSelected;
    @JSONField(name = "Latitude")
    public double latitudeDB;
    @JSONField(name = "Longitude")
    public double longitudeDB;
    @JSONField(name = CartConstant.KEY_YB_MESSAGE)
    public String message;
    public String mobile;
    public String mobileReal;
    public String name;
    public int operationType;
    public String phone;
    public String pin;
    public boolean presentFlag;
    public boolean selectedAddress;
    public Integer typeId;
    public int viewType;
    public String where;
    public String zip;

    public UserAddress(JSONObjectProxy jSONObjectProxy) {
        this.coordType = 2;
        this.longitudeDB = -1.0d;
        this.latitudeDB = -1.0d;
        try {
            this.pin = jSONObjectProxy.optString("pin");
            this.id = jSONObjectProxy.optInt("Id");
            this.idCity = Integer.valueOf(jSONObjectProxy.optInt("IdCity"));
            this.idTown = Integer.valueOf(jSONObjectProxy.optInt("IdTown"));
            this.idArea = Integer.valueOf(jSONObjectProxy.optInt("IdArea"));
            this.idProvince = Integer.valueOf(jSONObjectProxy.optInt("IdProvince"));
            this.name = jSONObjectProxy.optString("Name");
            this.where = jSONObjectProxy.optString("Where");
            this.typeId = Integer.valueOf(jSONObjectProxy.optInt("TypeId"));
            this.email = jSONObjectProxy.optString("Email");
            this.mobile = jSONObjectProxy.optString("Mobile");
            this.zip = jSONObjectProxy.optString("Zip");
            this.addressDetail = jSONObjectProxy.optString("addressDetail");
            this.isNoIdTown = Boolean.valueOf(jSONObjectProxy.optBoolean("isIdTown"));
            this.message = jSONObjectProxy.optString(CartConstant.KEY_YB_MESSAGE);
            this.latitudeDB = jSONObjectProxy.getDoubleValue("Latitude");
            this.longitudeDB = jSONObjectProxy.getDoubleValue("Longitude");
            int optInt = jSONObjectProxy.optInt("CoordType");
            this.coordType = optInt;
            if (optInt == 4) {
                double[] bdDecrypt = BaiduMapUtils.bdDecrypt(this.latitudeDB, this.longitudeDB);
                this.latitudeDB = bdDecrypt[0];
                this.longitudeDB = bdDecrypt[1];
                this.coordType = 2;
            }
            this.ProvinceName = jSONObjectProxy.optString("ProvinceName");
            this.CityName = jSONObjectProxy.optString("CityName");
            this.CountryName = jSONObjectProxy.optString("CountryName");
            this.TownName = jSONObjectProxy.optString("TownName");
            this.isDefaultAddr = Boolean.valueOf(jSONObjectProxy.optBoolean("addressDefault"));
            this.identityCard = jSONObjectProxy.optString("identityCard");
            setAreaExplainMsg(jSONObjectProxy.optString("areaExplainMsg"));
            setAreaExplainUrl(jSONObjectProxy.optString("areaExplainUrl"));
            this.isForeignOverSea = jSONObjectProxy.optBoolean("isForeignOverSea");
            this.phone = jSONObjectProxy.optString("Phone");
            this.postCode = jSONObjectProxy.optString("postCode");
            this.areaCode = jSONObjectProxy.optString("areaCode");
            this.nameCode = jSONObjectProxy.optString("nameCode");
            this.longitudeString = jSONObjectProxy.optString("longitudeString");
            this.latitudeString = jSONObjectProxy.optString("latitudeString");
            this.operationType = jSONObjectProxy.optInt(CartConstant.KEY_OPERATIONTYPE);
            this.clickAble = jSONObjectProxy.optString("clickAble");
            this.presentFlag = jSONObjectProxy.optBoolean("presentFlag");
            this.selectedAddress = jSONObjectProxy.optBoolean("selectedAddress");
        } catch (Exception e2) {
            OKLog.e("UserAddress", e2);
        }
    }

    public static UserAddress parser(JSONObject jSONObject) {
        UserAddress userAddress = (UserAddress) JDJSON.parseObject(jSONObject.toString(), UserAddress.class);
        if (userAddress != null && userAddress.coordType == 4) {
            double[] bdDecrypt = BaiduMapUtils.bdDecrypt(userAddress.latitudeDB, userAddress.longitudeDB);
            userAddress.latitudeDB = bdDecrypt[0];
            userAddress.longitudeDB = bdDecrypt[1];
            userAddress.coordType = 2;
        }
        return userAddress;
    }

    public static ArrayList<UserAddress> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<UserAddress> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
            if (jSONObjectOrNull != null) {
                arrayList.add(new UserAddress(jSONObjectOrNull));
            }
        }
        return arrayList;
    }

    public Boolean IsDefaultAddr() {
        Boolean bool = this.isDefaultAddr;
        return bool == null ? Boolean.FALSE : bool;
    }

    @Override // com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            UserAddress userAddress = (UserAddress) obj;
            return getMobile().equals(userAddress.getMobile()) && getName().equals(userAddress.getName()) && getWhere().equals(userAddress.getWhere());
        }
        return false;
    }

    public String getAddressDetail() {
        String str = this.addressDetail;
        return str == null ? "" : str;
    }

    public String getCityName() {
        return TextUtils.isEmpty(this.CityName) ? "" : this.CityName;
    }

    public String getCountryName() {
        return TextUtils.isEmpty(this.CountryName) ? "" : this.CountryName;
    }

    public String getEmail() {
        String str = this.email;
        return str == null ? "" : str;
    }

    public GeoPoint getGeoPoint() {
        return this.geoPoint;
    }

    public Integer getIdArea() {
        Integer num = this.idArea;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public Integer getIdCity() {
        Integer num = this.idCity;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public Integer getIdProvince() {
        Integer num = this.idProvince;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public Integer getIdTown() {
        Integer num = this.idTown;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public Boolean getIsNoIdTown() {
        Boolean bool = this.isNoIdTown;
        return bool == null ? Boolean.FALSE : bool;
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
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getPhone() {
        return TextUtils.isEmpty(this.phone) ? "" : this.phone;
    }

    public String getPin() {
        String str = this.pin;
        return str == null ? "" : str;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.ProvinceName) ? "" : this.ProvinceName;
    }

    public String getTownName() {
        return TextUtils.isEmpty(this.TownName) ? "" : this.TownName;
    }

    public Integer getTypeId() {
        Integer num = this.typeId;
        if (num == null) {
            return 1;
        }
        return num;
    }

    public Integer getUserLevel() {
        Integer num = this.UserLevel;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getWhere() {
        String str = this.where;
        return str == null ? "" : str;
    }

    public String getZip() {
        String str = this.zip;
        return str == null ? "" : str;
    }

    public int hashCode() {
        String str = this.mobile;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.where;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "UserAddress [pin=" + this.pin + ", id=" + this.id + ", idCity=" + this.idCity + ", idTown=" + this.idTown + ", idArea=" + this.idArea + ", idProvince=" + this.idProvince + ", name=" + this.name + ", where=" + this.where + ", typeId=" + this.typeId + ", email=" + this.email + ", mobile=" + this.mobile + ", zip=" + this.zip + ", addressDetail=" + this.addressDetail + ", isNoIdTown=" + this.isNoIdTown + ", message=" + this.message + "]";
    }

    @Override // com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.pin);
        parcel.writeLong(this.id);
        parcel.writeValue(this.idCity);
        parcel.writeValue(this.idTown);
        parcel.writeValue(this.idArea);
        parcel.writeValue(this.idProvince);
        parcel.writeString(this.name);
        parcel.writeString(this.where);
        parcel.writeValue(this.typeId);
        parcel.writeString(this.email);
        parcel.writeString(this.mobile);
        parcel.writeString(this.mobileReal);
        parcel.writeString(this.zip);
        parcel.writeSerializable(this.geoPoint);
        parcel.writeString(this.addressDetail);
        parcel.writeInt(this.coordType);
        parcel.writeValue(this.isDefaultAddr);
        parcel.writeString(this.ProvinceName);
        parcel.writeString(this.CityName);
        parcel.writeString(this.CountryName);
        parcel.writeString(this.TownName);
        parcel.writeString(this.identityCard);
        parcel.writeParcelable(this.addressTagMap, i2);
        parcel.writeDouble(this.longitudeDB);
        parcel.writeDouble(this.latitudeDB);
        parcel.writeInt(this.addressType);
        parcel.writeValue(this.isNoIdTown);
        parcel.writeString(this.message);
        parcel.writeValue(this.UserLevel);
        parcel.writeString(this.phone);
        parcel.writeByte(this.isAreaWrong ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isForeignOverSea ? (byte) 1 : (byte) 0);
        parcel.writeString(this.areaCode);
        parcel.writeString(this.postCode);
        parcel.writeString(this.nameCode);
        parcel.writeInt(this.viewType);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isOperationVisible ? (byte) 1 : (byte) 0);
        parcel.writeString(this.addressUUID);
        parcel.writeInt(this.operationType);
        parcel.writeString(this.clickAble);
        parcel.writeByte(this.presentFlag ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.selectedAddress ? (byte) 1 : (byte) 0);
    }

    public UserAddress() {
        this.coordType = 2;
        this.longitudeDB = -1.0d;
        this.latitudeDB = -1.0d;
    }

    public UserAddress(Parcel parcel) {
        super(parcel);
        this.coordType = 2;
        this.longitudeDB = -1.0d;
        this.latitudeDB = -1.0d;
        this.pin = parcel.readString();
        this.id = parcel.readLong();
        this.idCity = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.idTown = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.idArea = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.idProvince = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.name = parcel.readString();
        this.where = parcel.readString();
        this.typeId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.email = parcel.readString();
        this.mobile = parcel.readString();
        this.mobileReal = parcel.readString();
        this.zip = parcel.readString();
        this.geoPoint = (GeoPoint) parcel.readSerializable();
        this.addressDetail = parcel.readString();
        this.coordType = parcel.readInt();
        this.isDefaultAddr = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.ProvinceName = parcel.readString();
        this.CityName = parcel.readString();
        this.CountryName = parcel.readString();
        this.TownName = parcel.readString();
        this.identityCard = parcel.readString();
        this.addressTagMap = (AddressTagInfo) parcel.readParcelable(AddressTagInfo.class.getClassLoader());
        this.longitudeDB = parcel.readDouble();
        this.latitudeDB = parcel.readDouble();
        this.addressType = parcel.readInt();
        this.isNoIdTown = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.message = parcel.readString();
        this.UserLevel = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.phone = parcel.readString();
        this.isAreaWrong = parcel.readByte() != 0;
        this.isForeignOverSea = parcel.readByte() != 0;
        this.areaCode = parcel.readString();
        this.postCode = parcel.readString();
        this.nameCode = parcel.readString();
        this.viewType = parcel.readInt();
        this.isSelected = parcel.readByte() != 0;
        this.isOperationVisible = parcel.readByte() != 0;
        this.addressUUID = parcel.readString();
        this.operationType = parcel.readInt();
        this.clickAble = parcel.readString();
        this.presentFlag = parcel.readByte() != 0;
        this.selectedAddress = parcel.readByte() != 0;
    }
}
