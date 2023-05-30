package c.t.m.g;

import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class b6 {
    public final List<i6> a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f320c;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public long f321e;

    /* renamed from: f  reason: collision with root package name */
    public long f322f;

    /* renamed from: g  reason: collision with root package name */
    public long f323g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f324h;

    /* renamed from: i  reason: collision with root package name */
    public HandlerThread f325i;

    /* renamed from: j  reason: collision with root package name */
    public Looper f326j;

    /* loaded from: classes.dex */
    public class a extends GnssStatus.Callback {
        public a() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i2) {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            Iterator it = b6.this.a.iterator();
            while (it.hasNext()) {
                ((i6) it.next()).a(gnssStatus);
            }
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
        }
    }

    /* loaded from: classes.dex */
    public class b extends GnssNavigationMessage.Callback {
        public b() {
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) {
            if (b6.this.f320c) {
                Iterator it = b6.this.a.iterator();
                while (it.hasNext()) {
                    ((i6) it.next()).c(gnssNavigationMessage);
                }
            }
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onStatusChanged(int i2) {
            if (b6.this.f320c) {
                Iterator it = b6.this.a.iterator();
                while (it.hasNext()) {
                    ((i6) it.next()).a(i2);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends GnssMeasurementsEvent.Callback {
        public c() {
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssMeasurementsEvent) {
            if (b6.this.d) {
                Iterator it = b6.this.a.iterator();
                while (it.hasNext()) {
                    ((i6) it.next()).b(gnssMeasurementsEvent);
                }
            }
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(int i2) {
            if (b6.this.d) {
                Iterator it = b6.this.a.iterator();
                while (it.hasNext()) {
                    ((i6) it.next()).b(i2);
                }
            }
        }
    }

    static {
        TimeUnit.SECONDS.toMillis(1L);
    }

    public b6(Context context, i6... i6VarArr) {
        new a();
        this.b = true;
        this.f320c = true;
        new b();
        this.d = true;
        new c();
        this.f321e = 0L;
        this.f322f = 0L;
        this.f323g = 0L;
        this.f324h = true;
        HandlerThread handlerThread = new HandlerThread("GPS_DEMO");
        this.f325i = handlerThread;
        handlerThread.start();
        this.f326j = this.f325i.getLooper();
        new Handler(this.f326j);
        this.a = Arrays.asList(i6VarArr);
        context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
    }
}
