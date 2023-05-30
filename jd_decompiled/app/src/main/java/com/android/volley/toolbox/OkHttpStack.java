package com.android.volley.toolbox;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.b.a.a;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.utils.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* loaded from: classes.dex */
public class OkHttpStack implements HttpStack {
    public static final String TAG = "OkHttpStack";
    private OkHttpClient mOkHttpClientForDownload;
    private OkHttpClient mOkHttpClientForRequest;

    /* loaded from: classes.dex */
    public static class DNS4Download implements Dns {
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            InetAddress ip2InetAddress;
            ArrayList arrayList = new ArrayList();
            try {
                boolean z = VolleyLog.DEBUG;
                if (RuntimeConfigHelper.downloadUseHttpDns()) {
                    boolean z2 = VolleyLog.DEBUG;
                    IpModel ipModelByHost = JDHttpTookit.getEngine().getHttpDnsControllerImpl().getIpModelByHost(str, false);
                    if (ipModelByHost != null) {
                        String master = ipModelByHost.getMaster();
                        if (!TextUtils.isEmpty(master) && (ip2InetAddress = HttpUtils.ip2InetAddress(str, master)) != null) {
                            arrayList.add(ip2InetAddress);
                        }
                        if (ipModelByHost.getV4Backup() != null && ipModelByHost.getV4Backup().length > 0) {
                            for (String str2 : ipModelByHost.getV4Backup()) {
                                InetAddress ip2InetAddress2 = HttpUtils.ip2InetAddress(str, str2);
                                if (ip2InetAddress2 != null) {
                                    arrayList.add(ip2InetAddress2);
                                }
                            }
                        }
                        if (ipModelByHost.getV6Backup() != null && ipModelByHost.getV6Backup().length > 0) {
                            for (String str3 : ipModelByHost.getV6Backup()) {
                                InetAddress ip2InetAddress3 = HttpUtils.ip2InetAddress(str, str3);
                                if (ip2InetAddress3 != null) {
                                    arrayList.add(ip2InetAddress3);
                                }
                            }
                        }
                        if (VolleyLog.DEBUG) {
                            String str4 = "host: " + str + " , master: " + master + " , address: " + arrayList;
                        }
                    } else {
                        boolean z3 = VolleyLog.DEBUG;
                    }
                } else {
                    boolean z4 = VolleyLog.DEBUG;
                }
            } catch (Throwable unused) {
            }
            if (arrayList.isEmpty()) {
                arrayList.addAll(Dns.SYSTEM.lookup(str));
                if (VolleyLog.DEBUG) {
                    String str5 = "\u4f7f\u7528LocalDns\u89e3\u6790\u5230\u5730\u5740 " + arrayList;
                }
            }
            return arrayList;
        }
    }

    public OkHttpStack() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient.Builder pingInterval = new OkHttpClient.Builder().connectTimeout(getConnectTimeout(), timeUnit).readTimeout(getReadTimeout(), timeUnit).retryOnConnectionFailure(false).addInterceptor(new a(VolleyLog.DEBUG)).addInterceptor(new com.jingdong.app.mall.h.b.a()).pingInterval(Final.HALF_MINUTE, timeUnit);
        if (JDHttpTookit.getEngine().getNetworkInterceptors() != null && !JDHttpTookit.getEngine().getNetworkInterceptors().isEmpty()) {
            Iterator<Interceptor> it = JDHttpTookit.getEngine().getNetworkInterceptors().iterator();
            while (it.hasNext()) {
                pingInterval.addNetworkInterceptor(it.next());
            }
        }
        this.mOkHttpClientForRequest = pingInterval.build();
        TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
        this.mOkHttpClientForDownload = new OkHttpClient.Builder().connectTimeout(getConnectTimeout(), timeUnit2).readTimeout(getReadTimeout(), timeUnit2).followRedirects(false).followSslRedirects(false).dns(new DNS4Download()).build();
    }

    private RequestBody createRequestBody(Request<?> request) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body == null) {
            return null;
        }
        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }

    private HttpEntity entityFromResponse(Response response) throws IOException {
        InputStream inputStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        String header = response.header("Content-Encoding");
        ResponseBody body = response.body();
        try {
            if ("gzip".equals(header)) {
                inputStream = new GZIPInputStream(body.byteStream());
            } else {
                inputStream = body.byteStream();
            }
        } catch (IOException unused) {
            inputStream = null;
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength(body.contentLength());
        if (body.contentType() != null) {
            if (body.contentType().charset() != null) {
                basicHttpEntity.setContentEncoding(body.contentType().charset().name());
            }
            basicHttpEntity.setContentType(body.contentType().type() + "/" + body.contentType().subtype());
        }
        return basicHttpEntity;
    }

    private int getConnectTimeout() {
        try {
            int onlineConnectTimeout = RuntimeConfigHelper.getOnlineConnectTimeout();
            if (onlineConnectTimeout > 0) {
                return onlineConnectTimeout;
            }
            return 10000;
        } catch (Throwable unused) {
            return 10000;
        }
    }

    private int getReadTimeout() {
        try {
            int onlineReadTimeout = RuntimeConfigHelper.getOnlineReadTimeout();
            if (onlineReadTimeout > 0) {
                return onlineReadTimeout;
            }
            return 10000;
        } catch (Throwable unused) {
            return 10000;
        }
    }

    private Response getResponse(String str, OkHttpClient okHttpClient, Map<String, String> map) throws Exception {
        Request.Builder url = new Request.Builder().url(str);
        for (String str2 : map.keySet()) {
            url.addHeader(str2, map.get(str2));
            if (VolleyLog.DEBUG) {
                String str3 = "key:" + str2 + ":" + map.get(str2);
            }
        }
        url.cacheControl(CacheControl.FORCE_NETWORK);
        return okHttpClient.newCall(url.build()).execute();
    }

    private void setConnectionParametersForRequest(Request.Builder builder, com.android.volley.Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getBodyContentType()), postBody));
                    return;
                }
                return;
            case 0:
                builder.get();
                return;
            case 1:
                builder.post(createRequestBody(request));
                return;
            case 2:
                builder.put(createRequestBody(request));
                return;
            case 3:
                builder.delete();
                return;
            case 4:
                builder.head();
                return;
            case 5:
                builder.method(UrlHelper.METHOD_OPTIONS, null);
                return;
            case 6:
                builder.method(UrlHelper.METHOD_TRACE, null);
                return;
            case 7:
                builder.patch(createRequestBody(request));
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    public Response performFileRequest(JDFileRequest jDFileRequest, Map<String, String> map) throws Exception {
        OkHttpClient okHttpClient = this.mOkHttpClientForDownload;
        String url = jDFileRequest.getUrl();
        if (VolleyLog.DEBUG) {
            String str = "id:" + jDFileRequest.getSequence() + ",ConnectionUrl:" + url;
        }
        Response response = getResponse(url, okHttpClient, map);
        int i2 = 0;
        if (VolleyLog.DEBUG) {
            String str2 = "responseCode:" + response.code();
        }
        while (response.code() / 100 == 3 && i2 < 5 && !jDFileRequest.isIgnoreRedirect()) {
            String str3 = response.headers().get(HttpHeaders.LOCATION);
            new URL(str3);
            if (VolleyLog.DEBUG) {
                String str4 = "redirectUrl:" + str3;
            }
            response = getResponse(str3, okHttpClient, map);
            i2++;
        }
        if (VolleyLog.DEBUG) {
            String str5 = "responseCode:" + response.code();
        }
        if (i2 >= 5 || !(response.code() == 200 || response.code() == 206)) {
            if (i2 >= 5) {
                throw new JDError("Too many redirects!");
            }
            throw new JDError("error ResponseCode\uff1a" + response.code());
        }
        return response;
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(com.android.volley.Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        OkHttpClient okHttpClient = this.mOkHttpClientForRequest;
        String url = request.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        hashMap.putAll(request.getHeaders());
        if (VolleyLog.DEBUG) {
            String str = "id:" + request.getSequence() + ",ConnectionUrl:" + url;
        }
        Request.Builder url2 = new Request.Builder().url(url);
        for (String str2 : hashMap.keySet()) {
            url2.addHeader(str2, (String) hashMap.get(str2));
            if (VolleyLog.DEBUG) {
                String str3 = str2 + ":" + ((String) hashMap.get(str2));
            }
        }
        setConnectionParametersForRequest(url2, request);
        Call newCall = okHttpClient.newCall(url2.cacheControl(CacheControl.FORCE_NETWORK).build());
        if (request.getCallTimeoutMs() > 0) {
            if (VolleyLog.DEBUG) {
                String str4 = "call timeout(Ms) : " + request.getCallTimeoutMs();
            }
            newCall.timeout().timeout(request.getCallTimeoutMs(), TimeUnit.MILLISECONDS);
        }
        Response execute = newCall.execute();
        ProtocolVersion protocolVersion = null;
        if (execute.protocol().compareTo(Protocol.HTTP_1_1) == 0) {
            protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        } else if (execute.protocol().compareTo(Protocol.HTTP_1_0) == 0) {
            protocolVersion = new ProtocolVersion("HTTP", 1, 0);
        } else if (execute.protocol().compareTo(Protocol.SPDY_3) == 0) {
            protocolVersion = new ProtocolVersion("SPDY", 3, 1);
        } else if (execute.protocol().compareTo(Protocol.HTTP_2) == 0) {
            protocolVersion = new ProtocolVersion("HTTP", 2, 0);
        }
        if (VolleyLog.DEBUG) {
            String str5 = "protocal Version : " + protocolVersion.toString();
        }
        if (execute.code() != -1) {
            BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, execute.code(), execute.message()));
            basicHttpResponse.setEntity(entityFromResponse(execute));
            Headers headers = execute.headers();
            for (String str6 : headers.names()) {
                basicHttpResponse.addHeader(new BasicHeader(str6, headers.get(str6)));
            }
            return basicHttpResponse;
        }
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }

    public Response getResponse(JDFileRequest jDFileRequest) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.putAll(jDFileRequest.getHeader());
        if (!jDFileRequest.isIgnoreCharset()) {
            hashMap.put("Charset", "UTF-8");
        }
        if (!hashMap.containsKey("Connection")) {
            hashMap.put("Connection", "close");
        }
        if (jDFileRequest.isBreakpointTransmission()) {
            hashMap.put(HttpHeaders.RANGE, "bytes=" + jDFileRequest.getStartPosBreakpointTransmission() + "-");
            hashMap.put(HttpHeaders.ACCEPT_ENCODING, "");
        } else if (!hashMap.containsKey(HttpHeaders.ACCEPT_ENCODING)) {
            hashMap.put(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        }
        return performFileRequest(jDFileRequest, hashMap);
    }
}
