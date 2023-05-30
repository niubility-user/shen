package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.a.c;
import com.jd.dynamic.lib.utils.s;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class c extends k {
    public c(@NotNull String str) {
        super(str);
    }

    private final String A(String str, i iVar) {
        int length = str.length();
        if (length <= 5) {
            return "";
        }
        int i2 = length - 1;
        if (str != null) {
            String substring = str.substring(4, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            if (com.jd.dynamic.lib.expv2.g.b(substring)) {
                Object a = iVar.a(substring);
                return a instanceof String ? (String) a : "";
            }
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean f(@NotNull String str) {
        k(str);
        return Intrinsics.areEqual(str, String.valueOf('}'));
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @NotNull
    public i h() {
        c cVar = new c(p());
        cVar.k(q());
        cVar.y(z());
        return cVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.k, com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        Object i2 = super.i(obj, dynamicTemplateEngine, view);
        return i2 instanceof String ? new c.b(s.b(A((String) i2, this), obj, dynamicTemplateEngine, view)) : i2;
    }
}
