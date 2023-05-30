package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import c.t.m.g.n;
import c.t.m.g.w3;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class k3 implements LocationListener, com.tencent.tencentmap.lbssdk.service.b {
    public static LinkedList<a> G = new LinkedList<>();
    public volatile Location A;
    public c C;
    public i1 D;
    public final y4 a;

    /* renamed from: c  reason: collision with root package name */
    public volatile GpsStatus f510c;
    public volatile Object d;

    /* renamed from: e  reason: collision with root package name */
    public volatile Location f511e;

    /* renamed from: n  reason: collision with root package name */
    public boolean f520n;
    public volatile boolean p;
    public boolean v;
    public b y;
    public LocationManager z;
    public AtomicInteger b = new AtomicInteger(1024);

    /* renamed from: g  reason: collision with root package name */
    public volatile long f513g = 0;

    /* renamed from: h  reason: collision with root package name */
    public volatile long f514h = 0;

    /* renamed from: i  reason: collision with root package name */
    public volatile long f515i = 0;

    /* renamed from: j  reason: collision with root package name */
    public boolean f516j = false;

    /* renamed from: k  reason: collision with root package name */
    public boolean f517k = false;

    /* renamed from: l  reason: collision with root package name */
    public AtomicInteger f518l = new AtomicInteger(0);

    /* renamed from: m  reason: collision with root package name */
    public AtomicInteger f519m = new AtomicInteger(0);
    public ArrayList<Float> o = new ArrayList<>();
    public volatile boolean q = true;
    public volatile long s = 0;
    public int t = 0;
    public boolean u = false;
    public long x = -1;
    public final double[] E = new double[2];
    public boolean F = true;

    /* renamed from: f  reason: collision with root package name */
    public Location f512f = new Location("gps");
    public s0 w = s0.a();
    public k3 r = this;
    public w3 B = new w3();

    /* loaded from: classes.dex */
    public static class a implements Serializable {
        public double a;
        public double b;

        /* renamed from: c  reason: collision with root package name */
        public float f521c;
        public long d;

        public a(Location location) {
            if (location == null) {
                return;
            }
            this.a = location.getLatitude();
            this.b = location.getLongitude();
            this.f521c = location.getAccuracy();
            this.d = location.getTime();
        }

        public float a() {
            return this.f521c;
        }

        public double b() {
            return this.a;
        }

        public double c() {
            return this.b;
        }

        public long d() {
            return this.d;
        }

        public String toString() {
            return "BasicGpsInfo{latitude=" + this.a + ", longitude=" + this.b + ", accuracy=" + this.f521c + ", timeStamp=" + this.d + '}';
        }
    }

    /* loaded from: classes.dex */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public final void a(Message message) {
            removeMessages(message.what);
            switch (message.what) {
                case R2.attr.itemHorizontalPadding /* 1101 */:
                    Location location = (Location) message.obj;
                    if (location == null) {
                        o4.o("G", "gl null");
                        return;
                    }
                    boolean H = k3.this.H(location);
                    k3 k3Var = k3.this;
                    int b = k3Var.b(k3Var.a.a, location);
                    if (!H || b == 1 || b == 3) {
                        o4.o("G", "gl inRegular");
                        if (b == 1 || b == 3) {
                            k0.g().d(b);
                            return;
                        }
                        return;
                    }
                    k0.g().h();
                    k3 k3Var2 = k3.this;
                    boolean s = k3Var2.s(location, k3Var2.A);
                    k3.this.A = location;
                    if (s) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(location.getLatitude());
                        sb.append(DYConstants.DY_REGEX_COMMA);
                        sb.append(location.getLongitude());
                        sb.append(",isFilter=");
                        sb.append(s);
                        o4.o("G", String.format(Locale.ENGLISH, "l,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,%s", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), "f"));
                        return;
                    } else if (!k3.this.L(location)) {
                        StringBuilder sb2 = new StringBuilder("this location is consider nlp:");
                        Locale locale = Locale.ENGLISH;
                        sb2.append(String.format(locale, "l,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,%s", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), "f"));
                        o4.o("G", String.format(locale, "l,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,%s", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), "nf"));
                        return;
                    } else {
                        k3.this.l(location, b);
                        StringBuilder sb3 = new StringBuilder("notifyListeners, location: ");
                        sb3.append(location);
                        sb3.append(", fakeCode: ");
                        sb3.append(b);
                        k3.this.g0();
                        k3.this.b.set(k3.this.b.get() | 2);
                        if (k3.this.x == -1 || k3.this.x == 0) {
                            k3.this.q(true);
                            k3.this.x = System.currentTimeMillis();
                            return;
                        }
                        return;
                    }
                case 1102:
                    k3.this.g0();
                    o4.o("G", "vf:" + k3.this.f518l.get() + DYConstants.DY_REGEX_COMMA + k3.this.f519m.get());
                    k3.this.W();
                    if (k3.this.f510c != null && k3.this.o != null && k3.this.o.size() > 0) {
                        try {
                            k3 k3Var3 = k3.this;
                            k3Var3.q = k3Var3.w.b(k3.this.o, k3.this.f518l.get());
                        } catch (Throwable unused) {
                        }
                    }
                    if (k3.this.q) {
                        if (k3.this.x == -1 || k3.this.x == 0) {
                            k3.this.q(true);
                        }
                        k3.this.s = System.currentTimeMillis();
                        k3.this.x = System.currentTimeMillis();
                    } else if (k3.this.x == -1 || (System.currentTimeMillis() - k3.this.x > 40000 && k3.this.x != 0)) {
                        k3.this.q(false);
                        k3.this.x = 0L;
                    }
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (k3.this.f520n || k3.this.f518l.get() <= 10 || k3.this.f518l.get() >= 100 || k3.this.A == null || currentTimeMillis - k3.this.A.getTime() <= 60000) {
                            return;
                        }
                        StringBuilder sb4 = new StringBuilder("Visible num:");
                        sb4.append(k3.this.f518l.get());
                        sb4.append(",usedNum:");
                        sb4.append(k3.this.f519m.get());
                        sb4.append(",last gps time:");
                        sb4.append(k3.this.A.getTime());
                        o4.o("G", "restart gps.");
                        k3.this.e0();
                        k3.this.a0();
                        k3.this.f520n = true;
                        return;
                    } catch (Throwable unused2) {
                        return;
                    }
                case 1103:
                    k3.this.b.set(4);
                    k3.this.Y();
                    return;
                case R2.attr.itemIconSize /* 1104 */:
                    k3.this.f518l.set(0);
                    k3.this.f519m.set(0);
                    k3.this.b.set(0);
                    k3.this.f516j = false;
                    k3.this.Y();
                    return;
                case R2.attr.itemIconTint /* 1105 */:
                    k3.this.g();
                    return;
                case R2.attr.itemMarginTop /* 1106 */:
                    k3.this.j0();
                    return;
                default:
                    return;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            try {
                a(message);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends w3.g {
        public c() {
        }

        @Override // c.t.m.g.w3.g
        public void a() {
            super.a();
            k3.this.j(1, null);
        }

        @Override // c.t.m.g.w3.g
        public void b(int i2) {
            i1 i1Var;
            super.b(i2);
            if (!k3.this.V() || k3.this.D == null || (i1Var = k3.this.D) == null) {
                return;
            }
            i1Var.b(6005, i2, 0, null);
        }

        @Override // c.t.m.g.w3.g
        public void c(long j2, String str) {
            i1 i1Var;
            super.c(j2, str);
            t0 t0Var = new t0(str, j2);
            if (!k3.this.V() || k3.this.D == null || (i1Var = k3.this.D) == null) {
                return;
            }
            i1Var.b(6006, 0, 0, t0Var);
        }

        @Override // c.t.m.g.w3.g
        public void d(Object obj) {
            i1 i1Var;
            GnssMeasurementsEvent gnssMeasurementsEvent;
            super.d(obj);
            if ((Build.VERSION.SDK_INT < 24 || !((gnssMeasurementsEvent = (GnssMeasurementsEvent) obj) == null || gnssMeasurementsEvent.getClock() == null || gnssMeasurementsEvent.getMeasurements() == null)) && k3.this.V() && k3.this.D != null && (i1Var = k3.this.D) != null) {
                i1Var.b(6004, 0, 0, obj);
            }
        }

        @Override // c.t.m.g.w3.g
        public void e() {
            super.e();
            k3.this.j(2, null);
        }

        @Override // c.t.m.g.w3.g
        public void f(int i2) {
            i1 i1Var;
            super.f(i2);
            if (!k3.this.V() || k3.this.D == null || (i1Var = k3.this.D) == null) {
                return;
            }
            i1Var.b(6003, i2, 0, null);
        }

        @Override // c.t.m.g.w3.g
        public void g(Object obj) {
            i1 i1Var;
            super.g(obj);
            if (!k3.this.V() || k3.this.D == null || (i1Var = k3.this.D) == null) {
                return;
            }
            i1Var.b(6002, 0, 0, obj);
        }

        @Override // c.t.m.g.w3.g
        public void h(int i2) {
            super.h(i2);
            k3.this.j(3, null);
        }

        @Override // c.t.m.g.w3.g
        public void i(Object obj) {
            super.i(obj);
            k3.this.j(4, obj);
        }

        @Override // c.t.m.g.w3.g
        public void j(int i2) {
            if (Build.VERSION.SDK_INT >= 24) {
                return;
            }
            k3.this.j(i2, null);
        }
    }

    public k3(y4 y4Var, boolean z) {
        this.a = y4Var;
        this.z = y4Var.g();
        if (z) {
            this.D = new i1(y4Var.a);
        }
    }

    public final void A(int i2) {
        this.t = i2;
    }

    public final void B(boolean z) {
        this.u = z;
    }

    @SuppressLint({"NewApi"})
    public final boolean C(Location location) {
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                return location.getElapsedRealtimeNanos() != 0;
            } catch (Throwable unused) {
                return true;
            }
        }
        return true;
    }

    public final long E() {
        return this.f513g;
    }

    public final void G(boolean z) {
        this.v = z;
    }

    public final boolean H(Location location) {
        try {
            if (location.getAccuracy() > 10000.0f) {
                return false;
            }
            if ((!r(location.getLatitude()) || !r(location.getLongitude())) && Math.abs(location.getLatitude()) >= 1.0E-8d && Math.abs(location.getLongitude()) >= 1.0E-8d && Math.abs(location.getLatitude() - 1.0d) >= 1.0E-8d && Math.abs(location.getLongitude() - 1.0d) >= 1.0E-8d && location.getLatitude() >= -90.0d && location.getLatitude() <= 90.0d && location.getLongitude() >= -180.0d) {
                if (location.getLongitude() <= 180.0d) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    public final int K() {
        return this.f519m.get();
    }

    public final boolean L(Location location) {
        return (this.f519m.get() == 0 && location.getBearing() == 0.0f && location.getSpeed() <= 0.0f) ? false : true;
    }

    public final int O() {
        return this.f518l.get();
    }

    public final boolean R() {
        return System.currentTimeMillis() - this.f513g < 20000;
    }

    public final boolean S() {
        try {
            return this.a.g().isProviderEnabled("gps");
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean V() {
        return this.u;
    }

    public final boolean W() {
        int i2 = this.f518l.get();
        int i3 = this.f519m.get();
        if (i2 > 0) {
            this.f517k = true;
        }
        if (i3 > 0) {
            this.f516j = true;
        }
        if (!this.f517k || i2 > 2) {
            if (this.f516j) {
                if (i3 >= 3 || i3 == 0) {
                    return true;
                }
            } else if (i3 == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final void Y() {
        int i2 = this.b.get() == 4 ? 1 : this.b.get() == 0 ? 0 : -1;
        Message message = new Message();
        message.what = R2.id.decode_failed;
        message.arg1 = R2.drawable.x5_icon;
        message.arg2 = i2;
        this.a.f(message);
    }

    public final int a(float f2, double d, double d2) {
        return ((d <= 100000.0d || d / d2 <= 200.0d || d <= ((double) f2)) && (d > 100000.0d || d / d2 <= 50.0d || d <= ((double) f2))) ? 0 : 1;
    }

    @SuppressLint({"MissingPermission"})
    public final void a0() {
        StringBuilder sb;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                this.z.requestLocationUpdates("gps", 1000L, 0.0f, this, Looper.getMainLooper());
                sb = new StringBuilder("request in thread[");
                sb.append(Thread.currentThread().getName());
                sb.append("]");
            } else {
                LocationManager locationManager = this.z;
                b bVar = this.y;
                locationManager.requestLocationUpdates("gps", 1000L, 0.0f, this, bVar == null ? Looper.getMainLooper() : bVar.getLooper());
                sb = new StringBuilder("request in thread[");
                sb.append(Thread.currentThread().getName());
                sb.append("]");
            }
            o4.o("G", sb.toString());
        } catch (Throwable unused) {
            s5.a = true;
            o4.o("G", "request failed.");
        }
        try {
            Bundle bundle = new Bundle();
            this.z.sendExtraCommand("gps", "force_xtra_injection", bundle);
            this.z.sendExtraCommand("gps", "force_time_injection", bundle);
        } catch (Exception unused2) {
        }
    }

    @SuppressLint({"NewApi"})
    public final int b(Context context, Location location) {
        if (!k0.f() && !i1.d(location)) {
            int c2 = c(location);
            if (c2 != 0) {
                return c2;
            }
            if (!this.a.g().isProviderEnabled("gps")) {
                return 1;
            }
            if (Build.VERSION.SDK_INT >= 18) {
                if (location.isFromMockProvider()) {
                    return 1;
                }
            }
            if (!C(location)) {
                return 1;
            }
            if (!this.q && this.f519m.get() < 4 && System.currentTimeMillis() - this.s > 120000) {
                o4.o("G", "Mock:2");
                return 2;
            }
            Location location2 = this.f512f;
            if (location2 != null && location2.getTime() != 0 && System.currentTimeMillis() - this.f512f.getTime() < Final.HALF_MINUTE) {
                float distanceTo = location.distanceTo(this.f512f);
                if (distanceTo > 100.0f) {
                    o4.o("G", "D:3:".concat(String.valueOf(distanceTo)));
                    if (!this.q) {
                        return 3;
                    }
                }
            }
        }
        return 0;
    }

    public final int c(Location location) {
        int i2;
        if (location == null) {
            return 0;
        }
        if (G == null) {
            G = new LinkedList<>();
        }
        Iterator<a> it = G.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            a next = it.next();
            float accuracy = location.getAccuracy() + next.a();
            double distanceBetween = TencentLocationUtils.distanceBetween(next.b(), next.c(), location.getLatitude(), location.getLongitude());
            if (location.getTime() - next.d() >= 2000) {
                i3 += a(accuracy, distanceBetween, ((float) r7) / 1000.0f);
            }
        }
        if (i3 >= 3) {
            i2 = -2;
            StringBuilder sb = new StringBuilder("invalidSpeedNum:");
            sb.append(i3);
            sb.append(", gps speed fake");
        } else {
            a aVar = new a(location);
            if (G.isEmpty()) {
                G.add(aVar);
            } else {
                a last = G.getLast();
                if (System.currentTimeMillis() - last.d() >= 2000 && t(aVar, last)) {
                    G.add(aVar);
                }
            }
            i2 = 0;
        }
        if (G.size() > 10) {
            LinkedList<a> linkedList = G;
            linkedList.subList(0, linkedList.size() - 10).clear();
        }
        return i2;
    }

    public final void c0() {
        i1 i1Var;
        if (this.p) {
            this.p = false;
            this.b.set(1024);
            this.f516j = false;
            this.f517k = false;
            this.f518l.set(0);
            this.f519m.set(0);
            this.o.clear();
            this.x = -1L;
            this.v = false;
            this.f520n = false;
            Arrays.fill(this.E, 0.0d);
            h(R2.attr.itemMarginTop);
            e0();
            this.y = null;
            this.f511e = null;
            this.A = null;
            try {
                this.B.m();
                this.C = null;
            } catch (Exception unused) {
            }
            if (!V() || (i1Var = this.D) == null) {
                return;
            }
            i1Var.a();
        }
    }

    public final void e0() {
        try {
            this.a.g().removeUpdates(this.r);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b1 A[Catch: IOException -> 0x00b5, TRY_ENTER, TRY_LEAVE, TryCatch #4 {IOException -> 0x00b5, blocks: (B:20:0x008c, B:40:0x00b1), top: B:63:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.ObjectInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void g() {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e2;
        FileInputStream fileInputStream2;
        Exception e3;
        ObjectInputStream objectInputStream;
        Context context = this.a.a;
        ?? r2 = 0;
        r2 = 0;
        try {
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    File filesDir = context.getFilesDir();
                    filesDir.getClass();
                    sb.append(filesDir.getAbsolutePath());
                    sb.append(File.separator);
                    sb.append("g_q");
                    String sb2 = sb.toString();
                    if (!new File(sb2).exists()) {
                        StringBuilder sb3 = new StringBuilder("file: ");
                        sb3.append(sb2);
                        sb3.append(" not exits.");
                        return;
                    }
                    fileInputStream2 = context.openFileInput("g_q");
                    try {
                        objectInputStream = new ObjectInputStream(fileInputStream2);
                    } catch (Exception e4) {
                        e2 = e4;
                        e3 = e2;
                        objectInputStream = null;
                        e3.printStackTrace();
                        if (fileInputStream2 != null) {
                        }
                        if (objectInputStream == null) {
                        }
                    } catch (Throwable th2) {
                        fileInputStream = fileInputStream2;
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        LinkedList<a> linkedList = (LinkedList) objectInputStream.readObject();
                        G = linkedList;
                        if (linkedList == null) {
                            G = new LinkedList<>();
                        } else if (!linkedList.isEmpty()) {
                            StringBuilder sb4 = new StringBuilder("get gpsLocQueue form file, size:");
                            sb4.append(G.size());
                            sb4.append(LangUtils.SINGLE_SPACE);
                            sb4.append(G.toString());
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        objectInputStream.close();
                    } catch (Exception e8) {
                        e3 = e8;
                        e3.printStackTrace();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (objectInputStream == null) {
                            objectInputStream.close();
                        }
                    }
                } catch (Exception e10) {
                    e2 = e10;
                    fileInputStream2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
            } catch (IOException e11) {
                e11.printStackTrace();
            }
        } catch (Throwable th4) {
            fileInputStream = "g_q";
            th = th4;
            r2 = context;
        }
    }

    public final void g0() {
        int i2;
        StringBuilder sb;
        this.o.clear();
        int i3 = 0;
        try {
            p b2 = p.b();
            long currentTimeMillis = System.currentTimeMillis();
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    GnssStatus gnssStatus = (GnssStatus) this.d;
                    if (gnssStatus != null) {
                        int i4 = 0;
                        i2 = 0;
                        while (i3 < gnssStatus.getSatelliteCount()) {
                            try {
                                try {
                                    this.o.add(Float.valueOf(gnssStatus.getCn0DbHz(i3)));
                                    i4++;
                                    if (gnssStatus.usedInFix(i3)) {
                                        i2++;
                                    }
                                } catch (Throwable unused) {
                                }
                                i3++;
                            } catch (Throwable unused2) {
                                i3 = i4;
                                this.f518l.set(i3);
                                this.f519m.set(i2);
                                sb = new StringBuilder("viewSate: ");
                                sb.append(i3);
                                sb.append(", usedSate: ");
                                sb.append(i2);
                            }
                        }
                        i3 = i4;
                    } else {
                        i2 = 0;
                    }
                    if (b2 != null) {
                        b2.c(2, currentTimeMillis, gnssStatus);
                    }
                } else {
                    GpsStatus gpsStatus = this.f510c;
                    Iterator<GpsSatellite> it = gpsStatus == null ? null : gpsStatus.getSatellites().iterator();
                    i2 = 0;
                    if (it != null) {
                        while (it.hasNext()) {
                            GpsSatellite next = it.next();
                            i3++;
                            this.o.add(Float.valueOf(next.getSnr()));
                            if (next.usedInFix()) {
                                i2++;
                            }
                        }
                    }
                    if (b2 != null) {
                        b2.c(1, currentTimeMillis, gpsStatus);
                    }
                }
                this.f518l.set(i3);
                this.f519m.set(i2);
                sb = new StringBuilder("viewSate: ");
            } catch (Throwable unused3) {
            }
        } catch (Throwable unused4) {
            i2 = 0;
        }
        sb.append(i3);
        sb.append(", usedSate: ");
        sb.append(i2);
    }

    public final void h(int i2) {
        b bVar = this.y;
        if (bVar != null) {
            bVar.obtainMessage(i2).sendToTarget();
        }
    }

    public final void i(int i2, Location location) {
        try {
            b bVar = this.y;
            if (bVar != null) {
                new StringBuilder("innerthread isalive:").append(bVar.getLooper().getThread().isAlive());
                Message obtainMessage = bVar.obtainMessage(i2);
                obtainMessage.obj = location;
                bVar.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            o4.o("G", th.toString());
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void j(int i2, Object obj) {
        i1 i1Var;
        o4.o("G", "e[" + i2 + "]");
        boolean z = true;
        if (i2 == 1) {
            AtomicInteger atomicInteger = this.b;
            atomicInteger.set(atomicInteger.get() | 1);
        } else if (i2 == 2) {
            this.b.set(0);
        } else if (i2 == 3) {
            AtomicInteger atomicInteger2 = this.b;
            atomicInteger2.set(atomicInteger2.get() | 2);
        } else if (i2 != 4) {
        } else {
            if (Build.VERSION.SDK_INT < 24) {
                try {
                    this.f510c = this.a.g().getGpsStatus(this.f510c);
                    StringBuilder sb = new StringBuilder("mGpsStatus: ");
                    if (this.f510c != null) {
                        z = false;
                    }
                    sb.append(z);
                } catch (Throwable unused) {
                }
            } else {
                this.d = obj;
                if (V() && (i1Var = this.D) != null && i1Var != null) {
                    i1Var.b(6001, 0, 0, obj);
                }
            }
            h(1102);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
        if (r1 != null) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void j0() {
        FileOutputStream fileOutputStream;
        Exception e2;
        Exception e3;
        ObjectOutputStream objectOutputStream;
        LinkedList<a> linkedList = G;
        if (linkedList == null || linkedList.isEmpty()) {
            return;
        }
        int size = G.size();
        if (size > 10) {
            G.subList(0, size - 10).clear();
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            fileOutputStream = this.a.a.openFileOutput("g_q", 0);
        } catch (Exception e4) {
            e2 = e4;
            fileOutputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (Exception e5) {
            e2 = e5;
            e3 = e2;
            objectOutputStream = null;
            e3.printStackTrace();
            if (fileOutputStream != null) {
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
            }
            if (objectOutputStream2 != null) {
            }
            throw th;
        }
        try {
            try {
                objectOutputStream.writeObject(G);
                new StringBuilder("gpsLocQueue write to file, size:").append(G.size());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                objectOutputStream2 = objectOutputStream;
                th = th3;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e9) {
            e3 = e9;
            e3.printStackTrace();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
        }
        try {
            objectOutputStream.close();
        } catch (IOException e11) {
            e11.printStackTrace();
        }
        G.clear();
    }

    public final void k(Location location, double d, double d2, int i2, int i3) {
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

    public final void l(Location location, int i2) {
        int i3 = (this.f519m.get() < 4 || this.f519m.get() > 6) ? this.f519m.get() >= 7 ? 3 : 1 : 2;
        if (this.v && a6.b(location.getLatitude(), location.getLongitude())) {
            for (int i4 = 0; i4 <= 3; i4++) {
                double[] dArr = this.E;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                u0.k(location, dArr);
                StringBuilder sb = new StringBuilder("deflected, ");
                sb.append(i4);
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(this.E[0]);
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(this.E[1]);
                double[] dArr2 = this.E;
                if (dArr2[0] != 0.0d && dArr2[1] != 0.0d) {
                    break;
                }
            }
            double[] dArr3 = this.E;
            k(location, dArr3[0], dArr3[1], i3, i2);
            StringBuilder sb2 = new StringBuilder("deflected, ");
            sb2.append(this.E[0]);
            sb2.append(DYConstants.DY_REGEX_COMMA);
            sb2.append(this.E[1]);
        } else {
            k(location, location.getLatitude(), location.getLongitude(), i3, i2);
        }
        this.f513g = System.currentTimeMillis();
        this.a.f(new n(location, E(), O(), K(), w(), n.a.GPS));
    }

    public final void m(Location location, String str) {
        StringBuilder sb;
        long j2;
        if (x6.a) {
            double[] dArr = new double[2];
            u0.k(location, dArr);
            String format = String.format(Locale.getDefault(), ": %f,%f,%f,%f,%f,%f,%d", Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), Long.valueOf(location.getTime()));
            StringBuilder sb2 = new StringBuilder("$ ");
            sb2.append(str);
            sb2.append(format);
        }
        if ("gps".equals(location.getProvider())) {
            Bundle extras = location.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putString("gnss_source", str);
            location.setExtras(extras);
            if (str.equals(TencentLocation.BEIDOU_PROVIDER)) {
                this.f514h = System.currentTimeMillis();
                sb = new StringBuilder("mLastBeiDouTime: ");
                j2 = this.f514h;
            } else {
                this.f515i = System.currentTimeMillis();
                sb = new StringBuilder("mLastGpsOriginTime: ");
                j2 = this.f515i;
            }
            sb.append(j2);
            long currentTimeMillis = System.currentTimeMillis() - this.f514h;
            long currentTimeMillis2 = System.currentTimeMillis() - this.f515i;
            StringBuilder sb3 = new StringBuilder("lastBeidouElapsedTime: ");
            sb3.append(currentTimeMillis);
            sb3.append(", lastGpsOriginElapsedTime: ");
            sb3.append(currentTimeMillis2);
            if (this.t == 21) {
                if (str.equals("gps") && currentTimeMillis < 1500) {
                    return;
                }
            } else if (str.equals(TencentLocation.BEIDOU_PROVIDER) && currentTimeMillis2 < 3000) {
                return;
            }
            if (location.getExtras() != null) {
                int i2 = location.getExtras().getInt("SourceType", 0);
                if ((i2 & 128) == 128) {
                    o4.o("G", "SourceType:".concat(String.valueOf(i2)));
                    return;
                }
            }
            if (this.f511e == null || location.getTime() - this.f511e.getTime() > 10000 || this.F) {
                o4.o("G", String.format(Locale.ENGLISH, "l,%.6f,%.6f,%.1f,%.1f,%.1f,%.1f,%s", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), str));
            }
            this.F = !this.F;
            this.f511e = location;
            i(R2.attr.itemHorizontalPadding, location);
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void n(Handler handler, Handler handler2, Handler handler3, boolean z) {
        i1 i1Var;
        if (this.p) {
            return;
        }
        this.p = true;
        this.f513g = 0L;
        Looper looper = handler == null ? null : handler.getLooper();
        b bVar = this.y;
        if (bVar == null || bVar.getLooper() != looper) {
            if (looper != null) {
                this.y = new b(looper);
            } else {
                this.y = new b(Looper.getMainLooper());
            }
        }
        try {
            if (z) {
                this.z.requestLocationUpdates("passive", 2000L, 1.0f, this);
            } else {
                a0();
                if (V() && (i1Var = this.D) != null) {
                    i1Var.c(this, this.y.getLooper());
                }
                this.f520n = false;
                this.C = new c();
                this.B.p(V() ? 30 : 2, this.C, this.y.getLooper());
                this.s = System.currentTimeMillis();
            }
        } catch (Throwable unused) {
        }
        if (S()) {
            this.b.set(4);
            Y();
        }
        h(R2.attr.itemIconTint);
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        i1 i1Var;
        if (location == null) {
            return;
        }
        try {
            StringBuilder sb = new StringBuilder("onLocationChanged: ");
            sb.append((Object) DateFormat.format("yyyy-MM-dd kk:mm:ss", location.getTime()));
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(Build.VERSION.SDK_INT >= 17 ? location.getElapsedRealtimeNanos() : 0L);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(location.getLatitude());
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(location.getLongitude());
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(location.toString());
            Location location2 = new Location(location);
            if (V() && (i1Var = this.D) != null && i1Var != null) {
                i1Var.b(R2.dimen.dp_459, 0, 0, location2);
            }
            m(location, "gps");
        } catch (Throwable unused) {
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
        if ("gps".equals(str)) {
            h(R2.attr.itemIconSize);
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
        if ("gps".equals(str)) {
            h(1103);
        }
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i2, Bundle bundle) {
        i1 i1Var;
        l0 l0Var = new l0(str, i2, bundle);
        if (!V() || (i1Var = this.D) == null || i1Var == null) {
            return;
        }
        i1Var.b(R2.dimen.dp_46, 0, 0, l0Var);
    }

    public final void q(boolean z) {
        int i2 = z ? 3 : 4;
        Message message = new Message();
        message.what = R2.id.decode_failed;
        message.arg1 = R2.drawable.x_dialog_bottom;
        message.arg2 = i2;
        this.a.f(message);
    }

    public final boolean r(double d) {
        double longValue = Double.valueOf(d).longValue();
        Double.isNaN(longValue);
        return Math.abs(longValue - d) < Double.MIN_VALUE;
    }

    public final boolean s(Location location, Location location2) {
        if (location == null) {
            return true;
        }
        return location2 != null && this.f519m.get() < 3 && location.getLongitude() == location2.getLongitude() && location.getLatitude() == location2.getLatitude() && location.getAccuracy() == location2.getAccuracy() && location.getSpeed() == 0.0f && location2.getSpeed() == 0.0f && location.getBearing() == 0.0f && location2.getBearing() == 0.0f && location.getAltitude() == 0.0d && location2.getAltitude() == 0.0d;
    }

    public final boolean t(a aVar, a aVar2) {
        if (aVar.b() == 0.0d || aVar.c() == 0.0d) {
            return false;
        }
        return Math.abs(aVar.b() - aVar2.b()) >= 1.0E-7d || Math.abs(aVar.c() - aVar2.c()) >= 1.0E-7d;
    }

    public final int w() {
        return this.b.get();
    }
}
