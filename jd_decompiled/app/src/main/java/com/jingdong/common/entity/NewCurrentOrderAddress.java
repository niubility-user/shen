package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.entity.settlement.AddressOverSea;
import com.jingdong.common.entity.settlement.AddressTagInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.utils.StringUtils;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NewCurrentOrderAddress extends AddressOverSea implements Serializable {
    public static final Parcelable.Creator<NewCurrentOrderAddress> CREATOR = new Parcelable.Creator<NewCurrentOrderAddress>() { // from class: com.jingdong.common.entity.NewCurrentOrderAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewCurrentOrderAddress createFromParcel(Parcel parcel) {
            return new NewCurrentOrderAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewCurrentOrderAddress[] newArray(int i2) {
            return new NewCurrentOrderAddress[i2];
        }
    };
    public static final int EUROPEAN_ADDRESS_TYPE = 1;
    public static final int NORMAL_ADDRESS_TYPE = 0;
    private static final String TAG = "NewCurrentOrderAddress";
    public String MobileReal;
    public boolean addressDefault;
    public String addressDetail;
    public String addressPromptMsg;
    public AddressTagInfo addressTagMap;
    public int addressType;
    public String addressUUID;
    public String cityName;
    public String countryName;
    public boolean giftBuyHidePrice;
    public String giftRecImg;
    public String giftSenderConsigneeMobile;
    public String giftSenderConsigneeMobileReal;
    public String giftSenderConsigneeName;
    public String giftSenderImg;
    public String giftSenderMessage;
    public long id;
    public Integer idArea;
    public Integer idCity;
    public Integer idCompanyBranch;
    public Integer idProvince;
    public Integer idTown;
    public String identityCard;
    public Boolean isIdTown;
    public boolean isPickAddress;
    public String mobile;
    public String mobileTextColor;
    public String name;
    public String nameAndAddressDetail;
    public String phone;
    public String pickAddressIcon;
    public String pickDIscountMsg;
    public String pickName;
    public String pin;
    public String provinceName;
    public long serviceProviderId;
    public String townName;
    public Integer userLevel;
    public String where;
    public String zip;

    public NewCurrentOrderAddress() {
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean getAddressDefault() {
        return this.addressDefault;
    }

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

    public String getCityName() {
        return TextUtils.isEmpty(this.cityName) ? "" : this.cityName;
    }

    public String getCountryName() {
        String str = this.countryName;
        return str == null ? "" : str;
    }

    public String getGiftRecImg() {
        return TextUtils.isEmpty(this.giftRecImg) ? "" : this.giftRecImg;
    }

    public String getGiftSenderConsigneeMobile() {
        return TextUtils.isEmpty(this.giftSenderConsigneeMobile) ? "" : this.giftSenderConsigneeMobile;
    }

    public String getGiftSenderConsigneeName() {
        return TextUtils.isEmpty(this.giftSenderConsigneeName) ? "" : this.giftSenderConsigneeName;
    }

    public String getGiftSenderImg() {
        return TextUtils.isEmpty(this.giftSenderImg) ? "" : this.giftSenderImg;
    }

    public String getGiftSenderMessage() {
        return TextUtils.isEmpty(this.giftSenderMessage) ? "" : this.giftSenderMessage;
    }

    public Integer getIdArea() {
        Integer num = this.idArea;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdCity() {
        Integer num = this.idCity;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdCompanyBranch() {
        Integer num = this.idCompanyBranch;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdProvince() {
        Integer num = this.idProvince;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdTown() {
        Integer num = this.idTown;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Boolean getIsIdTown() {
        Boolean bool = this.isIdTown;
        return bool == null ? Boolean.FALSE : bool;
    }

    public String getMobile() {
        String str = this.mobile;
        return str == null ? "" : this.isPickAddress ? str : StringUtils.getPhoneNumber(str);
    }

    public String getMobileTextColor() {
        return this.mobileTextColor;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getNormalMobile() {
        String str = this.mobile;
        return str == null ? "" : str;
    }

    public String getPhone() {
        String str = this.phone;
        return str == null ? "" : StringUtils.getPhoneNumber(str);
    }

    public String getPin() {
        String str = this.pin;
        return str == null ? "" : str;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.provinceName) ? "" : this.provinceName;
    }

    public String getTownName() {
        return TextUtils.isEmpty(this.townName) ? "" : this.townName;
    }

    public Integer getUserLevel() {
        Integer num = this.userLevel;
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

    public boolean isEuropeanAddress() {
        return this.addressType == 1;
    }

    public JSONObject toJsonIds() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("IdArea", getIdArea());
            jSONObject.put("IdCity", getIdCity());
            jSONObject.put("IdProvince", getIdProvince());
            jSONObject.put("IdTown", getIdTown());
            jSONObject.put("IdCompanyBranch", getIdCompanyBranch());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.countryName);
        parcel.writeString(this.pin);
        parcel.writeString(this.phone);
        parcel.writeString(this.name);
        parcel.writeValue(this.isIdTown);
        parcel.writeValue(this.userLevel);
        parcel.writeString(this.cityName);
        parcel.writeValue(this.idCompanyBranch);
        parcel.writeString(this.where);
        parcel.writeString(this.zip);
        parcel.writeValue(this.idTown);
        parcel.writeString(this.mobile);
        parcel.writeString(this.MobileReal);
        parcel.writeValue(this.idProvince);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.townName);
        parcel.writeValue(this.idCity);
        parcel.writeValue(this.idArea);
        parcel.writeString(this.addressDetail);
        parcel.writeByte(this.addressDefault ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.id);
        parcel.writeString(this.giftSenderImg);
        parcel.writeString(this.giftRecImg);
        parcel.writeString(this.giftSenderMessage);
        parcel.writeString(this.giftSenderConsigneeName);
        parcel.writeString(this.giftSenderConsigneeMobile);
        parcel.writeString(this.giftSenderConsigneeMobileReal);
        parcel.writeString(this.identityCard);
        parcel.writeByte(this.giftBuyHidePrice ? (byte) 1 : (byte) 0);
        parcel.writeString(this.addressPromptMsg);
        parcel.writeParcelable(this.addressTagMap, i2);
        parcel.writeByte(this.isPickAddress ? (byte) 1 : (byte) 0);
        parcel.writeString(this.pickName);
        parcel.writeString(this.pickAddressIcon);
        parcel.writeString(this.pickDIscountMsg);
        parcel.writeString(this.addressUUID);
        parcel.writeString(this.mobileTextColor);
        parcel.writeInt(this.addressType);
        parcel.writeLong(this.serviceProviderId);
        parcel.writeString(this.nameAndAddressDetail);
    }

    protected NewCurrentOrderAddress(Parcel parcel) {
        super(parcel);
        this.countryName = parcel.readString();
        this.pin = parcel.readString();
        this.phone = parcel.readString();
        this.name = parcel.readString();
        this.isIdTown = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.userLevel = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.cityName = parcel.readString();
        this.idCompanyBranch = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.where = parcel.readString();
        this.zip = parcel.readString();
        this.idTown = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.mobile = parcel.readString();
        this.MobileReal = parcel.readString();
        this.idProvince = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.provinceName = parcel.readString();
        this.townName = parcel.readString();
        this.idCity = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.idArea = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.addressDetail = parcel.readString();
        this.addressDefault = parcel.readByte() != 0;
        this.id = parcel.readLong();
        this.giftSenderImg = parcel.readString();
        this.giftRecImg = parcel.readString();
        this.giftSenderMessage = parcel.readString();
        this.giftSenderConsigneeName = parcel.readString();
        this.giftSenderConsigneeMobile = parcel.readString();
        this.giftSenderConsigneeMobileReal = parcel.readString();
        this.identityCard = parcel.readString();
        this.giftBuyHidePrice = parcel.readByte() != 0;
        this.addressPromptMsg = parcel.readString();
        this.addressTagMap = (AddressTagInfo) parcel.readParcelable(AddressTagInfo.class.getClassLoader());
        this.isPickAddress = parcel.readByte() != 0;
        this.pickName = parcel.readString();
        this.pickAddressIcon = parcel.readString();
        this.pickDIscountMsg = parcel.readString();
        this.addressUUID = parcel.readString();
        this.mobileTextColor = parcel.readString();
        this.addressType = parcel.readInt();
        this.serviceProviderId = parcel.readLong();
        this.nameAndAddressDetail = parcel.readString();
    }
}
