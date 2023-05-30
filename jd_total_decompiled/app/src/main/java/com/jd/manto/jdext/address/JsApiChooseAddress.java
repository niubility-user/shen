package com.jd.manto.jdext.address;

import android.app.Activity;
import android.content.Context;
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
import com.jingdong.manto.sdk.api.IRouter;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoUserInfoAuthDialog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.InputUtil;
import com.tencent.connect.common.Constants;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiChooseAddress extends AbstractMantoModule {
    private MantoUserInfoAuthDialog a;

    /* loaded from: classes17.dex */
    class a implements AuthorizeCallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6720c;

        a(JsApiChooseAddress jsApiChooseAddress, Bundle bundle, Bundle bundle2, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = bundle2;
            this.f6720c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.putString("appid", this.b.getString("appid"));
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "native_openAddress2");
            this.f6720c.onSuccess(this.a);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            this.a.putString("dialogContent", str);
            this.a.putString("appid", this.b.getString("appid"));
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "native_openAddress");
            this.f6720c.onSuccess(this.a);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.a.putString("result", "fail");
            this.a.putString("message", str);
            this.f6720c.onFailed(this.a);
        }
    }

    /* loaded from: classes17.dex */
    class b extends IMantoHttpListener {
        final /* synthetic */ AuthorizeCallBack a;
        final /* synthetic */ String b;

        b(JsApiChooseAddress jsApiChooseAddress, AuthorizeCallBack authorizeCallBack, String str) {
            this.a = authorizeCallBack;
            this.b = str;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            if (optJSONObject == null) {
                this.a.onFailure(th.toString());
                return;
            }
            this.a.onFailure(optJSONObject.optString("msg"));
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    this.a.onFailure("error code");
                } else {
                    String optString2 = jSONObject.optJSONObject("data").optString(Constants.PARAM_SCOPE, "0");
                    if ("0".equals(optString2)) {
                        this.a.onConfirm(new AuthInfo("scope.3", "3", "\u83b7\u53d6\u6536\u8d27\u5730\u5740", "\u83b7\u53d6\u60a8\u6307\u5b9a\u7684\u6536\u8d27\u5730\u5740", AuthorizeManager.State.NONE), "\u83b7\u53d6\u60a8\u6307\u5b9a\u7684\u6536\u8d27\u5730\u5740");
                    } else if ("1".equals(optString2)) {
                        com.jingdong.a.e(this.b, "scope.3");
                        this.a.onAuth();
                    } else {
                        this.a.onFailure(optString);
                    }
                }
            } catch (Throwable th) {
                this.a.onFailure("error " + th.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Activity b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ AuthInfo f6721c;
        final /* synthetic */ MantoResultCallBack d;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            final Bundle a = new Bundle(1);
            final /* synthetic */ PkgDetailEntity b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ String f6723c;

            /* renamed from: com.jd.manto.jdext.address.JsApiChooseAddress$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class C0196a implements MantoAuthDialog.Callback {
                C0196a() {
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    a aVar = a.this;
                    c cVar = c.this;
                    JsApiChooseAddress.this.a(cVar.b, aVar.f6723c, cVar.a, cVar.d);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    a aVar = a.this;
                    com.jingdong.a.B(aVar.f6723c, c.this.f6721c);
                    a.this.a.putString("message", "fail: auth deny");
                    a aVar2 = a.this;
                    c.this.d.onFailed(aVar2.a);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    a aVar = a.this;
                    com.jingdong.a.B(aVar.f6723c, c.this.f6721c);
                    a.this.a.putString("message", "fail: reject");
                    a aVar2 = a.this;
                    c.this.d.onFailed(aVar2.a);
                }
            }

            a(PkgDetailEntity pkgDetailEntity, String str) {
                this.b = pkgDetailEntity;
                this.f6723c = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.b == null) {
                    this.a.putString("message", "fail: pkg not found");
                    c.this.d.onFailed(this.a);
                    return;
                }
                Activity activity = c.this.b;
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                InputUtil.hideVKB(c.this.b);
                if (JsApiChooseAddress.this.a != null && JsApiChooseAddress.this.a.isShowing() && !c.this.b.isFinishing()) {
                    JsApiChooseAddress.this.a.cancel();
                    JsApiChooseAddress.this.a = null;
                }
                LinkedList linkedList = new LinkedList();
                linkedList.add(c.this.f6721c);
                JsApiChooseAddress jsApiChooseAddress = JsApiChooseAddress.this;
                Activity activity2 = c.this.b;
                PkgDetailEntity pkgDetailEntity = this.b;
                jsApiChooseAddress.a = new MantoUserInfoAuthDialog(activity2, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new C0196a());
                JsApiChooseAddress.this.a.show();
            }
        }

        c(Bundle bundle, Activity activity, AuthInfo authInfo, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = activity;
            this.f6721c = authInfo;
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
        final /* synthetic */ Activity b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6724c;
        final /* synthetic */ Bundle d;

        d(String str, Activity activity, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = str;
            this.b = activity;
            this.f6724c = mantoResultCallBack;
            this.d = bundle;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            String optString = optJSONObject != null ? optJSONObject.optString("msg") : "";
            this.d.putString("message", "fail:" + optString);
            this.f6724c.onFailed(this.d);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    this.f6724c.onFailed(this.d);
                } else {
                    com.jingdong.a.e(this.a, "scope.3");
                    JsApiChooseAddress.this.a(this.b, this.f6724c);
                }
            } catch (Throwable unused) {
                this.f6724c.onFailed(this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements IRouter.CallBack {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        e(JsApiChooseAddress jsApiChooseAddress, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            this.b.putInt(IMantoBaseModule.ERROR_CODE, i2);
            if (i2 == -2) {
                this.b.putString("message", "cancel");
            }
            this.a.onFailed(this.b);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            try {
                String string = bundle.getString("result");
                Bundle formatJson = MantoUtils.formatJson(new JSONObject(string));
                formatJson.putString("result", string);
                this.a.onSuccess(formatJson);
            } catch (Throwable unused) {
                this.a.onSuccess(bundle);
            }
        }
    }

    private void a(Activity activity, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        com.jingdong.a.f().diskIO().execute(new c(bundle, activity, new AuthInfo("scope.3", "3", "\u83b7\u53d6\u6536\u8d27\u5730\u5740", bundle.getString("dialogContent"), AuthorizeManager.State.NONE), mantoResultCallBack));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, String str, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new MantoReqUpdateOtherAuthSetting(str, "3", 1), new d(str, activity, mantoResultCallBack, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle(1);
        try {
            IRouter iRouter = (IRouter) com.jingdong.a.n(IRouter.class);
            if (iRouter == null) {
                mantoResultCallBack.onFailed(null);
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", "router://JDCheckoutModule/showCheckoutAddressListPage?sourceMap={\"sourceTag\":\"caaaad49f6874580a13ccc107819f7c7\"}&bussinessType=JDMiniProgramLuxury&platformType=applets");
            iRouter.jumpTo(context, jSONObject, new e(this, mantoResultCallBack, bundle));
        } catch (Throwable th) {
            bundle.putString("message", "" + th.getMessage());
            mantoResultCallBack.onFailed(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JDAddress";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String str2;
        String string = bundle.getString("appid");
        if (!"chooseAddress".equals(str)) {
            if ("native_openAddress".equals(str)) {
                a(mantoCore != null ? mantoCore.getActivity() : null, bundle, mantoResultCallBack);
                return;
            } else if ("native_openAddress2".equals(str)) {
                com.jingdong.a.e(string, "scope.3");
                a(mantoCore != null ? mantoCore.getActivity() : null, mantoResultCallBack);
                return;
            } else {
                return;
            }
        }
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        Bundle bundle2 = new Bundle(2);
        if (iLogin == null) {
            str2 = "ILogin not registered!";
        } else if (iLogin.hasLogin()) {
            MantoJDHttpHandler.commit(new com.jd.manto.jdext.plus.a(bundle.getString("appid"), "3"), new b(this, new a(this, bundle2, bundle, mantoResultCallBack), string));
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
        if ("chooseAddress".equals(str)) {
            bundle.putString("json", jSONObject != null ? jSONObject.toString() : "{}");
        } else if ("native_openAddress".equals(str)) {
            bundle.putString("dialogContent", jSONObject.optString("dialogContent"));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("chooseAddress", 0));
        list.add(new JsApiMethod("native_openAddress", 1));
        list.add(new JsApiMethod("native_openAddress2", 1));
    }
}
