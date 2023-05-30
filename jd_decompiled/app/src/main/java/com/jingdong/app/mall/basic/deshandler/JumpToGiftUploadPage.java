package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_GIFT_SHARE_PAGE)
/* loaded from: classes19.dex */
public class JumpToGiftUploadPage extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        DeepLinkCommonHelper.startActivityDirect(context, "giftshare", bundle);
        c(context);
    }
}
