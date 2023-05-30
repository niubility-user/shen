package com.jingdong.common.web.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.xconsole.XLog;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.WebLoginUtil;
import com.jingdong.common.login.WebReqCookieUtil;
import com.jingdong.common.login.WebReqLoginTokenUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.common.web.WebViewPool;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.text.DecimalFormat;
import java.util.HashMap;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class JDWebCookieHelper {
    private static final String DEFAULT_URL = "https%3a%2f%2fplogin.m.jd.com%2fjd-mlogin%2fstatic%2fhtml%2fappjmp_blank.html";
    private static final String TAG = "JDWebCookieHelper";
    private static final String WEBVIEW_USER_AGENT = "webview_user_agent";
    private static long gentokenTime;
    public static int preGentokenType;
    private static X5WebView x5WebView;

    /* loaded from: classes12.dex */
    static class DefaultWebViewClient extends WebViewClient {
        boolean isError;
        long startTime;
        int syncType;

        DefaultWebViewClient(int i2) {
            this.syncType = i2;
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            Log.d(JDWebCookieHelper.TAG, "onPageFinished:" + str);
            if (!this.isError && webView.getProgress() >= 100) {
                WebLoginHelper.onGentokenSuccess();
                JDWebCookieHelper.reportCookieStatus(true, "", System.currentTimeMillis() - this.startTime, this.syncType);
                JDWebCookieHelper.onDestroy();
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Log.d(JDWebCookieHelper.TAG, "onPageStarted:" + str);
            this.isError = false;
            this.startTime = System.currentTimeMillis();
            if ("about:blank".equals(str)) {
                JDWebCookieHelper.onDestroy();
                ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-onPageStarted", "about:blank");
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            Log.d(JDWebCookieHelper.TAG, "onReceiveonReceivedErrordError:" + str);
            this.isError = true;
            WebLoginHelper.onGentokenFail();
            JDWebCookieHelper.reportCookieStatus(false, "code:" + i2 + "-desc:" + str, System.currentTimeMillis() - this.startTime, this.syncType);
            JDWebCookieHelper.onDestroy();
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public interface QueryUrlListener {
        void onComplete(String str);

        void onError(HttpError httpError);
    }

    public static void asyncWebCookie(int i2) {
        if (Log.D || WebLogHelper.showXLog) {
            String str = TAG;
            XLog.d(str, null, "[newGentoken][\u9884\u6253\u901a] \uff080:\u542f\u52a8  1\uff1a\u524d\u540e\u53f0\u5207\u6362  2\uff1a\u7528\u6237\u767b\u5f55\u4fe1\u606f\u5207\u6362\uff09 type =" + i2, "webview");
            XLog.d(str, null, "[newGentoken][\u9884\u6253\u901a]  isRefreshWebCookieed =" + WebLoginUtil.isRefreshWebCookieed, "webview");
        }
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            if (!WebReqLoginTokenUtil.reqLoginTokenConfig()) {
                WebLoginHelper.onGentokenFail();
                return;
            }
            try {
                if (!X5InitUtil.isInitFinished()) {
                    Log.d(TAG, "[gentoken] TBS CORE IS NOT INITED ");
                    ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-asyncWebCookie", "tbs core is not inited, distance:" + (System.currentTimeMillis() - X5InitUtil.mInitTime) + "ms");
                } else if (WebLoginUtil.isRefreshWebCookieed && WebSwitchQueryFetcher.newGentoken()) {
                    XLog.d(TAG, null, "[newGentoken][\u9884\u6253\u901a] ->  \u8c03\u7528setPreGetWebCookieListener \uff01\uff01\uff01\uff01\uff01", "webview");
                    preGentokenType = 2;
                    WebReqCookieUtil.setPreGetWebCookieListener(new WebReqCookieUtil.PreGetWebCookieListener() { // from class: com.jingdong.common.web.util.JDWebCookieHelper.1
                        @Override // com.jingdong.common.login.WebReqCookieUtil.PreGetWebCookieListener
                        public void onFail() {
                            if (Log.D || WebLogHelper.showXLog) {
                                XLog.d(JDWebCookieHelper.TAG, null, "[newGentoken][\u9884\u6253\u901a] ->  fail", "webview");
                            }
                            WebLoginHelper.onGentokenFail();
                        }

                        @Override // com.jingdong.common.login.WebReqCookieUtil.PreGetWebCookieListener
                        public void onSuccess() {
                            if (Log.D || WebLogHelper.showXLog) {
                                XLog.d(JDWebCookieHelper.TAG, null, "[newGentoken][\u9884\u6253\u901a] ->  success", "webview");
                            }
                            WebLoginHelper.onGentokenSuccess();
                        }

                        @Override // com.jingdong.common.login.WebReqCookieUtil.PreGetWebCookieListener
                        public void onWithinTheValidity() {
                            if (Log.D || WebLogHelper.showXLog) {
                                XLog.d(JDWebCookieHelper.TAG, null, "[newGentoken][\u9884\u6253\u901a] ->  onWithinTheValidity", "webview");
                            }
                            WebLoginHelper.onGentokenSuccess();
                        }
                    });
                } else {
                    gentokenAsync(i2);
                }
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
                ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-asyncWebCookie", e2.getMessage());
            }
        }
    }

    private static void gentokenAsync(final int i2) {
        preGentokenType = 1;
        try {
            queryBrowserUrl(new QueryUrlListener() { // from class: com.jingdong.common.web.util.JDWebCookieHelper.2
                @Override // com.jingdong.common.web.util.JDWebCookieHelper.QueryUrlListener
                public void onComplete(String str) {
                    JDWebCookieHelper.loadUrl(str, i2);
                }

                @Override // com.jingdong.common.web.util.JDWebCookieHelper.QueryUrlListener
                public void onError(HttpError httpError) {
                    WebLoginHelper.onGentokenFail();
                    Log.d(JDWebCookieHelper.TAG, "[\u9884\u6253\u901a]gentoken error time = " + System.currentTimeMillis());
                    StringBuilder sb = new StringBuilder();
                    sb.append("gentokenError:");
                    sb.append(httpError == null ? "" : Integer.valueOf(httpError.getErrorCode()));
                    JDWebCookieHelper.reportCookieStatus(false, sb.toString(), 0L, i2);
                }
            });
        } catch (Exception e2) {
            Log.d(TAG, e2.getMessage());
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-gentokenAsync", e2.getMessage());
            WebLoginHelper.onGentokenFail();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadUrl(final String str, final int i2) {
        Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.util.JDWebCookieHelper.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (JDWebCookieHelper.class) {
                    try {
                        if (JDWebCookieHelper.x5WebView != null) {
                            JDWebCookieHelper.onDestroy();
                        }
                        X5WebView unused = JDWebCookieHelper.x5WebView = WebViewPool.getAvlWebView();
                        JDWebCookieHelper.x5WebView.setWebViewClient(new DefaultWebViewClient(i2));
                        Log.d(JDWebCookieHelper.TAG, "loadUrl: " + str);
                        if (JDWebCookieHelper.x5WebView != null) {
                            Log.d(JDWebCookieHelper.TAG, "[\u9884\u6253\u901a]gentoken success time = " + System.currentTimeMillis());
                            JDWebCookieHelper.x5WebView.loadUrl(str);
                            if (JDWebCookieHelper.x5WebView.getSettings() != null && !TextUtils.isEmpty(JDWebCookieHelper.x5WebView.getSettings().getUserAgentString())) {
                                Log.d(JDWebCookieHelper.TAG, "origin userAgent: " + JDWebCookieHelper.x5WebView.getOrgUserAgent());
                                SharedPreferencesUtil.putString(MBaseKeyNames.KEY_PRELOAD_ORI_UA, JDWebCookieHelper.x5WebView.getOrgUserAgent());
                            }
                        } else {
                            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-loadUrl", "x5webview is null");
                            WebLoginHelper.onGentokenFail();
                        }
                    } catch (Exception e2) {
                        Log.d(JDWebCookieHelper.TAG, e2.getMessage());
                        String str2 = "";
                        try {
                            str2 = " | Process: " + ProcessUtil.getProcessName(JdSdk.getInstance().getApplicationContext());
                        } catch (Exception e3) {
                            Log.d(JDWebCookieHelper.TAG, e3.getMessage());
                        }
                        ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-loadUrl", e2.getMessage() + (str2 + " | " + WebUtils.getWebDirList(JdSdk.getInstance().getApplication())));
                        WebLoginHelper.onGentokenFail();
                    }
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void onDestroy() {
        synchronized (JDWebCookieHelper.class) {
            X5WebView x5WebView2 = x5WebView;
            if (x5WebView2 != null) {
                WebViewPool.recycleWebView(x5WebView2);
                x5WebView = null;
            }
        }
    }

    public static void onWebBackToForeground() {
        int switchIntValue = SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.WEB_COOKIE_EXPIRE, 0);
        if (switchIntValue <= 0 || System.currentTimeMillis() - WebLoginHelper.getLastGentokenTime() <= switchIntValue) {
            return;
        }
        Log.d(TAG, "onWebBackToForeground");
        asyncWebCookie(1);
    }

    private static void queryBrowserUrl(final QueryUrlListener queryUrlListener) {
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
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.web.util.JDWebCookieHelper.4
            double gentokenStart = 0.0d;

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse != null ? httpResponse.getFastJsonObject() : new JDJSONObject();
                String string = fastJsonObject.getString("tokenKey");
                if (string == null) {
                    onError(null);
                    return;
                }
                String string2 = fastJsonObject.getString("url");
                if (string2 == null) {
                    onError(null);
                    return;
                }
                if (OKLog.D) {
                    OKLog.d("CommonBase", "fun:genToken onEnd() -->> token = " + string);
                    OKLog.d("CommonBase", "fun:genToken onEnd() -->> url = " + string2);
                }
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(RemoteMessageConst.TO, JDWebCookieHelper.DEFAULT_URL);
                    hashMap.put("tokenKey", string);
                    String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(string2, hashMap);
                    if (OKLog.D) {
                        OKLog.d("Temp", "queryBrowserUrl() mergerUrl -->> " + mergerUrlAndParams);
                    }
                    long unused = JDWebCookieHelper.gentokenTime = System.currentTimeMillis() - Math.round(this.gentokenStart * 1000.0d);
                    QueryUrlListener queryUrlListener2 = QueryUrlListener.this;
                    if (queryUrlListener2 != null) {
                        queryUrlListener2.onComplete(mergerUrlAndParams);
                    }
                } catch (Exception e2) {
                    Log.e(JDWebCookieHelper.TAG, e2.getMessage(), e2);
                    onError(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                long unused = JDWebCookieHelper.gentokenTime = System.currentTimeMillis() - Math.round(this.gentokenStart * 1000.0d);
                QueryUrlListener queryUrlListener2 = QueryUrlListener.this;
                if (queryUrlListener2 != null) {
                    queryUrlListener2.onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                httpSettingParams.putJsonParam(RemoteMessageConst.TO, JDWebCookieHelper.DEFAULT_URL);
                double currentTimeMillis = System.currentTimeMillis();
                Double.isNaN(currentTimeMillis);
                this.gentokenStart = currentTimeMillis / 1000.0d;
            }
        });
        httpGroup.add(httpSetting);
    }

    public static void removeWebCookies() {
        try {
            CookieSyncManager.createInstance(JdSdk.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.flush();
            CookieSyncManager.getInstance().sync();
        } catch (Exception e2) {
            Log.d(TAG, e2.getMessage());
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-removeWebCookies", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportCookieStatus(boolean z, String str, long j2, int i2) {
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
                jDJSONObject.put("gTime", (Object) String.valueOf(gentokenTime));
                String str6 = i2 == 0 ? "startup" : i2 == 1 ? "tofront" : i2 == 2 ? "loginchange" : "unknown";
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
                hashMap.put("eventType", str6);
                PerformanceReporter.reportData(hashMap);
                Log.d(TAG, cookie);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-reportCookieStatus", e2.getMessage());
        }
    }

    public static void syncLocalCookies() {
        try {
            CookieSyncManager createInstance = CookieSyncManager.createInstance(JdSdk.getInstance().getApplication());
            CookieManager.getInstance().flush();
            createInstance.sync();
        } catch (Exception e2) {
            Log.d(TAG, e2.getMessage());
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "JDWebCookieHelper-syncLocalCookies", e2.getMessage());
        }
    }
}
