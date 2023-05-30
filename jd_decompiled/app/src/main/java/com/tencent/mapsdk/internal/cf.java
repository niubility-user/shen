package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.z7;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.model.AlphaAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.EmergeAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IAlphaAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IEmergeAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class cf extends ye<r0> implements r0 {
    private fg B;
    private gg C;
    private Polyline D;
    private final List<d> E;
    private final List<GeoPoint> F;
    private float G;
    private final qc H;
    private boolean I;
    private int[] J;
    private int[] K;
    private int[] L;
    private int M;
    private PolylineOptions.ColorType N;
    private BitmapDescriptor O;
    private final int P;
    private z7 Q;
    private float R;
    private d S;
    private float T;
    private int U;
    private PolylineOptions.Text V;
    private boolean W;
    private boolean X;
    private float Y;
    private boolean Z;
    private boolean a0;
    private int b0;
    private GeoPoint c0;
    private String d0;
    private int e0;
    private List<Integer> f0;
    private final float g0;
    private boolean h0;
    private Animation i0;
    private PolylineOptions j0;
    private List<LatLng> k0;
    private final a1 l0;
    private String m0;
    private boolean n0;
    private z7.b o0;

    /* loaded from: classes9.dex */
    public class a implements qc.b {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            cf.this.Q();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Comparator<Integer> {
        public b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(Integer num, Integer num2) {
            return num.compareTo(num2);
        }
    }

    /* loaded from: classes9.dex */
    public class c implements z7.b {
        public c() {
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(float f2) {
            cf.this.T = f2;
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(float f2, float f3, float f4, float f5) {
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(int i2, int i3) {
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void setAlpha(float f2) {
            cf.this.R = f2;
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void setScale(float f2, float f3) {
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends GeoPoint {
        public float a;
        public int b;

        public d() {
            this.a = 0.0f;
            this.b = 0;
        }

        public d(GeoPoint geoPoint) {
            super(geoPoint);
            this.a = 0.0f;
            this.b = 0;
        }

        @Override // com.tencent.map.lib.models.GeoPoint
        public String toString() {
            return super.toString() + DYConstants.DY_REGEX_COMMA + this.a;
        }
    }

    /* loaded from: classes9.dex */
    public static class e {
        public int[] a;
        public int[] b;

        /* renamed from: c  reason: collision with root package name */
        public int f16372c;
        public int d;

        public e(int i2) {
            this.d = i2;
            this.a = new int[i2];
            this.b = new int[i2];
        }

        public void a(int i2, int i3) {
            int i4 = this.f16372c;
            if (i4 >= this.d) {
                return;
            }
            this.a[i4] = i2;
            this.b[i4] = i3;
            this.f16372c = i4 + 1;
        }
    }

    public cf(a1 a1Var) {
        super(a1Var);
        this.B = null;
        this.C = null;
        this.D = null;
        this.I = false;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = -7829368;
        this.O = null;
        this.P = 12;
        this.S = null;
        this.T = 1.0f;
        this.U = 0;
        this.W = false;
        this.X = true;
        this.Y = 0.0f;
        this.Z = false;
        this.a0 = false;
        this.b0 = -1;
        this.c0 = null;
        this.e0 = (int) (f7.d(z().getContext()) * 100.0f);
        this.m0 = fj.r;
        this.o0 = new c();
        this.l0 = a1Var;
        qc A = a1Var.A();
        this.H = A;
        this.E = new CopyOnWriteArrayList();
        this.F = new CopyOnWriteArrayList();
        this.g0 = A.getContext().getResources().getDisplayMetrics().density;
    }

    private s5 J() {
        int a2;
        s5 s5Var = new s5();
        if (this.H == null) {
            return s5Var;
        }
        BitmapDescriptor bitmapDescriptor = this.O;
        if (bitmapDescriptor != null && bitmapDescriptor.getFormater() != null) {
            s5Var.c(this.O.getFormater().getBitmapId());
        }
        e a3 = a(this.F);
        s5Var.b(this.I);
        s5Var.d(this.e0);
        s5Var.b(this.F);
        s5Var.a(this.M);
        if (this.U != 0 || (a2 = a(this.H.getContext(), this.O)) <= 0) {
            s5Var.e(this.f17510j);
        } else {
            float f2 = this.f17510j;
            float f3 = a2;
            if (f2 > f3) {
                f2 = f3;
            }
            s5Var.e(f2);
        }
        if (this.N == PolylineOptions.ColorType.LINE_COLOR_ARGB) {
            s5Var.c(true);
            float f4 = this.f17510j;
            if (this.G * 2.0f >= f4) {
                this.G = f4 / 3.0f;
            }
            s5Var.b(this.G);
        }
        int[] M = M();
        if (this.X) {
            s5Var.c(a3.a);
            if (this.G <= 0.0f || M == null || M.length <= 0) {
                s5Var.d(a3.b);
            } else {
                s5Var.a(a3.b, M);
            }
        } else {
            s5Var.c(new int[]{0});
            if (this.G <= 0.0f || M == null || M.length <= 0) {
                s5Var.d(new int[]{this.f17512l});
            } else {
                s5Var.a(new int[]{this.f17512l}, new int[]{M[0]});
            }
        }
        s5Var.c(this.R);
        s5Var.c(this.U);
        s5Var.d((int) this.f17513m);
        s5Var.a(this.W);
        s5Var.f(this.Z);
        s5Var.e(this.a0);
        s5Var.d(this.X);
        s5Var.a(this.d0);
        s5Var.a(this.f0);
        s5Var.b(this.p);
        s5Var.D = this.n0;
        return s5Var;
    }

    private void L() {
        gg ggVar;
        qc qcVar = this.H;
        if (qcVar == null || !this.h0) {
            return;
        }
        this.h0 = false;
        if (this.V == null && (ggVar = this.C) != null) {
            ggVar.a();
            this.C = null;
            return;
        }
        qi S = qcVar.S();
        PolylineOptions.Text text = this.V;
        if (text != null) {
            gg ggVar2 = this.C;
            if (ggVar2 != null) {
                ggVar2.a(text);
                return;
            }
            List<GeoPoint> list = this.F;
            if (list == null || list.size() < 2) {
                return;
            }
            List<GeoPoint> list2 = this.F;
            this.C = new gg(S, (GeoPoint[]) list2.toArray(new GeoPoint[list2.size()]), this.V);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        if (this.H == null || this.F.size() < 2 || !isVisible()) {
            return;
        }
        fg fgVar = this.B;
        s5 J = J();
        if (J.a()) {
            if (fgVar == null) {
                fgVar = new fg(this, this.l0, J);
                fgVar.f17509i = this.f17509i;
                this.H.g().b(fgVar);
                this.B = fgVar;
            } else {
                fgVar.a(J);
            }
            fgVar.a(this.b0, this.c0);
            this.H.w0();
        }
    }

    private void R() {
        if (Thread.currentThread().getName().contains(this.m0)) {
            Q();
        } else {
            this.H.a(new a());
        }
    }

    private float a(d dVar, d dVar2, GeoPoint geoPoint, d dVar3) {
        double d2;
        double d3;
        int longitudeE6 = dVar.getLongitudeE6();
        int latitudeE6 = dVar.getLatitudeE6();
        int longitudeE62 = dVar2.getLongitudeE6();
        int latitudeE62 = dVar2.getLatitudeE6();
        int longitudeE63 = geoPoint.getLongitudeE6();
        int latitudeE63 = geoPoint.getLatitudeE6();
        int i2 = longitudeE62 - longitudeE6;
        int i3 = longitudeE63 - longitudeE6;
        int i4 = latitudeE62 - latitudeE6;
        int i5 = latitudeE63 - latitudeE6;
        float f2 = (i2 * i3) + (i4 * i5);
        if (f2 <= 0.0f) {
            dVar3.setLatitudeE6(dVar.getLatitudeE6());
            dVar3.setLongitudeE6(dVar.getLongitudeE6());
            dVar3.a = dVar.a;
            d2 = i3;
            d3 = i5;
        } else {
            double d4 = (i2 * i2) + (i4 * i4);
            double d5 = f2;
            if (d5 >= d4) {
                dVar3.setLatitudeE6(dVar2.getLatitudeE6());
                dVar3.setLongitudeE6(dVar2.getLongitudeE6());
                dVar3.a = dVar2.a;
                d2 = longitudeE63 - longitudeE62;
                d3 = latitudeE63 - latitudeE62;
            } else {
                Double.isNaN(d5);
                Double.isNaN(d4);
                float f3 = (float) (d5 / d4);
                float f4 = longitudeE6 + (i2 * f3);
                float f5 = latitudeE6 + (i4 * f3);
                dVar3.setLongitudeE6(Math.round(f4));
                dVar3.setLatitudeE6(Math.round(f5));
                float f6 = dVar.a;
                dVar3.a = f6 + ((dVar2.a - f6) * f3);
                d2 = longitudeE63 - f4;
                d3 = latitudeE63 - f5;
            }
        }
        return (float) Math.hypot(d2, d3);
    }

    private int a(Context context, BitmapDescriptor bitmapDescriptor) {
        Bitmap bitmap;
        int height;
        if (context == null || bitmapDescriptor == null || (bitmap = bitmapDescriptor.getBitmap(context)) == null || (height = bitmap.getHeight()) <= 0) {
            return 0;
        }
        double d2 = f7.d(context);
        Double.isNaN(d2);
        return (int) ((Math.pow(2.0d, 25.0d) / Math.pow(height, 2.0d)) / d2);
    }

    private d a(GeoPoint geoPoint) {
        d dVar = new d();
        List<d> list = this.E;
        d dVar2 = null;
        if (list != null && list.size() >= 2 && geoPoint != null) {
            d dVar3 = this.E.get(0);
            int i2 = 1;
            float f2 = Float.MAX_VALUE;
            while (i2 < this.E.size()) {
                d dVar4 = this.E.get(i2);
                float a2 = a(dVar3, dVar4, geoPoint, dVar);
                if (a2 < f2) {
                    f2 = a2;
                    dVar2 = dVar;
                    dVar = new d();
                }
                i2++;
                dVar3 = dVar4;
            }
        }
        return dVar2;
    }

    private d a(d dVar, d dVar2, float f2) {
        d dVar3 = new d();
        int longitudeE6 = dVar2.getLongitudeE6() - dVar.getLongitudeE6();
        dVar3.setLatitudeE6(dVar.getLatitudeE6() + Math.round((dVar2.getLatitudeE6() - dVar.getLatitudeE6()) * f2));
        dVar3.setLongitudeE6(dVar.getLongitudeE6() + Math.round(longitudeE6 * f2));
        float f3 = dVar.a;
        dVar3.a = f3 + ((dVar2.a - f3) * f2);
        return dVar3;
    }

    private e a(List<GeoPoint> list) {
        int[] iArr;
        int intValue;
        Object obj;
        int[] iArr2 = this.J;
        if (iArr2 == null || (iArr = this.K) == null || list == null || iArr2.length == 0 || iArr.length == 0 || list.isEmpty()) {
            int i2 = this.f17512l;
            if (this.N == PolylineOptions.ColorType.LINE_COLOR_TEXTURE) {
                i2 = b(i2);
            }
            e eVar = new e(1);
            eVar.a(0, i2);
            return eVar;
        }
        TreeSet treeSet = new TreeSet(new b());
        for (int i3 : this.K) {
            if (i3 >= 0 && i3 < list.size()) {
                treeSet.add(Integer.valueOf(i3));
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 : this.J) {
            arrayList.add(Integer.valueOf(i4));
        }
        Integer[] numArr = (Integer[]) treeSet.toArray(new Integer[0]);
        if (numArr[0].intValue() != 0) {
            treeSet.add(0);
            arrayList.add(0, Integer.valueOf(this.J[0]));
        }
        if (numArr[numArr.length - 1].intValue() != list.size() - 1) {
            treeSet.add(Integer.valueOf(list.size() - 1));
            if (numArr.length > this.J.length) {
                int[] iArr3 = this.J;
                arrayList.add(arrayList.size() - 1, Integer.valueOf(iArr3[iArr3.length - 1]));
            }
        }
        ArrayList arrayList2 = new ArrayList(treeSet);
        int size = arrayList2.size();
        e eVar2 = new e(size);
        for (int i5 = 0; i5 < size; i5++) {
            if (i5 > arrayList.size() - 1) {
                intValue = ((Integer) arrayList2.get(i5)).intValue();
                obj = arrayList.get(arrayList.size() - 1);
            } else {
                intValue = ((Integer) arrayList2.get(i5)).intValue();
                obj = arrayList.get(i5);
            }
            eVar2.a(intValue, ((Integer) obj).intValue());
        }
        return eVar2;
    }

    private PolylineOptions.ColorType a(PolylineOptions.ColorType colorType) {
        if (colorType != PolylineOptions.ColorType.LINE_COLOR_NONE) {
            return colorType;
        }
        int[] iArr = this.J;
        if (iArr == null || iArr.length <= 0) {
            int i2 = this.f17512l;
            return (i2 < 0 || i2 >= 12) ? PolylineOptions.ColorType.LINE_COLOR_ARGB : PolylineOptions.ColorType.LINE_COLOR_TEXTURE;
        }
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            int[] iArr2 = this.J;
            if (iArr2[i3] < 0 || iArr2[i3] >= 12) {
                return PolylineOptions.ColorType.LINE_COLOR_ARGB;
            }
        }
        return PolylineOptions.ColorType.LINE_COLOR_TEXTURE;
    }

    private void a(b8 b8Var) {
        if (this.H == null) {
            return;
        }
        this.Q = b8Var;
        GeoPoint from = GeoPoint.from(b8Var.i());
        d a2 = a(from);
        this.S = a2;
        if (a2 != null) {
            b8Var.a(this.o0);
            b8Var.a((GeoPoint) null, (GeoPoint) null);
            this.H.w0();
            return;
        }
        ma.b("Error, start point not found. [p=" + from + "] [offsetGeoPoints=" + this.E + "]");
    }

    private void a(y7 y7Var) {
        this.Q = y7Var;
        y7Var.a(this.o0);
        this.Q.a((GeoPoint) null, (GeoPoint) null);
        qc qcVar = this.H;
        if (qcVar != null) {
            qcVar.w0();
        }
    }

    private int b(int i2) {
        if (i2 >= 12) {
            i2 = 11;
        }
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    private ArrayList<GeoPoint> b(List<d> list) {
        ArrayList<GeoPoint> arrayList = new ArrayList<>();
        if (list != null && list.size() >= 2) {
            float f2 = this.S.a;
            float f3 = this.T;
            float f4 = (this.Y - f2) * f3;
            float f5 = f2 - (f2 * f3);
            float f6 = f2 + f4;
            d dVar = null;
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    break;
                }
                d dVar2 = list.get(i2);
                float f7 = dVar2.a;
                if (f7 <= f5 || f7 >= f6) {
                    if (f7 > f6) {
                        if (dVar != null) {
                            float f8 = dVar.a;
                            if (f8 < f6) {
                                d a2 = a(dVar, dVar2, (f6 - f8) / (f7 - f8));
                                a2.b = dVar2.b;
                                arrayList.add(a2);
                            }
                        }
                    } else if (Float.compare(f7, f5) != 0 && Float.compare(dVar2.a, f6) != 0) {
                        i2++;
                        dVar = dVar2;
                    }
                } else if (dVar != null) {
                    float f9 = dVar.a;
                    if (f9 < f5) {
                        d a3 = a(dVar, dVar2, (f5 - f9) / (f7 - f9));
                        a3.b = dVar.b;
                        arrayList.add(a3);
                    }
                }
                arrayList.add(dVar2);
                i2++;
                dVar = dVar2;
            }
        }
        return arrayList;
    }

    private int c(int i2) {
        if (i2 != 6) {
            if (i2 == 33) {
                return 33;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 1) {
                return 1;
            }
            if (i2 == 2) {
                return 2;
            }
            if (i2 == 3) {
                return 3;
            }
            if (i2 == 4) {
                return 4;
            }
        }
        return 6;
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void B() {
        super.B();
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.B();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        this.m0 = Thread.currentThread().getName();
        if (this.H != null && isVisible()) {
            if (C()) {
                if (this.E.size() >= 2) {
                    this.F.clear();
                    this.F.addAll(this.E);
                }
                R();
            }
            K();
            L();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.remove();
            this.B = null;
        }
        gg ggVar = this.C;
        if (ggVar != null) {
            ggVar.a();
            this.C = null;
        }
        List<d> list = this.E;
        if (list != null) {
            list.clear();
        }
        List<GeoPoint> list2 = this.F;
        if (list2 != null) {
            list2.clear();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r2.F.size() >= 2) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        if ((r0 instanceof com.tencent.mapsdk.internal.y7) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        R();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean K() {
        /*
            r2 = this;
            com.tencent.mapsdk.internal.qc r0 = r2.H
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            com.tencent.mapsdk.internal.z7 r0 = r2.Q
            if (r0 == 0) goto L53
            boolean r0 = r0.g()
            if (r0 == 0) goto L53
            com.tencent.mapsdk.internal.z7 r0 = r2.Q
            r0.a()
            com.tencent.mapsdk.internal.z7 r0 = r2.Q
            boolean r1 = r0 instanceof com.tencent.mapsdk.internal.b8
            if (r1 == 0) goto L35
            java.util.List<com.tencent.map.lib.models.GeoPoint> r0 = r2.F
            r0.clear()
            java.util.List<com.tencent.map.lib.models.GeoPoint> r0 = r2.F
            java.util.List<com.tencent.mapsdk.internal.cf$d> r1 = r2.E
            java.util.ArrayList r1 = r2.b(r1)
            r0.addAll(r1)
            java.util.List<com.tencent.map.lib.models.GeoPoint> r0 = r2.F
            int r0 = r0.size()
            r1 = 2
            if (r0 < r1) goto L3c
            goto L39
        L35:
            boolean r0 = r0 instanceof com.tencent.mapsdk.internal.y7
            if (r0 == 0) goto L3c
        L39:
            r2.R()
        L3c:
            com.tencent.mapsdk.internal.qc r0 = r2.H
            r0.w0()
            com.tencent.mapsdk.internal.z7 r0 = r2.Q
            boolean r0 = r0.e()
            if (r0 == 0) goto L51
            com.tencent.mapsdk.internal.z7 r0 = r2.Q
            r1 = 0
            r0.a(r1)
            r2.Q = r1
        L51:
            r0 = 1
            return r0
        L53:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.cf.K():boolean");
    }

    public int[] M() {
        return this.L;
    }

    public fg N() {
        return this.B;
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: O  reason: merged with bridge method [inline-methods] */
    public r0 x() {
        return this;
    }

    public Polyline P() {
        return this.D;
    }

    public float a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (geoPoint == null || geoPoint2 == null) {
            return 0.0f;
        }
        double d2 = 0.0f;
        double hypot = Math.hypot(geoPoint.getLatitudeE6() - geoPoint2.getLatitudeE6(), geoPoint.getLongitudeE6() - geoPoint2.getLongitudeE6());
        Double.isNaN(d2);
        return (float) (d2 + hypot);
    }

    public void a(float f2) {
        this.G = f2;
    }

    @Override // com.tencent.mapsdk.internal.r0
    public void a(int i2, LatLng latLng) {
        GeoPoint from = GeoPoint.from(latLng);
        if (i2 == -1 || from == null) {
            return;
        }
        this.b0 = i2;
        this.c0 = from;
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.a(i2, from);
        }
        qc qcVar = this.H;
        if (qcVar != null) {
            qcVar.w0();
        }
    }

    @Override // com.tencent.mapsdk.internal.r0
    public void a(z7 z7Var) {
        z7 z7Var2 = this.Q;
        if (z7Var2 != null) {
            z7Var2.h();
            this.Q.a((z7.b) null);
        }
        if (z7Var instanceof b8) {
            a((b8) z7Var);
        } else if (z7Var instanceof y7) {
            a((y7) z7Var);
        } else {
            this.Q = null;
        }
    }

    public void a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor.getBitmap(this.l0.getContext()) != null) {
            this.d0 = bitmapDescriptor.getFormater().getBitmapId();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.r4
    public void a(IndoorBuilding indoorBuilding) {
        super.a(indoorBuilding);
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.a(indoorBuilding);
        }
    }

    public void a(Polyline polyline) {
        this.D = polyline;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void addTurnArrow(int i2, int i3) {
        fg fgVar = this.B;
        if (fgVar == null) {
            return;
        }
        fgVar.J();
        this.B.b(i2, i3);
        R();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void appendPoint(LatLng... latLngArr) {
        if (this.k0 == null) {
            this.k0 = new ArrayList();
        }
        this.k0.addAll(Arrays.asList(latLngArr));
        c(this.k0);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void appendPoints(List<LatLng> list) {
        if (this.k0 == null) {
            this.k0 = new ArrayList();
        }
        this.k0.addAll(list);
        c(this.k0);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void arrowSpacing(int i2) {
        if (this.e0 != i2) {
            this.e0 = i2;
            PolylineOptions polylineOptions = this.j0;
            if (polylineOptions != null) {
                polylineOptions.arrowSpacing(i2);
            }
            B();
        }
    }

    @Override // com.tencent.mapsdk.internal.r0
    public void b(boolean z) {
        this.Z = z;
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.b(z);
        }
        B();
    }

    @Override // com.tencent.mapsdk.internal.r0
    public Rect c() {
        return this.B.c();
    }

    public void c(List<LatLng> list) {
        int size;
        this.k0 = list;
        if (list != null && (size = list.size()) > 0) {
            this.Y = 0.0f;
            this.E.clear();
            d dVar = null;
            for (int i2 = 0; i2 < size; i2++) {
                LatLng latLng = list.get(i2);
                if (latLng != null) {
                    d dVar2 = new d(GeoPoint.from(latLng));
                    if (dVar != null) {
                        float a2 = this.Y + a(dVar2, dVar);
                        this.Y = a2;
                        dVar2.a = a2;
                        dVar2.b = dVar.b + 1;
                    }
                    this.E.add(dVar2);
                    dVar = dVar2;
                }
            }
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void cleanTurnArrow() {
        fg fgVar = this.B;
        if (fgVar == null) {
            return;
        }
        fgVar.J();
        R();
    }

    public void d(int i2) {
        this.e0 = i2;
    }

    public void d(List<Integer> list) {
        this.f0 = list;
        B();
    }

    @Override // com.tencent.mapsdk.internal.r0
    public void d(boolean z) {
        f(z);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof cf)) {
            return TextUtils.equals(y(), ((cf) obj).y());
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void eraseColor(int i2) {
        if (this.M != i2) {
            this.M = i2;
            PolylineOptions polylineOptions = this.j0;
            if (polylineOptions != null) {
                polylineOptions.eraseColor(i2);
            }
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void eraseTo(int i2, LatLng latLng) {
        a(i2, latLng);
    }

    public void f(boolean z) {
        this.a0 = z;
        B();
    }

    public float getAlpha() {
        return this.R;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public int getColor() {
        return getStrokeColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public int[][] getColors() {
        int[] iArr;
        int[] iArr2 = this.J;
        if (iArr2 == null || (iArr = this.K) == null) {
            return null;
        }
        int[][] iArr3 = (int[][]) Array.newInstance(int.class, 2, Math.max(iArr2.length, iArr.length));
        iArr3[0] = this.J;
        iArr3[1] = this.K;
        return iArr3;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    public List<Boundable<s4>> getGroupBounds() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(this.B);
        return arrayList;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public List<Integer> getPattern() {
        return this.f0;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public List<LatLng> getPoints() {
        return this.k0;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public PolylineOptions getPolylineOptions() {
        return this.j0;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public PolylineOptions.Text getText() {
        return this.V;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public Rect getVisibleRect() {
        return c();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public float getWidth() {
        return this.f17510j;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean handleOnTap() {
        return true;
    }

    public int hashCode() {
        return y().hashCode();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public boolean isAboveMaskLayer() {
        return this.W;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public boolean isGradientEnable() {
        return this.n0;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.r4
    public void l() {
        super.l();
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.l();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public void n() {
        R();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        fg fgVar;
        if (this.H == null || !isVisible() || (fgVar = this.B) == null) {
            return false;
        }
        return fgVar.onTap(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void pattern(List<Integer> list) {
        d(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setAboveMaskLayer(boolean z) {
        if (this.W != z) {
            this.W = z;
            B();
        }
    }

    @Override // com.tencent.mapsdk.internal.r0
    public void setAlpha(float f2) {
        this.R = f2;
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void setAnimation(Animation animation) {
        if ((animation instanceof AlphaAnimation) || (animation instanceof EmergeAnimation) || (animation instanceof IAlphaAnimation) || (animation instanceof IEmergeAnimation)) {
            this.i0 = animation;
        } else {
            ma.h("Unsupported animation, only AlphaAnimation and EmergeAnimation allowed.");
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setArrow(boolean z) {
        if (this.I != z) {
            this.I = z;
            PolylineOptions polylineOptions = this.j0;
            if (polylineOptions != null) {
                polylineOptions.arrow(z);
            }
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setBorderColors(int[] iArr) {
        if (this.L != iArr) {
            this.L = iArr;
            PolylineOptions polylineOptions = this.j0;
            if (polylineOptions != null) {
                polylineOptions.borderColors(iArr);
            }
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColor(int i2) {
        setStrokeColor(i2);
        PolylineOptions polylineOptions = this.j0;
        if (polylineOptions != null) {
            polylineOptions.color(i2);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColorTexture(BitmapDescriptor bitmapDescriptor) {
        if (this.H == null || bitmapDescriptor == null || bitmapDescriptor.getFormater() == null) {
            return;
        }
        this.O = bitmapDescriptor;
        bitmapDescriptor.getBitmap(this.H.getContext());
        this.N = PolylineOptions.ColorType.LINE_COLOR_TEXTURE;
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColorTexture(String str) {
        setColorTexture(BitmapDescriptorFactory.fromAsset(str));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColors(int[] iArr, int[] iArr2) {
        this.J = iArr;
        this.K = iArr2;
        this.N = a(PolylineOptions.ColorType.LINE_COLOR_NONE);
        this.X = true;
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setEraseable(boolean z) {
        b(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setGradientEnable(boolean z) {
        if (this.U == 0 && this.X) {
            this.n0 = z;
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setPoints(List<LatLng> list) {
        c(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setPolylineOptions(PolylineOptions polylineOptions) {
        if (this.H == null || polylineOptions == null) {
            return;
        }
        D();
        this.j0 = polylineOptions;
        setStrokeWidth(polylineOptions.getWidth() == -1.0f ? this.g0 * 9.0f : polylineOptions.getWidth());
        setStrokeColor(polylineOptions.getColor());
        setZIndex(polylineOptions.getZIndex());
        setVisible(polylineOptions.isVisible());
        setAlpha(polylineOptions.getAlpha());
        setArrow(polylineOptions.isArrow());
        setColorTexture(polylineOptions.getColorTexture());
        f(polylineOptions.getLineCap());
        d(polylineOptions.getPattern());
        a(polylineOptions.getBorderWidth());
        setClickable(polylineOptions.isClickable());
        this.L = polylineOptions.getBorderColors();
        this.f17509i = polylineOptions.getIndoorInfo();
        this.U = polylineOptions.getLineType();
        this.p = polylineOptions.getLevel();
        this.M = polylineOptions.getEraseColor();
        if (polylineOptions.isAbovePillar() && this.p == 0) {
            this.p = 2;
        }
        c(polylineOptions.getPoints());
        if (polylineOptions.getArrowTexture() != null) {
            a(polylineOptions.getArrowTexture());
        }
        d(polylineOptions.getArrowSpacing());
        this.X = polylineOptions.isRoad();
        Animation animation = polylineOptions.getAnimation();
        if (animation != null) {
            a(((i7) animation).a);
        }
        int[][] colors = polylineOptions.getColors();
        if (colors != null && colors.length == 2) {
            int[] iArr = colors[0];
            int[] iArr2 = colors[1];
            if (iArr != null && iArr2 != null) {
                setColors(iArr, iArr2);
            }
        }
        this.N = a(polylineOptions.getColorType());
        setText(polylineOptions.getText());
        setGradientEnable(polylineOptions.isGradientEnable());
        I();
        R();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setText(PolylineOptions.Text text) {
        if (this.V != text) {
            this.V = text;
            this.h0 = true;
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public void setVisible(boolean z) {
        super.setVisible(z);
        fg fgVar = this.B;
        if (fgVar != null) {
            fgVar.setVisible(z);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setWidth(float f2) {
        if (f2 < 0.0f) {
            f2 = 1.0f;
        }
        if (f2 > 128.0f) {
            f2 = 128.0f;
        }
        setStrokeWidth(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline, com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void startAnimation(Animation animation) {
        setAnimation(animation);
        startAnimation();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public boolean startAnimation() {
        i7 a2;
        if (this.i0 == null || (a2 = h8.a(this.H.V(), this.i0)) == null) {
            return false;
        }
        a(a2.a);
        return false;
    }
}
