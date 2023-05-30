package com.jingdong.app.mall.home.floor.ctrl;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;

/* loaded from: classes4.dex */
public class c {
    private boolean a;
    private int b;

    /* renamed from: c */
    private int f9381c;
    private int d;

    /* renamed from: e */
    private boolean f9382e;

    /* renamed from: f */
    private boolean f9383f;

    /* renamed from: g */
    private int f9384g;

    /* renamed from: h */
    private boolean f9385h;

    /* renamed from: i */
    private boolean f9386i;

    /* renamed from: j */
    private boolean f9387j;

    /* renamed from: k */
    private boolean f9388k;

    /* renamed from: l */
    private int f9389l;

    /* renamed from: m */
    private volatile C0291c f9390m;

    /* renamed from: n */
    private final com.jingdong.app.mall.home.floor.common.f f9391n = new com.jingdong.app.mall.home.floor.common.f(-1, 72);
    private IHomeTitle o = null;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.f();
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.r.a.d {
        b() {
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.r.a.d
        protected void onEnd(Animator animator, boolean z) {
            c.this.r();
        }
    }

    /* renamed from: com.jingdong.app.mall.home.floor.ctrl.c$c */
    /* loaded from: classes4.dex */
    public static class C0291c extends RelativeLayout {

        /* renamed from: g */
        private Bitmap f9394g;

        /* renamed from: h */
        private Matrix f9395h;

        public C0291c(Context context) {
            super(context);
        }

        private void a(Canvas canvas) {
            Bitmap bitmap = this.f9394g;
            if (bitmap != null && this.f9395h != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(this.f9394g, this.f9395h, null);
            } else {
                canvas.drawColor(com.jingdong.app.mall.home.a.x);
            }
        }

        public void b(Bitmap bitmap, Matrix matrix) {
            this.f9394g = bitmap;
            this.f9395h = matrix;
            postInvalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            try {
                a(canvas);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            super.dispatchDraw(canvas);
        }
    }

    private boolean e() {
        IHomeTitle iHomeTitle;
        return this.f9383f && (iHomeTitle = this.o) != null && iHomeTitle.isScrollFixed();
    }

    public void f() {
        IHomeTitle h2;
        Object obj;
        if (this.f9390m == null || (h2 = h()) == null) {
            return;
        }
        Pair<Bitmap, Matrix> topBitmap = h2.getTopBitmap();
        if (topBitmap != null && (obj = topBitmap.first) != null && topBitmap.second != null && !((Bitmap) obj).isRecycled()) {
            Matrix matrix = new Matrix((Matrix) topBitmap.second);
            matrix.postTranslate(0.0f, -h.J());
            this.f9390m.b((Bitmap) topBitmap.first, matrix);
            return;
        }
        this.f9390m.b(null, null);
    }

    public static IHomeTitle h() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return null;
        }
        return z0.y0();
    }

    public void r() {
        this.f9387j = false;
        if (this.f9390m != null) {
            this.f9390m.setVisibility(8);
        }
        com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
        if (lastCreateView instanceof MallFloorCategory) {
            ((MallFloorCategory) lastCreateView).addContentView();
        }
    }

    private void s() {
        CategoryTabTitle categoryTabTitle;
        if (this.f9390m == null || this.f9386i) {
            return;
        }
        this.f9386i = true;
        this.f9387j = true;
        this.f9388k = true;
        com.jingdong.app.mall.home.floor.ctrl.a aVar = com.jingdong.app.mall.home.a.r;
        if (aVar != null) {
            aVar.d();
        }
        com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
        if (!(lastCreateView instanceof MallFloorCategory) || (categoryTabTitle = ((MallFloorCategory) lastCreateView).getCategoryTabTitle()) == null || JDHomeFragment.z0() == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.common.i.m.K(categoryTabTitle);
        categoryTabTitle.setLayoutParams(new com.jingdong.app.mall.home.floor.common.f(-1, -1).u(categoryTabTitle));
        com.jingdong.app.mall.home.floor.common.i.m.a(this.f9390m, categoryTabTitle);
        this.f9390m.setVisibility(0);
        com.jingdong.app.mall.home.v.c.a.a(this.f9390m);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f9390m, "translationY", -this.f9391n.h(), 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.start();
        com.jingdong.app.mall.home.o.a.f.r0("CategoryTitleCeilingCtrl", "showCategoryCeiling");
    }

    public void c() {
        ViewGroup viewGroup;
        boolean z = false;
        this.f9383f = false;
        this.f9390m = null;
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null || com.jingdong.app.mall.home.v.d.a.f()) {
            return;
        }
        IHomeTitle h2 = h();
        this.o = h2;
        if (h2 == null) {
            return;
        }
        RelativeLayout x0 = z0.x0();
        if (x0 != null && this.a) {
            z = true;
        }
        this.f9383f = z;
        if (z && (viewGroup = (ViewGroup) x0.findViewById(R.id.home_title_content)) != null) {
            int indexOfChild = x0.indexOfChild(viewGroup);
            this.f9390m = new C0291c(z0.thisActivity);
            RelativeLayout.LayoutParams u = this.f9391n.u(this.f9390m);
            u.topMargin = h.J();
            this.f9390m.setLayoutParams(u);
            com.jingdong.app.mall.home.floor.common.i.m.b(x0, this.f9390m, indexOfChild);
            this.f9390m.setVisibility(8);
            l();
        }
    }

    public boolean d() {
        IHomeTitle h2 = h();
        this.o = h2;
        boolean z = h2 != null && h2.needScrollTop();
        if (com.jingdong.app.mall.home.v.d.a.f()) {
            return z;
        }
        if (this.f9389l < com.jingdong.app.mall.home.floor.common.d.d(100)) {
            return true;
        }
        if (z) {
            return (e() && this.f9386i && !this.f9382e) ? false : true;
        }
        return false;
    }

    public void g(boolean z) {
        com.jingdong.app.mall.home.v.c.a.h(this.f9390m, z);
    }

    public String i() {
        return this.f9386i ? "1" : "0";
    }

    public String j() {
        return this.f9388k ? "1" : "0";
    }

    public boolean k() {
        return this.f9387j;
    }

    public void l() {
        com.jingdong.app.mall.home.o.a.f.E0(new a());
    }

    public void m() {
        if (e()) {
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 == null) {
                this.f9385h = false;
                return;
            }
            int F0 = z0.F0();
            this.f9384g = F0;
            this.f9385h = F0 >= this.b;
        }
    }

    public void n(int i2) {
        this.f9389l = i2;
        if (i2 == 0) {
            p(false);
        } else if (e()) {
            com.jingdong.app.mall.home.o.a.f.r0("CategoryTitleCeilingCtrl", "nScrollY: " + i2);
            if (this.f9386i) {
                if (i2 > this.f9384g || i2 < this.f9381c) {
                    p(true);
                }
            } else if (!this.f9385h || i2 < this.f9381c || this.f9384g - i2 <= this.d) {
            } else {
                s();
            }
        }
    }

    public void o(com.jingdong.app.mall.home.r.e.h hVar) {
        if (hVar.Z) {
            return;
        }
        boolean z = false;
        try {
            this.a = hVar.getJsonInt("isCeilingSwitch") == 1;
            String jsonString = hVar.getJsonString("swipeScreenNum");
            this.b = Math.round((TextUtils.isEmpty(jsonString) ? 0.01f : Float.parseFloat(jsonString)) * com.jingdong.app.mall.home.a.f8562k);
            String jsonString2 = hVar.getJsonString("hideScreenNum");
            this.f9381c = Math.round((TextUtils.isEmpty(jsonString2) ? 0.0f : Float.parseFloat(jsonString2)) * com.jingdong.app.mall.home.a.f8562k);
            this.d = com.jingdong.app.mall.home.floor.common.d.d(hVar.getJsonInt("backSwipeHeight", 0));
            this.f9382e = hVar.getJsonInt("isBackCeiling", 1) == 1;
        } catch (Exception unused) {
            this.a = false;
        }
        boolean z2 = this.a;
        if (this.b > 0 && this.f9381c >= 0 && this.d >= 0) {
            z = true;
        }
        this.a = z2 & z;
    }

    public void p(boolean z) {
        if (this.f9390m == null || !this.f9386i) {
            return;
        }
        this.f9386i = false;
        this.f9385h = false;
        if (!z) {
            r();
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f9390m, "translationY", 0.0f, -this.f9391n.h());
        ofFloat.setDuration(300L);
        ofFloat.addListener(new b());
        ofFloat.start();
    }

    public void q() {
        this.f9383f = false;
        this.a = false;
        this.f9388k = false;
        this.d = Integer.MAX_VALUE;
        this.b = Integer.MAX_VALUE;
        this.f9381c = 0;
        this.f9382e = true;
        this.f9386i = false;
        this.f9387j = false;
        com.jingdong.app.mall.home.floor.common.i.m.K(this.f9390m);
        com.jingdong.app.mall.home.o.a.f.r0("CategoryTitleCeilingCtrl", "reset");
    }
}
