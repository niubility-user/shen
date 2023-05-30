package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MORE_GAME_INTERACTION_ACTIVITY)
/* loaded from: classes19.dex */
public class JumpToMoreGameInteraction extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startMoreGameInteractionActivity(context, bundle);
        c(context);
    }
}
