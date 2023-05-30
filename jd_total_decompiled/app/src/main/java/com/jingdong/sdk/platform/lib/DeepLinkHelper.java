package com.jingdong.sdk.platform.lib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;

/* loaded from: classes10.dex */
public class DeepLinkHelper {
    private static final String SCHEME = "jingdong";

    public static boolean isSwitchOpen(String str) {
        return OpenApiHelper.getDeepLinkConfig().isSwitchOpen(str);
    }

    public static void startActivity(Context context, String str, Bundle bundle, boolean z, int i2, boolean z2, String str2) {
        if (context != null) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str);
            a d = b.a().d(host.toString());
            if (d == null) {
                return;
            }
            String b = d.b();
            if (!TextUtils.isEmpty(b) && isSwitchOpen(b)) {
                DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("context parameter is null");
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle) {
        startActivity(context, str, bundle, true, 0, false, "");
    }

    public static void startActivityDirectNeedLogin(IMyActivity iMyActivity, String str, Bundle bundle, String str2) {
        startActivity(iMyActivity.getThisActivity(), str, bundle, true, 0, true, str2);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2) {
        startActivityForResult(activity, str, bundle, i2, 0);
    }

    public static void startActivityForResultNeedLogin(IMyActivity iMyActivity, String str, Bundle bundle, int i2, String str2) {
        startActivityForResult(iMyActivity.getThisActivity(), str, bundle, i2, 0);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2, int i3) {
        if (activity != null) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str);
            a d = b.a().d(host.toString());
            if (d == null) {
                return;
            }
            String b = d.b();
            if (!TextUtils.isEmpty(b) && isSwitchOpen(b)) {
                DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("activity parameter is null");
    }
}
