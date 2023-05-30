package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.DynamicTemplateEngine;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class d implements h {
    public DynamicTemplateEngine a;

    public d(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        if (objArr.length <= 0) {
            JSONObject jSONObject = this.a.currentData;
            return jSONObject != null ? jSONObject : new JSONObject();
        } else if (objArr[0] instanceof JSONObject) {
            this.a.currentData = (JSONObject) objArr[0];
            return null;
        } else {
            return null;
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "data";
    }
}
