package com.jingdong.common.jdreactFramework.views.webview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import androidx.collection.ArrayMap;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.facebook.react.views.webview.events.TopMessageEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.impl.AndroidSound;
import com.jingdong.common.web.javainterface.impl.AndroidUploadImg;
import com.jingdong.common.web.javainterface.impl.JDFunction;
import com.jingdong.common.web.javainterface.impl.JSCalendarHelper;
import com.jingdong.common.web.javainterface.impl.JSControlHelper;
import com.jingdong.common.web.javainterface.impl.JSHttpRequest;
import com.jingdong.common.web.javainterface.impl.JSLoginUserBase;
import com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance;
import com.jingdong.common.web.javainterface.impl.MobileLogin;
import com.jingdong.common.web.javainterface.impl.ShareHelper;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.ui.IJdWebViewUi;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uibinder.impl.CommonWebUiBinder;
import com.jingdong.common.web.urlcheck.impl.UrlCheckImpl;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactWebViewManager extends SimpleViewManager<X5WebView> {
    private static final String BLANK_URL = "about:blank";
    private static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String REACT_CLASS = "RCTJDWebView";
    private WebViewConfig mWebViewConfig;

    /* loaded from: classes5.dex */
    public static class ReactWebView extends X5WebView implements LifecycleEventListener {
        @Nullable
        private String injectedJS;
        private boolean messagingEnabled;

        /* loaded from: classes5.dex */
        public class ReactWebViewBridge {
            ReactWebView mContext;

            ReactWebViewBridge(ReactWebView reactWebView) {
                ReactWebView.this = r1;
                this.mContext = reactWebView;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.mContext.onMessage(str);
            }
        }

        public ReactWebView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.messagingEnabled = false;
        }

        public void cleanupCallbacksAndDestroy() {
            setWebViewClient(null);
            destroy();
        }

        public void callInjectedJavaScript() {
            String str;
            if (!getSettings().getJavaScriptEnabled() || (str = this.injectedJS) == null || TextUtils.isEmpty(str)) {
                return;
            }
            loadUrl("javascript:(function() {\n" + this.injectedJS + ";\n})();");
        }

        public void linkBridge() {
            if (this.messagingEnabled) {
                loadUrl("javascript:(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        @Override // android.view.View
        public boolean onCheckIsTextEditor() {
            try {
                return super.onCheckIsTextEditor();
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            cleanupCallbacksAndDestroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        public void onMessage(String str) {
            JDReactWebViewManager.dispatchEvent(this, new TopMessageEvent(getId(), str));
        }

        public void setInjectedJavaScript(@Nullable String str) {
            this.injectedJS = str;
        }

        public void setMessagingEnabled(boolean z) {
            if (this.messagingEnabled == z) {
                return;
            }
            this.messagingEnabled = z;
            if (z) {
                addJavascriptInterface(new ReactWebViewBridge(this), JDReactWebViewManager.BRIDGE_NAME);
                linkBridge();
                return;
            }
            removeJavascriptInterface(JDReactWebViewManager.BRIDGE_NAME);
        }
    }

    /* loaded from: classes5.dex */
    public static class ReactWebViewClient extends WebViewClient {
        private boolean mLastLoadFailed;

        private ReactWebViewClient() {
            this.mLastLoadFailed = false;
        }

        private WritableMap createWebViewEvent(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(TouchesHelper.TARGET_KEY, webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", (this.mLastLoadFailed || webView.getProgress() == 100) ? false : true);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        private void emitFinishEvent(WebView webView, String str) {
            JDReactWebViewManager.dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        private void loginCallback(final BaseActivity baseActivity, final Uri uri, final WebView webView) {
            baseActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.ReactWebViewClient.1
                {
                    ReactWebViewClient.this = this;
                }

                @Override // com.jingdong.common.frame.IResumeListener
                public void onResume() {
                    baseActivity.removeResumeListener(this);
                    if (LoginUserBase.hasLogin()) {
                        ReactWebViewClient.loginStateSynchro(baseActivity, uri, webView);
                    }
                }
            });
        }

        public static void loginStateSynchro(final BaseActivity baseActivity, Uri uri, final WebView webView) {
            if (uri == null) {
                return;
            }
            try {
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(uri);
                CommonBase.queryBrowserUrl(LoginConstans.FREGMENT_LOGIN_FLAG, uRLParamMap, new CommonBase.BrowserUrlListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.ReactWebViewClient.2
                    @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
                    public void onComplete(final String str) {
                        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.ReactWebViewClient.2.1
                            {
                                AnonymousClass2.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                webView.loadUrl(str);
                            }
                        });
                    }
                });
            } catch (Exception unused) {
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            super.doUpdateVisitedHistory(webView, str, z);
            JDReactWebViewManager.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.mLastLoadFailed) {
                return;
            }
            ReactWebView reactWebView = (ReactWebView) webView;
            reactWebView.callInjectedJavaScript();
            reactWebView.linkBridge();
            emitFinishEvent(webView, str);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            JDReactWebViewManager.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", i2);
            createWebViewEvent.putString("description", str);
            JDReactWebViewManager.dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            BaseActivity baseActivity = (BaseActivity) ((ThemedReactContext) webView.getContext()).getCurrentActivity();
            if (UrlCheckImpl.LOGIN_PATH.equals(parse.getPath())) {
                if (LoginUserBase.hasLogin()) {
                    loginStateSynchro(baseActivity, parse, webView);
                    return true;
                }
                loginCallback(baseActivity, parse, webView);
                DeepLinkDispatch.startActivityDirect(baseActivity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_LOGININ).toString(), (Bundle) null, 0);
                return true;
            } else if (str.startsWith("http://") || str.startsWith("https://") || str.startsWith("file://")) {
                return false;
            } else {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(268435456);
                    webView.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e2) {
                    FLog.w(ReactConstants.TAG, "activity not found to handle uri scheme for: " + str, e2);
                }
                return true;
            }
        }
    }

    public JDReactWebViewManager() {
        this.mWebViewConfig = new WebViewConfig() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.1
            {
                JDReactWebViewManager.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.views.webview.WebViewConfig
            public void configWebView(X5WebView x5WebView) {
            }
        };
    }

    public static void dispatchEvent(WebView webView, Event event) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
    }

    @ReactProp(name = "bindjavaScriptInterface")
    public void bindJavaScriptInterface(X5WebView x5WebView, boolean z) {
        bindJavaScriptInterface(x5WebView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4, "postMessage", 5, "injectJavaScript", 6);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(X5WebView x5WebView, @Nullable String str) {
        ((ReactWebView) x5WebView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(X5WebView x5WebView, boolean z) {
        ((ReactWebView) x5WebView).setMessagingEnabled(z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setUseWideViewPort(!z);
    }

    @ReactProp(name = "source")
    public void setSource(X5WebView x5WebView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    x5WebView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, HTML_MIME_TYPE, "UTF-8", null);
                    return;
                } else {
                    x5WebView.loadData(string, HTML_MIME_TYPE, "UTF-8");
                    return;
                }
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = x5WebView.getUrl();
                if (url == null || !url.equals(string2)) {
                    if (readableMap.hasKey("method") && readableMap.getString("method").equals("POST")) {
                        byte[] bArr = null;
                        if (readableMap.hasKey("body")) {
                            String string3 = readableMap.getString("body");
                            try {
                                bArr = string3.getBytes("UTF-8");
                            } catch (UnsupportedEncodingException unused) {
                                bArr = string3.getBytes();
                            }
                        }
                        if (bArr == null) {
                            bArr = new byte[0];
                        }
                        x5WebView.postUrl(string2, bArr);
                        return;
                    }
                    ArrayMap arrayMap = new ArrayMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if ("user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                if (x5WebView.getSettings() != null) {
                                    x5WebView.getSettings().setUserAgentString(map.getString(nextKey));
                                }
                            } else {
                                arrayMap.put(nextKey, map.getString(nextKey));
                            }
                        }
                    }
                    x5WebView.loadUrl(string2, arrayMap);
                    return;
                }
                return;
            }
        }
        x5WebView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(X5WebView x5WebView, @Nullable String str) {
        if (str != null) {
            x5WebView.getSettings().setUserAgentString(str);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, X5WebView x5WebView) {
        try {
            x5WebView.setWebViewClient(new ReactWebViewClient());
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public void bindJavaScriptInterface(final X5WebView x5WebView) {
        CommonWebUiBinder commonWebUiBinder = new CommonWebUiBinder() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.3
            {
                JDReactWebViewManager.this = this;
            }

            @Override // com.jingdong.common.web.uibinder.impl.CommonWebUiBinder
            public void bindJavaScriptInterface() {
            }

            @Override // com.jingdong.common.web.uibinder.BaseUiBinder, com.jingdong.common.web.uibinder.IWebUiBinder, com.jingdong.common.web.IWebBusinessParams
            public BaseActivity getBaseActivity() {
                ReactContext reactContext = (ReactContext) x5WebView.getContext();
                if (reactContext.getCurrentActivity() instanceof BaseActivity) {
                    return (BaseActivity) reactContext.getCurrentActivity();
                }
                return null;
            }

            @Override // com.jingdong.common.web.uibinder.impl.CommonWebUiBinder, com.jingdong.common.web.uibinder.BaseUiBinder
            public void onBindUi() {
            }
        };
        commonWebUiBinder.bindUi(new IJdWebViewUi() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.4
            {
                JDReactWebViewManager.this = this;
            }

            @Override // com.jingdong.common.web.ui.IJdWebViewUi
            public Context getContext() {
                return null;
            }

            @Override // com.jingdong.common.web.ui.IJdWebViewUi
            public JDWebView getJdWebView() {
                return null;
            }

            @Override // com.jingdong.common.web.ui.IJdWebViewUi
            public NavigatorHolder getNaviHolder() {
                return null;
            }

            @Override // com.jingdong.common.web.ui.IJdWebViewUi
            public WebEntity getWebEntity() {
                return new WebEntity();
            }

            @Override // com.jingdong.common.web.ui.IJdWebViewUi
            public IWebUiBinder getWebUiBinder() {
                return null;
            }
        });
        WebJavaScript webJavaScript = new WebJavaScript(commonWebUiBinder);
        x5WebView.addJavascriptInterface(webJavaScript, webJavaScript.getName());
        ShareHelper shareHelper = new ShareHelper(commonWebUiBinder, false, new Handler());
        x5WebView.addJavascriptInterface(shareHelper, shareHelper.getName());
        AndroidSound androidSound = new AndroidSound(commonWebUiBinder);
        x5WebView.addJavascriptInterface(androidSound, androidSound.getName());
        AndroidUploadImg androidUploadImg = new AndroidUploadImg(commonWebUiBinder);
        x5WebView.addJavascriptInterface(androidUploadImg, androidUploadImg.getName());
        JSHttpRequest jSHttpRequest = new JSHttpRequest(commonWebUiBinder);
        x5WebView.addJavascriptInterface(jSHttpRequest, jSHttpRequest.getName());
        JSLoginUserBase jSLoginUserBase = new JSLoginUserBase(commonWebUiBinder);
        x5WebView.addJavascriptInterface(jSLoginUserBase, jSLoginUserBase.getName());
        JSControlHelper jSControlHelper = new JSControlHelper(commonWebUiBinder);
        x5WebView.addJavascriptInterface(jSControlHelper, jSControlHelper.getName());
        MobileLogin mobileLogin = new MobileLogin(commonWebUiBinder);
        x5WebView.addJavascriptInterface(mobileLogin, mobileLogin.getName());
        try {
            JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = new JavaScriptCallIntelligentAssistance(commonWebUiBinder);
            x5WebView.addJavascriptInterface(javaScriptCallIntelligentAssistance, javaScriptCallIntelligentAssistance.getName());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSCalendarHelper jSCalendarHelper = new JSCalendarHelper(commonWebUiBinder);
        x5WebView.addJavascriptInterface(jSCalendarHelper, jSCalendarHelper.getName());
        JDFunction jDFunction = new JDFunction(((ReactContext) x5WebView.getContext()).getCurrentActivity(), commonWebUiBinder);
        x5WebView.addJavascriptInterface(jDFunction, jDFunction.getName());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public X5WebView createViewInstance(ThemedReactContext themedReactContext) {
        try {
            ReactWebView reactWebView = new ReactWebView(themedReactContext);
            themedReactContext.addLifecycleEventListener(reactWebView);
            this.mWebViewConfig.configWebView(reactWebView);
            reactWebView.getSettings().setBuiltInZoomControls(true);
            reactWebView.getSettings().setDisplayZoomControls(false);
            reactWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            if (reactWebView.getX5WebViewExtension() != null) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("standardFullScreen", true);
                reactWebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
            }
            reactWebView.setWebChromeClient(new WebChromeClient() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebViewManager.2
                {
                    JDReactWebViewManager.this = this;
                }

                @Override // com.tencent.smtt.sdk.WebChromeClient
                public void onHideCustomView() {
                }

                @Override // com.tencent.smtt.sdk.WebChromeClient
                public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                    if (customViewCallback != null) {
                        customViewCallback.onCustomViewHidden();
                    }
                }
            });
            return reactWebView;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(X5WebView x5WebView) {
        super.onDropViewInstance((JDReactWebViewManager) x5WebView);
        ReactWebView reactWebView = (ReactWebView) x5WebView;
        ((ThemedReactContext) x5WebView.getContext()).removeLifecycleEventListener(reactWebView);
        reactWebView.cleanupCallbacksAndDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(X5WebView x5WebView, int i2, @Nullable ReadableArray readableArray) {
        switch (i2) {
            case 1:
                x5WebView.goBack();
                return;
            case 2:
                x5WebView.goForward();
                return;
            case 3:
                x5WebView.reload();
                return;
            case 4:
                x5WebView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    x5WebView.loadUrl("javascript:(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            case 6:
                x5WebView.loadUrl("javascript:" + readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    public JDReactWebViewManager(WebViewConfig webViewConfig) {
        this.mWebViewConfig = webViewConfig;
    }
}
