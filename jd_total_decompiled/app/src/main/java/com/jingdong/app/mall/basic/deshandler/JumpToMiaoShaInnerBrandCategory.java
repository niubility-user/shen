package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_INNER_BRAND_CATEGORY)
/* loaded from: classes19.dex */
public class JumpToMiaoShaInnerBrandCategory extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("id");
        if (bundle != null && !TextUtils.isEmpty(string)) {
            bundle.putLong(MiaoShaPublicConstants.KEY_BRAND_ID, Long.parseLong(string));
        }
        DeepLinkMiaoShaHelper.startMiaoShaBrandCategoryInner(context, bundle);
        c(context);
    }
}
