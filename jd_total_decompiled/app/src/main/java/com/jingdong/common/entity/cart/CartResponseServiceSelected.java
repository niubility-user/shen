package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CartResponseServiceSelected implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CartResponseServiceSelected> CREATOR = new Parcelable.Creator<CartResponseServiceSelected>() { // from class: com.jingdong.common.entity.cart.CartResponseServiceSelected.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseServiceSelected createFromParcel(Parcel parcel) {
            return new CartResponseServiceSelected(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseServiceSelected[] newArray(int i2) {
            return new CartResponseServiceSelected[i2];
        }
    };
    private static final String TAG = "CartResponseServiceSelected";
    public String rSuitId;
    public String rWid;
    public CartResponseSku serviceSku;

    public CartResponseServiceSelected(JDJSONObject jDJSONObject, String str, String str2, String str3) {
        if (jDJSONObject != null) {
            this.serviceSku = new CartResponseSku(jDJSONObject, str);
            this.rWid = str2;
            this.rSuitId = str3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object clone() {
        CartResponseServiceSelected cartResponseServiceSelected;
        CloneNotSupportedException e2;
        try {
            cartResponseServiceSelected = (CartResponseServiceSelected) super.clone();
            if (cartResponseServiceSelected != null) {
                try {
                    CartResponseSku cartResponseSku = this.serviceSku;
                    if (cartResponseSku != null) {
                        cartResponseServiceSelected.serviceSku = (CartResponseSku) cartResponseSku.clone();
                    }
                } catch (CloneNotSupportedException e3) {
                    e2 = e3;
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                    }
                    return cartResponseServiceSelected;
                }
            }
        } catch (CloneNotSupportedException e4) {
            cartResponseServiceSelected = null;
            e2 = e4;
        }
        return cartResponseServiceSelected;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getServiceSkuId() {
        CartResponseSku cartResponseSku = this.serviceSku;
        return cartResponseSku != null ? cartResponseSku.skuId : "";
    }

    public Integer getServiceSkuNum() {
        CartResponseSku cartResponseSku = this.serviceSku;
        if (cartResponseSku != null) {
            return Integer.valueOf(cartResponseSku.getNum());
        }
        return 0;
    }

    public String toOrderParamsString() {
        if (!TextUtils.isEmpty(this.rSuitId)) {
            return "4_" + this.rSuitId + CartConstant.KEY_YB_INFO_LINK + this.rWid + CartConstant.KEY_YB_INFO_LINK + getServiceSkuId();
        }
        return "1_" + this.rWid + CartConstant.KEY_YB_INFO_LINK + getServiceSkuId();
    }

    public String toString() {
        return "CartResponseServiceSelected [rWid=" + this.rWid + ", rSuitId=" + this.rSuitId + ", serviceSku=" + this.serviceSku + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.rWid);
        parcel.writeString(this.rSuitId);
        parcel.writeParcelable(this.serviceSku, i2);
    }

    public CartResponseServiceSelected() {
    }

    protected CartResponseServiceSelected(Parcel parcel) {
        this.rWid = parcel.readString();
        this.rSuitId = parcel.readString();
        this.serviceSku = (CartResponseSku) parcel.readParcelable(CartResponseSku.class.getClassLoader());
    }
}
