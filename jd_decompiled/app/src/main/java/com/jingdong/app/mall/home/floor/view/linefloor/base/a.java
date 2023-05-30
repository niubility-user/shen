package com.jingdong.app.mall.home.floor.view.linefloor.base;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class a extends com.jingdong.app.mall.home.r.e.b {
    public static int[] t = {-15066598, -15066598};
    public static final int[] u = {0, 0};
    public static final int[] v = {-4114, -2058};
    public static int[] w = {-1, -35584};
    public final c a;
    private com.jingdong.app.mall.home.floor.view.b.a b;

    /* renamed from: c  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.c.b f9850c;
    private com.jingdong.app.mall.home.r.c.b d;

    /* renamed from: e  reason: collision with root package name */
    protected f f9851e;

    /* renamed from: f  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.c.b f9852f;

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.c.b f9853g;

    /* renamed from: h  reason: collision with root package name */
    protected h f9854h;

    /* renamed from: i  reason: collision with root package name */
    protected d f9855i;

    /* renamed from: j  reason: collision with root package name */
    protected f f9856j;

    /* renamed from: k  reason: collision with root package name */
    private int f9857k;

    /* renamed from: l  reason: collision with root package name */
    protected boolean f9858l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f9859m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f9860n;
    protected int o;
    protected boolean p;
    private boolean q;
    private int r;
    private String s;

    public a(c cVar, f fVar, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(fVar.srcJson);
        this.a = cVar;
        this.f9856j = fVar;
        this.b = aVar;
        this.f9850c = com.jingdong.app.mall.home.r.c.b.c(fVar.l());
        if (a()) {
            u(this.f9850c, this.f9856j.l0());
        }
        JumpEntity jump = fVar.getJump();
        if (jump != null) {
            this.d = com.jingdong.app.mall.home.r.c.b.c(jump.getSrvJson());
            if (a()) {
                u(this.d, this.f9856j.l0());
            }
        }
        this.s = "slight".concat(this.f9856j.s());
    }

    private void u(com.jingdong.app.mall.home.r.c.b bVar, String str) {
        if (bVar != null) {
            bVar.put("videoid", str);
            bVar.put("isvideo", "0");
            bVar.put("islabelfrash", "0");
            bVar.put("tabstyle", "0_0");
        }
    }

    public static boolean y(int i2) {
        return i2 == 1;
    }

    public boolean A() {
        d dVar = this.f9855i;
        return dVar != null && dVar.n();
    }

    public boolean B() {
        return 2 == this.f9857k && q() < 2;
    }

    public boolean C() {
        return this.p;
    }

    public boolean D() {
        return true;
    }

    public boolean E() {
        return this.f9860n;
    }

    public boolean F() {
        return this.f9859m;
    }

    public void G(ArrayList<String> arrayList) {
        arrayList.add(this.f9856j.j());
        f fVar = this.f9851e;
        if (fVar != null) {
            arrayList.add(fVar.j());
        }
    }

    public void H(ArrayList<com.jingdong.app.mall.home.r.c.b> arrayList) {
        com.jingdong.app.mall.home.r.c.b bVar = this.f9850c;
        if (bVar != null) {
            arrayList.add(bVar);
        }
        com.jingdong.app.mall.home.r.c.b bVar2 = this.f9852f;
        if (bVar2 != null) {
            arrayList.add(bVar2);
        }
    }

    public void I(int i2, int i3) {
    }

    public final void J(h hVar, d dVar) {
        this.f9857k = hVar.d0;
        this.f9854h = hVar;
        this.f9855i = dVar;
        boolean z = false;
        this.f9858l = hVar.Z || hVar.a0;
        this.f9859m = m.F(hVar);
        this.f9860n = m.B(hVar);
        int jsonInt = this.f9856j.getJsonInt("sloganPlaySwitch", 0);
        if (!this.f9858l && hVar.S == 1 && jsonInt == 1) {
            z = true;
        }
        this.q = z;
        if (z) {
            this.r = hVar.T;
        }
        this.p = "1".equals(hVar.getJsonString("fontShape"));
    }

    public void K(f fVar) {
        if (fVar == null) {
            return;
        }
        this.f9851e = fVar;
        this.f9852f = com.jingdong.app.mall.home.r.c.b.c(fVar.l());
        if (a()) {
            u(this.f9852f, this.f9851e.l0());
        }
        JumpEntity jump = this.f9851e.getJump();
        if (jump != null) {
            this.f9853g = com.jingdong.app.mall.home.r.c.b.c(jump.getSrvJson());
            if (a()) {
                u(this.f9853g, this.f9851e.l0());
            }
        }
    }

    public boolean L(String str) {
        return TextUtils.equals(str, BaseAnimateLayout.f9826m) || TextUtils.equals(str, BaseAnimateLayout.f9827n);
    }

    public boolean M() {
        return this.q;
    }

    protected boolean a() {
        return this.a == c.NORMAL && this.b != com.jingdong.app.mall.home.floor.view.b.a.LINE_SALE;
    }

    public void b(int i2, String str, String str2) {
        try {
            f(i2).put(str, str2);
            j(i2).put(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c(int i2, int i3) {
        this.o = i3;
    }

    public String d(f fVar, boolean z) {
        return !z ? BaseAnimateLayout.f9826m : fVar.getJsonString("moduleAnimation");
    }

    public String e() {
        return g(0).F();
    }

    public final com.jingdong.app.mall.home.r.c.b f(int i2) {
        com.jingdong.app.mall.home.r.c.b bVar;
        if (i2 <= 0 || (bVar = this.f9853g) == null) {
            com.jingdong.app.mall.home.r.c.b bVar2 = this.d;
            return bVar2 != null ? bVar2 : com.jingdong.app.mall.home.r.c.b.c("");
        }
        return bVar;
    }

    public final f g(int i2) {
        f fVar;
        if (i2 <= 0 || (fVar = this.f9851e) == null) {
            f fVar2 = this.f9856j;
            return fVar2 != null ? fVar2 : new f(null, 0);
        }
        return fVar;
    }

    public int[] getBgColors() {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(0).E(), v);
    }

    public d h() {
        return this.f9855i;
    }

    public String i(int i2, String str) {
        try {
            return f(i2).getString(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public final com.jingdong.app.mall.home.r.c.b j(int i2) {
        com.jingdong.app.mall.home.r.c.b bVar;
        if (i2 <= 0 || (bVar = this.f9852f) == null) {
            com.jingdong.app.mall.home.r.c.b bVar2 = this.f9850c;
            return bVar2 != null ? bVar2 : com.jingdong.app.mall.home.r.c.b.c("");
        }
        return bVar;
    }

    public com.jingdong.app.mall.home.floor.view.b.a k() {
        return this.b;
    }

    public h l() {
        return this.f9854h;
    }

    public int m() {
        return this.o;
    }

    public int[] n(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).getJsonString("maskColor"), w);
    }

    public int o() {
        return this.r;
    }

    public String p() {
        return this.s;
    }

    public int q() {
        return this.b.getSubWeight();
    }

    public int r(int i2) {
        return g(i2).q();
    }

    public int s(int i2) {
        int q = g(i2).q();
        if (q != 0) {
            if (q == 1) {
                return 32;
            }
            if (q == 2) {
                return 34;
            }
            if (q == 3) {
                return 32;
            }
            if (q != 4 && this.a == c.NORMAL) {
                return 32;
            }
        }
        return 30;
    }

    public int t() {
        return this.b.getWeight();
    }

    public boolean v() {
        return this.f9858l;
    }

    public boolean w() {
        d dVar = this.f9855i;
        return dVar != null && dVar.m();
    }

    public boolean x(int i2) {
        return g(i2).p() != 0;
    }

    public boolean z() {
        return y(this.f9857k);
    }
}
