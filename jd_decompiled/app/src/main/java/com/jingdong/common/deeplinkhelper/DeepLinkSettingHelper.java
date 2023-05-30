package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkSettingHelper {
    public static void startAboutActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_ABOUT_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startFeedbackActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_FEEDBACK_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startNewFeedBackActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_NEW_FEEDBACK_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startPlatformFeedBackActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_PLATFORM_FEEDBACK_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startAboutActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startPrivacyPolicyPage(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_PRIVACY_POLICY_PAGE).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper secsettingactivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startSetTextScaleModePage(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_SET_TEXT_SCALE_MODE_PAGE).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper secsettingactivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }

    public static void startSettingActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(39))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_MORESETTING_ACTIVITY).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkSettingHelper startSettingActivity", "\u8bbe\u7f6e\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
        }
    }
}
