package com.jingdong.manto.m.q0;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.s;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements ILogin.CallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13568c;

        a(b bVar, Bundle bundle, Bundle bundle2, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = bundle2;
            this.f13568c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.CallBack
        public void onFailure() {
            Bundle bundle = new Bundle();
            bundle.putString("message", "doLogin onFailure");
            this.f13568c.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.CallBack
        public void onSuccess() {
            this.a.putString("needticket", this.b.getString("needticket"));
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "loginConfirm");
            this.f13568c.onSuccess(this.a);
        }
    }

    /* renamed from: com.jingdong.manto.m.q0.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0609b extends IMantoHttpListener {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ILogin f13569c;
        final /* synthetic */ MantoResultCallBack d;

        C0609b(b bVar, Bundle bundle, Bundle bundle2, ILogin iLogin, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = bundle2;
            this.f13569c = iLogin;
            this.d = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("errors");
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("msg");
                Bundle bundle = new Bundle();
                bundle.putString("message", "Exception: " + optString);
                this.d.onFailed(bundle);
                return;
            }
            MantoLog.e("JsApiLogin", "onError: " + th);
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "Exception: " + th.getMessage());
            this.d.onFailed(bundle2);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "code is: " + optString);
                    this.d.onFailed(bundle);
                    return;
                }
                this.a.putString("code", jSONObject.optJSONObject("data").optString("login_code"));
                if ("1".equals(this.b.getString("needticket"))) {
                    try {
                        this.a.putString("ticket", this.f13569c.getA2(com.jingdong.manto.b.f()));
                    } catch (Exception unused) {
                        this.a.putString("ticket", "");
                    }
                }
                this.a.putBoolean(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY, true);
                this.d.onSuccess(this.a);
            } catch (Exception e2) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "Exception: " + e2.getMessage());
                this.d.onFailed(bundle2);
            }
        }
    }

    /* loaded from: classes15.dex */
    class c implements ILogin.LoginInfoCallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13570c;

        c(b bVar, Bundle bundle, Bundle bundle2, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = bundle2;
            this.f13570c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.LoginInfoCallBack
        public void onError(int i2, String str) {
            this.a.putInt("code", i2);
            this.a.putString("message", str);
            this.f13570c.onFailed(this.a);
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.LoginInfoCallBack
        public void onFailure(int i2, String str) {
            this.a.putInt("code", i2);
            this.a.putString("message", str);
            this.f13570c.onFailed(this.a);
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.LoginInfoCallBack
        public void onSuccess(String str, String str2) {
            this.a.putString("ticket", str);
            if (this.b.getInt("needpin", 0) == 1) {
                this.a.putString("pt_pin", str2);
            }
            this.f13570c.onSuccess(this.a);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "LoginMethod";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        Bundle bundle2 = new Bundle();
        if (iLogin == null) {
            bundle2.putString("message", "ILogin not impl");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        if (LoginConstans.FREGMENT_LOGIN_FLAG.equals(str)) {
            if (!iLogin.hasLogin()) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("appId", bundle.getString("appid"));
                bundle3.putString("debugType", bundle.getString("type"));
                iLogin.doLogin(bundle.getString("processName"), bundle3, new a(this, bundle2, bundle, mantoResultCallBack));
                return;
            }
            bundle2.putString("needticket", bundle.getString("needticket"));
            bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "loginConfirm");
        } else if ("loginConfirm".equals(str)) {
            MantoJDHttpHandler.commit(new s(bundle.getString("appid")), new C0609b(this, bundle2, bundle, iLogin, mantoResultCallBack));
            return;
        } else if (!"hasUserLogined".equals(str)) {
            if ("requestWebCookie".equals(str)) {
                iLogin.getWebCookie(new c(this, bundle2, bundle, mantoResultCallBack));
                return;
            } else if ("clearWebCookie".equals(str)) {
                iLogin.clearWebCookie();
                mantoResultCallBack.onSuccess(null);
                return;
            } else {
                return;
            }
        } else {
            bundle2.putInt("status", iLogin.hasLogin() ? 1 : 0);
        }
        mantoResultCallBack.onSuccess(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if (LoginConstans.FREGMENT_LOGIN_FLAG.equals(str)) {
            bundle.putString("processName", MantoProcessUtil.getProcessName());
            bundle.putString("needticket", jSONObject.optString("needticket", "0"));
        }
        if ("loginConfirm".equals(str)) {
            bundle.putString("needticket", jSONObject.optString("needticket", "0"));
        }
        if ("requestWebCookie".equals(str)) {
            bundle.putInt("needpin", jSONObject.optInt("needpin"));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(LoginConstans.FREGMENT_LOGIN_FLAG, 0));
        list.add(new JsApiMethod("loginConfirm", 0));
        list.add(new JsApiMethod("hasUserLogined", 0));
        list.add(new JsApiMethod("requestWebCookie", 0));
        list.add(new JsApiMethod("clearWebCookie", 0));
    }
}
