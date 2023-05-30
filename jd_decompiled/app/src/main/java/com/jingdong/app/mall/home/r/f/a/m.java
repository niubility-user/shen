package com.jingdong.app.mall.home.r.f.a;

import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.model.entity.Banner09018Entity;
import com.jingdong.app.mall.home.floor.presenter.engine.Banner09018Engine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class m extends b<Banner09018Entity, Banner09018Engine, IMallFloorUI> {
    private MallFloorBanner09018.SkuInfo Q(String str, String str2, String str3) {
        SkuLabel.Info info = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        MallFloorBanner09018.SkuInfo skuInfo = new MallFloorBanner09018.SkuInfo();
        skuInfo.skuImgUrl = str;
        if (!TextUtils.isEmpty(str2)) {
            str3 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            String jsonString = fVar.getJsonString("finalPriceImg");
            SkuLabel.Info a = SkuLabel.Info.a();
            a.d(jsonString);
            a.u(!TextUtils.isEmpty(jsonString));
            a.l(null, 8);
            a.q(com.jingdong.app.mall.home.floor.common.i.m.o(fVar.getJsonString("finalPriceColor"), -1), 24);
            a.h(17);
            a.f(true);
            a.m(str3);
            info = a;
        }
        skuInfo.labelInfo = info;
        return skuInfo;
    }

    public boolean P() {
        if (j()) {
            return false;
        }
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        return (TextUtils.isEmpty(fVar.u()) || TextUtils.isEmpty(fVar.v()) || TextUtils.isEmpty(fVar.w()) || TextUtils.isEmpty(fVar.x())) ? false : true;
    }

    public long R() {
        return Math.max(((Banner09018Entity) this.d).midElement.getJsonInt("skuAnimationTime"), 3) * 1000;
    }

    public String S(boolean z) {
        return (z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement).G();
    }

    public String T(boolean z) {
        return (z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement).O();
    }

    public String U(boolean z) {
        return (z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement).C();
    }

    public String V(boolean z) {
        return (z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement).u();
    }

    public String W(boolean z) {
        return (z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement).f0();
    }

    public String X(boolean z) {
        com.jingdong.app.mall.home.r.e.f fVar = z ? ((Banner09018Entity) this.d).leftElement : ((Banner09018Entity) this.d).rightElement;
        String jsonString = fVar.getJsonString("price");
        if (!TextUtils.isEmpty(jsonString)) {
            return com.jingdong.app.mall.home.category.floor.feedssub.a.e(jsonString);
        }
        return fVar.d0();
    }

    public String Y() {
        return ((Banner09018Entity) this.d).midElement.G();
    }

    public MallFloorBanner09018.SkuInfo Z() {
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        return Q(fVar.u(), fVar.I(), fVar.d0());
    }

    public MallFloorBanner09018.SkuInfo a0() {
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        return Q(fVar.v(), fVar.J(), fVar.e0());
    }

    public MallFloorBanner09018.SkuInfo b0() {
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        return Q(fVar.w(), fVar.getJsonString("price3"), fVar.getJsonString("subtitle3"));
    }

    public MallFloorBanner09018.SkuInfo c0() {
        com.jingdong.app.mall.home.r.e.f fVar = ((Banner09018Entity) this.d).midElement;
        return Q(fVar.x(), fVar.getJsonString("price4"), fVar.getJsonString("subtitle4"));
    }

    public void d0(View view, int i2, int i3) {
        com.jingdong.app.mall.home.r.e.f fVar;
        JumpEntity jump;
        if (com.jingdong.app.mall.home.floor.common.i.l.k()) {
            return;
        }
        if (i2 == 0) {
            fVar = ((Banner09018Entity) this.d).leftElement;
        } else {
            fVar = i2 == 1 ? ((Banner09018Entity) this.d).midElement : ((Banner09018Entity) this.d).rightElement;
        }
        if (fVar == null || (jump = fVar.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jump.srvJson);
        c2.a("skuposition", "" + i3);
        com.jingdong.app.mall.home.floor.common.i.l.onClickJsonEvent(view.getContext(), jump, "", jump.getSrv(), c2.toString(), i3);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((Banner09018Entity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
