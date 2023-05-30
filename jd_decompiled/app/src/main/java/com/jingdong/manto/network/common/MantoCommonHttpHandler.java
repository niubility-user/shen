package com.jingdong.manto.network.common;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoCommonHttpHandler {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "CommonHttpHandler";
    private static volatile MantoCommonHttpHandler instance;
    private final OkHttpClient okHttpClient = com.jingdong.manto.p.a.b().a(10000);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Callback {
        final /* synthetic */ IMantoHttpListener a;

        a(MantoCommonHttpHandler mantoCommonHttpHandler, IMantoHttpListener iMantoHttpListener) {
            this.a = iMantoHttpListener;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            IMantoHttpListener iMantoHttpListener = this.a;
            if (iMantoHttpListener != null) {
                iMantoHttpListener.onError(null, iOException);
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            IMantoHttpListener iMantoHttpListener;
            if (response == null || !response.isSuccessful() || (iMantoHttpListener = this.a) == null) {
                return;
            }
            try {
                iMantoHttpListener.onSuccess(new JSONObject(response.body().string()));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Callback {
        final /* synthetic */ IMantoHttpListener a;

        b(MantoCommonHttpHandler mantoCommonHttpHandler, IMantoHttpListener iMantoHttpListener) {
            this.a = iMantoHttpListener;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            IMantoHttpListener iMantoHttpListener = this.a;
            if (iMantoHttpListener != null) {
                iMantoHttpListener.onError(null, iOException);
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            IMantoHttpListener iMantoHttpListener;
            if (response == null || !response.isSuccessful() || (iMantoHttpListener = this.a) == null) {
                return;
            }
            try {
                iMantoHttpListener.onSuccess(new JSONObject(response.body().string()));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private MantoCommonHttpHandler() {
    }

    public static MantoCommonHttpHandler getInstance() {
        if (instance == null) {
            synchronized (MantoCommonHttpHandler.class) {
                if (instance == null) {
                    instance = new MantoCommonHttpHandler();
                }
            }
        }
        return instance;
    }

    public void commit(MantoBaseRequest mantoBaseRequest, IMantoHttpListener iMantoHttpListener) {
        if (mantoBaseRequest == null) {
            throw new IllegalArgumentException("request is null");
        }
        if (mantoBaseRequest.getRequestMethod().equals(MantoBaseRequest.RequestMethod.GET)) {
            get(mantoBaseRequest, iMantoHttpListener);
        } else if (mantoBaseRequest.getRequestMethod().equals(MantoBaseRequest.RequestMethod.POST)) {
            post(mantoBaseRequest, iMantoHttpListener);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041 A[Catch: all -> 0x0071, TRY_LEAVE, TryCatch #0 {all -> 0x0071, blocks: (B:13:0x003b, B:15:0x0041, B:18:0x0057, B:19:0x0066), top: B:41:0x003b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:41:0x003b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String get(com.jingdong.manto.network.mantorequests.MantoBaseRequest r9) {
        /*
            r8 = this;
            java.lang.String r0 = "?"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = r9.getHost()
            r1.<init>(r2)
            org.json.JSONObject r9 = r9.getRequestParams()
            java.lang.String r2 = r1.toString()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto Lac
            if (r9 == 0) goto L79
            java.util.Iterator r2 = r9.keys()     // Catch: java.lang.Throwable -> L73
            r3 = 0
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L73
            boolean r4 = r4.endsWith(r0)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L3a
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L73
            boolean r4 = r4.contains(r0)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L38
            r1.append(r0)     // Catch: java.lang.Throwable -> L73
            goto L3a
        L38:
            r0 = r8
            goto L6f
        L3a:
            r0 = r8
        L3b:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Throwable -> L71
            if (r4 == 0) goto L7a
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L71
            java.lang.String r5 = r9.optString(r4)     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = java.net.URLEncoder.encode(r4)     // Catch: java.lang.Throwable -> L71
            java.lang.String r5 = java.net.URLEncoder.encode(r5)     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = "="
            if (r3 == 0) goto L66
            java.lang.String r7 = "&"
            r1.append(r7)     // Catch: java.lang.Throwable -> L71
            r1.append(r4)     // Catch: java.lang.Throwable -> L71
            r1.append(r6)     // Catch: java.lang.Throwable -> L71
            r1.append(r5)     // Catch: java.lang.Throwable -> L71
            goto L3b
        L66:
            r1.append(r4)     // Catch: java.lang.Throwable -> L71
            r1.append(r6)     // Catch: java.lang.Throwable -> L71
            r1.append(r5)     // Catch: java.lang.Throwable -> L71
        L6f:
            r3 = 1
            goto L3b
        L71:
            r9 = move-exception
            goto L75
        L73:
            r9 = move-exception
            r0 = r8
        L75:
            r9.printStackTrace()
            goto L7a
        L79:
            r0 = r8
        L7a:
            okhttp3.Request$Builder r9 = new okhttp3.Request$Builder
            r9.<init>()
            java.lang.String r1 = r1.toString()
            okhttp3.Request$Builder r9 = r9.url(r1)
            okhttp3.Request r9 = r9.build()
            okhttp3.OkHttpClient r0 = r0.okHttpClient     // Catch: java.lang.Throwable -> La6
            okhttp3.Call r9 = r0.newCall(r9)     // Catch: java.lang.Throwable -> La6
            okhttp3.Response r9 = r9.execute()     // Catch: java.lang.Throwable -> La6
            if (r9 == 0) goto Laa
            boolean r0 = r9.isSuccessful()     // Catch: java.lang.Throwable -> La6
            if (r0 == 0) goto Laa
            okhttp3.ResponseBody r9 = r9.body()     // Catch: java.lang.Throwable -> La6
            java.lang.String r9 = r9.string()     // Catch: java.lang.Throwable -> La6
            return r9
        La6:
            r9 = move-exception
            r9.printStackTrace()
        Laa:
            r9 = 0
            return r9
        Lac:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "url is null"
            r9.<init>(r0)
            goto Lb5
        Lb4:
            throw r9
        Lb5:
            goto Lb4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.network.common.MantoCommonHttpHandler.get(com.jingdong.manto.network.mantorequests.MantoBaseRequest):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041 A[Catch: all -> 0x0071, TRY_LEAVE, TryCatch #0 {all -> 0x0071, blocks: (B:13:0x003b, B:15:0x0041, B:18:0x0057, B:19:0x0066), top: B:32:0x003b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:32:0x003b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void get(com.jingdong.manto.network.mantorequests.MantoBaseRequest r9, com.jingdong.manto.network.common.IMantoHttpListener r10) {
        /*
            r8 = this;
            java.lang.String r0 = "?"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = r9.getHost()
            r1.<init>(r2)
            org.json.JSONObject r9 = r9.getRequestParams()
            java.lang.String r2 = r1.toString()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L9a
            if (r9 == 0) goto L79
            java.util.Iterator r2 = r9.keys()     // Catch: java.lang.Throwable -> L73
            r3 = 0
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L73
            boolean r4 = r4.endsWith(r0)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L3a
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L73
            boolean r4 = r4.contains(r0)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L38
            r1.append(r0)     // Catch: java.lang.Throwable -> L73
            goto L3a
        L38:
            r0 = r8
            goto L6f
        L3a:
            r0 = r8
        L3b:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Throwable -> L71
            if (r4 == 0) goto L7a
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L71
            java.lang.String r5 = r9.optString(r4)     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = java.net.URLEncoder.encode(r4)     // Catch: java.lang.Throwable -> L71
            java.lang.String r5 = java.net.URLEncoder.encode(r5)     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = "="
            if (r3 == 0) goto L66
            java.lang.String r7 = "&"
            r1.append(r7)     // Catch: java.lang.Throwable -> L71
            r1.append(r4)     // Catch: java.lang.Throwable -> L71
            r1.append(r6)     // Catch: java.lang.Throwable -> L71
            r1.append(r5)     // Catch: java.lang.Throwable -> L71
            goto L3b
        L66:
            r1.append(r4)     // Catch: java.lang.Throwable -> L71
            r1.append(r6)     // Catch: java.lang.Throwable -> L71
            r1.append(r5)     // Catch: java.lang.Throwable -> L71
        L6f:
            r3 = 1
            goto L3b
        L71:
            r9 = move-exception
            goto L75
        L73:
            r9 = move-exception
            r0 = r8
        L75:
            r9.printStackTrace()
            goto L7a
        L79:
            r0 = r8
        L7a:
            okhttp3.Request$Builder r9 = new okhttp3.Request$Builder
            r9.<init>()
            java.lang.String r1 = r1.toString()
            okhttp3.Request$Builder r9 = r9.url(r1)
            okhttp3.Request r9 = r9.build()
            okhttp3.OkHttpClient r1 = r0.okHttpClient
            okhttp3.Call r9 = r1.newCall(r9)
            com.jingdong.manto.network.common.MantoCommonHttpHandler$a r1 = new com.jingdong.manto.network.common.MantoCommonHttpHandler$a
            r1.<init>(r0, r10)
            r9.enqueue(r1)
            return
        L9a:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "url is null"
            r9.<init>(r10)
            goto La3
        La2:
            throw r9
        La3:
            goto La2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.network.common.MantoCommonHttpHandler.get(com.jingdong.manto.network.mantorequests.MantoBaseRequest, com.jingdong.manto.network.common.IMantoHttpListener):void");
    }

    public String post(MantoBaseRequest mantoBaseRequest) {
        String host = mantoBaseRequest.getHost();
        try {
            Response execute = this.okHttpClient.newCall(new Request.Builder().url(host).post(RequestBody.create(JSON, mantoBaseRequest.getRequestParams().toString())).build()).execute();
            if (execute == null || !execute.isSuccessful()) {
                return null;
            }
            return execute.body().string();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void post(MantoBaseRequest mantoBaseRequest, IMantoHttpListener iMantoHttpListener) {
        String host = mantoBaseRequest.getHost();
        this.okHttpClient.newCall(new Request.Builder().url(host).post(RequestBody.create(JSON, mantoBaseRequest.getRequestParams().toString())).build()).enqueue(new b(this, iMantoHttpListener));
    }
}
