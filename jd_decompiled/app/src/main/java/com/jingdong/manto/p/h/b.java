package com.jingdong.manto.p.h;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.p.h.c;
import com.jingdong.manto.utils.m;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class b implements Runnable {
    public volatile boolean a;
    public int b;

    /* renamed from: c */
    public Map<String, String> f13952c;
    public Call d = null;

    /* renamed from: e */
    public String f13953e;

    /* renamed from: f */
    public String f13954f;

    /* renamed from: g */
    public Map<String, String> f13955g;

    /* renamed from: h */
    public c.a f13956h;

    /* renamed from: i */
    public String f13957i;

    /* renamed from: j */
    public String f13958j;

    /* renamed from: k */
    public String f13959k;

    /* renamed from: l */
    public String f13960l;

    /* renamed from: m */
    public String f13961m;

    /* renamed from: n */
    public Request f13962n;

    /* loaded from: classes16.dex */
    class a extends RequestBody {
        int a;
        final /* synthetic */ File b;

        a(File file) {
            b.this = r1;
            this.b = file;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.b.length();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            String str = b.this.f13952c.get("content-type");
            if (TextUtils.isEmpty(str)) {
                String replace = b.this.f13959k.replace("\"", "");
                str = replace.substring(replace.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1);
            }
            return MediaType.parse(str);
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) {
            try {
                Source source = Okio.source(this.b);
                Buffer buffer = new Buffer();
                long contentLength = contentLength();
                long j2 = 0;
                while (true) {
                    long read = source.read(buffer, 2048L);
                    if (read == -1) {
                        return;
                    }
                    bufferedSink.write(buffer, read);
                    long j3 = j2 + read;
                    double d = j3;
                    Double.isNaN(d);
                    double d2 = contentLength;
                    Double.isNaN(d2);
                    int i2 = (int) ((d * 100.0d) / d2);
                    if (this.a != i2) {
                        this.a = i2;
                        b.this.f13956h.a(i2, j3, contentLength);
                    }
                    j2 = j3;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public b(String str, String str2, String str3, String str4, String str5, int i2, String str6, c.a aVar) {
        this.b = 60000;
        this.f13954f = str;
        this.f13961m = str2;
        this.f13956h = aVar;
        this.f13960l = str3;
        this.f13958j = str5;
        this.f13959k = str4;
        if (i2 > 0) {
            this.b = i2;
        }
        System.currentTimeMillis();
    }

    @Override // java.lang.Runnable
    public void run() {
        c.a aVar;
        String str;
        Iterator<String> it = this.f13952c.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                File file = new File(this.f13953e);
                if (file.exists() && file.isFile() && file.canRead()) {
                    OkHttpClient a2 = com.jingdong.manto.p.a.b().a(this.b);
                    a aVar2 = new a(file);
                    MultipartBody.Builder builder = new MultipartBody.Builder();
                    builder.setType(MultipartBody.FORM);
                    for (String str2 : this.f13955g.keySet()) {
                        builder.addFormDataPart(str2, this.f13955g.get(str2).toString());
                    }
                    builder.addFormDataPart(this.f13960l, this.f13959k, aVar2);
                    MultipartBody build = builder.build();
                    if (TextUtils.isEmpty(this.f13958j)) {
                        this.f13958j = "";
                    }
                    Request.Builder post = new Request.Builder().url(this.f13961m).addHeader("User-Agent", this.f13958j).post(build);
                    com.jingdong.manto.p.c.a(post, this.f13952c);
                    Request build2 = post.build();
                    this.f13962n = build2;
                    Call newCall = a2.newCall(build2);
                    this.d = newCall;
                    try {
                        Response execute = newCall.execute();
                        if (execute.isSuccessful()) {
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
                            this.f13956h.a(c.f13964e, execute.body().string(), execute.code(), jSONObject);
                        } else {
                            this.f13956h.a(c.d, execute.body().string(), execute.code(), null);
                        }
                    } catch (IOException e3) {
                        this.f13956h.a(e3.getMessage());
                    }
                } else {
                    aVar = this.f13956h;
                    str = "file is not exists or be read";
                }
            } else if (it.next().equalsIgnoreCase("content-length")) {
                aVar = this.f13956h;
                str = "not allow to set Content-Length";
                break;
            }
        }
        aVar.a(str);
        com.jingdong.manto.p.h.a.b().a(this.f13954f, this);
    }
}
