package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.database.table.CommentEditTable;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes3.dex */
public class SuitPromotion {
    public long promoId;

    public SuitPromotion(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        jDJSONObject.optInt(CommentEditTable.TB_COLUMN_SCORE);
        this.promoId = jDJSONObject.optLong("promoId");
        jDJSONObject.optInt("promoType");
        jDJSONObject.optString(CartConstant.KEY_CART_TEXTINFO_REPRICE);
        jDJSONObject.optString("manfanMoney");
        jDJSONObject.optString("discount");
    }
}
