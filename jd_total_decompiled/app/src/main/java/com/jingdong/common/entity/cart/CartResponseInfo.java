package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartResponseInfo implements Parcelable {
    public static final Parcelable.Creator<CartResponseInfo> CREATOR = new Parcelable.Creator<CartResponseInfo>() { // from class: com.jingdong.common.entity.cart.CartResponseInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseInfo createFromParcel(Parcel parcel) {
            return new CartResponseInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseInfo[] newArray(int i2) {
            return new CartResponseInfo[i2];
        }
    };
    private static final String TAG = "CartResponseInfo";
    public HashMap<String, String> abCards;
    public int cartAddClearGuide;
    public int cartClearGuide;
    public boolean cartClearShow;
    public String cartClearShowImg;
    public String cartExpansion220;
    public int cartNum;
    public int num;
    public boolean plusCartShow;
    public boolean subFlow;
    public String ybPackId;

    public CartResponseInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAbResult(String str) {
        return (this.abCards == null || TextUtils.isEmpty(str) || this.abCards.get(str) == null) ? "A" : this.abCards.get(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }

    public CartResponseInfo(JDJSONObject jDJSONObject) {
        this.subFlow = jDJSONObject.optBoolean(CartConstant.KEY_SUBFLOW, false);
        this.num = jDJSONObject.optInt(CartConstant.KEY_NUM_BIG);
        this.cartNum = jDJSONObject.optInt(CartConstant.KEY_CART_NUM);
        this.cartClearShow = jDJSONObject.optBoolean(CartConstant.KEY_CART_CARTCLEARSHOW);
        this.plusCartShow = jDJSONObject.optBoolean(CartConstant.KEY_CART_PLUSCARTSHOW, false);
        this.cartClearGuide = jDJSONObject.optInt(CartConstant.KEY_CARTCLEARGUIDE, 0);
        this.cartAddClearGuide = jDJSONObject.optInt(CartConstant.KEY_CARTADDCLEARGUIDE, 0);
        this.cartClearShowImg = jDJSONObject.optString(CartConstant.KEY_CARTCLEARSHOWIMG, "");
        this.cartExpansion220 = jDJSONObject.optString("cartExpansion220", "");
        this.abCards = CartABCards.parseJson(jDJSONObject.optJSONObject(CartConstant.KEY_SKU_ABCARDS));
        this.ybPackId = jDJSONObject.optString(CartConstant.KEY_YB_PACK_ID);
    }

    protected CartResponseInfo(Parcel parcel) {
    }
}
