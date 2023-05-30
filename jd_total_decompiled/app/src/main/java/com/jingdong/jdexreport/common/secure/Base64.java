package com.jingdong.jdexreport.common.secure;

import androidx.core.view.MotionEventCompat;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN = 61;
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _ORDERED_ALPHABET = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};
    private static final byte[] _ORDERED_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends ObjectInputStream {
        final /* synthetic */ ClassLoader a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(InputStream inputStream, ClassLoader classLoader) throws IOException {
            super(inputStream);
            this.a = classLoader;
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.a);
            return cls == null ? super.resolveClass(objectStreamClass) : cls;
        }
    }

    private Base64() {
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        return decode(bArr, 0, bArr.length, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int decode4to3(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6;
        if (bArr != null) {
            if (bArr2 != null) {
                if (i2 >= 0 && (i5 = i2 + 3) < bArr.length) {
                    if (i3 >= 0 && (i6 = i3 + 2) < bArr2.length) {
                        byte[] decodabet = getDecodabet(i4);
                        int i7 = i2 + 2;
                        if (bArr[i7] == 61) {
                            bArr2[i3] = (byte) ((((decodabet[bArr[i2 + 1]] & 255) << 12) | ((decodabet[bArr[i2]] & 255) << 18)) >>> 16);
                            return 1;
                        } else if (bArr[i5] == 61) {
                            int i8 = ((decodabet[bArr[i7]] & 255) << 6) | ((decodabet[bArr[i2 + 1]] & 255) << 12) | ((decodabet[bArr[i2]] & 255) << 18);
                            bArr2[i3] = (byte) (i8 >>> 16);
                            bArr2[i3 + 1] = (byte) (i8 >>> 8);
                            return 2;
                        } else {
                            int i9 = (decodabet[bArr[i5]] & 255) | ((decodabet[bArr[i2 + 1]] & 255) << 12) | ((decodabet[bArr[i2]] & 255) << 18) | ((decodabet[bArr[i7]] & 255) << 6);
                            bArr2[i3] = (byte) (i9 >> 16);
                            bArr2[i3 + 1] = (byte) (i9 >> 8);
                            bArr2[i6] = (byte) i9;
                            return 3;
                        }
                    }
                    throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i3)));
                }
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i2)));
            }
            throw new NullPointerException("Destination array was null.");
        }
        throw new NullPointerException("Source array was null.");
    }

    public static void decodeFileToFile(String str, String str2) throws IOException {
        byte[] decodeFromFile = decodeFromFile(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    bufferedOutputStream2.write(decodeFromFile);
                    try {
                        bufferedOutputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e2) {
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (IOException e3) {
                throw e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static byte[] decodeFromFile(String str) throws IOException {
        b bVar = null;
        try {
            try {
                File file = new File(str);
                if (file.length() <= 2147483647L) {
                    byte[] bArr = new byte[(int) file.length()];
                    b bVar2 = new b(new BufferedInputStream(new FileInputStream(file)), 0);
                    int i2 = 0;
                    while (true) {
                        try {
                            int read = bVar2.read(bArr, i2, 4096);
                            if (read < 0) {
                                break;
                            }
                            i2 += read;
                        } catch (IOException e2) {
                            throw e2;
                        } catch (Throwable th) {
                            th = th;
                            bVar = bVar2;
                            try {
                                bVar.close();
                            } catch (Exception unused) {
                            }
                            throw th;
                        }
                    }
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    try {
                        bVar2.close();
                    } catch (Exception unused2) {
                    }
                    return bArr2;
                }
                throw new IOException("File is too big for this convenience method (" + file.length() + " bytes).");
            } catch (IOException e3) {
                throw e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void decodeToFile(String str, String str2) throws IOException {
        c cVar = null;
        try {
            try {
                c cVar2 = new c(new FileOutputStream(str2), 0);
                try {
                    cVar2.write(str.getBytes("US-ASCII"));
                    try {
                        cVar2.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e2) {
                } catch (Throwable th) {
                    th = th;
                    cVar = cVar2;
                    try {
                        cVar.close();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static Object decodeToObject(String str) throws IOException, ClassNotFoundException {
        return decodeToObject(str, 0, null);
    }

    public static void encode(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            encode3to4(bArr2, bArr, min, 0);
            byteBuffer2.put(bArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, byte[] bArr2, int i2, int i3) {
        encode3to4(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    public static String encodeBytes(byte[] bArr) {
        try {
            return encodeBytes(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static byte[] encodeBytesToBytes(byte[] bArr) {
        try {
            return encodeBytesToBytes(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static void encodeFileToFile(String str, String str2) throws IOException {
        String encodeFromFile = encodeFromFile(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    bufferedOutputStream2.write(encodeFromFile.getBytes("US-ASCII"));
                    try {
                        bufferedOutputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e2) {
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (IOException e3) {
                throw e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String encodeFromFile(String str) throws IOException {
        b bVar = null;
        try {
            try {
                File file = new File(str);
                double length = file.length();
                Double.isNaN(length);
                byte[] bArr = new byte[Math.max((int) ((length * 1.4d) + 1.0d), 40)];
                b bVar2 = new b(new BufferedInputStream(new FileInputStream(file)), 1);
                int i2 = 0;
                while (true) {
                    try {
                        int read = bVar2.read(bArr, i2, 4096);
                        if (read < 0) {
                            break;
                        }
                        i2 += read;
                    } catch (IOException e2) {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        bVar = bVar2;
                        try {
                            bVar.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                }
                String str2 = new String(bArr, 0, i2, "US-ASCII");
                try {
                    bVar2.close();
                } catch (Exception unused2) {
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static String encodeObject(Serializable serializable) throws IOException {
        return encodeObject(serializable, 0);
    }

    public static void encodeToFile(byte[] bArr, String str) throws IOException {
        if (bArr != null) {
            c cVar = null;
            try {
                try {
                    c cVar2 = new c(new FileOutputStream(str), 1);
                    try {
                        cVar2.write(bArr);
                        try {
                            cVar2.close();
                        } catch (Exception unused) {
                        }
                    } catch (IOException e2) {
                    } catch (Throwable th) {
                        th = th;
                        cVar = cVar2;
                        try {
                            cVar.close();
                        } catch (Exception unused2) {
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    throw e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new NullPointerException("Data to encode was null.");
        }
    }

    private static final byte[] getAlphabet(int i2) {
        if ((i2 & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((i2 & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDecodabet(int i2) {
        if ((i2 & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((i2 & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    public static byte[] decode(byte[] bArr, int i2, int i3, int i4) throws IOException {
        int i5;
        if (bArr != null) {
            if (i2 < 0 || (i5 = i2 + i3) > bArr.length) {
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            if (i3 == 0) {
                return new byte[0];
            }
            if (i3 >= 4) {
                byte[] decodabet = getDecodabet(i4);
                byte[] bArr2 = new byte[(i3 * 3) / 4];
                byte[] bArr3 = new byte[4];
                int i6 = 0;
                int i7 = 0;
                while (i2 < i5) {
                    byte b2 = decodabet[bArr[i2] & 255];
                    if (b2 < -5) {
                        throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                    }
                    if (b2 >= -1) {
                        int i8 = i6 + 1;
                        bArr3[i6] = bArr[i2];
                        if (i8 > 3) {
                            i7 += decode4to3(bArr3, 0, bArr2, i7, i4);
                            if (bArr[i2] == 61) {
                                break;
                            }
                            i6 = 0;
                        } else {
                            i6 = i8;
                        }
                    }
                    i2++;
                }
                byte[] bArr4 = new byte[i7];
                System.arraycopy(bArr2, 0, bArr4, 0, i7);
                return bArr4;
            }
            throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i3);
        }
        throw new NullPointerException("Cannot decode null source array.");
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0032: MOVE (r2 I:??[OBJECT, ARRAY]) = (r0 I:??[OBJECT, ARRAY]), block:B:24:0x0031 */
    public static Object decodeToObject(String str, int i2, ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream aVar;
        byte[] decode = decode(str, i2);
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(decode);
                try {
                    if (classLoader == null) {
                        aVar = new ObjectInputStream(byteArrayInputStream3);
                    } else {
                        aVar = new a(byteArrayInputStream3, classLoader);
                    }
                    ObjectInputStream objectInputStream2 = aVar;
                    Object readObject = objectInputStream2.readObject();
                    try {
                        byteArrayInputStream3.close();
                    } catch (Exception unused) {
                    }
                    try {
                        objectInputStream2.close();
                    } catch (Exception unused2) {
                    }
                    return readObject;
                } catch (IOException e2) {
                    throw e2;
                } catch (ClassNotFoundException e3) {
                    throw e3;
                }
            } catch (IOException e4) {
                throw e4;
            } catch (ClassNotFoundException e5) {
                throw e5;
            } catch (Throwable th) {
                th = th;
                objectInputStream = null;
                try {
                    byteArrayInputStream2.close();
                } catch (Exception unused3) {
                }
                try {
                    objectInputStream.close();
                } catch (Exception unused4) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            objectInputStream = null;
            byteArrayInputStream2 = byteArrayInputStream;
            byteArrayInputStream2.close();
            objectInputStream.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] alphabet = getAlphabet(i5);
        int i6 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = alphabet[i6 >>> 18];
            bArr2[i4 + 1] = alphabet[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = EQUALS_SIGN;
            bArr2[i4 + 3] = EQUALS_SIGN;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = alphabet[i6 >>> 18];
            bArr2[i4 + 1] = alphabet[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = alphabet[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = EQUALS_SIGN;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = alphabet[i6 >>> 18];
            bArr2[i4 + 1] = alphabet[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = alphabet[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = alphabet[i6 & 63];
            return bArr2;
        }
    }

    public static String encodeBytes(byte[] bArr, int i2) throws IOException {
        return encodeBytes(bArr, 0, bArr.length, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    public static byte[] encodeBytesToBytes(byte[] bArr, int i2, int i3, int i4) throws IOException {
        c cVar;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i2);
        } else if (i3 >= 0) {
            int i5 = i2 + i3;
            ?? length = bArr.length;
            if (i5 > length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
            }
            if ((i4 & 2) != 0) {
                GZIPOutputStream gZIPOutputStream = null;
                try {
                    try {
                        length = new ByteArrayOutputStream();
                        try {
                            cVar = new c(length, i4 | 1);
                            try {
                                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(cVar);
                                try {
                                    gZIPOutputStream2.write(bArr, i2, i3);
                                    gZIPOutputStream2.close();
                                    try {
                                        gZIPOutputStream2.close();
                                    } catch (Exception unused) {
                                    }
                                    try {
                                        cVar.close();
                                    } catch (Exception unused2) {
                                    }
                                    try {
                                        length.close();
                                    } catch (Exception unused3) {
                                    }
                                    return length.toByteArray();
                                } catch (IOException e2) {
                                    throw e2;
                                } catch (Throwable th) {
                                    th = th;
                                    gZIPOutputStream = gZIPOutputStream2;
                                    try {
                                        gZIPOutputStream.close();
                                    } catch (Exception unused4) {
                                    }
                                    try {
                                        cVar.close();
                                    } catch (Exception unused5) {
                                    }
                                    try {
                                        length.close();
                                    } catch (Exception unused6) {
                                    }
                                    throw th;
                                }
                            } catch (IOException e3) {
                                throw e3;
                            }
                        } catch (IOException e4) {
                            throw e4;
                        } catch (Throwable th2) {
                            th = th2;
                            cVar = null;
                        }
                    } catch (IOException e5) {
                        throw e5;
                    } catch (Throwable th3) {
                        th = th3;
                        length = 0;
                        cVar = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } else {
                boolean z = (i4 & 8) != 0;
                int i6 = ((i3 / 3) * 4) + (i3 % 3 > 0 ? 4 : 0);
                if (z) {
                    i6 += i6 / 76;
                }
                int i7 = i6;
                byte[] bArr2 = new byte[i7];
                int i8 = i3 - 2;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                while (i9 < i8) {
                    int i12 = i9;
                    encode3to4(bArr, i9 + i2, 3, bArr2, i10, i4);
                    int i13 = i11 + 4;
                    if (!z || i13 < 76) {
                        i11 = i13;
                    } else {
                        bArr2[i10 + 4] = 10;
                        i10++;
                        i11 = 0;
                    }
                    i9 = i12 + 3;
                    i10 += 4;
                }
                int i14 = i9;
                if (i14 < i3) {
                    encode3to4(bArr, i14 + i2, i3 - i14, bArr2, i10, i4);
                    i10 += 4;
                }
                int i15 = i10;
                if (i15 <= i7 - 1) {
                    byte[] bArr3 = new byte[i15];
                    System.arraycopy(bArr2, 0, bArr3, 0, i15);
                    return bArr3;
                }
                return bArr2;
            }
        } else {
            throw new IllegalArgumentException("Cannot have length offset: " + i3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v21, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    public static String encodeObject(Serializable serializable, int i2) throws IOException {
        ?? r6;
        ByteArrayOutputStream byteArrayOutputStream;
        c cVar;
        ObjectOutputStream objectOutputStream;
        if (serializable != null) {
            ObjectOutputStream objectOutputStream2 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    cVar = new c(byteArrayOutputStream, i2 | 1);
                    try {
                        if ((i2 & 2) != 0) {
                            r6 = new GZIPOutputStream(cVar);
                            try {
                                try {
                                    objectOutputStream2 = new ObjectOutputStream(r6);
                                    r6 = r6;
                                } catch (Throwable th) {
                                    th = th;
                                    try {
                                        objectOutputStream2.close();
                                    } catch (Exception unused) {
                                    }
                                    try {
                                        r6.close();
                                    } catch (Exception unused2) {
                                    }
                                    try {
                                        cVar.close();
                                    } catch (Exception unused3) {
                                    }
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception unused4) {
                                    }
                                    throw th;
                                }
                            } catch (IOException e2) {
                                e = e2;
                                ObjectOutputStream objectOutputStream3 = objectOutputStream2;
                                objectOutputStream2 = r6;
                                objectOutputStream = objectOutputStream3;
                                try {
                                    throw e;
                                } catch (Throwable th2) {
                                    th = th2;
                                    ObjectOutputStream objectOutputStream4 = objectOutputStream2;
                                    objectOutputStream2 = objectOutputStream;
                                    r6 = objectOutputStream4;
                                    objectOutputStream2.close();
                                    r6.close();
                                    cVar.close();
                                    byteArrayOutputStream.close();
                                    throw th;
                                }
                            }
                        } else {
                            objectOutputStream2 = new ObjectOutputStream(cVar);
                            r6 = 0;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        objectOutputStream = objectOutputStream2;
                    } catch (Throwable th3) {
                        th = th3;
                        r6 = objectOutputStream2;
                    }
                } catch (IOException e4) {
                    e = e4;
                    objectOutputStream = null;
                    cVar = null;
                } catch (Throwable th4) {
                    th = th4;
                    r6 = 0;
                    cVar = null;
                }
            } catch (IOException e5) {
                e = e5;
                objectOutputStream = null;
                byteArrayOutputStream = null;
                cVar = null;
            } catch (Throwable th5) {
                th = th5;
                r6 = 0;
                byteArrayOutputStream = null;
                cVar = null;
            }
            try {
                objectOutputStream2.writeObject(serializable);
                try {
                    objectOutputStream2.close();
                } catch (Exception unused5) {
                }
                try {
                    r6.close();
                } catch (Exception unused6) {
                }
                try {
                    cVar.close();
                } catch (Exception unused7) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused8) {
                }
                try {
                    return new String(byteArrayOutputStream.toByteArray(), "US-ASCII");
                } catch (UnsupportedEncodingException unused9) {
                    return new String(byteArrayOutputStream.toByteArray());
                }
            } catch (Throwable th6) {
                th = th6;
                ObjectOutputStream objectOutputStream5 = objectOutputStream2;
                objectOutputStream2 = r6;
                objectOutputStream = objectOutputStream5;
                ObjectOutputStream objectOutputStream42 = objectOutputStream2;
                objectOutputStream2 = objectOutputStream;
                r6 = objectOutputStream42;
                objectOutputStream2.close();
                r6.close();
                cVar.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
        throw new NullPointerException("Cannot serialize a null object.");
    }

    public static String encodeBytes(byte[] bArr, int i2, int i3) {
        try {
            return encodeBytes(bArr, i2, i3, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bArr, int i2, int i3, int i4) throws IOException {
        byte[] encodeBytesToBytes = encodeBytesToBytes(bArr, i2, i3, i4);
        try {
            return new String(encodeBytesToBytes, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(encodeBytesToBytes);
        }
    }

    public static void encode(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            encode3to4(bArr2, bArr, min, 0);
            for (int i2 = 0; i2 < 4; i2++) {
                charBuffer.put((char) (bArr2[i2] & 255));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c extends FilterOutputStream {
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private byte[] f12588c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f12589e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f12590f;

        /* renamed from: g  reason: collision with root package name */
        private byte[] f12591g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f12592h;

        /* renamed from: i  reason: collision with root package name */
        private int f12593i;

        /* renamed from: j  reason: collision with root package name */
        private byte[] f12594j;

        public c(OutputStream outputStream, int i2) {
            super(outputStream);
            this.f12590f = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.a = z;
            int i3 = z ? 3 : 4;
            this.d = i3;
            this.f12588c = new byte[i3];
            this.b = 0;
            this.f12589e = 0;
            this.f12592h = false;
            this.f12591g = new byte[4];
            this.f12593i = i2;
            this.f12594j = Base64.getDecodabet(i2);
        }

        public void a() throws IOException {
            int i2 = this.b;
            if (i2 > 0) {
                if (this.a) {
                    ((FilterOutputStream) this).out.write(Base64.encode3to4(this.f12591g, this.f12588c, i2, this.f12593i));
                    this.b = 0;
                    return;
                }
                throw new IOException("Base64 input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a();
            super.close();
            this.f12588c = null;
            ((FilterOutputStream) this).out = null;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) throws IOException {
            if (this.f12592h) {
                ((FilterOutputStream) this).out.write(i2);
            } else if (this.a) {
                byte[] bArr = this.f12588c;
                int i3 = this.b;
                int i4 = i3 + 1;
                this.b = i4;
                bArr[i3] = (byte) i2;
                int i5 = this.d;
                if (i4 >= i5) {
                    ((FilterOutputStream) this).out.write(Base64.encode3to4(this.f12591g, bArr, i5, this.f12593i));
                    int i6 = this.f12589e + 4;
                    this.f12589e = i6;
                    if (this.f12590f && i6 >= 76) {
                        ((FilterOutputStream) this).out.write(10);
                        this.f12589e = 0;
                    }
                    this.b = 0;
                }
            } else {
                byte[] bArr2 = this.f12594j;
                int i7 = i2 & 127;
                if (bArr2[i7] > -5) {
                    byte[] bArr3 = this.f12588c;
                    int i8 = this.b;
                    int i9 = i8 + 1;
                    this.b = i9;
                    bArr3[i8] = (byte) i2;
                    if (i9 >= this.d) {
                        ((FilterOutputStream) this).out.write(this.f12591g, 0, Base64.decode4to3(bArr3, 0, this.f12591g, 0, this.f12593i));
                        this.b = 0;
                    }
                } else if (bArr2[i7] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.f12592h) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }

    public static byte[] decode(String str) throws IOException {
        return decode(str, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x007f -> B:77:0x007f). Please submit an issue!!! */
    public static byte[] decode(String str, int i2) throws IOException {
        byte[] bytes;
        ?? length;
        ?? r6;
        ByteArrayInputStream byteArrayInputStream;
        ?? r4;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream2;
        ByteArrayOutputStream byteArrayOutputStream2;
        if (str != null) {
            try {
                bytes = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                bytes = str.getBytes();
            }
            byte[] decode = decode(bytes, 0, bytes.length, i2);
            boolean z = (i2 & 4) != 0;
            if (decode != null && (length = decode.length) >= 4 && !z && 35615 == ((decode[0] & 255) | ((decode[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
                byte[] bArr = new byte[2048];
                ByteArrayOutputStream byteArrayOutputStream3 = null;
                try {
                    try {
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                    } catch (IOException e2) {
                        e = e2;
                        length = 0;
                        r4 = 0;
                    } catch (Throwable th) {
                        th = th;
                        r6 = 0;
                        byteArrayInputStream = null;
                        try {
                            byteArrayOutputStream3.close();
                        } catch (Exception unused2) {
                        }
                        try {
                            r6.close();
                        } catch (Exception unused3) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception unused4) {
                        }
                        throw th;
                    }
                    try {
                        length = new ByteArrayInputStream(decode);
                    } catch (IOException e3) {
                        e = e3;
                        length = 0;
                        r4 = 0;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = null;
                        byteArrayInputStream2 = null;
                        byteArrayOutputStream3 = byteArrayOutputStream2;
                        byteArrayInputStream = byteArrayInputStream2;
                        r6 = byteArrayOutputStream;
                        byteArrayOutputStream3.close();
                        r6.close();
                        byteArrayInputStream.close();
                        throw th;
                    }
                    try {
                        r4 = new GZIPInputStream(length);
                        while (true) {
                            try {
                                int read = r4.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, read);
                            } catch (IOException e4) {
                                e = e4;
                                byteArrayOutputStream3 = byteArrayOutputStream2;
                                length = length;
                                r4 = r4;
                                try {
                                    e.printStackTrace();
                                    try {
                                        byteArrayOutputStream3.close();
                                    } catch (Exception unused5) {
                                    }
                                    r4.close();
                                    try {
                                        length.close();
                                    } catch (Exception unused6) {
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    byteArrayOutputStream2 = byteArrayOutputStream3;
                                    byteArrayOutputStream3 = r4;
                                    byteArrayOutputStream = byteArrayOutputStream3;
                                    byteArrayInputStream2 = length;
                                    byteArrayOutputStream3 = byteArrayOutputStream2;
                                    byteArrayInputStream = byteArrayInputStream2;
                                    r6 = byteArrayOutputStream;
                                    byteArrayOutputStream3.close();
                                    r6.close();
                                    byteArrayInputStream.close();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                byteArrayOutputStream3 = r4;
                                byteArrayOutputStream = byteArrayOutputStream3;
                                byteArrayInputStream2 = length;
                                byteArrayOutputStream3 = byteArrayOutputStream2;
                                byteArrayInputStream = byteArrayInputStream2;
                                r6 = byteArrayOutputStream;
                                byteArrayOutputStream3.close();
                                r6.close();
                                byteArrayInputStream.close();
                                throw th;
                            }
                        }
                        byteArrayOutputStream2.toByteArray();
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception unused7) {
                        }
                        r4.close();
                    } catch (IOException e5) {
                        e = e5;
                        r4 = 0;
                    } catch (Throwable th5) {
                        th = th5;
                        byteArrayOutputStream = byteArrayOutputStream3;
                        byteArrayInputStream2 = length;
                        byteArrayOutputStream3 = byteArrayOutputStream2;
                        byteArrayInputStream = byteArrayInputStream2;
                        r6 = byteArrayOutputStream;
                        byteArrayOutputStream3.close();
                        r6.close();
                        byteArrayInputStream.close();
                        throw th;
                    }
                } catch (Exception unused8) {
                    length.close();
                }
            }
            return decode;
        }
        throw new NullPointerException("Input string was null.");
    }

    /* loaded from: classes.dex */
    public static class b extends FilterInputStream {
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private byte[] f12582c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f12583e;

        /* renamed from: f  reason: collision with root package name */
        private int f12584f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f12585g;

        /* renamed from: h  reason: collision with root package name */
        private int f12586h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f12587i;

        public b(InputStream inputStream, int i2) {
            super(inputStream);
            this.f12586h = i2;
            this.f12585g = (i2 & 8) > 0;
            boolean z = (i2 & 1) > 0;
            this.a = z;
            int i3 = z ? 4 : 3;
            this.d = i3;
            this.f12582c = new byte[i3];
            this.b = -1;
            this.f12584f = 0;
            this.f12587i = Base64.getDecodabet(i2);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read;
            if (this.b < 0) {
                if (this.a) {
                    byte[] bArr = new byte[3];
                    int i2 = 0;
                    for (int i3 = 0; i3 < 3; i3++) {
                        int read2 = ((FilterInputStream) this).in.read();
                        if (read2 < 0) {
                            break;
                        }
                        bArr[i3] = (byte) read2;
                        i2++;
                    }
                    if (i2 <= 0) {
                        return -1;
                    }
                    Base64.encode3to4(bArr, 0, i2, this.f12582c, 0, this.f12586h);
                    this.b = 0;
                    this.f12583e = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i4 = 0;
                    while (i4 < 4) {
                        do {
                            read = ((FilterInputStream) this).in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.f12587i[read & 127] <= -5);
                        if (read < 0) {
                            break;
                        }
                        bArr2[i4] = (byte) read;
                        i4++;
                    }
                    if (i4 != 4) {
                        if (i4 == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                    this.f12583e = Base64.decode4to3(bArr2, 0, this.f12582c, 0, this.f12586h);
                    this.b = 0;
                }
            }
            int i5 = this.b;
            if (i5 >= 0) {
                if (i5 >= this.f12583e) {
                    return -1;
                }
                if (this.a && this.f12585g && this.f12584f >= 76) {
                    this.f12584f = 0;
                    return 10;
                }
                this.f12584f++;
                byte[] bArr3 = this.f12582c;
                int i6 = i5 + 1;
                this.b = i6;
                byte b = bArr3[i5];
                if (i6 >= this.d) {
                    this.b = -1;
                }
                return b & 255;
            }
            throw new IOException("Error in Base64 code reading stream.");
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i2 + i4] = (byte) read;
                    i4++;
                } else if (i4 == 0) {
                    return -1;
                }
            }
            return i4;
        }
    }
}
