package com.jingdong.c.b;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] e(java.lang.String r6) {
        /*
            if (r6 == 0) goto L9b
            java.lang.String r0 = "US-ASCII"
            byte[] r6 = r6.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L9
            goto Ld
        L9:
            byte[] r6 = r6.getBytes()
        Ld:
            int r0 = r6.length
            byte[] r6 = f(r6, r0)
            int r0 = r6.length
            r1 = 4
            if (r0 < r1) goto L9a
            r0 = 0
            r1 = r6[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 1
            r2 = r6[r2]
            int r2 = r2 << 8
            r3 = 65280(0xff00, float:9.1477E-41)
            r2 = r2 & r3
            r1 = r1 | r2
            r2 = 35615(0x8b1f, float:4.9907E-41)
            if (r2 != r1) goto L9a
            r1 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r1]
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6e
            r3.<init>()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6e
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L65
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L65
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
        L3e:
            int r2 = r5.read(r1)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            if (r2 < 0) goto L48
            r3.write(r1, r0, r2)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            goto L3e
        L48:
            byte[] r6 = r3.toByteArray()     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r3.close()     // Catch: java.lang.Exception -> L4f
        L4f:
            r5.close()     // Catch: java.lang.Exception -> L52
        L52:
            r4.close()     // Catch: java.lang.Exception -> L9a
            goto L9a
        L56:
            r6 = move-exception
            goto L63
        L58:
            r0 = move-exception
            goto L68
        L5a:
            r6 = move-exception
            r5 = r2
            goto L63
        L5d:
            r0 = move-exception
            r5 = r2
            goto L68
        L60:
            r6 = move-exception
            r4 = r2
            r5 = r4
        L63:
            r2 = r3
            goto L86
        L65:
            r0 = move-exception
            r4 = r2
            r5 = r4
        L68:
            r2 = r3
            goto L71
        L6a:
            r6 = move-exception
            r4 = r2
            r5 = r4
            goto L86
        L6e:
            r0 = move-exception
            r4 = r2
            r5 = r4
        L71:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L85
            if (r2 == 0) goto L7b
            r2.close()     // Catch: java.lang.Exception -> L7a
            goto L7b
        L7a:
        L7b:
            if (r5 == 0) goto L82
            r5.close()     // Catch: java.lang.Exception -> L81
            goto L82
        L81:
        L82:
            if (r4 == 0) goto L9a
            goto L52
        L85:
            r6 = move-exception
        L86:
            if (r2 == 0) goto L8d
            r2.close()     // Catch: java.lang.Exception -> L8c
            goto L8d
        L8c:
        L8d:
            if (r5 == 0) goto L94
            r5.close()     // Catch: java.lang.Exception -> L93
            goto L94
        L93:
        L94:
            if (r4 == 0) goto L99
            r4.close()     // Catch: java.lang.Exception -> L99
        L99:
            throw r6
        L9a:
            return r6
        L9b:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r0 = "Input string was null."
            r6.<init>(r0)
            goto La4
        La3:
            throw r6
        La4:
            goto La3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.c.b.e.e(java.lang.String):byte[]");
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
