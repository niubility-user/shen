package com.jingdong.common.ui.homemix.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class ShopParam implements Parcelable {
    public static final Parcelable.Creator<ShopParam> CREATOR = new Parcelable.Creator<ShopParam>() { // from class: com.jingdong.common.ui.homemix.entity.ShopParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopParam createFromParcel(Parcel parcel) {
            return new ShopParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopParam[] newArray(int i2) {
            return new ShopParam[i2];
        }
    };
    public String shopId;
    public String shopType;
    public String venderId;

    public ShopParam() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.venderId);
        parcel.writeString(this.shopId);
        parcel.writeString(this.shopType);
    }

    protected ShopParam(Parcel parcel) {
        this.venderId = parcel.readString();
        this.shopId = parcel.readString();
        this.shopType = parcel.readString();
    }
}
