package com.jingdong.app.mall.home.n.g;

import androidx.core.internal.view.SupportMenu;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class q extends com.jingdong.app.mall.home.n.g.u.d {
    public q(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.b(SupportMenu.CATEGORY_MASK);
        caDividerDecoration.d(true);
        caDividerDecoration.j(8);
        this.t = caDividerDecoration;
        int length = this.s.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.u.add(this.s[i2].getTypeModel(null, this, i2));
        }
    }
}
