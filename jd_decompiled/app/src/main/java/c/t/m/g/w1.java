package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/* loaded from: classes.dex */
public class w1 {

    /* loaded from: classes.dex */
    public enum a {
        NETWORK_NONE,
        NETWORK_MOBILE,
        NETWORK_WIFI
    }

    public static String a() {
        return b(y3.a());
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) z3.e("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getTypeName().toUpperCase() + "[" + activeNetworkInfo.getSubtypeName() + "]";
            }
        } catch (Throwable unused) {
        }
        return "UNKNOWN";
    }

    public static a c() {
        return d(y3.a());
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static a d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) z3.e("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? a.NETWORK_NONE : Build.VERSION.SDK_INT >= 16 ? connectivityManager.isActiveNetworkMetered() ? a.NETWORK_MOBILE : a.NETWORK_WIFI : 1 == activeNetworkInfo.getType() ? a.NETWORK_WIFI : a.NETWORK_MOBILE;
        } catch (Throwable unused) {
            return a.NETWORK_NONE;
        }
    }
}
