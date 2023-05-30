package com.jingdong.common.ui.address.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class AddressPageParams implements Parcelable {
    public static final Parcelable.Creator<AddressPageParams> CREATOR = new Parcelable.Creator<AddressPageParams>() { // from class: com.jingdong.common.ui.address.entity.AddressPageParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressPageParams createFromParcel(Parcel parcel) {
            return new AddressPageParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressPageParams[] newArray(int i2) {
            return new AddressPageParams[i2];
        }
    };
    private int canEditLevel;
    private int cityId;
    private String cityName;
    private int countyId;
    private String countyName;
    private int provinceId;
    private String provinceName;
    public String saveBusiness;
    public String sceneId;
    private String shopId;
    private String shopType;
    public boolean showLevelAddress;
    private String sku;
    public String source;
    private int townId;
    private String townName;
    private String venderId;

    public AddressPageParams() {
        this.provinceId = -1;
        this.cityId = -1;
        this.countyId = -1;
        this.townId = -1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCanEditLevel() {
        return this.canEditLevel;
    }

    public int getCityId() {
        return this.cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public int getCountyId() {
        return this.countyId;
    }

    public String getCountyName() {
        return this.countyName;
    }

    public int getProvinceId() {
        return this.provinceId;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public String getSaveBusiness() {
        return this.saveBusiness;
    }

    public String getSceneId() {
        return this.sceneId;
    }

    public String getShopId() {
        return this.shopId;
    }

    public String getShopType() {
        return this.shopType;
    }

    public String getSku() {
        return this.sku;
    }

    public String getSource() {
        return this.source;
    }

    public int getTownId() {
        return this.townId;
    }

    public String getTownName() {
        return this.townName;
    }

    public String getVenderId() {
        return this.venderId;
    }

    public boolean isShowLevelAddress() {
        return this.showLevelAddress;
    }

    public void readFromParcel(Parcel parcel) {
        this.venderId = parcel.readString();
        this.shopId = parcel.readString();
        this.shopType = parcel.readString();
        this.sku = parcel.readString();
        this.provinceId = parcel.readInt();
        this.cityId = parcel.readInt();
        this.countyId = parcel.readInt();
        this.townId = parcel.readInt();
        this.provinceName = parcel.readString();
        this.cityName = parcel.readString();
        this.countyName = parcel.readString();
        this.townName = parcel.readString();
        this.canEditLevel = parcel.readInt();
        this.showLevelAddress = parcel.readByte() != 0;
        this.saveBusiness = parcel.readString();
        this.source = parcel.readString();
        this.sceneId = parcel.readString();
    }

    public void setCanEditLevel(int i2) {
        this.canEditLevel = i2;
    }

    public void setCityId(int i2) {
        this.cityId = i2;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setCountyId(int i2) {
        this.countyId = i2;
    }

    public void setCountyName(String str) {
        this.countyName = str;
    }

    public void setProvinceId(int i2) {
        this.provinceId = i2;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public void setSaveBusiness(String str) {
        this.saveBusiness = str;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
    }

    public void setShopId(String str) {
        this.shopId = str;
    }

    public void setShopType(String str) {
        this.shopType = str;
    }

    public void setShowLevelAddress(boolean z) {
        this.showLevelAddress = z;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setTownId(int i2) {
        this.townId = i2;
    }

    public void setTownName(String str) {
        this.townName = str;
    }

    public void setVenderId(String str) {
        this.venderId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.venderId);
        parcel.writeString(this.shopId);
        parcel.writeString(this.shopType);
        parcel.writeString(this.sku);
        parcel.writeInt(this.provinceId);
        parcel.writeInt(this.cityId);
        parcel.writeInt(this.countyId);
        parcel.writeInt(this.townId);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.cityName);
        parcel.writeString(this.countyName);
        parcel.writeString(this.townName);
        parcel.writeInt(this.canEditLevel);
        parcel.writeByte(this.showLevelAddress ? (byte) 1 : (byte) 0);
        parcel.writeString(this.saveBusiness);
        parcel.writeString(this.source);
        parcel.writeString(this.sceneId);
    }

    protected AddressPageParams(Parcel parcel) {
        this.provinceId = -1;
        this.cityId = -1;
        this.countyId = -1;
        this.townId = -1;
        this.venderId = parcel.readString();
        this.shopId = parcel.readString();
        this.shopType = parcel.readString();
        this.sku = parcel.readString();
        this.provinceId = parcel.readInt();
        this.cityId = parcel.readInt();
        this.countyId = parcel.readInt();
        this.townId = parcel.readInt();
        this.provinceName = parcel.readString();
        this.cityName = parcel.readString();
        this.countyName = parcel.readString();
        this.townName = parcel.readString();
        this.canEditLevel = parcel.readInt();
        this.showLevelAddress = parcel.readByte() != 0;
        this.saveBusiness = parcel.readString();
        this.source = parcel.readString();
        this.sceneId = parcel.readString();
    }
}
