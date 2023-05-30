package com.jingdong.sdk.dialingtest.c.c;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
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
    */
    public void run() {
        b bVar;
        String str;
        String str2;
        HttpURLConnection httpURLConnection;
        String str3;
        BufferedInputStream bufferedInputStream;
        StringBuilder sb;
        long j2;
        byte[] bArr;
        String str4 = "Exception: ";
        String str5 = "-1";
        if (TextUtils.isEmpty(this.f14738g)) {
            return;
        }
        c cVar = new c();
        long uptimeMillis = SystemClock.uptimeMillis();
        BufferedInputStream bufferedInputStream2 = null;
        try {
        } catch (Exception e2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", e2.getMessage());
        } catch (Throwable th) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", th.getMessage());
        }
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(this.f14738g).openConnection();
            } catch (Exception e3) {
                e = e3;
                str2 = "-1";
                str3 = "Exception: ";
                httpURLConnection = null;
            } catch (Throwable th2) {
                th = th2;
                str = "Exception: ";
                str2 = "-1";
                httpURLConnection = null;
            }
            if (httpURLConnection == null) {
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                        return;
                    } catch (Exception e4) {
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", e4.getMessage());
                        return;
                    } catch (Throwable th3) {
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", th3.getMessage());
                        return;
                    }
                }
                return;
            }
            try {
                httpURLConnection.setUseCaches(this.f14743l);
                httpURLConnection.setDoOutput(this.f14745n);
                httpURLConnection.setDoInput(this.f14744m);
                httpURLConnection.setRequestMethod(this.f14741j);
                httpURLConnection.setConnectTimeout(this.f14739h);
                httpURLConnection.setReadTimeout(this.f14740i);
                httpURLConnection.setInstanceFollowRedirects(this.o);
                if (this.q && (httpURLConnection instanceof HttpsURLConnection)) {
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(a(cVar).getSocketFactory());
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(com.jingdong.sdk.dialingtest.c.c.b.a);
                }
                Map<String, String> map = this.f14742k;
                if (map != null && map.size() != 0) {
                    for (String str6 : this.f14742k.keySet()) {
                        httpURLConnection.setRequestProperty(str6, this.f14742k.get(str6));
                    }
                }
                cVar.f14737l = "";
                uptimeMillis = SystemClock.uptimeMillis();
                httpURLConnection.connect();
                if ("POST".equals(this.f14741j) && (bArr = this.p) != null && bArr.length != 0) {
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(this.p);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                cVar.b = httpURLConnection.getHeaderFields();
                cVar.f14735j = "" + (SystemClock.uptimeMillis() - uptimeMillis);
                com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", this.f14738g + " http rtt : " + cVar.f14735j + " ms");
                cVar.f14729c = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                cVar.a = httpURLConnection.getResponseCode();
                if (this.f14744m) {
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    BufferedInputStream bufferedInputStream3 = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        byte[] bArr2 = new byte[1024];
                        sb = new StringBuilder();
                        j2 = 0;
                        while (true) {
                            int read = bufferedInputStream3.read(bArr2);
                            bufferedInputStream = bufferedInputStream3;
                            if (read == -1) {
                                break;
                            }
                            try {
                                str = str4;
                            } catch (Exception e5) {
                                e = e5;
                                str = str4;
                            } catch (Throwable th4) {
                                th = th4;
                                str = str4;
                            }
                            try {
                                sb.append(new String(bArr2, 0, read));
                                j2 += read;
                                str5 = str5;
                                bufferedInputStream3 = bufferedInputStream;
                                str4 = str;
                            } catch (Exception e6) {
                                e = e6;
                                str2 = str5;
                                bufferedInputStream2 = bufferedInputStream;
                                str3 = str;
                                cVar.f14732g = str2;
                                cVar.f14731f = e.getClass().getName();
                                cVar.f14730e = e.getMessage();
                                cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                                com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str3 + e.toString());
                                if (bufferedInputStream2 != null) {
                                }
                                if (httpURLConnection != null) {
                                }
                                bVar = this.r;
                                if (bVar != null) {
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                str2 = str5;
                                bufferedInputStream2 = bufferedInputStream;
                                cVar.f14732g = str2;
                                cVar.f14731f = th.getClass().getName();
                                cVar.f14730e = th.getMessage();
                                cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                                com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str + th.getClass().getName());
                                if (bufferedInputStream2 != null) {
                                }
                                if (httpURLConnection != null) {
                                }
                                bVar = this.r;
                                if (bVar != null) {
                                }
                            }
                        }
                        str = str4;
                        str2 = str5;
                    } catch (Exception e7) {
                        e = e7;
                        str = str4;
                        bufferedInputStream = bufferedInputStream3;
                    } catch (Throwable th6) {
                        th = th6;
                        str = str4;
                        bufferedInputStream = bufferedInputStream3;
                    }
                    try {
                        long uptimeMillis3 = SystemClock.uptimeMillis() - uptimeMillis2;
                        if (j2 == 0) {
                            cVar.f14736k = "0";
                        } else if (uptimeMillis3 == 0) {
                            cVar.f14736k = "9223372036854775807";
                        } else {
                            cVar.f14736k = "" + (((j2 * 8) * 1000) / uptimeMillis3);
                        }
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", this.f14738g + ", throughput : " + cVar.f14736k);
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", this.f14738g + ", total cost time : " + cVar.f14734i);
                        cVar.d = new String(sb.toString().getBytes(), "utf-8");
                        bufferedInputStream2 = bufferedInputStream;
                    } catch (Exception e8) {
                        e = e8;
                        bufferedInputStream2 = bufferedInputStream;
                        str3 = str;
                        cVar.f14732g = str2;
                        cVar.f14731f = e.getClass().getName();
                        cVar.f14730e = e.getMessage();
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str3 + e.toString());
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception e9) {
                                com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", e9.getMessage());
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        bVar = this.r;
                        if (bVar != null) {
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        bufferedInputStream2 = bufferedInputStream;
                        cVar.f14732g = str2;
                        cVar.f14731f = th.getClass().getName();
                        cVar.f14730e = th.getMessage();
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str + th.getClass().getName());
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception e10) {
                                com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", e10.getMessage());
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        bVar = this.r;
                        if (bVar != null) {
                        }
                    }
                } else {
                    str = "Exception: ";
                    str2 = "-1";
                    try {
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", this.f14738g + ", total cost time : " + cVar.f14734i);
                    } catch (Exception e11) {
                        e = e11;
                        str3 = str;
                        cVar.f14732g = str2;
                        cVar.f14731f = e.getClass().getName();
                        cVar.f14730e = e.getMessage();
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str3 + e.toString());
                        if (bufferedInputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        bVar = this.r;
                        if (bVar != null) {
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        cVar.f14732g = str2;
                        cVar.f14731f = th.getClass().getName();
                        cVar.f14730e = th.getMessage();
                        cVar.f14734i = String.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", str + th.getClass().getName());
                        if (bufferedInputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        bVar = this.r;
                        if (bVar != null) {
                        }
                    }
                }
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e12) {
                        com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", e12.getMessage());
                    }
                }
            } catch (Exception e13) {
                e = e13;
                str2 = "-1";
                str3 = "Exception: ";
            } catch (Throwable th9) {
                th = th9;
                str = "Exception: ";
                str2 = "-1";
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            bVar = this.r;
            if (bVar != null) {
                bVar.a(cVar);
            }
        } catch (Throwable th10) {
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (Exception e14) {
                    com.jingdong.sdk.dialingtest.c.e.a.c("DtHttpSetting", e14.getMessage());
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e15) {
                    com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", e15.getMessage());
                } catch (Throwable th11) {
                    com.jingdong.sdk.dialingtest.c.e.a.a("DtHttpSetting", th11.getMessage());
                }
            }
            throw th10;
        }
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
