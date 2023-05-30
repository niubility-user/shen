package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkJDCustomChannelHelper {
    public static final String HOST_JDALLYARDACTIVITY = "jdallyardactivity";
    public static final String HOST_JDBRANDACTIVITY = "jdbrandactivity";
    public static final String HOST_JDBUYPACK2ACTIVITY = "jdbuypack2activity";
    public static final String HOST_JDCUSTOMCHANNEL_MAINACTIVITY = "jdfamilyactivity";

    public static void startJDAllYardActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(82))) {
            if (Log.D) {
                Log.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDALLYARDACTIVITY).toString(), bundle);
    }

    public static void startJDBrandActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(82))) {
            if (Log.D) {
                Log.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDBRANDACTIVITY).toString(), bundle);
    }

    public static void startJDBuyPack2Activity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(82))) {
            if (Log.D) {
                Log.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDBUYPACK2ACTIVITY).toString(), bundle);
    }

    public static void startJDCustomChannel(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(82))) {
            if (Log.D) {
                Log.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDCUSTOMCHANNEL_MAINACTIVITY).toString(), bundle);
    }
}
