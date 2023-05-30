package com.jd.verify;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import org.json.JSONException;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class EmbedJSInterface {
    private CallBack a;
    private EmbedVerifyView b;

    /* renamed from: c */
    private Context f7087c;
    private String d;

    /* renamed from: e */
    private Handler f7088e = new Handler(Looper.getMainLooper());

    /* renamed from: f */
    private com.jd.verify.common.a f7089f;

    /* renamed from: g */
    private String f7090g;

    /* renamed from: h */
    private com.jd.verify.model.a f7091h;

    /* renamed from: i */
    private String f7092i;

    /* renamed from: j */
    private VerifyPrivacyInfoProxy f7093j;

    /* loaded from: classes18.dex */
    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            EmbedJSInterface.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject;
            if (EmbedJSInterface.this.b != null) {
                EmbedJSInterface.this.b.setVisibility(8);
            }
            try {
                jSONObject = new JSONObject(this.a);
            } catch (JSONException unused) {
                jSONObject = new JSONObject();
            }
            if (!TextUtils.isEmpty(jSONObject.optString("vt"))) {
                if (EmbedJSInterface.this.a != null) {
                    EmbedJSInterface.this.a.onSuccess(new IninVerifyInfo(jSONObject));
                    com.jd.verify.j.e.b(EmbedJSInterface.this.f7087c, "vf_preloadFinish", true);
                    return;
                }
                return;
            }
            Toast.makeText(EmbedJSInterface.this.f7087c, jSONObject.optString("msg"), 0).show();
        }
    }

    /* loaded from: classes18.dex */
    class b implements Runnable {
        final /* synthetic */ String a;

        b(String str) {
            EmbedJSInterface.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONObject jSONObject = new JSONObject(this.a);
                String optString = jSONObject.optString("interfaceName");
                String optString2 = jSONObject.optString("msg");
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = EmbedJSInterface.this.f7087c.getString(R.string.verify_fail);
                }
                IninVerifyInfo ininVerifyInfo = new IninVerifyInfo(jSONObject);
                if (1 == ininVerifyInfo.getErrorType()) {
                    Toast.makeText(EmbedJSInterface.this.f7087c, optString2, 0).show();
                } else {
                    if (ininVerifyInfo.getCode() == 16801 && ininVerifyInfo.getsCode() == 12101) {
                        Toast.makeText(EmbedJSInterface.this.f7087c, optString2, 0).show();
                        if (EmbedJSInterface.this.f7089f != null) {
                            EmbedJSInterface.this.f7089f.a(ininVerifyInfo.getsCode());
                        }
                    }
                    if (WebPerfManager.FP.equals(optString)) {
                        Toast.makeText(EmbedJSInterface.this.f7087c, optString2, 0).show();
                        if (EmbedJSInterface.this.f7089f != null) {
                            EmbedJSInterface.this.f7089f.a(ininVerifyInfo.getsCode());
                        }
                    }
                }
                if (EmbedJSInterface.this.a == null || 16808 != ininVerifyInfo.getCode()) {
                    return;
                }
                if (EmbedJSInterface.this.f7089f != null) {
                    EmbedJSInterface.this.f7089f.a(ininVerifyInfo.getsCode());
                }
                EmbedJSInterface.this.a.invalidSessiongId();
                Toast.makeText(EmbedJSInterface.this.f7087c, optString2, 0).show();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes18.dex */
    class c implements Runnable {
        c() {
            EmbedJSInterface.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (EmbedJSInterface.this.b != null) {
                    EmbedJSInterface.this.b.create();
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes18.dex */
    class d implements Runnable {
        final /* synthetic */ int a;

        d(int i2) {
            EmbedJSInterface.this = r1;
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                EmbedJSInterface.this.b.setIsLoadFinish(true);
                EmbedJSInterface.this.b.getWebView().setLayerType(2, null);
            } catch (Exception unused) {
            }
            if (EmbedJSInterface.this.b != null) {
                EmbedJSInterface.this.b.reSize(this.a);
                EmbedJSInterface.this.b.setVisibility(0);
                if (EmbedJSInterface.this.a != null && (EmbedJSInterface.this.a instanceof ShowCapCallback)) {
                    ((ShowCapCallback) EmbedJSInterface.this.a).showCap();
                }
            }
            if (EmbedJSInterface.this.f7089f != null) {
                EmbedJSInterface.this.f7089f.c();
            }
        }
    }

    /* loaded from: classes18.dex */
    class e implements Runnable {
        e(EmbedJSInterface embedJSInterface) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* loaded from: classes18.dex */
    class f implements Runnable {
        final /* synthetic */ String a;

        f(String str) {
            EmbedJSInterface.this = r1;
            this.a = str;
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0073 -> B:36:0x0076). Please submit an issue!!! */
        @Override // java.lang.Runnable
        public void run() {
            if (EmbedJSInterface.this.a != null) {
                try {
                    com.jd.verify.j.d.a("JDVerify.Embed", "WebThread:" + Thread.currentThread().getId());
                    IninVerifyInfo ininVerifyInfo = new IninVerifyInfo(new JSONObject(this.a));
                    com.jd.verify.j.d.a("JDVerify.Embed", "MainThread:" + Thread.currentThread().getId());
                    if (6 == ininVerifyInfo.getTp()) {
                        EmbedJSInterface.this.a.showButton(1);
                    } else if (5 == ininVerifyInfo.getTp()) {
                        EmbedJSInterface.this.a.showButton(2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            try {
                EmbedJSInterface.this.b.setIsLoadFinish(true);
            } catch (Exception unused) {
            }
        }
    }

    public EmbedJSInterface(Context context, CallBack callBack, EmbedVerifyView embedVerifyView, String str, String str2, com.jd.verify.model.a aVar, com.jd.verify.common.a aVar2, String str3) {
        this.a = callBack;
        this.b = embedVerifyView;
        this.f7087c = context;
        this.d = str;
        this.f7089f = aVar2;
        this.f7090g = str2;
        this.f7091h = aVar;
        this.f7092i = str3;
    }

    @JavascriptInterface
    public void appCheck() {
        com.jd.verify.j.d.a("JDVerify.Embed", "appCheck");
    }

    @JavascriptInterface
    public String appConfig() {
        com.jd.verify.j.d.a("JDVerify.Embed", "appConfig");
        return this.d;
    }

    @JavascriptInterface
    public void captchaType(String str) {
        com.jd.verify.j.d.a("JDVerify.Embed", "captchaType");
        this.f7088e.post(new f(str));
    }

    @JavascriptInterface
    public void closeWebview() {
        com.jd.verify.j.d.a("JDVerify.Embed", "closeWebview");
        this.f7088e.post(new e(this));
    }

    @JavascriptInterface
    public String deviceInfo() {
        com.jd.verify.j.d.a("JDVerify.Embed", "deviceInfo");
        return a();
    }

    @JavascriptInterface
    public String getAddtion() {
        com.jd.verify.j.d.a("JDVerify.Embed", "getAddtion");
        if (this.f7091h == null) {
            com.jd.verify.j.d.a("JDVerify.Embed", "addtionParam is null");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isEmbedded", this.f7091h.a());
        } catch (JSONException unused) {
            com.jd.verify.j.d.a("JDVerify.Embed", "getAddtion exception");
        }
        com.jd.verify.j.d.a("JDVerify.Embed", "getAddtion = " + jSONObject.toString());
        return jSONObject.toString();
    }

    @JavascriptInterface
    public String getFp() {
        com.jd.verify.j.d.a("JDVerify.Embed", "getFp");
        return com.jd.verify.j.e.a(this.f7087c);
    }

    public VerifyPrivacyInfoProxy getPrivacyInfoProxy() {
        return this.f7093j;
    }

    @JavascriptInterface
    public String language() {
        com.jd.verify.j.d.a("JDVerify.Embed", "language:" + this.f7092i);
        return this.f7092i;
    }

    @JavascriptInterface
    public void log(String str) {
        com.jd.verify.j.d.a("JDVerify.Embed", str);
    }

    @JavascriptInterface
    public void onFailure(String str) {
        com.jd.verify.j.d.a("JDVerify.Embed", "onFailure: " + str);
        this.f7088e.post(new b(str));
    }

    @JavascriptInterface
    public void onLoad(String str) {
        com.jd.verify.j.d.a("JDVerify.Embed", "onLoad:" + str);
        this.f7088e.post(new c());
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        com.jd.verify.j.d.a("JDVerify.Embed", "onSuccess: " + str);
        this.f7088e.post(new a(str));
    }

    @JavascriptInterface
    public String readyCheck() {
        JSONObject jSONObject = new JSONObject();
        try {
            com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
            b2.a(this.f7087c);
            jSONObject.put("sen", b2.a().toString());
            com.jd.verify.j.d.a(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        com.jd.verify.j.d.a("readyCheck\uff1a" + jSONObject2);
        return jSONObject2;
    }

    @JavascriptInterface
    public void setFp(String str) {
        com.jd.verify.j.e.a(this.f7087c, str);
        com.jd.verify.j.d.a("JDVerify.Embed", "setFp");
    }

    public void setPrivacyInfoProxy(VerifyPrivacyInfoProxy verifyPrivacyInfoProxy) {
        this.f7093j = verifyPrivacyInfoProxy;
    }

    @JavascriptInterface
    public void showWebviewCaptcha(String str) {
        int i2;
        com.jd.verify.j.d.a("JDVerify.Embed", "showWebviewCaptcha");
        try {
            i2 = new JSONObject(str).optInt("height");
        } catch (JSONException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        this.f7088e.post(new d(i2));
    }

    @JavascriptInterface
    public void startCheck() {
        com.jd.verify.j.d.a("startCheck\uff1a");
        com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
        b2.a(this.f7087c);
        b2.c();
    }

    private String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.f7090g);
            jSONObject.put("din", "");
            jSONObject.put("dnm", "");
            jSONObject.put("dbn", com.jd.verify.j.b.i());
            jSONObject.put("did", com.jd.verify.j.b.b());
            jSONObject.put("dmd", com.jd.verify.j.b.j());
            jSONObject.put("anm", com.jd.verify.j.b.b(this.f7087c));
            jSONObject.put("avs", com.jd.verify.j.b.i(this.f7087c));
            jSONObject.put("abd", com.jd.verify.j.b.h(this.f7087c) + "");
            jSONObject.put("abu", com.jd.verify.j.b.g(this.f7087c));
            jSONObject.put("os", "android");
            jSONObject.put("osv", com.jd.verify.j.b.l());
            jSONObject.put("sdv", "5.3.0");
            jSONObject.put("lan", com.jd.verify.j.b.d(this.f7087c));
            jSONObject.put("lns", "");
            jSONObject.put("tzo", com.jd.verify.j.b.d());
            jSONObject.put("tsp", com.jd.verify.j.b.s(this.f7087c));
            jSONObject.put("pt", "android");
            jSONObject.put(PerformanceManager.CUP, com.jd.verify.j.b.c());
            jSONObject.put(PerformanceManager.MEM_TOTAL, com.jd.verify.j.b.j(this.f7087c));
            jSONObject.put("lbs", com.jd.verify.j.b.f());
            jSONObject.put("ua", "");
            jSONObject.put("ed", "");
            jSONObject.put("scr", b());
            jSONObject.put("gyr", com.jd.verify.j.b.q(this.f7087c));
            jSONObject.put("dir", com.jd.verify.j.b.n(this.f7087c));
            jSONObject.put("dis", com.jd.verify.j.b.o(this.f7087c));
            jSONObject.put("lgt", com.jd.verify.j.b.r(this.f7087c));
            jSONObject.put("fin", com.jd.verify.j.b.p(this.f7087c));
            jSONObject.put("nfc", com.jd.verify.j.b.t(this.f7087c));
            jSONObject.put("3dt", "0");
            jSONObject.put("ccn", com.jd.verify.j.b.h() + "");
            jSONObject.put("cmx", com.jd.verify.j.b.a(false));
            jSONObject.put("cmi", com.jd.verify.j.b.a(true));
            jSONObject.put("mus", com.jd.verify.j.b.c(this.f7087c));
            jSONObject.put(HybridSDK.LNG, com.jd.verify.j.b.g());
            jSONObject.put("lat", com.jd.verify.j.b.e());
            jSONObject.put("accessibility", com.jd.verify.j.b.m(this.f7087c));
            com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
            b2.a(this.f7087c);
            jSONObject.put("sen", b2.a().toString());
            com.jd.verify.j.d.a("JDVerify.Embed", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private String b() {
        VerifyPrivacyInfoProxy verifyPrivacyInfoProxy = this.f7093j;
        return verifyPrivacyInfoProxy != null ? verifyPrivacyInfoProxy.getPrivacyScreen() : "";
    }
}
