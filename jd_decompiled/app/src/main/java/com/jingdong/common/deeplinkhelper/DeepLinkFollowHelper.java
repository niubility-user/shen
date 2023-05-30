package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkFollowHelper {
    public static final String HOST_FOLLOW = "follow";
    public static final String HOST_FOLLOW_CHANNEL = "followchannel";
    public static final String HOST_FOLLOW_TOPIC = "followtopic";
    public static final String HOST_MY_FOLLOW = "myfollow";

    public static void startFollowActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(47))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("follow").toString(), bundle);
        }
    }

    public static void startFollowChannelActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(47))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_FOLLOW_CHANNEL).toString(), bundle);
        }
    }

    public static void startFollowTopicActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(47))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_FOLLOW_TOPIC).toString(), bundle);
        }
    }

    public static void startMyFollowActivity(final Context context, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkFollowHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(47))) {
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("myfollow").toString(), bundle);
                }
            }
        });
    }
}
