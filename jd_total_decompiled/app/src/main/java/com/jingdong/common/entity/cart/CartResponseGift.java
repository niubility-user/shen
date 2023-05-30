package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartResponseGift extends CartResponseSku implements Parcelable {
    public static final Parcelable.Creator<CartResponseGift> CREATOR = new Parcelable.Creator<CartResponseGift>() { // from class: com.jingdong.common.entity.cart.CartResponseGift.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseGift createFromParcel(Parcel parcel) {
            return new CartResponseGift(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseGift[] newArray(int i2) {
            return new CartResponseGift[i2];
        }
    };
    private static final String TAG = "CartResponseGift";
    protected ArrayList<CartResponseSku> gifts;

    public CartResponseGift(JDJSONObject jDJSONObject, String str) {
        super(jDJSONObject, str);
        this.gifts = new ArrayList<>();
    }

    public static ArrayList<CartResponseSku> toList4Gift(JDJSONArray jDJSONArray, String str) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartResponseSku> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new CartResponseSku(optJSONObject, str));
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.common.entity.cart.CartResponseSku, com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<CartResponseSku> getGifts() {
        return this.gifts;
    }

    @Override // com.jingdong.common.entity.cart.CartResponseSku, com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeTypedList(this.gifts);
    }

    public CartResponseGift(String str, Integer num) {
        super(str, num);
        this.gifts = new ArrayList<>();
    }

    protected CartResponseGift(Parcel parcel) {
        super(parcel);
        this.gifts = new ArrayList<>();
        this.gifts = parcel.createTypedArrayList(CartResponseSku.CREATOR);
    }
}
