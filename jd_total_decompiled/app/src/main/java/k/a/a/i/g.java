package k.a.a.i;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import k.a.a.f.i;

/* loaded from: classes11.dex */
public class g {
    public static byte[] a(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            bArr[i2] = (byte) cArr[i2];
        }
        return bArr;
    }

    public static boolean b(File file) throws k.a.a.c.a {
        if (file != null) {
            if (file.exists()) {
                if (file.isDirectory()) {
                    return true;
                }
                throw new k.a.a.c.a("output directory is not valid");
            } else if (file.mkdirs()) {
                return true;
            } else {
                throw new k.a.a.c.a("Cannot create output directories");
            }
        }
        throw new k.a.a.c.a("output path is null");
    }

    private static long c(long j2) {
        int i2 = (int) ((j2 << 1) & 62);
        int i3 = (int) ((j2 >> 5) & 63);
        int i4 = (int) ((j2 >> 11) & 31);
        int i5 = (int) ((j2 >> 16) & 31);
        int i6 = (int) (((j2 >> 25) & 127) + 1980);
        Calendar calendar = Calendar.getInstance();
        calendar.set(i6, (int) (((j2 >> 21) & 15) - 1), i5, i4, i3, i2);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static long d(long j2) {
        return c(j2) + (j2 >> 32);
    }

    public static k.a.a.f.o.c e(i iVar) {
        if (iVar.d() != k.a.a.f.o.c.AES_INTERNAL_ONLY) {
            return iVar.d();
        }
        if (iVar.b() != null) {
            return iVar.b().d();
        }
        throw new RuntimeException("AesExtraDataRecord not present in local header for aes encrypted data");
    }

    public static boolean f(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static int g(InputStream inputStream, byte[] bArr) throws IOException {
        int read = inputStream.read(bArr);
        if (read == bArr.length || (read = i(inputStream, bArr, read)) == bArr.length) {
            return read;
        }
        throw new IOException("Cannot read fully into byte buffer");
    }

    public static int h(InputStream inputStream, byte[] bArr, int i2, int i3) throws IOException {
        if (i2 >= 0) {
            if (i3 >= 0) {
                int i4 = 0;
                if (i3 == 0) {
                    return 0;
                }
                if (i2 + i3 <= bArr.length) {
                    while (i4 != i3) {
                        int read = inputStream.read(bArr, i2 + i4, i3 - i4);
                        if (read == -1) {
                            if (i4 == 0) {
                                return -1;
                            }
                            return i4;
                        }
                        i4 += read;
                    }
                    return i4;
                }
                throw new IllegalArgumentException("Length greater than buffer size");
            }
            throw new IllegalArgumentException("Negative length");
        }
        throw new IllegalArgumentException("Negative offset");
    }

    private static int i(InputStream inputStream, byte[] bArr, int i2) throws IOException {
        int length = bArr.length - i2;
        int i3 = 0;
        for (int i4 = 1; i2 < bArr.length && i3 != -1 && i4 < 15; i4++) {
            i3 = inputStream.read(bArr, i2, length);
            if (i3 > 0) {
                i2 += i3;
                length -= i3;
            }
        }
        return i2;
    }
}
