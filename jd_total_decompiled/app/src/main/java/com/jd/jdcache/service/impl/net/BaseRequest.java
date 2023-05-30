package com.jd.jdcache.service.impl.net;

import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.jdcache.JDCacheConstant;
import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010%\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b&\u0018\u0000 m*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001mB\u00a5\u0001\u0012\u0006\u0010)\u001a\u00020\r\u0012\b\b\u0002\u0010P\u001a\u00020\r\u0012\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\r\u0012\u0018\b\u0002\u0010<\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\r\u0018\u00010;\u0012\u0016\b\u0002\u0010h\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u0018\u0012\u0016\b\u0002\u0010G\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u0018\u0012\b\b\u0002\u0010B\u001a\u00020\u0003\u0012\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u00105\u001a\u00020\u0006\u0012\b\b\u0002\u0010J\u001a\u00020\u0006\u00a2\u0006\u0004\bk\u0010lJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0010\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0011J!\u0010\u0014\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017JS\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0006\u0010\u0007\u001a\u00020\u00062\u001e\u0010\u001a\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0019\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u00a4@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJ#\u0010#\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001bH\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0%\u00a2\u0006\u0004\b&\u0010'J!\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0006\u0010)\u001a\u00020(H\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\"\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\bH\u0004\u00a2\u0006\u0004\b.\u0010/R\"\u0010)\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\"\u00105\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R2\u0010<\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\r\u0018\u00010;8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010\u0005\"\u0004\bE\u0010FR0\u0010G\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u0010=\u001a\u0004\bH\u0010?\"\u0004\bI\u0010AR\"\u0010J\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bJ\u00106\u001a\u0004\bK\u00108\"\u0004\bL\u0010:R$\u0010M\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bM\u00100\u001a\u0004\bN\u00102\"\u0004\bO\u00104R\"\u0010P\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bP\u00100\u001a\u0004\bQ\u00102\"\u0004\bR\u00104R$\u0010T\u001a\u00020\r2\u0006\u0010S\u001a\u00020\r8F@BX\u0086\u000e\u00a2\u0006\f\n\u0004\bT\u00100\u001a\u0004\bU\u00102R$\u0010V\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bV\u00100\u001a\u0004\bW\u00102\"\u0004\bX\u00104R$\u0010Y\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u00100\u001a\u0004\bZ\u00102\"\u0004\b[\u00104R$\u0010]\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d\u0018\u00010\\8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010`\u001a\u00020_8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010c\u001a\u00020\r8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bb\u00102R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010/R0\u0010h\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bh\u0010=\u001a\u0004\bi\u0010?\"\u0004\bj\u0010A\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006n"}, d2 = {"Lcom/jd/jdcache/service/impl/net/BaseRequest;", "T", "", "", "isAllowBody", "()Z", "", "responseCode", "Ljava/net/HttpURLConnection;", "connection", "Ljava/io/InputStream;", "getServerStream", "(ILjava/net/HttpURLConnection;)Ljava/io/InputStream;", "", "contentEncoding", "urlConnection", "getInputStream", "(Ljava/lang/String;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;", "getErrorStream", "inputStream", "gzipInputStream", "(Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/InputStream;", "isGzipContent", "(Ljava/lang/String;)Z", "", "", "responseHeaders", "", "contentLength", "Lcom/jd/jdcache/service/base/NetState;", "parseData", "(ILjava/util/Map;JLjava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationCompat.CATEGORY_PROGRESS, CartConstant.KEY_LENGTH, "", "notifyProgress", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/flow/Flow;", "connectFlow", "()Lkotlinx/coroutines/flow/Flow;", "Ljava/net/URL;", "url", "connect", "(Ljava/net/URL;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "()V", "writeBody", "(Ljava/net/HttpURLConnection;)V", "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "connectTimeout", "I", "getConnectTimeout", "()I", "setConnectTimeout", "(I)V", "", "header", "Ljava/util/Map;", "getHeader", "()Ljava/util/Map;", "setHeader", "(Ljava/util/Map;)V", "allowRedirect", "Z", "getAllowRedirect", "setAllowRedirect", "(Z)V", "body", "getBody", "setBody", "readTimeout", "getReadTimeout", "setReadTimeout", BabelLoginCallback.KEY_COOKIES, "getCookies", "setCookies", "method", "getMethod", "setMethod", "<set-?>", "requestUrl", "getRequestUrl", "userAgent", "getUserAgent", "setUserAgent", "referer", "getReferer", "setReferer", "Lkotlinx/coroutines/flow/FlowCollector;", "flowCollector", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getTAG", "TAG", "Ljava/net/HttpURLConnection;", "getConnection", "()Ljava/net/HttpURLConnection;", "setConnection", "params", "getParams", "setParams", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZLjava/lang/String;II)V", "Companion", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class BaseRequest<T> {
    @NotNull
    public static final String HEAD_KEY_CONNECTION = "Connection";
    @NotNull
    public static final String HEAD_KEY_CONTENT_ENCODING = "Content-Encoding";
    @NotNull
    public static final String HEAD_KEY_COOKIE = "Cookie";
    @NotNull
    public static final String HEAD_KEY_REFERER = "Referer";
    @NotNull
    public static final String HEAD_KEY_USER_AGENT = "User-Agent";
    @NotNull
    public static final String HEAD_VALUE_CONNECTION_CLOSE = "close";
    @NotNull
    public static final String HEAD_VALUE_CONNECTION_KEEP_ALIVE = "keep-alive";
    private boolean allowRedirect;
    @Nullable
    private Map<String, String> body;
    private int connectTimeout;
    @Nullable
    private HttpURLConnection connection;
    @Nullable
    private String cookies;
    private FlowCollector<? super NetState<T>> flowCollector;
    @Nullable
    private Map<String, String> header;
    private final CoroutineDispatcher ioDispatcher;
    @NotNull
    private String method;
    @Nullable
    private Map<String, String> params;
    private int readTimeout;
    @Nullable
    private String referer;
    @NotNull
    private String requestUrl;
    @NotNull
    private String url;
    @Nullable
    private String userAgent;

    public BaseRequest(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, boolean z, @Nullable String str5, int i2, int i3) {
        this.url = str;
        this.method = str2;
        this.userAgent = str3;
        this.cookies = str4;
        this.header = map;
        this.params = map2;
        this.body = map3;
        this.allowRedirect = z;
        this.referer = str5;
        this.connectTimeout = i2;
        this.readTimeout = i3;
        this.requestUrl = str;
        this.ioDispatcher = JDCacheConstant.INSTANCE.getIoDispatcher$JDCache_release();
    }

    private final InputStream getErrorStream(String contentEncoding, HttpURLConnection urlConnection) throws IOException {
        InputStream inputStream = urlConnection.getErrorStream();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
        return gzipInputStream(contentEncoding, inputStream);
    }

    private final InputStream getInputStream(String contentEncoding, HttpURLConnection urlConnection) throws IOException {
        InputStream inputStream = urlConnection.getInputStream();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
        return gzipInputStream(contentEncoding, inputStream);
    }

    private final InputStream getServerStream(int responseCode, HttpURLConnection connection) throws IOException {
        String headerField = connection.getHeaderField("Content-Encoding");
        if (responseCode >= 400) {
            return getErrorStream(headerField, connection);
        }
        return getInputStream(headerField, connection);
    }

    private final InputStream gzipInputStream(String contentEncoding, InputStream inputStream) throws IOException {
        return isGzipContent(contentEncoding) ? new GZIPInputStream(inputStream) : inputStream;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x002d A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x002f A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isAllowBody() {
        String str = this.method;
        switch (str.hashCode()) {
            case 79599:
                return str.equals(UrlHelper.METHOD_PUT);
            case 2461856:
                if (str.equals("POST")) {
                }
                break;
            case 75900968:
                if (str.equals(UrlHelper.METHOD_PATCH)) {
                }
                break;
            case 2012838315:
                if (str.equals(UrlHelper.METHOD_DELETE)) {
                }
                break;
        }
    }

    private final boolean isGzipContent(String contentEncoding) {
        boolean contains$default;
        if (contentEncoding != null) {
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) contentEncoding, (CharSequence) "gzip", false, 2, (Object) null);
            return contains$default;
        }
        return false;
    }

    @Nullable
    public final Object connect(@NotNull URL url, @NotNull Continuation<? super NetState<T>> continuation) throws Exception {
        long contentLength;
        URLConnection openConnection = url.openConnection();
        if (openConnection != null) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            this.connection = httpURLConnection;
            httpURLConnection.setConnectTimeout(this.connectTimeout);
            httpURLConnection.setReadTimeout(this.readTimeout);
            httpURLConnection.setInstanceFollowRedirects(this.allowRedirect);
            if (httpURLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                SSLUtils sSLUtils = SSLUtils.INSTANCE;
                httpsURLConnection.setSSLSocketFactory(sSLUtils.defaultSSLSocketFactory());
                httpsURLConnection.setHostnameVerifier(sSLUtils.defaultHostnameVerifier(url));
            }
            httpURLConnection.setRequestMethod(this.method);
            httpURLConnection.setDoInput(true);
            if (isAllowBody()) {
                httpURLConnection.setDoOutput(true);
                writeBody(httpURLConnection);
            }
            Map map = this.header;
            if (map == null) {
                map = new HashMap();
            }
            map.put("Connection", "keep-alive");
            for (String str : map.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) map.get(str));
            }
            String str2 = this.cookies;
            if (str2 != null) {
                httpURLConnection.setRequestProperty("Cookie", str2);
            }
            String str3 = this.userAgent;
            if (str3 != null) {
                httpURLConnection.setRequestProperty("User-Agent", str3);
            }
            String str4 = this.referer;
            if (str4 != null) {
                httpURLConnection.setRequestProperty("Referer", str4);
            }
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307 || responseCode == 308) {
                String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    inputStream.close();
                }
                return new NetState.Redirect(responseCode, httpURLConnection.getHeaderFields(), headerField);
            } else if ((100 > responseCode || 199 < responseCode) && responseCode != 204 && responseCode != 205 && (300 > responseCode || 399 < responseCode)) {
                InputStream serverStream = true ^ Intrinsics.areEqual(this.method, UrlHelper.METHOD_HEAD) ? getServerStream(responseCode, httpURLConnection) : null;
                if (Build.VERSION.SDK_INT >= 24) {
                    contentLength = httpURLConnection.getContentLengthLong();
                } else {
                    contentLength = httpURLConnection.getContentLength();
                }
                return parseData(responseCode, httpURLConnection.getHeaderFields(), contentLength, serverStream, continuation);
            } else {
                return new NetState.Error(responseCode, new Exception("Http Error: " + httpURLConnection.getResponseMessage()));
            }
        }
        throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
    }

    @NotNull
    public final Flow<NetState<T>> connectFlow() {
        return FlowKt.flowOn(FlowKt.m1238catch(FlowKt.onStart(FlowKt.flow(new BaseRequest$connectFlow$1(this, null)), new BaseRequest$connectFlow$2(this, null)), new BaseRequest$connectFlow$3(this, null)), this.ioDispatcher);
    }

    public final void disconnect() {
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog()) {
            jDCacheLog.d(getTAG(), "connection.disconnect() called");
        }
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public final boolean getAllowRedirect() {
        return this.allowRedirect;
    }

    @Nullable
    public final Map<String, String> getBody() {
        return this.body;
    }

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Nullable
    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    @Nullable
    public final String getCookies() {
        return this.cookies;
    }

    @Nullable
    public final Map<String, String> getHeader() {
        return this.header;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final Map<String, String> getParams() {
        return this.params;
    }

    public final int getReadTimeout() {
        return this.readTimeout;
    }

    @Nullable
    public final String getReferer() {
        return this.referer;
    }

    @NotNull
    public final String getRequestUrl() {
        Map<String, String> map = this.params;
        if (map != null && (!map.isEmpty()) == true) {
            Uri.Builder buildUpon = Uri.parse(this.url).buildUpon();
            for (String str : map.keySet()) {
                buildUpon.appendQueryParameter(str, URLEncoder.encode(map.get(str), "UTF-8"));
            }
            String uri = buildUpon.build().toString();
            Intrinsics.checkExpressionValueIsNotNull(uri, "builder.build().toString()");
            return uri;
        }
        return this.url;
    }

    @NotNull
    public abstract String getTAG();

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final String getUserAgent() {
        return this.userAgent;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0039  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object notifyProgress(long j2, long j3, @NotNull Continuation<? super Unit> continuation) {
        BaseRequest$notifyProgress$1 baseRequest$notifyProgress$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof BaseRequest$notifyProgress$1) {
            baseRequest$notifyProgress$1 = (BaseRequest$notifyProgress$1) continuation;
            int i3 = baseRequest$notifyProgress$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                baseRequest$notifyProgress$1.label = i3 - Integer.MIN_VALUE;
                Object obj = baseRequest$notifyProgress$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = baseRequest$notifyProgress$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowCollector<? super NetState<T>> flowCollector = this.flowCollector;
                    if (flowCollector != null) {
                        NetState.OnProgress onProgress = new NetState.OnProgress(j2, j3);
                        baseRequest$notifyProgress$1.L$0 = this;
                        baseRequest$notifyProgress$1.J$0 = j2;
                        baseRequest$notifyProgress$1.J$1 = j3;
                        baseRequest$notifyProgress$1.label = 1;
                        if (flowCollector.emit(onProgress, baseRequest$notifyProgress$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    long j4 = baseRequest$notifyProgress$1.J$1;
                    long j5 = baseRequest$notifyProgress$1.J$0;
                    BaseRequest baseRequest = (BaseRequest) baseRequest$notifyProgress$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        baseRequest$notifyProgress$1 = new BaseRequest$notifyProgress$1(this, continuation);
        Object obj2 = baseRequest$notifyProgress$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = baseRequest$notifyProgress$1.label;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }

    @Nullable
    protected abstract Object parseData(int i2, @Nullable Map<String, ? extends List<String>> map, long j2, @Nullable InputStream inputStream, @NotNull Continuation<? super NetState<T>> continuation);

    public final void setAllowRedirect(boolean z) {
        this.allowRedirect = z;
    }

    public final void setBody(@Nullable Map<String, String> map) {
        this.body = map;
    }

    public final void setConnectTimeout(int i2) {
        this.connectTimeout = i2;
    }

    public final void setConnection(@Nullable HttpURLConnection httpURLConnection) {
        this.connection = httpURLConnection;
    }

    public final void setCookies(@Nullable String str) {
        this.cookies = str;
    }

    public final void setHeader(@Nullable Map<String, String> map) {
        this.header = map;
    }

    public final void setMethod(@NotNull String str) {
        this.method = str;
    }

    public final void setParams(@Nullable Map<String, String> map) {
        this.params = map;
    }

    public final void setReadTimeout(int i2) {
        this.readTimeout = i2;
    }

    public final void setReferer(@Nullable String str) {
        this.referer = str;
    }

    public final void setUrl(@NotNull String str) {
        this.url = str;
    }

    public final void setUserAgent(@Nullable String str) {
        this.userAgent = str;
    }

    protected final void writeBody(@NotNull HttpURLConnection connection) throws IOException {
        Map<String, String> map = this.body;
        if (map == null || (!map.isEmpty()) != true) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(map.get(str), "UTF-8"));
        }
        sb.deleteCharAt(0);
        OutputStream outputStream = connection.getOutputStream();
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        Charset charset = Charsets.UTF_8;
        if (sb2 != null) {
            byte[] bytes = sb2.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public /* synthetic */ BaseRequest(String str, String str2, String str3, String str4, Map map, Map map2, Map map3, boolean z, String str5, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i4 & 2) != 0 ? "GET" : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? null : str4, (i4 & 16) != 0 ? null : map, (i4 & 32) != 0 ? null : map2, (i4 & 64) != 0 ? null : map3, (i4 & 128) != 0 ? true : z, (i4 & 256) == 0 ? str5 : null, (i4 & 512) != 0 ? 5000 : i2, (i4 & 1024) == 0 ? i3 : 5000);
    }
}
