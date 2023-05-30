package com.jingdong.common.jdreactFramework.views.vidoplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.jingdong.common.jdreactFramework.views.webview.WebViewConfig;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class JDReactVideoViewManager extends SimpleViewManager<com.jingdong.common.web.ui.X5WebView> {
    private static final String BLANK_URL = "about:blank";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String REACT_CLASS = "RCTVideoPlayer";
    private WebViewConfig mWebViewConfig;

    /* loaded from: classes5.dex */
    public class MyChromeClient extends WebChromeClient {
        FrameLayout frameLayout;
        IX5WebChromeClient.CustomViewCallback myCallBack;
        private View myView = null;
        View parentView;
        ThemedReactContext reactContext;
        private ReactVideoView webView;

        public MyChromeClient(ReactVideoView reactVideoView, ThemedReactContext themedReactContext) {
            this.reactContext = themedReactContext;
            this.webView = reactVideoView;
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onHideCustomView() {
            if (this.myView == null || this.frameLayout == null || this.parentView == null) {
                return;
            }
            this.frameLayout.removeView(this.myView);
            this.myView = null;
            this.frameLayout.addView(this.parentView);
            this.myCallBack.onCustomViewHidden();
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
            View decorView;
            if (this.myView != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            this.frameLayout = (FrameLayout) this.webView.getRootView();
            Activity currentActivity = this.reactContext.getCurrentActivity();
            if (currentActivity == null || (decorView = currentActivity.getWindow().getDecorView()) == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) decorView.findViewById(16908290);
            this.frameLayout = frameLayout;
            if (frameLayout != null) {
                View childAt = this.frameLayout.getChildAt(0);
                this.parentView = childAt;
                if (childAt != null) {
                    this.frameLayout.removeView(this.parentView);
                    this.frameLayout.addView(view);
                    this.myView = view;
                    this.myCallBack = customViewCallback;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class ReactVideoPlayerClient extends WebViewClient {
        private boolean mLastLoadFailed;

        private ReactVideoPlayerClient() {
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

        private static void dispatchEvent(WebView webView, Event event) {
            ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
        }

        private void emitFinishEvent(WebView webView, String str) {
            dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            super.doUpdateVisitedHistory(webView, str, z);
            dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.mLastLoadFailed) {
                return;
            }
            ((ReactVideoView) webView).callInjectedJavaScript();
            emitFinishEvent(webView, str);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", i2);
            createWebViewEvent.putString("description", str);
            dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith("http://") || str.startsWith("https://")) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            webView.getContext().startActivity(intent);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class ReactVideoView extends com.jingdong.common.web.ui.X5WebView implements LifecycleEventListener {
        @Nullable
        private String injectedJS;

        public ReactVideoView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
        }

        /* JADX INFO: Access modifiers changed from: private */
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

        public void setInjectedJavaScript(@Nullable String str) {
            this.injectedJS = str;
        }
    }

    public JDReactVideoViewManager() {
        this.mWebViewConfig = new WebViewConfig() { // from class: com.jingdong.common.jdreactFramework.views.vidoplayer.JDReactVideoViewManager.1
            @Override // com.jingdong.common.jdreactFramework.views.webview.WebViewConfig
            public void configWebView(com.jingdong.common.web.ui.X5WebView x5WebView) {
            }
        };
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(com.jingdong.common.web.ui.X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(com.jingdong.common.web.ui.X5WebView x5WebView, @Nullable String str) {
        ((ReactVideoView) x5WebView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(com.jingdong.common.web.ui.X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(com.jingdong.common.web.ui.X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(com.jingdong.common.web.ui.X5WebView x5WebView, boolean z) {
        x5WebView.getSettings().setUseWideViewPort(!z);
    }

    @ReactProp(name = "source")
    public void setSource(com.jingdong.common.web.ui.X5WebView x5WebView, @Nullable ReadableMap readableMap) {
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
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            hashMap.put(nextKey, map.getString(nextKey));
                        }
                    }
                    x5WebView.loadUrl(string2, hashMap);
                    return;
                }
                return;
            }
        }
        x5WebView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(com.jingdong.common.web.ui.X5WebView x5WebView, @Nullable String str) {
        if (str != null) {
            x5WebView.getSettings().setUserAgentString(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, com.jingdong.common.web.ui.X5WebView x5WebView) {
        x5WebView.setWebViewClient(new ReactVideoPlayerClient());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public com.jingdong.common.web.ui.X5WebView createViewInstance(ThemedReactContext themedReactContext) {
        ReactVideoView reactVideoView = new ReactVideoView(themedReactContext);
        themedReactContext.addLifecycleEventListener(reactVideoView);
        this.mWebViewConfig.configWebView(reactVideoView);
        reactVideoView.getSettings().setBuiltInZoomControls(true);
        reactVideoView.getSettings().setDisplayZoomControls(false);
        reactVideoView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (reactVideoView.getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("standardFullScreen", true);
            reactVideoView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
        }
        reactVideoView.setWebChromeClient(new MyChromeClient(reactVideoView, themedReactContext));
        return reactVideoView;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(com.jingdong.common.web.ui.X5WebView x5WebView) {
        super.onDropViewInstance((JDReactVideoViewManager) x5WebView);
        ReactVideoView reactVideoView = (ReactVideoView) x5WebView;
        ((ThemedReactContext) x5WebView.getContext()).removeLifecycleEventListener(reactVideoView);
        reactVideoView.cleanupCallbacksAndDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(com.jingdong.common.web.ui.X5WebView x5WebView, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            x5WebView.goBack();
        } else if (i2 == 2) {
            x5WebView.goForward();
        } else if (i2 == 3) {
            x5WebView.reload();
        } else if (i2 != 4) {
        } else {
            x5WebView.stopLoading();
        }
    }

    public JDReactVideoViewManager(WebViewConfig webViewConfig) {
        this.mWebViewConfig = webViewConfig;
    }
}
