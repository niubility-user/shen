package com.jingdong.sdk.platform.lib.entity.share;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes10.dex */
public class ShareImageItem implements Parcelable, Cloneable {
    public static final Parcelable.Creator<ShareImageItem> CREATOR = new Parcelable.Creator<ShareImageItem>() { // from class: com.jingdong.sdk.platform.lib.entity.share.ShareImageItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareImageItem createFromParcel(Parcel parcel) {
            return new ShareImageItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareImageItem[] newArray(int i2) {
            return new ShareImageItem[i2];
        }
    };
    public String directPath;
    public String directUrl;
    public String logoUrl;
    public String productDesc;
    public String productPath;
    public String productTitle;
    public String productUrl;
    public String slogan;

    public ShareImageItem() {
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
        parcel.writeString(this.directPath);
    }

    public ShareImageItem(String str, String str2, String str3, String str4, String str5) {
        this.logoUrl = str;
        this.slogan = str2;
        this.productUrl = str3;
        this.productTitle = str4;
        this.productDesc = str5;
    }

    public ShareImageItem(Parcel parcel) {
        this.logoUrl = parcel.readString();
        this.slogan = parcel.readString();
        this.productUrl = parcel.readString();
        this.productPath = parcel.readString();
        this.productTitle = parcel.readString();
        this.productDesc = parcel.readString();
        this.directUrl = parcel.readString();
        this.directPath = parcel.readString();
    }
}
