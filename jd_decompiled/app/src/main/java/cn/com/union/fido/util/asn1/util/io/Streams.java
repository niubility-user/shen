package cn.com.union.fido.util.asn1.util.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class Streams {
    private static int BUFFER_SIZE = 512;

    public static void drain(InputStream inputStream) {
        int i2 = BUFFER_SIZE;
        do {
        } while (inputStream.read(new byte[i2], 0, i2) >= 0);
    }

    public static void pipeAll(InputStream inputStream, OutputStream outputStream) {
        int i2 = BUFFER_SIZE;
        byte[] bArr = new byte[i2];
        while (true) {
            int read = inputStream.read(bArr, 0, i2);
            if (read < 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static long pipeAllLimited(InputStream inputStream, long j2, OutputStream outputStream) {
        int i2 = BUFFER_SIZE;
        byte[] bArr = new byte[i2];
        long j3 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, i2);
            if (read < 0) {
                return j3;
            }
            j3 += read;
            if (j3 > j2) {
                throw new StreamOverflowException("Data Overflow");
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static byte[] readAll(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAll(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readAllLimited(InputStream inputStream, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAllLimited(inputStream, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static int readFully(InputStream inputStream, byte[] bArr) {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            int read = inputStream.read(bArr, i2 + i4, i3 - i4);
            if (read < 0) {
                break;
            }
            i4 += read;
        }
        return i4;
    }
}
