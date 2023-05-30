package c.t.m.g;

import android.location.Location;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import java.util.Locale;

/* loaded from: classes.dex */
public class h2 {

    /* renamed from: g  reason: collision with root package name */
    public static volatile h2 f462g;
    public d0 a;
    public TencentLocationManager b;

    /* renamed from: c  reason: collision with root package name */
    public y4 f463c;
    public o2 d;

    /* renamed from: e  reason: collision with root package name */
    public o2 f464e;

    /* renamed from: f  reason: collision with root package name */
    public final TencentLocationListener f465f;

    /* loaded from: classes.dex */
    public class a implements TencentLocationListener {
        public a() {
        }

        @Override // com.tencent.map.geolocation.TencentLocationListener
        public void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
            z0.f("SDK", String.format(Locale.ENGLISH, "callback,%d,%s,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f", Integer.valueOf(i2), tencentLocation.getProvider(), Double.valueOf(tencentLocation.getLatitude()), Double.valueOf(tencentLocation.getLongitude()), Double.valueOf(tencentLocation.getAltitude()), Float.valueOf(tencentLocation.getAccuracy()), Float.valueOf(tencentLocation.getBearing()), Float.valueOf(tencentLocation.getSpeed())));
            if (h2.this.d == o2.q) {
                h2.this.d = new o2(tencentLocation);
            } else {
                h2.this.d.d(tencentLocation);
            }
            h2.this.d.b(i2);
        }

        @Override // com.tencent.map.geolocation.TencentLocationListener
        public void onStatusUpdate(String str, int i2, String str2) {
        }
    }

    public h2(y4 y4Var) {
        o2 o2Var = o2.q;
        this.d = o2Var;
        this.f464e = o2Var;
        this.f465f = new a();
        this.f463c = y4Var;
        this.a = y4Var.k().a();
        this.b = TencentLocationManager.getInstance(y4Var.a);
    }

    public static h2 b(y4 y4Var) {
        if (f462g == null) {
            synchronized (h2.class) {
                if (f462g == null) {
                    f462g = new h2(y4Var);
                }
            }
        }
        return f462g;
    }

    public int a(int i2) {
        if (this.f463c.p()) {
            int startDrEngine = this.a.startDrEngine(i2);
            if (this.a.a()) {
                g();
            }
            return startDrEngine;
        }
        return -1;
    }

    public TencentLocation e() {
        double[] position = this.a.getPosition();
        if (position != null && q3.b(position[1], position[2])) {
            Location location = new Location("gps");
            location.setLatitude(position[1]);
            location.setLongitude(position[2]);
            double[] dArr = new double[2];
            u0.k(location, dArr);
            position[1] = dArr[0];
            position[2] = dArr[1];
        }
        r1 r1Var = new r1(position);
        o2 o2Var = this.f464e;
        o2 o2Var2 = o2.q;
        if (o2Var == o2Var2) {
            this.f464e = new o2(r1Var);
        } else {
            o2Var.c(r1Var);
        }
        z0.f("DR", String.format(Locale.ENGLISH, "update,%d,%s,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f", Integer.valueOf(this.f464e.e()), this.f464e.getProvider(), Double.valueOf(this.f464e.getLatitude()), Double.valueOf(this.f464e.getLongitude()), Double.valueOf(this.f464e.getAltitude()), Float.valueOf(this.f464e.getAccuracy()), Float.valueOf(this.f464e.getBearing()), Float.valueOf(this.f464e.getSpeed())));
        if (this.f464e.e() == 0) {
            z0.f("TxDR", "callback,DR");
            o2 o2Var3 = new o2(this.d);
            o2Var3.c(r1Var);
            return o2Var3;
        } else if (this.d.e() == 0) {
            z0.f("TxDR", "callback,SDK");
            return new o2(this.d);
        } else {
            z0.f("TxDR", "callback,ERR");
            return o2Var2;
        }
    }

    public boolean f() {
        if (this.f463c.p()) {
            return this.a.isSupport();
        }
        return false;
    }

    public final void g() {
        TencentLocationRequest interval = TencentLocationRequest.create().setInterval(1000L);
        interval.setAllowGPS(true);
        z0.f("SDK", "register " + this.b.requestLocationUpdates(interval, this.f465f));
    }

    public void h() {
        this.b.removeUpdates(this.f465f);
        this.a.terminateDrEngine();
    }
}
