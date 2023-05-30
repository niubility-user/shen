package com.jingdong.jdsdk.secure;

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
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes14.dex */
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
    private static final String TAG = "Base64";
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
    /* loaded from: classes14.dex */
    public class a extends ObjectInputStream {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ClassLoader f12913g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(InputStream inputStream, ClassLoader classLoader) throws IOException {
            super(inputStream);
            this.f12913g = classLoader;
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.f12913g);
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
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception unused2) {
                        }
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
                            if (bVar != null) {
                                try {
                                    bVar.close();
                                } catch (Exception unused) {
                                }
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
                    if (cVar != null) {
                        try {
                            cVar.close();
                        } catch (Exception unused2) {
                        }
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
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception unused2) {
                        }
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
                        if (bVar != null) {
                            try {
                                bVar.close();
                            } catch (Exception unused) {
                            }
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
                        if (cVar != null) {
                            try {
                                cVar.close();
                            } catch (Exception unused2) {
                            }
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    public static Object decodeToObject(String str, int i2, ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream aVar;
        byte[] decode = decode(str, i2);
        ByteArrayInputStream byteArrayInputStream2 = null;
        r2 = null;
        r2 = null;
        ObjectInputStream objectInputStream = null;
        byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(decode);
            } catch (IOException e2) {
                e = e2;
            } catch (ClassNotFoundException e3) {
                e = e3;
            } catch (Throwable th) {
                th = th;
                classLoader = 0;
            }
            try {
                if (classLoader == 0) {
                    aVar = new ObjectInputStream(byteArrayInputStream);
                } else {
                    aVar = new a(byteArrayInputStream, classLoader);
                }
                objectInputStream = aVar;
                Object readObject = objectInputStream.readObject();
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    objectInputStream.close();
                } catch (Exception unused2) {
                }
                return readObject;
            } catch (IOException e4) {
                e = e4;
                throw e;
            } catch (ClassNotFoundException e5) {
                e = e5;
                throw e;
            } catch (Throwable th2) {
                th = th2;
                classLoader = objectInputStream;
                byteArrayInputStream2 = byteArrayInputStream;
                if (byteArrayInputStream2 != null) {
                    try {
                        byteArrayInputStream2.close();
                    } catch (Exception unused3) {
                    }
                }
                if (classLoader != 0) {
                    try {
                        classLoader.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
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
    public static byte[] encodeBytesToBytes(byte[] bArr, int i2, int i3, int i4) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        c cVar;
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i2);
        } else if (i3 >= 0) {
            if (i2 + i3 > bArr.length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
            }
            if ((i4 & 2) != 0) {
                GZIPOutputStream gZIPOutputStream2 = null;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        cVar = new c(byteArrayOutputStream, i4 | 1);
                    } catch (IOException e2) {
                        e = e2;
                        cVar = null;
                        gZIPOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        cVar = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    cVar = null;
                    gZIPOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = 0;
                    cVar = null;
                }
                try {
                    gZIPOutputStream = new GZIPOutputStream(cVar);
                    try {
                        gZIPOutputStream.write(bArr, i2, i3);
                        gZIPOutputStream.close();
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused) {
                        }
                        try {
                            cVar.close();
                        } catch (Exception unused2) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused3) {
                        }
                        return byteArrayOutputStream.toByteArray();
                    } catch (IOException e4) {
                        e = e4;
                        gZIPOutputStream2 = byteArrayOutputStream;
                        try {
                            throw e;
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayOutputStream = gZIPOutputStream2;
                            gZIPOutputStream2 = gZIPOutputStream;
                            try {
                                gZIPOutputStream2.close();
                            } catch (Exception unused4) {
                            }
                            try {
                                cVar.close();
                            } catch (Exception unused5) {
                            }
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused6) {
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        gZIPOutputStream2 = gZIPOutputStream;
                        gZIPOutputStream2.close();
                        cVar.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                    gZIPOutputStream = null;
                } catch (Throwable th5) {
                    th = th5;
                    gZIPOutputStream2.close();
                    cVar.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } else {
                boolean z = (i4 & 8) != 0;
                int i5 = ((i3 / 3) * 4) + (i3 % 3 > 0 ? 4 : 0);
                if (z) {
                    i5 += i5 / 76;
                }
                int i6 = i5;
                byte[] bArr2 = new byte[i6];
                int i7 = i3 - 2;
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                while (i8 < i7) {
                    int i11 = i8;
                    encode3to4(bArr, i8 + i2, 3, bArr2, i9, i4);
                    int i12 = i10 + 4;
                    if (!z || i12 < 76) {
                        i10 = i12;
                    } else {
                        bArr2[i9 + 4] = 10;
                        i9++;
                        i10 = 0;
                    }
                    i8 = i11 + 3;
                    i9 += 4;
                }
                int i13 = i8;
                if (i13 < i3) {
                    encode3to4(bArr, i13 + i2, i3 - i13, bArr2, i9, i4);
                    i9 += 4;
                }
                int i14 = i9;
                if (i14 <= i6 - 1) {
                    byte[] bArr3 = new byte[i14];
                    System.arraycopy(bArr2, 0, bArr3, 0, i14);
                    return bArr3;
                }
                return bArr2;
            }
        } else {
            throw new IllegalArgumentException("Cannot have length offset: " + i3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.OutputStream, com.jingdong.jdsdk.secure.Base64$c] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v19, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String encodeObject(java.io.Serializable r5, int r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L93
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6e
            r1.<init>()     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6e
            com.jingdong.jdsdk.secure.Base64$c r2 = new com.jingdong.jdsdk.secure.Base64$c     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r3 = r6 | 1
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r6 = r6 & 2
            if (r6 == 0) goto L28
            java.util.zip.GZIPOutputStream r6 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5c
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5c
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L22
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L22
            r0 = r3
            goto L30
        L1f:
            r5 = move-exception
            goto L78
        L22:
            r5 = move-exception
            r4 = r1
            r1 = r0
            r0 = r2
            r2 = r4
            goto L72
        L28:
            java.io.ObjectOutputStream r6 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5c
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5c
            r4 = r0
            r0 = r6
            r6 = r4
        L30:
            r0.writeObject(r5)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L22
            r0.close()     // Catch: java.lang.Exception -> L37
            goto L38
        L37:
        L38:
            if (r6 == 0) goto L3d
            r6.close()     // Catch: java.lang.Exception -> L3d
        L3d:
            r2.close()     // Catch: java.lang.Exception -> L40
        L40:
            r1.close()     // Catch: java.lang.Exception -> L43
        L43:
            java.lang.String r5 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L4f
            byte[] r6 = r1.toByteArray()     // Catch: java.io.UnsupportedEncodingException -> L4f
            java.lang.String r0 = "US-ASCII"
            r5.<init>(r6, r0)     // Catch: java.io.UnsupportedEncodingException -> L4f
            return r5
        L4f:
            java.lang.String r5 = new java.lang.String
            byte[] r6 = r1.toByteArray()
            r5.<init>(r6)
            return r5
        L59:
            r5 = move-exception
            r6 = r0
            goto L78
        L5c:
            r5 = move-exception
            r6 = r0
            r0 = r2
            goto L66
        L60:
            r5 = move-exception
            r6 = r0
            r2 = r6
            goto L78
        L64:
            r5 = move-exception
            r6 = r0
        L66:
            r2 = r1
            r1 = r6
            goto L72
        L69:
            r5 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
            goto L78
        L6e:
            r5 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
        L72:
            throw r5     // Catch: java.lang.Throwable -> L73
        L73:
            r5 = move-exception
            r4 = r2
            r2 = r0
            r0 = r1
            r1 = r4
        L78:
            if (r0 == 0) goto L7f
            r0.close()     // Catch: java.lang.Exception -> L7e
            goto L7f
        L7e:
        L7f:
            if (r6 == 0) goto L86
            r6.close()     // Catch: java.lang.Exception -> L85
            goto L86
        L85:
        L86:
            if (r2 == 0) goto L8d
            r2.close()     // Catch: java.lang.Exception -> L8c
            goto L8d
        L8c:
        L8d:
            if (r1 == 0) goto L92
            r1.close()     // Catch: java.lang.Exception -> L92
        L92:
            throw r5
        L93:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Cannot serialize a null object."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.secure.Base64.encodeObject(java.io.Serializable, int):java.lang.String");
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

    /* loaded from: classes14.dex */
    public static class c extends FilterOutputStream {

        /* renamed from: g  reason: collision with root package name */
        private boolean f12922g;

        /* renamed from: h  reason: collision with root package name */
        private int f12923h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f12924i;

        /* renamed from: j  reason: collision with root package name */
        private int f12925j;

        /* renamed from: k  reason: collision with root package name */
        private int f12926k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f12927l;

        /* renamed from: m  reason: collision with root package name */
        private byte[] f12928m;

        /* renamed from: n  reason: collision with root package name */
        private boolean f12929n;
        private int o;
        private byte[] p;

        public c(OutputStream outputStream, int i2) {
            super(outputStream);
            this.f12927l = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.f12922g = z;
            int i3 = z ? 3 : 4;
            this.f12925j = i3;
            this.f12924i = new byte[i3];
            this.f12923h = 0;
            this.f12926k = 0;
            this.f12929n = false;
            this.f12928m = new byte[4];
            this.o = i2;
            this.p = Base64.getDecodabet(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            f();
            super.close();
            this.f12924i = null;
            ((FilterOutputStream) this).out = null;
        }

        public void f() throws IOException {
            int i2 = this.f12923h;
            if (i2 > 0) {
                if (this.f12922g) {
                    ((FilterOutputStream) this).out.write(Base64.encode3to4(this.f12928m, this.f12924i, i2, this.o));
                    this.f12923h = 0;
                    return;
                }
                throw new IOException("Base64 input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) throws IOException {
            if (this.f12929n) {
                ((FilterOutputStream) this).out.write(i2);
            } else if (this.f12922g) {
                byte[] bArr = this.f12924i;
                int i3 = this.f12923h;
                int i4 = i3 + 1;
                this.f12923h = i4;
                bArr[i3] = (byte) i2;
                int i5 = this.f12925j;
                if (i4 >= i5) {
                    ((FilterOutputStream) this).out.write(Base64.encode3to4(this.f12928m, bArr, i5, this.o));
                    int i6 = this.f12926k + 4;
                    this.f12926k = i6;
                    if (this.f12927l && i6 >= 76) {
                        ((FilterOutputStream) this).out.write(10);
                        this.f12926k = 0;
                    }
                    this.f12923h = 0;
                }
            } else {
                byte[] bArr2 = this.p;
                int i7 = i2 & 127;
                if (bArr2[i7] > -5) {
                    byte[] bArr3 = this.f12924i;
                    int i8 = this.f12923h;
                    int i9 = i8 + 1;
                    this.f12923h = i9;
                    bArr3[i8] = (byte) i2;
                    if (i9 >= this.f12925j) {
                        ((FilterOutputStream) this).out.write(this.f12928m, 0, Base64.decode4to3(bArr3, 0, this.f12928m, 0, this.o));
                        this.f12923h = 0;
                    }
                } else if (bArr2[i7] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.f12929n) {
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

    /* JADX WARN: Code restructure failed: missing block: B:55:0x008e, code lost:
        if (r3 == null) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto La7
            java.lang.String r0 = "US-ASCII"
            byte[] r5 = r5.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L9
            goto Ld
        L9:
            byte[] r5 = r5.getBytes()
        Ld:
            int r0 = r5.length
            r1 = 0
            byte[] r5 = decode(r5, r1, r0, r6)
            r0 = 4
            r6 = r6 & r0
            r2 = 1
            if (r6 == 0) goto L1a
            r6 = 1
            goto L1b
        L1a:
            r6 = 0
        L1b:
            if (r5 == 0) goto La6
            int r3 = r5.length
            if (r3 < r0) goto La6
            if (r6 != 0) goto La6
            r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r0 = r5[r2]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto La6
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L78
            r2.<init>()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L78
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6f
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6f
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67
        L48:
            int r0 = r4.read(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            if (r0 < 0) goto L52
            r2.write(r6, r1, r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            goto L48
        L52:
            byte[] r5 = r2.toByteArray()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r2.close()     // Catch: java.lang.Exception -> L59
        L59:
            r4.close()     // Catch: java.lang.Exception -> L5c
        L5c:
            r3.close()     // Catch: java.lang.Exception -> La6
            goto La6
        L60:
            r5 = move-exception
            goto L6d
        L62:
            r6 = move-exception
            goto L72
        L64:
            r5 = move-exception
            r4 = r0
            goto L6d
        L67:
            r6 = move-exception
            r4 = r0
            goto L72
        L6a:
            r5 = move-exception
            r3 = r0
            r4 = r3
        L6d:
            r0 = r2
            goto L92
        L6f:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L72:
            r0 = r2
            goto L7b
        L74:
            r5 = move-exception
            r3 = r0
            r4 = r3
            goto L92
        L78:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L7b:
            java.lang.String r1 = "Base64"
            com.jingdong.sdk.oklog.OKLog.e(r1, r6)     // Catch: java.lang.Throwable -> L91
            if (r0 == 0) goto L87
            r0.close()     // Catch: java.lang.Exception -> L86
            goto L87
        L86:
        L87:
            if (r4 == 0) goto L8e
            r4.close()     // Catch: java.lang.Exception -> L8d
            goto L8e
        L8d:
        L8e:
            if (r3 == 0) goto La6
            goto L5c
        L91:
            r5 = move-exception
        L92:
            if (r0 == 0) goto L99
            r0.close()     // Catch: java.lang.Exception -> L98
            goto L99
        L98:
        L99:
            if (r4 == 0) goto La0
            r4.close()     // Catch: java.lang.Exception -> L9f
            goto La0
        L9f:
        La0:
            if (r3 == 0) goto La5
            r3.close()     // Catch: java.lang.Exception -> La5
        La5:
            throw r5
        La6:
            return r5
        La7:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Input string was null."
            r5.<init>(r6)
            goto Lb0
        Laf:
            throw r5
        Lb0:
            goto Laf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.secure.Base64.decode(java.lang.String, int):byte[]");
    }

    /* loaded from: classes14.dex */
    public static class b extends FilterInputStream {

        /* renamed from: g  reason: collision with root package name */
        private boolean f12914g;

        /* renamed from: h  reason: collision with root package name */
        private int f12915h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f12916i;

        /* renamed from: j  reason: collision with root package name */
        private int f12917j;

        /* renamed from: k  reason: collision with root package name */
        private int f12918k;

        /* renamed from: l  reason: collision with root package name */
        private int f12919l;

        /* renamed from: m  reason: collision with root package name */
        private boolean f12920m;

        /* renamed from: n  reason: collision with root package name */
        private int f12921n;
        private byte[] o;

        public b(InputStream inputStream, int i2) {
            super(inputStream);
            this.f12921n = i2;
            this.f12920m = (i2 & 8) > 0;
            boolean z = (i2 & 1) > 0;
            this.f12914g = z;
            int i3 = z ? 4 : 3;
            this.f12917j = i3;
            this.f12916i = new byte[i3];
            this.f12915h = -1;
            this.f12919l = 0;
            this.o = Base64.getDecodabet(i2);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read;
            if (this.f12915h < 0) {
                if (this.f12914g) {
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
                    Base64.encode3to4(bArr, 0, i2, this.f12916i, 0, this.f12921n);
                    this.f12915h = 0;
                    this.f12918k = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i4 = 0;
                    while (i4 < 4) {
                        do {
                            read = ((FilterInputStream) this).in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.o[read & 127] <= -5);
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
                    this.f12918k = Base64.decode4to3(bArr2, 0, this.f12916i, 0, this.f12921n);
                    this.f12915h = 0;
                }
            }
            int i5 = this.f12915h;
            if (i5 >= 0) {
                if (i5 >= this.f12918k) {
                    return -1;
                }
                if (this.f12914g && this.f12920m && this.f12919l >= 76) {
                    this.f12919l = 0;
                    return 10;
                }
                this.f12919l++;
                byte[] bArr3 = this.f12916i;
                int i6 = i5 + 1;
                this.f12915h = i6;
                byte b = bArr3[i5];
                if (i6 >= this.f12917j) {
                    this.f12915h = -1;
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
