package com.jingdong.manto.network.common;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
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
    */
    public String get(MantoBaseRequest mantoBaseRequest) {
        MantoCommonHttpHandler mantoCommonHttpHandler;
        Response execute;
        StringBuilder sb = new StringBuilder(mantoBaseRequest.getHost());
        JSONObject requestParams = mantoBaseRequest.getRequestParams();
        if (TextUtils.isEmpty(sb.toString())) {
            throw new IllegalArgumentException("url is null");
        }
        if (requestParams != null) {
            try {
                Iterator<String> keys = requestParams.keys();
                boolean z = false;
                if (!sb.toString().endsWith("?")) {
                    if (sb.toString().contains("?")) {
                        mantoCommonHttpHandler = this;
                        z = true;
                        while (keys.hasNext()) {
                            String next = keys.next();
                            String optString = requestParams.optString(next);
                            String encode = URLEncoder.encode(next);
                            String encode2 = URLEncoder.encode(optString);
                            if (z) {
                                sb.append(ContainerUtils.FIELD_DELIMITER);
                                sb.append(encode);
                                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                sb.append(encode2);
                            } else {
                                sb.append(encode);
                                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                sb.append(encode2);
                                z = true;
                                while (keys.hasNext()) {
                                    try {
                                    } catch (Throwable th) {
                                        th = th;
                                        th.printStackTrace();
                                        execute = mantoCommonHttpHandler.okHttpClient.newCall(new Request.Builder().url(sb.toString()).build()).execute();
                                        return execute == null ? null : null;
                                    }
                                }
                            }
                        }
                    } else {
                        sb.append("?");
                    }
                }
                mantoCommonHttpHandler = this;
                while (keys.hasNext()) {
                }
            } catch (Throwable th2) {
                th = th2;
                mantoCommonHttpHandler = this;
            }
        } else {
            mantoCommonHttpHandler = this;
        }
        try {
            execute = mantoCommonHttpHandler.okHttpClient.newCall(new Request.Builder().url(sb.toString()).build()).execute();
            if (execute == null && execute.isSuccessful()) {
                return execute.body().string();
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041 A[Catch: all -> 0x0071, TRY_LEAVE, TryCatch #0 {all -> 0x0071, blocks: (B:13:0x003b, B:15:0x0041, B:18:0x0057, B:19:0x0066), top: B:32:0x003b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:32:0x003b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void get(MantoBaseRequest mantoBaseRequest, IMantoHttpListener iMantoHttpListener) {
        MantoCommonHttpHandler mantoCommonHttpHandler;
        StringBuilder sb = new StringBuilder(mantoBaseRequest.getHost());
        JSONObject requestParams = mantoBaseRequest.getRequestParams();
        if (TextUtils.isEmpty(sb.toString())) {
            throw new IllegalArgumentException("url is null");
        }
        if (requestParams != null) {
            try {
                Iterator<String> keys = requestParams.keys();
                boolean z = false;
                if (!sb.toString().endsWith("?")) {
                    if (sb.toString().contains("?")) {
                        mantoCommonHttpHandler = this;
                        z = true;
                        while (keys.hasNext()) {
                            String next = keys.next();
                            String optString = requestParams.optString(next);
                            String encode = URLEncoder.encode(next);
                            String encode2 = URLEncoder.encode(optString);
                            if (z) {
                                sb.append(ContainerUtils.FIELD_DELIMITER);
                                sb.append(encode);
                                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                sb.append(encode2);
                            } else {
                                sb.append(encode);
                                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                sb.append(encode2);
                                z = true;
                                while (keys.hasNext()) {
                                    try {
                                    } catch (Throwable th) {
                                        th = th;
                                        th.printStackTrace();
                                        mantoCommonHttpHandler.okHttpClient.newCall(new Request.Builder().url(sb.toString()).build()).enqueue(new a(mantoCommonHttpHandler, iMantoHttpListener));
                                    }
                                }
                            }
                        }
                    } else {
                        sb.append("?");
                    }
                }
                mantoCommonHttpHandler = this;
                while (keys.hasNext()) {
                }
            } catch (Throwable th2) {
                th = th2;
                mantoCommonHttpHandler = this;
            }
        } else {
            mantoCommonHttpHandler = this;
        }
        mantoCommonHttpHandler.okHttpClient.newCall(new Request.Builder().url(sb.toString()).build()).enqueue(new a(mantoCommonHttpHandler, iMantoHttpListener));
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
