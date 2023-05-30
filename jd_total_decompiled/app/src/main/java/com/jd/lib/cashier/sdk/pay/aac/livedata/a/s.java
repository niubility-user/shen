package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class s {
    private final boolean a;

    public s(boolean z) {
        this.a = z;
    }

    public final boolean a() {
        return this.a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof s) && this.a == ((s) obj).a;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.a;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    @NotNull
    public String toString() {
        return "UpdatePayFooterHttpEvent(invisibility=" + this.a + ")";
    }
}
