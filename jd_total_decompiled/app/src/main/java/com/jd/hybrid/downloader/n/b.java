package com.jd.hybrid.downloader.n;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class b implements a {
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final String a;

    public b(String str) {
        this.a = str;
    }

    public static char[] b(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = b;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return cArr;
    }

    private static String c(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (-1 != read) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    return new String(b(messageDigest.digest()));
                }
            }
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean d(File file, String str) {
        if (str == null) {
            return false;
        }
        return str.equalsIgnoreCase(c(file));
    }

    @Override // com.jd.hybrid.downloader.n.a
    public boolean a(File file) {
        return this.a.equalsIgnoreCase(c(file));
    }
}
