package c.t.m.g;

import android.annotation.SuppressLint;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;

/* loaded from: classes.dex */
public class w3 extends s {

    /* renamed from: k  reason: collision with root package name */
    public int f728k = 0;

    /* renamed from: l  reason: collision with root package name */
    public volatile g f729l = g.a;

    /* renamed from: m  reason: collision with root package name */
    public LocationManager f730m = null;

    /* renamed from: n  reason: collision with root package name */
    public Object f731n;
    public Object o;
    public Object p;
    public Object q;
    public Object r;
    public Object s;

    /* loaded from: classes.dex */
    public class a extends GnssMeasurementsEvent.Callback {
        public a() {
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssMeasurementsEvent) {
            w3.this.f729l.d(gnssMeasurementsEvent);
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(int i2) {
            w3.this.f729l.b(i2);
        }
    }

    /* loaded from: classes.dex */
    public class b extends GnssNavigationMessage.Callback {
        public b() {
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) {
            w3.this.f729l.g(gnssNavigationMessage);
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onStatusChanged(int i2) {
            w3.this.f729l.f(i2);
        }
    }

    /* loaded from: classes.dex */
    public class c extends GnssStatus.Callback {
        public c() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i2) {
            w3.this.f729l.h(i2);
            w3.this.f729l.j(3);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            w3.this.f729l.i(gnssStatus);
            w3.this.f729l.j(4);
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            w3.this.f729l.a();
            w3.this.f729l.j(1);
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            w3.this.f729l.e();
            w3.this.f729l.j(2);
        }
    }

    /* loaded from: classes.dex */
    public class d implements GpsStatus.Listener {
        public d() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i2) {
            w3.this.f729l.j(i2);
        }
    }

    /* loaded from: classes.dex */
    public class e implements OnNmeaMessageListener {
        public e() {
        }

        @Override // android.location.OnNmeaMessageListener
        public void onNmeaMessage(String str, long j2) {
            w3.this.f729l.c(j2, str);
        }
    }

    /* loaded from: classes.dex */
    public class f implements GpsStatus.NmeaListener {
        public f() {
        }

        @Override // android.location.GpsStatus.NmeaListener
        public void onNmeaReceived(long j2, String str) {
            w3.this.f729l.c(j2, str);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class g {
        public static final g a = new a();

        /* loaded from: classes.dex */
        public static class a extends g {
        }

        public void a() {
        }

        public void b(int i2) {
        }

        public void c(long j2, String str) {
        }

        public void d(Object obj) {
        }

        public void e() {
        }

        public void f(int i2) {
        }

        public void g(Object obj) {
        }

        public void h(int i2) {
        }

        public void i(Object obj) {
        }

        public void j(int i2) {
        }
    }

    @Override // c.t.m.g.q0
    @SuppressLint({"MissingPermission"})
    public int a(Looper looper) {
        if (this.f729l != g.a) {
            LocationManager locationManager = (LocationManager) y3.a().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
            this.f730m = locationManager;
            if (locationManager == null) {
                return -1;
            }
            r();
            q();
            if (Build.VERSION.SDK_INT >= 24) {
                if ((this.f728k & 8) != 0) {
                    a aVar = new a();
                    this.s = aVar;
                    this.f730m.registerGnssMeasurementsCallback(aVar, k());
                }
                if ((this.f728k & 16) != 0) {
                    b bVar = new b();
                    this.r = bVar;
                    this.f730m.registerGnssNavigationMessageCallback(bVar, k());
                    return 0;
                }
                return 0;
            }
            return 0;
        }
        throw new IllegalStateException("Please invoke startup(GpsRequest,GpsInfoCallback,Looper).");
    }

    @Override // c.t.m.g.q0
    public String b() {
        return "GpsExtraInfoPro";
    }

    @Override // c.t.m.g.q0
    public void d() {
        if (this.f730m != null) {
            t();
            s();
            if (Build.VERSION.SDK_INT >= 24) {
                if ((this.f728k & 8) != 0) {
                    this.f730m.unregisterGnssMeasurementsCallback((GnssMeasurementsEvent.Callback) this.s);
                    this.s = null;
                }
                if ((this.f728k & 16) != 0) {
                    this.f730m.unregisterGnssNavigationMessageCallback((GnssNavigationMessage.Callback) this.r);
                    this.r = null;
                }
            }
        }
        this.f728k = 0;
        this.f729l = g.a;
        this.f730m = null;
    }

    @Override // c.t.m.g.s
    public void f(Message message) {
    }

    public void p(int i2, g gVar, Looper looper) {
        synchronized (this.f614h) {
            this.f728k = i2;
            this.f729l = gVar;
            i(looper);
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void q() {
        if ((this.f728k & 4) == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            e eVar = new e();
            this.q = eVar;
            this.f730m.addNmeaListener(eVar, k());
            return;
        }
        f fVar = new f();
        this.o = fVar;
        j3.b(this.f730m, "addNmeaListener", new Class[]{GpsStatus.NmeaListener.class}, new Object[]{fVar});
    }

    @SuppressLint({"MissingPermission"})
    public final void r() {
        if ((this.f728k & 2) == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            c cVar = new c();
            this.p = cVar;
            this.f730m.registerGnssStatusCallback(cVar, k());
            return;
        }
        d dVar = new d();
        this.f731n = dVar;
        this.f730m.addGpsStatusListener(dVar);
    }

    public final void s() {
        if ((this.f728k & 4) == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f730m.removeNmeaListener((OnNmeaMessageListener) this.q);
            this.q = null;
            return;
        }
        j3.b(this.f730m, "removeNmeaListener", new Class[]{GpsStatus.NmeaListener.class}, new Object[]{this.o});
        this.o = null;
    }

    public final void t() {
        if ((this.f728k & 2) == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f730m.unregisterGnssStatusCallback((GnssStatus.Callback) this.p);
            this.p = null;
            return;
        }
        this.f730m.removeGpsStatusListener((GpsStatus.Listener) this.f731n);
        this.f731n = null;
    }
}
