package com.jingdong.manto.m.q0;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.apkcenter.ApkDownloadTable;
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
import com.jingdong.manto.network.mantorequests.o;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoAuthDialogUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.input.InputUtil;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends AbstractMantoModule {
    private MantoAuthDialog a;

    /* loaded from: classes15.dex */
    class a implements AuthorizeCallBack {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13571c;
        final /* synthetic */ Bundle d;

        a(String str, String str2, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = str;
            this.b = str2;
            this.f13571c = mantoResultCallBack;
            this.d = bundle;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            c.this.a(this.a, this.b, this.f13571c);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("params", this.d.getString("params"));
            LinkedList linkedList = new LinkedList();
            linkedList.add(AuthInfo.getAuthInfoString(authInfo));
            int size = linkedList.size();
            for (int i2 = 0; i2 < size; i2++) {
                bundle.putString(String.valueOf(i2), (String) linkedList.get(i2));
            }
            bundle.putInt(ApkDownloadTable.FIELD_SIZE, size);
            bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "nativeJDData");
            this.f13571c.onSuccess(bundle);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("message", str);
            this.f13571c.onFailed(bundle);
        }
    }

    /* loaded from: classes15.dex */
    class b implements AuthorizeCallBack {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13573c;

        b(String str, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = str2;
            this.f13573c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            c.this.a(this.a, this.b, this.f13573c);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("message", str);
            this.f13573c.onFailed(bundle);
        }
    }

    /* renamed from: com.jingdong.manto.m.q0.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0610c implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f13574c;
        final /* synthetic */ LinkedList d;

        /* renamed from: com.jingdong.manto.m.q0.c$c$a */
        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;

            /* renamed from: com.jingdong.manto.m.q0.c$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class C0611a implements MantoAuthDialog.Callback {
                C0611a() {
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    Bundle bundle = new Bundle();
                    bundle.putString("params", RunnableC0610c.this.a.getString("params"));
                    bundle.putInt(XView2Constants.STATE, AuthorizeManager.State.ACCEPT.value());
                    bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "authConfirmOperateJDData");
                    RunnableC0610c.this.b.onSuccess(bundle);
                    c.this.a("\u670d\u52a1\u6388\u6743-\u540c\u610f\uff08\u7528\u6237\u4fe1\u606f\uff09", "Applets_UserInfo_Agree");
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "cancel");
                    RunnableC0610c.this.b.onFailed(bundle);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", VerifyTracker.EVENT_REJECT);
                    RunnableC0610c.this.b.onFailed(bundle);
                    c.this.a("\u670d\u52a1\u6388\u6743-\u540c\u610f\uff08\u7528\u6237\u4fe1\u606f\uff09", "Applets_UserInfo_DisAgree");
                }
            }

            a(PkgDetailEntity pkgDetailEntity) {
                this.a = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Activity activity = RunnableC0610c.this.f13574c.getActivity();
                if (activity.isFinishing()) {
                    RunnableC0610c.this.b.onFailed(null);
                    return;
                }
                InputUtil.hideVKB(activity);
                if (c.this.a != null && c.this.a.isShowing() && !activity.isFinishing()) {
                    c.this.a.cancel();
                    c.this.a = null;
                }
                RunnableC0610c runnableC0610c = RunnableC0610c.this;
                c cVar = c.this;
                LinkedList linkedList = runnableC0610c.d;
                PkgDetailEntity pkgDetailEntity = this.a;
                cVar.a = MantoAuthDialogUtils.getUserInfoAuthDialog(activity, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new C0611a());
                c.this.a.show();
            }
        }

        RunnableC0610c(Bundle bundle, MantoResultCallBack mantoResultCallBack, MantoCore mantoCore, LinkedList linkedList) {
            this.a = bundle;
            this.b = mantoResultCallBack;
            this.f13574c = mantoCore;
            this.d = linkedList;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(this.a.getString("appid"), this.a.getString("type"));
            if (c2 != null) {
                com.jingdong.manto.sdk.thread.a.a(new a(c2));
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("message", "PkgDetail is null");
            this.b.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        d(c cVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            JSONObject optJSONObject = jSONObject.optJSONObject("errors");
            if (optJSONObject == null) {
                MantoLog.e("JsApiOperateJDData", "onError: " + th);
                Bundle bundle = new Bundle();
                bundle.putString("message", "onError: " + th.getMessage());
                this.a.onFailed(bundle);
                return;
            }
            String optString = optJSONObject.optString("msg");
            MantoLog.e("JsApiOperateJDData", "onError: " + optString);
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "onError: " + optString);
            this.a.onFailed(bundle2);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString) || !optString.equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "code is: " + optString);
                    this.a.onFailed(bundle);
                } else {
                    String optString2 = jSONObject.optString("data");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("data", optString2);
                    this.a.onSuccess(bundle2);
                }
            } catch (Exception e2) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "Exception: " + e2.getMessage());
                this.a.onFailed(bundle3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new o(str, str2), new d(this, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "operateJDData";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("appid");
        String string2 = bundle.getString("params");
        if ("operateJDData".equals(str)) {
            ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
            if (iLogin != null && iLogin.hasLogin()) {
                AuthorizeManager.checkAuth(string, "scope.userInfo", new a(string, string2, mantoResultCallBack, bundle));
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "no loginImpl or not login");
            mantoResultCallBack.onFailed(bundle2);
        } else if ("authConfirmOperateJDData".equals(str)) {
            AuthorizeManager.doAuth(bundle.getString("appid"), "scope.userInfo", AuthorizeManager.State.get(bundle.getInt(XView2Constants.STATE)), new b(string, string2, mantoResultCallBack));
        } else if ("nativeJDData".equals(str)) {
            LinkedList linkedList = new LinkedList();
            int i2 = bundle.getInt(ApkDownloadTable.FIELD_SIZE);
            for (int i3 = 0; i3 < i2; i3++) {
                try {
                    linkedList.add(AuthInfo.getAuthInfo(new JSONObject(string2).optString(String.valueOf(i3))));
                } catch (Exception e2) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("message", e2.getMessage());
                    mantoResultCallBack.onFailed(bundle3);
                    return;
                }
            }
            if (linkedList.size() > 0) {
                com.jingdong.manto.b.d().diskIO().execute(new RunnableC0610c(bundle, mantoResultCallBack, mantoCore, linkedList));
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if (!"operateJDData".equals(str)) {
            if ("nativeJDData".equals(str)) {
                bundle.putString("params", jSONObject.toString());
                bundle.putInt(ApkDownloadTable.FIELD_SIZE, jSONObject.optInt(ApkDownloadTable.FIELD_SIZE));
            } else if ("authConfirmOperateJDData".equals(str)) {
                bundle.putInt(XView2Constants.STATE, jSONObject.optInt(XView2Constants.STATE));
            }
            return bundle;
        }
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("operateJDData", 0));
        list.add(new JsApiMethod("nativeJDData", 1));
        list.add(new JsApiMethod("authConfirmOperateJDData", 0));
    }
}
