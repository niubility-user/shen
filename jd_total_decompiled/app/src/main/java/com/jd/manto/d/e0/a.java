package com.jd.manto.d.e0;

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

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {

    /* renamed from: com.jd.manto.d.e0.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    class C0189a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        C0189a(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", jSONObject.optString("error"));
            this.a.onFailed(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "zeroOrder";
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
            return;
        }
        String optString = jSONObject.optString("trackerId");
        if (TextUtils.isEmpty(optString)) {
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("error", "trackerId is empty");
            mantoResultCallBack.onFailed(bundle2);
        }
        MantoJDHttpHandler.commit(new b(bundle.getString("appid"), optString, jSONObject.optJSONArray("skus")), new C0189a(this, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("submitZeroOrder", 1));
    }
}
