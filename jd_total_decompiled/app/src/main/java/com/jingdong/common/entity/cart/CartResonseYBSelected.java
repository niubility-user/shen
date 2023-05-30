package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CartResonseYBSelected implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CartResonseYBSelected> CREATOR = new Parcelable.Creator<CartResonseYBSelected>() { // from class: com.jingdong.common.entity.cart.CartResonseYBSelected.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResonseYBSelected createFromParcel(Parcel parcel) {
            return new CartResonseYBSelected(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResonseYBSelected[] newArray(int i2) {
            return new CartResonseYBSelected[i2];
        }
    };
    private static final String TAG = "CartResonseYBSelected";
    private String rSuitId;
    private String rWid;
    private CartResponseSku ybSku;

    public CartResonseYBSelected(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject != null) {
            setrWid(jDJSONObject.optString(CartConstant.KEY_YB_R_WID));
            setrSuitId(jDJSONObject.optString(CartConstant.KEY_YB_R_SUIT_ID));
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject(CartConstant.KEY_YB_MAIN_SKU);
            if (optJSONObject != null) {
                setYbSku(new CartResponseSku(optJSONObject, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object clone() {
        CartResonseYBSelected cartResonseYBSelected;
        CloneNotSupportedException e2;
        try {
            cartResonseYBSelected = (CartResonseYBSelected) super.clone();
            if (cartResonseYBSelected != null) {
                try {
                    CartResponseSku cartResponseSku = this.ybSku;
                    if (cartResponseSku != null) {
                        cartResonseYBSelected.ybSku = (CartResponseSku) cartResponseSku.clone();
                    }
                } catch (CloneNotSupportedException e3) {
                    e2 = e3;
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                    }
                    return cartResonseYBSelected;
                }
            }
        } catch (CloneNotSupportedException e4) {
            cartResonseYBSelected = null;
            e2 = e4;
        }
        return cartResonseYBSelected;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getYbId() {
        CartResponseSku cartResponseSku = this.ybSku;
        return cartResponseSku != null ? cartResponseSku.getSkuId() : "";
    }

    public Integer getYbNum() {
        CartResponseSku cartResponseSku = this.ybSku;
        if (cartResponseSku != null) {
            return Integer.valueOf(cartResponseSku.getNum());
        }
        return 0;
    }

    public CartResponseSku getYbSku() {
        return this.ybSku;
    }

    public String getrSuitId() {
        String str = this.rSuitId;
        return str == null ? "" : str;
    }

    public String getrWid() {
        String str = this.rWid;
        return str == null ? "" : str;
    }

    public void setYbSku(CartResponseSku cartResponseSku) {
        this.ybSku = cartResponseSku;
    }

    public void setrSuitId(String str) {
        this.rSuitId = str;
    }

    public void setrWid(String str) {
        this.rWid = str;
    }

    public String toOrderParamsString() {
        if (!TextUtils.isEmpty(getrSuitId())) {
            return "4_" + getrSuitId() + CartConstant.KEY_YB_INFO_LINK + getrWid() + CartConstant.KEY_YB_INFO_LINK + getYbId();
        }
        return "1_" + getrWid() + CartConstant.KEY_YB_INFO_LINK + getYbId();
    }

    public String toString() {
        return "CartResonseYBSelected [rWid=" + this.rWid + ", rSuitId=" + this.rSuitId + ", ybSku=" + this.ybSku + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.rWid);
        parcel.writeString(this.rSuitId);
        parcel.writeParcelable(this.ybSku, i2);
    }

    public CartResonseYBSelected() {
    }

    protected CartResonseYBSelected(Parcel parcel) {
        this.rWid = parcel.readString();
        this.rSuitId = parcel.readString();
        this.ybSku = (CartResponseSku) parcel.readParcelable(CartResponseSku.class.getClassLoader());
    }
}
