package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class t2 {
    private static void a(byte[] bArr) {
        if (bArr.length >= 2) {
            bArr[0] = 99;
            bArr[1] = ReplyCode.reply0x64;
        }
    }

    public static boolean b(Context context, String str, long j2) {
        if (com.xiaomi.push.service.b0.d(context).m(h7.DCJobMutualSwitch.a(), false)) {
            return (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) && !g.a(context, str, j2);
        }
        return false;
    }

    public static byte[] c(String str, byte[] bArr) {
        byte[] b = m0.b(str);
        try {
            a(b);
            return y5.b(b, bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] d(String str, byte[] bArr) {
        byte[] b = m0.b(str);
        try {
            a(b);
            return y5.c(b, bArr);
        } catch (Exception unused) {
            return null;
        }
    }
}
