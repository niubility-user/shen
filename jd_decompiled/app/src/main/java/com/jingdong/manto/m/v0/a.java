package com.jingdong.manto.m.v0;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.card.CardRequestParameter;
import com.jingdong.manto.card.MantoCardHelper;
import com.jingdong.manto.e;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.c0;
import com.jingdong.manto.network.mantorequests.d0;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.v0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0628a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ CardRequestParameter b;

        C0628a(a aVar, MantoResultCallBack mantoResultCallBack, CardRequestParameter cardRequestParameter) {
            this.a = mantoResultCallBack;
            this.b = cardRequestParameter;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            Bundle bundle = new Bundle();
            bundle.putString("message", th != null ? th.getMessage() : "\u83b7\u53d6\u6d3b\u52a8\u6570\u636e\u51fa\u9519");
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject == null || !TextUtils.equals("0", jSONObject.optString("code"))) {
                Bundle bundle = new Bundle();
                bundle.putString("message", "no data");
                this.a.onFailed(bundle);
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            String jSONObject2 = optJSONObject != null ? optJSONObject.toString() : "";
            Bundle bundle2 = new Bundle();
            bundle2.putString("result", jSONObject2);
            this.a.onSuccess(bundle2);
            MantoCardHelper.addUUidCache(this.b, optJSONObject != null ? optJSONObject.optString("activityUuid") : "");
        }
    }

    /* loaded from: classes15.dex */
    class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        b(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            StringBuilder sb = new StringBuilder();
            sb.append("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25:");
            sb.append(th != null ? th.getMessage() : "");
            bundle.putString("error", sb.toString());
            bundle.putString("code", "-4");
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (!TextUtils.equals(optString, "0") || optJSONObject == null) {
                bundle.putString("error", optString2);
                bundle.putString("code", optString);
                this.a.onFailed(bundle);
                return;
            }
            bundle.putString("appId", optJSONObject.optString("app_id"));
            bundle.putString("code", optString);
            this.a.onSuccess(bundle);
        }
    }

    /* loaded from: classes15.dex */
    class c extends IMantoHttpListener {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13802c;

        c(a aVar, JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
            this.a = jSONObject;
            this.b = str;
            this.f13802c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            StringBuilder sb = new StringBuilder();
            sb.append("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25:");
            sb.append(th != null ? th.getMessage() : "");
            bundle.putString("error", sb.toString());
            bundle.putString("code", "-4");
            this.f13802c.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (!TextUtils.equals(optString, "0") || optJSONObject == null) {
                bundle.putString("error", optString2);
                bundle.putString("code", optString);
                this.f13802c.onFailed(bundle);
                return;
            }
            String optString3 = optJSONObject.optString("app_id");
            LaunchParam launchParam = new LaunchParam();
            JSONObject jSONObject2 = this.a;
            if (jSONObject2 != null) {
                launchParam.extrasJson = jSONObject2.toString();
            }
            launchParam.appId = optString3;
            launchParam.debugType = this.b;
            com.jingdong.a.o(launchParam);
            bundle.putString("code", optString);
            this.f13802c.onSuccess(bundle);
        }
    }

    private void a(CardRequestParameter cardRequestParameter, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new c0(cardRequestParameter.getCardID(), cardRequestParameter.getVendorId(), cardRequestParameter.getScene()), new C0628a(this, mantoResultCallBack, cardRequestParameter));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "smartCard";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        if (mantoCore == null || bundle == null) {
            return;
        }
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            mantoResultCallBack.onFailed(null);
            return;
        }
        String string = bundle.getString("appid");
        bundle.getString("type");
        if (TextUtils.equals(str, "getActivityInfo")) {
            CardRequestParameter cardRequestParameter = ((e) mantoCore).getCardRequestParameter();
            if (cardRequestParameter != null && cardRequestParameter.isValid()) {
                a(cardRequestParameter, mantoResultCallBack);
                return;
            }
        } else if (TextUtils.equals(str, "setCardGestureMode")) {
            String optString = jSONObject.optString("gestureMode");
            Bundle bundle2 = new Bundle(1);
            ((e) mantoCore).setGestureMode(optString);
            mantoResultCallBack.onSuccess(bundle2);
            return;
        } else if (TextUtils.equals(str, "getRelationAppInfo")) {
            MantoJDHttpHandler.commit(new d0(string), new b(this, mantoResultCallBack));
            return;
        } else if (TextUtils.equals(str, "navigateToRelationMiniProgram")) {
            MantoJDHttpHandler.commit(new d0(string), new c(this, jSONObject.optJSONObject("extraData"), jSONObject.optString("type", "1"), mantoResultCallBack));
            return;
        }
        mantoResultCallBack.onFailed(null);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getRelationAppInfo", 1));
        list.add(new JsApiMethod("navigateToRelationMiniProgram", 1));
        list.add(new JsApiMethod("setCardGestureMode", 1));
        list.add(new JsApiMethod("getActivityInfo", 1));
    }
}
