package com.jingdong.jdexreport.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public class d {
    private static volatile ConnectivityManager a;

    private static ConnectivityManager a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    try {
                        a = (ConnectivityManager) context.getSystemService("connectivity");
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        return a;
    }

    public static String b(Context context) {
        NetworkInfo.State state;
        ConnectivityManager a2 = a(context);
        if (a2 != null && a(a2)) {
            NetworkInfo.State state2 = null;
            try {
                state = a2.getNetworkInfo(0).getState();
            } catch (Throwable unused) {
                state = null;
            }
            try {
                state2 = a2.getNetworkInfo(1).getState();
            } catch (Throwable unused2) {
            }
            return (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) ? "wifi" : (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) ? "mobile" : "UNKNOW";
        }
        return "UNKNOW";
    }

    public static boolean c(Context context) {
        return "wifi".equals(b(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x001e A[LOOP:0: B:8:0x000b->B:16:0x001e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x001d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.net.ConnectivityManager r6) {
        /*
            android.net.NetworkInfo[] r6 = r6.getAllNetworkInfo()     // Catch: java.lang.Throwable -> L5
            goto L6
        L5:
            r6 = 0
        L6:
            r0 = 0
            if (r6 == 0) goto L21
            int r1 = r6.length
            r2 = 0
        Lb:
            if (r2 >= r1) goto L21
            r3 = 1
            r4 = r6[r2]     // Catch: java.lang.Throwable -> L1a
            android.net.NetworkInfo$State r4 = r4.getState()     // Catch: java.lang.Throwable -> L1a
            android.net.NetworkInfo$State r5 = android.net.NetworkInfo.State.CONNECTED     // Catch: java.lang.Throwable -> L1a
            if (r4 != r5) goto L1a
            r4 = 1
            goto L1b
        L1a:
            r4 = 0
        L1b:
            if (r4 == 0) goto L1e
            return r3
        L1e:
            int r2 = r2 + 1
            goto Lb
        L21:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.a.a.d.a(android.net.ConnectivityManager):boolean");
    }
}
