package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.CaLoadingFloor;
import com.jingdong.app.mall.home.category.view.CaLoadingLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.n.g.s;
import com.jingdong.app.mall.home.n.h.g;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class CaContentLayout extends RelativeLayout {
    private static volatile CategoryEntity.CaItem y;

    /* renamed from: g */
    private int f8717g;

    /* renamed from: h */
    private CaLoadingLayout f8718h;

    /* renamed from: i */
    private CaRecycleView f8719i;

    /* renamed from: j */
    private CaAdapter f8720j;

    /* renamed from: k */
    private RelativeLayout f8721k;

    /* renamed from: l */
    private com.jingdong.app.mall.home.category.view.a f8722l;

    /* renamed from: m */
    private ImageView f8723m;

    /* renamed from: n */
    private f f8724n;
    private ImageView o;
    private f p;
    private CaDecorateLayout q;
    private f r;
    private AtomicBoolean s;
    private CaWebLayout t;
    private final ConcurrentHashMap<String, CaWebLayout> u;
    private final Path v;
    private final Matrix w;
    private int x;

    /* loaded from: classes4.dex */
    public class a implements CaLoadingLayout.b {
        a() {
            CaContentLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.category.view.CaLoadingLayout.b
        public void onRetry() {
            CaContentLayout.this.p();
        }
    }

    /* loaded from: classes4.dex */
    public class b extends CaRecycleView {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, CaContentLayout caContentLayout, RelativeLayout relativeLayout) {
            super(context, caContentLayout, relativeLayout);
            CaContentLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.category.view.CaRecycleView
        public void g(int i2, int i3) {
            super.g(i2, i3);
            CaContentLayout.this.q.setTranslationY(-i3);
            int i4 = i3 > (com.jingdong.app.mall.home.n.c.f10328n << 1) ? 0 : 8;
            if (CaContentLayout.this.f8723m.getVisibility() != i4) {
                CaContentLayout.this.f8723m.setVisibility(i4);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c extends CaPullRefreshLayout {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context) {
            super(context);
            CaContentLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.category.view.CaPullRefreshLayout, com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
        public RecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
            return CaContentLayout.this.f8719i;
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
        protected boolean isReadyForPullStart() {
            return CaContentLayout.this.f8719i.e() <= 0 && !CaContentLayout.this.f8720j.s();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.category.view.a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(CaContentLayout caContentLayout, CaPullRefreshLayout caPullRefreshLayout, CaRecycleView caRecycleView, CaDecorateLayout caDecorateLayout, CaAdapter caAdapter) {
            super(caContentLayout, caPullRefreshLayout, caRecycleView, caDecorateLayout, caAdapter);
            CaContentLayout.this = r7;
        }

        @Override // com.jingdong.app.mall.home.category.view.a
        public void j(List<com.jingdong.app.mall.home.n.g.u.c> list) {
            super.j(list);
            CategoryEntity.DecorateInfo c2 = com.jingdong.app.mall.home.n.e.c();
            CaContentLayout.this.r.C(c2 == null ? 0 : c2.getBgHeight());
            f.c(CaContentLayout.this.q, CaContentLayout.this.r);
        }
    }

    /* loaded from: classes4.dex */
    public class e implements View.OnClickListener {
        e() {
            CaContentLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaContentLayout.this.f8719i.scrollToTop();
        }
    }

    public CaContentLayout(Context context) {
        super(context);
        this.s = new AtomicBoolean(false);
        this.u = new ConcurrentHashMap<>();
        this.v = new Path();
        this.w = new Matrix();
        CaLoadingLayout caLoadingLayout = new CaLoadingLayout(context, false);
        this.f8718h = caLoadingLayout;
        caLoadingLayout.e(new a());
        this.f8721k = new RelativeLayout(context);
        HomeImageView homeImageView = new HomeImageView(context);
        this.o = homeImageView;
        homeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.o.setImageResource(R.drawable.home_webp_empty);
        f fVar = new f(-1, 48);
        this.p = fVar;
        fVar.F(new Rect(0, 72, 0, 0));
        RelativeLayout relativeLayout = this.f8721k;
        ImageView imageView = this.o;
        relativeLayout.addView(imageView, this.p.u(imageView));
        this.q = new CaDecorateLayout(context);
        f fVar2 = new f(-1, 0);
        this.r = fVar2;
        View view = this.q;
        addView(view, fVar2.u(view));
        this.f8719i = new b(context, this, this.f8721k);
        this.f8720j = new CaAdapter(context, this, this.f8719i);
        c cVar = new c(context);
        this.f8719i.setItemAnimator(null);
        this.f8719i.setAdapter(this.f8720j);
        this.f8719i.setClipToPadding(false);
        this.f8722l = new d(this, cVar, this.f8719i, this.q, this.f8720j);
        addView(cVar, new RelativeLayout.LayoutParams(-1, -1));
        addView(this.f8721k, new RelativeLayout.LayoutParams(-1, -2));
        HomeImageView homeImageView2 = new HomeImageView(context);
        this.f8723m = homeImageView2;
        homeImageView2.setOnClickListener(new e());
        this.f8723m.setVisibility(8);
        this.f8723m.setImageResource(R.drawable.home_direct_to_top);
        this.f8723m.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar3 = new f(112, 112);
        this.f8724n = fVar3;
        fVar3.F(new Rect(0, 0, 12, 4));
        RelativeLayout.LayoutParams u = this.f8724n.u(this.f8723m);
        u.addRule(12);
        u.addRule(11);
        addView(this.f8723m, u);
    }

    private void E(CategoryEntity.CaItem caItem, int i2) {
        int position = caItem.getPosition(i2);
        String valueOf = String.valueOf(position);
        CaWebLayout caWebLayout = this.u.get(valueOf);
        if (caWebLayout != null) {
            if (caWebLayout != this.t) {
                l(caWebLayout);
                this.t.setVisibility(8);
            }
            this.t = caWebLayout;
        } else {
            l(null);
            this.t = null;
        }
        if (this.t == null) {
            CaWebLayout caWebLayout2 = new CaWebLayout(getContext(), this, position);
            this.t = caWebLayout2;
            caWebLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            this.u.put(valueOf, this.t);
            addView(this.t);
        }
        this.f8719i.setVisibility(4);
        this.t.c(caItem, true);
        this.t.setVisibility(0);
    }

    private boolean F() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private void g(boolean z) {
        com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
        if (lastCreateView instanceof View) {
            com.jingdong.app.mall.home.a.t.g(z);
            com.jingdong.app.mall.home.v.c.a.b((View) lastCreateView, z);
        }
        IHomeTitle homeTitle = HomeTitleFactory.getHomeTitle(getContext());
        if (homeTitle != null) {
            homeTitle.isCheckHomeTab(z);
        }
    }

    private void h(Canvas canvas) {
        Pair<Bitmap, Matrix> L = h.N().L();
        if (L == null) {
            return;
        }
        Bitmap bitmap = (Bitmap) L.first;
        this.w.set((Matrix) L.second);
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int j2 = j();
        if (this.x != j2 || this.v.isEmpty()) {
            this.x = j2;
            float d2 = com.jingdong.app.mall.home.floor.common.d.d(20);
            float f2 = 0.5522848f * d2;
            this.v.reset();
            float f3 = -j2;
            this.v.moveTo(0.0f, f3);
            this.v.lineTo(0.0f, d2);
            g.c(0.0f, 0.0f, d2, f2, this.v);
            this.v.lineTo(getWidth() - r4, 0.0f);
            g.f(0.0f, getWidth(), d2, f2, this.v);
            this.v.lineTo(getWidth(), f3);
            this.v.moveTo(0.0f, f3);
            this.v.close();
        }
        canvas.save();
        this.w.postTranslate(0.0f, -j2);
        canvas.clipPath(this.v);
        canvas.drawBitmap(bitmap, this.w, com.jingdong.app.mall.home.v.c.a.c(this.f8717g == 0));
        canvas.restore();
    }

    public static CategoryEntity.CaItem i() {
        return y;
    }

    private void l(CaWebLayout caWebLayout) {
        for (CaWebLayout caWebLayout2 : this.u.values()) {
            if (caWebLayout2 != null && caWebLayout2 != caWebLayout) {
                caWebLayout2.setVisibility(8);
                caWebLayout2.g();
            }
        }
    }

    private void m() {
        for (CaWebLayout caWebLayout : this.u.values()) {
            if (caWebLayout != null) {
                caWebLayout.setVisibility(8);
                caWebLayout.g();
            }
        }
        this.f8719i.setVisibility(0);
    }

    public void p() {
        this.f8722l.i(y, true, true);
    }

    private void x() {
        for (CaWebLayout caWebLayout : this.u.values()) {
            if (caWebLayout != null) {
                caWebLayout.j();
            }
        }
    }

    private void z() {
        if (this.s.get()) {
            for (CaWebLayout caWebLayout : this.u.values()) {
                if (caWebLayout != null && caWebLayout == this.t) {
                    caWebLayout.k();
                }
            }
        }
    }

    public void A(int i2) {
        setBackgroundColor(i2);
        this.f8718h.setBackgroundColor(i2);
    }

    public void B(int i2, int i3) {
        com.jingdong.app.mall.home.category.floor.base.b floorView = com.jingdong.app.mall.home.n.a.C_LOADING.getFloorView(getContext(), this.f8720j);
        if (floorView instanceof CaLoadingFloor) {
            ((CaLoadingFloor) floorView).I(i2, i3);
        }
    }

    public void C(boolean z) {
        this.f8718h.d(z);
    }

    public void D(boolean z) {
        if (this.f8718h.getParent() != this) {
            this.f8718h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            m.b(this, this.f8718h, 0);
        }
        C(z);
        this.f8718h.setAlpha(1.0f);
        this.f8718h.setVisibility(0);
        this.f8718h.bringToFront();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        try {
            if (F()) {
                h(canvas);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected int j() {
        return h.N().K(0);
    }

    public void k() {
        this.f8718h.setVisibility(8);
    }

    public void n() {
        this.f8722l.f();
    }

    public void o(s sVar) {
        this.f8722l.g(sVar);
    }

    public void q() {
        if (F()) {
            postInvalidate();
        }
    }

    public void r(CategoryEntity.CaItem caItem, int i2) {
        com.jingdong.app.mall.home.n.h.a.i(y);
        if (!caItem.isTopTab()) {
            CategoryEntity.CaItem caItem2 = TitleTabManager.getInstance().getTitleTabInfo().getTabCustom().getCaItem();
            if (caItem2 != null) {
                caItem2.setPreTab(caItem);
            }
            CategoryEntity.CaItem caItem3 = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getCaItem();
            if (caItem3 != null) {
                caItem3.setPreTab(caItem);
            }
            CategoryEntity.CaItem caItem4 = TitleTabManager.getInstance().getTitleTabInfo().getTabPromotion().getCaItem();
            if (caItem4 != null) {
                caItem4.setPreTab(caItem);
            }
        }
        this.f8717g = i2;
        y = caItem;
        this.f8722l.l();
        this.f8720j.l();
        this.f8718h.b();
        this.f8723m.setVisibility(8);
        this.q.setTranslationY(0.0f);
        f.c(this.o, this.p);
        f.c(this.f8723m, this.f8724n);
        boolean z = i2 == 0;
        g(z);
        if (z) {
            this.s.set(false);
            this.f8719i.i();
            setVisibility(8);
            u();
            return;
        }
        com.jingdong.app.mall.home.n.g.v.b.b(caItem);
        y.clearRequest();
        y.onTagChanged();
        w();
        this.f8719i.setPadding(com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10357j), 0, com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10357j), 0);
        this.f8719i.stopScroll();
        this.f8719i.scrollToPosition(0);
        setVisibility(0);
        this.s.set(caItem.isWebTab());
        if (this.s.get()) {
            E(caItem, i2);
            return;
        }
        m();
        p();
    }

    public void s() {
        for (CaWebLayout caWebLayout : this.u.values()) {
            if (caWebLayout != null) {
                caWebLayout.h();
            }
        }
    }

    public boolean t(int i2) {
        CaWebLayout caWebLayout = this.t;
        return caWebLayout != null && caWebLayout.i(i2);
    }

    public void u() {
        this.f8722l.k();
        this.f8719i.stopScroll();
        com.jingdong.app.mall.home.n.g.v.a.a("ev_gone");
        com.jingdong.app.mall.home.n.g.v.b.d();
        x();
    }

    public void v() {
        if (getVisibility() != 0) {
            u();
            return;
        }
        z();
        com.jingdong.app.mall.home.n.g.v.b.b(y);
        com.jingdong.app.mall.home.n.g.v.a.a("ev_show");
    }

    public void w() {
        com.jingdong.app.mall.home.n.g.v.a.a("ev_tab_change");
        com.jingdong.app.mall.home.n.g.v.b.d();
    }

    public void y() {
        if (this.s.get()) {
            return;
        }
        this.f8719i.scrollToTop();
        this.f8722l.i(y, true, false);
    }
}
