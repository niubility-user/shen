package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantChatTimbreHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantLoginGuideHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;

@Des(des = "intelligent_assistant_chat_timbre")
/* loaded from: classes19.dex */
public class JumpToIntelligent_assistant_chat_timbre extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (LoginUserBase.hasLogin()) {
            DeepLinkIntelligentAssistantChatTimbreHelper.startChatTimbreActivity((IMyActivity) context, bundle);
        } else {
            DeepLinkIntelligentAssistantLoginGuideHelper.startIntelligentAssistantLoginGuideActivity((IMyActivity) context, bundle);
        }
        c(context);
    }
}
