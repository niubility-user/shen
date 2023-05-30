package com.jingdong.app.mall.home.n.g.x;

import com.jd.framework.json.JDJSONObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class b extends com.jingdong.app.mall.home.n.g.u.f {
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private String f10403k;

    public b(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Brand_Brand");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        com.jingdong.app.mall.home.n.g.u.c g2 = g();
        if (g2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(g2, "parentModel ?: return");
            this.f10403k = g2.getJsonString("showIconName");
        }
    }

    @Nullable
    public final String u() {
        return this.f10403k;
    }
}
