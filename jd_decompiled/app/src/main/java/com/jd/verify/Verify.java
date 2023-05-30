package com.jd.verify;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import com.jd.verify.View.IView;
import com.jd.verify.View.e;
import com.jd.verify.model.IninVerifyInfo;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class Verify {
    public static final String CHINESE = "zh";
    public static final String ENGLISH = "en";
    public static final String HOLLAND = "nl";
    public static final int NO_VERIFY = 0;
    public static final int SHOW_BUTTON_CLICK = 1;
    public static final int SHOW_BUTTON_SLIDE = 2;
    public static final String THAILAND = "th";
    private VerifyExtendProxy a;
    private VerifyPrivacyInfoProxy b;

    /* renamed from: c */
    private VerifyParamProxy f7097c;
    private h d;

    /* renamed from: f */
    private com.jd.verify.View.e f7099f;

    /* renamed from: h */
    private com.jd.verify.View.a f7101h;

    /* renamed from: e */
    private Handler f7098e = new Handler(Looper.getMainLooper());

    /* renamed from: g */
    private boolean f7100g = false;

    /* renamed from: i */
    private boolean f7102i = false;

    /* renamed from: j */
    private com.jd.verify.j.c f7103j = com.jd.verify.j.c.a();

    /* renamed from: k */
    private DialogInterface.OnDismissListener f7104k = new d();

    /* renamed from: l */
    private e f7105l = new e(this, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ EmbedVerifyView a;
        final /* synthetic */ Context b;

        /* renamed from: c */
        final /* synthetic */ String f7106c;
        final /* synthetic */ CallBack d;

        /* renamed from: e */
        final /* synthetic */ String f7107e;

        /* renamed from: f */
        final /* synthetic */ String f7108f;

        a(Verify verify2, EmbedVerifyView embedVerifyView, Context context, String str, CallBack callBack, String str2, String str3) {
            this.a = embedVerifyView;
            this.b = context;
            this.f7106c = str;
            this.d = callBack;
            this.f7107e = str2;
            this.f7108f = str3;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            com.jd.verify.j.d.a("\u5d4c\u5165\u5f0f\u9a8c\u8bc1\u7801\u5f00\u59cb\u52a0\u8f7d");
            this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            int width = (int) (this.a.getWidth() / this.b.getResources().getDisplayMetrics().density);
            String str = this.f7106c;
            if (str == null) {
                str = "";
            }
            new com.jd.verify.b().b(this.b, this.a, this.d, this.f7107e, this.f7108f, "{\"account\":\"" + str + "\",\"countryCode\":\"86\",\"display\":\"embed\",\"width\":\"" + width + "\"}", "");
        }
    }

    /* loaded from: classes18.dex */
    public class b implements Runnable {
        final /* synthetic */ CallBack a;
        final /* synthetic */ Context b;

        /* renamed from: c */
        final /* synthetic */ String f7109c;
        final /* synthetic */ String d;

        /* renamed from: e */
        final /* synthetic */ com.jd.verify.j.a f7110e;

        /* renamed from: f */
        final /* synthetic */ IView f7111f;

        /* renamed from: g */
        final /* synthetic */ String f7112g;

        /* renamed from: h */
        final /* synthetic */ String f7113h;

        b(CallBack callBack, Context context, String str, String str2, com.jd.verify.j.a aVar, IView iView, String str3, String str4) {
            Verify.this = r1;
            this.a = callBack;
            this.b = context;
            this.f7109c = str;
            this.d = str2;
            this.f7110e = aVar;
            this.f7111f = iView;
            this.f7112g = str3;
            this.f7113h = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Verify.this.f7100g) {
                CallBack callBack = this.a;
                if (callBack != null && (callBack instanceof InterceptCallback)) {
                    ((InterceptCallback) callBack).intercept();
                }
                com.jd.verify.j.d.a("\u4e0a\u4e00\u4e2a\u9a8c\u8bc1\u7801\u8fd8\u5728\u6267\u884c\u4e2d");
                return;
            }
            Verify.this.f7100g = true;
            Context context = this.b;
            if (context != null) {
                Verify.this.a(context);
                if (!com.jd.verify.j.b.l(this.b)) {
                    com.jd.verify.j.d.b("initTruly \u7f51\u7edc\u4e0d\u53ef\u7528");
                    CallBack callBack2 = this.a;
                    if (callBack2 != null && (callBack2 instanceof ShowCapCallWithLocalErrorback)) {
                        ((ShowCapCallWithLocalErrorback) callBack2).withLocalError(2);
                    } else {
                        com.jd.verify.j.h.a(this.b.getString(R.string.verify_fail));
                        CallBack callBack3 = this.a;
                        if (callBack3 != null && (callBack3 instanceof ShowCapCallback)) {
                            ((ShowCapCallback) callBack3).loadFail();
                        }
                    }
                    Verify.this.f7100g = false;
                    return;
                } else if (TextUtils.isEmpty(this.f7109c)) {
                    com.jd.verify.j.d.b("initTruly session id \u4e3a\u7a7a");
                    CallBack callBack4 = this.a;
                    if (callBack4 != null && (callBack4 instanceof ShowCapCallWithLocalErrorback)) {
                        ((ShowCapCallWithLocalErrorback) callBack4).withLocalError(3);
                    } else {
                        com.jd.verify.j.h.a(this.b.getString(R.string.verify_fail));
                        CallBack callBack5 = this.a;
                        if (callBack5 != null && (callBack5 instanceof ShowCapCallback)) {
                            ((ShowCapCallback) callBack5).loadFail();
                        }
                    }
                    Verify.this.f7100g = false;
                    return;
                } else {
                    String str = this.d;
                    if (TextUtils.isEmpty(str)) {
                        str = com.jd.verify.j.b.b();
                    }
                    com.jd.verify.j.d.a("\u5f00\u59cb\u52a0\u8f7d\u9a8c\u8bc1\u7801 : isVerifying = [" + Verify.this.f7100g + "]");
                    Verify.this.a(this.f7110e, this.f7109c, this.b, str, this.a, this.f7111f, this.f7112g, this.f7113h);
                    return;
                }
            }
            com.jd.verify.j.d.b("activity context is null");
            CallBack callBack6 = this.a;
            if (callBack6 == null || !(callBack6 instanceof ShowCapCallWithLocalErrorback)) {
                return;
            }
            ((ShowCapCallWithLocalErrorback) callBack6).withLocalError(1);
        }
    }

    /* loaded from: classes18.dex */
    public class c implements e.a {
        final /* synthetic */ IView a;

        c(Verify verify2, IView iView) {
            this.a = iView;
        }

        @Override // com.jd.verify.View.e.a
        public void a(int i2, String str) {
            this.a.checkFinish(i2, str);
        }

        @Override // com.jd.verify.View.e.a
        public void a(int i2) {
            this.a.setCurrentType(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class d implements DialogInterface.OnDismissListener {
        d() {
            Verify.this = r1;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            Verify.this.f7100g = false;
        }
    }

    /* loaded from: classes18.dex */
    public class e implements com.jd.verify.common.a {
        private CallBack a;

        private e() {
            Verify.this = r1;
        }

        public com.jd.verify.common.a a(CallBack callBack) {
            this.a = callBack;
            return this;
        }

        @Override // com.jd.verify.common.a
        public void b(int i2) {
            com.jd.verify.j.d.a("Verify NotifyListener notifyLocalError");
            com.jd.verify.j.g.a(0);
            Verify.this.f7100g = false;
            CallBack callBack = this.a;
            if (callBack != null && (callBack instanceof ShowCapCallWithLocalErrorback)) {
                com.jd.verify.j.d.a("Verify ShowCapCallWithLocalErrorback notifyLocalError");
                ((ShowCapCallWithLocalErrorback) this.a).withLocalError(i2);
            }
            Verify.this.free();
        }

        @Override // com.jd.verify.common.a
        public void c() {
            com.jd.verify.j.d.a("Verify  NotifyListener notifySecVerify");
            CallBack callBack = this.a;
            if (callBack != null && (callBack instanceof ShowCapCallback)) {
                ((ShowCapCallback) callBack).showCap();
            }
            Verify.this.f7100g = false;
        }

        @Override // com.jd.verify.common.a
        public void d() {
            Verify.this.f7100g = false;
            try {
                com.jd.verify.j.d.a("Verify  NotifyListener notifyCancel");
                if (this.a instanceof ShowCapWithCancelCallback) {
                    com.jd.verify.j.d.a("Verify  mCallBack==ShowCapWithCancelCallback");
                    ((ShowCapWithCancelCallback) this.a).onDialogCancel();
                }
                com.jd.verify.j.g.a(0);
                if (!com.jd.verify.a.b()) {
                    if (Verify.this.f7099f != null) {
                        Verify.this.f7099f.dismiss();
                        return;
                    }
                    return;
                }
                Verify.this.free();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* synthetic */ e(Verify verify2, a aVar) {
            this();
        }

        @Override // com.jd.verify.common.a
        public void a(int i2) {
            com.jd.verify.j.d.a("Verify  NotifyListener notifyOver");
            Verify.this.f7100g = false;
            CallBack callBack = this.a;
            if (callBack != null && (callBack instanceof ShowCapCallback)) {
                com.jd.verify.j.d.b("ShowCapCallback loadFail code=" + i2);
                ((ShowCapCallback) this.a).loadFail();
            }
            Verify.this.free();
        }

        @Override // com.jd.verify.common.a
        public void a(IninVerifyInfo ininVerifyInfo) {
            com.jd.verify.j.d.a("Verify NotifyListener notifySuccess");
            com.jd.verify.j.g.a(0);
            CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onSuccess(ininVerifyInfo);
            }
            Verify.this.f7100g = false;
        }

        @Override // com.jd.verify.common.a
        public void b() {
            com.jd.verify.j.d.a("Verify  NotifyListener notifyInvalidSessiongId");
            CallBack callBack = this.a;
            if (callBack != null) {
                callBack.invalidSessiongId();
            }
            Verify.this.f7100g = false;
            Verify.this.free();
        }

        @Override // com.jd.verify.common.a
        public void a() {
            com.jd.verify.j.d.a("Verify  NotifyListener notifyonSSLError");
            CallBack callBack = this.a;
            if (callBack != null && (callBack instanceof SSLDialogCallback)) {
                ((SSLDialogCallback) callBack).onSSLError();
            }
            Verify.this.f7100g = false;
            Verify.this.free();
        }
    }

    private Verify() {
        setInternationalURL("");
    }

    public static Verify getInstance() {
        com.jd.verify.a.b(true);
        return new Verify();
    }

    public static PreLoader preLoad(Context context) {
        return preLoad(context, com.jd.verify.j.c.a().c());
    }

    public static void setDebug(boolean z) {
        com.jd.verify.a.c(z);
    }

    public static void setIsDismissCreateNewDialog(boolean z) {
        com.jd.verify.a.a(z);
    }

    public static void setLog(boolean z) {
        com.jd.verify.j.d.a(z);
    }

    public static void testLocal(Context context, boolean z) {
        com.jd.verify.e.a(context, z);
    }

    public void free() {
        try {
            com.jd.verify.j.f.b(false);
            com.jd.verify.View.e eVar = this.f7099f;
            if (eVar != null) {
                if (eVar.d() != null && "0" == this.f7099f.d().a()) {
                    this.f7099f.c();
                }
                this.f7099f.j();
                this.f7099f.dismiss();
                this.f7099f.b();
                this.f7099f = null;
            }
            com.jd.verify.View.a aVar = this.f7101h;
            if (aVar != null) {
                aVar.dismiss();
                this.f7101h = null;
            }
            this.f7100g = false;
            com.jd.verify.j.g.a(0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public VerifyParamProxy getParamProxy() {
        return this.f7097c;
    }

    public VerifyPrivacyInfoProxy getPrivacyInfoProxy() {
        return this.b;
    }

    public VerifyExtendProxy getProxy() {
        return this.a;
    }

    public String getTinyProxy() {
        h hVar = this.d;
        if (hVar != null) {
            String a2 = hVar.a();
            com.jd.verify.j.d.a("JDVerify.Verify", "tiny=" + a2);
            return TextUtils.isEmpty(a2) ? "0" : a2;
        }
        return "0";
    }

    @Deprecated
    public void init(String str, Context context, String str2, CallBack callBack, IView iView) {
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str2, callBack, iView, "", "");
    }

    public void initEmbed(Context context, EmbedVerifyView embedVerifyView, String str, String str2, String str3, CallBack callBack) {
        com.jd.verify.j.d.a("initEmbed \u5d4c\u5165\u5f0f\u9a8c\u8bc1\u7801");
        if (embedVerifyView == null) {
            return;
        }
        a(context);
        embedVerifyView.setVisibility(8);
        embedVerifyView.setVisibility(0);
        embedVerifyView.getViewTreeObserver().addOnGlobalLayoutListener(new a(this, embedVerifyView, context, str3, callBack, str, str2));
    }

    public void initInternational(String str, Context context, String str2, CallBack callBack, String str3, String str4) {
        String str5 = str3 == null ? "" : str3;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str5);
            try {
                a(jSONObject);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                com.jd.verify.j.a aVar = new com.jd.verify.j.a();
                aVar.a("1");
                b(aVar, str, context, str2, callBack, null, jSONObject.toString(), str4);
            }
        } catch (Exception e3) {
            e = e3;
        }
        com.jd.verify.j.a aVar2 = new com.jd.verify.j.a();
        aVar2.a("1");
        b(aVar2, str, context, str2, callBack, null, jSONObject.toString(), str4);
    }

    public void isOpenSensor(boolean z) {
        com.jd.verify.j.f.b(z);
    }

    public void isShowToast(boolean z) {
        com.jd.verify.a.b(z);
    }

    public Verify setInternationalURL(String str) {
        this.f7103j.a(str);
        return this;
    }

    public Verify setLoading(boolean z) {
        this.f7102i = z;
        return this;
    }

    public void setParamProxy(VerifyParamProxy verifyParamProxy) {
        this.f7097c = verifyParamProxy;
    }

    public void setPrivacyInfoProxy(VerifyPrivacyInfoProxy verifyPrivacyInfoProxy) {
        if (verifyPrivacyInfoProxy != null) {
            com.jd.verify.j.b.c(verifyPrivacyInfoProxy.getPrivacyDeviceBrand() == null ? "" : verifyPrivacyInfoProxy.getPrivacyDeviceBrand());
            com.jd.verify.j.b.d(verifyPrivacyInfoProxy.getPrivacyDeviceModel() == null ? "" : verifyPrivacyInfoProxy.getPrivacyDeviceModel());
            com.jd.verify.j.b.b(verifyPrivacyInfoProxy.getPrivacyAndroidId() == null ? "" : verifyPrivacyInfoProxy.getPrivacyAndroidId());
            com.jd.verify.j.b.e(verifyPrivacyInfoProxy.getPrivacyLatitude() == null ? "" : verifyPrivacyInfoProxy.getPrivacyLatitude());
            com.jd.verify.j.b.f(verifyPrivacyInfoProxy.getPrivacyLongitude() == null ? "" : verifyPrivacyInfoProxy.getPrivacyLongitude());
            com.jd.verify.j.b.g(verifyPrivacyInfoProxy.getPrivacyScreen() == null ? "" : verifyPrivacyInfoProxy.getPrivacyScreen());
            com.jd.verify.j.b.h(verifyPrivacyInfoProxy.getPrivateOSRelease() != null ? verifyPrivacyInfoProxy.getPrivateOSRelease() : "");
        }
    }

    public void setProxy(VerifyExtendProxy verifyExtendProxy) {
        this.a = verifyExtendProxy;
    }

    public void setTinyProxy(h hVar) {
        this.d = hVar;
    }

    private void b(com.jd.verify.j.a aVar, String str, Context context, String str2, CallBack callBack, IView iView, String str3, String str4) {
        com.jd.verify.j.d.a("\u5f00\u59cb\u521d\u59cb\u5316 : session_id = [" + str + "], context = [" + context + "], udid = [" + str2 + "], callBack = [" + callBack + "],account = [" + str3 + "], language = [" + str4 + "]");
        this.f7098e.post(new b(callBack, context, str, str2, aVar, iView, str3, str4));
    }

    public static PreLoader preLoad(Context context, String str) {
        PreLoader preLoader = new PreLoader();
        try {
            if (!com.jd.verify.j.e.a(context, "vf_preloadFinish", false)) {
                preLoader.a(context, str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return preLoader;
    }

    public void init(String str, Context context, String str2, String str3, CallBack callBack, String str4) {
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str3, callBack, null, "", "");
    }

    public void a(Context context) {
        if (context != null) {
            try {
                if (com.jd.verify.d.a == null) {
                    com.jd.verify.d.a = context.getApplicationContext();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private String b() {
        VerifyParamProxy verifyParamProxy = this.f7097c;
        return (verifyParamProxy == null || verifyParamProxy.getVerifyParams() == null) ? "" : this.f7097c.getVerifyParams();
    }

    public void init(String str, Context context, String str2, String str3, CallBack callBack) {
        if (str3 == null) {
            str3 = "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str3);
            a(jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str2, callBack, null, jSONObject.toString(), "");
    }

    public void a(com.jd.verify.j.a aVar, String str, Context context, String str2, CallBack callBack, IView iView, String str3, String str4) {
        com.jd.verify.j.d.a("\u5f00\u59cb\u9a8c\u8bc1\uff08doVerify\uff09: session_id = [" + str + "], context = [" + context + "], udid = [" + str2 + "], callBack = [" + callBack + "], account = [" + str3 + "], language = [" + str4 + "]");
        com.jd.verify.View.e eVar = this.f7099f;
        if (eVar != null) {
            eVar.j();
            this.f7099f.dismiss();
            this.f7099f = null;
        }
        com.jd.verify.View.a aVar2 = this.f7101h;
        if (aVar2 != null) {
            aVar2.dismiss();
            this.f7101h = null;
        }
        try {
            this.f7099f = new com.jd.verify.View.e(context);
            if ("0" == aVar.a()) {
                this.f7099f.c();
            }
            this.f7099f.a(this.f7103j);
            this.f7099f.setOnDismissListener(this.f7104k);
            this.f7105l.a(callBack);
            this.f7099f.e(str2).c(str).a(str3).a(callBack).a(this.f7105l).b(str4);
            this.f7099f.f(a());
            this.f7099f.d(getTinyProxy());
            this.f7099f.a(aVar);
            com.jd.verify.model.a aVar3 = new com.jd.verify.model.a();
            if (this.f7102i) {
                com.jd.verify.View.a aVar4 = new com.jd.verify.View.a(context);
                this.f7101h = aVar4;
                aVar4.show();
                this.f7099f.a(this.f7101h);
            }
            if (iView != null) {
                aVar3.a("1");
                iView.setDialg(this.f7099f);
                iView.setFinishListener(callBack);
                iView.setNotifyListener(this.f7105l);
                this.f7099f.a(new c(this, iView));
            } else {
                aVar3.a("0");
            }
            this.f7099f.a(aVar3);
            this.f7099f.i();
        } catch (Exception e2) {
            com.jd.verify.j.d.b(e2.getLocalizedMessage());
            if (callBack != null && (callBack instanceof ShowCapCallWithLocalErrorback)) {
                ((ShowCapCallWithLocalErrorback) callBack).withLocalError(8);
            } else {
                callBack.onFail("WebView\u521d\u59cb\u5316\u5931\u8d25");
            }
            this.f7100g = false;
        }
    }

    public void initInternational(String str, Context context, String str2, CallBack callBack, String str3) {
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str2, callBack, null, "", str3);
    }

    public void initInternational(String str, Context context, String str2, CallBack callBack, String str3, String str4, String str5) {
        String str6 = str4 == null ? "" : str4;
        String str7 = str3 != null ? str3 : "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str6);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, str7);
            try {
                a(jSONObject);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                com.jd.verify.j.a aVar = new com.jd.verify.j.a();
                aVar.a("1");
                b(aVar, str, context, str2, callBack, null, jSONObject.toString(), str5);
            }
        } catch (Exception e3) {
            e = e3;
        }
        com.jd.verify.j.a aVar2 = new com.jd.verify.j.a();
        aVar2.a("1");
        b(aVar2, str, context, str2, callBack, null, jSONObject.toString(), str5);
    }

    public void init(String str, Context context, String str2, String str3, String str4, CallBack callBack) {
        String str5 = str4 == null ? "" : str4;
        String str6 = str3 != null ? str3 : "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str5);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, str6);
            try {
                a(jSONObject);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                com.jd.verify.j.a aVar = new com.jd.verify.j.a();
                aVar.a("1");
                b(aVar, str, context, str2, callBack, null, jSONObject.toString(), "");
            }
        } catch (Exception e3) {
            e = e3;
        }
        com.jd.verify.j.a aVar2 = new com.jd.verify.j.a();
        aVar2.a("1");
        b(aVar2, str, context, str2, callBack, null, jSONObject.toString(), "");
    }

    public void init(String str, String str2, Context context, String str3, String str4, String str5, CallBack callBack) {
        String str6 = str5 == null ? "" : str5;
        String str7 = str4 != null ? str4 : "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str6);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, str7);
            jSONObject.put("autoClose", str);
            try {
                a(jSONObject);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                com.jd.verify.j.a aVar = new com.jd.verify.j.a();
                aVar.a(str);
                b(aVar, str2, context, str3, callBack, null, jSONObject.toString(), "");
            }
        } catch (Exception e3) {
            e = e3;
        }
        com.jd.verify.j.a aVar2 = new com.jd.verify.j.a();
        aVar2.a(str);
        b(aVar2, str2, context, str3, callBack, null, jSONObject.toString(), "");
    }

    @Deprecated
    public void init(String str, Context context, String str2, CallBack callBack, IView iView, String str3) {
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str2, callBack, iView, "", str3);
    }

    @Deprecated
    public void init(String str, Context context, String str2, CallBack callBack, IView iView, String str3, String str4) {
        String str5 = str3 == null ? "" : str3;
        com.jd.verify.j.a aVar = new com.jd.verify.j.a();
        aVar.a("1");
        b(aVar, str, context, str2, callBack, iView, str5, str4);
    }

    private String a() {
        VerifyExtendProxy verifyExtendProxy = this.a;
        if (verifyExtendProxy != null) {
            String elderUemps = verifyExtendProxy.getElderUemps();
            com.jd.verify.j.d.a("JDVerify.Verify", "uepms=" + elderUemps);
            return TextUtils.isEmpty(elderUemps) ? "0" : elderUemps;
        }
        return "0";
    }

    private void a(JSONObject jSONObject) {
        try {
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject(b2);
            String optString = jSONObject2.optString("pin");
            String optString2 = jSONObject2.optString("eid");
            if (TextUtils.isEmpty(optString)) {
                optString = "";
            }
            jSONObject.put("pin", optString);
            if (TextUtils.isEmpty(optString2)) {
                optString2 = "";
            }
            jSONObject.put("eid", optString2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
