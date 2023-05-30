package c.t.m.g;

import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import c.t.m.g.w1;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.Typography;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class v0 extends s {

    /* renamed from: l */
    public final File f713l;
    public volatile long t;
    public volatile int u;
    public volatile int v;
    public volatile int w;
    public volatile float x;
    public volatile float y;

    /* renamed from: k */
    public final StringBuilder f712k = new StringBuilder();

    /* renamed from: m */
    public volatile long f714m = 0;

    /* renamed from: n */
    public volatile long f715n = 0;
    public final long[] o = new long[2];
    public final int[] p = new int[2];
    public ArrayList<Float> q = new ArrayList<>();
    public ArrayList<Float> r = new ArrayList<>();
    public HashSet<Integer> s = new HashSet<>();
    public AtomicBoolean z = new AtomicBoolean(false);

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
            v0.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (v0.this.z.get()) {
                return;
            }
            v0.this.z.set(true);
            try {
                v0.this.B();
            } catch (Throwable unused) {
            }
            v0.this.z.set(false);
        }
    }

    /* loaded from: classes.dex */
    public class b implements v4 {

        /* renamed from: g */
        public final /* synthetic */ File f717g;

        public b(v0 v0Var, File file) {
            this.f717g = file;
        }

        @Override // c.t.m.g.v4
        public void a(String str) {
            z0.e();
        }

        @Override // c.t.m.g.v4
        public void b(String str) {
            z0.e();
            this.f717g.delete();
        }
    }

    public v0(File file) {
        this.f713l = file;
    }

    public final void A() {
        this.f715n = System.currentTimeMillis();
        this.f712k.setLength(0);
        Arrays.fill(this.p, 0);
        Arrays.fill(this.o, 0L);
    }

    public final void B() {
        File file = this.f713l;
        File[] listFiles = (file == null || !file.exists()) ? null : this.f713l.listFiles();
        if (t2.i(listFiles)) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        w1.a c2 = w1.c();
        String a2 = w4.a("yyyyMMdd");
        int i2 = 0;
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (file2.exists() && file2.isFile() && name.startsWith("utr_") && !name.contains(a2)) {
                if (currentTimeMillis - file2.lastModified() <= 1296000000) {
                    byte[] f2 = u6.f(file2);
                    i2 += f2.length;
                    if (z0.e()) {
                        StringBuilder sb = new StringBuilder("upload file:");
                        sb.append(file2.getName());
                        sb.append(",len=");
                        sb.append(f2.length);
                        sb.append(",sum=");
                        sb.append(i2);
                        sb.append(",netType=");
                        sb.append(c2);
                    }
                    if (f2.length != 0) {
                        a2.f297k.a(z(), f2, new b(this, file2));
                        if (c2 == w1.a.NETWORK_MOBILE || i2 >= 512000) {
                            return;
                        }
                    }
                } else if (z0.e()) {
                    new StringBuilder("del file:").append(file2.getName());
                }
                file2.delete();
            }
        }
    }

    @Override // c.t.m.g.q0
    public int a(Looper looper) {
        this.t = 0L;
        this.w = -1;
        this.v = -1;
        g(1001, 0L);
        return 0;
    }

    @Override // c.t.m.g.q0
    public String b() {
        return "UserTrackPro";
    }

    @Override // c.t.m.g.q0
    public void d() {
        g(1002, 0L);
        this.q.clear();
        this.r.clear();
        this.s.clear();
    }

    @Override // c.t.m.g.s
    public void f(Message message) {
        switch (message.what) {
            case 1001:
                A();
                this.f714m = System.currentTimeMillis() - 40000;
                g(1004, 300000L);
                return;
            case 1002:
                t.j(k());
                int[] iArr = this.p;
                if (iArr[0] + iArr[1] >= 3) {
                    v(this.f712k.toString());
                }
                A();
                x();
                return;
            case 1003:
                t((String) message.obj);
                return;
            case 1004:
                g(1004, 1800000L);
                x();
                return;
            default:
                return;
        }
    }

    public final float o(List<Float> list) {
        int size = list.size();
        if (size == 0) {
            return 0.0f;
        }
        if (size % 2 == 0) {
            int i2 = size / 2;
            return (list.get(i2 - 1).floatValue() + list.get(i2).floatValue()) / 2.0f;
        }
        return list.get((size - 1) / 2).floatValue();
    }

    public void q(int i2, long j2, Object obj) {
        if (obj == null) {
            return;
        }
        synchronized (this.f614h) {
            this.q.clear();
            this.r.clear();
            this.s.clear();
            int i3 = 0;
            if (i2 == 1) {
                Iterable<GpsSatellite> satellites = ((GpsStatus) obj).getSatellites();
                Iterator<GpsSatellite> it = satellites == null ? null : satellites.iterator();
                while (it != null && it.hasNext()) {
                    GpsSatellite next = it.next();
                    float snr = next.getSnr();
                    if (next.usedInFix()) {
                        this.r.add(Float.valueOf(snr));
                    }
                    i3++;
                    if (!f2.e(snr, 0.0f)) {
                        this.q.add(Float.valueOf(snr));
                    }
                }
            } else if (i2 == 2 && Build.VERSION.SDK_INT >= 24) {
                GnssStatus gnssStatus = (GnssStatus) obj;
                int i4 = 0;
                while (i3 < gnssStatus.getSatelliteCount()) {
                    try {
                        int svid = gnssStatus.getSvid(i3) + (gnssStatus.getConstellationType(i3) * 1000);
                        if (!this.s.contains(Integer.valueOf(svid))) {
                            this.s.add(Integer.valueOf(svid));
                            float cn0DbHz = gnssStatus.getCn0DbHz(i3);
                            if (gnssStatus.usedInFix(i3)) {
                                this.r.add(Float.valueOf(cn0DbHz));
                            }
                            i4++;
                            if (!f2.e(cn0DbHz, 0.0f)) {
                                this.q.add(Float.valueOf(cn0DbHz));
                            }
                        }
                        i3++;
                    } catch (Throwable unused) {
                    }
                }
                i3 = i4;
            }
            Collections.sort(this.q);
            Collections.sort(this.r);
            this.t = j2;
            this.u = i3;
            this.v = this.q.size();
            this.w = this.r.size();
            this.x = o(this.q);
            this.y = o(this.r);
        }
    }

    public void r(int i2, Location location) {
        String format;
        synchronized (this.f614h) {
            if (c()) {
                long currentTimeMillis = System.currentTimeMillis();
                long[] jArr = this.o;
                if (currentTimeMillis - jArr[0] < 900) {
                    return;
                }
                jArr[0] = currentTimeMillis;
                int[] iArr = this.p;
                iArr[0] = iArr[0] + 1;
                if (location == null || !"gps".equals(location.getProvider())) {
                    return;
                }
                if (a2.f294h || Build.VERSION.SDK_INT < 18 || !location.isFromMockProvider()) {
                    if (currentTimeMillis - this.t > 2000) {
                        format = String.format(Locale.ENGLISH, "%d,G,%d,%d,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,-1,-1,0,-1,0", Long.valueOf(currentTimeMillis), Long.valueOf(location.getTime()), Integer.valueOf(i2), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getSpeed()), Float.valueOf(location.getBearing()));
                    } else {
                        format = String.format(Locale.ENGLISH, "%d,G,%d,%d,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,%d,%d,%.2f,%d,%.2f", Long.valueOf(currentTimeMillis), Long.valueOf(location.getTime()), Integer.valueOf(i2), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getSpeed()), Float.valueOf(location.getBearing()), Integer.valueOf(this.u), Integer.valueOf(this.v), Float.valueOf(this.x), Integer.valueOf(this.w), Float.valueOf(this.y));
                    }
                    t.l(k(), 1003, 0, 0, format);
                }
            }
        }
    }

    public void s(long j2, int i2, double d, double d2, double d3) {
    }

    public final void t(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f712k.length() == 0) {
            this.f712k.append(y());
            this.f715n = currentTimeMillis;
        }
        StringBuilder sb = this.f712k;
        sb.append(Typography.dollar);
        sb.append(str);
        if (this.f712k.length() >= 15360 || (this.f715n != 0 && currentTimeMillis - this.f715n >= 600000)) {
            int[] iArr = this.p;
            if (iArr[0] + iArr[1] >= 3) {
                v(this.f712k.toString());
            }
            this.f712k.setLength(0);
            Arrays.fill(this.p, 0);
        }
    }

    public final void v(String str) {
        if (t2.c(str)) {
            return;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] c2 = p2.c(bytes, 3);
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("srcBytes.len=");
                sb.append(bytes.length);
                sb.append(",encBytes.len=");
                sb.append(c2.length);
            }
            File file = new File(this.f713l, "utr_" + o1.a(e.class.getName(), MessageDigestAlgorithms.SHA_256).substring(0, 8) + CartConstant.KEY_YB_INFO_LINK + w4.a("yyyyMMdd"));
            u6.e(file, c2, true);
            if (file.length() > 51200) {
                file.renameTo(new File(file.getParent(), file.getName() + CartConstant.KEY_YB_INFO_LINK + w4.a("HHmmss")));
            }
        } catch (Throwable unused) {
            z0.e();
        }
    }

    public void w(long j2) {
        if (j2 < 0) {
            j2 = 0;
        }
        g(1004, j2);
    }

    public final void x() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f714m < 60000) {
            z0.e();
            return;
        }
        this.f714m = currentTimeMillis;
        w1.a c2 = w1.c();
        boolean z = true;
        if (c2 == w1.a.NETWORK_NONE) {
            z = false;
        } else if (c2 == w1.a.NETWORK_MOBILE) {
            boolean z2 = a2.f292f;
            if (!z2 && a2.f293g) {
                long longValue = ((Long) r3.d("LocationSDK", "log_utr_up_in_m", Long.valueOf(currentTimeMillis))).longValue();
                if (currentTimeMillis - longValue > 86400000) {
                    r3.h("LocationSDK", "log_utr_up_in_m", Long.valueOf(currentTimeMillis));
                    if (z0.e()) {
                        StringBuilder sb = new StringBuilder("upload in mobile once today. lastUpT=");
                        sb.append(longValue);
                        sb.append(",curT=");
                        sb.append(currentTimeMillis);
                    }
                }
            }
            z = z2;
        }
        if (z) {
            d.d("th_loc_task_t_consume", new a());
        }
    }

    public final String y() {
        String str = z3.h().replaceAll("[| _,]", "") + CartConstant.KEY_YB_INFO_LINK + z3.i() + CartConstant.KEY_YB_INFO_LINK + z3.j();
        String str2 = ((String) l2.a(BaseInfo.getDeviceManufacture(), "")).replaceAll("[| _,]", "") + CartConstant.KEY_YB_INFO_LINK + ((String) l2.a(z3.k(), "")).replaceAll("[| _,]", "");
        String a2 = z3.a();
        if (t2.c(a2) || "0123456789ABCDEF".equals(a2)) {
            a2 = z3.p();
        }
        StringBuilder sb = new StringBuilder("SYSTEM,");
        sb.append(System.currentTimeMillis());
        sb.append(',');
        sb.append(a2);
        sb.append(',');
        sb.append(a2.f295i == null ? "" : a2.f295i.a());
        sb.append(',');
        sb.append(str);
        sb.append(',');
        sb.append(str2);
        sb.append(',');
        sb.append(Build.VERSION.SDK_INT);
        sb.append(',');
        sb.append(x2.a());
        sb.append(',');
        sb.append(x2.b());
        sb.append(',');
        sb.append(x2.c().replaceAll(":", "").toLowerCase());
        sb.append(',');
        sb.append(x2.d());
        return sb.toString();
    }

    public final String z() {
        String str = a2.f294h ? "https://testdatalbs.sparta.html5.qq.com/tr?utr" : "https://analytics.map.qq.com/tr?utr";
        return !a2.f291e ? str.replace("https:", "http:") : str;
    }
}
