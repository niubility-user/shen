package com.jd.manto.b;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c extends AbstractMantoModule {

    /* loaded from: classes17.dex */
    class a implements com.jd.manto.b.a {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        a(c cVar, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = mantoResultCallBack;
        }

        @Override // com.jd.manto.b.a
        public void a(String str) {
            MantoLog.d("JsApiJosGetAuthCode, ok:", "" + str);
            this.a.putString("token", str);
            this.b.onSuccess(this.a);
        }

        @Override // com.jd.manto.b.a
        public void onError(String str) {
            MantoLog.d("JsApiJosGetAuthCode, fail:", "" + str);
            this.a.putString("message", str);
            this.b.onFailed(this.a);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "GetTokenNew";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null && iLogin.hasLogin()) {
            bundle.getString("appid", "");
            String string = bundle.getString("json");
            Bundle bundle2 = new Bundle();
            MantoLog.d("JsApiGetToken", "invoked. param is: " + string);
            b.b(string, new a(this, bundle2, mantoResultCallBack));
            return;
        }
        Bundle bundle3 = new Bundle(1);
        bundle3.putString("error", "user not login");
        mantoResultCallBack.onFailed(bundle3);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("requestIsvToken", 502, 0));
    }
}
