package com.jd.lib.cashier.sdk.b.h;

import androidx.fragment.app.FragmentActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public abstract class d<T extends FragmentActivity> {
    @NotNull
    private final T a;
    @NotNull
    private final com.jd.lib.cashier.sdk.core.utils.a b;

    private d(T t, com.jd.lib.cashier.sdk.core.utils.a aVar) {
        this.a = t;
        this.b = aVar;
    }

    @NotNull
    public final T a() {
        return this.a;
    }

    @NotNull
    public final com.jd.lib.cashier.sdk.core.utils.a b() {
        return this.b;
    }

    public /* synthetic */ d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.core.utils.a aVar, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, aVar);
    }
}
