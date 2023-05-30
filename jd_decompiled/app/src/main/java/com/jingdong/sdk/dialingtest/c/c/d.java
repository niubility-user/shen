package com.jingdong.sdk.dialingtest.c.c;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* loaded from: classes7.dex */
public class d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private String f14738g;

    /* renamed from: j  reason: collision with root package name */
    private String f14741j;

    /* renamed from: k  reason: collision with root package name */
    private Map<String, String> f14742k;

    /* renamed from: h  reason: collision with root package name */
    private int f14739h = 10000;

    /* renamed from: i  reason: collision with root package name */
    private int f14740i = 20000;

    /* renamed from: l  reason: collision with root package name */
    private boolean f14743l = false;

    /* renamed from: m  reason: collision with root package name */
    private boolean f14744m = false;

    /* renamed from: n  reason: collision with root package name */
    private boolean f14745n = false;
    private boolean o = true;
    private byte[] p = null;
    private boolean q = false;
    private b r = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements X509TrustManager {
        final /* synthetic */ c a;

        a(d dVar, c cVar) {
            this.a = cVar;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null || x509CertificateArr.length == 0) {
                return;
            }
            this.a.f14733h = Arrays.asList(x509CertificateArr);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* loaded from: classes7.dex */
    public interface b {
        void a(c cVar);
    }

    public void a() {
        com.jingdong.sdk.dialingtest.c.d.a.e().b(this);
    }

    public void b(int i2) {
        this.f14739h = i2;
    }

    public void c(b bVar) {
        this.r = bVar;
    }

    public void d(String str) {
        this.f14738g = str;
    }

    public void e(Map<String, String> map) {
        this.f14742k = map;
    }

    public void f(boolean z) {
        this.f14744m = z;
    }

    public void g() {
        run();
    }

    public void h(String str) {
        this.f14741j = str;
    }

    public void i(boolean z) {
        this.o = z;
    }

    public void j(boolean z) {
        this.q = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x02b4 A[Catch: all -> 0x0304, Exception -> 0x030d, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x030d, all -> 0x0304, blocks: (B:108:0x02b4, B:119:0x0300, B:89:0x0250), top: B:162:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0300 A[Catch: all -> 0x0304, Exception -> 0x030d, TRY_ENTER, TRY_LEAVE, TryCatch #23 {Exception -> 0x030d, all -> 0x0304, blocks: (B:108:0x02b4, B:119:0x0300, B:89:0x0250), top: B:162:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 842
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.dialingtest.c.c.d.run():void");
    }

    private SSLContext a(c cVar) {
        SSLContext sSLContext = null;
        try {
            SSLContext sSLContext2 = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            try {
                sSLContext2.init(null, new TrustManager[]{new a(this, cVar)}, new SecureRandom());
                return sSLContext2;
            } catch (Exception e2) {
                e = e2;
                sSLContext = sSLContext2;
                e.printStackTrace();
                return sSLContext;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
