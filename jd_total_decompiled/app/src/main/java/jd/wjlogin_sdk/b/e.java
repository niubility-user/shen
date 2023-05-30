package jd.wjlogin_sdk.b;

/* loaded from: classes.dex */
class e implements f {
    private static final String a = "0123456789ABCDEF";

    @Override // jd.wjlogin_sdk.b.f
    public byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    @Override // jd.wjlogin_sdk.b.f
    public String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            a(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(a.charAt((b >> 4) & 15));
        stringBuffer.append(a.charAt(b & 15));
    }
}
