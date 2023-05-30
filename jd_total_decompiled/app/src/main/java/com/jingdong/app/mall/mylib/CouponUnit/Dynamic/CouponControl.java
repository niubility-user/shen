package com.jingdong.app.mall.mylib.CouponUnit.Dynamic;

import android.view.ViewParent;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CouponControl extends CommFunction {
    public static final String ONBTNCLICK = "onBtnClick";
    public static final String ONCOUPONCLICK = "onCouponClick";
    public static final String ONCOUPONLONGCLICK = "onCouponLongClick";
    public static final String ONSHARECLICK = "onShareClick";
    public static final String ONSHOPCLICK = "onShopClick";

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mEngine = dynamicTemplateEngine;
            String optString = jSONObject.optString("fun");
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            for (ViewParent rootContainer = dynamicTemplateEngine.getRootContainer(); rootContainer != null; rootContainer = rootContainer.getParent()) {
                if (rootContainer instanceof CommonCouponView) {
                    ((CommonCouponView) rootContainer).couponClickListener(optString, optJSONObject);
                    return null;
                }
            }
        }
        return null;
    }
}
