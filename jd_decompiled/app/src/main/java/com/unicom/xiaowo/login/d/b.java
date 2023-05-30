package com.unicom.xiaowo.login.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes11.dex */
public class b {

    /* renamed from: g  reason: collision with root package name */
    private static b f18112g;
    private ExecutorService d;

    /* renamed from: e  reason: collision with root package name */
    private List<a> f18114e;
    private Network a = null;
    private ConnectivityManager.NetworkCallback b = null;

    /* renamed from: c  reason: collision with root package name */
    private ConnectivityManager f18113c = null;

    /* renamed from: f  reason: collision with root package name */
    private Timer f18115f = null;

    /* loaded from: classes11.dex */
    public interface a {
        void a(boolean z, Network network);
    }

    private b() {
        this.d = null;
        this.f18114e = null;
        this.f18114e = new ArrayList();
        this.d = Executors.newSingleThreadExecutor();
    }

    public synchronized void b() {
        ConnectivityManager connectivityManager;
        ConnectivityManager.NetworkCallback networkCallback;
        try {
            e.b("releaseNetwork");
            Timer timer = this.f18115f;
            if (timer != null) {
                timer.cancel();
                this.f18115f = null;
            }
            if (Build.VERSION.SDK_INT >= 21 && (connectivityManager = this.f18113c) != null && (networkCallback = this.b) != null) {
                connectivityManager.unregisterNetworkCallback(networkCallback);
            }
            this.f18113c = null;
            this.b = null;
            this.a = null;
            this.f18114e.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static b a() {
        if (f18112g == null) {
            synchronized (b.class) {
                if (f18112g == null) {
                    f18112g = new b();
                }
            }
        }
        return f18112g;
    }

    public void a(Network network, final String str) {
        this.d.submit(new Runnable() { // from class: com.unicom.xiaowo.login.d.b.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    if (allByName == null || allByName.length <= 0) {
                        return;
                    }
                    for (InetAddress inetAddress : allByName) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("opencloud.wostore.cn:");
                        sb.append(inetAddress.getHostAddress());
                        e.a(sb.toString());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void a(final Context context, final String str, final a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            a(context, aVar);
        } else {
            this.d.submit(new Runnable() { // from class: com.unicom.xiaowo.login.d.b.2
                @Override // java.lang.Runnable
                public void run() {
                    boolean a2 = b.this.a(context, str);
                    a aVar2 = aVar;
                    if (aVar2 != null) {
                        aVar2.a(a2, null);
                    }
                }
            });
        }
    }

    @TargetApi(21)
    private synchronized void a(Context context, a aVar) {
        Network network = this.a;
        if (network != null) {
            aVar.a(true, network);
            return;
        }
        a(aVar);
        if (this.b == null || this.f18114e.size() < 2) {
            try {
                this.f18113c = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0);
                builder.addCapability(12);
                NetworkRequest build = builder.build();
                this.b = new ConnectivityManager.NetworkCallback() { // from class: com.unicom.xiaowo.login.d.b.3
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network2) {
                        super.onAvailable(network2);
                        e.a("Network onAvailable");
                        if (b.this.b == null) {
                            return;
                        }
                        b.this.a = network2;
                        b.this.a(true, network2);
                        try {
                            NetworkInfo networkInfo = b.this.f18113c.getNetworkInfo(b.this.a);
                            String extraInfo = networkInfo.getExtraInfo();
                            StringBuilder sb = new StringBuilder();
                            sb.append("APN:");
                            sb.append(networkInfo.toString());
                            e.a(sb.toString());
                            if (TextUtils.isEmpty(extraInfo)) {
                                return;
                            }
                            f.d(extraInfo);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network2) {
                        super.onLost(network2);
                        e.a("Network onLost");
                        b.this.b();
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onUnavailable() {
                        super.onUnavailable();
                        e.a("Network onUnavailable");
                        b.this.a(false, (Network) null);
                        b.this.b();
                    }
                };
                int i2 = 3000;
                if (f.f() < 3000) {
                    i2 = 2000;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    this.f18113c.requestNetwork(build, this.b, i2);
                } else {
                    Timer timer = new Timer();
                    this.f18115f = timer;
                    timer.schedule(new TimerTask() { // from class: com.unicom.xiaowo.login.d.b.4
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            b.this.a(false, (Network) null);
                        }
                    }, i2);
                    this.f18113c.requestNetwork(build, this.b);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                a(false, (Network) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context, String str) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            this.f18113c = connectivityManager;
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo.State state = connectivityManager.getNetworkInfo(5).getState();
            if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                Method method = ConnectivityManager.class.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class);
                method.setAccessible(true);
                ConnectivityManager connectivityManager2 = this.f18113c;
                Object[] objArr = new Object[2];
                objArr[0] = 0;
                objArr[1] = "enableHIPRI";
                int intValue = ((Integer) method.invoke(connectivityManager2, objArr)).intValue();
                if (-1 == intValue) {
                    return false;
                }
                if (intValue == 0) {
                    return true;
                }
                String b = g.b(str);
                if (!TextUtils.isEmpty(b)) {
                    str = b;
                }
                int c2 = g.c(str);
                if (-1 == c2) {
                    return false;
                }
                for (int i2 = 0; i2 < 3; i2++) {
                    try {
                        if (this.f18113c.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                Class cls = Integer.TYPE;
                Method method2 = ConnectivityManager.class.getMethod("requestRouteToHost", cls, cls);
                method2.setAccessible(true);
                boolean booleanValue = ((Boolean) method2.invoke(this.f18113c, 5, Integer.valueOf(c2))).booleanValue();
                this.f18113c.getNetworkInfo(5).getState();
                return booleanValue;
            }
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }

    private synchronized void a(a aVar) {
        try {
            this.f18114e.add(aVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z, Network network) {
        try {
            Timer timer = this.f18115f;
            if (timer != null) {
                timer.cancel();
                this.f18115f = null;
            }
            Iterator<a> it = this.f18114e.iterator();
            while (it.hasNext()) {
                it.next().a(z, network);
            }
            this.f18114e.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
