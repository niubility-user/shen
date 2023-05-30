package com.xiaomi.mipush.sdk;

import android.content.Context;

/* loaded from: classes11.dex */
public class g {
    private static boolean a;
    private static g.j.a.a.a.a b;

    /* JADX INFO: Access modifiers changed from: protected */
    public static g.j.a.a.a.a a() {
        return b;
    }

    private static boolean b(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String str : strArr) {
                    if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void c(Context context, g.j.a.a.a.a aVar) {
        b = aVar;
        d(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (b(r4) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(android.content.Context r4) {
        /*
            g.j.a.a.a.a r0 = com.xiaomi.mipush.sdk.g.b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L8
            r0 = 1
            goto L9
        L8:
            r0 = 0
        L9:
            boolean r3 = com.xiaomi.mipush.sdk.g.a
            if (r3 == 0) goto Le
            goto L16
        Le:
            boolean r3 = b(r4)
            r2 = r0
            if (r3 == 0) goto L16
            goto L17
        L16:
            r1 = 0
        L17:
            com.xiaomi.push.l2 r0 = new com.xiaomi.push.l2
            r3 = 0
            if (r2 == 0) goto L1f
            g.j.a.a.a.a r2 = com.xiaomi.mipush.sdk.g.b
            goto L20
        L1f:
            r2 = r3
        L20:
            if (r1 == 0) goto L26
            com.xiaomi.push.m2 r3 = com.xiaomi.push.m2.b(r4)
        L26:
            r0.<init>(r2, r3)
            g.j.a.a.a.c.m(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.g.d(android.content.Context):void");
    }
}
