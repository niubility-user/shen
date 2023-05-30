package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class k extends com.jingdong.app.mall.home.n.g.u.d {
    private int A;
    private boolean z;

    public k(@Nullable JDJSONObject jDJSONObject, @NotNull com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
        if (aVar == com.jingdong.app.mall.home.n.a.C_HOT_SALE) {
            boolean areEqual = Intrinsics.areEqual("1", getJsonString("showPriceCurve"));
            this.z = areEqual;
            this.A = areEqual ? R2.attr.behavior_peekHeight : R2.attr.arrowHeadLength;
            return;
        }
        this.A = R2.attr.backgroundSplit;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    public final boolean Q() {
        return this.z;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        int i2 = this.A;
        return i2 > 0 ? com.jingdong.app.mall.home.floor.common.d.d(i2) : super.getFloorHeight();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.d, com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return H() > 2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.s("Category_Main_Hot_Product_Expo", "\u7206\u54c1\u4e13\u533a");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        v(true);
        E(com.jingdong.app.mall.home.floor.common.d.d(90), com.jingdong.app.mall.home.floor.common.d.d(26));
        N(true, 112, this.A - 25, new int[0]);
    }
}
