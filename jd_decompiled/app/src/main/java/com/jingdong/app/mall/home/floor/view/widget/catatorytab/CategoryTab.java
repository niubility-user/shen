package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.category.widget.CaSmileLine;
import com.jingdong.app.mall.home.category.widget.CaSmileLineB;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView;
import com.jingdong.app.mall.home.r.f.a.j;
import com.jingdong.app.mall.home.widget.HomeRecycleView;

/* loaded from: classes4.dex */
public class CategoryTab extends RelativeLayout implements com.jingdong.app.mall.home.floor.view.widget.catatorytab.a {

    /* renamed from: g */
    private CategoryTabItemBaseView f10214g;

    /* renamed from: h */
    private f f10215h;

    /* renamed from: i */
    private CategoryEntity.CaItem f10216i;

    /* renamed from: j */
    private int f10217j;

    /* renamed from: k */
    private j f10218k;

    /* renamed from: l */
    private CategoryTabTitle f10219l;

    /* renamed from: m */
    private boolean f10220m;

    /* renamed from: n */
    private boolean f10221n;
    private CaSmileLine o;
    private f p;
    private CaSmileLineB q;
    private f r;
    private CategoryTabItemBaseView.b s;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends AccessibilityDelegateCompat {
        a() {
            CategoryTab.this = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setSelected(CategoryTab.this.f10216i.isSelect());
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ int f10222g;

        b(int i2) {
            CategoryTab.this = r1;
            this.f10222g = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CategoryTab.this.f10219l.mCurrentItem == CategoryTab.this || !s.f9358e) {
                return;
            }
            if (JDHomeFragment.P0() || !HomeRecycleView.j()) {
                com.jingdong.app.mall.home.r.c.b srvJson = CategoryTab.this.f10216i.getSrvJson();
                srvJson.a("ceiling", com.jingdong.app.mall.home.a.t.i());
                com.jingdong.app.mall.home.r.c.a.s("Home_ClassifyTab", "", srvJson.toString());
                CategoryTab.this.f10219l.mCurrentItem.u(false);
                CategoryTabTitle categoryTabTitle = CategoryTab.this.f10219l;
                CategoryTab categoryTab = CategoryTab.this;
                categoryTabTitle.mCurrentItem = categoryTab;
                categoryTab.u(true);
                CategoryTab.this.f10219l.mScrollContent.scrollTo(((view.getRight() + view.getLeft()) - CategoryTab.this.f10219l.mScrollContent.getWidth()) >> 1, 0);
                CategoryTabTitle.onItemSelect(CategoryTab.this.f10216i, this.f10222g);
                new com.jingdong.app.mall.home.q.a("\u5206\u7c7bTab\u70b9\u51fb", CategoryTab.this.f10216i.clkLog).b();
                CategoryTab.this.f10218k.a0(true);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
            CategoryTab.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CategoryTab.this.t();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements CategoryTabItemBaseView.b {
        d() {
            CategoryTab.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView.b
        public void onAnimEnd() {
            CategoryTab.this.q();
        }
    }

    public CategoryTab(Context context) {
        super(context);
        this.s = new d();
        i();
        ViewCompat.setAccessibilityDelegate(this, new a());
    }

    private void i() {
        this.o = new CaSmileLine(getContext());
        f fVar = new f(40, 20);
        this.p = fVar;
        fVar.E(0, 0, 0, 14);
        RelativeLayout.LayoutParams u = this.p.u(this.o);
        u.addRule(14);
        u.addRule(12);
        addView(this.o, u);
        CaSmileLineB caSmileLineB = new CaSmileLineB(getContext());
        this.q = caSmileLineB;
        caSmileLineB.setVisibility(8);
        f fVar2 = new f(40, 20);
        this.r = fVar2;
        fVar2.E(0, 0, 0, 11);
        RelativeLayout.LayoutParams u2 = this.r.u(this.q);
        u2.addRule(14);
        u2.addRule(12);
        addView(this.q, u2);
    }

    private void j(int i2, int i3) {
        CategoryTabItemBaseView categoryTabItemBaseView = this.f10214g;
        if (categoryTabItemBaseView != null) {
            removeView(categoryTabItemBaseView);
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3 && i2 != 4) {
                    if (!(this.f10214g instanceof CategoryTabNormalView)) {
                        this.f10214g = new CategoryTabNormalView(getContext());
                        this.f10215h = new f(-2, -1);
                    }
                } else if (!(this.f10214g instanceof CategoryTabIconView)) {
                    this.f10214g = new CategoryTabIconView(getContext());
                    f fVar = new f(-2, -1);
                    this.f10215h = fVar;
                    fVar.J(16, 0, 0, 0);
                }
            } else if (!(this.f10214g instanceof CategoryTabFlipperView)) {
                this.f10214g = new CategoryTabFlipperView(getContext());
                this.f10215h = new f(-2, -1);
            }
        } else if (!(this.f10214g instanceof CategoryTabBlingView)) {
            this.f10214g = new CategoryTabBlingView(getContext());
            this.f10215h = new f(-2, -1);
        }
        this.f10214g.f(this.f10218k.V());
        CategoryTabItemBaseView categoryTabItemBaseView2 = this.f10214g;
        addView(categoryTabItemBaseView2, this.f10215h.u(categoryTabItemBaseView2));
    }

    private void l() {
        if (com.jingdong.app.mall.home.v.d.a.f()) {
            this.o.setVisibility(8);
            this.q.setVisibility((!this.f10216i.isSelect() || this.f10218k.W()) ? 8 : 0);
            this.q.b(this.f10216i.mCategoryEntity.getSmileColor(), com.jingdong.app.mall.home.floor.common.d.d(26), com.jingdong.app.mall.home.floor.common.d.d(30), com.jingdong.app.mall.home.floor.common.d.d(5));
        } else {
            this.q.setVisibility(8);
            this.o.setVisibility((!this.f10216i.isSelect() || this.f10218k.W()) ? 8 : 0);
            this.o.b(this.f10216i.mCategoryEntity.getSmileColor(), com.jingdong.app.mall.home.floor.common.d.d(38), com.jingdong.app.mall.home.floor.common.d.d(38), com.jingdong.app.mall.home.floor.common.d.d(8));
        }
        CategoryTabItemBaseView categoryTabItemBaseView = this.f10214g;
        if (categoryTabItemBaseView != null) {
            categoryTabItemBaseView.a();
        }
        if (this.f10216i.isSelect()) {
            this.f10220m = true;
            a();
        }
    }

    private boolean o() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int i2 = iArr[0];
        return i2 >= 0 && i2 < this.f10219l.mScrollContent.getWidth();
    }

    private boolean p() {
        return m.I(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, true);
    }

    private void r(CategoryEntity.CaItem caItem, int i2) {
        if (caItem == null) {
            return;
        }
        if (this.f10216i != caItem || this.f10219l.isRebind()) {
            this.f10217j = i2;
            this.f10216i = caItem;
            j(caItem.getImgAnimType(), caItem.getImgType());
            setOnClickListener(new b(i2));
            f.c(this.o, this.p);
            f.c(this.q, this.r);
            this.f10214g.d(caItem, i2);
            if (this.f10216i.isAnimType()) {
                this.f10218k.P(this);
                this.f10214g.e(this.s);
            }
        }
    }

    private void v(boolean z) {
        this.f10221n = z;
        this.f10218k.c0(z);
        if (z) {
            this.f10216i.getSrvJson().a("isdynamic", "1");
            this.f10216i.getExpoJson().a("isdynamic", "1");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.a
    public void a() {
        CategoryTabItemBaseView categoryTabItemBaseView = this.f10214g;
        if (categoryTabItemBaseView != null) {
            categoryTabItemBaseView.i();
        }
        if (n()) {
            v(false);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.a
    public int b() {
        CategoryEntity.CaItem caItem = this.f10216i;
        if (caItem != null) {
            return caItem.getPlayCount();
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.a
    public boolean c() {
        return b() > 0 && o() && !this.f10220m;
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.a
    public int d() {
        CategoryEntity.CaItem caItem = this.f10216i;
        if (caItem != null) {
            return caItem.getDynamicType();
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.a
    public void e() {
        CategoryEntity.CaItem caItem = this.f10216i;
        if (caItem != null) {
            caItem.setPlayCount(caItem.getPlayCount() - 1);
        }
        CategoryTabItemBaseView categoryTabItemBaseView = this.f10214g;
        if (categoryTabItemBaseView != null) {
            categoryTabItemBaseView.h();
        }
        v(true);
    }

    public void k(CategoryTabTitle categoryTabTitle, j jVar, CategoryEntity.CaItem caItem, int i2) {
        this.f10220m = false;
        this.f10219l = categoryTabTitle;
        this.f10218k = jVar;
        r(caItem, i2);
        l();
    }

    public CategoryEntity.CaItem m() {
        return this.f10216i;
    }

    public boolean n() {
        return this.f10221n;
    }

    public void q() {
        v(false);
        if (!p() || this.f10218k.X()) {
            return;
        }
        if (o() && b() > 0) {
            e();
        } else {
            this.f10218k.a0(false);
        }
    }

    public void s() {
        new com.jingdong.app.mall.home.q.a("\u5206\u7c7bTab\u66dd\u5149", true, this.f10216i.expoLog).b();
    }

    public void t() {
        CategoryEntity.CaItem caItem;
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new c());
        } else if (this.f10214g == null || (caItem = this.f10216i) == null) {
        } else {
            caItem.setPName(TitleTabManager.getInstance().getHomeName());
            this.f10214g.d(this.f10216i, this.f10217j);
        }
    }

    public void u(boolean z) {
        this.f10216i.setSelect(z);
        l();
    }
}
