package com.jd.verify.common;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.verify.CallBack;
import com.jd.verify.View.e;
import com.jd.verify.j.g;
import com.jd.verify.j.h;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import org.json.JSONException;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class JSInterface {
    private CallBack a;
    private com.jd.verify.View.e b;

    /* renamed from: c */
    private Context f7160c;
    private String d;

    /* renamed from: e */
    private e.a f7161e;

    /* renamed from: f */
    private Handler f7162f = new Handler(Looper.getMainLooper());

    /* renamed from: g */
    private com.jd.verify.common.a f7163g;

    /* renamed from: h */
    private com.jd.verify.View.a f7164h;

    /* renamed from: i */
    private String f7165i;

    /* renamed from: j */
    private com.jd.verify.model.a f7166j;

    /* renamed from: k */
    private String f7167k;

    /* renamed from: l */
    private String f7168l;

    /* renamed from: m */
    private String f7169m;

    /* renamed from: n */
    private com.jd.verify.j.a f7170n;

    /* loaded from: classes18.dex */
    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            JSInterface.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject;
            if (JSInterface.this.f7164h != null) {
                JSInterface.this.f7164h.cancel();
            }
            try {
                jSONObject = new JSONObject(this.a);
            } catch (JSONException unused) {
                jSONObject = new JSONObject();
            }
            IninVerifyInfo ininVerifyInfo = null;
            if (!TextUtils.isEmpty(jSONObject.optString("vt"))) {
                if (JSInterface.this.f7170n != null) {
                    com.jd.verify.j.d.a("JSInterface onSuccess  AutoCloseVerify.getAutoClose()=" + JSInterface.this.f7170n.a());
                    if ("0" != JSInterface.this.f7170n.a() && JSInterface.this.b != null) {
                        JSInterface.this.b.cancel();
                    }
                }
                if (JSInterface.this.f7161e != null) {
                    JSInterface.this.f7161e.a(1, "");
                }
                ininVerifyInfo = new IninVerifyInfo(jSONObject);
            } else {
                h.a(jSONObject.optString("msg"));
                if (JSInterface.this.f7161e != null) {
                    JSInterface.this.f7161e.a(3, "");
                }
            }
            if (JSInterface.this.f7163g != null) {
                if (ininVerifyInfo != null) {
                    com.jd.verify.j.d.a("JSInterface onSuccess  notifySuccess:");
                    JSInterface.this.f7163g.a(ininVerifyInfo);
                    com.jd.verify.j.e.b(JSInterface.this.f7160c, "vf_preloadFinish", true);
                    return;
                }
                com.jd.verify.j.d.a("JSInterface onSuccess  notifyOver:");
                JSInterface.this.f7163g.a(9);
            }
        }
    }

    /* loaded from: classes18.dex */
    class b implements Runnable {
        final /* synthetic */ String a;

        b(String str) {
            JSInterface.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JSInterface.this.f7164h != null) {
                JSInterface.this.f7164h.cancel();
            }
            try {
                JSONObject jSONObject = new JSONObject(this.a);
                String optString = jSONObject.optString("interfaceName");
                String optString2 = jSONObject.optString("msg");
                if (!TextUtils.isEmpty(optString)) {
                    com.jd.verify.j.d.b("\u63a5\u53e3\u5931\u8d25:interfaceName = [" + optString + "], msg\u8fd4\u56de\u4e86\u7a7a");
                }
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = JSInterface.this.f7160c.getString(R.string.verify_fail);
                }
                IninVerifyInfo ininVerifyInfo = new IninVerifyInfo(jSONObject);
                if (!TextUtils.isEmpty(optString)) {
                    com.jd.verify.j.d.b("\u63a5\u53e3\u5931\u8d25:interfaceName = [" + optString + "], ErrorType = [" + ininVerifyInfo.getErrorType() + "]");
                }
                if (1 == ininVerifyInfo.getErrorType()) {
                    com.jd.verify.j.d.b("onFailure111: ");
                    h.a(optString2);
                    if (JSInterface.this.f7161e != null) {
                        if (!WebPerfManager.FP.equals(optString) && (ininVerifyInfo.getCode() != 16801 || ininVerifyInfo.getsCode() != 12101)) {
                            JSInterface.this.f7161e.a(2, optString2);
                            com.jd.verify.j.d.b("onFailureclickFinish2 +" + jSONObject.optString("msg"));
                        }
                        JSInterface.this.f7161e.a(4, optString2);
                    }
                } else {
                    com.jd.verify.j.d.b("onFailure222: ");
                    if (ininVerifyInfo.getCode() == 16801 && ininVerifyInfo.getsCode() == 12101) {
                        h.a(optString2);
                        if (JSInterface.this.f7163g != null) {
                            JSInterface.this.f7163g.a(ininVerifyInfo.getsCode());
                            return;
                        }
                    }
                    if (WebPerfManager.FP.equals(optString)) {
                        h.a(optString2);
                        if (JSInterface.this.f7163g != null) {
                            JSInterface.this.f7163g.a(ininVerifyInfo.getCode());
                            com.jd.verify.j.d.b("onFailure333: fp");
                            return;
                        }
                    }
                }
                if (16808 != ininVerifyInfo.getCode()) {
                    if (JSInterface.this.f7163g != null) {
                        com.jd.verify.j.d.b("\u63a5\u53e3\u5931\u8d25:interfaceName = [" + optString + "],\u5176\u4ed6\u9519\u8bef ");
                        if (JSInterface.this.f7160c != null) {
                            h.a(optString2);
                        }
                        JSInterface.this.f7163g.a(ininVerifyInfo.getCode());
                        return;
                    }
                    return;
                }
                h.a(optString2);
                if (JSInterface.this.f7163g != null) {
                    JSInterface.this.f7163g.b();
                }
                com.jd.verify.j.d.b("onFailure444: invalidSessiongId");
            } catch (JSONException e2) {
                com.jd.verify.j.d.b(e2.getMessage());
                e2.printStackTrace();
                if (JSInterface.this.f7163g != null) {
                    if (1 == g.a()) {
                        JSInterface.this.f7163g.b(5);
                        return;
                    }
                    if (JSInterface.this.f7160c != null) {
                        h.a(JSInterface.this.f7160c.getResources().getString(R.string.verify_fail));
                    }
                    JSInterface.this.f7163g.a(5);
                }
            }
        }
    }

    /* loaded from: classes18.dex */
    class c implements Runnable {
        c() {
            JSInterface.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (JSInterface.this.b != null) {
                    JSInterface.this.b.create();
                }
            } catch (Throwable unused) {
                if (JSInterface.this.f7163g != null) {
                    if (1 == g.a()) {
                        JSInterface.this.f7163g.b(6);
                        return;
                    }
                    if (JSInterface.this.f7160c != null) {
                        h.a(JSInterface.this.f7160c.getResources().getString(R.string.verify_fail));
                    }
                    JSInterface.this.f7163g.a(6);
                }
            }
        }
    }

    /* loaded from: classes18.dex */
    class d implements Runnable {
        d() {
            JSInterface.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSInterface.this.b.g().setIsLoadFinish(true);
                if (Build.VERSION.SDK_INT < 31) {
                    JSInterface.this.b.g().setLayerType(2, null);
                }
            } catch (Exception e2) {
                if (JSInterface.this.f7163g != null) {
                    if (1 == g.a()) {
                        JSInterface.this.f7163g.b(7);
                    } else {
                        if (JSInterface.this.f7160c != null) {
                            h.a(JSInterface.this.f7160c.getResources().getString(R.string.verify_fail));
                        }
                        JSInterface.this.f7163g.a(7);
                    }
                }
                e2.printStackTrace();
            }
            if (JSInterface.this.f7164h != null) {
                JSInterface.this.f7164h.cancel();
            }
            if (JSInterface.this.b == null) {
                if (JSInterface.this.f7163g != null) {
                    JSInterface.this.f7163g.a(7);
                    return;
                }
                return;
            }
            com.jd.verify.j.d.a("showDialog");
            JSInterface.this.b.show();
            if (JSInterface.this.f7163g != null) {
                JSInterface.this.f7163g.c();
            }
        }
    }

    /* loaded from: classes18.dex */
    class e implements Runnable {
        e() {
            JSInterface.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.verify.j.f b = com.jd.verify.j.f.b();
            b.a(JSInterface.this.f7160c);
            b.f();
            if (JSInterface.this.f7164h != null) {
                JSInterface.this.f7164h.cancel();
            }
            if (JSInterface.this.f7161e != null) {
                JSInterface.this.f7161e.a(3, "");
            }
            if (JSInterface.this.f7163g != null) {
                JSInterface.this.f7163g.d();
            }
        }
    }

    /* loaded from: classes18.dex */
    class f implements Runnable {
        final /* synthetic */ String a;

        f(String str) {
            JSInterface.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JSInterface.this.f7164h != null) {
                JSInterface.this.f7164h.cancel();
            }
            try {
                if (JSInterface.this.a != null) {
                    IninVerifyInfo ininVerifyInfo = new IninVerifyInfo(new JSONObject(this.a));
                    boolean z = JSInterface.this.f7161e != null;
                    if (6 == ininVerifyInfo.getTp()) {
                        com.jd.verify.j.d.a("MainThread 6");
                        JSInterface.this.a.showButton(1);
                        if (z) {
                            JSInterface.this.f7161e.a(1);
                        }
                    } else if (5 == ininVerifyInfo.getTp()) {
                        com.jd.verify.j.d.a("MainThread 5");
                        JSInterface.this.a.showButton(2);
                        if (z) {
                            JSInterface.this.f7161e.a(2);
                        }
                    }
                    if (z) {
                        JSInterface.this.f7161e.a(6, "");
                    }
                }
                JSInterface.this.b.g().setIsLoadFinish(true);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (JSInterface.this.f7163g != null) {
                    if (JSInterface.this.f7160c != null) {
                        h.a(JSInterface.this.f7160c.getResources().getString(R.string.verify_fail));
                    }
                    JSInterface.this.f7163g.a(10);
                }
            }
        }
    }

    public JSInterface(Context context, CallBack callBack, com.jd.verify.View.e eVar, String str, String str2, com.jd.verify.model.a aVar, e.a aVar2, com.jd.verify.common.a aVar3, com.jd.verify.View.a aVar4, String str3) {
        this.a = callBack;
        this.b = eVar;
        this.f7160c = context;
        this.d = str;
        this.f7161e = aVar2;
        this.f7163g = aVar3;
        this.f7164h = aVar4;
        this.f7165i = str2;
        this.f7166j = aVar;
        this.f7167k = str3;
    }

    @JavascriptInterface
    public void appCheck() {
        com.jd.verify.j.d.a("appCheck");
    }

    @JavascriptInterface
    public String appConfig() {
        com.jd.verify.j.d.a("JSInterface appConfig");
        return this.d;
    }

    @JavascriptInterface
    public void captchaType(String str) {
        com.jd.verify.j.d.a("captchaType\uff1a" + str);
        this.f7162f.post(new f(str));
    }

    @JavascriptInterface
    public void closeWebview() {
        com.jd.verify.j.d.a("closeWebview");
        this.f7162f.post(new e());
    }

    @JavascriptInterface
    public void destory() {
        com.jd.verify.j.d.a("destory");
    }

    @JavascriptInterface
    public String deviceInfo() {
        String a2 = a();
        com.jd.verify.j.d.a("JSInterface deviceInfo\uff1a");
        return a2;
    }

    @JavascriptInterface
    public String getAddtion() {
        com.jd.verify.j.d.a("JSInterface getAddtion");
        if (this.f7166j == null) {
            com.jd.verify.j.d.a("addtionParam is null");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isEmbedded", this.f7166j.a());
        } catch (JSONException unused) {
            com.jd.verify.j.d.a("getAddtion exception");
        }
        com.jd.verify.j.d.a("getAddtion = " + jSONObject.toString());
        return jSONObject.toString();
    }

    public com.jd.verify.j.a getAutoCloseVerify() {
        return this.f7170n;
    }

    @JavascriptInterface
    public String getFp() {
        com.jd.verify.j.d.a("getFp");
        return com.jd.verify.j.e.a(this.f7160c);
    }

    public com.jd.verify.common.a getNotifyListener() {
        return this.f7163g;
    }

    public String getTinyType() {
        return this.f7169m;
    }

    public String getUemps() {
        return this.f7168l;
    }

    public CallBack getmCallBack() {
        return this.a;
    }

    @JavascriptInterface
    public String language() {
        com.jd.verify.j.d.a("JSInterface language:" + this.f7167k);
        return this.f7167k;
    }

    @JavascriptInterface
    public void log(String str) {
        com.jd.verify.j.d.a(str);
    }

    @JavascriptInterface
    public void onFailure(String str) {
        com.jd.verify.j.d.b("JSInterface onFailure: " + str);
        this.f7162f.post(new b(str));
    }

    @JavascriptInterface
    public void onLoad(String str) {
        com.jd.verify.j.d.a("onLoad:" + str);
        this.f7162f.post(new c());
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        com.jd.verify.j.d.a("JSInterface onSuccess: " + str);
        this.f7162f.post(new a(str));
    }

    @JavascriptInterface
    public String readyCheck() {
        com.jd.verify.j.d.a("readyCheck");
        JSONObject jSONObject = new JSONObject();
        try {
            com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
            b2.a(this.f7160c);
            jSONObject.put("sen", b2.a().toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        com.jd.verify.j.d.a("readyCheck\uff1a" + jSONObject2);
        return jSONObject2;
    }

    public void setAutoCloseVerify(com.jd.verify.j.a aVar) {
        this.f7170n = aVar;
    }

    public void setCallBack(CallBack callBack) {
        this.a = callBack;
    }

    @JavascriptInterface
    public void setFp(String str) {
        com.jd.verify.j.e.a(this.f7160c, str);
        com.jd.verify.j.d.a("setFp");
    }

    public void setNotifyListener(com.jd.verify.common.a aVar) {
        this.f7163g = aVar;
    }

    public void setTinyType(String str) {
        this.f7169m = str;
    }

    public void setUemps(String str) {
        this.f7168l = str;
    }

    public void setmCallBack(CallBack callBack) {
        this.a = callBack;
    }

    @JavascriptInterface
    public void showWebviewCaptcha() {
        com.jd.verify.j.d.a("showWebviewCaptcha");
        this.f7162f.post(new d());
    }

    @JavascriptInterface
    public void startCheck() {
        com.jd.verify.j.d.a("startCheck\uff1a");
        com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
        b2.a(this.f7160c);
        b2.a(true);
    }

    private String a() {
        String str = "0";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.f7165i);
            jSONObject.put("din", "");
            jSONObject.put("dnm", "");
            jSONObject.put("dbn", com.jd.verify.j.b.i());
            jSONObject.put("did", com.jd.verify.j.b.b());
            jSONObject.put("dmd", com.jd.verify.j.b.j());
            jSONObject.put("anm", com.jd.verify.j.b.b(this.f7160c));
            jSONObject.put("avs", com.jd.verify.j.b.i(this.f7160c));
            jSONObject.put("abd", com.jd.verify.j.b.h(this.f7160c) + "");
            jSONObject.put("abu", com.jd.verify.j.b.g(this.f7160c));
            jSONObject.put("os", "android");
            jSONObject.put("osv", com.jd.verify.j.b.l());
            jSONObject.put("sdv", "5.3.0");
            jSONObject.put("lan", com.jd.verify.j.b.d(this.f7160c));
            jSONObject.put("lns", "");
            jSONObject.put("tzo", com.jd.verify.j.b.d());
            jSONObject.put("tsp", com.jd.verify.j.b.s(this.f7160c));
            jSONObject.put("pt", "android");
            jSONObject.put(PerformanceManager.CUP, com.jd.verify.j.b.c());
            jSONObject.put(PerformanceManager.MEM_TOTAL, com.jd.verify.j.b.j(this.f7160c));
            jSONObject.put("lbs", com.jd.verify.j.b.f());
            jSONObject.put("ua", "");
            jSONObject.put("ed", "");
            jSONObject.put("scr", com.jd.verify.j.b.k());
            jSONObject.put("gyr", com.jd.verify.j.b.q(this.f7160c));
            jSONObject.put("dir", com.jd.verify.j.b.n(this.f7160c));
            jSONObject.put("dis", com.jd.verify.j.b.o(this.f7160c));
            jSONObject.put("lgt", com.jd.verify.j.b.r(this.f7160c));
            jSONObject.put("fin", com.jd.verify.j.b.p(this.f7160c));
            jSONObject.put("nfc", com.jd.verify.j.b.t(this.f7160c));
            jSONObject.put("3dt", "0");
            jSONObject.put("ccn", com.jd.verify.j.b.h() + "");
            jSONObject.put("cmx", com.jd.verify.j.b.a(false));
            jSONObject.put("cmi", com.jd.verify.j.b.a(true));
            jSONObject.put("mus", com.jd.verify.j.b.c(this.f7160c));
            jSONObject.put(HybridSDK.LNG, com.jd.verify.j.b.g());
            jSONObject.put("lat", com.jd.verify.j.b.e());
            jSONObject.put("accessibility", com.jd.verify.j.b.m(this.f7160c));
            jSONObject.put("uemps", TextUtils.isEmpty(this.f7168l) ? "0" : this.f7168l);
            com.jd.verify.j.f b2 = com.jd.verify.j.f.b();
            b2.a(this.f7160c);
            jSONObject.put("sen", b2.a().toString());
            if (!TextUtils.isEmpty(this.f7169m)) {
                str = this.f7169m;
            }
            jSONObject.put("tiny", str);
            com.jd.verify.j.d.a(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }
}
