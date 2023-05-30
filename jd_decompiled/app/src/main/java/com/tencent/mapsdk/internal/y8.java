package com.tencent.mapsdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.tencent.mapsdk.internal.j8;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class y8 extends j8 {
    public static final int I = 1;
    public static final int J = 2;
    public static final int K = -1;
    public static final int v = 0;
    public static final int w = 1;
    public static final int x = 0;
    public static final int y = 1;
    public static final int z = 2;
    public long b;

    /* renamed from: h  reason: collision with root package name */
    private long f17494h;
    public u8[] r;
    public HashMap<Integer, u8> s;
    public g8 t;
    private static ThreadLocal<f> A = new ThreadLocal<>();
    private static final ThreadLocal<ArrayList<y8>> B = new a();
    private static final ThreadLocal<ArrayList<y8>> C = new b();
    private static final ThreadLocal<ArrayList<y8>> D = new c();
    private static final ThreadLocal<ArrayList<y8>> E = new d();
    private static final ThreadLocal<ArrayList<y8>> F = new e();
    private static final Interpolator G = new LinearInterpolator();
    private static final long u = 10;
    private static long H = u;

    /* renamed from: c  reason: collision with root package name */
    public long f17490c = -1;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f17491e = 0;

    /* renamed from: f  reason: collision with root package name */
    private float f17492f = 0.0f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17493g = false;

    /* renamed from: i  reason: collision with root package name */
    public int f17495i = 0;

    /* renamed from: j  reason: collision with root package name */
    private boolean f17496j = false;

    /* renamed from: k  reason: collision with root package name */
    private boolean f17497k = false;

    /* renamed from: l  reason: collision with root package name */
    private long f17498l = 300;

    /* renamed from: m  reason: collision with root package name */
    private long f17499m = 0;

    /* renamed from: n  reason: collision with root package name */
    private int f17500n = 0;
    private int o = 1;
    private Interpolator p = G;
    private ArrayList<g> q = null;

    /* loaded from: classes9.dex */
    public static class a extends ThreadLocal<ArrayList<y8>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ArrayList<y8> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends ThreadLocal<ArrayList<y8>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ArrayList<y8> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends ThreadLocal<ArrayList<y8>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ArrayList<y8> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends ThreadLocal<ArrayList<y8>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ArrayList<y8> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes9.dex */
    public static class e extends ThreadLocal<ArrayList<y8>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ArrayList<y8> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes9.dex */
    public static class f extends Handler {
        public f(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            ArrayList arrayList = (ArrayList) y8.B.get();
            ArrayList arrayList2 = (ArrayList) y8.D.get();
            int i2 = message.what;
            if (i2 == 0) {
                ArrayList arrayList3 = (ArrayList) y8.C.get();
                z = arrayList.size() <= 0 && arrayList2.size() <= 0;
                while (arrayList3.size() > 0) {
                    ArrayList arrayList4 = (ArrayList) arrayList3.clone();
                    arrayList3.clear();
                    int size = arrayList4.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        y8 y8Var = (y8) arrayList4.get(i3);
                        if (y8Var.f17499m == 0) {
                            y8Var.D();
                        } else {
                            arrayList2.add(y8Var);
                        }
                    }
                }
            } else if (i2 != 1) {
                return;
            } else {
                z = true;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            ArrayList arrayList5 = (ArrayList) y8.F.get();
            ArrayList arrayList6 = (ArrayList) y8.E.get();
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                y8 y8Var2 = (y8) arrayList2.get(i4);
                if (y8Var2.d(uptimeMillis)) {
                    arrayList5.add(y8Var2);
                }
            }
            int size3 = arrayList5.size();
            if (size3 > 0) {
                for (int i5 = 0; i5 < size3; i5++) {
                    y8 y8Var3 = (y8) arrayList5.get(i5);
                    y8Var3.D();
                    y8Var3.f17496j = true;
                    arrayList2.remove(y8Var3);
                }
                arrayList5.clear();
            }
            int size4 = arrayList.size();
            int i6 = 0;
            while (i6 < size4) {
                y8 y8Var4 = (y8) arrayList.get(i6);
                if (y8Var4.c(uptimeMillis)) {
                    arrayList6.add(y8Var4);
                }
                if (arrayList.size() == size4) {
                    i6++;
                } else {
                    size4--;
                    arrayList6.remove(y8Var4);
                }
            }
            if (arrayList6.size() > 0) {
                for (int i7 = 0; i7 < arrayList6.size(); i7++) {
                    ((y8) arrayList6.get(i7)).r();
                }
                arrayList6.clear();
            }
            if (z) {
                if (arrayList.isEmpty() && arrayList2.isEmpty()) {
                    return;
                }
                sendEmptyMessageDelayed(1, Math.max(0L, y8.H - (SystemClock.uptimeMillis() - uptimeMillis)));
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface g {
        void a(y8 y8Var);
    }

    public y8(g8 g8Var) {
        this.t = g8Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        ArrayList<j8.a> arrayList;
        B.get().add(this);
        if (this.f17499m <= 0 || (arrayList = this.a) == null) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((j8.a) arrayList2.get(i2)).d(this);
        }
    }

    public static y8 a(g8 g8Var, x8<?> x8Var, Object[] objArr) {
        y8 y8Var = new y8(g8Var);
        y8Var.a(objArr);
        y8Var.a(x8Var);
        return y8Var;
    }

    public static y8 a(g8 g8Var, double... dArr) {
        y8 y8Var = new y8(g8Var);
        y8Var.a(dArr);
        return y8Var;
    }

    public static y8 a(g8 g8Var, int... iArr) {
        y8 y8Var = new y8(g8Var);
        y8Var.a(iArr);
        return y8Var;
    }

    public static y8 a(g8 g8Var, u8... u8VarArr) {
        y8 y8Var = new y8(g8Var);
        y8Var.a(u8VarArr);
        return y8Var;
    }

    private void a(boolean z2) {
        this.d = z2;
        this.f17491e = 0;
        this.f17495i = 0;
        this.f17497k = true;
        this.f17493g = false;
        C.get().add(this);
        if (this.f17499m == 0) {
            e(v());
            this.f17495i = 0;
            this.f17496j = true;
            ArrayList<j8.a> arrayList = this.a;
            if (arrayList != null) {
                ArrayList arrayList2 = (ArrayList) arrayList.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((j8.a) arrayList2.get(i2)).d(this);
                }
            }
        }
        f fVar = A.get();
        if (fVar == null) {
            fVar = new f(Looper.getMainLooper());
            A.set(fVar);
        } else {
            fVar.removeMessages(0);
            fVar.removeMessages(1);
        }
        fVar.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(long j2) {
        if (!this.f17493g) {
            this.f17493g = true;
            this.f17494h = j2;
            return false;
        }
        long j3 = j2 - this.f17494h;
        long j4 = this.f17499m;
        if (j3 > j4) {
            this.b = j2 - (j3 - j4);
            this.f17495i = 1;
            return true;
        }
        return false;
    }

    public static void f(long j2) {
        H = j2;
    }

    public static void q() {
        B.get().clear();
        C.get().clear();
        D.get().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ArrayList<j8.a> arrayList;
        B.get().remove(this);
        C.get().remove(this);
        D.get().remove(this);
        this.f17495i = 0;
        if (this.f17496j && (arrayList = this.a) != null) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((j8.a) arrayList2.get(i2)).c(this);
            }
        }
        this.f17496j = false;
        this.f17497k = false;
    }

    public static int u() {
        return B.get().size();
    }

    public static long w() {
        return H;
    }

    public u8[] A() {
        return this.r;
    }

    public void B() {
        ArrayList<g> arrayList = this.q;
        if (arrayList == null) {
            return;
        }
        arrayList.clear();
        this.q = null;
    }

    public void C() {
        this.d = !this.d;
        if (this.f17495i != 1) {
            a(true);
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        this.b = uptimeMillis - (this.f17498l - (uptimeMillis - this.b));
    }

    @Override // com.tencent.mapsdk.internal.j8
    public j8 a(long j2) {
        if (j2 >= 0) {
            this.f17498l = j2;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j2);
    }

    public Object a(int i2) {
        u8 u8Var = this.s.get(Integer.valueOf(i2));
        if (u8Var != null) {
            return u8Var.b();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void a() {
        ArrayList<j8.a> arrayList;
        if (this.f17495i != 0 || C.get().contains(this) || D.get().contains(this)) {
            if (this.f17496j && (arrayList = this.a) != null) {
                Iterator it = ((ArrayList) arrayList.clone()).iterator();
                while (it.hasNext()) {
                    ((j8.a) it.next()).b(this);
                }
            }
            r();
        }
    }

    public void a(float f2) {
        float interpolation = this.p.getInterpolation(f2);
        this.f17492f = interpolation;
        int length = this.r.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.r[i2].a(interpolation);
            g8 g8Var = this.t;
            if (g8Var != null) {
                u8[] u8VarArr = this.r;
                g8Var.a(u8VarArr[i2].a, u8VarArr[i2].b());
            }
        }
        ArrayList<g> arrayList = this.q;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.q.get(i3).a(this);
            }
        }
    }

    public void a(int i2, double d2) {
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void a(Interpolator interpolator) {
        if (interpolator == null) {
            interpolator = new LinearInterpolator();
        }
        this.p = interpolator;
    }

    public void a(x8<?> x8Var) {
        u8[] u8VarArr;
        if (x8Var == null || (u8VarArr = this.r) == null || u8VarArr.length <= 0) {
            return;
        }
        u8VarArr[0].a(x8Var);
    }

    public void a(g gVar) {
        if (this.q == null) {
            this.q = new ArrayList<>();
        }
        this.q.add(gVar);
    }

    public void a(double... dArr) {
        if (dArr == null || dArr.length == 0) {
            return;
        }
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(0, dArr));
        } else {
            u8VarArr[0].a(dArr);
        }
    }

    public void a(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            return;
        }
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(0, iArr));
        } else {
            u8VarArr[0].a(iArr);
        }
    }

    public void a(u8... u8VarArr) {
        int length = u8VarArr.length;
        this.r = u8VarArr;
        this.s = new HashMap<>(length);
        for (u8 u8Var : u8VarArr) {
            this.s.put(Integer.valueOf(u8Var.c()), u8Var);
        }
    }

    public void a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return;
        }
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(0, null, objArr));
        } else {
            u8VarArr[0].a(objArr);
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    /* renamed from: b */
    public j8 clone() {
        y8 y8Var = (y8) super.clone();
        ArrayList<g> arrayList = this.q;
        if (arrayList != null) {
            y8Var.q = new ArrayList<>();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                y8Var.q.add(arrayList.get(i2));
            }
        }
        y8Var.f17490c = -1L;
        y8Var.d = false;
        y8Var.f17491e = 0;
        y8Var.f17495i = 0;
        y8Var.f17493g = false;
        u8[] u8VarArr = this.r;
        if (u8VarArr != null) {
            int length = u8VarArr.length;
            y8Var.r = new u8[length];
            y8Var.s = new HashMap<>(length);
            for (int i3 = 0; i3 < length; i3++) {
                u8 clone = u8VarArr[i3].clone();
                y8Var.r[i3] = clone;
                y8Var.s.put(Integer.valueOf(clone.c()), clone);
            }
        }
        return y8Var;
    }

    public void b(int i2) {
        this.f17500n = i2;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void b(long j2) {
        this.f17499m = j2;
    }

    public void b(g gVar) {
        ArrayList<g> arrayList = this.q;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(gVar);
        if (this.q.size() == 0) {
            this.q = null;
        }
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void c() {
        if (!B.get().contains(this) && !C.get().contains(this)) {
            this.f17493g = false;
            D();
        }
        int i2 = this.f17500n;
        a((i2 <= 0 || (i2 & 1) != 1) ? 1.0f : 0.0f);
        r();
    }

    public void c(int i2) {
        this.o = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(long r10) {
        /*
            r9 = this;
            int r0 = r9.f17495i
            r1 = 0
            r3 = 1
            if (r0 != 0) goto L1a
            r9.f17495i = r3
            long r4 = r9.f17490c
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L12
            r9.b = r10
            goto L1a
        L12:
            long r4 = r10 - r4
            r9.b = r4
            r4 = -1
            r9.f17490c = r4
        L1a:
            int r0 = r9.f17495i
            r4 = 2
            r5 = 0
            if (r0 == r3) goto L23
            if (r0 == r4) goto L23
            goto L82
        L23:
            long r6 = r9.f17498l
            r0 = 1065353216(0x3f800000, float:1.0)
            int r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r8 <= 0) goto L32
            long r1 = r9.b
            long r10 = r10 - r1
            float r10 = (float) r10
            float r11 = (float) r6
            float r10 = r10 / r11
            goto L34
        L32:
            r10 = 1065353216(0x3f800000, float:1.0)
        L34:
            int r11 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r11 < 0) goto L77
            int r11 = r9.f17491e
            int r1 = r9.f17500n
            if (r11 < r1) goto L47
            r11 = -1
            if (r1 != r11) goto L42
            goto L47
        L42:
            float r10 = java.lang.Math.min(r10, r0)
            goto L78
        L47:
            java.util.ArrayList<com.tencent.mapsdk.internal.j8$a> r11 = r9.a
            if (r11 == 0) goto L60
            int r11 = r11.size()
            r1 = 0
        L50:
            if (r1 >= r11) goto L60
            java.util.ArrayList<com.tencent.mapsdk.internal.j8$a> r2 = r9.a
            java.lang.Object r2 = r2.get(r1)
            com.tencent.mapsdk.internal.j8$a r2 = (com.tencent.mapsdk.internal.j8.a) r2
            r2.a(r9)
            int r1 = r1 + 1
            goto L50
        L60:
            int r11 = r9.o
            if (r11 != r4) goto L69
            boolean r11 = r9.d
            r11 = r11 ^ r3
            r9.d = r11
        L69:
            int r11 = r9.f17491e
            int r1 = (int) r10
            int r11 = r11 + r1
            r9.f17491e = r11
            float r10 = r10 % r0
            long r1 = r9.b
            long r3 = r9.f17498l
            long r1 = r1 + r3
            r9.b = r1
        L77:
            r3 = 0
        L78:
            boolean r11 = r9.d
            if (r11 == 0) goto L7e
            float r10 = r0 - r10
        L7e:
            r9.a(r10)
            r5 = r3
        L82:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.y8.c(long):boolean");
    }

    @Override // com.tencent.mapsdk.internal.j8
    public long d() {
        return this.f17498l;
    }

    public void e(long j2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.f17495i != 1) {
            this.f17490c = j2;
            this.f17495i = 2;
        }
        this.b = uptimeMillis - j2;
        c(uptimeMillis);
    }

    @Override // com.tencent.mapsdk.internal.j8
    public long f() {
        return this.f17499m;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public boolean g() {
        return this.f17495i == 1 || this.f17496j;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public boolean h() {
        return this.f17497k;
    }

    @Override // com.tencent.mapsdk.internal.j8
    public void j() {
        a(false);
    }

    public float s() {
        return this.f17492f;
    }

    public Object t() {
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length <= 0) {
            return null;
        }
        return u8VarArr[0].b();
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.r != null) {
            for (int i2 = 0; i2 < this.r.length; i2++) {
                str = str + "\n    " + this.r[i2].toString();
            }
        }
        return str;
    }

    public long v() {
        if (this.f17495i == 0) {
            return 0L;
        }
        return SystemClock.uptimeMillis() - this.b;
    }

    public Interpolator x() {
        return this.p;
    }

    public int y() {
        return this.f17500n;
    }

    public int z() {
        return this.o;
    }
}
