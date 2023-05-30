package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.LineFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LineFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;
import java.util.List;

/* loaded from: classes4.dex */
public class p extends b<LineFloorEntity, LineFloorEngine, MallFloorLineMore> {
    private void P(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        if (((LineFloorEntity) this.d).hasAsync()) {
            if ((com.jingdong.app.mall.home.floor.view.b.g.f.b0(((LineFloorEntity) this.d).getElements()) || ((LineFloorEntity) this.d).isAsyncCoreFloor()) && iMallFloorUI.isCurrentData() && !mallFloorEvent.d()) {
                boolean z = false;
                if (!com.jingdong.app.mall.home.floor.common.i.k.k(LineFloorEngine.l())) {
                    if ("1".equals(((LineFloorEntity) this.d).getSiteType())) {
                        return;
                    }
                    z = true;
                }
                if (((LineFloorEntity) this.d).isAsyncCoreFloor()) {
                    com.jingdong.app.mall.home.floor.common.i.k.m(com.jingdong.app.mall.home.o.a.f.C(), (LineFloorEntity) this.d, z);
                } else {
                    ((LineFloorEngine) this.f10738e).q(com.jingdong.app.mall.home.o.a.f.C(), (LineFloorEntity) this.d, z);
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void K(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        super.K(iMallFloorUI, mallFloorEvent);
        P(iMallFloorUI, mallFloorEvent);
    }

    public String Q() {
        return ((LineFloorEntity) this.d).getClkLog();
    }

    public String R() {
        return ((LineFloorEntity) this.d).getExpoLog();
    }

    public com.jingdong.app.mall.home.floor.view.b.g.a S() {
        return ((LineFloorEntity) this.d).getTitleInfo();
    }

    public List<com.jingdong.app.mall.home.floor.view.linefloor.base.a> T() {
        return ((LineFloorEntity) this.d).getLineList();
    }

    public int U() {
        return ((LineFloorEntity) this.d).getTopOverlay();
    }

    public String V() {
        return ((LineFloorEntity) this.d).getTypeTag();
    }

    public boolean W() {
        return ((LineFloorEntity) this.d).isFirstLineFloor();
    }

    public boolean X() {
        return ((LineFloorEntity) this.d).isLastLineFloor();
    }

    public void Y() {
        MallFloorLineMore mallFloorLineMore = (MallFloorLineMore) c();
        if (mallFloorLineMore == null) {
            return;
        }
        if (((LineFloorEntity) this.d).isValid()) {
            mallFloorLineMore.onSetVisible(true);
            mallFloorLineMore.onRefreshView();
            return;
        }
        mallFloorLineMore.cleanUI();
        mallFloorLineMore.onSetVisible(false);
    }

    public boolean Z() {
        return ((LineFloorEntity) this.d).isValid() && ((LineFloorEntity) this.d).getSelfIndex() != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        ((LineFloorEngine) this.f10738e).j(this);
        Y();
    }
}
