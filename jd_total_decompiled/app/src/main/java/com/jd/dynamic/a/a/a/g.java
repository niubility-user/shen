package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;

/* loaded from: classes13.dex */
public class g implements h {
    public DynamicTemplateEngine a;
    public com.jd.dynamic.a.g b;

    public g(DynamicTemplateEngine dynamicTemplateEngine, com.jd.dynamic.a.g gVar) {
        this.a = dynamicTemplateEngine;
        this.b = gVar;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        if (objArr != null) {
            Object a = i.a(0, objArr);
            if (a instanceof String) {
                DynamicTemplateEngine dynamicTemplateEngine = this.a;
                com.jd.dynamic.a.g gVar2 = this.b;
                return s.a((String) a, dynamicTemplateEngine, gVar2.d, gVar2.f1712e);
            }
            return null;
        }
        return null;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "expression";
    }
}
