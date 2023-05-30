package com.jingdong.app.mall.home.floor.common.i;

import android.graphics.Point;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.LinearFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LinearFloorEngine;
import com.jingdong.common.utils.StringUtil;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class p {
    public static AtomicInteger a = new AtomicInteger(1);
    public static AtomicInteger b = new AtomicInteger(1);

    /* loaded from: classes4.dex */
    public static class a implements Cloneable {
        public Point A;
        public Point B;
        public Point C;
        public Point D;
        public Point E;
        public Point F;
        public Point G;
        public Point H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public int O;
        public int P;
        public int Q;
        public int R;
        public int S;
        public int T;
        public int U;
        public int V;
        public int W;
        public q X;
        public C0288a Y;
        public Point Z;
        public int a0;
        public int b0;
        public boolean c0;
        public c d0;
        public Point e0;

        /* renamed from: g */
        public d f9339g;

        /* renamed from: h */
        public d f9340h;

        /* renamed from: i */
        public f f9341i;

        /* renamed from: j */
        public com.jingdong.app.mall.home.floor.common.i.a f9342j;

        /* renamed from: k */
        public e f9343k;

        /* renamed from: l */
        public e f9344l;

        /* renamed from: m */
        public com.jingdong.app.mall.home.floor.common.i.a f9345m;

        /* renamed from: n */
        public float f9346n;
        public float o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean s;
        public boolean t;
        public boolean u;
        public boolean v;
        public boolean w;
        public boolean x;
        public boolean y;
        public Point z;

        /* renamed from: com.jingdong.app.mall.home.floor.common.i.p$a$a */
        /* loaded from: classes4.dex */
        public static class C0288a implements Cloneable {

            /* renamed from: g */
            public boolean f9347g;

            /* renamed from: n */
            public String f9354n;
            public String o;
            public String q;
            public String s;

            /* renamed from: h */
            public boolean f9348h = false;

            /* renamed from: i */
            public int f9349i = 0;

            /* renamed from: j */
            public int f9350j = -1;

            /* renamed from: k */
            public int[] f9351k = null;

            /* renamed from: l */
            public int[] f9352l = null;

            /* renamed from: m */
            public int[] f9353m = null;
            public String p = "";
            public String r = "";
        }

        public a() {
            d dVar = d.NONE;
            this.f9339g = dVar;
            this.f9340h = dVar;
            b bVar = b.UNDER_SUBTITLE;
            this.f9341i = f.LEFT_TOP;
            this.f9342j = com.jingdong.app.mall.home.floor.common.i.a.LEFT_BOTTOM;
            e eVar = e.LEFT;
            this.f9343k = eVar;
            this.f9344l = eVar;
            this.f9345m = com.jingdong.app.mall.home.floor.common.i.a.LEFT_TOP;
            this.f9346n = com.jingdong.app.mall.home.floor.common.d.d(30);
            this.o = com.jingdong.app.mall.home.floor.common.d.d(22);
            com.jingdong.app.mall.home.floor.common.d.d(20);
            com.jingdong.app.mall.home.floor.common.d.d(12);
            this.q = false;
            this.r = false;
            this.s = false;
            this.u = true;
            this.w = true;
            this.z = new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(20));
            this.A = new Point(0, 0);
            this.B = new Point(com.jingdong.app.mall.home.floor.common.d.d(6), 0);
            this.C = new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(6));
            this.D = new Point(0, 0);
            this.E = new Point(-1, com.jingdong.app.mall.home.floor.common.d.d(100));
            this.F = new Point(0, 0);
            this.I = -2;
            this.J = -2;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 1;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 1;
            this.V = 0;
            this.W = 0;
            this.X = q.UN_OVERLAY_UN_DRAW_SKU_FIRST;
            this.Y = new C0288a();
            this.Z = null;
            this.a0 = com.jingdong.app.mall.home.floor.common.d.d(36);
            this.b0 = com.jingdong.app.mall.home.floor.common.d.d(36);
            this.c0 = false;
            this.d0 = c.OBEY_ENUM_SEPARATION_TITLE_SUBTITLE_POS_ENUM;
            this.e0 = null;
        }

        private void h(int i2, int i3, int i4) {
            this.O = i2;
            this.P = i3;
            this.S = i4;
        }

        private void i(int i2, int i3, int i4) {
            this.T = i4;
        }

        public void A(f fVar) {
            this.f9341i = fVar;
        }

        public void B(float f2) {
            this.f9346n = f2;
        }

        public void C(int i2) {
            this.I = i2;
        }

        public void D(boolean z) {
            this.c0 = z;
        }

        public void E(Point point2) {
            this.G = point2;
        }

        public void F(boolean z) {
            this.y = z;
        }

        public void a() {
            boolean z = this.Y.f9349i == 3;
            if (z && this.v) {
                this.X = q.OVERLAY_DRAW_SKU_FIRST;
            }
            if (z && !this.v) {
                this.X = q.OVERLAY_UN_DRAW_SKU_FIRST;
            }
            if (!z && this.v) {
                this.X = q.UN_OVERLAY_DRAW_SKU_FIRST;
            }
            if (z || this.v) {
                return;
            }
            this.X = q.UN_OVERLAY_UN_DRAW_SKU_FIRST;
        }

        public void b() {
            this.f9346n = com.jingdong.app.mall.home.floor.common.d.d(30);
            this.o = com.jingdong.app.mall.home.floor.common.d.d(22);
            com.jingdong.app.mall.home.floor.common.d.d(20);
            com.jingdong.app.mall.home.floor.common.d.d(12);
            this.z.set(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(20));
            this.A.set(0, 0);
            this.B.set(com.jingdong.app.mall.home.floor.common.d.d(6), 0);
            this.C.set(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(6));
            this.D.set(0, 0);
            this.E.set(-1, com.jingdong.app.mall.home.floor.common.d.d(100));
            this.F.set(0, 0);
            this.a0 = com.jingdong.app.mall.home.floor.common.d.d(36);
            this.b0 = com.jingdong.app.mall.home.floor.common.d.d(36);
        }

        public void c(int i2) {
            this.Q = i2;
        }

        public void d(Point point2) {
            this.H = point2;
        }

        public void e(int i2, int i3, int i4, int i5) {
            l(i3, i4);
            if (i2 >= 2) {
                g(i3, i4, i5);
            }
            if (i2 >= 3) {
                h(i3, i4, i5);
            }
            if (i2 >= 4) {
                i(i3, i4, i5);
            }
        }

        public void f(boolean z) {
            this.v = z;
        }

        public void g(int i2, int i3, int i4) {
            this.M = i2;
            this.N = i3;
            this.R = i4;
        }

        public void j(Point point2) {
            this.D = point2;
        }

        public void k(com.jingdong.app.mall.home.floor.common.i.a aVar) {
            this.f9342j = aVar;
        }

        public void l(int i2, int i3) {
            this.K = i2;
            this.L = i3;
        }

        public void m(Point point2) {
            this.A = point2;
        }

        public void n(Point point2) {
            this.B = point2;
        }

        public void o(b bVar) {
        }

        public void p(boolean z) {
            this.x = z;
        }

        public void q(boolean z) {
            this.p = z;
        }

        public void r(Point point2) {
            this.e0 = point2;
        }

        public void s(e eVar) {
            this.f9344l = eVar;
        }

        public void t(Point point2) {
            this.C = point2;
        }

        public void u(c cVar) {
            this.d0 = cVar;
        }

        public void v(float f2) {
            this.o = f2;
        }

        public void w(int i2) {
            this.J = i2;
        }

        public void x(e eVar) {
            this.f9343k = eVar;
        }

        public void y(Point point2) {
            this.Z = point2;
        }

        public void z(Point point2) {
            this.z = point2;
        }
    }

    public static void a(com.jingdong.app.mall.home.r.e.d dVar) {
        ArrayList<com.jingdong.app.mall.home.r.e.f> a2;
        int i2;
        if (dVar == null || (a2 = dVar.a()) == null || a2.size() <= 0) {
            return;
        }
        for (int i3 = 0; i3 < a2.size(); i3++) {
            com.jingdong.app.mall.home.r.e.f fVar = a2.get(i3);
            if (fVar != null) {
                a moduleParamsAt = dVar.o.getModuleParamsAt(i3);
                boolean C = m.C(dVar.mParentModel);
                int q = fVar.q();
                if (C) {
                    int i4 = 68;
                    int i5 = 24;
                    int i6 = 22;
                    if (q == 0) {
                        i2 = 30;
                    } else if (q != 2) {
                        i2 = 32;
                        i4 = 67;
                        i5 = 23;
                        i6 = 24;
                    } else {
                        i2 = 34;
                        i5 = 22;
                        i6 = 26;
                    }
                    moduleParamsAt.z(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(i5)));
                    moduleParamsAt.u(c.LEFT_TOP);
                    moduleParamsAt.t(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(i4)));
                    moduleParamsAt.B(com.jingdong.app.mall.home.floor.common.d.d(i2));
                    moduleParamsAt.v(com.jingdong.app.mall.home.floor.common.d.d(i6));
                }
            }
        }
    }

    public static void b(com.jingdong.app.mall.home.r.e.d dVar, t tVar) {
        ArrayList<com.jingdong.app.mall.home.r.e.f> a2;
        if (dVar == null || (a2 = dVar.a()) == null || a2.size() <= 0) {
            return;
        }
        dVar.o = new LinearFloorEntity();
        LinearFloorEngine linearFloorEngine = new LinearFloorEngine();
        dVar.f10670n = linearFloorEngine;
        linearFloorEngine.e(dVar.mParentModel, dVar, dVar.o);
        for (int i2 = 0; i2 < a2.size(); i2++) {
            com.jingdong.app.mall.home.r.e.f fVar = a2.get(i2);
            if (fVar != null) {
                FloorEntity floorEntity = dVar.o;
                a moduleParamsAt = floorEntity.getModuleParamsAt(i2);
                if (moduleParamsAt != null) {
                    moduleParamsAt.b();
                }
                r modelTypeEnum = tVar.getModelTypeEnum(fVar.a0());
                d(fVar, floorEntity, i2);
                tVar.parseSeparationParams(dVar, fVar, floorEntity, i2);
                modelTypeEnum.parseModelParams(dVar, fVar, floorEntity, i2);
                c(fVar, floorEntity, i2, false);
            }
        }
    }

    private static void c(com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2, boolean z) {
        int i3;
        a moduleParamsAt = floorEntity.getModuleParamsAt(i2);
        if (moduleParamsAt == null) {
            return;
        }
        a.C0288a c0288a = moduleParamsAt.Y;
        c0288a.f9354n = m.g(fVar.O());
        int i4 = -1;
        if (z) {
            i3 = -1;
        } else {
            i3 = moduleParamsAt.c0 ? -11250604 : -13684945;
        }
        boolean z2 = moduleParamsAt.u;
        c0288a.f9351k = m.p(fVar.C(), i3, z2);
        c0288a.o = m.g(fVar.d0());
        if (!z) {
            i4 = moduleParamsAt.c0 ? -6776680 : -8684677;
        }
        c0288a.f9352l = m.p(fVar.f0(), i4, z2);
        c0288a.p = fVar.s();
        c0288a.q = fVar.d();
        c0288a.r = fVar.G();
        c0288a.f9348h = !StringUtil.isEmpty(r7);
        c0288a.s = fVar.u();
        c0288a.f9347g = fVar.r() == 1;
        c0288a.f9353m = m.p(fVar.Y(), -6776680, z2);
        moduleParamsAt.a();
    }

    private static void d(com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
        a moduleParamsAt = floorEntity.getModuleParamsAt(i2);
        if (moduleParamsAt == null) {
            return;
        }
        moduleParamsAt.Y.f9349i = fVar.t();
    }
}
