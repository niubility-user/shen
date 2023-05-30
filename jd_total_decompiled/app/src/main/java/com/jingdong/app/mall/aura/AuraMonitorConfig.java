package com.jingdong.app.mall.aura;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes19.dex */
public class AuraMonitorConfig implements AuraMonitorConfigListener {
    private static final String AURA_ENGINE_CONFIG_CLOSE_SWITCH = "1";
    private static final String AURA_ENGINE_CONFIG_KEY = "AuraEngineConfig";
    private static final String AURA_ENGINE_CONFIG_OPEN_SWITCH = "2";
    private static final String AURA_MONITOR_KEY = "AuraMonitor";
    private static final String AURA_MONITOR_NAMESPACE = "AuraMonitor";
    private static final String TAG = "AuraMonitorConfig";

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean bundleLocationNullCheck() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "bundleLocationNullCheck", "2");
            Log.i(TAG, "bundleLocationNullCheck :" + config);
            if (!TextUtils.isEmpty(config)) {
                if ("2".equals(config)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            Log.e(TAG, "bundleLocationNullCheck config parse error");
            return true;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean bundleSoInfoCheck() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "bundleSoInfoCheck", "1");
            Log.i(TAG, "bundleSoInfoCheck :" + config);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return "2".equals(config);
        } catch (Throwable unused) {
            Log.e(TAG, "bundleSoInfoCheck config parse error");
            return false;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean classNotFoundRunningTaskCheck() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "classNotFoundRunningTaskCheck", "2");
            Log.i(TAG, "classNotFoundRunningTaskCheck :" + config);
            if (!TextUtils.isEmpty(config)) {
                if ("2".equals(config)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            Log.e(TAG, "classNotFoundRunningTaskCheck config parse error");
            return true;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public int dynamicBundleInfoListAbTest() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", "dynamicBundleInfoListAbTest", "1");
            Log.i(TAG, "dynamicBundleInfoListAbTest :" + config);
            if (TextUtils.isEmpty(config)) {
                return 0;
            }
            return Integer.parseInt(config);
        } catch (Throwable unused) {
            Log.e(TAG, "dynamicBundleInfoListAbTest config parse error");
            return 0;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean forceCheckBundleSoInfo() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "forceCheckBundleSoInfo", "1");
            Log.i(TAG, "forceCheckBundleSoInfo :" + config);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return "2".equals(config);
        } catch (Throwable unused) {
            Log.e(TAG, "forceCheckBundleSoInfo config parse error");
            return false;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean isMonitorProvidedInstallFail() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", "providedBundleInstallSwitch", "2");
            Log.i(TAG, "isMonitorProvidedInstallFail :" + config);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return "2".equals(config);
        } catch (Throwable unused) {
            Log.e(TAG, "isMonitorProvidedInstallFail config parse error");
            return false;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean providedBundleActivityResultCheck() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "providedBundleActivityResultCheck", "1");
            Log.i(TAG, "providedBundleActivityResultCheck :" + config);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return "2".equals(config);
        } catch (Throwable unused) {
            Log.e(TAG, "providedBundleActivityResultCheck config parse error");
            return false;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean splitApkConfig() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "AndroidSplitApkConfig", "1");
            Log.i(TAG, "AndroidSplitApkConfig :" + config);
            if (!TextUtils.isEmpty(config)) {
                if ("2".equals(config)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            Log.e(TAG, "splitApkConfig config parse error");
            return true;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean startBundleByBackUp() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "startBundleByBackUp", "2");
            Log.i(TAG, "startBundleByBackUpSwitch :" + config);
            if (!TextUtils.isEmpty(config)) {
                if ("2".equals(config)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            Log.e(TAG, "startBundleByBackUp config parse error");
            return true;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean updateMetaConfig() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "updateMetaConfig", "2");
            Log.i(TAG, "updateMetaConfig :" + config);
            if (!TextUtils.isEmpty(config)) {
                if ("2".equals(config)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            Log.e(TAG, "updateMetaConfig config parse error");
            return true;
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean verityBundleZipSign() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", AURA_ENGINE_CONFIG_KEY, "verityBundleZipSign", "1");
            Log.i(TAG, "verityBundleZipSign :" + config);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return "2".equals(config);
        } catch (Throwable unused) {
            Log.e(TAG, "verityBundleZipSign config parse error");
            return false;
        }
    }
}
