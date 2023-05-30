package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressBottomTagMap implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressBottomTagMap> CREATOR = new Parcelable.Creator<AddressBottomTagMap>() { // from class: com.jingdong.common.entity.AddressBottomTagMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMap createFromParcel(Parcel parcel) {
            return new AddressBottomTagMap(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMap[] newArray(int i2) {
            return new AddressBottomTagMap[i2];
        }
    };
    public AddressBottomTagMapEasyBuyMap easyBuyMap;
    public AddressBottomTagMapPaymentMap paymentMap;
    public AddressBottomTagMapPickMap pickMap;

    public AddressBottomTagMap() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.paymentMap, i2);
        parcel.writeParcelable(this.pickMap, i2);
        parcel.writeParcelable(this.easyBuyMap, i2);
    }

    protected AddressBottomTagMap(Parcel parcel) {
        this.paymentMap = (AddressBottomTagMapPaymentMap) parcel.readParcelable(AddressBottomTagMapPaymentMap.class.getClassLoader());
        this.pickMap = (AddressBottomTagMapPickMap) parcel.readParcelable(AddressBottomTagMapPickMap.class.getClassLoader());
        this.easyBuyMap = (AddressBottomTagMapEasyBuyMap) parcel.readParcelable(AddressBottomTagMapEasyBuyMap.class.getClassLoader());
    }
}
