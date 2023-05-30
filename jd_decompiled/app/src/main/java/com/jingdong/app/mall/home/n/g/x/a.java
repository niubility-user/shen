package com.jingdong.app.mall.home.n.g.x;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.unionpay.tsmservice.mi.data.Constant;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class a extends com.jingdong.app.mall.home.n.g.u.f {
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public String f10399k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public String f10400l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public String f10401m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public String f10402n;
    @NotNull
    public int[] o;
    @NotNull
    public int[] p;

    public a(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public int b() {
        com.jingdong.app.mall.home.n.b mSubType = this.a;
        Intrinsics.checkExpressionValueIsNotNull(mSubType, "mSubType");
        return mSubType.getFloorWidth();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public int getFloorHeight() {
        com.jingdong.app.mall.home.n.g.u.c parentModel = g();
        Intrinsics.checkExpressionValueIsNotNull(parentModel, "parentModel");
        return parentModel.getFloorHeight();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_Cobulid_Icon");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.f10366e = getJsonString("saleNumText");
        String jsonString = getJsonString("subTitle");
        Intrinsics.checkExpressionValueIsNotNull(jsonString, "getJsonString(\"subTitle\")");
        this.f10399k = jsonString;
        String jsonString2 = getJsonString("title");
        Intrinsics.checkExpressionValueIsNotNull(jsonString2, "getJsonString(\"title\")");
        this.f10400l = jsonString2;
        String jsonString3 = getJsonString("subTitle");
        Intrinsics.checkExpressionValueIsNotNull(jsonString3, "getJsonString(\"subTitle\")");
        this.f10399k = jsonString3;
        String jsonString4 = getJsonString("benefitGif");
        Intrinsics.checkExpressionValueIsNotNull(jsonString4, "getJsonString(\"benefitGif\")");
        this.f10402n = jsonString4;
        String jsonString5 = getJsonString("img");
        Intrinsics.checkExpressionValueIsNotNull(jsonString5, "getJsonString(\"img\")");
        this.f10401m = jsonString5;
        int[] o = com.jingdong.app.mall.home.floor.common.i.m.o(getJsonString(Constant.KEY_TITLE_COLOR), (int) 4279900698L);
        Intrinsics.checkExpressionValueIsNotNull(o, "MallFloorCommonUtil.getG\u2026or\"), 0xff1a1a1a.toInt())");
        this.o = o;
        int[] o2 = com.jingdong.app.mall.home.floor.common.i.m.o(getJsonString("subTitleColor"), (int) 4286611584L);
        Intrinsics.checkExpressionValueIsNotNull(o2, "MallFloorCommonUtil.getG\u2026or\"), 0xff808080.toInt())");
        this.p = o2;
        String str = this.f10399k;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubTitle");
        }
        if (TextUtils.isEmpty(str) && (g() instanceof com.jingdong.app.mall.home.n.g.a)) {
            com.jingdong.app.mall.home.n.g.u.c g2 = g();
            if (g2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jingdong.app.mall.home.category.model.CaBannerIconModel");
            }
            ((com.jingdong.app.mall.home.n.g.a) g2).R(false);
        }
    }

    @NotNull
    public final String u() {
        String str = this.f10401m;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImg");
        }
        return str;
    }

    @NotNull
    public final String v() {
        String str = this.f10402n;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRightIcon");
        }
        return str;
    }

    @NotNull
    public final String w() {
        String str = this.f10399k;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubTitle");
        }
        return str;
    }

    @NotNull
    public final int[] x() {
        int[] iArr = this.p;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubTitleColor");
        }
        return iArr;
    }

    @NotNull
    public final String y() {
        String str = this.f10400l;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTitle");
        }
        return str;
    }

    @NotNull
    public final int[] z() {
        int[] iArr = this.o;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTitleColor");
        }
        return iArr;
    }
}
