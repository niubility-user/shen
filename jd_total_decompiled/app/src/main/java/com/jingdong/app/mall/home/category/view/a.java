package com.jingdong.app.mall.home.category.view;

import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.n.d;
import com.jingdong.app.mall.home.n.e;
import com.jingdong.app.mall.home.n.g.s;
import com.jingdong.app.mall.home.n.h.a;
import com.jingdong.app.mall.home.o.a.f;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: f  reason: collision with root package name */
    private static d f8789f = new d();
    private CaContentLayout a;
    private CaPullRefreshLayout b;

    /* renamed from: c  reason: collision with root package name */
    private CaRecycleView f8790c;
    private CaDecorateLayout d;

    /* renamed from: e  reason: collision with root package name */
    private CaAdapter f8791e;

    /* renamed from: com.jingdong.app.mall.home.category.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0277a implements PullToRefreshBase.OnRefreshListener<RecyclerView> {
        C0277a() {
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
        public void onRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
            CategoryEntity.CaItem i2 = CaContentLayout.i();
            if (i2 != null) {
                com.jingdong.app.mall.home.n.g.v.b.c("Category_Main_Fresh", i2.getSrvString());
                a.this.i(i2, false, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends d.c {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ boolean f8792f;

        /* renamed from: com.jingdong.app.mall.home.category.view.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0278a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ CategoryEntity.CaItem f8794g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ JDJSONObject f8795h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ List f8796i;

            C0278a(CategoryEntity.CaItem caItem, JDJSONObject jDJSONObject, List list) {
                this.f8794g = caItem;
                this.f8795h = jDJSONObject;
                this.f8796i = list;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                CategoryEntity.CaItem i2 = CaContentLayout.i();
                CategoryEntity.CaItem caItem = this.f8794g;
                if (i2 != caItem) {
                    return;
                }
                caItem.clearRequest();
                this.f8794g.sendPvExpoParams(this.f8795h);
                a.f8789f.h();
                a.this.b.onRefreshComplete();
                com.jingdong.app.mall.home.n.g.v.b.d();
                a.this.j(this.f8796i);
                a.this.h(true, false);
                com.jingdong.app.mall.home.n.h.a.g(this.f8794g, this.f8795h);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.home.category.view.a$b$b  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class C0279b extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ CategoryEntity.CaItem f8798g;

            C0279b(CategoryEntity.CaItem caItem) {
                this.f8798g = caItem;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (CaContentLayout.i() != this.f8798g) {
                    return;
                }
                a.this.b.onRefreshComplete();
                b bVar = b.this;
                if (bVar.f8792f) {
                    a.this.a.D(true);
                    com.jingdong.app.mall.home.n.h.a.c(this.f8798g);
                    a.this.f8791e.l();
                    return;
                }
                a.this.a.C(true);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(boolean z, boolean z2, CategoryEntity.CaItem caItem, boolean z3) {
            super(z, z2, caItem);
            this.f8792f = z3;
        }

        @Override // com.jingdong.app.mall.home.n.d.c
        public void h(CategoryEntity.CaItem caItem, @NotNull JDJSONObject jDJSONObject) {
            if (CaContentLayout.i() != caItem) {
                return;
            }
            List<com.jingdong.app.mall.home.n.g.u.c> f2 = e.f(jDJSONObject, a());
            if (f2 != null && f2.size() > 0) {
                f.E0(new C0278a(caItem, jDJSONObject, f2));
            } else {
                i(caItem, "");
            }
        }

        @Override // com.jingdong.app.mall.home.n.d.c
        public void i(CategoryEntity.CaItem caItem, String str) {
            if (CaContentLayout.i() != caItem) {
                return;
            }
            f.E0(new C0279b(caItem));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends d.c {

        /* renamed from: com.jingdong.app.mall.home.category.view.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0280a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ List f8801g;

            C0280a(List list) {
                this.f8801g = list;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (c.this.f(CaContentLayout.i())) {
                    if (((d.c) c.this).f10348e) {
                        a.this.f8790c.j(a.this.f8791e);
                    }
                    a.this.f8791e.h(this.f8801g, ((d.c) c.this).d);
                    int p = a.this.f8791e.p();
                    if (p < 4) {
                        a.this.f();
                    }
                    a.this.a.B(c.this.e() ? 2 : 0, p);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class b extends com.jingdong.app.mall.home.o.a.b {
            b() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                a.this.a.B(3, a.this.f8791e.p());
                a.this.f8790c.j(a.this.f8791e);
                a.this.f8791e.h(new ArrayList(), true);
            }
        }

        c(boolean z, boolean z2, CategoryEntity.CaItem caItem) {
            super(z, z2, caItem);
        }

        @Override // com.jingdong.app.mall.home.n.d.c
        public void h(CategoryEntity.CaItem caItem, @NotNull JDJSONObject jDJSONObject) {
            if (CaContentLayout.i() == caItem && f(CaContentLayout.i()) && !caItem.isBottom()) {
                List<com.jingdong.app.mall.home.n.g.u.c> e2 = e.e(jDJSONObject, a(), this.d ? 0 : a.this.f8791e.p());
                caItem.setBottom("0".equals(com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "hasNextPage", "0")));
                if (e2 != null && e2.size() > 0) {
                    d(jDJSONObject);
                    f.E0(new C0280a(e2));
                } else if (caItem.getPage() == 1) {
                    o();
                } else if (e()) {
                    a.this.a.B(2, a.this.f8791e.p());
                } else {
                    i(caItem, "");
                }
            }
        }

        @Override // com.jingdong.app.mall.home.n.d.c
        public void i(CategoryEntity.CaItem caItem, String str) {
            if (CaContentLayout.i() != caItem || caItem.isBottom()) {
                return;
            }
            if (this.d) {
                a.this.f8791e.m();
            }
            a.this.a.B(1, a.this.f8791e.p());
        }

        void o() {
            f.E0(new b());
        }
    }

    public a(CaContentLayout caContentLayout, CaPullRefreshLayout caPullRefreshLayout, CaRecycleView caRecycleView, CaDecorateLayout caDecorateLayout, CaAdapter caAdapter) {
        this.a = caContentLayout;
        this.b = caPullRefreshLayout;
        this.f8790c = caRecycleView;
        this.d = caDecorateLayout;
        this.f8791e = caAdapter;
        caPullRefreshLayout.setOnRefreshListener(new C0277a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        h(false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(s sVar) {
        f8789f.h();
        com.jingdong.app.mall.home.n.g.v.b.e("Category_Selected_Product_Expo");
        com.jingdong.app.mall.home.n.g.v.b.e("Category_Selected_Content_Expo");
        sVar.I(CaContentLayout.i());
        h(true, true);
    }

    synchronized void h(boolean z, boolean z2) {
        CategoryEntity.CaItem i2 = CaContentLayout.i();
        if (!f8789f.j() && !i2.isBottom()) {
            if (z) {
                this.a.B(4, 0);
                if (z2) {
                    this.f8790c.j(this.f8791e);
                }
                this.f8791e.m();
            } else {
                this.a.B(0, this.f8791e.p());
            }
            f8789f.l(new c(z, z2, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void i(CategoryEntity.CaItem caItem, boolean z, boolean z2) {
        JDJSONObject b2;
        List<com.jingdong.app.mall.home.n.g.u.c> g2;
        if (caItem == null) {
            return;
        }
        a.C0310a c0310a = null;
        if (z2) {
            try {
                this.a.D(false);
                c0310a = com.jingdong.app.mall.home.n.h.a.d(caItem);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (c0310a != null && (g2 = e.g((b2 = c0310a.b()), caItem, c0310a.c())) != null && g2.size() > 0) {
            caItem.sendPvExpoParams(b2);
            j(g2);
            h(true, false);
        } else if (f8789f.k()) {
        } else {
            f8789f.m(this.a.getContext(), new b(false, false, caItem, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(List<com.jingdong.app.mall.home.n.g.u.c> list) {
        this.d.c(e.c(), this.b);
        this.f8790c.i();
        this.a.k();
        this.f8791e.w(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        this.b.onRefreshComplete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.b.dispatchCancelEvent();
        this.b.onRefreshComplete();
        com.jingdong.app.mall.home.x.a.b().e();
        f8789f.g();
    }
}
