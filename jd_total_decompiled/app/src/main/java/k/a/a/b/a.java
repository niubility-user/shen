package k.a.a.b;

import java.util.Arrays;
import k.a.a.c.a;

/* loaded from: classes11.dex */
public class a implements c {
    private k.a.a.f.a a;
    private char[] b;

    /* renamed from: c  reason: collision with root package name */
    private k.a.a.b.f.a f20186c;
    private k.a.a.b.d.a d;

    /* renamed from: e  reason: collision with root package name */
    private int f20187e = 1;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f20188f = new byte[16];

    /* renamed from: g  reason: collision with root package name */
    private byte[] f20189g = new byte[16];

    public a(k.a.a.f.a aVar, char[] cArr, byte[] bArr, byte[] bArr2) throws k.a.a.c.a {
        this.a = aVar;
        this.b = cArr;
        c(bArr, bArr2);
    }

    private void c(byte[] bArr, byte[] bArr2) throws k.a.a.c.a {
        char[] cArr = this.b;
        if (cArr != null && cArr.length > 0) {
            k.a.a.f.o.a b = this.a.b();
            byte[] a = b.a(bArr, this.b, b);
            if (Arrays.equals(bArr2, b.b(a, b))) {
                this.f20186c = b.c(a, b);
                this.d = b.d(a, b);
                return;
            }
            throw new k.a.a.c.a("Wrong Password", a.EnumC0855a.WRONG_PASSWORD);
        }
        throw new k.a.a.c.a("empty or null password provided for AES decryption");
    }

    @Override // k.a.a.b.c
    public int a(byte[] bArr, int i2, int i3) throws k.a.a.c.a {
        int i4 = i2;
        while (true) {
            int i5 = i2 + i3;
            if (i4 >= i5) {
                return i3;
            }
            int i6 = i4 + 16;
            int i7 = i6 <= i5 ? 16 : i5 - i4;
            this.d.e(bArr, i4, i7);
            b.e(this.f20188f, this.f20187e);
            this.f20186c.e(this.f20188f, this.f20189g);
            for (int i8 = 0; i8 < i7; i8++) {
                int i9 = i4 + i8;
                bArr[i9] = (byte) (bArr[i9] ^ this.f20189g[i8]);
            }
            this.f20187e++;
            i4 = i6;
        }
    }

    public byte[] b() {
        return this.d.d();
    }
}
