package com.jingdong.app.mall.j;

import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.union.common.config.UnionConstants;

/* loaded from: classes4.dex */
public class d {
    public static void a(BaseActivity baseActivity, Bundle bundle) {
        if (Log.D) {
            Log.d("Temp", "startImActivity-->> ");
        }
        DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
        if (generateWithPin != null) {
            generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_PRODUCT_ASK).addSkuID(bundle.getString(UnionConstants.BUNDLE_SKUID)).addProductName(bundle.getString("product_name")).addProductPrice(bundle.getString("product_jd_price")).addProductImageUrl(bundle.getString("product_image_url"));
            DeeplinkDongDongHelper.getInstance().startDongDong(baseActivity, generateWithPin.getBundle());
        }
        if (Log.D) {
            Log.d("Temp", "startImActivity-->> startIMActivity");
        }
    }
}
