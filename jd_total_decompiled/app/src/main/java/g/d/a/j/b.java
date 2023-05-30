package g.d.a.j;

import android.text.TextUtils;
import com.heytap.msp.push.encrypt.AESEncrypt;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes12.dex */
public class b {
    public static String a = null;
    public static String b = "";

    public static String a(String str) {
        boolean z;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str2 = AESEncrypt.decrypt(AESEncrypt.SDK_APP_SECRET, str);
            d.a("sdkDecrypt aesDecrypt aes data " + str2);
            z = true;
        } catch (Exception e2) {
            d.a("sdkDecrypt AES excepiton " + e2.toString());
            z = false;
        }
        if (TextUtils.isEmpty(str2) ? false : z) {
            return str2;
        }
        try {
            str2 = c.a(str, c());
            a = "DES";
            e.b().f(a);
            d.a("sdkDecrypt aesDecrypt des data " + str2);
            return str2;
        } catch (Exception e3) {
            d.a("sdkDecrypt DES excepiton " + e3.toString());
            return str2;
        }
    }

    public static String b(String str) {
        boolean z;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str2 = c.a(str, c());
            d.a("sdkDecrypt desDecrypt des data " + str2);
            z = true;
        } catch (Exception e2) {
            d.a("sdkDecrypt DES excepiton " + e2.toString());
            z = false;
        }
        if (TextUtils.isEmpty(str2) ? false : z) {
            return str2;
        }
        try {
            str2 = AESEncrypt.decrypt(AESEncrypt.SDK_APP_SECRET, str);
            a = JDKeyStore.KEY_TYPE_AES;
            e.b().f(a);
            d.a("sdkDecrypt desDecrypt aes data " + str2);
            return str2;
        } catch (Exception e3) {
            d.a("sdkDecrypt AES excepiton " + e3.toString());
            return str2;
        }
    }

    private static String c() {
        if (TextUtils.isEmpty(b)) {
            b = new String(g.d.a.c.a.l("Y29tLm5lYXJtZS5tY3M="));
        }
        byte[] d = d(b);
        f(d);
        return d != null ? new String(d, Charset.forName("UTF-8")) : "";
    }

    public static byte[] d(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new byte[0];
        }
    }

    public static String e(String str) {
        d.a("sdkDecrypt start data " + str);
        if (TextUtils.isEmpty(a)) {
            a = e.b().a();
        }
        if ("DES".equals(a)) {
            d.a("sdkDecrypt start DES");
            return b(str);
        }
        d.a("sdkDecrypt start AES");
        return a(str);
    }

    public static byte[] f(byte[] bArr) {
        int length = bArr.length % 2 == 0 ? bArr.length : bArr.length - 1;
        for (int i2 = 0; i2 < length; i2 += 2) {
            byte b2 = bArr[i2];
            int i3 = i2 + 1;
            bArr[i2] = bArr[i3];
            bArr[i3] = b2;
        }
        return bArr;
    }
}
