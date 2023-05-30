package com.jd.hybrid.downloader.l;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.jd.libs.hybrid.base.BaseGraySwitch;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public class b implements com.jd.hybrid.downloader.l.a {
    private Context a;
    private final Object b;

    /* renamed from: c  reason: collision with root package name */
    private volatile ConnectivityManager.NetworkCallback f2693c = null;
    private volatile AtomicBoolean d = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a extends ConnectivityManager.NetworkCallback {
        a() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (!b.i(networkCapabilities)) {
                b.this.d.set(false);
                Log.d("NetworkDownloadCondition", "Download condition(network: " + network.toString() + ") DISABLE. cap = " + networkCapabilities.toString());
                return;
            }
            b.this.d.set(true);
            Log.d("NetworkDownloadCondition", "Download condition(network: " + network.toString() + ") ENABLE. cap = " + networkCapabilities.toString());
            synchronized (b.this.b) {
                b.this.b.notifyAll();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            b.this.d.set(false);
            Log.d("NetworkDownloadCondition", "Download condition(network: " + network.toString() + ") DISABLE, lost network");
        }
    }

    @RequiresApi(api = 23)
    public b(Context context, Object obj) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = obj;
        if (BaseGraySwitch.bugfix12dd3bOn) {
            b();
        } else if (applicationContext.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.d("NetworkDownloadCondition", "cannot download, no network state permission");
            return;
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            this.d.set(i(connectivityManager != null ? connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()) : null));
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 23)
    public static boolean i(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities != null) {
            boolean hasTransport = networkCapabilities.hasTransport(1);
            if (HybridSettings.CONDITION_DOWNLOAD_NETWORK_IGNORE) {
                hasTransport = true;
            }
            boolean hasCapability = networkCapabilities.hasCapability(12);
            boolean hasCapability2 = !BaseGraySwitch.offlineDownloadNoCheckValidate ? networkCapabilities.hasCapability(16) : true;
            if (hasTransport && hasCapability && hasCapability2) {
                Log.d("NetworkDownloadCondition", "can download, network type is WiFi");
                return true;
            }
            Log.d("NetworkDownloadCondition", "cannot download, network type WiFi = " + networkCapabilities.hasTransport(1) + ", has internet = " + hasCapability + ", validated = " + hasCapability2 + ", network_ignore = " + HybridSettings.CONDITION_DOWNLOAD_NETWORK_IGNORE);
            return false;
        }
        Log.d("NetworkDownloadCondition", "cannot download, unknown network status");
        return false;
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void a() {
        this.d.set(false);
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void b() {
        synchronized (b.class) {
            if (this.f2693c != null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 23) {
                return;
            }
            if (this.a.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                Log.d("NetworkDownloadCondition", "cannot download, no network state permission");
                return;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            this.d.set(i(connectivityManager != null ? connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()) : null));
        }
    }

    @Override // com.jd.hybrid.downloader.l.a
    public boolean c() {
        return this.d.get();
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void d() {
        synchronized (b.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            if (this.f2693c != null) {
                connectivityManager.unregisterNetworkCallback(this.f2693c);
                this.f2693c = null;
            }
        }
    }

    @Override // com.jd.hybrid.downloader.l.a
    @RequiresApi(api = 23)
    public void e() {
        ConnectivityManager connectivityManager;
        if (this.a.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return;
        }
        synchronized (b.class) {
            if (this.f2693c != null) {
                return;
            }
            try {
                connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            } catch (Exception e2) {
                Log.e("NetworkDownloadCondition", e2);
            }
            if (connectivityManager == null) {
                return;
            }
            this.f2693c = new a();
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), this.f2693c);
            Log.d("NetworkDownloadCondition", "register receiver of network change.");
        }
    }
}
