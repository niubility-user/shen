package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.constant.CartConstant;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_BRAND)
/* loaded from: classes19.dex */
public class JumpToMiaosha_brand extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString(CartConstant.KEY_SKU_BRANDID);
        if (bundle != null) {
            try {
                if (!TextUtils.isEmpty(string)) {
                    bundle.putLong(MiaoShaPublicConstants.KEY_BRAND_ID, Long.parseLong(string));
                }
            } catch (Exception unused) {
                DeepLinkMiaoShaHelper.startMiaoShaBrandInner(context, bundle);
            }
        }
        String string2 = bundle.getString("type");
        if (TextUtils.isEmpty(string2)) {
            string2 = "0";
        }
        if ("0".equals(string2)) {
            DeepLinkMiaoShaHelper.startMiaoShaBrandInner(context, bundle);
        } else if ("1".equals(string2)) {
            DeepLinkMiaoShaHelper.startMiaoShaBrandCategoryInner(context, bundle);
        }
        c(context);
    }
}
