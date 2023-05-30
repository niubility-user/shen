package com.facebook.imageutils;

import android.util.Pair;
import androidx.core.view.MotionEventCompat;
import java.io.IOException;
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
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Pair<Integer, Integer> getSize(InputStream inputStream) {
        byte[] bArr = new byte[4];
        try {
            try {
                inputStream.read(bArr);
                if (!compare(bArr, "RIFF")) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return null;
                }
                getInt(inputStream);
                inputStream.read(bArr);
                if (!compare(bArr, "WEBP")) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                }
                inputStream.read(bArr);
                String header = getHeader(bArr);
                if (VP8_HEADER.equals(header)) {
                    Pair<Integer, Integer> vP8Dimension = getVP8Dimension(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return vP8Dimension;
                } else if (VP8L_HEADER.equals(header)) {
                    Pair<Integer, Integer> vP8LDimension = getVP8LDimension(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return vP8LDimension;
                } else if (VP8X_HEADER.equals(header)) {
                    Pair<Integer, Integer> vP8XDimension = getVP8XDimension(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return vP8XDimension;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            throw th;
        }
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
