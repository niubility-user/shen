package com.jd.dynamic.lib.expv2.a;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.a.a.a;
import com.jd.dynamic.lib.expv2.a.c;
import com.jd.dynamic.lib.utils.s;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class d extends a {
    public d(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj, @Nullable View view) {
        super(dynamicTemplateEngine, obj, view);
    }

    private final String e(String str, com.jd.dynamic.lib.expv2.c.i iVar) {
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

    @Override // com.jd.dynamic.lib.expv2.a.i
    @Nullable
    public Object a(@NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
        String l2 = iVar.l();
        return l2 instanceof String ? new c.b(s.b(e(l2, iVar), c(), b(), d())) : l2;
    }
}
