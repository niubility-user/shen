package com.caverock.androidsvg;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Base64;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.b;
import com.caverock.androidsvg.f;
import com.caverock.androidsvg.h;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i {

    /* renamed from: i  reason: collision with root package name */
    private static HashSet<String> f915i;
    private Canvas a;
    private float b;

    /* renamed from: c  reason: collision with root package name */
    private com.caverock.androidsvg.h f916c;
    private h d;

    /* renamed from: e  reason: collision with root package name */
    private Stack<h> f917e;

    /* renamed from: f  reason: collision with root package name */
    private Stack<h.j0> f918f;

    /* renamed from: g  reason: collision with root package name */
    private Stack<Matrix> f919g;

    /* renamed from: h  reason: collision with root package name */
    private b.q f920h = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f921c;

        static {
            int[] iArr = new int[h.e0.d.values().length];
            f921c = iArr;
            try {
                iArr[h.e0.d.Miter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f921c[h.e0.d.Round.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f921c[h.e0.d.Bevel.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[h.e0.c.values().length];
            b = iArr2;
            try {
                iArr2[h.e0.c.Butt.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[h.e0.c.Round.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[h.e0.c.Square.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[f.a.values().length];
            a = iArr3;
            try {
                iArr3[f.a.xMidYMin.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[f.a.xMidYMid.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[f.a.xMidYMax.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[f.a.xMaxYMin.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[f.a.xMaxYMid.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[f.a.xMaxYMax.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[f.a.xMinYMid.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[f.a.xMinYMax.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements h.x {
        private float b;

        /* renamed from: c  reason: collision with root package name */
        private float f922c;

        /* renamed from: h  reason: collision with root package name */
        private boolean f926h;
        private List<c> a = new ArrayList();
        private c d = null;

        /* renamed from: e  reason: collision with root package name */
        private boolean f923e = false;

        /* renamed from: f  reason: collision with root package name */
        private boolean f924f = true;

        /* renamed from: g  reason: collision with root package name */
        private int f925g = -1;

        b(h.w wVar) {
            if (wVar == null) {
                return;
            }
            wVar.h(this);
            if (this.f926h) {
                this.d.b(this.a.get(this.f925g));
                this.a.set(this.f925g, this.d);
                this.f926h = false;
            }
            c cVar = this.d;
            if (cVar != null) {
                this.a.add(cVar);
            }
        }

        @Override // com.caverock.androidsvg.h.x
        public void a(float f2, float f3, float f4, float f5) {
            this.d.a(f2, f3);
            this.a.add(this.d);
            this.d = new c(i.this, f4, f5, f4 - f2, f5 - f3);
            this.f926h = false;
        }

        @Override // com.caverock.androidsvg.h.x
        public void b(float f2, float f3) {
            if (this.f926h) {
                this.d.b(this.a.get(this.f925g));
                this.a.set(this.f925g, this.d);
                this.f926h = false;
            }
            c cVar = this.d;
            if (cVar != null) {
                this.a.add(cVar);
            }
            this.b = f2;
            this.f922c = f3;
            this.d = new c(i.this, f2, f3, 0.0f, 0.0f);
            this.f925g = this.a.size();
        }

        @Override // com.caverock.androidsvg.h.x
        public void c(float f2, float f3, float f4, float f5, float f6, float f7) {
            if (this.f924f || this.f923e) {
                this.d.a(f2, f3);
                this.a.add(this.d);
                this.f923e = false;
            }
            this.d = new c(i.this, f6, f7, f6 - f4, f7 - f5);
            this.f926h = false;
        }

        @Override // com.caverock.androidsvg.h.x
        public void close() {
            this.a.add(this.d);
            e(this.b, this.f922c);
            this.f926h = true;
        }

        @Override // com.caverock.androidsvg.h.x
        public void d(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            this.f923e = true;
            this.f924f = false;
            c cVar = this.d;
            i.m(cVar.a, cVar.b, f2, f3, f4, z, z2, f5, f6, this);
            this.f924f = true;
            this.f926h = false;
        }

        @Override // com.caverock.androidsvg.h.x
        public void e(float f2, float f3) {
            this.d.a(f2, f3);
            this.a.add(this.d);
            i iVar = i.this;
            c cVar = this.d;
            this.d = new c(iVar, f2, f3, f2 - cVar.a, f3 - cVar.b);
            this.f926h = false;
        }

        List<c> f() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c {
        float a;
        float b;

        /* renamed from: c  reason: collision with root package name */
        float f928c;
        float d;

        /* renamed from: e  reason: collision with root package name */
        boolean f929e = false;

        c(i iVar, float f2, float f3, float f4, float f5) {
            this.f928c = 0.0f;
            this.d = 0.0f;
            this.a = f2;
            this.b = f3;
            double sqrt = Math.sqrt((f4 * f4) + (f5 * f5));
            if (sqrt != 0.0d) {
                double d = f4;
                Double.isNaN(d);
                this.f928c = (float) (d / sqrt);
                double d2 = f5;
                Double.isNaN(d2);
                this.d = (float) (d2 / sqrt);
            }
        }

        void a(float f2, float f3) {
            float f4 = f2 - this.a;
            float f5 = f3 - this.b;
            double sqrt = Math.sqrt((f4 * f4) + (f5 * f5));
            if (sqrt != 0.0d) {
                double d = f4;
                Double.isNaN(d);
                f4 = (float) (d / sqrt);
                double d2 = f5;
                Double.isNaN(d2);
                f5 = (float) (d2 / sqrt);
            }
            float f6 = this.f928c;
            if (f4 == (-f6) && f5 == (-this.d)) {
                this.f929e = true;
                this.f928c = -f5;
                this.d = f4;
                return;
            }
            this.f928c = f6 + f4;
            this.d += f5;
        }

        void b(c cVar) {
            float f2 = cVar.f928c;
            float f3 = this.f928c;
            if (f2 == (-f3)) {
                float f4 = cVar.d;
                if (f4 == (-this.d)) {
                    this.f929e = true;
                    this.f928c = -f4;
                    this.d = cVar.f928c;
                    return;
                }
            }
            this.f928c = f3 + f2;
            this.d += cVar.d;
        }

        public String toString() {
            return "(" + this.a + DYConstants.DY_REGEX_COMMA + this.b + LangUtils.SINGLE_SPACE + this.f928c + DYConstants.DY_REGEX_COMMA + this.d + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements h.x {
        Path a = new Path();
        float b;

        /* renamed from: c  reason: collision with root package name */
        float f930c;

        d(i iVar, h.w wVar) {
            if (wVar == null) {
                return;
            }
            wVar.h(this);
        }

        @Override // com.caverock.androidsvg.h.x
        public void a(float f2, float f3, float f4, float f5) {
            this.a.quadTo(f2, f3, f4, f5);
            this.b = f4;
            this.f930c = f5;
        }

        @Override // com.caverock.androidsvg.h.x
        public void b(float f2, float f3) {
            this.a.moveTo(f2, f3);
            this.b = f2;
            this.f930c = f3;
        }

        @Override // com.caverock.androidsvg.h.x
        public void c(float f2, float f3, float f4, float f5, float f6, float f7) {
            this.a.cubicTo(f2, f3, f4, f5, f6, f7);
            this.b = f6;
            this.f930c = f7;
        }

        @Override // com.caverock.androidsvg.h.x
        public void close() {
            this.a.close();
        }

        @Override // com.caverock.androidsvg.h.x
        public void d(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            i.m(this.b, this.f930c, f2, f3, f4, z, z2, f5, f6, this);
            this.b = f5;
            this.f930c = f6;
        }

        @Override // com.caverock.androidsvg.h.x
        public void e(float f2, float f3) {
            this.a.lineTo(f2, f3);
            this.b = f2;
            this.f930c = f3;
        }

        Path f() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e extends f {
        private Path d;

        e(Path path, float f2, float f3) {
            super(f2, f3);
            this.d = path;
        }

        @Override // com.caverock.androidsvg.i.f, com.caverock.androidsvg.i.j
        public void b(String str) {
            if (i.this.g1()) {
                if (i.this.d.b) {
                    i.this.a.drawTextOnPath(str, this.d, this.a, this.b, i.this.d.d);
                }
                if (i.this.d.f934c) {
                    i.this.a.drawTextOnPath(str, this.d, this.a, this.b, i.this.d.f935e);
                }
            }
            this.a += i.this.d.d.measureText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f extends j {
        float a;
        float b;

        f(float f2, float f3) {
            super(i.this, null);
            this.a = f2;
            this.b = f3;
        }

        @Override // com.caverock.androidsvg.i.j
        public void b(String str) {
            i.G("TextSequence render", new Object[0]);
            if (i.this.g1()) {
                if (i.this.d.b) {
                    i.this.a.drawText(str, this.a, this.b, i.this.d.d);
                }
                if (i.this.d.f934c) {
                    i.this.a.drawText(str, this.a, this.b, i.this.d.f935e);
                }
            }
            this.a += i.this.d.d.measureText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g extends j {
        float a;
        float b;

        /* renamed from: c  reason: collision with root package name */
        Path f933c;

        g(float f2, float f3, Path path) {
            super(i.this, null);
            this.a = f2;
            this.b = f3;
            this.f933c = path;
        }

        @Override // com.caverock.androidsvg.i.j
        public boolean a(h.y0 y0Var) {
            if (y0Var instanceof h.z0) {
                i.h1("Using <textPath> elements in a clip path is not supported.", new Object[0]);
                return false;
            }
            return true;
        }

        @Override // com.caverock.androidsvg.i.j
        public void b(String str) {
            if (i.this.g1()) {
                Path path = new Path();
                i.this.d.d.getTextPath(str, 0, str.length(), this.a, this.b, path);
                this.f933c.addPath(path);
            }
            this.a += i.this.d.d.measureText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.caverock.androidsvg.i$i  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0015i extends j {
        float a;
        float b;

        /* renamed from: c  reason: collision with root package name */
        RectF f939c;

        C0015i(float f2, float f3) {
            super(i.this, null);
            this.f939c = new RectF();
            this.a = f2;
            this.b = f3;
        }

        @Override // com.caverock.androidsvg.i.j
        public boolean a(h.y0 y0Var) {
            if (y0Var instanceof h.z0) {
                h.z0 z0Var = (h.z0) y0Var;
                h.n0 q = y0Var.a.q(z0Var.f914n);
                if (q == null) {
                    i.N("TextPath path reference '%s' not found", z0Var.f914n);
                    return false;
                }
                h.v vVar = (h.v) q;
                Path f2 = new d(i.this, vVar.o).f();
                Matrix matrix = vVar.f896n;
                if (matrix != null) {
                    f2.transform(matrix);
                }
                RectF rectF = new RectF();
                f2.computeBounds(rectF, true);
                this.f939c.union(rectF);
                return false;
            }
            return true;
        }

        @Override // com.caverock.androidsvg.i.j
        public void b(String str) {
            if (i.this.g1()) {
                Rect rect = new Rect();
                i.this.d.d.getTextBounds(str, 0, str.length(), rect);
                RectF rectF = new RectF(rect);
                rectF.offset(this.a, this.b);
                this.f939c.union(rectF);
            }
            this.a += i.this.d.d.measureText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class j {
        private j(i iVar) {
        }

        public boolean a(h.y0 y0Var) {
            return true;
        }

        public abstract void b(String str);

        /* synthetic */ j(i iVar, a aVar) {
            this(iVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Canvas canvas, float f2) {
        this.a = canvas;
        this.b = f2;
    }

    private void A(h.n0 n0Var) {
        Boolean bool;
        if ((n0Var instanceof h.l0) && (bool = ((h.l0) n0Var).d) != null) {
            this.d.f938h = bool.booleanValue();
        }
    }

    private void A0(h.q qVar) {
        G("Line render", new Object[0]);
        e1(this.d, qVar);
        if (I() && g1() && this.d.f934c) {
            Matrix matrix = qVar.f896n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            Path i0 = i0(qVar);
            c1(qVar);
            x(qVar);
            u(qVar);
            boolean u0 = u0();
            K(i0);
            Q0(qVar);
            if (u0) {
                r0(qVar);
            }
        }
    }

    private static double B(double d2) {
        if (d2 < -1.0d) {
            return 3.141592653589793d;
        }
        if (d2 > 1.0d) {
            return 0.0d;
        }
        return Math.acos(d2);
    }

    private void B0(h.v vVar) {
        G("Path render", new Object[0]);
        if (vVar.o == null) {
            return;
        }
        e1(this.d, vVar);
        if (I() && g1()) {
            h hVar = this.d;
            if (hVar.f934c || hVar.b) {
                Matrix matrix = vVar.f896n;
                if (matrix != null) {
                    this.a.concat(matrix);
                }
                Path f2 = new d(this, vVar.o).f();
                if (vVar.f895h == null) {
                    vVar.f895h = r(f2);
                }
                c1(vVar);
                x(vVar);
                u(vVar);
                boolean u0 = u0();
                if (this.d.b) {
                    f2.setFillType(c0());
                    J(vVar, f2);
                }
                if (this.d.f934c) {
                    K(f2);
                }
                Q0(vVar);
                if (u0) {
                    r0(vVar);
                }
            }
        }
    }

    private static int C(float f2) {
        int i2 = (int) (f2 * 256.0f);
        if (i2 < 0) {
            return 0;
        }
        if (i2 > 255) {
            return 255;
        }
        return i2;
    }

    private void C0(h.z zVar) {
        G("PolyLine render", new Object[0]);
        e1(this.d, zVar);
        if (I() && g1()) {
            h hVar = this.d;
            if (hVar.f934c || hVar.b) {
                Matrix matrix = zVar.f896n;
                if (matrix != null) {
                    this.a.concat(matrix);
                }
                if (zVar.o.length < 2) {
                    return;
                }
                Path j0 = j0(zVar);
                c1(zVar);
                j0.setFillType(c0());
                x(zVar);
                u(zVar);
                boolean u0 = u0();
                if (this.d.b) {
                    J(zVar, j0);
                }
                if (this.d.f934c) {
                    K(j0);
                }
                Q0(zVar);
                if (u0) {
                    r0(zVar);
                }
            }
        }
    }

    private void D() {
        this.a.restore();
        this.d = this.f917e.pop();
    }

    private void D0(h.a0 a0Var) {
        G("Polygon render", new Object[0]);
        e1(this.d, a0Var);
        if (I() && g1()) {
            h hVar = this.d;
            if (hVar.f934c || hVar.b) {
                Matrix matrix = a0Var.f896n;
                if (matrix != null) {
                    this.a.concat(matrix);
                }
                if (a0Var.o.length < 2) {
                    return;
                }
                Path j0 = j0(a0Var);
                c1(a0Var);
                x(a0Var);
                u(a0Var);
                boolean u0 = u0();
                if (this.d.b) {
                    J(a0Var, j0);
                }
                if (this.d.f934c) {
                    K(j0);
                }
                Q0(a0Var);
                if (u0) {
                    r0(a0Var);
                }
            }
        }
    }

    private void E() {
        com.caverock.androidsvg.c.a(this.a, com.caverock.androidsvg.c.a);
        this.f917e.push(this.d);
        this.d = new h(this, this.d);
    }

    private void E0(h.b0 b0Var) {
        G("Rect render", new Object[0]);
        h.p pVar = b0Var.q;
        if (pVar == null || b0Var.r == null || pVar.h() || b0Var.r.h()) {
            return;
        }
        e1(this.d, b0Var);
        if (I() && g1()) {
            Matrix matrix = b0Var.f896n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            Path k0 = k0(b0Var);
            c1(b0Var);
            x(b0Var);
            u(b0Var);
            boolean u0 = u0();
            if (this.d.b) {
                J(b0Var, k0);
            }
            if (this.d.f934c) {
                K(k0);
            }
            if (u0) {
                r0(b0Var);
            }
        }
    }

    private static int F(int i2, float f2) {
        int i3 = 255;
        int round = Math.round(((i2 >> 24) & 255) * f2);
        if (round < 0) {
            i3 = 0;
        } else if (round <= 255) {
            i3 = round;
        }
        return (i2 & ViewCompat.MEASURED_SIZE_MASK) | (i3 << 24);
    }

    private void F0(h.f0 f0Var) {
        H0(f0Var, n0(f0Var.p, f0Var.q, f0Var.r, f0Var.s), f0Var.o, f0Var.f906n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void G(String str, Object... objArr) {
    }

    private void G0(h.f0 f0Var, h.b bVar) {
        H0(f0Var, bVar, f0Var.o, f0Var.f906n);
    }

    private void H(boolean z, h.b bVar, h.u uVar) {
        h.n0 q = this.f916c.q(uVar.f910g);
        if (q == null) {
            Object[] objArr = new Object[2];
            objArr[0] = z ? "Fill" : "Stroke";
            objArr[1] = uVar.f910g;
            N("%s reference '%s' not found", objArr);
            h.o0 o0Var = uVar.f911h;
            if (o0Var != null) {
                X0(this.d, z, o0Var);
            } else if (z) {
                this.d.b = false;
            } else {
                this.d.f934c = false;
            }
        } else if (q instanceof h.m0) {
            f0(z, bVar, (h.m0) q);
        } else if (q instanceof h.q0) {
            m0(z, bVar, (h.q0) q);
        } else if (q instanceof h.c0) {
            Y0(z, (h.c0) q);
        }
    }

    private void H0(h.f0 f0Var, h.b bVar, h.b bVar2, com.caverock.androidsvg.f fVar) {
        G("Svg render", new Object[0]);
        if (bVar.f853c == 0.0f || bVar.d == 0.0f) {
            return;
        }
        if (fVar == null && (fVar = f0Var.f906n) == null) {
            fVar = com.caverock.androidsvg.f.d;
        }
        e1(this.d, f0Var);
        if (I()) {
            h hVar = this.d;
            hVar.f936f = bVar;
            if (!hVar.a.B.booleanValue()) {
                h.b bVar3 = this.d.f936f;
                W0(bVar3.a, bVar3.b, bVar3.f853c, bVar3.d);
            }
            v(f0Var, this.d.f936f);
            if (bVar2 != null) {
                this.a.concat(t(this.d.f936f, bVar2, fVar));
                this.d.f937g = f0Var.o;
            } else {
                Canvas canvas = this.a;
                h.b bVar4 = this.d.f936f;
                canvas.translate(bVar4.a, bVar4.b);
            }
            boolean u0 = u0();
            f1();
            N0(f0Var, true);
            if (u0) {
                r0(f0Var);
            }
            c1(f0Var);
        }
    }

    private boolean I() {
        Boolean bool = this.d.a.G;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private void I0(h.n0 n0Var) {
        if (n0Var instanceof h.t) {
            return;
        }
        a1();
        A(n0Var);
        if (n0Var instanceof h.f0) {
            F0((h.f0) n0Var);
        } else if (n0Var instanceof h.e1) {
            M0((h.e1) n0Var);
        } else if (n0Var instanceof h.s0) {
            J0((h.s0) n0Var);
        } else if (n0Var instanceof h.m) {
            y0((h.m) n0Var);
        } else if (n0Var instanceof h.o) {
            z0((h.o) n0Var);
        } else if (n0Var instanceof h.v) {
            B0((h.v) n0Var);
        } else if (n0Var instanceof h.b0) {
            E0((h.b0) n0Var);
        } else if (n0Var instanceof h.d) {
            w0((h.d) n0Var);
        } else if (n0Var instanceof h.i) {
            x0((h.i) n0Var);
        } else if (n0Var instanceof h.q) {
            A0((h.q) n0Var);
        } else if (n0Var instanceof h.a0) {
            D0((h.a0) n0Var);
        } else if (n0Var instanceof h.z) {
            C0((h.z) n0Var);
        } else if (n0Var instanceof h.w0) {
            L0((h.w0) n0Var);
        }
        Z0();
    }

    private void J(h.k0 k0Var, Path path) {
        h.o0 o0Var = this.d.a.f859h;
        if (o0Var instanceof h.u) {
            h.n0 q = this.f916c.q(((h.u) o0Var).f910g);
            if (q instanceof h.y) {
                T(k0Var, path, (h.y) q);
                return;
            }
        }
        this.a.drawPath(path, this.d.d);
    }

    private void J0(h.s0 s0Var) {
        G("Switch render", new Object[0]);
        e1(this.d, s0Var);
        if (I()) {
            Matrix matrix = s0Var.f901n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            u(s0Var);
            boolean u0 = u0();
            S0(s0Var);
            if (u0) {
                r0(s0Var);
            }
            c1(s0Var);
        }
    }

    private void K(Path path) {
        h hVar = this.d;
        if (hVar.a.R == h.e0.i.NonScalingStroke) {
            Matrix matrix = this.a.getMatrix();
            Path path2 = new Path();
            path.transform(matrix, path2);
            this.a.setMatrix(new Matrix());
            Shader shader = this.d.f935e.getShader();
            Matrix matrix2 = new Matrix();
            if (shader != null) {
                shader.getLocalMatrix(matrix2);
                Matrix matrix3 = new Matrix(matrix2);
                matrix3.postConcat(matrix);
                shader.setLocalMatrix(matrix3);
            }
            this.a.drawPath(path2, this.d.f935e);
            this.a.setMatrix(matrix);
            if (shader != null) {
                shader.setLocalMatrix(matrix2);
                return;
            }
            return;
        }
        this.a.drawPath(path, hVar.f935e);
    }

    private void K0(h.t0 t0Var, h.b bVar) {
        G("Symbol render", new Object[0]);
        if (bVar.f853c == 0.0f || bVar.d == 0.0f) {
            return;
        }
        com.caverock.androidsvg.f fVar = t0Var.f906n;
        if (fVar == null) {
            fVar = com.caverock.androidsvg.f.d;
        }
        e1(this.d, t0Var);
        h hVar = this.d;
        hVar.f936f = bVar;
        if (!hVar.a.B.booleanValue()) {
            h.b bVar2 = this.d.f936f;
            W0(bVar2.a, bVar2.b, bVar2.f853c, bVar2.d);
        }
        h.b bVar3 = t0Var.o;
        if (bVar3 != null) {
            this.a.concat(t(this.d.f936f, bVar3, fVar));
            this.d.f937g = t0Var.o;
        } else {
            Canvas canvas = this.a;
            h.b bVar4 = this.d.f936f;
            canvas.translate(bVar4.a, bVar4.b);
        }
        boolean u0 = u0();
        N0(t0Var, true);
        if (u0) {
            r0(t0Var);
        }
        c1(t0Var);
    }

    private float L(float f2, float f3, float f4, float f5) {
        return (f2 * f4) + (f3 * f5);
    }

    private void L0(h.w0 w0Var) {
        G("Text render", new Object[0]);
        e1(this.d, w0Var);
        if (I()) {
            Matrix matrix = w0Var.r;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            List<h.p> list = w0Var.f852n;
            float f2 = 0.0f;
            float e2 = (list == null || list.size() == 0) ? 0.0f : w0Var.f852n.get(0).e(this);
            List<h.p> list2 = w0Var.o;
            float f3 = (list2 == null || list2.size() == 0) ? 0.0f : w0Var.o.get(0).f(this);
            List<h.p> list3 = w0Var.p;
            float e3 = (list3 == null || list3.size() == 0) ? 0.0f : w0Var.p.get(0).e(this);
            List<h.p> list4 = w0Var.q;
            if (list4 != null && list4.size() != 0) {
                f2 = w0Var.q.get(0).f(this);
            }
            h.e0.f W = W();
            if (W != h.e0.f.Start) {
                float s = s(w0Var);
                if (W == h.e0.f.Middle) {
                    s /= 2.0f;
                }
                e2 -= s;
            }
            if (w0Var.f895h == null) {
                C0015i c0015i = new C0015i(e2, f3);
                M(w0Var, c0015i);
                RectF rectF = c0015i.f939c;
                w0Var.f895h = new h.b(rectF.left, rectF.top, rectF.width(), c0015i.f939c.height());
            }
            c1(w0Var);
            x(w0Var);
            u(w0Var);
            boolean u0 = u0();
            M(w0Var, new f(e2 + e3, f3 + f2));
            if (u0) {
                r0(w0Var);
            }
        }
    }

    private void M(h.y0 y0Var, j jVar) {
        if (I()) {
            Iterator<h.n0> it = y0Var.f879i.iterator();
            boolean z = true;
            while (it.hasNext()) {
                h.n0 next = it.next();
                if (next instanceof h.c1) {
                    jVar.b(b1(((h.c1) next).f855c, z, !it.hasNext()));
                } else {
                    t0(next, jVar);
                }
                z = false;
            }
        }
    }

    private void M0(h.e1 e1Var) {
        G("Use render", new Object[0]);
        h.p pVar = e1Var.r;
        if (pVar == null || !pVar.h()) {
            h.p pVar2 = e1Var.s;
            if (pVar2 == null || !pVar2.h()) {
                e1(this.d, e1Var);
                if (I()) {
                    h.n0 q = e1Var.a.q(e1Var.o);
                    if (q == null) {
                        N("Use reference '%s' not found", e1Var.o);
                        return;
                    }
                    Matrix matrix = e1Var.f901n;
                    if (matrix != null) {
                        this.a.concat(matrix);
                    }
                    h.p pVar3 = e1Var.p;
                    float e2 = pVar3 != null ? pVar3.e(this) : 0.0f;
                    h.p pVar4 = e1Var.q;
                    this.a.translate(e2, pVar4 != null ? pVar4.f(this) : 0.0f);
                    u(e1Var);
                    boolean u0 = u0();
                    q0(e1Var);
                    if (q instanceof h.f0) {
                        h.b n0 = n0(null, null, e1Var.r, e1Var.s);
                        a1();
                        G0((h.f0) q, n0);
                        Z0();
                    } else if (q instanceof h.t0) {
                        h.p pVar5 = e1Var.r;
                        if (pVar5 == null) {
                            pVar5 = new h.p(100.0f, h.d1.percent);
                        }
                        h.p pVar6 = e1Var.s;
                        if (pVar6 == null) {
                            pVar6 = new h.p(100.0f, h.d1.percent);
                        }
                        h.b n02 = n0(null, null, pVar5, pVar6);
                        a1();
                        K0((h.t0) q, n02);
                        Z0();
                    } else {
                        I0(q);
                    }
                    p0();
                    if (u0) {
                        r0(e1Var);
                    }
                    c1(e1Var);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void N(String str, Object... objArr) {
        String.format(str, objArr);
    }

    private void N0(h.j0 j0Var, boolean z) {
        if (z) {
            q0(j0Var);
        }
        Iterator<h.n0> it = j0Var.getChildren().iterator();
        while (it.hasNext()) {
            I0(it.next());
        }
        if (z) {
            p0();
        }
    }

    private void O(h.y0 y0Var, StringBuilder sb) {
        Iterator<h.n0> it = y0Var.f879i.iterator();
        boolean z = true;
        while (it.hasNext()) {
            h.n0 next = it.next();
            if (next instanceof h.y0) {
                O((h.y0) next, sb);
            } else if (next instanceof h.c1) {
                sb.append(b1(((h.c1) next).f855c, z, !it.hasNext()));
            }
            z = false;
        }
    }

    private void P(h.j jVar, String str) {
        h.n0 q = jVar.a.q(str);
        if (q == null) {
            h1("Gradient reference '%s' not found", str);
        } else if (!(q instanceof h.j)) {
            N("Gradient href attributes must point to other gradient elements", new Object[0]);
        } else if (q == jVar) {
            N("Circular reference in gradient href attribute '%s'", str);
        } else {
            h.j jVar2 = (h.j) q;
            if (jVar.f890i == null) {
                jVar.f890i = jVar2.f890i;
            }
            if (jVar.f891j == null) {
                jVar.f891j = jVar2.f891j;
            }
            if (jVar.f892k == null) {
                jVar.f892k = jVar2.f892k;
            }
            if (jVar.f889h.isEmpty()) {
                jVar.f889h = jVar2.f889h;
            }
            try {
                if (jVar instanceof h.m0) {
                    Q((h.m0) jVar, (h.m0) q);
                } else {
                    R((h.q0) jVar, (h.q0) q);
                }
            } catch (ClassCastException unused) {
            }
            String str2 = jVar2.f893l;
            if (str2 != null) {
                P(jVar, str2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0104, code lost:
        if (r7 != 8) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void P0(h.r rVar, c cVar) {
        float f2;
        h.b bVar;
        boolean u0;
        float f3;
        float f4;
        float f5;
        a1();
        Float f6 = rVar.u;
        float f7 = 0.0f;
        if (f6 != null) {
            if (Float.isNaN(f6.floatValue())) {
                float f8 = cVar.f928c;
                if (f8 != 0.0f || cVar.d != 0.0f) {
                    f2 = (float) Math.toDegrees(Math.atan2(cVar.d, f8));
                }
            } else {
                f2 = rVar.u.floatValue();
            }
            float b2 = !rVar.p ? 1.0f : this.d.a.f864m.b(this.b);
            this.d = U(rVar);
            Matrix matrix = new Matrix();
            matrix.preTranslate(cVar.a, cVar.b);
            matrix.preRotate(f2);
            matrix.preScale(b2, b2);
            h.p pVar = rVar.q;
            float e2 = pVar == null ? pVar.e(this) : 0.0f;
            h.p pVar2 = rVar.r;
            float f9 = pVar2 == null ? pVar2.f(this) : 0.0f;
            h.p pVar3 = rVar.s;
            float e3 = pVar3 == null ? pVar3.e(this) : 3.0f;
            h.p pVar4 = rVar.t;
            float f10 = pVar4 != null ? pVar4.f(this) : 3.0f;
            bVar = rVar.o;
            if (bVar == null) {
                float f11 = e3 / bVar.f853c;
                float f12 = f10 / bVar.d;
                com.caverock.androidsvg.f fVar = rVar.f906n;
                if (fVar == null) {
                    fVar = com.caverock.androidsvg.f.d;
                }
                if (!fVar.equals(com.caverock.androidsvg.f.f843c)) {
                    f11 = fVar.b() == f.b.slice ? Math.max(f11, f12) : Math.min(f11, f12);
                    f12 = f11;
                }
                matrix.preTranslate((-e2) * f11, (-f9) * f12);
                this.a.concat(matrix);
                h.b bVar2 = rVar.o;
                float f13 = bVar2.f853c * f11;
                float f14 = bVar2.d * f12;
                int[] iArr = a.a;
                switch (iArr[fVar.a().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        f3 = (e3 - f13) / 2.0f;
                        f4 = 0.0f - f3;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        f3 = e3 - f13;
                        f4 = 0.0f - f3;
                        break;
                    default:
                        f4 = 0.0f;
                        break;
                }
                int i2 = iArr[fVar.a().ordinal()];
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 5) {
                            if (i2 != 6) {
                                if (i2 != 7) {
                                }
                            }
                        }
                    }
                    f5 = f10 - f14;
                    f7 = 0.0f - f5;
                    if (!this.d.a.B.booleanValue()) {
                        W0(f4, f7, e3, f10);
                    }
                    matrix.reset();
                    matrix.preScale(f11, f12);
                    this.a.concat(matrix);
                }
                f5 = (f10 - f14) / 2.0f;
                f7 = 0.0f - f5;
                if (!this.d.a.B.booleanValue()) {
                }
                matrix.reset();
                matrix.preScale(f11, f12);
                this.a.concat(matrix);
            } else {
                matrix.preTranslate(-e2, -f9);
                this.a.concat(matrix);
                if (!this.d.a.B.booleanValue()) {
                    W0(0.0f, 0.0f, e3, f10);
                }
            }
            u0 = u0();
            N0(rVar, false);
            if (u0) {
                r0(rVar);
            }
            Z0();
        }
        f2 = 0.0f;
        if (!rVar.p) {
        }
        this.d = U(rVar);
        Matrix matrix2 = new Matrix();
        matrix2.preTranslate(cVar.a, cVar.b);
        matrix2.preRotate(f2);
        matrix2.preScale(b2, b2);
        h.p pVar5 = rVar.q;
        if (pVar5 == null) {
        }
        h.p pVar22 = rVar.r;
        if (pVar22 == null) {
        }
        h.p pVar32 = rVar.s;
        if (pVar32 == null) {
        }
        h.p pVar42 = rVar.t;
        if (pVar42 != null) {
        }
        bVar = rVar.o;
        if (bVar == null) {
        }
        u0 = u0();
        N0(rVar, false);
        if (u0) {
        }
        Z0();
    }

    private void Q(h.m0 m0Var, h.m0 m0Var2) {
        if (m0Var.f902m == null) {
            m0Var.f902m = m0Var2.f902m;
        }
        if (m0Var.f903n == null) {
            m0Var.f903n = m0Var2.f903n;
        }
        if (m0Var.o == null) {
            m0Var.o = m0Var2.o;
        }
        if (m0Var.p == null) {
            m0Var.p = m0Var2.p;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void Q0(h.l lVar) {
        h.r rVar;
        String str;
        h.r rVar2;
        String str2;
        h.r rVar3;
        List<c> q;
        int size;
        int i2;
        h.e0 e0Var = this.d.a;
        String str3 = e0Var.D;
        if (str3 == null && e0Var.E == null && e0Var.F == null) {
            return;
        }
        if (str3 != null) {
            h.n0 q2 = lVar.a.q(str3);
            if (q2 != null) {
                rVar = (h.r) q2;
                str = this.d.a.E;
                if (str != null) {
                    h.n0 q3 = lVar.a.q(str);
                    if (q3 != null) {
                        rVar2 = (h.r) q3;
                        str2 = this.d.a.F;
                        if (str2 != null) {
                            h.n0 q4 = lVar.a.q(str2);
                            if (q4 != null) {
                                rVar3 = (h.r) q4;
                                if (!(lVar instanceof h.v)) {
                                    q = new b(((h.v) lVar).o).f();
                                } else if (lVar instanceof h.q) {
                                    q = p((h.q) lVar);
                                } else {
                                    q = q((h.z) lVar);
                                }
                                if (q == null && (size = q.size()) != 0) {
                                    h.e0 e0Var2 = this.d.a;
                                    e0Var2.F = null;
                                    e0Var2.E = null;
                                    e0Var2.D = null;
                                    if (rVar != null) {
                                        P0(rVar, q.get(0));
                                    }
                                    if (rVar2 != null && q.size() > 2) {
                                        c cVar = q.get(1);
                                        i2 = 1;
                                        c cVar2 = q.get(0);
                                        c cVar3 = cVar;
                                        while (i2 < size - 1) {
                                            i2++;
                                            c cVar4 = q.get(i2);
                                            if (cVar3.f929e) {
                                                v0(cVar2, cVar3, cVar4);
                                            }
                                            P0(rVar2, cVar3);
                                            cVar2 = cVar3;
                                            cVar3 = cVar4;
                                        }
                                    }
                                    if (rVar3 == null) {
                                        P0(rVar3, q.get(size - 1));
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            N("Marker reference '%s' not found", this.d.a.F);
                        }
                        rVar3 = null;
                        if (!(lVar instanceof h.v)) {
                        }
                        if (q == null) {
                            return;
                        }
                        h.e0 e0Var22 = this.d.a;
                        e0Var22.F = null;
                        e0Var22.E = null;
                        e0Var22.D = null;
                        if (rVar != null) {
                        }
                        if (rVar2 != null) {
                            c cVar5 = q.get(1);
                            i2 = 1;
                            c cVar22 = q.get(0);
                            c cVar32 = cVar5;
                            while (i2 < size - 1) {
                            }
                        }
                        if (rVar3 == null) {
                        }
                    } else {
                        N("Marker reference '%s' not found", this.d.a.E);
                    }
                }
                rVar2 = null;
                str2 = this.d.a.F;
                if (str2 != null) {
                }
                rVar3 = null;
                if (!(lVar instanceof h.v)) {
                }
                if (q == null) {
                }
            } else {
                N("Marker reference '%s' not found", this.d.a.D);
            }
        }
        rVar = null;
        str = this.d.a.E;
        if (str != null) {
        }
        rVar2 = null;
        str2 = this.d.a.F;
        if (str2 != null) {
        }
        rVar3 = null;
        if (!(lVar instanceof h.v)) {
        }
        if (q == null) {
        }
    }

    private void R(h.q0 q0Var, h.q0 q0Var2) {
        if (q0Var.f907m == null) {
            q0Var.f907m = q0Var2.f907m;
        }
        if (q0Var.f908n == null) {
            q0Var.f908n = q0Var2.f908n;
        }
        if (q0Var.o == null) {
            q0Var.o = q0Var2.o;
        }
        if (q0Var.p == null) {
            q0Var.p = q0Var2.p;
        }
        if (q0Var.q == null) {
            q0Var.q = q0Var2.q;
        }
    }

    private void R0(h.s sVar, h.k0 k0Var, h.b bVar) {
        float f2;
        float f3;
        G("Mask render", new Object[0]);
        Boolean bool = sVar.f909n;
        boolean z = true;
        if (bool != null && bool.booleanValue()) {
            h.p pVar = sVar.r;
            f2 = pVar != null ? pVar.e(this) : bVar.f853c;
            h.p pVar2 = sVar.s;
            f3 = pVar2 != null ? pVar2.f(this) : bVar.d;
        } else {
            h.p pVar3 = sVar.r;
            float d2 = pVar3 != null ? pVar3.d(this, 1.0f) : 1.2f;
            h.p pVar4 = sVar.s;
            float d3 = pVar4 != null ? pVar4.d(this, 1.0f) : 1.2f;
            f2 = d2 * bVar.f853c;
            f3 = d3 * bVar.d;
        }
        if (f2 == 0.0f || f3 == 0.0f) {
            return;
        }
        a1();
        h U = U(sVar);
        this.d = U;
        U.a.s = Float.valueOf(1.0f);
        boolean u0 = u0();
        this.a.save();
        Boolean bool2 = sVar.o;
        if (bool2 != null && !bool2.booleanValue()) {
            z = false;
        }
        if (!z) {
            this.a.translate(bVar.a, bVar.b);
            this.a.scale(bVar.f853c, bVar.d);
        }
        N0(sVar, false);
        this.a.restore();
        if (u0) {
            s0(k0Var, bVar);
        }
        Z0();
    }

    private void S(h.y yVar, String str) {
        h.n0 q = yVar.a.q(str);
        if (q == null) {
            h1("Pattern reference '%s' not found", str);
        } else if (!(q instanceof h.y)) {
            N("Pattern href attributes must point to other pattern elements", new Object[0]);
        } else if (q == yVar) {
            N("Circular reference in pattern href attribute '%s'", str);
        } else {
            h.y yVar2 = (h.y) q;
            if (yVar.p == null) {
                yVar.p = yVar2.p;
            }
            if (yVar.q == null) {
                yVar.q = yVar2.q;
            }
            if (yVar.r == null) {
                yVar.r = yVar2.r;
            }
            if (yVar.s == null) {
                yVar.s = yVar2.s;
            }
            if (yVar.t == null) {
                yVar.t = yVar2.t;
            }
            if (yVar.u == null) {
                yVar.u = yVar2.u;
            }
            if (yVar.v == null) {
                yVar.v = yVar2.v;
            }
            if (yVar.f879i.isEmpty()) {
                yVar.f879i = yVar2.f879i;
            }
            if (yVar.o == null) {
                yVar.o = yVar2.o;
            }
            if (yVar.f906n == null) {
                yVar.f906n = yVar2.f906n;
            }
            String str2 = yVar2.w;
            if (str2 != null) {
                S(yVar, str2);
            }
        }
    }

    private void S0(h.s0 s0Var) {
        Set<String> a2;
        String language = Locale.getDefault().getLanguage();
        com.caverock.androidsvg.j g2 = com.caverock.androidsvg.h.g();
        for (h.n0 n0Var : s0Var.getChildren()) {
            if (n0Var instanceof h.g0) {
                h.g0 g0Var = (h.g0) n0Var;
                if (g0Var.b() == null && ((a2 = g0Var.a()) == null || (!a2.isEmpty() && a2.contains(language)))) {
                    Set<String> f2 = g0Var.f();
                    if (f2 != null) {
                        if (f915i == null) {
                            d0();
                        }
                        if (!f2.isEmpty() && f915i.containsAll(f2)) {
                        }
                    }
                    Set<String> l2 = g0Var.l();
                    if (l2 != null) {
                        if (!l2.isEmpty() && g2 != null) {
                            Iterator<String> it = l2.iterator();
                            if (it.hasNext()) {
                                g2.a(it.next());
                                throw null;
                            }
                        }
                    }
                    Set<String> m2 = g0Var.m();
                    if (m2 != null) {
                        if (!m2.isEmpty() && g2 != null) {
                            Iterator<String> it2 = m2.iterator();
                            if (it2.hasNext()) {
                                g2.c(it2.next(), this.d.a.w.intValue(), String.valueOf(this.d.a.x));
                                throw null;
                            }
                        }
                    }
                    I0(n0Var);
                    return;
                }
            }
        }
    }

    private void T(h.k0 k0Var, Path path, h.y yVar) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        Boolean bool = yVar.p;
        boolean z = bool != null && bool.booleanValue();
        String str = yVar.w;
        if (str != null) {
            S(yVar, str);
        }
        if (z) {
            h.p pVar = yVar.s;
            f2 = pVar != null ? pVar.e(this) : 0.0f;
            h.p pVar2 = yVar.t;
            f4 = pVar2 != null ? pVar2.f(this) : 0.0f;
            h.p pVar3 = yVar.u;
            f5 = pVar3 != null ? pVar3.e(this) : 0.0f;
            h.p pVar4 = yVar.v;
            f3 = pVar4 != null ? pVar4.f(this) : 0.0f;
        } else {
            h.p pVar5 = yVar.s;
            float d2 = pVar5 != null ? pVar5.d(this, 1.0f) : 0.0f;
            h.p pVar6 = yVar.t;
            float d3 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.0f;
            h.p pVar7 = yVar.u;
            float d4 = pVar7 != null ? pVar7.d(this, 1.0f) : 0.0f;
            h.p pVar8 = yVar.v;
            float d5 = pVar8 != null ? pVar8.d(this, 1.0f) : 0.0f;
            h.b bVar = k0Var.f895h;
            float f7 = bVar.a;
            float f8 = bVar.f853c;
            f2 = (d2 * f8) + f7;
            float f9 = bVar.b;
            float f10 = bVar.d;
            float f11 = d4 * f8;
            f3 = d5 * f10;
            f4 = (d3 * f10) + f9;
            f5 = f11;
        }
        if (f5 == 0.0f || f3 == 0.0f) {
            return;
        }
        com.caverock.androidsvg.f fVar = yVar.f906n;
        if (fVar == null) {
            fVar = com.caverock.androidsvg.f.d;
        }
        a1();
        this.a.clipPath(path);
        h hVar = new h(this);
        d1(hVar, h.e0.a());
        hVar.a.B = Boolean.FALSE;
        V(yVar, hVar);
        this.d = hVar;
        h.b bVar2 = k0Var.f895h;
        Matrix matrix = yVar.r;
        if (matrix != null) {
            this.a.concat(matrix);
            Matrix matrix2 = new Matrix();
            if (yVar.r.invert(matrix2)) {
                h.b bVar3 = k0Var.f895h;
                h.b bVar4 = k0Var.f895h;
                h.b bVar5 = k0Var.f895h;
                float[] fArr = {bVar3.a, bVar3.b, bVar3.b(), bVar4.b, bVar4.b(), k0Var.f895h.c(), bVar5.a, bVar5.c()};
                matrix2.mapPoints(fArr);
                RectF rectF = new RectF(fArr[0], fArr[1], fArr[0], fArr[1]);
                for (int i2 = 2; i2 <= 6; i2 += 2) {
                    if (fArr[i2] < rectF.left) {
                        rectF.left = fArr[i2];
                    }
                    if (fArr[i2] > rectF.right) {
                        rectF.right = fArr[i2];
                    }
                    int i3 = i2 + 1;
                    if (fArr[i3] < rectF.top) {
                        rectF.top = fArr[i3];
                    }
                    if (fArr[i3] > rectF.bottom) {
                        rectF.bottom = fArr[i3];
                    }
                }
                float f12 = rectF.left;
                float f13 = rectF.top;
                bVar2 = new h.b(f12, f13, rectF.right - f12, rectF.bottom - f13);
            }
        }
        float floor = f2 + (((float) Math.floor((bVar2.a - f2) / f5)) * f5);
        float b2 = bVar2.b();
        float c2 = bVar2.c();
        h.b bVar6 = new h.b(0.0f, 0.0f, f5, f3);
        boolean u0 = u0();
        for (float floor2 = f4 + (((float) Math.floor((bVar2.b - f4) / f3)) * f3); floor2 < c2; floor2 += f3) {
            float f14 = floor;
            while (f14 < b2) {
                bVar6.a = f14;
                bVar6.b = floor2;
                a1();
                if (this.d.a.B.booleanValue()) {
                    f6 = floor;
                } else {
                    f6 = floor;
                    W0(bVar6.a, bVar6.b, bVar6.f853c, bVar6.d);
                }
                h.b bVar7 = yVar.o;
                if (bVar7 != null) {
                    this.a.concat(t(bVar6, bVar7, fVar));
                } else {
                    Boolean bool2 = yVar.q;
                    boolean z2 = bool2 == null || bool2.booleanValue();
                    this.a.translate(f14, floor2);
                    if (!z2) {
                        Canvas canvas = this.a;
                        h.b bVar8 = k0Var.f895h;
                        canvas.scale(bVar8.f853c, bVar8.d);
                    }
                }
                Iterator<h.n0> it = yVar.f879i.iterator();
                while (it.hasNext()) {
                    I0(it.next());
                }
                Z0();
                f14 += f5;
                floor = f6;
            }
        }
        if (u0) {
            r0(yVar);
        }
        Z0();
    }

    private void T0(h.z0 z0Var) {
        G("TextPath render", new Object[0]);
        e1(this.d, z0Var);
        if (I() && g1()) {
            h.n0 q = z0Var.a.q(z0Var.f914n);
            if (q == null) {
                N("TextPath reference '%s' not found", z0Var.f914n);
                return;
            }
            h.v vVar = (h.v) q;
            Path f2 = new d(this, vVar.o).f();
            Matrix matrix = vVar.f896n;
            if (matrix != null) {
                f2.transform(matrix);
            }
            PathMeasure pathMeasure = new PathMeasure(f2, false);
            h.p pVar = z0Var.o;
            float d2 = pVar != null ? pVar.d(this, pathMeasure.getLength()) : 0.0f;
            h.e0.f W = W();
            if (W != h.e0.f.Start) {
                float s = s(z0Var);
                if (W == h.e0.f.Middle) {
                    s /= 2.0f;
                }
                d2 -= s;
            }
            x((h.k0) z0Var.d());
            boolean u0 = u0();
            M(z0Var, new e(f2, d2, 0.0f));
            if (u0) {
                r0(z0Var);
            }
        }
    }

    private h U(h.n0 n0Var) {
        h hVar = new h(this);
        d1(hVar, h.e0.a());
        V(n0Var, hVar);
        return hVar;
    }

    private boolean U0() {
        return this.d.a.s.floatValue() < 1.0f || this.d.a.M != null;
    }

    private h V(h.n0 n0Var, h hVar) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (n0Var instanceof h.l0) {
                arrayList.add(0, (h.l0) n0Var);
            }
            h.j0 j0Var = n0Var.b;
            if (j0Var == null) {
                break;
            }
            n0Var = (h.n0) j0Var;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            e1(hVar, (h.l0) it.next());
        }
        h hVar2 = this.d;
        hVar.f937g = hVar2.f937g;
        hVar.f936f = hVar2.f936f;
        return hVar;
    }

    private void V0() {
        this.d = new h(this);
        this.f917e = new Stack<>();
        d1(this.d, h.e0.a());
        h hVar = this.d;
        hVar.f936f = null;
        hVar.f938h = false;
        this.f917e.push(new h(this, hVar));
        this.f919g = new Stack<>();
        this.f918f = new Stack<>();
    }

    private h.e0.f W() {
        h.e0.f fVar;
        h.e0 e0Var = this.d.a;
        if (e0Var.z != h.e0.EnumC0013h.LTR && (fVar = e0Var.A) != h.e0.f.Middle) {
            h.e0.f fVar2 = h.e0.f.Start;
            return fVar == fVar2 ? h.e0.f.End : fVar2;
        }
        return e0Var.A;
    }

    private void W0(float f2, float f3, float f4, float f5) {
        float f6 = f4 + f2;
        float f7 = f5 + f3;
        h.c cVar = this.d.a.C;
        if (cVar != null) {
            f2 += cVar.d.e(this);
            f3 += this.d.a.C.a.f(this);
            f6 -= this.d.a.C.b.e(this);
            f7 -= this.d.a.C.f854c.f(this);
        }
        this.a.clipRect(f2, f3, f6, f7);
    }

    private Path.FillType X() {
        h.e0.a aVar = this.d.a.L;
        if (aVar != null && aVar == h.e0.a.EvenOdd) {
            return Path.FillType.EVEN_ODD;
        }
        return Path.FillType.WINDING;
    }

    private void X0(h hVar, boolean z, h.o0 o0Var) {
        int i2;
        h.e0 e0Var = hVar.a;
        float floatValue = (z ? e0Var.f861j : e0Var.f863l).floatValue();
        if (o0Var instanceof h.f) {
            i2 = ((h.f) o0Var).f877g;
        } else if (!(o0Var instanceof h.g)) {
            return;
        } else {
            i2 = hVar.a.t.f877g;
        }
        int F = F(i2, floatValue);
        if (z) {
            hVar.d.setColor(F);
        } else {
            hVar.f935e.setColor(F);
        }
    }

    private void Y0(boolean z, h.c0 c0Var) {
        if (z) {
            if (e0(c0Var.f898e, IjkMediaMeta.AV_CH_WIDE_LEFT)) {
                h hVar = this.d;
                h.e0 e0Var = hVar.a;
                h.o0 o0Var = c0Var.f898e.N;
                e0Var.f859h = o0Var;
                hVar.b = o0Var != null;
            }
            if (e0(c0Var.f898e, IjkMediaMeta.AV_CH_WIDE_RIGHT)) {
                this.d.a.f861j = c0Var.f898e.O;
            }
            if (e0(c0Var.f898e, 6442450944L)) {
                h hVar2 = this.d;
                X0(hVar2, z, hVar2.a.f859h);
                return;
            }
            return;
        }
        if (e0(c0Var.f898e, IjkMediaMeta.AV_CH_WIDE_LEFT)) {
            h hVar3 = this.d;
            h.e0 e0Var2 = hVar3.a;
            h.o0 o0Var2 = c0Var.f898e.N;
            e0Var2.f862k = o0Var2;
            hVar3.f934c = o0Var2 != null;
        }
        if (e0(c0Var.f898e, IjkMediaMeta.AV_CH_WIDE_RIGHT)) {
            this.d.a.f863l = c0Var.f898e.O;
        }
        if (e0(c0Var.f898e, 6442450944L)) {
            h hVar4 = this.d;
            X0(hVar4, z, hVar4.a.f862k);
        }
    }

    private void Z0() {
        this.a.restore();
        this.d = this.f917e.pop();
    }

    private void a1() {
        this.a.save();
        this.f917e.push(this.d);
        this.d = new h(this, this.d);
    }

    private String b1(String str, boolean z, boolean z2) {
        if (this.d.f938h) {
            return str.replaceAll("[\\n\\t]", LangUtils.SINGLE_SPACE);
        }
        String replaceAll = str.replaceAll("\\n", "").replaceAll("\\t", LangUtils.SINGLE_SPACE);
        if (z) {
            replaceAll = replaceAll.replaceAll("^\\s+", "");
        }
        if (z2) {
            replaceAll = replaceAll.replaceAll("\\s+$", "");
        }
        return replaceAll.replaceAll("\\s{2,}", LangUtils.SINGLE_SPACE);
    }

    private Path.FillType c0() {
        h.e0.a aVar = this.d.a.f860i;
        if (aVar != null && aVar == h.e0.a.EvenOdd) {
            return Path.FillType.EVEN_ODD;
        }
        return Path.FillType.WINDING;
    }

    private void c1(h.k0 k0Var) {
        if (k0Var.b == null || k0Var.f895h == null) {
            return;
        }
        Matrix matrix = new Matrix();
        if (this.f919g.peek().invert(matrix)) {
            h.b bVar = k0Var.f895h;
            h.b bVar2 = k0Var.f895h;
            h.b bVar3 = k0Var.f895h;
            float[] fArr = {bVar.a, bVar.b, bVar.b(), bVar2.b, bVar2.b(), k0Var.f895h.c(), bVar3.a, bVar3.c()};
            matrix.preConcat(this.a.getMatrix());
            matrix.mapPoints(fArr);
            RectF rectF = new RectF(fArr[0], fArr[1], fArr[0], fArr[1]);
            for (int i2 = 2; i2 <= 6; i2 += 2) {
                if (fArr[i2] < rectF.left) {
                    rectF.left = fArr[i2];
                }
                if (fArr[i2] > rectF.right) {
                    rectF.right = fArr[i2];
                }
                int i3 = i2 + 1;
                if (fArr[i3] < rectF.top) {
                    rectF.top = fArr[i3];
                }
                if (fArr[i3] > rectF.bottom) {
                    rectF.bottom = fArr[i3];
                }
            }
            h.k0 k0Var2 = (h.k0) this.f918f.peek();
            h.b bVar4 = k0Var2.f895h;
            if (bVar4 == null) {
                k0Var2.f895h = h.b.a(rectF.left, rectF.top, rectF.right, rectF.bottom);
            } else {
                bVar4.d(h.b.a(rectF.left, rectF.top, rectF.right, rectF.bottom));
            }
        }
    }

    private static synchronized void d0() {
        synchronized (i.class) {
            HashSet<String> hashSet = new HashSet<>();
            f915i = hashSet;
            hashSet.add("Structure");
            f915i.add("BasicStructure");
            f915i.add("ConditionalProcessing");
            f915i.add(TemplateViewBase.ELEM_TYPE_IMAGE);
            f915i.add("Style");
            f915i.add("ViewportAttribute");
            f915i.add("Shape");
            f915i.add("BasicText");
            f915i.add("PaintAttribute");
            f915i.add("BasicPaintAttribute");
            f915i.add("OpacityAttribute");
            f915i.add("BasicGraphicsAttribute");
            f915i.add("Marker");
            f915i.add("Gradient");
            f915i.add("Pattern");
            f915i.add("Clip");
            f915i.add("BasicClip");
            f915i.add("Mask");
            f915i.add(TemplateViewBase.ELEM_TYPE_VIEW);
        }
    }

    private void d1(h hVar, h.e0 e0Var) {
        if (e0(e0Var, 4096L)) {
            hVar.a.t = e0Var.t;
        }
        if (e0(e0Var, 2048L)) {
            hVar.a.s = e0Var.s;
        }
        if (e0(e0Var, 1L)) {
            hVar.a.f859h = e0Var.f859h;
            h.o0 o0Var = e0Var.f859h;
            hVar.b = (o0Var == null || o0Var == h.f.f876i) ? false : true;
        }
        if (e0(e0Var, 4L)) {
            hVar.a.f861j = e0Var.f861j;
        }
        if (e0(e0Var, 6149L)) {
            X0(hVar, true, hVar.a.f859h);
        }
        if (e0(e0Var, 2L)) {
            hVar.a.f860i = e0Var.f860i;
        }
        if (e0(e0Var, 8L)) {
            hVar.a.f862k = e0Var.f862k;
            h.o0 o0Var2 = e0Var.f862k;
            hVar.f934c = (o0Var2 == null || o0Var2 == h.f.f876i) ? false : true;
        }
        if (e0(e0Var, 16L)) {
            hVar.a.f863l = e0Var.f863l;
        }
        if (e0(e0Var, 6168L)) {
            X0(hVar, false, hVar.a.f862k);
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_LOW_FREQUENCY_2)) {
            hVar.a.R = e0Var.R;
        }
        if (e0(e0Var, 32L)) {
            h.e0 e0Var2 = hVar.a;
            h.p pVar = e0Var.f864m;
            e0Var2.f864m = pVar;
            hVar.f935e.setStrokeWidth(pVar.c(this));
        }
        if (e0(e0Var, 64L)) {
            hVar.a.f865n = e0Var.f865n;
            int i2 = a.b[e0Var.f865n.ordinal()];
            if (i2 == 1) {
                hVar.f935e.setStrokeCap(Paint.Cap.BUTT);
            } else if (i2 == 2) {
                hVar.f935e.setStrokeCap(Paint.Cap.ROUND);
            } else if (i2 == 3) {
                hVar.f935e.setStrokeCap(Paint.Cap.SQUARE);
            }
        }
        if (e0(e0Var, 128L)) {
            hVar.a.o = e0Var.o;
            int i3 = a.f921c[e0Var.o.ordinal()];
            if (i3 == 1) {
                hVar.f935e.setStrokeJoin(Paint.Join.MITER);
            } else if (i3 == 2) {
                hVar.f935e.setStrokeJoin(Paint.Join.ROUND);
            } else if (i3 == 3) {
                hVar.f935e.setStrokeJoin(Paint.Join.BEVEL);
            }
        }
        if (e0(e0Var, 256L)) {
            hVar.a.p = e0Var.p;
            hVar.f935e.setStrokeMiter(e0Var.p.floatValue());
        }
        if (e0(e0Var, 512L)) {
            hVar.a.q = e0Var.q;
        }
        if (e0(e0Var, 1024L)) {
            hVar.a.r = e0Var.r;
        }
        Typeface typeface = null;
        if (e0(e0Var, 1536L)) {
            h.p[] pVarArr = hVar.a.q;
            if (pVarArr == null) {
                hVar.f935e.setPathEffect(null);
            } else {
                int length = pVarArr.length;
                int i4 = length % 2 == 0 ? length : length * 2;
                float[] fArr = new float[i4];
                float f2 = 0.0f;
                for (int i5 = 0; i5 < i4; i5++) {
                    fArr[i5] = hVar.a.q[i5 % length].c(this);
                    f2 += fArr[i5];
                }
                if (f2 == 0.0f) {
                    hVar.f935e.setPathEffect(null);
                } else {
                    float c2 = hVar.a.r.c(this);
                    if (c2 < 0.0f) {
                        c2 = (c2 % f2) + f2;
                    }
                    hVar.f935e.setPathEffect(new DashPathEffect(fArr, c2));
                }
            }
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT)) {
            float Y = Y();
            hVar.a.v = e0Var.v;
            hVar.d.setTextSize(e0Var.v.d(this, Y));
            hVar.f935e.setTextSize(e0Var.v.d(this, Y));
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER)) {
            hVar.a.u = e0Var.u;
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_TOP_BACK_LEFT)) {
            if (e0Var.w.intValue() == -1 && hVar.a.w.intValue() > 100) {
                h.e0 e0Var3 = hVar.a;
                e0Var3.w = Integer.valueOf(e0Var3.w.intValue() - 100);
            } else if (e0Var.w.intValue() == 1 && hVar.a.w.intValue() < 900) {
                h.e0 e0Var4 = hVar.a;
                e0Var4.w = Integer.valueOf(e0Var4.w.intValue() + 100);
            } else {
                hVar.a.w = e0Var.w;
            }
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_TOP_BACK_CENTER)) {
            hVar.a.x = e0Var.x;
        }
        if (e0(e0Var, 106496L)) {
            if (hVar.a.u != null && this.f916c != null) {
                com.caverock.androidsvg.j g2 = com.caverock.androidsvg.h.g();
                Iterator<String> it = hVar.a.u.iterator();
                Typeface typeface2 = null;
                while (true) {
                    if (!it.hasNext()) {
                        typeface = typeface2;
                        break;
                    }
                    String next = it.next();
                    h.e0 e0Var5 = hVar.a;
                    Typeface z = z(next, e0Var5.w, e0Var5.x);
                    if (z == null && g2 != null) {
                        g2.c(next, hVar.a.w.intValue(), String.valueOf(hVar.a.x));
                        throw null;
                    } else if (z != null) {
                        typeface = z;
                        break;
                    } else {
                        typeface2 = z;
                    }
                }
            }
            if (typeface == null) {
                h.e0 e0Var6 = hVar.a;
                typeface = z("serif", e0Var6.w, e0Var6.x);
            }
            hVar.d.setTypeface(typeface);
            hVar.f935e.setTypeface(typeface);
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_TOP_BACK_RIGHT)) {
            hVar.a.y = e0Var.y;
            Paint paint = hVar.d;
            h.e0.g gVar = e0Var.y;
            h.e0.g gVar2 = h.e0.g.LineThrough;
            paint.setStrikeThruText(gVar == gVar2);
            Paint paint2 = hVar.d;
            h.e0.g gVar3 = e0Var.y;
            h.e0.g gVar4 = h.e0.g.Underline;
            paint2.setUnderlineText(gVar3 == gVar4);
            if (Build.VERSION.SDK_INT >= 17) {
                hVar.f935e.setStrikeThruText(e0Var.y == gVar2);
                hVar.f935e.setUnderlineText(e0Var.y == gVar4);
            }
        }
        if (e0(e0Var, 68719476736L)) {
            hVar.a.z = e0Var.z;
        }
        if (e0(e0Var, 262144L)) {
            hVar.a.A = e0Var.A;
        }
        if (e0(e0Var, 524288L)) {
            hVar.a.B = e0Var.B;
        }
        if (e0(e0Var, 2097152L)) {
            hVar.a.D = e0Var.D;
        }
        if (e0(e0Var, 4194304L)) {
            hVar.a.E = e0Var.E;
        }
        if (e0(e0Var, 8388608L)) {
            hVar.a.F = e0Var.F;
        }
        if (e0(e0Var, 16777216L)) {
            hVar.a.G = e0Var.G;
        }
        if (e0(e0Var, 33554432L)) {
            hVar.a.H = e0Var.H;
        }
        if (e0(e0Var, 1048576L)) {
            hVar.a.C = e0Var.C;
        }
        if (e0(e0Var, 268435456L)) {
            hVar.a.K = e0Var.K;
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_STEREO_LEFT)) {
            hVar.a.L = e0Var.L;
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_STEREO_RIGHT)) {
            hVar.a.M = e0Var.M;
        }
        if (e0(e0Var, 67108864L)) {
            hVar.a.I = e0Var.I;
        }
        if (e0(e0Var, 134217728L)) {
            hVar.a.J = e0Var.J;
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT)) {
            hVar.a.P = e0Var.P;
        }
        if (e0(e0Var, IjkMediaMeta.AV_CH_SURROUND_DIRECT_RIGHT)) {
            hVar.a.Q = e0Var.Q;
        }
        if (e0(e0Var, 137438953472L)) {
            hVar.a.S = e0Var.S;
        }
    }

    private boolean e0(h.e0 e0Var, long j2) {
        return (j2 & e0Var.f858g) != 0;
    }

    private void e1(h hVar, h.l0 l0Var) {
        hVar.a.b(l0Var.b == null);
        h.e0 e0Var = l0Var.f898e;
        if (e0Var != null) {
            d1(hVar, e0Var);
        }
        if (this.f916c.m()) {
            for (b.p pVar : this.f916c.d()) {
                if (com.caverock.androidsvg.b.l(this.f920h, pVar.a, l0Var)) {
                    d1(hVar, pVar.b);
                }
            }
        }
        h.e0 e0Var2 = l0Var.f899f;
        if (e0Var2 != null) {
            d1(hVar, e0Var2);
        }
    }

    private void f0(boolean z, h.b bVar, h.m0 m0Var) {
        float f2;
        float d2;
        float f3;
        float f4;
        String str = m0Var.f893l;
        if (str != null) {
            P(m0Var, str);
        }
        Boolean bool = m0Var.f890i;
        int i2 = 0;
        boolean z2 = bool != null && bool.booleanValue();
        h hVar = this.d;
        Paint paint = z ? hVar.d : hVar.f935e;
        if (z2) {
            h.b a0 = a0();
            h.p pVar = m0Var.f902m;
            float e2 = pVar != null ? pVar.e(this) : 0.0f;
            h.p pVar2 = m0Var.f903n;
            float f5 = pVar2 != null ? pVar2.f(this) : 0.0f;
            h.p pVar3 = m0Var.o;
            float e3 = pVar3 != null ? pVar3.e(this) : a0.f853c;
            h.p pVar4 = m0Var.p;
            f4 = e3;
            f2 = e2;
            f3 = f5;
            d2 = pVar4 != null ? pVar4.f(this) : 0.0f;
        } else {
            h.p pVar5 = m0Var.f902m;
            float d3 = pVar5 != null ? pVar5.d(this, 1.0f) : 0.0f;
            h.p pVar6 = m0Var.f903n;
            float d4 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.0f;
            h.p pVar7 = m0Var.o;
            float d5 = pVar7 != null ? pVar7.d(this, 1.0f) : 1.0f;
            h.p pVar8 = m0Var.p;
            f2 = d3;
            d2 = pVar8 != null ? pVar8.d(this, 1.0f) : 0.0f;
            f3 = d4;
            f4 = d5;
        }
        a1();
        this.d = U(m0Var);
        Matrix matrix = new Matrix();
        if (!z2) {
            matrix.preTranslate(bVar.a, bVar.b);
            matrix.preScale(bVar.f853c, bVar.d);
        }
        Matrix matrix2 = m0Var.f891j;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = m0Var.f889h.size();
        if (size == 0) {
            Z0();
            if (z) {
                this.d.b = false;
                return;
            } else {
                this.d.f934c = false;
                return;
            }
        }
        int[] iArr = new int[size];
        float[] fArr = new float[size];
        float f6 = -1.0f;
        Iterator<h.n0> it = m0Var.f889h.iterator();
        while (it.hasNext()) {
            h.d0 d0Var = (h.d0) it.next();
            Float f7 = d0Var.f856h;
            float floatValue = f7 != null ? f7.floatValue() : 0.0f;
            if (i2 != 0 && floatValue < f6) {
                fArr[i2] = f6;
            } else {
                fArr[i2] = floatValue;
                f6 = floatValue;
            }
            a1();
            e1(this.d, d0Var);
            h.e0 e0Var = this.d.a;
            h.f fVar = (h.f) e0Var.I;
            if (fVar == null) {
                fVar = h.f.f875h;
            }
            iArr[i2] = F(fVar.f877g, e0Var.J.floatValue());
            i2++;
            Z0();
        }
        if ((f2 == f4 && f3 == d2) || size == 1) {
            Z0();
            paint.setColor(iArr[size - 1]);
            return;
        }
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        h.k kVar = m0Var.f892k;
        if (kVar != null) {
            if (kVar == h.k.reflect) {
                tileMode = Shader.TileMode.MIRROR;
            } else if (kVar == h.k.repeat) {
                tileMode = Shader.TileMode.REPEAT;
            }
        }
        Z0();
        LinearGradient linearGradient = new LinearGradient(f2, f3, f4, d2, iArr, fArr, tileMode);
        linearGradient.setLocalMatrix(matrix);
        paint.setShader(linearGradient);
        paint.setAlpha(C(this.d.a.f861j.floatValue()));
    }

    private void f1() {
        int i2;
        h.e0 e0Var = this.d.a;
        h.o0 o0Var = e0Var.P;
        if (o0Var instanceof h.f) {
            i2 = ((h.f) o0Var).f877g;
        } else if (!(o0Var instanceof h.g)) {
            return;
        } else {
            i2 = e0Var.t.f877g;
        }
        Float f2 = e0Var.Q;
        if (f2 != null) {
            i2 = F(i2, f2.floatValue());
        }
        this.a.drawColor(i2);
    }

    private Path g0(h.d dVar) {
        h.p pVar = dVar.o;
        float e2 = pVar != null ? pVar.e(this) : 0.0f;
        h.p pVar2 = dVar.p;
        float f2 = pVar2 != null ? pVar2.f(this) : 0.0f;
        float c2 = dVar.q.c(this);
        float f3 = e2 - c2;
        float f4 = f2 - c2;
        float f5 = e2 + c2;
        float f6 = f2 + c2;
        if (dVar.f895h == null) {
            float f7 = 2.0f * c2;
            dVar.f895h = new h.b(f3, f4, f7, f7);
        }
        float f8 = 0.5522848f * c2;
        Path path = new Path();
        path.moveTo(e2, f4);
        float f9 = e2 + f8;
        float f10 = f2 - f8;
        path.cubicTo(f9, f4, f5, f10, f5, f2);
        float f11 = f2 + f8;
        path.cubicTo(f5, f11, f9, f6, e2, f6);
        float f12 = e2 - f8;
        path.cubicTo(f12, f6, f3, f11, f3, f2);
        path.cubicTo(f3, f10, f12, f4, e2, f4);
        path.close();
        return path;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g1() {
        Boolean bool = this.d.a.H;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private void h(h.l lVar, Path path, Matrix matrix) {
        Path j0;
        e1(this.d, lVar);
        if (I() && g1()) {
            Matrix matrix2 = lVar.f896n;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            if (lVar instanceof h.b0) {
                j0 = k0((h.b0) lVar);
            } else if (lVar instanceof h.d) {
                j0 = g0((h.d) lVar);
            } else if (lVar instanceof h.i) {
                j0 = h0((h.i) lVar);
            } else if (!(lVar instanceof h.z)) {
                return;
            } else {
                j0 = j0((h.z) lVar);
            }
            u(lVar);
            path.setFillType(X());
            path.addPath(j0, matrix);
        }
    }

    private Path h0(h.i iVar) {
        h.p pVar = iVar.o;
        float e2 = pVar != null ? pVar.e(this) : 0.0f;
        h.p pVar2 = iVar.p;
        float f2 = pVar2 != null ? pVar2.f(this) : 0.0f;
        float e3 = iVar.q.e(this);
        float f3 = iVar.r.f(this);
        float f4 = e2 - e3;
        float f5 = f2 - f3;
        float f6 = e2 + e3;
        float f7 = f2 + f3;
        if (iVar.f895h == null) {
            iVar.f895h = new h.b(f4, f5, e3 * 2.0f, 2.0f * f3);
        }
        float f8 = e3 * 0.5522848f;
        float f9 = 0.5522848f * f3;
        Path path = new Path();
        path.moveTo(e2, f5);
        float f10 = e2 + f8;
        float f11 = f2 - f9;
        path.cubicTo(f10, f5, f6, f11, f6, f2);
        float f12 = f9 + f2;
        path.cubicTo(f6, f12, f10, f7, e2, f7);
        float f13 = e2 - f8;
        path.cubicTo(f13, f7, f4, f12, f4, f2);
        path.cubicTo(f4, f11, f13, f5, e2, f5);
        path.close();
        return path;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h1(String str, Object... objArr) {
        String.format(str, objArr);
    }

    private void i(h.v vVar, Path path, Matrix matrix) {
        e1(this.d, vVar);
        if (I() && g1()) {
            Matrix matrix2 = vVar.f896n;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            Path f2 = new d(this, vVar.o).f();
            if (vVar.f895h == null) {
                vVar.f895h = r(f2);
            }
            u(vVar);
            path.setFillType(X());
            path.addPath(f2, matrix);
        }
    }

    private Path i0(h.q qVar) {
        h.p pVar = qVar.o;
        float e2 = pVar == null ? 0.0f : pVar.e(this);
        h.p pVar2 = qVar.p;
        float f2 = pVar2 == null ? 0.0f : pVar2.f(this);
        h.p pVar3 = qVar.q;
        float e3 = pVar3 == null ? 0.0f : pVar3.e(this);
        h.p pVar4 = qVar.r;
        float f3 = pVar4 != null ? pVar4.f(this) : 0.0f;
        if (qVar.f895h == null) {
            qVar.f895h = new h.b(Math.min(e2, e3), Math.min(f2, f3), Math.abs(e3 - e2), Math.abs(f3 - f2));
        }
        Path path = new Path();
        path.moveTo(e2, f2);
        path.lineTo(e3, f3);
        return path;
    }

    private void j(h.n0 n0Var, boolean z, Path path, Matrix matrix) {
        if (I()) {
            E();
            if (n0Var instanceof h.e1) {
                if (z) {
                    l((h.e1) n0Var, path, matrix);
                } else {
                    N("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
                }
            } else if (n0Var instanceof h.v) {
                i((h.v) n0Var, path, matrix);
            } else if (n0Var instanceof h.w0) {
                k((h.w0) n0Var, path, matrix);
            } else if (n0Var instanceof h.l) {
                h((h.l) n0Var, path, matrix);
            } else {
                N("Invalid %s element found in clipPath definition", n0Var.toString());
            }
            D();
        }
    }

    private Path j0(h.z zVar) {
        Path path = new Path();
        float[] fArr = zVar.o;
        path.moveTo(fArr[0], fArr[1]);
        int i2 = 2;
        while (true) {
            float[] fArr2 = zVar.o;
            if (i2 >= fArr2.length) {
                break;
            }
            path.lineTo(fArr2[i2], fArr2[i2 + 1]);
            i2 += 2;
        }
        if (zVar instanceof h.a0) {
            path.close();
        }
        if (zVar.f895h == null) {
            zVar.f895h = r(path);
        }
        return path;
    }

    private void k(h.w0 w0Var, Path path, Matrix matrix) {
        e1(this.d, w0Var);
        if (I()) {
            Matrix matrix2 = w0Var.r;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            List<h.p> list = w0Var.f852n;
            float f2 = 0.0f;
            float e2 = (list == null || list.size() == 0) ? 0.0f : w0Var.f852n.get(0).e(this);
            List<h.p> list2 = w0Var.o;
            float f3 = (list2 == null || list2.size() == 0) ? 0.0f : w0Var.o.get(0).f(this);
            List<h.p> list3 = w0Var.p;
            float e3 = (list3 == null || list3.size() == 0) ? 0.0f : w0Var.p.get(0).e(this);
            List<h.p> list4 = w0Var.q;
            if (list4 != null && list4.size() != 0) {
                f2 = w0Var.q.get(0).f(this);
            }
            if (this.d.a.A != h.e0.f.Start) {
                float s = s(w0Var);
                if (this.d.a.A == h.e0.f.Middle) {
                    s /= 2.0f;
                }
                e2 -= s;
            }
            if (w0Var.f895h == null) {
                C0015i c0015i = new C0015i(e2, f3);
                M(w0Var, c0015i);
                RectF rectF = c0015i.f939c;
                w0Var.f895h = new h.b(rectF.left, rectF.top, rectF.width(), c0015i.f939c.height());
            }
            u(w0Var);
            Path path2 = new Path();
            M(w0Var, new g(e2 + e3, f3 + f2, path2));
            path.setFillType(X());
            path.addPath(path2, matrix);
        }
    }

    private Path k0(h.b0 b0Var) {
        float e2;
        float f2;
        Path path;
        h.p pVar = b0Var.s;
        if (pVar == null && b0Var.t == null) {
            e2 = 0.0f;
            f2 = 0.0f;
        } else {
            if (pVar == null) {
                e2 = b0Var.t.f(this);
            } else if (b0Var.t == null) {
                e2 = pVar.e(this);
            } else {
                e2 = pVar.e(this);
                f2 = b0Var.t.f(this);
            }
            f2 = e2;
        }
        float min = Math.min(e2, b0Var.q.e(this) / 2.0f);
        float min2 = Math.min(f2, b0Var.r.f(this) / 2.0f);
        h.p pVar2 = b0Var.o;
        float e3 = pVar2 != null ? pVar2.e(this) : 0.0f;
        h.p pVar3 = b0Var.p;
        float f3 = pVar3 != null ? pVar3.f(this) : 0.0f;
        float e4 = b0Var.q.e(this);
        float f4 = b0Var.r.f(this);
        if (b0Var.f895h == null) {
            b0Var.f895h = new h.b(e3, f3, e4, f4);
        }
        float f5 = e3 + e4;
        float f6 = f3 + f4;
        Path path2 = new Path();
        if (min != 0.0f && min2 != 0.0f) {
            float f7 = min * 0.5522848f;
            float f8 = 0.5522848f * min2;
            float f9 = f3 + min2;
            path2.moveTo(e3, f9);
            float f10 = f9 - f8;
            float f11 = e3 + min;
            float f12 = f11 - f7;
            path2.cubicTo(e3, f10, f12, f3, f11, f3);
            float f13 = f5 - min;
            path2.lineTo(f13, f3);
            float f14 = f13 + f7;
            path2.cubicTo(f14, f3, f5, f10, f5, f9);
            float f15 = f6 - min2;
            path2.lineTo(f5, f15);
            float f16 = f15 + f8;
            path = path2;
            path2.cubicTo(f5, f16, f14, f6, f13, f6);
            path.lineTo(f11, f6);
            path.cubicTo(f12, f6, e3, f16, e3, f15);
            path.lineTo(e3, f9);
        } else {
            path = path2;
            path.moveTo(e3, f3);
            path.lineTo(f5, f3);
            path.lineTo(f5, f6);
            path.lineTo(e3, f6);
            path.lineTo(e3, f3);
        }
        path.close();
        return path;
    }

    private void l(h.e1 e1Var, Path path, Matrix matrix) {
        e1(this.d, e1Var);
        if (I() && g1()) {
            Matrix matrix2 = e1Var.f901n;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            h.n0 q = e1Var.a.q(e1Var.o);
            if (q == null) {
                N("Use reference '%s' not found", e1Var.o);
                return;
            }
            u(e1Var);
            j(q, false, path, matrix);
        }
    }

    private Path l0(h.w0 w0Var) {
        List<h.p> list = w0Var.f852n;
        float f2 = 0.0f;
        float e2 = (list == null || list.size() == 0) ? 0.0f : w0Var.f852n.get(0).e(this);
        List<h.p> list2 = w0Var.o;
        float f3 = (list2 == null || list2.size() == 0) ? 0.0f : w0Var.o.get(0).f(this);
        List<h.p> list3 = w0Var.p;
        float e3 = (list3 == null || list3.size() == 0) ? 0.0f : w0Var.p.get(0).e(this);
        List<h.p> list4 = w0Var.q;
        if (list4 != null && list4.size() != 0) {
            f2 = w0Var.q.get(0).f(this);
        }
        if (this.d.a.A != h.e0.f.Start) {
            float s = s(w0Var);
            if (this.d.a.A == h.e0.f.Middle) {
                s /= 2.0f;
            }
            e2 -= s;
        }
        if (w0Var.f895h == null) {
            C0015i c0015i = new C0015i(e2, f3);
            M(w0Var, c0015i);
            RectF rectF = c0015i.f939c;
            w0Var.f895h = new h.b(rectF.left, rectF.top, rectF.width(), c0015i.f939c.height());
        }
        Path path = new Path();
        M(w0Var, new g(e2 + e3, f3 + f2, path));
        return path;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m(float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, float f7, float f8, h.x xVar) {
        float f9;
        h.x xVar2;
        if (f2 == f7 && f3 == f8) {
            return;
        }
        if (f4 == 0.0f) {
            f9 = f7;
            xVar2 = xVar;
        } else if (f5 != 0.0f) {
            float abs = Math.abs(f4);
            float abs2 = Math.abs(f5);
            double d2 = f6;
            Double.isNaN(d2);
            double radians = Math.toRadians(d2 % 360.0d);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f2 - f7;
            Double.isNaN(d3);
            double d4 = d3 / 2.0d;
            double d5 = f3 - f8;
            Double.isNaN(d5);
            double d6 = d5 / 2.0d;
            double d7 = (cos * d4) + (sin * d6);
            double d8 = ((-sin) * d4) + (d6 * cos);
            double d9 = abs * abs;
            double d10 = abs2 * abs2;
            double d11 = d7 * d7;
            double d12 = d8 * d8;
            Double.isNaN(d9);
            Double.isNaN(d10);
            double d13 = (d11 / d9) + (d12 / d10);
            if (d13 > 0.99999d) {
                double sqrt = Math.sqrt(d13) * 1.00001d;
                double d14 = abs;
                Double.isNaN(d14);
                abs = (float) (d14 * sqrt);
                double d15 = abs2;
                Double.isNaN(d15);
                abs2 = (float) (sqrt * d15);
                d9 = abs * abs;
                d10 = abs2 * abs2;
            }
            double d16 = z == z2 ? -1.0d : 1.0d;
            double d17 = d9 * d10;
            double d18 = d9 * d12;
            double d19 = d10 * d11;
            double d20 = ((d17 - d18) - d19) / (d18 + d19);
            if (d20 < 0.0d) {
                d20 = 0.0d;
            }
            double sqrt2 = d16 * Math.sqrt(d20);
            double d21 = abs;
            Double.isNaN(d21);
            double d22 = abs2;
            Double.isNaN(d22);
            double d23 = ((d21 * d8) / d22) * sqrt2;
            Double.isNaN(d22);
            Double.isNaN(d21);
            float f10 = abs;
            float f11 = abs2;
            double d24 = sqrt2 * (-((d22 * d7) / d21));
            double d25 = f2 + f7;
            Double.isNaN(d25);
            double d26 = f3 + f8;
            Double.isNaN(d26);
            double d27 = (d25 / 2.0d) + ((cos * d23) - (sin * d24));
            double d28 = (d26 / 2.0d) + (sin * d23) + (cos * d24);
            Double.isNaN(d21);
            double d29 = (d7 - d23) / d21;
            Double.isNaN(d22);
            double d30 = (d8 - d24) / d22;
            Double.isNaN(d21);
            double d31 = ((-d7) - d23) / d21;
            Double.isNaN(d22);
            double d32 = ((-d8) - d24) / d22;
            double d33 = (d29 * d29) + (d30 * d30);
            double acos = (d30 < 0.0d ? -1.0d : 1.0d) * Math.acos(d29 / Math.sqrt(d33));
            double B = ((d29 * d32) - (d30 * d31) >= 0.0d ? 1.0d : -1.0d) * B(((d29 * d31) + (d30 * d32)) / Math.sqrt(d33 * ((d31 * d31) + (d32 * d32))));
            if (!z2 && B > 0.0d) {
                B -= 6.283185307179586d;
            } else if (z2 && B < 0.0d) {
                B += 6.283185307179586d;
            }
            float[] n2 = n(acos % 6.283185307179586d, B % 6.283185307179586d);
            Matrix matrix = new Matrix();
            matrix.postScale(f10, f11);
            matrix.postRotate(f6);
            matrix.postTranslate((float) d27, (float) d28);
            matrix.mapPoints(n2);
            n2[n2.length - 2] = f7;
            n2[n2.length - 1] = f8;
            for (int i2 = 0; i2 < n2.length; i2 += 6) {
                xVar.c(n2[i2], n2[i2 + 1], n2[i2 + 2], n2[i2 + 3], n2[i2 + 4], n2[i2 + 5]);
            }
            return;
        } else {
            xVar2 = xVar;
            f9 = f7;
        }
        xVar2.e(f9, f8);
    }

    private void m0(boolean z, h.b bVar, h.q0 q0Var) {
        float f2;
        float d2;
        float f3;
        String str = q0Var.f893l;
        if (str != null) {
            P(q0Var, str);
        }
        Boolean bool = q0Var.f890i;
        int i2 = 0;
        boolean z2 = bool != null && bool.booleanValue();
        h hVar = this.d;
        Paint paint = z ? hVar.d : hVar.f935e;
        if (z2) {
            h.p pVar = new h.p(50.0f, h.d1.percent);
            h.p pVar2 = q0Var.f907m;
            float e2 = pVar2 != null ? pVar2.e(this) : pVar.e(this);
            h.p pVar3 = q0Var.f908n;
            float f4 = pVar3 != null ? pVar3.f(this) : pVar.f(this);
            h.p pVar4 = q0Var.o;
            d2 = pVar4 != null ? pVar4.c(this) : pVar.c(this);
            f2 = e2;
            f3 = f4;
        } else {
            h.p pVar5 = q0Var.f907m;
            float d3 = pVar5 != null ? pVar5.d(this, 1.0f) : 0.5f;
            h.p pVar6 = q0Var.f908n;
            float d4 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.5f;
            h.p pVar7 = q0Var.o;
            f2 = d3;
            d2 = pVar7 != null ? pVar7.d(this, 1.0f) : 0.5f;
            f3 = d4;
        }
        a1();
        this.d = U(q0Var);
        Matrix matrix = new Matrix();
        if (!z2) {
            matrix.preTranslate(bVar.a, bVar.b);
            matrix.preScale(bVar.f853c, bVar.d);
        }
        Matrix matrix2 = q0Var.f891j;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = q0Var.f889h.size();
        if (size == 0) {
            Z0();
            if (z) {
                this.d.b = false;
                return;
            } else {
                this.d.f934c = false;
                return;
            }
        }
        int[] iArr = new int[size];
        float[] fArr = new float[size];
        float f5 = -1.0f;
        Iterator<h.n0> it = q0Var.f889h.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            h.d0 d0Var = (h.d0) it.next();
            Float f6 = d0Var.f856h;
            float floatValue = f6 != null ? f6.floatValue() : 0.0f;
            if (i2 != 0 && floatValue < f5) {
                fArr[i2] = f5;
            } else {
                fArr[i2] = floatValue;
                f5 = floatValue;
            }
            a1();
            e1(this.d, d0Var);
            h.e0 e0Var = this.d.a;
            h.f fVar = (h.f) e0Var.I;
            if (fVar == null) {
                fVar = h.f.f875h;
            }
            iArr[i2] = F(fVar.f877g, e0Var.J.floatValue());
            i2++;
            Z0();
        }
        if (d2 != 0.0f && size != 1) {
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            h.k kVar = q0Var.f892k;
            if (kVar != null) {
                if (kVar == h.k.reflect) {
                    tileMode = Shader.TileMode.MIRROR;
                } else if (kVar == h.k.repeat) {
                    tileMode = Shader.TileMode.REPEAT;
                }
            }
            Z0();
            RadialGradient radialGradient = new RadialGradient(f2, f3, d2, iArr, fArr, tileMode);
            radialGradient.setLocalMatrix(matrix);
            paint.setShader(radialGradient);
            paint.setAlpha(C(this.d.a.f861j.floatValue()));
            return;
        }
        Z0();
        paint.setColor(iArr[size - 1]);
    }

    private static float[] n(double d2, double d3) {
        int ceil = (int) Math.ceil((Math.abs(d3) * 2.0d) / 3.141592653589793d);
        double d4 = ceil;
        Double.isNaN(d4);
        double d5 = d3 / d4;
        double d6 = d5 / 2.0d;
        double sin = (Math.sin(d6) * 1.3333333333333333d) / (Math.cos(d6) + 1.0d);
        float[] fArr = new float[ceil * 6];
        int i2 = 0;
        for (int i3 = 0; i3 < ceil; i3++) {
            double d7 = i3;
            Double.isNaN(d7);
            double d8 = d2 + (d7 * d5);
            double cos = Math.cos(d8);
            double sin2 = Math.sin(d8);
            int i4 = i2 + 1;
            fArr[i2] = (float) (cos - (sin * sin2));
            int i5 = i4 + 1;
            fArr[i4] = (float) (sin2 + (cos * sin));
            d5 = d5;
            double d9 = d8 + d5;
            double cos2 = Math.cos(d9);
            double sin3 = Math.sin(d9);
            int i6 = i5 + 1;
            fArr[i5] = (float) ((sin * sin3) + cos2);
            int i7 = i6 + 1;
            fArr[i6] = (float) (sin3 - (sin * cos2));
            int i8 = i7 + 1;
            fArr[i7] = (float) cos2;
            i2 = i8 + 1;
            fArr[i8] = (float) sin3;
        }
        return fArr;
    }

    private h.b n0(h.p pVar, h.p pVar2, h.p pVar3, h.p pVar4) {
        float e2 = pVar != null ? pVar.e(this) : 0.0f;
        float f2 = pVar2 != null ? pVar2.f(this) : 0.0f;
        h.b a0 = a0();
        return new h.b(e2, f2, pVar3 != null ? pVar3.e(this) : a0.f853c, pVar4 != null ? pVar4.f(this) : a0.d);
    }

    @TargetApi(19)
    private Path o(h.k0 k0Var, h.b bVar) {
        Path o0;
        h.n0 q = k0Var.a.q(this.d.a.K);
        if (q == null) {
            N("ClipPath reference '%s' not found", this.d.a.K);
            return null;
        }
        h.e eVar = (h.e) q;
        this.f917e.push(this.d);
        this.d = U(eVar);
        Boolean bool = eVar.o;
        boolean z = bool == null || bool.booleanValue();
        Matrix matrix = new Matrix();
        if (!z) {
            matrix.preTranslate(bVar.a, bVar.b);
            matrix.preScale(bVar.f853c, bVar.d);
        }
        Matrix matrix2 = eVar.f901n;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        Path path = new Path();
        for (h.n0 n0Var : eVar.f879i) {
            if ((n0Var instanceof h.k0) && (o0 = o0((h.k0) n0Var, true)) != null) {
                path.op(o0, Path.Op.UNION);
            }
        }
        if (this.d.a.K != null) {
            if (eVar.f895h == null) {
                eVar.f895h = r(path);
            }
            Path o = o(eVar, eVar.f895h);
            if (o != null) {
                path.op(o, Path.Op.INTERSECT);
            }
        }
        path.transform(matrix);
        this.d = this.f917e.pop();
        return path;
    }

    @TargetApi(19)
    private Path o0(h.k0 k0Var, boolean z) {
        Path l0;
        Path o;
        this.f917e.push(this.d);
        h hVar = new h(this, this.d);
        this.d = hVar;
        e1(hVar, k0Var);
        if (I() && g1()) {
            if (k0Var instanceof h.e1) {
                if (!z) {
                    N("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
                }
                h.e1 e1Var = (h.e1) k0Var;
                h.n0 q = k0Var.a.q(e1Var.o);
                if (q == null) {
                    N("Use reference '%s' not found", e1Var.o);
                    this.d = this.f917e.pop();
                    return null;
                } else if (!(q instanceof h.k0)) {
                    this.d = this.f917e.pop();
                    return null;
                } else {
                    l0 = o0((h.k0) q, false);
                    if (l0 == null) {
                        return null;
                    }
                    if (e1Var.f895h == null) {
                        e1Var.f895h = r(l0);
                    }
                    Matrix matrix = e1Var.f901n;
                    if (matrix != null) {
                        l0.transform(matrix);
                    }
                }
            } else if (k0Var instanceof h.l) {
                h.l lVar = (h.l) k0Var;
                if (k0Var instanceof h.v) {
                    l0 = new d(this, ((h.v) k0Var).o).f();
                    if (k0Var.f895h == null) {
                        k0Var.f895h = r(l0);
                    }
                } else {
                    l0 = k0Var instanceof h.b0 ? k0((h.b0) k0Var) : k0Var instanceof h.d ? g0((h.d) k0Var) : k0Var instanceof h.i ? h0((h.i) k0Var) : k0Var instanceof h.z ? j0((h.z) k0Var) : null;
                }
                if (l0 == null) {
                    return null;
                }
                if (lVar.f895h == null) {
                    lVar.f895h = r(l0);
                }
                Matrix matrix2 = lVar.f896n;
                if (matrix2 != null) {
                    l0.transform(matrix2);
                }
                l0.setFillType(X());
            } else if (!(k0Var instanceof h.w0)) {
                N("Invalid %s element found in clipPath definition", k0Var.n());
                return null;
            } else {
                h.w0 w0Var = (h.w0) k0Var;
                l0 = l0(w0Var);
                if (l0 == null) {
                    return null;
                }
                Matrix matrix3 = w0Var.r;
                if (matrix3 != null) {
                    l0.transform(matrix3);
                }
                l0.setFillType(X());
            }
            if (this.d.a.K != null && (o = o(k0Var, k0Var.f895h)) != null) {
                l0.op(o, Path.Op.INTERSECT);
            }
            this.d = this.f917e.pop();
            return l0;
        }
        this.d = this.f917e.pop();
        return null;
    }

    private List<c> p(h.q qVar) {
        h.p pVar = qVar.o;
        float e2 = pVar != null ? pVar.e(this) : 0.0f;
        h.p pVar2 = qVar.p;
        float f2 = pVar2 != null ? pVar2.f(this) : 0.0f;
        h.p pVar3 = qVar.q;
        float e3 = pVar3 != null ? pVar3.e(this) : 0.0f;
        h.p pVar4 = qVar.r;
        float f3 = pVar4 != null ? pVar4.f(this) : 0.0f;
        ArrayList arrayList = new ArrayList(2);
        float f4 = e3 - e2;
        float f5 = f3 - f2;
        arrayList.add(new c(this, e2, f2, f4, f5));
        arrayList.add(new c(this, e3, f3, f4, f5));
        return arrayList;
    }

    private void p0() {
        this.f918f.pop();
        this.f919g.pop();
    }

    private List<c> q(h.z zVar) {
        int length = zVar.o.length;
        int i2 = 2;
        if (length < 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        float[] fArr = zVar.o;
        c cVar = new c(this, fArr[0], fArr[1], 0.0f, 0.0f);
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (i2 < length) {
            float[] fArr2 = zVar.o;
            float f4 = fArr2[i2];
            float f5 = fArr2[i2 + 1];
            cVar.a(f4, f5);
            arrayList.add(cVar);
            i2 += 2;
            cVar = new c(this, f4, f5, f4 - cVar.a, f5 - cVar.b);
            f3 = f5;
            f2 = f4;
        }
        if (zVar instanceof h.a0) {
            float[] fArr3 = zVar.o;
            if (f2 != fArr3[0] && f3 != fArr3[1]) {
                float f6 = fArr3[0];
                float f7 = fArr3[1];
                cVar.a(f6, f7);
                arrayList.add(cVar);
                c cVar2 = new c(this, f6, f7, f6 - cVar.a, f7 - cVar.b);
                cVar2.b((c) arrayList.get(0));
                arrayList.add(cVar2);
                arrayList.set(0, cVar2);
            }
        } else {
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private void q0(h.j0 j0Var) {
        this.f918f.push(j0Var);
        this.f919g.push(this.a.getMatrix());
    }

    private h.b r(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new h.b(rectF.left, rectF.top, rectF.width(), rectF.height());
    }

    private void r0(h.k0 k0Var) {
        s0(k0Var, k0Var.f895h);
    }

    private float s(h.y0 y0Var) {
        k kVar = new k(this, null);
        M(y0Var, kVar);
        return kVar.a;
    }

    private void s0(h.k0 k0Var, h.b bVar) {
        if (this.d.a.M != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.a.saveLayer(null, paint, 31);
            Paint paint2 = new Paint();
            paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f})));
            this.a.saveLayer(null, paint2, 31);
            h.s sVar = (h.s) this.f916c.q(this.d.a.M);
            R0(sVar, k0Var, bVar);
            this.a.restore();
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.a.saveLayer(null, paint3, 31);
            R0(sVar, k0Var, bVar);
            this.a.restore();
            this.a.restore();
        }
        Z0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r12 != 8) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Matrix t(h.b bVar, h.b bVar2, com.caverock.androidsvg.f fVar) {
        int i2;
        float f2;
        float f3;
        Matrix matrix = new Matrix();
        if (fVar != null && fVar.a() != null) {
            float f4 = bVar.f853c / bVar2.f853c;
            float f5 = bVar.d / bVar2.d;
            float f6 = -bVar2.a;
            float f7 = -bVar2.b;
            if (fVar.equals(com.caverock.androidsvg.f.f843c)) {
                matrix.preTranslate(bVar.a, bVar.b);
                matrix.preScale(f4, f5);
                matrix.preTranslate(f6, f7);
                return matrix;
            }
            float max = fVar.b() == f.b.slice ? Math.max(f4, f5) : Math.min(f4, f5);
            float f8 = bVar.f853c / max;
            float f9 = bVar.d / max;
            int[] iArr = a.a;
            switch (iArr[fVar.a().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    f3 = (bVar2.f853c - f8) / 2.0f;
                    f6 -= f3;
                    i2 = iArr[fVar.a().ordinal()];
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 5) {
                                if (i2 != 6) {
                                    if (i2 != 7) {
                                        break;
                                    }
                                }
                            }
                        }
                        f2 = bVar2.d - f9;
                        f7 -= f2;
                        matrix.preTranslate(bVar.a, bVar.b);
                        matrix.preScale(max, max);
                        matrix.preTranslate(f6, f7);
                        break;
                    }
                    f2 = (bVar2.d - f9) / 2.0f;
                    f7 -= f2;
                    matrix.preTranslate(bVar.a, bVar.b);
                    matrix.preScale(max, max);
                    matrix.preTranslate(f6, f7);
                case 4:
                case 5:
                case 6:
                    f3 = bVar2.f853c - f8;
                    f6 -= f3;
                    i2 = iArr[fVar.a().ordinal()];
                    if (i2 != 2) {
                    }
                    f2 = (bVar2.d - f9) / 2.0f;
                    f7 -= f2;
                    matrix.preTranslate(bVar.a, bVar.b);
                    matrix.preScale(max, max);
                    matrix.preTranslate(f6, f7);
                    break;
                default:
                    i2 = iArr[fVar.a().ordinal()];
                    if (i2 != 2) {
                    }
                    f2 = (bVar2.d - f9) / 2.0f;
                    f7 -= f2;
                    matrix.preTranslate(bVar.a, bVar.b);
                    matrix.preScale(max, max);
                    matrix.preTranslate(f6, f7);
                    break;
            }
        }
        return matrix;
    }

    private void t0(h.n0 n0Var, j jVar) {
        float f2;
        float f3;
        float f4;
        h.e0.f W;
        if (jVar.a((h.y0) n0Var)) {
            if (n0Var instanceof h.z0) {
                a1();
                T0((h.z0) n0Var);
                Z0();
                return;
            }
            if (n0Var instanceof h.v0) {
                G("TSpan render", new Object[0]);
                a1();
                h.v0 v0Var = (h.v0) n0Var;
                e1(this.d, v0Var);
                if (I()) {
                    List<h.p> list = v0Var.f852n;
                    boolean z = list != null && list.size() > 0;
                    boolean z2 = jVar instanceof f;
                    float f5 = 0.0f;
                    if (z2) {
                        float e2 = !z ? ((f) jVar).a : v0Var.f852n.get(0).e(this);
                        List<h.p> list2 = v0Var.o;
                        f3 = (list2 == null || list2.size() == 0) ? ((f) jVar).b : v0Var.o.get(0).f(this);
                        List<h.p> list3 = v0Var.p;
                        f4 = (list3 == null || list3.size() == 0) ? 0.0f : v0Var.p.get(0).e(this);
                        List<h.p> list4 = v0Var.q;
                        if (list4 != null && list4.size() != 0) {
                            f5 = v0Var.q.get(0).f(this);
                        }
                        f2 = f5;
                        f5 = e2;
                    } else {
                        f2 = 0.0f;
                        f3 = 0.0f;
                        f4 = 0.0f;
                    }
                    if (z && (W = W()) != h.e0.f.Start) {
                        float s = s(v0Var);
                        if (W == h.e0.f.Middle) {
                            s /= 2.0f;
                        }
                        f5 -= s;
                    }
                    x((h.k0) v0Var.d());
                    if (z2) {
                        f fVar = (f) jVar;
                        fVar.a = f5 + f4;
                        fVar.b = f3 + f2;
                    }
                    boolean u0 = u0();
                    M(v0Var, jVar);
                    if (u0) {
                        r0(v0Var);
                    }
                }
                Z0();
            } else if (n0Var instanceof h.u0) {
                a1();
                h.u0 u0Var = (h.u0) n0Var;
                e1(this.d, u0Var);
                if (I()) {
                    x((h.k0) u0Var.d());
                    h.n0 q = n0Var.a.q(u0Var.f912n);
                    if (q == null || !(q instanceof h.y0)) {
                        N("Tref reference '%s' not found", u0Var.f912n);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        O((h.y0) q, sb);
                        if (sb.length() > 0) {
                            jVar.b(sb.toString());
                        }
                    }
                }
                Z0();
            }
        }
    }

    private void u(h.k0 k0Var) {
        v(k0Var, k0Var.f895h);
    }

    private boolean u0() {
        h.n0 q;
        if (U0()) {
            this.a.saveLayerAlpha(null, C(this.d.a.s.floatValue()), 31);
            this.f917e.push(this.d);
            h hVar = new h(this, this.d);
            this.d = hVar;
            String str = hVar.a.M;
            if (str != null && ((q = this.f916c.q(str)) == null || !(q instanceof h.s))) {
                N("Mask reference '%s' not found", this.d.a.M);
                this.d.a.M = null;
            }
            return true;
        }
        return false;
    }

    private void v(h.k0 k0Var, h.b bVar) {
        if (this.d.a.K == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            Path o = o(k0Var, bVar);
            if (o != null) {
                this.a.clipPath(o);
                return;
            }
            return;
        }
        w(k0Var, bVar);
    }

    private c v0(c cVar, c cVar2, c cVar3) {
        float L = L(cVar2.f928c, cVar2.d, cVar2.a - cVar.a, cVar2.b - cVar.b);
        if (L == 0.0f) {
            L = L(cVar2.f928c, cVar2.d, cVar3.a - cVar2.a, cVar3.b - cVar2.b);
        }
        if (L > 0.0f) {
            return cVar2;
        }
        if (L != 0.0f || (cVar2.f928c <= 0.0f && cVar2.d < 0.0f)) {
            cVar2.f928c = -cVar2.f928c;
            cVar2.d = -cVar2.d;
            return cVar2;
        }
        return cVar2;
    }

    private void w(h.k0 k0Var, h.b bVar) {
        h.n0 q = k0Var.a.q(this.d.a.K);
        if (q == null) {
            N("ClipPath reference '%s' not found", this.d.a.K);
            return;
        }
        h.e eVar = (h.e) q;
        if (eVar.f879i.isEmpty()) {
            this.a.clipRect(0, 0, 0, 0);
            return;
        }
        Boolean bool = eVar.o;
        boolean z = bool == null || bool.booleanValue();
        if ((k0Var instanceof h.m) && !z) {
            h1("<clipPath clipPathUnits=\"objectBoundingBox\"> is not supported when referenced from container elements (like %s)", k0Var.n());
            return;
        }
        E();
        if (!z) {
            Matrix matrix = new Matrix();
            matrix.preTranslate(bVar.a, bVar.b);
            matrix.preScale(bVar.f853c, bVar.d);
            this.a.concat(matrix);
        }
        Matrix matrix2 = eVar.f901n;
        if (matrix2 != null) {
            this.a.concat(matrix2);
        }
        this.d = U(eVar);
        u(eVar);
        Path path = new Path();
        Iterator<h.n0> it = eVar.f879i.iterator();
        while (it.hasNext()) {
            j(it.next(), true, path, new Matrix());
        }
        this.a.clipPath(path);
        D();
    }

    private void w0(h.d dVar) {
        G("Circle render", new Object[0]);
        h.p pVar = dVar.q;
        if (pVar == null || pVar.h()) {
            return;
        }
        e1(this.d, dVar);
        if (I() && g1()) {
            Matrix matrix = dVar.f896n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            Path g0 = g0(dVar);
            c1(dVar);
            x(dVar);
            u(dVar);
            boolean u0 = u0();
            if (this.d.b) {
                J(dVar, g0);
            }
            if (this.d.f934c) {
                K(g0);
            }
            if (u0) {
                r0(dVar);
            }
        }
    }

    private void x(h.k0 k0Var) {
        h.o0 o0Var = this.d.a.f859h;
        if (o0Var instanceof h.u) {
            H(true, k0Var.f895h, (h.u) o0Var);
        }
        h.o0 o0Var2 = this.d.a.f862k;
        if (o0Var2 instanceof h.u) {
            H(false, k0Var.f895h, (h.u) o0Var2);
        }
    }

    private void x0(h.i iVar) {
        G("Ellipse render", new Object[0]);
        h.p pVar = iVar.q;
        if (pVar == null || iVar.r == null || pVar.h() || iVar.r.h()) {
            return;
        }
        e1(this.d, iVar);
        if (I() && g1()) {
            Matrix matrix = iVar.f896n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            Path h0 = h0(iVar);
            c1(iVar);
            x(iVar);
            u(iVar);
            boolean u0 = u0();
            if (this.d.b) {
                J(iVar, h0);
            }
            if (this.d.f934c) {
                K(h0);
            }
            if (u0) {
                r0(iVar);
            }
        }
    }

    private Bitmap y(String str) {
        int indexOf;
        if (str.startsWith("data:") && str.length() >= 14 && (indexOf = str.indexOf(44)) >= 12 && ";base64".equals(str.substring(indexOf - 7, indexOf))) {
            try {
                byte[] decode = Base64.decode(str.substring(indexOf + 1), 0);
                return BitmapFactory.decodeByteArray(decode, 0, decode.length);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    private void y0(h.m mVar) {
        G("Group render", new Object[0]);
        e1(this.d, mVar);
        if (I()) {
            Matrix matrix = mVar.f901n;
            if (matrix != null) {
                this.a.concat(matrix);
            }
            u(mVar);
            boolean u0 = u0();
            N0(mVar, true);
            if (u0) {
                r0(mVar);
            }
            c1(mVar);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
        if (r6.equals("monospace") == false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Typeface z(String str, Integer num, h.e0.b bVar) {
        char c2 = 1;
        boolean z = bVar == h.e0.b.Italic;
        int i2 = num.intValue() > 500 ? z ? 3 : 1 : z ? 2 : 0;
        str.hashCode();
        switch (str.hashCode()) {
            case -1536685117:
                if (str.equals("sans-serif")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1431958525:
                break;
            case -1081737434:
                if (str.equals("fantasy")) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case 109326717:
                if (str.equals("serif")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1126973893:
                if (str.equals("cursive")) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                return Typeface.create(Typeface.SANS_SERIF, i2);
            case 1:
                return Typeface.create(Typeface.MONOSPACE, i2);
            case 2:
                return Typeface.create(Typeface.SANS_SERIF, i2);
            case 3:
                return Typeface.create(Typeface.SERIF, i2);
            case 4:
                return Typeface.create(Typeface.SANS_SERIF, i2);
            default:
                return null;
        }
    }

    private void z0(h.o oVar) {
        h.p pVar;
        String str;
        G("Image render", new Object[0]);
        h.p pVar2 = oVar.r;
        if (pVar2 == null || pVar2.h() || (pVar = oVar.s) == null || pVar.h() || (str = oVar.o) == null) {
            return;
        }
        com.caverock.androidsvg.f fVar = oVar.f906n;
        if (fVar == null) {
            fVar = com.caverock.androidsvg.f.d;
        }
        Bitmap y = y(str);
        if (y == null) {
            com.caverock.androidsvg.j g2 = com.caverock.androidsvg.h.g();
            if (g2 == null) {
                return;
            }
            g2.d(oVar.o);
            throw null;
        } else if (y == null) {
            N("Could not locate image '%s'", oVar.o);
        } else {
            h.b bVar = new h.b(0.0f, 0.0f, y.getWidth(), y.getHeight());
            e1(this.d, oVar);
            if (I() && g1()) {
                Matrix matrix = oVar.t;
                if (matrix != null) {
                    this.a.concat(matrix);
                }
                h.p pVar3 = oVar.p;
                float e2 = pVar3 != null ? pVar3.e(this) : 0.0f;
                h.p pVar4 = oVar.q;
                float f2 = pVar4 != null ? pVar4.f(this) : 0.0f;
                float e3 = oVar.r.e(this);
                float e4 = oVar.s.e(this);
                h hVar = this.d;
                hVar.f936f = new h.b(e2, f2, e3, e4);
                if (!hVar.a.B.booleanValue()) {
                    h.b bVar2 = this.d.f936f;
                    W0(bVar2.a, bVar2.b, bVar2.f853c, bVar2.d);
                }
                oVar.f895h = this.d.f936f;
                c1(oVar);
                u(oVar);
                boolean u0 = u0();
                f1();
                this.a.save();
                this.a.concat(t(this.d.f936f, bVar, fVar));
                this.a.drawBitmap(y, 0.0f, 0.0f, new Paint(this.d.a.S != h.e0.e.optimizeSpeed ? 2 : 0));
                this.a.restore();
                if (u0) {
                    r0(oVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O0(com.caverock.androidsvg.h hVar, com.caverock.androidsvg.g gVar) {
        h.b bVar;
        com.caverock.androidsvg.f fVar;
        if (gVar != null) {
            this.f916c = hVar;
            h.f0 l2 = hVar.l();
            if (l2 == null) {
                h1("Nothing to render. Document is empty.", new Object[0]);
                return;
            }
            if (gVar.e()) {
                h.l0 f2 = this.f916c.f(gVar.f847e);
                if (f2 == null || !(f2 instanceof h.f1)) {
                    String.format("View element with id \"%s\" not found.", gVar.f847e);
                    return;
                }
                h.f1 f1Var = (h.f1) f2;
                bVar = f1Var.o;
                if (bVar == null) {
                    String.format("View element with id \"%s\" is missing a viewBox attribute.", gVar.f847e);
                    return;
                }
                fVar = f1Var.f906n;
            } else {
                bVar = gVar.f() ? gVar.d : l2.o;
                fVar = gVar.c() ? gVar.b : l2.f906n;
            }
            if (gVar.b()) {
                hVar.a(gVar.a);
            }
            if (gVar.d()) {
                b.q qVar = new b.q();
                this.f920h = qVar;
                qVar.a = hVar.f(gVar.f846c);
            }
            V0();
            A(l2);
            a1();
            h.b bVar2 = new h.b(gVar.f848f);
            h.p pVar = l2.r;
            if (pVar != null) {
                bVar2.f853c = pVar.d(this, bVar2.f853c);
            }
            h.p pVar2 = l2.s;
            if (pVar2 != null) {
                bVar2.d = pVar2.d(this, bVar2.d);
            }
            H0(l2, bVar2, bVar, fVar);
            Z0();
            if (gVar.b()) {
                hVar.b();
                return;
            }
            return;
        }
        throw new NullPointerException("renderOptions shouldn't be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float Y() {
        return this.d.d.getTextSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float Z() {
        return this.d.d.getTextSize() / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h.b a0() {
        h hVar = this.d;
        h.b bVar = hVar.f937g;
        return bVar != null ? bVar : hVar.f936f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b0() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class k extends j {
        float a;

        private k() {
            super(i.this, null);
            this.a = 0.0f;
        }

        @Override // com.caverock.androidsvg.i.j
        public void b(String str) {
            this.a += i.this.d.d.measureText(str);
        }

        /* synthetic */ k(i iVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class h {
        h.e0 a;
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        boolean f934c;
        Paint d;

        /* renamed from: e  reason: collision with root package name */
        Paint f935e;

        /* renamed from: f  reason: collision with root package name */
        h.b f936f;

        /* renamed from: g  reason: collision with root package name */
        h.b f937g;

        /* renamed from: h  reason: collision with root package name */
        boolean f938h;

        h(i iVar) {
            Paint paint = new Paint();
            this.d = paint;
            paint.setFlags(R2.anim.slide_out_from_left);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 14) {
                this.d.setHinting(0);
            }
            this.d.setStyle(Paint.Style.FILL);
            this.d.setTypeface(Typeface.DEFAULT);
            Paint paint2 = new Paint();
            this.f935e = paint2;
            paint2.setFlags(R2.anim.slide_out_from_left);
            if (i2 >= 14) {
                this.f935e.setHinting(0);
            }
            this.f935e.setStyle(Paint.Style.STROKE);
            this.f935e.setTypeface(Typeface.DEFAULT);
            this.a = h.e0.a();
        }

        h(i iVar, h hVar) {
            this.b = hVar.b;
            this.f934c = hVar.f934c;
            this.d = new Paint(hVar.d);
            this.f935e = new Paint(hVar.f935e);
            h.b bVar = hVar.f936f;
            if (bVar != null) {
                this.f936f = new h.b(bVar);
            }
            h.b bVar2 = hVar.f937g;
            if (bVar2 != null) {
                this.f937g = new h.b(bVar2);
            }
            this.f938h = hVar.f938h;
            try {
                this.a = (h.e0) hVar.a.clone();
            } catch (CloneNotSupportedException unused) {
                this.a = h.e0.a();
            }
        }
    }
}
