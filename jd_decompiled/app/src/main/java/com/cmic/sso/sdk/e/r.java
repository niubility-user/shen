package com.cmic.sso.sdk.e;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

/* loaded from: classes.dex */
public class r {
    private static r a;
    private ConnectivityManager b;

    /* renamed from: c  reason: collision with root package name */
    private Network f1042c;
    private ConnectivityManager.NetworkCallback d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1043e;

    /* loaded from: classes.dex */
    public interface a {
        void a(Network network);
    }

    private r(Context context) {
        try {
            this.b = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void b() {
        if (this.b == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.d == null) {
                    return;
                }
                c.b("WifiNetworkUtils", "unregisterNetwork");
                this.b.unregisterNetworkCallback(this.d);
                this.d = null;
                this.f1042c = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean a() {
        return Build.VERSION.SDK_INT >= 21 && this.f1042c != null;
    }

    public static r a(Context context) {
        if (a == null) {
            synchronized (r.class) {
                if (a == null) {
                    a = new r(context);
                }
            }
        }
        return a;
    }

    @TargetApi(21)
    public synchronized void a(final a aVar) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = this.b;
        if (connectivityManager == null) {
            c.a("WifiNetworkUtils", "mConnectivityManager \u4e3a\u7a7a");
            aVar.a(null);
            return;
        }
        Network network = this.f1042c;
        if (network != null && !this.f1043e && (networkInfo = connectivityManager.getNetworkInfo(network)) != null && networkInfo.isAvailable()) {
            c.a("HttpUtils", "reuse network: ");
            aVar.a(this.f1042c);
            return;
        }
        ConnectivityManager.NetworkCallback networkCallback = this.d;
        if (networkCallback != null) {
            try {
                this.b.unregisterNetworkCallback(networkCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
                this.d = null;
            }
            c.a("HttpUtils", "clear: ");
        }
        NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        ConnectivityManager.NetworkCallback networkCallback2 = new ConnectivityManager.NetworkCallback() { // from class: com.cmic.sso.sdk.e.r.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network2) {
                try {
                    if (r.this.b.getNetworkCapabilities(network2).hasTransport(0)) {
                        r.this.f1042c = network2;
                        aVar.a(network2);
                        r.this.f1043e = false;
                    } else {
                        c.a("WifiNetworkUtils", "\u5207\u6362\u5931\u8d25\uff0c\u672a\u5f00\u542f\u6570\u636e\u7f51\u7edc");
                        r.this.f1042c = null;
                        aVar.a(null);
                        r.this.b.unregisterNetworkCallback(r.this.d);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    r.this.f1042c = null;
                    aVar.a(null);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network2) {
                r.this.f1043e = true;
            }
        };
        this.d = networkCallback2;
        try {
            this.b.requestNetwork(build, networkCallback2);
        } catch (Exception e3) {
            e3.printStackTrace();
            aVar.a(null);
        }
    }
}
