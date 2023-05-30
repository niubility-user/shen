package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResonseYB implements Parcelable {
    public static final Parcelable.Creator<CartResonseYB> CREATOR = new Parcelable.Creator<CartResonseYB>() { // from class: com.jingdong.common.entity.cart.CartResonseYB.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResonseYB createFromParcel(Parcel parcel) {
            return new CartResonseYB(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResonseYB[] newArray(int i2) {
            return new CartResonseYB[i2];
        }
    };
    private ArrayList<CartResponseYBCategory> categories;
    private String skuId;

    public CartResonseYB(JSONObject jSONObject) {
        setSkuId(jSONObject.optString(CartConstant.KEY_YB_SKU_ID));
        JSONArray optJSONArray = jSONObject.optJSONArray(CartConstant.KEY_YB_TONG_VOS);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        int length = optJSONArray.length();
        this.categories = new ArrayList<>(length);
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                this.categories.add(new CartResponseYBCategory(optJSONObject));
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<CartResponseYBCategory> getCategories() {
        return this.categories;
    }

    public String getSkuId() {
        String str = this.skuId;
        return str == null ? "" : str;
    }

    public void setCategories(ArrayList<CartResponseYBCategory> arrayList) {
        this.categories = arrayList;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public String toString() {
        return "CartResonseYB [skuId=" + this.skuId + ", categories=" + this.categories + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.skuId);
    }

    protected CartResonseYB(Parcel parcel) {
        this.skuId = parcel.readString();
    }
}
