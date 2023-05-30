package com.jingdong.common.navutils;

import android.text.TextUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DegradeConfig {
    public static final String BUNDLE_DEGRADE_CONFIG = "bundleDegradeConfig";
    public static final String BUNDLE_DEGRADE_SWITCH = "bundleDegradeSwitch";
    private static final int DEFAULT_MAX_NAV_CENTER_CONFIG = 255;
    private static final int DEFAULT_MIN_NAV_CENTER_CONFIG = 0;
    private static final int DEFAULT_NAV_CENTER_CONFIG = 3;
    private static final int DEGRADE_TO_H5 = 2;
    private static final int DEGRADE_TO_ORIGIN = 3;
    public static final String NAV_CENTER_CONFIG = "navCenterConfig";
    private static final int NAV_CONFIG_MASK_TO_WEB_IF_EXCEPTION = 1;
    private static final int NAV_CONFIG_MASK_USE_NAVCENTER_PAESER = 2;
    private static final int NAV_CONFIG_MASK_WEB_TO_NATIVE = 4;
    private static final int NOT_DEGRADE = 1;
    private static final String TAG = "DegradeConfig";
    private static DegradeConfig mInstance;
    private Map<String, IDegradeConfig> degradeListenerMap = new HashMap();

    private DegradeConfig() {
    }

    private int getDegradeConfigByMask(long j2) {
        String stringFromPreference = ConfigUtil.getStringFromPreference(BUNDLE_DEGRADE_SWITCH);
        String binaryString = Long.toBinaryString(j2);
        if (TextUtils.isEmpty(stringFromPreference) || stringFromPreference.length() < binaryString.length()) {
            return 1;
        }
        try {
            return Integer.parseInt(String.valueOf(stringFromPreference.charAt(binaryString.length() - 1)));
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return 1;
        }
    }

    public static DegradeConfig getInstance() {
        if (mInstance == null) {
            mInstance = new DegradeConfig();
        }
        return mInstance;
    }

    private int getNavCenterConfigInt() {
        int i2;
        String trim = ConfigUtil.getStringFromPreference(NAV_CENTER_CONFIG).trim();
        if (trim != null && !trim.equals("")) {
            try {
                i2 = Integer.parseInt(trim, 16);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
            if (i2 < 0 && i2 <= 255) {
                return i2;
            }
        }
        i2 = 3;
        return i2 < 0 ? 3 : 3;
    }

    private boolean isOpenOfMaskBit(int i2) {
        return (i2 & getNavCenterConfigInt()) > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBundleConfig(String str) {
        return getDegradeConfigByMask(AuraBundleInfos.getSwitchMaskFromBundleName(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDegradeToH5(String str) {
        return this.degradeListenerMap.containsKey(str) ? this.degradeListenerMap.get(str).getDegradeConfig() == 2 : getBundleConfig(str) == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDegradeToOrigin(String str) {
        return this.degradeListenerMap.containsKey(str) ? this.degradeListenerMap.get(str).getDegradeConfig() == 3 : getBundleConfig(str) == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isNotDegrade(String str) {
        return this.degradeListenerMap.containsKey(str) ? this.degradeListenerMap.get(str).getDegradeConfig() == 1 : getBundleConfig(str) == 1;
    }

    public void setDegradeListenerMap(Map<String, IDegradeConfig> map) {
        this.degradeListenerMap = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldNav2WebIfException() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldNavWeb2Native() {
        return isOpenOfMaskBit(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldUseNavCenterParser() {
        return isOpenOfMaskBit(2);
    }
}
