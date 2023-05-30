package cn.com.union.fido.util;

import java.math.BigInteger;

/* loaded from: classes.dex */
public class BigIntegerUtil {
    public static byte[] asUnsigned32ByteArray(BigInteger bigInteger) {
        return asUnsignedNByteArray(bigInteger, 32);
    }

    public static byte[] asUnsignedNByteArray(BigInteger bigInteger, int i2) {
        if (bigInteger == null) {
            return null;
        }
        byte[] bArr = new byte[i2];
        int length = bigInteger.toByteArray().length;
        int i3 = i2 + 1;
        if (length > i3) {
            return null;
        }
        if (length != i3) {
            System.arraycopy(bigInteger.toByteArray(), 0, bArr, i2 - length, length);
            return bArr;
        } else if (bigInteger.toByteArray()[0] != 0) {
            return null;
        } else {
            System.arraycopy(bigInteger.toByteArray(), 1, bArr, 0, i2);
            return bArr;
        }
    }

    public static BigInteger toPositiveInteger(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr[0] < 0) {
            int length = bArr.length + 1;
            byte[] bArr2 = new byte[length];
            bArr2[0] = 0;
            System.arraycopy(bArr, 0, bArr2, 1, length - 1);
            bArr = bArr2;
        }
        return new BigInteger(bArr);
    }
}
