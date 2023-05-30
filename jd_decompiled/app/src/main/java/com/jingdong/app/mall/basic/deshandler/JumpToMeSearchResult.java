package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMemeHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_MEME_DISCSEARCH_RESULT)
/* loaded from: classes19.dex */
public class JumpToMeSearchResult extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMemeHelper.startSearchResultActivity(context, bundle);
        c(context);
    }
}
