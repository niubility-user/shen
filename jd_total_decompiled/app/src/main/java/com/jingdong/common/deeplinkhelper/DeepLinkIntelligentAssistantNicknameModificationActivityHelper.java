package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkIntelligentAssistantNicknameModificationActivityHelper {
    public static void startForResultNicknameModificationActivity(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult((Activity) context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_INTELLIGENT_ASSISTANT_NICKNAME_MODIFICATION).toString(), bundle, i2);
    }

    public static void startNicknameModificationActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_INTELLIGENT_ASSISTANT_NICKNAME_MODIFICATION).toString(), bundle);
    }
}
