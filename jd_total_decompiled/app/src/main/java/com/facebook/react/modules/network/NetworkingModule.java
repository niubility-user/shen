package com.facebook.react.modules.network;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.StandardCharsets;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.common.net.HttpHeaders;
import com.jd.jdcache.util.UrlHelper;
import com.jdpay.net.http.HTTP;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;

@ReactModule(name = NetworkingModule.NAME)
/* loaded from: classes12.dex */
public final class NetworkingModule extends ReactContextBaseJavaModule {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    protected static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    @Nullable
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<ResponseHandler> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    /* loaded from: classes12.dex */
    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    /* loaded from: classes12.dex */
    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    /* loaded from: classes12.dex */
    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient, @Nullable List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            Iterator<NetworkInterceptorCreator> it = list.iterator();
            while (it.hasNext()) {
                newBuilder.addNetworkInterceptor(it.next().create());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer) okHttpClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    private synchronized void addRequest(int i2) {
        this.mRequestIds.add(Integer.valueOf(i2));
    }

    private synchronized void cancelAllRequests() {
        Iterator<Integer> it = this.mRequestIds.iterator();
        while (it.hasNext()) {
            cancelRequest(it.next().intValue());
        }
        this.mRequestIds.clear();
    }

    private void cancelRequest(final int i2) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.network.NetworkingModule.4
            {
                NetworkingModule.this = this;
            }

            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                OkHttpCallUtil.cancelTag(NetworkingModule.this.mClient, Integer.valueOf(i2));
            }
        }.execute(new Void[0]);
    }

    @Nullable
    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i2) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int i3 = 0; i3 < size; i3++) {
            ReadableMap map = readableArray.getMap(i3);
            Headers extractHeaders = extractHeaders(map.getArray("headers"), null);
            if (extractHeaders == null) {
                ResponseUtil.onRequestError(eventEmitter, i2, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!map.hasKey(REQUEST_BODY_KEY_URI)) {
                ResponseUtil.onRequestError(eventEmitter, i2, "Unrecognized FormData part.", null);
            } else if (mediaType == null) {
                ResponseUtil.onRequestError(eventEmitter, i2, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String string = map.getString(REQUEST_BODY_KEY_URI);
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, i2, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                builder.addPart(extractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            }
        }
        return builder;
    }

    @Nullable
    private Headers extractHeaders(@Nullable ReadableArray readableArray, @Nullable ReadableMap readableMap) {
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            ReadableArray array = readableArray.getArray(i2);
            if (array != null && array.size() == 2) {
                String stripHeaderName = HeaderUtil.stripHeaderName(array.getString(0));
                String stripHeaderValue = HeaderUtil.stripHeaderValue(array.getString(1));
                if (stripHeaderName != null && stripHeaderValue != null) {
                    builder.add(stripHeaderName, stripHeaderValue);
                }
            }
            return null;
        }
        if (TextUtils.isEmpty(builder.get(USER_AGENT_HEADER_NAME))) {
            builder.add(USER_AGENT_HEADER_NAME, BaseInfoHelper.newInstance().getUserAgent());
        }
        if (readableMap != null && readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            z = true;
        }
        if (!z) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter() {
        return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    public void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, ResponseBody responseBody) throws IOException {
        long j2;
        long j3 = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j2 = progressResponseBody.totalBytesRead();
            try {
                j3 = progressResponseBody.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j2 = -1;
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(responseBody.contentType() == null ? StandardCharsets.UTF_8 : responseBody.contentType().charset(StandardCharsets.UTF_8));
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    return;
                }
                ResponseUtil.onIncrementalDataReceived(rCTDeviceEventEmitter, i2, progressiveStringDecoder.decodeNext(bArr, read), j2, j3);
            }
        } finally {
            byteStream.close();
        }
    }

    public synchronized void removeRequest(int i2) {
        this.mRequestIds.remove(Integer.valueOf(i2));
    }

    public static boolean shouldDispatch(long j2, long j3) {
        return j3 + 100000000 < j2;
    }

    public static WritableMap translateHeaders(Headers headers) {
        WritableMap createMap = Arguments.createMap();
        for (int i2 = 0; i2 < headers.size(); i2++) {
            String name = headers.name(i2);
            if (createMap.hasKey(name)) {
                createMap.putString(name, createMap.getString(name) + ", " + headers.value(i2));
            } else {
                createMap.putString(name, headers.value(i2));
            }
        }
        return createMap;
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int i2) {
        if (requestBody == null) {
            return null;
        }
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.3
            long last = System.nanoTime();

            {
                NetworkingModule.this = this;
            }

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long j2, long j3, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.last)) {
                    ResponseUtil.onDataSend(rCTDeviceEventEmitter, i2, j2, j3);
                    this.last = nanoTime;
                }
            }
        });
    }

    @ReactMethod
    public void abortRequest(int i2) {
        cancelRequest(i2);
        removeRequest(i2);
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    @ReactMethod
    public void clearCookies(Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    @ReactMethod
    public void sendRequest(String str, String str2, int i2, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, int i3, boolean z2) {
        try {
            sendRequestInternal(str, str2, i2, readableArray, readableMap, str3, z, i3, z2);
        } catch (Throwable th) {
            FLog.e(TAG, "Failed to send url request: " + str2, th);
            ResponseUtil.onRequestError(getEventEmitter(), i2, th.getMessage(), th);
        }
    }

    public void sendRequestInternal(String str, String str2, final int i2, ReadableArray readableArray, ReadableMap readableMap, final String str3, final boolean z, int i3, boolean z2) {
        RequestBodyHandler requestBodyHandler;
        RequestBody emptyBody;
        Charset charset;
        final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        try {
            Uri parse = Uri.parse(str2);
            for (UriHandler uriHandler : this.mUriHandlers) {
                if (uriHandler.supports(parse, str3)) {
                    ResponseUtil.onDataReceived(eventEmitter, i2, uriHandler.fetch(parse));
                    ResponseUtil.onRequestSuccess(eventEmitter, i2);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (i2 != 0) {
                    url.tag(Integer.valueOf(i2));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new Interceptor() { // from class: com.facebook.react.modules.network.NetworkingModule.1
                        {
                            NetworkingModule.this = this;
                        }

                        @Override // okhttp3.Interceptor
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.1.1
                                long last = System.nanoTime();

                                {
                                    AnonymousClass1.this = this;
                                }

                                @Override // com.facebook.react.modules.network.ProgressListener
                                public void onProgress(long j2, long j3, boolean z3) {
                                    long nanoTime = System.nanoTime();
                                    if ((z3 || NetworkingModule.shouldDispatch(nanoTime, this.last)) && !str3.equals("text")) {
                                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                        ResponseUtil.onDataReceivedProgress(eventEmitter, i2, j2, j3);
                                        this.last = nanoTime;
                                    }
                                }
                            })).build();
                        }
                    });
                }
                if (i3 != this.mClient.connectTimeoutMillis()) {
                    newBuilder.connectTimeout(i3, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    ResponseUtil.onRequestError(eventEmitter, i2, "Unrecognized headers format", null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<RequestBodyHandler> it = this.mRequestBodyHandlers.iterator();
                    while (it.hasNext()) {
                        requestBodyHandler = it.next();
                        if (requestBodyHandler.supports(readableMap)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (readableMap == null || str.toLowerCase().equals(IMantoServerRequester.GET) || str.toLowerCase().equals(DataCompassUtils.MODULE_TYPE_HEAD)) {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                } else if (requestBodyHandler != null) {
                    emptyBody = requestBodyHandler.toRequestBody(readableMap, str4);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i2, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                    MediaType parse2 = MediaType.parse(str4);
                    if (RequestBodyUtil.isGzipEncoding(str5)) {
                        emptyBody = RequestBodyUtil.createGzip(parse2, string);
                        if (emptyBody == null) {
                            ResponseUtil.onRequestError(eventEmitter, i2, "Failed to gzip request body", null);
                            return;
                        }
                    } else {
                        if (parse2 == null) {
                            charset = StandardCharsets.UTF_8;
                        } else {
                            charset = parse2.charset(StandardCharsets.UTF_8);
                        }
                        emptyBody = RequestBody.create(parse2, string.getBytes(charset));
                    }
                } else if (readableMap.hasKey("base64")) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i2, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    emptyBody = RequestBody.create(MediaType.parse(str4), ByteString.decodeBase64(readableMap.getString("base64")));
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_URI)) {
                    if (str4 == null) {
                        ResponseUtil.onRequestError(eventEmitter, i2, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string2 = readableMap.getString(REQUEST_BODY_KEY_URI);
                    InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string2);
                    if (fileInputStream == null) {
                        ResponseUtil.onRequestError(eventEmitter, i2, "Could not retrieve file for uri " + string2, null);
                        return;
                    }
                    emptyBody = RequestBodyUtil.create(MediaType.parse(str4), fileInputStream);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (str4 == null) {
                        str4 = HTTP.CONTENT_TYPE_FORM_DATA;
                    }
                    MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, i2);
                    if (constructMultipartBody == null) {
                        return;
                    }
                    emptyBody = constructMultipartBody.build();
                } else {
                    emptyBody = RequestBodyUtil.getEmptyBody(str);
                }
                url.method(str, wrapRequestBodyWithProgressEmitter(emptyBody, eventEmitter, i2));
                addRequest(i2);
                build.newCall(url.build()).enqueue(new okhttp3.Callback() { // from class: com.facebook.react.modules.network.NetworkingModule.2
                    {
                        NetworkingModule.this = this;
                    }

                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        String str6;
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(i2);
                        if (iOException.getMessage() != null) {
                            str6 = iOException.getMessage();
                        } else {
                            str6 = "Error while executing request: " + iOException.getClass().getSimpleName();
                        }
                        ResponseUtil.onRequestError(eventEmitter, i2, str6, iOException);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) throws IOException {
                        if (NetworkingModule.this.mShuttingDown) {
                            return;
                        }
                        NetworkingModule.this.removeRequest(i2);
                        ResponseUtil.onResponseReceived(eventEmitter, i2, response.code(), NetworkingModule.translateHeaders(response.headers()), response.request().url().toString());
                        try {
                            ResponseBody body = response.body();
                            if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                GzipSource gzipSource = new GzipSource(body.source());
                                String header = response.header(HttpHeaders.CONTENT_TYPE);
                                body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1L, Okio.buffer(gzipSource));
                            }
                            for (ResponseHandler responseHandler : NetworkingModule.this.mResponseHandlers) {
                                if (responseHandler.supports(str3)) {
                                    ResponseUtil.onDataReceived(eventEmitter, i2, responseHandler.toResponseData(body));
                                    ResponseUtil.onRequestSuccess(eventEmitter, i2);
                                    return;
                                }
                            }
                            if (z && str3.equals("text")) {
                                NetworkingModule.this.readWithProgress(eventEmitter, i2, body);
                                ResponseUtil.onRequestSuccess(eventEmitter, i2);
                                return;
                            }
                            String str6 = "";
                            if (str3.equals("text")) {
                                try {
                                    str6 = body.string();
                                } catch (IOException e2) {
                                    if (!response.request().method().equalsIgnoreCase(UrlHelper.METHOD_HEAD)) {
                                        ResponseUtil.onRequestError(eventEmitter, i2, e2.getMessage(), e2);
                                    }
                                }
                            } else if (str3.equals("base64")) {
                                str6 = Base64.encodeToString(body.bytes(), 2);
                            }
                            ResponseUtil.onDataReceived(eventEmitter, i2, str6);
                            ResponseUtil.onRequestSuccess(eventEmitter, i2);
                        } catch (IOException e3) {
                            ResponseUtil.onRequestError(eventEmitter, i2, e3.getMessage(), e3);
                        }
                    }
                });
            } catch (Exception e2) {
                ResponseUtil.onRequestError(eventEmitter, i2, e2.getMessage(), null);
            }
        } catch (IOException e3) {
            ResponseUtil.onRequestError(eventEmitter, i2, e3.getMessage(), e3);
        }
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }
}
