package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartResponseSoldOffShop implements Parcelable {
    public static final Parcelable.Creator<CartResponseSoldOffShop> CREATOR = new Parcelable.Creator<CartResponseSoldOffShop>() { // from class: com.jingdong.common.entity.cart.CartResponseSoldOffShop.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseSoldOffShop createFromParcel(Parcel parcel) {
            return new CartResponseSoldOffShop(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseSoldOffShop[] newArray(int i2) {
            return new CartResponseSoldOffShop[i2];
        }
    };
    public ArrayList<? super CartSummary> cartSummary;
    public boolean isEditChecked;

    public CartResponseSoldOffShop() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.cartSummary);
    }

    public CartResponseSoldOffShop(JDJSONArray jDJSONArray, String str) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return;
        }
        int size = jDJSONArray.size();
        ArrayList<? super CartSummary> arrayList = new ArrayList<>(size);
        boolean z = true;
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                CartResponseSoldOffSku cartResponseSoldOffSku = new CartResponseSoldOffSku(optJSONObject, str);
                if (z && !cartResponseSoldOffSku.isEditChecked) {
                    z = false;
                }
                arrayList.add(cartResponseSoldOffSku);
            }
        }
        this.isEditChecked = z;
        this.cartSummary = arrayList;
    }

    protected CartResponseSoldOffShop(Parcel parcel) {
        ArrayList<? super CartSummary> arrayList = new ArrayList<>();
        this.cartSummary = arrayList;
        parcel.readList(arrayList, CartSummary.class.getClassLoader());
    }
}
