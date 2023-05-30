package com.jingdong.app.mall.home.n.g;

import android.graphics.Rect;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class l extends com.jingdong.app.mall.home.n.g.u.d {
    @NotNull
    private static com.jingdong.app.mall.home.floor.common.f E;
    @Nullable
    private String A;
    @Nullable
    private String B;
    @Nullable
    private String C;
    @Nullable
    private String z;
    public static final a F = new a(null);
    @JvmField
    @NotNull
    public static com.jingdong.app.mall.home.n.g.w.d D = new com.jingdong.app.mall.home.n.g.w.d();

    /* loaded from: classes4.dex */
    public static final class a {
        private a() {
        }

        @NotNull
        public final com.jingdong.app.mall.home.floor.common.f a() {
            return l.E;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
        E = fVar;
        fVar.K(new Rect(16, 0, 16, 12));
        D.a = new com.jingdong.app.mall.home.floor.common.f(120, 120);
        D.a.F(new Rect(0, 0, 0, 0));
        D.b = new com.jingdong.app.mall.home.floor.common.f(125, 48);
        D.b.F(new Rect(0, 120, 0, 0));
        com.jingdong.app.mall.home.n.g.w.d dVar = D;
        dVar.d = 24;
        dVar.f10389c = -14277082;
    }

    public l(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Nullable
    public final String R() {
        return this.A;
    }

    @Nullable
    public final String S() {
        return this.B;
    }

    @Nullable
    public final String T() {
        return this.z;
    }

    @Nullable
    public final String U() {
        return this.C;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        int f2;
        if (this.w == 2) {
            return super.getFloorHeight();
        }
        int floorHeight = (com.jingdong.app.mall.home.n.b.S_ICON.getFloorHeight() * this.w) + E.s() + E.p();
        CaDividerDecoration mDividerDecoration = this.t;
        if (mDividerDecoration == null) {
            f2 = 0;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(mDividerDecoration, "mDividerDecoration");
            f2 = mDividerDecoration.f() * (this.w - 1);
        }
        return floorHeight + f2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        O("Category_Main_Subcategory_Expo", cVar, "\u7c7b\u76ee");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        this.z = getJsonString("filteredCateIds");
        this.A = getJsonString("catePoolId");
        this.B = getJsonString("cid");
        this.C = getJsonString("pcid");
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.h(8);
        this.t = caDividerDecoration;
        E(com.jingdong.app.mall.home.floor.common.d.d(90), com.jingdong.app.mall.home.floor.common.d.d(26));
        K(4);
    }
}
