package com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PdPromotionPackEntry implements Parcelable {
    public static final Parcelable.Creator<PdPromotionPackEntry> CREATOR = new Parcelable.Creator<PdPromotionPackEntry>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion.PdPromotionPackEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdPromotionPackEntry createFromParcel(Parcel parcel) {
            return new PdPromotionPackEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdPromotionPackEntry[] newArray(int i2) {
            return new PdPromotionPackEntry[i2];
        }
    };
    private String discount;
    private long mainSkuId;
    private String mainSkuName;
    private String mainSkuPicUrl;
    private String mainSkuPrice;
    private long packId;
    private String packListPrice;
    private String packName;
    private String packPrice;
    private String packType;
    private List<PdPromotionPackProductEntry> productList = new ArrayList();

    public PdPromotionPackEntry() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDiscount() {
        return this.discount;
    }

    public long getMainSkuId() {
        return this.mainSkuId;
    }

    public String getMainSkuName() {
        return this.mainSkuName;
    }

    public String getMainSkuPicUrl() {
        return this.mainSkuPicUrl;
    }

    public String getMainSkuPrice() {
        return this.mainSkuPrice;
    }

    public long getPackId() {
        return this.packId;
    }

    public String getPackListPrice() {
        return this.packListPrice;
    }

    public String getPackName() {
        return this.packName;
    }

    public String getPackPrice() {
        return this.packPrice;
    }

    public String getPackType() {
        return this.packType;
    }

    public List<PdPromotionPackProductEntry> getProductList() {
        return this.productList;
    }

    public void readToParcel(Parcel parcel) {
        this.packListPrice = parcel.readString();
        this.packPrice = parcel.readString();
        this.mainSkuPicUrl = parcel.readString();
        this.packId = parcel.readLong();
        this.mainSkuId = parcel.readLong();
        this.discount = parcel.readString();
        this.packName = parcel.readString();
        this.mainSkuName = parcel.readString();
        this.mainSkuPrice = parcel.readString();
        this.packType = parcel.readString();
        parcel.readTypedList(this.productList, PdPromotionPackProductEntry.CREATOR);
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public void setMainSkuId(long j2) {
        this.mainSkuId = j2;
    }

    public void setMainSkuName(String str) {
        this.mainSkuName = str;
    }

    public void setMainSkuPicUrl(String str) {
        this.mainSkuPicUrl = str;
    }

    public void setMainSkuPrice(String str) {
        this.mainSkuPrice = str;
    }

    public void setPackId(long j2) {
        this.packId = j2;
    }

    public void setPackListPrice(String str) {
        this.packListPrice = str;
    }

    public void setPackName(String str) {
        this.packName = str;
    }

    public void setPackPrice(String str) {
        this.packPrice = str;
    }

    public void setPackType(String str) {
        this.packType = str;
    }

    public void setProductList(List<PdPromotionPackProductEntry> list) {
        this.productList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.packListPrice);
        parcel.writeString(this.packPrice);
        parcel.writeString(this.mainSkuPicUrl);
        parcel.writeLong(this.packId);
        parcel.writeLong(this.mainSkuId);
        parcel.writeString(this.discount);
        parcel.writeString(this.packName);
        parcel.writeString(this.mainSkuName);
        parcel.writeString(this.mainSkuPrice);
        parcel.writeString(this.packType);
        parcel.writeTypedList(this.productList);
    }

    public PdPromotionPackEntry(Parcel parcel) {
        readToParcel(parcel);
    }
}
