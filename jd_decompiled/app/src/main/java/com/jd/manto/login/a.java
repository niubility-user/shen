package com.jd.manto.login;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes17.dex */
public class a {
    private static final String a = "a";
    private static X5WebView b;

    /* renamed from: c  reason: collision with root package name */
    private static long f6753c;
    private static boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.login.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0201a implements f {
        C0201a() {
        }

        @Override // com.jd.manto.login.a.f
        public void onComplete(String str) {
            boolean unused = a.d = true;
            a.j(str);
        }

        @Override // com.jd.manto.login.a.f
        public void onError(HttpError httpError) {
            WebLoginHelper.onGentokenFail();
            StringBuilder sb = new StringBuilder();
            sb.append("gentokenError:");
            sb.append(httpError == null ? "" : Integer.valueOf(httpError.getErrorCode()));
            a.n(false, sb.toString(), 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6754g;

        b(String str) {
            this.f6754g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (a.class) {
                try {
                    if (a.b != null) {
                        a.k();
                    }
                    X5WebView unused = a.b = new X5WebView(JdSdk.getInstance().getApplication());
                    a.b.setWebViewClient(new e());
                    MantoLog.d(a.a, "loadUrl: " + this.f6754g);
                    if (a.b != null) {
                        a.b.loadUrl(this.f6754g);
                    } else {
                        ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-loadUrl", "x5webview is null");
                        WebLoginHelper.onGentokenFail();
                    }
                } catch (Exception e2) {
                    MantoLog.d(a.a, e2.getMessage());
                    String str = "";
                    try {
                        str = " | Process: " + ProcessUtil.getProcessName(JdSdk.getInstance().getApplicationContext());
                    } catch (Exception e3) {
                        MantoLog.d(a.a, e3.getMessage());
                    }
                    ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-loadUrl", e2.getMessage() + (str + " | " + WebUtils.getWebDirList(JdSdk.getInstance().getApplication())));
                    WebLoginHelper.onGentokenFail();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        double f6755g = 0.0d;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ f f6756h;

        c(f fVar) {
            this.f6756h = fVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse != null ? httpResponse.getFastJsonObject() : new JDJSONObject();
            String string = fastJsonObject.getString("tokenKey");
            if (string == null) {
                onError(null);
                return;
            }
            String string2 = fastJsonObject.getString("url");
            if (string2 != null) {
                MantoLog.d(a.a, "fun:genToken onEnd() -->> token = " + string);
                MantoLog.d(a.a, "fun:genToken onEnd() -->> url = " + string2);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(RemoteMessageConst.TO, "https%3a%2f%2fplogin.m.jd.com%2fjd-mlogin%2fstatic%2fhtml%2fappjmp_blank.html");
                    hashMap.put("tokenKey", string);
                    String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(string2, hashMap);
                    MantoLog.d(a.a, "queryBrowserUrl() mergerUrl -->> " + mergerUrlAndParams);
                    long unused = a.f6753c = System.currentTimeMillis() - Math.round(this.f6755g * 1000.0d);
                    f fVar = this.f6756h;
                    if (fVar != null) {
                        fVar.onComplete(mergerUrlAndParams);
                    }
                    Application application = JdSdk.getInstance().getApplication();
                    String str = a.a;
                    String valueOf = String.valueOf(this.f6755g);
                    double currentTimeMillis = System.currentTimeMillis();
                    Double.isNaN(currentTimeMillis);
                    JDMtaUtils.sendWebviewLoadData(application, str, "", "gentoken", "https%3a%2f%2fplogin.m.jd.com%2fjd-mlogin%2fstatic%2fhtml%2fappjmp_blank.html", valueOf, String.valueOf(currentTimeMillis / 1000.0d), CartConstant.KEY_CART_TEXTINFO_FINISH);
                    return;
                } catch (Exception e2) {
                    MantoLog.e(a.a, e2.getMessage(), e2);
                    onError(null);
                    return;
                }
            }
            onError(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            long unused = a.f6753c = System.currentTimeMillis() - Math.round(this.f6755g * 1000.0d);
            f fVar = this.f6756h;
            if (fVar != null) {
                fVar.onError(httpError);
            }
            Application application = JdSdk.getInstance().getApplication();
            String str = a.a;
            String valueOf = String.valueOf(this.f6755g);
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            JDMtaUtils.sendWebviewLoadData(application, str, "", "gentoken", "https%3a%2f%2fplogin.m.jd.com%2fjd-mlogin%2fstatic%2fhtml%2fappjmp_blank.html", valueOf, String.valueOf(currentTimeMillis / 1000.0d), "fail");
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam(RemoteMessageConst.TO, "https%3a%2f%2fplogin.m.jd.com%2fjd-mlogin%2fstatic%2fhtml%2fappjmp_blank.html");
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            this.f6755g = currentTimeMillis / 1000.0d;
        }
    }

    /* loaded from: classes17.dex */
    class d implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6757g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ ILogin.WebCookieCallBack f6758h;

        d(String str, ILogin.WebCookieCallBack webCookieCallBack) {
            this.f6757g = str;
            this.f6758h = webCookieCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse != null ? httpResponse.getFastJsonObject() : new JDJSONObject();
            String string = fastJsonObject.getString("tokenKey");
            if (string == null) {
                this.f6758h.onFailure();
                return;
            }
            String string2 = fastJsonObject.getString("url");
            if (string2 != null && !fastJsonObject.containsKey("error_msg") && !string2.startsWith("https://h5.360buyimg.com/login/html/gentokenWarning.html")) {
                MantoLog.d(a.a, "fun:genToken onEnd() -->> token = " + string);
                MantoLog.d(a.a, "fun:genToken onEnd() -->> url = " + string2);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(RemoteMessageConst.TO, URLEncoder.encode(this.f6757g));
                    hashMap.put("tokenKey", string);
                    String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(string2, hashMap);
                    MantoLog.d(a.a, "queryBrowserUrl() mergerUrl -->> " + mergerUrlAndParams);
                    ILogin.WebCookieCallBack webCookieCallBack = this.f6758h;
                    if (webCookieCallBack != null) {
                        webCookieCallBack.onSuccess(mergerUrlAndParams);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    MantoLog.e(a.a, e2.getMessage(), e2);
                    this.f6758h.onFailure();
                    return;
                }
            }
            this.f6758h.onFailure();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            ILogin.WebCookieCallBack webCookieCallBack = this.f6758h;
            if (webCookieCallBack != null) {
                webCookieCallBack.onFailure();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam(RemoteMessageConst.TO, this.f6757g);
        }
    }

    /* loaded from: classes17.dex */
    static class e extends WebViewClient {
        boolean a;
        long b;

        e() {
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            MantoLog.d(a.a, "onPageFinished:" + str);
            if (!this.a && webView.getProgress() >= 100) {
                WebLoginHelper.onGentokenSuccess();
                a.n(true, "", System.currentTimeMillis() - this.b);
                a.k();
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            MantoLog.d(a.a, "onPageStarted:" + str);
            this.a = false;
            this.b = System.currentTimeMillis();
            if ("about:blank".equals(str)) {
                a.k();
                ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-onPageStarted", "about:blank");
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            MantoLog.d(a.a, "onReceiveonReceivedErrordError:" + str);
            this.a = true;
            WebLoginHelper.onGentokenFail();
            a.n(false, "code:" + i2 + "-desc:" + str, System.currentTimeMillis() - this.b);
            a.k();
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes17.dex */
    public interface f {
        void onComplete(String str);

        void onError(HttpError httpError);
    }

    public static void i() {
        if (d) {
            MantoLog.d(a, "gentokenAsync has done");
            return;
        }
        try {
            l(new C0201a());
        } catch (Exception e2) {
            MantoLog.d(a, e2.getMessage());
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-gentokenAsync", e2.getMessage());
            WebLoginHelper.onGentokenFail();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(String str) {
        b bVar = new b(str);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            bVar.run();
        } else {
            new Handler(Looper.getMainLooper()).post(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void k() {
        synchronized (a.class) {
            X5WebView x5WebView = b;
            if (x5WebView != null) {
                try {
                    x5WebView.stopLoading();
                    b.removeAllViews();
                    b.destroy();
                    b = null;
                } catch (Exception e2) {
                    MantoLog.d(a, e2.getMessage());
                    ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-onDestroy", e2.getMessage());
                }
            }
        }
    }

    private static void l(f fVar) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("genToken");
        httpSetting.setListener(new c(fVar));
        httpGroup.add(httpSetting);
    }

    public static void m() {
        if (MantoProcessUtil.isMantoProcess()) {
            d = false;
            UserUtil.getWJLoginHelper().refreshLoginStatus();
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n(boolean z, String str, long j2) {
        String str2;
        String str3;
        String str4 = "1";
        try {
            StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "10", "2");
            if (stategyEntitiy != null && "1".equals(stategyEntitiy.ret)) {
                String cookie = CookieManager.getInstance().getCookie("https://m.jd.com");
                if (cookie != null) {
                    str2 = "";
                    str3 = str2;
                    for (String str5 : cookie.split(";")) {
                        if (str5.trim().startsWith("pt_pin")) {
                            str2 = str5.trim().substring(7);
                        }
                        if (str5.trim().startsWith("pt_key")) {
                            str3 = str5.trim().substring(7);
                        }
                    }
                } else {
                    str2 = "";
                    str3 = str2;
                }
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("n_pin", (Object) LoginUserBase.getUserPin());
                jDJSONObject.put("pt_pin", (Object) str2);
                jDJSONObject.put("pt_key", (Object) str3);
                jDJSONObject.put("gTime", (Object) String.valueOf(f6753c));
                HashMap hashMap = new HashMap();
                double currentTimeMillis = System.currentTimeMillis();
                Double.isNaN(currentTimeMillis);
                hashMap.put("occurTime", new DecimalFormat("0.000000").format(currentTimeMillis / 1000.0d));
                hashMap.put("typeId", "10");
                hashMap.put("chId", "2");
                hashMap.put("isSuccess", z ? "1" : "0");
                hashMap.put("errMsg", str);
                if (!LoginUserBase.hasLogin()) {
                    str4 = "0";
                }
                hashMap.put("isLogin", str4);
                hashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(j2));
                hashMap.put("eventInfo", jDJSONObject.toJSONString());
                hashMap.put("eventType", "manto");
                PerformanceReporter.reportData(hashMap);
                MantoLog.d(a, cookie);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "MantoWebLoginHelper-reportCookieStatus", e2.getMessage());
        }
    }

    public static void o(String str, ILogin.WebCookieCallBack webCookieCallBack) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("genToken");
        httpSetting.setListener(new d(str, webCookieCallBack));
        httpGroup.add(httpSetting);
    }
}
