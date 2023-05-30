package com.jd.fireeye.network;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.stat.network.ExceptionEnum;
import com.jdpay.net.http.HTTP;
import java.io.DataOutputStream;
import java.io.IOException;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;

/* loaded from: classes13.dex */
public class d implements Runnable {

    /* renamed from: j  reason: collision with root package name */
    private static Map<Object, WeakReference<d>> f2618j = Collections.synchronizedMap(new WeakHashMap());
    private int a;

    /* renamed from: c  reason: collision with root package name */
    private int f2619c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2620e;

    /* renamed from: h  reason: collision with root package name */
    private f f2623h;

    /* renamed from: i  reason: collision with root package name */
    private Object f2624i;
    private int b = 1;

    /* renamed from: f  reason: collision with root package name */
    private final AtomicBoolean f2621f = new AtomicBoolean();

    /* renamed from: g  reason: collision with root package name */
    private int f2622g = 15000;

    public d(String str) {
        this.d = str;
    }

    private void b(HttpURLConnection httpURLConnection, String str) throws IOException {
        int e2 = e();
        if (e2 == 0) {
            httpURLConnection.setRequestMethod("POST");
            a(httpURLConnection, str);
        } else if (e2 != 1) {
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
    }

    private void c() throws InterruptedException {
        try {
            if (i()) {
                return;
            }
            j();
        } catch (NetworkException e2) {
            e2.printStackTrace();
            if (!i()) {
                a(e2);
            }
        } finally {
            k();
        }
    }

    private void j() throws NetworkException {
        NetworkException networkException;
        if (b.a()) {
            NetworkException networkException2 = null;
            HashMap hashMap = new HashMap();
            Map<String, String> b = b();
            if (b != null) {
                hashMap.putAll(b);
            }
            String a = a();
            boolean z = true;
            while (z) {
                try {
                    this.a++;
                    if (b.a) {
                        a.a("JDMob.Network.Request", "Request.tag = " + this.f2624i + ", url = " + this.d + " executionCount = " + this.a);
                    }
                    e a2 = a(b, a);
                    if (b.a && a2 != null) {
                        a.a("JDMob.Network.Request", "Response.tag = " + this.f2624i + ", response.responseCode = " + a2.c());
                    }
                    if (a2.d()) {
                        if (i()) {
                            return;
                        }
                        a(a2);
                        return;
                    }
                    throw new NetworkException(a2.c());
                } catch (NetworkException e2) {
                    z = g.a(e2, this.a, this);
                    int a3 = e2.a();
                    if (a3 == 0) {
                        a3 = -1003;
                    }
                    networkException2 = new NetworkException(e2, a3);
                } catch (SocketTimeoutException e3) {
                    if (b.a) {
                        e3.printStackTrace();
                    }
                    z = g.a(e3, this.a, this);
                    networkException = new NetworkException(e3, -1001);
                    networkException2 = networkException;
                } catch (SSLException e4) {
                    if (b.a) {
                        e4.printStackTrace();
                    }
                    z = g.a(e4, this.a, this);
                    networkException = new NetworkException(e4, ExceptionEnum.SSLEXCEPTION);
                    networkException2 = networkException;
                } catch (Exception e5) {
                    if (i()) {
                        return;
                    }
                    z = g.a(e5, this.a, this);
                    if (z && this.f2620e && !TextUtils.isEmpty(this.d) && this.d.startsWith("https://")) {
                        this.d = this.d.replaceFirst("https://", "http://");
                        if (b.a) {
                            a.a("JDMob.Network.Request", "Request.tag = " + f() + ", after replace, url  = " + this.d);
                        }
                    }
                    networkException = new NetworkException(e5, -1002);
                    if (b.a) {
                        e5.printStackTrace();
                    }
                    networkException2 = networkException;
                }
            }
            throw networkException2;
        }
    }

    private void k() {
        Object obj = this.f2624i;
        if (obj == null) {
            return;
        }
        try {
            f2618j.remove(obj);
        } catch (Exception unused) {
        }
    }

    protected String a() {
        throw null;
    }

    public void a(f fVar) {
        this.f2623h = fVar;
    }

    protected Map<String, String> b() {
        return null;
    }

    public int d() {
        int i2 = this.b;
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public int e() {
        return this.f2619c;
    }

    public Object f() {
        return this.f2624i;
    }

    public int g() {
        return this.f2622g;
    }

    protected String h() {
        return this.d;
    }

    public boolean i() {
        return this.f2621f.get();
    }

    public void l() {
        if (this.f2624i == null) {
            this.f2624i = Long.valueOf(System.currentTimeMillis());
        }
        Object obj = this.f2624i;
        if (obj != null) {
            f2618j.put(obj, new WeakReference<>(this));
        }
        c.a().a(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            c();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public void a(Object obj) {
        this.f2624i = obj;
    }

    public void a(int i2) {
        this.f2622g = i2;
    }

    protected void a(e eVar) {
        f fVar = this.f2623h;
        if (fVar != null) {
            fVar.a(eVar);
        }
    }

    private HttpURLConnection b(URL url) throws IOException {
        HttpURLConnection a = a(url);
        int g2 = g();
        a.setConnectTimeout(g2);
        a.setReadTimeout(g2);
        a.setDoInput(true);
        a.setDoOutput(true);
        return a;
    }

    protected void a(NetworkException networkException) {
        f fVar = this.f2623h;
        if (fVar != null) {
            fVar.a(networkException);
        }
    }

    private e a(Map<String, String> map, String str) throws Exception {
        if (!i()) {
            HttpURLConnection b = b(new URL(h()));
            if (map != null && !map.isEmpty()) {
                for (String str2 : map.keySet()) {
                    b.addRequestProperty(str2, map.get(str2));
                }
            }
            b.setRequestProperty("Charset", "UTF-8");
            b.setRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
            b(b, str);
            if (!i()) {
                int responseCode = b.getResponseCode();
                if (responseCode != -1) {
                    e eVar = new e();
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, List<String>> entry : b.getHeaderFields().entrySet()) {
                        if (entry.getKey() != null) {
                            hashMap.put(entry.getKey(), entry.getValue().get(0));
                        }
                    }
                    eVar.a(responseCode);
                    eVar.b(hashMap);
                    eVar.a(a(b));
                    if (i()) {
                        throw new NetworkException(ExceptionEnum.CANCELLED);
                    }
                    return eVar;
                }
                throw new NetworkException(responseCode);
            }
            throw new NetworkException(ExceptionEnum.CANCELLED);
        }
        throw new NetworkException(ExceptionEnum.CANCELLED);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(java.net.HttpURLConnection r7) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: com.jd.fireeye.network.d.a(java.net.HttpURLConnection):byte[]");
    }

    private void a(HttpURLConnection httpURLConnection, String str) throws IOException {
        if (httpURLConnection != null) {
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            if (str != null) {
                dataOutputStream.write(str.getBytes());
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    protected HttpURLConnection a(URL url) throws IOException {
        if ("https".equals(url.getProtocol().toLowerCase())) {
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }
}
