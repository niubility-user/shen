package com.jd.dynamic.lib.viewparse.c.e;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public abstract class p0<T extends View> implements q0<T> {
    protected DynamicTemplateEngine a;
    protected JSONObject b;

    /* JADX INFO: Access modifiers changed from: protected */
    public String b() {
        return com.jd.dynamic.lib.utils.m.j(this.a);
    }

    public void c(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String d() {
        return com.jd.dynamic.lib.utils.m.O(this.a);
    }

    public void e(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }
}
