package com.jd.dynamic.a.a.a;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class o implements h {
    private final DynamicTemplateEngine a;

    public o(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable com.jd.dynamic.a.g gVar) {
        this.a = dynamicTemplateEngine;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x0069, code lost:
        if (r5 != null) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x006b, code lost:
        r1 = r5.f1712e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x006d, code lost:
        r5 = com.jd.dynamic.lib.utils.s.b(r6, r7, r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0017, code lost:
        if (r5 != null) goto L110;
     */
    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object a(@Nullable com.jd.dynamic.a.g gVar, @Nullable String str, @NotNull Object... objArr) {
        Object obj;
        DynamicTemplateEngine dynamicTemplateEngine;
        int doubleValue;
        View view = null;
        if (objArr.length != 1) {
            obj = gVar != null ? gVar.f1712e : null;
            dynamicTemplateEngine = this.a;
        } else if ((objArr[0] instanceof Double) || (objArr[0] instanceof String)) {
            if (objArr[0] instanceof String) {
                DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
                Object obj2 = objArr[0];
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                doubleValue = com.jd.dynamic.lib.dynamic.parser.h.a(dynamicTemplateEngine2, (String) obj2);
            } else {
                Object obj3 = objArr[0];
                if (obj3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Double");
                }
                doubleValue = (int) ((Double) obj3).doubleValue();
            }
            Integer valueOf = Integer.valueOf(doubleValue);
            DynamicTemplateEngine dynamicTemplateEngine3 = this.a;
            Object obj4 = s.b(str, null, dynamicTemplateEngine3, com.jd.dynamic.lib.utils.m.d(dynamicTemplateEngine3, valueOf.intValue(), gVar != null ? gVar.f1712e : null));
            Intrinsics.checkExpressionValueIsNotNull(obj4, "if (params.size == 1) {\n\u2026xt?.targetView)\n        }");
            return obj4;
        } else {
            obj = objArr[0];
            dynamicTemplateEngine = this.a;
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    public String a() {
        return "events";
    }
}
