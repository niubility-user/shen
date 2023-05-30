package c.t.m.g;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class x5 {
    public byte[] a = new byte[0];
    public PublicKey b;

    public static String a(String str) {
        if (str.contains("-----")) {
            str = str.split("-----")[2];
        }
        return str.replaceAll(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "");
    }

    public final void b(ByteArrayOutputStream byteArrayOutputStream, Cipher cipher, byte[] bArr, int i2, int i3, int i4) {
        synchronized (this.a) {
            while (i3 > 0) {
                int min = Math.min(i3, i4);
                byteArrayOutputStream.write(cipher.doFinal(bArr, i2, min));
                i2 += min;
                i3 -= min;
            }
        }
    }

    public void c(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr) {
        d(byteArrayOutputStream, bArr, 0, bArr.length);
    }

    public void d(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, int i2, int i3) {
        synchronized (this.a) {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, this.b);
                b(byteArrayOutputStream, cipher, bArr, i2, i3, 117);
            } catch (Throwable unused) {
            }
        }
    }

    public void e(byte[] bArr) {
        synchronized (this.a) {
            try {
                this.b = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArr));
            } catch (Throwable unused) {
            }
        }
    }
}
