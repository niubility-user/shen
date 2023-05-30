package com.jd.libs.hybrid.requestpreload.perf;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jingdong.jdsdk.a.a;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0012J%\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ'\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012R.\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00130\u00138\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0018"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/perf/PerfMonitor;", "", "", VerifyTracker.KEY_TIMESTAMP, "key", "value", "", "addRecord", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/libs/hybrid/base/HybridWebView;", "webView", "", "isRemove", "report", "(Ljava/lang/String;Lcom/jd/libs/hybrid/base/HybridWebView;Z)V", "destroy", "(Ljava/lang/String;)V", "destroyAll", "()V", "Ljava/util/concurrent/ConcurrentHashMap;", a.a, "Ljava/util/concurrent/ConcurrentHashMap;", "performanceInfo", "<init>", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PerfMonitor {

    /* renamed from: a  reason: from kotlin metadata */
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> performanceInfo = new ConcurrentHashMap<>();

    public final void addRecord(@NotNull String timestamp, @NotNull String key, @NotNull String value) {
        if (this.performanceInfo.get(timestamp) == null) {
            this.performanceInfo.put(timestamp, new ConcurrentHashMap<>());
        }
        ConcurrentHashMap<String, String> concurrentHashMap = this.performanceInfo.get(timestamp);
        if (concurrentHashMap == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(concurrentHashMap, "performanceInfo[timestamp]!!");
        concurrentHashMap.put(key, value);
    }

    public final void destroy(@NotNull String timestamp) {
        if (this.performanceInfo.containsKey(timestamp)) {
            this.performanceInfo.remove(timestamp);
        }
    }

    public final void destroyAll() {
        this.performanceInfo.clear();
    }

    public final void report(@NotNull String timestamp, @Nullable HybridWebView webView, boolean isRemove) {
        if (webView == null || this.performanceInfo.get(timestamp) == null) {
            return;
        }
        PerformanceUtils.onMonitor(webView, HttpDnsConfig.PREDOWNLOAD_PARAMS, this.performanceInfo.get(timestamp));
        if (isRemove) {
            this.performanceInfo.remove(timestamp);
        }
    }
}
