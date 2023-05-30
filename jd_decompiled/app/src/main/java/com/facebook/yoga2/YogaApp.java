package com.facebook.yoga2;

import android.app.Application;

/* loaded from: classes12.dex */
public class YogaApp {
    public static Application application;

    /* JADX WARN: Removed duplicated region for block: B:18:0x0017 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean loadLib(android.content.Context r3) {
        /*
            r0 = 1
            java.lang.String r1 = "ylayout"
            java.lang.String r2 = "c++_shared"
            if (r3 == 0) goto L14
            com.getkeepsafe.relinker.c.a(r3, r2)     // Catch: java.lang.Throwable -> L10
            com.getkeepsafe.relinker.c.a(r3, r1)     // Catch: java.lang.Throwable -> L10
            r3 = 1
            goto L15
        L10:
            r3 = move-exception
            r3.printStackTrace()
        L14:
            r3 = 0
        L15:
            if (r3 != 0) goto L22
            java.lang.System.loadLibrary(r2)     // Catch: java.lang.Throwable -> L1e
            java.lang.System.loadLibrary(r1)     // Catch: java.lang.Throwable -> L1e
            goto L23
        L1e:
            r0 = move-exception
            r0.printStackTrace()
        L22:
            r0 = r3
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.yoga2.YogaApp.loadLib(android.content.Context):boolean");
    }

    public static boolean loadLib() {
        return loadLib(application);
    }
}
