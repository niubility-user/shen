package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkUserManagerHelper {
    public static final String HOST_USER_MANAGER = "usersecondactivity";

    public static void startCardListActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(34))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_USER_MANAGER_ID_CARD_LIST_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startInnovationLabActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(34))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("innovationLab").toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startUserSecondActivity(IMyActivity iMyActivity, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(34))) {
            DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host("usersecondactivity").toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkUserManagerHelper", "\u8d26\u6237\u7ba1\u7406\u4e8c\u7ea7\u9875\u5173\u95ed\u4e86");
        }
    }
}
