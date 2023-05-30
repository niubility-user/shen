package jd.wjweblogin.d;

import java.io.UnsupportedEncodingException;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes11.dex */
public class a {
    public static final int a = 0;
    public static final int b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f20029c = 2;
    public static final int d = 4;

    /* renamed from: e  reason: collision with root package name */
    public static final int f20030e = 8;

    /* renamed from: f  reason: collision with root package name */
    public static final int f20031f = 16;

    /* renamed from: g  reason: collision with root package name */
    static final /* synthetic */ boolean f20032g = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: jd.wjweblogin.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static abstract class AbstractC0853a {
        public byte[] a;
        public int b;

        AbstractC0853a() {
        }

        public abstract int a(int i2);

        public abstract boolean a(byte[] bArr, int i2, int i3, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class b extends AbstractC0853a {

        /* renamed from: f  reason: collision with root package name */
        private static final int[] f20033f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: g  reason: collision with root package name */
        private static final int[] f20034g = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: h  reason: collision with root package name */
        private static final int f20035h = -1;

        /* renamed from: i  reason: collision with root package name */
        private static final int f20036i = -2;

        /* renamed from: c  reason: collision with root package name */
        private int f20037c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private final int[] f20038e;

        public b(int i2, byte[] bArr) {
            this.a = bArr;
            this.f20038e = (i2 & 8) == 0 ? f20033f : f20034g;
            this.f20037c = 0;
            this.d = 0;
        }

        @Override // jd.wjweblogin.d.a.AbstractC0853a
        public int a(int i2) {
            return ((i2 * 3) / 4) + 10;
        }

        @Override // jd.wjweblogin.d.a.AbstractC0853a
        public boolean a(byte[] bArr, int i2, int i3, boolean z) {
            int i4 = this.f20037c;
            if (i4 == 6) {
                return false;
            }
            int i5 = i3 + i2;
            int i6 = this.d;
            byte[] bArr2 = this.a;
            int[] iArr = this.f20038e;
            int i7 = i6;
            int i8 = 0;
            int i9 = i4;
            int i10 = i2;
            while (i10 < i5) {
                if (i9 == 0) {
                    while (true) {
                        int i11 = i10 + 4;
                        if (i11 > i5 || (i7 = (iArr[bArr[i10] & 255] << 18) | (iArr[bArr[i10 + 1] & 255] << 12) | (iArr[bArr[i10 + 2] & 255] << 6) | iArr[bArr[i10 + 3] & 255]) < 0) {
                            break;
                        }
                        bArr2[i8 + 2] = (byte) i7;
                        bArr2[i8 + 1] = (byte) (i7 >> 8);
                        bArr2[i8] = (byte) (i7 >> 16);
                        i8 += 3;
                        i10 = i11;
                    }
                    if (i10 >= i5) {
                        break;
                    }
                }
                int i12 = i10 + 1;
                int i13 = iArr[bArr[i10] & 255];
                if (i9 != 0) {
                    if (i9 == 1) {
                        if (i13 < 0) {
                            if (i13 != -1) {
                                this.f20037c = 6;
                                return false;
                            }
                        }
                        i13 |= i7 << 6;
                    } else if (i9 == 2) {
                        if (i13 < 0) {
                            if (i13 == -2) {
                                bArr2[i8] = (byte) (i7 >> 4);
                                i8++;
                                i9 = 4;
                            } else if (i13 != -1) {
                                this.f20037c = 6;
                                return false;
                            }
                        }
                        i13 |= i7 << 6;
                    } else if (i9 != 3) {
                        if (i9 != 4) {
                            if (i9 == 5 && i13 != -1) {
                                this.f20037c = 6;
                                return false;
                            }
                        } else if (i13 == -2) {
                            i9++;
                        } else if (i13 != -1) {
                            this.f20037c = 6;
                            return false;
                        }
                    } else if (i13 >= 0) {
                        int i14 = i13 | (i7 << 6);
                        bArr2[i8 + 2] = (byte) i14;
                        bArr2[i8 + 1] = (byte) (i14 >> 8);
                        bArr2[i8] = (byte) (i14 >> 16);
                        i8 += 3;
                        i7 = i14;
                        i9 = 0;
                    } else if (i13 == -2) {
                        bArr2[i8 + 1] = (byte) (i7 >> 2);
                        bArr2[i8] = (byte) (i7 >> 10);
                        i8 += 2;
                        i9 = 5;
                    } else if (i13 != -1) {
                        this.f20037c = 6;
                        return false;
                    }
                    i9++;
                    i7 = i13;
                } else {
                    if (i13 < 0) {
                        if (i13 != -1) {
                            this.f20037c = 6;
                            return false;
                        }
                    }
                    i9++;
                    i7 = i13;
                }
                i10 = i12;
            }
            if (!z) {
                this.f20037c = i9;
                this.d = i7;
                this.b = i8;
                return true;
            } else if (i9 != 1) {
                if (i9 == 2) {
                    bArr2[i8] = (byte) (i7 >> 4);
                    i8++;
                } else if (i9 == 3) {
                    int i15 = i8 + 1;
                    bArr2[i8] = (byte) (i7 >> 10);
                    i8 = i15 + 1;
                    bArr2[i15] = (byte) (i7 >> 2);
                } else if (i9 == 4) {
                    this.f20037c = 6;
                    return false;
                }
                this.f20037c = i9;
                this.b = i8;
                return true;
            } else {
                this.f20037c = 6;
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class c extends AbstractC0853a {

        /* renamed from: j  reason: collision with root package name */
        public static final int f20039j = 19;

        /* renamed from: k  reason: collision with root package name */
        private static final byte[] f20040k = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};

        /* renamed from: l  reason: collision with root package name */
        private static final byte[] f20041l = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};

        /* renamed from: m  reason: collision with root package name */
        static final /* synthetic */ boolean f20042m = true;

        /* renamed from: c  reason: collision with root package name */
        private final byte[] f20043c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        private int f20044e;

        /* renamed from: f  reason: collision with root package name */
        public final boolean f20045f;

        /* renamed from: g  reason: collision with root package name */
        public final boolean f20046g;

        /* renamed from: h  reason: collision with root package name */
        public final boolean f20047h;

        /* renamed from: i  reason: collision with root package name */
        private final byte[] f20048i;

        public c(int i2, byte[] bArr) {
            this.a = bArr;
            this.f20045f = (i2 & 1) == 0;
            boolean z = (i2 & 2) == 0;
            this.f20046g = z;
            this.f20047h = (i2 & 4) != 0;
            this.f20048i = (i2 & 8) == 0 ? f20040k : f20041l;
            this.f20043c = new byte[2];
            this.d = 0;
            this.f20044e = z ? 19 : -1;
        }

        @Override // jd.wjweblogin.d.a.AbstractC0853a
        public int a(int i2) {
            return ((i2 * 8) / 5) + 10;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x01bb  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01c8 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x00e6 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00dd -> B:22:0x008a). Please submit an issue!!! */
        @Override // jd.wjweblogin.d.a.AbstractC0853a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(byte[] r18, int r19, int r20, boolean r21) {
            /*
                Method dump skipped, instructions count: 511
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: jd.wjweblogin.d.a.c.a(byte[], int, int, boolean):boolean");
        }
    }

    private a() {
    }

    public static byte[] a(String str, int i2) {
        return a(str.getBytes(), i2);
    }

    public static byte[] b(byte[] bArr, int i2) {
        return b(bArr, 0, bArr.length, i2);
    }

    public static String c(byte[] bArr, int i2) {
        try {
            return new String(b(bArr, i2), CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public static byte[] a(byte[] bArr, int i2) {
        return a(bArr, 0, bArr.length, i2);
    }

    public static byte[] b(byte[] bArr, int i2, int i3, int i4) {
        c cVar = new c(i4, null);
        int i5 = (i3 / 3) * 4;
        if (cVar.f20045f) {
            if (i3 % 3 > 0) {
                i5 += 4;
            }
        } else {
            int i6 = i3 % 3;
            if (i6 == 1) {
                i5 += 2;
            } else if (i6 == 2) {
                i5 += 3;
            }
        }
        if (cVar.f20046g && i3 > 0) {
            i5 += (((i3 - 1) / 57) + 1) * (cVar.f20047h ? 2 : 1);
        }
        cVar.a = new byte[i5];
        cVar.a(bArr, i2, i3, true);
        if (f20032g || cVar.b == i5) {
            return cVar.a;
        }
        throw new AssertionError();
    }

    public static byte[] a(byte[] bArr, int i2, int i3, int i4) {
        b bVar = new b(i4, new byte[(i3 * 3) / 4]);
        if (bVar.a(bArr, i2, i3, true)) {
            int i5 = bVar.b;
            byte[] bArr2 = bVar.a;
            if (i5 == bArr2.length) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i5];
            System.arraycopy(bArr2, 0, bArr3, 0, i5);
            return bArr3;
        }
        throw new IllegalArgumentException("bad base-64");
    }

    public static String c(byte[] bArr, int i2, int i3, int i4) {
        try {
            return new String(b(bArr, i2, i3, i4), CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }
}
