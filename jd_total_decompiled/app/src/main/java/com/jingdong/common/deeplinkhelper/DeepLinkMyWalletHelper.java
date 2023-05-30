package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkMyWalletHelper {
    private static final String TAG = "DeepLinkMyWalletHelper";

    public static void startMobikeMView(final Context context, final Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper.4
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if (DeepLinkCommonHelper.HOST_JD_TO_MOBIKE.equals(str)) {
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JD_TO_MOBIKE).toString(), bundle);
                }
            }
        }, DeepLinkCommonHelper.HOST_JD_TO_MOBIKE);
    }

    public static void startMyWalletActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (IMyActivity.this == null) {
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_MY_WALLET_ACTIVITY).toString(), bundle);
            }
        });
    }

    public static void startMyWalletActivity2(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_MY_WALLET_ACTIVITY).toString(), bundle);
    }

    public static void startPlatformWalletActivity(final Context context, final Bundle bundle) {
        try {
            final String builder = new DeepLinkUri.Builder().scheme("jingdong").host("myPlatformWallet").toString();
            DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper.2
                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str) {
                    if (TextUtils.equals(str, builder)) {
                        if (OKLog.D) {
                            OKLog.d(DeepLinkMyWalletHelper.TAG, "startPlatformWalletActivity onSuccess host: " + builder);
                        }
                        DeepLinkDispatch.startActivityDirect(context, builder, bundle);
                    }
                }
            }, builder);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "startPlatformWalletActivity error: " + e2);
            }
        }
    }

    public static void startPlatformWalletNewActivity(final Context context, final Bundle bundle) {
        try {
            final String builder = new DeepLinkUri.Builder().scheme("jingdong").host("platformwalletnew").toString();
            DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper.3
                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str) {
                    if (TextUtils.equals(str, builder)) {
                        if (OKLog.D) {
                            OKLog.d(DeepLinkMyWalletHelper.TAG, "startPlatformWalletActivity onSuccess host: " + builder);
                        }
                        DeepLinkDispatch.startActivityDirect(context, builder, bundle);
                    }
                }
            }, builder);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "startPlatformWalletActivity error: " + e2);
            }
        }
    }
}
