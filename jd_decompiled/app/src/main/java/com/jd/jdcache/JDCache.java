package com.jd.jdcache;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.lifecycle.LifecycleOwner;
import com.jd.jdcache.match.ResourceMatcherManager;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.match.impl.MapResourceMatcher;
import com.jd.jdcache.match.impl.PreloadHtmlMatcher;
import com.jd.jdcache.service.DelegateManager;
import com.jd.jdcache.service.JDCacheMaster;
import com.jd.jdcache.service.base.AbstractDelegate;
import com.jd.jdcache.service.impl.FileRepo;
import com.jd.jdcache.service.impl.net.NetConnection;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.LinkedList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\u00042\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0018\u001a\u00020\u00042\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u000f\u00a2\u0006\u0004\b\u0018\u0010\u0013J\u001d\u0010\u0019\u001a\u00020\u00042\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u000f\u00a2\u0006\u0004\b\u0019\u0010\u0013J\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\u00042\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u000f\u00a2\u0006\u0004\b\u001f\u0010\u0013J'\u0010\"\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010 *\u00020\u001d2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u00a2\u0006\u0004\b\"\u0010#J%\u0010)\u001a\u0004\u0018\u00010(2\b\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b)\u0010*J\u001d\u0010)\u001a\u0004\u0018\u00010(*\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b)\u0010+J\u0019\u0010-\u001a\u0004\u0018\u00010(2\b\u0010,\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b-\u0010.J%\u0010/\u001a\u0004\u0018\u00010(2\b\u0010,\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b/\u0010*J\u001d\u0010/\u001a\u0004\u0018\u00010(*\u00020&2\b\u0010,\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b/\u0010+J\u0017\u00100\u001a\u00020\u00042\b\u0010,\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020\u00042\b\u00103\u001a\u0004\u0018\u000102\u00a2\u0006\u0004\b4\u00105\u00a8\u00068"}, d2 = {"Lcom/jd/jdcache/JDCache;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "initInternal", "(Landroid/content/Context;)V", "", "debug", XView2Constants.XVIEW2_ACTION_INIT, "(Landroid/content/Context;Z)V", "getContext", "()Landroid/content/Context;", "enable", "(Z)V", "Ljava/lang/Class;", "Lcom/jd/jdcache/JDCacheParamsProvider;", "paramsProviderClazz", "setGlobalParams", "(Ljava/lang/Class;)V", "getGlobalParams", "()Lcom/jd/jdcache/JDCacheParamsProvider;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "matcherClazz", "registerDefaultResourceMatcher", "unregisterDefaultResourceMatcher", "Ljava/util/LinkedList;", "createDefaultResourceMatcherList", "()Ljava/util/LinkedList;", "Lcom/jd/jdcache/service/base/AbstractDelegate;", "delegateClazz", "registerService", "T", "delegateType", "getService", "(Ljava/lang/Class;)Lcom/jd/jdcache/service/base/AbstractDelegate;", "", "url", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Lcom/jd/jdcache/JDCacheLoader;", "createDefaultLoader", "(Ljava/lang/String;Landroidx/lifecycle/LifecycleOwner;)Lcom/jd/jdcache/JDCacheLoader;", "(Landroidx/lifecycle/LifecycleOwner;Ljava/lang/String;)Lcom/jd/jdcache/JDCacheLoader;", "key", "getLoader", "(Ljava/lang/String;)Lcom/jd/jdcache/JDCacheLoader;", "getAndBindLoader", "removeLoader", "(Ljava/lang/String;)V", "Lcom/jd/jdcache/JDCacheLogger;", "logger", "setLogger", "(Lcom/jd/jdcache/JDCacheLogger;)V", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCache {
    public static final JDCache INSTANCE = new JDCache();

    private JDCache() {
    }

    public static /* synthetic */ JDCacheLoader createDefaultLoader$default(JDCache jDCache, String str, LifecycleOwner lifecycleOwner, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            lifecycleOwner = null;
        }
        return jDCache.createDefaultLoader(str, lifecycleOwner);
    }

    public static /* synthetic */ JDCacheLoader getAndBindLoader$default(JDCache jDCache, String str, LifecycleOwner lifecycleOwner, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            lifecycleOwner = null;
        }
        return jDCache.getAndBindLoader(str, lifecycleOwner);
    }

    public static /* synthetic */ void init$default(JDCache jDCache, Context context, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        jDCache.init(context, z);
    }

    private final void initInternal(Context context) {
        JDCacheSetting.INSTANCE.setAppContext(context);
        registerService(NetConnection.class);
        registerService(FileRepo.class);
        registerDefaultResourceMatcher(PreloadHtmlMatcher.class);
        registerDefaultResourceMatcher(MapResourceMatcher.class);
    }

    @Nullable
    public final JDCacheLoader createDefaultLoader(@Nullable String url, @Nullable LifecycleOwner lifecycleOwner) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            JDCacheLoader createDefaultLoader = url != null ? JDCacheMaster.INSTANCE.getInstance().createDefaultLoader(url) : null;
            if (createDefaultLoader != null) {
                createDefaultLoader.setLifecycleOwner(lifecycleOwner);
            }
            return createDefaultLoader;
        }
        return null;
    }

    @NotNull
    public final LinkedList<JDCacheResourceMatcher> createDefaultResourceMatcherList() {
        if (!JDCacheSetting.INSTANCE.getEnable()) {
            return new LinkedList<>();
        }
        return ResourceMatcherManager.INSTANCE.createDefaultMatcherList();
    }

    public final void enable(boolean enable) {
        JDCacheSetting.INSTANCE.setEnable(enable);
    }

    @Nullable
    public final JDCacheLoader getAndBindLoader(@Nullable String key, @Nullable LifecycleOwner lifecycleOwner) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            JDCacheLoader loader = key != null ? JDCacheMaster.INSTANCE.getInstance().getLoader(key) : null;
            if (loader != null) {
                loader.setLifecycleOwner(lifecycleOwner);
            }
            return loader;
        }
        return null;
    }

    @Nullable
    public final Context getContext() {
        return JDCacheSetting.INSTANCE.getAppContext();
    }

    @Nullable
    public final JDCacheParamsProvider getGlobalParams() {
        return JDCacheSetting.INSTANCE.getParamsProvider();
    }

    @Nullable
    public final JDCacheLoader getLoader(@Nullable String key) {
        if (JDCacheSetting.INSTANCE.getEnable() && key != null) {
            return JDCacheMaster.INSTANCE.getInstance().getLoader(key);
        }
        return null;
    }

    @Nullable
    public final <T extends AbstractDelegate> T getService(@NotNull Class<T> delegateType) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            return (T) DelegateManager.INSTANCE.getDelegate(delegateType);
        }
        return null;
    }

    public final void init(@NotNull Context context, boolean debug) {
        JDCacheSetting.INSTANCE.setDebug(debug);
        initInternal(context);
    }

    public final void registerDefaultResourceMatcher(@NotNull Class<? extends JDCacheResourceMatcher> matcherClazz) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            ResourceMatcherManager.INSTANCE.registerMatcher(matcherClazz);
        }
    }

    public final void registerService(@NotNull Class<? extends AbstractDelegate> delegateClazz) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            DelegateManager.INSTANCE.addDelegateClass(delegateClazz);
        }
    }

    public final void removeLoader(@Nullable String key) {
        JDCacheLoader loader;
        if (!JDCacheSetting.INSTANCE.getEnable() || key == null || (loader = JDCacheMaster.INSTANCE.getInstance().getLoader(key)) == null) {
            return;
        }
        loader.destroy();
    }

    public final void setGlobalParams(@NotNull Class<? extends JDCacheParamsProvider> paramsProviderClazz) {
        JDCacheSetting.INSTANCE.setGlobalParamsClass(paramsProviderClazz);
    }

    public final void setLogger(@Nullable JDCacheLogger logger) {
        JDCacheLog.INSTANCE.setMyLogger(logger);
    }

    public final void unregisterDefaultResourceMatcher(@NotNull Class<? extends JDCacheResourceMatcher> matcherClazz) {
        if (JDCacheSetting.INSTANCE.getEnable()) {
            ResourceMatcherManager.INSTANCE.unregisterMatcher(matcherClazz);
        }
    }

    @Nullable
    public final JDCacheLoader createDefaultLoader(@NotNull LifecycleOwner lifecycleOwner, @Nullable String str) {
        return createDefaultLoader(str, lifecycleOwner);
    }

    @Nullable
    public final JDCacheLoader getAndBindLoader(@NotNull LifecycleOwner lifecycleOwner, @Nullable String str) {
        return getAndBindLoader(str, lifecycleOwner);
    }
}
