package com.jd.dynamic.a.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.t;

/* loaded from: classes13.dex */
public class f implements h {
    public DynamicTemplateEngine a;

    public f(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        DynamicTemplateEngine dynamicTemplateEngine;
        int i2;
        DynamicTemplateEngine dynamicTemplateEngine2;
        t.g("TEST", "onInvoke methodName : " + str);
        if (objArr == null || objArr.length <= 0 || !(objArr[0] instanceof String)) {
            return null;
        }
        String str2 = (String) objArr[0];
        if (TextUtils.equals("width", str2) && (dynamicTemplateEngine2 = this.a) != null) {
            i2 = dynamicTemplateEngine2.containerWidth;
        } else if (!TextUtils.equals("height", str2) || (dynamicTemplateEngine = this.a) == null) {
            return null;
        } else {
            i2 = dynamicTemplateEngine.containerHeight;
        }
        return Integer.valueOf(i2);
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "estimatedSize";
    }
}
