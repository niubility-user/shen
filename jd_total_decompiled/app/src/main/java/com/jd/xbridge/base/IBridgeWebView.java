package com.jd.xbridge.base;

import android.view.View;
import android.webkit.ValueCallback;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\b\u0010\u0004J\u0011\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\tH&\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\t2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011H&\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\tH&\u00a2\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\t2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0018H&\u00a2\u0006\u0004\b\u0016\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u001b\u0010\u0004R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001c8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR$\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020 \u0018\u00010\u00188&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"\u00a8\u0006$"}, d2 = {"Lcom/jd/xbridge/base/IBridgeWebView;", "", "", "onStart", "()V", "onResume", "onPause", "onStop", "destroy", "", "getUrl", "()Ljava/lang/String;", "obj", "interfaceName", "addJavascriptInterface", "(Ljava/lang/Object;Ljava/lang/String;)V", "script", "Landroid/webkit/ValueCallback;", "resultCallback", "evaluateJavascript", "(Ljava/lang/String;Landroid/webkit/ValueCallback;)V", "url", "loadUrl", "(Ljava/lang/String;)V", "", "additionalHttpHeaders", "(Ljava/lang/String;Ljava/util/Map;)V", "reload", "Landroid/view/View;", "getView", "()Landroid/view/View;", "view", "Lcom/jd/xbridge/base/d;", "getBridgeMap", "()Ljava/util/Map;", "bridgeMap", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes18.dex */
public interface IBridgeWebView {
    void addJavascriptInterface(@NotNull Object obj, @NotNull String interfaceName);

    void destroy();

    void evaluateJavascript(@NotNull String script, @Nullable ValueCallback<String> resultCallback);

    @Nullable
    Map<String, d> getBridgeMap();

    @Nullable
    String getUrl();

    @Nullable
    View getView();

    void loadUrl(@NotNull String url);

    void loadUrl(@NotNull String url, @NotNull Map<String, String> additionalHttpHeaders);

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void reload();
}
