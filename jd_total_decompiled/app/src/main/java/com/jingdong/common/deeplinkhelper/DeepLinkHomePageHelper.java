package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkHomePageHelper {
    public static final String HOST_PAGE_HOME = "personalpagehomeactivity";

    public static void startHomePageActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(34))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PAGE_HOME).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkHomePageHelper", "\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startHomePageActivityForResult(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(34))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PAGE_HOME).toString(), bundle, i2);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkHomePageHelper", "\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }
}
