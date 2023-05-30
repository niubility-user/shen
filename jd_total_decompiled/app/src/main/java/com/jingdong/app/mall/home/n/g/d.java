package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class d extends com.jingdong.app.mall.home.n.g.u.d {
    public d(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.d, com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return this.u.size() > 1;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.s("Category_Main_Coupon_Expo", "\u4f18\u60e0\u5238");
        cVar.x("Category_Main_CouponGWC_Expo", "\u4f18\u60e0\u5238\u66dd\u5149");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        v(true);
        M(false, new int[0]);
    }
}
