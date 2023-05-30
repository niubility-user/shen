package com.jd.jdcache.service.impl.net;

import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jd/jdcache/service/impl/net/SSLUtils;", "", "Ljavax/net/ssl/SSLSocketFactory;", "defaultSSLSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "Ljava/net/URL;", "url", "Ljavax/net/ssl/HostnameVerifier;", "defaultHostnameVerifier", "(Ljava/net/URL;)Ljavax/net/ssl/HostnameVerifier;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class SSLUtils {
    public static final SSLUtils INSTANCE = new SSLUtils();

    private SSLUtils() {
    }

    @NotNull
    public final HostnameVerifier defaultHostnameVerifier(@NotNull URL url) {
        return new HostVerifier(url);
    }

    @NotNull
    public final SSLSocketFactory defaultSSLSocketFactory() {
        return new TLSSocketFactory();
    }
}
