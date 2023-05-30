package c.t.m.g;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import c.t.m.g.n;
import c.t.m.g.q5;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class z4 {
    public final y4 a;

    /* renamed from: c  reason: collision with root package name */
    public final LocationManager f802c;

    /* renamed from: e  reason: collision with root package name */
    public final HandlerThread f803e;

    /* renamed from: f  reason: collision with root package name */
    public b f804f;

    /* renamed from: g  reason: collision with root package name */
    public Looper f805g;

    /* renamed from: l  reason: collision with root package name */
    public int f810l;

    /* renamed from: m  reason: collision with root package name */
    public String f811m;

    /* renamed from: n  reason: collision with root package name */
    public int f812n;
    public int o;
    public c p;
    public int b = 0;

    /* renamed from: h  reason: collision with root package name */
    public final byte[] f806h = new byte[0];

    /* renamed from: i  reason: collision with root package name */
    public boolean f807i = false;

    /* renamed from: j  reason: collision with root package name */
    public boolean f808j = false;

    /* renamed from: k  reason: collision with root package name */
    public boolean f809k = false;
    public final a d = new a();

    /* loaded from: classes.dex */
    public class a implements LocationListener {
        public a() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            try {
                new StringBuilder("system nlp callback,location: ").append(location);
                new StringBuilder("system nlp callback,isGetLastKownLoc: ").append(z4.this.f808j);
                if (z4.this.f808j) {
                    return;
                }
                z4.this.f807i = true;
                z4.this.o(z4.this.b(location));
            } catch (Exception unused) {
                z4.this.f807i = false;
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i2, Bundle bundle) {
        }
    }

    /* loaded from: classes.dex */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public final void a(Message message) {
            Location location = null;
            boolean z = false;
            switch (message.what) {
                case 1001:
                    z4.this.f808j = false;
                    z4.this.f807i = false;
                    try {
                        List<String> allProviders = z4.this.f802c.getAllProviders();
                        if (allProviders != null) {
                            Iterator<String> it = allProviders.iterator();
                            while (it.hasNext()) {
                                if ("network".equals(it.next())) {
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            z4.this.f802c.requestLocationUpdates("network", 1000L, 0.0f, z4.this.d, z4.this.f805g);
                        }
                    } catch (Exception unused) {
                    }
                    z4.this.b = 1;
                    return;
                case 1002:
                    z4.this.f808j = false;
                    z4.this.f807i = false;
                    removeCallbacksAndMessages(null);
                    z4.this.f802c.removeUpdates(z4.this.d);
                    z4.this.b = 0;
                    return;
                case 1003:
                    new StringBuilder("MSG_ID_DELAY_GET_LASTLOC msg come, and isNLPcallback: ").append(z4.this.f807i);
                    if (z4.this.f807i) {
                        return;
                    }
                    try {
                        location = z4.this.f802c.getLastKnownLocation("network");
                        new StringBuilder("getLastKownLocation, ").append(location);
                    } catch (Throwable unused2) {
                    }
                    z4.this.o(z4.this.b(location));
                    z4.this.f808j = true;
                    return;
                default:
                    return;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            try {
                a(message);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(q5 q5Var, int i2);
    }

    public z4(y4 y4Var) {
        this.a = y4Var;
        this.f802c = y4Var.g();
        HandlerThread handlerThread = new HandlerThread("loc_nlp_thread");
        this.f803e = handlerThread;
        handlerThread.start();
        this.f805g = handlerThread.getLooper();
        this.f804f = new b(this.f805g);
    }

    public Location b(Location location) {
        double latitude;
        double longitude;
        if (location == null) {
            return m2.a;
        }
        if (this.f809k && a6.b(location.getLatitude(), location.getLongitude())) {
            double[] dArr = new double[2];
            u0.k(location, dArr);
            latitude = dArr[0];
            longitude = dArr[1];
        } else {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        e(location, latitude, longitude, 0, 0);
        return location;
    }

    public final String c(byte[] bArr, int i2) {
        if (!y1.a() && bArr != null) {
            try {
                if (SoUtils.fun_o(bArr, 1) >= 0) {
                    return m2.a(1, i2, 1);
                }
            } catch (UnsatisfiedLinkError unused) {
                return null;
            }
        }
        return m2.a(1, i2, 0);
    }

    public void d(int i2) {
        this.f812n = i2;
    }

    public final void e(Location location, double d, double d2, int i2, int i3) {
        Bundle extras = location.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putDouble("lat", d);
        extras.putDouble(HybridSDK.LNG, d2);
        extras.putInt("rssi", i2);
        extras.putInt("fakeCode", i3);
        location.setExtras(extras);
    }

    public void f(c cVar) {
        this.p = cVar;
    }

    public void h(String str) {
        this.f811m = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void i(String str, v vVar, int i2, n nVar) {
        double d;
        c cVar;
        Location location;
        double d2;
        double d3 = 0.0d;
        try {
            location = new Location(nVar.a);
            Bundle extras = location.getExtras();
            if (extras != null) {
                d = extras.getDouble("lat");
                try {
                    d2 = extras.getDouble(HybridSDK.LNG);
                } catch (Throwable unused) {
                    Location location2 = new Location(nVar.a);
                    new StringBuilder("origin loc 1: ").append(location2);
                    q5.b bVar = new q5.b();
                    bVar.g("network");
                    bVar.a(this.f810l);
                    bVar.c(location2.getExtras());
                    bVar.b(new Location(nVar.a));
                    q5 f2 = bVar.f();
                    location2.setLatitude(d);
                    location2.setLongitude(d3);
                    f2.v(location2);
                    new StringBuilder("origin loc 2: ").append(location2);
                    cVar = this.p;
                    if (cVar == null) {
                    }
                }
            } else {
                d = 0.0d;
                d2 = 0.0d;
            }
        } catch (Throwable unused2) {
            d = 0.0d;
        }
        try {
            StringBuilder sb = new StringBuilder("ongpschanged location extras:");
            sb.append(d);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(d2);
            if (d == 0.0d && d2 == 0.0d) {
                c cVar2 = this.p;
                if (cVar2 != null) {
                    cVar2.a(q5.w, this.o);
                    return;
                }
                return;
            }
            byte[] c2 = c1.c(str.getBytes("GBK"));
            String c3 = c(c2, i2);
            long currentTimeMillis = System.currentTimeMillis();
            String string = this.a.a(c3, c2, true).getString("result");
            StringBuilder sb2 = new StringBuilder("cost:");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            sb2.append(",result:");
            sb2.append(string);
            if (string == null) {
                string = "";
            }
            q5.b bVar2 = new q5.b();
            bVar2.e(string);
            bVar2.a(this.f810l);
            bVar2.g(TencentLocation.COARSE_PROVIDER);
            q5 f3 = bVar2.f();
            q5.b bVar3 = new q5.b();
            bVar3.d(f3);
            bVar3.g("network");
            bVar3.a(this.f810l);
            bVar3.c(location.getExtras());
            bVar3.b(new Location(nVar.a));
            q5 f4 = bVar3.f();
            location.setLatitude(d);
            location.setLongitude(d2);
            f4.v(location);
            c cVar3 = this.p;
            if (cVar3 != null) {
                cVar3.a(f4, this.o);
            }
        } catch (Throwable unused3) {
            d3 = d2;
            Location location22 = new Location(nVar.a);
            new StringBuilder("origin loc 1: ").append(location22);
            q5.b bVar4 = new q5.b();
            bVar4.g("network");
            bVar4.a(this.f810l);
            bVar4.c(location22.getExtras());
            bVar4.b(new Location(nVar.a));
            q5 f22 = bVar4.f();
            location22.setLatitude(d);
            location22.setLongitude(d3);
            f22.v(location22);
            new StringBuilder("origin loc 2: ").append(location22);
            cVar = this.p;
            if (cVar == null) {
                cVar.a(f22, this.o);
            }
        }
    }

    public void j(boolean z) {
        this.f809k = z;
    }

    public boolean k() {
        if (this.b == 0) {
            return false;
        }
        synchronized (this.f806h) {
            t.l(this.f804f, 1002, 0, 0, null);
        }
        return true;
    }

    public void n(int i2) {
        this.o = i2;
    }

    public final void o(Location location) {
        n nVar = new n(location, System.currentTimeMillis(), 0, 0, 0, n.a.NONE);
        v vVar = new v(null, null, nVar, null);
        String d = vVar.d(this.f810l, this.f811m, this.a, true, false, false);
        if (!(d == null || !u0.m(d))) {
            i(d, vVar, this.f812n, nVar);
            return;
        }
        c cVar = this.p;
        if (cVar != null) {
            cVar.a(q5.w, this.o);
        }
    }

    public boolean p() {
        if (this.b == 1) {
            return false;
        }
        synchronized (this.f806h) {
            t.l(this.f804f, 1001, 0, 0, null);
            t.d(this.f804f, 1003, 500L);
        }
        return true;
    }

    public void t(int i2) {
        this.f810l = i2;
    }
}
