package com.facebook.imageutils;

import android.util.Pair;
import androidx.core.view.MotionEventCompat;
import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpUtil {
    private static final String VP8L_HEADER = "VP8L";
    private static final String VP8X_HEADER = "VP8X";
    private static final String VP8_HEADER = "VP8 ";

    private WebpUtil() {
    }

    private static boolean compare(byte[] bArr, String str) {
        if (bArr.length != str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (str.charAt(i2) != bArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int get2BytesAsInt(InputStream inputStream) {
        return ((((byte) inputStream.read()) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (((byte) inputStream.read()) & 255);
    }

    private static byte getByte(InputStream inputStream) {
        return (byte) (inputStream.read() & 255);
    }

    private static String getHeader(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    private static int getInt(InputStream inputStream) {
        return ((((byte) inputStream.read()) << 24) & (-16777216)) | ((((byte) inputStream.read()) << 16) & 16711680) | ((((byte) inputStream.read()) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (((byte) inputStream.read()) & 255);
    }

    private static short getShort(InputStream inputStream) {
        return (short) (inputStream.read() & 255);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x007f, code lost:
        if (r3 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0088, code lost:
        if (r3 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x008a, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008e, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008f, code lost:
        r3.printStackTrace();
     */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getSize(java.io.InputStream r3) {
        /*
            r0 = 4
            byte[] r0 = new byte[r0]
            r1 = 0
            r3.read(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            java.lang.String r2 = "RIFF"
            boolean r2 = compare(r0, r2)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r2 != 0) goto L1a
            if (r3 == 0) goto L19
            r3.close()     // Catch: java.io.IOException -> L15
            goto L19
        L15:
            r3 = move-exception
            r3.printStackTrace()
        L19:
            return r1
        L1a:
            getInt(r3)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            r3.read(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            java.lang.String r2 = "WEBP"
            boolean r2 = compare(r0, r2)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r2 != 0) goto L33
            if (r3 == 0) goto L32
            r3.close()     // Catch: java.io.IOException -> L2e
            goto L32
        L2e:
            r3 = move-exception
            r3.printStackTrace()
        L32:
            return r1
        L33:
            r3.read(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            java.lang.String r0 = getHeader(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            java.lang.String r2 = "VP8 "
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r2 == 0) goto L51
            android.util.Pair r0 = getVP8Dimension(r3)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r3 == 0) goto L50
            r3.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r3 = move-exception
            r3.printStackTrace()
        L50:
            return r0
        L51:
            java.lang.String r2 = "VP8L"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r2 == 0) goto L68
            android.util.Pair r0 = getVP8LDimension(r3)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r3 == 0) goto L67
            r3.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r3 = move-exception
            r3.printStackTrace()
        L67:
            return r0
        L68:
            java.lang.String r2 = "VP8X"
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r0 == 0) goto L7f
            android.util.Pair r0 = getVP8XDimension(r3)     // Catch: java.lang.Throwable -> L82 java.io.IOException -> L84
            if (r3 == 0) goto L7e
            r3.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r3 = move-exception
            r3.printStackTrace()
        L7e:
            return r0
        L7f:
            if (r3 == 0) goto L92
            goto L8a
        L82:
            r0 = move-exception
            goto L93
        L84:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L82
            if (r3 == 0) goto L92
        L8a:
            r3.close()     // Catch: java.io.IOException -> L8e
            goto L92
        L8e:
            r3 = move-exception
            r3.printStackTrace()
        L92:
            return r1
        L93:
            if (r3 == 0) goto L9d
            r3.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r3 = move-exception
            r3.printStackTrace()
        L9d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageutils.WebpUtil.getSize(java.io.InputStream):android.util.Pair");
    }

    @Nullable
    private static Pair<Integer, Integer> getVP8Dimension(InputStream inputStream) {
        inputStream.skip(7L);
        short s = getShort(inputStream);
        short s2 = getShort(inputStream);
        short s3 = getShort(inputStream);
        if (s == 157 && s2 == 1 && s3 == 42) {
            return new Pair<>(Integer.valueOf(get2BytesAsInt(inputStream)), Integer.valueOf(get2BytesAsInt(inputStream)));
        }
        return null;
    }

    @Nullable
    private static Pair<Integer, Integer> getVP8LDimension(InputStream inputStream) {
        getInt(inputStream);
        if (getByte(inputStream) != 47) {
            return null;
        }
        int read = ((byte) inputStream.read()) & 255;
        return new Pair<>(Integer.valueOf(((((byte) inputStream.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf(((((((byte) inputStream.read()) & 255) & 15) << 10) | ((((byte) inputStream.read()) & 255) << 2) | ((read & 192) >> 6)) + 1));
    }

    private static Pair<Integer, Integer> getVP8XDimension(InputStream inputStream) {
        inputStream.skip(8L);
        return new Pair<>(Integer.valueOf(read3Bytes(inputStream) + 1), Integer.valueOf(read3Bytes(inputStream) + 1));
    }

    private static boolean isBitOne(byte b, int i2) {
        return ((b >> (i2 % 8)) & 1) == 1;
    }

    private static int read3Bytes(InputStream inputStream) {
        byte b = getByte(inputStream);
        return ((getByte(inputStream) << 16) & 16711680) | ((getByte(inputStream) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (b & 255);
    }
}
