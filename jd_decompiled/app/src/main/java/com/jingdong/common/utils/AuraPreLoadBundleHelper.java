package com.jingdong.common.utils;

import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.Arrays;

/* loaded from: classes6.dex */
public class AuraPreLoadBundleHelper {
    private static final String TAG = "AuraPreLoadBundleHelper";
    private boolean isInitPreLoad;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final AuraPreLoadBundleHelper INSTANCE = new AuraPreLoadBundleHelper();

        private SingletonHolder() {
        }
    }

    public static AuraPreLoadBundleHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void preLoadBundle(int i2) {
        try {
            final String bundleNameFromBundleId = AuraBundleInfos.getBundleNameFromBundleId(i2);
            if (TextUtils.isEmpty(bundleNameFromBundleId)) {
                return;
            }
            boolean isBundleLoaded = AuraBundleConfig.getInstance().isBundleLoaded(bundleNameFromBundleId);
            if (OKLog.D) {
                OKLog.d(TAG, "preLoadBundle-isBundleLoaded=" + isBundleLoaded);
            }
            if (isBundleLoaded) {
                return;
            }
            ThreadManager.single().post(new Runnable() { // from class: com.jingdong.common.utils.AuraPreLoadBundleHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    if (OKLog.D) {
                        OKLog.d(AuraPreLoadBundleHelper.TAG, "preLoadBundle-loadBundle=" + bundleNameFromBundleId + " curThread=" + Thread.currentThread().getName());
                    }
                    AuraBundleConfig.getInstance().loadBundle(bundleNameFromBundleId);
                }
            }, "preLoadBundle-" + bundleNameFromBundleId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void preLoadBundleAtHome() {
        try {
            if (this.isInitPreLoad) {
                return;
            }
            String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "preLoadBundle", "bundle", "");
            if (TextUtils.isEmpty(config)) {
                return;
            }
            String[] split = config.split("#");
            if (OKLog.D) {
                OKLog.d(TAG, "preLoadBundleAtHome_bundles=" + config + " arr=" + Arrays.toString(split));
            }
            if (split == null || split.length <= 0) {
                return;
            }
            this.isInitPreLoad = true;
            for (String str : split) {
                if (!TextUtils.isEmpty(str)) {
                    preLoadBundle(ParseUtil.parseInt(str, -1));
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new Exception("preLoadBundleAtHome-fail-" + e2));
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void preLoadBundleManual(int i2) {
        try {
            String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "loadBundleManual", "bundle", "");
            if (TextUtils.isEmpty(config)) {
                return;
            }
            String[] split = config.split("#");
            if (OKLog.D) {
                OKLog.d(TAG, "preLoadBundleManual_bundles=" + config + " arr=" + Arrays.toString(split));
            }
            if (split == null || split.length <= 0) {
                return;
            }
            for (String str : split) {
                if (TextUtils.equals(str, String.valueOf(i2))) {
                    preLoadBundle(i2);
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new Exception("preLoadBundleManual-fail-" + e2));
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void preLoadClass(Runnable runnable) {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                if (OKLog.D) {
                    OKLog.d(TAG, "preLoadClass-runnable=" + runnable + " curThread=" + Thread.currentThread().getName());
                }
                if (runnable != null) {
                    ThreadManager.single().post(runnable);
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private AuraPreLoadBundleHelper() {
        this.isInitPreLoad = false;
    }
}
