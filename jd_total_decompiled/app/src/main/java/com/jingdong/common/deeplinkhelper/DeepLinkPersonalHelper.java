package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkPersonalHelper {
    public static final String HOST_JOY_WELFARE_ACTIVITY = "joywelfareactivity";
    public static final String HOST_PERSONAL_CARD_LIST_ACTIVITY = "personalcardlistactivity";
    public static final String HOST_PERSONAL_CHANNEl_ACTIVITY = "personalchannelactivity";
    public static final String HOST_PERSONAL_MORE_CHANNEl_ACTIVITY = "personalmorechannelactivity";
    public static final String HOST_PERSONAL_SEARCH_CHANNEl_ACTIVITY = "personalchannelsearchactivity";
    public static final String HOST_PERSONAL_TOOLS_ACTIVITY = "personaltoolsactivity";

    public static void startJoyWelfareActivity(Context context, Bundle bundle) {
        startPersonalActivity(context, bundle, HOST_JOY_WELFARE_ACTIVITY);
    }

    private static void startPersonalActivity(Context context, Bundle bundle, String str) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(58))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkPersonalHelper", "\u63d2\u4ef6\u5f00\u5173\u5173\u95ed\u4e86");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle);
    }

    public static void startPersonalCardListActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_PERSONAL_CARD_LIST_ACTIVITY).toString(), bundle);
    }

    public static void startPersonalChannelActivity(Context context, Bundle bundle) {
        startPersonalActivity(context, bundle, HOST_PERSONAL_CHANNEl_ACTIVITY);
    }

    public static void startPersonalMoreChannelActivity(Context context, Bundle bundle) {
        startPersonalActivity(context, bundle, HOST_PERSONAL_MORE_CHANNEl_ACTIVITY);
    }

    public static void startPersonalSearchChannelActivity(Context context, Bundle bundle) {
        startPersonalActivity(context, bundle, HOST_PERSONAL_SEARCH_CHANNEl_ACTIVITY);
    }

    public static void startPersonalToolsActivity(Context context, Bundle bundle) {
        startPersonalActivity(context, bundle, HOST_PERSONAL_TOOLS_ACTIVITY);
    }
}
