package com.jingdong.app.mall.home.r.f.a;

import android.os.SystemClock;
import com.jingdong.app.mall.home.floor.model.entity.MarketFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.MarketFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorMarket;
import com.jingdong.corelib.utils.Log;
import java.util.List;

/* loaded from: classes4.dex */
public class u extends b<MarketFloorEntity, MarketFloorEngine, MallFloorMarket> {

    /* renamed from: h */
    private com.jingdong.app.mall.home.x.c f10784h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.x.c {
        final /* synthetic */ MallFloorMarket b;

        a(u uVar, MallFloorMarket mallFloorMarket) {
            this.b = mallFloorMarket;
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            this.b.setTimeEnd();
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
                MallFloorMarket mallFloorMarket = this.b;
                if (a.length() <= 1) {
                    a = "0" + a;
                }
                if (b.length() <= 1) {
                    b = "0" + b;
                }
                if (c2.length() <= 1) {
                    c2 = "0" + c2;
                }
                mallFloorMarket.updateTime(a, b, c2);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public com.jingdong.app.mall.home.r.e.f P() {
        return ((MarketFloorEntity) this.d).getData();
    }

    public List<MarketFloorEntity.MarketSkuItem> Q() {
        return ((MarketFloorEntity) this.d).getSkuList();
    }

    public void R(MallFloorMarket mallFloorMarket, com.jingdong.app.mall.home.r.e.f fVar) {
        long longValue = (fVar.g0().longValue() * 1000) - (SystemClock.elapsedRealtime() - com.jingdong.app.mall.home.floor.common.i.s.f9357c);
        long longValue2 = fVar.g0().longValue();
        if (longValue <= 0) {
            mallFloorMarket.setTimeEnd();
            return;
        }
        try {
            com.jingdong.app.mall.home.x.b f2 = com.jingdong.app.mall.home.x.g.b().f(longValue2, longValue);
            if (this.f10784h == null) {
                this.f10784h = new a(this, mallFloorMarket);
            }
            if (f2 != null) {
                f2.a(this.f10784h);
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
        if (((MarketFloorEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
