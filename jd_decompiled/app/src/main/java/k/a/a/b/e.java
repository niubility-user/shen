package k.a.a.b;

import k.a.a.c.a;

/* loaded from: classes11.dex */
public class e implements c {
    private char[] a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f20192c = new byte[4];
    private k.a.a.b.f.b d = new k.a.a.b.f.b();

    public e(char[] cArr, byte[] bArr, byte[] bArr2) throws k.a.a.c.a {
        this.a = cArr;
        this.b = bArr;
        b(bArr2);
    }

    private void b(byte[] bArr) throws k.a.a.c.a {
        byte[] bArr2 = this.f20192c;
        byte[] bArr3 = this.b;
        bArr2[3] = (byte) (bArr3[3] & 255);
        bArr2[2] = (byte) ((bArr3[3] >> 8) & 255);
        bArr2[1] = (byte) ((bArr3[3] >> 16) & 255);
        int i2 = 0;
        bArr2[0] = (byte) ((bArr3[3] >> 24) & 255);
        if (bArr2[2] <= 0 && bArr2[1] <= 0 && bArr2[0] <= 0) {
            char[] cArr = this.a;
            if (cArr != null && cArr.length > 0) {
                this.d.c(cArr);
                byte b = bArr[0];
                while (i2 < 12) {
                    k.a.a.b.f.b bVar = this.d;
                    bVar.d((byte) (bVar.b() ^ b));
                    i2++;
                    if (i2 != 12) {
                        b = bArr[i2];
                    }
                }
                return;
            }
            throw new k.a.a.c.a("Wrong password!", a.EnumC0855a.WRONG_PASSWORD);
        }
        throw new IllegalStateException("Invalid CRC in File Header");
    }

    @Override // k.a.a.b.c
    public int a(byte[] bArr, int i2, int i3) throws k.a.a.c.a {
        if (i2 < 0 || i3 < 0) {
            throw new k.a.a.c.a("one of the input parameters were null in standard decrypt data");
        }
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            byte b = (byte) (((bArr[i4] & 255) ^ this.d.b()) & 255);
            this.d.d(b);
            bArr[i4] = b;
        }
        return i3;
    }
}
