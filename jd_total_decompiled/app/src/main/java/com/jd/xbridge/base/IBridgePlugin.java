package com.jd.xbridge.base;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J7\u0010\n\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/jd/xbridge/base/IBridgePlugin;", "", "Lcom/jd/xbridge/base/IBridgeWebView;", "webView", "", "method", "params", "Lcom/jd/xbridge/base/IBridgeCallback;", "callback", "", "execute", "(Lcom/jd/xbridge/base/IBridgeWebView;Ljava/lang/String;Ljava/lang/String;Lcom/jd/xbridge/base/IBridgeCallback;)Z", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes18.dex */
public interface IBridgePlugin {
    boolean execute(@Nullable IBridgeWebView webView, @Nullable String method, @Nullable String params, @Nullable IBridgeCallback callback);
}
