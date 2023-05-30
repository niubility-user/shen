package com.jingdong.common.entity.cart;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes5.dex */
public class CartPlatformPerDiscount {
    public boolean allShowTips;
    public String bottomBgColor;
    public String bottomFontColor;
    public String bottomTagEnd;
    public String bottomTagStart;
    public String couDan;
    public String couDanDeg;
    public String entryLabel;
    public boolean isShowShoppingBag;
    public String jumpUrl;
    public String preferentialTag;
    public String promotionDes;
    public long promotionId;
    public boolean promotionShowTips;
    public String promotionTitle;
    public String stillNeed;

    public CartPlatformPerDiscount(@NonNull JDJSONObject jDJSONObject) {
        this.bottomTagStart = jDJSONObject.optString("bottomTagStart");
        this.bottomTagEnd = jDJSONObject.optString("bottomTagEnd");
        this.allShowTips = jDJSONObject.optBoolean("allShowTips", false);
        this.promotionShowTips = jDJSONObject.optBoolean("promotionShowTips", false);
        this.bottomBgColor = jDJSONObject.optString("bottomBgColor");
        this.bottomFontColor = jDJSONObject.optString("bottomFontColor");
        this.promotionDes = jDJSONObject.optString("promotionDes");
        this.couDan = jDJSONObject.optString("couDan");
        this.couDanDeg = jDJSONObject.optString("couDanDeg");
        this.entryLabel = jDJSONObject.optString(CartConstant.KEY_SUIT_ENTRYLABEL);
        this.jumpUrl = jDJSONObject.optString(CartConstant.KEY_JUMPURL);
        this.promotionTitle = jDJSONObject.optString("promotionTitle");
        this.promotionId = jDJSONObject.optLong("promotionId");
        this.isShowShoppingBag = jDJSONObject.optBoolean("isShowShoppingBag", false);
        this.stillNeed = jDJSONObject.optString(CartConstant.KEY_CART_STILL_NEED);
        this.preferentialTag = jDJSONObject.optString("preferentialTag");
    }

    public static CartPlatformPerDiscount parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        try {
            return new CartPlatformPerDiscount(jDJSONObject);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(th);
            return null;
        }
    }

    @NonNull
    public String toString() {
        return "CartPlatformPerDiscount{bottomTagStart='" + this.bottomTagStart + "', bottomTagEnd='" + this.bottomTagEnd + "', allShowTips='" + this.allShowTips + "', promotionShowTips='" + this.promotionShowTips + "', bottomBgColor='" + this.bottomBgColor + "', bottomFontColor='" + this.bottomFontColor + "', promotionDes='" + this.promotionDes + "', couDan='" + this.couDan + "', couDanDeg='" + this.couDanDeg + "', entryLabel='" + this.entryLabel + "', jumpUrl='" + this.jumpUrl + "', promotionTitle='" + this.promotionTitle + "', promotionId='" + this.promotionId + "', isShowShoppingBag='" + this.isShowShoppingBag + "', stillNeed='" + this.stillNeed + "'}";
    }
}
