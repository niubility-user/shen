package com.jd.manto.hd.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public class a {
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f6708c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f6709e;

    /* renamed from: f  reason: collision with root package name */
    private String f6710f;

    /* renamed from: g  reason: collision with root package name */
    private Context f6711g;

    /* renamed from: h  reason: collision with root package name */
    private h f6712h;

    /* renamed from: i  reason: collision with root package name */
    private WifiManager f6713i;

    /* renamed from: j  reason: collision with root package name */
    private g f6714j;

    /* renamed from: k  reason: collision with root package name */
    private f f6715k;

    /* renamed from: l  reason: collision with root package name */
    private f f6716l;
    private int a = -1;

    /* renamed from: m  reason: collision with root package name */
    private Handler f6717m = new HandlerC0194a(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.hd.wifi.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class HandlerC0194a extends Handler {
        HandlerC0194a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                a.this.h();
            } else if (i2 != 2) {
            } else {
                a.this.d();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f6712h.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {
        final /* synthetic */ Network a;

        c(Network network) {
            this.a = network;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f6712h.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f6712h.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ String b;

        e(int i2, String str) {
            this.a = i2;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f6712h.a(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 21)
    /* loaded from: classes17.dex */
    public class f extends ConnectivityManager.NetworkCallback {

        /* renamed from: com.jd.manto.hd.wifi.a$f$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class RunnableC0195a implements Runnable {
            final /* synthetic */ Network a;

            RunnableC0195a(Network network) {
                this.a = network;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b(this.a);
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f();
            }
        }

        private f() {
        }

        /* synthetic */ f(a aVar, HandlerC0194a handlerC0194a) {
            this();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            String str = "NetworkCallback onAvailable " + network;
            a aVar = a.this;
            if (aVar.a(aVar.f6711g, network)) {
                a.this.f6717m.post(new RunnableC0195a(network));
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            String str = "NetworkCallback onLost " + network;
            a.this.f6717m.post(new b());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            super.onUnavailable();
            a.this.f6717m.post(new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes17.dex */
    public class g extends BroadcastReceiver {
        private g() {
        }

        /* synthetic */ g(a aVar, HandlerC0194a handlerC0194a) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                a.this.g();
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                NetworkInfo.State state = networkInfo != null ? networkInfo.getState() : null;
                if (state == NetworkInfo.State.CONNECTED) {
                    a.this.b((Network) null);
                } else if (state == NetworkInfo.State.DISCONNECTED) {
                    a.this.e();
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    public interface h {
        void a();

        void a(int i2, String str);

        void a(Network network);

        void a(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, h hVar) {
        this.f6711g = context;
        this.f6712h = hVar;
        a(context);
    }

    private void a() {
        Handler handler = this.f6717m;
        if (handler == null || this.f6712h == null) {
            return;
        }
        handler.post(new b());
    }

    private void a(int i2, String str) {
        Handler handler = this.f6717m;
        if (handler == null || this.f6712h == null) {
            return;
        }
        handler.post(new e(i2, str));
    }

    private synchronized void a(Context context) {
        if (context != null) {
            if (this.f6713i == null) {
                Object systemService = context.getApplicationContext().getSystemService("wifi");
                if (systemService instanceof WifiManager) {
                    this.f6713i = (WifiManager) systemService;
                }
            }
        }
    }

    @RequiresApi(api = 21)
    private void a(Context context, ConnectivityManager.NetworkCallback networkCallback) {
        if (context == null || networkCallback == null) {
            return;
        }
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService instanceof ConnectivityManager) {
            ((ConnectivityManager) systemService).registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).build(), networkCallback);
        }
    }

    private void a(Context context, String str, String str2, ConnectivityManager.NetworkCallback networkCallback) {
        if (context == null || Build.VERSION.SDK_INT < 29) {
            return;
        }
        NetworkRequest build = new NetworkRequest.Builder().addTransportType(1).addCapability(14).removeCapability(12).setNetworkSpecifier(new WifiNetworkSpecifier.Builder().setSsid(str).setWpa2Passphrase(str2).build()).build();
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService instanceof ConnectivityManager) {
            ((ConnectivityManager) systemService).requestNetwork(build, networkCallback);
        }
    }

    private void a(Network network) {
        Handler handler = this.f6717m;
        if (handler == null || this.f6712h == null) {
            return;
        }
        handler.post(new c(network));
    }

    private void a(String str) {
        Handler handler = this.f6717m;
        if (handler == null || this.f6712h == null) {
            return;
        }
        handler.post(new d(str));
    }

    private void a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        this.d = str;
        if (str2 == null) {
            str2 = "";
        }
        this.f6709e = str2;
    }

    private void a(String str, String str2, int i2) {
        int addNetwork;
        if (this.f6713i != null) {
            WifiConfiguration b2 = b(str);
            if (b2 == null || a(b2.networkId)) {
                addNetwork = this.f6713i.addNetwork(b(str, str2, i2));
            } else {
                addNetwork = b2.networkId;
            }
            String str3 = "enableNetwork networkId:" + addNetwork + " ssid: " + str;
            String str4 = "enableNetwork enabled:" + this.f6713i.enableNetwork(addNetwork, true) + " connected:" + this.f6713i.reconnect();
        }
    }

    private boolean a(int i2) {
        WifiManager wifiManager = this.f6713i;
        if (wifiManager != null) {
            return wifiManager.removeNetwork(i2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 21)
    public boolean a(Context context, @NonNull Network network) {
        NetworkCapabilities networkCapabilities;
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        return (systemService instanceof ConnectivityManager) && (networkCapabilities = ((ConnectivityManager) systemService).getNetworkCapabilities(network)) != null && (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(5));
    }

    private WifiConfiguration b(String str) {
        List<WifiConfiguration> configuredNetworks;
        try {
            WifiManager wifiManager = this.f6713i;
            if (wifiManager == null || (configuredNetworks = wifiManager.getConfiguredNetworks()) == null) {
                return null;
            }
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!str.equals(wifiConfiguration.SSID)) {
                    if (("\"" + str + "\"").equals(wifiConfiguration.SSID)) {
                    }
                }
                return wifiConfiguration;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private WifiConfiguration b(String str, String str2, int i2) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (i2 == 0) {
            wifiConfiguration.wepKeys[0] = "";
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (i2 != 1) {
            if (i2 == 2) {
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.status = 2;
            }
            return wifiConfiguration;
        } else {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
        }
        wifiConfiguration.wepTxKeyIndex = 0;
        return wifiConfiguration;
    }

    private void b(Context context) {
        if (context == null || this.f6714j != null) {
            return;
        }
        HandlerC0194a handlerC0194a = null;
        this.f6714j = new g(this, handlerC0194a);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        if (Build.VERSION.SDK_INT >= 21) {
            f fVar = new f(this, handlerC0194a);
            this.f6715k = fVar;
            a(context, fVar);
        } else {
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        }
        context.registerReceiver(this.f6714j, intentFilter);
    }

    @RequiresApi(api = 21)
    private void b(Context context, ConnectivityManager.NetworkCallback networkCallback) {
        if (context == null || networkCallback == null) {
            return;
        }
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService instanceof ConnectivityManager) {
            ((ConnectivityManager) systemService).unregisterNetworkCallback(networkCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(Network network) {
        this.f6710f = a(b());
        String str = "handleNetworkConnected " + this.f6710f;
        String str2 = this.f6710f;
        if (str2 != null && str2.equals(this.d)) {
            a(network);
            Handler handler = this.f6717m;
            if (handler != null) {
                handler.removeMessages(2);
            }
        }
    }

    private ScanResult c(String str) {
        for (ScanResult scanResult : c()) {
            if (!TextUtils.isEmpty(scanResult.SSID) && scanResult.SSID.equals(str)) {
                return scanResult;
            }
        }
        return null;
    }

    private void c(Context context) {
        if (context != null) {
            try {
                g gVar = this.f6714j;
                if (gVar != null) {
                    context.unregisterReceiver(gVar);
                    this.f6714j = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            b(context, this.f6715k);
            b(context, this.f6716l);
            this.f6716l = null;
            this.f6715k = null;
        }
    }

    private int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.contains("WPA-PSK") || str.contains("WPA2-PSK")) {
            return 2;
        }
        if (str.contains("WPA2-EAP")) {
            return 3;
        }
        return str.contains("WEP") ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f6708c = true;
        a(R2.drawable.x_dialog_bg2, "connection timeout");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        WifiManager wifiManager;
        int i2;
        String str = "handleNetworkDisconnected " + this.f6710f;
        a(this.f6710f);
        String str2 = this.f6710f;
        if (str2 != null && str2.equals(this.d) && (wifiManager = this.f6713i) != null && (i2 = this.a) != -1) {
            String str3 = "enableNetwork enable: " + wifiManager.enableNetwork(i2, true) + " netId: " + this.a;
        }
        this.f6710f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        a(R2.drawable.x_view_bg, "user denied");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g() {
        Handler handler;
        a();
        String str = "handleWifiScanResult isConnectScan: " + this.b;
        if (this.b && !this.f6708c) {
            ScanResult c2 = c(this.d);
            String str2 = "handleWifiScanResult scanResult: " + c2;
            if (c2 != null) {
                this.b = false;
                this.f6717m.removeMessages(1);
                a(this.d, this.f6709e, TextUtils.isEmpty(this.f6709e) ? 0 : d(c2.capabilities));
            } else if (!this.f6708c && (handler = this.f6717m) != null) {
                handler.sendEmptyMessageDelayed(1, 3000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(WifiInfo wifiInfo) {
        String wifiSSID = wifiInfo != null ? BaseInfo.getWifiSSID() : "";
        return (wifiSSID != null && wifiSSID.startsWith("\"") && wifiSSID.endsWith("\"")) ? wifiSSID.substring(1, wifiSSID.length() - 1) : wifiSSID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, boolean z) {
        this.f6708c = false;
        a(str, str2);
        WifiInfo b2 = b();
        if (b2 != null) {
            this.a = b2.getNetworkId();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (z) {
                ((Activity) this.f6711g).startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 81);
                return;
            }
            f fVar = new f(this, null);
            this.f6716l = fVar;
            a(this.f6711g, str, str2, fVar);
        } else if (this.f6713i != null) {
            this.b = true;
            String str3 = "connectWifi startScan result: " + h();
            Handler handler = this.f6717m;
            if (handler != null) {
                handler.sendEmptyMessageDelayed(2, 15000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WifiInfo b() {
        WifiManager wifiManager = this.f6713i;
        if (wifiManager != null) {
            return wifiManager.getConnectionInfo();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ScanResult> c() {
        ArrayList arrayList = new ArrayList();
        WifiManager wifiManager = this.f6713i;
        if (wifiManager != null) {
            arrayList.addAll(wifiManager.getScanResults());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        WifiManager wifiManager = this.f6713i;
        if (wifiManager != null) {
            return wifiManager.startScan();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void i() {
        b(this.f6711g);
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void j() {
        c(this.f6711g);
        Handler handler = this.f6717m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
