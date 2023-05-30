package com.jingdong.common.mywallet;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkPayCodeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpayQuickPassSdkHelper;
import com.jingdong.common.entity.JDPayCodeParam;
import com.jingdong.common.entity.JDQuickPassParam;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;

/* loaded from: classes5.dex */
public class WalletUtils {
    public static final String FUNCTIONID_FUKUANMA = "fukuanma";
    public static final String FUNCTIONID_SHANFU = "dongpayment";
    private static final String QUICK_PASS_BUNDLE_NAME = "com.jd.lib.quickpass";

    public static void dealJumpLogic(BaseActivity baseActivity, String str) {
        if (baseActivity == null || str == null || str == "") {
            return;
        }
        if (TextUtils.equals(str, FUNCTIONID_SHANFU)) {
            jumpQuickPayment(baseActivity);
        } else if (TextUtils.equals(str, FUNCTIONID_FUKUANMA)) {
            jumpPaymentCode(baseActivity);
        }
    }

    public static void jumpPaymentCode(Activity activity) {
        String userPin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(userPin)) {
            return;
        }
        try {
            JDPayCodeParam jDPayCodeParam = new JDPayCodeParam();
            jDPayCodeParam.jdId = userPin;
            jDPayCodeParam.mode = "Native";
            jDPayCodeParam.token = UserUtil.getWJLoginHelper().getA2();
            jDPayCodeParam.source = "jdmall";
            jDPayCodeParam.extraInfo = "jdapp-wdqb-fkm";
            Bundle bundle = new Bundle();
            bundle.putString("businessType", "JDPayPaymentCode");
            bundle.putString("param", JDJSON.toJSONString(jDPayCodeParam));
            DeepLinkPayCodeHelper.startPayCodeActivity(activity, bundle);
        } catch (Exception unused) {
        }
    }

    public static void jumpQuickPayment(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            JDQuickPassParam jDQuickPassParam = new JDQuickPassParam();
            jDQuickPassParam.source = "jdmall_wallet";
            jDQuickPassParam.mode = JDQuickPassParam.JD_QUICK_PASS_MODE_NATIVE;
            jDQuickPassParam.sessionKey = UserUtil.getWJLoginHelper().getA2();
            jDQuickPassParam.pin = LoginUserBase.getUserPin();
            String jSONString = JDJSON.toJSONString(jDQuickPassParam);
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", JDQuickPassParam.JD_PAY_QUICK_PASS);
            bundle.putString(JDQuickPassParam.JD_QUICK_PASS_PARAM, jSONString);
            jumpToQuickPassWithOffline(activity, bundle);
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    private static void jumpToQuickPassWithOffline(final Activity activity, final Bundle bundle) {
        if (activity == null || bundle == null) {
            return;
        }
        String bundleNameFromBundleId = AuraBundleInfos.getBundleNameFromBundleId(49);
        if (TextUtils.isEmpty(bundleNameFromBundleId)) {
            bundleNameFromBundleId = QUICK_PASS_BUNDLE_NAME;
        }
        if (AuraBundleConfig.getInstance().isBundlePrepared(bundleNameFromBundleId)) {
            startToQuickPassPage(activity, bundle);
        } else {
            ((IAuraInstallManager) AuraServiceLoader.get(activity, IAuraInstallManager.class)).startInstall(activity, new AuraInstallRequest.Builder().setBundleName(bundleNameFromBundleId).setAuraInstallStyle(AuraInstallRequest.AURA_INSTALL_STYLE2).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.mywallet.WalletUtils.1
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                public void onSuccess() {
                    WalletUtils.startToQuickPassPage(activity, bundle);
                }
            }).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startToQuickPassPage(final Activity activity, final Bundle bundle) {
        if (activity == null || bundle == null) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            DeeplinkJDpayQuickPassSdkHelper.startJDPayActivityForResult(activity, bundle, 1026);
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.mywallet.WalletUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    DeeplinkJDpayQuickPassSdkHelper.startJDPayActivityForResult(activity, bundle, 1026);
                }
            });
        }
    }
}
