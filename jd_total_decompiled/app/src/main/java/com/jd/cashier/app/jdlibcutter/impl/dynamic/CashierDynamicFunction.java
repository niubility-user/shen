package com.jd.cashier.app.jdlibcutter.impl.dynamic;

import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class CashierDynamicFunction extends CommFunction {
    public static final String PAY_DYNAMIC_CLASS_NAME = "CashierDynamicFunction";
    private final WeakReference<DynamicEventListener<JSONObject>> mEventListener;

    public CashierDynamicFunction(DynamicEventListener<JSONObject> dynamicEventListener) {
        this.mEventListener = new WeakReference<>(dynamicEventListener);
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        try {
            DynamicEventListener<JSONObject> dynamicEventListener = this.mEventListener.get();
            return dynamicEventListener != null ? dynamicEventListener.onEvent(jSONObject) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
