package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class h extends com.jingdong.app.mall.home.n.g.u.d {
    public h(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
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
        cVar.s("Category_Flashbuy_Product_Expo", "\u95ea\u8d2d");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        v(true);
        E(com.jingdong.app.mall.home.floor.common.d.d(80), com.jingdong.app.mall.home.floor.common.d.d(14));
        M(false, new int[0]);
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.j(24);
        this.t = caDividerDecoration;
    }
}
