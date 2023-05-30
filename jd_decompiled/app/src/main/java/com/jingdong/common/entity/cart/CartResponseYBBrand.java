package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseYBBrand implements Parcelable {
    public static final Parcelable.Creator<CartResponseYBBrand> CREATOR = new Parcelable.Creator<CartResponseYBBrand>() { // from class: com.jingdong.common.entity.cart.CartResponseYBBrand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBBrand createFromParcel(Parcel parcel) {
            return new CartResponseYBBrand(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBBrand[] newArray(int i2) {
            return new CartResponseYBBrand[i2];
        }
    };
    public String brandId;
    public String brandLinks;
    public String brandName;
    public Integer sort;
    private ArrayList<CartResponseYBDetail> ybDetails;

    public CartResponseYBBrand(JSONObject jSONObject) {
        this.brandId = jSONObject.optString(CartConstant.KEY_YB_BRAND_ID);
        this.brandLinks = jSONObject.optString(CartConstant.KEY_YB_BRAND_LINKS);
        this.brandName = jSONObject.optString(CartConstant.KEY_YB_BRAND_NAME);
        this.sort = Integer.valueOf(jSONObject.optInt(CartConstant.KEY_YB_SORT));
        JSONArray optJSONArray = jSONObject.optJSONArray(CartConstant.KEY_YB_CONFIG_VOS);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        this.ybDetails = new ArrayList<>();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                this.ybDetails.add(new CartResponseYBDetail(optJSONObject));
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBrandId() {
        String str = this.brandId;
        return str == null ? "" : str;
    }

    public String getBrandLinks() {
        String str = this.brandLinks;
        return str == null ? "" : str;
    }

    public String getBrandName() {
        String str = this.brandName;
        return str == null ? "" : str;
    }

    public Integer getSort() {
        Integer num = this.sort;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public ArrayList<CartResponseYBDetail> getYbDetails() {
        return this.ybDetails;
    }

    public void setYbDetails(ArrayList<CartResponseYBDetail> arrayList) {
        this.ybDetails = arrayList;
    }

    public String toString() {
        return "CartResponseYBBrand [brandId=" + this.brandId + ", sort=" + this.sort + ", brandName=" + this.brandName + ", brandLinks=" + this.brandLinks + ", ybDetails=" + this.ybDetails + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.brandId);
        parcel.writeString(this.brandName);
        parcel.writeString(this.brandLinks);
    }

    protected CartResponseYBBrand(Parcel parcel) {
        this.brandId = parcel.readString();
        this.brandName = parcel.readString();
        this.brandLinks = parcel.readString();
    }
}
