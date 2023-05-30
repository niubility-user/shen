package k.a.a.b;

import org.apache.commons.codec.CharEncoding;

/* loaded from: classes11.dex */
public class b {
    public static byte[] a(byte[] bArr, char[] cArr, k.a.a.f.o.a aVar) throws k.a.a.c.a {
        k.a.a.b.d.b bVar = new k.a.a.b.d.b(new k.a.a.b.d.c("HmacSHA1", CharEncoding.ISO_8859_1, bArr, 1000));
        int keyLength = aVar.getKeyLength();
        int macLength = aVar.getMacLength();
        int i2 = keyLength + macLength + 2;
        byte[] f2 = bVar.f(cArr, i2);
        if (f2 == null || f2.length != i2) {
            throw new k.a.a.c.a(String.format("Derived Key invalid for Key Length [%d] MAC Length [%d]", Integer.valueOf(keyLength), Integer.valueOf(macLength)));
        }
        return f2;
    }

    public static byte[] b(byte[] bArr, k.a.a.f.o.a aVar) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, aVar.getKeyLength() + aVar.getMacLength(), bArr2, 0, 2);
        return bArr2;
    }

    public static k.a.a.b.f.a c(byte[] bArr, k.a.a.f.o.a aVar) throws k.a.a.c.a {
        int keyLength = aVar.getKeyLength();
        byte[] bArr2 = new byte[keyLength];
        System.arraycopy(bArr, 0, bArr2, 0, keyLength);
        return new k.a.a.b.f.a(bArr2);
    }

    public static k.a.a.b.d.a d(byte[] bArr, k.a.a.f.o.a aVar) {
        int macLength = aVar.getMacLength();
        byte[] bArr2 = new byte[macLength];
        System.arraycopy(bArr, aVar.getKeyLength(), bArr2, 0, macLength);
        k.a.a.b.d.a aVar2 = new k.a.a.b.d.a("HmacSHA1");
        aVar2.b(bArr2);
        return aVar2;
    }

    public static void e(byte[] bArr, int i2) {
        bArr[0] = (byte) i2;
        bArr[1] = (byte) (i2 >> 8);
        bArr[2] = (byte) (i2 >> 16);
        bArr[3] = (byte) (i2 >> 24);
        for (int i3 = 4; i3 <= 15; i3++) {
            bArr[i3] = 0;
        }
    }
}
