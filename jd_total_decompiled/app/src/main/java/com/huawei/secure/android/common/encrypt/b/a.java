package com.huawei.secure.android.common.encrypt.b;

import android.os.Build;
import com.huawei.secure.android.common.encrypt.d.f;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: classes12.dex */
public abstract class a {
    private static final String a = "PBKDF2";

    private static byte[] a(char[] cArr, byte[] bArr, int i2, int i3, boolean z) {
        SecretKeyFactory secretKeyFactory;
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr, bArr, i2, i3);
            if (z) {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            } else {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            }
            return secretKeyFactory.generateSecret(pBEKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
            f.c(a, "pbkdf exception : " + e2.getMessage());
            return new byte[0];
        }
    }

    public static byte[] b(char[] cArr, byte[] bArr, int i2, int i3) {
        return a(cArr, bArr, i2, i3, false);
    }

    public static byte[] c(char[] cArr, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[0];
        if (Build.VERSION.SDK_INT < 26) {
            f.c(a, "system version not high than 26");
            return bArr2;
        }
        return a(cArr, bArr, i2, i3, true);
    }
}
