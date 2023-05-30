package jd.wjlogin_sdk.util;

import android.content.Context;

/* loaded from: classes.dex */
public class DecryptorJni {
    private static final int a = 3;
    private static boolean b;

    static {
        b();
    }

    public static boolean a() {
        return b;
    }

    public static void b() {
        if (b) {
            return;
        }
        try {
            jd.wjlogin_sdk.relinker.b.a(jd.wjlogin_sdk.common.b.a(), "DecryptorJni");
            b = true;
        } catch (Throwable th) {
            th.printStackTrace();
            b = false;
        }
    }

    public static native byte[] jniDecrypt(byte[] bArr, int i2, String str);

    public static native byte[] jniDecryptData(byte[] bArr, int i2);

    public static native byte[] jniDecryptMessage(byte[] bArr, int i2, byte[] bArr2, int i3);

    public static native byte[] jniDecryptMsg(byte[] bArr, int i2, String str);

    public static native String jniDeviceKey(Context context, String str);

    public static native byte[] jniEncrypt(byte[] bArr, int i2);

    public static native byte[] jniEncryptMessage(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4);

    public static native byte[] jniEncryptMsg(byte[] bArr, int i2, String str);

    public static native int jniFirstExchangeStep1(byte[] bArr, int[] iArr, byte[] bArr2, int[] iArr2);

    public static native int jniFirstExchangeStep2(byte[] bArr, int[] iArr, byte[] bArr2, int[] iArr2, byte[] bArr3, int[] iArr3, byte[] bArr4, int[] iArr4, byte[] bArr5, int i2, byte[] bArr6, int i3);

    public static native int jniFollowExchangeStep1(byte[] bArr, int[] iArr, byte[] bArr2, int[] iArr2, byte[] bArr3, int i2, byte[] bArr4, int i3);

    public static native int jniFollowExchangeStep2(byte[] bArr, int[] iArr, byte[] bArr2, int[] iArr2, byte[] bArr3, int[] iArr3, byte[] bArr4, int[] iArr4, byte[] bArr5, int i2, byte[] bArr6, int i3, byte[] bArr7, int i4);

    public static native String jniRandomKey();

    public static native String jniUserFilename(Context context, String str);
}
