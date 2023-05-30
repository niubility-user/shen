package com.jd.fireeye.network;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.stat.network.ExceptionEnum;
import com.jdpay.net.http.HTTP;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    */
    private byte[] a(HttpURLConnection httpURLConnection) throws IOException {
        Throwable th;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException unused) {
                byteArrayOutputStream = null;
                if (inputStream != null) {
                }
                if (byteArrayOutputStream != null) {
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            inputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (inputStream != null) {
                inputStream.close();
            }
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException unused3) {
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th;
        }
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
