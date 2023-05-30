package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.SystemClock;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.v;
import com.tencent.tencentmap.mapsdk.maps.MapParamConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class d0 implements be, c9, g8, k5, ne {
    private static final int O = 20;
    public static final int P = 12;
    public static final int Q = 11;
    public static final int R = 10;
    public static final int S = 18;
    public static final int T = 13;
    public static final int U = 15;
    public static final int V = 15;
    public static final int W = 17;
    public static final int X = 18;
    public static final int Y = 16;
    public static final int Z = 18;
    public static final int a0 = 12;
    private static int b0 = 116307503;
    private static int c0 = 39984186;
    private static final int d0 = 0;
    private static final int e0 = 1;
    private static final int f0 = 2;
    private static final int g0 = 3;
    private static final int h0 = 4;
    private Rect A;
    private Rect B;
    private Runnable K;
    private boolean N;

    /* renamed from: g */
    public z f16378g;

    /* renamed from: h */
    public uf f16379h;

    /* renamed from: i */
    private List<d5> f16380i;

    /* renamed from: j */
    private List<y4> f16381j;

    /* renamed from: k */
    private List<se> f16382k;

    /* renamed from: l */
    private List<a5> f16383l;

    /* renamed from: n */
    private List<g5> f16385n;
    private List<f5> o;
    private List<k5> p;
    private List<b1> q;
    private final List<l5> s;
    private List<e5> t;
    private List<i5> u;
    private a1 w;
    private e1 x;
    private v y;
    private s4 z;

    /* renamed from: m */
    private final byte[] f16384m = new byte[0];
    private final byte[] r = new byte[0];
    private Stack<v> v = new Stack<>();
    private h C = null;
    private float D = 0.5f;
    private float E = 0.5f;
    private boolean F = false;
    private int G = 0;
    private int H = 0;
    private int I = 0;
    private int J = 0;
    private boolean L = false;
    private boolean M = false;

    /* loaded from: classes9.dex */
    public class a implements se {
        public final /* synthetic */ boolean a;

        public a(boolean z) {
            d0.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.se
        public void a() {
            PointF o = d0.this.o();
            d0.this.a(o.x, o.y, this.a);
            d0.this.b(this);
        }
    }

    /* loaded from: classes9.dex */
    public class b extends z8 {
        public final /* synthetic */ long B;
        public final /* synthetic */ float C;
        public final /* synthetic */ float D;
        public final /* synthetic */ double E;
        public final /* synthetic */ double F;
        public final /* synthetic */ double G;
        public final /* synthetic */ double H;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(int i2, double[] dArr, boolean z, long j2, float f2, float f3, double d, double d2, double d3, double d4) {
            super(i2, dArr, z);
            d0.this = r5;
            this.B = j2;
            this.C = f2;
            this.D = f3;
            this.E = d;
            this.F = d2;
            this.G = d3;
            this.H = d4;
        }

        @Override // com.tencent.mapsdk.internal.z8
        public boolean d() {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.B;
            double t = w8.t(elapsedRealtime, 0.0f, this.C, Final.FIVE_SECOND);
            double t2 = w8.t(elapsedRealtime, 0.0f, this.D, Final.FIVE_SECOND);
            double[] dArr = this.b;
            dArr[2] = dArr[2] + Math.abs(t);
            double[] dArr2 = this.b;
            dArr2[3] = dArr2[3] + Math.abs(t2);
            boolean z = this.b[2] >= Math.abs(this.E);
            boolean z2 = this.b[3] >= Math.abs(this.F);
            if (z) {
                double[] dArr3 = this.b;
                double d = this.G;
                double p = d0.this.p();
                Double.isNaN(p);
                dArr3[0] = d - p;
            } else {
                this.b[0] = t;
            }
            if (z2) {
                double[] dArr4 = this.b;
                double d2 = this.H;
                double v = d0.this.v();
                Double.isNaN(v);
                dArr4[1] = d2 - v;
            } else {
                this.b[1] = t2;
            }
            return z && z2;
        }
    }

    /* loaded from: classes9.dex */
    public class c extends z8 {
        public final /* synthetic */ long B;
        public final /* synthetic */ float C;
        public final /* synthetic */ float D;
        public final /* synthetic */ double E;
        public final /* synthetic */ double F;
        public final /* synthetic */ double G;
        public final /* synthetic */ double H;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(int i2, double[] dArr, boolean z, long j2, float f2, float f3, double d, double d2, double d3, double d4) {
            super(i2, dArr, z);
            d0.this = r5;
            this.B = j2;
            this.C = f2;
            this.D = f3;
            this.E = d;
            this.F = d2;
            this.G = d3;
            this.H = d4;
        }

        @Override // com.tencent.mapsdk.internal.z8
        public boolean d() {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.B;
            double t = w8.t(elapsedRealtime, 0.0f, this.C, Final.FIVE_SECOND);
            double t2 = w8.t(elapsedRealtime, 0.0f, this.D, Final.FIVE_SECOND);
            double[] dArr = this.b;
            dArr[2] = dArr[2] + Math.abs(t);
            double[] dArr2 = this.b;
            dArr2[3] = dArr2[3] + Math.abs(t2);
            boolean z = this.b[2] >= Math.abs(this.E);
            boolean z2 = this.b[3] >= Math.abs(this.F);
            if (z) {
                double[] dArr3 = this.b;
                double d = this.G;
                double p = d0.this.p();
                Double.isNaN(p);
                dArr3[0] = d - p;
            } else {
                this.b[0] = t;
            }
            if (z2) {
                double[] dArr4 = this.b;
                double d2 = this.H;
                double v = d0.this.v();
                Double.isNaN(v);
                dArr4[1] = d2 - v;
            } else {
                this.b[1] = t2;
            }
            return z && z2;
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public d() {
            d0.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.a(0.0d, 0.0d, false);
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Runnable {
        public final /* synthetic */ double a;

        public e(double d) {
            d0.this = r1;
            this.a = d;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.a((360.0d - this.a) % 360.0d, MapParamConstants.MAX_SKEW_ANGLE, false);
        }
    }

    /* loaded from: classes9.dex */
    public class f implements Runnable {
        public final /* synthetic */ i8 a;

        public f(i8 i8Var) {
            d0.this = r1;
            this.a = i8Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            i8 i8Var = this.a;
            if (i8Var != null) {
                i8Var.onFinish();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g implements Runnable {
        public final /* synthetic */ GeoPoint a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ Runnable f16386c;

        public g(GeoPoint geoPoint, float f2, Runnable runnable) {
            d0.this = r1;
            this.a = geoPoint;
            this.b = f2;
            this.f16386c = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.b(this.a, this.b, this.f16386c);
        }
    }

    /* loaded from: classes9.dex */
    public interface h {
        void a(z8 z8Var);
    }

    public d0(e1 e1Var) {
        this.x = e1Var;
        this.w = (a1) e1Var.j();
        this.y = e1Var.b();
        this.z = e1Var.getProjection();
        this.A = e1Var.e();
        z zVar = new z(this);
        this.f16378g = zVar;
        zVar.a(this);
        a(this.f16378g);
        this.f16385n = new CopyOnWriteArrayList();
        this.o = new CopyOnWriteArrayList();
        this.f16380i = new CopyOnWriteArrayList();
        this.f16381j = new CopyOnWriteArrayList();
        this.f16382k = new CopyOnWriteArrayList();
        this.s = new CopyOnWriteArrayList();
        this.t = new CopyOnWriteArrayList();
        this.u = new CopyOnWriteArrayList();
        this.p = new CopyOnWriteArrayList();
        a(k4.f16759e);
    }

    private void A() {
        this.y.f(0.0f);
        this.y.i(0.0f);
        h(0.0d);
        i(0.0d);
        D();
    }

    private void D() {
        List<b1> list = this.q;
        if (list == null) {
            return;
        }
        for (b1 b1Var : list) {
            if (b1Var != null) {
                try {
                    b1Var.a(this.y);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        a();
    }

    private void F() {
        for (d5 d5Var : this.f16380i) {
            if (d5Var != null) {
                try {
                    d5Var.a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        D();
    }

    private double a(double d2) {
        double d3 = d2 % 360.0d;
        return d3 > 180.0d ? d3 - 360.0d : d3 < -180.0d ? d3 + 360.0d : d3;
    }

    public void a(double d2, double d3, boolean z) {
        double p = p();
        Double.isNaN(p);
        double a2 = a(d2 - p);
        double v = v();
        Double.isNaN(v);
        double d4 = d3 - v;
        if (a2 == 0.0d && d4 == 0.0d) {
            return;
        }
        ma.c("postRotateAndSkew distance:" + a2 + DYConstants.DY_REGEX_COMMA + d4);
        b(new b(102, new double[]{d2, d3, 0.0d, 0.0d}, z ^ true, SystemClock.elapsedRealtime(), (float) (a2 * 0.10000000149011612d), (float) (0.10000000149011612d * d4), a2, d4, d2, d3));
    }

    private void a(double d2, double d3, boolean z, Runnable runnable) {
        double p = p();
        Double.isNaN(p);
        double a2 = a(d2 - p);
        double v = v();
        Double.isNaN(v);
        double d4 = d3 - v;
        if (a2 == 0.0d && d4 == 0.0d) {
            if (runnable != null) {
                z8 z8Var = new z8(runnable);
                z8Var.f17566e = true;
                z8Var.f17565c = 0L;
                b(z8Var);
                return;
            }
            return;
        }
        ma.c("postRotateAndSkew distance:" + a2 + DYConstants.DY_REGEX_COMMA + d4);
        b(new c(102, new double[]{d2, d3, 0.0d, 0.0d}, z ^ true, SystemClock.elapsedRealtime(), (float) (a2 * 0.10000000149011612d), (float) (0.10000000149011612d * d4), a2, d4, d2, d3));
        if (runnable != null) {
            z8 z8Var2 = new z8(runnable);
            z8Var2.f17566e = true;
            z8Var2.f17565c = 0L;
            b(z8Var2);
        }
    }

    private void a(GeoPoint geoPoint, float f2, int i2, Runnable runnable, i8 i8Var) {
        if (geoPoint == null) {
            return;
        }
        if (f2 >= 3.0f && f2 <= 20.0f) {
            this.x.f().a(geoPoint, (int) f2, true);
        }
        this.K = runnable;
    }

    private void a(GeoPoint geoPoint, boolean z, Runnable runnable) {
        if (geoPoint == null) {
            return;
        }
        this.f16378g.b();
        int i2 = z ? 20 : 40;
        GeoPoint a2 = this.y.a();
        double[] c2 = h8.c(a2.getLatitudeE6(), geoPoint.getLatitudeE6() - a2.getLatitudeE6(), i2);
        double[] c3 = h8.c(a2.getLongitudeE6(), geoPoint.getLongitudeE6() - a2.getLongitudeE6(), i2);
        for (int i3 = 0; i3 < i2; i3++) {
            b(new z8(4, new double[]{c2[i3], c3[i3]}));
        }
        if (runnable != null) {
            b(new z8(runnable));
        }
    }

    private void b(int i2) {
        for (y4 y4Var : this.f16381j) {
            if (y4Var != null) {
                try {
                    y4Var.a(i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        F();
    }

    public void b(GeoPoint geoPoint, float f2, Runnable runnable) {
        int i2;
        boolean z;
        if (geoPoint == null) {
            return;
        }
        this.f16378g.b();
        double q = q();
        double d2 = f2;
        Double.isNaN(d2);
        Double.isNaN(q);
        double d3 = d2 / q;
        int i3 = (d3 > 1.0d ? 1 : (d3 == 1.0d ? 0 : -1));
        if (i3 > 0) {
            i2 = (int) (d3 / 0.5d);
            z = true;
        } else if (d3 >= 1.0d) {
            a(geoPoint, true, runnable);
            return;
        } else {
            i2 = i3 != 0 ? (int) (0.5d / d3) : 0;
            z = false;
        }
        int max = Math.max(60, Math.min(120, (i2 >> 1) << 1));
        double log10 = Math.log10(q) / Math.log10(2.0d);
        double log102 = Math.log10(d2) / Math.log10(2.0d);
        GeoPoint a2 = this.y.a();
        if (z) {
            int i4 = 0;
            while (i4 < max) {
                long j2 = max;
                i4++;
                long j3 = i4;
                double d4 = log102;
                double pow = Math.pow(2.0d, ac.e(log10, log102, j2, j3));
                double d5 = log10;
                double f3 = ac.f(a2.getLatitudeE6(), geoPoint.getLatitudeE6(), j2, j3);
                double f4 = ac.f(a2.getLongitudeE6(), geoPoint.getLongitudeE6(), j2, j3);
                ma.c("debug location anim zoomOut:" + f3 + DYConstants.DY_REGEX_COMMA + f4);
                b(new z8(120, new double[]{pow, f3, f4}));
                log102 = d4;
                log10 = d5;
                a2 = a2;
            }
        } else {
            int i5 = 0;
            while (i5 < max) {
                long j4 = max;
                int i6 = i5 + 1;
                long j5 = i6;
                double pow2 = Math.pow(2.0d, ac.b(log10, log102, j4, j5));
                double c2 = ac.c(a2.getLatitudeE6(), geoPoint.getLatitudeE6(), j4, j5);
                double c3 = ac.c(a2.getLongitudeE6(), geoPoint.getLongitudeE6(), j4, j5);
                ma.c("debug location anim zoomin:" + c2 + DYConstants.DY_REGEX_COMMA + c3);
                b(new z8(120, new double[]{pow2, c2, c3}));
                i5 = i6;
            }
        }
        if (runnable != null) {
            b(new z8(runnable));
        }
    }

    private void c(double d2, double d3) {
        double p = p();
        Double.isNaN(p);
        double a2 = a(d2 - p);
        double v = v();
        Double.isNaN(v);
        double d4 = d3 - v;
        if (a2 == 0.0d && d4 == 0.0d) {
            return;
        }
        ma.c("rotateAndSkew distance:" + a2 + DYConstants.DY_REGEX_COMMA + d4);
        b(new z8(102, new double[]{a2, d4, 0.0d, 0.0d}, true));
    }

    private void c(int i2) {
        for (l5 l5Var : this.s) {
            if (l5Var != null) {
                try {
                    l5Var.a(i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        D();
    }

    private void c(z8 z8Var) {
        double d2;
        double d3;
        int i2 = z8Var.a;
        if (i2 == 2) {
            a1 a1Var = this.w;
            if (a1Var == null || a1Var.getMapRenderView() == null) {
                return;
            }
            this.w.getMapRenderView().j();
            return;
        }
        if (i2 == 3) {
            double[] dArr = z8Var.b;
            d2 = dArr[0];
            d3 = dArr[1];
        } else if (i2 == 4) {
            double[] dArr2 = z8Var.b;
            a((int) dArr2[0], (int) dArr2[1], dArr2.length > 2 ? (int) dArr2[2] : 1);
            return;
        } else if (i2 == 6) {
            Runnable runnable = z8Var.f17567f;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        } else if (i2 == 120) {
            e(z8Var.b[0]);
            double[] dArr3 = z8Var.b;
            b((int) dArr3[1], (int) dArr3[2]);
            return;
        } else if (i2 == 10000) {
            h hVar = this.C;
            if (hVar != null) {
                hVar.a(z8Var);
                return;
            }
            return;
        } else {
            switch (i2) {
                case 100:
                    d(z8Var.b[0]);
                    return;
                case 101:
                    int width = this.x.e().width();
                    int height = this.x.e().height();
                    int i3 = width / 2;
                    int i4 = height / 2;
                    v.c v = l().v();
                    if (v != null) {
                        i3 = (int) (i3 + (v.a() * width));
                        i4 = (int) (i4 + (v.b() * height));
                    }
                    double d4 = i3;
                    double[] dArr4 = z8Var.b;
                    double d5 = dArr4[1];
                    Double.isNaN(d4);
                    double d6 = i4;
                    double d7 = dArr4[2];
                    Double.isNaN(d6);
                    double d8 = dArr4[3];
                    Double.isNaN(d4);
                    double d9 = d8 - d4;
                    double d10 = dArr4[4];
                    Double.isNaN(d6);
                    a(d4 - d5, d6 - d7);
                    d(z8Var.b[0]);
                    a(d9, d10 - d6);
                    return;
                case 102:
                    b(z8Var.b[0]);
                    f(z8Var.b[1]);
                    return;
                case 103:
                    double width2 = this.x.e().width() / 2;
                    double[] dArr5 = z8Var.b;
                    double d11 = dArr5[1];
                    Double.isNaN(width2);
                    double height2 = this.x.e().height() / 2;
                    double d12 = dArr5[2];
                    Double.isNaN(height2);
                    a(width2 - d11, height2 - d12);
                    b(z8Var.b[0]);
                    double[] dArr6 = z8Var.b;
                    double d13 = dArr6[3];
                    Double.isNaN(width2);
                    d2 = d13 - width2;
                    double d14 = dArr6[4];
                    Double.isNaN(height2);
                    d3 = d14 - height2;
                    break;
                case 104:
                    A();
                    return;
                default:
                    switch (i2) {
                        case 108:
                            e(z8Var.b[0]);
                            return;
                        case 109:
                            c(z8Var.b[0]);
                            return;
                        case 110:
                            g(z8Var.b[0]);
                            return;
                        default:
                            return;
                    }
            }
        }
        a(d2, d3);
    }

    private void c(boolean z) {
        if (this.f16383l == null) {
            return;
        }
        boolean B = B();
        for (a5 a5Var : this.f16383l) {
            if (a5Var != null) {
                try {
                    a5Var.a(B, z);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean c() {
        return r() < this.y.g();
    }

    private boolean d() {
        return r() > this.y.j();
    }

    public PointF o() {
        int i2;
        Rect rect = this.A;
        int i3 = 0;
        if (rect != null) {
            i3 = rect.width();
            i2 = this.A.height();
        } else {
            i2 = 0;
        }
        int i4 = this.G;
        int i5 = i4 + (((i3 - i4) - this.I) / 2);
        int i6 = this.H;
        return (i3 == 0 || i2 == 0) ? new PointF(0.5f, 0.5f) : new PointF((i5 * 1.0f) / i3, ((i6 + (((i2 - i6) - this.J) / 2)) * 1.0f) / i2);
    }

    private boolean y() {
        Rect rect = this.A;
        return rect != null && rect.width() > 0 && this.A.height() > 0;
    }

    public boolean B() {
        return ((double) Math.abs(v())) > 1.0E-6d || Math.abs(p()) > 1.0f;
    }

    public boolean C() {
        return this.N;
    }

    public void E() {
        for (k5 k5Var : this.p) {
            if (k5Var != null) {
                try {
                    k5Var.b();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void G() {
        for (f5 f5Var : this.o) {
            if (f5Var != null) {
                try {
                    f5Var.a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void H() {
        b(this.B);
        for (se seVar : this.f16382k) {
            if (seVar != null) {
                try {
                    seVar.a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void I() {
        z zVar = this.f16378g;
        if (zVar != null) {
            zVar.h();
        }
    }

    public boolean J() {
        return this.f16378g.i();
    }

    public void K() {
        c(false);
        M();
    }

    public void L() {
        d(false);
    }

    public void M() {
        this.f16378g.b();
        a(0.0d, 0.0d, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0039 A[Catch: Exception -> 0x0060, TryCatch #0 {Exception -> 0x0060, blocks: (B:48:0x0000, B:50:0x0014, B:51:0x0016, B:55:0x002b, B:57:0x0039, B:58:0x0041, B:60:0x004f, B:61:0x0057, B:52:0x001a, B:54:0x0028), top: B:66:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x004f A[Catch: Exception -> 0x0060, TryCatch #0 {Exception -> 0x0060, blocks: (B:48:0x0000, B:50:0x0014, B:51:0x0016, B:55:0x002b, B:57:0x0039, B:58:0x0041, B:60:0x004f, B:61:0x0057, B:52:0x001a, B:54:0x0028), top: B:66:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void N() {
        y5 y5Var;
        try {
            v pop = this.v.pop();
            if (pop.q() == this.y.q()) {
                if (pop.p() != this.y.p()) {
                    y5Var = y5.SCALE_CHANGED;
                }
                if (pop.m() != this.y.m()) {
                    h(pop.m());
                }
                if (pop.s() != this.y.s()) {
                    i(pop.s());
                }
                this.y.a(pop);
                D();
            }
            y5Var = y5.SCALE_LEVEL_CHANGED;
            a(y5Var);
            if (pop.m() != this.y.m()) {
            }
            if (pop.s() != this.y.s()) {
            }
            this.y.a(pop);
            D();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void O() {
        z zVar = this.f16378g;
        if (zVar != null) {
            zVar.k();
        }
    }

    public void P() {
        try {
            v vVar = (v) this.y.clone();
            ma.c("mapParam stack saveMapParam:" + vVar.toString());
            this.v.push(vVar);
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
    }

    public void Q() {
        c(false);
        this.f16378g.b();
        c(0.0d, 0.0d);
    }

    public void R() {
        c(false);
        this.f16378g.b();
        c(p(), MapParamConstants.MAX_SKEW_ANGLE);
    }

    public z S() {
        this.f16378g.l();
        return this.f16378g;
    }

    public void T() {
        z zVar = this.f16378g;
        if (zVar != null) {
            zVar.c();
        }
    }

    public float a(Rect rect, Rect rect2) {
        GeoPoint geoPoint = new GeoPoint(rect.top, rect.left);
        GeoPoint geoPoint2 = new GeoPoint(rect.bottom, rect.right);
        Rect rect3 = new Rect();
        rect3.left = Math.min(geoPoint.getLongitudeE6(), geoPoint2.getLongitudeE6());
        rect3.right = Math.max(geoPoint.getLongitudeE6(), geoPoint2.getLongitudeE6());
        rect3.top = Math.min(geoPoint.getLatitudeE6(), geoPoint2.getLatitudeE6());
        rect3.bottom = Math.max(geoPoint.getLatitudeE6(), geoPoint2.getLatitudeE6());
        return (float) this.x.f().a(rect3, rect2);
    }

    public float a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (y()) {
            int latitudeE6 = geoPoint.getLatitudeE6();
            int longitudeE6 = geoPoint.getLongitudeE6();
            int latitudeE62 = geoPoint2.getLatitudeE6();
            int longitudeE62 = geoPoint2.getLongitudeE6();
            return a(new Rect(Math.min(longitudeE62, longitudeE6), Math.min(latitudeE62, latitudeE6), Math.max(longitudeE62, longitudeE6), Math.max(latitudeE62, latitudeE6)), this.A);
        }
        return q();
    }

    public int a(int i2, int i3, int i4, int i5, boolean z) {
        this.G = i2;
        this.H = i3;
        this.I = i4;
        this.J = i5;
        Rect rect = this.A;
        if (rect == null || rect.width() <= 0 || this.A.height() <= 0) {
            a(new a(z));
            e1 e1Var = this.x;
            if (e1Var instanceof qc) {
                Context context = ((qc) e1Var).getContext();
                return (i2 + i4 > f7.j(context) || i3 + i5 > f7.i(context)) ? -1 : 0;
            }
            return -2;
        } else if (i2 + i4 > this.A.width() || i3 + i5 > this.A.height()) {
            return -1;
        } else {
            PointF o = o();
            a(o.x, o.y, z);
            return 0;
        }
    }

    public j8 a(int i2, Object obj, Object obj2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        double doubleValue = ((Number) obj2).doubleValue() - ((Number) obj).doubleValue();
                        double p = p();
                        Double.isNaN(p);
                        return t8.a(this, 2, p(), doubleValue + p);
                    } else if (i2 != 4) {
                        return null;
                    } else {
                        double doubleValue2 = ((Number) obj2).doubleValue() - ((Number) obj).doubleValue();
                        double v = v();
                        Double.isNaN(v);
                        return t8.a(this, 4, v(), doubleValue2 + v);
                    }
                }
                return t8.a(this, 3, Math.log10(((Number) obj).doubleValue()) / Math.log10(2.0d), Math.log10(((Number) obj2).doubleValue()) / Math.log10(2.0d));
            }
            return t8.a(this, 1, new x7(), new Object[]{obj, obj2});
        }
        return t8.a((g8) this, 0, 0, 1);
    }

    public o5 a(GeoPoint geoPoint, o5 o5Var) {
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double min = Math.min(Math.max(Math.sin((latitudeE6 / 1000000.0d) * 0.017453292519943295d), -0.9999d), 0.9999d);
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        double d2 = ((longitudeE6 / 1000000.0d) * 745654.0444444445d) + 1.34217728E8d;
        double log = (Math.log((min + 1.0d) / (1.0d - min)) * 4.272282972352698E7d * 0.5d) + 1.34217728E8d;
        if (o5Var == null) {
            o5Var = new o5();
        }
        o5Var.e(d2, log);
        return o5Var;
    }

    @Override // com.tencent.mapsdk.internal.be
    public void a() {
        this.f16378g.a();
    }

    public void a(double d2, double d3) {
        this.x.f().a((float) d2, (float) d3, false);
        b(1);
        D();
    }

    public void a(double d2, double d3, double d4, double d5, double d6) {
        this.f16378g.b();
        float width = this.x.e().width() / 2.0f;
        float height = this.x.e().height() / 2.0f;
        v.c v = l().v();
        if (this.M) {
            if (v != null) {
                d3 = width + (v.a() * width * 2.0f);
                height += v.b() * height * 2.0f;
            } else {
                d3 = width;
            }
            d4 = height;
            d5 = d3;
            d6 = d4;
        }
        a(new z8(103, new double[]{d2, d3, d4, d5, d6}));
    }

    public void a(double d2, double d3, double d4, double d5, double d6, Runnable runnable) {
        double d7;
        double d8;
        double d9;
        double d10;
        this.f16378g.b();
        float width = this.x.e().width() / 2.0f;
        float height = this.x.e().height() / 2.0f;
        v.c v = l().v();
        if (this.L) {
            if (v != null) {
                d7 = width + (v.a() * width * 2.0f);
                d8 = height + (v.b() * height * 2.0f);
            } else {
                d7 = width;
                d8 = height;
            }
            d10 = d8;
            d9 = d7;
        } else {
            d7 = d3;
            d8 = d4;
            d9 = d5;
            d10 = d6;
        }
        b(new z8(101, new double[]{d2, d7, d8, d9, d10}));
        z8 z8Var = new z8(runnable);
        z8Var.f17566e = false;
        z8Var.f17565c = 0L;
        b(z8Var);
    }

    public void a(float f2) {
        c(f2);
    }

    public void a(float f2, float f3) {
        qi f4;
        e1 e1Var = this.x;
        if (e1Var == null || (f4 = e1Var.f()) == null) {
            return;
        }
        f4.a(f2, f3, false);
        D();
    }

    public void a(float f2, float f3, int i2, boolean z) {
        if (this.y.a(f2 - 0.5f, f3 - 0.5f, z)) {
            D();
        }
    }

    public void a(float f2, float f3, Runnable runnable) {
        if (this.L) {
            v.c v = l().v();
            if (v == null) {
                a(runnable);
                return;
            }
            float width = this.x.e().width() * (v.a() + 0.5f);
            f3 = (v.b() + 0.5f) * this.x.e().height();
            f2 = width;
        }
        a(f2, f3, runnable, (i8) null);
    }

    public void a(float f2, float f3, Runnable runnable, i8 i8Var) {
        if (c()) {
            Rect r = this.y.r();
            int height = r.height();
            float A = this.x.f().A();
            if (f3 >= r.top) {
                float f4 = (r7 + height) - A;
                if (f3 < f4) {
                    f3 = f4;
                }
            }
            this.x.f().c(f2, f3);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public void a(float f2, float f3, boolean z) {
        this.y.a(f2 - 0.5f, f3 - 0.5f, z);
        if (z) {
            D();
        }
    }

    public void a(int i2) {
        a(i2, (Runnable) new d(), false);
    }

    public void a(int i2, double d2, boolean z) {
        if (z) {
            a(i2, (Runnable) new e(d2), false);
            return;
        }
        e(i2);
        a(((float) (360.0d - d2)) % 360.0f);
        c(MapParamConstants.MAX_SKEW_ANGLE);
    }

    public void a(int i2, int i3) {
        a(i2, i3);
    }

    public void a(int i2, int i3, int i4) {
        this.y.a(i2, i3, false);
        if (i4 == 1) {
            b(i4);
            D();
        }
    }

    public void a(int i2, GeoPoint geoPoint) {
        b(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
        e(i2);
    }

    @Override // com.tencent.mapsdk.internal.g8
    public void a(int i2, Object obj) {
        if (i2 == 1) {
            GeoPoint geoPoint = (GeoPoint) obj;
            a(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6(), 2);
        } else if (i2 == 2) {
            double doubleValue = ((Number) obj).doubleValue();
            double p = p();
            Double.isNaN(p);
            b(doubleValue - p);
        } else if (i2 == 3) {
            e(Math.pow(2.0d, ((Number) obj).doubleValue()));
        } else if (i2 != 4) {
        } else {
            double doubleValue2 = ((Number) obj).doubleValue();
            double v = v();
            Double.isNaN(v);
            f(doubleValue2 - v);
        }
    }

    public void a(int i2, Runnable runnable, boolean z) {
        this.x.f().c(i2, true);
        if (runnable != null) {
            runnable.run();
        }
    }

    public void a(Rect rect) {
        if (y()) {
            float a2 = a(rect, this.A);
            this.y.a(rect);
            this.y.e(a2);
        }
    }

    public void a(Rect rect, Rect rect2, Runnable runnable) {
        a(rect, rect2, runnable, (i8) null);
    }

    public void a(Rect rect, Rect rect2, Runnable runnable, i8 i8Var) {
        if (y()) {
            Rect rect3 = new Rect(this.A);
            if (rect2 != null) {
                rect3.left += rect2.left;
                rect3.right -= rect2.right;
                rect3.top += rect2.top;
                rect3.bottom -= rect2.bottom;
            }
            a(rect, rect2, true);
            b(1);
        }
    }

    public void a(Rect rect, Rect rect2, boolean z) {
        if (y()) {
            Rect rect3 = new Rect(this.A);
            if (rect2 != null) {
                rect3.left += rect2.left;
                rect3.right -= rect2.right;
                rect3.top += rect2.top;
                rect3.bottom -= rect2.bottom;
            }
            GeoPoint geoPoint = new GeoPoint(rect.top, rect.left);
            GeoPoint geoPoint2 = new GeoPoint(rect.bottom, rect.right);
            Rect rect4 = new Rect();
            rect4.left = Math.min(geoPoint.getLongitudeE6(), geoPoint2.getLongitudeE6());
            rect4.right = Math.max(geoPoint.getLongitudeE6(), geoPoint2.getLongitudeE6());
            rect4.top = Math.min(geoPoint.getLatitudeE6(), geoPoint2.getLatitudeE6());
            rect4.bottom = Math.max(geoPoint.getLatitudeE6(), geoPoint2.getLatitudeE6());
            this.x.f().a(rect4, rect3, z);
            a();
        }
    }

    public void a(Rect rect, Runnable runnable) {
        a(rect, (Rect) null, runnable);
    }

    public void a(GeoPoint geoPoint) {
        a(geoPoint, (Runnable) null);
    }

    public void a(GeoPoint geoPoint, float f2, float f3, float f4) {
        b(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
        b(f2);
        a(f3);
        c(f4);
    }

    public void a(GeoPoint geoPoint, float f2, Runnable runnable) {
        int i2;
        Runnable runnable2 = runnable;
        if (geoPoint == null) {
            return;
        }
        this.f16378g.b();
        double q = q();
        double d2 = f2;
        Double.isNaN(d2);
        Double.isNaN(q);
        double d3 = d2 / q;
        int i3 = (d3 > 1.0d ? 1 : (d3 == 1.0d ? 0 : -1));
        if (i3 > 0) {
            i2 = (int) (d3 / 0.5d);
        } else if (d3 >= 1.0d) {
            a(geoPoint, true, runnable2);
            return;
        } else {
            i2 = i3 != 0 ? (int) (0.5d / d3) : 0;
        }
        int max = Math.max(60, Math.min(120, (i2 >> 1) << 1));
        double log10 = Math.log10(q) / Math.log10(2.0d);
        double log102 = Math.log10(d2) / Math.log10(2.0d);
        GeoPoint a2 = this.y.a();
        int i4 = 0;
        while (i4 < max) {
            long j2 = max;
            i4++;
            long j3 = i4;
            double d4 = log10;
            double d5 = log102;
            double pow = Math.pow(2.0d, ac.a(log10, log102, j2, j3));
            double a3 = ac.a(a2.getLatitudeE6(), geoPoint.getLatitudeE6(), j2, j3);
            double a4 = ac.a(a2.getLongitudeE6(), geoPoint.getLongitudeE6(), j2, j3);
            ma.c("debug location anim zoomOut:" + a3 + DYConstants.DY_REGEX_COMMA + a4);
            b(new z8(120, new double[]{pow, a3, a4}));
            runnable2 = runnable;
            log10 = d4;
            log102 = d5;
            a2 = a2;
        }
        if (runnable2 != null) {
            b(new z8(runnable2));
        }
    }

    public void a(GeoPoint geoPoint, int i2, Runnable runnable) {
        a(geoPoint, i2, 2, runnable, (i8) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x003c, code lost:
        if (r1 > r11) goto L57;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(GeoPoint geoPoint, Rect rect) {
        double d2;
        double d3;
        int i2;
        s4 s4Var = this.z;
        o5 a2 = s4Var.a(geoPoint);
        if (rect.contains((int) a2.a, (int) a2.b)) {
            return;
        }
        double d4 = a2.a;
        int i3 = rect.left;
        double d5 = 0.0d;
        if (d4 >= i3) {
            i3 = rect.right;
            if (d4 <= i3) {
                d2 = 0.0d;
                d3 = a2.b;
                i2 = rect.top;
                if (d3 >= i2) {
                    i2 = rect.bottom;
                }
                double d6 = i2;
                Double.isNaN(d6);
                d5 = d6 - d3;
                o5 a3 = s4Var.a(h());
                a3.a -= d2;
                a3.b -= d5;
                a(s4Var.a(a3));
            }
        }
        double d7 = i3;
        Double.isNaN(d7);
        d2 = d7 - d4;
        d3 = a2.b;
        i2 = rect.top;
        if (d3 >= i2) {
        }
        double d62 = i2;
        Double.isNaN(d62);
        d5 = d62 - d3;
        o5 a32 = s4Var.a(h());
        a32.a -= d2;
        a32.b -= d5;
        a(s4Var.a(a32));
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x005d, code lost:
        if (r4 <= r11.A.height()) goto L57;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(GeoPoint geoPoint, i8 i8Var) {
        Rect n2 = n();
        boolean z = false;
        boolean contains = n2 != null ? n2.contains(geoPoint.getLongitudeE6(), geoPoint.getLatitudeE6()) : false;
        f fVar = new f(i8Var);
        float q = q();
        if (!contains) {
            GeoPoint a2 = this.y.a();
            o5 a3 = this.z.a(a2);
            o5 a4 = this.z.a(geoPoint);
            if (this.A != null) {
                double abs = Math.abs(a3.a - a4.a);
                double abs2 = Math.abs(a3.b - a4.b);
                if (abs <= this.A.width()) {
                }
                if (!z) {
                    b(new GeoPoint((geoPoint.getLatitudeE6() + a2.getLatitudeE6()) / 2, (geoPoint.getLongitudeE6() + a2.getLongitudeE6()) / 2), c(a2, geoPoint), new g(geoPoint, q, fVar));
                    return;
                }
            }
            z = true;
            if (!z) {
            }
        }
        a(geoPoint, true, (Runnable) fVar);
    }

    public void a(GeoPoint geoPoint, Runnable runnable) {
        b(geoPoint, 1, runnable);
    }

    public void a(GeoPoint geoPoint, Runnable runnable, i8 i8Var) {
        if (geoPoint == null) {
            return;
        }
        a(geoPoint, r(), 1, runnable, i8Var);
    }

    public void a(a5 a5Var) {
        if (a5Var == null) {
            return;
        }
        if (this.f16383l == null) {
            this.f16383l = new ArrayList();
        }
        synchronized (this.f16384m) {
            if (!this.f16383l.contains(a5Var)) {
                this.f16383l.add(a5Var);
            }
        }
    }

    public void a(b1 b1Var) {
        if (b1Var == null) {
            return;
        }
        if (this.q == null) {
            this.q = new CopyOnWriteArrayList();
        }
        synchronized (this.r) {
            if (!this.q.contains(b1Var)) {
                this.q.add(b1Var);
            }
        }
    }

    public void a(h hVar) {
        this.C = hVar;
    }

    public void a(d5 d5Var) {
        if (d5Var == null) {
            return;
        }
        synchronized (this.f16380i) {
            if (!this.f16380i.contains(d5Var)) {
                this.f16380i.add(d5Var);
            }
        }
    }

    public void a(e5 e5Var) {
        if (e5Var == null) {
            return;
        }
        synchronized (this.t) {
            if (!this.t.contains(e5Var)) {
                this.t.add(e5Var);
            }
        }
    }

    public void a(f5 f5Var) {
        if (f5Var == null) {
            return;
        }
        synchronized (this.o) {
            if (!this.o.contains(f5Var)) {
                this.o.add(f5Var);
            }
        }
    }

    public void a(g5 g5Var) {
        if (g5Var == null) {
            return;
        }
        synchronized (this.f16385n) {
            if (!this.f16385n.contains(g5Var)) {
                this.f16385n.add(g5Var);
            }
        }
    }

    public void a(i5 i5Var) {
        if (i5Var == null) {
            return;
        }
        synchronized (this.u) {
            ma.c("skew addSkewListener");
            if (!this.u.contains(i5Var)) {
                this.u.add(i5Var);
            }
        }
    }

    public void a(k5 k5Var) {
        if (k5Var == null) {
            return;
        }
        synchronized (this.p) {
            if (!this.p.contains(k5Var)) {
                this.p.add(k5Var);
            }
        }
    }

    public void a(l5 l5Var) {
        if (l5Var == null) {
            return;
        }
        synchronized (this.s) {
            if (!this.s.contains(l5Var)) {
                this.s.add(l5Var);
            }
        }
    }

    public void a(se seVar) {
        if (seVar == null || this.f16382k.contains(seVar)) {
            return;
        }
        this.f16382k.add(seVar);
    }

    public void a(xf xfVar) {
        if (this.f16379h == null) {
            this.f16379h = this.w.d();
        }
        this.f16379h.a(xfVar);
    }

    public void a(y4 y4Var) {
        if (y4Var == null) {
            return;
        }
        synchronized (this.f16381j) {
            if (!this.f16381j.contains(y4Var)) {
                this.f16381j.add(y4Var);
            }
        }
    }

    public void a(y5 y5Var) {
        if (y5Var == y5.NO_CHANGED) {
            return;
        }
        for (g5 g5Var : this.f16385n) {
            if (g5Var != null) {
                g5Var.a(y5Var);
            }
        }
        D();
    }

    @Override // com.tencent.mapsdk.internal.c9
    public void a(z8 z8Var) {
        if (z8Var != null) {
            c(z8Var);
        }
    }

    public void a(Runnable runnable) {
        a(runnable, (i8) null);
    }

    public void a(Runnable runnable, i8 i8Var) {
        if (c()) {
            this.x.f().c(this.x.e().width() / 2.0f, this.x.e().height() / 2.0f);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.ne
    public void a(boolean z) {
        if (z) {
            Runnable runnable = this.K;
            if (runnable != null) {
                runnable.run();
                this.K = null;
            }
            D();
        }
    }

    public void a(boolean z, Runnable runnable) {
        this.f16378g.b();
        a(0.0d, 0.0d, z, runnable);
    }

    public double b(GeoPoint geoPoint, GeoPoint geoPoint2) {
        o5 a2 = this.z.a(geoPoint);
        o5 a3 = this.z.a(geoPoint2);
        double d2 = a3.a - a2.a;
        double d3 = a3.b - a2.b;
        return Math.sqrt((d2 * d2) + (d3 * d3));
    }

    public float b(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(this.x.e());
        if (rect2 != null) {
            rect3.left += rect2.left;
            rect3.right -= rect2.right;
            rect3.top += rect2.top;
            rect3.bottom -= rect2.bottom;
        }
        return a(rect, rect3);
    }

    @Override // com.tencent.mapsdk.internal.k5
    public void b() {
        E();
    }

    public void b(double d2) {
        if (d2 == 0.0d) {
            return;
        }
        v vVar = this.y;
        float f2 = vVar.f(vVar.m() + ((float) d2));
        D();
        h(f2);
    }

    public void b(double d2, double d3) {
        this.f16378g.a(d2, d3);
    }

    public void b(float f2) {
        a(this.y.g(f2));
    }

    public void b(float f2, float f3) {
        this.D = f2;
        this.E = f3;
    }

    public void b(int i2, int i3) {
        a(i2, i3, 1);
    }

    public void b(Rect rect) {
        if (this.A == null || rect == null) {
            return;
        }
        this.B = rect;
        e1 e1Var = this.x;
        if (e1Var instanceof qc) {
            ((qc) e1Var).b(rect);
        }
        D();
    }

    public void b(GeoPoint geoPoint, int i2, Runnable runnable) {
        if (geoPoint == null) {
            return;
        }
        a(geoPoint, r(), i2, runnable, (i8) null);
    }

    public void b(GeoPoint geoPoint, Rect rect) {
        s4 s4Var = this.z;
        o5 a2 = s4Var.a(geoPoint);
        double centerX = rect.centerX();
        double d2 = a2.a;
        Double.isNaN(centerX);
        double d3 = centerX - d2;
        double centerY = rect.centerY();
        double d4 = a2.b;
        Double.isNaN(centerY);
        double d5 = centerY - d4;
        o5 a3 = s4Var.a(h());
        a3.a -= d3;
        a3.b -= d5;
        a(s4Var.a(a3));
    }

    public void b(GeoPoint geoPoint, Runnable runnable) {
        c(geoPoint, 18, runnable);
    }

    public void b(a5 a5Var) {
        if (this.f16383l == null) {
            return;
        }
        synchronized (this.f16384m) {
            this.f16383l.remove(a5Var);
        }
    }

    public void b(b1 b1Var) {
        if (this.q == null) {
            return;
        }
        synchronized (this.r) {
            this.q.remove(b1Var);
        }
    }

    public void b(d5 d5Var) {
        synchronized (this.f16380i) {
            this.f16380i.remove(d5Var);
        }
    }

    public void b(e5 e5Var) {
        synchronized (this.t) {
            this.t.remove(e5Var);
        }
    }

    public void b(f5 f5Var) {
        synchronized (this.o) {
            this.o.remove(f5Var);
        }
    }

    public void b(g5 g5Var) {
        synchronized (this.f16385n) {
            this.f16385n.remove(g5Var);
        }
    }

    public void b(i5 i5Var) {
        synchronized (this.u) {
            ma.c("skew addSkewListener");
            this.u.remove(i5Var);
        }
    }

    public void b(k5 k5Var) {
        synchronized (this.p) {
            this.p.remove(k5Var);
        }
    }

    public void b(l5 l5Var) {
        synchronized (this.s) {
            this.s.remove(l5Var);
        }
    }

    public void b(se seVar) {
        synchronized (this.f16382k) {
            this.f16382k.remove(seVar);
        }
    }

    public void b(y4 y4Var) {
        synchronized (this.f16381j) {
            this.f16381j.remove(y4Var);
        }
    }

    public void b(z8 z8Var) {
        this.f16378g.a(z8Var);
    }

    public void b(Runnable runnable) {
        b(runnable, (i8) null);
    }

    public void b(Runnable runnable, i8 i8Var) {
        if (d()) {
            this.x.f().Y();
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public void b(boolean z) {
        this.N = z;
    }

    public boolean b(GeoPoint geoPoint) {
        boolean z;
        if (geoPoint == null) {
            return true;
        }
        Rect n2 = n();
        boolean contains = n2 != null ? n2.contains(geoPoint.getLongitudeE6(), geoPoint.getLatitudeE6()) : false;
        o5 a2 = this.z.a(this.y.a());
        o5 a3 = this.z.a(geoPoint);
        if (this.A != null) {
            double abs = Math.abs(a2.a - a3.a);
            double abs2 = Math.abs(a2.b - a3.b);
            if (abs > this.A.width() || abs2 > this.A.height()) {
                z = false;
                return contains && !z;
            }
        }
        z = true;
        if (contains) {
        }
    }

    public float c(GeoPoint geoPoint, GeoPoint geoPoint2) {
        int i2;
        Rect rect = this.A;
        int i3 = 0;
        if (rect != null) {
            i3 = rect.width();
            i2 = this.A.height();
        } else {
            i2 = 0;
        }
        if (i3 == 0 || i2 == 0 || geoPoint == null || geoPoint2 == null) {
            return 1.0f;
        }
        o5 a2 = a(geoPoint, (o5) null);
        o5 a3 = a(geoPoint2, (o5) null);
        double d2 = a3.a - a2.a;
        if (d2 < 0.0d) {
            d2 = Math.abs(d2);
        }
        double d3 = a3.b - a2.b;
        if (d3 < 0.0d) {
            d3 = Math.abs(d3);
        }
        double d4 = d2 * 1.0d;
        double d5 = d3 * 1.0d;
        int i4 = (i3 - this.G) - this.I;
        int i5 = (i2 - this.H) - this.J;
        if (i4 <= 0) {
            i4 = 1;
        }
        if (i5 <= 0) {
            i5 = 1;
        }
        double d6 = i4;
        Double.isNaN(d6);
        double d7 = i5;
        Double.isNaN(d7);
        double max = Math.max(Math.log(d4 / d6) / Math.log(2.0d), Math.log(d5 / d7) / Math.log(2.0d));
        double d8 = 20;
        Double.isNaN(d8);
        float f2 = (float) (d8 - max);
        v vVar = this.y;
        return vVar != null ? vVar.a((int) f2) : f2;
    }

    public void c(double d2) {
        double m2 = this.y.m();
        Double.isNaN(m2);
        if (wa.b(d2 - m2) == 0.0d) {
            return;
        }
        float f2 = this.y.f((float) d2);
        D();
        h(f2);
    }

    public void c(float f2) {
        g(f2);
    }

    public void c(Rect rect, Rect rect2) {
        if (rect == null) {
            return;
        }
        if (rect.height() > 0 || rect.width() > 0) {
            a(rect, rect2, false);
        }
        b(1);
    }

    public void c(GeoPoint geoPoint, int i2, Runnable runnable) {
        a(geoPoint, i2, runnable);
    }

    public void d(double d2) {
        e(this.y.p() * ((float) d2));
    }

    public void d(int i2) {
        if (this.y.c(i2)) {
            c(i2);
        }
    }

    public void d(z8 z8Var) {
        this.f16378g.b();
        b(z8Var);
    }

    public void d(boolean z) {
        c(z);
        a(p(), MapParamConstants.MAX_SKEW_ANGLE, true);
    }

    public void e() {
        this.f16378g.b();
    }

    public void e(double d2) {
        b((float) d2);
    }

    public void e(int i2) {
        if (this.y.f(i2)) {
            a(y5.SCALE_LEVEL_CHANGED);
        }
    }

    public void e(boolean z) {
        this.F = z;
        this.x.f().e(this.F);
    }

    public v f() {
        try {
            return (v) this.y.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void f(double d2) {
        if (d2 == 0.0d) {
            return;
        }
        v vVar = this.y;
        float i2 = vVar.i(vVar.s() + ((float) d2));
        D();
        i(i2);
    }

    public void f(boolean z) {
        this.M = z;
    }

    public z g() {
        return this.f16378g;
    }

    public void g(double d2) {
        if (d2 == this.y.s()) {
            return;
        }
        float i2 = this.y.i((float) d2);
        D();
        i(i2);
    }

    public void g(boolean z) {
        this.L = z;
    }

    public GeoPoint h() {
        return this.y.a();
    }

    public void h(double d2) {
        for (e5 e5Var : this.t) {
            if (e5Var != null) {
                try {
                    e5Var.a(d2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void h(boolean z) {
        d(z ? 12 : 0);
    }

    public Rect i() {
        o5 o5Var = new o5(this.x.e().width(), this.x.e().height());
        GeoPoint a2 = this.z.a(o5Var);
        o5Var.e(0.0d, this.x.e().height());
        GeoPoint a3 = this.z.a(o5Var);
        o5Var.e(this.x.e().width(), 0.0d);
        GeoPoint a4 = this.z.a(o5Var);
        o5Var.e(0.0d, 0.0d);
        GeoPoint a5 = this.z.a(o5Var);
        return new Rect(Math.min(Math.min(Math.min(a5.getLongitudeE6(), a2.getLongitudeE6()), a3.getLongitudeE6()), a4.getLongitudeE6()), Math.min(Math.min(Math.min(a5.getLatitudeE6(), a2.getLatitudeE6()), a3.getLatitudeE6()), a4.getLatitudeE6()), Math.max(Math.max(Math.max(a5.getLongitudeE6(), a2.getLongitudeE6()), a3.getLongitudeE6()), a4.getLongitudeE6()), Math.max(Math.max(Math.max(a5.getLatitudeE6(), a2.getLatitudeE6()), a3.getLatitudeE6()), a4.getLatitudeE6()));
    }

    public void i(double d2) {
        ma.c("skew notifySkew");
        for (i5 i5Var : this.u) {
            if (i5Var != null) {
                try {
                    i5Var.a(d2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void i(boolean z) {
        d(0);
    }

    public float j() {
        return this.y.c();
    }

    public void j(double d2) {
        a((360.0d - d2) % 360.0d, MapParamConstants.MAX_SKEW_ANGLE, true);
    }

    public Rect k() {
        return new Rect(this.G, this.H, this.I, this.J);
    }

    public void k(double d2) {
        this.f16378g.b();
        b(new z8(102, new double[]{d2, 0.0d}));
    }

    public v l() {
        return this.y;
    }

    public void l(double d2) {
        a((360.0d - d2) % 360.0d, v(), true);
    }

    public int m() {
        return this.y.f();
    }

    public void m(double d2) {
        this.f16378g.b();
        b(new z8(100, new double[]{d2}));
    }

    public Rect n() {
        if (this.z == null) {
            return null;
        }
        o5 o5Var = new o5(this.G, this.H);
        GeoPoint a2 = this.z.a(o5Var);
        o5Var.e(this.x.e().width() - this.I, this.x.e().height() - this.J);
        GeoPoint a3 = this.z.a(o5Var);
        return new Rect(Math.min(a2.getLongitudeE6(), a3.getLongitudeE6()), Math.min(a2.getLatitudeE6(), a3.getLatitudeE6()), Math.max(a2.getLongitudeE6(), a3.getLongitudeE6()), Math.max(a2.getLatitudeE6(), a3.getLatitudeE6()));
    }

    public void n(double d2) {
        this.f16378g.b();
        b(new z8(108, new double[]{d2}));
    }

    public void o(double d2) {
        this.f16378g.b();
        b(new z8(102, new double[]{0.0d, d2}));
    }

    public float p() {
        return this.y.m();
    }

    public float q() {
        return this.y.p();
    }

    public int r() {
        return this.y.q();
    }

    public Rect s() {
        GeoPoint t = t();
        GeoPoint u = u();
        return new Rect(t.getLongitudeE6(), t.getLatitudeE6(), u.getLongitudeE6(), u.getLatitudeE6());
    }

    public GeoPoint t() {
        return this.z.a(new o5(0.0d, 0.0d));
    }

    public GeoPoint u() {
        return this.z.a(new o5(this.x.e().width(), this.x.e().height()));
    }

    public float v() {
        return this.y.s();
    }

    public boolean w() {
        return this.f16378g.g();
    }

    public boolean x() {
        uf ufVar = this.f16379h;
        if (ufVar != null) {
            return ufVar.e();
        }
        return false;
    }

    public void z() {
        GeoPoint geoPoint = new GeoPoint(c0, b0);
        this.y.a(this.x.e(), geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6(), 13);
        this.x.f().e(this.F);
    }
}
