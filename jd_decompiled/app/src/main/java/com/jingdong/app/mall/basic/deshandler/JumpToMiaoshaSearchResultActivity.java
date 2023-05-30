package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_SEARCH_RESULT)
/* loaded from: classes19.dex */
public class JumpToMiaoshaSearchResultActivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMiaoShaHelper.startMiaoShaSearchResult(context, bundle);
        c(context);
    }
}