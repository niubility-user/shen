package com.jd.aips.common.utils;

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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes12.dex */
public class Base64Utils {
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    public static final int GZIP = 2;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    public static final int URL_SAFE = 16;
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f1592c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f1593e = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f1594f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: g  reason: collision with root package name */
    static final /* synthetic */ boolean f1595g = true;

    /* loaded from: classes12.dex */
    public static class InputStream extends FilterInputStream {
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private byte[] f1596c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f1597e;

        /* renamed from: f  reason: collision with root package name */
        private int f1598f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f1599g;

        /* renamed from: h  reason: collision with root package name */
        private int f1600h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f1601i;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read;
            if (this.b < 0) {
                if (!this.a) {
                    byte[] bArr = new byte[4];
                    int i2 = 0;
                    while (i2 < 4) {
                        do {
                            read = ((FilterInputStream) this).in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.f1601i[read & 127] <= -5);
                        if (read < 0) {
                            break;
                        }
                        bArr[i2] = (byte) read;
                        i2++;
                    }
                    if (i2 != 4) {
                        if (i2 == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64Utils input.");
                    }
                    this.f1597e = Base64Utils.b(bArr, 0, this.f1596c, 0, this.f1600h);
                    this.b = 0;
                } else {
                    byte[] bArr2 = new byte[3];
                    int i3 = 0;
                    for (int i4 = 0; i4 < 3; i4++) {
                        int read2 = ((FilterInputStream) this).in.read();
                        if (read2 < 0) {
                            break;
                        }
                        bArr2[i4] = (byte) read2;
                        i3++;
                    }
                    if (i3 <= 0) {
                        return -1;
                    }
                    Base64Utils.b(bArr2, 0, i3, this.f1596c, 0, this.f1600h);
                    this.b = 0;
                    this.f1597e = 4;
                }
            }
            int i5 = this.b;
            if (i5 >= 0) {
                if (i5 >= this.f1597e) {
                    return -1;
                }
                if (this.a && this.f1599g && this.f1598f >= 76) {
                    this.f1598f = 0;
                    return 10;
                }
                this.f1598f++;
                byte[] bArr3 = this.f1596c;
                int i6 = i5 + 1;
                this.b = i6;
                byte b = bArr3[i5];
                if (i6 >= this.d) {
                    this.b = -1;
                }
                return b & 255;
            }
            throw new IOException("Error in Base64Utils code reading stream.");
        }

        public InputStream(java.io.InputStream inputStream, int i2) {
            super(inputStream);
            this.f1600h = i2;
            this.f1599g = (i2 & 8) > 0;
            boolean z = (i2 & 1) > 0;
            this.a = z;
            int i3 = z ? 4 : 3;
            this.d = i3;
            this.f1596c = new byte[i3];
            this.b = -1;
            this.f1598f = 0;
            this.f1601i = Base64Utils.b(i2);
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

    /* loaded from: classes12.dex */
    public static class OutputStream extends FilterOutputStream {
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private byte[] f1602c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f1603e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f1604f;

        /* renamed from: g  reason: collision with root package name */
        private byte[] f1605g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f1606h;

        /* renamed from: i  reason: collision with root package name */
        private int f1607i;

        /* renamed from: j  reason: collision with root package name */
        private byte[] f1608j;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            flushBase64();
            super.close();
            this.f1602c = null;
            ((FilterOutputStream) this).out = null;
        }

        public void flushBase64() throws IOException {
            int i2 = this.b;
            if (i2 > 0) {
                if (this.a) {
                    ((FilterOutputStream) this).out.write(Base64Utils.b(this.f1605g, this.f1602c, i2, this.f1607i));
                    this.b = 0;
                    return;
                }
                throw new IOException("Base64Utils input not properly padded.");
            }
        }

        public void resumeEncoding() {
            this.f1606h = false;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.f1606h = true;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) throws IOException {
            if (this.f1606h) {
                ((FilterOutputStream) this).out.write(i2);
            } else if (this.a) {
                byte[] bArr = this.f1602c;
                int i3 = this.b;
                int i4 = i3 + 1;
                this.b = i4;
                bArr[i3] = (byte) i2;
                int i5 = this.d;
                if (i4 >= i5) {
                    ((FilterOutputStream) this).out.write(Base64Utils.b(this.f1605g, bArr, i5, this.f1607i));
                    int i6 = this.f1603e + 4;
                    this.f1603e = i6;
                    if (this.f1604f && i6 >= 76) {
                        ((FilterOutputStream) this).out.write(10);
                        this.f1603e = 0;
                    }
                    this.b = 0;
                }
            } else {
                byte[] bArr2 = this.f1608j;
                int i7 = i2 & 127;
                if (bArr2[i7] > -5) {
                    byte[] bArr3 = this.f1602c;
                    int i8 = this.b;
                    int i9 = i8 + 1;
                    this.b = i9;
                    bArr3[i8] = (byte) i2;
                    if (i9 >= this.d) {
                        ((FilterOutputStream) this).out.write(this.f1605g, 0, Base64Utils.b(bArr3, 0, this.f1605g, 0, this.f1607i));
                        this.b = 0;
                    }
                } else if (bArr2[i7] != -5) {
                    throw new IOException("Invalid character in Base64Utils data.");
                }
            }
        }

        public OutputStream(java.io.OutputStream outputStream, int i2) {
            super(outputStream);
            this.f1604f = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.a = z;
            int i3 = z ? 3 : 4;
            this.d = i3;
            this.f1602c = new byte[i3];
            this.b = 0;
            this.f1603e = 0;
            this.f1606h = false;
            this.f1605g = new byte[4];
            this.f1607i = i2;
            this.f1608j = Base64Utils.b(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.f1606h) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }

    private Base64Utils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] bArr3;
        if ((i5 & 16) == 16) {
            bArr3 = f1592c;
        } else {
            bArr3 = (i5 & 32) == 32 ? f1593e : a;
        }
        int i6 = (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = bArr3[i6 >>> 18];
            bArr2[i4 + 1] = bArr3[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = 61;
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = bArr3[i6 >>> 18];
            bArr2[i4 + 1] = bArr3[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = bArr3[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = bArr3[i6 >>> 18];
            bArr2[i4 + 1] = bArr3[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = bArr3[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = bArr3[i6 & 63];
            return bArr2;
        }
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        return decode(bArr, 0, bArr.length, 0);
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
        InputStream inputStream = null;
        try {
            try {
                File file = new File(str);
                if (file.length() <= 2147483647L) {
                    byte[] bArr = new byte[(int) file.length()];
                    InputStream inputStream2 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
                    int i2 = 0;
                    while (true) {
                        try {
                            int read = inputStream2.read(bArr, i2, 4096);
                            if (read < 0) {
                                break;
                            }
                            i2 += read;
                        } catch (IOException e2) {
                            throw e2;
                        } catch (Throwable th) {
                            th = th;
                            inputStream = inputStream2;
                            try {
                                inputStream.close();
                            } catch (Exception unused) {
                            }
                            throw th;
                        }
                    }
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    try {
                        inputStream2.close();
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
        OutputStream outputStream = null;
        try {
            try {
                OutputStream outputStream2 = new OutputStream(new FileOutputStream(str2), 0);
                try {
                    outputStream2.write(str.getBytes(CharEncoding.US_ASCII));
                    try {
                        outputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e2) {
                } catch (Throwable th) {
                    th = th;
                    outputStream = outputStream2;
                    try {
                        outputStream.close();
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
            b(bArr2, bArr, min, 0);
            byteBuffer2.put(bArr2);
        }
    }

    public static String encodeBytes(byte[] bArr) {
        String str;
        try {
            str = encodeBytes(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (!f1595g) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f1595g || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static byte[] encodeBytesToBytes(byte[] bArr) {
        try {
            return encodeBytesToBytes(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (f1595g) {
                return null;
            }
            throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + e2.getMessage());
        }
    }

    public static void encodeFileToFile(String str, String str2) throws IOException {
        String encodeFromFile = encodeFromFile(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    bufferedOutputStream2.write(encodeFromFile.getBytes(CharEncoding.US_ASCII));
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
        InputStream inputStream = null;
        try {
            try {
                File file = new File(str);
                double length = file.length();
                Double.isNaN(length);
                byte[] bArr = new byte[Math.max((int) ((length * 1.4d) + 1.0d), 40)];
                InputStream inputStream2 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
                int i2 = 0;
                while (true) {
                    try {
                        int read = inputStream2.read(bArr, i2, 4096);
                        if (read < 0) {
                            break;
                        }
                        i2 += read;
                    } catch (IOException e2) {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        try {
                            inputStream.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                }
                String str2 = new String(bArr, 0, i2, CharEncoding.US_ASCII);
                try {
                    inputStream2.close();
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
            OutputStream outputStream = null;
            try {
                try {
                    OutputStream outputStream2 = new OutputStream(new FileOutputStream(str), 1);
                    try {
                        outputStream2.write(bArr);
                        try {
                            outputStream2.close();
                        } catch (Exception unused) {
                        }
                    } catch (IOException e2) {
                    } catch (Throwable th) {
                        th = th;
                        outputStream = outputStream2;
                        try {
                            outputStream.close();
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
                byte[] b2 = b(i4);
                byte[] bArr2 = new byte[(i3 * 3) / 4];
                byte[] bArr3 = new byte[4];
                int i6 = 0;
                int i7 = 0;
                while (i2 < i5) {
                    byte b3 = b2[bArr[i2] & 255];
                    if (b3 < -5) {
                        throw new IOException(String.format("Bad Base64Utils input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                    }
                    if (b3 >= -1) {
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
            throw new IllegalArgumentException("Base64Utils-encoded string must have at least four characters, but length specified was " + i3);
        }
        throw new NullPointerException("Cannot decode null source array.");
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0032: MOVE (r2 I:??[OBJECT, ARRAY]) = (r0 I:??[OBJECT, ARRAY]), block:B:24:0x0031 */
    public static Object decodeToObject(String str, int i2, final ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream2;
        byte[] decode = decode(str, i2);
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(decode);
                try {
                    if (classLoader == null) {
                        objectInputStream2 = new ObjectInputStream(byteArrayInputStream3);
                    } else {
                        objectInputStream2 = new ObjectInputStream(byteArrayInputStream3) { // from class: com.jd.aips.common.utils.Base64Utils.1
                            @Override // java.io.ObjectInputStream
                            public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                                Class<?> cls = Class.forName(objectStreamClass.getName(), false, classLoader);
                                return cls == null ? super.resolveClass(objectStreamClass) : cls;
                            }
                        };
                    }
                    ObjectInputStream objectInputStream3 = objectInputStream2;
                    Object readObject = objectInputStream3.readObject();
                    try {
                        byteArrayInputStream3.close();
                    } catch (Exception unused) {
                    }
                    try {
                        objectInputStream3.close();
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
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        if (serializable != null) {
            ObjectOutputStream objectOutputStream2 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    outputStream = new OutputStream(byteArrayOutputStream, i2 | 1);
                    try {
                        if ((i2 & 2) != 0) {
                            r6 = new GZIPOutputStream(outputStream);
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
                                        outputStream.close();
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
                                    outputStream.close();
                                    byteArrayOutputStream.close();
                                    throw th;
                                }
                            }
                        } else {
                            objectOutputStream2 = new ObjectOutputStream(outputStream);
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
                    outputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    r6 = 0;
                    outputStream = null;
                }
            } catch (IOException e5) {
                e = e5;
                objectOutputStream = null;
                byteArrayOutputStream = null;
                outputStream = null;
            } catch (Throwable th5) {
                th = th5;
                r6 = 0;
                byteArrayOutputStream = null;
                outputStream = null;
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
                    outputStream.close();
                } catch (Exception unused7) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused8) {
                }
                try {
                    return new String(byteArrayOutputStream.toByteArray(), CharEncoding.US_ASCII);
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
                outputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
        throw new NullPointerException("Cannot serialize a null object.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    public static byte[] encodeBytesToBytes(byte[] bArr, int i2, int i3, int i4) throws IOException {
        OutputStream outputStream;
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
                            outputStream = new OutputStream(length, i4 | 1);
                            try {
                                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                                try {
                                    gZIPOutputStream2.write(bArr, i2, i3);
                                    gZIPOutputStream2.close();
                                    try {
                                        gZIPOutputStream2.close();
                                    } catch (Exception unused) {
                                    }
                                    try {
                                        outputStream.close();
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
                                        outputStream.close();
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
                            outputStream = null;
                        }
                    } catch (IOException e5) {
                        throw e5;
                    } catch (Throwable th3) {
                        th = th3;
                        length = 0;
                        outputStream = null;
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
                    b(bArr, i9 + i2, 3, bArr2, i10, i4);
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
                    b(bArr, i14 + i2, i3 - i14, bArr2, i10, i4);
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

    public static String encodeBytes(byte[] bArr, int i2) throws IOException {
        return encodeBytes(bArr, 0, bArr.length, i2);
    }

    public static void encode(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            b(bArr2, bArr, min, 0);
            for (int i2 = 0; i2 < 4; i2++) {
                charBuffer.put((char) (bArr2[i2] & 255));
            }
        }
    }

    public static String encodeBytes(byte[] bArr, int i2, int i3) {
        String str;
        try {
            str = encodeBytes(bArr, i2, i3, 0);
        } catch (IOException e2) {
            if (!f1595g) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f1595g || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String encodeBytes(byte[] bArr, int i2, int i3, int i4) throws IOException {
        byte[] encodeBytesToBytes = encodeBytesToBytes(bArr, i2, i3, i4);
        try {
            return new String(encodeBytesToBytes, CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException unused) {
            return new String(encodeBytesToBytes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] b(int i2) {
        if ((i2 & 16) == 16) {
            return d;
        }
        return (i2 & 32) == 32 ? f1594f : b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i2, int i3) {
        b(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    public static byte[] decode(String str) throws IOException {
        return decode(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6;
        if (bArr != null) {
            if (bArr2 != null) {
                if (i2 >= 0 && (i5 = i2 + 3) < bArr.length) {
                    if (i3 >= 0 && (i6 = i3 + 2) < bArr2.length) {
                        byte[] b2 = b(i4);
                        int i7 = i2 + 2;
                        if (bArr[i7] == 61) {
                            bArr2[i3] = (byte) ((((b2[bArr[i2 + 1]] & 255) << 12) | ((b2[bArr[i2]] & 255) << 18)) >>> 16);
                            return 1;
                        } else if (bArr[i5] == 61) {
                            int i8 = ((b2[bArr[i7]] & 255) << 6) | ((b2[bArr[i2 + 1]] & 255) << 12) | ((b2[bArr[i2]] & 255) << 18);
                            bArr2[i3] = (byte) (i8 >>> 16);
                            bArr2[i3 + 1] = (byte) (i8 >>> 8);
                            return 2;
                        } else {
                            int i9 = (b2[bArr[i5]] & 255) | ((b2[bArr[i2 + 1]] & 255) << 12) | ((b2[bArr[i2]] & 255) << 18) | ((b2[bArr[i7]] & 255) << 6);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0082 -> B:71:0x0082). Please submit an issue!!! */
    public static byte[] decode(String str, int i2) throws IOException {
        byte[] bytes;
        ?? length;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream2;
        if (str != null) {
            try {
                bytes = str.getBytes(CharEncoding.US_ASCII);
            } catch (UnsupportedEncodingException unused) {
                bytes = str.getBytes();
            }
            byte[] decode = decode(bytes, 0, bytes.length, i2);
            boolean z = (i2 & 4) != 0;
            if (decode != null && (length = decode.length) >= 4 && !z && 35615 == ((decode[0] & 255) | ((decode[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
                byte[] bArr = new byte[2048];
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byteArrayInputStream2 = new ByteArrayInputStream(decode);
                            try {
                                gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                                while (true) {
                                    try {
                                        int read = gZIPInputStream.read(bArr);
                                        if (read < 0) {
                                            break;
                                        }
                                        byteArrayOutputStream2.write(bArr, 0, read);
                                    } catch (IOException unused2) {
                                        byteArrayOutputStream = byteArrayOutputStream2;
                                        length = byteArrayInputStream2;
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused3) {
                                        }
                                        gZIPInputStream.close();
                                        try {
                                            length.close();
                                        } catch (Exception unused4) {
                                        }
                                        return decode;
                                    } catch (Throwable th) {
                                        th = th;
                                        byteArrayOutputStream = byteArrayOutputStream2;
                                        byteArrayInputStream = byteArrayInputStream2;
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused5) {
                                        }
                                        try {
                                            gZIPInputStream.close();
                                        } catch (Exception unused6) {
                                        }
                                        try {
                                            byteArrayInputStream.close();
                                        } catch (Exception unused7) {
                                        }
                                        throw th;
                                    }
                                }
                                byteArrayOutputStream2.toByteArray();
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (Exception unused8) {
                                }
                                gZIPInputStream.close();
                            } catch (IOException unused9) {
                                gZIPInputStream = null;
                            } catch (Throwable th2) {
                                th = th2;
                                gZIPInputStream = null;
                            }
                        } catch (IOException unused10) {
                            byteArrayInputStream2 = null;
                            gZIPInputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayInputStream2 = null;
                            gZIPInputStream = null;
                        }
                    } catch (IOException unused11) {
                        length = 0;
                        gZIPInputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        byteArrayInputStream = null;
                        gZIPInputStream = null;
                    }
                } catch (Exception unused12) {
                    length.close();
                    return decode;
                }
            }
            return decode;
        }
        throw new NullPointerException("Input string was null.");
    }
}
