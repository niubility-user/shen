package com.jd.libs.hybrid.requestpreload.proxy;

import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\f\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/proxy/NetworkInfoProxyAdapter;", "Lcom/jd/libs/hybrid/requestpreload/proxy/NetworkInfoProxy;", "", "isBeta", "()Z", "", "url", "Ljava/util/HashMap;", "getHeader", "(Ljava/lang/String;)Ljava/util/HashMap;", "getCookie", "(Ljava/lang/String;)Ljava/lang/String;", "getUserAgent", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public class NetworkInfoProxyAdapter extends NetworkInfoProxy {
    @Override // com.jd.libs.hybrid.requestpreload.proxy.NetworkInfoProxy
    @NotNull
    public String getCookie(@NotNull String url) {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.NetworkInfoProxy
    @NotNull
    public HashMap<String, String> getHeader(@NotNull String url) {
        return new HashMap<>();
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.NetworkInfoProxy
    @NotNull
    public String getUserAgent(@NotNull String url) {
        return OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR;
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.NetworkInfoProxy
    public boolean isBeta() {
        return false;
    }
}
