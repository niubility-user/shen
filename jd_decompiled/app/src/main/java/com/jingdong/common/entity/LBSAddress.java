package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class LBSAddress implements Parcelable {
    public static final Parcelable.Creator<LBSAddress> CREATOR = new Parcelable.Creator<LBSAddress>() { // from class: com.jingdong.common.entity.LBSAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LBSAddress createFromParcel(Parcel parcel) {
            return new LBSAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LBSAddress[] newArray(int i2) {
            return new LBSAddress[i2];
        }
    };
    public int cityId;
    public String cityName;
    public int districtId;
    public String districtName;
    public String message;
    public int provinceId;
    public String provinceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        if (TextUtils.isEmpty(getProvinceName())) {
            return "";
        }
        return getProvinceName() + getCityName();
    }

    public String getCityName() {
        return TextUtils.isEmpty(this.cityName) ? "" : this.cityName;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.provinceName) ? "" : this.provinceName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.provinceId);
        parcel.writeInt(this.cityId);
        parcel.writeInt(this.districtId);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.cityName);
        parcel.writeString(this.districtName);
        parcel.writeString(this.message);
    }

    public LBSAddress() {
    }

    private LBSAddress(Parcel parcel) {
        this.provinceId = parcel.readInt();
        this.cityId = parcel.readInt();
        this.districtId = parcel.readInt();
        this.provinceName = parcel.readString();
        this.cityName = parcel.readString();
        this.districtName = parcel.readString();
        this.message = parcel.readString();
    }
}
