package com.jingdong.common.XView2.dynamic.context;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.tencent.smtt.sdk.JsContext;
import com.tencent.smtt.sdk.JsValue;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ExpressContextDelegate implements IExpressContext {
    JsContext mJsContext;

    public ExpressContextDelegate(Context context) {
        if (this.mJsContext == null) {
            this.mJsContext = new JsContext(context);
        }
    }

    @Override // com.jingdong.common.XView2.dynamic.context.IExpressContext
    public String getValueScript(Object obj) {
        if (XView2Utils.isJsonObject(obj)) {
            JSONObject jSONObject = new JSONObject();
            JDJSONObject jDJSONObject = (JDJSONObject) obj;
            for (String str : jDJSONObject.keySet()) {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        jSONObject.put(str, jDJSONObject.get(str));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return jSONObject.toString();
        } else if (obj instanceof Number) {
            if (obj instanceof Integer) {
                return String.format("%d", obj);
            }
            return String.format("%f", obj);
        } else if (obj instanceof Boolean) {
            Object[] objArr = new Object[1];
            objArr[0] = ((Boolean) obj).booleanValue() ? DYConstants.DY_TRUE : DYConstants.DY_FALSE;
            return String.format("%b", objArr);
        } else if (obj instanceof String) {
            return String.format("'%s'", obj);
        } else {
            return null;
        }
    }

    @Override // com.jingdong.common.XView2.dynamic.context.IExpressContext
    public Object runScript(String str) {
        JsValue evaluateScript;
        JsContext jsContext = this.mJsContext;
        if (jsContext != null && (evaluateScript = jsContext.evaluateScript(str)) != null) {
            if (evaluateScript.isString()) {
                return evaluateScript.toString();
            }
            if (evaluateScript.isBoolean()) {
                return Boolean.valueOf(evaluateScript.toBoolean());
            }
            if (evaluateScript.isNumber()) {
                return evaluateScript.toNumber();
            }
            if (evaluateScript.isInteger()) {
                return Integer.valueOf(evaluateScript.toInteger());
            }
            if (evaluateScript.isUndefined()) {
                return evaluateScript;
            }
        }
        return null;
    }

    @Override // com.jingdong.common.XView2.dynamic.context.IExpressContext
    public void setVariables(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty() || this.mJsContext == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : hashMap.keySet()) {
            String valueScript = getValueScript(hashMap.get(str));
            if (!TextUtils.isEmpty(valueScript)) {
                sb.append(String.format("var %s=%s;", str, valueScript));
            }
        }
        if (this.mJsContext != null) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "builder.toString()" + sb.toString());
            this.mJsContext.evaluateScript(sb.toString());
        }
    }
}
