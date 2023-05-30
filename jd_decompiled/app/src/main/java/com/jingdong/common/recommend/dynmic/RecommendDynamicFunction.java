package com.jingdong.common.recommend.dynmic;

import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.common.recommend.RecommendExecCallback;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendDynamicFunction extends CommFunction {
    private RecommendUtil.OnRecommendClickedListener clickedListener;
    public RecommendExecCallback execCallback;
    public int from;
    private int position;
    private RecommendItem recommendItem;

    public RecommendDynamicFunction(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, int i2) {
        this.clickedListener = onRecommendClickedListener;
        this.from = i2;
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        if (jSONObject != null && dynamicTemplateEngine != null) {
            RecommendExecCallback recommendExecCallback = this.execCallback;
            if (recommendExecCallback != null) {
                recommendExecCallback.exec(dynamicTemplateEngine, jSONObject);
            }
            if (this.recommendItem != null && this.clickedListener != null) {
                String optString = jSONObject.optString("fun");
                if (TextUtils.equals("onClickShoppingCartButton", optString)) {
                    int i2 = this.from;
                    this.clickedListener.onAddCarClick(this.recommendItem.product, i2 != 6 ? i2 != 10 ? null : RecommendMtaUtils.OrderCenterMyPurchase_FloorAddToCart : RecommendMtaUtils.Shopcart_Compare_AddtoCart);
                } else if (TextUtils.equals("onClickSimilarButton", optString)) {
                    this.clickedListener.onSimilarClick(this.recommendItem.product);
                } else if (TextUtils.equals("adClickMta", optString)) {
                    String optString2 = jSONObject.optString("client_click_url");
                    if (!TextUtils.isEmpty(optString2)) {
                        this.clickedListener.onRecommendMoneyExpo(optString2);
                    }
                }
            }
        }
        return null;
    }

    public void setExecCallback(RecommendExecCallback recommendExecCallback) {
        this.execCallback = recommendExecCallback;
    }

    public void setPositionAndItem(RecommendItem recommendItem) {
        this.recommendItem = recommendItem;
    }
}
