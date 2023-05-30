package com.jd.jdcache.service.impl.net;

import com.jd.aips.verify.tracker.VerifyTracker;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\u00020\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/jd/jdcache/service/impl/net/HostVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "", "hostname", "Ljavax/net/ssl/SSLSession;", "session", "", VerifyTracker.P_CODE_VERIFY, "(Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "Ljava/net/URL;", "url", "Ljava/net/URL;", "getUrl", "()Ljava/net/URL;", "<init>", "(Ljava/net/URL;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class HostVerifier implements HostnameVerifier {
    @NotNull
    private final URL url;

    public HostVerifier(@NotNull URL url) {
        this.url = url;
    }

    @NotNull
    public final URL getUrl() {
        return this.url;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(@Nullable String hostname, @Nullable SSLSession session) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.url.getHost(), session);
    }
}
