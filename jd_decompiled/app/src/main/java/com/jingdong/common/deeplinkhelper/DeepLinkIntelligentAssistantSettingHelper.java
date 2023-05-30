package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkIntelligentAssistantSettingHelper {
    private DeepLinkIntelligentAssistantSettingHelper() {
    }

    public static void startForResultIntelligentAssistantSettingActivity(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult((Activity) context, new DeepLinkUri.Builder().scheme("jingdong").host("intelligent_assistant_setting").toString(), bundle, i2);
    }

    public static void startIntelligentAssistantSettingActivity(IMyActivity iMyActivity, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect((Context) iMyActivity, new DeepLinkUri.Builder().scheme("jingdong").host("intelligent_assistant_setting").toString(), bundle);
    }
}
