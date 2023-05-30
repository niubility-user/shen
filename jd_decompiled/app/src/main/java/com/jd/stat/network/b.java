package com.jd.stat.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes18.dex */
public class b {
    protected static boolean a;
    public static Context b;

    /* renamed from: c  reason: collision with root package name */
    private static ConnectivityManager f7028c;

    public static void a(boolean z) {
        a = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a() {
        if (f7028c == null) {
            Context context = b;
            if (context == null) {
                return true;
            }
            f7028c = (ConnectivityManager) context.getSystemService("connectivity");
        }
        ConnectivityManager connectivityManager = f7028c;
        if (connectivityManager == null) {
            return true;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }
}
