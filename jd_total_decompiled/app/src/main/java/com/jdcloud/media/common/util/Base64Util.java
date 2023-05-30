package com.jdcloud.media.common.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes18.dex */
public class Base64Util {

    /* loaded from: classes18.dex */
    private static class DecInputStream extends InputStream {
        private final int[] base64;
        private final InputStream is;
        private final boolean isMIME;
        private int bits = 0;
        private int nextin = 18;
        private int nextout = -8;
        private boolean eof = false;
        private boolean closed = false;
        private byte[] sbBuf = new byte[1];

        DecInputStream(InputStream inputStream, int[] iArr, boolean z) {
            this.is = inputStream;
            this.base64 = iArr;
            this.isMIME = z;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            if (!this.closed) {
                return this.is.available();
            }
            throw new IOException("Stream is closed");
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.is.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (read(this.sbBuf, 0, 1) == -1) {
                return -1;
            }
            return this.sbBuf[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            int i4;
            if (!this.closed) {
                if (!this.eof || this.nextout >= 0) {
                    if (i2 >= 0 && i3 >= 0 && i3 <= bArr.length - i2) {
                        if (this.nextout >= 0) {
                            int i5 = i2;
                            while (i3 != 0) {
                                i4 = i5 + 1;
                                int i6 = this.bits;
                                int i7 = this.nextout;
                                bArr[i5] = (byte) (i6 >> i7);
                                i3--;
                                int i8 = i7 - 8;
                                this.nextout = i8;
                                if (i8 < 0) {
                                    this.bits = 0;
                                } else {
                                    i5 = i4;
                                }
                            }
                            return i5 - i2;
                        }
                        i4 = i2;
                        while (true) {
                            if (i3 <= 0) {
                                break;
                            }
                            int read = this.is.read();
                            if (read == -1) {
                                this.eof = true;
                                int i9 = this.nextin;
                                if (i9 != 18) {
                                    if (i9 != 12) {
                                        int i10 = i4 + 1;
                                        int i11 = this.bits;
                                        bArr[i4] = (byte) (i11 >> 16);
                                        int i12 = i3 - 1;
                                        if (i9 == 0) {
                                            if (i12 == 0) {
                                                this.bits = i11 >> 8;
                                                this.nextout = 0;
                                            } else {
                                                i4 = i10 + 1;
                                                bArr[i10] = (byte) (i11 >> 8);
                                            }
                                        }
                                        i4 = i10;
                                    } else {
                                        throw new IOException("Base64Util stream has one un-decoded dangling byte.");
                                    }
                                }
                                if (i4 == i2) {
                                    return -1;
                                }
                                return i4 - i2;
                            } else if (read == 61) {
                                int i13 = this.nextin;
                                if (i13 != 18 && i13 != 12 && (i13 != 6 || this.is.read() == 61)) {
                                    int i14 = i4 + 1;
                                    int i15 = this.bits;
                                    bArr[i4] = (byte) (i15 >> 16);
                                    int i16 = i3 - 1;
                                    if (this.nextin == 0) {
                                        if (i16 == 0) {
                                            this.bits = i15 >> 8;
                                            this.nextout = 0;
                                        } else {
                                            bArr[i14] = (byte) (i15 >> 8);
                                            i4 = i14 + 1;
                                            this.eof = true;
                                        }
                                    }
                                    i4 = i14;
                                    this.eof = true;
                                } else {
                                    throw new IOException("Illegal base64 ending sequence:" + this.nextin);
                                }
                            } else {
                                int i17 = this.base64[read];
                                if (i17 == -1) {
                                    if (!this.isMIME) {
                                        throw new IOException("Illegal base64 character " + Integer.toString(i17, 16));
                                    }
                                } else {
                                    int i18 = this.bits;
                                    int i19 = this.nextin;
                                    this.bits = (i17 << i19) | i18;
                                    if (i19 == 0) {
                                        this.nextin = 18;
                                        this.nextout = 16;
                                        while (true) {
                                            int i20 = this.nextout;
                                            if (i20 >= 0) {
                                                int i21 = i4 + 1;
                                                bArr[i4] = (byte) (this.bits >> i20);
                                                i3--;
                                                int i22 = i20 - 8;
                                                this.nextout = i22;
                                                if (i3 == 0 && i22 >= 0) {
                                                    return i21 - i2;
                                                }
                                                i4 = i21;
                                            } else {
                                                this.bits = 0;
                                                break;
                                            }
                                        }
                                    } else {
                                        this.nextin = i19 - 6;
                                    }
                                }
                            }
                        }
                        return i4 - i2;
                    }
                    throw new IndexOutOfBoundsException();
                }
                return -1;
            }
            throw new IOException("Stream is closed");
        }
    }

    /* loaded from: classes18.dex */
    private static class EncOutputStream extends FilterOutputStream {
        private int b0;
        private int b1;
        private int b2;
        private final char[] base64;
        private boolean closed;
        private final boolean doPadding;
        private int leftover;
        private final int linemax;
        private int linepos;
        private final byte[] newline;

        EncOutputStream(OutputStream outputStream, char[] cArr, byte[] bArr, int i2, boolean z) {
            super(outputStream);
            this.leftover = 0;
            this.closed = false;
            this.linepos = 0;
            this.base64 = cArr;
            this.newline = bArr;
            this.linemax = i2;
            this.doPadding = z;
        }

        private void checkNewline() throws IOException {
            if (this.linepos == this.linemax) {
                ((FilterOutputStream) this).out.write(this.newline);
                this.linepos = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            int i2 = this.leftover;
            if (i2 == 1) {
                checkNewline();
                ((FilterOutputStream) this).out.write(this.base64[this.b0 >> 2]);
                ((FilterOutputStream) this).out.write(this.base64[(this.b0 << 4) & 63]);
                if (this.doPadding) {
                    ((FilterOutputStream) this).out.write(61);
                    ((FilterOutputStream) this).out.write(61);
                }
            } else if (i2 == 2) {
                checkNewline();
                ((FilterOutputStream) this).out.write(this.base64[this.b0 >> 2]);
                ((FilterOutputStream) this).out.write(this.base64[((this.b0 << 4) & 63) | (this.b1 >> 4)]);
                ((FilterOutputStream) this).out.write(this.base64[(this.b1 << 2) & 63]);
                if (this.doPadding) {
                    ((FilterOutputStream) this).out.write(61);
                }
            }
            this.leftover = 0;
            ((FilterOutputStream) this).out.close();
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) throws IOException {
            write(new byte[]{(byte) (i2 & 255)}, 0, 1);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (!this.closed) {
                if (i2 < 0 || i3 < 0 || i3 > bArr.length - i2) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                if (i3 == 0) {
                    return;
                }
                int i4 = this.leftover;
                if (i4 != 0) {
                    if (i4 == 1) {
                        int i5 = i2 + 1;
                        this.b1 = bArr[i2] & 255;
                        i3--;
                        if (i3 == 0) {
                            this.leftover = i4 + 1;
                            return;
                        }
                        i2 = i5;
                    }
                    this.b2 = bArr[i2] & 255;
                    i3--;
                    checkNewline();
                    ((FilterOutputStream) this).out.write(this.base64[this.b0 >> 2]);
                    ((FilterOutputStream) this).out.write(this.base64[((this.b0 << 4) & 63) | (this.b1 >> 4)]);
                    ((FilterOutputStream) this).out.write(this.base64[((this.b1 << 2) & 63) | (this.b2 >> 6)]);
                    ((FilterOutputStream) this).out.write(this.base64[this.b2 & 63]);
                    this.linepos += 4;
                    i2++;
                }
                int i6 = i3 / 3;
                this.leftover = i3 - (i6 * 3);
                while (true) {
                    int i7 = i6 - 1;
                    if (i6 <= 0) {
                        break;
                    }
                    checkNewline();
                    int i8 = i2 + 1;
                    int i9 = i8 + 1;
                    int i10 = ((bArr[i2] & 255) << 16) | ((bArr[i8] & 255) << 8) | (bArr[i9] & 255);
                    ((FilterOutputStream) this).out.write(this.base64[(i10 >>> 18) & 63]);
                    ((FilterOutputStream) this).out.write(this.base64[(i10 >>> 12) & 63]);
                    ((FilterOutputStream) this).out.write(this.base64[(i10 >>> 6) & 63]);
                    ((FilterOutputStream) this).out.write(this.base64[i10 & 63]);
                    this.linepos += 4;
                    i2 = i9 + 1;
                    i6 = i7;
                }
                int i11 = this.leftover;
                if (i11 == 1) {
                    this.b0 = bArr[i2] & 255;
                    return;
                } else if (i11 == 2) {
                    this.b0 = bArr[i2] & 255;
                    this.b1 = bArr[i2 + 1] & 255;
                    return;
                } else {
                    return;
                }
            }
            throw new IOException("Stream is closed");
        }
    }

    /* loaded from: classes18.dex */
    public static class Encoder {
        private static final byte[] CRLF;
        private static final int MIMELINEMAX = 76;
        static final Encoder RFC2045;
        private final boolean doPadding;
        private final boolean isURL;
        private final int linemax;
        private final byte[] newline;
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        static final Encoder RFC4648 = new Encoder(false, null, -1, true);
        static final Encoder RFC4648_URLSAFE = new Encoder(true, null, -1, true);

        static {
            byte[] bArr = {13, 10};
            CRLF = bArr;
            RFC2045 = new Encoder(false, bArr, 76, true);
        }

        private int encode0(byte[] bArr, int i2, int i3, byte[] bArr2) {
            char[] cArr = this.isURL ? toBase64URL : toBase64;
            int i4 = ((i3 - i2) / 3) * 3;
            int i5 = i2 + i4;
            int i6 = this.linemax;
            if (i6 > 0 && i4 > (i6 / 4) * 3) {
                i4 = (i6 / 4) * 3;
            }
            int i7 = 0;
            while (i2 < i5) {
                int min = Math.min(i2 + i4, i5);
                int i8 = i2;
                int i9 = i7;
                while (i8 < min) {
                    int i10 = i8 + 1;
                    int i11 = i10 + 1;
                    int i12 = ((bArr[i8] & 255) << 16) | ((bArr[i10] & 255) << 8);
                    int i13 = i11 + 1;
                    int i14 = i12 | (bArr[i11] & 255);
                    int i15 = i9 + 1;
                    bArr2[i9] = (byte) cArr[(i14 >>> 18) & 63];
                    int i16 = i15 + 1;
                    bArr2[i15] = (byte) cArr[(i14 >>> 12) & 63];
                    int i17 = i16 + 1;
                    bArr2[i16] = (byte) cArr[(i14 >>> 6) & 63];
                    i9 = i17 + 1;
                    bArr2[i17] = (byte) cArr[i14 & 63];
                    i8 = i13;
                }
                int i18 = ((min - i2) / 3) * 4;
                i7 += i18;
                if (i18 == this.linemax && min < i3) {
                    byte[] bArr3 = this.newline;
                    int length = bArr3.length;
                    int i19 = 0;
                    while (i19 < length) {
                        bArr2[i7] = bArr3[i19];
                        i19++;
                        i7++;
                    }
                }
                i2 = min;
            }
            if (i2 < i3) {
                int i20 = i2 + 1;
                int i21 = bArr[i2] & 255;
                int i22 = i7 + 1;
                bArr2[i7] = (byte) cArr[i21 >> 2];
                if (i20 == i3) {
                    int i23 = i22 + 1;
                    bArr2[i22] = (byte) cArr[(i21 << 4) & 63];
                    if (this.doPadding) {
                        int i24 = i23 + 1;
                        bArr2[i23] = 61;
                        int i25 = i24 + 1;
                        bArr2[i24] = 61;
                        return i25;
                    }
                    return i23;
                }
                int i26 = bArr[i20] & 255;
                int i27 = i22 + 1;
                bArr2[i22] = (byte) cArr[((i21 << 4) & 63) | (i26 >> 4)];
                int i28 = i27 + 1;
                bArr2[i27] = (byte) cArr[(i26 << 2) & 63];
                if (this.doPadding) {
                    int i29 = i28 + 1;
                    bArr2[i28] = 61;
                    return i29;
                }
                return i28;
            }
            return i7;
        }

        private final int outLength(int i2) {
            int i3;
            if (this.doPadding) {
                i3 = ((i2 + 2) / 3) * 4;
            } else {
                int i4 = i2 % 3;
                i3 = ((i2 / 3) * 4) + (i4 == 0 ? 0 : i4 + 1);
            }
            int i5 = this.linemax;
            return i5 > 0 ? i3 + (((i3 - 1) / i5) * this.newline.length) : i3;
        }

        public byte[] encode(byte[] bArr) {
            int outLength = outLength(bArr.length);
            byte[] bArr2 = new byte[outLength];
            int encode0 = encode0(bArr, 0, bArr.length, bArr2);
            return encode0 != outLength ? Arrays.copyOf(bArr2, encode0) : bArr2;
        }

        public String encodeToString(byte[] bArr) {
            byte[] encode = encode(bArr);
            return new String(encode, 0, 0, encode.length);
        }

        public Encoder withoutPadding() {
            return !this.doPadding ? this : new Encoder(this.isURL, this.newline, this.linemax, false);
        }

        public OutputStream wrap(OutputStream outputStream) {
            outputStream.getClass();
            return new EncOutputStream(outputStream, this.isURL ? toBase64URL : toBase64, this.newline, this.linemax, this.doPadding);
        }

        private Encoder(boolean z, byte[] bArr, int i2, boolean z2) {
            this.isURL = z;
            this.newline = bArr;
            this.linemax = i2;
            this.doPadding = z2;
        }

        public int encode(byte[] bArr, byte[] bArr2) {
            if (bArr2.length >= outLength(bArr.length)) {
                return encode0(bArr, 0, bArr.length, bArr2);
            }
            throw new IllegalArgumentException("Output byte array is too small for encoding all input bytes");
        }

        public ByteBuffer encode(ByteBuffer byteBuffer) {
            int encode0;
            int outLength = outLength(byteBuffer.remaining());
            byte[] bArr = new byte[outLength];
            if (byteBuffer.hasArray()) {
                encode0 = encode0(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.arrayOffset() + byteBuffer.limit(), bArr);
                byteBuffer.position(byteBuffer.limit());
            } else {
                int remaining = byteBuffer.remaining();
                byte[] bArr2 = new byte[remaining];
                byteBuffer.get(bArr2);
                encode0 = encode0(bArr2, 0, remaining, bArr);
            }
            if (encode0 != outLength) {
                bArr = Arrays.copyOf(bArr, encode0);
            }
            return ByteBuffer.wrap(bArr);
        }
    }

    private Base64Util() {
    }

    public static Decoder getDecoder() {
        return Decoder.RFC4648;
    }

    public static Encoder getEncoder() {
        return Encoder.RFC4648;
    }

    public static Decoder getMimeDecoder() {
        return Decoder.RFC2045;
    }

    public static Encoder getMimeEncoder() {
        return Encoder.RFC2045;
    }

    public static Decoder getUrlDecoder() {
        return Decoder.RFC4648_URLSAFE;
    }

    public static Encoder getUrlEncoder() {
        return Encoder.RFC4648_URLSAFE;
    }

    public static Encoder getMimeEncoder(int i2, byte[] bArr) {
        bArr.getClass();
        int[] iArr = Decoder.fromBase64;
        for (byte b : bArr) {
            if (iArr[b & 255] != -1) {
                throw new IllegalArgumentException("Illegal base64 line separator character 0x" + Integer.toString(b, 16));
            }
        }
        if (i2 <= 0) {
            return Encoder.RFC4648;
        }
        return new Encoder(false, bArr, (i2 >> 2) << 2, true);
    }

    /* loaded from: classes18.dex */
    public static class Decoder {
        static final Decoder RFC2045;
        static final Decoder RFC4648;
        static final Decoder RFC4648_URLSAFE;
        private static final int[] fromBase64;
        private static final int[] fromBase64URL;
        private final boolean isMIME;
        private final boolean isURL;

        static {
            int[] iArr = new int[256];
            fromBase64 = iArr;
            Arrays.fill(iArr, -1);
            for (int i2 = 0; i2 < Encoder.toBase64.length; i2++) {
                fromBase64[Encoder.toBase64[i2]] = i2;
            }
            fromBase64[61] = -2;
            int[] iArr2 = new int[256];
            fromBase64URL = iArr2;
            Arrays.fill(iArr2, -1);
            for (int i3 = 0; i3 < Encoder.toBase64URL.length; i3++) {
                fromBase64URL[Encoder.toBase64URL[i3]] = i3;
            }
            fromBase64URL[61] = -2;
            RFC4648 = new Decoder(false, false);
            RFC4648_URLSAFE = new Decoder(true, false);
            RFC2045 = new Decoder(false, true);
        }

        private Decoder(boolean z, boolean z2) {
            this.isURL = z;
            this.isMIME = z2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
            if (r11[r8] == 61) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0030, code lost:
            if (r4 != 18) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
            if (r4 != 6) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0081, code lost:
            r14[r5] = (byte) (r3 >> 16);
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
            if (r4 != 0) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x008c, code lost:
            r1 = r5 + 1;
            r14[r5] = (byte) (r3 >> 16);
            r5 = r1 + 1;
            r14[r1] = (byte) (r3 >> 8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x009d, code lost:
            if (r4 == 12) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x009f, code lost:
            if (r12 >= r13) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00a3, code lost:
            if (r10.isMIME == false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a5, code lost:
            r14 = r12 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00ab, code lost:
            if (r0[r11[r12]] >= 0) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00ad, code lost:
            r12 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00af, code lost:
            r12 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00c6, code lost:
            throw new java.lang.IllegalArgumentException("Input byte array has incorrect ending byte at " + r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00c7, code lost:
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00d0, code lost:
            throw new java.lang.IllegalArgumentException("Last unit does not have enough valid bits");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private int decode0(byte[] bArr, int i2, int i3, byte[] bArr2) {
            int[] iArr = this.isURL ? fromBase64URL : fromBase64;
            int i4 = 0;
            int i5 = 18;
            int i6 = 0;
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                int i7 = i2 + 1;
                int i8 = iArr[bArr[i2] & 255];
                if (i8 >= 0) {
                    int i9 = (i8 << i5) | i4;
                    i5 -= 6;
                    if (i5 < 0) {
                        int i10 = i6 + 1;
                        bArr2[i6] = (byte) (i9 >> 16);
                        int i11 = i10 + 1;
                        bArr2[i10] = (byte) (i9 >> 8);
                        i6 = i11 + 1;
                        bArr2[i11] = (byte) i9;
                        i4 = 0;
                        i5 = 18;
                    } else {
                        i4 = i9;
                    }
                } else if (i8 == -2) {
                    if (i5 == 6) {
                        if (i7 != i3) {
                            i2 = i7 + 1;
                        }
                        throw new IllegalArgumentException("Input byte array has wrong 4-byte ending unit");
                    }
                    i2 = i7;
                } else if (!this.isMIME) {
                    throw new IllegalArgumentException("Illegal base64 character " + Integer.toString(bArr[i7 - 1], 16));
                }
                i2 = i7;
            }
        }

        private int outLength(byte[] bArr, int i2, int i3) {
            int i4;
            int[] iArr = this.isURL ? fromBase64URL : fromBase64;
            int i5 = i3 - i2;
            int i6 = 0;
            if (i5 == 0) {
                return 0;
            }
            if (i5 < 2) {
                if (this.isMIME && iArr[0] == -1) {
                    return 0;
                }
                throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
            }
            if (this.isMIME) {
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int i8 = i2 + 1;
                    int i9 = bArr[i2] & 255;
                    if (i9 == 61) {
                        i5 -= (i3 - i8) + 1;
                        break;
                    }
                    if (iArr[i9] == -1) {
                        i7++;
                    }
                    i2 = i8;
                }
                i5 -= i7;
            } else if (bArr[i3 - 1] == 61) {
                i6 = bArr[i3 - 2] == 61 ? 2 : 1;
            }
            if (i6 == 0 && (i4 = i5 & 3) != 0) {
                i6 = 4 - i4;
            }
            return (((i5 + 3) / 4) * 3) - i6;
        }

        public byte[] decode(byte[] bArr) {
            int outLength = outLength(bArr, 0, bArr.length);
            byte[] bArr2 = new byte[outLength];
            int decode0 = decode0(bArr, 0, bArr.length, bArr2);
            return decode0 != outLength ? Arrays.copyOf(bArr2, decode0) : bArr2;
        }

        public InputStream wrap(InputStream inputStream) {
            inputStream.getClass();
            return new DecInputStream(inputStream, this.isURL ? fromBase64URL : fromBase64, this.isMIME);
        }

        public byte[] decode(String str) {
            return decode(str.getBytes(StandardCharsets.ISO_8859_1));
        }

        public int decode(byte[] bArr, byte[] bArr2) {
            if (bArr2.length >= outLength(bArr, 0, bArr.length)) {
                return decode0(bArr, 0, bArr.length, bArr2);
            }
            throw new IllegalArgumentException("Output byte array is too small for decoding all input bytes");
        }

        public ByteBuffer decode(ByteBuffer byteBuffer) {
            int remaining;
            byte[] bArr;
            int i2;
            int position = byteBuffer.position();
            try {
                if (byteBuffer.hasArray()) {
                    bArr = byteBuffer.array();
                    i2 = byteBuffer.arrayOffset() + byteBuffer.position();
                    remaining = byteBuffer.arrayOffset() + byteBuffer.limit();
                    byteBuffer.position(byteBuffer.limit());
                } else {
                    remaining = byteBuffer.remaining();
                    bArr = new byte[remaining];
                    byteBuffer.get(bArr);
                    i2 = 0;
                }
                byte[] bArr2 = new byte[outLength(bArr, i2, remaining)];
                return ByteBuffer.wrap(bArr2, 0, decode0(bArr, i2, remaining, bArr2));
            } catch (IllegalArgumentException e2) {
                byteBuffer.position(position);
                throw e2;
            }
        }
    }
}
