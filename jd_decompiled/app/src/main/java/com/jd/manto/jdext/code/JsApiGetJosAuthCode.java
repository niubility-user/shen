package com.jd.manto.jdext.code;

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
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoUserInfoAuthDialog;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.widget.input.InputUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiGetJosAuthCode extends AbstractMantoModule {
    private MantoAuthDialog a;

    /* loaded from: classes17.dex */
    class a implements ILogin.CallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        a(JsApiGetJosAuthCode jsApiGetJosAuthCode, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = bundle;
            this.b = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.CallBack
        public void onFailure() {
            this.b.onFailed(null);
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.CallBack
        public void onSuccess() {
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "authConfirmCode");
            this.b.onSuccess(this.a);
        }
    }

    /* loaded from: classes17.dex */
    class b implements AuthorizeCallBack {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6726c;
        final /* synthetic */ Bundle d;

        b(String str, String str2, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = str;
            this.b = str2;
            this.f6726c = mantoResultCallBack;
            this.d = bundle;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            JsApiGetJosAuthCode.this.a(this.a, this.b, this.f6726c);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            this.d.putString("authInfo", AuthInfo.getAuthInfoString(authInfo));
            this.d.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "nativeAuthCodeConfirm");
            this.f6726c.onSuccess(this.d);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.f6726c.onFailed(null);
        }
    }

    /* loaded from: classes17.dex */
    class c implements AuthorizeCallBack {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6728c;

        c(String str, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = str2;
            this.f6728c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            JsApiGetJosAuthCode.this.a(this.a, this.b, this.f6728c);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.f6728c.onFailed(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6729c;

        /* loaded from: classes17.dex */
        class a extends IMantoHttpListener {
            a() {
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                String str;
                super.onError(jSONObject, th);
                MantoLog.d("JsApiJosGetAuthCode", "" + jSONObject.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("errors");
                if (optJSONObject != null) {
                    str = "onError: " + optJSONObject.optString("msg");
                } else {
                    str = "onError: " + th;
                }
                MantoLog.e("JsApiJosGetAuthCode", str);
                d.this.f6729c.onFailed(null);
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                try {
                    MantoLog.d("JsApiGetJosAuthCode", jSONObject.toString());
                    String optString = jSONObject.optString("code");
                    if (jSONObject.isNull("data") || TextUtils.isEmpty(optString) || !optString.equals("0")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("getAuthCode error: ");
                        sb.append(String.format("{code:%s, msg:%s}", optString, jSONObject.optString("msg")));
                        MantoLog.d(sb.toString(), new Object[0]);
                        Bundle bundle = new Bundle();
                        bundle.putString("errCode", jSONObject.optString("errCode"));
                        bundle.putString("message", jSONObject.optString("errMsg"));
                        d.this.f6729c.onFailed(bundle);
                    } else {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("code", jSONObject.optString("data"));
                        bundle2.putString("errCode", "0");
                        d.this.f6729c.onSuccess(bundle2);
                    }
                } catch (Exception unused) {
                    d.this.f6729c.onFailed(null);
                }
            }
        }

        d(JsApiGetJosAuthCode jsApiGetJosAuthCode, String str, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = str2;
            this.f6729c = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            String[] split;
            String str = "";
            PkgDetailEntity k2 = com.jingdong.a.k(this.a, String.valueOf(this.b));
            if (k2 == null) {
                this.f6729c.onFailed(null);
                return;
            }
            String str2 = k2.appId;
            try {
                if (!TextUtils.isEmpty(k2.domains) && (split = k2.domains.split("@,@")) != null && split.length > 0) {
                    str = split[0];
                }
            } catch (Exception unused) {
            }
            MantoJDHttpHandler.commit(new com.jd.manto.jdext.code.a(str2, str), new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6730c;
        final /* synthetic */ Activity d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f6731e;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;

            /* renamed from: com.jd.manto.jdext.code.JsApiGetJosAuthCode$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class C0197a implements MantoAuthDialog.Callback {
                C0197a() {
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    Bundle bundle = new Bundle();
                    bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "authConfirmAfter");
                    e.this.f6730c.onSuccess(bundle);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    e.this.f6730c.onFailed(null);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    e.this.f6730c.onFailed(null);
                }
            }

            a(PkgDetailEntity pkgDetailEntity) {
                this.a = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                Activity activity = e.this.d;
                if (activity == null || activity.isFinishing()) {
                    e.this.f6730c.onFailed(null);
                    return;
                }
                InputUtil.hideVKB(e.this.d);
                if (JsApiGetJosAuthCode.this.a != null && JsApiGetJosAuthCode.this.a.isShowing() && !e.this.d.isFinishing()) {
                    JsApiGetJosAuthCode.this.a.cancel();
                    JsApiGetJosAuthCode.this.a = null;
                }
                LinkedList linkedList = new LinkedList();
                linkedList.add(AuthInfo.getAuthInfo(e.this.f6731e));
                JsApiGetJosAuthCode jsApiGetJosAuthCode = JsApiGetJosAuthCode.this;
                Activity activity2 = e.this.d;
                PkgDetailEntity pkgDetailEntity = this.a;
                jsApiGetJosAuthCode.a = new MantoUserInfoAuthDialog(activity2, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new C0197a());
                JsApiGetJosAuthCode.this.a.show();
            }
        }

        e(String str, String str2, MantoResultCallBack mantoResultCallBack, Activity activity, String str3) {
            this.a = str;
            this.b = str2;
            this.f6730c = mantoResultCallBack;
            this.d = activity;
            this.f6731e = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity k2 = com.jingdong.a.k(this.a, String.valueOf(this.b));
            if (k2 == null) {
                this.f6730c.onFailed(null);
            } else {
                new Handler(Looper.getMainLooper()).post(new a(k2));
            }
        }
    }

    private void a(Activity activity, String str, String str2, String str3, MantoResultCallBack mantoResultCallBack) {
        com.jingdong.a.f().diskIO().execute(new e(str, str3, mantoResultCallBack, activity, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, MantoResultCallBack mantoResultCallBack) {
        MantoLog.d("JsApiJosGetAuthCode", "loginConfirm");
        com.jingdong.a.f().diskIO().execute(new d(this, str, str2, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiGetJosAuthCode";
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
        String string = bundle.getString("appid");
        String string2 = bundle.getString("type");
        if ("getAuthCode".equals(str)) {
            MantoLog.d("JsApiJosGetAuthCode", "getAuthCode");
            if (iLogin.hasLogin()) {
                bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "authConfirmCode");
                mantoResultCallBack.onSuccess(bundle2);
                return;
            }
            String string3 = bundle.getString("processName");
            Bundle bundle3 = new Bundle();
            bundle3.putString("appId", string);
            bundle3.putString("debugType", string2);
            iLogin.doLogin(string3, bundle3, new a(this, bundle2, mantoResultCallBack));
        } else if ("authConfirmCode".equals(str)) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add("scope.userInfo");
            AuthorizeManager.checkAuth(string, arrayList, new b(string, string2, mantoResultCallBack, bundle2));
        } else if ("nativeAuthCodeConfirm".equals(str)) {
            a(mantoCore != null ? mantoCore.getActivity() : null, string, bundle.getString("authInfo"), string2, mantoResultCallBack);
        } else if ("authConfirmAfter".equals(str)) {
            AuthorizeManager.doAuth(string, "scope.userInfo", AuthorizeManager.State.ACCEPT, new c(string, string2, mantoResultCallBack));
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if ("getAuthCode".equals(str)) {
            bundle.putString("processName", MantoProcessUtil.getProcessName());
        } else if ("nativeAuthCodeConfirm".equals(str)) {
            bundle.putString("authInfo", jSONObject.optString("authInfo"));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getAuthCode", 54, 0));
        list.add(new JsApiMethod("authConfirmCode", 503, 0));
        list.add(new JsApiMethod("nativeAuthCodeConfirm", 504, 1));
        list.add(new JsApiMethod("authConfirmAfter", 505, 0));
    }
}
