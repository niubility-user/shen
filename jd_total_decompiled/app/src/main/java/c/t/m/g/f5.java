package c.t.m.g;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class f5 {
    public static String a(String str) {
        try {
            return new String(c(k2.d(str)), "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static Cipher b(byte[] bArr, byte[] bArr2, int i2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(i2, secretKeySpec, new IvParameterSpec(bArr2));
        return cipher;
    }

    public static byte[] c(byte[] bArr) {
        return d(bArr, 0, bArr.length);
    }

    public static byte[] d(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        return f(bArr, i2 + 16, i3 - 16, bArr2, bArr2, 2);
    }

    public static byte[] e(byte[] bArr, int i2, int i3, byte[] bArr2, byte[] bArr3) {
        return f(bArr, i2, i3, bArr2, bArr3, 1);
    }

    public static byte[] f(byte[] bArr, int i2, int i3, byte[] bArr2, byte[] bArr3, int i4) {
        if (i4 == 1 || i4 == 2) {
            if (bArr == null || bArr.length == 0 || i2 < 0 || i3 <= 0) {
                return e2.a;
            }
            try {
                Cipher b = b(bArr2, bArr3, i4);
                return b == null ? e2.a : b.doFinal(bArr, i2, i3);
            } catch (Throwable unused) {
                return e2.a;
            }
        }
        throw new IllegalArgumentException("wrong mode.");
    }

    public static byte[] g(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return e(bArr, 0, bArr.length, bArr2, bArr3);
    }

    public static String h(String str) {
        try {
            return k2.b(i(str.getBytes("UTF-8")));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static byte[] i(byte[] bArr) {
        return j(bArr, 0, bArr.length);
    }

    public static byte[] j(byte[] bArr, int i2, int i3) {
        try {
            byte[] b = f6.b(16);
            byte[] f2 = f(bArr, i2, i3, b, b, 1);
            if (t2.e(f2)) {
                return f2;
            }
            byte[] bArr2 = new byte[b.length + f2.length];
            System.arraycopy(b, 0, bArr2, 0, b.length);
            System.arraycopy(f2, 0, bArr2, b.length, f2.length);
            return bArr2;
        } catch (Throwable unused) {
            return e2.a;
        }
    }
}
