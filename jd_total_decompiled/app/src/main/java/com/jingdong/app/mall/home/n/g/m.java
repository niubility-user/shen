package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class m extends com.jingdong.app.mall.home.n.g.u.d {
    private final com.jingdong.app.mall.home.n.g.w.b z;

    public m(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr, @NotNull com.jingdong.app.mall.home.n.g.w.b bVar) {
        super(jDJSONObject, aVar, bVarArr);
        this.f10356n &= bVar.d;
        this.z = bVar;
        this.f10364i += bVar.d();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    @NotNull
    public String d() {
        String a = this.z.a();
        Intrinsics.checkExpressionValueIsNotNull(a, "mLineMoreInfo.eventClickId");
        return a;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        O(this.z.b(), cVar, "\u4e00\u884c\u591a\u4e2a");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        v(true);
        K(1);
        E(com.jingdong.app.mall.home.floor.common.d.d(80), com.jingdong.app.mall.home.n.a.C_DIVIDER.getFloorHeight() - this.z.d());
    }
}
