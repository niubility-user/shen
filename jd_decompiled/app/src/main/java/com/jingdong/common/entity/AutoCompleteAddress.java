package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class AutoCompleteAddress implements Parcelable {
    public static final Parcelable.Creator<AutoCompleteAddress> CREATOR = new Parcelable.Creator<AutoCompleteAddress>() { // from class: com.jingdong.common.entity.AutoCompleteAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoCompleteAddress createFromParcel(Parcel parcel) {
            return new AutoCompleteAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoCompleteAddress[] newArray(int i2) {
            return new AutoCompleteAddress[i2];
        }
    };
    public String areaName;
    public String cityName;
    public int coord_type;
    public String distance;
    public String id;
    public boolean isForeignOverSea;
    public boolean isGangAoTai;
    public double latitude;
    public String latitudeString;
    public double longitude;
    public String longitudeString;
    public String provinceName;
    public String shortAddress;
    public String shortTitle;
    public String title;

    public AutoCompleteAddress() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.shortTitle);
        parcel.writeString(this.shortAddress);
        parcel.writeString(this.provinceName);
        parcel.writeString(this.cityName);
        parcel.writeString(this.areaName);
        parcel.writeString(this.distance);
        parcel.writeByte(this.isGangAoTai ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isForeignOverSea ? (byte) 1 : (byte) 0);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
        parcel.writeString(this.latitudeString);
        parcel.writeString(this.longitudeString);
        parcel.writeInt(this.coord_type);
    }

    protected AutoCompleteAddress(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.shortTitle = parcel.readString();
        this.shortAddress = parcel.readString();
        this.provinceName = parcel.readString();
        this.cityName = parcel.readString();
        this.areaName = parcel.readString();
        this.distance = parcel.readString();
        this.isGangAoTai = parcel.readByte() != 0;
        this.isForeignOverSea = parcel.readByte() != 0;
        this.longitude = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.latitudeString = parcel.readString();
        this.longitudeString = parcel.readString();
        this.coord_type = parcel.readInt();
    }
}
