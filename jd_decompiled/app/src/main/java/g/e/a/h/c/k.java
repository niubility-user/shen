package g.e.a.h.c;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
class k {
    public static SecretKey a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i2) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (bArr.length == 16 && bArr2.length == 16 && bArr3.length == 16) {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(a.c(e(bArr, bArr2, bArr3)).toCharArray(), bArr4, i2, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES);
        }
        throw new IllegalArgumentException("invalid data for generating the key.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] b(SecretKey secretKey, byte[] bArr) throws GeneralSecurityException {
        if (secretKey == null || bArr == null) {
            throw new NullPointerException("key or cipherText must not be null.");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, 17);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(2, secretKey, new IvParameterSpec(copyOfRange));
        return cipher.doFinal(bArr, copyOfRange.length + 1, (bArr.length - copyOfRange.length) - 1);
    }

    private static byte[] c(byte[] bArr, int i2) {
        if (bArr != null) {
            for (int i3 = 0; i3 < bArr.length; i3++) {
                if (i2 < 0) {
                    bArr[i3] = (byte) (bArr[i3] << (-i2));
                } else {
                    bArr[i3] = (byte) (bArr[i3] >> i2);
                }
            }
            return bArr;
        }
        throw new NullPointerException("bytes must not be null.");
    }

    private static byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            throw new NullPointerException("left or right must not be null.");
        }
        if (bArr.length == bArr2.length) {
            byte[] bArr3 = new byte[bArr.length];
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            }
            return bArr3;
        }
        throw new IllegalArgumentException("left and right must be the same length.");
    }

    public static byte[] e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        c(bArr, -4);
        byte[] d = d(bArr, bArr2);
        c(d, 6);
        return d(d, bArr3);
    }
}
