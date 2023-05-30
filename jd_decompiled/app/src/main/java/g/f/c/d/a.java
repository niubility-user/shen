package g.f.c.d;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes13.dex */
public class a {
    public static String a(String str, String str2) throws Exception {
        b(str, str2);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        byte[] bytes = str2.getBytes("UTF-8");
        cipher.init(1, new SecretKeySpec(bytes, JDKeyStore.KEY_TYPE_AES), new IvParameterSpec(bytes));
        return c(cipher.doFinal(str.getBytes("UTF-8")));
    }

    private static void b(String str, String str2) throws Exception {
        if (str != null && str.trim().length() >= 1) {
            if (str2 == null || str2.trim().length() < 1) {
                throw new Exception("authKey is empty");
            }
            return;
        }
        throw new Exception("message is empty");
    }

    private static String c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
}
