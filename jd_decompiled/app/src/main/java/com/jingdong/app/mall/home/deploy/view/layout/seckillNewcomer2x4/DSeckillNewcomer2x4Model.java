package com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4;

import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckillNewcomer2x4Model extends SeckillModel<DSeckillNewcomer2x4> {
    private static final int[] U = {-381927, -381927};
    private String R;
    private int S;
    private int[] T;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel, com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        if (this.f8920m == null) {
            return;
        }
        IconLabel.Info a = IconLabel.Info.a();
        a.b(S(), "");
        a.g(CoreModel.o, 22);
        a.f(this.x);
        a.c(false);
        this.O = a;
        this.R = this.f8920m.getJsonString("giftBgImg");
        this.S = this.f8920m.getJsonInt("giftPosition", 62);
        this.T = m.n(this.f8920m.getJsonString("giftColor"), U, true);
    }

    public String f1() {
        return this.R;
    }

    public int[] g1() {
        return this.T;
    }

    public int h1() {
        return this.S;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        this.f8880j.R(R2.attr.actionTextColorAlpha, R2.attr.behavior_peekHeight);
    }
}
