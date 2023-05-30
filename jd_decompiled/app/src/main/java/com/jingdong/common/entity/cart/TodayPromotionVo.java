package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class TodayPromotionVo {
    public String bottomBgColor;
    public String bottomFontColor;
    public String bottomTagEnd;
    public String bottomTagStart;
    public HashMap<String, String> bubblesShow;
    public String bubblesText;
    public String couDan;
    public String couDanDeg;
    public String entryLabel;
    public boolean isShowShoppingBag;
    public String preferentialTag;
    public String promotionDes;
    public long promotionId;
    public boolean promotionShowTips;
    public String promotionTitle;
    public String topText;

    public static TodayPromotionVo parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return null;
        }
        TodayPromotionVo todayPromotionVo = new TodayPromotionVo();
        todayPromotionVo.isShowShoppingBag = jDJSONObject.optBoolean("isShowShoppingBag", false);
        todayPromotionVo.topText = jDJSONObject.optString("topText");
        todayPromotionVo.bottomTagStart = jDJSONObject.optString("bottomTagStart");
        todayPromotionVo.bottomTagEnd = jDJSONObject.optString("bottomTagEnd");
        todayPromotionVo.promotionShowTips = jDJSONObject.optBoolean("promotionShowTips", false);
        todayPromotionVo.bottomBgColor = jDJSONObject.optString("bottomBgColor");
        todayPromotionVo.bottomFontColor = jDJSONObject.optString("bottomFontColor");
        todayPromotionVo.promotionDes = jDJSONObject.optString("promotionDes");
        todayPromotionVo.couDan = jDJSONObject.optString("couDan");
        todayPromotionVo.couDanDeg = jDJSONObject.optString("couDanDeg");
        todayPromotionVo.entryLabel = jDJSONObject.optString(CartConstant.KEY_SUIT_ENTRYLABEL);
        todayPromotionVo.bubblesText = jDJSONObject.optString("bubblesText");
        todayPromotionVo.promotionId = jDJSONObject.optLong("promotionId");
        todayPromotionVo.promotionTitle = jDJSONObject.optString("promotionTitle");
        todayPromotionVo.preferentialTag = jDJSONObject.optString("preferentialTag");
        todayPromotionVo.bubblesShow = CartABCards.parseJsonToMap(jDJSONObject.optJSONObject("bubblesShow"));
        return todayPromotionVo;
    }
}
