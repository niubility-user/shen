package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.telephony.TelephonyManager;
import androidx.annotation.Nullable;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class y4 {

    /* renamed from: n  reason: collision with root package name */
    public static HandlerThread f780n;
    public static volatile y4 o;
    public final Context a;
    public final g5 b;

    /* renamed from: c  reason: collision with root package name */
    public final ExecutorService f781c;
    public final HashMap<String, p5> d;

    /* renamed from: e  reason: collision with root package name */
    public final PackageManager f782e;

    /* renamed from: f  reason: collision with root package name */
    public final TelephonyManager f783f;

    /* renamed from: g  reason: collision with root package name */
    public final WifiManager f784g;

    /* renamed from: h  reason: collision with root package name */
    public final LocationManager f785h;

    /* renamed from: i  reason: collision with root package name */
    public final q1 f786i;

    /* renamed from: j  reason: collision with root package name */
    public CountDownLatch f787j;

    /* renamed from: k  reason: collision with root package name */
    public String f788k;

    /* renamed from: l  reason: collision with root package name */
    public a5 f789l;

    /* renamed from: m  reason: collision with root package name */
    public List<y6> f790m;

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public a(y4 y4Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "network_request_pool");
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            y4.this.u();
            y4.this.f787j.countDown();
        }
    }

    public y4(Context context) {
        this.a = context;
        this.f782e = context.getPackageManager();
        this.f783f = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        this.f784g = (WifiManager) context.getSystemService("wifi");
        this.f785h = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        this.f789l = new i5(context);
        this.f786i = new p4(context, c1.a(context.getPackageName()));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 60000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new a(this));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.f781c = threadPoolExecutor;
        HandlerThread handlerThread = new HandlerThread("GeoLocationService");
        f780n = handlerThread;
        handlerThread.start();
        HashMap<String, p5> hashMap = new HashMap<>();
        this.d = hashMap;
        if (Build.VERSION.SDK_INT >= 12) {
            hashMap.put("cell", new y5("cell"));
        }
        g5 g5Var = new g5();
        this.b = g5Var;
        try {
            g5Var.r(h(context));
        } catch (Exception unused) {
        }
        f0.d(context.getApplicationContext());
        t();
    }

    public static y4 b(Context context) {
        if (o == null) {
            synchronized (y4.class) {
                if (o == null) {
                    o = new y4(context);
                }
            }
        }
        return o;
    }

    public static String h(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                if (bundle.containsKey("TencentGeoLocationSDK")) {
                    return bundle.getString("TencentGeoLocationSDK");
                }
                if (bundle.containsKey("TencentMapSDK")) {
                    return bundle.getString("TencentMapSDK");
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public Bundle a(String str, byte[] bArr, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        Bundle a2 = this.f786i.a(str, bArr);
        StringBuilder sb = new StringBuilder("HalleyTimeCost: ");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append(", reqKey: ");
        sb.append(a2.getString("req_key"));
        byte[] d = z ? c1.d(a2.getByteArray("data_bytes")) : a2.getByteArray("data_bytes");
        String str2 = d != null ? new String(d, a2.getString("data_charset")) : "{}";
        a2.remove("data_charset");
        a2.remove("data_bytes");
        a2.putString("result", str2);
        return a2;
    }

    public g5 c() {
        return this.b;
    }

    public p5 d(String str) {
        return this.d.get(str);
    }

    public synchronized void f(@Nullable Object obj) {
        if (obj == null) {
            return;
        }
        List<y6> list = this.f790m;
        if (list != null) {
            for (y6 y6Var : list) {
                if (y6Var.b(obj)) {
                    try {
                        y6Var.a().invoke(y6Var.c(), obj);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    @Nullable
    public LocationManager g() {
        return this.f785h;
    }

    public String i(String str) {
        return this.f786i.a(str);
    }

    public synchronized void j(Object obj) {
        boolean z;
        if (this.f790m == null) {
            this.f790m = new ArrayList();
        }
        Iterator<y6> it = this.f790m.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (obj == it.next().c()) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        for (Method method : obj.getClass().getDeclaredMethods()) {
            String name = method.getName();
            if (name.startsWith("on") && name.endsWith("Event")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("EventHandler methods must specify a single Object paramter.");
                }
                this.f790m.add(new y6(parameterTypes[0], method, obj, false));
            }
        }
    }

    public a5 k() {
        return this.f789l;
    }

    @Nullable
    public TelephonyManager l() {
        return this.f783f;
    }

    public ExecutorService m() {
        return this.f781c;
    }

    @Nullable
    public WifiManager n() {
        return this.f784g;
    }

    public HandlerThread o() {
        HandlerThread handlerThread;
        synchronized (y4.class) {
            HandlerThread handlerThread2 = f780n;
            if (handlerThread2 == null || handlerThread2.getLooper() == null || !f780n.isAlive()) {
                HandlerThread handlerThread3 = new HandlerThread("GeoLocationService");
                f780n = handlerThread3;
                handlerThread3.start();
            }
            handlerThread = f780n;
        }
        return handlerThread;
    }

    public boolean p() {
        return this.f785h != null;
    }

    public boolean q() {
        return this.f783f != null;
    }

    public boolean r() {
        return this.f784g != null;
    }

    @SuppressLint({"MissingPermission"})
    public final void s() {
        g5 g5Var = this.b;
        PackageInfo v = v();
        g5Var.h(v.versionCode);
        g5Var.D(v.versionName);
        CharSequence loadLabel = this.a.getApplicationInfo().loadLabel(this.f782e);
        g5Var.e(loadLabel != null ? loadLabel.toString() : "unknown");
        try {
            TelephonyManager l2 = l();
            if (l2 != null) {
                this.f788k = w.a(z3.m(), w.a).toUpperCase(Locale.ENGLISH);
                String a2 = w.a(z3.o(), w.b);
                g5Var.b(l2.getPhoneType());
                g5Var.j(this.f788k);
                g5Var.B(a2);
                StringBuilder sb = new StringBuilder("mDeviceId: ");
                sb.append(this.f788k);
                sb.append("; subscriberId: ");
                sb.append(a2);
                sb.append(";");
            }
        } catch (Throwable unused) {
        }
        g5Var.v(w.a(z3.q().replaceAll(":", "").toUpperCase(Locale.ENGLISH), w.f724c));
        PackageManager packageManager = this.f782e;
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.location.gps");
        boolean hasSystemFeature2 = packageManager.hasSystemFeature("android.hardware.wifi");
        boolean hasSystemFeature3 = packageManager.hasSystemFeature("android.hardware.telephony");
        g5Var.k(hasSystemFeature);
        g5Var.o(hasSystemFeature2);
        g5Var.f(hasSystemFeature3);
        StringBuilder sb2 = new StringBuilder("doInBg: hasGps=");
        sb2.append(hasSystemFeature);
        sb2.append(",hasWifi=");
        sb2.append(hasSystemFeature2);
        sb2.append(",hasCell=");
        sb2.append(hasSystemFeature3);
    }

    public void t() {
        this.f787j = new CountDownLatch(1);
        new Thread(new b()).start();
    }

    public void u() {
        try {
            s();
        } catch (Throwable unused) {
        }
    }

    public final PackageInfo v() {
        try {
            return this.f782e.getPackageInfo(this.a.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return new PackageInfo();
        }
    }
}
