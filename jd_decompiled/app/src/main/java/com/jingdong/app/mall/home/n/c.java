package com.jingdong.app.mall.home.n;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.category.floor.CaLoadingFloor;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: l */
    private static AtomicBoolean f10326l = new AtomicBoolean(false);

    /* renamed from: m */
    private static AtomicBoolean f10327m = new AtomicBoolean(true);

    /* renamed from: n */
    public static int f10328n;
    private static long o;
    private JDHomeFragment a;
    private RelativeLayout b;

    /* renamed from: c */
    private HomePullRefreshRecyclerView f10329c;
    private HomeRecycleView d;

    /* renamed from: e */
    private CaContentLayout f10330e;

    /* renamed from: f */
    private AtomicInteger f10331f = new AtomicInteger(0);

    /* renamed from: g */
    private ViewOutlineProvider f10332g;

    /* renamed from: h */
    private int f10333h;

    /* renamed from: i */
    private int f10334i;

    /* renamed from: j */
    private int f10335j;

    /* renamed from: k */
    private final RelativeLayout.LayoutParams f10336k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends CaContentLayout {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(context);
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.category.view.CaContentLayout
        protected int j() {
            return h.N().K(c.this.f10331f.get());
        }

        @Override // android.widget.RelativeLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(c.f10328n, 1073741824));
        }
    }

    /* loaded from: classes4.dex */
    public class b extends ViewOutlineProvider {
        b() {
            c.this = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, c.this.f10329c.getWidth(), c.this.f10334i);
        }
    }

    /* renamed from: com.jingdong.app.mall.home.n.c$c */
    /* loaded from: classes4.dex */
    public class C0308c extends com.jingdong.app.mall.home.o.a.b {
        C0308c() {
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.i();
        }
    }

    public c(JDHomeFragment jDHomeFragment, RelativeLayout relativeLayout, HomePullRefreshRecyclerView homePullRefreshRecyclerView, HomeRecycleView homeRecycleView) {
        this.a = jDHomeFragment;
        this.b = relativeLayout;
        this.f10329c = homePullRefreshRecyclerView;
        this.d = homeRecycleView;
        f.d();
        h(this.b.getHeight());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f10336k = layoutParams;
        n(this.f10331f.get());
        a aVar = new a(relativeLayout.getContext());
        this.f10330e = aVar;
        aVar.setLayoutParams(layoutParams);
        this.f10330e.setClickable(true);
    }

    public static int d() {
        return com.jingdong.app.mall.home.state.dark.a.e(com.jingdong.app.mall.home.state.dark.a.d(), IconFloorEntity.BGCOLOR_DEFAULT);
    }

    public static boolean e() {
        return f10326l.get();
    }

    private void n(int i2) {
        if (this.f10333h <= 0) {
            this.f10333h = this.b.getHeight();
        }
        int K = h.N().K(i2);
        this.f10334i = K;
        f10328n = this.f10333h - K;
        RelativeLayout.LayoutParams layoutParams = this.f10336k;
        if (layoutParams != null && layoutParams.topMargin != K) {
            layoutParams.topMargin = K;
            CaContentLayout caContentLayout = this.f10330e;
            if (caContentLayout != null) {
                caContentLayout.setLayoutParams(layoutParams);
            }
        }
        com.jingdong.app.mall.home.category.floor.base.b lastView = com.jingdong.app.mall.home.n.a.C_LOADING.getLastView();
        if (lastView instanceof CaLoadingFloor) {
            ((CaLoadingFloor) lastView).F();
        }
    }

    private void o(boolean z) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21 && (this.f10329c.getClipToOutline() != z || this.f10335j != this.f10334i)) {
            if (this.f10332g == null) {
                this.f10332g = new b();
            }
            this.f10335j = this.f10334i;
            this.f10329c.setOutlineProvider(z ? this.f10332g : ViewOutlineProvider.BACKGROUND);
            this.f10329c.setClipToOutline(z);
        }
        boolean a2 = com.jingdong.app.mall.home.floor.view.b.h.a.a(com.jingdong.app.mall.home.a.x, 240, 240, 240);
        if (i2 < 21 || !a2) {
            this.f10330e.A(d());
        }
    }

    public static boolean p() {
        return f10327m.get();
    }

    public void f() {
        CaContentLayout caContentLayout = this.f10330e;
        if (caContentLayout != null) {
            caContentLayout.q();
        }
    }

    public void g(CategoryEntity.CaItem caItem, int i2) {
        f10327m.set(false);
        boolean z = i2 != 0;
        n(i2);
        this.d.t(z);
        this.f10329c.s0(!z);
        o(z);
        if (i2 == this.f10331f.get()) {
            return;
        }
        f10326l.set(i2 <= 0);
        this.f10331f.set(i2);
        if (com.jingdong.app.mall.home.a.t.d()) {
            this.a.k1();
        }
        ViewParent parent = this.f10330e.getParent();
        RelativeLayout relativeLayout = this.b;
        if (parent != relativeLayout) {
            m.b(relativeLayout, this.f10330e, -1);
            com.jingdong.app.mall.home.a.s.b();
        }
        this.f10330e.r(caItem, i2);
    }

    public void h(int i2) {
        CaContentLayout caContentLayout;
        int i3 = this.f10333h;
        if (i3 > 0 && i2 != i3 && (caContentLayout = this.f10330e) != null) {
            caContentLayout.requestLayout();
        }
        this.f10333h = i2;
        n(this.f10331f.get());
    }

    public void i() {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new C0308c());
        } else {
            this.f10330e.s();
        }
    }

    public boolean j(int i2) {
        return this.f10330e.t(i2);
    }

    public void k() {
        f10326l.set(true);
        o = SystemClock.elapsedRealtime();
        this.f10330e.u();
    }

    public void l() {
        if (com.jingdong.app.mall.home.n.h.a.f(o)) {
            return;
        }
        f10326l.set(false);
        this.f10330e.v();
    }

    public void m() {
        this.f10330e.y();
    }
}
