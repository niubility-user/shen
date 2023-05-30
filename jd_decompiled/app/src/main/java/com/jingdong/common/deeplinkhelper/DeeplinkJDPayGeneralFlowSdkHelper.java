package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeeplinkJDPayGeneralFlowSdkHelper {
    private static String HOST_JDPAY_ACTIVITY = "jdpaygeneralflowactivity";

    public static void startJDPayActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(67))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDPAY_ACTIVITY).toString(), bundle);
        }
    }

    public static void startJDPayActivityForResult(Activity activity, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_JDPAY_ACTIVITY).toString(), bundle, i2);
    }
}
