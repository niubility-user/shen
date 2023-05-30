package com.jd.libs.hybrid.base;

import android.text.TextUtils;
import android.webkit.ValueCallback;
import com.jd.libs.xdog.b;
import com.jingdong.common.web.managers.WebPerfManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class XDogUtils {
    public static final String JS_FCP_FORMAT = "JSON.stringify(window.performance.getEntriesByType('paint'))";

    public static void setConfigForPaint(HybridWebView hybridWebView, final String str) {
        hybridWebView.evaluateJavascript(JS_FCP_FORMAT, new ValueCallback<String>() { // from class: com.jd.libs.hybrid.base.XDogUtils.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                String replace = str2.replace("\\\"", "\"");
                if (replace.startsWith("\"")) {
                    replace = replace.substring(1, replace.length() - 1);
                }
                JSONArray jSONArray = null;
                try {
                    jSONArray = new JSONArray(replace);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (jSONArray == null) {
                    return;
                }
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                        String valueOf = String.valueOf(jSONObject.opt("startTime"));
                        if ("first-contentful-paint".equals(jSONObject.getString("name"))) {
                            b.j(str, "data", WebPerfManager.FCP, valueOf);
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
    }
}
