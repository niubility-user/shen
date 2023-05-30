package com.jingdong.manto.m.q0;

import android.app.Activity;
import android.os.Bundle;
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
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoAuthDialogUtils;
import com.jingdong.manto.widget.input.InputUtil;
import com.tencent.connect.common.Constants;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {
    private MantoAuthDialog a;

    /* renamed from: com.jingdong.manto.m.q0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0605a implements AuthorizeCallBack {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        C0605a(a aVar, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.onSuccess(null);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("params", this.b.getString("params"));
            LinkedList linkedList = new LinkedList();
            linkedList.add(AuthInfo.getAuthInfoString(authInfo));
            int size = linkedList.size();
            for (int i2 = 0; i2 < size; i2++) {
                bundle.putString(String.valueOf(i2), (String) linkedList.get(i2));
            }
            bundle.putInt(ApkDownloadTable.FIELD_SIZE, size);
            bundle.putString("permissionTag", str);
            bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "nativeAuth");
            this.a.onSuccess(bundle);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("message", str);
            this.a.onFailed(bundle);
        }
    }

    /* loaded from: classes15.dex */
    class b implements AuthorizeCallBack {
        final /* synthetic */ MantoResultCallBack a;

        b(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.onSuccess(null);
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("message", str);
            this.a.onFailed(bundle);
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f13565c;
        final /* synthetic */ LinkedList d;

        /* renamed from: com.jingdong.manto.m.q0.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0606a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;
            final /* synthetic */ String b;

            /* renamed from: com.jingdong.manto.m.q0.a$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class C0607a implements MantoAuthDialog.Callback {
                final /* synthetic */ Activity a;

                /* renamed from: com.jingdong.manto.m.q0.a$c$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes15.dex */
                class C0608a implements AuthorizeCallBack {
                    C0608a() {
                    }

                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                    public void onAuth() {
                        c.this.b.onSuccess(null);
                    }

                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                    public void onConfirm(AuthInfo authInfo, String str) {
                    }

                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                    public void onFailure(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putString("message", str);
                        c.this.b.onFailed(bundle);
                    }
                }

                C0607a(Activity activity) {
                    this.a = activity;
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    Activity activity = this.a;
                    RunnableC0606a runnableC0606a = RunnableC0606a.this;
                    AuthorizeManager.doDeviceApiAuth(activity, runnableC0606a.b, (AuthInfo) c.this.d.get(0), new C0608a());
                    c cVar = c.this;
                    a.this.a("\u4f4d\u7f6e\u7b49\u6388\u6743-\u5141\u8bb8", "Applets_Allow", ((AuthInfo) cVar.d.get(0)).scope);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "cancel");
                    c.this.b.onFailed(bundle);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", VerifyTracker.EVENT_REJECT);
                    c.this.b.onFailed(bundle);
                    c cVar = c.this;
                    a.this.a("\u4f4d\u7f6e\u7b49\u6388\u6743-\u4e0d\u5141\u8bb8", "Applets_DisAllow", ((AuthInfo) cVar.d.get(0)).scope);
                }
            }

            /* renamed from: com.jingdong.manto.m.q0.a$c$a$b */
            /* loaded from: classes15.dex */
            class b implements MantoAuthDialog.Callback {
                b() {
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onAccept() {
                    Bundle bundle = new Bundle();
                    bundle.putString("params", c.this.a.getString("params"));
                    bundle.putInt(XView2Constants.STATE, AuthorizeManager.State.ACCEPT.value());
                    bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "authConfirm");
                    c.this.b.onSuccess(bundle);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onCancel() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "cancel");
                    c.this.b.onFailed(bundle);
                }

                @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                public void onReject() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", VerifyTracker.EVENT_REJECT);
                    c.this.b.onFailed(bundle);
                }
            }

            RunnableC0606a(PkgDetailEntity pkgDetailEntity, String str) {
                this.a = pkgDetailEntity;
                this.b = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Activity activity = c.this.f13565c.getActivity();
                if (activity.isFinishing()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "activity isFinished");
                    c.this.b.onFailed(bundle);
                    return;
                }
                boolean equals = "devicePermission".equals(c.this.a.getString("permissionTag"));
                InputUtil.hideVKB(activity);
                if (equals) {
                    if (a.this.a != null && a.this.a.isShowing() && !c.this.f13565c.isFinishing()) {
                        a.this.a.cancel();
                        a.this.a = null;
                    }
                    c cVar = c.this;
                    a.this.a = MantoAuthDialogUtils.getDeviceAuthDialog(activity, this.a.name, ((AuthInfo) cVar.d.get(0)).description, new C0607a(activity));
                    a.this.a.show();
                    return;
                }
                if (a.this.a != null && a.this.a.isShowing() && !activity.isFinishing()) {
                    a.this.a.cancel();
                    a.this.a = null;
                }
                c cVar2 = c.this;
                a aVar = a.this;
                LinkedList linkedList = cVar2.d;
                PkgDetailEntity pkgDetailEntity = this.a;
                aVar.a = MantoAuthDialogUtils.getUserInfoAuthDialog(activity, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, new b());
                a.this.a.show();
            }
        }

        c(Bundle bundle, MantoResultCallBack mantoResultCallBack, MantoCore mantoCore, LinkedList linkedList) {
            this.a = bundle;
            this.b = mantoResultCallBack;
            this.f13565c = mantoCore;
            this.d = linkedList;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = this.a.getString("appid");
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(string, this.a.getString("type"));
            if (c2 != null) {
                com.jingdong.manto.sdk.thread.a.a(new RunnableC0606a(c2, string));
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("message", "pkg detail is null");
            this.b.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        if (str3.contains("userLocation") || str3.contains("camera")) {
            return;
        }
        str3.contains("writePhotosAlbum");
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "AuthMethod";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        ILogin iLogin;
        ILogin iLogin2;
        if ("authorize".equals(str)) {
            LinkedList linkedList = new LinkedList();
            try {
                JSONArray jSONArray = new JSONArray(new JSONObject(bundle.getString("params")).optString(Constants.PARAM_SCOPE));
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    linkedList.add(jSONArray.optString(i2));
                }
                if (AuthorizeManager.isDevicePermission((String) linkedList.get(0)) || ((iLogin2 = (ILogin) com.jingdong.a.n(ILogin.class)) != null && iLogin2.hasLogin())) {
                    AuthorizeManager.checkAuth(bundle.getString("appid"), linkedList, new C0605a(this, mantoResultCallBack, bundle));
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "no loginImpl or not login");
                mantoResultCallBack.onFailed(bundle2);
            } catch (JSONException e2) {
                e2.printStackTrace();
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "Exception:" + e2.getMessage());
                mantoResultCallBack.onFailed(bundle3);
            }
        } else if (!"authConfirm".equals(str)) {
            if ("nativeAuth".equals(str)) {
                LinkedList linkedList2 = new LinkedList();
                int i3 = bundle.getInt(ApkDownloadTable.FIELD_SIZE);
                for (int i4 = 0; i4 < i3; i4++) {
                    try {
                        linkedList2.add(AuthInfo.getAuthInfo(new JSONObject(bundle.getString("params")).optString(String.valueOf(i4))));
                    } catch (Exception e3) {
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("message", e3.getMessage());
                        mantoResultCallBack.onFailed(bundle4);
                        return;
                    }
                }
                if (linkedList2.size() > 0) {
                    com.jingdong.manto.b.d().diskIO().execute(new c(bundle, mantoResultCallBack, mantoCore, linkedList2));
                }
            }
        } else {
            LinkedList linkedList3 = new LinkedList();
            try {
                JSONArray jSONArray2 = new JSONArray(new JSONObject(new JSONObject(new JSONObject(bundle.getString("params")).optString("params")).optString("params")).optString(Constants.PARAM_SCOPE));
                for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                    linkedList3.add(jSONArray2.optString(i5));
                }
                if (AuthorizeManager.isDevicePermission((String) linkedList3.get(0)) || ((iLogin = (ILogin) com.jingdong.a.n(ILogin.class)) != null && iLogin.hasLogin())) {
                    AuthorizeManager.doAuth(bundle.getString("appid"), linkedList3, AuthorizeManager.State.get(bundle.getInt(XView2Constants.STATE)), new b(this, mantoResultCallBack));
                    return;
                }
                Bundle bundle5 = new Bundle();
                bundle5.putString("message", "no loginImpl or not login");
                mantoResultCallBack.onFailed(bundle5);
            } catch (JSONException e4) {
                e4.printStackTrace();
                Bundle bundle6 = new Bundle();
                bundle6.putString("message", e4.getMessage());
                mantoResultCallBack.onFailed(bundle6);
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if ("authorize".equals(str)) {
            bundle.putString("params", jSONObject.toString());
        } else if ("nativeAuth".equals(str)) {
            bundle.putString("params", jSONObject.toString());
            bundle.putInt(ApkDownloadTable.FIELD_SIZE, jSONObject.optInt(ApkDownloadTable.FIELD_SIZE));
            bundle.putString("permissionTag", jSONObject.optString("permissionTag"));
        } else if ("authConfirm".equals(str)) {
            bundle.putString("params", jSONObject.toString());
            bundle.putInt(XView2Constants.STATE, jSONObject.optInt(XView2Constants.STATE));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("authorize", 0));
        list.add(new JsApiMethod("nativeAuth", 1));
        list.add(new JsApiMethod("authConfirm", 0));
    }
}
