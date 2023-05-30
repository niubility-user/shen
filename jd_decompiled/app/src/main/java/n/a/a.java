package n.a;

/* loaded from: classes11.dex */
public class a {
    public static byte[] a(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i2));
        return bArr2;
    }
}
