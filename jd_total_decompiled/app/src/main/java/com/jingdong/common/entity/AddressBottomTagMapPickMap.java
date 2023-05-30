package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressBottomTagMapPickMap implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressBottomTagMapPickMap> CREATOR = new Parcelable.Creator<AddressBottomTagMapPickMap>() { // from class: com.jingdong.common.entity.AddressBottomTagMapPickMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapPickMap createFromParcel(Parcel parcel) {
            return new AddressBottomTagMapPickMap(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressBottomTagMapPickMap[] newArray(int i2) {
            return new AddressBottomTagMapPickMap[i2];
        }
    };
    public int pickId;

    public AddressBottomTagMapPickMap() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.pickId);
    }

    protected AddressBottomTagMapPickMap(Parcel parcel) {
        this.pickId = parcel.readInt();
    }
}
