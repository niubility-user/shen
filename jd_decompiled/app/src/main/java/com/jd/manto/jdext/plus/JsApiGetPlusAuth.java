package com.jd.manto.jdext.plus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
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
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.widget.input.InputUtil;
import com.tencent.connect.common.Constants;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiGetPlusAuth extends AbstractMantoModule {
    private MantoAuthDialog a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ String b;

        a(MantoResultCallBack mantoResultCallBack, String str) {
            this.a = mantoResultCallBack;
            this.b = str;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            String optString = optJSONObject != null ? optJSONObject.optString("msg") : "";
            Bundle bundle = new Bundle();
            bundle.putString("message", optString);
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            MantoResultCallBack mantoResultCallBack;
            Bundle bundle;
            try {
                String optString = jSONObject.optString("code", "0");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("message", "error:" + optString);
                    bundle2.putString("errorMessage", jSONObject.optString("errorMessage"));
                    mantoResultCallBack = this.a;
                    bundle = bundle2;
                } else {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject == null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("message", "error:" + optString + DYConstants.DY_REGEX_COMMA + jSONObject.optString("msg"));
                        this.a.onFailed(bundle3);
                        return;
                    }
                    String optString2 = optJSONObject.optString(Constants.PARAM_SCOPE);
                    if ("0".equals(optString2)) {
                        Bundle bundle4 = new Bundle();
                        bundle4.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "plusAuthConfirm");
                        this.a.onSuccess(bundle4);
                        return;
                    } else if ("1".equals(optString2)) {
                        com.jingdong.a.e(this.b, "scope.0");
                        JsApiGetPlusAuth.this.a(this.a, this.b);
                        return;
                    } else {
                        bundle = new Bundle();
                        bundle.putString("message", "error:" + optString + DYConstants.DY_REGEX_COMMA + jSONObject.optString("msg"));
                        mantoResultCallBack = this.a;
                    }
                }
                mantoResultCallBack.onFailed(bundle);
            } catch (Exception e2) {
                Bundle bundle5 = new Bundle();
                bundle5.putString("message", "error:" + e2.getMessage());
                this.a.onFailed(bundle5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        b(JsApiGetPlusAuth jsApiGetPlusAuth, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            String optString = optJSONObject != null ? optJSONObject.optString("msg") : "";
            Bundle bundle = new Bundle();
            bundle.putString("message", optString);
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "error:" + optString);
                    bundle.putString("errorMessage", jSONObject.optString("errorMessage"));
                    this.a.onFailed(bundle);
                } else {
                    String optString2 = jSONObject.optString("data");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("result", optString2);
                    this.a.onSuccess(bundle2);
                }
            } catch (Exception e2) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "error:" + e2.getMessage());
                this.a.onFailed(bundle3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c extends IMantoHttpListener {
        final /* synthetic */ String a;
        final /* synthetic */ MantoResultCallBack b;

        c(JsApiGetPlusAuth jsApiGetPlusAuth, String str, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            String optString = optJSONObject != null ? optJSONObject.optString("msg") : "";
            Bundle bundle = new Bundle();
            bundle.putString("message", optString);
            this.b.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "error:" + optString);
                    bundle.putString("errorMessage", jSONObject.optString("errorMessage"));
                    this.b.onFailed(bundle);
                } else {
                    com.jingdong.a.e(this.a, "scope.0");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "realGetPlusInfo");
                    this.b.onSuccess(bundle2);
                }
            } catch (Exception e2) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "error:" + e2.getMessage());
                this.b.onFailed(bundle3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6740c;
        final /* synthetic */ MantoCore d;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;

            /* renamed from: com.jd.manto.jdext.plus.JsApiGetPlusAuth$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class C0199a implements MantoAuthDialog.Callback {
                final /* synthetic */ Activity a;
                final /* synthetic */ AuthInfo b;

                C0199a(Activity activity, AuthInfo authInfo) {
                    this.a = activity;
                    this.b = authInfo;
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    d dVar = d.this;
                    JsApiGetPlusAuth.this.b(dVar.f6740c, dVar.a);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("vapp_type", d.this.b);
                        jSONObject.put("flag", "1");
                    } catch (Exception e2) {
                        MantoLog.e(DYConstants.DY_TRACK, e2);
                    }
                    MantoTrack.sendCommonDataWithExt(this.a, "PLUS\u6388\u6743", "applets_information_about_plus", d.this.a, "PLUS\u6388\u6743", "", jSONObject.toString(), "", null);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    com.jingdong.a.B(d.this.a, this.b);
                    d.this.f6740c.onFailed(null);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("vapp_type", d.this.b);
                        jSONObject.put("flag", "0");
                    } catch (Exception e2) {
                        MantoLog.e(DYConstants.DY_TRACK, e2);
                    }
                    MantoTrack.sendCommonDataWithExt(this.a, "PLUS\u6388\u6743", "applets_information_about_plus", d.this.a, "PLUS\u6388\u6743", "", jSONObject.toString(), "", null);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    com.jingdong.a.B(d.this.a, this.b);
                    d.this.f6740c.onFailed(null);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("vapp_type", d.this.b);
                        jSONObject.put("flag", "0");
                    } catch (Exception e2) {
                        MantoLog.e(DYConstants.DY_TRACK, e2);
                    }
                    MantoTrack.sendCommonDataWithExt(this.a, "PLUS\u6388\u6743", "applets_information_about_plus", d.this.a, "PLUS\u6388\u6743", "", jSONObject.toString(), "", null);
                }
            }

            a(PkgDetailEntity pkgDetailEntity) {
                this.a = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCore mantoCore = d.this.d;
                if (mantoCore == null || mantoCore.isFinishing()) {
                    d.this.f6740c.onFailed(null);
                    return;
                }
                Activity activity = d.this.d.getActivity();
                if (activity == null) {
                    d.this.f6740c.onFailed(null);
                    return;
                }
                InputUtil.hideVKB(activity);
                if (JsApiGetPlusAuth.this.a != null && JsApiGetPlusAuth.this.a.isShowing() && !activity.isFinishing()) {
                    JsApiGetPlusAuth.this.a.cancel();
                    JsApiGetPlusAuth.this.a = null;
                }
                LinkedList linkedList = new LinkedList();
                AuthInfo authInfo = new AuthInfo("scope.0", "0", "Plus\u4fe1\u606f", "\u83b7\u53d6\u4f60\u7684PLUS\u4f1a\u5458\u8eab\u4efd\u3001\u4eac\u4e1c\u4f1a\u5458\u8eab\u4efd", AuthorizeManager.State.NONE);
                linkedList.add(authInfo);
                JsApiGetPlusAuth jsApiGetPlusAuth = JsApiGetPlusAuth.this;
                PkgDetailEntity pkgDetailEntity = this.a;
                jsApiGetPlusAuth.a = new MantoUserInfoAuthDialog(activity, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new C0199a(activity, authInfo));
                JsApiGetPlusAuth.this.a.show();
            }
        }

        d(String str, String str2, MantoResultCallBack mantoResultCallBack, MantoCore mantoCore) {
            this.a = str;
            this.b = str2;
            this.f6740c = mantoResultCallBack;
            this.d = mantoCore;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity k2 = com.jingdong.a.k(this.a, String.valueOf(this.b));
            if (k2 == null) {
                this.f6740c.onFailed(null);
            } else {
                new Handler(Looper.getMainLooper()).post(new a(k2));
            }
        }
    }

    private void a(MantoCore mantoCore, String str, String str2, MantoResultCallBack mantoResultCallBack) {
        com.jingdong.a.f().diskIO().execute(new d(str, str2, mantoResultCallBack, mantoCore));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(MantoResultCallBack mantoResultCallBack, String str) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.plus.b(str), new b(this, mantoResultCallBack));
    }

    private final void a(String str, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.plus.a(str, "0"), new a(mantoResultCallBack, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b(MantoResultCallBack mantoResultCallBack, String str) {
        MantoJDHttpHandler.commit(new MantoReqUpdateOtherAuthSetting(str, "0", 1), new c(this, str, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "plusModule";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("appid");
        String string2 = bundle.getString("type");
        if ("getPlusInfo".equals(str)) {
            ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
            if (iLogin == null || !iLogin.hasLogin()) {
                mantoResultCallBack.onFailed(null);
            } else {
                a(string, mantoResultCallBack);
            }
        } else if ("plusAuthConfirm".equals(str)) {
            a(mantoCore, string, string2, mantoResultCallBack);
        } else if ("realGetPlusInfo".equals(str)) {
            a(mantoResultCallBack, string);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getPlusInfo", 241, 0));
        list.add(new JsApiMethod("plusAuthConfirm", 241, 1));
        list.add(new JsApiMethod("realGetPlusInfo", 241, 1));
    }
}
