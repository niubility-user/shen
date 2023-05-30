package com.jingdong.jdsdk.network.config;

import android.text.TextUtils;
import com.jd.framework.network.dialing.DNSManager;
import com.jingdong.jdsdk.network.JDHttpTookit;

/* loaded from: classes14.dex */
public class BuildInIPBackUpConfig implements DNSManager.IBuildInIPBackUpConfig {
    @Override // com.jd.framework.network.dialing.DNSManager.IBuildInIPBackUpConfig
    public boolean isFeatureEnable() {
        if (JDHttpTookit.getEngine() != null) {
            String stringFromPreference = JDHttpTookit.getEngine().getRuntimeConfigImpl().getStringFromPreference("buildInIpDegrade");
            if (TextUtils.isEmpty(stringFromPreference)) {
                return true;
            }
            return TextUtils.equals(stringFromPreference, "1");
        }
        return true;
    }
}
