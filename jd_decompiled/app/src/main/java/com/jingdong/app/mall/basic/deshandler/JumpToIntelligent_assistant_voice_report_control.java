package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantLoginGuideHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantVoiceReportControlHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;

@Des(des = "intelligent_assistant_voice_report_control")
/* loaded from: classes19.dex */
public class JumpToIntelligent_assistant_voice_report_control extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (LoginUserBase.hasLogin()) {
            DeepLinkIntelligentAssistantVoiceReportControlHelper.startIntelligentAssistantVoiceReportControlActivity((IMyActivity) context, bundle);
        } else {
            DeepLinkIntelligentAssistantLoginGuideHelper.startIntelligentAssistantLoginGuideActivity((IMyActivity) context, bundle);
        }
        c(context);
    }
}
