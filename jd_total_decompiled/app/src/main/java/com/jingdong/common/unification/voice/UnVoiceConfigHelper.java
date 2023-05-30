package com.jingdong.common.unification.voice;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes6.dex */
public class UnVoiceConfigHelper {
    private static UnVoiceConfigHelper helper;

    private UnVoiceConfigHelper() {
    }

    public static UnVoiceConfigHelper getInstance() {
        UnVoiceConfigHelper unVoiceConfigHelper;
        UnVoiceConfigHelper unVoiceConfigHelper2 = helper;
        if (unVoiceConfigHelper2 != null) {
            return unVoiceConfigHelper2;
        }
        synchronized (UnVoiceConfigHelper.class) {
            if (helper == null) {
                helper = new UnVoiceConfigHelper();
            }
            unVoiceConfigHelper = helper;
        }
        return unVoiceConfigHelper;
    }

    public boolean useNewVoiceAsr() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "voiceConfig", "useAiVoiceAsr");
        if (TextUtils.isEmpty(config)) {
            return true;
        }
        return TextUtils.equals("1", config);
    }

    public boolean useNewVoiceByNusinessNo(String str) {
        if (useNewVoiceAsr()) {
            String config = JDMobileConfig.getInstance().getConfig("unification", "voiceConfig", str);
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            return TextUtils.equals("1", config);
        }
        return false;
    }

    public boolean useNewVoiceTts() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "voiceConfig", "useAiVoiceTts");
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        return TextUtils.equals("1", config);
    }
}
