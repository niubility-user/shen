package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseYBDetail implements Parcelable {
    public static final Parcelable.Creator<CartResponseYBDetail> CREATOR = new Parcelable.Creator<CartResponseYBDetail>() { // from class: com.jingdong.common.entity.cart.CartResponseYBDetail.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBDetail createFromParcel(Parcel parcel) {
            return new CartResponseYBDetail(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseYBDetail[] newArray(int i2) {
            return new CartResponseYBDetail[i2];
        }
    };
    public String cid;
    public String id;
    public String message;
    public String name;
    public String priceCent;
    public String priceShow;
    public String rSuitId;
    public String rWid;
    public String serviceYear;
    public String skuName;

    public CartResponseYBDetail(JSONObject jSONObject) {
        this.serviceYear = jSONObject.optString(CartConstant.KEY_YB_SERVICE_YEAR);
        this.cid = jSONObject.optString(CartConstant.KEY_YB_CID);
        this.id = jSONObject.optString("Id");
        this.name = jSONObject.optString("Name");
        this.priceShow = jSONObject.optString("PriceShow");
        this.priceCent = jSONObject.optString(CartConstant.KEY_YB_PRICE_CENT);
        this.rWid = jSONObject.optString(CartConstant.KEY_YB_R_WID);
        this.rSuitId = jSONObject.optString(CartConstant.KEY_YB_R_SUIT_ID);
        this.skuName = jSONObject.optString(CartConstant.KEY_YB_SKU_NAME);
        this.message = jSONObject.optString(CartConstant.KEY_YB_MESSAGE);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCid() {
        String str = this.cid;
        return str == null ? "" : str;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getPriceCent() {
        String str = this.priceCent;
        return str == null ? "" : str;
    }

    public String getPriceShow() {
        String str = this.priceShow;
        return str == null ? "" : str;
    }

    public String getServiceYear() {
        String str = this.serviceYear;
        return str == null ? "" : str;
    }

    public String getSkuName() {
        String str = this.skuName;
        return str == null ? "" : str;
    }

    public String getrSuitId() {
        String str = this.rSuitId;
        return str == null ? "" : str;
    }

    public String getrWid() {
        String str = this.rWid;
        return str == null ? "" : str;
    }

    public String toString() {
        return "CartResponseYBDetail [serviceYear=" + this.serviceYear + ", name=" + this.name + ", id=" + this.id + ", priceShow=" + this.priceShow + ", cid=" + this.cid + ", priceCent=" + this.priceCent + ", rSuitId=" + this.rSuitId + ", rWid=" + this.rWid + ", skuName=" + this.skuName + ", message=" + this.message + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.serviceYear);
        parcel.writeString(this.name);
        parcel.writeString(this.id);
        parcel.writeString(this.priceShow);
        parcel.writeString(this.cid);
        parcel.writeString(this.priceCent);
        parcel.writeString(this.rSuitId);
        parcel.writeString(this.rWid);
        parcel.writeString(this.skuName);
        parcel.writeString(this.message);
    }

    protected CartResponseYBDetail(Parcel parcel) {
        this.serviceYear = parcel.readString();
        this.name = parcel.readString();
        this.id = parcel.readString();
        this.priceShow = parcel.readString();
        this.cid = parcel.readString();
        this.priceCent = parcel.readString();
        this.rSuitId = parcel.readString();
        this.rWid = parcel.readString();
        this.skuName = parcel.readString();
        this.message = parcel.readString();
    }
}
