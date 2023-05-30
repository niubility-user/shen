package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.l2;
import com.xiaomi.push.m2;

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
    */
    public static void d(Context context) {
        boolean z = true;
        boolean z2 = false;
        boolean z3 = b != null;
        if (!a) {
            z2 = z3;
        }
        z = false;
        g.j.a.a.a.c.m(new l2(z2 ? b : null, z ? m2.b(context) : null));
    }
}
