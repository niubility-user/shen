package com.huawei.secure.android.common.encrypt.b;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.d.c;
import com.huawei.secure.android.common.encrypt.d.f;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public final class b {
    private static final String a = "SHA";
    private static final String[] b = {MessageDigestAlgorithms.SHA_256, MessageDigestAlgorithms.SHA_384, MessageDigestAlgorithms.SHA_512};

    private static boolean a(String str) {
        for (String str2 : b) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String b(String str) {
        return c(str, MessageDigestAlgorithms.SHA_256);
    }

    public static String c(String str, String str2) {
        byte[] bArr;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!a(str2)) {
                f.c(a, "algorithm is not safe or legal");
                return "";
            }
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bArr = new byte[0];
                f.c(a, "Error in generate SHA UnsupportedEncodingException");
            }
            return c.a(d(bArr, str2));
        }
        f.c(a, "content or algorithm is null.");
        return "";
    }

    public static byte[] d(byte[] bArr, String str) {
        if (bArr != null && !TextUtils.isEmpty(str)) {
            if (!a(str)) {
                f.c(a, "algorithm is not safe or legal");
                return new byte[0];
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException unused) {
                f.c(a, "Error in generate SHA NoSuchAlgorithmException");
                return new byte[0];
            }
        }
        f.c(a, "content or algorithm is null.");
        return new byte[0];
    }
}
