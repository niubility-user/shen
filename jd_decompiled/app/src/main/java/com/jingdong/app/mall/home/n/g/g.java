package com.jingdong.app.mall.home.n.g;

import android.text.SpannableString;
import android.text.TextUtils;
import androidx.core.internal.view.SupportMenu;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class g extends com.jingdong.app.mall.home.n.g.u.a {
    private static final int[] F = {-67337, -67337};
    private static final int[] G = {SupportMenu.CATEGORY_MASK};
    private static final int[] H = {(int) 4294585369L};
    @Nullable
    private JumpEntity A;
    @Nullable
    private String B;
    @Nullable
    private String C;
    private int D;
    private boolean E;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10351l;

    /* renamed from: m  reason: collision with root package name */
    private SpannableString f10352m;

    /* renamed from: n  reason: collision with root package name */
    private SpannableString f10353n;
    @Nullable
    private SpannableString o;
    private final List<com.jingdong.app.mall.home.category.floor.feedssub.b.a> p;
    @Nullable
    private String q;
    @Nullable
    private String r;
    @Nullable
    private String s;
    @Nullable
    private String t;
    @NotNull
    private int[] u;
    @NotNull
    private int[] v;
    @Nullable
    private String w;
    @Nullable
    private String x;
    @Nullable
    private String y;
    @NotNull
    private int[] z;

    public g(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
        this.p = new ArrayList();
        this.u = F;
        this.v = G;
        this.z = H;
    }

    private final List<String> G(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private final void U() {
        JDJSONObject jsonObject = getJsonObject("rmd");
        if (jsonObject != null) {
            String optString = jsonObject.optString("text");
            this.w = optString;
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            String optString2 = jsonObject.optString(DYConstants.DY_BG_COLOR);
            String optString3 = jsonObject.optString(DYConstants.DY_TEXT_COLOR);
            int[] iArr = F;
            int[] n2 = com.jingdong.app.mall.home.floor.common.i.m.n(optString2, iArr, true);
            Intrinsics.checkExpressionValueIsNotNull(n2, "MallFloorCommonUtil.getG\u2026ng(bgColor, sDefBg, true)");
            this.u = n2;
            if (n2.length == 0) {
                this.u = iArr;
            }
            int[] iArr2 = this.u;
            if (iArr2.length < 2) {
                this.u = new int[]{iArr2[0], iArr2[0]};
            }
            int[] n3 = com.jingdong.app.mall.home.floor.common.i.m.n(optString3, new int[]{SupportMenu.CATEGORY_MASK}, true);
            Intrinsics.checkExpressionValueIsNotNull(n3, "MallFloorCommonUtil.getG\u2026ArrayOf(Color.RED), true)");
            this.v = n3;
        }
    }

    private final void V(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String skuLabel = com.jingdong.app.mall.home.r.e.b.getJsonString(getJsonObject("businessLabel"), "listResCode", "");
        Intrinsics.checkExpressionValueIsNotNull(skuLabel, "skuLabel");
        this.f10352m = com.jingdong.app.mall.home.category.floor.feedssub.a.f(G(skuLabel), str, true);
    }

    private final void W() {
        int size;
        JDJSONArray jsonArr = getJsonArr("promotionLabel");
        if (jsonArr == null || (size = jsonArr.size()) <= 0) {
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            com.jingdong.app.mall.home.category.floor.feedssub.b.a aVar = new com.jingdong.app.mall.home.category.floor.feedssub.b.a(jsonArr.getJSONObject(i2));
            if (aVar.c()) {
                this.p.add(aVar);
            }
        }
    }

    private final void X(String str, String str2) {
        String str3;
        if (com.jingdong.app.mall.home.n.h.c.d(str)) {
            return;
        }
        if (Intrinsics.areEqual("secKillPrice", str2)) {
            str3 = "tab_050";
        } else {
            str3 = Intrinsics.areEqual("flashBuyPrice", str2) ? "tab_063" : "";
        }
        this.p.add(new com.jingdong.app.mall.home.category.floor.feedssub.b.a(str3));
        this.f10353n = com.jingdong.app.mall.home.category.floor.feedssub.a.g(G(""), str, false, true, 0.71f);
    }

    private final void Y(JDJSONObject jDJSONObject, String str) {
        if ((!Intrinsics.areEqual(CartConstant.KEY_PLUSPRICE, str)) == true) {
            return;
        }
        String jsonString = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "price", "");
        if (com.jingdong.app.mall.home.n.h.c.d(jsonString)) {
            return;
        }
        this.o = com.jingdong.app.mall.home.category.floor.feedssub.a.g(G("tab_040"), jsonString, false, true, 1.0f);
    }

    @Nullable
    public final String D() {
        return this.f10351l;
    }

    @Nullable
    public final String E() {
        return this.s;
    }

    @NotNull
    public final CharSequence F() {
        CharSequence a = com.jingdong.app.mall.home.n.h.c.a(this.f10353n);
        Intrinsics.checkExpressionValueIsNotNull(a, "CaCommonUtil.getPriceWithDef(mJDPriceSpan)");
        return a;
    }

    @Nullable
    public final SpannableString H() {
        return this.o;
    }

    @Nullable
    public final String I() {
        return this.x;
    }

    @NotNull
    public final int[] J() {
        return this.z;
    }

    @Nullable
    public final String K() {
        return this.y;
    }

    @NotNull
    public final int[] L() {
        return this.u;
    }

    @Nullable
    public final String M() {
        return this.w;
    }

    @NotNull
    public final int[] N() {
        return this.v;
    }

    @Nullable
    public final String O() {
        return this.q;
    }

    @Nullable
    public final String P() {
        return this.r;
    }

    @Nullable
    public final String Q() {
        return this.t;
    }

    @Nullable
    public final CharSequence R() {
        return this.f10352m;
    }

    public final int S() {
        return this.D;
    }

    @NotNull
    public final List<com.jingdong.app.mall.home.category.floor.feedssub.b.a> T() {
        return this.p;
    }

    public final void Z(boolean z) {
        if (this.D != 1) {
            return;
        }
        if (z && this.E) {
            return;
        }
        String str = z ? this.C : this.B;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            com.jingdong.app.mall.home.o.a.f.v0(str, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z) {
            this.E = true;
        }
    }

    @Nullable
    public final JumpEntity getJump() {
        return this.A;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.a, com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        this.r = getJsonString("skuId");
        this.f10351l = getJsonString("imageUrl", "https://feeds");
        String jsonString = getJsonString("reviews");
        this.q = jsonString;
        this.q = Intrinsics.areEqual("\u6682\u65e0\u8bc4\u4ef7", jsonString) ? "" : this.q;
        this.t = getJsonString("skuName");
        this.s = getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.x = getJsonString("rankBgImg");
        this.y = getJsonString("rankIconImg");
        int[] n2 = com.jingdong.app.mall.home.floor.common.i.m.n(getJsonString("rankColor"), H, true);
        Intrinsics.checkExpressionValueIsNotNull(n2, "MallFloorCommonUtil.getG\u2026kColor\"), sDefRank, true)");
        this.z = n2;
        this.A = (JumpEntity) getObject("jump", JumpEntity.class);
        this.B = getJsonString("clickUrl");
        this.C = getJsonString("exposalUrl");
        this.D = getJsonInt("source", 0);
        JDJSONObject jsonObject = getJsonObject("subPrice");
        String subType = com.jingdong.app.mall.home.r.e.b.getJsonString(jsonObject, "type", "");
        if (!TextUtils.isEmpty(this.s)) {
            Intrinsics.checkExpressionValueIsNotNull(subType, "subType");
            Y(jsonObject, subType);
        }
        V(this.t);
        String str = this.s;
        Intrinsics.checkExpressionValueIsNotNull(subType, "subType");
        X(str, subType);
        W();
        U();
    }
}
