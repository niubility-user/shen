package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartStock implements Parcelable {
    public static final Parcelable.Creator<CartStock> CREATOR = new Parcelable.Creator<CartStock>() { // from class: com.jingdong.common.entity.cart.CartStock.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartStock createFromParcel(Parcel parcel) {
            return new CartStock(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartStock[] newArray(int i2) {
            return new CartStock[i2];
        }
    };
    public String isSupport;
    public String remainNum;
    public String skuId;
    public String stockState;
    public String storeId;

    public CartStock(JSONObjectProxy jSONObjectProxy) {
        this.skuId = jSONObjectProxy.getStringOrNull("skuId");
        this.stockState = jSONObjectProxy.getStringOrNull(CartConstant.KEY_SKU_STOCKSTATE);
        this.remainNum = jSONObjectProxy.getStringOrNull(CartConstant.KEY_SKU_REMAINNUM);
        this.isSupport = jSONObjectProxy.getStringOrNull("isSupport");
        this.storeId = jSONObjectProxy.getStringOrNull("storeId");
    }

    public static ArrayList<CartStock> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() == 0) {
            return null;
        }
        int length = jSONArrayPoxy.length();
        ArrayList<CartStock> arrayList = new ArrayList<>(length);
        for (int i2 = 0; i2 < length; i2++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
            if (jSONObjectOrNull != null) {
                CartStock cartStock = new CartStock(jSONObjectOrNull);
                if (!TextUtils.isEmpty(cartStock.skuId)) {
                    arrayList.add(cartStock);
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.skuId);
        parcel.writeString(this.stockState);
        parcel.writeString(this.remainNum);
        parcel.writeString(this.isSupport);
        parcel.writeString(this.storeId);
    }

    protected CartStock(Parcel parcel) {
        this.skuId = parcel.readString();
        this.stockState = parcel.readString();
        this.remainNum = parcel.readString();
        this.isSupport = parcel.readString();
        this.storeId = parcel.readString();
    }
}
