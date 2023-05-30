package com.jingdong.app.mall.home.deploy.view.layout.seckill2x2;

import android.text.TextUtils;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckill2x2Model extends SeckillModel<DSeckill2x2> {
    private String R = "";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel, com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        f fVar = this.f8920m;
        if (fVar != null) {
            this.R = fVar.getJsonString("markedImg");
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel
    protected boolean R0() {
        return !V0() && i1() && !TextUtils.isEmpty(this.K) && d.f("HOME_CORE_DSeckill2x2_Lottie", 1);
    }

    public String f1() {
        return this.K;
    }

    public int g1() {
        return this.L;
    }

    public String h1() {
        return this.R;
    }

    public boolean i1() {
        return this.J;
    }

    public boolean j1() {
        return this.C.size() > 3 && this.P;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        this.f8880j.R(m(R2.anim.popdown_anim_feedback, R2.anim.popup_center_enter) << 1, m(109, 102) << 1);
    }
}
