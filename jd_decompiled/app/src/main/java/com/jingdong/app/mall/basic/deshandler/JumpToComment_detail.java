package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_COMMENT_DETAIL)
/* loaded from: classes19.dex */
public class JumpToComment_detail extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null && !TextUtils.isEmpty(bundle.getString("secondReplyId"))) {
            DeepLinkEvaluateCenterHelper.startCommentSecondReplyActivity(context, bundle);
        } else {
            DeepLinkEvaluateCenterHelper.startEvaluateDetail(context, bundle);
        }
        c(context);
    }
}
