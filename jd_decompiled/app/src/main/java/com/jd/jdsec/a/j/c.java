package com.jd.jdsec.a.j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class c {
    private int a = 4;

    public float a(String str) {
        try {
            byte[] d = d(str);
            if (b(d)) {
                int length = d.length;
                int i2 = 0;
                float f2 = 0.0f;
                while (true) {
                    int i3 = this.a;
                    if (i2 >= length / i3) {
                        return f2;
                    }
                    int i4 = i2 * i3;
                    i2++;
                    byte[] copyOfRange = Arrays.copyOfRange(d, i4, i3 * i2);
                    e(copyOfRange);
                    f2 += c(copyOfRange);
                }
            } else {
                throw new IllegalArgumentException("The length of encode byte is not multiples of 4 bytes");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public boolean b(byte[] bArr) {
        return bArr.length % this.a == 0;
    }

    public float c(byte[] bArr) throws IllegalArgumentException {
        if (bArr.length == this.a) {
            return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        }
        throw new IllegalArgumentException("The length of byte array is incompatible");
    }

    public byte[] d(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] e(byte[] bArr) throws IllegalArgumentException {
        if (bArr.length == this.a) {
            if (((bArr[3] >> 6) & 1) == 1) {
                bArr[3] = (byte) (bArr[3] & 193);
            } else {
                bArr[3] = (byte) (bArr[3] | 62);
            }
            return bArr;
        }
        throw new IllegalArgumentException("The length of byte array is incompatible");
    }
}
