package com.jd.manto.jdext.phone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.MantoReqUpdateOtherAuthSetting;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoUserInfoAuthDialog;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.input.InputUtil;
import com.tencent.connect.common.Constants;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiGetPhoneNumber extends AbstractMantoModule {
    private MantoUserInfoAuthDialog a = null;

    /* loaded from: classes17.dex */
    class a implements AuthorizeCallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bundle f6734c;

        a(JsApiGetPhoneNumber jsApiGetPhoneNumber, Bundle bundle, MantoResultCallBack mantoResultCallBack, Bundle bundle2) {
            this.a = bundle;
            this.b = mantoResultCallBack;
            this.f6734c = bundle2;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.putString("result", IMantoBaseModule.SUCCESS);
            this.b.onSuccess(this.a);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            this.a.putString("dialogContent", str);
            this.a.putString("appid", this.f6734c.getString("appid"));
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "native_getPhoneNumber");
            this.b.onSuccess(this.a);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.a.putString("result", "fail");
            this.a.putString("message", str);
            this.b.onFailed(this.a);
        }
    }

    /* loaded from: classes17.dex */
    class b extends IMantoHttpListener {
        final /* synthetic */ AuthorizeCallBack a;
        final /* synthetic */ String b;

        b(JsApiGetPhoneNumber jsApiGetPhoneNumber, AuthorizeCallBack authorizeCallBack, String str) {
            this.a = authorizeCallBack;
            this.b = str;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            if (optJSONObject != null) {
                this.a.onFailure(optJSONObject.optString("msg"));
                return;
            }
            MantoLog.e("getPhoneNumber", "onError: " + th);
            this.a.onFailure(th.toString());
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    this.a.onFailure("error");
                } else {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    String optString2 = optJSONObject.optString(Constants.PARAM_SCOPE, "0");
                    String optString3 = optJSONObject.optString("mobileWithMask");
                    if ("0".equals(optString2)) {
                        this.a.onConfirm(new AuthInfo("scope.1", "1", "\u83b7\u53d6\u624b\u673a\u53f7\u7801", optString3, AuthorizeManager.State.NONE), optString3);
                    } else if ("1".equals(optString2)) {
                        com.jingdong.a.e(this.b, "scope.1");
                        this.a.onAuth();
                    } else {
                        this.a.onFailure(optString);
                    }
                }
            } catch (Exception unused) {
                this.a.onFailure("error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoCore b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ AuthInfo f6735c;
        final /* synthetic */ MantoResultCallBack d;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            final Bundle a = new Bundle(1);
            final /* synthetic */ PkgDetailEntity b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ String f6737c;

            /* renamed from: com.jd.manto.jdext.phone.JsApiGetPhoneNumber$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class C0198a implements MantoAuthDialog.Callback {
                C0198a() {
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    a aVar = a.this;
                    c cVar = c.this;
                    JsApiGetPhoneNumber.this.a(aVar.f6737c, cVar.a, cVar.d);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    a aVar = a.this;
                    com.jingdong.a.B(aVar.f6737c, c.this.f6735c);
                    a.this.a.putString("message", "fail: auth deny");
                    a aVar2 = a.this;
                    c.this.d.onFailed(aVar2.a);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    a aVar = a.this;
                    com.jingdong.a.B(aVar.f6737c, c.this.f6735c);
                    a.this.a.putString("message", "fail: reject");
                    a aVar2 = a.this;
                    c.this.d.onFailed(aVar2.a);
                }
            }

            a(PkgDetailEntity pkgDetailEntity, String str) {
                this.b = pkgDetailEntity;
                this.f6737c = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle;
                String str;
                if (this.b != null) {
                    MantoCore mantoCore = c.this.b;
                    if (mantoCore != null && !mantoCore.isFinishing()) {
                        InputUtil.hideVKB(c.this.b.getActivity());
                        if (JsApiGetPhoneNumber.this.a != null && JsApiGetPhoneNumber.this.a.isShowing() && !c.this.b.isFinishing()) {
                            JsApiGetPhoneNumber.this.a.cancel();
                            JsApiGetPhoneNumber.this.a = null;
                        }
                        LinkedList linkedList = new LinkedList();
                        linkedList.add(c.this.f6735c);
                        JsApiGetPhoneNumber jsApiGetPhoneNumber = JsApiGetPhoneNumber.this;
                        Activity activity = c.this.b.getActivity();
                        PkgDetailEntity pkgDetailEntity = this.b;
                        jsApiGetPhoneNumber.a = new MantoUserInfoAuthDialog(activity, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new C0198a());
                        JsApiGetPhoneNumber.this.a.show();
                        return;
                    }
                    bundle = this.a;
                    str = "fail: internal error";
                } else {
                    bundle = this.a;
                    str = "fail: pkg not found";
                }
                bundle.putString("message", str);
                c.this.d.onFailed(this.a);
            }
        }

        c(Bundle bundle, MantoCore mantoCore, AuthInfo authInfo, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = mantoCore;
            this.f6735c = authInfo;
            this.d = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = this.a.getString("appid");
            new Handler(Looper.getMainLooper()).post(new a(com.jingdong.a.k(string, this.a.getString("type")), string));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d extends IMantoHttpListener {
        final /* synthetic */ String a;
        final /* synthetic */ MantoResultCallBack b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bundle f6738c;
        final /* synthetic */ Bundle d;

        d(JsApiGetPhoneNumber jsApiGetPhoneNumber, String str, MantoResultCallBack mantoResultCallBack, Bundle bundle, Bundle bundle2) {
            this.a = str;
            this.b = mantoResultCallBack;
            this.f6738c = bundle;
            this.d = bundle2;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            String optString = optJSONObject != null ? optJSONObject.optString("msg") : "";
            this.d.putString("message", "fail:" + optString);
            this.b.onFailed(this.d);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    this.b.onFailed(this.d);
                } else {
                    com.jingdong.a.e(this.a, "scope.1");
                    this.b.onSuccess(this.f6738c);
                }
            } catch (Exception unused) {
                this.b.onFailed(this.d);
            }
        }
    }

    private void a(MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        com.jingdong.a.f().diskIO().execute(new c(bundle, mantoCore, new AuthInfo("scope.1", "1", "\u83b7\u53d6\u624b\u673a\u53f7\u7801", bundle.getString("dialogContent"), AuthorizeManager.State.NONE), mantoResultCallBack));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new MantoReqUpdateOtherAuthSetting(str, "1", 1), new d(this, str, mantoResultCallBack, new Bundle(1), bundle));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "phoneNumber";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String str2;
        String string = bundle.getString("appid");
        if (!"getPhoneNumber".equals(str)) {
            if ("native_getPhoneNumber".equals(str)) {
                a(mantoCore, bundle, mantoResultCallBack);
                return;
            }
            return;
        }
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        Bundle bundle2 = new Bundle(2);
        if (iLogin == null) {
            str2 = "ILogin not registered!";
            MantoLog.e("getPhoneNumber", "ILogin not registered!");
        } else if (iLogin.hasLogin()) {
            MantoJDHttpHandler.commit(new com.jd.manto.jdext.plus.a(bundle.getString("appid"), "1"), new b(this, new a(this, bundle2, mantoResultCallBack, bundle), string));
            return;
        } else {
            str2 = "Not login!";
        }
        bundle2.putString("message", str2);
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        if ("getPhoneNumber".equals(str)) {
            bundle.putString("json", jSONObject != null ? jSONObject.toString() : "{}");
        } else if ("native_getPhoneNumber".equals(str)) {
            bundle.putString("dialogContent", jSONObject.optString("dialogContent"));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getPhoneNumber", 239, 0));
        list.add(new JsApiMethod("native_getPhoneNumber", 240, 1));
    }
}
