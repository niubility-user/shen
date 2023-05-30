package com.jingdong.c.b;

import androidx.core.view.MotionEventCompat;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes.dex */
public class e {
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f12300c = true;

    public static String a(byte[] bArr) {
        String str;
        try {
            str = b(bArr, bArr.length);
        } catch (IOException e2) {
            if (!f12300c) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f12300c || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    private static String b(byte[] bArr, int i2) {
        if (bArr != null) {
            if (i2 >= 0) {
                if (i2 + 0 <= bArr.length) {
                    int i3 = ((i2 / 3) * 4) + (i2 % 3 <= 0 ? 0 : 4);
                    byte[] bArr2 = new byte[i3];
                    int i4 = i2 - 2;
                    int i5 = 0;
                    int i6 = 0;
                    while (i5 < i4) {
                        d(bArr, i5 + 0, 3, bArr2, i6);
                        i5 += 3;
                        i6 += 4;
                    }
                    if (i5 < i2) {
                        d(bArr, i5 + 0, i2 - i5, bArr2, i6);
                        i6 += 4;
                    }
                    if (i6 <= i3 - 1) {
                        byte[] bArr3 = new byte[i6];
                        System.arraycopy(bArr2, 0, bArr3, 0, i6);
                        bArr2 = bArr3;
                    }
                    try {
                        return new String(bArr2, CharEncoding.US_ASCII);
                    } catch (UnsupportedEncodingException unused) {
                        return new String(bArr2);
                    }
                }
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", 0, Integer.valueOf(i2), Integer.valueOf(bArr.length)));
            }
            throw new IllegalArgumentException("Cannot have length offset: ".concat(String.valueOf(i2)));
        }
        throw new NullPointerException("Cannot serialize a null array.");
    }

    public static byte[] c(String str) {
        return e(str);
    }

    private static byte[] d(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        byte[] bArr3 = a;
        int i5 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = bArr3[i5 >>> 18];
            bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
            bArr2[i4 + 2] = 61;
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = bArr3[i5 >>> 18];
            bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
            bArr2[i4 + 2] = bArr3[(i5 >>> 6) & 63];
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = bArr3[i5 >>> 18];
            bArr2[i4 + 1] = bArr3[(i5 >>> 12) & 63];
            bArr2[i4 + 2] = bArr3[(i5 >>> 6) & 63];
            bArr2[i4 + 3] = bArr3[i5 & 63];
            return bArr2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0082, code lost:
        if (r4 == null) goto L63;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] e(String str) {
        byte[] bytes;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        if (str != null) {
            try {
                bytes = str.getBytes(CharEncoding.US_ASCII);
            } catch (UnsupportedEncodingException unused) {
                bytes = str.getBytes();
            }
            byte[] f2 = f(bytes, bytes.length);
            if (f2.length >= 4 && 35615 == ((f2[0] & 255) | ((f2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
                byte[] bArr = new byte[2048];
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(f2);
                        try {
                            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                            while (true) {
                                try {
                                    int read = gZIPInputStream.read(bArr);
                                    if (read < 0) {
                                        break;
                                    }
                                    byteArrayOutputStream2.write(bArr, 0, read);
                                } catch (IOException e2) {
                                    e = e2;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    try {
                                        e.printStackTrace();
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused2) {
                                            }
                                        }
                                        if (gZIPInputStream != null) {
                                            try {
                                                gZIPInputStream.close();
                                            } catch (Exception unused3) {
                                            }
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused4) {
                                            }
                                        }
                                        if (gZIPInputStream != null) {
                                            try {
                                                gZIPInputStream.close();
                                            } catch (Exception unused5) {
                                            }
                                        }
                                        if (byteArrayInputStream != null) {
                                            try {
                                                byteArrayInputStream.close();
                                            } catch (Exception unused6) {
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    if (byteArrayOutputStream != null) {
                                    }
                                    if (gZIPInputStream != null) {
                                    }
                                    if (byteArrayInputStream != null) {
                                    }
                                    throw th;
                                }
                            }
                            f2 = byteArrayOutputStream2.toByteArray();
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception unused7) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception unused8) {
                            }
                        } catch (IOException e3) {
                            e = e3;
                            gZIPInputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            gZIPInputStream = null;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        byteArrayInputStream = null;
                        gZIPInputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        byteArrayInputStream = null;
                        gZIPInputStream = null;
                    }
                } catch (IOException e5) {
                    e = e5;
                    byteArrayInputStream = null;
                    gZIPInputStream = null;
                } catch (Throwable th5) {
                    th = th5;
                    byteArrayInputStream = null;
                    gZIPInputStream = null;
                }
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused9) {
                }
            }
            return f2;
        }
        throw new NullPointerException("Input string was null.");
    }

    private static byte[] f(byte[] bArr, int i2) {
        int i3;
        if (bArr != null) {
            int i4 = i2 + 0;
            int i5 = 1;
            if (i4 <= bArr.length) {
                if (i2 == 0) {
                    return new byte[0];
                }
                if (i2 >= 4) {
                    byte[] bArr2 = b;
                    int i6 = (i2 * 3) / 4;
                    byte[] bArr3 = new byte[i6];
                    byte[] bArr4 = new byte[4];
                    int i7 = 0;
                    int i8 = 0;
                    int i9 = 0;
                    while (i7 < i4) {
                        byte b2 = bArr2[bArr[i7] & 255];
                        if (b2 < -5) {
                            throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i7] & 255), Integer.valueOf(i7)));
                        }
                        if (b2 >= -1) {
                            int i10 = i8 + 1;
                            bArr4[i8] = bArr[i7];
                            if (i10 <= 3) {
                                i8 = i10;
                            } else if (i9 < 0 || (i3 = i9 + 2) >= i6) {
                                throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(i6), Integer.valueOf(i9)));
                            } else {
                                byte[] bArr5 = b;
                                if (bArr4[2] == 61) {
                                    bArr3[i9] = (byte) ((((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[i5]] & 255) << 12)) >>> 16);
                                } else if (bArr4[3] == 61) {
                                    int i11 = ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[i5]] & 255) << 12) | ((bArr5[bArr4[2]] & 255) << 6);
                                    bArr3[i9] = (byte) (i11 >>> 16);
                                    bArr3[i9 + 1] = (byte) (i11 >>> 8);
                                    i5 = 2;
                                } else {
                                    int i12 = ((bArr5[bArr4[i5]] & 255) << 12) | ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[2]] & 255) << 6) | (bArr5[bArr4[3]] & 255);
                                    bArr3[i9] = (byte) (i12 >> 16);
                                    bArr3[i9 + 1] = (byte) (i12 >> 8);
                                    bArr3[i3] = (byte) i12;
                                    i5 = 3;
                                }
                                i9 += i5;
                                if (bArr[i7] == 61) {
                                    break;
                                }
                                i8 = 0;
                            }
                        }
                        i7++;
                        i5 = 1;
                    }
                    byte[] bArr6 = new byte[i9];
                    System.arraycopy(bArr3, 0, bArr6, 0, i9);
                    return bArr6;
                }
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was ".concat(String.valueOf(i2)));
            }
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
        }
        throw new NullPointerException("Cannot decode null source array.");
    }
}
