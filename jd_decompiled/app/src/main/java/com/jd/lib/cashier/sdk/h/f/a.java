package com.jd.lib.cashier.sdk.h.f;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class a extends com.jd.lib.cashier.sdk.d.f.c {
    @Nullable
    private String a = "";
    @Nullable
    private String b = "";
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private String f3529c = "";
    @Nullable
    private String d = "";

    @Nullable
    public final String a() {
        return this.d;
    }

    @Nullable
    public final String b() {
        return this.a;
    }

    @Nullable
    public final String c() {
        return this.f3529c;
    }

    @Nullable
    public final String d() {
        return this.b;
    }

    public final void e(@Nullable String str) {
        this.d = str;
    }

    public final void f(@Nullable String str) {
        this.a = str;
    }

    public final void g(@Nullable String str) {
        this.f3529c = str;
    }

    public final void h(@Nullable String str) {
        this.b = str;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    @NotNull
    public String toString() {
        return "BaiTiaoCouponRequestParam(code='" + this.a + "',sdkToken = '" + this.b + "')";
    }
}
