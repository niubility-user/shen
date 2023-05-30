package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkFriendHelper {
    public static void showShareFriendList(Context context, Bundle bundle) {
        startFriendActivity(context, bundle, "showsharefriendlist");
    }

    private static void startFriendActivity(Context context, Bundle bundle, String str) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(100))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkFriendHelper startFriendListActivity", "\u4eac\u4e1c\u597d\u53cb\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle);
    }

    public static void startFriendListActivity(Context context, Bundle bundle) {
        startFriendActivity(context, bundle, DeepLinkCommonHelper.HOST_FRIEND_LIST);
    }

    public static void startFriendManagerActivity(Context context, Bundle bundle) {
        startFriendActivity(context, bundle, DeepLinkCommonHelper.HOST_FRIEND_MANAGER);
    }
}
