package com.jd.lib.cashier.sdk.h.h;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class h {
    @NotNull
    private final String a;
    @NotNull
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f3563c;
    @NotNull
    private final String d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private final String f3564e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    private final String f3565f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    private final String f3566g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    private final String f3567h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    private final String f3568i;

    public h(@NotNull String str, @NotNull String str2, boolean z, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8) {
        this.a = str;
        this.b = str2;
        this.f3563c = z;
        this.d = str3;
        this.f3564e = str4;
        this.f3565f = str5;
        this.f3566g = str6;
        this.f3567h = str7;
        this.f3568i = str8;
    }

    @NotNull
    public final String a() {
        return this.f3567h;
    }

    @NotNull
    public final String b() {
        return this.b;
    }

    @NotNull
    public final String c() {
        return this.a;
    }

    @NotNull
    public final String d() {
        return this.f3566g;
    }

    @NotNull
    public final String e() {
        return this.f3568i;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof h) {
                h hVar = (h) obj;
                return Intrinsics.areEqual(this.a, hVar.a) && Intrinsics.areEqual(this.b, hVar.b) && this.f3563c == hVar.f3563c && Intrinsics.areEqual(this.d, hVar.d) && Intrinsics.areEqual(this.f3564e, hVar.f3564e) && Intrinsics.areEqual(this.f3565f, hVar.f3565f) && Intrinsics.areEqual(this.f3566g, hVar.f3566g) && Intrinsics.areEqual(this.f3567h, hVar.f3567h) && Intrinsics.areEqual(this.f3568i, hVar.f3568i);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String f() {
        return this.f3564e;
    }

    @NotNull
    public final String g() {
        return this.f3565f;
    }

    public final boolean h() {
        return this.f3563c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.f3563c;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode2 + i2) * 31;
        String str3 = this.d;
        int hashCode3 = (i3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f3564e;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f3565f;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.f3566g;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.f3567h;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.f3568i;
        return hashCode7 + (str8 != null ? str8.hashCode() : 0);
    }

    @NotNull
    public final String i() {
        return this.d;
    }

    @NotNull
    public String toString() {
        return "MtaClickParams(code=" + this.a + ", channelId=" + this.b + ", isCombined=" + this.f3563c + ", isNewUser=" + this.d + ", orderId=" + this.f3564e + ", skuId=" + this.f3565f + ", currentSelectedCode=" + this.f3566g + ", changetag=" + this.f3567h + ", defaultCouponId=" + this.f3568i + ")";
    }
}
