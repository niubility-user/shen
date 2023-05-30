package c.t.m.g;

/* loaded from: classes.dex */
public class i3 {
    public static String a(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() != 2) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            sb.append(hexString);
            sb.append(str);
        }
        return sb.toString();
    }

    public static byte[] b(int i2) {
        return new byte[]{(byte) (i2 >>> 24), (byte) (i2 >>> 16), (byte) (i2 >>> 8), (byte) i2};
    }
}
