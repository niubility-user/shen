package com.jingdong.common.utils.security;

import android.text.TextUtils;
import com.jingdong.common.utils.HexUtils;

/* loaded from: classes.dex */
public final class JDUUIDEncHelper {
    public static final String TAG = "JDUUIDEncryptUtil";

    /* loaded from: classes.dex */
    public static class EncryptResult {
        public String eu;
        public String fv;

        public EncryptResult(String str, String str2) {
            this.eu = str;
            this.fv = str2;
        }
    }

    public static String decrypt(EncryptResult encryptResult) {
        if (encryptResult == null) {
            return null;
        }
        String str = encryptResult.eu + encryptResult.fv;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return HexUtils.hexUTF82String(swap(str));
    }

    public static EncryptResult encrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String swap = swap(HexUtils.string2HexUTF8(str));
            return new EncryptResult(swap.substring(0, swap.length() / 2), swap.substring(swap.length() / 2));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String swap(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            char c2 = charArray[i3];
            int i4 = i3 + 1;
            charArray[i3] = charArray[i4];
            charArray[i4] = c2;
        }
        return new String(charArray);
    }
}
