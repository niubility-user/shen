package com.jd.lib.cashier.sdk.b.i;

import android.content.Context;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class f {

    /* loaded from: classes14.dex */
    public static final class a implements com.jd.lib.cashier.sdk.b.d.b.a {
        final /* synthetic */ Function0 a;

        a(Function0 function0) {
            this.a = function0;
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.a
        public void a(@Nullable String str) {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.a
        public void b(@Nullable String str) {
            Function0 function0 = this.a;
            if (function0 != null) {
                Unit unit = (Unit) function0.invoke();
            }
        }
    }

    @JvmStatic
    public static final void a(@NotNull Context context, @Nullable Function0<Unit> function0) {
        com.jd.lib.cashier.sdk.b.d.a.h(context, new a(function0));
    }
}
