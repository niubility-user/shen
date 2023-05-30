package com.jingdong.aura.sdk.provided;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.updater.IUpdateListener;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.aura.wrapper.AuraInitializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class b implements IAuraInstallManager {
    public static Map<String, List<AuraInstallRequest.IInstallListener>> a = new HashMap();
    private IAuraInstallCallBack b;

    @Override // com.jingdong.aura.serviceloder.IServiceProvider
    public void init(Context context) {
        this.b = (IAuraInstallCallBack) AuraServiceLoader.get(context, IAuraInstallCallBack.class);
    }

    @Override // com.jingdong.aura.provided.api.IAuraInstallManager
    public void startInstall(Context context, AuraInstallRequest auraInstallRequest) {
        final String bundleName = auraInstallRequest.getBundleName();
        AuraInstallRequest.IInstallListener installListener = auraInstallRequest.getInstallListener();
        if (AuraConfig.isBundleLoaded(bundleName)) {
            if (installListener.getOnSuccessListener() != null) {
                installListener.getOnSuccessListener().onSuccess();
                return;
            }
            return;
        }
        if (a.get(bundleName) == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(installListener);
            a.put(bundleName, arrayList);
        } else if (!a.get(bundleName).contains(installListener)) {
            a.get(bundleName).add(installListener);
        }
        ArrayList<String> notPreparedProvidedBundles = AuraConfig.getNotPreparedProvidedBundles(bundleName);
        if (notPreparedProvidedBundles == null || notPreparedProvidedBundles.size() <= 0) {
            try {
                AuraInitializer.loadBundle(bundleName);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.b.installFinished(bundleName, true, null);
        } else if (auraInstallRequest.getDownloadType() == 1 || auraInstallRequest.getDownloadType() == 2) {
            com.jingdong.aura.sdk.update.a.a().f12242l.a(com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getUpdateIdFromBundleName(bundleName), new IUpdateListener() { // from class: com.jingdong.aura.sdk.provided.b.1
                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onDownloadFailure(Exception exc) {
                    b.this.b.installFinished(bundleName, false, exc);
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onDownloadFinish(AuraBundleResult auraBundleResult) {
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onDownloadPause(boolean z) {
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onDownloadProgress(long j2, long j3) {
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onDownloadStart() {
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onInstallFinish(boolean z) {
                    b.this.b.installFinished(bundleName, true, null);
                }

                @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
                public final void onInstallStart() {
                }
            }, auraInstallRequest.getDownloadType() == 1);
        } else {
            String name = ((auraInstallRequest.getAuraInstallStyle() == null || !auraInstallRequest.getAuraInstallStyle().equals(AuraInstallRequest.AURA_INSTALL_STYLE2)) ? ProvidedBundleNotFoundActivity.class : ProvidedBundleDownloadActivityStyle2.class).getName();
            if (TextUtils.isEmpty(name)) {
                return;
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, name));
            intent.putExtra("aura_target_bundlename", bundleName);
            intent.putExtra("aura_action", "installbundle");
            intent.putStringArrayListExtra("aura_not_prepared_bundlename", notPreparedProvidedBundles);
            intent.addFlags(268435456);
            intent.putExtra("trigger", "proactive");
            context.startActivity(intent);
        }
    }
}
