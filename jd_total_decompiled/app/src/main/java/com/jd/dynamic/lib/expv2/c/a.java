package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a extends k {
    public a(@NotNull String str) {
        super(str);
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object b(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        return super.i(obj, dynamicTemplateEngine, view);
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean f(@NotNull String str) {
        char c2;
        k(str);
        if (Intrinsics.areEqual(String.valueOf('{'), p())) {
            c2 = '}';
        } else if (!Intrinsics.areEqual(String.valueOf('('), p())) {
            return false;
        } else {
            c2 = ')';
        }
        return Intrinsics.areEqual(str, String.valueOf(c2));
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @NotNull
    public i h() {
        a aVar = new a(p());
        aVar.k(q());
        aVar.y(z());
        return aVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean n() {
        return true;
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object o() {
        return l();
    }

    @Override // com.jd.dynamic.lib.expv2.c.k
    public boolean x() {
        return false;
    }
}
