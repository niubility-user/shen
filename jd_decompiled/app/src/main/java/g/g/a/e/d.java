package g.g.a.e;

import android.content.Context;
import g.g.a.f;
import java.io.BufferedInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* loaded from: classes18.dex */
public class d {
    static String a = "X509TrustManager";
    private static SSLContext b;

    /* renamed from: c  reason: collision with root package name */
    public static boolean f19602c;

    /* loaded from: classes18.dex */
    static class a implements X509TrustManager {
        final /* synthetic */ Certificate a;

        a(Certificate certificate) {
            this.a = certificate;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            f.c(d.a, "checkClientTrusted1 --> authType = " + str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            f.c(d.a, "checkServerTrusted2 --> authType = " + str);
            for (X509Certificate x509Certificate : x509CertificateArr) {
                x509Certificate.checkValidity();
                try {
                    x509Certificate.verify(this.a.getPublicKey());
                    d.f19602c = true;
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e2) {
                    e2.printStackTrace();
                    d.f19602c = false;
                }
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static SSLContext a(Context context) {
        if (b == null) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(context.getResources().getAssets().open("GeoTrust_Global_CA.cer"));
                Certificate generateCertificate = certificateFactory.generateCertificate(bufferedInputStream);
                bufferedInputStream.close();
                SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
                b = sSLContext;
                sSLContext.init(null, new X509TrustManager[]{new a(generateCertificate)}, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return b;
    }
}
