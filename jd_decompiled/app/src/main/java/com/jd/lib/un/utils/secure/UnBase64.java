package com.jd.lib.un.utils.secure;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes16.dex */
public class UnBase64 {
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
    private static final String TAG = "UnBase64";
    public static final int URL_SAFE = 16;
    private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN = 61;
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _ORDERED_ALPHABET = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};
    private static final byte[] _ORDERED_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, EQUALS_SIGN, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* loaded from: classes16.dex */
    public static class InputStream extends FilterInputStream {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read;
            if (this.position < 0) {
                if (this.encode) {
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
                    UnBase64.encode3to4(bArr, 0, i2, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i4 = 0;
                    while (i4 < 4) {
                        do {
                            read = ((FilterInputStream) this).in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.decodabet[read & 127] <= -5);
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
                        throw new IOException("Improperly padded UnBase64 input.");
                    }
                    this.numSigBytes = UnBase64.decode4to3(bArr2, 0, this.buffer, 0, this.options);
                    this.position = 0;
                }
            }
            int i5 = this.position;
            if (i5 >= 0) {
                if (i5 >= this.numSigBytes) {
                    return -1;
                }
                if (this.encode && this.breakLines && this.lineLength >= 76) {
                    this.lineLength = 0;
                    return 10;
                }
                this.lineLength++;
                byte[] bArr3 = this.buffer;
                int i6 = i5 + 1;
                this.position = i6;
                byte b = bArr3[i5];
                if (i6 >= this.bufferLength) {
                    this.position = -1;
                }
                return b & 255;
            }
            throw new IOException("Error in UnBase64 code reading stream.");
        }

        public InputStream(java.io.InputStream inputStream, int i2) {
            super(inputStream);
            this.options = i2;
            this.breakLines = (i2 & 8) > 0;
            boolean z = (i2 & 1) > 0;
            this.encode = z;
            int i3 = z ? 4 : 3;
            this.bufferLength = i3;
            this.buffer = new byte[i3];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = UnBase64.getDecodabet(i2);
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

    /* loaded from: classes16.dex */
    public static class OutputStream extends FilterOutputStream {
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            ((FilterOutputStream) this).out = null;
        }

        public void flushBase64() throws IOException {
            int i2 = this.position;
            if (i2 > 0) {
                if (this.encode) {
                    ((FilterOutputStream) this).out.write(UnBase64.encode3to4(this.b4, this.buffer, i2, this.options));
                    this.position = 0;
                    return;
                }
                throw new IOException("UnBase64 input not properly padded.");
            }
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) throws IOException {
            if (this.suspendEncoding) {
                ((FilterOutputStream) this).out.write(i2);
            } else if (this.encode) {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                int i4 = i3 + 1;
                this.position = i4;
                bArr[i3] = (byte) i2;
                int i5 = this.bufferLength;
                if (i4 >= i5) {
                    ((FilterOutputStream) this).out.write(UnBase64.encode3to4(this.b4, bArr, i5, this.options));
                    int i6 = this.lineLength + 4;
                    this.lineLength = i6;
                    if (this.breakLines && i6 >= 76) {
                        ((FilterOutputStream) this).out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else {
                byte[] bArr2 = this.decodabet;
                int i7 = i2 & 127;
                if (bArr2[i7] > -5) {
                    byte[] bArr3 = this.buffer;
                    int i8 = this.position;
                    int i9 = i8 + 1;
                    this.position = i9;
                    bArr3[i8] = (byte) i2;
                    if (i9 >= this.bufferLength) {
                        ((FilterOutputStream) this).out.write(this.b4, 0, UnBase64.decode4to3(bArr3, 0, this.b4, 0, this.options));
                        this.position = 0;
                    }
                } else if (bArr2[i7] != -5) {
                    throw new IOException("Invalid character in UnBase64 data.");
                }
            }
        }

        public OutputStream(java.io.OutputStream outputStream, int i2) {
            super(outputStream);
            this.breakLines = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.encode = z;
            int i3 = z ? 3 : 4;
            this.bufferLength = i3;
            this.buffer = new byte[i3];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = i2;
            this.decodabet = UnBase64.getDecodabet(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.suspendEncoding) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }

    private UnBase64() {
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
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception unused) {
                                }
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
                    outputStream2.write(str.getBytes("US-ASCII"));
                    try {
                        outputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e2) {
                } catch (Throwable th) {
                    th = th;
                    outputStream = outputStream2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
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
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        throw th;
                    }
                }
                String str2 = new String(bArr, 0, i2, "US-ASCII");
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
                        if (outputStream != null) {
                            try {
                                outputStream.close();
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
                    byte b = decodabet[bArr[i2] & 255];
                    if (b < -5) {
                        throw new IOException(String.format("Bad UnBase64 input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                    }
                    if (b >= -1) {
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
            throw new IllegalArgumentException("UnBase64-encoded string must have at least four characters, but length specified was " + i3);
        }
        throw new NullPointerException("Cannot decode null source array.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    public static Object decodeToObject(String str, int i2, final ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        byte[] decode = decode(str, i2);
        ByteArrayInputStream byteArrayInputStream2 = null;
        r2 = null;
        r2 = null;
        ObjectInputStream objectInputStream2 = null;
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
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } else {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream) { // from class: com.jd.lib.un.utils.secure.UnBase64.1
                        @Override // java.io.ObjectInputStream
                        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                            Class<?> cls = Class.forName(objectStreamClass.getName(), false, classLoader);
                            return cls == null ? super.resolveClass(objectStreamClass) : cls;
                        }
                    };
                }
                objectInputStream2 = objectInputStream;
                Object readObject = objectInputStream2.readObject();
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    objectInputStream2.close();
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
                classLoader = objectInputStream2;
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
    /* JADX WARN: Removed duplicated region for block: B:101:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] encodeBytesToBytes(byte[] r18, int r19, int r20, int r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.secure.UnBase64.encodeBytesToBytes(byte[], int, int, int):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String encodeObject(java.io.Serializable r5, int r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L98
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L77
            r1.<init>()     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L77
            com.jd.lib.un.utils.secure.UnBase64$OutputStream r2 = new com.jd.lib.un.utils.secure.UnBase64$OutputStream     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L6c
            r3 = r6 | 1
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L6c
            r6 = r6 & 2
            if (r6 == 0) goto L2b
            java.util.zip.GZIPOutputStream r6 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L61
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L61
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L25
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L25
            r0 = r3
            goto L33
        L1f:
            r5 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L7d
        L25:
            r5 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L7b
        L2b:
            java.io.ObjectOutputStream r6 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L61
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L61
            r4 = r0
            r0 = r6
            r6 = r4
        L33:
            r0.writeObject(r5)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L25
            r1.close()     // Catch: java.lang.Exception -> L39
        L39:
            r2.close()     // Catch: java.lang.Exception -> L3d
            goto L3e
        L3d:
        L3e:
            if (r6 == 0) goto L43
            r6.close()     // Catch: java.lang.Exception -> L43
        L43:
            r0.close()     // Catch: java.lang.Exception -> L46
        L46:
            java.lang.String r5 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L52
            byte[] r6 = r1.toByteArray()     // Catch: java.io.UnsupportedEncodingException -> L52
            java.lang.String r0 = "US-ASCII"
            r5.<init>(r6, r0)     // Catch: java.io.UnsupportedEncodingException -> L52
            return r5
        L52:
            java.lang.String r5 = new java.lang.String
            byte[] r6 = r1.toByteArray()
            r5.<init>(r6)
            return r5
        L5c:
            r5 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
            goto L7d
        L61:
            r5 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
            goto L7b
        L66:
            r5 = move-exception
            r6 = r0
            r2 = r6
            r0 = r1
            r1 = r2
            goto L7d
        L6c:
            r5 = move-exception
            r6 = r0
            r2 = r6
            r0 = r1
            r1 = r2
            goto L7b
        L72:
            r5 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
            goto L7d
        L77:
            r5 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
        L7b:
            throw r5     // Catch: java.lang.Throwable -> L7c
        L7c:
            r5 = move-exception
        L7d:
            if (r0 == 0) goto L84
            r0.close()     // Catch: java.lang.Exception -> L83
            goto L84
        L83:
        L84:
            if (r2 == 0) goto L8b
            r2.close()     // Catch: java.lang.Exception -> L8a
            goto L8b
        L8a:
        L8b:
            if (r6 == 0) goto L92
            r6.close()     // Catch: java.lang.Exception -> L91
            goto L92
        L91:
        L92:
            if (r1 == 0) goto L97
            r1.close()     // Catch: java.lang.Exception -> L97
        L97:
            throw r5
        L98:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Cannot serialize a null object."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.secure.UnBase64.encodeObject(java.io.Serializable, int):java.lang.String");
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

    public static byte[] decode(String str) throws IOException {
        return decode(str, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0093, code lost:
        if (r3 == null) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 182
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.secure.UnBase64.decode(java.lang.String, int):byte[]");
    }
}
