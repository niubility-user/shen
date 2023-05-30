package com.jingdong.common.utils;

import android.content.Context;
import android.nfc.NfcAdapter;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.runTimeConfig.ConfigUtil;

/* loaded from: classes6.dex */
public final class JDNfcAdapterUtil {
    private JDNfcAdapterUtil() {
    }

    public static boolean hasNfcAdapter(Context context) {
        return (context == null || NfcAdapter.getDefaultAdapter(context) == null) ? false : true;
    }

    public static boolean isEnabled(Context context) {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(context);
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static boolean isNfcEnabled(Context context) {
        if (hasNfcAdapter(context) && isEnabled(context)) {
            return CommonBase.getBooleanFromPreference(PersonalConstants.SP_SETTING_NFC, Boolean.valueOf(ConfigUtil.getKeySwitchState(ConfigUtil.KEY_NFC_CONFIG))).booleanValue();
        }
        return false;
    }
}
