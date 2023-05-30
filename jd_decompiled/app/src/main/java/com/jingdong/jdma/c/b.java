package com.jingdong.jdma.c;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* loaded from: classes12.dex */
public class b {

    /* loaded from: classes12.dex */
    public class a implements HostnameVerifier {
        final /* synthetic */ String a;

        a(b bVar, String str) {
            this.a = str;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.a, sSLSession);
        }
    }

    /* renamed from: com.jingdong.jdma.c.b$b */
    /* loaded from: classes12.dex */
    public class C0488b implements X509TrustManager {
        C0488b(b bVar) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                x509Certificate.checkValidity();
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                x509Certificate.checkValidity();
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:355:0x01e3, code lost:
        if (r1 == 0) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x01ee, code lost:
        if (r1 == 0) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x0218, code lost:
        if (r1 == null) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x0222, code lost:
        if (r1 == null) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0248, code lost:
        if (r1 == null) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0252, code lost:
        if (r1 == null) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x0258, code lost:
        r13.a(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x025b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x027b, code lost:
        if (r2 != 0) goto L428;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:421:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0270 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:453:0x023f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:459:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.jingdong.jdma.c.a] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.jingdong.jdma.bean.b.b] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.jingdong.jdma.bean.b.b] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.jingdong.jdma.bean.b.b] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.jingdong.jdma.bean.b.c.a] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.jingdong.jdma.bean.b.a r12, com.jingdong.jdma.c.a r13) {
        /*
            Method dump skipped, instructions count: 675
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdma.c.b.a(com.jingdong.jdma.bean.b.a, com.jingdong.jdma.c.a):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:417:0x023e, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x0249, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x0274, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x027f, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:462:0x02bb, code lost:
        if (r6 != null) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:467:0x02c5, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x02ef, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x02f9, code lost:
        if (r6 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x02ff, code lost:
        r18.a(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x0302, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0324, code lost:
        if ("utf-8" != 0) goto L512;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0314: IF  (r11 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:502:0x0319, block:B:500:0x0314 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x031c: INVOKE (r7 I:java.io.Closeable) type: STATIC call: com.jingdong.jdma.common.utils.a.a(java.io.Closeable):void A[Catch: all -> 0x0327, MD:(java.io.Closeable):void (m), TRY_LEAVE] (LINE:97), block:B:502:0x0319 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0319: INVOKE (r8 I:java.io.Closeable) type: STATIC call: com.jingdong.jdma.common.utils.a.a(java.io.Closeable):void A[Catch: all -> 0x0327, MD:(java.io.Closeable):void (m)] (LINE:96), block:B:502:0x0319 */
    /* JADX WARN: Removed duplicated region for block: B:456:0x029a A[Catch: all -> 0x0312, TRY_ENTER, TryCatch #36 {all -> 0x0312, blocks: (B:412:0x022b, B:433:0x0261, B:456:0x029a, B:457:0x02a4, B:478:0x02dc), top: B:552:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:457:0x02a4 A[Catch: all -> 0x0312, TRY_LEAVE, TryCatch #36 {all -> 0x0312, blocks: (B:412:0x022b, B:433:0x0261, B:456:0x029a, B:457:0x02a4, B:478:0x02dc), top: B:552:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:530:0x02e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:534:0x0268 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:538:0x02af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String, com.jingdong.jdma.bean.b.c.a] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(com.jingdong.jdma.bean.b.a r17, com.jingdong.jdma.c.a r18) {
        /*
            Method dump skipped, instructions count: 845
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdma.c.b.b(com.jingdong.jdma.bean.b.a, com.jingdong.jdma.c.a):void");
    }

    private void a(HttpURLConnection httpURLConnection, com.jingdong.jdma.bean.b.a aVar) throws ProtocolException {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestMethod(aVar.f());
        httpURLConnection.setReadTimeout(aVar.g());
        httpURLConnection.setConnectTimeout(aVar.b());
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        if (aVar.d() != null) {
            for (Map.Entry<String, String> entry : aVar.d().entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void a(String str, HttpURLConnection httpURLConnection) {
        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new a(this, str));
    }

    private void a() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
        sSLContext.init(null, new X509TrustManager[]{new C0488b(this)}, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
    }
}
