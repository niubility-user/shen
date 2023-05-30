package com.jingdong.manto.jsapi.refact;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JSApiFetch extends AbstractMantoModule {
    public void fetch(String str, String str2, String str3, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "fetchModule";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle2 = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("paramsStr"));
            fetch(jSONObject.optString("url"), jSONObject.optString("host"), jSONObject.optString("beta_host"), jSONObject.optJSONObject("param"), mantoResultCallBack);
        } catch (Exception e2) {
            bundle2.putString("data", e2.getMessage());
            mantoResultCallBack.onSuccess(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return new Bundle();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("paramsStr", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("fetch", 0));
    }
}
