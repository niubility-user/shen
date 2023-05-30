package com.jd.aips.verify;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.jd.aips.common.utils.ThreadUtils;
import com.jd.aips.verify.BaseEngineLauncher;
import com.jd.aips.verify.tracker.TrackerCallback;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;

/* loaded from: classes12.dex */
public class BundleEngineLauncher extends BaseEngineLauncher {
    public BundleEngineLauncher(@NonNull Handler handler, @NonNull BaseEngineLauncher.LauncherCallback launcherCallback) {
        super(handler, launcherCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadBundleToLaunch(@NonNull final Context context, @NonNull final String str, @NonNull String str2, @NonNull final Bundle bundle, @NonNull final VerifyCallback verifyCallback, final TrackerCallback trackerCallback) {
        try {
            ((IAuraInstallManager) AuraServiceLoader.get(context, IAuraInstallManager.class)).startInstall(context, new AuraInstallRequest.Builder().setBundleName(str2).setAuraInstallStyle(AuraInstallRequest.AURA_INSTALL_STYLE1).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jd.aips.verify.BundleEngineLauncher.2
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                public void onSuccess() {
                    if (ThreadUtils.isMainThread()) {
                        BundleEngineLauncher.this.launchEngine(context, str, bundle, verifyCallback, trackerCallback);
                    } else {
                        BundleEngineLauncher.this.mainHandler.post(new Runnable() { // from class: com.jd.aips.verify.BundleEngineLauncher.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                BundleEngineLauncher.this.launchEngine(context, str, bundle, verifyCallback, trackerCallback);
                            }
                        });
                    }
                }
            }).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jd.aips.verify.BundleEngineLauncher.1
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
                public void onFailure(Exception exc) {
                    BundleEngineLauncher.this.launcherCallback.onFailure();
                }
            }).build());
        } catch (Throwable unused) {
            this.launcherCallback.onFailure();
        }
    }
}
