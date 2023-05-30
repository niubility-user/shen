package c.t.m.g;

/* loaded from: classes.dex */
public class s2 {
    public static void a(byte[] bArr, int i2, boolean z) {
        if (z) {
            int i3 = i2 / 8;
            bArr[i3] = (byte) ((1 << (i2 % 8)) | bArr[i3]);
            return;
        }
        int i4 = i2 / 8;
        bArr[i4] = (byte) (((1 << (i2 % 8)) ^ (-1)) & bArr[i4]);
    }

    public static boolean b(byte[] bArr, int i2) {
        return (bArr[i2 / 8] & (1 << (i2 % 8))) != 0;
    }
}
