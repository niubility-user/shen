package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class WebFileUploader {
    private static final String TAG = "WebFileUploader";
    protected String cookies;
    private String[] data;
    private ExecutorService executorService;
    private UniformListener mUniformListener;
    private AtomicBoolean code = new AtomicBoolean(true);
    private String videoThread = "videoThread";
    private String picThread = "picThread";
    public Map<Call, Runnable> requestMap = new ConcurrentHashMap();
    private OkHttpClient okHttpClient = initOkHttpClient();

    /* loaded from: classes12.dex */
    private static class OkHttpUploadRunnable implements Runnable {
        private Call call;
        private OkHttpClient client;
        private byte[] dataSource;
        private String filePath;
        private HashMap<String, String> header;
        private final WebFileUploader mWebFileUploader;
        private String requestUrl;
        private int seq;
        private int type;

        OkHttpUploadRunnable(UploadRequest uploadRequest, OkHttpClient okHttpClient, WebFileUploader webFileUploader, int i2) {
            this.mWebFileUploader = webFileUploader;
            this.requestUrl = uploadRequest.url;
            HashMap<String, String> hashMap = uploadRequest.header;
            this.header = hashMap;
            this.dataSource = uploadRequest.source;
            this.filePath = uploadRequest.uploadPath;
            this.client = okHttpClient;
            this.seq = i2;
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
                            if (TextUtils.isEmpty(this.mWebFileUploader.cookies)) {
                                this.mWebFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                            }
                            if (!TextUtils.isEmpty(this.mWebFileUploader.cookies)) {
                                cacheControl.addHeader("Cookie", this.mWebFileUploader.cookies);
                            }
                            HashMap<String, String> hashMap = this.header;
                            if (hashMap != null && !hashMap.isEmpty()) {
                                for (String str : this.header.keySet()) {
                                    cacheControl.addHeader(str, this.header.get(str));
                                    if (OKLog.D) {
                                        OKLog.d(WebFileUploader.TAG, str + " : " + this.header.get(str));
                                    }
                                }
                            }
                            Request build = cacheControl.build();
                            OkHttpClient okHttpClient = this.client;
                            if (okHttpClient == null) {
                                return null;
                            }
                            this.call = okHttpClient.newCall(build);
                        } catch (MalformedURLException unused) {
                            if (OKLog.D) {
                                OKLog.e(WebFileUploader.TAG, "[upload image error] requestUrl format error!");
                            }
                            return null;
                        }
                    } else {
                        if (OKLog.D) {
                            OKLog.e(WebFileUploader.TAG, "[upload image error] input param mustn't be null, request url or byte source is null");
                        }
                        return null;
                    }
                }
            } else if (this.call == null) {
                if (!TextUtils.isEmpty(this.requestUrl) && !TextUtils.isEmpty(this.filePath)) {
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
                        if (TextUtils.isEmpty(this.mWebFileUploader.cookies)) {
                            this.mWebFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                        }
                        if (!TextUtils.isEmpty(this.mWebFileUploader.cookies)) {
                            cacheControl2.addHeader("Cookie", this.mWebFileUploader.cookies);
                        }
                        Request build2 = cacheControl2.build();
                        OkHttpClient okHttpClient2 = this.client;
                        if (okHttpClient2 == null) {
                            return null;
                        }
                        this.call = okHttpClient2.newCall(build2);
                    } catch (MalformedURLException unused2) {
                        if (OKLog.D) {
                            OKLog.e(WebFileUploader.TAG, "requestUrl format error!");
                        }
                        return null;
                    }
                } else {
                    if (OKLog.D) {
                        OKLog.e(WebFileUploader.TAG, "input param mustn't be null!");
                    }
                    return null;
                }
            }
            return this.call;
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:97:0x01e5 -> B:98:0x01e8). Please submit an issue!!! */
        @Override // java.lang.Runnable
        public void run() {
            String str = "";
            if (OKLog.D) {
                String unused = WebFileUploader.TAG;
                Thread.currentThread().getName();
            }
            ResponseBody responseBody = null;
            try {
                try {
                    try {
                        if (this.call == null) {
                            this.call = getCall();
                        }
                        WebFileUploader webFileUploader = this.mWebFileUploader;
                        if (webFileUploader != null && !webFileUploader.code.get()) {
                            this.mWebFileUploader.requestMap.remove(this.call);
                            try {
                                WebFileUploader webFileUploader2 = this.mWebFileUploader;
                                if (webFileUploader2 == null || webFileUploader2.data == null) {
                                    return;
                                }
                                this.mWebFileUploader.data[this.seq] = "";
                                this.mWebFileUploader.requestMap.remove(this.call);
                                if (this.mWebFileUploader.requestMap.size() == 0) {
                                    this.mWebFileUploader.notifyEndAndMessage();
                                    return;
                                }
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        if (OKLog.D) {
                            OKLog.d(WebFileUploader.TAG, "start upload file...");
                        }
                        Response execute = this.call.execute();
                        if (!execute.isSuccessful()) {
                            WebFileUploader webFileUploader3 = this.mWebFileUploader;
                            if (webFileUploader3 != null) {
                                webFileUploader3.code.set(false);
                                this.mWebFileUploader.notifyEndAndMessage();
                                try {
                                    WebFileUploader webFileUploader4 = this.mWebFileUploader;
                                    if (webFileUploader4 == null || webFileUploader4.data == null) {
                                        return;
                                    }
                                    this.mWebFileUploader.data[this.seq] = "";
                                    this.mWebFileUploader.requestMap.remove(this.call);
                                    if (this.mWebFileUploader.requestMap.size() == 0) {
                                        this.mWebFileUploader.notifyEndAndMessage();
                                        return;
                                    }
                                    return;
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            }
                        } else {
                            responseBody = execute.body();
                            String string = responseBody.string();
                            if (OKLog.D) {
                                OKLog.d(WebFileUploader.TAG, " upload succeed! response message : " + string);
                            }
                            if (this.mWebFileUploader != null && !TextUtils.isEmpty(string)) {
                                if (this.type == 0) {
                                    JSONObject jSONObject = new JSONArray(string).getJSONObject(0);
                                    if (!"1".equals(jSONObject.getString("id"))) {
                                        this.mWebFileUploader.code.set(false);
                                        str = jSONObject.getString("msg");
                                    } else {
                                        str = MediaUploadUtils.DOMAINNAME + jSONObject.getString("msg");
                                    }
                                } else {
                                    str = new JSONObject(string).getString("message");
                                }
                            }
                        }
                        if (responseBody != null) {
                            try {
                                responseBody.close();
                            } catch (Exception e4) {
                                if (OKLog.D) {
                                    OKLog.e(WebFileUploader.TAG, "Exception when closing response body", e4);
                                }
                            }
                        }
                        WebFileUploader webFileUploader5 = this.mWebFileUploader;
                        if (webFileUploader5 == null || webFileUploader5.data == null) {
                            return;
                        }
                        this.mWebFileUploader.data[this.seq] = str;
                        this.mWebFileUploader.requestMap.remove(this.call);
                        if (this.mWebFileUploader.requestMap.size() == 0) {
                            this.mWebFileUploader.notifyEndAndMessage();
                        }
                    } catch (Exception e5) {
                        while (true) {
                            e5.printStackTrace();
                            return;
                        }
                    }
                } catch (Exception e6) {
                    e6.printStackTrace();
                    if (OKLog.D) {
                        OKLog.e(WebFileUploader.TAG, "Exception : " + e6.getMessage());
                    }
                    String exc = e6.toString();
                    if (0 != 0) {
                        try {
                            responseBody.close();
                        } catch (Exception e7) {
                            if (OKLog.D) {
                                OKLog.e(WebFileUploader.TAG, "Exception when closing response body", e7);
                            }
                        }
                    }
                    WebFileUploader webFileUploader6 = this.mWebFileUploader;
                    if (webFileUploader6 == null || webFileUploader6.data == null) {
                        return;
                    }
                    this.mWebFileUploader.data[this.seq] = exc;
                    this.mWebFileUploader.requestMap.remove(this.call);
                    if (this.mWebFileUploader.requestMap.size() == 0) {
                        this.mWebFileUploader.notifyEndAndMessage();
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        responseBody.close();
                    } catch (Exception e8) {
                        if (OKLog.D) {
                            OKLog.e(WebFileUploader.TAG, "Exception when closing response body", e8);
                        }
                    }
                }
                try {
                    WebFileUploader webFileUploader7 = this.mWebFileUploader;
                    if (webFileUploader7 != null && webFileUploader7.data != null) {
                        this.mWebFileUploader.data[this.seq] = "";
                        this.mWebFileUploader.requestMap.remove(this.call);
                        if (this.mWebFileUploader.requestMap.size() == 0) {
                            this.mWebFileUploader.notifyEndAndMessage();
                        }
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                throw th;
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface SingleListener {
        void onEnd(String str, String str2, String str3, int i2);

        void onError(String str, String str2, String str3, int i2);
    }

    /* loaded from: classes12.dex */
    public interface UniformListener {
        void onEnd(String str, String[] strArr, String str2);

        void onError(String str, String[] strArr, String str2);

        void onStart();
    }

    /* loaded from: classes12.dex */
    public static class UploadRequest {
        public HashMap<String, String> header;
        public byte[] source;
        public String uploadPath;
        public String url;

        /* loaded from: classes12.dex */
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
            this.source = builder.source;
            this.uploadPath = builder.uploadPath;
        }
    }

    /* loaded from: classes12.dex */
    private static class simpleOkHttpUploadRunnable implements Runnable {
        private Call call;
        private OkHttpClient client;
        private byte[] dataSource;
        private String filePath;
        private HashMap<String, String> header;
        private String requestUrl;
        int seq;
        private SingleListener singleListener;
        private int type;
        private final WebFileUploader webFileUploader;

        simpleOkHttpUploadRunnable(UploadRequest uploadRequest, OkHttpClient okHttpClient, int i2, WebFileUploader webFileUploader, SingleListener singleListener) {
            this.webFileUploader = webFileUploader;
            this.singleListener = singleListener;
            this.requestUrl = uploadRequest.url;
            HashMap<String, String> hashMap = uploadRequest.header;
            this.header = hashMap;
            this.dataSource = uploadRequest.source;
            this.filePath = uploadRequest.uploadPath;
            this.client = okHttpClient;
            this.seq = i2;
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
                            if (TextUtils.isEmpty(this.webFileUploader.cookies)) {
                                this.webFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                            }
                            if (!TextUtils.isEmpty(this.webFileUploader.cookies)) {
                                cacheControl.addHeader("Cookie", this.webFileUploader.cookies);
                            }
                            HashMap<String, String> hashMap = this.header;
                            if (hashMap != null && !hashMap.isEmpty()) {
                                for (String str : this.header.keySet()) {
                                    cacheControl.addHeader(str, this.header.get(str));
                                    if (OKLog.D) {
                                        OKLog.d(WebFileUploader.TAG, str + " : " + this.header.get(str));
                                    }
                                }
                            }
                            Request build = cacheControl.build();
                            OkHttpClient okHttpClient = this.client;
                            if (okHttpClient == null) {
                                return null;
                            }
                            this.call = okHttpClient.newCall(build);
                        } catch (MalformedURLException unused) {
                            if (OKLog.D) {
                                OKLog.e(WebFileUploader.TAG, "[upload image error] requestUrl format error!");
                            }
                            return null;
                        }
                    } else {
                        if (OKLog.D) {
                            OKLog.e(WebFileUploader.TAG, "[upload image error] input param mustn't be null, request url or byte source is null");
                        }
                        return null;
                    }
                }
            } else if (this.call == null) {
                if (!TextUtils.isEmpty(this.requestUrl) && !TextUtils.isEmpty(this.filePath)) {
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
                        if (TextUtils.isEmpty(this.webFileUploader.cookies)) {
                            this.webFileUploader.cookies = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
                        }
                        if (!TextUtils.isEmpty(this.webFileUploader.cookies)) {
                            cacheControl2.addHeader("Cookie", this.webFileUploader.cookies);
                        }
                        Request build2 = cacheControl2.build();
                        OkHttpClient okHttpClient2 = this.client;
                        if (okHttpClient2 == null) {
                            return null;
                        }
                        this.call = okHttpClient2.newCall(build2);
                    } catch (MalformedURLException unused2) {
                        if (OKLog.D) {
                            OKLog.e(WebFileUploader.TAG, "requestUrl format error!");
                        }
                        return null;
                    }
                } else {
                    if (OKLog.D) {
                        OKLog.e(WebFileUploader.TAG, "input param mustn't be null!");
                    }
                    return null;
                }
            }
            return this.call;
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x00e3, code lost:
            if (r0 != null) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0136, code lost:
            if (r0 == null) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0138, code lost:
            r0.requestMap.remove(r12.call);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x013f, code lost:
            r12.singleListener = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0141, code lost:
            return;
         */
        /* JADX WARN: Removed duplicated region for block: B:77:0x015a  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0146 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 356
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebFileUploader.simpleOkHttpUploadRunnable.run():void");
        }
    }

    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return builder.connectTimeout(15000L, timeUnit).readTimeout(Final.HALF_MINUTE, timeUnit).writeTimeout(35000L, timeUnit).followRedirects(false).followSslRedirects(false).retryOnConnectionFailure(false).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEndAndMessage() {
        if (this.mUniformListener != null) {
            if (this.code.get()) {
                this.mUniformListener.onEnd("0", this.data, "success");
            } else {
                this.mUniformListener.onError("-1", this.data, "fail");
            }
        }
        this.data = null;
        this.code.set(true);
        this.requestMap.clear();
        this.mUniformListener = null;
    }

    public void addTask(UploadRequest uploadRequest) {
        if (uploadRequest == null || TextUtils.isEmpty(uploadRequest.url)) {
            return;
        }
        if (OKLog.D) {
            OKLog.e(TAG, "webFileUploader add pic to upload task");
        }
        if (this.okHttpClient == null) {
            this.okHttpClient = initOkHttpClient();
        }
        OkHttpUploadRunnable okHttpUploadRunnable = new OkHttpUploadRunnable(uploadRequest, this.okHttpClient, this, this.requestMap.size());
        Call call = okHttpUploadRunnable.getCall();
        if (call != null) {
            this.requestMap.put(call, okHttpUploadRunnable);
        }
    }

    public void cancel() {
        if (OKLog.D) {
            OKLog.e(TAG, "webFileUploader cancel");
        }
        Map<Call, Runnable> map = this.requestMap;
        if (map != null && !map.isEmpty()) {
            try {
                for (Map.Entry<Call, Runnable> entry : this.requestMap.entrySet()) {
                    if (entry.getKey() != null) {
                        entry.getKey().cancel();
                        if (OKLog.D) {
                            OKLog.d(TAG, entry.getKey() + "is cancel");
                        }
                    }
                }
                this.requestMap.clear();
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, "webFileUploader cancel error");
                }
                e2.printStackTrace();
            }
        }
        this.cookies = null;
        this.mUniformListener = null;
        this.okHttpClient = null;
        this.data = null;
        this.code.set(true);
        try {
            ExecutorService executorService = this.executorService;
            if (executorService != null) {
                executorService.shutdownNow();
                this.executorService = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void uploadPicWithSingleCallBack(UploadRequest uploadRequest, int i2, SingleListener singleListener) {
        if (this.executorService == null) {
            this.executorService = Executors.newFixedThreadPool(1);
        }
        if (uploadRequest == null || TextUtils.isEmpty(uploadRequest.url)) {
            return;
        }
        if (OKLog.D) {
            OKLog.e(TAG, "webFileUploader add pic to upload task");
        }
        if (this.okHttpClient == null) {
            this.okHttpClient = initOkHttpClient();
        }
        simpleOkHttpUploadRunnable simpleokhttpuploadrunnable = new simpleOkHttpUploadRunnable(uploadRequest, this.okHttpClient, i2, this, singleListener);
        Call call = simpleokhttpuploadrunnable.getCall();
        if (call != null) {
            this.requestMap.put(call, simpleokhttpuploadrunnable);
        }
        this.executorService.execute(simpleokhttpuploadrunnable);
    }

    public void uploadPicWithUniformCallBack(UniformListener uniformListener) {
        Map<Call, Runnable> map = this.requestMap;
        if (map == null || map.isEmpty()) {
            return;
        }
        this.data = new String[this.requestMap.size()];
        this.mUniformListener = uniformListener;
        if (uniformListener != null) {
            uniformListener.onStart();
        }
        if (this.executorService == null) {
            this.executorService = Executors.newFixedThreadPool(5);
        }
        for (Map.Entry<Call, Runnable> entry : this.requestMap.entrySet()) {
            if (entry.getValue() != null) {
                this.executorService.execute(entry.getValue());
            }
        }
    }

    public void uploadVideo(String str, String str2, UniformListener uniformListener) {
        if (str != null) {
            if (OKLog.D) {
                OKLog.e(TAG, "webFileUploader upload video");
            }
            if (this.okHttpClient == null) {
                this.okHttpClient = initOkHttpClient();
            }
            this.mUniformListener = uniformListener;
            OkHttpUploadRunnable okHttpUploadRunnable = new OkHttpUploadRunnable(UploadRequest.Builder.newInstance().url(str).filePath(str2).build(), this.okHttpClient, this, 0);
            Call call = okHttpUploadRunnable.getCall();
            if (call != null) {
                this.requestMap.put(call, okHttpUploadRunnable);
                new Thread(okHttpUploadRunnable, this.videoThread).start();
            }
        }
    }
}
