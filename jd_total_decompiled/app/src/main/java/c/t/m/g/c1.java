package c.t.m.g;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class c1 {
    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes("UTF-8"));
            return b(messageDigest.digest(), "");
        } catch (Exception unused) {
            return str;
        }
    }

    public static String b(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toHexString(b & 255));
            sb.append(str);
        }
        return sb.toString();
    }

    public static byte[] c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        try {
            deflaterOutputStream.write(bArr, 0, bArr.length);
            deflaterOutputStream.finish();
            deflaterOutputStream.flush();
            deflaterOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] d(byte[] bArr) {
        int read;
        if (bArr == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[0];
        byte[] bArr3 = new byte[1024];
        int i2 = 0;
        do {
            try {
                read = inflaterInputStream.read(bArr3);
                if (read > 0) {
                    i2 += read;
                    byte[] bArr4 = new byte[i2];
                    System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
                    System.arraycopy(bArr3, 0, bArr4, bArr2.length, read);
                    bArr2 = bArr4;
                }
            } catch (IOException | Exception unused) {
                return null;
            }
        } while (read > 0);
        byteArrayInputStream.close();
        inflaterInputStream.close();
        return bArr2;
    }
}
