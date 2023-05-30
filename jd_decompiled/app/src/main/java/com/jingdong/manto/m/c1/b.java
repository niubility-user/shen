package com.jingdong.manto.m.c1;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        a(b bVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            JSONObject optJSONObject;
            JSONObject optJSONObject2;
            String message = th.getMessage();
            if (jSONObject != null && (optJSONObject2 = jSONObject.optJSONObject("error")) != null) {
                message = optJSONObject2.optString("error", message);
            }
            Bundle bundle = new Bundle(2);
            bundle.putString("error", message);
            if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("error")) != null) {
                jSONObject = optJSONObject;
            }
            bundle.putString("resp", jSONObject != null ? jSONObject.toString() : "");
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(2);
            if (jSONObject == null) {
                bundle.putString("error", "resp data is empty");
                bundle.putString("resp", "");
                this.a.onFailed(bundle);
            } else if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                bundle.putString("resp", jSONObject.toString());
                this.a.onSuccess(bundle);
            } else {
                bundle.putString("error", jSONObject.optString("error"));
                bundle.putString("resp", jSONObject.toString());
                this.a.onFailed(bundle);
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "commonRequest";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            mantoResultCallBack.onFailed(null);
        } else {
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.j(bundle.getString("appid"), bundle.getString("type", "1"), jSONObject.optString("functionId"), jSONObject.optString("method"), jSONObject.optJSONObject("data")), new a(this, mantoResultCallBack));
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("mpCommonRequest", 1));
    }
}
