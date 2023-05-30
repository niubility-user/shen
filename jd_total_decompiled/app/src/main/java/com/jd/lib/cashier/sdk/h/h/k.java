package com.jd.lib.cashier.sdk.h.h;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class k {
    @NotNull
    private final String a;
    @NotNull
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f3575c;
    private final boolean d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f3576e;

    public k(@NotNull String str, @NotNull String str2, boolean z, boolean z2, boolean z3) {
        this.a = str;
        this.b = str2;
        this.f3575c = z;
        this.d = z2;
        this.f3576e = z3;
    }

    @NotNull
    public final String a() {
        return this.b;
    }

    @NotNull
    public final String b() {
        return this.a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof k) {
                k kVar = (k) obj;
                return Intrinsics.areEqual(this.a, kVar.a) && Intrinsics.areEqual(this.b, kVar.b) && this.f3575c == kVar.f3575c && this.d == kVar.d && this.f3576e == kVar.f3576e;
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.f3575c;
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
        boolean z3 = this.f3576e;
        return i5 + (z3 ? 1 : z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "MtaShowParams(code=" + this.a + ", channelId=" + this.b + ", isCombined=" + this.f3575c + ", isDefault=" + this.d + ", canUse=" + this.f3576e + ")";
    }
}
