package com.jingdong.app.mall.home.deploy.view.layout.seckill2x4;

import android.content.Context;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckill2x4Model extends SeckillModel<DSeckill2x4> {
    private String R;
    private b S;
    private JumpEntity T;
    private boolean U;

    private void h1() {
        JumpEntity jumpEntity = this.T;
        if (jumpEntity == null) {
            this.S = null;
            return;
        }
        b c2 = b.c(jumpEntity.srv);
        this.S = c2;
        c2.e(b.c(this.f8920m.l()));
    }

    private void i1(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("bannerInfo");
        if (optJSONObject == null) {
            return;
        }
        String optString = optJSONObject.optString("bannerImg");
        String optString2 = optJSONObject.optString("bannerJump");
        this.R = optString;
        this.T = (JumpEntity) JDJSON.parseObject(optString2, JumpEntity.class);
        h1();
    }

    private boolean j1(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("bottomImg");
        if (optJSONObject == null) {
            return false;
        }
        String optString = optJSONObject.optString("img");
        String optString2 = optJSONObject.optString("imgJump");
        this.R = optString;
        this.T = (JumpEntity) JDJSON.parseObject(optString2, JumpEntity.class);
        h1();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel
    public void S0(JDJSONObject jDJSONObject) {
        super.S0(jDJSONObject);
        f fVar = this.f8920m;
        if (fVar == null) {
            return;
        }
        boolean z = 1 == fVar.getJsonInt("showBanner");
        this.U = z;
        if (!z || j1(jDJSONObject)) {
            return;
        }
        i1(jDJSONObject);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel
    public void e1() {
        super.e1();
        b bVar = this.S;
        if (bVar != null) {
            a.y("Home_SeckillActivityExpo", "", bVar.toString());
        }
    }

    public String f1() {
        return this.R;
    }

    public void g1(Context context) {
        b bVar;
        if (this.T == null || (bVar = this.S) == null) {
            return;
        }
        a.s("Home_SeckillActivity", "", bVar.toString());
        l.e(context, this.T);
    }

    public boolean k1() {
        return this.U;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(com.jingdong.app.mall.home.p.b.e.b bVar) {
        this.f8880j.R(R2.attr.actionTextColorAlpha, m(226, 226) << 1);
    }
}
