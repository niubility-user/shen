package com.jd.jdcache.match.base;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.Lifecycle;
import com.jd.jdcache.JDCacheLoader;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b.\u0010\u0011J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH'\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0015\u00a2\u0006\u0004\b\u0012\u0010\u0011JA\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0016\u0010\u0016\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019JA\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0016\u0010\u0016\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00152\u0006\u0010\u001b\u001a\u00020\u001aH\u0014\u00a2\u0006\u0004\b\u0018\u0010\u001cJ\u0017\u0010\u0018\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0014\u00a2\u0006\u0004\b\u0018\u0010\u001eR\u0016\u0010!\u001a\u00020\u00078&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\u00020\"8\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R$\u0010(\u001a\u0004\u0018\u00010'8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\u00a8\u0006/"}, d2 = {"Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "", "Landroidx/lifecycle/Lifecycle$Event;", "event", "", "onLifecycleStateChanged", "(Landroidx/lifecycle/Lifecycle$Event;)V", "", "url", JDReactConstant.PREPARE, "(Ljava/lang/String;)V", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "destroy", "()V", "onDestroy", "mimeType", "encoding", "", "header", TbsReaderView.KEY_FILE_PATH, "createResponse", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;", "Ljava/io/InputStream;", "inputStream", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/io/InputStream;)Landroid/webkit/WebResourceResponse;", "response", "(Landroid/webkit/WebResourceResponse;)Landroid/webkit/WebResourceResponse;", "getName", "()Ljava/lang/String;", "name", "Ljava/util/concurrent/atomic/AtomicBoolean;", "destroyed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getDestroyed", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "Lcom/jd/jdcache/JDCacheLoader;", "loader", "Lcom/jd/jdcache/JDCacheLoader;", "getLoader", "()Lcom/jd/jdcache/JDCacheLoader;", "setLoader", "(Lcom/jd/jdcache/JDCacheLoader;)V", "<init>", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class JDCacheResourceMatcher {
    @NotNull
    private final AtomicBoolean destroyed = new AtomicBoolean(false);
    @Nullable
    private JDCacheLoader loader;

    @NotNull
    protected WebResourceResponse createResponse(@NotNull String mimeType, @Nullable String encoding, @Nullable Map<String, String> header, @NotNull String filePath) {
        return createResponse(mimeType, encoding, header, new FileInputStream(filePath));
    }

    public final void destroy() {
        if (this.destroyed.compareAndSet(false, true)) {
            onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final AtomicBoolean getDestroyed() {
        return this.destroyed;
    }

    @Nullable
    public final JDCacheLoader getLoader() {
        return this.loader;
    }

    @NotNull
    public abstract String getName();

    @WorkerThread
    @Nullable
    public abstract WebResourceResponse match(@NotNull WebResourceRequest request);

    /* JADX INFO: Access modifiers changed from: protected */
    @CallSuper
    public void onDestroy() {
        this.loader = null;
    }

    public void onLifecycleStateChanged(@NotNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            destroy();
        }
    }

    public void prepare(@NotNull String url) {
    }

    public final void setLoader(@Nullable JDCacheLoader jDCacheLoader) {
        this.loader = jDCacheLoader;
    }

    @NotNull
    protected WebResourceResponse createResponse(@NotNull String mimeType, @Nullable String encoding, @Nullable Map<String, String> header, @NotNull InputStream inputStream) {
        return createResponse(new WebResourceResponse(mimeType, encoding, inputStream));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public WebResourceResponse createResponse(@NotNull WebResourceResponse response) {
        Map<String, String> responseHeaders = response.getResponseHeaders();
        if (responseHeaders == null) {
            responseHeaders = new HashMap<>(1);
        }
        response.setResponseHeaders(responseHeaders);
        Map<String, String> responseHeaders2 = response.getResponseHeaders();
        Intrinsics.checkExpressionValueIsNotNull(responseHeaders2, "response.responseHeaders");
        responseHeaders2.put("JDCache", getName());
        return response;
    }
}
