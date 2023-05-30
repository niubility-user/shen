package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CartResponseThreeCcSelected implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CartResponseThreeCcSelected> CREATOR = new Parcelable.Creator<CartResponseThreeCcSelected>() { // from class: com.jingdong.common.entity.cart.CartResponseThreeCcSelected.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseThreeCcSelected createFromParcel(Parcel parcel) {
            return new CartResponseThreeCcSelected(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseThreeCcSelected[] newArray(int i2) {
            return new CartResponseThreeCcSelected[i2];
        }
    };
    private static final String TAG = "CartResponseThreeCcSelected";
    public String rSuitId;
    public String rWid;
    public CartResponseSku threeCcSku;

    public CartResponseThreeCcSelected(JDJSONObject jDJSONObject, String str, String str2, String str3) {
        if (jDJSONObject != null) {
            this.threeCcSku = new CartResponseSku(jDJSONObject, str);
            this.rWid = str2;
            this.rSuitId = str3;
        }
    }

    protected Object clone() {
        CartResponseThreeCcSelected cartResponseThreeCcSelected;
        CloneNotSupportedException e2;
        try {
            cartResponseThreeCcSelected = (CartResponseThreeCcSelected) super.clone();
            if (cartResponseThreeCcSelected != null) {
                try {
                    CartResponseSku cartResponseSku = this.threeCcSku;
                    if (cartResponseSku != null) {
                        cartResponseThreeCcSelected.threeCcSku = (CartResponseSku) cartResponseSku.clone();
                    }
                } catch (CloneNotSupportedException e3) {
                    e2 = e3;
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                    }
                    return cartResponseThreeCcSelected;
                }
            }
        } catch (CloneNotSupportedException e4) {
            cartResponseThreeCcSelected = null;
            e2 = e4;
        }
        return cartResponseThreeCcSelected;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getThreeCcSkuId() {
        CartResponseSku cartResponseSku = this.threeCcSku;
        return cartResponseSku != null ? cartResponseSku.skuId : "";
    }

    public Integer getThreeCcSkuNum() {
        CartResponseSku cartResponseSku = this.threeCcSku;
        if (cartResponseSku != null) {
            return Integer.valueOf(cartResponseSku.getNum());
        }
        return 0;
    }

    public String toOrderParamsString() {
        if (!TextUtils.isEmpty(this.rSuitId)) {
            return "4_" + this.rSuitId + CartConstant.KEY_YB_INFO_LINK + this.rWid + CartConstant.KEY_YB_INFO_LINK + getThreeCcSkuId();
        }
        return "1_" + this.rWid + CartConstant.KEY_YB_INFO_LINK + getThreeCcSkuId();
    }

    public String toString() {
        return "CartResponseThreeCcSelected [rWid=" + this.rWid + ", rSuitId=" + this.rSuitId + ", threeCcSku=" + this.threeCcSku + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.rWid);
        parcel.writeString(this.rSuitId);
        parcel.writeParcelable(this.threeCcSku, i2);
    }

    public CartResponseThreeCcSelected() {
    }

    protected CartResponseThreeCcSelected(Parcel parcel) {
        this.rWid = parcel.readString();
        this.rSuitId = parcel.readString();
        this.threeCcSku = (CartResponseSku) parcel.readParcelable(CartResponseSku.class.getClassLoader());
    }
}
