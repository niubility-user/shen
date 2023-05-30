package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DeepLinkMemberCodeHelper {
    public static final String HOST_MEMBER_CODE_HOME = "MemberCodeHome";
    public static final String[] MemberCodeHosts = {HOST_MEMBER_CODE_HOME};

    private static int getBundleIdFromHost(String str) {
        return Arrays.asList(MemberCodeHosts).contains(str) ? 13 : -1;
    }

    private static boolean isSwitchOpen(int i2) {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(i2));
    }

    public static boolean startActivity(Context context, Bundle bundle, String str) {
        if (isSwitchOpen(getBundleIdFromHost(str))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle);
            return true;
        }
        return false;
    }

    public static boolean startActivityForResult(Activity activity, Bundle bundle, String str, int i2) {
        if (isSwitchOpen(getBundleIdFromHost(str))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle, i2);
            return true;
        }
        return false;
    }
}
