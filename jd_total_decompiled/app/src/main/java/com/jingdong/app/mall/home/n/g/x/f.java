package com.jingdong.app.mall.home.n.g.x;

import android.text.SpannableString;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class f extends com.jingdong.app.mall.home.n.g.u.e {
    private static final NumberFormat q;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private String f10414k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10415l;

    /* renamed from: m  reason: collision with root package name */
    private float f10416m;

    /* renamed from: n  reason: collision with root package name */
    private SpannableString f10417n;
    private SpannableString o;
    private final AtomicBoolean p;

    static {
        NumberFormat sFormat = NumberFormat.getInstance();
        q = sFormat;
        Intrinsics.checkExpressionValueIsNotNull(sFormat, "sFormat");
        sFormat.setMaximumFractionDigits(0);
        Intrinsics.checkExpressionValueIsNotNull(sFormat, "sFormat");
        sFormat.setRoundingMode(RoundingMode.DOWN);
    }

    public f(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.p = new AtomicBoolean(false);
    }

    private final void A(String str) {
        if (com.jingdong.app.mall.home.n.h.c.d(str)) {
            return;
        }
        this.o = com.jingdong.app.mall.home.category.floor.feedssub.a.d(str, OrderISVUtil.MONEY_DECIMAL, 0.71f);
    }

    private final void z(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f10417n = com.jingdong.app.mall.home.category.floor.feedssub.a.d(str, OrderISVUtil.MONEY_DECIMAL, 1.0f);
    }

    public final boolean B() {
        return this.p.getAndSet(false);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_Seckill_Product");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        float coerceAtLeast;
        float coerceAtMost;
        this.f10414k = getJsonString("promotionTag");
        float f2 = 100;
        float f3 = com.jingdong.app.mall.home.n.h.c.f(q, getJsonString("saleRatio"), f2);
        this.f10416m = f3;
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(0.0f, f3);
        this.f10416m = coerceAtLeast;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(f2, coerceAtLeast);
        this.f10416m = coerceAtMost;
        this.f10415l = "\u5df2\u552e" + ((int) this.f10416m) + "%";
        getJsonString("");
        this.p.set(this.f10368g < 4);
        String sprice = getJsonString("sprice");
        Intrinsics.checkExpressionValueIsNotNull(sprice, "sprice");
        A(sprice);
        if (TextUtils.isEmpty(sprice)) {
            return;
        }
        String jsonString = getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        Intrinsics.checkExpressionValueIsNotNull(jsonString, "getJsonString(\"jdPrice\")");
        z(jsonString);
    }

    @Nullable
    public final CharSequence u() {
        return this.f10417n;
    }

    public final float v() {
        return this.f10416m;
    }

    @Nullable
    public final String w() {
        return this.f10415l;
    }

    @NotNull
    public final CharSequence x() {
        CharSequence a = com.jingdong.app.mall.home.n.h.c.a(this.o);
        Intrinsics.checkExpressionValueIsNotNull(a, "CaCommonUtil.getPriceWithDef(mSubPriceSpan)");
        return a;
    }

    @Nullable
    public final String y() {
        return this.f10414k;
    }
}
