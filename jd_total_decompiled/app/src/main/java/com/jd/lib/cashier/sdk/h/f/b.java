package com.jd.lib.cashier.sdk.h.f;

import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntityRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b extends com.jd.lib.cashier.sdk.d.f.c {
    @NotNull
    private String a;
    @NotNull
    private String b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private String f3530c;
    @NotNull
    private String d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private String f3531e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    private String f3532f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CouponEntityRequest f3533g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    private CouponEntityRequest f3534h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    private String f3535i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    private String f3536j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f3537k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    private String f3538l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    private String f3539m;

    public b() {
        this.a = "";
        this.b = "";
        this.f3530c = "";
        this.d = "";
        this.f3531e = "";
        this.f3535i = "";
        this.f3536j = "";
        this.f3538l = "";
        this.f3539m = "";
    }

    @NotNull
    public final String a() {
        return this.b;
    }

    @NotNull
    public final String b() {
        return this.a;
    }

    @NotNull
    public final String c() {
        return this.f3539m;
    }

    @Nullable
    public final CouponEntityRequest d() {
        return this.f3533g;
    }

    @NotNull
    public final String e() {
        return this.f3531e;
    }

    @NotNull
    public final String f() {
        return this.f3530c;
    }

    @NotNull
    public final String g() {
        return this.d;
    }

    @NotNull
    public final String h() {
        return this.f3535i;
    }

    @NotNull
    public final String i() {
        return this.f3538l;
    }

    @NotNull
    public final String j() {
        return this.f3536j;
    }

    @Nullable
    public final CouponEntityRequest k() {
        return this.f3534h;
    }

    @Nullable
    public final String l() {
        return this.f3532f;
    }

    public final boolean m() {
        return this.f3537k;
    }

    public final void n(@NotNull String str) {
        this.a = str;
    }

    public final void o(@Nullable CouponEntityRequest couponEntityRequest) {
        this.f3533g = couponEntityRequest;
    }

    public final void p(@NotNull String str) {
        this.f3531e = str;
    }

    public final void q(@NotNull String str) {
        this.f3530c = str;
    }

    public final void r(@NotNull String str) {
        this.d = str;
    }

    public final void s(@NotNull String str) {
        this.f3535i = str;
    }

    public final void t(boolean z) {
        this.f3537k = z;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    @NotNull
    public String toString() {
        return "BaiTiaoPayPlanParam(code='" + this.a + "', firstQuery='" + this.f3530c + "', operationType='" + this.d + "', currentPlan='" + this.f3531e + "', targetPlan='" + this.f3532f + "', currentCoupon=" + this.f3533g + ", targetCoupon=" + this.f3534h + ", planRate='" + this.f3535i + ",sdkToken = '" + this.f3538l + "')";
    }

    public final void u(@Nullable CouponEntityRequest couponEntityRequest) {
        this.f3534h = couponEntityRequest;
    }

    public final void v(@Nullable String str) {
        this.f3532f = str;
    }

    public b(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @Nullable String str6, @Nullable CouponEntityRequest couponEntityRequest, @Nullable CouponEntityRequest couponEntityRequest2, @NotNull String str7, @NotNull String str8, @NotNull String str9) {
        this();
        this.a = str;
        this.b = str2;
        this.f3530c = str3;
        this.d = str4;
        this.f3531e = str5;
        this.f3532f = str6;
        this.f3533g = couponEntityRequest;
        this.f3534h = couponEntityRequest2;
        this.f3535i = str7;
        this.f3538l = str8;
        this.f3539m = str9;
    }
}
