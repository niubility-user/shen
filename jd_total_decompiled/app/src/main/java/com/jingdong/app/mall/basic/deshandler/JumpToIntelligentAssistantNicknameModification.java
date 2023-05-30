package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantNicknameModificationActivityHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_INTELLIGENT_ASSISTANT_NICKNAME_MODIFICATION)
/* loaded from: classes19.dex */
public class JumpToIntelligentAssistantNicknameModification extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkIntelligentAssistantNicknameModificationActivityHelper.startForResultNicknameModificationActivity(context, bundle, 0);
        c(context);
    }
}
