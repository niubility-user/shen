package com.jingdong.manto.p.e;

import android.net.Uri;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.p.e.c;
import com.jingdong.manto.utils.m;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class b implements Runnable {
    private String b;

    /* renamed from: c */
    public String f13918c;

    /* renamed from: e */
    private String f13919e;

    /* renamed from: f */
    public c.b f13920f;

    /* renamed from: g */
    public Map<String, String> f13921g;

    /* renamed from: h */
    private Call f13922h;

    /* renamed from: i */
    public String f13923i;

    /* renamed from: j */
    public String f13924j;

    /* renamed from: k */
    private Request f13925k;
    private volatile int a = -1;
    public int d = 60000;

    /* loaded from: classes16.dex */
    class a implements Interceptor {
        a() {
            b.this = r1;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Response proceed = chain.proceed(chain.request());
            return proceed.newBuilder().body(new C0649b(proceed.body(), b.this.f13920f)).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.manto.p.e.b$b */
    /* loaded from: classes16.dex */
    public class C0649b extends ResponseBody {
        private final ResponseBody a;
        private final c.b b;

        /* renamed from: c */
        private BufferedSource f13926c;

        /* renamed from: com.jingdong.manto.p.e.b$b$a */
        /* loaded from: classes16.dex */
        public class a extends ForwardingSource {
            long a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Source source) {
                super(source);
                C0649b.this = r1;
                this.a = 0L;
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j2) {
                long read = super.read(buffer, j2);
                long j3 = this.a + (read != -1 ? read : 0L);
                this.a = j3;
                double d = j3;
                Double.isNaN(d);
                double contentLength = C0649b.this.a.contentLength();
                Double.isNaN(contentLength);
                int i2 = (int) ((d * 100.0d) / contentLength);
                if (b.this.a == i2) {
                    return read;
                }
                b.this.a = i2;
                C0649b.this.b.a(i2, this.a, C0649b.this.a.contentLength());
                return read;
            }
        }

        C0649b(ResponseBody responseBody, c.b bVar) {
            b.this = r1;
            this.a = responseBody;
            this.b = bVar;
        }

        private Source a(Source source) {
            return new a(source);
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.a.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.a.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            if (this.f13926c == null) {
                this.f13926c = Okio.buffer(a(this.a.source()));
            }
            return this.f13926c;
        }
    }

    public b(String str, String str2, String str3, String str4, c.b bVar) {
        this.b = str;
        this.f13924j = str2;
        this.f13918c = str3;
        this.f13920f = bVar;
        System.currentTimeMillis();
        this.f13919e = str4;
    }

    private static String a(Response response) {
        String header = response.header(HttpHeaders.CONTENT_DISPOSITION);
        if (!TextUtils.isEmpty(header)) {
            header.replace("attachment;filename=", "");
            header.replace("filename*=utf-8", "");
            String[] split = header.split("; ");
            if (split.length > 1) {
                String replace = split[1].replace("filename=", "").replace("\"", "");
                return replace.substring(replace.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1);
            }
        }
        return "";
    }

    public final void a() {
        try {
            Call call = this.f13922h;
            if (call != null) {
                call.cancel();
            }
        } catch (Exception unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f13920f.a(this.f13918c, this.f13924j);
        a aVar = new a();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient build = new OkHttpClient.Builder().readTimeout(this.d, timeUnit).connectTimeout(this.d, timeUnit).addNetworkInterceptor(aVar).build();
        if (TextUtils.isEmpty(this.f13919e)) {
            this.f13919e = "";
        }
        Request.Builder addHeader = new Request.Builder().get().url(this.f13924j).addHeader("User-Agent", this.f13919e).addHeader("mimeType", "application/octet-stream");
        com.jingdong.manto.p.c.a(addHeader, this.f13921g);
        Request build2 = addHeader.build();
        this.f13925k = build2;
        Call newCall = build.newCall(build2);
        this.f13922h = newCall;
        try {
            Response execute = newCall.execute();
            if (!execute.isSuccessful()) {
                this.f13920f.a(this.f13918c, this.f13924j, execute.body().string());
                return;
            }
            JSONObject jSONObject = new JSONObject();
            if (execute.networkResponse() != null && execute.networkResponse().request() != null) {
                Headers headers = TextUtils.equals(m.a("response", "1"), "1") ? execute.networkResponse().headers() : execute.networkResponse().request().headers();
                if (headers != null) {
                    for (int i2 = 0; i2 < headers.size(); i2++) {
                        String name = headers.name(i2);
                        try {
                            jSONObject.put(name, headers.get(name));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            this.f13920f.a(jSONObject);
            String a2 = a(execute);
            if (TextUtils.isEmpty(a2)) {
                int lastIndexOf = this.f13924j.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
                a2 = lastIndexOf > -1 ? this.f13924j.substring(lastIndexOf + 1) : "unknown";
            }
            try {
                a2 = Uri.parse(a2).getPath();
                if (a2.length() > 15) {
                    a2 = a2.substring(14);
                }
            } catch (Exception unused) {
            }
            String str = a2;
            this.f13918c += OrderISVUtil.MONEY_DECIMAL + str;
            BufferedSource source = execute.body().source();
            File file = new File(this.f13918c);
            file.delete();
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedSink buffer = Okio.buffer(Okio.sink(file));
            source.readAll(buffer);
            buffer.flush();
            source.close();
            this.f13920f.a(this.f13918c, str, this.f13924j, execute.code(), jSONObject);
            com.jingdong.manto.p.e.a.b().a(this.b, this);
        } catch (Throwable th) {
            new File(this.f13918c).delete();
            this.f13920f.a(this.f13918c, this.f13924j, th.getMessage());
            com.jingdong.manto.p.e.a.b().a(this.b, this);
            th.printStackTrace();
        }
    }
}
