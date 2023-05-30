package c.t.m.g;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;

/* loaded from: classes.dex */
public class q extends b0 {

    /* renamed from: c  reason: collision with root package name */
    public LocationManager f607c;
    public Handler d;

    /* renamed from: f  reason: collision with root package name */
    public volatile Location f609f;

    /* renamed from: g  reason: collision with root package name */
    public volatile int f610g = 2;

    /* renamed from: h  reason: collision with root package name */
    public LocationListener f611h = new b();

    /* renamed from: e  reason: collision with root package name */
    public Runnable f608e = new a();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"MissingPermission"})
        public void run() {
            try {
                if (q.this.d == null || q.this.f610g == 0) {
                    return;
                }
                String str = "passive";
                if (q.this.f610g == 1) {
                    str = "gps";
                } else {
                    int unused = q.this.f610g;
                }
                String str2 = str;
                LocationManager locationManager = q.this.f607c;
                q qVar = q.this;
                locationManager.requestLocationUpdates(str2, 1000L, 0.0f, qVar.f611h, qVar.d.getLooper());
            } catch (Throwable th) {
                t1.b("ArGpsProvider", "No Permission,can not add location listener", th);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements LocationListener {
        public long a = 0;

        public b() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                try {
                    if ("gps".equals(location.getProvider())) {
                        if (l1.a || Build.VERSION.SDK_INT < 18 || !location.isFromMockProvider()) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (Math.abs(currentTimeMillis - this.a) < 1000) {
                                return;
                            }
                            this.a = currentTimeMillis;
                            q.this.f609f = location;
                            float speed = location.hasSpeed() ? location.getSpeed() : -1.0f;
                            if (i.f() != null) {
                                i.f().b(currentTimeMillis, speed);
                            }
                        }
                    }
                } catch (Throwable th) {
                    t1.b("ArGpsProvider", "onLocationChanged error.", th);
                }
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

    public q() {
        this.f607c = null;
        this.f607c = (LocationManager) y3.a().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
    }

    @Override // c.t.m.g.i0
    public String a() {
        return "ArGpsProvider";
    }

    @Override // c.t.m.g.i0
    @SuppressLint({"MissingPermission"})
    public void d() {
        try {
            this.f607c.removeUpdates(this.f611h);
        } catch (Throwable th) {
            t1.b("ArGpsProvider", "remove updates error.", th);
        }
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.d = null;
        t1.a("ArGpsProvider", "status:[shutdown]");
    }

    @Override // c.t.m.g.b0
    public int h(Looper looper) {
        Handler handler = new Handler(looper);
        this.d = handler;
        handler.post(this.f608e);
        t1.a("ArGpsProvider", "status:[start]");
        return 0;
    }

    public void k(int i2) {
        this.f610g = i2;
    }
}
