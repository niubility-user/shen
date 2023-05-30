package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.b.a.a.m1;

/* loaded from: classes13.dex */
public class q implements h {
    /* JADX WARN: Code restructure failed: missing block: B:213:0x019e, code lost:
        if (r12 != null) goto L214;
     */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(com.jd.dynamic.a.g r11, java.lang.String r12, java.lang.Object... r13) {
        /*
            Method dump skipped, instructions count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.a.a.a.q.a(com.jd.dynamic.a.g, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "util";
    }

    public m1 b(com.jd.dynamic.a.g gVar, DynamicTemplateEngine dynamicTemplateEngine) {
        CommFunction commFunctionByType = dynamicTemplateEngine.getCachePool().getCommFunctionByType(a());
        if (commFunctionByType == null) {
            commFunctionByType = new m1();
            dynamicTemplateEngine.getCachePool().addCommFunction(a(), commFunctionByType);
        }
        commFunctionByType.mTargetView = gVar.f1712e;
        return (m1) commFunctionByType;
    }
}
