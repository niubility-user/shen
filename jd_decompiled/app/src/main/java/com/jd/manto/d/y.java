package com.jd.manto.d;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.manto.BaseWebView;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.webview.BaseWebChromeClient;
import com.jingdong.manto.jsengine.IMantoBaseInterface;
import com.jingdong.manto.sdk.api.INavigate;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebPermissionRequest;
import com.jingdong.sdk.jweb.JWebViewClient;
import java.lang.ref.Reference;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class y implements IWebview {

    /* loaded from: classes17.dex */
    private static class a implements IWebview.IMantoWebViewInterface {

        /* renamed from: g  reason: collision with root package name */
        private BaseWebView f6671g;

        /* renamed from: h  reason: collision with root package name */
        private Context f6672h;

        /* renamed from: i  reason: collision with root package name */
        private Reference<MantoCore> f6673i;

        /* renamed from: com.jd.manto.d.y$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class C0192a extends JWebViewClient {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            C0192a(a aVar, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                this.a = iMantoWebViewCallBack;
            }

            @Override // com.jingdong.sdk.jweb.JWebViewClient
            public void onPageFinished(JDWebView jDWebView, String str) {
                this.a.onPageFinished(jDWebView, str);
            }

            @Override // com.jingdong.sdk.jweb.JWebViewClient
            public boolean shouldOverrideUrlLoading(JDWebView jDWebView, String str) {
                INavigate iNavigate;
                try {
                    if (z.a(str) && (iNavigate = (INavigate) com.jingdong.a.n(INavigate.class)) != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", str);
                        iNavigate.navigateTo(jDWebView.getContext(), jSONObject.toString());
                        return true;
                    }
                } catch (Throwable unused) {
                }
                return this.a.shouldOverrideUrlLoading(jDWebView, str);
            }
        }

        /* loaded from: classes17.dex */
        class b extends BaseWebChromeClient {
            final /* synthetic */ IWebview.IMantoWebViewCallBack a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(a aVar, MantoActivityResult mantoActivityResult, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                super(mantoActivityResult);
                this.a = iMantoWebViewCallBack;
            }

            @Override // com.jingdong.manto.jsapi.webview.BaseWebChromeClient, com.jingdong.sdk.jweb.JWebChromeClient
            public void onPermissionRequest(JWebPermissionRequest jWebPermissionRequest) {
                super.onPermissionRequest(jWebPermissionRequest);
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onProgressChanged(JDWebView jDWebView, int i2) {
                this.a.onProgressChanged(jDWebView, i2);
                super.onProgressChanged(jDWebView, i2);
            }

            @Override // com.jingdong.sdk.jweb.JWebChromeClient
            public void onReceivedTitle(JDWebView jDWebView, String str) {
                this.a.onReceivedTitle(jDWebView, str);
            }
        }

        /* loaded from: classes17.dex */
        class c implements View.OnLongClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ IWebview.IMantoWebViewCallBack f6674g;

            c(a aVar, IWebview.IMantoWebViewCallBack iMantoWebViewCallBack) {
                this.f6674g = iMantoWebViewCallBack;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return this.f6674g.onLongClick(view);
            }
        }

        public a(Reference<MantoCore> reference) {
            MantoCore mantoCore;
            this.f6673i = reference;
            if (reference != null && (mantoCore = reference.get()) != null) {
                this.f6672h = mantoCore.getActivity();
            }
            if (this.f6672h == null) {
                this.f6672h = com.jingdong.a.g();
            }
            this.f6671g = new BaseWebView(this.f6672h);
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void addJavascriptInterface(Object obj, String str) {
            this.f6671g.addJavascriptInterface(obj, str);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public boolean canGoBack() {
            return this.f6671g.canGoBack();
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void destroy() {
            this.f6671g.destroy();
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
            this.f6671g.evaluateJavascript(str, valueCallback);
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public JDWebView.HitTestResult getHitTestResult() {
            return this.f6671g.getHitTestResult();
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public IMantoBaseInterface getInterface(Class cls) {
            if (cls.isInstance(this)) {
                return this;
            }
            return null;
        }

        @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
        public String getName() {
            return "webviewContainer";
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public String getTitle() {
            return this.f6671g.getTitle();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public String getUrl() {
            return this.f6671g.getUrl();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public View getView() {
            return this.f6671g;
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public JWebChromeClient getWebChromeClient() {
            return this.f6671g.getWebChromeClient();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void goBack() {
            this.f6671g.goBack();
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void init(IWebview.IMantoWebViewCallBack iMantoWebViewCallBack, String str, boolean z) {
            String str2;
            BaseWebView baseWebView = this.f6671g;
            baseWebView.getSettings().setDomStorageEnabled(true);
            baseWebView.getSettings().setJavaScriptEnabled(true);
            IWebview iWebview = (IWebview) com.jingdong.a.n(IWebview.class);
            String hostUA = iWebview != null ? iWebview.getHostUA() : "";
            baseWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            if (!TextUtils.isEmpty(hostUA)) {
                str2 = hostUA + ";" + baseWebView.getSettings().getUserAgentString() + str;
            } else {
                str2 = baseWebView.getSettings().getUserAgentString() + str;
            }
            MantoLog.d("webview", "finalUA: " + str2);
            if (z) {
                str2 = str2.replaceAll(CashierSdkGlobalConfig.CASHIER_SDK_SOURCE, "").replaceAll("Jdapp", "");
                MantoLog.w("webview", "finalUA after removing jdapp: " + str2);
            }
            baseWebView.getSettings().setUserAgentString(str2);
            baseWebView.getView().setHorizontalScrollBarEnabled(false);
            baseWebView.getView().setVerticalScrollBarEnabled(false);
            baseWebView.getSettings().setBuiltInZoomControls(true);
            baseWebView.getSettings().setUseWideViewPort(true);
            baseWebView.getSettings().setLoadWithOverviewMode(true);
            baseWebView.getSettings().setGeolocationEnabled(true);
            baseWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            baseWebView.getSettings().setAppCacheMaxSize(10485760L);
            baseWebView.getSettings().setAppCachePath(this.f6672h.getDir("webviewcache", 0).getAbsolutePath());
            baseWebView.getSettings().setDatabaseEnabled(true);
            String absolutePath = this.f6672h.getCacheDir().getAbsolutePath();
            baseWebView.getSettings().setDatabasePath(absolutePath + "databases/");
            baseWebView.setOnTouchListener();
            baseWebView.getSettings().setAllowFileAccess(false);
            baseWebView.getSettings().setAppCacheMaxSize(1L);
            baseWebView.getSettings().enableMixedContent();
            baseWebView.setWebViewClient(new C0192a(this, iMantoWebViewCallBack));
            Reference<MantoCore> reference = this.f6673i;
            MantoCore mantoCore = reference != null ? reference.get() : null;
            if (mantoCore != null) {
                baseWebView.setWebChromeClient(new b(this, mantoCore.getActivityResultImpl(), iMantoWebViewCallBack));
            }
            baseWebView.setOnLongClickListener(new c(this, iMantoWebViewCallBack));
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public boolean isSysWebView() {
            BaseWebView baseWebView = this.f6671g;
            return baseWebView == null || baseWebView.getX5WebViewExtension() == null;
        }

        @Override // com.jingdong.manto.sdk.api.IWebview.IMantoWebViewInterface
        public void loadUrl(String str) {
            this.f6671g.loadUrl(str);
        }
    }

    @Override // com.jingdong.manto.sdk.api.IWebview
    public Map<String, Object> getHostJsInterfaces() {
        return null;
    }

    @Override // com.jingdong.manto.sdk.api.IWebview
    public String getHostUA() {
        return WebViewHelper.getJdUaInfo2().toString();
    }

    @Override // com.jingdong.manto.sdk.api.IWebview
    public IWebview.IMantoWebViewInterface getWebview(Reference<MantoCore> reference) {
        return new a(reference);
    }
}
