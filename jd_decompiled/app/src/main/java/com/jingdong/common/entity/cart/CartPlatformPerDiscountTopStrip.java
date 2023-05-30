package com.jingdong.common.entity.cart;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartPlatformPerDiscountTopStrip {
    public HashMap<String, String> bubblesShow;
    public boolean checked;
    public long endTime;
    public long promotionId;
    public String promotionTitle;
    public String skuCount;
    public long startTime;
    public String topBeforeCountdownText;
    public long topBeforeCountdownTime;
    public String topBeforeText;
    public String topEmptyText;
    public String topNowStaticText;
    public String topNowText;
    public long topNowTime;
    public String topText;
    public int state = -1;
    public int type = 0;

    public static CartPlatformPerDiscountTopStrip parseJson(JDJSONObject jDJSONObject) {
        if (OKLog.D) {
            OKLog.d("CartPlatformPerDiscountTopStrip", " parseJson--->jsonObject=" + jDJSONObject);
        }
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return null;
        }
        CartPlatformPerDiscountTopStrip cartPlatformPerDiscountTopStrip = new CartPlatformPerDiscountTopStrip();
        cartPlatformPerDiscountTopStrip.state = jDJSONObject.optInt(XView2Constants.STATE, -1);
        cartPlatformPerDiscountTopStrip.startTime = jDJSONObject.optLong("startTime", 0L);
        cartPlatformPerDiscountTopStrip.topBeforeText = jDJSONObject.optString("topBeforeText");
        cartPlatformPerDiscountTopStrip.topBeforeCountdownText = jDJSONObject.optString("topBeforeCountdownText");
        cartPlatformPerDiscountTopStrip.topBeforeCountdownTime = jDJSONObject.optLong("topBeforeCountdownTime", 0L);
        cartPlatformPerDiscountTopStrip.endTime = jDJSONObject.optLong("endTime", 0L);
        cartPlatformPerDiscountTopStrip.topNowTime = jDJSONObject.optLong("topNowTime", 0L);
        cartPlatformPerDiscountTopStrip.topNowText = jDJSONObject.optString("topNowText");
        cartPlatformPerDiscountTopStrip.checked = jDJSONObject.optBoolean("checked");
        cartPlatformPerDiscountTopStrip.topNowStaticText = jDJSONObject.optString("topNowStaticText");
        cartPlatformPerDiscountTopStrip.topEmptyText = jDJSONObject.optString("topEmptyText");
        cartPlatformPerDiscountTopStrip.promotionId = jDJSONObject.optLong("promotionId");
        cartPlatformPerDiscountTopStrip.promotionTitle = jDJSONObject.optString("promotionTitle");
        cartPlatformPerDiscountTopStrip.skuCount = jDJSONObject.optString("skuCount");
        HashMap<String, String> parseJsonToMap = CartABCards.parseJsonToMap(jDJSONObject.optJSONObject("bubblesShow"));
        cartPlatformPerDiscountTopStrip.bubblesShow = parseJsonToMap;
        if (parseJsonToMap != null) {
            long j2 = cartPlatformPerDiscountTopStrip.startTime;
            if (j2 > 0 && cartPlatformPerDiscountTopStrip.endTime > 0) {
                parseJsonToMap.put("startTime", String.valueOf(j2));
                cartPlatformPerDiscountTopStrip.bubblesShow.put("endTime", String.valueOf(cartPlatformPerDiscountTopStrip.endTime));
            }
        }
        return cartPlatformPerDiscountTopStrip;
    }

    public static ArrayList<CartPlatformPerDiscountTopStrip> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartPlatformPerDiscountTopStrip> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                arrayList.add(parseJson(jSONObject));
            }
        }
        return arrayList;
    }

    @NonNull
    public String toString() {
        return "CartPlatformPerDiscountTopStrip{startTime=" + this.startTime + ", endTime=" + this.endTime + ", state=" + this.state + ", topBeforeText='" + this.topBeforeText + "', topNowText='" + this.topNowText + "', topBeforeCountdownTime=" + this.topBeforeCountdownTime + "', topBeforeCountdownText='" + this.topBeforeCountdownText + "', checked='" + this.checked + "', topNowStaticText='" + this.topNowStaticText + "', topNowTime='" + this.topNowTime + "', promotionId='" + this.promotionId + "', promotionTitle='" + this.promotionTitle + "', topEmptyText='" + this.topEmptyText + "', skuCount='" + this.skuCount + "'}";
    }
}
