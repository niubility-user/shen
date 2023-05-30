package m.a.b;

import java.io.IOException;
import java.util.ArrayList;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
class e {
    private byte[] a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private int f20289c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f20290e;

    /* renamed from: f  reason: collision with root package name */
    private int f20291f;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<Object> f20292g = new ArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private int f20293h = 0;

    private byte[] b(int i2) {
        if (i2 < 128) {
            return new byte[]{(byte) i2};
        }
        return i2 < 256 ? new byte[]{-127, (byte) i2} : i2 < 65536 ? new byte[]{ReplyCode.reply0x82, (byte) (i2 >> 8), (byte) i2} : i2 < 16777216 ? new byte[]{ReplyCode.reply0x83, (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2} : new byte[]{ReplyCode.reply0x84, (byte) (i2 >> 24), (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2};
    }

    private boolean c(int i2) {
        return (i2 & 31) == 0 && (i2 & 32) == 0 && (i2 & 192) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(int i2) {
        return e(i2) && (i2 & 127) == 0;
    }

    static boolean e(int i2) {
        return (i2 & 128) == 128;
    }

    private int f() throws IOException {
        int i2 = this.d;
        if (i2 == this.f20290e) {
            return 0;
        }
        byte[] bArr = this.a;
        this.d = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (d(i3)) {
            this.f20292g.add(new Integer(this.d));
            return 0;
        } else if (e(i3)) {
            int i4 = i3 & 127;
            if (i4 <= 4) {
                if (this.f20290e - this.d >= i4 + 1) {
                    int i5 = 0;
                    for (int i6 = 0; i6 < i4; i6++) {
                        byte[] bArr2 = this.a;
                        int i7 = this.d;
                        this.d = i7 + 1;
                        i5 = (i5 << 8) + (bArr2[i7] & 255);
                    }
                    return i5;
                }
                throw new IOException("Too little data");
            }
            throw new IOException("Too much data");
        } else {
            return i3 & 127;
        }
    }

    private void g() throws IOException {
        int i2 = this.d;
        if (i2 == this.f20290e) {
            return;
        }
        if (c(this.a[i2]) && this.a[this.d + 1] == 0) {
            int i3 = 0;
            Object obj = null;
            int size = this.f20292g.size() - 1;
            while (size >= 0) {
                obj = this.f20292g.get(size);
                if (obj instanceof Integer) {
                    break;
                }
                i3 += ((byte[]) obj).length - 3;
                size--;
            }
            if (size >= 0) {
                this.f20292g.set(size, b((this.d - ((Integer) obj).intValue()) + i3));
                this.f20293h += r0.length - 3;
            } else {
                throw new IOException("EOC does not have matching indefinite-length tag");
            }
        }
        this.d++;
    }

    private void h(int i2) {
        this.d += i2;
    }

    private void i(int i2) {
        if (i2 < 128) {
            byte[] bArr = this.b;
            int i3 = this.f20289c;
            this.f20289c = i3 + 1;
            bArr[i3] = (byte) i2;
        } else if (i2 < 256) {
            byte[] bArr2 = this.b;
            int i4 = this.f20289c;
            int i5 = i4 + 1;
            this.f20289c = i5;
            bArr2[i4] = -127;
            this.f20289c = i5 + 1;
            bArr2[i5] = (byte) i2;
        } else if (i2 < 65536) {
            byte[] bArr3 = this.b;
            int i6 = this.f20289c;
            int i7 = i6 + 1;
            this.f20289c = i7;
            bArr3[i6] = ReplyCode.reply0x82;
            int i8 = i7 + 1;
            this.f20289c = i8;
            bArr3[i7] = (byte) (i2 >> 8);
            this.f20289c = i8 + 1;
            bArr3[i8] = (byte) i2;
        } else if (i2 < 16777216) {
            byte[] bArr4 = this.b;
            int i9 = this.f20289c;
            int i10 = i9 + 1;
            this.f20289c = i10;
            bArr4[i9] = ReplyCode.reply0x83;
            int i11 = i10 + 1;
            this.f20289c = i11;
            bArr4[i10] = (byte) (i2 >> 16);
            int i12 = i11 + 1;
            this.f20289c = i12;
            bArr4[i11] = (byte) (i2 >> 8);
            this.f20289c = i12 + 1;
            bArr4[i12] = (byte) i2;
        } else {
            byte[] bArr5 = this.b;
            int i13 = this.f20289c;
            int i14 = i13 + 1;
            this.f20289c = i14;
            bArr5[i13] = ReplyCode.reply0x84;
            int i15 = i14 + 1;
            this.f20289c = i15;
            bArr5[i14] = (byte) (i2 >> 24);
            int i16 = i15 + 1;
            this.f20289c = i16;
            bArr5[i15] = (byte) (i2 >> 16);
            int i17 = i16 + 1;
            this.f20289c = i17;
            bArr5[i16] = (byte) (i2 >> 8);
            this.f20289c = i17 + 1;
            bArr5[i17] = (byte) i2;
        }
    }

    private void j() throws IOException {
        int i2;
        int i3 = this.d;
        if (i3 == this.f20290e) {
            return;
        }
        byte[] bArr = this.a;
        this.d = i3 + 1;
        int i4 = bArr[i3] & 255;
        if (d(i4)) {
            ArrayList<Object> arrayList = this.f20292g;
            int i5 = this.f20291f;
            this.f20291f = i5 + 1;
            byte[] bArr2 = (byte[]) arrayList.get(i5);
            System.arraycopy(bArr2, 0, this.b, this.f20289c, bArr2.length);
            this.f20289c += bArr2.length;
            return;
        }
        if (e(i4)) {
            int i6 = i4 & 127;
            i2 = 0;
            for (int i7 = 0; i7 < i6; i7++) {
                byte[] bArr3 = this.a;
                int i8 = this.d;
                this.d = i8 + 1;
                i2 = (i2 << 8) + (bArr3[i8] & 255);
            }
        } else {
            i2 = i4 & 127;
        }
        i(i2);
        l(i2);
    }

    private void k() {
        int i2 = this.d;
        if (i2 == this.f20290e) {
            return;
        }
        byte[] bArr = this.a;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if (c(b)) {
            byte[] bArr2 = this.a;
            int i3 = this.d;
            if (bArr2[i3] == 0) {
                this.d = i3 + 1;
                k();
                return;
            }
        }
        byte[] bArr3 = this.b;
        int i4 = this.f20289c;
        this.f20289c = i4 + 1;
        bArr3[i4] = b;
    }

    private void l(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr = this.b;
            int i4 = this.f20289c;
            this.f20289c = i4 + 1;
            byte[] bArr2 = this.a;
            int i5 = this.d;
            this.d = i5 + 1;
            bArr[i4] = bArr2[i5];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(byte[] bArr) throws IOException {
        int i2;
        this.a = bArr;
        this.d = 0;
        this.f20291f = 0;
        this.f20290e = bArr.length;
        while (true) {
            int i3 = this.d;
            i2 = this.f20290e;
            if (i3 >= i2) {
                break;
            }
            g();
            h(f());
        }
        this.b = new byte[i2 + this.f20293h];
        this.d = 0;
        this.f20289c = 0;
        this.f20291f = 0;
        while (this.d < this.f20290e) {
            k();
            j();
        }
        return this.b;
    }
}
