package com.jd.jdcache.service;

import com.jd.jdcache.JDCacheLoader;
import com.jd.jdcache.util.JDCacheLog;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\t\b\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\u0006J\u0017\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\u0006R)\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u000e8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/jd/jdcache/service/JDCacheMaster;", "", "", "url", "Lcom/jd/jdcache/JDCacheLoader;", "createDefaultLoader", "(Ljava/lang/String;)Lcom/jd/jdcache/JDCacheLoader;", "loader", "", "addLoader", "(Lcom/jd/jdcache/JDCacheLoader;)V", "loaderKey", "getLoader", "removeLoader", "Ljava/util/concurrent/ConcurrentHashMap;", "loaderMap$delegate", "Lkotlin/Lazy;", "getLoaderMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "loaderMap", "<init>", "()V", "Companion", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCacheMaster {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile JDCacheMaster INSTANCE = null;
    private static final String TAG = "JDCacheMaster";

    /* renamed from: loaderMap$delegate  reason: from kotlin metadata */
    private final Lazy loaderMap;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jd/jdcache/service/JDCacheMaster$Companion;", "", "Lcom/jd/jdcache/service/JDCacheMaster;", "getInstance", "()Lcom/jd/jdcache/service/JDCacheMaster;", "INSTANCE", "Lcom/jd/jdcache/service/JDCacheMaster;", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final JDCacheMaster getInstance() {
            JDCacheMaster jDCacheMaster = JDCacheMaster.INSTANCE;
            if (jDCacheMaster == null) {
                synchronized (this) {
                    jDCacheMaster = JDCacheMaster.INSTANCE;
                    if (jDCacheMaster == null) {
                        jDCacheMaster = new JDCacheMaster(null);
                        JDCacheMaster.INSTANCE = jDCacheMaster;
                    }
                }
            }
            return jDCacheMaster;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private JDCacheMaster() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<ConcurrentHashMap<String, JDCacheLoader>>() { // from class: com.jd.jdcache.service.JDCacheMaster$loaderMap$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ConcurrentHashMap<String, JDCacheLoader> invoke() {
                return new ConcurrentHashMap<>();
            }
        });
        this.loaderMap = lazy;
    }

    private final ConcurrentHashMap<String, JDCacheLoader> getLoaderMap() {
        return (ConcurrentHashMap) this.loaderMap.getValue();
    }

    public final void addLoader(@NotNull JDCacheLoader loader) {
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog()) {
            jDCacheLog.d(TAG, "Add new loader(id:" + loader.getKey() + ") for " + loader.getUrl());
        }
        getLoaderMap().put(loader.getKey(), loader);
    }

    @NotNull
    public final JDCacheLoader createDefaultLoader(@NotNull String url) {
        JDCacheLoader init = new JDCacheLoader(url, String.valueOf(System.currentTimeMillis()), null, false, 12, null).init();
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog()) {
            jDCacheLog.d(TAG, "Create new loader(id:" + init.getKey() + ") for " + init.getUrl());
        }
        getLoaderMap().put(init.getKey(), init);
        return init;
    }

    @Nullable
    public final JDCacheLoader getLoader(@NotNull String loaderKey) {
        return getLoaderMap().get(loaderKey);
    }

    @Nullable
    public final JDCacheLoader removeLoader(@NotNull String loaderKey) {
        JDCacheLoader remove = getLoaderMap().remove(loaderKey);
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog() && remove != null) {
            jDCacheLog.d(TAG, "Remove loader(id:" + remove.getKey() + ')');
        }
        return remove;
    }

    public /* synthetic */ JDCacheMaster(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
