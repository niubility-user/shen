package com.jingdong.app.mall.home.n.g.x;

import com.jd.framework.json.JDJSONObject;
import com.unionpay.tsmservice.mi.data.Constant;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class i extends com.jingdong.app.mall.home.n.g.u.f {
    @NotNull
    private static final int[] s = {-1};
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private String f10422k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10423l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    private String f10424m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private String f10425n;
    @Nullable
    private String o;
    @NotNull
    private int[] p;
    @NotNull
    private int[] q;
    private int r;

    public i(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        int[] iArr = s;
        this.p = iArr;
        this.q = iArr;
        this.r = 1;
    }

    @Nullable
    public final String A() {
        return this.f10422k;
    }

    @NotNull
    public final int[] B() {
        return this.p;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        com.jingdong.app.mall.home.n.g.u.c mParentModel = this.f10369h;
        Intrinsics.checkExpressionValueIsNotNull(mParentModel, "mParentModel");
        cVar.p(mParentModel.d());
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.f10422k = getJsonString("title", "");
        this.f10423l = getJsonString("subTitle", "");
        this.f10424m = getJsonString("img", "");
        this.f10425n = getJsonString("img2", "");
        this.o = getJsonString("bgImg", "");
        this.r = getJsonInt("materialType", 1);
        String jsonString = getJsonString(Constant.KEY_TITLE_COLOR, "");
        String jsonString2 = getJsonString("subTitleColor", "");
        int[] o = com.jingdong.app.mall.home.floor.common.i.m.o(jsonString, -1);
        Intrinsics.checkExpressionValueIsNotNull(o, "MallFloorCommonUtil.getG\u2026ring(titleColorStr, -0x1)");
        this.p = o;
        int[] o2 = com.jingdong.app.mall.home.floor.common.i.m.o(jsonString2, -1);
        Intrinsics.checkExpressionValueIsNotNull(o2, "MallFloorCommonUtil.getG\u2026g(subTitleColorStr, -0x1)");
        this.q = o2;
    }

    @Nullable
    public final String u() {
        return this.o;
    }

    @Nullable
    public final String v() {
        return this.f10424m;
    }

    @Nullable
    public final String w() {
        return this.f10425n;
    }

    public final int x() {
        return this.r;
    }

    @Nullable
    public final String y() {
        return this.f10423l;
    }

    @NotNull
    public final int[] z() {
        return this.q;
    }
}
