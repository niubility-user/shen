package com.jingdong.app.mall.home.n.g.x;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class g extends com.jingdong.app.mall.home.n.g.u.e {
    private static Paint w = new Paint();
    private static Paint x = new Paint();
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private String f10418k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10419l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    private String f10420m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private String f10421n;
    @Nullable
    private String o;
    @NotNull
    private int[] p;
    @Nullable
    private String q;
    private String r;
    private String s;
    private int t;
    @Nullable
    private SpannableString u;
    @NotNull
    private int[] v;

    static {
        w.setTextSize(com.jingdong.app.mall.home.floor.common.d.d(20));
        x.setTextSize(com.jingdong.app.mall.home.floor.common.d.d(30));
    }

    public g(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.p = new int[]{-907508};
        this.v = new int[]{-49073, -381927};
    }

    private final void E() {
        int lastIndexOf$default;
        String str = this.s;
        if (str == null) {
            return;
        }
        if (str == null) {
            Intrinsics.throwNpe();
        }
        int length = str.length();
        String str2 = this.r;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        int length2 = str2.length();
        String str3 = this.s;
        if (str3 == null) {
            Intrinsics.throwNpe();
        }
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str3, OrderISVUtil.MONEY_DECIMAL, 0, false, 6, (Object) null);
        SpannableString spannableString = new SpannableString(this.s);
        this.u = spannableString;
        if (length2 > 0) {
            if (spannableString == null) {
                Intrinsics.throwNpe();
            }
            spannableString.setSpan(new com.jingdong.app.mall.home.category.floor.base.d((-DPIUtil.dip2px(1.0f)) + 0.0f), 0, length2, 17);
        }
        SpannableString spannableString2 = this.u;
        if (spannableString2 == null) {
            Intrinsics.throwNpe();
        }
        spannableString2.setSpan(new RelativeSizeSpan(0.67f), 0, length2 + 1, 17);
        if (lastIndexOf$default > 0) {
            SpannableString spannableString3 = this.u;
            if (spannableString3 == null) {
                Intrinsics.throwNpe();
            }
            spannableString3.setSpan(new RelativeSizeSpan(0.8f), lastIndexOf$default, length, 17);
        }
    }

    @Nullable
    public final String A() {
        return this.f10419l;
    }

    @Nullable
    public final String B() {
        return this.o;
    }

    @NotNull
    public final int[] C() {
        return this.p;
    }

    @Nullable
    public final String D() {
        return this.f10421n;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_Hot_Product");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        String str;
        this.f10418k = getJsonString("img2");
        this.f10419l = getJsonString("priceImg");
        this.f10420m = getJsonString("priceCapsuleImg");
        this.f10421n = getJsonString("title");
        this.o = getJsonString("promotionTag");
        int[] o = com.jingdong.app.mall.home.floor.common.i.m.o(getJsonString("promotionTagColor"), -907508);
        Intrinsics.checkExpressionValueIsNotNull(o, "MallFloorCommonUtil.getG\u2026ng(tagColorStr, -0xdd8f4)");
        this.p = o;
        String jsonString = getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        if (!TextUtils.isEmpty(jsonString)) {
            this.q = com.jingdong.app.mall.home.category.floor.feedssub.a.e(jsonString);
        }
        String jsonString2 = getJsonString("priceTag");
        this.r = jsonString2;
        if (!TextUtils.isEmpty(jsonString2)) {
            String str2 = this.r;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            if (str2.length() > 3) {
                String str3 = this.r;
                if (str3 == null) {
                    Intrinsics.throwNpe();
                }
                if (str3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                str = str3.substring(0, 3);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            } else {
                str = this.r;
            }
            this.r = str;
            this.r += ' ';
        }
        String jsonString3 = getJsonString("sprice");
        if (!TextUtils.isEmpty(jsonString3)) {
            String e2 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(jsonString3);
            String stringPlus = Intrinsics.stringPlus(this.r, e2);
            this.s = stringPlus;
            if (stringPlus.length() > 10) {
                String valueOf = String.valueOf(com.jingdong.app.mall.home.category.floor.feedssub.a.c(e2));
                Paint paint = w;
                if (paint == null) {
                    Intrinsics.throwNpe();
                }
                float measureText = paint.measureText(stringPlus);
                Paint paint2 = w;
                if (paint2 == null) {
                    Intrinsics.throwNpe();
                }
                float measureText2 = paint2.measureText(valueOf);
                Paint paint3 = x;
                if (paint3 == null) {
                    Intrinsics.throwNpe();
                }
                if ((measureText + paint3.measureText(valueOf)) - measureText2 > com.jingdong.app.mall.home.floor.common.d.d(R2.anim.settlement_dialog_bottom_enter)) {
                    this.r = "";
                    this.s = e2;
                }
            }
        }
        this.t = com.jingdong.app.mall.home.floor.common.i.m.i(getJsonString(CartPromotion.KEY_PRICECOLOR), -1);
        int[] n2 = com.jingdong.app.mall.home.floor.common.i.m.n(getJsonString("priceCapsuleColor"), this.v, true);
        if (n2 != null) {
            if (n2.length > 1) {
                this.v = n2;
            } else if (n2.length == 1) {
                int[] iArr = this.v;
                iArr[0] = n2[0];
                iArr[1] = n2[0];
            }
        }
        E();
    }

    @Nullable
    public final SpannableString u() {
        return this.u;
    }

    @Nullable
    public final String v() {
        return this.f10420m;
    }

    @Nullable
    public final String w() {
        return this.f10418k;
    }

    @Nullable
    public final String x() {
        return this.q;
    }

    @NotNull
    public final int[] y() {
        return this.v;
    }

    public final int z() {
        return this.t;
    }
}
