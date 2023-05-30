package com.jingdong.sdk.jdupgrade;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public enum RemindType implements Parcelable {
    UPGRADE_REMIND,
    INSTALL_REMIND,
    DOWNLOADING_REMIND;
    
    public static final Parcelable.Creator<RemindType> CREATOR = new Parcelable.Creator<RemindType>() { // from class: com.jingdong.sdk.jdupgrade.RemindType.a
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public RemindType createFromParcel(Parcel parcel) {
            return RemindType.values()[parcel.readInt()];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public RemindType[] newArray(int i2) {
            return new RemindType[i2];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return RemindType.class.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(ordinal());
    }
}
