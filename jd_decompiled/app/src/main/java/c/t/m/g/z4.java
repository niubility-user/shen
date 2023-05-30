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
import com.jd.libs.hybrid.HybridSDK;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(java.lang.String r11, c.t.m.g.v r12, int r13, c.t.m.g.n r14) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.z4.i(java.lang.String, c.t.m.g.v, int, c.t.m.g.n):void");
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
