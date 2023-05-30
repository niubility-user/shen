package com.jd.manto.router;

import android.os.Bundle;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {

    /* renamed from: com.jd.manto.router.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    class C0203a implements CallBackListener {
        final /* synthetic */ MantoResultCallBack a;

        C0203a(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
            this.a.onSuccess(new Bundle());
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("error", i2);
            this.a.onFailed(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "postMsgToRN";
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
        JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
        jDRouterUrlBuilder.setModuleName("JDReactNativeRouter").setMethodName("sendEvent").putStringParam("appId", bundle.getString("appid")).putStringParam("moduleName", jSONObject.optString("moduleName")).putStringParam("eventName", jSONObject.optString("eventName")).putStringParam("params", jSONObject.optString("params"));
        JDRouter.build(com.jingdong.a.g(), jDRouterUrlBuilder.build()).callBackListener(new C0203a(this, mantoResultCallBack)).open();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("postMessageToRN", 0));
    }
}
