package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressBottomTagMapPaymentMap implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressBottomTagMapPaymentMap> CREATOR = new Parcelable.Creator<AddressBottomTagMapPaymentMap>() { // from class: com.jingdong.common.entity.AddressBottomTagMapPaymentMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapPaymentMap createFromParcel(Parcel parcel) {
            return new AddressBottomTagMapPaymentMap(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapPaymentMap[] newArray(int i2) {
            return new AddressBottomTagMapPaymentMap[i2];
        }
    };
    public int paymentId;

    public AddressBottomTagMapPaymentMap() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.paymentId);
    }

    protected AddressBottomTagMapPaymentMap(Parcel parcel) {
        this.paymentId = parcel.readInt();
    }
}
