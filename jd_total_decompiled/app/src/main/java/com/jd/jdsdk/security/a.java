package com.jd.jdsdk.security;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes.dex */
public class a {
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f2710c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f2711e = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f2712f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    static /* synthetic */ int b(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        return f(bArr, i2, bArr2, i3, i4);
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    static /* synthetic */ byte[] c(byte[] r0, byte[] r1, int r2, int r3) {
        /*
            h(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsdk.security.a.c(byte[], byte[], int, int):byte[]");
    }

    public static byte[] d(byte[] bArr) throws IOException {
        return e(bArr, 0, bArr.length, 0);
    }

    public static byte[] e(byte[] bArr, int i2, int i3, int i4) throws IOException {
        int i5;
        if (bArr != null) {
            if (i2 < 0 || (i5 = i2 + i3) > bArr.length) {
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            if (i3 == 0) {
                return new byte[0];
            }
            if (i3 >= 4) {
                byte[] m2 = m(i4);
                byte[] bArr2 = new byte[(i3 * 3) / 4];
                byte[] bArr3 = new byte[4];
                int i6 = 0;
                int i7 = 0;
                while (i2 < i5) {
                    byte b2 = m2[bArr[i2] & 255];
                    if (b2 < -5) {
                        throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                    }
                    if (b2 >= -1) {
                        int i8 = i6 + 1;
                        bArr3[i6] = bArr[i2];
                        if (i8 > 3) {
                            i7 += f(bArr3, 0, bArr2, i7, i4);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static int f(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6;
        if (bArr != null) {
            if (bArr2 != null) {
                if (i2 >= 0 && (i5 = i2 + 3) < bArr.length) {
                    if (i3 >= 0 && (i6 = i3 + 2) < bArr2.length) {
                        byte[] m2 = m(i4);
                        int i7 = i2 + 2;
                        if (bArr[i7] == 61) {
                            bArr2[i3] = (byte) ((((m2[bArr[i2 + 1]] & 255) << 12) | ((m2[bArr[i2]] & 255) << 18)) >>> 16);
                            return 1;
                        } else if (bArr[i5] == 61) {
                            int i8 = ((m2[bArr[i7]] & 255) << 6) | ((m2[bArr[i2 + 1]] & 255) << 12) | ((m2[bArr[i2]] & 255) << 18);
                            bArr2[i3] = (byte) (i8 >>> 16);
                            bArr2[i3 + 1] = (byte) (i8 >>> 8);
                            return 2;
                        } else {
                            int i9 = (m2[bArr[i5]] & 255) | ((m2[bArr[i2 + 1]] & 255) << 12) | ((m2[bArr[i2]] & 255) << 18) | ((m2[bArr[i7]] & 255) << 6);
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

    private static byte[] g(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] l2 = l(i5);
        int i6 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = l2[i6 >>> 18];
            bArr2[i4 + 1] = l2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = 61;
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = l2[i6 >>> 18];
            bArr2[i4 + 1] = l2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = l2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = l2[i6 >>> 18];
            bArr2[i4 + 1] = l2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = l2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = l2[i6 & 63];
            return bArr2;
        }
    }

    private static byte[] h(byte[] bArr, byte[] bArr2, int i2, int i3) {
        g(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    public static String i(byte[] bArr) {
        try {
            return j(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String j(byte[] bArr, int i2, int i3, int i4) throws IOException {
        byte[] k2 = k(bArr, i2, i3, i4);
        try {
            return new String(k2, CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException unused) {
            return new String(k2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] k(byte[] bArr, int i2, int i3, int i4) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        C0088a c0088a;
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
                        c0088a = new C0088a(byteArrayOutputStream, i4 | 1);
                    } catch (IOException e2) {
                        e = e2;
                        c0088a = null;
                        gZIPOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        c0088a = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    c0088a = null;
                    gZIPOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = 0;
                    c0088a = null;
                }
                try {
                    gZIPOutputStream = new GZIPOutputStream(c0088a);
                    try {
                        gZIPOutputStream.write(bArr, i2, i3);
                        gZIPOutputStream.close();
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused) {
                        }
                        try {
                            c0088a.close();
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
                                c0088a.close();
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
                        c0088a.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                    gZIPOutputStream = null;
                } catch (Throwable th5) {
                    th = th5;
                    gZIPOutputStream2.close();
                    c0088a.close();
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
                    g(bArr, i8 + i2, 3, bArr2, i9, i4);
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
                    g(bArr, i13 + i2, i3 - i13, bArr2, i9, i4);
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

    private static final byte[] l(int i2) {
        if ((i2 & 16) == 16) {
            return f2710c;
        }
        if ((i2 & 32) == 32) {
            return f2711e;
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] m(int i2) {
        if ((i2 & 16) == 16) {
            return d;
        }
        if ((i2 & 32) == 32) {
            return f2712f;
        }
        return b;
    }

    /* renamed from: com.jd.jdsdk.security.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0088a extends FilterOutputStream {

        /* renamed from: g  reason: collision with root package name */
        private boolean f2713g;

        /* renamed from: h  reason: collision with root package name */
        private int f2714h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f2715i;

        /* renamed from: j  reason: collision with root package name */
        private int f2716j;

        /* renamed from: k  reason: collision with root package name */
        private int f2717k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f2718l;

        /* renamed from: m  reason: collision with root package name */
        private byte[] f2719m;

        /* renamed from: n  reason: collision with root package name */
        private boolean f2720n;
        private int o;
        private byte[] p;

        public C0088a(OutputStream outputStream, int i2) {
            super(outputStream);
            this.f2718l = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.f2713g = z;
            int i3 = z ? 3 : 4;
            this.f2716j = i3;
            this.f2715i = new byte[i3];
            this.f2714h = 0;
            this.f2717k = 0;
            this.f2720n = false;
            this.f2719m = new byte[4];
            this.o = i2;
            this.p = a.m(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            f();
            super.close();
            this.f2715i = null;
            ((FilterOutputStream) this).out = null;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.jdsdk.security.a.c(byte[], byte[], int, int):byte[]
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
            	... 1 more
            */
        public void f() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.f2714h
                if (r0 <= 0) goto L22
                boolean r1 = r5.f2713g
                if (r1 == 0) goto L1a
                java.io.OutputStream r1 = r5.out
                byte[] r2 = r5.f2719m
                byte[] r3 = r5.f2715i
                int r4 = r5.o
                com.jd.jdsdk.security.a.c(r2, r3, r0, r4)
                r1.write(r2)
                r0 = 0
                r5.f2714h = r0
                goto L22
            L1a:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Base64 input not properly padded."
                r0.<init>(r1)
                throw r0
            L22:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsdk.security.a.C0088a.f():void");
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.jdsdk.security.a.c(byte[], byte[], int, int):byte[]
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
            	... 1 more
            */
        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int r6) throws java.io.IOException {
            /*
                r5 = this;
                boolean r0 = r5.f2720n
                if (r0 == 0) goto La
                java.io.OutputStream r0 = r5.out
                r0.write(r6)
                return
            La:
                boolean r0 = r5.f2713g
                r1 = 0
                if (r0 == 0) goto L44
                byte[] r0 = r5.f2715i
                int r2 = r5.f2714h
                int r3 = r2 + 1
                r5.f2714h = r3
                byte r6 = (byte) r6
                r0[r2] = r6
                int r6 = r5.f2716j
                if (r3 < r6) goto L72
                java.io.OutputStream r2 = r5.out
                byte[] r3 = r5.f2719m
                int r4 = r5.o
                com.jd.jdsdk.security.a.c(r3, r0, r6, r4)
                r2.write(r3)
                int r6 = r5.f2717k
                int r6 = r6 + 4
                r5.f2717k = r6
                boolean r0 = r5.f2718l
                if (r0 == 0) goto L41
                r0 = 76
                if (r6 < r0) goto L41
                java.io.OutputStream r6 = r5.out
                r0 = 10
                r6.write(r0)
                r5.f2717k = r1
            L41:
                r5.f2714h = r1
                goto L72
            L44:
                byte[] r0 = r5.p
                r2 = r6 & 127(0x7f, float:1.78E-43)
                r3 = r0[r2]
                r4 = -5
                if (r3 <= r4) goto L6e
                byte[] r0 = r5.f2715i
                int r2 = r5.f2714h
                int r3 = r2 + 1
                r5.f2714h = r3
                byte r6 = (byte) r6
                r0[r2] = r6
                int r6 = r5.f2716j
                if (r3 < r6) goto L72
                byte[] r6 = r5.f2719m
                int r2 = r5.o
                int r6 = com.jd.jdsdk.security.a.b(r0, r1, r6, r1, r2)
                java.io.OutputStream r0 = r5.out
                byte[] r2 = r5.f2719m
                r0.write(r2, r1, r6)
                r5.f2714h = r1
                goto L72
            L6e:
                r6 = r0[r2]
                if (r6 != r4) goto L73
            L72:
                return
            L73:
                java.io.IOException r6 = new java.io.IOException
                java.lang.String r0 = "Invalid character in Base64 data."
                r6.<init>(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsdk.security.a.C0088a.write(int):void");
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.f2720n) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }
}
