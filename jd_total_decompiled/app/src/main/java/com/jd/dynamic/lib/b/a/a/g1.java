package com.jd.dynamic.lib.b.a.a;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class g1 extends e1 {
    private void a(JSONObject jSONObject) {
        int optInt;
        View pendingView;
        String optString = jSONObject.optString("methodName");
        if (TextUtils.isEmpty(optString) || (optInt = jSONObject.optInt("layoutId")) <= 0 || (pendingView = getPendingView(com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, String.valueOf(optInt)))) == null) {
            return;
        }
        try {
            Method method = pendingView.getClass().getMethod(optString, JSONObject.class);
            if (method != null) {
                method.invoke(pendingView, jSONObject.optJSONObject("map"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine != null && "method".equals((String) jSONObject.remove("fun"))) {
            a(jSONObject);
        }
        return null;
    }
}
