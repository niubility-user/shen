package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkJDGameHelper {
    public static void startJDGameActivity(Context context, Bundle bundle) {
        OKLog.d("jjjjj", "@@@@@@startJDGameActivity");
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JDGAME_ACTIVITY).toString(), bundle);
    }
}
