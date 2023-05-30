package com.jingdong.app.mall.home.r.f.a;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.SecKillElderEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.SecKillElderEngine;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class x extends b<SecKillElderEntity, SecKillElderEngine, MallFloorSecKillElder> {

    /* renamed from: h */
    private com.jingdong.app.mall.home.x.b f10789h;

    /* renamed from: i */
    private com.jingdong.app.mall.home.x.c f10790i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.x.c {
        final /* synthetic */ MallFloorSecKillElder.SecKillElderTitle b;

        a(MallFloorSecKillElder.SecKillElderTitle secKillElderTitle) {
            x.this = r1;
            this.b = secKillElderTitle;
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            this.b.updateTimeDrawable("00", "00", "00");
            x.this.U();
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
                MallFloorSecKillElder.SecKillElderTitle secKillElderTitle = this.b;
                if (a.length() <= 1) {
                    a = "0" + a;
                }
                if (b.length() <= 1) {
                    b = "0" + b;
                }
                if (c2.length() <= 1) {
                    c2 = "0" + c2;
                }
                secKillElderTitle.updateTimeDrawable(a, b, c2);
                x.this.T(eVar);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean R(JDJSONObject jDJSONObject, boolean z) {
        G g2 = this.f10738e;
        if (g2 == 0) {
            return false;
        }
        return ((SecKillElderEngine) g2).k(jDJSONObject, z);
    }

    private void S(boolean z) {
        G g2 = this.f10738e;
        if (g2 == 0) {
            return;
        }
        ((SecKillElderEngine) g2).l(z);
    }

    public void T(com.jingdong.app.mall.home.x.e eVar) {
        int miaoshaAdvance = ((SecKillElderEntity) this.d).getMiaoshaAdvance();
        int i2 = miaoshaAdvance / 60;
        int i3 = miaoshaAdvance % 60;
        JDJSONObject nextRoundObject = ((SecKillElderEntity) this.d).getNextRoundObject();
        if (eVar.d == 0 && eVar.f11088e == i2 && eVar.f11089f == i3 && nextRoundObject == null) {
            S(true);
        }
    }

    public SecKillElderEntity Q() {
        return (SecKillElderEntity) this.d;
    }

    public void U() {
        JDJSONObject nextRoundObject = ((SecKillElderEntity) this.d).getNextRoundObject();
        if (nextRoundObject != null) {
            if (!R(nextRoundObject, true)) {
                S(false);
            }
            ((SecKillElderEntity) this.d).clearNextRoundMap();
            return;
        }
        S(false);
    }

    public void V(String str, int i2) {
        G g2 = this.f10738e;
        if (g2 == 0) {
            return;
        }
        ((SecKillElderEngine) g2).o(str, i2);
    }

    public void W(MallFloorSecKillElder.SecKillElderTitle secKillElderTitle) {
        if (com.jingdong.app.mall.home.floor.common.i.g.k()) {
            com.jingdong.app.mall.home.x.b bVar = this.f10789h;
            if (bVar != null) {
                bVar.g(this.f10790i);
            }
            secKillElderTitle.updateTimeGone(true);
            return;
        }
        secKillElderTitle.updateTimeGone(false);
        b.a buyTimeViewData = ((SecKillElderEntity) this.d).getBuyTimeViewData();
        long abs = (Math.abs(buyTimeViewData.c()) * 1000) - buyTimeViewData.b();
        if (abs <= 0) {
            secKillElderTitle.updateTimeDrawable("00", "00", "00");
            U();
            return;
        }
        try {
            this.f10789h = com.jingdong.app.mall.home.x.g.b().f(buyTimeViewData.a(), abs);
            if (this.f10790i == null) {
                this.f10790i = new a(secKillElderTitle);
            }
            com.jingdong.app.mall.home.x.b bVar2 = this.f10789h;
            if (bVar2 != null) {
                bVar2.a(this.f10790i);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((SecKillElderEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
