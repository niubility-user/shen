package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class AddressSearch implements Parcelable {
    public static final Parcelable.Creator<AddressSearch> CREATOR = new Parcelable.Creator<AddressSearch>() { // from class: com.jingdong.common.entity.AddressSearch.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressSearch createFromParcel(Parcel parcel) {
            return new AddressSearch(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressSearch[] newArray(int i2) {
            return new AddressSearch[i2];
        }
    };
    public String areaId;
    public String cityId;
    public String key;
    public double latitude;
    public String latitudeString;
    public double longitude;
    public String longitudeString;
    public String nameCode;
    public String provinceId;
    public String region;
    public String sourceMap;

    public AddressSearch() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String formatText(String str) {
        return formatText(str, false);
    }

    public void setRegion(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.region = "";
        }
        this.region = str + str2 + str3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.key);
        parcel.writeString(this.provinceId);
        parcel.writeString(this.cityId);
        parcel.writeString(this.areaId);
        parcel.writeString(this.region);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeString(this.latitudeString);
        parcel.writeString(this.longitudeString);
        parcel.writeString(this.sourceMap);
        parcel.writeString(this.nameCode);
    }

    protected AddressSearch(Parcel parcel) {
        this.key = parcel.readString();
        this.provinceId = parcel.readString();
        this.cityId = parcel.readString();
        this.areaId = parcel.readString();
        this.region = parcel.readString();
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.latitudeString = parcel.readString();
        this.longitudeString = parcel.readString();
        this.sourceMap = parcel.readString();
        this.nameCode = parcel.readString();
    }

    public String formatText(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "|" : "");
        sb.append(str);
        return sb.toString();
    }
}
