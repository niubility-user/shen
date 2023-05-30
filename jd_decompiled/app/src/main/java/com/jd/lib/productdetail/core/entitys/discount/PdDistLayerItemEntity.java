package com.jd.lib.productdetail.core.entitys.discount;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.discount.PdDiscountLayerEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessExpression;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdDistLayerItemEntity {
    public static final int DISCOUNT_VIEW_TYPE_BOTTOMTIPS = 1008;
    public static final int DISCOUNT_VIEW_TYPE_BRIEFPREFERENCE = 1006;
    public static final int DISCOUNT_VIEW_TYPE_BRIEFPREFERENCE_DYN = 1007;
    public static final int DISCOUNT_VIEW_TYPE_DETAILPREFERENCE = 1002;
    public static final int DISCOUNT_VIEW_TYPE_DETAILPREFERENCE_DYN = 1004;
    public static final int DISCOUNT_VIEW_TYPE_DETAILPREFERENCE_PRO = 1003;
    public static final int DISCOUNT_VIEW_TYPE_EMPTY = 1009;
    public static final int DISCOUNT_VIEW_TYPE_HANDPRICE = 1001;
    public static final int DISCOUNT_VIEW_TYPE_MORETITLE = 1005;
    public PdDiscountLayerEntity.BottomTipsEntity bottomTips;
    private JSONObject couponDynObject;
    public JDJSONObject couponObject;
    public WareBusinessExpression expression;
    public boolean isFirstbriefPromotion;
    public boolean isLastbriefPromotion;
    public String moreTitle;
    public PdDiscountLayerEntity.DetailPreferenceEntity preferenceEntity;
    public int viewType;
    public boolean isDyn = false;
    public int index = -1;

    public JSONObject getCouponDynObject() {
        if (this.couponDynObject == null) {
            try {
                JSONObject jSONObject = new JSONObject(JDJSON.toJSONString(this.couponObject));
                this.couponDynObject = jSONObject;
                jSONObject.put("paddingLeft", 0);
                this.couponDynObject.put("paddingRight", 0);
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.couponDynObject;
    }
}
