package com.jd.fireeye.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.fireeye.b.f;
import com.jd.fireeye.b.h;
import com.jd.fireeye.b.l;
import com.jd.fireeye.security.fireeye.IForegroundCheck;
import com.jd.fireeye.security.fireeye.SwitchCallback;
import com.jd.fireeye.security.fireeye.a;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class FireEyeInit {
    private static final String BETA_H5_URL = "http://beta-fp-webview.jd.com";
    private static final String PRODUCT_H5_URL = "https://fp-webview.jd.com";
    public static final int STATE_DELAY = 103;
    public static final int STATE_FAIL = 102;
    public static final int STATE_INIT = 100;
    public static final int STATE_SUCCESS = 101;
    private static final String TAG = "JDMob.Security.FireEye";
    private static boolean initialized;
    private static b mH5InfoCallback;
    private static IForegroundCheck mIBackForegroundCheck;
    public static long start;
    private static Handler handler = new Handler(Looper.getMainLooper());
    public static int STATE = 100;
    public static boolean isSuccess = true;

    /* loaded from: classes13.dex */
    public class a implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ boolean b;

        /* loaded from: classes13.dex */
        public class b extends WebChromeClient {

            /* renamed from: com.jd.fireeye.security.FireEyeInit$a$b$a */
            /* loaded from: classes13.dex */
            class C0080a implements a.l {
                final /* synthetic */ long a;
                final /* synthetic */ JSONObject b;

                C0080a(long j2, JSONObject jSONObject) {
                    b.this = r1;
                    this.a = j2;
                    this.b = jSONObject;
                }

                @Override // com.jd.fireeye.security.fireeye.a.l
                public void a(int i2) {
                    JSONObject g2 = com.jd.fireeye.security.a.g();
                    try {
                        g2.put("duration", String.valueOf(this.a));
                        g2.put("h5Info", this.b);
                        com.jd.fireeye.security.a.n().sendClickDataWithExt(a.this.a, "FireEye_SDK_DeepLink_H5Info_Success", g2.toString(), "", "", "", "", "", "", null);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }

            /* renamed from: com.jd.fireeye.security.FireEyeInit$a$b$b */
            /* loaded from: classes13.dex */
            class RunnableC0081b implements Runnable {
                final /* synthetic */ WebView a;

                RunnableC0081b(b bVar, WebView webView) {
                    this.a = webView;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.a.destroy();
                }
            }

            b() {
                a.this = r1;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    if (FireEyeInit.STATE == 100) {
                        FireEyeInit.STATE = 101;
                        if (FireEyeInit.mH5InfoCallback != null) {
                            boolean z = a.this.b;
                            FireEyeInit.mH5InfoCallback.a(FireEyeInit.STATE);
                        }
                    }
                    com.jd.fireeye.security.a.a(jSONObject);
                    long currentTimeMillis = System.currentTimeMillis() - FireEyeInit.start;
                    if (com.jd.fireeye.security.a.r()) {
                        if (com.jd.fireeye.security.fireeye.a.a()) {
                            JSONObject g2 = com.jd.fireeye.security.a.g();
                            g2.put("duration", String.valueOf(currentTimeMillis));
                            g2.put("h5Info", jSONObject);
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(a.this.a, "FireEye_SDK_DeepLink_H5Info_Success", g2.toString(), "", "", "", "", "", "", null);
                        } else {
                            com.jd.fireeye.security.fireeye.a.a(new C0080a(currentTimeMillis, jSONObject));
                        }
                    }
                    if (webView != null) {
                        FireEyeInit.handler.post(new RunnableC0081b(this, webView));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                jsResult.confirm();
                return true;
            }
        }

        a(Context context, boolean z) {
            this.a = context;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                WebView webView = new WebView(this.a);
                WebSettings settings = webView.getSettings();
                if (settings == null) {
                    return;
                }
                settings.setJavaScriptEnabled(true);
                webView.setWebViewClient(new C0079a(currentTimeMillis));
                webView.setWebChromeClient(new b());
                webView.loadUrl(FireEyeInit.PRODUCT_H5_URL);
            } catch (Exception e2) {
                long currentTimeMillis2 = System.currentTimeMillis() - FireEyeInit.start;
                if (com.jd.fireeye.security.a.r()) {
                    JSONObject g2 = com.jd.fireeye.security.a.g();
                    try {
                        g2.put("duration", String.valueOf(currentTimeMillis2));
                        g2.put("reason", e2);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                    com.jd.fireeye.security.a.n().sendClickDataWithExt(this.a, "FireEye_SDK_DeepLink_H5Info_Failed", g2.toString(), "", "", "", "", "", "", null);
                }
            }
        }

        /* renamed from: com.jd.fireeye.security.FireEyeInit$a$a */
        /* loaded from: classes13.dex */
        class C0079a extends WebViewClient {
            final /* synthetic */ long a;

            C0079a(long j2) {
                a.this = r1;
                this.a = j2;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i2, String str, String str2) {
                if (Build.VERSION.SDK_INT < 23) {
                    FireEyeInit.h5InfoFailMta(a.this.a, str, this.a);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                if (Build.VERSION.SDK_INT < 23 || webResourceRequest == null || !webResourceRequest.isForMainFrame() || webResourceResponse == null) {
                    return;
                }
                FireEyeInit.h5InfoFailMta(a.this.a, webResourceResponse.getReasonPhrase(), this.a);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                if (Build.VERSION.SDK_INT < 23 || webResourceError == null || webResourceError.getDescription() == null || webResourceError.getDescription().length() <= 0) {
                    return;
                }
                FireEyeInit.h5InfoFailMta(a.this.a, webResourceError.getDescription().toString(), this.a);
            }
        }
    }

    /* loaded from: classes13.dex */
    public interface b {
        void a(int i2);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void getH5Info(Context context, boolean z) {
        handler.post(new a(context, z));
    }

    public static void h5InfoFailMta(Context context, String str, long j2) {
        if (context != null && com.jd.fireeye.security.a.r()) {
            long currentTimeMillis = System.currentTimeMillis() - j2;
            JSONObject g2 = com.jd.fireeye.security.a.g();
            try {
                g2.put("duration", String.valueOf(currentTimeMillis));
                g2.put("reason", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            com.jd.fireeye.security.a.n().sendClickDataWithExt(context, "FireEye_SDK_DeepLink_H5Info_Failed", g2.toString(), "", "", "", "", "", "", null);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static synchronized void init(Context context, FireEyeBaseData fireEyeBaseData, boolean z, boolean z2) {
        long j2;
        synchronized (FireEyeInit.class) {
            if (!initialized) {
                start = System.currentTimeMillis();
                if (context != null) {
                    com.jd.fireeye.security.a.a(context);
                    if (fireEyeBaseData == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(fireEyeBaseData.getAppKey()) && !TextUtils.isEmpty(fireEyeBaseData.getPublicKey())) {
                        initialized = true;
                        isSuccess = true;
                        long currentTimeMillis = System.currentTimeMillis();
                        if (fireEyeBaseData.getiMtaUtils() == null || !fireEyeBaseData.isMtaSwitch()) {
                            j2 = currentTimeMillis;
                        } else {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put(PairKey.APP_KEY, fireEyeBaseData.getAppKey());
                                jSONObject.put("unionID", fireEyeBaseData.getUnionId());
                                jSONObject.put("installID", fireEyeBaseData.getInstalltionid());
                                jSONObject.put("devInfo", fireEyeBaseData.getDeviceCode());
                                jSONObject.put("timeStamp", String.valueOf(currentTimeMillis));
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            j2 = currentTimeMillis;
                            fireEyeBaseData.getiMtaUtils().sendClickDataWithExt(context, "FireEye_SDK_Init_Start", jSONObject.toString(), "", "", "", "", "", "", null);
                        }
                        com.jd.fireeye.network.b.a(z);
                        f.a(z);
                        com.jd.fireeye.security.a.b(z2);
                        com.jd.fireeye.security.a.a(fireEyeBaseData, j2);
                        h.b().a(context);
                        l.a(context);
                        if (fireEyeBaseData.isAppSwitch()) {
                            com.jd.fireeye.security.fireeye.a.b().a(false, fireEyeBaseData.isClipSwitch(), (SwitchCallback) null);
                        }
                        if (fireEyeBaseData.getiMtaUtils() != null && fireEyeBaseData.isMtaSwitch() && isSuccess) {
                            JSONObject g2 = com.jd.fireeye.security.a.g();
                            try {
                                g2.put("timeStamp", String.valueOf(System.currentTimeMillis()));
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                            com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Init_Success", g2.toString(), "", "", "", "", "", "", null);
                        }
                    }
                } else {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("reason", "context \u4e3a\u7a7a");
                        jSONObject2.put("duration", String.valueOf(System.currentTimeMillis() - start));
                        if (!TextUtils.isEmpty(fireEyeBaseData.getAppKey())) {
                            jSONObject2.put(PairKey.APP_KEY, fireEyeBaseData.getAppKey());
                        }
                        if (!TextUtils.isEmpty(fireEyeBaseData.getUnionId())) {
                            jSONObject2.put("unionID", fireEyeBaseData.getUnionId());
                        }
                        if (!TextUtils.isEmpty(fireEyeBaseData.getInstalltionid())) {
                            jSONObject2.put("installID", fireEyeBaseData.getInstalltionid());
                        }
                        if (!TextUtils.isEmpty(fireEyeBaseData.getDeviceCode())) {
                            jSONObject2.put("devInfo", fireEyeBaseData.getDeviceCode());
                        }
                        com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Init_Failed", jSONObject2.toString(), "", "", "", "", "", "", null);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    return;
                }
            } else if (com.jd.fireeye.security.a.r()) {
                com.jd.fireeye.security.a.n().sendClickDataWithExt(com.jd.fireeye.security.a.a, "FireEye_SDK_Init_Already", com.jd.fireeye.security.a.g().toString(), "", "", "", "", "", "", null);
            }
        }
    }

    public static boolean isAppForeground() {
        IForegroundCheck iForegroundCheck = mIBackForegroundCheck;
        if (iForegroundCheck == null) {
            return true;
        }
        return iForegroundCheck.isAppForeground();
    }

    public static boolean needGetH5Info() {
        return 0 != com.jd.fireeye.security.a.f() && STATE == 100;
    }

    public static void setForegroundCheck(IForegroundCheck iForegroundCheck) {
        mIBackForegroundCheck = iForegroundCheck;
    }

    public static void setOnFinishGetH5InfoObserver(b bVar) {
        mH5InfoCallback = bVar;
    }
}
