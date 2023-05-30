package com.jd.jdcache;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001d\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001b\u00a8\u0006!"}, d2 = {"Lcom/jd/jdcache/JDCacheSetting;", "", "Ljava/lang/Class;", "Lcom/jd/jdcache/JDCacheParamsProvider;", "clazz", "", "setGlobalParamsClass", "(Ljava/lang/Class;)V", "getParamsProvider", "()Lcom/jd/jdcache/JDCacheParamsProvider;", "paramsProvider", "Lcom/jd/jdcache/JDCacheParamsProvider;", "Landroid/content/Context;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "paramsProviderClass", "Ljava/lang/Class;", "", "debug", "Z", "getDebug", "()Z", "setDebug", "(Z)V", "enable", "getEnable", "setEnable", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCacheSetting {
    @Nullable
    private static Context appContext;
    private static boolean debug;
    private static JDCacheParamsProvider paramsProvider;
    public static final JDCacheSetting INSTANCE = new JDCacheSetting();
    private static boolean enable = true;
    private static Class<? extends JDCacheParamsProvider> paramsProviderClass = JDCacheParamsProvider.class;

    private JDCacheSetting() {
    }

    @Nullable
    public final Context getAppContext() {
        return appContext;
    }

    public final boolean getDebug() {
        return debug;
    }

    public final boolean getEnable() {
        return enable;
    }

    @Nullable
    public final JDCacheParamsProvider getParamsProvider() {
        if (paramsProvider == null) {
            synchronized (this) {
                if (paramsProvider == null) {
                    paramsProvider = paramsProviderClass.newInstance();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return paramsProvider;
    }

    public final void setAppContext(@Nullable Context context) {
        appContext = context;
    }

    public final void setDebug(boolean z) {
        debug = z;
    }

    public final void setEnable(boolean z) {
        enable = z;
    }

    public final void setGlobalParamsClass(@NotNull Class<? extends JDCacheParamsProvider> clazz) {
        paramsProvider = null;
        paramsProviderClass = clazz;
    }
}
