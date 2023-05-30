package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_ASSISTANT_CHAT_BACKGROUND_SETTING)
/* loaded from: classes19.dex */
public class JumpToAssistant_chat_background_setting extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkIntelligentAssistantHelper.startForResultChatBackgroundSettingActivity((IMyActivity) context, bundle, 0);
        c(context);
    }
}
