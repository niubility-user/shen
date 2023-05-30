package com.jd.dynamic.lib.expv2.a;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.c.a;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class j implements f {
    private final DynamicTemplateEngine a;
    private final Object b;

    /* renamed from: c  reason: collision with root package name */
    private final View f2232c;

    public j(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj, @Nullable View view) {
        this.a = dynamicTemplateEngine;
        this.b = obj;
        this.f2232c = view;
    }

    @Override // com.jd.dynamic.lib.expv2.a.f
    @NotNull
    public i a(@NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
        return iVar instanceof com.jd.dynamic.lib.expv2.c.f ? new g(this.a, this.b, this.f2232c) : iVar instanceof com.jd.dynamic.lib.expv2.c.d ? new e(this.a, this.b, this.f2232c) : iVar instanceof a ? new b() : iVar instanceof com.jd.dynamic.lib.expv2.c.b ? new c(this.a) : iVar instanceof com.jd.dynamic.lib.expv2.c.c ? new d(this.a, this.b, this.f2232c) : new h();
    }
}
