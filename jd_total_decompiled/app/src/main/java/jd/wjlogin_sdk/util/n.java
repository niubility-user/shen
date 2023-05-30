package jd.wjlogin_sdk.util;

/* loaded from: classes.dex */
public class n {
    public static String a(String str) {
        DecryptorJni.b();
        if (str != null) {
            try {
                if (!str.equals("")) {
                    byte[] parseHexStr2Byte = ByteUtil.parseHexStr2Byte(str);
                    return DecryptorJni.a() ? new String(DecryptorJni.jniDecryptData(parseHexStr2Byte, parseHexStr2Byte.length)) : str;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String b(String str) {
        DecryptorJni.b();
        if (str != null) {
            try {
                if (str.equals("")) {
                    return str;
                }
                byte[] bytes = str.getBytes();
                return ByteUtil.parseByte2HexStr(DecryptorJni.jniEncrypt(bytes, bytes.length));
            } catch (Throwable unused) {
                return str;
            }
        }
        return str;
    }
}
