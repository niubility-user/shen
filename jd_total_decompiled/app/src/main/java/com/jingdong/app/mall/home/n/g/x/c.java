package com.jingdong.app.mall.home.n.g.x;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class c extends com.jingdong.app.mall.home.n.g.u.e {
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private SpannableString f10404k;

    /* renamed from: l  reason: collision with root package name */
    private int f10405l;

    /* renamed from: m  reason: collision with root package name */
    private int f10406m;

    /* renamed from: n  reason: collision with root package name */
    private int f10407n;
    @Nullable
    private String o;
    @NotNull
    private String p;
    @Nullable
    private String q;
    @Nullable
    private String r;
    private String s;
    private String t;
    @Nullable
    private String u;
    @Nullable
    private String v;
    @Nullable
    private String w;
    @Nullable
    private com.jingdong.app.mall.home.n.g.v.d x;

    public c(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.f10405l = 3;
        this.f10407n = 1;
        this.p = "5";
    }

    private final SpannableString D(String str) {
        boolean contains$default;
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "\u6298", false, 2, (Object) null);
        if (!contains$default) {
            str = str + "\u6298";
        }
        SpannableString spannableString = new SpannableString(str);
        int length = str.length();
        if (length > 1) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), length - 1, length, 17);
        }
        return spannableString;
    }

    private final void F(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.f10406m == 3) {
            this.f10404k = D(str);
        }
        if (this.f10406m != 0 || com.jingdong.app.mall.home.n.h.c.d(str)) {
            return;
        }
        this.f10404k = com.jingdong.app.mall.home.category.floor.feedssub.a.d(str, OrderISVUtil.MONEY_DECIMAL, 0.5f);
    }

    @Nullable
    public final String A() {
        return this.v;
    }

    @Nullable
    public final com.jingdong.app.mall.home.n.g.v.d B() {
        return this.x;
    }

    @Nullable
    public final String C() {
        return this.w;
    }

    @Nullable
    public final String E() {
        return this.o;
    }

    public final boolean G() {
        int i2 = this.f10405l;
        return (i2 == 1 || i2 == 2) ? false : true;
    }

    public final boolean H() {
        return this.f10407n == 2;
    }

    public final boolean I() {
        return this.f10405l == 2;
    }

    public final void J(@NotNull String str, @Nullable String str2) {
        this.o = str2;
        if (Intrinsics.areEqual("999", str)) {
            this.f10405l = 2;
            this.p = "1";
        } else if (!Intrinsics.areEqual("6", str) && !Intrinsics.areEqual("8", str) && !Intrinsics.areEqual("11", str)) {
            if (!Intrinsics.areEqual("16", str) && !Intrinsics.areEqual(Constants.VIA_REPORT_TYPE_START_GROUP, str)) {
                this.f10405l = 1;
                this.p = "5";
                return;
            }
            this.f10405l = 3;
            this.p = "3";
        } else {
            this.f10405l = 0;
            this.p = "4";
        }
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public boolean m() {
        return this.f10404k != null;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_Coupon_Use");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.f10407n = getJsonInt("couponStyle", 1);
        this.f10406m = getJsonInt(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, 0);
        this.f10405l = getJsonInt("couponStatus", 3);
        String discount = getJsonString("discount");
        Intrinsics.checkExpressionValueIsNotNull(discount, "discount");
        F(discount);
        this.q = getJsonString("bgImg");
        this.r = getJsonString("couponTitle");
        String jsonString = getJsonString("getButtonText");
        this.s = jsonString;
        this.s = TextUtils.isEmpty(jsonString) ? "\u7acb\u5373\u9886\u53d6" : this.s;
        String jsonString2 = getJsonString("useButtonText");
        this.t = jsonString2;
        this.t = TextUtils.isEmpty(jsonString2) ? "\u53bb\u4f7f\u7528" : this.t;
        this.u = getJsonString("couponSubTitle");
        JDJSONObject jsonObject = getJsonObject("couponInfo");
        if (jsonObject != null) {
            this.w = com.jingdong.app.mall.home.r.e.b.getJsonString(jsonObject, "ruleId", "");
            this.v = com.jingdong.app.mall.home.r.e.b.getJsonString(jsonObject, "encryptedKey", "");
        }
        boolean H = H();
        this.f10370i = com.jingdong.app.mall.home.floor.common.d.d(H ? R2.attr.center_ellipsize_textview : R2.attr.borderColor);
        com.jingdong.app.mall.home.n.g.u.c mParentModel = this.f10369h;
        Intrinsics.checkExpressionValueIsNotNull(mParentModel, "mParentModel");
        String j2 = mParentModel.j();
        com.jingdong.app.mall.home.n.g.u.c mParentModel2 = this.f10369h;
        Intrinsics.checkExpressionValueIsNotNull(mParentModel2, "mParentModel");
        String b = mParentModel2.b();
        if (!TextUtils.isEmpty(j2) && !H) {
            this.q = j2;
        } else if (!TextUtils.isEmpty(b) && H) {
            this.q = b;
        }
        String h2 = h();
        if (TextUtils.isEmpty(h2)) {
            return;
        }
        this.x = com.jingdong.app.mall.home.n.g.v.d.c(h2);
    }

    @Nullable
    public final String u() {
        return this.q;
    }

    @Nullable
    public final String v() {
        int i2 = this.f10405l;
        if (i2 == 1) {
            return this.s;
        }
        if (i2 == 2) {
            return this.t;
        }
        return i2 == 3 ? "\u5df2\u62a2\u5149" : "\u5df2\u7ed3\u675f";
    }

    @NotNull
    public final String w() {
        return this.p;
    }

    @Nullable
    public final SpannableString x() {
        return this.f10404k;
    }

    @Nullable
    public final String y() {
        return this.u;
    }

    @Nullable
    public final String z() {
        return this.r;
    }
}
