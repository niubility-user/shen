package com.jingdong.app.mall.home.floor.view.widget.bubblebanner936;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.f.a.h;
import com.jingdong.common.entity.JumpEntity;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes4.dex */
public class a {
    private h a;
    private f b;

    /* renamed from: c  reason: collision with root package name */
    private int f10213c;

    public a(h hVar, int i2) {
        this.a = hVar;
        this.f10213c = i2;
        this.b = hVar.U(i2);
    }

    public String a() {
        return this.b.getJsonString(DYConstants.DY_BG_COLOR);
    }

    public String b() {
        return this.b.d();
    }

    public String c() {
        return this.b.getJsonString(Constant.KEY_TITLE_COLOR);
    }

    public String d() {
        return this.b.getJsonString("title");
    }

    public String e() {
        return this.b.u();
    }

    public String f() {
        return this.b.getJsonString("subTitleBgColor");
    }

    public String g() {
        return this.b.f0();
    }

    public String h() {
        return this.b.d0();
    }

    public boolean i() {
        return this.b.t() == 0;
    }

    public void j(Context context) {
        JumpEntity jump = this.b.getJump();
        if (jump == null) {
            return;
        }
        l.onClickJsonEvent(context, jump, "", jump.getSrv(), this.a.W(this.f10213c).toString());
    }

    public boolean k() {
        return this.a.b0();
    }
}
