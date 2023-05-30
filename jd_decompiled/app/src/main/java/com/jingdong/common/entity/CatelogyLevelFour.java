package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class CatelogyLevelFour implements Parcelable {
    public static final Parcelable.Creator<CatelogyLevelFour> CREATOR = new Parcelable.Creator<CatelogyLevelFour>() { // from class: com.jingdong.common.entity.CatelogyLevelFour.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CatelogyLevelFour createFromParcel(Parcel parcel) {
            return new CatelogyLevelFour(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CatelogyLevelFour[] newArray(int i2) {
            return new CatelogyLevelFour[i2];
        }
    };
    public String catalogId;
    public String catalogName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.catalogId);
        parcel.writeString(this.catalogName);
    }

    public CatelogyLevelFour() {
    }

    private CatelogyLevelFour(Parcel parcel) {
        this.catalogId = parcel.readString();
        this.catalogName = parcel.readString();
    }
}
