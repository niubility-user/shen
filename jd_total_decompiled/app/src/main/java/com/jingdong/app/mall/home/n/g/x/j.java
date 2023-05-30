package com.jingdong.app.mall.home.n.g.x;

import com.jd.framework.json.JDJSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class j extends com.jingdong.app.mall.home.n.g.u.f {
    public j(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_Subcategory_More_Detail");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.d = getJsonString("iconImg");
    }
}
