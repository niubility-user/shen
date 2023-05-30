package com.huawei.hms.hatool;

import android.util.Pair;
import java.nio.charset.Charset;

/* loaded from: classes12.dex */
public class n {
    public static final Charset a = Charset.forName("UTF-8");

    public static Pair<byte[], String> a(String str) {
        if (str == null || str.length() < 32) {
            return new Pair<>(new byte[0], str);
        }
        String substring = str.substring(0, 32);
        return new Pair<>(com.huawei.secure.android.common.encrypt.d.c.b(substring), str.substring(32));
    }

    public static String a(String str, String str2) {
        Pair<byte[], String> a2 = a(str);
        return new String(com.huawei.secure.android.common.encrypt.a.a.i(com.huawei.secure.android.common.encrypt.d.c.b((String) a2.second), com.huawei.secure.android.common.encrypt.d.c.b(str2), (byte[]) a2.first), a);
    }

    public static String a(byte[] bArr, String str) {
        String str2;
        if (bArr == null || bArr.length == 0 || str == null) {
            str2 = "cbc encrypt(byte) param is not right";
        } else {
            byte[] b = com.huawei.secure.android.common.encrypt.d.c.b(str);
            if (b.length >= 16) {
                return com.huawei.secure.android.common.encrypt.d.c.a(com.huawei.secure.android.common.encrypt.a.a.l(bArr, b));
            }
            str2 = "key length is not right";
        }
        v.b("AesCipher", str2);
        return "";
    }

    public static String b(String str, String str2) {
        return com.huawei.secure.android.common.encrypt.d.c.a(com.huawei.secure.android.common.encrypt.a.a.l(str.getBytes(a), com.huawei.secure.android.common.encrypt.d.c.b(str2)));
    }
}
