package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes5.dex */
public class CartAddHotListBaseItem implements Parcelable {
    public static final Parcelable.Creator<CartAddHotListBaseItem> CREATOR = new Parcelable.Creator<CartAddHotListBaseItem>() { // from class: com.jingdong.common.entity.cart.CartAddHotListBaseItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartAddHotListBaseItem createFromParcel(Parcel parcel) {
            return new CartAddHotListBaseItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartAddHotListBaseItem[] newArray(int i2) {
            return new CartAddHotListBaseItem[i2];
        }
    };
    public String addCartUsrNum;
    public int addCartUsrNumRnk;
    public String jdPrice;
    public Long skuId;
    public String skuImg;
    public String skuName;

    public CartAddHotListBaseItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.skuId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.skuName = parcel.readString();
        this.skuImg = parcel.readString();
        this.addCartUsrNum = parcel.readString();
        this.addCartUsrNumRnk = parcel.readInt();
        this.jdPrice = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.skuId);
        parcel.writeString(this.skuName);
        parcel.writeString(this.skuImg);
        parcel.writeString(this.addCartUsrNum);
        parcel.writeInt(this.addCartUsrNumRnk);
        parcel.writeString(this.jdPrice);
    }

    public CartAddHotListBaseItem(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.skuId = Long.valueOf(jDJSONObject.optLong("skuId", 0L));
        this.skuName = jDJSONObject.optString("skuName", "");
        this.skuImg = jDJSONObject.optString("skuImg", "");
        this.addCartUsrNum = jDJSONObject.optString("addCartUsrNum", "");
        this.addCartUsrNumRnk = jDJSONObject.optInt("addCartUsrNumRnk", 0);
        this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE, "");
    }

    protected CartAddHotListBaseItem(Parcel parcel) {
        this.skuId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.skuName = parcel.readString();
        this.skuImg = parcel.readString();
        this.addCartUsrNum = parcel.readString();
        this.addCartUsrNumRnk = parcel.readInt();
        this.jdPrice = parcel.readString();
    }
}
