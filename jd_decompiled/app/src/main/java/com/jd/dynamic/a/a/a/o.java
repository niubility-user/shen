package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.DynamicTemplateEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class o implements h {
    private final DynamicTemplateEngine a;

    public o(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable com.jd.dynamic.a.g gVar) {
        this.a = dynamicTemplateEngine;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0017, code lost:
        if (r5 != null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0069, code lost:
        if (r5 != null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x006b, code lost:
        r1 = r5.f1712e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x006d, code lost:
        r5 = com.jd.dynamic.lib.utils.s.b(r6, r7, r0, r1);
     */
    @Override // com.jd.dynamic.a.a.a.h
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(@org.jetbrains.annotations.Nullable com.jd.dynamic.a.g r5, @org.jetbrains.annotations.Nullable java.lang.String r6, @org.jetbrains.annotations.NotNull java.lang.Object... r7) {
        /*
            r4 = this;
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L61
            r0 = 0
            r2 = r7[r0]
            boolean r2 = r2 instanceof java.lang.Double
            if (r2 != 0) goto L1a
            r2 = r7[r0]
            boolean r2 = r2 instanceof java.lang.String
            if (r2 == 0) goto L13
            goto L1a
        L13:
            r7 = r7[r0]
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r4.a
            if (r5 == 0) goto L6d
            goto L6b
        L1a:
            r2 = r7[r0]
            boolean r2 = r2 instanceof java.lang.String
            if (r2 == 0) goto L35
            com.jd.dynamic.base.DynamicTemplateEngine r2 = r4.a
            r7 = r7[r0]
            if (r7 == 0) goto L2d
            java.lang.String r7 = (java.lang.String) r7
            int r7 = com.jd.dynamic.lib.dynamic.parser.h.a(r2, r7)
            goto L40
        L2d:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.String"
            r5.<init>(r6)
            throw r5
        L35:
            r7 = r7[r0]
            if (r7 == 0) goto L59
            java.lang.Double r7 = (java.lang.Double) r7
            double r2 = r7.doubleValue()
            int r7 = (int) r2
        L40:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r4.a
            int r7 = r7.intValue()
            if (r5 == 0) goto L4f
            android.view.View r5 = r5.f1712e
            goto L50
        L4f:
            r5 = r1
        L50:
            android.view.View r5 = com.jd.dynamic.lib.utils.m.d(r0, r7, r5)
            java.lang.Object r5 = com.jd.dynamic.lib.utils.s.b(r6, r1, r0, r5)
            goto L71
        L59:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Double"
            r5.<init>(r6)
            throw r5
        L61:
            if (r5 == 0) goto L66
            android.view.View r7 = r5.f1712e
            goto L67
        L66:
            r7 = r1
        L67:
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r4.a
            if (r5 == 0) goto L6d
        L6b:
            android.view.View r1 = r5.f1712e
        L6d:
            java.lang.Object r5 = com.jd.dynamic.lib.utils.s.b(r6, r7, r0, r1)
        L71:
            java.lang.String r6 = "if (params.size == 1) {\n\u2026xt?.targetView)\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.a.a.a.o.a(com.jd.dynamic.a.g, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    public String a() {
        return "events";
    }
}
