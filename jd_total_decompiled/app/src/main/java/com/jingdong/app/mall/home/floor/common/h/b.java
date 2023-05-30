package com.jingdong.app.mall.home.floor.common.h;

import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class b {
    public int a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f9287c;

    public com.jingdong.app.mall.home.r.c.b a(com.jingdong.app.mall.home.r.c.b bVar) {
        if (bVar == null) {
            return null;
        }
        bVar.a("cardlength", String.valueOf(this.a));
        bVar.a("cardwidth", String.valueOf(this.b));
        bVar.a("floHeight", String.valueOf(this.f9287c));
        f.r0(this, "width : " + this.b + " height : " + this.a + " top : " + this.f9287c);
        return bVar;
    }

    public String b(String str) {
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(str);
        a(c2);
        return c2.toString();
    }

    public void c(JumpEntity jumpEntity) {
        if (jumpEntity == null) {
            return;
        }
        jumpEntity.srvJson = b(jumpEntity.srvJson);
    }

    public void d(int i2) {
        this.a = DPIUtil.px2dip(i2);
    }

    public void e(int i2) {
        this.f9287c = DPIUtil.px2dip(i2);
    }

    public void f(int i2) {
        this.b = DPIUtil.px2dip(i2);
    }
}
