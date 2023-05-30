package com.jingdong.app.mall.bundle.jdbrotli;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes2.dex */
public class Utils {
    private static final byte[] BYTE_ZEROES = new byte[1024];
    private static final int[] INT_ZEROES = new int[1024];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void closeInput(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyBytesWithin(byte[] bArr, int i2, int i3, int i4) {
        System.arraycopy(bArr, i3, bArr, i2, i4 - i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fillBytesWithZeroes(byte[] bArr, int i2, int i3) {
        while (i2 < i3) {
            int min = Math.min(i2 + 1024, i3) - i2;
            System.arraycopy(BYTE_ZEROES, 0, bArr, i2, min);
            i2 += min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fillIntsWithZeroes(int[] iArr, int i2, int i3) {
        while (i2 < i3) {
            int min = Math.min(i2 + 1024, i3) - i2;
            System.arraycopy(INT_ZEROES, 0, iArr, i2, min);
            i2 += min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void flipBuffer(Buffer buffer) {
        buffer.flip();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLogBintness() {
        return Boolean.parseBoolean(System.getProperty("BROTLI_32_BIT_CPU")) ? 5 : 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int isDebugMode() {
        return Boolean.parseBoolean(System.getProperty("BROTLI_ENABLE_ASSERTS")) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInput(InputStream inputStream, byte[] bArr, int i2, int i3) {
        try {
            return inputStream.read(bArr, i2, i3);
        } catch (IOException e2) {
            throw new BrotliRuntimeException("Failed to read input", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] toUsAsciiBytes(String str) {
        try {
            return str.getBytes(CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}
