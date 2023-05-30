package com.jd.jdsec.b;

import android.text.TextUtils;
import com.jd.stat.network.ExceptionEnum;
import com.jdpay.net.http.HTTP;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;

/* loaded from: classes13.dex */
public class e implements Runnable {
    private static Map<Object, WeakReference<e>> q = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: g  reason: collision with root package name */
    private int f2727g;

    /* renamed from: i  reason: collision with root package name */
    private int f2729i;

    /* renamed from: j  reason: collision with root package name */
    private String f2730j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f2731k;

    /* renamed from: n  reason: collision with root package name */
    private g f2734n;
    private Object o;

    /* renamed from: h  reason: collision with root package name */
    private int f2728h = 1;

    /* renamed from: l  reason: collision with root package name */
    private final AtomicBoolean f2732l = new AtomicBoolean();

    /* renamed from: m  reason: collision with root package name */
    private int f2733m = 15000;
    private boolean p = false;

    public e(String str) {
        this.f2730j = str;
    }

    private f a(Map<String, String> map, String str) throws Exception {
        boolean z;
        if (!t()) {
            HttpURLConnection k2 = k(new URL(s()));
            if (map == null || map.isEmpty()) {
                z = false;
            } else {
                z = false;
                for (String str2 : map.keySet()) {
                    k2.addRequestProperty(str2, map.get(str2));
                    if (str2.toLowerCase().equals("content-type") && map.get(str2).equals(HTTP.CONTENT_TYPE_JSON)) {
                        z = true;
                    }
                }
            }
            k2.setRequestProperty("Charset", "UTF-8");
            m(k2, str, z);
            if (!t()) {
                int responseCode = k2.getResponseCode();
                if (responseCode != -1) {
                    f fVar = new f();
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, List<String>> entry : k2.getHeaderFields().entrySet()) {
                        if (entry.getKey() != null) {
                            hashMap.put(entry.getKey(), entry.getValue().get(0));
                        }
                    }
                    fVar.d(responseCode);
                    fVar.g(hashMap);
                    fVar.e(j(k2));
                    if (t()) {
                        throw new a(ExceptionEnum.CANCELLED);
                    }
                    return fVar;
                }
                throw new a(responseCode);
            }
            throw new a(ExceptionEnum.CANCELLED);
        }
        throw new a(ExceptionEnum.CANCELLED);
    }

    private void h(HttpURLConnection httpURLConnection, String str, boolean z) throws IOException {
        OutputStream dataOutputStream;
        if (httpURLConnection != null) {
            if (this.p && !z) {
                dataOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
            } else {
                dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            }
            if (str != null) {
                dataOutputStream.write(str.getBytes());
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] j(java.net.HttpURLConnection r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L47
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L48
            r1.<init>()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L48
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
        Le:
            int r3 = r7.read(r2)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            r4 = -1
            if (r3 == r4) goto L1a
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            goto Le
        L1a:
            r1.flush()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            r1.close()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            r7.close()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L35
            if (r7 == 0) goto L2c
            r7.close()
        L2c:
            r1.close()
            return r0
        L30:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L3c
        L35:
            goto L49
        L37:
            r1 = move-exception
            goto L3c
        L39:
            r7 = move-exception
            r1 = r7
            r7 = r0
        L3c:
            if (r7 == 0) goto L41
            r7.close()
        L41:
            if (r0 == 0) goto L46
            r0.close()
        L46:
            throw r1
        L47:
            r7 = r0
        L48:
            r1 = r0
        L49:
            if (r7 == 0) goto L4e
            r7.close()
        L4e:
            if (r1 == 0) goto L53
            r1.close()
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.b.e.j(java.net.HttpURLConnection):byte[]");
    }

    private HttpURLConnection k(URL url) throws IOException {
        HttpURLConnection c2 = c(url);
        int r = r();
        c2.setConnectTimeout(r);
        c2.setReadTimeout(r);
        c2.setDoInput(true);
        c2.setDoOutput(true);
        return c2;
    }

    private void m(HttpURLConnection httpURLConnection, String str, boolean z) throws IOException {
        int p = p();
        if (p == 0) {
            httpURLConnection.setRequestMethod("POST");
            h(httpURLConnection, str, z);
        } else if (p != 1) {
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
    }

    private void n() throws InterruptedException {
        try {
            if (t()) {
                return;
            }
            u();
        } catch (a e2) {
            e2.printStackTrace();
            if (!t()) {
                d(e2);
            }
        } finally {
            v();
        }
    }

    private void u() throws a {
        a aVar;
        if (c.b()) {
            a aVar2 = null;
            HashMap hashMap = new HashMap();
            Map<String, String> l2 = l();
            if (l2 != null) {
                hashMap.putAll(l2);
            }
            String b = b();
            boolean z = true;
            while (z) {
                try {
                    this.f2727g++;
                    if (c.a) {
                        b.a("JDMob.Network.Request", "Request.tag = " + this.o + ", url = " + this.f2730j + " executionCount = " + this.f2727g);
                    }
                    f a = a(l2, b);
                    if (c.a && a != null) {
                        b.a("JDMob.Network.Request", "Response.tag = " + this.o + ", response.responseCode = " + a.f());
                    }
                    if (a.h()) {
                        if (t()) {
                            return;
                        }
                        e(a);
                        return;
                    }
                    throw new a(a.f());
                } catch (a e2) {
                    z = h.a(e2, this.f2727g, this);
                    int errorCode = e2.getErrorCode();
                    if (errorCode == 0) {
                        errorCode = -1003;
                    }
                    aVar2 = new a(e2, errorCode);
                } catch (SocketTimeoutException e3) {
                    if (c.a) {
                        e3.printStackTrace();
                    }
                    z = h.a(e3, this.f2727g, this);
                    aVar = new a(e3, -1001);
                    aVar2 = aVar;
                } catch (SSLException e4) {
                    if (c.a) {
                        e4.printStackTrace();
                    }
                    z = h.a(e4, this.f2727g, this);
                    aVar = new a(e4, (int) ExceptionEnum.SSLEXCEPTION);
                    aVar2 = aVar;
                } catch (Exception e5) {
                    if (t()) {
                        return;
                    }
                    z = h.a(e5, this.f2727g, this);
                    if (z && this.f2731k && !TextUtils.isEmpty(this.f2730j) && this.f2730j.startsWith("https://")) {
                        this.f2730j = this.f2730j.replaceFirst("https://", "http://");
                        if (c.a) {
                            b.a("JDMob.Network.Request", "Request.tag = " + q() + ", after replace, url  = " + this.f2730j);
                        }
                    }
                    aVar = new a(e5, -1002);
                    if (c.a) {
                        e5.printStackTrace();
                    }
                    aVar2 = aVar;
                }
            }
            throw aVar2;
        }
    }

    private void v() {
        Object obj = this.o;
        if (obj == null) {
            return;
        }
        try {
            q.remove(obj);
        } catch (Exception unused) {
        }
    }

    protected String b() {
        throw null;
    }

    protected HttpURLConnection c(URL url) throws IOException {
        if ("https".equals(url.getProtocol().toLowerCase())) {
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }

    protected void d(a aVar) {
        g gVar = this.f2734n;
        if (gVar != null) {
            gVar.a(aVar);
        }
    }

    protected void e(f fVar) {
        g gVar = this.f2734n;
        if (gVar != null) {
            gVar.b(fVar);
        }
    }

    public void f(g gVar) {
        this.f2734n = gVar;
    }

    public void g(Object obj) {
        this.o = obj;
    }

    public void i(boolean z) {
        this.p = z;
    }

    protected Map<String, String> l() {
        return null;
    }

    public int o() {
        int i2 = this.f2728h;
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public int p() {
        return this.f2729i;
    }

    public Object q() {
        return this.o;
    }

    public int r() {
        return this.f2733m;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            n();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    protected String s() {
        return this.f2730j;
    }

    public boolean t() {
        return this.f2732l.get();
    }

    public void w() {
        if (this.o == null) {
            this.o = Long.valueOf(System.currentTimeMillis());
        }
        Object obj = this.o;
        if (obj != null) {
            q.put(obj, new WeakReference<>(this));
        }
        d.a().b(this);
    }
}
