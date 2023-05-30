package com.jdjr.risk.device.c;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;

/* loaded from: classes18.dex */
public class aj {
    private static String[] a = {"com.bly.dkplat", "com.lbe.parallel", "com.excelliance.dualaid", "com.lody.virtual", "com.qihoo.magic"};

    /* JADX WARN: Removed duplicated region for block: B:27:0x006e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a() {
        /*
            r0 = 0
            r1 = 1
            java.lang.String r2 = b()     // Catch: java.lang.Exception -> L6b
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L6b
            if (r3 != 0) goto L6b
            java.lang.String r3 = "ps"
            java.lang.String r3 = com.jdjr.risk.device.c.j.a(r3, r1)     // Catch: java.lang.Exception -> L6b
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L6b
            if (r4 != 0) goto L6b
            java.lang.String r4 = "\n"
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Exception -> L6b
            if (r3 == 0) goto L6b
            int r4 = r3.length     // Catch: java.lang.Exception -> L6b
            if (r4 <= 0) goto L6b
            r4 = 0
            r5 = 0
        L25:
            int r6 = r3.length     // Catch: java.lang.Exception -> L69
            if (r4 >= r6) goto L6c
            r6 = r3[r4]     // Catch: java.lang.Exception -> L69
            boolean r6 = r6.contains(r2)     // Catch: java.lang.Exception -> L69
            if (r6 == 0) goto L66
            r6 = r3[r4]     // Catch: java.lang.Exception -> L69
            java.lang.String r7 = " "
            int r6 = r6.lastIndexOf(r7)     // Catch: java.lang.Exception -> L69
            r7 = r3[r4]     // Catch: java.lang.Exception -> L69
            if (r6 > 0) goto L3e
            r6 = 0
            goto L40
        L3e:
            int r6 = r6 + 1
        L40:
            r8 = r3[r4]     // Catch: java.lang.Exception -> L69
            int r8 = r8.length()     // Catch: java.lang.Exception -> L69
            java.lang.String r6 = r7.substring(r6, r8)     // Catch: java.lang.Exception -> L69
            java.io.File r7 = new java.io.File     // Catch: java.lang.Exception -> L69
            java.lang.String r8 = "/data/data/%s"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Exception -> L69
            r9[r0] = r6     // Catch: java.lang.Exception -> L69
            java.util.Locale r6 = java.util.Locale.CHINA     // Catch: java.lang.Exception -> L69
            r9[r1] = r6     // Catch: java.lang.Exception -> L69
            java.lang.String r6 = java.lang.String.format(r8, r9)     // Catch: java.lang.Exception -> L69
            r7.<init>(r6)     // Catch: java.lang.Exception -> L69
            boolean r6 = r7.exists()     // Catch: java.lang.Exception -> L69
            if (r6 == 0) goto L66
            int r5 = r5 + 1
        L66:
            int r4 = r4 + 1
            goto L25
        L69:
            goto L6c
        L6b:
            r5 = 0
        L6c:
            if (r5 <= r1) goto L6f
            r0 = 1
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.aj.a():boolean");
    }

    public static boolean a(Context context) {
        if (context != null) {
            boolean b = b(context);
            if (b) {
                return b;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                b = a(context.getDataDir().getAbsolutePath());
            }
            if (b) {
                return b;
            }
            boolean d = d(context);
            if (d) {
                return d;
            }
            boolean c2 = c(context);
            return !c2 ? a() : c2;
        }
        return false;
    }

    private static boolean a(String str) {
        return new File(str + File.separator + "..").canRead();
    }

    private static String b() {
        String str = "";
        try {
            String b = j.b("cat /proc/self/cgroup");
            try {
                if (TextUtils.isEmpty(b)) {
                    return b;
                }
                int lastIndexOf = b.lastIndexOf("uid");
                int lastIndexOf2 = b.lastIndexOf("/pid");
                if (lastIndexOf >= 0) {
                    if (lastIndexOf2 <= 0) {
                        lastIndexOf2 = b.length();
                    }
                    String substring = b.substring(lastIndexOf + 4, lastIndexOf2);
                    return b(substring.replaceAll(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "")) ? String.format("u0_a%d", Integer.valueOf(Integer.valueOf(r0).intValue() - 10000)) : substring;
                }
                return b;
            } catch (Exception unused) {
                str = b;
                return str;
            }
        } catch (Exception unused2) {
        }
    }

    private static boolean b(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            String path = filesDir.getPath();
            for (String str : a) {
                if (path.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean b(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    private static boolean c(Context context) {
        return false;
    }

    private static boolean d(Context context) {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).uid / 100000 != 0;
    }
}
