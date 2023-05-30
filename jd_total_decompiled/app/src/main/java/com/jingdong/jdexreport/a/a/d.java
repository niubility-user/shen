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
