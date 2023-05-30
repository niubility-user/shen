package com.jingdong.sdk.jdupgrade.a.j;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.jdupgrade.a.j.b;
import com.tencent.mapsdk.internal.l4;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class j {
    private static ExecutorService b;
    private static final OkHttpClient a = new OkHttpClient.Builder().retryOnConnectionFailure(false).build();

    /* renamed from: c  reason: collision with root package name */
    public static AtomicInteger f15101c = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class a implements RejectedExecutionHandler {
        a() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            h.b("NetworkHelper", "runnable rejected..." + runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class b implements Comparator<String> {
        b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class c implements Interceptor {
        final /* synthetic */ e a;
        final /* synthetic */ long b;

        c(e eVar, long j2) {
            this.a = eVar;
            this.b = j2;
        }

        @Override // okhttp3.Interceptor
        @NonNull
        public Response intercept(@NonNull Interceptor.Chain chain) {
            Response proceed = chain.proceed(chain.request());
            return proceed.newBuilder().body(new f(proceed.body(), this.a, this.b)).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class d implements b.a {
        final /* synthetic */ boolean a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f15102c;
        final /* synthetic */ e d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f15103e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f15104f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f15105g;

        /* loaded from: classes7.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h.a("NetworkHelper", "download: onAppSwitch2Foreground");
                d dVar = d.this;
                j.a(dVar.a, dVar.b, dVar.f15102c, dVar.d, dVar.f15103e, dVar.f15104f, dVar.f15105g);
            }
        }

        d(boolean z, String str, String str2, e eVar, boolean z2, String str3, boolean z3) {
            this.a = z;
            this.b = str;
            this.f15102c = str2;
            this.d = eVar;
            this.f15103e = z2;
            this.f15104f = str3;
            this.f15105g = z3;
        }

        @Override // com.jingdong.sdk.jdupgrade.a.j.b.a
        public void a() {
        }

        @Override // com.jingdong.sdk.jdupgrade.a.j.b.a
        public void b() {
            j.b.execute(new a());
            com.jingdong.sdk.jdupgrade.a.j.b.b(this);
        }
    }

    /* loaded from: classes7.dex */
    public interface e {
        void a(int i2);

        void a(Throwable th, String str);

        void onProgress(int i2, long j2, long j3);

        void onStart();

        void onSuccess(String str);
    }

    /* loaded from: classes7.dex */
    private static class f extends ResponseBody {

        /* renamed from: g  reason: collision with root package name */
        private final ResponseBody f15107g;

        /* renamed from: h  reason: collision with root package name */
        private final e f15108h;

        /* renamed from: i  reason: collision with root package name */
        private BufferedSource f15109i;

        /* renamed from: j  reason: collision with root package name */
        private long f15110j;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class a extends ForwardingSource {

            /* renamed from: g  reason: collision with root package name */
            long f15111g;

            /* renamed from: h  reason: collision with root package name */
            int f15112h;

            a(Source source) {
                super(source);
                this.f15111g = 0L;
                this.f15112h = -1;
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(@NonNull Buffer buffer, long j2) {
                long read = super.read(buffer, j2);
                j.f();
                long j3 = this.f15111g + (read != -1 ? read : 0L);
                this.f15111g = j3;
                double d = j3 + f.this.f15110j;
                double contentLength = f.this.f15107g.contentLength() + f.this.f15110j;
                if (contentLength >= d) {
                    Double.isNaN(d);
                    Double.isNaN(contentLength);
                    int i2 = (int) ((100.0d * d) / contentLength);
                    if (i2 >= 0 && this.f15112h != i2) {
                        this.f15112h = i2;
                        f.this.f15108h.onProgress(i2, (long) d, (long) contentLength);
                    }
                }
                return read;
            }
        }

        f(ResponseBody responseBody, e eVar, long j2) {
            this.f15107g = responseBody;
            this.f15108h = eVar;
            this.f15110j = j2;
        }

        private Source a(Source source) {
            return new a(source);
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.f15107g.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.f15107g.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            if (this.f15109i == null) {
                this.f15109i = Okio.buffer(a(this.f15107g.source()));
            }
            return this.f15109i;
        }
    }

    private static String a(String str, JSONObject jSONObject) {
        try {
            Map<String, String> a2 = a(str, d(), jSONObject);
            String str2 = com.jingdong.sdk.jdupgrade.a.c.R() ? "https://beta-api.m.jd.com" : "https://api.m.jd.com";
            h.a("NetworkHelper", "body:" + a2.toString());
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : a2.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    builder.add(key, value);
                }
            }
            String string = a(new Request.Builder().url(str2).post(builder.build()).build(), (Interceptor) null).body().string();
            h.a("NetworkHelper", "result:" + string);
            return string;
        } catch (Throwable th) {
            th.printStackTrace();
            if (th.getMessage() != null) {
                h.b("NetworkHelper", "requestConfig error:" + th.getMessage());
                return "";
            }
            return "";
        }
    }

    private static Map<String, String> a(String str, Map<String, String> map, JSONObject jSONObject) {
        TreeMap treeMap = new TreeMap(new b());
        treeMap.put("functionId", str);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    treeMap.put(key, value);
                }
            }
        }
        if (jSONObject != null) {
            try {
                treeMap.put("body", com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(jSONObject.toString().getBytes())));
            } catch (Throwable unused) {
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            sb.append((String) ((Map.Entry) it.next()).getValue());
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        if (sb.toString().endsWith(ContainerUtils.FIELD_DELIMITER)) {
            sb.setLength(sb.length() - 1);
        }
        treeMap.put("sign", com.jingdong.sdk.jdupgrade.a.j.c.a(sb.toString().getBytes(Charset.forName("UTF-8")), com.jingdong.sdk.jdupgrade.a.j.c.a("i9 _b\u0006N\u001d").getBytes(Charset.forName("UTF-8"))));
        return treeMap;
    }

    private static Response a(Request request, Interceptor interceptor) {
        OkHttpClient okHttpClient;
        h.a("NetworkHelper", "url:" + request.url());
        if (interceptor != null) {
            OkHttpClient.Builder addNetworkInterceptor = a.newBuilder().addNetworkInterceptor(interceptor);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            okHttpClient = addNetworkInterceptor.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(120L, timeUnit).retryOnConnectionFailure(true).build();
        } else {
            okHttpClient = a;
        }
        return okHttpClient.newCall(request).execute();
    }

    private static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PairKey.APP_KEY, com.jingdong.sdk.jdupgrade.a.c.b());
            String g2 = com.jingdong.sdk.jdupgrade.a.c.g();
            jSONObject.put("userId", g2);
            h.a("NetworkHelper", "param userId:" + g2);
            jSONObject.put("sign", g());
            jSONObject.put(l4.f16791e, "3.1.0");
            jSONObject.put("upgradeType", str);
            h.a("NetworkHelper", "param upgradeType:" + str);
        } catch (Throwable th) {
            h.b("NetworkHelper", th.getMessage());
        }
        return jSONObject;
    }

    public static void a(String str, String str2, e eVar, boolean z, String str3, boolean z2) {
        a(true, str, str2, eVar, z, str3, z2);
    }

    public static void a(boolean z, String str, String str2, e eVar, boolean z2, String str3, boolean z3) {
        h.a("NetworkHelper", "url:" + str + ", path:" + str2);
        try {
            k.b("LOCAL_APK_STORAGE_PATH", "");
            k.b("LOCAL_LOADING_APK_STORAGE_PATH", str2);
            File file = new File(str2);
            String a2 = com.jingdong.sdk.jdupgrade.a.j.d.a(file);
            if (z3) {
                h.a("", "delete apk " + file.delete() + ", reload");
            } else if (TextUtils.equals(a2, str3)) {
                h.a("", "Apk is downloaded already");
                if (z) {
                    k.b("LOCAL_APK_STORAGE_PATH", str2);
                }
                k.b("LOCAL_LOADING_APK_STORAGE_PATH", "");
                f15101c.set(0);
                if (eVar != null) {
                    eVar.onSuccess(str2);
                    return;
                }
                return;
            }
            if (!file.exists() && !file.isFile()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();
            f15101c.incrementAndGet();
            h.a("NetworkHelper", "retryImmediately:" + z2 + ", downloadIndex:" + f15101c.get() + ", downloadedSize:" + length + ", savedFile:" + file.getAbsolutePath());
            if (1 == f15101c.get()) {
                eVar.onStart();
            }
            eVar.a(f15101c.get());
            Response a3 = a(new Request.Builder().url(str).get().addHeader("mimeType", "application/zip").addHeader("range", "bytes=" + length + "-").build(), new c(eVar, length));
            if (a3 == null || !a3.isSuccessful()) {
                if (length > 0) {
                    String a4 = com.jingdong.sdk.jdupgrade.a.j.d.a(file);
                    if (TextUtils.equals(a4, str3)) {
                        if (z) {
                            k.b("LOCAL_APK_STORAGE_PATH", file.getAbsolutePath());
                        }
                        k.b("LOCAL_LOADING_APK_STORAGE_PATH", "");
                        eVar.onSuccess(file.getAbsolutePath());
                        f15101c.set(0);
                        return;
                    }
                    file.delete();
                    h.b("", "md5 check fail, apkMd5:" + str3 + ", downloadedFileMd5:" + a4);
                }
                throw new IOException("response is null");
            } else if (a3.body() == null) {
                throw new IOException("response.body is null");
            } else {
                ResponseBody body = a3.body();
                randomAccessFile.seek(file.length());
                InputStream byteStream = body.byteStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = byteStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    randomAccessFile.write(bArr, 0, read);
                }
                body.close();
                String absolutePath = file.getAbsolutePath();
                String a5 = com.jingdong.sdk.jdupgrade.a.j.d.a(file);
                if (TextUtils.equals(a5, str3)) {
                    if (z) {
                        k.b("LOCAL_APK_STORAGE_PATH", absolutePath);
                    }
                    k.b("LOCAL_LOADING_APK_STORAGE_PATH", "");
                    eVar.onSuccess(absolutePath);
                    f15101c.set(0);
                    return;
                }
                h.b("", "md5 check fail, apkMd5:" + str3 + ", downloadedFileMd5:" + a5);
                eVar.a(new RuntimeException("md5 check fail, apkMd5:" + str3 + ", downloadedFileMd5:" + a5), "7");
            }
        } catch (Throwable th) {
            h.b("NetworkHelper", "index:" + f15101c.get() + ", download error:" + th.getMessage());
            if (f15101c.get() >= com.jingdong.sdk.jdupgrade.a.c.u()) {
                eVar.a(th, "2");
            } else if (!com.jingdong.sdk.jdupgrade.a.j.b.b() && !z2) {
                com.jingdong.sdk.jdupgrade.a.j.b.a(new d(z, str, str2, eVar, z2, str3, z3));
            } else {
                try {
                    Thread.sleep(com.jingdong.sdk.jdupgrade.a.c.t() * 1000);
                } catch (Throwable unused) {
                }
                h.a("NetworkHelper", "download: isForeground after sleep");
                a(z, str, str2, eVar, z2, str3, z3);
            }
        }
    }

    public static String b(String str) {
        return a("openStats", a(str));
    }

    private static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PairKey.APP_KEY, com.jingdong.sdk.jdupgrade.a.c.b());
            String g2 = com.jingdong.sdk.jdupgrade.a.c.g();
            jSONObject.put("userId", g2);
            h.a("NetworkHelper", "param userId:" + g2);
            jSONObject.put("sign", g());
            jSONObject.put(l4.f16791e, "3.1.0");
            h.a("NetworkHelper", "param sdkVersion:3.1.0");
            String o = com.jingdong.sdk.jdupgrade.a.c.o();
            jSONObject.put("abis", o);
            h.a("NetworkHelper", "param abis:" + o);
            int B = com.jingdong.sdk.jdupgrade.a.c.B();
            jSONObject.put("apiLevel", B);
            h.a("NetworkHelper", "param aplLevel:" + B);
            int a2 = com.jingdong.sdk.jdupgrade.a.c.a();
            if (a2 > 0) {
                h.a("NetworkHelper", "param type:" + a2);
                jSONObject.put("type", a2);
            }
        } catch (Throwable th) {
            h.b("NetworkHelper", th.getMessage());
        }
        return jSONObject;
    }

    public static synchronized ExecutorService c() {
        ExecutorService executorService;
        synchronized (j.class) {
            if (b == null) {
                b = new ThreadPoolExecutor(0, 20, 120L, TimeUnit.SECONDS, new SynchronousQueue(), new a());
            }
            executorService = b;
        }
        return executorService;
    }

    private static Map<String, String> d() {
        HashMap hashMap = new HashMap();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String i2 = com.jingdong.sdk.jdupgrade.a.c.i();
            String h2 = com.jingdong.sdk.jdupgrade.a.c.h();
            String C = com.jingdong.sdk.jdupgrade.a.c.C();
            String d2 = com.jingdong.sdk.jdupgrade.a.c.d();
            String f2 = com.jingdong.sdk.jdupgrade.a.c.f();
            String A = com.jingdong.sdk.jdupgrade.a.c.A();
            String m2 = com.jingdong.sdk.jdupgrade.a.c.m();
            String n2 = com.jingdong.sdk.jdupgrade.a.c.n();
            hashMap.put(l4.f16791e, "E1.0");
            hashMap.put("appid", "app_upgrade");
            hashMap.put("t", String.valueOf(currentTimeMillis));
            h.c("NetworkHelper", "param time:" + ((Object) DateFormat.format("yyyy-MM-dd HH:mm:ss", currentTimeMillis)));
            hashMap.put("client", "android");
            hashMap.put(HybridSDK.APP_VERSION, i2);
            h.a("NetworkHelper", "param clientVersion:" + i2);
            hashMap.put(HybridSDK.APP_VERSION_CODE, com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(h2.getBytes())));
            h.a("NetworkHelper", "param build:" + h2);
            hashMap.put(HybridSDK.OS_VERSION, com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(C.getBytes())));
            h.a("NetworkHelper", "param osVersion:" + C);
            hashMap.put(Configuration.PARTNER, com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(d2.getBytes())));
            h.a("NetworkHelper", "param partner:" + d2);
            hashMap.put("uuid", com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(f2.getBytes())));
            h.a("NetworkHelper", "param uuid:" + f2);
            hashMap.put("networkType", com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(A.getBytes())));
            h.a("NetworkHelper", "param networkType:" + A);
            hashMap.put(HybridSDK.D_BRAND, com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(m2.getBytes())));
            h.a("NetworkHelper", "param d_brand:" + m2);
            hashMap.put(HybridSDK.D_MODEL, com.jingdong.sdk.jdupgrade.a.j.e.a(com.jingdong.sdk.jdupgrade.a.j.e.b(n2.getBytes())));
            h.a("NetworkHelper", "param d_model:" + n2);
        } catch (Throwable th) {
            h.b("NetworkHelper", th.getMessage());
        }
        return hashMap;
    }

    public static String e() {
        return a("openUpgrade", b());
    }

    public static void f() {
        if (f15101c.get() != 0) {
            h.a("NetworkHelper", "resetDownloadIndex");
            f15101c.set(0);
        }
    }

    public static String g() {
        return Base64.encodeToString(com.jingdong.sdk.jdupgrade.a.j.a.a((com.jingdong.sdk.jdupgrade.a.c.b() + com.jingdong.sdk.jdupgrade.a.c.f() + com.jingdong.sdk.jdupgrade.a.c.i() + com.jingdong.sdk.jdupgrade.a.c.h()).getBytes(), com.jingdong.sdk.jdupgrade.a.c.e().getBytes(), com.jingdong.sdk.jdupgrade.a.d.a), 2);
    }
}
