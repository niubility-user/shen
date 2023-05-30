package com.jingdong.common.entity.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AddressOverSea implements Parcelable, Serializable {
    public static final Parcelable.Creator<AddressOverSea> CREATOR = new Parcelable.Creator<AddressOverSea>() { // from class: com.jingdong.common.entity.settlement.AddressOverSea.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressOverSea createFromParcel(Parcel parcel) {
            return new AddressOverSea(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressOverSea[] newArray(int i2) {
            return new AddressOverSea[i2];
        }
    };
    public String areaCode;
    public String email;
    public boolean isForeignOverSea;
    public boolean isGangAoTai;
    public String latitudeString;
    public String longitudeString;
    public String nameCode;
    public String phone;
    public String postCode;

    public AddressOverSea() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.isForeignOverSea ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isGangAoTai ? (byte) 1 : (byte) 0);
        parcel.writeString(this.areaCode);
        parcel.writeString(this.postCode);
        parcel.writeString(this.email);
        parcel.writeString(this.nameCode);
        parcel.writeString(this.phone);
        parcel.writeString(this.longitudeString);
        parcel.writeString(this.latitudeString);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AddressOverSea(Parcel parcel) {
        this.isForeignOverSea = parcel.readByte() != 0;
        this.isGangAoTai = parcel.readByte() != 0;
        this.areaCode = parcel.readString();
        this.postCode = parcel.readString();
        this.email = parcel.readString();
        this.nameCode = parcel.readString();
        this.phone = parcel.readString();
        this.longitudeString = parcel.readString();
        this.latitudeString = parcel.readString();
    }
}
