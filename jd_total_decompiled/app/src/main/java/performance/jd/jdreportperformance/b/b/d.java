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
    */
    private static boolean a(ConnectivityManager connectivityManager) {
        NetworkInfo[] networkInfoArr;
        boolean z;
        try {
            networkInfoArr = connectivityManager.getAllNetworkInfo();
        } catch (Throwable unused) {
            networkInfoArr = null;
        }
        if (networkInfoArr != null) {
            int length = networkInfoArr.length;
            for (NetworkInfo networkInfo : networkInfoArr) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    z = true;
                    if (!z) {
                        return true;
                    }
                }
                z = false;
                if (!z) {
                }
            }
        }
        return false;
    }
}
