package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public abstract class f<T extends View> implements j<T> {
    protected DynamicTemplateEngine a;
    protected JSONObject b;

    public void b(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(JSONObject jSONObject) {
        this.b = jSONObject;
    }
}
