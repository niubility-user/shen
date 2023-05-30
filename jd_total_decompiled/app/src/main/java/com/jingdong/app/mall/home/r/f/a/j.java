package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.CategoryEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class j extends b<CategoryEntity, CategoryEngine, IMallFloorUI> {

    /* renamed from: h */
    private List<com.jingdong.app.mall.home.floor.view.widget.catatorytab.a> f10761h = new ArrayList();

    /* renamed from: i */
    private List<com.jingdong.app.mall.home.floor.view.widget.catatorytab.a> f10762i = new ArrayList();

    /* renamed from: j */
    private int f10763j;

    /* renamed from: k */
    private boolean f10764k;

    /* renamed from: l */
    private boolean f10765l;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ CategoryEntity.CaItem f10766g;

        a(CategoryEntity.CaItem caItem) {
            j.this = r1;
            this.f10766g = caItem;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            IMallFloorUI iMallFloorUI = (IMallFloorUI) j.this.c();
            if (iMallFloorUI instanceof MallFloorCategory) {
                ((MallFloorCategory) iMallFloorUI).addAsyncTab(this.f10766g);
            }
        }
    }

    private com.jingdong.app.mall.home.floor.view.widget.catatorytab.a Z() {
        for (com.jingdong.app.mall.home.floor.view.widget.catatorytab.a aVar : this.f10762i) {
            if (aVar.c() && aVar.b() > 0) {
                return aVar;
            }
        }
        for (com.jingdong.app.mall.home.floor.view.widget.catatorytab.a aVar2 : this.f10761h) {
            if (aVar2.c() && aVar2.b() > 0) {
                return aVar2;
            }
        }
        return null;
    }

    private void b0() {
        Iterator<com.jingdong.app.mall.home.floor.view.widget.catatorytab.a> it = this.f10762i.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        Iterator<com.jingdong.app.mall.home.floor.view.widget.catatorytab.a> it2 = this.f10761h.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
        this.f10761h.clear();
        this.f10762i.clear();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void I(IMallFloorUI iMallFloorUI) {
        this.f10765l = true;
        super.I(iMallFloorUI);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void K(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        this.f10765l = false;
        super.K(iMallFloorUI, mallFloorEvent);
    }

    public void P(com.jingdong.app.mall.home.floor.view.widget.catatorytab.a aVar) {
        int d = aVar.d();
        if (d == 0) {
            if (this.f10761h.contains(aVar)) {
                return;
            }
            this.f10761h.add(aVar);
        } else if (d != 1 || this.f10762i.contains(aVar)) {
        } else {
            this.f10762i.add(aVar);
        }
    }

    public List<CategoryEntity.CaItem> Q() {
        return ((CategoryEntity) this.d).getItemList();
    }

    public String R() {
        return ((CategoryEntity) this.d).getRightImg();
    }

    public JumpEntity S() {
        return ((CategoryEntity) this.d).getRightJump();
    }

    public int T() {
        return ((CategoryEntity) this.d).getRightWidth();
    }

    public String U() {
        return ((CategoryEntity) this.d).getSrvStr();
    }

    public int V() {
        return ((CategoryEntity) this.d).getTabMargin();
    }

    public boolean W() {
        return ((CategoryEntity) this.d).isHideSmileLine();
    }

    public boolean X() {
        return JDHomeFragment.P0() ? com.jingdong.app.mall.home.n.c.e() : this.f10765l;
    }

    public boolean Y() {
        return ((CategoryEntity) this.d).isShowAllBtn();
    }

    public void a0(boolean z) {
        com.jingdong.app.mall.home.floor.view.widget.catatorytab.a Z;
        if (z) {
            this.f10763j = ((CategoryEntity) this.d).getAnimationCount();
        }
        String str = X() + "";
        if (this.f10763j < 1 || this.f10764k || X() || (Z = Z()) == null) {
            return;
        }
        Z.e();
        this.f10763j--;
    }

    public void c0(boolean z) {
        this.f10764k = z;
    }

    public void d0(CategoryEntity.CaItem caItem) {
        com.jingdong.app.mall.home.o.a.f.E0(new a(caItem));
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b, com.jingdong.app.mall.home.r.f.a.c
    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        if ("ev_show".equals(baseEvent.getType())) {
            a0(true);
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        b0();
        this.f10763j = ((CategoryEntity) this.d).getAnimationCount();
        ((CategoryEngine) this.f10738e).k(this);
    }
}
