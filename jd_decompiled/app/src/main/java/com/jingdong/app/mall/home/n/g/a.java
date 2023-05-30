package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class a extends com.jingdong.app.mall.home.n.g.u.d {
    private int A;
    private boolean z;

    public a(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return true;
    }

    public final boolean Q() {
        return this.z;
    }

    public final void R(boolean z) {
        this.z = z;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        return com.jingdong.app.mall.home.floor.common.d.d(this.A);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.d, com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        int size = this.u.size();
        return 4 <= size && 5 >= size;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        O("Category_Main_Cobulid_Icon_Expo", cVar, "\u5171\u5efa");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        this.z = Intrinsics.areEqual("1", getJsonString("showSubTitle", "0"));
        Intrinsics.checkExpressionValueIsNotNull(getJsonString("floorBgImg"), "getJsonString(\"floorBgImg\")");
        E(com.jingdong.app.mall.home.floor.common.d.d(68), 0);
        N(false, 0, 0, 4, 5);
        this.A = this.z ? 208 : 180;
    }
}
