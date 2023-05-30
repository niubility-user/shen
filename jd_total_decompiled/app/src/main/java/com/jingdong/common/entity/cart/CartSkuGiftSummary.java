package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartSkuGiftSummary extends CartSkuSummary implements Parcelable {
    public static final Parcelable.Creator<CartSkuGiftSummary> CREATOR = new Parcelable.Creator<CartSkuGiftSummary>() { // from class: com.jingdong.common.entity.cart.CartSkuGiftSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSkuGiftSummary createFromParcel(Parcel parcel) {
            return new CartSkuGiftSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSkuGiftSummary[] newArray(int i2) {
            return new CartSkuGiftSummary[i2];
        }
    };
    public String giftId;
    public Long jBeanPromotionId;
    public Long promotionId;

    public CartSkuGiftSummary(String str, String str2, Integer num) {
        super(str, num.intValue());
        this.giftId = str2;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary
    public JSONObject toSummaryParams() throws JSONException {
        JSONObject summaryParams = super.toSummaryParams();
        summaryParams.put("giftId", this.giftId);
        summaryParams.put("activePromotionId", this.promotionId);
        summaryParams.put("skuPromotionId", this.jBeanPromotionId);
        return summaryParams;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.giftId);
        parcel.writeValue(this.promotionId);
        parcel.writeValue(this.jBeanPromotionId);
    }

    public CartSkuGiftSummary(String str, ArrayList<String> arrayList, Integer num) {
        super(str, arrayList, num);
    }

    public CartSkuGiftSummary(String str, Integer num) {
        super(str, num.intValue());
    }

    public CartSkuGiftSummary(String str, Integer num, String str2, String str3) {
        super(str, num.intValue(), str2, str3);
    }

    public CartSkuGiftSummary(String str, Integer num, String str2, String str3, boolean z) {
        super(str, num.intValue(), str2, str3, z);
    }

    protected CartSkuGiftSummary(Parcel parcel) {
        super(parcel);
        this.giftId = parcel.readString();
        this.promotionId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.jBeanPromotionId = (Long) parcel.readValue(Long.class.getClassLoader());
    }
}
