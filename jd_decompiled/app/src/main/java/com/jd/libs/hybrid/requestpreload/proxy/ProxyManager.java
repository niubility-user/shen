package com.jd.libs.hybrid.requestpreload.proxy;

import com.jingdong.jdsdk.a.a;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0010J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u0005\u001a\u00020\u00042\u0010\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0007\u00a2\u0006\u0004\b\u0005\u0010\tJ%\u0010\r\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\n*\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/proxy/ProxyManager;", "", "Lcom/jd/libs/hybrid/requestpreload/proxy/IProxy;", "proxy", "", "registerProxy", "(Lcom/jd/libs/hybrid/requestpreload/proxy/IProxy;)V", "Ljava/lang/Class;", "clazz", "(Ljava/lang/Class;)V", "T", "", "proxyName", "getProxy", "(Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/proxy/IProxy;", "destroy", "()V", "", a.a, "Ljava/util/Map;", "mProxyMap", "<init>", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class ProxyManager {

    /* renamed from: a  reason: from kotlin metadata */
    private final Map<String, IProxy> mProxyMap = new HashMap();

    public final void destroy() {
        this.mProxyMap.clear();
    }

    @Nullable
    public final <T extends IProxy> T getProxy(@Nullable String proxyName) {
        try {
            return (T) this.mProxyMap.get(proxyName);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final void registerProxy(@Nullable IProxy proxy) {
        if (proxy != null) {
            this.mProxyMap.put(proxy.getName(), proxy);
        }
    }

    public final void registerProxy(@NotNull Class<? extends IProxy> clazz) {
        try {
            IProxy newInstance = clazz.newInstance();
            if (newInstance != null) {
                this.mProxyMap.put(newInstance.getName(), newInstance);
            }
        } catch (Exception unused) {
        }
    }
}
