package com.jd.stat.network;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;

/* loaded from: classes18.dex */
public class d implements Runnable {
    private static Map<Object, WeakReference<d>> a = Collections.synchronizedMap(new WeakHashMap());
    private Future b;

    /* renamed from: c  reason: collision with root package name */
    private int f7029c;

    /* renamed from: e  reason: collision with root package name */
    private int f7030e;

    /* renamed from: f  reason: collision with root package name */
    private String f7031f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f7032g;

    /* renamed from: j  reason: collision with root package name */
    private f f7035j;

    /* renamed from: k  reason: collision with root package name */
    private Object f7036k;
    private int d = 1;

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f7033h = new AtomicBoolean();

    /* renamed from: i  reason: collision with root package name */
    private int f7034i = 15000;

    /* renamed from: l  reason: collision with root package name */
    private boolean f7037l = false;

    /* renamed from: m  reason: collision with root package name */
    private boolean f7038m = false;

    /* renamed from: n  reason: collision with root package name */
    private HashMap<String, String> f7039n = new HashMap<>();

    public d(String str) {
        this.f7031f = str;
    }

    private String j() {
        HashMap<String, String> hashMap = this.f7039n;
        if (hashMap == null || hashMap.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.f7039n.entrySet()) {
            sb.append(entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(entry.getValue());
            sb.append("; ");
        }
        if (this.f7039n.size() == 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private void k() throws InterruptedException {
        try {
            if (c()) {
                return;
            }
            try {
                m();
            } catch (NetworkException e2) {
                e2.printStackTrace();
                if (!c()) {
                    a(e2);
                }
            }
        } finally {
            l();
        }
    }

    private void l() {
        Object obj = this.f7036k;
        if (obj == null) {
            return;
        }
        try {
            a.remove(obj);
        } catch (Exception unused) {
        }
    }

    private void m() throws NetworkException {
        NetworkException networkException;
        if (!b.a()) {
            if (this.f7038m) {
                throw new NetworkException(-1004);
            }
        } else if (!BaseInfo.isAppForeground()) {
            com.jd.stat.common.b.b.a("JDMob.Network.Request", "background request forbid!!!");
            if (this.f7038m) {
                throw new NetworkException(-1002);
            }
        } else {
            NetworkException networkException2 = null;
            HashMap hashMap = new HashMap();
            Map<String, String> f2 = f();
            if (f2 != null) {
                hashMap.putAll(f2);
            }
            String e2 = e();
            boolean z = true;
            while (z) {
                try {
                    this.f7029c++;
                    if (b.a) {
                        a.a("JDMob.Network.Request", "Request.tag = " + this.f7036k + ", url = " + this.f7031f + " executionCount = " + this.f7029c);
                    }
                    e a2 = a(f2, e2);
                    if (b.a && a2 != null) {
                        a.a("JDMob.Network.Request", "Response.tag = " + this.f7036k + ", response.responseCode = " + a2.b());
                    }
                    if (a2.e()) {
                        if (c()) {
                            return;
                        }
                        a(a2);
                        return;
                    }
                    throw new NetworkException(a2.b());
                } catch (NetworkException e3) {
                    z = g.a(e3, this.f7029c, this);
                    int errorCode = e3.getErrorCode();
                    if (errorCode == 0) {
                        errorCode = -1003;
                    }
                    networkException2 = new NetworkException(e3, errorCode);
                } catch (SocketTimeoutException e4) {
                    if (b.a) {
                        e4.printStackTrace();
                    }
                    z = g.a(e4, this.f7029c, this);
                    networkException = new NetworkException(e4, -1001);
                    networkException2 = networkException;
                } catch (SSLException e5) {
                    if (b.a) {
                        e5.printStackTrace();
                    }
                    z = g.a(e5, this.f7029c, this);
                    networkException = new NetworkException(e5, (int) ExceptionEnum.SSLEXCEPTION);
                    networkException2 = networkException;
                } catch (Exception e6) {
                    if (c()) {
                        return;
                    }
                    z = g.a(e6, this.f7029c, this);
                    if (z && this.f7032g && !TextUtils.isEmpty(this.f7031f) && this.f7031f.startsWith("https://")) {
                        this.f7031f = this.f7031f.replaceFirst("https://", "http://");
                        if (b.a) {
                            a.a("JDMob.Network.Request", "Request.tag = " + a() + ", after replace, url  = " + this.f7031f);
                        }
                    }
                    networkException = new NetworkException(e6, -1002);
                    if (b.a) {
                        e6.printStackTrace();
                    }
                    networkException2 = networkException;
                }
            }
            throw networkException2;
        }
    }

    public d a(String str, String str2) {
        this.f7039n.put(a(str), a(str2));
        return this;
    }

    public int b() {
        int i2 = this.d;
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public boolean c() {
        return this.f7033h.get();
    }

    public int d() {
        return this.f7034i;
    }

    protected String e() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, String> f() {
        return null;
    }

    protected String g() {
        return this.f7031f;
    }

    public void h() {
        if (this.f7036k == null) {
            this.f7036k = Long.valueOf(System.currentTimeMillis());
        }
        Object obj = this.f7036k;
        if (obj != null) {
            a.put(obj, new WeakReference<>(this));
        }
        this.b = c.a().a(this);
    }

    public int i() {
        return this.f7030e;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            k();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public void a(f fVar) {
        this.f7035j = fVar;
    }

    public void b(boolean z) {
        this.f7038m = z;
    }

    private void b(HttpURLConnection httpURLConnection, String str) throws IOException {
        OutputStream dataOutputStream;
        if (httpURLConnection != null) {
            if (this.f7037l) {
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

    public void a(Object obj) {
        this.f7036k = obj;
    }

    public void a(boolean z) {
        this.f7037l = z;
    }

    public Object a() {
        return this.f7036k;
    }

    public void a(int i2) {
        this.f7034i = i2;
    }

    protected void a(e eVar) {
        f fVar = this.f7035j;
        if (fVar != null) {
            fVar.a(eVar);
        }
    }

    private HttpURLConnection b(URL url) throws IOException {
        HttpURLConnection a2 = a(url);
        int d = d();
        a2.setConnectTimeout(d);
        a2.setReadTimeout(d);
        a2.setDoInput(true);
        a2.setDoOutput(true);
        return a2;
    }

    protected void a(NetworkException networkException) {
        f fVar = this.f7035j;
        if (fVar != null) {
            fVar.a(networkException);
        }
    }

    private String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }

    private e a(Map<String, String> map, String str) throws Exception {
        if (!c()) {
            HttpURLConnection b = b(new URL(g()));
            if (map != null && !map.isEmpty()) {
                for (String str2 : map.keySet()) {
                    b.addRequestProperty(str2, map.get(str2));
                }
            }
            b.setRequestProperty("Charset", "UTF-8");
            String j2 = j();
            if (!TextUtils.isEmpty(j2)) {
                b.setRequestProperty("Cookie", j2);
            }
            a(b, str);
            if (!c()) {
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
                    eVar.a(hashMap);
                    eVar.a(a(b));
                    if (c()) {
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

    private void a(HttpURLConnection httpURLConnection, String str) throws IOException {
        int i2 = i();
        if (i2 == 0) {
            httpURLConnection.setRequestMethod("POST");
            b(httpURLConnection, str);
        } else if (i2 != 1) {
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
    }

    private byte[] a(HttpURLConnection httpURLConnection) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException unused) {
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                byteArrayOutputStream = null;
                th = th2;
            }
        } catch (IOException unused2) {
            inputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
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
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    protected HttpURLConnection a(URL url) throws IOException {
        if ("https".equals(url.getProtocol().toLowerCase())) {
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }
}
