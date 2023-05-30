package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.R;
import com.jingdong.common.login.ILogin;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeeplinkFaceloginLivenessHelper {
    private static final String HOST_FACELOGIN = "livenessDetectNew";
    private static final String HOST_FACELOGIN_SPECIAL = "faceLoginSpecial";
    private static final String HOST_LIVENESS = "faceLoginSwitchNewV2";
    private static final String HOST_LIVENESSSTART = "livenessStartNewV2";
    private static final String TAG = "DeeplinkFaceloginLivenessHelper";

    public static boolean isAuraSuccess() {
        return true;
    }

    public static void jumpLivenessStart(final Context context, final Bundle bundle) {
        if (!isAuraSuccess()) {
            if (OKLog.D) {
                OKLog.i(TAG, "aura is closed, so can't start delivery staff");
            }
            ToastUtils.showToast(context.getResources().getString(R.string.aura_fail));
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeeplinkFaceloginLivenessHelper.2
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("livenessStart".equals(str)) {
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeeplinkFaceloginLivenessHelper.HOST_LIVENESSSTART).toString(), bundle);
                }
            }
        }, "livenessStart");
    }

    public static void startFaceLoginSpecial(Activity activity, Bundle bundle) {
        if (!isAuraSuccess()) {
            if (OKLog.D) {
                OKLog.i(TAG, "aura is closed, so can't start delivery staff");
            }
            ToastUtils.showToast(activity.getResources().getString(R.string.aura_fail));
            return;
        }
        DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVENESSSTART).toString(), bundle);
    }

    public static void startLiveness(final Context context, final Bundle bundle) {
        if (!isAuraSuccess()) {
            if (OKLog.D) {
                OKLog.i(TAG, "aura is closed, so can't start delivery staff");
            }
            ToastUtils.showToast(context.getResources().getString(R.string.aura_fail));
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeeplinkFaceloginLivenessHelper.1
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("faceLoginSwitchStatus".equals(str)) {
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeeplinkFaceloginLivenessHelper.HOST_LIVENESS).toString(), bundle);
                }
            }
        }, "faceLoginSwitchStatus");
    }

    public static void startLivenessLogin(Activity activity, Bundle bundle) {
        if (isAuraSuccess()) {
            return;
        }
        if (OKLog.D) {
            OKLog.i(TAG, "aura is closed, so can't start delivery staff");
        }
        ToastUtils.showToast(activity.getResources().getString(R.string.aura_fail));
    }
}
