package com.jd.lib.cashier.sdk.h.f;

import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntityRequest;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class f extends com.jd.lib.cashier.sdk.d.f.c {
    @NotNull
    private String a;
    @NotNull
    private String b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private String f3551c;
    @Nullable
    private CouponEntityRequest d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    private CouponEntityRequest f3552e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    private String f3553f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private String f3554g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    private CreditCard f3555h;

    public f() {
        this.a = "";
        this.b = "";
        this.f3551c = "";
        this.f3553f = "";
        this.f3554g = "";
    }

    @NotNull
    public final String a() {
        return this.b;
    }

    @NotNull
    public final String b() {
        return this.a;
    }

    @Nullable
    public final CouponEntityRequest c() {
        return this.d;
    }

    @Nullable
    public final String d() {
        return this.f3553f;
    }

    @Nullable
    public final CreditCard e() {
        return this.f3555h;
    }

    @NotNull
    public final String f() {
        return this.f3551c;
    }

    @Nullable
    public final CouponEntityRequest g() {
        return this.f3552e;
    }

    @Nullable
    public final String h() {
        return this.f3554g;
    }

    public final void i(@Nullable CreditCard creditCard) {
        this.f3555h = creditCard;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    @NotNull
    public String toString() {
        return "CreditCardPayPlanRequestParam(bankCode='" + this.b + "', channelId='" + this.a + "', operationType='" + this.f3551c + "', currentPlan='" + this.f3553f + "', targetPlan='" + this.f3554g + "', currentCoupon=" + this.d + ", targetCoupon=" + this.f3552e + " )";
    }

    public /* synthetic */ f(String str, String str2, String str3, String str4, String str5, CouponEntityRequest couponEntityRequest, CouponEntityRequest couponEntityRequest2, CreditCard creditCard, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? null : couponEntityRequest, (i2 & 64) != 0 ? null : couponEntityRequest2, (i2 & 128) != 0 ? null : creditCard);
    }

    @JvmOverloads
    public f(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @Nullable CouponEntityRequest couponEntityRequest, @Nullable CouponEntityRequest couponEntityRequest2, @Nullable CreditCard creditCard) {
        this();
        this.a = str;
        this.b = str2;
        this.f3551c = str3;
        this.f3553f = str4;
        this.f3554g = str5;
        this.d = couponEntityRequest;
        this.f3552e = couponEntityRequest2;
        this.f3555h = creditCard;
    }
}
