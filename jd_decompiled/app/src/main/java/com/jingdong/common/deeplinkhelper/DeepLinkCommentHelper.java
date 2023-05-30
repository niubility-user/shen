package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DeepLinkCommentHelper {
    public static final int EVALUATE_CENTER = 21;
    public static final int SHARE_ORDER = 83;
    public static final String HOST_COMMENT_REPORT = "evaluateReport";
    public static final String HOST_COMMENT_EDITOR = "commentEditor";
    public static final String[] EvaluateHosts = {HOST_COMMENT_REPORT, HOST_COMMENT_EDITOR};
    public static final String[] ShareOrderHosts = new String[0];

    private static int getBundleIdFromHost(String str) {
        if (Arrays.asList(EvaluateHosts).contains(str)) {
            return 21;
        }
        return Arrays.asList(ShareOrderHosts).contains(str) ? 83 : -1;
    }

    private static boolean isSwitchOpen(int i2) {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(i2));
    }

    public static boolean startCommentPage(Context context, Bundle bundle, String str) {
        if (isSwitchOpen(getBundleIdFromHost(str))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle);
            return true;
        }
        return false;
    }

    public static boolean startCommentPageForResult(Activity activity, Bundle bundle, String str, int i2) {
        if (isSwitchOpen(getBundleIdFromHost(str))) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle, i2);
            return true;
        }
        return false;
    }
}
