package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class t0 {
    public static String a() {
        return Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL;
    }

    public static String b(Context context) {
        String c2 = w0.b(context).c("sp_client_report_status", "sp_client_report_key", "");
        if (TextUtils.isEmpty(c2)) {
            String a = p0.a(20);
            w0.b(context).e("sp_client_report_status", "sp_client_report_key", a);
            return a;
        }
        return c2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e4, code lost:
        if (r7 == null) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.t0.c(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static boolean d(Context context, String str) {
        File file = new File(str);
        long d = g.j.b.b.b.e(context).c().d();
        if (file.exists()) {
            try {
                if (file.length() > d) {
                    return false;
                }
            } catch (Exception e2) {
                g.j.a.a.a.c.s(e2);
                return false;
            }
        } else {
            u9.f(file);
        }
        return true;
    }

    @TargetApi(9)
    public static byte[] e(String str) {
        byte[] copyOf = Arrays.copyOf(m0.b(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    public static File[] f(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir(str);
        if (externalFilesDir != null) {
            return externalFilesDir.listFiles(new v0());
        }
        return null;
    }
}
