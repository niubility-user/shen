package com.jd.phc;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class PHCNativeLoader {
    public static boolean a;

    /* loaded from: classes.dex */
    private static class b {
        public static PHCNativeLoader a = new PHCNativeLoader();
    }

    static {
        try {
            System.loadLibrary("phccommon-lib");
            a = true;
        } catch (Throwable th) {
            if (com.jd.phc.a.a) {
                th.printStackTrace();
            }
            a = false;
        }
    }

    private native byte[] Decrypt(byte[] bArr, int i2, byte[] bArr2);

    private native byte[] Encrypt(byte[] bArr, int i2, byte[] bArr2);

    private native byte[] GenDKey(byte[] bArr, String str, String str2, byte[] bArr2);

    private native byte[] GenHash(String str);

    private native String GenRandomKey();

    private native byte[] RSADecrypt(String str, byte[] bArr);

    private native byte[] RSAEncrypt(String str, byte[] bArr);

    public static PHCNativeLoader f() {
        return b.a;
    }

    public byte[] a(byte[] bArr, int i2, byte[] bArr2) {
        if (a) {
            return Decrypt(bArr, i2, bArr2);
        }
        return null;
    }

    public byte[] b(byte[] bArr, int i2, byte[] bArr2) {
        if (a) {
            return Encrypt(bArr, i2, bArr2);
        }
        return null;
    }

    public byte[] c(byte[] bArr, String str, String str2, byte[] bArr2) {
        if (a) {
            return GenDKey(bArr, str, str2, bArr2);
        }
        return null;
    }

    public byte[] d(String str) {
        if (a) {
            return GenHash(str);
        }
        return null;
    }

    public String e() {
        return a ? GenRandomKey() : "";
    }

    public byte[] g(String str, byte[] bArr) {
        if (!a || TextUtils.isEmpty(str) || bArr == null || bArr.length <= 0) {
            return null;
        }
        return RSAEncrypt(str, bArr);
    }

    private PHCNativeLoader() {
    }
}
