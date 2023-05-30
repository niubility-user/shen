package com.jd.jdsec.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes13.dex */
public class c {
    protected static boolean a;
    public static Context b;

    /* renamed from: c  reason: collision with root package name */
    private static ConnectivityManager f2726c;

    public static void a(boolean z) {
        a = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b() {
        if (f2726c == null) {
            Context context = b;
            if (context == null) {
                return true;
            }
            f2726c = (ConnectivityManager) context.getSystemService("connectivity");
        }
        ConnectivityManager connectivityManager = f2726c;
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
