package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkHourlyGoHelper implements PDConstant {
    private static boolean isSwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(114));
    }

    public static void startHourlyGoDetailPage(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_HOURLY_GO).toString(), bundle);
        } else if (OKLog.D) {
            OKLog.d("DeepLinkHourlyGoHelper", "bundle-hourlygo switch is close ");
        }
    }
}
