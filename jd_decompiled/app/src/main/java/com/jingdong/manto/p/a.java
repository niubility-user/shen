package com.jingdong.manto.p;

import android.util.LruCache;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* loaded from: classes16.dex */
public class a {
    private static a b;
    private LruCache<Integer, OkHttpClient> a = new C0647a(this, 4);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.p.a$a */
    /* loaded from: classes16.dex */
    public class C0647a extends LruCache<Integer, OkHttpClient> {
        C0647a(a aVar, int i2) {
            super(i2);
        }

        @Override // android.util.LruCache
        /* renamed from: a */
        public int sizeOf(Integer num, OkHttpClient okHttpClient) {
            return 1;
        }
    }

    private a() {
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public OkHttpClient a(int i2) {
        OkHttpClient okHttpClient = this.a.get(Integer.valueOf(i2));
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            long j2 = i2;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            OkHttpClient build = builder.connectTimeout(j2, timeUnit).writeTimeout(j2, timeUnit).readTimeout(j2, timeUnit).dns(com.jingdong.manto.p.d.a.a()).build();
            this.a.put(Integer.valueOf(i2), build);
            return build;
        }
        return okHttpClient;
    }

    public void a() {
        LruCache<Integer, OkHttpClient> lruCache = this.a;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }
}
