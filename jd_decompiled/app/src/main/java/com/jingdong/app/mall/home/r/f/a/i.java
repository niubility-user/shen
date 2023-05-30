package com.jingdong.app.mall.home.r.f.a;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.app.mall.home.floor.model.entity.BubbleDynamicEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.BubbleDynamicEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicLeftRightLayout;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout;

/* loaded from: classes4.dex */
public class i extends com.jingdong.app.mall.home.r.f.a.b<BubbleDynamicEntity, BubbleDynamicEngine, IMallFloorUI> {

    /* renamed from: h  reason: collision with root package name */
    private BubbleDynamicMiddleLayout f10756h;

    /* renamed from: i  reason: collision with root package name */
    private final Handler f10757i = new Handler(Looper.getMainLooper());

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.b f10758j;

    /* renamed from: k  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.c f10759k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            i.this.U();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.x.c {
        b() {
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            if (i.this.f10756h != null) {
                i.this.f10756h.setTimeEnd();
            }
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            if (i.this.f10756h != null) {
                i.this.f10756h.setTimeText(eVar);
            }
        }
    }

    public int Q() {
        return ((BubbleDynamicEntity) this.d).middleWidth;
    }

    public void R() {
        BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout = this.f10756h;
        if (bubbleDynamicMiddleLayout != null) {
            bubbleDynamicMiddleLayout.onHomeResume();
        }
        long startTimeRemain = ((BubbleDynamicEntity) this.d).getStartTimeRemain();
        long endTimeRemain = ((BubbleDynamicEntity) this.d).getEndTimeRemain();
        if (startTimeRemain > 0) {
            return;
        }
        if (endTimeRemain <= 0) {
            BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout2 = this.f10756h;
            if (bubbleDynamicMiddleLayout2 != null) {
                bubbleDynamicMiddleLayout2.setTimeEnd();
                return;
            }
            return;
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10758j;
        if (bVar == null || this.f10759k == null) {
            return;
        }
        bVar.k(false);
        this.f10758j.i(endTimeRemain);
        this.f10758j.a(this.f10759k);
    }

    public void S() {
        BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout = this.f10756h;
        if (bubbleDynamicMiddleLayout != null) {
            bubbleDynamicMiddleLayout.onHomeStop();
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10758j;
        if (bVar != null) {
            bVar.k(true);
            com.jingdong.app.mall.home.x.c cVar = this.f10759k;
            if (cVar != null) {
                this.f10758j.g(cVar);
            }
        }
    }

    public void T(BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout, BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout, BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout2) {
        this.f10756h = bubbleDynamicMiddleLayout;
        bubbleDynamicLeftRightLayout.bindData(0, (BubbleDynamicEntity) this.d);
        bubbleDynamicMiddleLayout.bindData(1, (BubbleDynamicEntity) this.d);
        bubbleDynamicLeftRightLayout2.bindData(2, (BubbleDynamicEntity) this.d);
        U();
    }

    public void U() {
        E e2 = this.d;
        if (e2 == 0 || this.f10756h == null) {
            return;
        }
        long startTimeRemain = ((BubbleDynamicEntity) e2).getStartTimeRemain();
        long endTimeRemain = ((BubbleDynamicEntity) this.d).getEndTimeRemain();
        if (startTimeRemain > 0) {
            this.f10756h.beforeTimeStart();
            this.f10757i.removeCallbacksAndMessages(null);
            this.f10757i.postDelayed(new a(), startTimeRemain + 1);
        } else if (endTimeRemain <= 0) {
            this.f10756h.setTimeEnd();
        } else {
            try {
                this.f10758j = com.jingdong.app.mall.home.x.g.b().f(((BubbleDynamicEntity) this.d).getEndTime(), endTimeRemain);
                if (this.f10759k == null) {
                    this.f10759k = new b();
                }
                com.jingdong.app.mall.home.x.b bVar = this.f10758j;
                if (bVar != null) {
                    bVar.a(this.f10759k);
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
        if (((BubbleDynamicEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
