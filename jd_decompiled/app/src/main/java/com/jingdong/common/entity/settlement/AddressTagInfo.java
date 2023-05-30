package com.jingdong.common.entity.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressTagInfo implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressTagInfo> CREATOR = new Parcelable.Creator<AddressTagInfo>() { // from class: com.jingdong.common.entity.settlement.AddressTagInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressTagInfo createFromParcel(Parcel parcel) {
            return new AddressTagInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressTagInfo[] newArray(int i2) {
            return new AddressTagInfo[i2];
        }
    };
    public static final int CUSTOM_TYPE = 2;
    public static final int TECENT_TYPE = 1;
    public int addressTagId;
    public String addressTagName;
    public int addressTagTextColorId;
    public int addressTagType;

    public AddressTagInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.addressTagType);
        parcel.writeString(this.addressTagName);
        parcel.writeInt(this.addressTagId);
        parcel.writeInt(this.addressTagTextColorId);
    }

    protected AddressTagInfo(Parcel parcel) {
        this.addressTagType = parcel.readInt();
        this.addressTagName = parcel.readString();
        this.addressTagId = parcel.readInt();
        this.addressTagTextColorId = parcel.readInt();
    }
}
