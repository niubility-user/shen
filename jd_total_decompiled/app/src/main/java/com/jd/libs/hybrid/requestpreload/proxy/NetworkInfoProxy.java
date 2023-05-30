package com.jd.libs.hybrid.requestpreload.proxy;

import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J#\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u000e\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/proxy/NetworkInfoProxy;", "Lcom/jd/libs/hybrid/requestpreload/proxy/IProxy;", "", "getName", "()Ljava/lang/String;", "", "isBeta", "()Z", "url", "Ljava/util/HashMap;", "getHeader", "(Ljava/lang/String;)Ljava/util/HashMap;", "getCookie", "(Ljava/lang/String;)Ljava/lang/String;", "getUserAgent", "<init>", "()V", "Companion", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public abstract class NetworkInfoProxy implements IProxy {
    @NotNull
    public static final String PROXY_NAME = "NetworkInfoProxy";

    @NotNull
    public abstract String getCookie(@NotNull String url);

    @NotNull
    public abstract HashMap<String, String> getHeader(@NotNull String url);

    @Override // com.jd.libs.hybrid.requestpreload.proxy.IProxy
    @NotNull
    public String getName() {
        return PROXY_NAME;
    }

    @NotNull
    public abstract String getUserAgent(@NotNull String url);

    public abstract boolean isBeta();
}
