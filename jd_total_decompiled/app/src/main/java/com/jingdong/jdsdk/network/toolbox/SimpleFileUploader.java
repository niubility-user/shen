package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class SimpleFileUploader {
    private static final String BOUNDARY = "Boundary-b1ed-4060-99b9-fca7ff59c113";
    private static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 15000;
    private static final int DEFAULT_HTTP_READ_TIMEOUT = 20000;
    private static final int DEFAULT_HTTP_WRITE_TIMEOUT = 25000;
    private static final String ENTER = "\r\n";
    private static final int READ_TIMEOUT = 5000;
    private static final String TAG = "SimpleFileUploader";
    private static final int THREAD_NUM = 4;
    protected static String cookies;
    private static ExecutorService executorService;
    private static SimpleFileUploader instance;
    private OkHttpClient okHttpClient;

    /* loaded from: classes14.dex */
    public static class OkHttpUploadRunnable implements Runnable {
        private static final int TYPE_IMAGE = 0;
        private static final int TYPE_VEDIO = 1;
        private Call call;
        private OkHttpClient client;
        private byte[] dataSource;
        private String filePath;
        private HashMap<String, String> header;
        private HttpGroup.OnAllListener listener;
        private String requestUrl;
        private int type;

        public OkHttpUploadRunnable(UploadRequest uploadRequest, OkHttpClient okHttpClient, HttpGroup.OnAllListener onAllListener) {
            this.type = -1;
            this.requestUrl = uploadRequest.url;
            this.filePath = uploadRequest.uploadPath;
            HashMap<String, String> hashMap = uploadRequest.header;
            this.header = hashMap;
            this.dataSource = uploadRequest.source;
            this.listener = onAllListener;
            this.client = okHttpClient;
            if (TextUtils.equals(hashMap.get("type"), "0")) {
                this.type = 0;
            } else {
                this.type = 1;
            }
        }

        public Call getCall() {
            RequestBody create;
            if (this.type == 0) {
                if (this.call == null) {
                    if (!TextUtils.isEmpty(this.requestUrl) && this.dataSource != null) {
                        try {
                            new URL(this.requestUrl);
                            Request.Builder cacheControl = new Request.Builder().url(this.requestUrl).post(RequestBody.create(MediaType.parse("image/jpg"), this.dataSource)).cacheControl(CacheControl.FORCE_NETWORK);
                            if (TextUtils.isEmpty(SimpleFileUploader.cookies)) {
                                SimpleFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                            }
                            if (!TextUtils.isEmpty(SimpleFileUploader.cookies)) {
                                cacheControl.addHeader("Cookie", SimpleFileUploader.cookies);
                            }
                            HashMap<String, String> hashMap = this.header;
                            if (hashMap != null && !hashMap.isEmpty()) {
                                for (String str : this.header.keySet()) {
                                    cacheControl.addHeader(str, this.header.get(str));
                                    if (OKLog.D) {
                                        OKLog.d(SimpleFileUploader.TAG, str + " : " + this.header.get(str));
                                    }
                                }
                            }
                            this.call = this.client.newCall(cacheControl.build());
                        } catch (MalformedURLException unused) {
                            if (OKLog.D) {
                                OKLog.e(SimpleFileUploader.TAG, "[upload image error] requestUrl format error!");
                            }
                            return null;
                        }
                    } else {
                        if (OKLog.D) {
                            OKLog.e(SimpleFileUploader.TAG, "[upload image error] input param mustn't be null, request url or byte source is null");
                        }
                        return null;
                    }
                }
            } else if (this.call == null) {
                if (!TextUtils.isEmpty(this.requestUrl) && (!TextUtils.isEmpty(this.filePath) || this.dataSource != null)) {
                    try {
                        new URL(this.requestUrl);
                        MediaType parse = MediaType.parse("application/octet-stream");
                        if (!TextUtils.isEmpty(this.filePath)) {
                            File file = new File(this.filePath);
                            if (!file.exists()) {
                                return null;
                            }
                            create = RequestBody.create(parse, file);
                        } else {
                            create = RequestBody.create(parse, this.dataSource);
                        }
                        Request.Builder cacheControl2 = new Request.Builder().url(this.requestUrl).post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("video_file", "file", create).addFormDataPart("parameter", "hifreud").build()).addHeader(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY).cacheControl(CacheControl.FORCE_NETWORK);
                        if (TextUtils.isEmpty(SimpleFileUploader.cookies)) {
                            SimpleFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                        }
                        if (!TextUtils.isEmpty(SimpleFileUploader.cookies)) {
                            cacheControl2.addHeader("Cookie", SimpleFileUploader.cookies);
                        }
                        HashMap<String, String> hashMap2 = this.header;
                        if (hashMap2 != null && !hashMap2.isEmpty()) {
                            for (String str2 : this.header.keySet()) {
                                cacheControl2.addHeader(str2, this.header.get(str2));
                                if (OKLog.D) {
                                    OKLog.d(SimpleFileUploader.TAG, str2 + " : " + this.header.get(str2));
                                }
                            }
                        }
                        this.call = this.client.newCall(cacheControl2.build());
                    } catch (MalformedURLException unused2) {
                        if (OKLog.D) {
                            OKLog.e(SimpleFileUploader.TAG, "requestUrl format error!");
                        }
                        return null;
                    }
                } else {
                    if (OKLog.D) {
                        OKLog.e(SimpleFileUploader.TAG, "input param mustn't be null!");
                    }
                    return null;
                }
            }
            return this.call;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResponseBody responseBody = null;
            try {
                try {
                    try {
                        if (this.call == null) {
                            this.call = getCall();
                        }
                        if (OKLog.D) {
                            OKLog.d(SimpleFileUploader.TAG, "start upload file...");
                        }
                        HttpGroup.OnAllListener onAllListener = this.listener;
                        if (onAllListener != null) {
                            onAllListener.onStart();
                        }
                        Response execute = this.call.execute();
                        if (!execute.isSuccessful()) {
                            Exception exc = new Exception("upload error code " + execute);
                            HttpGroup.OnAllListener onAllListener2 = this.listener;
                            if (onAllListener2 != null) {
                                onAllListener2.onError(new HttpError(exc));
                            }
                            if (OKLog.D) {
                                OKLog.e(SimpleFileUploader.TAG, "Exception occured : " + execute.message());
                            }
                        } else {
                            ResponseBody body = execute.body();
                            try {
                                String string = body.string();
                                OKLog.d(SimpleFileUploader.TAG, " upload succeed! response message : " + string);
                                if (this.listener != null) {
                                    HttpResponse httpResponse = new HttpResponse(null);
                                    httpResponse.setCode(execute.code());
                                    httpResponse.setString(string);
                                    if (this.type == 0) {
                                        httpResponse.setFastJsonArray(JDJSON.parseArray(string));
                                    } else {
                                        httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject(string)));
                                    }
                                    this.listener.onEnd(httpResponse);
                                }
                                responseBody = body;
                            } catch (Exception e2) {
                                e = e2;
                                responseBody = body;
                                e.printStackTrace();
                                if (OKLog.D) {
                                    OKLog.e(SimpleFileUploader.TAG, "Exception : " + e.getMessage());
                                }
                                HttpGroup.OnAllListener onAllListener3 = this.listener;
                                if (onAllListener3 != null) {
                                    onAllListener3.onError(new HttpError(e));
                                }
                                if (responseBody != null) {
                                    responseBody.close();
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                                responseBody = body;
                                if (responseBody != null) {
                                    try {
                                        responseBody.close();
                                    } catch (Exception e3) {
                                        OKLog.e(SimpleFileUploader.TAG, "Exception when closing response body", e3);
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (Exception e5) {
                OKLog.e(SimpleFileUploader.TAG, "Exception when closing response body", e5);
            }
        }
    }

    /* loaded from: classes14.dex */
    public static class UploadRequest {
        public HashMap<String, String> header;
        public byte[] source;
        public String uploadPath;
        public String url;

        /* loaded from: classes14.dex */
        public static class Builder {
            private HashMap<String, String> header = new HashMap<>();
            public byte[] source;
            private String uploadPath;
            private String url;

            private Builder() {
            }

            public static Builder newInstance() {
                return new Builder();
            }

            public Builder addHeader(String str, String str2) {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    this.header.put(str, str2);
                }
                return this;
            }

            public UploadRequest build() {
                return new UploadRequest(this);
            }

            public Builder byteSource(byte[] bArr) {
                this.source = bArr;
                return this;
            }

            public Builder filePath(String str) {
                this.uploadPath = str;
                return this;
            }

            public Builder url(String str) {
                this.url = str;
                return this;
            }
        }

        private UploadRequest(Builder builder) {
            this.header = builder.header;
            this.url = builder.url;
            this.uploadPath = builder.uploadPath;
            this.source = builder.source;
        }
    }

    private SimpleFileUploader() {
        executorService = Executors.newFixedThreadPool(4);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.okHttpClient = builder.connectTimeout(15000L, timeUnit).readTimeout(20000L, timeUnit).writeTimeout(25000L, timeUnit).followRedirects(false).followSslRedirects(false).retryOnConnectionFailure(false).build();
    }

    public static SimpleFileUploader getInstance() {
        if (instance == null) {
            synchronized (SimpleFileUploader.class) {
                if (instance == null) {
                    instance = new SimpleFileUploader();
                }
            }
        }
        return instance;
    }

    @Deprecated
    public void cancel(Call call) {
        if (call == null || call.isCanceled()) {
            return;
        }
        call.cancel();
    }

    public Call upload(String str, String str2, HttpGroup.OnAllListener onAllListener) {
        OkHttpUploadRunnable okHttpUploadRunnable = new OkHttpUploadRunnable(UploadRequest.Builder.newInstance().url(str).filePath(str2).build(), this.okHttpClient, onAllListener);
        Call call = okHttpUploadRunnable.getCall();
        if (call != null) {
            executorService.execute(okHttpUploadRunnable);
        }
        return call;
    }

    public Call upload(UploadRequest uploadRequest, HttpGroup.OnAllListener onAllListener) {
        if (uploadRequest != null && !TextUtils.isEmpty(uploadRequest.url) && (!TextUtils.isEmpty(uploadRequest.uploadPath) || uploadRequest.source != null)) {
            OkHttpUploadRunnable okHttpUploadRunnable = new OkHttpUploadRunnable(uploadRequest, this.okHttpClient, onAllListener);
            Call call = okHttpUploadRunnable.getCall();
            if (call != null) {
                executorService.execute(okHttpUploadRunnable);
            }
            return call;
        } else if (OKLog.D) {
            OKLog.e(TAG, "input parameter error, give up!");
            return null;
        } else {
            return null;
        }
    }
}
