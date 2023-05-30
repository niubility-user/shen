package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class bf extends ye<q0> implements g5, q0 {
    private static final int X = 180;
    private static final int Y = 1;
    private static final double Z = 1.0E-10d;
    private List<GeoPoint> B;
    private List<LatLng> C;
    private w5[] D;
    private GeoPoint E;
    private Rect F;
    private Rect G;
    public final PolygonInfo H;
    private hg I;
    private final byte[] J;
    private c K;
    private String L;
    private GeoPoint M;
    private Rect N;
    private GeoPoint[] O;
    private boolean P;
    private boolean Q;
    private ArrayList<hg> R;
    private ArrayList<hg> S;
    private PolygonOptions T;
    private qc U;
    private String V;
    private BitmapDescriptor W;

    /* loaded from: classes9.dex */
    public class b {
        public LatLng[] a;

        public b(LatLng latLng, LatLng latLng2) {
            this.a = r2;
            LatLng[] latLngArr = {latLng, latLng2};
        }

        public String toString() {
            return this.a[0].toString() + "    " + this.a[1].toString();
        }
    }

    /* loaded from: classes9.dex */
    public static class c {

        /* renamed from: g  reason: collision with root package name */
        private static final int f16325g = 900;

        /* renamed from: h  reason: collision with root package name */
        private static final int f16326h = 180;
        private Bitmap a;
        private TextPaint b;

        /* renamed from: c  reason: collision with root package name */
        private int f16327c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f16328e;

        /* renamed from: f  reason: collision with root package name */
        private HashMap<String, Integer> f16329f;

        private c() {
            this.a = null;
            this.b = null;
            this.f16327c = 900;
            this.d = 180;
            this.f16328e = -1;
            this.f16329f = new HashMap<>();
        }

        private String a(String str, String str2, int i2) {
            return str + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + i2;
        }

        private void a(int i2) {
            TextPaint textPaint = this.b;
            if (textPaint != null) {
                textPaint.setTextSize(i2);
            }
        }

        private void a(int i2, int i3, Typeface typeface) {
            if (this.b == null) {
                TextPaint textPaint = new TextPaint(65);
                this.b = textPaint;
                textPaint.setStyle(Paint.Style.FILL);
                this.b.setTextAlign(Paint.Align.CENTER);
            }
            this.b.setColor(i3);
            this.b.setTextSize(i2);
            this.b.setTypeface(typeface);
        }

        private void a(String str, int i2, int i3, Typeface typeface) {
            if (this.a == null) {
                try {
                    this.a = Bitmap.createBitmap(this.f16327c, this.d, Bitmap.Config.ARGB_8888);
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            a(i2, i3, typeface);
            Rect rect = new Rect();
            this.b.getTextBounds(str, 0, str.length(), rect);
            int abs = Math.abs(rect.width());
            int abs2 = Math.abs(rect.height());
            int i4 = this.f16327c;
            if (abs > i4 || abs2 > this.d) {
                this.f16327c = Math.max(abs, i4);
                int max = Math.max(abs2, this.d);
                this.d = max;
                this.a = Bitmap.createBitmap(this.f16327c, max, Bitmap.Config.ARGB_8888);
            }
            this.a.eraseColor(0);
            new Canvas(this.a).drawText(str, this.f16327c / 2.0f, (this.d / 2.0f) - ((this.b.descent() + this.b.ascent()) / 2.0f), this.b);
        }

        private int[] a(String str, int i2, Rect rect) {
            a(i2);
            this.b.getTextBounds(str, 0, str.length(), rect);
            return new int[]{Math.abs(rect.width()), Math.abs(rect.height())};
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int a(android.graphics.Rect r9, java.lang.String r10, int r11, android.graphics.Typeface r12, int r13, int r14) {
            /*
                r8 = this;
                if (r9 != 0) goto L3
                return r14
            L3:
                int r0 = r9.height()
                int r0 = java.lang.Math.abs(r0)
                int r9 = r9.width()
                int r9 = java.lang.Math.abs(r9)
                android.graphics.Rect r1 = new android.graphics.Rect
                r2 = 0
                r1.<init>(r2, r2, r2, r2)
                r8.a(r13, r11, r12)
                float r11 = com.tencent.mapsdk.internal.b7.w()
                r12 = 1065353216(0x3f800000, float:1.0)
                float r12 = r12 / r11
                double r11 = (double) r12
                double r11 = java.lang.Math.ceil(r11)
                int r11 = (int) r11
                int r12 = r13 / 2
                int[] r3 = r8.a(r10, r12, r1)
                r4 = r3[r2]
                r5 = 1
                r3 = r3[r5]
                if (r4 > r9) goto L4c
                if (r3 > r0) goto L4c
            L38:
                int r3 = r12 + r11
                r7 = r3
                r3 = r12
                r12 = r7
                if (r12 >= r13) goto L5e
                int[] r4 = r8.a(r10, r12, r1)
                r6 = r4[r2]
                r4 = r4[r5]
                if (r6 > r9) goto L5e
                if (r4 <= r0) goto L38
                goto L5e
            L4c:
                int r12 = r12 - r11
                if (r12 < r14) goto L5d
                int[] r3 = r8.a(r10, r12, r1)
                r4 = r3[r2]
                r3 = r3[r5]
                if (r4 > r9) goto L4c
                if (r3 > r0) goto L4c
                r3 = r12
                goto L5e
            L5d:
                r3 = r14
            L5e:
                if (r3 >= r14) goto L61
                goto L62
            L61:
                r14 = r3
            L62:
                if (r14 <= r13) goto L65
                goto L66
            L65:
                r13 = r14
            L66:
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.bf.c.a(android.graphics.Rect, java.lang.String, int, android.graphics.Typeface, int, int):int");
        }

        public void a() {
            Bitmap bitmap = this.a;
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            this.a.recycle();
            this.a = null;
        }

        public Object[] a(String str, String str2, int i2, int i3, Typeface typeface) {
            String a = a(str, str2, i2);
            if (i2 > 180) {
                i2 = 180;
            }
            if (i2 != this.f16328e) {
                a(str2, i2, i3, typeface);
                this.f16328e = i2;
            }
            return new Object[]{a, this.a};
        }
    }

    public bf(a1 a1Var, PolygonOptions polygonOptions) {
        super(a1Var);
        this.B = new CopyOnWriteArrayList();
        this.J = new byte[0];
        this.K = new c();
        this.M = new GeoPoint();
        this.N = new Rect();
        this.P = false;
        this.Q = false;
        PolygonInfo polygonInfo = new PolygonInfo();
        this.H = polygonInfo;
        polygonInfo.polygonId = -1;
        this.T = polygonOptions;
        this.U = a1Var.A();
        if (polygonOptions != null && !e7.b(this.V)) {
            this.U.h().a(this);
        }
        a(polygonOptions);
    }

    private b[] L() {
        List<GeoPoint> list = this.B;
        List<LatLng> a2 = ea.a(list);
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = size - 1;
            if (i2 >= i3) {
                arrayList.add(new b(a2.get(i3), a2.get(0)));
                return (b[]) arrayList.toArray(new b[arrayList.size()]);
            }
            i2++;
            arrayList.add(new b(a2.get(i2), a2.get(i2)));
        }
    }

    private double a(LatLng latLng, b[] bVarArr, LatLng latLng2) {
        LatLng latLng3 = latLng;
        b[] bVarArr2 = bVarArr;
        int length = bVarArr2.length;
        double d = 0.0d;
        LatLng latLng4 = new LatLng(0.0d, 0.0d);
        LatLng latLng5 = new LatLng(0.0d, 0.0d);
        LatLng[] latLngArr = bVarArr2[0].a;
        LatLng latLng6 = latLngArr[0];
        LatLng latLng7 = latLngArr[1];
        double c2 = wa.c((Coordinate) latLng3, (Coordinate) latLng6, (Coordinate) latLng7);
        latLng4.latitude = latLng6.latitude;
        latLng4.longitude = latLng6.longitude;
        latLng5.latitude = latLng7.latitude;
        latLng5.longitude = latLng7.longitude;
        for (int i2 = 1; i2 < length; i2++) {
            LatLng[] latLngArr2 = bVarArr2[i2].a;
            LatLng latLng8 = latLngArr2[0];
            LatLng latLng9 = latLngArr2[1];
            double c3 = wa.c((Coordinate) latLng3, (Coordinate) latLng8, (Coordinate) latLng9);
            if (c3 < c2) {
                latLng4.latitude = latLng8.latitude;
                latLng4.longitude = latLng8.longitude;
                latLng5.latitude = latLng9.latitude;
                latLng5.longitude = latLng9.longitude;
                c2 = c3;
            }
        }
        LatLng b2 = wa.b(latLng3, latLng4, latLng5);
        LatLng latLng10 = new LatLng(0.0d, 0.0d);
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        while (!z) {
            LatLng a2 = wa.a(latLng3, b2, i3);
            double a3 = wa.a((Coordinate) a2, (Coordinate) b2);
            int i5 = 0;
            while (true) {
                if (i5 >= length) {
                    break;
                }
                LatLng[] latLngArr3 = bVarArr2[i5].a;
                if (wa.a(a2, a3, latLngArr3[0], latLngArr3[1])) {
                    z = true;
                    break;
                }
                i5++;
                bVarArr2 = bVarArr;
            }
            if (!z) {
                latLng10.latitude = a2.latitude;
                latLng10.longitude = a2.longitude;
                i3 += 5;
            } else if (i4 == 0) {
                latLng10.latitude = a2.latitude;
                latLng10.longitude = a2.longitude;
            } else {
                i4++;
                latLng3 = latLng;
                bVarArr2 = bVarArr;
            }
            d = a3;
            i4++;
            latLng3 = latLng;
            bVarArr2 = bVarArr;
        }
        latLng2.latitude = latLng10.latitude;
        latLng2.longitude = latLng10.longitude;
        return d;
    }

    private double a(b[] bVarArr, LatLng latLng) {
        List<LatLng> a2;
        List<GeoPoint> i2 = i();
        if (i2 == null || i2.isEmpty() || (a2 = ea.a(i2)) == null || a2.isEmpty()) {
            return 0.0d;
        }
        int size = a2.size();
        LatLng[] latLngArr = new LatLng[size];
        double[] dArr = new double[size];
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            latLngArr[i4] = new LatLng(0.0d, 0.0d);
            dArr[i4] = a(a2.get(i4), bVarArr, latLngArr[i4]);
            if (Double.isNaN(dArr[i4])) {
                dArr[i4] = 0.0d;
            }
        }
        double d = dArr[0];
        for (int i5 = 1; i5 < size; i5++) {
            if (dArr[i5] > d) {
                d = dArr[i5];
                i3 = i5;
            }
        }
        latLng.latitude = latLngArr[i3].latitude;
        latLng.longitude = latLngArr[i3].longitude;
        return dArr[i3];
    }

    private int a(Rect rect, String str) {
        return 1;
    }

    private int a(String str, GeoPoint geoPoint, s4 s4Var) {
        Rect a2 = a(L());
        this.N = a2;
        geoPoint.setLatitudeE6(a2.centerY());
        geoPoint.setLongitudeE6(this.N.centerX());
        Rect rect = this.N;
        GeoPoint geoPoint2 = new GeoPoint(rect.top, rect.left);
        Rect rect2 = this.N;
        GeoPoint geoPoint3 = new GeoPoint(rect2.top, rect2.right);
        Rect rect3 = this.N;
        GeoPoint geoPoint4 = new GeoPoint(rect3.bottom, rect3.right);
        Rect rect4 = this.N;
        o5[] a3 = a(new o5[]{s4Var.a(geoPoint2), s4Var.a(geoPoint3), s4Var.a(geoPoint4), s4Var.a(new GeoPoint(rect4.bottom, rect4.left))});
        return a(new Rect((int) a3[0].a, (int) a3[0].b, (int) a3[1].a, (int) a3[1].b), str);
    }

    private int a(String str, s4 s4Var) {
        Rect rect = this.N;
        GeoPoint geoPoint = new GeoPoint(rect.top, rect.left);
        Rect rect2 = this.N;
        GeoPoint geoPoint2 = new GeoPoint(rect2.top, rect2.right);
        Rect rect3 = this.N;
        GeoPoint geoPoint3 = new GeoPoint(rect3.bottom, rect3.right);
        Rect rect4 = this.N;
        o5[] a2 = a(new o5[]{s4Var.a(geoPoint), s4Var.a(geoPoint2), s4Var.a(geoPoint3), s4Var.a(new GeoPoint(rect4.bottom, rect4.left))});
        return a(new Rect((int) a2[0].a, (int) a2[0].b, (int) a2[1].a, (int) a2[1].b), str);
    }

    private Rect a(b[] bVarArr) {
        LatLng latLng = new LatLng(0.0d, 0.0d);
        double a2 = a(bVarArr, latLng);
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        return new Rect((int) ((d + a2) * 1000000.0d), (int) ((d2 + a2) * 1000000.0d), (int) ((d - a2) * 1000000.0d), (int) ((d2 - a2) * 1000000.0d));
    }

    private boolean a(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(b(d, d2, d3, d4, d5, d6)) < 1.0E-9d && (d - d3) * (d - d5) <= 0.0d && (d2 - d4) * (d2 - d6) <= 0.0d;
    }

    private boolean a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = d3 - d;
        double d10 = d8 - d6;
        double d11 = d4 - d2;
        double d12 = d7 - d5;
        double d13 = (d9 * d10) - (d11 * d12);
        if (d13 != 0.0d) {
            double d14 = d2 - d6;
            double d15 = d - d5;
            double d16 = ((d12 * d14) - (d10 * d15)) / d13;
            double d17 = ((d14 * d9) - (d15 * d11)) / d13;
            if (d16 >= 0.0d && d16 <= 1.0d && d17 >= 0.0d && d17 <= 1.0d) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x014e, code lost:
        if (r9 > r5) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016c, code lost:
        if (r5 > r9) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x018c, code lost:
        if (a(r11, r9, r7, r5, r33, r31, 180.0d, r31) != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x018e, code lost:
        r22 = r22 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(com.tencent.map.lib.models.GeoPoint r36) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.bf.a(com.tencent.map.lib.models.GeoPoint):boolean");
    }

    private boolean a(w5 w5Var) {
        List<LatLng> list = this.C;
        if (list == null || list.size() <= 2) {
            return false;
        }
        this.D = new w5[this.C.size() + 1];
        for (int i2 = 0; i2 < this.C.size(); i2++) {
            this.D[i2] = this.U.getProjection().a(this.C.get(i2));
            w5[] w5VarArr = this.D;
            w5VarArr[i2].setX(w5VarArr[i2].b() - w5Var.b());
            w5[] w5VarArr2 = this.D;
            w5VarArr2[i2].setY(w5VarArr2[i2].c() - w5Var.c());
        }
        this.D[this.C.size()] = this.U.getProjection().a(this.C.get(0));
        this.D[this.C.size()].setX(this.D[this.C.size()].b() - w5Var.b());
        this.D[this.C.size()].setY(this.D[this.C.size()].c() - w5Var.c());
        int i3 = (this.D[0].b() > 0.0d ? 1 : (this.D[0].b() == 0.0d ? 0 : -1));
        w5[] w5VarArr3 = this.D;
        int i4 = i3 >= 0 ? w5VarArr3[0].c() >= 0.0d ? 0 : 3 : w5VarArr3[0].c() >= 0.0d ? 1 : 2;
        int i5 = 1;
        int i6 = 0;
        while (true) {
            w5[] w5VarArr4 = this.D;
            if (i5 > w5VarArr4.length - 1 || (w5VarArr4[i5].b() == 0.0d && this.D[i5].c() == 0.0d)) {
                break;
            }
            int i7 = i5 - 1;
            int i8 = (((this.D[i5].c() * this.D[i7].b()) - (this.D[i5].b() * this.D[i7].c())) > 0.0d ? 1 : (((this.D[i5].c() * this.D[i7].b()) - (this.D[i5].b() * this.D[i7].c())) == 0.0d ? 0 : -1));
            if (i8 == 0 && this.D[i7].b() * this.D[i5].b() <= 0.0d && this.D[i7].c() * this.D[i5].c() <= 0.0d) {
                break;
            }
            int i9 = (this.D[i5].b() > 0.0d ? 1 : (this.D[i5].b() == 0.0d ? 0 : -1));
            w5[] w5VarArr5 = this.D;
            int i10 = i9 >= 0 ? w5VarArr5[i5].c() >= 0.0d ? 0 : 3 : w5VarArr5[i5].c() >= 0.0d ? 1 : 2;
            if (i10 == (i4 + 1) % 4) {
                i6++;
            } else if (i10 == (i4 + 3) % 4) {
                i6--;
            } else if (i10 == (i4 + 2) % 4) {
                i6 = i8 > 0 ? i6 + 2 : i6 - 2;
            }
            i5++;
            i4 = i10;
        }
        return i5 <= this.C.size() || i6 != 0;
    }

    private o5[] a(o5[] o5VarArr) {
        int length = o5VarArr.length;
        double d = o5VarArr[0].a;
        double d2 = o5VarArr[0].b;
        double d3 = o5VarArr[0].a;
        double d4 = o5VarArr[0].b;
        int i2 = 1;
        while (i2 < length) {
            double d5 = o5VarArr[i2].a;
            int i3 = i2;
            double d6 = o5VarArr[i2].b;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d3) {
                d3 = d5;
            }
            if (d6 < d2) {
                d2 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
            i2 = i3 + 1;
        }
        return new o5[]{new o5(d, d2), new o5(d3, d4)};
    }

    private double b(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    private boolean c(s4 s4Var) {
        PolygonOptions polygonOptions;
        Rect K;
        if (this.H == null || (polygonOptions = this.T) == null || !polygonOptions.isValid() || (K = K()) == null) {
            return false;
        }
        o5[] a2 = a(new o5[]{s4Var.a(new GeoPoint(K.top, K.left)), s4Var.a(new GeoPoint(K.top, K.right)), s4Var.a(new GeoPoint(K.bottom, K.right)), s4Var.a(new GeoPoint(K.bottom, K.left))});
        Rect rect = new Rect((int) a2[0].a, (int) a2[0].b, (int) a2[1].a, (int) a2[1].b);
        return Math.abs(rect.width()) > 5 && Math.abs(rect.height()) > 5;
    }

    private void d(s4 s4Var) {
        if (this.U == null) {
            return;
        }
        String str = this.V;
        if (e7.b(str)) {
            return;
        }
        synchronized (this.J) {
            if (this.K == null) {
                this.K = new c();
            }
        }
        a(str, s4Var);
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        qc qcVar = this.U;
        if (qcVar == null) {
            return;
        }
        s4 projection = qcVar.getProjection();
        if (!isVisible()) {
            qcVar.c(this.H.polygonId);
            this.H.polygonId = -1;
        } else if (c(projection)) {
            if (e7.b(this.V)) {
                n();
            }
            J();
            hg hgVar = this.I;
            if (hgVar != null) {
                hgVar.E();
                this.L = this.I.N() + "";
                if (this.Q) {
                    return;
                }
                qcVar.a(this.I.N(), p());
                this.Q = true;
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        qc qcVar = this.U;
        if (qcVar == null) {
            return;
        }
        qcVar.h().b(this);
        PolygonInfo polygonInfo = this.H;
        if (polygonInfo != null) {
            this.U.c(polygonInfo.polygonId);
        }
        List<GeoPoint> list = this.B;
        if (list != null) {
            list.clear();
        }
        synchronized (this.J) {
            c cVar = this.K;
            if (cVar != null) {
                cVar.a();
                this.K = null;
            }
        }
        if (this.I != null) {
            this.U.g().d(this.I);
            this.U.K();
            this.I = null;
        }
        this.P = false;
    }

    public void J() {
        PolygonInfo polygonInfo = this.H;
        if (-1 == polygonInfo.polygonId) {
            polygonInfo.polygonId = this.U.a(polygonInfo);
        } else if (C()) {
            this.U.b(this.H);
        }
    }

    public Rect K() {
        Rect rect = this.F;
        if (rect != null) {
            return rect;
        }
        List<GeoPoint> list = this.B;
        if (list == null || list.isEmpty() || this.B.size() < 3) {
            return null;
        }
        GeoPoint geoPoint = this.B.get(0);
        int latitudeE6 = geoPoint.getLatitudeE6();
        int longitudeE6 = geoPoint.getLongitudeE6();
        int size = this.B.size();
        int i2 = latitudeE6;
        int i3 = i2;
        int i4 = longitudeE6;
        for (int i5 = 1; i5 < size; i5++) {
            GeoPoint geoPoint2 = this.B.get(i5);
            int latitudeE62 = geoPoint2.getLatitudeE6();
            int longitudeE62 = geoPoint2.getLongitudeE6();
            longitudeE6 = Math.min(longitudeE6, longitudeE62);
            i4 = Math.max(i4, longitudeE62);
            i2 = Math.max(i2, latitudeE62);
            i3 = Math.min(i3, latitudeE62);
        }
        Rect rect2 = new Rect(longitudeE6, i2, i4, i3);
        this.F = rect2;
        return rect2;
    }

    public Rect M() {
        Rect rect = this.G;
        if (rect != null) {
            return rect;
        }
        List<GeoPoint> list = this.B;
        if (list == null || list.isEmpty() || this.B.size() < 3) {
            return null;
        }
        w5 a2 = this.U.getProjection().a(this.B.get(0).toLatLng());
        int x = (int) a2.x();
        int x2 = (int) a2.x();
        int y = (int) a2.y();
        int y2 = (int) a2.y();
        int size = this.B.size();
        for (int i2 = 1; i2 < size; i2++) {
            w5 a3 = this.U.getProjection().a(this.B.get(i2).toLatLng());
            x = (int) Math.min(x, a3.x());
            x2 = (int) Math.max(x2, a3.x());
            y = (int) Math.min(y, a3.y());
            y2 = (int) Math.max(y2, a3.y());
        }
        Rect rect2 = new Rect(x, y, x2, y2);
        this.G = rect2;
        return rect2;
    }

    public Rect N() {
        if (this.U != null) {
            w5 a2 = this.U.getProjection().a(this.U.getProjection().a(new o5(0.0d, 0.0d)).toLatLng());
            w5 a3 = this.U.getProjection().a(this.U.getProjection().a(new o5(this.U.e().width(), this.U.e().height())).toLatLng());
            w5 a4 = this.U.getProjection().a(this.U.getProjection().a(new o5(0.0d, this.U.e().height())).toLatLng());
            w5 a5 = this.U.getProjection().a(this.U.getProjection().a(new o5(this.U.e().width(), 0.0d)).toLatLng());
            return new Rect((int) Math.min(Math.min(Math.min(a2.x(), a4.x()), a3.x()), a5.x()), (int) Math.min(Math.min(Math.min(a2.y(), a4.y()), a3.y()), a5.y()), (int) Math.max(Math.max(Math.max(a2.x(), a4.x()), a3.x()), a5.x()), (int) Math.max(Math.max(Math.max(a2.y(), a4.y()), a3.y()), a5.y()));
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: O  reason: merged with bridge method [inline-methods] */
    public q0 x() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        Rect rect = new Rect();
        new Rect();
        if (this.H != null) {
            rect = K();
        }
        hg hgVar = this.I;
        if (hgVar != null) {
            Rect bound = hgVar.getBound(s4Var);
            rect.left = Math.min(rect.left, bound.left);
            rect.top = Math.min(rect.top, bound.top);
            rect.right = Math.max(rect.right, bound.right);
            rect.bottom = Math.max(rect.bottom, bound.bottom);
        }
        return rect;
    }

    @Override // com.tencent.mapsdk.internal.g5
    public void a(y5 y5Var) {
        qc qcVar;
        if (y5Var == y5.NO_CHANGED || (qcVar = this.U) == null) {
            return;
        }
        d(qcVar.getProjection());
    }

    @Override // com.tencent.mapsdk.internal.q0
    public void a(PolygonOptions polygonOptions) {
        if (polygonOptions == null) {
            return;
        }
        setFillColor(polygonOptions.getFillColor());
        setStrokeColor(polygonOptions.getStrokeColor());
        setStrokeWidth(polygonOptions.getStrokeWidth());
        setZIndex(polygonOptions.getZIndex());
        setVisible(polygonOptions.isVisible());
        setLevel(polygonOptions.getLevel());
        setClickable(polygonOptions.isClickable());
        setPoints(polygonOptions.getPoints());
        List<Integer> pattern = polygonOptions.getPattern();
        if (pattern != null && !pattern.isEmpty()) {
            int[] iArr = new int[pattern.size()];
            for (int i2 = 0; i2 < pattern.size(); i2++) {
                iArr[i2] = pattern.get(i2).intValue();
            }
            this.H.pattern = iArr;
        }
        BitmapDescriptor texture = polygonOptions.getTexture();
        if (this.W != texture) {
            this.W = texture;
            texture.getBitmap(this.U.getContext());
            this.H.textureName = this.W.getFormater().getBitmapId();
        }
        this.H.textureSpacing = polygonOptions.getTextureSpacing();
        B();
    }

    public void a(List<GeoPoint> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<GeoPoint> list2 = this.B;
        if (list2 == null) {
            this.B = new ArrayList();
        } else {
            list2.clear();
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            GeoPoint geoPoint = list.get(i2);
            if (geoPoint != null) {
                B();
                this.B.add(geoPoint);
            }
        }
    }

    public boolean a(Rect rect) {
        if (this.G == null || rect == null) {
            return false;
        }
        Rect rect2 = this.G;
        double abs = Math.abs(((rect2.top + rect2.bottom) / 2) - ((rect.top + rect.bottom) / 2));
        Rect rect3 = this.G;
        double abs2 = Math.abs(rect3.left - rect3.right) + Math.abs(rect.left - rect.right);
        Rect rect4 = this.G;
        double abs3 = Math.abs(rect4.top - rect4.bottom) + Math.abs(rect.top - rect.bottom);
        Double.isNaN(abs2);
        if (Math.abs(((r0.left + r0.right) / 2) - ((rect.left + rect.right) / 2)) <= abs2 / 2.0d) {
            Double.isNaN(abs3);
            return abs <= abs3 / 2.0d;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        Rect bound = getBound(s4Var);
        int i2 = bound.left;
        int i3 = bound.right;
        int i4 = bound.top;
        int i5 = bound.bottom;
        GeoPoint geoPoint = new GeoPoint(i4, i2);
        GeoPoint geoPoint2 = new GeoPoint(i5, i2);
        GeoPoint geoPoint3 = new GeoPoint(i5, i3);
        GeoPoint geoPoint4 = new GeoPoint(i4, i3);
        o5 a2 = s4Var.a(geoPoint);
        o5 a3 = s4Var.a(geoPoint2);
        o5 a4 = s4Var.a(geoPoint3);
        o5 a5 = s4Var.a(geoPoint4);
        return new Rect((int) Math.min(Math.min(a2.a, a3.a), Math.min(a4.a, a5.a)), (int) Math.min(Math.min(a2.b, a3.b), Math.min(a4.b, a5.b)), (int) Math.max(Math.max(a2.a, a3.a), Math.max(a4.a, a5.a)), (int) Math.max(Math.max(a2.b, a3.b), Math.max(a4.b, a5.b)));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public boolean contains(LatLng latLng) {
        List<LatLng> points = getPoints();
        if (points == null || points.size() < 3 || latLng == null) {
            return false;
        }
        int size = points.size() - 1;
        for (int i2 = 0; i2 < points.size(); i2++) {
            if (points.get(i2).equals(latLng)) {
                return true;
            }
        }
        int i3 = size;
        boolean z = false;
        for (int i4 = 0; i4 < points.size(); i4++) {
            if (((points.get(i4).latitude < latLng.latitude && points.get(i3).latitude >= latLng.latitude) || (points.get(i3).latitude < latLng.latitude && points.get(i4).latitude >= latLng.latitude)) && (points.get(i4).longitude <= latLng.longitude || points.get(i3).longitude <= latLng.longitude)) {
                z ^= points.get(i4).longitude + (((latLng.latitude - points.get(i4).latitude) / (points.get(i3).latitude - points.get(i4).latitude)) * (points.get(i3).longitude - points.get(i4).longitude)) <= latLng.longitude;
            }
            i3 = i4;
        }
        return z;
    }

    @Override // com.tencent.mapsdk.internal.q0
    public PolygonInfo e() {
        return this.H;
    }

    @Override // com.tencent.mapsdk.internal.q0
    public GeoPoint getCenter() {
        Rect K = K();
        return new GeoPoint(K.centerY(), K.centerX());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public List<LatLng> getPoints() {
        List<LatLng> list = this.C;
        return list != null ? list : ea.a(this.B);
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean handleOnTap() {
        return true;
    }

    @Override // com.tencent.mapsdk.internal.q0
    public List<GeoPoint> i() {
        ArrayList arrayList = new ArrayList(49);
        Rect K = K();
        int i2 = K.left;
        int i3 = K.top;
        int abs = Math.abs(K.width());
        int abs2 = Math.abs(K.height());
        double d = i2;
        double d2 = abs;
        Double.isNaN(d2);
        Double.isNaN(d);
        double d3 = d - (d2 * 0.125d);
        double d4 = i3;
        double d5 = abs2;
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d6 = d4 - (0.125d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d7 = d - (d2 * 0.25d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d8 = d4 - (0.25d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d9 = d - (d2 * 0.375d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d10 = d4 - (0.375d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d11 = d - (d2 * 0.5d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d12 = d4 - (0.5d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d13 = d - (d2 * 0.625d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d14 = d4 - (0.625d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d15 = d - (d2 * 0.75d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d16 = d4 - (0.75d * d5);
        Double.isNaN(d2);
        Double.isNaN(d);
        double d17 = d - (d2 * 0.825d);
        Double.isNaN(d5);
        Double.isNaN(d4);
        double d18 = d4 - (d5 * 0.825d);
        int i4 = (int) d6;
        int i5 = (int) d3;
        GeoPoint geoPoint = new GeoPoint(new GeoPoint(i4, i5));
        if (a(geoPoint)) {
            arrayList.add(geoPoint);
        }
        int i6 = (int) d8;
        GeoPoint geoPoint2 = new GeoPoint(new GeoPoint(i6, i5));
        if (a(geoPoint2)) {
            arrayList.add(geoPoint2);
        }
        int i7 = (int) d10;
        GeoPoint geoPoint3 = new GeoPoint(new GeoPoint(i7, i5));
        if (a(geoPoint3)) {
            arrayList.add(geoPoint3);
        }
        int i8 = (int) d12;
        GeoPoint geoPoint4 = new GeoPoint(new GeoPoint(i8, i5));
        if (a(geoPoint4)) {
            arrayList.add(geoPoint4);
        }
        int i9 = (int) d14;
        GeoPoint geoPoint5 = new GeoPoint(new GeoPoint(i9, i5));
        if (a(geoPoint5)) {
            arrayList.add(geoPoint5);
        }
        int i10 = (int) d16;
        GeoPoint geoPoint6 = new GeoPoint(new GeoPoint(i10, i5));
        if (a(geoPoint6)) {
            arrayList.add(geoPoint6);
        }
        int i11 = (int) d18;
        GeoPoint geoPoint7 = new GeoPoint(new GeoPoint(i11, i5));
        if (a(geoPoint7)) {
            arrayList.add(geoPoint7);
        }
        int i12 = (int) d7;
        GeoPoint geoPoint8 = new GeoPoint(new GeoPoint(i4, i12));
        if (a(geoPoint8)) {
            arrayList.add(geoPoint8);
        }
        GeoPoint geoPoint9 = new GeoPoint(new GeoPoint(i6, i12));
        if (a(geoPoint9)) {
            arrayList.add(geoPoint9);
        }
        GeoPoint geoPoint10 = new GeoPoint(new GeoPoint(i7, i12));
        if (a(geoPoint10)) {
            arrayList.add(geoPoint10);
        }
        GeoPoint geoPoint11 = new GeoPoint(new GeoPoint(i8, i12));
        if (a(geoPoint11)) {
            arrayList.add(geoPoint11);
        }
        GeoPoint geoPoint12 = new GeoPoint(new GeoPoint(i9, i12));
        if (a(geoPoint12)) {
            arrayList.add(geoPoint12);
        }
        GeoPoint geoPoint13 = new GeoPoint(new GeoPoint(i10, i12));
        if (a(geoPoint13)) {
            arrayList.add(geoPoint13);
        }
        GeoPoint geoPoint14 = new GeoPoint(new GeoPoint(i11, i12));
        if (a(geoPoint14)) {
            arrayList.add(geoPoint14);
        }
        int i13 = (int) d9;
        GeoPoint geoPoint15 = new GeoPoint(new GeoPoint(i4, i13));
        if (a(geoPoint15)) {
            arrayList.add(geoPoint15);
        }
        GeoPoint geoPoint16 = new GeoPoint(new GeoPoint(i6, i13));
        if (a(geoPoint16)) {
            arrayList.add(geoPoint16);
        }
        GeoPoint geoPoint17 = new GeoPoint(new GeoPoint(i7, i13));
        if (a(geoPoint17)) {
            arrayList.add(geoPoint17);
        }
        GeoPoint geoPoint18 = new GeoPoint(new GeoPoint(i8, i13));
        if (a(geoPoint18)) {
            arrayList.add(geoPoint18);
        }
        GeoPoint geoPoint19 = new GeoPoint(new GeoPoint(i9, i13));
        if (a(geoPoint19)) {
            arrayList.add(geoPoint19);
        }
        GeoPoint geoPoint20 = new GeoPoint(new GeoPoint(i10, i13));
        if (a(geoPoint20)) {
            arrayList.add(geoPoint20);
        }
        GeoPoint geoPoint21 = new GeoPoint(new GeoPoint(i11, i13));
        if (a(geoPoint21)) {
            arrayList.add(geoPoint21);
        }
        int i14 = (int) d11;
        GeoPoint geoPoint22 = new GeoPoint(new GeoPoint(i4, i14));
        if (a(geoPoint22)) {
            arrayList.add(geoPoint22);
        }
        GeoPoint geoPoint23 = new GeoPoint(new GeoPoint(i6, i14));
        if (a(geoPoint23)) {
            arrayList.add(geoPoint23);
        }
        GeoPoint geoPoint24 = new GeoPoint(new GeoPoint(i7, i14));
        if (a(geoPoint24)) {
            arrayList.add(geoPoint24);
        }
        GeoPoint geoPoint25 = new GeoPoint(new GeoPoint(i8, i14));
        if (a(geoPoint25)) {
            arrayList.add(geoPoint25);
        }
        GeoPoint geoPoint26 = new GeoPoint(new GeoPoint(i9, i14));
        if (a(geoPoint26)) {
            arrayList.add(geoPoint26);
        }
        GeoPoint geoPoint27 = new GeoPoint(new GeoPoint(i10, i14));
        if (a(geoPoint27)) {
            arrayList.add(geoPoint27);
        }
        GeoPoint geoPoint28 = new GeoPoint(new GeoPoint(i11, i14));
        if (a(geoPoint28)) {
            arrayList.add(geoPoint28);
        }
        int i15 = (int) d13;
        GeoPoint geoPoint29 = new GeoPoint(new GeoPoint(i4, i15));
        if (a(geoPoint29)) {
            arrayList.add(geoPoint29);
        }
        GeoPoint geoPoint30 = new GeoPoint(new GeoPoint(i6, i15));
        if (a(geoPoint30)) {
            arrayList.add(geoPoint30);
        }
        GeoPoint geoPoint31 = new GeoPoint(new GeoPoint(i7, i15));
        if (a(geoPoint31)) {
            arrayList.add(geoPoint31);
        }
        GeoPoint geoPoint32 = new GeoPoint(new GeoPoint(i8, i15));
        if (a(geoPoint32)) {
            arrayList.add(geoPoint32);
        }
        GeoPoint geoPoint33 = new GeoPoint(new GeoPoint(i9, i15));
        if (a(geoPoint33)) {
            arrayList.add(geoPoint33);
        }
        GeoPoint geoPoint34 = new GeoPoint(new GeoPoint(i10, i15));
        if (a(geoPoint34)) {
            arrayList.add(geoPoint34);
        }
        GeoPoint geoPoint35 = new GeoPoint(new GeoPoint(i11, i15));
        if (a(geoPoint35)) {
            arrayList.add(geoPoint35);
        }
        int i16 = (int) d15;
        GeoPoint geoPoint36 = new GeoPoint(new GeoPoint(i4, i16));
        if (a(geoPoint36)) {
            arrayList.add(geoPoint36);
        }
        GeoPoint geoPoint37 = new GeoPoint(new GeoPoint(i6, i16));
        if (a(geoPoint37)) {
            arrayList.add(geoPoint37);
        }
        GeoPoint geoPoint38 = new GeoPoint(new GeoPoint(i7, i16));
        if (a(geoPoint38)) {
            arrayList.add(geoPoint38);
        }
        GeoPoint geoPoint39 = new GeoPoint(new GeoPoint(i8, i16));
        if (a(geoPoint39)) {
            arrayList.add(geoPoint39);
        }
        GeoPoint geoPoint40 = new GeoPoint(new GeoPoint(i9, i16));
        if (a(geoPoint40)) {
            arrayList.add(geoPoint40);
        }
        GeoPoint geoPoint41 = new GeoPoint(new GeoPoint(i10, i16));
        if (a(geoPoint41)) {
            arrayList.add(geoPoint41);
        }
        GeoPoint geoPoint42 = new GeoPoint(new GeoPoint(i11, i16));
        if (a(geoPoint42)) {
            arrayList.add(geoPoint42);
        }
        int i17 = (int) d17;
        GeoPoint geoPoint43 = new GeoPoint(new GeoPoint(i4, i17));
        if (a(geoPoint43)) {
            arrayList.add(geoPoint43);
        }
        GeoPoint geoPoint44 = new GeoPoint(new GeoPoint(i6, i17));
        if (a(geoPoint44)) {
            arrayList.add(geoPoint44);
        }
        GeoPoint geoPoint45 = new GeoPoint(new GeoPoint(i7, i17));
        if (a(geoPoint45)) {
            arrayList.add(geoPoint45);
        }
        GeoPoint geoPoint46 = new GeoPoint(new GeoPoint(i8, i17));
        if (a(geoPoint46)) {
            arrayList.add(geoPoint46);
        }
        GeoPoint geoPoint47 = new GeoPoint(new GeoPoint(i9, i17));
        if (a(geoPoint47)) {
            arrayList.add(geoPoint47);
        }
        GeoPoint geoPoint48 = new GeoPoint(new GeoPoint(i10, i17));
        if (a(geoPoint48)) {
            arrayList.add(geoPoint48);
        }
        GeoPoint geoPoint49 = new GeoPoint(new GeoPoint(i11, i17));
        if (a(geoPoint49)) {
            arrayList.add(geoPoint49);
        }
        return arrayList;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public boolean isSelected() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public void n() {
        List<GeoPoint> list;
        if (this.U == null) {
            return;
        }
        if ((this.H.polygonId < 0 || C()) && (list = this.B) != null && list.size() > 2) {
            PolygonInfo polygonInfo = this.H;
            polygonInfo.color = this.f17511k;
            polygonInfo.borderColor = this.f17512l;
            polygonInfo.borderWidth = this.f17510j;
            polygonInfo.zIndex = getZIndex();
            this.H.level = getLevel();
            int size = this.B.size();
            this.H.points = new LatLng[size];
            for (int i2 = 0; i2 < size; i2++) {
                this.H.points[i2] = this.B.get(i2).toLatLng();
            }
            this.U.w0();
            B();
        }
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        if (this.U == null || !isVisible()) {
            return false;
        }
        M();
        if (a(N())) {
            return a(this.U.getProjection().a(this.U.getProjection().a(new o5(f2, f3)).toLatLng()));
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.q0
    public int p() {
        PolygonInfo polygonInfo = this.H;
        if (polygonInfo == null) {
            return -1;
        }
        return polygonInfo.polygonId;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public void setOptions(PolygonOptions polygonOptions) {
        a(polygonOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public void setPoints(List<LatLng> list) {
        int size;
        GeoPoint from;
        this.C = list;
        if (list != null && (size = list.size()) > 0) {
            PolygonOptions polygonOptions = this.T;
            if (polygonOptions != null) {
                polygonOptions.setPoints(new ArrayList(list));
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < size; i2++) {
                LatLng latLng = list.get(i2);
                if (latLng != null && (from = GeoPoint.from(latLng)) != null) {
                    arrayList.add(from);
                }
            }
            a(arrayList);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelected(boolean z) {
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelectedListener(Selectable.OnSelectedListener onSelectedListener) {
    }
}
