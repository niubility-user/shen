package com.jingdong.app.mall.home.floor.view.b.g;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.ctrl.l;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class e extends com.jingdong.app.mall.home.floor.view.linefloor.base.a {
    public e(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(cVar, fVar, aVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void I(int i2, int i3) {
        super.I(i2, i3);
        if (i2 == 0) {
            l g2 = l.g();
            com.jingdong.app.mall.home.r.e.h hVar = this.f9854h;
            if (hVar != null) {
                g2.j(hVar.getJsonInt("aInterval", 10));
            }
        }
    }

    public String N() {
        return X() ? this.f9856j.G() : R();
    }

    public String O() {
        return this.f9856j.s();
    }

    public String P() {
        return this.f9856j.C();
    }

    public String Q() {
        return this.f9856j.O();
    }

    public String R() {
        return this.f9856j.u();
    }

    public String S() {
        return this.f9856j.v();
    }

    public String T() {
        return this.f9856j.f0();
    }

    public String U() {
        return this.f9856j.d0();
    }

    public boolean V() {
        return this.f9856j.c() != 0;
    }

    public boolean W() {
        return TextUtils.equals(this.f9855i.a, "09007");
    }

    public boolean X() {
        return this.f9856j.t() != 0;
    }

    public void Y(Context context, int i2) {
        JumpEntity jump;
        String str;
        if (this.f9856j == null || com.jingdong.app.mall.home.floor.common.i.l.k() || (jump = this.f9856j.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jump.srvJson);
        if (W() && i2 == 0) {
            str = "-100";
        } else {
            str = i2 + "";
        }
        c2.a("skuposition", str);
        com.jingdong.app.mall.home.floor.common.i.l.onClickJsonEvent(context, jump, this.f9856j.U(i2 == 0 ? 0 : i2 - 1), jump.getSrv(), c2.toString(), i2);
    }

    public String getFloorId() {
        return TextUtils.equals(this.f9855i.a, "09007") ? "09007" : "06069";
    }
}
