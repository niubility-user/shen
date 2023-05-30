package com.jingdong.jdma.c;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.jdma.bean.b.c.f;
import com.jingdong.jdma.common.utils.LogUtil;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
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

    /* JADX WARN: Code restructure failed: missing block: B:595:0x01e3, code lost:
        if (r1 == 0) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:600:0x01ee, code lost:
        if (r1 == 0) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:618:0x0218, code lost:
        if (r1 == null) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:623:0x0222, code lost:
        if (r1 == null) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x0248, code lost:
        if (r1 == null) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:644:0x0252, code lost:
        if (r1 == null) goto L646;
     */
    /* JADX WARN: Code restructure failed: missing block: B:646:0x0258, code lost:
        r13.a(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:647:0x025b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x027b, code lost:
        if (r2 != 0) goto L668;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:661:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:682:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:689:0x0270 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:693:0x023f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:699:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
    */
    public void a(com.jingdong.jdma.bean.b.a aVar, com.jingdong.jdma.c.a aVar2) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        IOException e2;
        MalformedURLException e3;
        BufferedInputStream bufferedInputStream;
        com.jingdong.jdma.bean.b.b bVar;
        com.jingdong.jdma.bean.b.b bVar2;
        com.jingdong.jdma.bean.b.c.e eVar;
        com.jingdong.jdma.bean.b.b bVar3;
        String h2;
        URL url;
        com.jingdong.jdma.bean.b.c.b bVar4;
        com.jingdong.jdma.bean.b.b bVar5;
        ?? r1 = "https://";
        com.jingdong.jdma.bean.b.c.b bVar6 = 0;
        bVar6 = 0;
        bVar6 = 0;
        BufferedInputStream bufferedInputStream2 = null;
        r2 = null;
        r2 = null;
        BufferedInputStream bufferedInputStream3 = null;
        BufferedInputStream bufferedInputStream4 = null;
        try {
            try {
                h2 = aVar.h();
                if (LogUtil.isDebug()) {
                    try {
                        LogUtil.w("JDMA_JDMAHttp", "---get \u8bf7\u6c42\u5730\u5740\uff1a" + h2);
                    } catch (MalformedURLException e4) {
                        e = e4;
                        httpURLConnection = null;
                        e3 = e;
                        bVar2 = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e3);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream3);
                        aVar2.a();
                        bVar3 = bVar2;
                    } catch (IOException e5) {
                        e = e5;
                        httpURLConnection = null;
                        e2 = e;
                        bVar = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e2);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream4);
                        aVar2.a();
                        bVar3 = bVar;
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection = null;
                        th = th;
                        bufferedInputStream = null;
                        r1 = 0;
                        try {
                            eVar = new com.jingdong.jdma.bean.b.c.e(th);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            aVar2.a();
                            bVar3 = r1;
                        } catch (Throwable th3) {
                            th = th3;
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            aVar2.a();
                            if (r1 == 0) {
                            }
                            throw th;
                        }
                    }
                }
                if (h2.startsWith("https://")) {
                    a();
                }
                url = new URL(h2);
                if (!TextUtils.isEmpty(aVar.e())) {
                    String replaceFirst = h2.replaceFirst(url.getHost(), aVar.e());
                    httpURLConnection = (HttpURLConnection) new URL(replaceFirst).openConnection();
                    try {
                        httpURLConnection.setRequestProperty(HttpHeaders.HOST, url.getHost());
                        if (LogUtil.isDebug()) {
                            LogUtil.w("JDMA_JDMAHttp", "HttpDns\u7f51\u7edc\u8bf7\u6c42\u5730\u5740\uff1a" + replaceFirst);
                        }
                    } catch (MalformedURLException e6) {
                        e = e6;
                        e3 = e;
                        bVar2 = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e3);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream3);
                        aVar2.a();
                        bVar3 = bVar2;
                    } catch (IOException e7) {
                        e = e7;
                        e2 = e;
                        bVar = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e2);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream4);
                        aVar2.a();
                        bVar3 = bVar;
                    } catch (Throwable th4) {
                        th = th4;
                        th = th;
                        bufferedInputStream = null;
                        r1 = 0;
                        eVar = new com.jingdong.jdma.bean.b.c.e(th);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        aVar2.a();
                        bVar3 = r1;
                    }
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                }
            } catch (MalformedURLException e8) {
                e3 = e8;
                httpURLConnection = null;
            } catch (IOException e9) {
                e2 = e9;
                httpURLConnection = null;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = null;
            }
            try {
                if (h2.startsWith("https://")) {
                    a(url.getHost(), httpURLConnection);
                }
                a(httpURLConnection, aVar);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        byte[] bArr = new byte[256];
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            int read = bufferedInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            sb.append(new String(bArr, 0, read));
                        }
                        if (LogUtil.isDebug()) {
                            LogUtil.w("JDMA_JDMAHttp", "---get \u8bf7\u6c42\u6210\u529f\uff0c\u8fd4\u56de\u62a5\u6587\uff1a" + sb.toString());
                        }
                        r1 = new com.jingdong.jdma.bean.b.b();
                        try {
                            r1.a(httpURLConnection.getResponseCode());
                            r1.a(new String(httpURLConnection.getResponseMessage().getBytes(), "utf-8"));
                            r1.b(new String(sb.toString().getBytes(), "utf-8"));
                            bufferedInputStream2 = bufferedInputStream;
                            bVar4 = null;
                            bVar5 = r1;
                        } catch (MalformedURLException e10) {
                            e3 = e10;
                            bufferedInputStream3 = bufferedInputStream;
                            bVar2 = r1;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e3);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream3);
                            aVar2.a();
                            bVar3 = bVar2;
                        } catch (IOException e11) {
                            e2 = e11;
                            bufferedInputStream4 = bufferedInputStream;
                            bVar = r1;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e2);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream4);
                            aVar2.a();
                            bVar3 = bVar;
                        } catch (Throwable th6) {
                            th = th6;
                            eVar = new com.jingdong.jdma.bean.b.c.e(th);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            aVar2.a();
                            bVar3 = r1;
                        }
                    } catch (MalformedURLException e12) {
                        e3 = e12;
                        bVar2 = null;
                        bufferedInputStream3 = bufferedInputStream;
                    } catch (IOException e13) {
                        e2 = e13;
                        bVar = null;
                        bufferedInputStream4 = bufferedInputStream;
                    } catch (Throwable th7) {
                        th = th7;
                        r1 = 0;
                    }
                } else {
                    if (LogUtil.isDebug()) {
                        LogUtil.w("JDMA_JDMAHttp", "---get \u54cd\u5e94\u5931\u8d25--responseCode=" + httpURLConnection.getResponseCode() + "---responseMessage=" + httpURLConnection.getResponseMessage());
                    }
                    bVar4 = new com.jingdong.jdma.bean.b.c.b(httpURLConnection.getResponseCode());
                    try {
                        bVar4.b(httpURLConnection.getResponseMessage());
                        bVar5 = null;
                    } catch (MalformedURLException e14) {
                        e3 = e14;
                        bVar2 = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e3);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Throwable th8) {
                                try {
                                    th8.printStackTrace();
                                    aVar2.a();
                                    bVar3 = bVar2;
                                } finally {
                                }
                            }
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream3);
                        aVar2.a();
                        bVar3 = bVar2;
                    } catch (IOException e15) {
                        e2 = e15;
                        bVar = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e2);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Throwable th9) {
                                try {
                                    th9.printStackTrace();
                                    aVar2.a();
                                    bVar3 = bVar;
                                } finally {
                                }
                            }
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream4);
                        aVar2.a();
                        bVar3 = bVar;
                    } catch (Throwable th10) {
                        th = th10;
                        r1 = 0;
                        bVar6 = bVar4;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(th);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Throwable th11) {
                                try {
                                    th11.printStackTrace();
                                    aVar2.a();
                                    bVar3 = r1;
                                } finally {
                                    aVar2.a();
                                    if (r1 == 0) {
                                        aVar2.a(eVar);
                                    } else {
                                        aVar2.a(r1);
                                    }
                                }
                            }
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        aVar2.a();
                        bVar3 = r1;
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable th12) {
                        try {
                            th12.printStackTrace();
                            aVar2.a();
                            bVar3 = bVar5;
                            if (bVar5 == null) {
                                if (bVar4 == null) {
                                    return;
                                }
                            }
                        } finally {
                        }
                    }
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream2);
                aVar2.a();
                bVar3 = bVar5;
                if (bVar5 == null) {
                    if (bVar4 == null) {
                        return;
                    }
                    aVar2.a(bVar4);
                    return;
                }
            } catch (MalformedURLException e16) {
                e = e16;
                e3 = e;
                bVar2 = null;
                eVar = new com.jingdong.jdma.bean.b.c.e(e3);
                if (httpURLConnection != null) {
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream3);
                aVar2.a();
                bVar3 = bVar2;
            } catch (IOException e17) {
                e = e17;
                e2 = e;
                bVar = null;
                eVar = new com.jingdong.jdma.bean.b.c.e(e2);
                if (httpURLConnection != null) {
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream4);
                aVar2.a();
                bVar3 = bVar;
            } catch (Throwable th13) {
                th = th13;
                th = th;
                bufferedInputStream = null;
                r1 = 0;
                eVar = new com.jingdong.jdma.bean.b.c.e(th);
                if (httpURLConnection != null) {
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                aVar2.a();
                bVar3 = r1;
            }
            aVar2.a(bVar3);
        } catch (Throwable th14) {
            th = th14;
            bVar6 = aVar;
            bufferedInputStream = null;
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th15) {
                    try {
                        th15.printStackTrace();
                        throw th;
                    } finally {
                        aVar2.a();
                        if (r1 != 0) {
                            aVar2.a(r1);
                        } else if (bVar6 != 0) {
                            aVar2.a(bVar6);
                        }
                    }
                }
            }
            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
            aVar2.a();
            if (r1 == 0) {
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:702:0x023e, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x0249, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:723:0x0274, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:728:0x027f, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:747:0x02bb, code lost:
        if (r6 != null) goto L774;
     */
    /* JADX WARN: Code restructure failed: missing block: B:752:0x02c5, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:768:0x02ef, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:773:0x02f9, code lost:
        if (r6 == null) goto L775;
     */
    /* JADX WARN: Code restructure failed: missing block: B:775:0x02ff, code lost:
        r18.a(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:776:0x0302, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:790:0x0324, code lost:
        if ("utf-8" != 0) goto L797;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0314: IF  (r11 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:787:0x0319, block:B:785:0x0314 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x031c: INVOKE (r7 I:java.io.Closeable) type: STATIC call: com.jingdong.jdma.common.utils.a.a(java.io.Closeable):void A[Catch: all -> 0x0327, MD:(java.io.Closeable):void (m), TRY_LEAVE] (LINE:97), block:B:787:0x0319 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0319: INVOKE (r8 I:java.io.Closeable) type: STATIC call: com.jingdong.jdma.common.utils.a.a(java.io.Closeable):void A[Catch: all -> 0x0327, MD:(java.io.Closeable):void (m)] (LINE:96), block:B:787:0x0319 */
    /* JADX WARN: Removed duplicated region for block: B:741:0x029a A[Catch: all -> 0x0312, TRY_ENTER, TryCatch #36 {all -> 0x0312, blocks: (B:697:0x022b, B:718:0x0261, B:741:0x029a, B:742:0x02a4, B:763:0x02dc), top: B:837:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:742:0x02a4 A[Catch: all -> 0x0312, TRY_LEAVE, TryCatch #36 {all -> 0x0312, blocks: (B:697:0x022b, B:718:0x0261, B:741:0x029a, B:742:0x02a4, B:763:0x02dc), top: B:837:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:815:0x02e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:819:0x0268 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:823:0x02af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:825:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String, com.jingdong.jdma.bean.b.c.a] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(com.jingdong.jdma.bean.b.a aVar, com.jingdong.jdma.c.a aVar2) {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        Closeable a2;
        Closeable a3;
        BufferedInputStream bufferedInputStream;
        com.jingdong.jdma.bean.b.c.a eVar;
        long length;
        com.jingdong.jdma.bean.b.c.b bVar;
        boolean z = true;
        com.jingdong.jdma.bean.b.b bVar2 = null;
        try {
            try {
                String h2 = aVar.h();
                if (LogUtil.isDebug()) {
                    try {
                        LogUtil.w("JDMA_JDMAHttp", "---post \u8bf7\u6c42\u5730\u5740\uff1a" + h2);
                    } catch (MalformedURLException e2) {
                        e = e2;
                        httpURLConnection = null;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (SocketTimeoutException e3) {
                        e = e3;
                        httpURLConnection = null;
                        outputStream = null;
                        bufferedInputStream = null;
                        z = false;
                        if (!z) {
                        }
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (IOException e4) {
                        e = e4;
                        httpURLConnection = null;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (Throwable th) {
                        th = th;
                        httpURLConnection = null;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(th);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    }
                }
                if (h2.startsWith("https://")) {
                    a();
                }
                URL url = new URL(h2);
                if (!TextUtils.isEmpty(aVar.e())) {
                    String replaceFirst = h2.replaceFirst(url.getHost(), aVar.e());
                    httpURLConnection = (HttpURLConnection) new URL(replaceFirst).openConnection();
                    try {
                        httpURLConnection.setRequestProperty(HttpHeaders.HOST, url.getHost());
                        if (LogUtil.isDebug()) {
                            LogUtil.w("JDMA_JDMAHttp", String.format("Httpdns\u7f51\u7edc\u8bf7\u6c42\u5730\u5740=%s", replaceFirst));
                        }
                    } catch (MalformedURLException e5) {
                        e = e5;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (SocketTimeoutException e6) {
                        e = e6;
                        outputStream = null;
                        bufferedInputStream = null;
                        z = false;
                        if (!z) {
                        }
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (IOException e7) {
                        e = e7;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(e);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = null;
                        bufferedInputStream = null;
                        eVar = new com.jingdong.jdma.bean.b.c.e(th);
                        if (httpURLConnection != null) {
                        }
                        com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                        com.jingdong.jdma.common.utils.a.a(outputStream);
                        aVar2.a();
                    }
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                }
                try {
                    if (h2.startsWith("https://")) {
                        a(url.getHost(), httpURLConnection);
                    }
                    a(httpURLConnection, aVar);
                    httpURLConnection.connect();
                    outputStream = httpURLConnection.getOutputStream();
                } catch (MalformedURLException e8) {
                    e = e8;
                    outputStream = null;
                } catch (SocketTimeoutException e9) {
                    e = e9;
                    outputStream = null;
                } catch (IOException e10) {
                    e = e10;
                    outputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = null;
                }
            } catch (MalformedURLException e11) {
                e = e11;
                outputStream = null;
                httpURLConnection = null;
            } catch (SocketTimeoutException e12) {
                e = e12;
                outputStream = null;
                httpURLConnection = null;
            } catch (IOException e13) {
                e = e13;
                outputStream = null;
                httpURLConnection = null;
            } catch (Throwable th4) {
                th = th4;
                outputStream = null;
                httpURLConnection = null;
            }
            try {
                try {
                    byte[] bytes = aVar.a().getBytes("utf-8");
                    length = bytes.length;
                    outputStream.write(bytes);
                    outputStream.flush();
                } catch (MalformedURLException e14) {
                    e = e14;
                    bufferedInputStream = null;
                    eVar = new com.jingdong.jdma.bean.b.c.e(e);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th5) {
                            try {
                                th5.printStackTrace();
                                aVar2.a();
                            } finally {
                            }
                        }
                    }
                    com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                    com.jingdong.jdma.common.utils.a.a(outputStream);
                    aVar2.a();
                } catch (IOException e15) {
                    e = e15;
                    bufferedInputStream = null;
                    eVar = new com.jingdong.jdma.bean.b.c.e(e);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th6) {
                            try {
                                th6.printStackTrace();
                                aVar2.a();
                            } finally {
                                aVar2.a();
                                if (bVar2 == null) {
                                    aVar2.a(eVar);
                                } else {
                                    aVar2.a(bVar2);
                                }
                            }
                        }
                    }
                    com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                    com.jingdong.jdma.common.utils.a.a(outputStream);
                    aVar2.a();
                } catch (Throwable th7) {
                    th = th7;
                    bufferedInputStream = null;
                    eVar = new com.jingdong.jdma.bean.b.c.e(th);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th8) {
                            try {
                                th8.printStackTrace();
                                aVar2.a();
                            } finally {
                                aVar2.a();
                                if (bVar2 == null) {
                                    aVar2.a(eVar);
                                } else {
                                    aVar2.a(bVar2);
                                }
                            }
                        }
                    }
                    com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                    com.jingdong.jdma.common.utils.a.a(outputStream);
                    aVar2.a();
                }
            } catch (SocketTimeoutException e16) {
                e = e16;
                z = false;
                bufferedInputStream = null;
                if (!z) {
                }
                if (httpURLConnection != null) {
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                com.jingdong.jdma.common.utils.a.a(outputStream);
                aVar2.a();
            }
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    byte[] bArr = new byte[256];
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        sb.append(new String(bArr, 0, read));
                    }
                    if (LogUtil.isDebug()) {
                        LogUtil.w("JDMA_JDMAHttp", "0.---post \u8bf7\u6c42\u8fd4\u56de\u62a5\u6587 URL \uff1a" + httpURLConnection.getURL());
                        LogUtil.w("JDMA_JDMAHttp", "1.---post \u8bf7\u6c42\u8fd4\u56de\u62a5\u6587 ResponseString \uff1a" + sb.toString());
                        LogUtil.w("JDMA_JDMAHttp", "2.---post \u8bf7\u6c42\u8fd4\u56de\u62a5\u6587 ResponseCode:" + httpURLConnection.getResponseCode());
                        LogUtil.w("JDMA_JDMAHttp", "3.---post \u8bf7\u6c42\u8fd4\u56de\u62a5\u6587 ResponseMessage:" + httpURLConnection.getResponseMessage());
                    }
                    if (httpURLConnection.getResponseCode() == 200) {
                        com.jingdong.jdma.bean.b.b bVar3 = new com.jingdong.jdma.bean.b.b();
                        try {
                            bVar3.a(httpURLConnection.getResponseCode());
                            bVar3.a(new String(httpURLConnection.getResponseMessage().getBytes(), "utf-8"));
                            bVar3.b(new String(sb.toString().getBytes(), "utf-8"));
                            bVar = null;
                            bVar2 = bVar3;
                        } catch (MalformedURLException e17) {
                            e = e17;
                            bVar2 = bVar3;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (SocketTimeoutException e18) {
                            e = e18;
                            bVar2 = bVar3;
                            if (!z) {
                            }
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (IOException e19) {
                            e = e19;
                            bVar2 = bVar3;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (Throwable th9) {
                            th = th9;
                            bVar2 = bVar3;
                            eVar = new com.jingdong.jdma.bean.b.c.e(th);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        }
                    } else {
                        bVar = new com.jingdong.jdma.bean.b.c.b(httpURLConnection.getResponseCode());
                        try {
                            bVar.b(httpURLConnection.getResponseMessage());
                            bVar.a(length);
                        } catch (MalformedURLException e20) {
                            e = e20;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (SocketTimeoutException e21) {
                            e = e21;
                            if (!z) {
                            }
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (IOException e22) {
                            e = e22;
                            eVar = new com.jingdong.jdma.bean.b.c.e(e);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        } catch (Throwable th10) {
                            th = th10;
                            eVar = new com.jingdong.jdma.bean.b.c.e(th);
                            if (httpURLConnection != null) {
                            }
                            com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                            com.jingdong.jdma.common.utils.a.a(outputStream);
                            aVar2.a();
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th11) {
                            try {
                                th11.printStackTrace();
                                aVar2.a();
                                if (bVar2 == null) {
                                    if (bVar == null) {
                                        return;
                                    }
                                }
                            } catch (Throwable th12) {
                                aVar2.a();
                                if (bVar2 != null) {
                                    aVar2.a(bVar2);
                                } else if (bVar != null) {
                                    aVar2.a(bVar);
                                }
                                throw th12;
                            }
                        }
                    }
                    com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                    com.jingdong.jdma.common.utils.a.a(outputStream);
                    aVar2.a();
                    if (bVar2 == null) {
                        if (bVar == null) {
                            return;
                        }
                        aVar2.a(bVar);
                        return;
                    }
                } catch (MalformedURLException e23) {
                    e = e23;
                } catch (SocketTimeoutException e24) {
                    e = e24;
                } catch (IOException e25) {
                    e = e25;
                } catch (Throwable th13) {
                    th = th13;
                }
            } catch (SocketTimeoutException e26) {
                e = e26;
                bufferedInputStream = null;
                if (!z) {
                    eVar = new com.jingdong.jdma.bean.b.c.c(e.getMessage());
                } else {
                    eVar = new f(e.getMessage());
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable th14) {
                        try {
                            th14.printStackTrace();
                            aVar2.a();
                        } finally {
                        }
                    }
                }
                com.jingdong.jdma.common.utils.a.a(bufferedInputStream);
                com.jingdong.jdma.common.utils.a.a(outputStream);
                aVar2.a();
            }
            aVar2.a(bVar2);
        } catch (Throwable th15) {
            if (httpURLConnection2 != null) {
                try {
                    httpURLConnection2.disconnect();
                } catch (Throwable th16) {
                    try {
                        th16.printStackTrace();
                        throw th15;
                    } finally {
                        aVar2.a();
                        if (0 != 0) {
                            aVar2.a((com.jingdong.jdma.bean.b.b) null);
                        } else if ("utf-8" != 0) {
                            aVar2.a((com.jingdong.jdma.bean.b.c.a) "utf-8");
                        }
                    }
                }
            }
            com.jingdong.jdma.common.utils.a.a(a2);
            com.jingdong.jdma.common.utils.a.a(a3);
            aVar2.a();
            if (0 == 0) {
            }
            throw th15;
        }
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
