package com.jd.dynamic.lib.expv2.e;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a {
    private int a;
    private int b;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a() {
        /*
            r3 = this;
            r0 = 0
            r1 = 3
            r2 = 0
            r3.<init>(r0, r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.expv2.e.a.<init>():void");
    }

    public a(int i2, int i3) {
        this.a = i2;
        this.b = i3;
    }

    public /* synthetic */ a(int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? -1 : i2, (i4 & 2) != 0 ? -1 : i3);
    }

    public final int a() {
        return this.b;
    }

    public final void b(int i2) {
        this.a = i2;
    }

    public final void c(int i2) {
        this.b = i2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.a == aVar.a && this.b == aVar.b;
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.a * 31) + this.b;
    }

    @NotNull
    public String toString() {
        return "XPStringParam(times=" + this.a + ", pos=" + this.b + ")";
    }
}
