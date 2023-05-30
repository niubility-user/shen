package com.jd.lib.cashier.sdk.h.h;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class i {
    @NotNull
    private final String a;
    @NotNull
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f3569c;
    private final boolean d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f3570e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    private final String f3571f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    private final String f3572g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    private final String f3573h;

    public i(@NotNull String str, @NotNull String str2, boolean z, boolean z2, boolean z3, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        this.a = str;
        this.b = str2;
        this.f3569c = z;
        this.d = z2;
        this.f3570e = z3;
        this.f3571f = str3;
        this.f3572g = str4;
        this.f3573h = str5;
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
        return this.f3573h;
    }

    @NotNull
    public final String d() {
        return this.f3571f;
    }

    public final boolean e() {
        return this.d;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof i) {
                i iVar = (i) obj;
                return Intrinsics.areEqual(this.a, iVar.a) && Intrinsics.areEqual(this.b, iVar.b) && this.f3569c == iVar.f3569c && this.d == iVar.d && this.f3570e == iVar.f3570e && Intrinsics.areEqual(this.f3571f, iVar.f3571f) && Intrinsics.areEqual(this.f3572g, iVar.f3572g) && Intrinsics.areEqual(this.f3573h, iVar.f3573h);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String f() {
        return this.f3572g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.f3569c;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode2 + i2) * 31;
        boolean z2 = this.d;
        int i4 = z2;
        if (z2 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        boolean z3 = this.f3570e;
        int i6 = (i5 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
        String str3 = this.f3571f;
        int hashCode3 = (i6 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f3572g;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f3573h;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "MtaExposeParams(code=" + this.a + ", channelId=" + this.b + ", isCombined=" + this.f3569c + ", isDefault=" + this.d + ", canUse=" + this.f3570e + ", strategy=" + this.f3571f + ", isNewUser=" + this.f3572g + ", orderId=" + this.f3573h + ")";
    }
}
