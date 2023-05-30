package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class CartSummary implements Parcelable {
    public static final String CART_SKUFLAG_YUSHOU = "15";
    public static final Parcelable.Creator<CartSummary> CREATOR = new Parcelable.Creator<CartSummary>() { // from class: com.jingdong.common.entity.cart.CartSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSummary createFromParcel(Parcel parcel) {
            return new CartSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSummary[] newArray(int i2) {
            return new CartSummary[i2];
        }
    };
    public static final int SKU_TYPE_GIFT = 12;
    public static final int SKU_TYPE_PACK = 4;
    public static final int SKU_TYPE_RETURN = 9;
    public static final int SKU_TYPE_SINGLE = 1;
    public int itemType;
    protected int num = 1;
    protected String skuFlag;

    public CartSummary() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getNum() {
        return this.num;
    }

    public String getSkuFlag() {
        return this.skuFlag;
    }

    public void setNum(int i2) {
        this.num = i2;
    }

    public void setSkuFlag(String str) {
        this.skuFlag = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.itemType);
        parcel.writeString(this.skuFlag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CartSummary(Parcel parcel) {
        this.itemType = parcel.readInt();
        this.skuFlag = parcel.readString();
    }
}
