package com.jingdong.sdk.utils.c;

import androidx.core.view.MotionEventCompat;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes10.dex */
public class a {
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f15571c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f15572e = {45, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f15573f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, -9, -9, -9, -9, ReplyCode.reply0x25, -9, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: com.jingdong.sdk.utils.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0749a extends FilterOutputStream {

        /* renamed from: g  reason: collision with root package name */
        private boolean f15574g;

        /* renamed from: h  reason: collision with root package name */
        private int f15575h;

        /* renamed from: i  reason: collision with root package name */
        private byte[] f15576i;

        /* renamed from: j  reason: collision with root package name */
        private int f15577j;

        /* renamed from: k  reason: collision with root package name */
        private int f15578k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f15579l;

        /* renamed from: m  reason: collision with root package name */
        private byte[] f15580m;

        /* renamed from: n  reason: collision with root package name */
        private boolean f15581n;
        private int o;
        private byte[] p;

        public C0749a(OutputStream outputStream, int i2) {
            super(outputStream);
            this.f15579l = (i2 & 8) != 0;
            boolean z = (i2 & 1) != 0;
            this.f15574g = z;
            int i3 = z ? 3 : 4;
            this.f15577j = i3;
            this.f15576i = new byte[i3];
            this.f15575h = 0;
            this.f15578k = 0;
            this.f15581n = false;
            this.f15580m = new byte[4];
            this.o = i2;
            this.p = a.o(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            f();
            super.close();
            this.f15576i = null;
            ((FilterOutputStream) this).out = null;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jingdong.sdk.utils.c.a.c(byte[], byte[], int, int):byte[]
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
        public void f() {
            /*
                r5 = this;
                int r0 = r5.f15575h
                if (r0 <= 0) goto L22
                boolean r1 = r5.f15574g
                if (r1 == 0) goto L1a
                java.io.OutputStream r1 = r5.out
                byte[] r2 = r5.f15580m
                byte[] r3 = r5.f15576i
                int r4 = r5.o
                com.jingdong.sdk.utils.c.a.c(r2, r3, r0, r4)
                r1.write(r2)
                r0 = 0
                r5.f15575h = r0
                goto L22
            L1a:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Base64 input not properly padded."
                r0.<init>(r1)
                throw r0
            L22:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.utils.c.a.C0749a.f():void");
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jingdong.sdk.utils.c.a.c(byte[], byte[], int, int):byte[]
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
        public void write(int r6) {
            /*
                r5 = this;
                boolean r0 = r5.f15581n
                if (r0 == 0) goto La
                java.io.OutputStream r0 = r5.out
                r0.write(r6)
                return
            La:
                boolean r0 = r5.f15574g
                r1 = 0
                if (r0 == 0) goto L42
                byte[] r0 = r5.f15576i
                int r2 = r5.f15575h
                int r3 = r2 + 1
                r5.f15575h = r3
                byte r6 = (byte) r6
                r0[r2] = r6
                int r6 = r5.f15577j
                if (r3 < r6) goto L70
                java.io.OutputStream r2 = r5.out
                byte[] r3 = r5.f15580m
                int r4 = r5.o
                com.jingdong.sdk.utils.c.a.c(r3, r0, r6, r4)
                r2.write(r3)
                int r6 = r5.f15578k
                int r6 = r6 + 4
                r5.f15578k = r6
                boolean r0 = r5.f15579l
                if (r0 == 0) goto L69
                r0 = 76
                if (r6 < r0) goto L69
                java.io.OutputStream r6 = r5.out
                r0 = 10
                r6.write(r0)
                r5.f15578k = r1
                goto L69
            L42:
                byte[] r0 = r5.p
                r2 = r6 & 127(0x7f, float:1.78E-43)
                r3 = r0[r2]
                r4 = -5
                if (r3 <= r4) goto L6c
                byte[] r0 = r5.f15576i
                int r2 = r5.f15575h
                int r3 = r2 + 1
                r5.f15575h = r3
                byte r6 = (byte) r6
                r0[r2] = r6
                int r6 = r5.f15577j
                if (r3 < r6) goto L70
                byte[] r6 = r5.f15580m
                int r2 = r5.o
                int r6 = com.jingdong.sdk.utils.c.a.b(r0, r1, r6, r1, r2)
                java.io.OutputStream r0 = r5.out
                byte[] r2 = r5.f15580m
                r0.write(r2, r1, r6)
            L69:
                r5.f15575h = r1
                goto L70
            L6c:
                r6 = r0[r2]
                if (r6 != r4) goto L71
            L70:
                return
            L71:
                java.io.IOException r6 = new java.io.IOException
                java.lang.String r0 = "Invalid character in Base64 data."
                r6.<init>(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.utils.c.a.C0749a.write(int):void");
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            if (this.f15581n) {
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }
    }

    static /* synthetic */ int b(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        return h(bArr, i2, bArr2, i3, i4);
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
            j(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.utils.c.a.c(byte[], byte[], int, int):byte[]");
    }

    public static byte[] d(String str) {
        return e(str, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x008c, code lost:
        if (r3 == null) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] e(String str, int i2) {
        byte[] bytes;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        if (str != null) {
            try {
                bytes = str.getBytes(CharEncoding.US_ASCII);
            } catch (UnsupportedEncodingException unused) {
                bytes = str.getBytes();
            }
            byte[] g2 = g(bytes, 0, bytes.length, i2);
            boolean z = (i2 & 4) != 0;
            if (g2 != null && g2.length >= 4 && !z && 35615 == ((g2[0] & 255) | ((g2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
                byte[] bArr = new byte[2048];
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(g2);
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
                            g2 = byteArrayOutputStream2.toByteArray();
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
            return g2;
        }
        throw new NullPointerException("Input string was null.");
    }

    public static byte[] f(byte[] bArr) {
        return g(bArr, 0, bArr.length, 0);
    }

    public static byte[] g(byte[] bArr, int i2, int i3, int i4) {
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
            byte[] o = o(i4);
            byte[] bArr2 = new byte[(i3 * 3) / 4];
            byte[] bArr3 = new byte[4];
            int i6 = 0;
            int i7 = 0;
            while (i2 < i5) {
                byte b2 = o[bArr[i2] & 255];
                if (b2 < -5) {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i2] & 255), Integer.valueOf(i2)));
                }
                if (b2 >= -1) {
                    int i8 = i6 + 1;
                    bArr3[i6] = bArr[i2];
                    if (i8 > 3) {
                        i7 += h(bArr3, 0, bArr2, i7, i4);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static int h(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
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
                byte[] o = o(i4);
                int i7 = i2 + 2;
                if (bArr[i7] == 61) {
                    bArr2[i3] = (byte) ((((o[bArr[i2 + 1]] & 255) << 12) | ((o[bArr[i2]] & 255) << 18)) >>> 16);
                    return 1;
                } else if (bArr[i5] == 61) {
                    int i8 = ((o[bArr[i7]] & 255) << 6) | ((o[bArr[i2 + 1]] & 255) << 12) | ((o[bArr[i2]] & 255) << 18);
                    bArr2[i3] = (byte) (i8 >>> 16);
                    bArr2[i3 + 1] = (byte) (i8 >>> 8);
                    return 2;
                } else {
                    int i9 = (o[bArr[i5]] & 255) | ((o[bArr[i2 + 1]] & 255) << 12) | ((o[bArr[i2]] & 255) << 18) | ((o[bArr[i7]] & 255) << 6);
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

    private static byte[] i(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] n2 = n(i5);
        int i6 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0) | (i3 > 2 ? (bArr[i2 + 2] << 24) >>> 24 : 0);
        if (i3 == 1) {
            bArr2[i4] = n2[i6 >>> 18];
            bArr2[i4 + 1] = n2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = 61;
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 == 2) {
            bArr2[i4] = n2[i6 >>> 18];
            bArr2[i4 + 1] = n2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = n2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = 61;
            return bArr2;
        } else if (i3 != 3) {
            return bArr2;
        } else {
            bArr2[i4] = n2[i6 >>> 18];
            bArr2[i4 + 1] = n2[(i6 >>> 12) & 63];
            bArr2[i4 + 2] = n2[(i6 >>> 6) & 63];
            bArr2[i4 + 3] = n2[i6 & 63];
            return bArr2;
        }
    }

    private static byte[] j(byte[] bArr, byte[] bArr2, int i2, int i3) {
        i(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    public static String k(byte[] bArr) {
        try {
            return l(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String l(byte[] bArr, int i2, int i3, int i4) {
        byte[] m2 = m(bArr, i2, i3, i4);
        try {
            return new String(m2, CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException unused) {
            return new String(m2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] m(byte[] bArr, int i2, int i3, int i4) {
        ByteArrayOutputStream byteArrayOutputStream;
        C0749a c0749a;
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i2);
        } else if (i3 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i3);
        } else if (i2 + i3 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
        } else {
            if ((i4 & 2) == 0) {
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
                    i(bArr, i8 + i2, 3, bArr2, i9, i4);
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
                    i(bArr, i13 + i2, i3 - i13, bArr2, i9, i4);
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
            GZIPOutputStream gZIPOutputStream2 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    c0749a = new C0749a(byteArrayOutputStream, i4 | 1);
                } catch (IOException e2) {
                    e = e2;
                    c0749a = null;
                    gZIPOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    c0749a = null;
                }
            } catch (IOException e3) {
                e = e3;
                c0749a = null;
                gZIPOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = 0;
                c0749a = null;
            }
            try {
                gZIPOutputStream = new GZIPOutputStream(c0749a);
                try {
                    gZIPOutputStream.write(bArr, i2, i3);
                    gZIPOutputStream.close();
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused) {
                    }
                    try {
                        c0749a.close();
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
                            c0749a.close();
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
                    c0749a.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                gZIPOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream2.close();
                c0749a.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
    }

    private static final byte[] n(int i2) {
        return (i2 & 16) == 16 ? f15571c : (i2 & 32) == 32 ? f15572e : a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] o(int i2) {
        return (i2 & 16) == 16 ? d : (i2 & 32) == 32 ? f15573f : b;
    }
}
