package com.jingdong.common.utils;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ILogin;

/* loaded from: classes6.dex */
public class JumpMessageActivityUtil {
    private static final String MESSAGE_CENTER_KEY = "messagecenterkey";

    public static void jumpToMessageCenter(final Context context) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.utils.JumpMessageActivityUtil.1
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if (JumpMessageActivityUtil.MESSAGE_CENTER_KEY.equals(str)) {
                    DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MESSAGE_CENTER_MAIN_ACTIVITY_NEW, null);
                }
            }
        }, MESSAGE_CENTER_KEY);
    }

    public static void jumpToSecond(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MESSAGE_CENTER_SECOND_ACTIVITY, bundle);
    }

    public static void jumpToSetting(Context context) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MESSAGE_CENTER_SETTING_ACTIVITY, null);
    }

    public static void jumpToShield(Context context) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MESSAGE_CENTER_SHIELD_ACTIVITY, null);
    }

    public static void productDetailjumpToMessageCenter(Context context) {
        jumpToMessageCenter(context);
    }
}
