package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.constant.CartConstant;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_LIANGFAN)
/* loaded from: classes19.dex */
public class JumpToMiaosha_liangfan extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString(CartConstant.KEY_SKU_BRANDID);
        if (bundle != null) {
            try {
                bundle.putInt(CartConstant.KEY_SKU_BRANDID, Integer.parseInt(string));
            } catch (Exception unused) {
                DeepLinkMiaoShaHelper.startMiaoShaLiangfanInner(context, bundle);
            }
        }
        DeepLinkMiaoShaHelper.startMiaoShaLiangfanInner(context, bundle);
        c(context);
    }
}
