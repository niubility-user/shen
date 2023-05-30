package com.jd.dynamic.lib.expv2;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.c.e;
import com.jd.dynamic.lib.expv2.c.i;
import com.jd.dynamic.lib.expv2.c.j;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class h extends f {

    /* renamed from: e */
    private DynamicTemplateEngine f2243e;

    /* renamed from: f */
    private Object f2244f;

    /* renamed from: g */
    private View f2245g;

    @Override // com.jd.dynamic.lib.expv2.f, com.jd.dynamic.lib.expv2.d
    @Nullable
    public Object a(@NotNull String str) {
        boolean startsWith$default;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "fun{", false, 2, null);
        if (startsWith$default) {
            return str;
        }
        Object a = super.a(str);
        if (a instanceof i) {
            Object u = ((i) a).u();
            return !g.a(u) ? "" : u;
        }
        return "";
    }

    @Override // com.jd.dynamic.lib.expv2.d
    public void a(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj, @Nullable View view) {
        this.f2243e = dynamicTemplateEngine;
        this.f2244f = obj;
        this.f2245g = view;
    }

    @Override // com.jd.dynamic.lib.expv2.f
    @NotNull
    public e b() {
        return new j(new com.jd.dynamic.lib.expv2.a.j(this.f2243e, this.f2244f, this.f2245g));
    }

    @Nullable
    public final i e(@NotNull String str) {
        Object a = super.a(str);
        if (a instanceof i) {
            return (i) a;
        }
        return null;
    }
}
