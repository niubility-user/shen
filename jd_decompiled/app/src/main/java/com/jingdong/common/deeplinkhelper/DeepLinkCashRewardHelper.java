package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;

/* loaded from: classes5.dex */
public class DeepLinkCashRewardHelper {
    public static void startCashRewardActivity(final Context context, final Bundle bundle) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.CASH_REWARD, "");
        if (TextUtils.isEmpty(switchStringValue)) {
            if (context instanceof IMyActivity) {
                LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCashRewardHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String bundleNameFromBundleId = AuraBundleInfos.getBundleNameFromBundleId(98);
                        if (AuraBundleConfig.getInstance().isBundlePrepared(bundleNameFromBundleId)) {
                            DeepLinkDispatch.startActivityDirect(context, "jingdong://cashreward/main/", bundle);
                            return;
                        }
                        ((IAuraInstallManager) AuraServiceLoader.get(context, IAuraInstallManager.class)).startInstall(context, new AuraInstallRequest.Builder().setBundleName(bundleNameFromBundleId).setAuraInstallStyle(AuraInstallRequest.AURA_INSTALL_STYLE2).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCashRewardHelper.1.1
                            @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                            public void onSuccess() {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                DeepLinkDispatch.startActivityDirect(context, "jingdong://cashreward/main/", bundle);
                            }
                        }).build());
                    }
                });
            }
        } else if (context != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("url", switchStringValue);
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle2);
        }
    }

    public static void startCashRewardActivity(Context context) {
        startCashRewardActivity(context, null);
    }
}
