package com.android.volley.toolbox;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.net.HttpURLConnection getFileUrlConnection(com.jd.framework.network.request.JDFileRequest r6) throws java.lang.Exception {
        /*
            r5 = this;
            boolean r0 = r6.isBreakpointTransmission()
            java.net.HttpURLConnection r0 = r5.createConnection(r6, r0)
            boolean r1 = com.android.volley.VolleyLog.DEBUG
            if (r1 == 0) goto L20
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ResponseCode:"
            r1.append(r2)
            int r2 = r0.getResponseCode()
            r1.append(r2)
            r1.toString()
        L20:
            r1 = 0
        L21:
            int r2 = r0.getResponseCode()
            int r2 = r2 / 100
            r3 = 3
            r4 = 5
            if (r2 != r3) goto L5c
            if (r1 >= r4) goto L5c
            boolean r2 = r6.isIgnoreRedirect()
            if (r2 != 0) goto L5c
            java.lang.String r2 = "Location"
            java.lang.String r0 = r0.getHeaderField(r2)
            boolean r2 = com.android.volley.VolleyLog.DEBUG
            if (r2 == 0) goto L4e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "redirectUrl:"
            r2.append(r3)
            r2.append(r0)
            r2.toString()
        L4e:
            r6.setUrl(r0)
            boolean r0 = r6.isBreakpointTransmission()
            java.net.HttpURLConnection r0 = r5.createConnection(r6, r0)
            int r1 = r1 + 1
            goto L21
        L5c:
            if (r1 >= r4) goto L70
            int r2 = r0.getResponseCode()
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L6f
            int r2 = r0.getResponseCode()
            r3 = 206(0xce, float:2.89E-43)
            if (r2 == r3) goto L6f
            goto L70
        L6f:
            return r0
        L70:
            boolean r2 = com.android.volley.VolleyLog.DEBUG
            if (r2 == 0) goto L95
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "requestID:"
            r2.append(r3)
            int r6 = r6.getSequence()
            r2.append(r6)
            java.lang.String r6 = ",\u4e0b\u8f7d\u5931\u8d25\uff0cResponseCode:"
            r2.append(r6)
            int r6 = r0.getResponseCode()
            r2.append(r6)
            r2.toString()
        L95:
            if (r1 < r4) goto L9f
            com.jd.framework.network.error.JDError r6 = new com.jd.framework.network.error.JDError
            java.lang.String r1 = "Too many redirects!"
            r6.<init>(r1)
            goto Lb9
        L9f:
            com.jd.framework.network.error.JDError r6 = new com.jd.framework.network.error.JDError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "error ResponseCode\uff1a"
            r1.append(r2)
            int r2 = r0.getResponseCode()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.<init>(r1)
        Lb9:
            r0.disconnect()
            goto Lbe
        Lbd:
            throw r6
        Lbe:
            goto Lbd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.HurlStack.getFileUrlConnection(com.jd.framework.network.request.JDFileRequest):java.net.HttpURLConnection");
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
