package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_BANNER)
/* loaded from: classes19.dex */
public class JumpToMiaosha_banner extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("id");
        if (bundle != null) {
            try {
                if (!TextUtils.isEmpty(string)) {
                    bundle.putString("activityId", string);
                }
            } catch (Exception unused) {
                DeepLinkMiaoShaHelper.startMiaoShaBanner(context, bundle);
            }
        }
        DeepLinkMiaoShaHelper.startMiaoShaBanner(context, bundle);
        c(context);
    }
}
