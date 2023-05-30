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
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* loaded from: classes.dex */
public class HurlStack implements HttpStack {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String TAG = "HurlStack";
    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;

    /* loaded from: classes.dex */
    public interface UrlRewriter {
        String rewriteUrl(String str);
    }

    public HurlStack() {
        this(null);
    }

    private static void addBodyIfExists(HttpURLConnection httpURLConnection, Request<?> request, long j2) throws IOException, AuthFailureError {
        byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", request.getBodyContentType());
            httpURLConnection.connect();
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }

    private static HttpEntity entityFromConnection(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            if ("gzip".equals(httpURLConnection.getHeaderField("Content-Encoding"))) {
                errorStream = new GZIPInputStream(httpURLConnection.getInputStream());
            } else {
                errorStream = httpURLConnection.getInputStream();
            }
        } catch (IOException unused) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength(httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    private static boolean hasResponseBody(int i2, int i3) {
        return (i2 == 4 || (100 <= i3 && i3 < 200) || i3 == 204 || i3 == 304) ? false : true;
    }

    private HttpURLConnection openConnection(URL url, Request<?> request, final String str) throws IOException {
        HttpURLConnection createConnection = createConnection(url);
        if (VolleyLog.DEBUG) {
            String str2 = "Request : " + url + "\n connectTimeout : " + request.getConnectTimeoutMs() + "\n readTimeout : " + request.getReadTimeoutMs();
        }
        createConnection.setConnectTimeout(request.getConnectTimeoutMs());
        createConnection.setReadTimeout(request.getReadTimeoutMs());
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            SSLSocketFactory sSLSocketFactory = this.mSslSocketFactory;
            if (sSLSocketFactory != null) {
                ((HttpsURLConnection) createConnection).setSSLSocketFactory(sSLSocketFactory);
            }
            ((HttpsURLConnection) createConnection).setHostnameVerifier(new HostnameVerifier() { // from class: com.android.volley.toolbox.HurlStack.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str3, SSLSession sSLSession) {
                    if (!TextUtils.isEmpty(str)) {
                        str3 = str;
                    }
                    return HttpsURLConnection.getDefaultHostnameVerifier().verify(str3, sSLSession);
                }
            });
        }
        return createConnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request<?> request, long j2) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                addBodyIfExists(httpURLConnection, request, j2);
                return;
            case 2:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_PUT);
                addBodyIfExists(httpURLConnection, request, j2);
                return;
            case 3:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_DELETE);
                return;
            case 4:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_HEAD);
                return;
            case 5:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_OPTIONS);
                return;
            case 6:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_TRACE);
                return;
            case 7:
                httpURLConnection.setRequestMethod(UrlHelper.METHOD_PATCH);
                addBodyIfExists(httpURLConnection, request, j2);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0064, code lost:
        if (r0.getResponseCode() == 200) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006c, code lost:
        if (r0.getResponseCode() == 206) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006f, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpURLConnection getFileUrlConnection(JDFileRequest jDFileRequest) throws Exception {
        JDError jDError;
        HttpURLConnection createConnection = createConnection(jDFileRequest, jDFileRequest.isBreakpointTransmission());
        if (VolleyLog.DEBUG) {
            String str = "ResponseCode:" + createConnection.getResponseCode();
        }
        int i2 = 0;
        while (createConnection.getResponseCode() / 100 == 3 && i2 < 5 && !jDFileRequest.isIgnoreRedirect()) {
            String headerField = createConnection.getHeaderField(HttpHeaders.LOCATION);
            if (VolleyLog.DEBUG) {
                String str2 = "redirectUrl:" + headerField;
            }
            jDFileRequest.setUrl(headerField);
            createConnection = createConnection(jDFileRequest, jDFileRequest.isBreakpointTransmission());
            i2++;
        }
        if (VolleyLog.DEBUG) {
            String str3 = "requestID:" + jDFileRequest.getSequence() + ",\u4e0b\u8f7d\u5931\u8d25\uff0cResponseCode:" + createConnection.getResponseCode();
        }
        if (i2 >= 5) {
            jDError = new JDError("Too many redirects!");
        } else {
            jDError = new JDError("error ResponseCode\uff1a" + createConnection.getResponseCode());
        }
        createConnection.disconnect();
        throw jDError;
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        String url = request.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        hashMap.putAll(request.getHeaders());
        String str = (String) hashMap.get(HttpHeaders.ACCEPT_ENCODING);
        if (!TextUtils.isEmpty(str) && str.contains(BrightRemindSetting.BRIGHT_REMIND)) {
            hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
        }
        UrlRewriter urlRewriter = this.mUrlRewriter;
        if (urlRewriter != null) {
            String rewriteUrl = urlRewriter.rewriteUrl(url);
            if (rewriteUrl == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
            url = rewriteUrl;
        }
        if (VolleyLog.DEBUG) {
            String str2 = "id:" + request.getSequence() + ",ConnectionUrl:" + url;
        }
        URL url2 = new URL(url);
        long currentTimeMillis = System.currentTimeMillis();
        HttpURLConnection openConnection = openConnection(url2, request, (String) hashMap.get("host"));
        for (String str3 : hashMap.keySet()) {
            openConnection.addRequestProperty(str3, (String) hashMap.get(str3));
        }
        setConnectionParametersForRequest(openConnection, request, currentTimeMillis);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (openConnection.getResponseCode() != -1) {
            BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, openConnection.getResponseCode(), openConnection.getResponseMessage());
            BasicHttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
            if (hasResponseBody(request.getMethod(), basicStatusLine.getStatusCode())) {
                basicHttpResponse.setEntity(entityFromConnection(openConnection));
            }
            for (Map.Entry<String, List<String>> entry : openConnection.getHeaderFields().entrySet()) {
                if (entry.getKey() != null) {
                    basicHttpResponse.addHeader(new BasicHeader(entry.getKey(), entry.getValue().get(0)));
                }
            }
            return basicHttpResponse;
        }
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }

    public HurlStack(UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }

    public HurlStack(UrlRewriter urlRewriter, SSLSocketFactory sSLSocketFactory) {
        this.mUrlRewriter = urlRewriter;
        this.mSslSocketFactory = sSLSocketFactory;
    }

    private HttpURLConnection createConnection(JDFileRequest jDFileRequest, boolean z) throws Exception {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(jDFileRequest.getUrl()).openConnection();
            httpURLConnection.setConnectTimeout(jDFileRequest.getConnectTimeoutMs());
            httpURLConnection.setReadTimeout(jDFileRequest.getReadTimeoutMs());
            Map<String, String> header = jDFileRequest.getHeader();
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (!jDFileRequest.isIgnoreCharset()) {
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
            }
            if (TextUtils.isEmpty(httpURLConnection.getRequestProperty("Connection"))) {
                httpURLConnection.setRequestProperty("Connection", "close");
            }
            httpURLConnection.setInstanceFollowRedirects(false);
            if (z) {
                httpURLConnection.setRequestProperty(HttpHeaders.RANGE, "bytes=" + jDFileRequest.getStartPosBreakpointTransmission() + "-");
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "");
            } else if (TextUtils.isEmpty(httpURLConnection.getRequestProperty(HttpHeaders.ACCEPT_ENCODING))) {
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            }
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            return httpURLConnection;
        } catch (Exception e2) {
            if (VolleyLog.DEBUG) {
                e2.printStackTrace();
            }
            throw e2;
        }
    }
}
