package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.DynamicTemplateEngine;

/* loaded from: classes13.dex */
public class c implements h {
    public DynamicTemplateEngine a;

    public c(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        DynamicTemplateEngine dynamicTemplateEngine;
        if (!"rebindData".equals(str) || (dynamicTemplateEngine = this.a) == null) {
            return null;
        }
        dynamicTemplateEngine.rebindDataRefreshView(dynamicTemplateEngine.currentData);
        return null;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "container";
    }
}
