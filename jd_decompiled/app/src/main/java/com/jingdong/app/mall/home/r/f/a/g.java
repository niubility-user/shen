package com.jingdong.app.mall.home.r.f.a;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.BubbleBannerEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.IBubbleBannerSmall;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class g extends com.jingdong.app.mall.home.r.f.a.b<BubbleBannerEntity, BubbleBannerEngine, IMallFloorUI> {

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.b f10749h;

    /* renamed from: i  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.c f10750i;

    /* renamed from: j  reason: collision with root package name */
    private BubbleBannerMiddleLayout f10751j;

    /* renamed from: k  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.e.k.a f10752k;

    /* renamed from: l  reason: collision with root package name */
    private Handler f10753l = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            g.this.T();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.x.c {
        b() {
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            if (g.this.f10751j != null) {
                g.this.f10751j.setTimeEnd();
            }
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            if (g.this.f10751j != null) {
                g.this.f10751j.setTimeText(eVar);
            }
        }
    }

    public void Q() {
        BubbleBannerMiddleLayout bubbleBannerMiddleLayout = this.f10751j;
        if (bubbleBannerMiddleLayout != null) {
            bubbleBannerMiddleLayout.onHomeResume();
        }
        long h2 = this.f10752k.h();
        long e2 = this.f10752k.e();
        if (h2 > 0) {
            return;
        }
        if (e2 <= 0) {
            this.f10751j.setTimeEnd();
            return;
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10749h;
        if (bVar == null || this.f10750i == null) {
            return;
        }
        bVar.k(false);
        this.f10749h.i(e2);
        this.f10749h.a(this.f10750i);
    }

    public void R() {
        BubbleBannerMiddleLayout bubbleBannerMiddleLayout = this.f10751j;
        if (bubbleBannerMiddleLayout != null) {
            bubbleBannerMiddleLayout.onHomeStop();
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10749h;
        if (bVar != null) {
            bVar.k(true);
            com.jingdong.app.mall.home.x.c cVar = this.f10750i;
            if (cVar != null) {
                this.f10749h.g(cVar);
            }
        }
    }

    public void S(IBubbleBannerSmall[] iBubbleBannerSmallArr, BubbleBannerMiddleLayout bubbleBannerMiddleLayout) {
        this.f10752k = ((BubbleBannerEntity) this.d).getMiddleItem();
        this.f10751j = bubbleBannerMiddleLayout;
        bubbleBannerMiddleLayout.onViewBind(((BubbleBannerEntity) this.d).getMiddle(), this.f10752k, 1);
        ((BubbleBannerEntity) this.d).getUiType();
        ArrayList<com.jingdong.app.mall.home.r.e.f> smallList = ((BubbleBannerEntity) this.d).getSmallList();
        for (int i2 = 0; i2 < iBubbleBannerSmallArr.length; i2++) {
            com.jingdong.app.mall.home.r.e.f fVar = smallList.get(i2);
            if (fVar != null) {
                iBubbleBannerSmallArr[i2].onViewBind(fVar, i2);
            }
        }
        T();
    }

    public void T() {
        com.jingdong.app.mall.home.r.e.k.a aVar = this.f10752k;
        if (aVar == null) {
            return;
        }
        long h2 = aVar.h();
        long e2 = this.f10752k.e();
        if (h2 > 0) {
            this.f10751j.beforeTimeStart();
            this.f10753l.removeCallbacksAndMessages(null);
            this.f10753l.postDelayed(new a(), h2 + 1);
        } else if (e2 <= 0) {
            this.f10751j.setTimeEnd();
        } else {
            try {
                this.f10749h = com.jingdong.app.mall.home.x.g.b().f(this.f10752k.d(), e2);
                if (this.f10750i == null) {
                    this.f10750i = new b();
                }
                com.jingdong.app.mall.home.x.b bVar = this.f10749h;
                if (bVar != null) {
                    bVar.a(this.f10750i);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((BubbleBannerEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.cleanUI();
        iMallFloorUI.onSetVisible(false);
    }
}
