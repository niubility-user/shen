package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartPackGiftSummary extends CartPackSummary implements Parcelable {
    public static final Parcelable.Creator<CartPackGiftSummary> CREATOR = new Parcelable.Creator<CartPackGiftSummary>() { // from class: com.jingdong.common.entity.cart.CartPackGiftSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPackGiftSummary createFromParcel(Parcel parcel) {
            return new CartPackGiftSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPackGiftSummary[] newArray(int i2) {
            return new CartPackGiftSummary[i2];
        }
    };
    private ArrayList<CartSkuGiftSummary> skusMustGifts;

    public CartPackGiftSummary(String str, Integer num, ArrayList<CartSkuGiftSummary> arrayList, String str2) {
        super(str, num, str2);
        this.skusMustGifts = new ArrayList<>();
        this.skusMustGifts = arrayList;
    }

    @Override // com.jingdong.common.entity.cart.CartPackSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.entity.cart.CartPackSummary
    public JSONObject toSummaryParams() throws JSONException {
        ArrayList<CartSkuGiftSummary> arrayList;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Id", getPackId());
        if (!TextUtils.isEmpty(getYbPackId())) {
            jSONObject.put(CartConstant.KEY_YB_PACK_ID, getYbPackId());
        }
        jSONObject.put("num", getNum());
        if (!TextUtils.isEmpty(this.skuUuid)) {
            jSONObject.put("skuUuid", this.skuUuid);
        }
        if (!TextUtils.isEmpty(this.storeId)) {
            jSONObject.put("storeId", this.storeId);
        }
        boolean z = this.useUuid;
        if (z) {
            jSONObject.put("useUuid", z);
        }
        if (!"".equals(getsType())) {
            if (TextUtils.equals(getsType(), "16") && (arrayList = this.skusMustGifts) != null && arrayList.size() > 0) {
                jSONObject.put("sType", "13");
            } else {
                jSONObject.put("sType", getsType());
            }
        }
        JSONArray jSONArray = null;
        ArrayList<? super CartSkuSummary> arrayList2 = this.gifts;
        if (arrayList2 != null && arrayList2.size() > 0) {
            jSONArray = new JSONArray();
            Iterator<? super CartSkuSummary> it = this.gifts.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toSummaryParams());
            }
        }
        ArrayList<CartSkuGiftSummary> arrayList3 = this.skusMustGifts;
        if (arrayList3 != null && arrayList3.size() > 0) {
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            Iterator<CartSkuGiftSummary> it2 = this.skusMustGifts.iterator();
            while (it2.hasNext()) {
                jSONArray.put(it2.next().toSummaryParams());
            }
        }
        if (jSONArray != null) {
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.cart.CartPackSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeTypedList(this.skusMustGifts);
    }

    protected CartPackGiftSummary(Parcel parcel) {
        super(parcel);
        this.skusMustGifts = new ArrayList<>();
        this.skusMustGifts = parcel.createTypedArrayList(CartSkuGiftSummary.CREATOR);
    }
}
