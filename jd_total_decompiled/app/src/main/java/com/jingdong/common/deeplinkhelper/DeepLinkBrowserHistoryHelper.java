package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkBrowserHistoryHelper {
    public static final String HOST_BROWSERHISTORY = "browserhistory";

    public static void startBrowserHistoryActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(24))) {
                    DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkBrowserHistoryHelper.HOST_BROWSERHISTORY).toString(), bundle);
                } else if (OKLog.D) {
                    OKLog.d("DeepLinkFavouritesHelper", "\u6d4f\u89c8\u8bb0\u5f55\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
                }
            }
        });
    }

    public static void startBrowserHistoryActivity2(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(24))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_BROWSERHISTORY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkFavouritesHelper", "\u6d4f\u89c8\u8bb0\u5f55\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }
}
