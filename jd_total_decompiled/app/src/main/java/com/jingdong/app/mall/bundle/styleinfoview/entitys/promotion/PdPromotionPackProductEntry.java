package com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class PdPromotionPackProductEntry implements Parcelable {
    public static final Parcelable.Creator<PdPromotionPackProductEntry> CREATOR = new Parcelable.Creator<PdPromotionPackProductEntry>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion.PdPromotionPackProductEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdPromotionPackProductEntry createFromParcel(Parcel parcel) {
            return new PdPromotionPackProductEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdPromotionPackProductEntry[] newArray(int i2) {
            return new PdPromotionPackProductEntry[i2];
        }
    };
    private String name = "";
    private long skuId;
    private String skuName;
    private String skuPicUrl;
    private String skuPrice;

    public PdPromotionPackProductEntry() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public long getSkuId() {
        return this.skuId;
    }

    public String getSkuName() {
        return this.skuName;
    }

    public String getSkuPicUrl() {
        return this.skuPicUrl;
    }

    public String getSkuPrice() {
        return this.skuPrice;
    }

    public void readToParcel(Parcel parcel) {
        this.skuPicUrl = parcel.readString();
        this.skuId = parcel.readLong();
        this.skuName = parcel.readString();
        this.skuPrice = parcel.readString();
        this.name = parcel.readString();
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSkuId(long j2) {
        this.skuId = j2;
    }

    public void setSkuName(String str) {
        this.skuName = str;
    }

    public void setSkuPicUrl(String str) {
        this.skuPicUrl = str;
    }

    public void setSkuPrice(String str) {
        this.skuPrice = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.skuPicUrl);
        parcel.writeLong(this.skuId);
        parcel.writeString(this.skuName);
        parcel.writeString(this.skuPrice);
        parcel.writeString(this.name);
    }

    public PdPromotionPackProductEntry(Parcel parcel) {
        readToParcel(parcel);
    }
}
