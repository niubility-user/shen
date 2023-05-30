package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class CmShareChannelInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CmShareChannelInfo> CREATOR = new Parcelable.Creator<CmShareChannelInfo>() { // from class: com.jingdong.common.entity.CmShareChannelInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmShareChannelInfo createFromParcel(Parcel parcel) {
            return new CmShareChannelInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmShareChannelInfo[] newArray(int i2) {
            return new CmShareChannelInfo[i2];
        }
    };
    public String cmBizId;
    public int cmCIconResId;
    public String cmIconName;
    public String cmIconUrl;

    public CmShareChannelInfo() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return this;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.cmIconName);
        parcel.writeInt(this.cmCIconResId);
        parcel.writeString(this.cmIconUrl);
        parcel.writeString(this.cmBizId);
    }

    public CmShareChannelInfo(String str, int i2, String str2, String str3) {
        this.cmIconName = str;
        this.cmCIconResId = i2;
        this.cmIconUrl = str2;
        this.cmBizId = str3;
    }

    public CmShareChannelInfo(Parcel parcel) {
        this.cmIconName = parcel.readString();
        this.cmCIconResId = parcel.readInt();
        this.cmIconUrl = parcel.readString();
        this.cmBizId = parcel.readString();
    }
}
