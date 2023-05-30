package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.a.c;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class b extends k {
    public b(@NotNull String str) {
        super(str);
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean f(@NotNull String str) {
        k(str);
        return Intrinsics.areEqual(str, String.valueOf('}'));
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @NotNull
    public i h() {
        b bVar = new b(p());
        bVar.k(q());
        bVar.y(z());
        return bVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.k, com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        Object i2 = super.i(obj, dynamicTemplateEngine, view);
        if (i2 instanceof String) {
            String str = (String) i2;
            if (str.length() > 3) {
                c.a aVar = com.jd.dynamic.lib.expv2.a.c.d;
                String c2 = aVar.c(str);
                List<String> f2 = aVar.f(c2);
                if (f2.isEmpty()) {
                    return "";
                }
                if (f2.size() >= 2) {
                    return aVar.b(f2, this);
                }
                try {
                    return Boolean.valueOf(aVar.e(c2, this));
                } catch (Exception unused) {
                    return "";
                }
            }
        }
        return i2;
    }
}
