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
public final class b extends com.jingdong.app.mall.home.n.g.u.d {
    @NotNull
    private static com.jingdong.app.mall.home.floor.common.f B;
    private boolean z;
    public static final a C = new a(null);
    @JvmField
    @NotNull
    public static com.jingdong.app.mall.home.n.g.w.d A = new com.jingdong.app.mall.home.n.g.w.d();

    /* loaded from: classes4.dex */
    public static final class a {
        private a() {
        }

        @NotNull
        public final com.jingdong.app.mall.home.floor.common.f a() {
            return b.B;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
        B = fVar;
        fVar.K(new Rect(5, 0, 5, 23));
        A.a = new com.jingdong.app.mall.home.floor.common.f(102, 66);
        A.a.F(new Rect(0, 0, 0, 0));
        A.b = new com.jingdong.app.mall.home.floor.common.f(160, 44);
        A.b.F(new Rect(0, 66, 0, 0));
        com.jingdong.app.mall.home.n.g.w.d dVar = A;
        dVar.d = 24;
        dVar.f10389c = -7566196;
    }

    public b(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        if (this.w == 2 && this.z) {
            return super.getFloorHeight();
        }
        int i2 = 0;
        com.jingdong.app.mall.home.n.g.u.e firstItem = this.u.get(0);
        Intrinsics.checkExpressionValueIsNotNull(firstItem, "firstItem");
        int floorHeight = (firstItem.getFloorHeight() * this.w) + B.s() + B.p();
        CaDividerDecoration mDividerDecoration = this.t;
        if (mDividerDecoration != null) {
            Intrinsics.checkExpressionValueIsNotNull(mDividerDecoration, "mDividerDecoration");
            i2 = mDividerDecoration.f() * (this.w - 1);
        }
        return floorHeight + i2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        O("Category_Brand_Brand_Expo", cVar, "\u54c1\u724c");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        this.z = Intrinsics.areEqual("1", getJsonString("showIconName"));
        E(com.jingdong.app.mall.home.floor.common.d.d(90), com.jingdong.app.mall.home.floor.common.d.d(26));
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.h(this.z ? 13 : 20);
        caDividerDecoration.j(10);
        this.t = caDividerDecoration;
        B.K(new Rect(5, 0, 5, this.z ? 23 : 21));
        K(3);
    }
}
