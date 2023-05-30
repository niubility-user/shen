package g.f.b.b;

/* loaded from: classes13.dex */
public class a {
    public static String a(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i2 = 0; i2 < bArr.length; i2++) {
            stringBuffer.append(charArray[(bArr[i2] & 240) >> 4]);
            stringBuffer.append(charArray[bArr[i2] & 15]);
        }
        return stringBuffer.toString();
    }

    public static byte[] b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }
}
