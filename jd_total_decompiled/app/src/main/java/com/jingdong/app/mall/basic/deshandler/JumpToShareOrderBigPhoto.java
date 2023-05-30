package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkShareOrderHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_SHARE_ORDER_COMMENT_BIG_PHOTO)
/* loaded from: classes19.dex */
public class JumpToShareOrderBigPhoto extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkShareOrderHelper.startCommentPhotoActivity(context, bundle);
        c(context);
    }
}
