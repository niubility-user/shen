package com.jd.lib.productdetail.core.entitys.promotion;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdPromotionPackProductEntry implements Parcelable {
    public static final Parcelable.Creator<PdPromotionPackProductEntry> CREATOR = new Parcelable.Creator<PdPromotionPackProductEntry>() { // from class: com.jd.lib.productdetail.core.entitys.promotion.PdPromotionPackProductEntry.1
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
    private String bindSku;
    private String bindSkuNumText;
    private String name = "";
    private long skuId;
    private String skuName;
    private String skuPicUrl;
    private String skuPrice;
    private int suitProductType;

    public PdPromotionPackProductEntry() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBindSku() {
        return this.bindSku;
    }

    public String getBindSkuNumText() {
        return this.bindSkuNumText;
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

    public int getSuitProductType() {
        return this.suitProductType;
    }

    public void readToParcel(Parcel parcel) {
        this.skuPicUrl = parcel.readString();
        this.skuId = parcel.readLong();
        this.skuName = parcel.readString();
        this.skuPrice = parcel.readString();
        this.name = parcel.readString();
        this.suitProductType = parcel.readInt();
        this.bindSku = parcel.readString();
        this.bindSkuNumText = parcel.readString();
    }

    public void setBindSku(String str) {
        this.bindSku = str;
    }

    public void setBindSkuNumText(String str) {
        this.bindSkuNumText = str;
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

    public void setSuitProductType(int i2) {
        this.suitProductType = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.skuPicUrl);
        parcel.writeLong(this.skuId);
        parcel.writeString(this.skuName);
        parcel.writeString(this.skuPrice);
        parcel.writeString(this.name);
        parcel.writeInt(this.suitProductType);
        parcel.writeString(this.bindSku);
        parcel.writeString(this.bindSkuNumText);
    }

    public PdPromotionPackProductEntry(Parcel parcel) {
        readToParcel(parcel);
    }
}
