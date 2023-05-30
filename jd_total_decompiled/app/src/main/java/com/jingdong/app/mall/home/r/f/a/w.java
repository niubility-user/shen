package com.jingdong.app.mall.home.r.f.a;

import android.view.View;
import android.widget.LinearLayout;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.PanicFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.PanicFloorEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorPanic;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class w extends com.jingdong.app.mall.home.r.f.a.a<PanicFloorEntity, PanicFloorEngine, MallFloorPanic> {

    /* renamed from: i  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.b f10785i;

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.c f10786j;

    /* renamed from: k  reason: collision with root package name */
    private View f10787k;

    /* renamed from: l  reason: collision with root package name */
    private TimeFormatView f10788l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.x.c {
        a() {
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            w wVar = w.this;
            wVar.E0(wVar.f10788l);
            w.this.C0();
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            if (eVar == null) {
                return;
            }
            try {
                String a = eVar.a();
                String b = eVar.b();
                String c2 = eVar.c();
                if (w.this.f10788l != null) {
                    TimeFormatView timeFormatView = w.this.f10788l;
                    if (a.length() <= 1) {
                        a = "0" + a;
                    }
                    if (b.length() <= 1) {
                        b = "0" + b;
                    }
                    if (c2.length() <= 1) {
                        c2 = "0" + c2;
                    }
                    timeFormatView.m(a, b, c2);
                }
                w.this.x0(eVar);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public w(Class<PanicFloorEntity> cls, Class<PanicFloorEngine> cls2) {
        super(cls, cls2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0(com.jingdong.app.mall.home.x.e eVar) {
        int miaoshaAdvance = ((PanicFloorEntity) this.d).getMiaoshaAdvance();
        int i2 = miaoshaAdvance / 60;
        int i3 = miaoshaAdvance % 60;
        JDJSONObject nextRoundObject = ((PanicFloorEntity) this.d).getNextRoundObject();
        if (eVar.d == 0 && eVar.f11088e == i2 && eVar.f11089f == i3 && nextRoundObject == null) {
            ((PanicFloorEngine) this.f10738e).m(true, (PanicFloorEntity) this.d);
        }
    }

    public void A0(boolean z) {
        if (Log.D) {
            Log.d("HHH_MallPanicFloorPresenter", "onPanicResume.");
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10785i;
        if (bVar != null) {
            bVar.k(false);
        }
        if (com.jingdong.app.mall.home.floor.common.i.g.k()) {
            return;
        }
        PanicFloorEntity.BuyTimeViewData buyTimeViewData = ((PanicFloorEntity) this.d).getBuyTimeViewData();
        long timeRemain = (buyTimeViewData.getTimeRemain() * 1000) - buyTimeViewData.getTimeOffset();
        if (Log.D) {
            Log.d("HHH_MallPanicFloorPresenter", "onPanicResume. end: " + timeRemain);
        }
        if (timeRemain <= 0) {
            C0();
            return;
        }
        com.jingdong.app.mall.home.x.b bVar2 = this.f10785i;
        if (bVar2 != null) {
            com.jingdong.app.mall.home.x.c cVar = this.f10786j;
            if (cVar != null) {
                bVar2.a(cVar);
            }
            this.f10785i.i(timeRemain);
        }
    }

    public void B0() {
        if (Log.D) {
            Log.d("HHH_MallPanicFloorPresenter", "onPanicStop.");
        }
        com.jingdong.app.mall.home.x.b bVar = this.f10785i;
        if (bVar != null) {
            bVar.k(true);
            com.jingdong.app.mall.home.x.c cVar = this.f10786j;
            if (cVar != null) {
                this.f10785i.g(cVar);
            }
        }
    }

    public void C0() {
        if (Log.D) {
            Log.d("HHH_MallPanicFloorPresenter", "onRefresh.");
        }
        JDJSONObject nextRoundObject = ((PanicFloorEntity) this.d).getNextRoundObject();
        if (nextRoundObject != null) {
            if (!((PanicFloorEngine) this.f10738e).l(nextRoundObject, (PanicFloorEntity) this.d, true)) {
                ((PanicFloorEngine) this.f10738e).m(false, (PanicFloorEntity) this.d);
            }
            ((PanicFloorEntity) this.d).clearNextRoundMap();
            return;
        }
        ((PanicFloorEngine) this.f10738e).m(false, (PanicFloorEntity) this.d);
    }

    public void D0() {
        ((PanicFloorEntity) this.d).parseJsonParam();
    }

    public void E0(TimeFormatView timeFormatView) {
        if (timeFormatView != null) {
            timeFormatView.m("00", "00", "00");
        }
    }

    public void F0() {
        if (com.jingdong.app.mall.home.floor.common.i.g.k()) {
            com.jingdong.app.mall.home.x.b bVar = this.f10785i;
            if (bVar != null) {
                bVar.g(this.f10786j);
            }
            com.jingdong.app.mall.home.n.h.c.k(true, this.f10787k);
            return;
        }
        com.jingdong.app.mall.home.n.h.c.k(false, this.f10787k);
        PanicFloorEntity.BuyTimeViewData buyTimeViewData = ((PanicFloorEntity) this.d).getBuyTimeViewData();
        long abs = (Math.abs(buyTimeViewData.getTimeRemain()) * 1000) - buyTimeViewData.getTimeOffset();
        if (abs <= 0) {
            TimeFormatView timeFormatView = this.f10788l;
            if (timeFormatView != null) {
                E0(timeFormatView);
                C0();
                return;
            }
            return;
        }
        try {
            long nextStartTime = buyTimeViewData.getNextStartTime();
            if (Log.D) {
                Log.d("HHH_MallPanicFloorPresenter", "startBuyTimeCount -> key: " + nextStartTime);
            }
            this.f10785i = com.jingdong.app.mall.home.x.g.b().f(nextStartTime, abs);
            if (this.f10786j == null) {
                if (Log.D) {
                    Log.d("HHH_MallPanicFloorPresenter", "startBuyTimeCount. new mCountdownListener");
                }
                this.f10786j = new a();
            }
            com.jingdong.app.mall.home.x.b bVar2 = this.f10785i;
            if (bVar2 != null) {
                bVar2.a(this.f10786j);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void G(MallFloorEvent mallFloorEvent) {
        MallFloorPanic mallFloorPanic = (MallFloorPanic) c();
        if (mallFloorPanic == null) {
            return;
        }
        String type = mallFloorEvent.getType();
        type.hashCode();
        if (!type.equals("home_refresh_floor")) {
            super.G(mallFloorEvent);
            return;
        }
        try {
            if (mallFloorEvent.c().equals(i())) {
                mallFloorPanic.onRefreshViewPager(((PanicFloorEntity) this.d).getContentHeight(), 0, 0);
                mallFloorPanic.startTimeTick();
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.adapter.BaseListItemPagerAdapter.b
    public void a(View view) {
        JumpEntity advertJump;
        MallFloorPanic mallFloorPanic = (MallFloorPanic) c();
        if (mallFloorPanic == null || (advertJump = ((PanicFloorEntity) this.d).getAdvertJump()) == null) {
            return;
        }
        mallFloorPanic.execJump(advertJump);
        mallFloorPanic.sendMaiDianData("Home_SeckillAdAccess", advertJump.srv);
    }

    public void c0(LinearLayout linearLayout, TimeFormatView timeFormatView) {
        this.f10787k = linearLayout;
        this.f10788l = timeFormatView;
    }

    public String d0(int i2, int i3) {
        return ((PanicFloorEntity) this.d).collectExpoJsonParam(i2, i3);
    }

    public int e0() {
        return ((PanicFloorEntity) this.d).getBuyTimeLayoutHeight();
    }

    public int f0() {
        E e2 = this.d;
        if (e2 == 0) {
            return 0;
        }
        return ((PanicFloorEntity) this.d).getBuyTimeLayoutWidth() + ((((PanicFloorEntity) e2).getBuyTimeDateSpaceX() - DPIUtil.dip2px(2.0f)) * 10);
    }

    public String g0() {
        return ((PanicFloorEntity) this.d).element.l();
    }

    public int h0() {
        return ((PanicFloorEntity) this.d).getInterestPointColor();
    }

    public JumpEntity i0() {
        return ((PanicFloorEntity) this.d).jumpEntity;
    }

    public int j0() {
        return ((PanicFloorEntity) this.d).getNameImgHeight();
    }

    public int k0() {
        return ((PanicFloorEntity) this.d).getNameImgWidth();
    }

    public String l0() {
        return ((PanicFloorEntity) this.d).getNameText();
    }

    public String m0() {
        return ((PanicFloorEntity) this.d).getPanicExpoSourceValue();
    }

    public int n0() {
        return ((PanicFloorEntity) this.d).getPriceStyle();
    }

    public String o0(int i2) {
        return ((PanicFloorEntity) this.d).getProductJsonByPosition(i2);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b, com.jingdong.app.mall.home.r.f.a.c
    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof MallFloorEvent) {
            MallFloorEvent mallFloorEvent = (MallFloorEvent) baseEvent;
            String type = mallFloorEvent.getType();
            type.hashCode();
            if (type.equals("home_resume")) {
                A0(mallFloorEvent.d());
            } else if (type.equals("home_pause")) {
                B0();
            }
            super.onEventMainThread(baseEvent);
        }
    }

    public int[] p0() {
        return ((PanicFloorEntity) this.d).getPromotionTagColor();
    }

    public String q0() {
        return ((PanicFloorEntity) this.d).getRightCornerAdView();
    }

    public int[] r0() {
        return ((PanicFloorEntity) this.d).getRightArrowColor();
    }

    public String s0() {
        return ((PanicFloorEntity) this.d).getShowNameImg();
    }

    public String t0() {
        return ((PanicFloorEntity) this.d).getSkuTagImg();
    }

    public int u0() {
        return ((PanicFloorEntity) this.d).getTitleColor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        MallFloorPanic mallFloorPanic = (MallFloorPanic) c();
        if (mallFloorPanic == null) {
            return;
        }
        E e2 = this.d;
        if (((PanicFloorEntity) e2).element != null && ((PanicFloorEngine) this.f10738e).n(false, ((PanicFloorEntity) e2).element.h(), (PanicFloorEntity) this.d)) {
            mallFloorPanic.onRefreshViewPager(((PanicFloorEntity) this.d).getContentHeight(), 0, 0);
            mallFloorPanic.initFloorView();
            mallFloorPanic.startTimeTick();
            return;
        }
        mallFloorPanic.onSetVisible(false);
    }

    public void v0(TimeFormatView timeFormatView) {
        PanicFloorEntity.BuyTimeViewData buyTimeViewData = ((PanicFloorEntity) this.d).getBuyTimeViewData();
        timeFormatView.i(buyTimeViewData.getTimePointColor());
        timeFormatView.e(buyTimeViewData.getBackgroundColor());
        timeFormatView.h(buyTimeViewData.getBackgroundWidth());
        timeFormatView.g(buyTimeViewData.getBackgroundHeight());
        timeFormatView.k(buyTimeViewData.getTimeTextColor());
        timeFormatView.l(buyTimeViewData.getTimeTextSizePx());
    }

    public boolean w0() {
        return ((PanicFloorEntity) this.d).isVersion100();
    }

    public void y0(int i2) {
        MallFloorPanic mallFloorPanic = (MallFloorPanic) c();
        if (mallFloorPanic == null) {
            return;
        }
        mallFloorPanic.onClickItem(((PanicFloorEntity) this.d).getItemByPosition(i2), ((PanicFloorEntity) this.d).isTestA(), i2);
    }

    public void z0() {
        MallFloorPanic mallFloorPanic = (MallFloorPanic) c();
        if (mallFloorPanic == null) {
            return;
        }
        mallFloorPanic.jumpToRightCornerAd(i0());
        try {
            com.jingdong.app.mall.home.r.c.a.u("Home_SeckillSlideIn", ((PanicFloorEntity) this.d).getMaiDianSourceValue(false), ((PanicFloorEntity) this.d).element.l(), RecommendMtaUtils.Home_PageId, null, "");
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }
}
