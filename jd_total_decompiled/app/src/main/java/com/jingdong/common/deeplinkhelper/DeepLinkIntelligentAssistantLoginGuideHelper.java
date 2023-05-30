package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkIntelligentAssistantLoginGuideHelper {
    private DeepLinkIntelligentAssistantLoginGuideHelper() {
    }

    public static void startIntelligentAssistantLoginGuideActivity(IMyActivity iMyActivity, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect((Context) iMyActivity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_INTELLIGENT_ASSISTANT_LOGIN_GUIDE_PAGE).toString(), bundle);
    }
}
