package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SelfUserAddress extends UserAddress {
    public static final Parcelable.Creator<SelfUserAddress> CREATOR = new Parcelable.Creator<SelfUserAddress>() { // from class: com.jingdong.common.entity.SelfUserAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SelfUserAddress createFromParcel(Parcel parcel) {
            return new SelfUserAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SelfUserAddress[] newArray(int i2) {
            return new SelfUserAddress[i2];
        }
    };
    public boolean canUse;
    public String cantUseMsg;
    public List<Long> cantUseSkuList;
    public int cantUseType;
    public boolean isRecommendAddress;
    public String minDistance;
    public String minDistanceMsg;
    public double minDistanceNum;
    public String pickIcon;
    public int pickId;
    public String pickName;

    public SelfUserAddress() {
    }

    @Override // com.jingdong.common.entity.UserAddress, com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.entity.UserAddress, com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.pickId);
        parcel.writeByte(this.isRecommendAddress ? (byte) 1 : (byte) 0);
        parcel.writeString(this.pickIcon);
        parcel.writeString(this.pickName);
        parcel.writeByte(this.canUse ? (byte) 1 : (byte) 0);
        parcel.writeString(this.cantUseMsg);
        parcel.writeInt(this.cantUseType);
        parcel.writeDouble(this.minDistanceNum);
        parcel.writeString(this.minDistance);
        parcel.writeString(this.minDistanceMsg);
        parcel.writeList(this.cantUseSkuList);
    }

    protected SelfUserAddress(Parcel parcel) {
        super(parcel);
        this.pickId = parcel.readInt();
        this.isRecommendAddress = parcel.readByte() != 0;
        this.pickIcon = parcel.readString();
        this.pickName = parcel.readString();
        this.canUse = parcel.readByte() != 0;
        this.cantUseMsg = parcel.readString();
        this.cantUseType = parcel.readInt();
        this.minDistanceNum = parcel.readDouble();
        this.minDistance = parcel.readString();
        this.minDistanceMsg = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.cantUseSkuList = arrayList;
        parcel.readList(arrayList, Long.class.getClassLoader());
    }
}
