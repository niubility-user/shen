package jd.wjlogin_sdk.b;

/* loaded from: classes.dex */
public class g {
    private static final String a = "JXReeQ==";
    private static final String b = "#e$r";

    /* renamed from: c  reason: collision with root package name */
    private static final String f19712c = "!q@w";

    private g() {
    }

    public static g b() {
        return new g();
    }

    public byte[] a() throws Exception {
        byte[] bArr = new byte[16];
        byte[] bytes = "!q@w".getBytes();
        byte[] bytes2 = b.getBytes();
        byte[] a2 = jd.wjlogin_sdk.util.a.a(a, 0);
        byte[] bArr2 = new byte[bytes.length];
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr2[i2] = (byte) (((bytes[i2] + bytes2[i2]) + a2[i2]) / 3);
        }
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(a2, 0, bArr, 8, 4);
        System.arraycopy(bArr2, 0, bArr, 12, 4);
        return bArr;
    }
}
