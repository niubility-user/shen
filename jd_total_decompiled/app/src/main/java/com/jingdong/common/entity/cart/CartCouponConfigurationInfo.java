package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartCouponConfigurationInfo implements Parcelable {
    public static final Parcelable.Creator<CartCouponConfigurationInfo> CREATOR = new Parcelable.Creator<CartCouponConfigurationInfo>() { // from class: com.jingdong.common.entity.cart.CartCouponConfigurationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponConfigurationInfo createFromParcel(Parcel parcel) {
            return new CartCouponConfigurationInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponConfigurationInfo[] newArray(int i2) {
            return new CartCouponConfigurationInfo[i2];
        }
    };
    public String couDanTextColor;
    public String couponDescUrl;
    public String couponFaceCornerMarkUrl;
    public String couponFaceUrl;
    public ArrayList<String> couponTypeCornerMarkColor;
    public String titleImageUrl;

    public CartCouponConfigurationInfo() {
    }

    public static CartCouponConfigurationInfo parseCartCouponConfigurationInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartCouponConfigurationInfo cartCouponConfigurationInfo = new CartCouponConfigurationInfo();
        cartCouponConfigurationInfo.couponFaceUrl = jDJSONObject.optString("couponFaceUrl");
        cartCouponConfigurationInfo.couponDescUrl = jDJSONObject.optString("couponDescUrl");
        cartCouponConfigurationInfo.couponFaceCornerMarkUrl = jDJSONObject.optString("couponFaceCornerMarkUrl");
        cartCouponConfigurationInfo.titleImageUrl = jDJSONObject.optString("titleImageUrl");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("couponTypeCornerMarkColor");
        if (optJSONArray != null && optJSONArray.size() > 0) {
            cartCouponConfigurationInfo.couponTypeCornerMarkColor = new ArrayList<>();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (optString != null) {
                    cartCouponConfigurationInfo.couponTypeCornerMarkColor.add(optString);
                }
            }
        }
        cartCouponConfigurationInfo.couDanTextColor = jDJSONObject.optString("couDanTextColor");
        return cartCouponConfigurationInfo;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.couponFaceUrl);
        parcel.writeString(this.couponDescUrl);
        parcel.writeString(this.couponFaceCornerMarkUrl);
        parcel.writeString(this.titleImageUrl);
        parcel.writeStringList(this.couponTypeCornerMarkColor);
        parcel.writeString(this.couDanTextColor);
    }

    protected CartCouponConfigurationInfo(Parcel parcel) {
        this.couponFaceUrl = parcel.readString();
        this.couponDescUrl = parcel.readString();
        this.couponFaceCornerMarkUrl = parcel.readString();
        this.titleImageUrl = parcel.readString();
        this.couponTypeCornerMarkColor = parcel.createStringArrayList();
        this.couDanTextColor = parcel.readString();
    }
}
