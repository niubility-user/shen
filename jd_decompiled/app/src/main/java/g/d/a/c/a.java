package g.d.a.c;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.binary.StringUtils;

/* loaded from: classes12.dex */
public class a extends b {

    /* renamed from: n  reason: collision with root package name */
    static final byte[] f19419n = {13, 10};
    private static final byte[] o = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] p = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] q = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51};

    /* renamed from: h  reason: collision with root package name */
    private final byte[] f19420h;

    /* renamed from: i  reason: collision with root package name */
    private final byte[] f19421i;

    /* renamed from: j  reason: collision with root package name */
    private final byte[] f19422j;

    /* renamed from: k  reason: collision with root package name */
    private final int f19423k;

    /* renamed from: l  reason: collision with root package name */
    private final int f19424l;

    /* renamed from: m  reason: collision with root package name */
    private int f19425m;

    public a() {
        this(0);
    }

    public static byte[] l(String str) {
        return new a().d(str);
    }

    @Override // g.d.a.c.b
    void c(byte[] bArr, int i2, int i3) {
        byte b;
        if (this.f19427e) {
            return;
        }
        if (i3 < 0) {
            this.f19427e = true;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            f(this.f19423k);
            int i5 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 == 61) {
                this.f19427e = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = q;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i6 = (this.f19429g + 1) % 4;
                    this.f19429g = i6;
                    int i7 = (this.f19425m << 6) + b;
                    this.f19425m = i7;
                    if (i6 == 0) {
                        byte[] bArr3 = this.b;
                        int i8 = this.f19426c;
                        int i9 = i8 + 1;
                        this.f19426c = i9;
                        bArr3[i8] = (byte) ((i7 >> 16) & 255);
                        int i10 = i9 + 1;
                        this.f19426c = i10;
                        bArr3[i9] = (byte) ((i7 >> 8) & 255);
                        this.f19426c = i10 + 1;
                        bArr3[i10] = (byte) (i7 & 255);
                    }
                }
            }
            i4++;
            i2 = i5;
        }
        if (!this.f19427e || this.f19429g == 0) {
            return;
        }
        f(this.f19423k);
        int i11 = this.f19429g;
        if (i11 == 2) {
            int i12 = this.f19425m >> 4;
            this.f19425m = i12;
            byte[] bArr4 = this.b;
            int i13 = this.f19426c;
            this.f19426c = i13 + 1;
            bArr4[i13] = (byte) (i12 & 255);
        } else if (i11 != 3) {
        } else {
            int i14 = this.f19425m >> 2;
            this.f19425m = i14;
            byte[] bArr5 = this.b;
            int i15 = this.f19426c;
            int i16 = i15 + 1;
            this.f19426c = i16;
            bArr5[i15] = (byte) ((i14 >> 8) & 255);
            this.f19426c = i16 + 1;
            bArr5[i16] = (byte) (i14 & 255);
        }
    }

    @Override // g.d.a.c.b
    void e(byte[] bArr, int i2, int i3) {
        if (this.f19427e) {
            return;
        }
        if (i3 >= 0) {
            int i4 = 0;
            while (i4 < i3) {
                f(this.f19424l);
                int i5 = (this.f19429g + 1) % 3;
                this.f19429g = i5;
                int i6 = i2 + 1;
                int i7 = bArr[i2];
                if (i7 < 0) {
                    i7 += 256;
                }
                int i8 = (this.f19425m << 8) + i7;
                this.f19425m = i8;
                if (i5 == 0) {
                    byte[] bArr2 = this.b;
                    int i9 = this.f19426c;
                    int i10 = i9 + 1;
                    this.f19426c = i10;
                    byte[] bArr3 = this.f19420h;
                    bArr2[i9] = bArr3[(i8 >> 18) & 63];
                    int i11 = i10 + 1;
                    this.f19426c = i11;
                    bArr2[i10] = bArr3[(i8 >> 12) & 63];
                    int i12 = i11 + 1;
                    this.f19426c = i12;
                    bArr2[i11] = bArr3[(i8 >> 6) & 63];
                    int i13 = i12 + 1;
                    this.f19426c = i13;
                    bArr2[i12] = bArr3[i8 & 63];
                    int i14 = this.f19428f + 4;
                    this.f19428f = i14;
                    int i15 = this.a;
                    if (i15 > 0 && i15 <= i14) {
                        byte[] bArr4 = this.f19422j;
                        System.arraycopy(bArr4, 0, bArr2, i13, bArr4.length);
                        this.f19426c += this.f19422j.length;
                        this.f19428f = 0;
                    }
                }
                i4++;
                i2 = i6;
            }
            return;
        }
        this.f19427e = true;
        if (this.f19429g == 0 && this.a == 0) {
            return;
        }
        f(this.f19424l);
        int i16 = this.f19426c;
        int i17 = this.f19429g;
        if (i17 == 1) {
            byte[] bArr5 = this.b;
            int i18 = i16 + 1;
            this.f19426c = i18;
            byte[] bArr6 = this.f19420h;
            int i19 = this.f19425m;
            bArr5[i16] = bArr6[(i19 >> 2) & 63];
            int i20 = i18 + 1;
            this.f19426c = i20;
            bArr5[i18] = bArr6[(i19 << 4) & 63];
            if (bArr6 == o) {
                int i21 = i20 + 1;
                this.f19426c = i21;
                bArr5[i20] = 61;
                this.f19426c = i21 + 1;
                bArr5[i21] = 61;
            }
        } else if (i17 == 2) {
            byte[] bArr7 = this.b;
            int i22 = i16 + 1;
            this.f19426c = i22;
            byte[] bArr8 = this.f19420h;
            int i23 = this.f19425m;
            bArr7[i16] = bArr8[(i23 >> 10) & 63];
            int i24 = i22 + 1;
            this.f19426c = i24;
            bArr7[i22] = bArr8[(i23 >> 4) & 63];
            int i25 = i24 + 1;
            this.f19426c = i25;
            bArr7[i24] = bArr8[(i23 << 2) & 63];
            if (bArr8 == o) {
                this.f19426c = i25 + 1;
                bArr7[i25] = 61;
            }
        }
        int i26 = this.f19428f;
        int i27 = this.f19426c;
        int i28 = i26 + (i27 - i16);
        this.f19428f = i28;
        if (this.a <= 0 || i28 <= 0) {
            return;
        }
        byte[] bArr9 = this.f19422j;
        System.arraycopy(bArr9, 0, this.b, i27, bArr9.length);
        this.f19426c += this.f19422j.length;
    }

    @Override // g.d.a.c.b
    protected boolean h(byte b) {
        if (b >= 0) {
            byte[] bArr = this.f19421i;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public a(int i2) {
        this(i2, f19419n);
    }

    public a(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    public a(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        this.f19421i = q;
        if (bArr != null) {
            if (b(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            } else if (i2 > 0) {
                this.f19424l = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.f19422j = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.f19424l = 4;
                this.f19422j = null;
            }
        } else {
            this.f19424l = 4;
            this.f19422j = null;
        }
        this.f19423k = this.f19424l - 1;
        this.f19420h = z ? p : o;
    }
}
