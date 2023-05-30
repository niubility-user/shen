package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartCouponSkuItem implements Parcelable {
    public static final Parcelable.Creator<CartCouponSkuItem> CREATOR = new Parcelable.Creator<CartCouponSkuItem>() { // from class: com.jingdong.common.entity.cart.CartCouponSkuItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponSkuItem createFromParcel(Parcel parcel) {
            return new CartCouponSkuItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponSkuItem[] newArray(int i2) {
            return new CartCouponSkuItem[i2];
        }
    };
    public String id;
    public String p;
    public String s;
    public String skuUuid;
    public int type;
    public boolean useUuid;

    public CartCouponSkuItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.s);
        parcel.writeString(this.p);
        parcel.writeString(this.id);
        parcel.writeInt(this.type);
        parcel.writeString(this.skuUuid);
        parcel.writeValue(Boolean.valueOf(this.useUuid));
    }

    public CartCouponSkuItem(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.s = jDJSONObject.optString("s");
        this.p = jDJSONObject.optString("p");
        this.id = jDJSONObject.optString("id");
        this.type = jDJSONObject.optInt("type");
        this.skuUuid = jDJSONObject.optString("skuUuid", "");
        this.useUuid = jDJSONObject.optBoolean("useUuid");
    }

    protected CartCouponSkuItem(Parcel parcel) {
        this.s = parcel.readString();
        this.p = parcel.readString();
        this.id = parcel.readString();
        this.type = parcel.readInt();
        this.skuUuid = parcel.readString();
        this.useUuid = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
    }
}
