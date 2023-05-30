package com.jingdong.manto.j;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes15.dex */
public class a {
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f13105c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f13106e = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f13107f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: g  reason: collision with root package name */
    static final /* synthetic */ boolean f13108g = true;

    /* renamed from: com.jingdong.manto.j.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static class C0515a extends FilterOutputStream {
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private byte[] f13109c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f13110e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f13111f;

        /* renamed from: g  reason: collision with root package name */
        private byte[] f13112g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f13113h;

        /* renamed from: i  reason: collision with root package name */
        private int f13114i;

        /* renamed from: j  reason: collision with root package name */
        private byte[] f13115j;

        public C0515a(OutputStream outputStream, int i2) {
            super(outputStream);
            this.f13111f = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.a = z;
            int i3 = z ? 3 : 4;
            this.d = i3;
            this.f13109c = new byte[i3];
            this.b = 0;
            this.f13110e = 0;
            this.f13113h = false;
            this.f13112g = new byte[4];
            this.f13114i = i2;
            this.f13115j = a.c(i2);
        }

        public void a() {
            int i2 = this.b;
            if (i2 > 0) {
                if (!this.a) {
                    throw new IOException("Base64 input not properly padded.");
                }
                ((FilterOutputStream) this).out.write(a.b(this.f13112g, this.f13109c, i2, this.f13114i));
                this.b = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            a();
            super.close();
            this.f13109c = null;
            ((FilterOutputStream) this).out = null;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) {
            if (this.f13113h) {
                ((FilterOutputStream) this).out.write(i2);
                return;
            }
            if (this.a) {
                byte[] bArr = this.f13109c;
                int i3 = this.b;
                int i4 = i3 + 1;
                this.b = i4;
                bArr[i3] = (byte) i2;
                int i5 = this.d;
                if (i4 < i5) {
                    return;
                }
                ((FilterOutputStream) this).out.write(a.b(this.f13112g, bArr, i5, this.f13114i));
                int i6 = this.f13110e + 4;
                this.f13110e = i6;
                if (this.f13111f && i6 >= 76) {
                    ((FilterOutputStream) this).out.write(10);
                    this.f13110e = 0;
                }
            } else {
                byte[] bArr2 = this.f13115j;
                int i7 = i2 & 127;
                if (bArr2[i7] <= -5) {
                    if (bArr2[i7] != -5) {
                        throw new IOException("Invalid character in Base64 data.");
                    }
                    return;
                }
                byte[] bArr3 = this.f13109c;
                int i8 = this.b;
                int i9 = i8 + 1;
                this.b = i9;
                bArr3[i8] = (byte) i2;
                if (i9 < this.d) {
                    return;
                }
                ((FilterOutputStream) this).out.write(this.f13112g, 0, a.b(bArr3, 0, this.f13112g, 0, this.f13114i));
            }
            this.b = 0;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            if (this.f13113h) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }

    private a() {
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length, 0);
    }

    public static byte[] a(byte[] bArr, int i2, int i3, int i4) {
        int i5;
        if (bArr != null) {
            if (i2 < 0 || (i5 = i2 + i3) > bArr.length) {
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            if (i3 == 0) {
                return new byte[0];
            }
            if (i3 < 4) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i3);
            }
            byte[] c2 = c(i4);
            byte[] bArr2 = new byte[(i3 * 3) / 4];
            byte[] bArr3 = new byte[4];
            int i6 = 0;
            int i7 = 0;
            while (i2 < i5) {
                byte b2 = c2[bArr[i2] & 255];
                if (b2 < -5) {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                }
                if (b2 >= -1) {
                    int i8 = i6 + 1;
                    bArr3[i6] = bArr[i2];
                    if (i8 > 3) {
                        i7 += b(bArr3, 0, bArr2, i7, i4);
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
        throw new NullPointerException("Cannot decode null source array.");
    }

    private static byte[] a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] b2 = b(i5);
        int i6 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = b2[i6 >>> 18];
            bArr2[i4 + 1] = b2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = 61;
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = b2[i6 >>> 18];
            bArr2[i4 + 1] = b2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = b2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = b2[i6 >>> 18];
            bArr2[i4 + 1] = b2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = b2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = b2[i6 & 63];
            return bArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6;
        if (bArr != null) {
            if (bArr2 != null) {
                if (i2 < 0 || (i5 = i2 + 3) >= bArr.length) {
                    throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i2)));
                }
                if (i3 < 0 || (i6 = i3 + 2) >= bArr2.length) {
                    throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i3)));
                }
                byte[] c2 = c(i4);
                int i7 = i2 + 2;
                if (bArr[i7] == 61) {
                    bArr2[i3] = (byte) ((((c2[bArr[i2 + 1]] & 255) << 12) | ((c2[bArr[i2]] & 255) << 18)) >>> 16);
                    return 1;
                } else if (bArr[i5] == 61) {
                    int i8 = ((c2[bArr[i7]] & 255) << 6) | ((c2[bArr[i2 + 1]] & 255) << 12) | ((c2[bArr[i2]] & 255) << 18);
                    bArr2[i3] = (byte) (i8 >>> 16);
                    bArr2[i3 + 1] = (byte) (i8 >>> 8);
                    return 2;
                } else {
                    int i9 = (c2[bArr[i5]] & 255) | ((c2[bArr[i2 + 1]] & 255) << 12) | ((c2[bArr[i2]] & 255) << 18) | ((c2[bArr[i7]] & 255) << 6);
                    bArr2[i3] = (byte) (i9 >> 16);
                    bArr2[i3 + 1] = (byte) (i9 >> 8);
                    bArr2[i6] = (byte) i9;
                    return 3;
                }
            }
            throw new NullPointerException("Destination array was null.");
        }
        throw new NullPointerException("Source array was null.");
    }

    public static String b(byte[] bArr) {
        String str;
        try {
            str = b(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (!f13108g) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f13108g || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String b(byte[] bArr, int i2, int i3, int i4) {
        byte[] c2 = c(bArr, i2, i3, i4);
        try {
            return new String(c2, CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException unused) {
            return new String(c2);
        }
    }

    private static final byte[] b(int i2) {
        return (i2 & 16) == 16 ? f13105c : (i2 & 32) == 32 ? f13106e : a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i2, int i3) {
        a(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] c(int i2) {
        return (i2 & 16) == 16 ? d : (i2 & 32) == 32 ? f13107f : b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    public static byte[] c(byte[] bArr, int i2, int i3, int i4) {
        C0515a c0515a;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i2);
        } else if (i3 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i3);
        } else {
            int i5 = i2 + i3;
            ?? length = bArr.length;
            if (i5 > length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
            }
            if ((i4 & 2) == 0) {
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
                    a(bArr, i9 + i2, 3, bArr2, i10, i4);
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
                    a(bArr, i14 + i2, i3 - i14, bArr2, i10, i4);
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
            GZIPOutputStream gZIPOutputStream = null;
            try {
                try {
                    length = new ByteArrayOutputStream();
                    try {
                        c0515a = new C0515a(length, i4 | 1);
                        try {
                            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(c0515a);
                            try {
                                gZIPOutputStream2.write(bArr, i2, i3);
                                gZIPOutputStream2.close();
                                try {
                                    gZIPOutputStream2.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    c0515a.close();
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
                                    c0515a.close();
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
                        c0515a = null;
                    }
                } catch (IOException e5) {
                    throw e5;
                } catch (Throwable th3) {
                    th = th3;
                    length = 0;
                    c0515a = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }
}
