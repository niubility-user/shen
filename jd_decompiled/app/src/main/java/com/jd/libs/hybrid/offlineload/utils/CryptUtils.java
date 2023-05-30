package com.jd.libs.hybrid.offlineload.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.base.util.Log;
import java.util.Random;

/* loaded from: classes16.dex */
public class CryptUtils {
    private CryptUtils() {
    }

    public static String decryptData(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = str.trim();
                Log.d("CryptUtils", str);
                if (!str.startsWith("{") && !str.endsWith("}") && !str.startsWith("[") && !str.endsWith("]")) {
                    Log.d("CryptUtils", DYConstants.DY_FALSE);
                    int length = str.length();
                    if (length > 3) {
                        int i2 = length - 3;
                        int charAt = str.charAt(i2) - '0';
                        if (charAt >= 0 && charAt <= 9) {
                            return new String(Base64.decode((str.substring(0, i2) + str.substring(length - 2)).substring(charAt), 0), "utf-8");
                        }
                        Log.e("CryptUtils", "Fail to decrypt. Encrypted string's insert-count is wrong, count = " + charAt);
                    } else {
                        Log.e("CryptUtils", "Fail to decrypt. Encrypted string too short, string = " + str);
                    }
                }
                Log.d("CryptUtils", "It is not a encrypted data, return the raw data");
                return str;
            } catch (Exception e2) {
                Log.e("CryptUtils", "Fail to decrypt.", e2);
            }
        }
        Log.e("CryptUtils", "Return the raw data = " + str);
        return str;
    }

    public static String encodeData(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            String str2 = new String(Base64.encode(str.getBytes(), 2));
            int nextInt = new Random().nextInt(9) + 1;
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 <= nextInt; i2++) {
                sb.append("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".charAt(i2));
            }
            sb.append(str2);
            sb.insert(sb.length() - 2, nextInt);
            return sb.toString();
        } catch (Exception unused) {
            return str;
        }
    }
}
