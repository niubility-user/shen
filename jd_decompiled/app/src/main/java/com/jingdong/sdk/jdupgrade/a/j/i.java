package com.jingdong.sdk.jdupgrade.a.j;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes7.dex */
public class i {
    static ConnectivityManager a() {
        try {
            return (ConnectivityManager) com.jingdong.sdk.jdupgrade.a.c.j().getSystemService("connectivity");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static NetworkInfo a(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return null;
        }
        try {
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean b() {
        NetworkInfo a = a(a());
        return a != null && a.isConnectedOrConnecting();
    }

    public static boolean c() {
        ConnectivityManager a = a();
        NetworkInfo a2 = a(a);
        if (a2 != null && a2.isConnectedOrConnecting()) {
            NetworkInfo networkInfo = a.getNetworkInfo(0);
            NetworkInfo.State state = networkInfo != null ? networkInfo.getState() : null;
            NetworkInfo networkInfo2 = a.getNetworkInfo(1);
            NetworkInfo.State state2 = networkInfo2 != null ? networkInfo2.getState() : null;
            if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
                return true;
            }
            if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                return false;
            }
        }
        return false;
    }
}
