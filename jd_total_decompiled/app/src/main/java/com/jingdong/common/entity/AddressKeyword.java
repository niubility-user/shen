package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class AddressKeyword implements Parcelable {
    public static final Parcelable.Creator<AddressKeyword> CREATOR = new Parcelable.Creator<AddressKeyword>() { // from class: com.jingdong.common.entity.AddressKeyword.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressKeyword createFromParcel(Parcel parcel) {
            return new AddressKeyword(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressKeyword[] newArray(int i2) {
            return new AddressKeyword[i2];
        }
    };
    public String keyword;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.keyword);
    }

    public AddressKeyword() {
    }

    private AddressKeyword(Parcel parcel) {
        this.keyword = parcel.readString();
    }
}
