package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressBottomTagMapEasyBuyMap implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressBottomTagMapEasyBuyMap> CREATOR = new Parcelable.Creator<AddressBottomTagMapEasyBuyMap>() { // from class: com.jingdong.common.entity.AddressBottomTagMapEasyBuyMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapEasyBuyMap createFromParcel(Parcel parcel) {
            return new AddressBottomTagMapEasyBuyMap(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapEasyBuyMap[] newArray(int i2) {
            return new AddressBottomTagMapEasyBuyMap[i2];
        }
    };
    public boolean Onekey2Order;

    public AddressBottomTagMapEasyBuyMap() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.Onekey2Order ? (byte) 1 : (byte) 0);
    }

    protected AddressBottomTagMapEasyBuyMap(Parcel parcel) {
        this.Onekey2Order = parcel.readByte() != 0;
    }
}
