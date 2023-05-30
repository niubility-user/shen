package com.jingdong.manto.jsapi.auth.tools;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.b;
import com.jingdong.manto.f;
import com.jingdong.manto.jsapi.refact.JsApiScanCode;
import com.jingdong.manto.jsapi.refact.lbs.JsApiLocation;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.b0;
import com.jingdong.manto.pkg.a;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.auth.MantoAuthDialogUtils;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoStringUtils;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class AuthorizeManager {
    private static Map<String, AuthInfo> JDApiMap = new HashMap();
    private static Map<String, AuthInfo> deviceApiMap = new HashMap();
    private static Map<String, String> apiAuthMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager$6 */
    /* loaded from: classes15.dex */
    public class AnonymousClass6 implements AuthorizeCallBack {
        final /* synthetic */ String val$appId;
        final /* synthetic */ MantoAuthDialog.Callback val$callback;
        final /* synthetic */ MantoCore val$core;
        final /* synthetic */ e0 val$mantoComponent;
        final /* synthetic */ String val$pkgDebugType;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager$6$2 */
        /* loaded from: classes15.dex */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ AuthInfo val$authInfo;
            final /* synthetic */ String val$tag;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager$6$2$1 */
            /* loaded from: classes15.dex */
            public class AnonymousClass1 implements Runnable {
                final /* synthetic */ PkgDetailEntity val$detail;

                AnonymousClass1(PkgDetailEntity pkgDetailEntity) {
                    AnonymousClass2.this = r1;
                    this.val$detail = pkgDetailEntity;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    f h2;
                    MantoAuthDialog userInfoAuthDialog;
                    if ("devicePermission".equals(AnonymousClass2.this.val$tag)) {
                        MantoAuthDialog.Callback callback = new MantoAuthDialog.Callback() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.6.2.1.1
                            {
                                AnonymousClass1.this = this;
                            }

                            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                            public void onAccept() {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                AuthInfo authInfo = anonymousClass2.val$authInfo;
                                State state = State.ACCEPT;
                                authInfo.state = state;
                                AuthorizeManager.updateAuth(AnonymousClass6.this.val$appId, authInfo.scope, state);
                                Activity activity = AnonymousClass6.this.val$core.getActivity();
                                AnonymousClass2 anonymousClass22 = AnonymousClass2.this;
                                AuthorizeManager.doDeviceApiAuth(activity, AnonymousClass6.this.val$appId, anonymousClass22.val$authInfo, new AuthorizeCallBack() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.6.2.1.1.1
                                    {
                                        C05161.this = this;
                                    }

                                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                                    public void onAuth() {
                                        AnonymousClass6.this.val$callback.onAccept();
                                    }

                                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                                    public void onConfirm(AuthInfo authInfo2, String str) {
                                    }

                                    @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
                                    public void onFailure(String str) {
                                        AnonymousClass6.this.val$callback.onReject();
                                    }
                                });
                                AuthorizeManager.sendEventTrack("\u4f4d\u7f6e\u7b49\u6388\u6743-\u5141\u8bb8", "Applets_Allow", AnonymousClass2.this.val$authInfo.scope);
                            }

                            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                            public void onCancel() {
                                AnonymousClass6.this.val$callback.onCancel();
                            }

                            @Override // com.jingdong.manto.ui.auth.MantoAuthDialog.Callback
                            public void onReject() {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                AuthInfo authInfo = anonymousClass2.val$authInfo;
                                State state = State.REJECT;
                                authInfo.state = state;
                                AuthorizeManager.updateAuth(AnonymousClass6.this.val$appId, authInfo.scope, state);
                                AnonymousClass6.this.val$callback.onReject();
                                AuthorizeManager.sendEventTrack("\u4f4d\u7f6e\u7b49\u6388\u6743-\u4e0d\u5141\u8bb8", "Applets_DisAllow", AnonymousClass2.this.val$authInfo.scope);
                            }
                        };
                        h2 = AnonymousClass6.this.val$mantoComponent.h();
                        userInfoAuthDialog = MantoAuthDialogUtils.getDeviceAuthDialog(AnonymousClass6.this.val$core.getActivity(), this.val$detail.name, AnonymousClass2.this.val$authInfo.description, callback);
                    } else {
                        LinkedList linkedList = new LinkedList();
                        linkedList.add(AnonymousClass2.this.val$authInfo);
                        h2 = AnonymousClass6.this.val$mantoComponent.h();
                        Activity activity = AnonymousClass6.this.val$core.getActivity();
                        PkgDetailEntity pkgDetailEntity = this.val$detail;
                        userInfoAuthDialog = MantoAuthDialogUtils.getUserInfoAuthDialog(activity, linkedList, pkgDetailEntity.name, pkgDetailEntity.logo, AnonymousClass6.this.val$callback);
                    }
                    h2.a(userInfoAuthDialog);
                }
            }

            AnonymousClass2(String str, AuthInfo authInfo) {
                AnonymousClass6.this = r1;
                this.val$tag = str;
                this.val$authInfo = authInfo;
            }

            @Override // java.lang.Runnable
            public void run() {
                a k2 = b.k();
                AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                PkgDetailEntity c2 = k2.c(anonymousClass6.val$appId, String.valueOf(anonymousClass6.val$pkgDebugType));
                if (c2 != null) {
                    com.jingdong.manto.sdk.thread.a.a(new AnonymousClass1(c2));
                } else {
                    com.jingdong.manto.sdk.thread.a.a(new Runnable() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.6.2.2
                        {
                            AnonymousClass2.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass6.this.val$callback.onAccept();
                        }
                    });
                }
            }
        }

        AnonymousClass6(MantoAuthDialog.Callback callback, String str, String str2, MantoCore mantoCore, e0 e0Var) {
            this.val$callback = callback;
            this.val$appId = str;
            this.val$pkgDebugType = str2;
            this.val$core = mantoCore;
            this.val$mantoComponent = e0Var;
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onAuth() {
            com.jingdong.manto.sdk.thread.a.a(new Runnable() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.6.1
                {
                    AnonymousClass6.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass6.this.val$callback.onAccept();
                }
            });
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onConfirm(AuthInfo authInfo, String str) {
            b.d().diskIO().execute(new AnonymousClass2(str, authInfo));
        }

        @Override // com.jingdong.manto.jsapi.auth.tools.AuthorizeCallBack
        public void onFailure(String str) {
            this.val$callback.onCancel();
        }
    }

    /* loaded from: classes15.dex */
    public enum State implements Parcelable {
        REJECT(-1),
        NONE(0),
        ACCEPT(1);
        
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.State.1
            @Override // android.os.Parcelable.Creator
            public State createFromParcel(Parcel parcel) {
                return State.values()[parcel.readInt()];
            }

            @Override // android.os.Parcelable.Creator
            public State[] newArray(int i2) {
                return new State[i2];
            }
        };
        private int state;

        State(int i2) {
            this.state = i2;
        }

        public static State get(int i2) {
            if (i2 != -1) {
                if (i2 != 0 && i2 == 1) {
                    return ACCEPT;
                }
                return NONE;
            }
            return REJECT;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return this.state;
        }

        public int value() {
            return this.state;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(ordinal());
        }
    }

    static {
        Map<String, AuthInfo> map = JDApiMap;
        State state = State.NONE;
        map.put("scope.userInfo", new AuthInfo("scope.userInfo", JshopConst.JSKEY_SHOP_USER_INFO, "", "", state));
        deviceApiMap.put("scope.userLocation", new AuthInfo("scope.userLocation", "android.permission.ACCESS_FINE_LOCATION", "\u4f7f\u7528\u60a8\u7684\u5730\u7406\u4f4d\u7f6e", "\u4f7f\u7528\u60a8\u7684\u5730\u7406\u4f4d\u7f6e", state));
        deviceApiMap.put("scope.record", new AuthInfo("scope.record", "android.permission.RECORD_AUDIO", "\u4f7f\u7528\u60a8\u7684\u5f55\u97f3\u529f\u80fd", "\u4f7f\u7528\u60a8\u7684\u5f55\u97f3\u529f\u80fd", state));
        deviceApiMap.put("scope.camera", new AuthInfo("scope.camera", "android.permission.CAMERA", "\u4f7f\u7528\u60a8\u7684\u6444\u50cf\u5934", "\u4f7f\u7528\u60a8\u7684\u624b\u673a\u62cd\u7167", state));
        apiAuthMap.put(JsApiLocation.GETLOCATION_NAME, "scope.userLocation");
        apiAuthMap.put("insertCamera", "scope.camera");
        apiAuthMap.put(JsApiScanCode.JSAPI_NAME, "scope.camera");
        apiAuthMap.put("operateRecorder", "scope.record");
    }

    public static void checkAndDoAuthorsize(e0 e0Var, com.jingdong.manto.m.a aVar, MantoAuthDialog.Callback callback) {
        String str = apiAuthMap.get(aVar.getJsApiName());
        if (TextUtils.isEmpty(str)) {
            callback.onAccept();
            return;
        }
        MantoCore k2 = e0Var.h().k();
        checkAuth(e0Var.h().f13017i, str, new AnonymousClass6(callback, e0Var.h().f13017i, e0Var.h().r.f13082e, k2, e0Var));
    }

    public static void checkAuth(String str, String str2, AuthorizeCallBack authorizeCallBack) {
        AuthInfo authInfo = JDApiMap.get(str2);
        if (authInfo != null) {
            checkJDApiAuth(str, authInfo, authorizeCallBack);
            return;
        }
        AuthInfo authInfo2 = deviceApiMap.get(str2);
        if (authInfo2 != null) {
            checkDeviceApiAuth(str, authInfo2, authorizeCallBack);
        }
    }

    public static void checkAuth(String str, List list, AuthorizeCallBack authorizeCallBack) {
        checkAuth(str, (String) list.get(0), authorizeCallBack);
    }

    private static void checkDeviceApiAuth(final String str, final AuthInfo authInfo, final AuthorizeCallBack authorizeCallBack) {
        b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<AuthInfo> it = b.k().a(str).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AuthInfo next = it.next();
                    if (!MantoStringUtils.isEmpty(authInfo.scope) && authInfo.scope.equals(next.scope)) {
                        if (next.state.equals(State.ACCEPT)) {
                            authorizeCallBack.onAuth();
                            return;
                        }
                    }
                }
                authorizeCallBack.onConfirm(authInfo, "devicePermission");
            }
        });
    }

    private static void checkJDApiAuth(String str, final AuthInfo authInfo, final AuthorizeCallBack authorizeCallBack) {
        MantoJDHttpHandler.commit(new b0(str), new IMantoHttpListener() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.1
            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                super.onError(jSONObject, th);
                JSONObject optJSONObject = jSONObject.optJSONObject("errors");
                if (optJSONObject == null) {
                    authorizeCallBack.onFailure(th.getMessage());
                    return;
                }
                authorizeCallBack.onFailure(optJSONObject.optString("msg"));
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject;
                JSONArray optJSONArray;
                if (jSONObject != null && jSONObject.optJSONObject("data") != null && (optJSONObject = jSONObject.optJSONObject("data")) != null && (optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i2);
                        if (jSONObject2 != null && authInfo.permission.equals(jSONObject2.optString("key"))) {
                            String optString = jSONObject2.optString(Constants.PARAM_SCOPE);
                            if ("1".equals(optString)) {
                                authorizeCallBack.onAuth();
                                return;
                            } else if ("0".equals(optString) || "-1".equals(optString)) {
                                String optString2 = jSONObject2.optString("title");
                                String optString3 = jSONObject2.optString("word");
                                AuthorizeCallBack authorizeCallBack2 = authorizeCallBack;
                                AuthInfo authInfo2 = authInfo;
                                authorizeCallBack2.onConfirm(new AuthInfo(authInfo2.scope, authInfo2.permission, optString2, optString3, State.get(Integer.valueOf(optString).intValue())), "jdPermission");
                                return;
                            }
                        }
                    }
                }
                authorizeCallBack.onFailure("auth check error");
            }
        });
    }

    public static void doAuth(String str, String str2, State state, AuthorizeCallBack authorizeCallBack) {
        AuthInfo authInfo = JDApiMap.get(str2);
        if (authInfo != null) {
            doJDApiAuth(str, authInfo, state, authorizeCallBack);
            return;
        }
        AuthInfo authInfo2 = deviceApiMap.get(str2);
        if (authInfo2 != null) {
            authorizeCallBack.onConfirm(authInfo2, "devicePermission");
        }
    }

    public static void doAuth(String str, List list, State state, AuthorizeCallBack authorizeCallBack) {
        doAuth(str, (String) list.get(0), state, authorizeCallBack);
    }

    public static void doDeviceApiAuth(Activity activity, final String str, final AuthInfo authInfo, final AuthorizeCallBack authorizeCallBack) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission == null) {
            authorizeCallBack.onFailure("system Error");
        } else if (!iPermission.hasPermission(authInfo.permission)) {
            MantoPermission.requestPermission(activity, authInfo.permission, new IPermission.PermissionCallBack() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.4
                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onDenied() {
                    authorizeCallBack.onFailure("\u7528\u6237\u62d2\u7edd\u6388\u6743");
                    AuthInfo authInfo2 = authInfo;
                    State state = State.REJECT;
                    authInfo2.state = state;
                    AuthorizeManager.updateAuth(str, authInfo2.scope, state);
                }

                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onGranted() {
                    authorizeCallBack.onAuth();
                    AuthInfo authInfo2 = authInfo;
                    State state = State.ACCEPT;
                    authInfo2.state = state;
                    AuthorizeManager.updateAuth(str, authInfo2.scope, state);
                }
            });
        } else {
            authorizeCallBack.onAuth();
            State state = State.ACCEPT;
            authInfo.state = state;
            updateAuth(str, authInfo.scope, state);
        }
    }

    private static void doJDApiAuth(String str, AuthInfo authInfo, State state, final AuthorizeCallBack authorizeCallBack) {
        MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.e0(str, authInfo.permission, state), new IMantoHttpListener() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.3
            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                AuthorizeCallBack authorizeCallBack2;
                String message;
                super.onError(jSONObject, th);
                if (jSONObject != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("errors");
                    if (optJSONObject != null) {
                        authorizeCallBack.onFailure(optJSONObject.optString("msg"));
                        return;
                    } else {
                        authorizeCallBack2 = authorizeCallBack;
                        message = "unknown";
                    }
                } else {
                    authorizeCallBack2 = authorizeCallBack;
                    message = th.getMessage();
                }
                authorizeCallBack2.onFailure(message);
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    String optString = jSONObject.optString("code");
                    if (TextUtils.isEmpty(optString) || !"0".equals(optString)) {
                        authorizeCallBack.onFailure("");
                    } else {
                        authorizeCallBack.onAuth();
                    }
                }
            }
        });
    }

    public static boolean isDevicePermission(String str) {
        return deviceApiMap.containsKey(str);
    }

    public static void sendEventTrack(String str, String str2, String str3) {
        if (str3.contains("userLocation") || str3.contains("camera")) {
            return;
        }
        str3.contains("writePhotosAlbum");
    }

    public static void updateAuth(final String str, final String str2, final State state) {
        b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.auth.tools.AuthorizeManager.5
            @Override // java.lang.Runnable
            public void run() {
                AuthInfo authInfo = (AuthInfo) AuthorizeManager.deviceApiMap.get(str2);
                authInfo.state = state;
                b.k().a(str, authInfo);
            }
        });
    }
}
