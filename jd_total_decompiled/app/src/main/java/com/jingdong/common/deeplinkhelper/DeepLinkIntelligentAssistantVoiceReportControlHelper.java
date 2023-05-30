package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkIntelligentAssistantVoiceReportControlHelper {
    private DeepLinkIntelligentAssistantVoiceReportControlHelper() {
    }

    public static void startIntelligentAssistantVoiceReportControlActivity(IMyActivity iMyActivity, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect((Context) iMyActivity, new DeepLinkUri.Builder().scheme("jingdong").host("intelligent_assistant_voice_report_control").toString(), bundle);
    }
}
