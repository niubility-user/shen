package com.jingdong.app.mall.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.verify.PreLoader;
import com.jd.verify.ShowCapCallback;
import com.jd.verify.Verify;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.BusinessRegistObserverManager;
import com.jingdong.common.login.CCFLoginUtil;
import com.jingdong.common.login.DialogLoginUtil;
import com.jingdong.common.login.IBusinessRegist;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.MobileLoginUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.data.Constant;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.SuccessResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class LoginRouterHelper {
    public static final String CHINA_MOBILE_LOGIN_OPERATORTYPE = "CM";
    private static final String PRE_LOAD = "preLoad";
    private static final String TAG = "LoginRouterHelper";
    public static final String TELECOM_LOGIN_OPERATORTYPE = "CT";
    public static final String UNICOM_LOGIN_OPERATORTYPE = "CU";
    private static boolean hasRegister = true;
    private static String operateType = null;
    private static PreLoader proload = null;
    private static int type = 3;

    /* renamed from: verify  reason: collision with root package name */
    private static Verify f11194verify;
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private static String pageID = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f11195g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f11196h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f11197i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f11198j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ IRouterParams f11199k;

        /* renamed from: com.jingdong.app.mall.login.LoginRouterHelper$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0345a implements ShowCapCallback {
            C0345a() {
            }

            @Override // com.jd.verify.CallBack
            public void invalidSessiongId() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "init verifyCallback invalidSessiongId countryCode = " + a.this.f11197i);
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(2, "sid\u5931\u6548\uff0c\u8bf7\u91cd\u65b0\u83b7\u53d6"));
            }

            @Override // com.jd.verify.ShowCapCallback
            public void loadFail() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, " verifyCallback loadFail:");
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(1, "\u9a8c\u8bc1\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5"));
            }

            @Override // com.jd.verify.InnerCallBack
            public void onFail(String str) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, " verifyCallback onFail:" + str);
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(1, "\u9a8c\u8bc1\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5"));
            }

            @Override // com.jd.verify.SSLDialogCallback
            public void onSSLError() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, " verifyCallback onSSLError:");
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(1, "\u9a8c\u8bc1\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5"));
            }

            @Override // com.jd.verify.InnerCallBack
            public void onSuccess(IninVerifyInfo ininVerifyInfo) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "init verifyCallback onSuccess countryCode = " + a.this.f11197i);
                }
                a aVar = a.this;
                aVar.f11199k.onCallBack(LoginRouterHelper.getMsgSuccessObj(null, aVar.f11195g, ininVerifyInfo.getVt()));
            }

            @Override // com.jd.verify.CallBack
            public void showButton(int i2) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, " verifyCallback showButton:" + i2);
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(1, "\u9a8c\u8bc1\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5"));
            }

            @Override // com.jd.verify.ShowCapCallback
            public void showCap() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, " verifyCallback showCap:");
                }
                a.this.f11199k.onCallBack(LoginRouterHelper.genCommonObj(1, ""));
            }
        }

        a(String str, Context context, String str2, String str3, IRouterParams iRouterParams) {
            this.f11195g = str;
            this.f11196h = context;
            this.f11197i = str2;
            this.f11198j = str3;
            this.f11199k = iRouterParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            LoginRouterHelper.access$900().init(this.f11195g, this.f11196h, StatisticsReportUtil.readAndroidId(), this.f11197i, this.f11198j, new C0345a());
        }
    }

    /* loaded from: classes4.dex */
    class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IRouterParams f11200g;

        b(IRouterParams iRouterParams) {
            this.f11200g = iRouterParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f11200g.getContext() instanceof Activity) {
                Bundle bundle = new Bundle();
                bundle.putString("pageID", LoginRouterHelper.pageID);
                DialogLoginUtil.getInstance().showDialog((Activity) this.f11200g.getContext(), bundle);
                String unused = LoginRouterHelper.pageID = "";
            }
        }
    }

    /* loaded from: classes4.dex */
    class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IRouterParams f11201g;

        /* loaded from: classes4.dex */
        class a implements ICancelLogin {
            a() {
            }

            @Override // com.jingdong.common.login.ICancelLogin
            public void onCancel(String str) {
                if ("dialogLogin".equals(str)) {
                    String unused = LoginRouterHelper.pageID = "";
                    c.this.f11201g.onCallBack(LoginRouterHelper.genCommonObj(3, "\u53d6\u6d88\u767b\u5f55"));
                }
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("dialogLogin".equals(str)) {
                    String unused = LoginRouterHelper.pageID = "";
                    c.this.f11201g.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
                }
            }
        }

        c(IRouterParams iRouterParams) {
            this.f11201g = iRouterParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f11201g.getContext() instanceof Activity) {
                Bundle bundle = new Bundle();
                bundle.putString("pageID", LoginRouterHelper.pageID);
                DialogLoginUtil.getInstance().showDialog((Activity) this.f11201g.getContext(), bundle, new a(), "dialogLogin");
            }
        }
    }

    /* loaded from: classes4.dex */
    class d implements IBusinessRegist {
        final /* synthetic */ IRouterParams a;

        d(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.login.IRegist
        public void onCancel(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "jump2BusRegist \u53d6\u6d88\u4e86\u539f\u751f\u4f01\u4e1a\u6ce8\u518c");
            }
            if ("busRegister".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(2, "\u53d6\u6d88\u6ce8\u518c"));
            }
        }

        @Override // com.jingdong.common.login.IBusinessRegist
        public void onFail(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "jump2BusRegist \u539f\u751f\u4f01\u4e1a\u6ce8\u518c-\u5b8c\u5584\u4fe1\u606f\u9875\u9762\u540e\u8df3\u56deapp\u83b7\u53d6\u767b\u5f55\u6001\u63a5\u53e3\u5931\u8d25");
            }
            if ("busRegister".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(4, "\u767b\u5f55\u5931\u8d25"));
            }
        }

        @Override // com.jingdong.common.login.IRegist
        public void onSuccess(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "jump2BusRegist \u539f\u751f\u4f01\u4e1a\u6ce8\u518c\u6210\u529f\uff0c\u76f4\u63a5\u4e0b\u53d1\u767b\u5f55\u6001\u4e86");
            }
            if ("busRegister".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(1, "\u6ce8\u518c\u6210\u529f"));
            }
        }
    }

    /* loaded from: classes4.dex */
    class e implements IBusinessRegist {
        final /* synthetic */ IRouterParams a;

        e(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.login.IRegist
        public void onCancel(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "busRegistObserver \u53d6\u6d88\u4e86\u539f\u751f\u4f01\u4e1a\u6ce8\u518c");
            }
            if ("busRegistObserver".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(2, "\u53d6\u6d88\u767b\u5f55"));
            }
        }

        @Override // com.jingdong.common.login.IBusinessRegist
        public void onFail(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "busRegistFailListen \u539f\u751f\u4f01\u4e1a\u6ce8\u518c-\u5b8c\u5584\u4fe1\u606f\u9875\u9762\u540e\u8df3\u56deapp\u83b7\u53d6\u767b\u5f55\u6001\u63a5\u53e3\u5931\u8d25");
            }
            if ("busRegistObserver".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(4, "\u767b\u5f55\u5931\u8d25"));
            }
        }

        @Override // com.jingdong.common.login.IRegist
        public void onSuccess(String str) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "busRegistFailListen \u539f\u751f\u4f01\u4e1a\u6ce8\u518c\u6210\u529f\uff0c\u76f4\u63a5\u4e0b\u53d1\u767b\u5f55\u6001\u4e86");
            }
            if ("busRegistObserver".equals(str)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(1, "\u767b\u5f55\u6210\u529f"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f extends OnCommonCallback {
        final /* synthetic */ String a;
        final /* synthetic */ IRouterParams b;

        f(String str, IRouterParams iRouterParams) {
            this.a = str;
            this.b = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, " getCaptchaSid onError");
            }
            this.b.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            String strVal = failResult.getStrVal();
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, " getCaptchaSid onFail:" + strVal);
                OKLog.d(LoginRouterHelper.TAG, " getCaptchaSid onFail code :" + ((int) failResult.getReplyCode()));
            }
            if (!TextUtils.isEmpty(strVal)) {
                this.b.onCallBack(LoginRouterHelper.getSidSuccessObj(200, strVal, ""));
            } else {
                this.b.onCallBack(LoginRouterHelper.genFailObj(failResult));
            }
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "getSessionId() onSuccess countryCode = " + this.a);
            }
            this.b.onCallBack(LoginRouterHelper.getSidSuccessObj(0, "", ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g extends OnDataCallback<SuccessResult> {
        final /* synthetic */ String a;
        final /* synthetic */ IRouterParams b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f11202c;
        final /* synthetic */ String d;

        g(String str, IRouterParams iRouterParams, String str2, String str3) {
            this.a = str;
            this.b = iRouterParams;
            this.f11202c = str2;
            this.d = str3;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "sendMsgCodeForPhoneNumLogin4JD onError");
            }
            String errorMsg = errorResult.getErrorMsg() != null ? errorResult.getErrorMsg() : "";
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "getMsg onError message" + errorMsg + "  code=" + errorResult.getErrorCode());
            }
            this.b.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "sendMsgCodeForPhoneNumLogin4JD onFail");
            }
            if (7 == failResult.getReplyCode() || 31 == failResult.getReplyCode()) {
                boolean unused = LoginRouterHelper.hasRegister = false;
            } else {
                boolean unused2 = LoginRouterHelper.hasRegister = true;
            }
            this.b.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnDataCallback
        public void onSuccess(SuccessResult successResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "sendMsgCodeForPhoneNumLogin4JD onSuccess countryCode = " + this.a);
            }
            this.b.onCallBack(LoginRouterHelper.getMsgSuccessObj(successResult, this.f11202c, this.d));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        h(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkMsgCodeForPhoneNumLogin4JD onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkMsgCodeForPhoneNumLogin4JD onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkMsgCodeForPhoneNumLogin4JD onSuccess");
            }
            if (LoginRouterHelper.hasRegister) {
                LoginUserBase.saveInfoAfterLogin();
            } else {
                LoginUserBase.saveInfoAfterLogin(1);
            }
            try {
                if (LoginRouterHelper.f11194verify != null) {
                    LoginRouterHelper.f11194verify.free();
                    Verify unused = LoginRouterHelper.f11194verify = null;
                }
                if (LoginRouterHelper.proload != null) {
                    LoginRouterHelper.proload.onDestroy();
                    PreLoader unused2 = LoginRouterHelper.proload = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        i(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkHistory4JDPhoneNumLoginNew onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkHistory4JDPhoneNumLoginNew onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "checkHistory4JDPhoneNumLoginNew onSuccess");
            }
            LoginUserBase.saveInfoAfterLogin();
            try {
                if (LoginRouterHelper.f11194verify != null) {
                    LoginRouterHelper.f11194verify.free();
                    Verify unused = LoginRouterHelper.f11194verify = null;
                }
                if (LoginRouterHelper.proload != null) {
                    LoginRouterHelper.proload.onDestroy();
                    PreLoader unused2 = LoginRouterHelper.proload = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j extends OnResponseCallback {
        final /* synthetic */ IRouterParams a;
        final /* synthetic */ String b;

        /* loaded from: classes4.dex */
        class a extends OnCommonCallback {
            a() {
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onError(ErrorResult errorResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyLogin() onError");
                }
                j.this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onFail(FailResult failResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyLogin() onFail");
                }
                if (35 == failResult.getReplyCode()) {
                    boolean unused = LoginRouterHelper.hasRegister = false;
                } else {
                    boolean unused2 = LoginRouterHelper.hasRegister = true;
                }
                j.this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onSuccess() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyLogin() onSuccess");
                }
                if (LoginRouterHelper.hasRegister) {
                    LoginUserBase.saveInfoAfterLogin();
                } else {
                    LoginUserBase.saveInfoAfterLogin(1);
                }
                j.this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
            }
        }

        /* loaded from: classes4.dex */
        class b extends OnCommonCallback {
            b() {
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onError(ErrorResult errorResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyLogin() onError");
                }
                j.this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onFail(FailResult failResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyLogin() onFail");
                }
                if (35 == failResult.getReplyCode()) {
                    boolean unused = LoginRouterHelper.hasRegister = false;
                } else {
                    boolean unused2 = LoginRouterHelper.hasRegister = true;
                }
                j.this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onSuccess() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyLogin() onSuccess");
                }
                if (LoginRouterHelper.hasRegister) {
                    LoginUserBase.saveInfoAfterLogin();
                } else {
                    LoginUserBase.saveInfoAfterLogin(1);
                }
                j.this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
            }
        }

        /* loaded from: classes4.dex */
        class c extends OnCommonCallback {
            c() {
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onError(ErrorResult errorResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyLogin() onError");
                }
                j.this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onFail(FailResult failResult) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyLogin() onFail");
                }
                if (35 == failResult.getReplyCode()) {
                    boolean unused = LoginRouterHelper.hasRegister = false;
                } else {
                    boolean unused2 = LoginRouterHelper.hasRegister = true;
                }
                j.this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onSuccess() {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyLogin() onSuccess");
                }
                if (LoginRouterHelper.hasRegister) {
                    LoginUserBase.saveInfoAfterLogin();
                } else {
                    LoginUserBase.saveInfoAfterLogin(1);
                }
                j.this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
            }
        }

        j(IRouterParams iRouterParams, String str) {
            this.a = iRouterParams;
            this.b = str;
        }

        @Override // com.wjlogin.onekey.sdk.common.listener.OnResponseCallback
        public void onFail(JSONObject jSONObject) {
            if (jSONObject != null && OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "getOneKeyLoginHelper getAccessToken onFail = " + jSONObject.toString());
            }
            this.a.onCallBack(LoginRouterHelper.genCommonObj(1, "\u83b7\u53d6\u6388\u6743token\u5931\u8d25"));
        }

        @Override // com.wjlogin.onekey.sdk.common.listener.OnResponseCallback
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject == null) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "getOneKeyLoginHelper getAccessToken onSuccess null ");
                }
                this.a.onCallBack(LoginRouterHelper.genCommonObj(1, "\u83b7\u53d6\u6388\u6743token\u5931\u8d25"));
                return;
            }
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "getOneKeyLoginHelper getAccessToken onSuccess = " + jSONObject.toString());
            }
            String optString = jSONObject.optString("accessCode");
            if (TextUtils.isEmpty(optString)) {
                this.a.onCallBack(LoginRouterHelper.genCommonObj(1, "\u83b7\u53d6\u6388\u6743token\u5931\u8d25"));
            } else if ("CT".equals(this.b)) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyLogin() ");
                }
                LoginRouterHelper.sendAction("PopupLogin_LocalLogin_CTCC", "PopupLogin");
                UserUtil.getWJLoginHelper().telecomOneKeyLogin("", optString, new a());
            } else if ("CU".equals(this.b)) {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyLogin()");
                }
                LoginRouterHelper.sendAction("PopupLogin_LocalLogin_CUCC", "PopupLogin");
                UserUtil.getWJLoginHelper().unicomOneKeyLogin(optString, new b());
            } else {
                if (OKLog.D) {
                    OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyLogin() ");
                }
                LoginRouterHelper.sendAction("PopupLogin_LocalLogin", "PopupLogin");
                UserUtil.getWJLoginHelper().chinaMobileOneKeyLogin(optString, new c());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class k extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        k(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyRegister()  onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyRegister()  onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "telecomOneKeyRegister()  onSuccess");
            }
            LoginUserBase.saveInfoAfterLogin(1);
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class l extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        l(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyRegister() onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyRegister() onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "unicomOneKeyRegister() onSuccess");
            }
            LoginUserBase.saveInfoAfterLogin(1);
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class m extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        m(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyRegister() onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyRegister() onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "chinaMobileOneKeyRegister() onSuccess");
            }
            LoginUserBase.saveInfoAfterLogin(1);
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class n extends OnCommonCallback {
        final /* synthetic */ IRouterParams a;

        n(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "h5Back2App() onError");
            }
            this.a.onCallBack(LoginRouterHelper.genErrorObj(errorResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "h5Back2App() onFail");
            }
            this.a.onCallBack(LoginRouterHelper.genFailObj(failResult));
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (OKLog.D) {
                OKLog.d(LoginRouterHelper.TAG, "h5Back2App() onSuccess");
            }
            LoginUserBase.saveInfoAfterLogin();
            this.a.onCallBack(LoginRouterHelper.genCommonObj(0, "\u767b\u5f55\u6210\u529f"));
        }
    }

    static /* synthetic */ Verify access$900() {
        return getVerify();
    }

    public static void busRegistObserver(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "busRegistObserver begin");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "busRegistObserver router == null || router.getContext() == null");
            }
            iRouterParams.onCallBack(genCommonObj(3, "\u4f20\u5165\u53c2\u6570\u9519\u8bef"));
        }
        try {
            registbusRegisterFail(new e(iRouterParams), "busRegistObserver");
        } catch (Exception e2) {
            e2.printStackTrace();
            if (OKLog.D) {
                OKLog.d(TAG, "h5\u8df3\u8f6c\u5230\u539f\u751f\u4f01\u4e1a\u6ce8\u518c\u53d1\u751f\u4e86\u5f02\u5e38");
            }
            iRouterParams.onCallBack(genCommonObj(0, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
        }
    }

    public static void checkHistory(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "checkHistory() ");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        sendAction("PopupLogin_MessageLogin_VerifyHistorical", "PopupLogin");
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString(Constant.KEY_COUNTRY_CODE);
            String optString2 = jSONObject.optString("phoneNum");
            String optString3 = jSONObject.optString("historyName");
            LoginConstans.FROM_ROUTER_BMODE = 0;
            LoginConstans.FROM_BMODE = 0;
            if (CCFLoginUtil.isOpenRouterBModel() && TextUtils.equals("1", jSONObject.optString(LoginConstans.NEED_REFRESH_MODE, "0"))) {
                LoginConstans.FROM_ROUTER_BMODE = LoginConstans.REFRESH_MODE_VALUE;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "checkHistory() countryCode=" + optString + " phoneNum=" + optString2 + " historyName=" + optString3);
            }
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            checkHostory(iRouterParams, optString2, optString, optString3);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    private static void checkHostory(IRouterParams iRouterParams, String str, String str2, String str3) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "checkHistoryPerson  countryCode = " + str2);
            }
            UserUtil.getWJLoginHelper().checkHistory4JDPhoneNumLoginNew(str, str2, str3, new i(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    private static void checkMsg(IRouterParams iRouterParams, String str, String str2, String str3) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "checkMsg countryCode = " + str2);
            }
            UserUtil.getWJLoginHelper().checkMsgCodeForPhoneNumLogin4JD(str, str3, str2, new h(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    public static void checkMsgCode(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "checkMsgCode()");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        sendAction("PopupLogin_MessageLogin", "PopupLogin");
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString(Constant.KEY_COUNTRY_CODE);
            String optString2 = jSONObject.optString("phoneNum");
            String optString3 = jSONObject.optString(JshopConst.JSHOP_TIP_MESSAGE_CODE);
            LoginConstans.FROM_ROUTER_BMODE = 0;
            LoginConstans.FROM_BMODE = 0;
            if (CCFLoginUtil.isOpenRouterBModel() && TextUtils.equals("1", jSONObject.optString(LoginConstans.NEED_REFRESH_MODE, "0"))) {
                LoginConstans.FROM_ROUTER_BMODE = LoginConstans.REFRESH_MODE_VALUE;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "checkMsgCode() countryCode=" + optString + " phoneNum=" + optString2 + " msgCode=" + optString3);
            }
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            checkMsg(iRouterParams, optString2, optString, optString3);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    public static JSONObject dialogLogin(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "dialogLogin begin");
        }
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (LoginUserBase.hasLogin()) {
                    return genCommonObj(2, "\u5df2\u767b\u5f55\u6210\u529f\uff0c\u65e0\u9700\u5f39\u6846");
                }
                try {
                    if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                        pageID = new JSONObject(iRouterParams.getRouterParam()).optString("pageID");
                    }
                } catch (JSONException unused) {
                }
                uiHandler.post(new b(iRouterParams));
                return genCommonObj(0, "\u5df2\u8c03\u5f39\u51fa\u6846");
            } catch (Exception e2) {
                e2.printStackTrace();
                if (iRouterParams.getContext() instanceof Activity) {
                    DeepLinkLoginHelper.startLoginActivity(iRouterParams.getContext(), null);
                }
                return genCommonObj(1, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86");
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "router == null || router.getContext() == null");
        }
        return new JSONObject();
    }

    public static JSONObject freeVerify(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "freeVerify begin");
        }
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                Verify verify2 = f11194verify;
                if (verify2 != null) {
                    verify2.free();
                    f11194verify = null;
                }
                PreLoader preLoader = proload;
                if (preLoader != null) {
                    preLoader.onDestroy();
                    proload = null;
                }
                return genCommonObj(1, "\u5df2\u91ca\u653e\u9a8c\u8bc1\u7801\u8d44\u6e90");
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "router == null || router.getContext() == null");
        }
        return new JSONObject();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genCommonObj(int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i2);
            jSONObject.put("message", str + "");
            if (OKLog.D) {
                OKLog.d(TAG, "genCommonObj() code=" + i2 + " message=" + str);
            }
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genErrorObj(ErrorResult errorResult) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (errorResult == null) {
                jSONObject.put("code", -102);
                jSONObject.put("errorMsg", "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86");
                return jSONObject;
            }
            jSONObject.put("code", errorResult.getErrorCode());
            jSONObject.put("message", errorResult.getErrorMsg() + "");
            if (OKLog.D) {
                OKLog.d(TAG, "genErrorObj() code=" + errorResult.getErrorCode() + " message=" + errorResult.getErrorMsg());
            }
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genFailObj(FailResult failResult) {
        if (OKLog.D) {
            OKLog.d(TAG, "genFailObj()");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (failResult == null) {
                jSONObject.put("code", -102);
                jSONObject.put("message", "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86");
                return jSONObject;
            }
            jSONObject.put("code", (int) failResult.getReplyCode());
            jSONObject.put("message", failResult.getMessage() + "");
            jSONObject.put("intVal", failResult.getIntVal());
            jSONObject.put("phoneNum", failResult.getStrVal() + "");
            if (OKLog.D) {
                OKLog.d(TAG, "genFailObj() code=" + ((int) failResult.getReplyCode()) + " message=" + failResult.getMessage() + " intVal=" + failResult.getIntVal());
            }
            if (failResult.getJumpResult() != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "genFailObj() token=" + failResult.getJumpResult().getToken() + " url=" + failResult.getJumpResult().getUrl());
                }
                jSONObject.put("token", failResult.getJumpResult().getToken() + "");
                jSONObject.put("url", failResult.getJumpResult().getUrl() + "");
            }
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    private static JSONObject genPregetMobileObj(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 0);
            jSONObject.put("phoneNum", str + "");
            jSONObject.put("operateType", MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication()) + "");
            if (OKLog.D) {
                OKLog.d(TAG, "genPregetMobileObj() phoneNum=" + str + " operateType=" + MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication()));
            }
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    private static JSONObject genSuccessObj(SuccessResult successResult) {
        if (OKLog.D) {
            OKLog.d(TAG, "genSuccessObj()");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 0);
            if (successResult != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "genSuccessObj()intVal=" + successResult.getIntVal() + "strVal=" + successResult.getStrVal());
                }
                jSONObject.put("intVal", successResult.getIntVal());
                jSONObject.put("strVal", successResult.getStrVal() + "");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static void getCaptchaSid(IRouterParams iRouterParams) {
        String str;
        if (OKLog.D) {
            OKLog.d(TAG, "getCaptchaSid() begin");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        sendAction("PopupLogin_VerifyCheck", "PopupLogin");
        try {
            String str2 = "";
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = "";
            } else {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString(Constant.KEY_COUNTRY_CODE);
                String optString2 = jSONObject.optString("phoneNum");
                type = jSONObject.optInt("type");
                if (OKLog.D) {
                    OKLog.d(TAG, "getCaptchaSid() getRouterParam() countryCode =" + optString + "phoneNum=" + optString2 + "type=" + type);
                }
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    iRouterParams.onCallBack(new JSONObject());
                }
                str = optString;
                str2 = optString2;
            }
            getSessionId(iRouterParams, type, str2, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    private static void getMsg(IRouterParams iRouterParams, String str, String str2, String str3, String str4) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "getMsg  countryCode = " + str2);
            }
            UserUtil.getWJLoginHelper().sendMsgCodeForPhoneNumLogin4JD(str, str2, str3, str4, new g(str2, iRouterParams, str3, str4));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getMsgSuccessObj(SuccessResult successResult, String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "getMsgSuccessObj()sid=" + str + "token=" + str2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 0);
            if (successResult != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "getMsgSuccessObj()intVal=" + successResult.getIntVal() + "strVal=" + successResult.getStrVal());
                }
                jSONObject.put("intVal", successResult.getIntVal());
                jSONObject.put("strVal", successResult.getStrVal() + "");
            }
            jSONObject.put("sid", str + "");
            jSONObject.put("token", str2 + "");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static void getSessionId(IRouterParams iRouterParams, int i2, String str, String str2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "getSessionId ");
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
            jSONObject.put(SignUpTable.TB_COLUMN_PHONE, str);
            UserUtil.getWJLoginHelper().getCaptchaSid(i2, jSONObject, new f(str2, iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getSidSuccessObj(int i2, String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "getSidSuccessObj() code =" + i2 + "sid=" + str + "token=" + str2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i2);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("sid", str + "");
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("token", str2 + "");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static Verify getVerify() {
        if (f11194verify == null) {
            f11194verify = Verify.getInstance();
        }
        if (OKLog.D) {
            Verify.setLog(true);
            Verify.testLocal(JdSdk.getInstance().getApplicationContext(), true);
        }
        return f11194verify;
    }

    private static void h5Back2App(IRouterParams iRouterParams, String str) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "h5Back2App()");
            }
            UserUtil.getWJLoginHelper().h5BackToApp(str, new n(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    public static JSONObject hasLogin(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "hasLogin begin");
        }
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (LoginUserBase.hasLogin()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "hasLogin() = true");
                    }
                    return genCommonObj(1, "\u5df2\u767b\u5f55");
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "hasLogin() = false");
                }
                return genCommonObj(0, "\u672a\u767b\u5f55");
            } catch (Exception e2) {
                e2.printStackTrace();
                return genCommonObj(0, "\u672a\u767b\u5f55");
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "router == null || router.getContext() == null");
        }
        return new JSONObject();
    }

    private static void initVerify(IRouterParams iRouterParams, Context context, String str, String str2, String str3) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "initVerify()");
            }
            uiHandler.post(new a(str, context, str3, str2, iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    public static boolean isJsPreLoad() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(PRE_LOAD, 0) == 1) {
                z = true;
            }
            if (OKLog.D) {
                OKLog.i(TAG, "preLoad = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    private static boolean isOpenCM() {
        String operateType2 = MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication());
        boolean isOpenChinaMobileLoginSwitch = CCFLoginUtil.isOpenChinaMobileLoginSwitch();
        if (OKLog.D) {
            OKLog.d(TAG, "isOpenCM =" + isOpenChinaMobileLoginSwitch + " operateType=" + operateType2);
        }
        return "CM".equals(operateType2) && isOpenChinaMobileLoginSwitch;
    }

    public static void jump2BusRegist(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "jump2BusRegist begin");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "jump2BusRegist router == null || router.getContext() == null");
            }
            iRouterParams.onCallBack(genCommonObj(3, "\u4f20\u5165\u53c2\u6570\u9519\u8bef"));
        }
        try {
            if (iRouterParams.getContext() instanceof Activity) {
                if (OKLog.D) {
                    OKLog.d(TAG, "jump2BusRegist \u5f00\u59cb\u6267\u884c\u8df3\u8f6c\u5230\u539f\u751f\u4f01\u4e1a\u6ce8\u518c");
                }
                if (!CCFLoginUtil.isOpenNativeBusinessRegist()) {
                    ToastUtils.longToast(iRouterParams.getContext(), "\u4f01\u4e1a\u6ce8\u518c\u6682\u65f6\u4e0d\u53ef\u7528");
                    return;
                }
                String optString = TextUtils.isEmpty(iRouterParams.getRouterParam()) ? "" : new JSONObject(iRouterParams.getRouterParam()).optString("companyRegistParam");
                Bundle bundle = new Bundle();
                bundle.putString("companyRegistParam", optString);
                if (OKLog.D) {
                    OKLog.d(TAG, "jump2BusRegist companyRegistParam=" + optString);
                }
                DeepLinkLoginHelper.startBusinessRegisterActivity(iRouterParams.getContext(), bundle, new d(iRouterParams), "busRegister");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (OKLog.D) {
                OKLog.d(TAG, "jump2BusRegist h5\u8df3\u8f6c\u5230\u539f\u751f\u4f01\u4e1a\u6ce8\u518c\u53d1\u751f\u4e86\u5f02\u5e38");
            }
            if (iRouterParams.getContext() instanceof Activity) {
                ToastUtils.longToast(iRouterParams.getContext(), "\u62b1\u6b49\uff0c\u4f7f\u7528\u4f01\u4e1a\u6ce8\u518c\u65f6\u53d1\u751f\u4e86\u5f02\u5e38");
            }
            iRouterParams.onCallBack(genCommonObj(0, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
        }
    }

    public static void loginH5BackToAppWithToken(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "loginH5BackToAppWithToken ");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        sendAction("PopupLogin_VerifyRisk", "PopupLogin");
        try {
            String str = "";
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("token");
                LoginConstans.FROM_ROUTER_BMODE = 0;
                LoginConstans.FROM_BMODE = 0;
                if (CCFLoginUtil.isOpenRouterBModel() && TextUtils.equals("1", jSONObject.optString(LoginConstans.NEED_REFRESH_MODE, "0"))) {
                    LoginConstans.FROM_ROUTER_BMODE = LoginConstans.REFRESH_MODE_VALUE;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "loginH5BackToAppWithToken token=" + optString);
                }
                if (TextUtils.isEmpty(optString)) {
                    iRouterParams.onCallBack(new JSONObject());
                }
                str = optString;
            }
            h5Back2App(iRouterParams, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    public static void loginOneClickRegister(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "loginOneClickRegister ");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("phoneNum");
            String optString2 = jSONObject.optString("token");
            LoginConstans.FROM_ROUTER_BMODE = 0;
            LoginConstans.FROM_BMODE = 0;
            if (CCFLoginUtil.isOpenRouterBModel() && TextUtils.equals("1", jSONObject.optString(LoginConstans.NEED_REFRESH_MODE, "0"))) {
                LoginConstans.FROM_ROUTER_BMODE = LoginConstans.REFRESH_MODE_VALUE;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "loginOneClickRegister token=" + optString2 + " phoneNum=" + optString);
            }
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            onekeyRegister(iRouterParams, optString, optString2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    public static void loginWithOneClick(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "loginWithOneClick ");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("operateType");
            LoginConstans.FROM_ROUTER_BMODE = 0;
            LoginConstans.FROM_BMODE = 0;
            if (CCFLoginUtil.isOpenRouterBModel() && TextUtils.equals("1", jSONObject.optString(LoginConstans.NEED_REFRESH_MODE, "0"))) {
                LoginConstans.FROM_ROUTER_BMODE = LoginConstans.REFRESH_MODE_VALUE;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "loginOneClickRegister localOperateType=" + optString);
            }
            if (TextUtils.isEmpty(optString)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            operateType = optString;
            onekeyLogin(iRouterParams, optString);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    private static void onekeyLogin(IRouterParams iRouterParams, String str) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "onekeyLogin");
            }
            if ("CU".equals(str)) {
                sendAction("PopupLogin_LocalLogin_CUCC_getAccessToken", "PopupLogin");
            } else if ("CT".equals(str)) {
                sendAction("PopupLogin_LocalLogin_CTCC_getAccessToken", "PopupLogin");
            } else {
                sendAction("PopupLogin_LocalLogin_getAccessToken", "PopupLogin");
            }
            UserUtil.getOneKeyLoginHelper().getAccessToken(new j(iRouterParams, str));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    private static void onekeyRegister(IRouterParams iRouterParams, String str, String str2) {
        try {
            if ("CT".equals(operateType)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "telecomOneKeyRegister()");
                }
                sendAction("PopupLogin_LocalLogin_Register_CTCC", "PopupLogin");
                UserUtil.getWJLoginHelper().telecomOneKeyRegister(str, str2, new k(iRouterParams));
            } else if ("CU".equals(operateType)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "unicomOneKeyRegister()");
                }
                sendAction("PopupLogin_LocalLogin_Register_CUCC", "PopupLogin");
                UserUtil.getWJLoginHelper().unicomOneKeyRegister(str, str2, new l(iRouterParams));
            } else if ("CM".equals(operateType)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "chinaMobileOneKeyRegister()");
                }
                sendAction("PopupLogin_LocalLogin_Register", "PopupLogin");
                UserUtil.getWJLoginHelper().chinaMobileOneKeyRegister(str, str2, new m(iRouterParams));
            } else {
                iRouterParams.onCallBack(genCommonObj(1, "\u53c2\u6570\u4f20\u9519"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(genCommonObj(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
            }
        }
    }

    public static JSONObject preGetPhoneNum(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "preGetPhoneNum() ");
        }
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (!MobileLoginUtil.canGoToTelecom() && !MobileLoginUtil.canGoToUnicom() && !isOpenCM()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "preGetPhoneNum() \uff01MobileLoginUtil.canGoToTelecom() \uff01MobileLoginUtil.canGoToUnicom() \uff01isOpenCM()");
                    }
                    return genCommonObj(1, "\u9884\u53d6\u53f7\u5931\u8d25");
                }
                if (!TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_OPERATETYPE) && !TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_PHONENUMBER)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "preGetPhoneNum() phoneNum =" + LoginConstans.ONEKEY_LOGIN_PHONENUMBER);
                    }
                    return genPregetMobileObj(LoginConstans.ONEKEY_LOGIN_PHONENUMBER);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "preGetPhoneNum() TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_OPERATETYPE) || TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_PHONENUMBER)");
                }
                return genCommonObj(1, "\u9884\u53d6\u53f7\u5931\u8d25");
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    private static void registbusRegisterFail(IBusinessRegist iBusinessRegist, String str) {
        BusinessRegistObserverManager.getInstance().registerRegistListener(iBusinessRegist, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendAction(String str, String str2) {
        JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), str, TAG, str2);
    }

    public static void sendMsgCode(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "sendMsgCode()");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        sendAction("PopupLogin_MessageLogin_MessageSent", "PopupLogin");
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString(Constant.KEY_COUNTRY_CODE);
            String optString2 = jSONObject.optString("phoneNum");
            String optString3 = jSONObject.optString("sid");
            String optString4 = jSONObject.optString("token");
            if (OKLog.D) {
                OKLog.d(TAG, "checkMsgCode() countryCode=" + optString + " phoneNum=" + optString2 + " sid=" + optString3 + " token=" + optString4);
            }
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            getMsg(iRouterParams, optString2, optString, optString3, optString4);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    public static void showDialogLogin(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "dialogLogin begin");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "router == null || router.getContext() == null");
            }
            iRouterParams.onCallBack(genCommonObj(1, "\u4f20\u5165\u53c2\u6570\u9519\u8bef"));
        }
        try {
            if (LoginUserBase.hasLogin()) {
                iRouterParams.onCallBack(genCommonObj(2, "\u5df2\u767b\u5f55\u6210\u529f\uff0c\u65e0\u9700\u5f39\u6846\u767b\u5f55"));
            }
            try {
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    pageID = new JSONObject(iRouterParams.getRouterParam()).optString("pageID");
                }
            } catch (JSONException unused) {
            }
            uiHandler.post(new c(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams.getContext() instanceof Activity) {
                DeepLinkLoginHelper.startLoginActivity(iRouterParams.getContext(), null);
            }
            iRouterParams.onCallBack(genCommonObj(1, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"));
        }
    }

    public static void verify(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "verify()");
        }
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(new JSONObject());
        }
        try {
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString(Constant.KEY_COUNTRY_CODE);
            String optString2 = jSONObject.optString("phoneNum");
            String optString3 = jSONObject.optString("sid");
            if (OKLog.D) {
                OKLog.d(TAG, "verify() countryCode=" + optString + " phoneNum=" + optString2 + " sid=" + optString3);
            }
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
                iRouterParams.onCallBack(new JSONObject());
            }
            initVerify(iRouterParams, iRouterParams.getContext(), optString3, optString2, optString);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iRouterParams != null) {
                iRouterParams.onCallBack(new JSONObject());
            }
        }
    }

    public static JSONObject verifyPreload(IRouterParams iRouterParams) {
        if (OKLog.D) {
            OKLog.d(TAG, "verifyPreload begin");
        }
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (isJsPreLoad()) {
                    proload = Verify.preLoad(iRouterParams.getContext());
                }
                return genCommonObj(1, "\u9a8c\u8bc1\u7801\u9884\u52a0\u8f7d");
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "router == null || router.getContext() == null");
        }
        return new JSONObject();
    }
}
