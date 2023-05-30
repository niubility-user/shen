package com.huawei.secure.android.common.encrypt.d;

import android.os.Build;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;

/* loaded from: classes12.dex */
public class b {
    private static boolean a = false;
    private static boolean b = true;

    /* JADX WARN: Removed duplicated region for block: B:23:0x001b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static SecureRandom a() {
        SecureRandom secureRandom;
        f.b("EncryptUtil", "generateSecureRandomNew ");
        try {
        } catch (NoSuchAlgorithmException unused) {
            f.c("EncryptUtil", "getSecureRandomBytes: NoSuchAlgorithmException");
        }
        if (Build.VERSION.SDK_INT >= 26) {
            secureRandom = SecureRandom.getInstanceStrong();
            if (secureRandom == null) {
                try {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                } catch (NoSuchAlgorithmException unused2) {
                    f.c("EncryptUtil", "NoSuchAlgorithmException");
                    return secureRandom;
                } catch (Throwable th) {
                    if (b) {
                        f.c("EncryptUtil", "exception : " + th.getMessage() + " , you should implementation bcprov-jdk15on library");
                        b = false;
                    }
                    return secureRandom;
                }
            }
            AESEngine aESEngine = new AESEngine();
            byte[] bArr = new byte[32];
            secureRandom.nextBytes(bArr);
            return new SP800SecureRandomBuilder(secureRandom, true).setEntropyBitsRequired(384).buildCTR(aESEngine, 256, bArr, false);
        }
        secureRandom = null;
        if (secureRandom == null) {
        }
        AESEngine aESEngine2 = new AESEngine();
        byte[] bArr2 = new byte[32];
        secureRandom.nextBytes(bArr2);
        return new SP800SecureRandomBuilder(secureRandom, true).setEntropyBitsRequired(384).buildCTR(aESEngine2, 256, bArr2, false);
    }

    private static byte[] b(int i2) {
        SecureRandom a2 = a();
        if (a2 == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        a2.nextBytes(bArr);
        return bArr;
    }

    public static byte[] c(int i2) {
        if (!a) {
            byte[] bArr = new byte[i2];
            SecureRandom secureRandom = null;
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    secureRandom = SecureRandom.getInstanceStrong();
                }
            } catch (NoSuchAlgorithmException unused) {
                f.c("EncryptUtil", "getSecureRandomBytes: NoSuchAlgorithmException");
            }
            if (secureRandom == null) {
                try {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                } catch (NoSuchAlgorithmException unused2) {
                    f.c("EncryptUtil", "getSecureRandomBytes getInstance: NoSuchAlgorithmException");
                    return new byte[0];
                } catch (Exception e2) {
                    f.c("EncryptUtil", "getSecureRandomBytes getInstance: exception : " + e2.getMessage());
                    return new byte[0];
                }
            }
            secureRandom.nextBytes(bArr);
            return bArr;
        }
        return b(i2);
    }

    public static String d(int i2) {
        return c.a(c(i2));
    }
}
