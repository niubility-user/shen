package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class ShareImageInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<ShareImageInfo> CREATOR = new Parcelable.Creator<ShareImageInfo>() { // from class: com.jingdong.common.entity.ShareImageInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareImageInfo createFromParcel(Parcel parcel) {
            return new ShareImageInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareImageInfo[] newArray(int i2) {
            return new ShareImageInfo[i2];
        }
    };
    public String channels;
    public String directPath;
    public String directUrl;
    public int imageShareType;
    public int isBizCustom;
    public int isDecodeDirectUrl;
    public String logoUrl;
    public String productDesc;
    public String productPath;
    public String productTitle;
    public String productUrl;
    public String slogan;

    public ShareImageInfo() {
        this.isBizCustom = 0;
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
        parcel.writeString(this.logoUrl);
        parcel.writeString(this.slogan);
        parcel.writeString(this.productUrl);
        parcel.writeString(this.productPath);
        parcel.writeString(this.productTitle);
        parcel.writeString(this.productDesc);
        parcel.writeString(this.directUrl);
        parcel.writeInt(this.imageShareType);
        parcel.writeInt(this.isDecodeDirectUrl);
        parcel.writeString(this.directPath);
        parcel.writeInt(this.isBizCustom);
    }

    public ShareImageInfo(String str, String str2, String str3, String str4, String str5) {
        this.isBizCustom = 0;
        this.logoUrl = str;
        this.slogan = str2;
        this.productUrl = str3;
        this.productTitle = str4;
        this.productDesc = str5;
    }

    public ShareImageInfo(Parcel parcel) {
        this.isBizCustom = 0;
        this.logoUrl = parcel.readString();
        this.slogan = parcel.readString();
        this.productUrl = parcel.readString();
        this.productPath = parcel.readString();
        this.productTitle = parcel.readString();
        this.productDesc = parcel.readString();
        this.directUrl = parcel.readString();
        this.imageShareType = parcel.readInt();
        this.isDecodeDirectUrl = parcel.readInt();
        this.directPath = parcel.readString();
        this.isBizCustom = parcel.readInt();
    }
}
