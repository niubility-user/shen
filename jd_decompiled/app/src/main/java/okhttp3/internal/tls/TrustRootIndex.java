package okhttp3.internal.tls;

import java.security.cert.X509Certificate;

/* loaded from: classes11.dex */
public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
