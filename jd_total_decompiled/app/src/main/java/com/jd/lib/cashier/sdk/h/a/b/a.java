package com.jd.lib.cashier.sdk.h.a.b;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class a {
    private final int a;

    public a(int i2) {
        this.a = i2;
    }

    public final int a() {
        return this.a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof a) && this.a == ((a) obj).a;
        }
        return true;
    }

    public int hashCode() {
        return this.a;
    }

    @NotNull
    public String toString() {
        return "CashierPayFailData(visible=" + this.a + ")";
    }
}
