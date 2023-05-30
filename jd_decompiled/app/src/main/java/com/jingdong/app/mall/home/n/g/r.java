package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class r extends com.jingdong.app.mall.home.n.g.u.d {
    public r(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.b(-16777216);
        caDividerDecoration.d(true);
        caDividerDecoration.j(1);
        caDividerDecoration.i(20);
        caDividerDecoration.a(20);
        this.t = caDividerDecoration;
        com.jingdong.app.mall.home.n.b[] mSubTypeEnum = this.s;
        Intrinsics.checkExpressionValueIsNotNull(mSubTypeEnum, "mSubTypeEnum");
        if (mSubTypeEnum.length == 0) {
            return;
        }
        com.jingdong.app.mall.home.n.b bVar = this.s[0];
        for (int i2 = 0; i2 < 16; i2++) {
            this.u.add(bVar.getTypeModel(null, this, i2));
        }
        this.u.add(com.jingdong.app.mall.home.n.b.S_JUMP_MORE.getTypeModel(null, this, 16));
    }
}
