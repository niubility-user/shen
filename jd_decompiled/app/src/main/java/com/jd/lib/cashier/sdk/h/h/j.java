package com.jd.lib.cashier.sdk.h.h;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class j {
    @NotNull
    private final String a;
    @NotNull
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f3574c;

    public j(@NotNull String str, @NotNull String str2, boolean z) {
        this.a = str;
        this.b = str2;
        this.f3574c = z;
    }

    @NotNull
    public final String a() {
        return this.b;
    }

    @NotNull
    public final String b() {
        return this.a;
    }

    public final boolean c() {
        return this.f3574c;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof j) {
                j jVar = (j) obj;
                return Intrinsics.areEqual(this.a, jVar.a) && Intrinsics.areEqual(this.b, jVar.b) && this.f3574c == jVar.f3574c;
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
        boolean z = this.f3574c;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode2 + i2;
    }

    @NotNull
    public String toString() {
        return "MtaMultiCouponClickParams(code=" + this.a + ", channelId=" + this.b + ", isDefault=" + this.f3574c + ")";
    }
}
