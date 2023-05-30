package com.jingdong.manto.jsapi.webview;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.h0;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ MantoResultCallBack b;

        a(f fVar, MantoCore mantoCore, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoCore;
            this.b = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.a(this.a).b();
            this.b.onSuccess(null);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "WebViewApi";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("params");
        if ("checkJsApi".equals(str)) {
            try {
                JSONArray optJSONArray = new JSONObject(string).optJSONArray("jsApiList");
                if (optJSONArray == null) {
                    mantoResultCallBack.onFailed(null);
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                int i2 = 0;
                while (i2 < optJSONArray.length()) {
                    try {
                        String optString = optJSONArray.optString(i2);
                        jSONObject.put(optString, h0.c().containsKey(optString));
                        i2++;
                    } catch (Throwable th) {
                        MantoLog.e("checkJsApi", th.toString());
                    }
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("checkResult", jSONObject.toString());
                mantoResultCallBack.onSuccess(bundle2);
            } catch (Exception unused) {
                mantoResultCallBack.onFailed(null);
            }
        } else if ("closeWindow".equals(str)) {
            if (g.a(mantoCore) == null) {
                mantoResultCallBack.onFailed(null);
            } else {
                MantoThreadUtils.runOnUIThread(new a(this, mantoCore, mantoResultCallBack));
            }
        } else if ("invokeMiniProgramAPI".equals(str)) {
            if (g.a(mantoCore) == null) {
                mantoResultCallBack.onFailed(null);
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                dispatchEvent(mantoCore, new c().getJsApiName(), jSONObject2, bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE));
                mantoResultCallBack.onSuccess(null);
            } catch (Exception unused2) {
                mantoResultCallBack.onFailed(null);
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("checkJsApi", 1));
        list.add(new JsApiMethod("closeWindow", 1));
        list.add(new JsApiMethod("invokeMiniProgramAPI", 1));
    }
}
