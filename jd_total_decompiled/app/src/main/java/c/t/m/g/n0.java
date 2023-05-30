package c.t.m.g;

import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class n0 extends s {

    /* renamed from: k  reason: collision with root package name */
    public volatile Handler f554k = null;

    /* renamed from: l  reason: collision with root package name */
    public volatile b f555l = new b();

    /* renamed from: m  reason: collision with root package name */
    public StringBuilder f556m = new StringBuilder();

    /* renamed from: n  reason: collision with root package name */
    public AtomicInteger f557n = new AtomicInteger(0);

    /* loaded from: classes.dex */
    public static class b implements v4, Runnable {

        /* renamed from: g  reason: collision with root package name */
        public volatile String f558g;

        /* renamed from: h  reason: collision with root package name */
        public volatile int f559h;

        public b() {
            this.f559h = 0;
        }

        @Override // c.t.m.g.v4
        public void a(String str) {
            z0.e();
        }

        @Override // c.t.m.g.v4
        public void b(String str) {
            z0.d("FC", "NaviGps," + this.f559h);
            z0.e();
        }

        public void b(String str, int i2) {
            if (t2.c(str)) {
                str = "";
            }
            this.f558g = str;
            this.f559h = i2;
        }

        public final void c(byte[] bArr) {
            byte[] b = f4.b(bArr);
            byte[] b2 = i3.b(b.length);
            byte[] bArr2 = new byte[b2.length + 1 + b.length];
            bArr2[0] = 1;
            System.arraycopy(b2, 0, bArr2, 1, b2.length);
            System.arraycopy(b, 0, bArr2, b2.length + 1, b.length);
            byte[] h2 = o5.h(bArr2, o5.a("fc_gps_for_navi"));
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("start upload:strBytes=");
                sb.append(bArr.length);
                sb.append(",encBytes=");
                sb.append(h2.length);
            }
            e5.a.a("https://rttgpsreport.map.qq.com/report?type=sdk&key=5e1fe70424035ee83066ac22b24f31dc", h2, this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (t2.c(this.f558g)) {
                    return;
                }
                byte[] bytes = this.f558g.getBytes("UTF-8");
                this.f558g = "";
                c(bytes);
            } catch (Throwable unused) {
                z0.e();
            }
        }
    }

    @Override // c.t.m.g.q0
    public int a(Looper looper) {
        t.d(k(), 1001, Final.FIVE_SECOND);
        this.f554k = new Handler(d.e("th_loc_task_t_consume").getLooper());
        this.f556m.setLength(0);
        this.f557n.set(0);
        return 0;
    }

    @Override // c.t.m.g.q0
    public String b() {
        return "GpsNaviPro";
    }

    @Override // c.t.m.g.q0
    public void d() {
        this.f556m.setLength(0);
        this.f557n.set(0);
        this.f555l.b("", 0);
        this.f554k = null;
        d.b("th_loc_task_t_consume");
    }

    @Override // c.t.m.g.s
    public void f(Message message) {
        String sb;
        if (message.what == 1001) {
            t.b(k(), 1001);
            t.d(k(), 1001, Final.FIVE_SECOND);
            int i2 = this.f557n.get();
            synchronized (this.f614h) {
                sb = this.f556m.toString();
                this.f556m.setLength(0);
                this.f557n.set(0);
            }
            if (sb.length() <= 0 || this.f554k == null) {
                return;
            }
            this.f555l.b(sb, i2);
            t.g(this.f554k, this.f555l);
        }
    }

    public void o(int i2, Location location) {
        String str;
        synchronized (this.f614h) {
            if (this.f556m.length() > 5120) {
                this.f556m.setLength(0);
                this.f557n.set(0);
            }
            if (this.f556m.length() > 0) {
                this.f556m.append('\n');
            }
            h hVar = a2.f296j;
            if (t2.c(hVar.a())) {
                str = DYConstants.DY_NULL_STR;
            } else {
                str = "loc_" + hVar.a();
            }
            String a2 = z3.a();
            if (t2.c(a2) || "0123456789ABCDEF".equals(a2)) {
                a2 = z3.p();
            }
            this.f556m.append(String.format(Locale.ENGLISH, "%s,%d,%d,%.6f,%.6f,%.3f,%.3f,%.3f,%.3f,%d,%d,%d,%d,%d,%d,%d,%s,%s,%s", a2, 12, Integer.valueOf(i2), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), Long.valueOf(location.getTime() / 1000), 2, 0, 2, 2, 1, 0, DYConstants.DY_NULL_STR, str, DYConstants.DY_NULL_STR));
            this.f557n.getAndAdd(1);
        }
    }
}
