package com.jingdong.common.jdreactFramework.views.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.facebook.react.views.webview.events.TopMessageEvent;
import com.facebook.react.views.webview.events.TopShouldStartLoadWithRequestEvent;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.views.webview.CommonMView;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes5.dex */
public class JDReactWebView2 extends CommonMView implements ActivityEventListener {
    private boolean mLastLoadFailed;
    private ThemedReactContext reactContext;

    public JDReactWebView2(@NonNull ThemedReactContext themedReactContext) {
        super(AbstractJDReactInitialHelper.getCurrentMyActivity());
        this.mLastLoadFailed = false;
        this.reactContext = themedReactContext;
        setWebViewClientListener(new WebViewClientListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebView2.1
            @Override // com.jingdong.common.web.uilistener.WebViewClientListener
            public void onPageFinished(WebView webView, String str) {
                if (JDReactWebView2.this.mLastLoadFailed) {
                    return;
                }
                JDReactWebView2.this.emitFinishEvent(str);
            }

            @Override // com.jingdong.common.web.uilistener.WebViewClientListener
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                JDReactWebView2.this.mLastLoadFailed = false;
                JDReactWebView2.this.dispatchEvent(new TopLoadingStartEvent(JDReactWebView2.this.getId(), JDReactWebView2.this.createWebViewEvent(str)));
            }

            @Override // com.jingdong.common.web.uilistener.WebViewClientListener
            public void onReceivedError(WebView webView, int i2, String str, String str2) {
            }

            @Override // com.jingdong.common.web.uilistener.WebViewClientListener
            public void shouldOverrideUrlLoading(WebView webView, String str) {
                JDReactWebView2.this.dispatchEvent(new TopShouldStartLoadWithRequestEvent(JDReactWebView2.this.getId(), JDReactWebView2.this.createWebViewEvent(str)));
            }
        });
        setOnMessageListener(new CommonMView.OnMessageListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.JDReactWebView2.2
            @Override // com.jingdong.common.jdreactFramework.views.webview.CommonMView.OnMessageListener
            public void onMessage(String str) {
                JDReactWebView2.this.dispatchEvent(new TopMessageEvent(JDReactWebView2.this.getId(), str));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap createWebViewEvent(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(TouchesHelper.TARGET_KEY, getId());
        createMap.putString("url", str);
        createMap.putBoolean("loading", (this.mLastLoadFailed || getProgress() == 100) ? false : true);
        createMap.putString("title", getTitle());
        createMap.putBoolean("canGoBack", canGoBack());
        createMap.putBoolean("canGoForward", canGoForward());
        return createMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEvent(Event event) {
        ((UIManagerModule) this.reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitFinishEvent(String str) {
        dispatchEvent(new TopLoadingFinishEvent(getId(), createWebViewEvent(str)));
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
