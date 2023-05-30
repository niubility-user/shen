package com.cmic.sso.sdk.c.a;

import android.annotation.TargetApi;
import android.text.TextUtils;
import com.cmic.sso.sdk.c.b.e;
import com.cmic.sso.sdk.c.b.g;
import com.cmic.sso.sdk.c.b.h;
import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class a implements b {
    private static com.cmic.sso.sdk.c.c a;

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(2:2|3)|(13:(2:154|(28:156|8|(2:10|11)(2:152|153)|12|13|(3:15|(5:18|19|20|21|16)|81)|83|(1:(7:90|91|92|93|94|95|96)(1:97))|98|(1:100)|101|102|103|(3:105|106|107)(1:147)|108|110|111|112|113|114|(2:115|(1:117)(1:118))|119|120|(1:122)|123|(1:125)(1:136)|126|(2:134|135)(2:132|133)))|113|114|(3:115|(0)(0)|117)|119|120|(0)|123|(0)(0)|126|(1:128)|134|135)|7|8|(0)(0)|12|13|(0)|83|(2:85|(0)(0))|98|(0)|101|102|103|(0)(0)|108|110|111|112|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:1|2|3|(13:(2:154|(28:156|8|(2:10|11)(2:152|153)|12|13|(3:15|(5:18|19|20|21|16)|81)|83|(1:(7:90|91|92|93|94|95|96)(1:97))|98|(1:100)|101|102|103|(3:105|106|107)(1:147)|108|110|111|112|113|114|(2:115|(1:117)(1:118))|119|120|(1:122)|123|(1:125)(1:136)|126|(2:134|135)(2:132|133)))|113|114|(3:115|(0)(0)|117)|119|120|(0)|123|(0)(0)|126|(1:128)|134|135)|7|8|(0)(0)|12|13|(0)|83|(2:85|(0)(0))|98|(0)|101|102|103|(0)(0)|108|110|111|112|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01d9, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01da, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01dd, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01de, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01e8, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e9, code lost:
        r18 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ed, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ee, code lost:
        r18 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0236 A[Catch: all -> 0x02a4, TryCatch #2 {all -> 0x02a4, blocks: (B:108:0x0232, B:110:0x0236, B:112:0x023e, B:114:0x0246), top: B:151:0x0232 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0171 A[EDGE_INSN: B:166:0x0171->B:58:0x0171 BREAK  A[LOOP:1: B:55:0x015d->B:57:0x0163], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008b A[Catch: all -> 0x01e8, Exception -> 0x01ed, TryCatch #12 {Exception -> 0x01ed, all -> 0x01e8, blocks: (B:18:0x0085, B:20:0x008b, B:21:0x0093, B:23:0x0099), top: B:158:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1 A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fd A[Catch: all -> 0x01e4, Exception -> 0x01e6, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0129 A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013d A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0163 A[Catch: all -> 0x01d4, Exception -> 0x01d7, LOOP:1: B:55:0x015d->B:57:0x0163, LOOP_END, TryCatch #5 {Exception -> 0x01d7, blocks: (B:54:0x015b, B:55:0x015d, B:57:0x0163, B:58:0x0171), top: B:153:0x015b }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ac  */
    @Override // com.cmic.sso.sdk.c.a.b
    @TargetApi(21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(com.cmic.sso.sdk.c.c.c cVar, com.cmic.sso.sdk.c.d.c cVar2, com.cmic.sso.sdk.a aVar) {
        String str;
        Closeable closeable;
        HttpURLConnection httpURLConnection;
        Closeable closeable2;
        int i2;
        int i3;
        String a2;
        URL url;
        final String host;
        g j2;
        boolean z;
        Map<String, String> c2;
        String e2;
        byte[] bArr;
        int read;
        String str2 = "";
        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "\u8bf7\u6c42\u5730\u5740: " + cVar.a());
        StringBuilder sb = new StringBuilder();
        try {
            a2 = cVar.a();
            url = new URL(a2);
            host = url.getHost();
            j2 = cVar.j();
        } catch (Exception e3) {
            e = e3;
            str = "";
            closeable = null;
            httpURLConnection = null;
        } catch (Throwable th) {
            th = th;
            str = "";
            closeable = null;
            httpURLConnection = null;
        }
        try {
            try {
                if ((j2 instanceof h) || (j2 instanceof e)) {
                    String b = aVar.b("remote_ip");
                    if (!TextUtils.isEmpty(b)) {
                        url = new URL(a2.replaceFirst(host, b));
                        z = true;
                        if (cVar.g() == null) {
                            com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "\u5f00\u59cbwifi\u4e0b\u53d6\u53f7");
                            httpURLConnection = (HttpURLConnection) cVar.g().openConnection(url);
                        } else {
                            com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "\u4f7f\u7528\u5f53\u524d\u7f51\u7edc\u73af\u5883\u53d1\u9001\u8bf7\u6c42");
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                        }
                        c2 = cVar.c();
                        if (c2 != null) {
                            for (String str3 : c2.keySet()) {
                                str = str2;
                                try {
                                    httpURLConnection.addRequestProperty(str3, c2.get(str3));
                                    str2 = str;
                                } catch (Exception e4) {
                                    e = e4;
                                    closeable = null;
                                    closeable2 = null;
                                    i2 = -1;
                                    e.printStackTrace();
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("\u8bf7\u6c42\u5931\u8d25: ");
                                    sb2.append(cVar.a());
                                    com.cmic.sso.sdk.e.c.a("ConnectionInterceptor", sb2.toString());
                                    aVar.a().a.add(e);
                                    if (e instanceof EOFException) {
                                    }
                                    try {
                                        if (e instanceof UnknownHostException) {
                                        }
                                        a(closeable);
                                        a(closeable2);
                                        if (httpURLConnection != null) {
                                        }
                                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i3);
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append("responseResult: ");
                                        sb3.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb3.toString());
                                        if (i3 == 200) {
                                        }
                                        cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                                        return;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        i2 = i3;
                                        a(closeable);
                                        a(closeable2);
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                                        StringBuilder sb4 = new StringBuilder();
                                        sb4.append("responseResult: ");
                                        sb4.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb4.toString());
                                        if (i2 == 200 && i2 != 301 && i2 != 302) {
                                            cVar2.a(com.cmic.sso.sdk.c.d.a.a(i2));
                                        } else {
                                            cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    closeable = null;
                                    closeable2 = null;
                                    i2 = -1;
                                    a(closeable);
                                    a(closeable2);
                                    if (httpURLConnection != null) {
                                    }
                                    com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                                    StringBuilder sb42 = new StringBuilder();
                                    sb42.append("responseResult: ");
                                    sb42.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                                    com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb42.toString());
                                    if (i2 == 200) {
                                    }
                                    cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                                    throw th;
                                }
                            }
                        }
                        str = str2;
                        if ((httpURLConnection instanceof HttpsURLConnection) && ((j2 instanceof h) || (j2 instanceof e))) {
                            if (!z) {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("host = ");
                                sb5.append(host);
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb5.toString());
                                httpURLConnection.setRequestProperty(HttpHeaders.HOST, host);
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "need sni handle");
                                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new com.cmic.sso.sdk.c.d((HttpsURLConnection) httpURLConnection, cVar.g(), aVar));
                                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new HostnameVerifier() { // from class: com.cmic.sso.sdk.c.a.a.1
                                    @Override // javax.net.ssl.HostnameVerifier
                                    public boolean verify(String str4, SSLSession sSLSession) {
                                        return HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sSLSession);
                                    }
                                });
                            } else {
                                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(a(j2, aVar));
                            }
                        }
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setConnectTimeout(5000);
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setDefaultUseCaches(false);
                        e2 = cVar.e();
                        httpURLConnection.setRequestMethod(e2);
                        httpURLConnection.setDoOutput(true);
                        if (cVar instanceof com.cmic.sso.sdk.c.c.b) {
                            httpURLConnection.connect();
                            ((com.cmic.sso.sdk.c.c.b) cVar).a(aVar);
                        }
                        if (e2.endsWith("POST")) {
                            closeable = null;
                        } else {
                            closeable = httpURLConnection.getOutputStream();
                            try {
                                closeable.write(cVar.d().getBytes("utf-8"));
                                closeable.flush();
                            } catch (Exception e5) {
                                e = e5;
                                closeable2 = null;
                                i2 = -1;
                                e.printStackTrace();
                                StringBuilder sb22 = new StringBuilder();
                                sb22.append("\u8bf7\u6c42\u5931\u8d25: ");
                                sb22.append(cVar.a());
                                com.cmic.sso.sdk.e.c.a("ConnectionInterceptor", sb22.toString());
                                aVar.a().a.add(e);
                                if (e instanceof EOFException) {
                                }
                                if (e instanceof UnknownHostException) {
                                    aVar.a("remote_ip", a());
                                }
                                a(closeable);
                                a(closeable2);
                                if (httpURLConnection != null) {
                                }
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i3);
                                StringBuilder sb32 = new StringBuilder();
                                sb32.append("responseResult: ");
                                sb32.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb32.toString());
                                if (i3 == 200) {
                                }
                                cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                                return;
                            } catch (Throwable th4) {
                                th = th4;
                                closeable2 = null;
                                i2 = -1;
                                a(closeable);
                                a(closeable2);
                                if (httpURLConnection != null) {
                                }
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                                StringBuilder sb422 = new StringBuilder();
                                sb422.append("responseResult: ");
                                sb422.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb422.toString());
                                if (i2 == 200) {
                                }
                                cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                                throw th;
                            }
                        }
                        i2 = httpURLConnection.getResponseCode();
                        closeable2 = httpURLConnection.getInputStream();
                        bArr = new byte[2048];
                        while (true) {
                            read = closeable2.read(bArr);
                            if (read > 0) {
                                break;
                            }
                            sb.append(new String(bArr, 0, read, "utf-8"));
                        }
                        com.cmic.sso.sdk.c.d.b bVar = new com.cmic.sso.sdk.c.d.b(i2, httpURLConnection.getHeaderFields(), sb.toString());
                        a(closeable);
                        a(closeable2);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("responseResult: ");
                        sb6.append(!TextUtils.isEmpty(sb) ? str : sb.toString());
                        com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb6.toString());
                        if (i2 == 200 && i2 != 301 && i2 != 302) {
                            cVar2.a(com.cmic.sso.sdk.c.d.a.a(i2));
                            return;
                        } else {
                            cVar2.a(bVar);
                            return;
                        }
                    }
                }
                bArr = new byte[2048];
                while (true) {
                    read = closeable2.read(bArr);
                    if (read > 0) {
                    }
                    sb.append(new String(bArr, 0, read, "utf-8"));
                }
                com.cmic.sso.sdk.c.d.b bVar2 = new com.cmic.sso.sdk.c.d.b(i2, httpURLConnection.getHeaderFields(), sb.toString());
                a(closeable);
                a(closeable2);
                if (httpURLConnection != null) {
                }
                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                StringBuilder sb62 = new StringBuilder();
                sb62.append("responseResult: ");
                sb62.append(!TextUtils.isEmpty(sb) ? str : sb.toString());
                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb62.toString());
                if (i2 == 200) {
                }
                cVar2.a(bVar2);
                return;
            } catch (Throwable th5) {
                th = th5;
                a(closeable);
                a(closeable2);
                if (httpURLConnection != null) {
                }
                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i2);
                StringBuilder sb4222 = new StringBuilder();
                sb4222.append("responseResult: ");
                sb4222.append(TextUtils.isEmpty(sb) ? str : sb.toString());
                com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb4222.toString());
                if (i2 == 200) {
                }
                cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            e.printStackTrace();
            StringBuilder sb222 = new StringBuilder();
            sb222.append("\u8bf7\u6c42\u5931\u8d25: ");
            sb222.append(cVar.a());
            com.cmic.sso.sdk.e.c.a("ConnectionInterceptor", sb222.toString());
            aVar.a().a.add(e);
            i3 = e instanceof EOFException ? 200050 : 102102;
            if ((e instanceof UnknownHostException) && ((cVar.j() instanceof h) || (cVar.j() instanceof e))) {
                aVar.a("remote_ip", a());
            }
            a(closeable);
            a(closeable2);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", "responseCode: " + i3);
            StringBuilder sb322 = new StringBuilder();
            sb322.append("responseResult: ");
            sb322.append(TextUtils.isEmpty(sb) ? str : sb.toString());
            com.cmic.sso.sdk.e.c.b("ConnectionInterceptor", sb322.toString());
            if (i3 == 200 && i3 != 301 && i3 != 302) {
                cVar2.a(com.cmic.sso.sdk.c.d.a.a(i3));
                return;
            } else {
                cVar2.a((com.cmic.sso.sdk.c.d.b) null);
                return;
            }
        }
        z = false;
        if (cVar.g() == null) {
        }
        c2 = cVar.c();
        if (c2 != null) {
        }
        str = str2;
        if (httpURLConnection instanceof HttpsURLConnection) {
            if (!z) {
            }
        }
        httpURLConnection.setDoInput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setDefaultUseCaches(false);
        e2 = cVar.e();
        httpURLConnection.setRequestMethod(e2);
        httpURLConnection.setDoOutput(true);
        if (cVar instanceof com.cmic.sso.sdk.c.c.b) {
        }
        if (e2.endsWith("POST")) {
        }
        i2 = httpURLConnection.getResponseCode();
        closeable2 = httpURLConnection.getInputStream();
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private String a() {
        return com.cmic.sso.sdk.b.a[0] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[2] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[4] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[6];
    }

    public synchronized SSLSocketFactory a(g gVar, com.cmic.sso.sdk.a aVar) {
        if (gVar instanceof e) {
            com.cmic.sso.sdk.c.c cVar = new com.cmic.sso.sdk.c.c(HttpsURLConnection.getDefaultSSLSocketFactory(), aVar);
            if (a == null) {
                a = cVar;
            }
            return cVar;
        }
        if (a == null) {
            a = new com.cmic.sso.sdk.c.c(HttpsURLConnection.getDefaultSSLSocketFactory(), aVar);
        }
        return a;
    }
}
