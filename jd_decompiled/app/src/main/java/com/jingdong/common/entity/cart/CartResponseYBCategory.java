package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseYBCategory implements Parcelable {
    public static final Parcelable.Creator<CartResponseYBCategory> CREATOR = new Parcelable.Creator<CartResponseYBCategory>() { // from class: com.jingdong.common.entity.cart.CartResponseYBCategory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBCategory createFromParcel(Parcel parcel) {
            return new CartResponseYBCategory(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBCategory[] newArray(int i2) {
            return new CartResponseYBCategory[i2];
        }
    };
    public String describe;
    public Integer sort;
    public String virtualSkuName;
    public String virtualSkuType;
    private ArrayList<CartResponseYBBrand> ybBrands;

    public CartResponseYBCategory(JSONObject jSONObject) {
        this.virtualSkuName = jSONObject.optString(CartConstant.KEY_YB_VIRTUAL_TYPE_NAME);
        this.virtualSkuType = jSONObject.optString(CartConstant.KEY_YB_VIRTUAL_SKU_TYPE);
        this.sort = Integer.valueOf(jSONObject.optInt(CartConstant.KEY_YB_SORT));
        this.describe = jSONObject.optString(CartConstant.KEY_YB_DESCRIBE);
        JSONArray optJSONArray = jSONObject.optJSONArray(CartConstant.KEY_YB_TRADEMARK_VO);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        this.ybBrands = new ArrayList<>();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                this.ybBrands.add(new CartResponseYBBrand(optJSONObject));
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDescribe() {
        String str = this.describe;
        return str == null ? "" : str;
    }

    public Integer getSort() {
        Integer num = this.sort;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getVirtualSkuName() {
        String str = this.virtualSkuName;
        return str == null ? "" : str;
    }

    public String getVirtualSkuType() {
        String str = this.virtualSkuType;
        return str == null ? "" : str;
    }

    public ArrayList<CartResponseYBBrand> getYbDetails() {
        return this.ybBrands;
    }

    public void setYbDetails(ArrayList<CartResponseYBBrand> arrayList) {
        this.ybBrands = arrayList;
    }

    public String toString() {
        return "CartResponseYBCategory [sort=" + this.sort + ", virtualSkuType=" + this.virtualSkuType + ", virtualSkuName=" + this.virtualSkuName + ", describe=" + this.describe + ", ybBrands=" + this.ybBrands + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.virtualSkuType);
        parcel.writeString(this.virtualSkuName);
        parcel.writeString(this.describe);
        parcel.writeTypedList(this.ybBrands);
    }

    protected CartResponseYBCategory(Parcel parcel) {
        this.virtualSkuType = parcel.readString();
        this.virtualSkuName = parcel.readString();
        this.describe = parcel.readString();
        this.ybBrands = parcel.createTypedArrayList(CartResponseYBBrand.CREATOR);
    }
}
