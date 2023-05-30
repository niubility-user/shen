package com.jd.jdcache;

import android.view.View;
import android.webkit.ValueCallback;
import androidx.annotation.Keep;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00032\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tH&\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\r\u0010\u000eR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u000f8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/jd/jdcache/JDCacheWebView;", "", "obj", "", "interfaceName", "", "addJavascriptInterface", "(Ljava/lang/Object;Ljava/lang/String;)V", "script", "Landroid/webkit/ValueCallback;", "resultCallback", "evaluateJavascript", "(Ljava/lang/String;Landroid/webkit/ValueCallback;)V", "reload", "()V", "Landroid/view/View;", "getView", "()Landroid/view/View;", "view", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public interface JDCacheWebView {
    void addJavascriptInterface(@NotNull Object obj, @NotNull String interfaceName);

    void evaluateJavascript(@NotNull String script, @Nullable ValueCallback<String> resultCallback);

    @Nullable
    View getView();

    void reload();
}
