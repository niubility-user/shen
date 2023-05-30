package com.jingdong.aura.sdk.update.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/* loaded from: classes4.dex */
public final class j {
    public static boolean a = true;
    private static String b = "";

    /* loaded from: classes4.dex */
    public static class a {
        int a;
        private String b;

        public a() {
            this.a = 0;
        }

        public a(int i2, String str) {
            this.a = 0;
            this.a = i2;
            this.b = str;
        }
    }

    /* loaded from: classes4.dex */
    public static class b {
        public static ConnectivityManager a(Context context) {
            try {
                return (ConnectivityManager) context.getSystemService("connectivity");
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static NetworkInfo a(ConnectivityManager connectivityManager) {
            if (connectivityManager == null) {
                return null;
            }
            try {
                return connectivityManager.getActiveNetworkInfo();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static NetworkInfo a(ConnectivityManager connectivityManager, int i2) {
            if (connectivityManager == null) {
                return null;
            }
            try {
                return connectivityManager.getNetworkInfo(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static String a(Context context) {
        a aVar = new a();
        if (b(context)) {
            ConnectivityManager a2 = b.a(context);
            int i2 = 0;
            NetworkInfo a3 = b.a(a2, 0);
            NetworkInfo.State state = a3 != null ? a3.getState() : null;
            NetworkInfo a4 = b.a(a2, 1);
            NetworkInfo.State state2 = a4 != null ? a4.getState() : null;
            if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
                i2 = 1;
            } else if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                i2 = 2;
            }
            NetworkInfo a5 = b.a(a2);
            aVar = new a(i2, a5 == null ? "" : a5.getExtraInfo());
        }
        int i3 = aVar.a;
        return i3 != 1 ? i3 != 2 ? "unknown" : "mobile" : "wifi";
    }

    public static boolean b(Context context) {
        try {
            ConnectivityManager a2 = b.a(context);
            " isNetworkAvailable -->> connectivityManager ".concat(String.valueOf(a2));
            if (a2 == null) {
                return false;
            }
            NetworkInfo a3 = b.a(a2);
            boolean z = a3 != null && a3.isConnectedOrConnecting();
            " isNetworkAvailable -->> result ".concat(String.valueOf(z));
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean c(Context context) {
        String netWorkType = com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider != null ? com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider.getNetWorkType(context) : a(context);
        return TextUtils.isEmpty(netWorkType) || netWorkType.toUpperCase().contains("WIFI") || netWorkType.toUpperCase().contains("UNKNOWN");
    }
}
