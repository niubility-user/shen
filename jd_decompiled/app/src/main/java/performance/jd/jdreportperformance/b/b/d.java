package performance.jd.jdreportperformance.b.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public class d {
    public static String a = "";
    private static volatile ConnectivityManager b;

    /* loaded from: classes.dex */
    static class a implements Runnable {
        final /* synthetic */ Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.c(this.a);
        }
    }

    private static ConnectivityManager a(Context context) {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    try {
                        b = (ConnectivityManager) context.getSystemService("connectivity");
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        return b;
    }

    private static String b(Context context) {
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
            return (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) ? "wifi" : (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) ? "mobile" : "UNKNOWN";
        }
        return "UNKNOWN";
    }

    public static String c(Context context) {
        String b2 = b(context);
        a = b2;
        return b2;
    }

    public static void d(Context context) {
        performance.jd.jdreportperformance.d.c.b().a(new a(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x001f A[LOOP:0: B:8:0x000c->B:16:0x001f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x001e A[SYNTHETIC] */
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
            if (r6 == 0) goto L22
            int r1 = r6.length
            int r1 = r6.length
            r2 = 0
        Lc:
            if (r2 >= r1) goto L22
            r3 = r6[r2]
            r4 = 1
            android.net.NetworkInfo$State r3 = r3.getState()     // Catch: java.lang.Throwable -> L1b
            android.net.NetworkInfo$State r5 = android.net.NetworkInfo.State.CONNECTED     // Catch: java.lang.Throwable -> L1b
            if (r3 != r5) goto L1b
            r3 = 1
            goto L1c
        L1b:
            r3 = 0
        L1c:
            if (r3 == 0) goto L1f
            return r4
        L1f:
            int r2 = r2 + 1
            goto Lc
        L22:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.b.b.d.a(android.net.ConnectivityManager):boolean");
    }
}
