package com.unionpay.a;

import com.unionpay.utils.j;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

/* loaded from: classes11.dex */
public final class c {
    private String a = null;
    private InputStream b = null;

    /* renamed from: c  reason: collision with root package name */
    private d f18143c;
    private String d;

    public c(d dVar, String str) {
        this.f18143c = null;
        this.f18143c = dVar;
        this.d = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x010e, code lost:
        if (r1 != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0110, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x011c, code lost:
        if (r1 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0125, code lost:
        if (r1 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x012e, code lost:
        if (r1 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0138, code lost:
        if (r1 == null) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int a() {
        InputStream inputStream;
        HttpsURLConnection httpsURLConnection;
        j.a("uppay", "HttpConn.connect() +++");
        d dVar = this.f18143c;
        int i2 = 1;
        try {
            try {
                if (dVar == null) {
                    j.c("uppay", "params==null!!!");
                    return 1;
                }
                try {
                    try {
                        try {
                            URL a = dVar.a();
                            if ("https".equals(a.getProtocol().toLowerCase())) {
                                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) a.openConnection();
                                httpsURLConnection2.setSSLSocketFactory(new a(this.d).a().getSocketFactory());
                                httpsURLConnection = httpsURLConnection2;
                            } else {
                                httpsURLConnection = (HttpURLConnection) a.openConnection();
                            }
                            httpsURLConnection.setRequestMethod(this.f18143c.b());
                            httpsURLConnection.setReadTimeout(60000);
                            httpsURLConnection.setConnectTimeout(30000);
                            httpsURLConnection.setInstanceFollowRedirects(true);
                            httpsURLConnection.setUseCaches(false);
                            HashMap d = this.f18143c.d();
                            if (d != null) {
                                for (String str : d.keySet()) {
                                    httpsURLConnection.setRequestProperty(str, (String) d.get(str));
                                }
                            }
                            String b = this.f18143c.b();
                            char c2 = '\uffff';
                            int hashCode = b.hashCode();
                            if (hashCode != 70454) {
                                if (hashCode == 2461856 && b.equals("POST")) {
                                    c2 = 1;
                                }
                            } else if (b.equals("GET")) {
                                c2 = 0;
                            }
                            if (c2 == 1) {
                                httpsURLConnection.setDoOutput(true);
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream(), "UTF-8");
                                outputStreamWriter.write(this.f18143c.c());
                                outputStreamWriter.flush();
                                outputStreamWriter.close();
                            }
                            httpsURLConnection.connect();
                            if (httpsURLConnection.getResponseCode() == 200) {
                                InputStream inputStream2 = httpsURLConnection.getInputStream();
                                this.b = inputStream2;
                                if (inputStream2 != null) {
                                    this.a = com.unionpay.utils.b.a(inputStream2, "UTF-8");
                                    i2 = 0;
                                }
                            } else if (httpsURLConnection.getResponseCode() == 401) {
                                i2 = 8;
                            } else {
                                j.c("uppay", "http status code:" + httpsURLConnection.getResponseCode());
                            }
                            inputStream = this.b;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            inputStream = this.b;
                        } catch (IllegalStateException e3) {
                            e3.printStackTrace();
                            inputStream = this.b;
                        }
                    } catch (SSLHandshakeException e4) {
                        e4.printStackTrace();
                        i2 = 4;
                        inputStream = this.b;
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                    inputStream = this.b;
                }
            } catch (Exception unused) {
            }
            j.a("uppay", "HttpConn.connect() ---");
            return i2;
        } catch (Throwable th) {
            try {
                InputStream inputStream3 = this.b;
                if (inputStream3 != null) {
                    inputStream3.close();
                }
            } catch (Exception unused2) {
            }
            throw th;
        }
    }

    public final String b() {
        return this.a;
    }
}
