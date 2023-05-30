package jd.wjlogin_sdk.util;

/* loaded from: classes11.dex */
public class t {
    public static String a(byte[] bArr, String str) throws Exception {
        DecryptorJni.b();
        return b.a(DecryptorJni.jniEncryptMsg(bArr, bArr.length, str));
    }

    public static byte[] b(String str) throws Exception {
        DecryptorJni.b();
        byte[] bytes = jd.wjlogin_sdk.common.c.b.getBytes();
        byte[] a = b.a(str);
        return DecryptorJni.jniDecryptMessage(a, a.length, bytes, bytes.length);
    }

    public static byte[] a(String str, String str2) throws Exception {
        DecryptorJni.b();
        byte[] a = b.a(str);
        return DecryptorJni.jniDecryptMsg(a, a.length, str2);
    }

    public static String a() {
        DecryptorJni.b();
        return DecryptorJni.jniRandomKey();
    }

    public static String a(byte[] bArr) throws Exception {
        byte[] bytes;
        byte[] bytes2;
        if (bArr == null) {
            return "";
        }
        DecryptorJni.b();
        if (jd.wjlogin_sdk.common.c.e() != null) {
            bytes = jd.wjlogin_sdk.common.c.e().getBytes();
        } else {
            bytes = new Long(System.currentTimeMillis()).toString().getBytes();
        }
        byte[] bArr2 = bytes;
        if (jd.wjlogin_sdk.common.c.g() != null) {
            bytes2 = jd.wjlogin_sdk.common.c.g().getBytes();
        } else {
            bytes2 = new Long(System.currentTimeMillis()).toString().getBytes();
        }
        byte[] bArr3 = bytes2;
        return b.a(DecryptorJni.jniEncryptMessage(bArr, bArr.length, bArr2, bArr2.length, bArr3, bArr3.length));
    }

    public static byte[] a(String str) throws Exception {
        DecryptorJni.b();
        byte[] bytes = jd.wjlogin_sdk.common.c.d().getBytes();
        byte[] a = b.a(str);
        return DecryptorJni.jniDecryptMessage(a, a.length, bytes, bytes.length);
    }
}
