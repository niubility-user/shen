package com.jd.manto.d.d0;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.manto.d.d0.c;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoAuthDialogUtils;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.widget.input.InputUtil;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.d.d0.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0186a implements AuthorizeCallBack {
        final /* synthetic */ g a;
        final /* synthetic */ CountDownLatch b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ g f6634c;

        C0186a(a aVar, g gVar, CountDownLatch countDownLatch, g gVar2) {
            this.a = gVar;
            this.b = countDownLatch;
            this.f6634c = gVar2;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.b = true;
            this.b.countDown();
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            g gVar = this.a;
            gVar.b = false;
            gVar.f6649c = authInfo;
            this.b.countDown();
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.f6634c.b = false;
            this.b.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements AuthorizeCallBack {
        final /* synthetic */ g a;
        final /* synthetic */ CountDownLatch b;

        b(a aVar, g gVar, CountDownLatch countDownLatch) {
            this.a = gVar;
            this.b = countDownLatch;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            this.a.b = true;
            this.b.countDown();
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            g gVar = this.a;
            gVar.b = false;
            gVar.f6649c = authInfo;
            this.b.countDown();
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.a.b = false;
            this.b.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f6635g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6636h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ PkgDetailEntity f6637i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f6638j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f6639k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f6640l;

        /* renamed from: m  reason: collision with root package name */
        final /* synthetic */ g f6641m;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ g f6642n;
        final /* synthetic */ Bundle o;

        /* renamed from: com.jd.manto.d.d0.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class C0187a implements MantoAuthDialog.Callback {
            C0187a() {
            }

            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
            public void onAccept() {
                c cVar = c.this;
                a.this.c(cVar.f6635g, cVar.f6638j, cVar.f6639k, cVar.f6640l, cVar.f6641m, cVar.f6642n, cVar.o, cVar.f6636h);
            }

            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
            public void onCancel() {
                Bundle bundle = new Bundle();
                bundle.putString("message", "cancel auth");
                c.this.f6636h.onFailed(bundle);
            }

            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
            public void onReject() {
                Bundle bundle = new Bundle();
                bundle.putString("message", "reject auth");
                c.this.f6636h.onFailed(bundle);
            }
        }

        c(Activity activity, MantoResultCallBack mantoResultCallBack, PkgDetailEntity pkgDetailEntity, String str, String str2, String str3, g gVar, g gVar2, Bundle bundle) {
            this.f6635g = activity;
            this.f6636h = mantoResultCallBack;
            this.f6637i = pkgDetailEntity;
            this.f6638j = str;
            this.f6639k = str2;
            this.f6640l = str3;
            this.f6641m = gVar;
            this.f6642n = gVar2;
            this.o = bundle;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.f6635g.isFinishing()) {
                Bundle bundle = new Bundle();
                bundle.putString("message", "activity isFinished");
                this.f6636h.onFailed(bundle);
                return;
            }
            InputUtil.hideVKB(this.f6635g);
            MantoAuthDialogUtils.getDeviceAuthDialog(this.f6635g, this.f6637i.name, "\u4f7f\u7528\u60a8\u7684\u5f55\u97f3\u3001\u6444\u50cf\u5934\u529f\u80fd", new C0187a()).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements IPermission.PermissionCallBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6643c;
        final /* synthetic */ MantoResultCallBack d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ g f6644e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f6645f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g f6646g;

        d(a aVar, Bundle bundle, String str, String str2, MantoResultCallBack mantoResultCallBack, g gVar, String str3, g gVar2) {
            this.a = bundle;
            this.b = str;
            this.f6643c = str2;
            this.d = mantoResultCallBack;
            this.f6644e = gVar;
            this.f6645f = str3;
            this.f6646g = gVar2;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onDenied() {
            this.a.putString("error", "has no recode or camera permission");
            this.d.onFailed(this.a);
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onGranted() {
            this.a.putString("pin", this.b);
            this.a.putString("rtcAppId", this.f6643c);
            this.a.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "registerRtcServerInstall");
            this.d.onSuccess(this.a);
            g gVar = this.f6644e;
            if (gVar != null && gVar.f6649c != null) {
                AuthorizeManager.updateAuth(this.f6645f, gVar.a, AuthorizeManager.State.ACCEPT);
            }
            g gVar2 = this.f6646g;
            if (gVar2 == null || gVar2.f6649c == null) {
                return;
            }
            AuthorizeManager.updateAuth(this.f6645f, gVar2.a, AuthorizeManager.State.ACCEPT);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements c.e {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6647c;

        e(a aVar, String str, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = str2;
            this.f6647c = mantoResultCallBack;
        }

        @Override // com.jd.manto.d.d0.c.e
        public void failed() {
            Bundle bundle = new Bundle();
            bundle.putString("message", "install rtc plugin failed");
            this.f6647c.onFailed(bundle);
        }

        @Override // com.jd.manto.d.d0.c.e
        public void success() {
            Bundle bundle = new Bundle();
            bundle.putString("pin", this.a);
            bundle.putString("rtcAppId", this.b);
            bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "registerRtcServerNative");
            this.f6647c.onSuccess(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements c.f {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f6648c;

        f(MantoResultCallBack mantoResultCallBack, Bundle bundle, MantoCore mantoCore) {
            this.a = mantoResultCallBack;
            this.b = bundle;
            this.f6648c = mantoCore;
        }

        @Override // com.jd.manto.d.d0.c.f
        public void a() {
            this.a.onSuccess(this.b);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void b(String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("conferenceId", str);
                jSONObject.put("pin", str2);
                jSONObject.put("name", str3);
                jSONObject.put("avatar", str4);
                if (str5 == null) {
                    str5 = "";
                }
                jSONObject.put("appId", str5);
                jSONObject.put("isVideoType", z);
                if (str6 == null) {
                    str6 = "";
                }
                jSONObject.put("userData", str6);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a.this.dispatchEvent(this.f6648c, "onRtcCalling", jSONObject, 0);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void c(String str) {
            this.b.putString("message", str);
            this.a.onFailed(this.b);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void d() {
            this.b.putString("message", "pin has registered");
            this.a.onSuccess(this.b);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void onRtcCamera(boolean z, boolean z2, boolean z3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("isInit", z);
                jSONObject.put("isRemote", z2);
                jSONObject.put("isShared", z3);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a.this.dispatchEvent(this.f6648c, "onCameraStateChanged", jSONObject, 0);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void onRtcCreate(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("conferenceId", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a.this.dispatchEvent(this.f6648c, "onRtcCreated", jSONObject, 0);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void onRtcLeave(boolean z) {
            a.this.dispatchEvent(this.f6648c, "onRtcHungUp", null, 0);
        }

        @Override // com.jd.manto.d.d0.c.f
        public void onRtcStart(boolean z) {
            a.this.dispatchEvent(this.f6648c, "onRtcConnected", null, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class g {
        String a;
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        AuthInfo f6649c;

        g(a aVar) {
        }
    }

    private void b(Activity activity, String str, String str2, String str3, String str4, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        g gVar = new g(this);
        gVar.a = "scope.record";
        g gVar2 = new g(this);
        gVar2.a = "scope.camera";
        CountDownLatch countDownLatch = new CountDownLatch(2);
        AuthorizeManager.checkAuth(str, gVar.a, new C0186a(this, gVar, countDownLatch, gVar2));
        AuthorizeManager.checkAuth(str, gVar2.a, new b(this, gVar2, countDownLatch));
        try {
            countDownLatch.await(2L, TimeUnit.SECONDS);
        } catch (Throwable unused) {
        }
        MantoPermission.hasPermission("android.permission.RECORD_AUDIO");
        MantoPermission.hasPermission("android.permission.CAMERA");
        if (gVar.b && gVar2.b) {
            c(activity, str4, str, str2, gVar, gVar2, bundle, mantoResultCallBack);
        } else {
            f(activity, str4, str, str2, str3, gVar, gVar2, bundle, mantoResultCallBack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Activity activity, String str, String str2, String str3, g gVar, g gVar2, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String[] strArr = {"android.permission.RECORD_AUDIO", "android.permission.CAMERA"};
        if (Build.VERSION.SDK_INT >= 31) {
            strArr = new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA", "android.permission.BLUETOOTH_CONNECT"};
        }
        String[] strArr2 = strArr;
        if (MantoPermission.hasPermissions(strArr2)) {
            bundle.putString("pin", str);
            bundle.putString("rtcAppId", str3);
            bundle.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "registerRtcServerInstall");
            mantoResultCallBack.onSuccess(bundle);
            if (gVar != null && gVar.f6649c != null) {
                AuthorizeManager.updateAuth(str2, gVar.a, AuthorizeManager.State.ACCEPT);
            }
            if (gVar2 == null || gVar2.f6649c == null) {
                return;
            }
            AuthorizeManager.updateAuth(str2, gVar2.a, AuthorizeManager.State.ACCEPT);
            return;
        }
        MantoPermission.requestPermissions(activity, strArr2, new d(this, bundle, str, str3, mantoResultCallBack, gVar, str2, gVar2));
    }

    private void d(String str, String str2, MantoResultCallBack mantoResultCallBack) {
        com.jd.manto.d.d0.c.g(new e(this, str, str2, mantoResultCallBack));
    }

    private void e(MantoCore mantoCore, String str, String str2, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        com.jd.manto.d.d0.c.l(mantoCore.getActivity(), str, str2, new f(mantoResultCallBack, bundle, mantoCore));
    }

    private void f(Activity activity, String str, String str2, String str3, String str4, g gVar, g gVar2, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        PkgDetailEntity k2 = com.jingdong.a.k(str2, str4);
        if (k2 == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "pkg detail is null");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        new Handler(Looper.getMainLooper()).post(new c(activity, mantoResultCallBack, k2, str, str2, str3, gVar, gVar2, bundle));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "voipRoomApi";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            mantoResultCallBack.onFailed(null);
            return;
        }
        Bundle bundle2 = new Bundle(1);
        String string = bundle.getString("appid");
        String string2 = bundle.getString("type");
        if (TextUtils.equals(str, "registerRtcServer")) {
            b(mantoCore.getActivity(), string, jSONObject.optString("rtcAppId"), string2, jSONObject.optString("pin"), bundle2, mantoResultCallBack);
        } else if (TextUtils.equals(str, "registerRtcServerInstall")) {
            d(jSONObject.optString("pin"), jSONObject.optString("rtcAppId"), mantoResultCallBack);
        } else if (TextUtils.equals(str, "registerRtcServerNative")) {
            e(mantoCore, jSONObject.optString("pin"), jSONObject.optString("rtcAppId"), bundle2, mantoResultCallBack);
        } else if (TextUtils.equals(str, "unRegisterRtcServer")) {
            com.jd.manto.d.d0.c.q(mantoCore.getActivity());
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpCall")) {
            com.jd.manto.d.d0.c.p(mantoCore.getActivity(), jSONObject.optString("calleePin"), jSONObject.optString("selfAvatar"), jSONObject.optString("calleeAppId"), jSONObject.optString("selfName"), TextUtils.equals(jSONObject.optString("roomType"), "video"), jSONObject.optString("userData"), jSONObject.optString("sessionId"), jSONObject.optBoolean("openCamera", false));
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpAcceptInvite")) {
            com.jd.manto.d.d0.c.a(mantoCore.getActivity());
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpHangUp")) {
            com.jd.manto.d.d0.c.f(mantoCore.getActivity());
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpVideoOpen")) {
            com.jd.manto.d.d0.c.k(mantoCore.getActivity(), jSONObject.optBoolean(JshopConst.JSKEY_CATE_OPEN));
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpToggleCamera")) {
            com.jd.manto.d.d0.c.j(mantoCore.getActivity());
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcMpSpeakerOpen")) {
            com.jd.manto.d.d0.c.i(mantoCore.getActivity(), jSONObject.optBoolean(JshopConst.JSKEY_CATE_OPEN));
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "rtcHandFreeOpen")) {
            com.jd.manto.d.d0.c.h(mantoCore.getActivity(), jSONObject.optBoolean(JshopConst.JSKEY_CATE_OPEN));
            mantoResultCallBack.onSuccess(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("registerRtcServer", 1));
        list.add(new JsApiMethod("registerRtcServerInstall", 0));
        list.add(new JsApiMethod("registerRtcServerNative", 1));
        list.add(new JsApiMethod("unRegisterRtcServer", 1));
        list.add(new JsApiMethod("rtcMpCall", 1));
        list.add(new JsApiMethod("rtcMpAcceptInvite", 1));
        list.add(new JsApiMethod("rtcMpHangUp", 1));
        list.add(new JsApiMethod("rtcMpVideoOpen", 1));
        list.add(new JsApiMethod("rtcMpToggleCamera", 1));
        list.add(new JsApiMethod("rtcMpSpeakerOpen", 1));
        list.add(new JsApiMethod("rtcHandFreeOpen", 1));
    }
}
