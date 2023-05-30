package c.t.m.g;

import android.util.Base64;
import com.google.common.primitives.SignedBytes;
import com.jd.dynamic.DYConstants;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;

@Deprecated
/* loaded from: classes.dex */
public class o5 {
    public static byte[] a;
    public static HashMap<String, String> b;

    static {
        e();
    }

    public static final synchronized String a(String str) {
        String str2;
        synchronized (o5.class) {
            str2 = b.get(str);
            if (t2.c(str2)) {
                str2 = "";
            }
        }
        return str2;
    }

    public static String b(String str, String str2) {
        return c(str, str2, 2);
    }

    public static String c(String str, String str2, int i2) {
        if (i2 == 1 || i2 == 2) {
            if (str != null && str.length() != 0) {
                try {
                    byte[] bytes = i2 == 1 ? str.getBytes() : i2 == 2 ? Base64.decode(str.getBytes(), 2) : null;
                    if (bytes != null && bytes.length != 0) {
                        byte[] g2 = g(bytes, 0, bytes.length, str2, i2);
                        if (i2 == 1) {
                            return Base64.encodeToString(g2, 2);
                        }
                        if (i2 == 2) {
                            return new String(g2);
                        }
                        return null;
                    }
                } catch (Throwable unused) {
                }
            }
            return "";
        }
        throw new IllegalArgumentException("wrong mode.");
    }

    public static Cipher d(String str, int i2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(i2, secretKeySpec, new IvParameterSpec(a));
        return cipher;
    }

    public static final synchronized void e() {
        synchronized (o5.class) {
            try {
                b = new HashMap<>();
                for (String str : new String(f5.c(new byte[]{8, 16, 116, 91, -113, -11, 70, ReplyCode.reply0xc2, -45, -36, 8, ReplyCode.reply0x77, ReplyCode.reply0x87, 51, ReplyCode.reply0x36, ReplyCode.reply0x86, 72, 12, -109, -53, 78, 13, -80, ReplyCode.reply0x8a, -43, -111, 85, -110, 87, 62, 10, 92, ReplyCode.reply0xd2, ReplyCode.reply0x35, ReplyCode.reply0xaa, ReplyCode.reply0xb2, 75, ReplyCode.reply0x86, 108, -53, 30, ReplyCode.reply0x7b, ReplyCode.reply0x86, ReplyCode.reply0x8e, 65, 78, 27, -15, 63, -58, -22, 26, ReplyCode.reply0xed, ReplyCode.reply0xa9, -65, ReplyCode.reply0xde, ReplyCode.reply0xb4, 3, -26, ReplyCode.reply0xb5, ReplyCode.reply0x35, ReplyCode.reply0x73, -22, -113, 102, 43, -52, 74, ReplyCode.reply0x27, ReplyCode.reply0x8b, ReplyCode.reply0x27, -63, ReplyCode.reply0x8c, 4, -99, 109, 1, ReplyCode.reply0x35, -127, ReplyCode.reply0xb5, 74, 12, ReplyCode.reply0xa8, 77, -37, ReplyCode.reply0x35, 1, 89, 73, -95, -103, ReplyCode.reply0xdf, ReplyCode.reply0xa5, 1, -22, 66, 105, 3, 12, 111, ReplyCode.reply0xad, 85, ReplyCode.reply0xc7, ReplyCode.reply0xa7, -43, -94, 99, ReplyCode.reply0x8b, 2, ReplyCode.reply0xaf, 67, -2, 111, Byte.MIN_VALUE, ReplyCode.reply0x84, ReplyCode.reply0x4f, -111, 86, ReplyCode.reply0xad, -106, 92, ReplyCode.reply0xa9, 17, -85, ReplyCode.reply0x77, -15, ReplyCode.reply0x8c, -68, 121, 1, 6, 92, ReplyCode.reply0x28, -12, -22, Constant.MAX_DURATION_DEFAULT, 31, -73, -6, 31, -28, -8, -14, ReplyCode.reply0x83, -63, ReplyCode.reply0xdf, -85, -12, 7, 23, 109, ReplyCode.reply0x7a, -110, 113, -103, 15, -101, SignedBytes.MAX_POWER_OF_TWO, 67, ReplyCode.reply0x73, 3, -85, 73, 102, 59, -61, -16, 112, ReplyCode.reply0x35, ReplyCode.reply0x7b, 118, ReplyCode.reply0xed, -61, 63, -102, 101, -40, ReplyCode.reply0x8a, ReplyCode.reply0x31, -127, 14, -109, -30, ReplyCode.reply0x85, ReplyCode.reply0x29, ReplyCode.reply0x8b, -14, 59, 10, ReplyCode.reply0xc8, 78, 121, ReplyCode.reply0xb3, ReplyCode.reply0x37, ReplyCode.reply0xac, -40, ReplyCode.reply0xde, -69, -94, ReplyCode.reply0x34, ReplyCode.reply0x35, 94, ReplyCode.reply0xcf, 48, -10, 82, 18, -7, ReplyCode.reply0x38, ReplyCode.reply0x82, -70, -94, 59, 118, 25, 0, -11, -26, 7, ReplyCode.reply0x82, 72, -93, -66, -6, 62, 124, 74, 109, 18, 61, ReplyCode.reply0xc7, 98, 67, 95, 25, -53, -25, 65, Byte.MAX_VALUE, -5, -74, 30, -99, -1, 87, -104, ReplyCode.reply0x36, -61, ReplyCode.reply0x25, 107, 12, ReplyCode.reply0x6a, 67, 21, 17, -100, 113, -35, -43, ReplyCode.reply0xc2, -11, -52, ReplyCode.reply0x8c, -97, -44, ReplyCode.reply0x83, 28, -67, 67, 72, -45, ReplyCode.reply0xac, ReplyCode.reply0xae, 102, ReplyCode.reply0x8a, 80, -9, ReplyCode.reply0xa9, 6, 30, -100, ReplyCode.reply0x24, ReplyCode.reply0x86, 6, 71, ReplyCode.reply0xa5, 21, 96, -53, ReplyCode.reply0xb1, ReplyCode.reply0xae, 76, 91, -101, 84, -4, 87, -15, ReplyCode.reply0xd0, -38, ReplyCode.reply0xaf, 10, 42, -11, 17, 117, -39, ReplyCode.reply0x89, 90, -51, 67, ReplyCode.reply0xa9, ReplyCode.reply0x37, -69, 48, -92, 116, ReplyCode.reply0x22, 118, ReplyCode.reply0xc8, 4, 95, -106, -3, 23, -9, 81, -69, -58, ReplyCode.reply0xc7, 26, ReplyCode.reply0xa9, ReplyCode.reply0x77, 62, ReplyCode.reply0xa9, 69, -8, -58, ReplyCode.reply0xb3, ReplyCode.reply0xc7, 8, ReplyCode.reply0xa7, 15, 77, SignedBytes.MAX_POWER_OF_TWO, -15, -110, 80, -13, 70, 96, -41, 73, -2, -66, 43, 107, ReplyCode.reply0x67, 44, ReplyCode.reply0xaf, ReplyCode.reply0xef, -41, ReplyCode.reply0xd0, ReplyCode.reply0xa5, Constant.MAX_DURATION_DEFAULT, 25, ReplyCode.reply0xb4, -109}), "UTF-8").split(";")) {
                    String[] split = str.split(DYConstants.DY_REGEX_COMMA);
                    if (split.length == 2) {
                        b.put(split[0], split[1]);
                    }
                }
                a = b.get("enc_iv").getBytes("UTF-8");
            } catch (Throwable unused) {
            }
        }
    }

    public static byte[] f(byte[] bArr, int i2, int i3, String str) {
        return g(bArr, i2, i3, str, 1);
    }

    public static byte[] g(byte[] bArr, int i2, int i3, String str, int i4) {
        if (i4 == 1 || i4 == 2) {
            if (bArr == null || bArr.length == 0 || i2 < 0 || i3 == 0) {
                return e2.a;
            }
            try {
                Cipher d = d(str, i4);
                return d == null ? e2.a : d.doFinal(bArr, i2, i3);
            } catch (Throwable unused) {
                return e2.a;
            }
        }
        throw new IllegalArgumentException("wrong mode.");
    }

    public static byte[] h(byte[] bArr, String str) {
        return f(bArr, 0, bArr.length, str);
    }

    public static String i(String str, String str2) {
        return c(str, str2, 1);
    }
}
