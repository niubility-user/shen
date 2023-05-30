package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartShopFareInfo implements Parcelable {
    public static final Parcelable.Creator<CartShopFareInfo> CREATOR = new Parcelable.Creator<CartShopFareInfo>() { // from class: com.jingdong.common.entity.cart.CartShopFareInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartShopFareInfo createFromParcel(Parcel parcel) {
            return new CartShopFareInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartShopFareInfo[] newArray(int i2) {
            return new CartShopFareInfo[i2];
        }
    };
    public int fareType;
    public PieceOrder pieceOrder;
    public String priceNeed;
    public String weightNeed;

    public CartShopFareInfo() {
    }

    public static HashMap<String, CartShopFareInfo> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        HashMap<String, CartShopFareInfo> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            CartShopFareInfo parseOrderInfo = parseOrderInfo(jDJSONObject.optJSONObject(str));
            if (parseOrderInfo != null) {
                hashMap.put(str, parseOrderInfo);
            }
        }
        return hashMap;
    }

    private static CartShopFareInfo parseOrderInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartShopFareInfo cartShopFareInfo = new CartShopFareInfo();
        cartShopFareInfo.fareType = jDJSONObject.optInt("fareType");
        cartShopFareInfo.priceNeed = jDJSONObject.optString(CartConstant.KEY_VENDOR_PRICENEED);
        cartShopFareInfo.weightNeed = jDJSONObject.optString(CartConstant.KEY_VENDOR_WEIGHTNEED);
        cartShopFareInfo.pieceOrder = PieceOrder.ParseJson(jDJSONObject.optJSONObject(CartConstant.KEY_CART_COUDANPAGEMSG));
        return cartShopFareInfo;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.fareType);
        parcel.writeParcelable(this.pieceOrder, i2);
        parcel.writeString(this.priceNeed);
        parcel.writeString(this.weightNeed);
    }

    protected CartShopFareInfo(Parcel parcel) {
        this.fareType = parcel.readInt();
        this.pieceOrder = (PieceOrder) parcel.readParcelable(PieceOrder.class.getClassLoader());
        this.priceNeed = parcel.readString();
        this.weightNeed = parcel.readString();
    }
}
