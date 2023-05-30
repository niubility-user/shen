package com.jingdong.common.jdreactFramework.views.webview;

import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.common.web.ui.JDWebView;
import com.tencent.smtt.sdk.WebView;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class JDReactWebView2ViewManager extends SimpleViewManager<JDReactWebView2> {
    private static final String BLANK_URL = "about:blank";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String REACT_CLASS = "RCTJDReactWebView2";

    private void enableHybrid(JDReactWebView2 jDReactWebView2, ReadableMap readableMap, String str) {
        JDWebView jdWebView = jDReactWebView2.getJdWebView();
        String string = readableMap.hasKey("offlineId") ? readableMap.getString("offlineId") : null;
        String string2 = readableMap.hasKey("preLoadId") ? readableMap.getString("preLoadId") : null;
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
            jdWebView.enableHybrid(str);
        } else {
            jdWebView.enableHybrid(string, string2);
        }
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
    public void setDomStorageEnabled(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setDomStorageEnabled(z);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(JDReactWebView2 jDReactWebView2, @Nullable String str) {
        jDReactWebView2.injectJS(str);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setJavaScriptEnabled(z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setMediaPlaybackRequiresUserAction(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setMessagingEnabled(z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setScalesPageToFit(z);
    }

    @ReactProp(name = "source")
    public void setSource(JDReactWebView2 jDReactWebView2, @Nullable ReadableMap readableMap) {
        WebView realWebView = jDReactWebView2.getRealWebView();
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    realWebView.loadDataWithBaseURL(readableMap.getString("baseUrl"), string, HTML_MIME_TYPE, "UTF-8", null);
                    return;
                } else {
                    realWebView.loadData(string, HTML_MIME_TYPE, "UTF-8");
                    return;
                }
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = realWebView.getUrl();
                if (url == null || !url.equals(string2)) {
                    enableHybrid(jDReactWebView2, readableMap, string2);
                    jDReactWebView2.updateUrl(string2);
                    return;
                }
                return;
            }
        }
        realWebView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "topBarGone")
    public void setTopBarGone(JDReactWebView2 jDReactWebView2, boolean z) {
        jDReactWebView2.setTopBarGone(z);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(JDReactWebView2 jDReactWebView2, @Nullable String str) {
        jDReactWebView2.setUserAgent(str);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public JDReactWebView2 createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new JDReactWebView2(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(JDReactWebView2 jDReactWebView2) {
        super.onDropViewInstance((JDReactWebView2ViewManager) jDReactWebView2);
        jDReactWebView2.cleanupCallbacksAndDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDReactWebView2 jDReactWebView2, int i2, @Nullable ReadableArray readableArray) {
        WebView realWebView = jDReactWebView2.getRealWebView();
        switch (i2) {
            case 1:
                realWebView.goBack();
                return;
            case 2:
                realWebView.goForward();
                return;
            case 3:
                realWebView.reload();
                return;
            case 4:
                realWebView.stopLoading();
                return;
            case 5:
                jDReactWebView2.postMessage(readableArray.getString(0));
                return;
            case 6:
                jDReactWebView2.injectJS(readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    @ReactProp(customType = "Color", defaultInt = 0, name = ViewProps.BACKGROUND_COLOR)
    public void setBackgroundColor(JDReactWebView2 jDReactWebView2, int i2) {
        if (i2 == 0) {
            jDReactWebView2.getRealWebView().setBackgroundColor(0);
        } else {
            super.setBackgroundColor((JDReactWebView2ViewManager) jDReactWebView2, i2);
        }
    }
}
