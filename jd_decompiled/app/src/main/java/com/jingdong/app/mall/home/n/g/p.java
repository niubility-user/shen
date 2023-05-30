package com.jingdong.app.mall.home.n.g;

import android.graphics.Rect;
import com.jd.framework.json.JDJSONObject;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class p extends com.jingdong.app.mall.home.n.g.u.d {
    @JvmField
    @NotNull
    public static com.jingdong.app.mall.home.n.g.w.b A;
    private boolean z;

    static {
        com.jingdong.app.mall.home.n.g.w.b bVar = new com.jingdong.app.mall.home.n.g.w.b();
        A = bVar;
        bVar.g(new Rect(18, 10, 18, 0));
    }

    public p(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        O("Category_Main_UnionRanking_List_Expo", cVar, "\u805a\u5408\u699c\u5355");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        int d;
        v(true);
        K(1);
        E(com.jingdong.app.mall.home.floor.common.d.d(80), com.jingdong.app.mall.home.floor.common.d.d(14));
        this.z = Intrinsics.areEqual("1", getJsonString("showRankType"));
        int d2 = A.d();
        if (this.z) {
            d = com.jingdong.app.mall.home.n.a.C_RANKING2.getFloorHeight();
        } else {
            d = com.jingdong.app.mall.home.floor.common.d.d(298);
        }
        this.f10364i = d2 + d;
    }
}
