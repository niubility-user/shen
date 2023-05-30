package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class i extends com.jingdong.app.mall.home.n.g.u.d {
    public i(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.d, com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return this.u.size() > 4;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.s("Category_Main_Seckill_Product_Expo", "\u79d2\u6740");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        E(com.jingdong.app.mall.home.floor.common.d.d(90), com.jingdong.app.mall.home.floor.common.d.d(26));
        M(true, new int[0]);
    }
}
