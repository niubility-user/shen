package com.facebook.react.views.webview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.facebook.react.views.webview.events.TopMessageEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = ReactWebViewManager.REACT_CLASS)
/* loaded from: classes12.dex */
public class ReactWebViewManager extends SimpleViewManager<WebView> {
    protected static final String BLANK_URL = "about:blank";
    protected static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String HTTP_METHOD_POST = "POST";
    private static final String INTENT_URL_PREFIX = "intent://";
    public static final String REACT_CLASS = "RCTWebView";
    @Nullable
    protected WebView.PictureListener mPictureListener;
    protected WebViewConfig mWebViewConfig;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class ReactWebView extends WebView implements LifecycleEventListener {
        @Nullable
        protected String injectedJS;
        @Nullable
        protected ReactWebViewClient mReactWebViewClient;
        protected boolean messagingEnabled;

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes12.dex */
        public class ReactWebViewBridge {
            ReactWebView mContext;

            ReactWebViewBridge(ReactWebView reactWebView) {
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

        public void callInjectedJavaScript() {
            String str;
            if (!getSettings().getJavaScriptEnabled() || (str = this.injectedJS) == null || TextUtils.isEmpty(str)) {
                return;
            }
            evaluateJavascriptWithFallback("(function() {\n" + this.injectedJS + ";\n})();");
        }

        protected void cleanupCallbacksAndDestroy() {
            setWebViewClient(null);
            destroy();
        }

        protected ReactWebViewBridge createReactWebViewBridge(ReactWebView reactWebView) {
            return new ReactWebViewBridge(reactWebView);
        }

        protected void evaluateJavascriptWithFallback(String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                evaluateJavascript(str, null);
                return;
            }
            try {
                loadUrl("javascript:" + URLEncoder.encode(str, "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Nullable
        public ReactWebViewClient getReactWebViewClient() {
            return this.mReactWebViewClient;
        }

        public void linkBridge() {
            if (this.messagingEnabled) {
                evaluateJavascriptWithFallback("(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
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
            ReactWebViewManager.dispatchEvent(this, new TopMessageEvent(getId(), str));
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
                addJavascriptInterface(createReactWebViewBridge(this), ReactWebViewManager.BRIDGE_NAME);
                linkBridge();
                return;
            }
            removeJavascriptInterface(ReactWebViewManager.BRIDGE_NAME);
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.mReactWebViewClient = (ReactWebViewClient) webViewClient;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class ReactWebViewClient extends WebViewClient {
        protected boolean mLastLoadFailed = false;
        @Nullable
        protected List<Pattern> mOriginWhitelist;
        @Nullable
        protected ReadableArray mUrlPrefixesForDefaultIntent;

        protected ReactWebViewClient() {
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001e  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void launchIntent(android.content.Context r7, java.lang.String r8) {
            /*
                r6 = this;
                java.lang.String r0 = "intent://"
                boolean r0 = r8.startsWith(r0)
                java.lang.String r1 = "ReactNative"
                r2 = 0
                if (r0 == 0) goto L17
                r0 = 1
                android.content.Intent r0 = android.content.Intent.parseUri(r8, r0)     // Catch: java.net.URISyntaxException -> L11
                goto L18
            L11:
                r0 = move-exception
                java.lang.String r3 = "Can't parse intent:// URI"
                com.facebook.common.logging.FLog.e(r1, r3, r0)
            L17:
                r0 = r2
            L18:
                java.lang.String r3 = "android.intent.category.BROWSABLE"
                java.lang.String r4 = "android.intent.action.VIEW"
                if (r0 == 0) goto L48
                r0.addCategory(r3)
                r0.setComponent(r2)
                r0.setSelector(r2)
                android.content.pm.PackageManager r2 = r7.getPackageManager()
                r5 = 65536(0x10000, float:9.18355E-41)
                android.content.pm.ResolveInfo r2 = r2.resolveActivity(r0, r5)
                if (r2 == 0) goto L37
                r7.startActivity(r0)
                goto L51
            L37:
                java.lang.String r2 = "browser_fallback_url"
                java.lang.String r0 = r0.getStringExtra(r2)
                android.content.Intent r2 = new android.content.Intent
                android.net.Uri r0 = android.net.Uri.parse(r0)
                r2.<init>(r4, r0)
                r0 = r2
                goto L51
            L48:
                android.content.Intent r0 = new android.content.Intent
                android.net.Uri r2 = android.net.Uri.parse(r8)
                r0.<init>(r4, r2)
            L51:
                r2 = 268435456(0x10000000, float:2.5243549E-29)
                r0.setFlags(r2)     // Catch: android.content.ActivityNotFoundException -> L5d
                r0.addCategory(r3)     // Catch: android.content.ActivityNotFoundException -> L5d
                r7.startActivity(r0)     // Catch: android.content.ActivityNotFoundException -> L5d
                goto L72
            L5d:
                r7 = move-exception
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "activity not found to handle uri scheme for: "
                r0.append(r2)
                r0.append(r8)
                java.lang.String r8 = r0.toString()
                com.facebook.common.logging.FLog.w(r1, r8, r7)
            L72:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.webview.ReactWebViewManager.ReactWebViewClient.launchIntent(android.content.Context, java.lang.String):void");
        }

        private boolean shouldHandleURL(List<Pattern> list, String str) {
            Uri parse = Uri.parse(str);
            String str2 = (parse.getScheme() != null ? parse.getScheme() : "") + "://" + (parse.getAuthority() != null ? parse.getAuthority() : "");
            Iterator<Pattern> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().matcher(str2).matches()) {
                    return true;
                }
            }
            return false;
        }

        protected WritableMap createWebViewEvent(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(TouchesHelper.TARGET_KEY, webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", (this.mLastLoadFailed || webView.getProgress() == 100) ? false : true);
            createMap.putString("title", webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        protected void emitFinishEvent(WebView webView, String str) {
            ReactWebViewManager.dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
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

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            ReactWebViewManager.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", i2);
            createWebViewEvent.putString("description", str);
            ReactWebViewManager.dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
        }

        public void setOriginWhitelist(List<Pattern> list) {
            this.mOriginWhitelist = list;
        }

        public void setUrlPrefixesForDefaultIntent(ReadableArray readableArray) {
            this.mUrlPrefixesForDefaultIntent = readableArray;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.equals(ReactWebViewManager.BLANK_URL)) {
                return false;
            }
            ReadableArray readableArray = this.mUrlPrefixesForDefaultIntent;
            if (readableArray != null && readableArray.size() > 0) {
                Iterator<Object> it = this.mUrlPrefixesForDefaultIntent.toArrayList().iterator();
                while (it.hasNext()) {
                    if (str.startsWith((String) it.next())) {
                        launchIntent(webView.getContext(), str);
                        return true;
                    }
                }
            }
            List<Pattern> list = this.mOriginWhitelist;
            if (list == null || !shouldHandleURL(list, str)) {
                launchIntent(webView.getContext(), str);
                return true;
            }
            return false;
        }
    }

    public ReactWebViewManager() {
        this.mWebViewConfig = new WebViewConfig() { // from class: com.facebook.react.views.webview.ReactWebViewManager.1
            @Override // com.facebook.react.views.webview.WebViewConfig
            public void configWebView(WebView webView) {
            }
        };
    }

    protected static void dispatchEvent(WebView webView, Event event) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
    }

    protected ReactWebView createReactWebViewInstance(ThemedReactContext themedReactContext) {
        return new ReactWebView(themedReactContext);
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

    protected WebView.PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = new WebView.PictureListener() { // from class: com.facebook.react.views.webview.ReactWebViewManager.3
                @Override // android.webkit.WebView.PictureListener
                public void onNewPicture(WebView webView, Picture picture) {
                    ReactWebViewManager.dispatchEvent(webView, new ContentSizeChangeEvent(webView.getId(), webView.getWidth(), webView.getContentHeight()));
                }
            };
        }
        return this.mPictureListener;
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, @Nullable String str) {
        ((ReactWebView) webView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    @TargetApi(17)
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((ReactWebView) webView).setMessagingEnabled(z);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(WebView webView, @Nullable String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (str != null && !ReactScrollViewHelper.OVER_SCROLL_NEVER.equals(str)) {
                if (ReactScrollViewHelper.OVER_SCROLL_ALWAYS.equals(str)) {
                    webView.getSettings().setMixedContentMode(0);
                    return;
                } else if ("compatibility".equals(str)) {
                    webView.getSettings().setMixedContentMode(2);
                    return;
                } else {
                    return;
                }
            }
            webView.getSettings().setMixedContentMode(1);
        }
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        if (z) {
            webView.setPictureListener(getPictureListener());
        } else {
            webView.setPictureListener(null);
        }
    }

    @ReactProp(name = "originWhitelist")
    public void setOriginWhitelist(WebView webView, @Nullable ReadableArray readableArray) {
        ReactWebViewClient reactWebViewClient = ((ReactWebView) webView).getReactWebViewClient();
        if (reactWebViewClient == null || readableArray == null) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            linkedList.add(Pattern.compile(readableArray.getString(i2)));
        }
        reactWebViewClient.setOriginWhitelist(linkedList);
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setUseWideViewPort(!z);
    }

    @ReactProp(name = "source")
    public void setSource(WebView webView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    webView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, "text/html", "UTF-8", null);
                    return;
                } else {
                    webView.loadData(string, "text/html", "UTF-8");
                    return;
                }
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url == null || !url.equals(string2)) {
                    if (readableMap.hasKey("method") && readableMap.getString("method").equalsIgnoreCase("POST")) {
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
                        webView.postUrl(string2, bArr);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if ("user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                if (webView.getSettings() != null) {
                                    webView.getSettings().setUserAgentString(map.getString(nextKey));
                                }
                            } else {
                                hashMap.put(nextKey, map.getString(nextKey));
                            }
                        }
                    }
                    webView.loadUrl(string2, hashMap);
                    return;
                }
                return;
            }
        }
        webView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
        }
    }

    @ReactProp(name = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, @Nullable ReadableArray readableArray) {
        ReactWebViewClient reactWebViewClient = ((ReactWebView) webView).getReactWebViewClient();
        if (reactWebViewClient == null || readableArray == null) {
            return;
        }
        reactWebViewClient.setUrlPrefixesForDefaultIntent(readableArray);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView webView, @Nullable String str) {
        if (str != null) {
            webView.getSettings().setUserAgentString(str);
        }
    }

    @ReactProp(defaultBoolean = true, name = "hardwareAccelerationEnabledExperimental")
    public void sethardwareAccelerationEnabledExperimental(WebView webView, boolean z) {
        if (z) {
            return;
        }
        webView.setLayerType(1, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, WebView webView) {
        if (webView == null) {
            return;
        }
        webView.setWebViewClient(new ReactWebViewClient());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @TargetApi(21)
    public WebView createViewInstance(ThemedReactContext themedReactContext) {
        try {
            ReactWebView createReactWebViewInstance = createReactWebViewInstance(themedReactContext);
            createReactWebViewInstance.setWebChromeClient(new WebChromeClient() { // from class: com.facebook.react.views.webview.ReactWebViewManager.2
                @Override // android.webkit.WebChromeClient
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    return true;
                }

                @Override // android.webkit.WebChromeClient
                public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                    callback.invoke(str, true, false);
                }
            });
            themedReactContext.addLifecycleEventListener(createReactWebViewInstance);
            this.mWebViewConfig.configWebView(createReactWebViewInstance);
            WebSettings settings = createReactWebViewInstance.getSettings();
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setDomStorageEnabled(true);
            settings.setAllowFileAccess(false);
            settings.setAllowContentAccess(false);
            settings.setAllowFileAccessFromFileURLs(false);
            setAllowUniversalAccessFromFileURLs(createReactWebViewInstance, false);
            setMixedContentMode(createReactWebViewInstance, ReactScrollViewHelper.OVER_SCROLL_NEVER);
            createReactWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            setGeolocationEnabled(createReactWebViewInstance, Boolean.FALSE);
            return createReactWebViewInstance;
        } catch (Exception e2) {
            FLog.e("ReactWebViewManager", String.valueOf(e2));
            return null;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance((ReactWebViewManager) webView);
        ReactWebView reactWebView = (ReactWebView) webView;
        ((ThemedReactContext) webView.getContext()).removeLifecycleEventListener(reactWebView);
        reactWebView.cleanupCallbacksAndDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(WebView webView, int i2, @Nullable ReadableArray readableArray) {
        switch (i2) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case 3:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((ReactWebView) webView).evaluateJavascriptWithFallback("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            case 6:
                ((ReactWebView) webView).evaluateJavascriptWithFallback(readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    public ReactWebViewManager(WebViewConfig webViewConfig) {
        this.mWebViewConfig = webViewConfig;
    }
}
