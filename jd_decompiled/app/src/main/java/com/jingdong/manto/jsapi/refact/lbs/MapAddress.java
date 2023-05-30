package com.jingdong.manto.jsapi.refact.lbs;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.utils.MantoUtils;

/* loaded from: classes15.dex */
public class MapAddress implements Parcelable {
    public static final Parcelable.Creator<MapAddress> CREATOR = new Parcelable.Creator<MapAddress>() { // from class: com.jingdong.manto.jsapi.refact.lbs.MapAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MapAddress createFromParcel(Parcel parcel) {
            MapAddress mapAddress = new MapAddress();
            mapAddress.address = parcel.readString();
            mapAddress.country = parcel.readString();
            mapAddress.name = parcel.readString();
            mapAddress.latitude = parcel.readDouble();
            mapAddress.longitude = parcel.readDouble();
            return mapAddress;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MapAddress[] newArray(int i2) {
            return new MapAddress[i2];
        }
    };
    public String address;
    public String country;
    public double latitude;
    public double longitude;
    public String name;

    public final String Kx() {
        return "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("address='" + this.address + '\'');
        sb.append(", country='" + this.country + '\'');
        sb.append(", poi_name='" + this.name + '\'');
        StringBuilder sb2 = new StringBuilder();
        sb2.append(", lat=");
        sb2.append(this.latitude);
        sb.append(sb2.toString());
        sb.append(", lng=" + this.longitude);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(MantoUtils.getNonNull(this.address, ""));
        parcel.writeString(MantoUtils.getNonNull(this.country, ""));
        parcel.writeString(MantoUtils.getNonNull(this.name, ""));
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }
}
