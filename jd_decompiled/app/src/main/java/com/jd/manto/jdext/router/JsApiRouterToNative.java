package com.jd.manto.jdext.router;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.IRouter;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public final class JsApiRouterToNative extends AbstractMantoModule {

    /* loaded from: classes17.dex */
    class a implements IRouter.CallBack {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        a(JsApiRouterToNative jsApiRouterToNative, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            this.b.putInt(IMantoBaseModule.ERROR_CODE, i2);
            this.a.onFailed(this.b);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            this.a.onSuccess(bundle);
        }
    }

    /* loaded from: classes17.dex */
    class b implements IRouter.CallBack {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        b(JsApiRouterToNative jsApiRouterToNative, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            this.b.putInt(IMantoBaseModule.ERROR_CODE, i2);
            this.a.onFailed(this.b);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            this.a.onSuccess(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "RouterGroup";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle2;
        StringBuilder sb;
        String string = bundle.getString("json");
        IRouter iRouter = (IRouter) com.jingdong.a.n(IRouter.class);
        if (iRouter == null) {
            mantoResultCallBack.onFailed(null);
            return;
        }
        if (TextUtils.equals("routerToNative", str)) {
            Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
            bundle2 = new Bundle(1);
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.optBoolean("isMethod", false)) {
                    Bundle bundle3 = new Bundle(1);
                    bundle3.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "routerToNativeMethod");
                    bundle3.putString("json", string);
                    mantoResultCallBack.onSuccess(bundle3);
                } else {
                    iRouter.jumpTo(activity, jSONObject, new a(this, mantoResultCallBack, bundle2));
                }
                return;
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder();
            }
        } else if (!TextUtils.equals("routerToNativeMethod", str)) {
            return;
        } else {
            bundle2 = new Bundle(1);
            try {
                iRouter.jumpTo(com.jingdong.a.g(), new JSONObject(string), new b(this, mantoResultCallBack, bundle2));
                return;
            } catch (Exception e3) {
                e = e3;
                sb = new StringBuilder();
            }
        }
        sb.append("");
        sb.append(e.getMessage());
        bundle2.putString("message", sb.toString());
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        String optString;
        Bundle bundle = new Bundle();
        if (!TextUtils.equals("routerToNative", str)) {
            if (TextUtils.equals("routerToNativeMethod", str)) {
                optString = jSONObject.optString("json", "");
            }
            return bundle;
        }
        optString = jSONObject.toString();
        bundle.putString("json", optString);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("routerToNative", 1));
        list.add(new JsApiMethod("routerToNativeMethod", 0));
    }
}
