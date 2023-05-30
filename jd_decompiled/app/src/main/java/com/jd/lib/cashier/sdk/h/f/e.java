package com.jd.lib.cashier.sdk.h.f;

import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class e extends com.jd.lib.cashier.sdk.d.f.c {
    @NotNull
    private final String a;
    @NotNull
    private final String b;

    public e(@NotNull String str, @NotNull String str2) {
        this.a = str;
        this.b = str2;
    }

    @NotNull
    public final String a() {
        return this.a;
    }

    @NotNull
    public final String b() {
        return this.b;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    @NotNull
    public String toString() {
        return "CloseRecommendParam(channelId='" + this.a + "', recommendDesc='" + this.b + "')";
    }
}
