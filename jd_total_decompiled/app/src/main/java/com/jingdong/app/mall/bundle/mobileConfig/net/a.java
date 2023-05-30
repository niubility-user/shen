package com.jingdong.app.mall.bundle.mobileConfig.net;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a implements IConfigFetcher {
    ExecutorService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.mobileConfig.net.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class ThreadFactoryC0258a implements ThreadFactory {
        ThreadFactoryC0258a(a aVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "jdmobileconfig");
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {
        final /* synthetic */ IConfigFetcherCallBack a;

        b(IConfigFetcherCallBack iConfigFetcherCallBack) {
            this.a = iConfigFetcherCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a(this.a);
        }
    }

    private String a(long j2) {
        return com.jingdong.app.mall.bundle.mobileConfig.c.b.a(com.jingdong.app.mall.bundle.mobileConfig.a.l().a(j2), "", com.jingdong.app.mall.bundle.mobileConfig.a.l().g());
    }

    private synchronized ExecutorService a() {
        if (this.a == null) {
            this.a = Executors.newSingleThreadExecutor(new ThreadFactoryC0258a(this));
        }
        return this.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b0 A[Catch: all -> 0x00e5, Exception -> 0x00e7, LOOP:0: B:21:0x00aa->B:23:0x00b0, LOOP_END, TryCatch #1 {all -> 0x00e5, blocks: (B:3:0x000d, B:20:0x0097, B:21:0x00aa, B:23:0x00b0, B:24:0x00b4, B:26:0x00d9, B:36:0x00e8, B:38:0x00ed), top: B:49:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9 A[Catch: all -> 0x00e5, Exception -> 0x00e7, TRY_LEAVE, TryCatch #1 {all -> 0x00e5, blocks: (B:3:0x000d, B:20:0x0097, B:21:0x00aa, B:23:0x00b0, B:24:0x00b4, B:26:0x00d9, B:36:0x00e8, B:38:0x00ed), top: B:49:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b4 A[EDGE_INSN: B:53:0x00b4->B:24:0x00b4 BREAK  A[LOOP:0: B:21:0x00aa->B:23:0x00b0], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void a(IConfigFetcherCallBack iConfigFetcherCallBack) {
        HttpURLConnection httpURLConnection;
        Map<String, List<String>> headerFields;
        BufferedReader bufferedReader;
        String readLine;
        List<String> list;
        String str;
        com.jingdong.app.mall.bundle.mobileConfig.b.a("start request data");
        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                String a = a(System.currentTimeMillis());
                com.jingdong.app.mall.bundle.mobileConfig.b.a("request url:" + a);
                httpURLConnection = (HttpURLConnection) new URL(a).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(15000);
                    headerFields = httpURLConnection.getHeaderFields();
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection2 = httpURLConnection;
                    e.printStackTrace();
                    if (iConfigFetcherCallBack != null) {
                        iConfigFetcherCallBack.onError(e);
                    }
                    if (httpURLConnection2 == null) {
                        return;
                    }
                    httpURLConnection2.disconnect();
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            if (headerFields != null && headerFields.containsKey("X-API-Sign-Message") && (list = headerFields.get("X-API-Sign-Message")) != null && list.contains("stale")) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("X-API-Sign-Message\uff1astale, retry...");
                try {
                    str = headerFields.get("X-API-Sign-Millis").get(0);
                } catch (Throwable unused) {
                    com.jingdong.app.mall.bundle.mobileConfig.b.a("X-API-Sign-Message\uff1astale, retry failed!!");
                }
                if (!TextUtils.isEmpty(str)) {
                    long parseLong = Long.parseLong(str);
                    httpURLConnection.disconnect();
                    com.jingdong.app.mall.bundle.mobileConfig.b.a("X-API-Sign-Message\uff1astale, reconnection...");
                    httpURLConnection2 = (HttpURLConnection) new URL(a(parseLong)).openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(httpURLConnection2.getInputStream())));
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    com.jingdong.app.mall.bundle.mobileConfig.b.a("reponse data:" + ((Object) sb));
                    JSONObject jSONObject = new JSONObject(sb.toString()).getJSONObject("data");
                    if (iConfigFetcherCallBack != null) {
                        iConfigFetcherCallBack.onSuccess(jSONObject);
                    }
                    if (httpURLConnection2 == null) {
                        return;
                    }
                    httpURLConnection2.disconnect();
                }
            }
            httpURLConnection2 = httpURLConnection;
            bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(httpURLConnection2.getInputStream())));
            while (true) {
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                }
                sb.append(readLine);
            }
            com.jingdong.app.mall.bundle.mobileConfig.b.a("reponse data:" + ((Object) sb));
            JSONObject jSONObject2 = new JSONObject(sb.toString()).getJSONObject("data");
            if (iConfigFetcherCallBack != null) {
            }
            if (httpURLConnection2 == null) {
            }
            httpURLConnection2.disconnect();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher
    public void fetch(ConfigRequestParams configRequestParams, IConfigFetcherCallBack iConfigFetcherCallBack) {
        try {
            a().execute(new b(iConfigFetcherCallBack));
        } catch (Throwable th) {
            th.printStackTrace();
            if (iConfigFetcherCallBack != null) {
                iConfigFetcherCallBack.onError(new RuntimeException(th));
            }
        }
    }
}
