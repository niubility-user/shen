package com.jingdong.sdk.baseinfo.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.android.sdk.coreinfo.util.Supplier;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.baseinfo.c.e;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: f  reason: collision with root package name */
    public static final String f14679f = null;
    public volatile boolean a;
    public WifiInfo b;

    /* renamed from: c  reason: collision with root package name */
    public WifiInfo f14680c;
    ArrayList<NetworkInterface> d;

    /* renamed from: e  reason: collision with root package name */
    String f14681e;

    /* renamed from: g  reason: collision with root package name */
    private List<ScanResult> f14682g;

    /* renamed from: h  reason: collision with root package name */
    private long f14683h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static final class a {
        static final b a = new b();
    }

    public static b a() {
        return a.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ArrayList b() {
        if (this.d == null || TextUtils.equals("MOBILE", this.f14681e)) {
            Logger.d("BaseInfo.Network", "try get network interfaces from system");
            this.d = CoreInfo.Device.getNetworkInterfaces();
        } else {
            Logger.d("BaseInfo.Network", "return cached networkInterfaces");
        }
        return this.d;
    }

    public static void b(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(new com.jingdong.sdk.baseinfo.a.a(), intentFilter);
        } catch (IllegalArgumentException e2) {
            Logger.e("BaseInfo.Network", e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List f(Context context) {
        String str;
        List<ScanResult> list = this.f14682g;
        long currentTimeMillis = System.currentTimeMillis();
        if (list == null || currentTimeMillis - this.f14683h > 600000) {
            Supplier<Boolean> locationPermissionCheck = BaseInfo.getConfig().getLocationPermissionCheck();
            if (locationPermissionCheck == null || locationPermissionCheck.get().booleanValue()) {
                Logger.d("BaseInfo.Network", "try get wifi list from system");
                list = CoreInfo.Device.getWifiScanResultList(context);
                if (list != null) {
                    this.f14682g = list;
                }
                this.f14683h = System.currentTimeMillis();
                if (Logger.D) {
                    str = "getWifiScanResultList(cost time " + (this.f14683h - currentTimeMillis) + "ms):" + com.jingdong.sdk.baseinfo.c.e.a(list, new e.b() { // from class: com.jingdong.sdk.baseinfo.a.d
                        @Override // com.jingdong.sdk.baseinfo.c.e.b
                        public final String toString(Object obj) {
                            String str2;
                            str2 = ((ScanResult) obj).SSID;
                            return str2;
                        }
                    });
                }
                return list;
            }
            str = "location permission is not granted, skip getWifiScanResultList~";
        } else {
            str = "return cached wifiScanResultList";
        }
        Logger.d("BaseInfo.Network", str);
        return list;
    }

    public final void a(final Context context) {
        CoreInfo.Device.wifiInfoSupplier = new Supplier() { // from class: com.jingdong.sdk.baseinfo.a.c
            @Override // com.jd.android.sdk.coreinfo.util.Supplier
            public final Object get() {
                WifiInfo g2;
                g2 = b.this.g(context);
                return g2;
            }
        };
        CoreInfo.Device.networkInterfacesSupplier = new Supplier() { // from class: com.jingdong.sdk.baseinfo.a.f
            @Override // com.jd.android.sdk.coreinfo.util.Supplier
            public final Object get() {
                ArrayList b;
                b = b.this.b();
                return b;
            }
        };
        CoreInfo.Device.wifiListSupplier = new Supplier() { // from class: com.jingdong.sdk.baseinfo.a.e
            @Override // com.jd.android.sdk.coreinfo.util.Supplier
            public final Object get() {
                List f2;
                f2 = b.this.f(context);
                return f2;
            }
        };
    }

    @SuppressLint({"MissingPermission"})
    public final boolean c(Context context) {
        NetworkInfo networkInfo;
        if (TextUtils.equals("WIFI", this.f14681e)) {
            return true;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || networkInfo.getState() == null) {
                return false;
            }
            NetworkInfo.State state = networkInfo.getState();
            if (state != NetworkInfo.State.CONNECTED) {
                if (state != NetworkInfo.State.CONNECTING) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            Logger.e("BaseInfo.Network", "", th);
            return false;
        }
    }

    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public final WifiInfo g(Context context) {
        String str;
        WifiInfo wifiInfo = this.b;
        if (wifiInfo != null) {
            str = "return cached wifiInfo";
        } else if (c(context)) {
            Supplier<Boolean> locationPermissionCheck = BaseInfo.getConfig().getLocationPermissionCheck();
            if (locationPermissionCheck == null || locationPermissionCheck.get().booleanValue()) {
                Logger.d("BaseInfo.Network", "try get wifiInfo from system");
                return e(context);
            }
            str = "location permission is not granted, skip wifiInfo~";
        } else {
            str = "current net is not wifi, skip wifiInfo~";
        }
        Logger.d("BaseInfo.Network", str);
        return wifiInfo;
    }

    public final WifiInfo e(Context context) {
        WifiInfo wifiInfo = CoreInfo.Device.getWifiInfo(context);
        this.b = wifiInfo;
        return wifiInfo;
    }
}
