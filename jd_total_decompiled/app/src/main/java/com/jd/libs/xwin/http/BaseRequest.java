package com.jd.libs.xwin.http;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.jdcache.util.UrlHelper;
import i.a.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes16.dex */
public abstract class BaseRequest implements Runnable {
    public static final String HEAD_KEY_CONNECTION = "Connection";
    public static final String HEAD_KEY_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEAD_VALUE_CONNECTION_CLOSE = "close";
    public static final String HEAD_VALUE_CONNECTION_KEEP_ALIVE = "keep-alive";
    public static final int METHOD_DELETE = 260;
    public static final int METHOD_GET = 257;
    public static final int METHOD_HEAD = 261;
    public static final int METHOD_OPTIONS = 263;
    public static final int METHOD_PATCH = 262;
    public static final int METHOD_POST = 258;
    public static final int METHOD_PUT = 259;
    public static final int METHOD_TRACE = 264;
    public Map<String, String> mBody;
    public boolean mColorSign;
    public HttpURLConnection mConnection;
    public String mCookies;
    public Map<String, String> mHeader;
    public Map<String, String> mParams;
    public String mReferer;
    public String mRequestUrl;
    public String mUserAgent;
    public boolean mAllowRedirect = false;
    public int mMethod = 257;
    public int mConnectTimeout = 5000;
    public int mReadTimeout = 5000;
    public boolean isEncodeGetParam = true;

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Method {
    }

    public BaseRequest(String str) {
        this.mRequestUrl = str;
    }

    private boolean allowRequestBody() {
        switch (this.mMethod) {
            case 258:
            case 259:
            case 260:
            case 262:
                return true;
            case 261:
            default:
                return false;
        }
    }

    private void connect(URL url) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        this.mConnection = httpURLConnection;
        httpURLConnection.setConnectTimeout(this.mConnectTimeout);
        httpURLConnection.setReadTimeout(this.mReadTimeout);
        httpURLConnection.setInstanceFollowRedirects(this.mAllowRedirect);
        if (httpURLConnection instanceof HttpsURLConnection) {
            SSLSocketFactory b = e.b();
            if (b != null) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(b);
            }
            HostnameVerifier a = e.a();
            if (a != null) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(a);
            }
        }
        httpURLConnection.setRequestMethod(getRequestMethod());
        httpURLConnection.setDoInput(true);
        if (this.mHeader == null) {
            this.mHeader = new HashMap();
        }
        this.mHeader.put("Connection", Build.VERSION.SDK_INT > 19 ? "keep-alive" : "close");
        for (String str : this.mHeader.keySet()) {
            httpURLConnection.setRequestProperty(str, this.mHeader.get(str));
        }
        String str2 = this.mCookies;
        if (str2 != null) {
            httpURLConnection.setRequestProperty("Cookie", str2);
        }
        String str3 = this.mUserAgent;
        if (str3 != null) {
            httpURLConnection.setRequestProperty("User-Agent", str3);
        }
        String str4 = this.mReferer;
        if (str4 != null) {
            httpURLConnection.setRequestProperty("Referer", str4);
        }
        if (isAllowBody()) {
            httpURLConnection.setDoOutput(true);
            writeBody(httpURLConnection);
        }
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307 || responseCode == 308) {
            onRedirect(responseCode, httpURLConnection.getHeaderFields(), httpURLConnection.getHeaderField(HttpHeaders.LOCATION));
            InputStream inputStream = httpURLConnection.getInputStream();
            if (inputStream != null) {
                inputStream.close();
            }
        } else if ((100 > responseCode || responseCode >= 200) && responseCode != 204 && responseCode != 205) {
            if (300 > responseCode || responseCode >= 400) {
                onSuccess(responseCode, httpURLConnection.getHeaderFields(), httpURLConnection.getContentLength(), this.mMethod != 261 ? getServerStream(responseCode, httpURLConnection) : null);
            }
        }
    }

    private InputStream getErrorStream(String str, HttpURLConnection httpURLConnection) throws IOException {
        return gzipInputStream(str, httpURLConnection.getErrorStream());
    }

    private InputStream getInputStream(String str, HttpURLConnection httpURLConnection) throws IOException {
        return gzipInputStream(str, httpURLConnection.getInputStream());
    }

    private String getRequestMethod() {
        switch (this.mMethod) {
            case 258:
                return "POST";
            case 259:
                return UrlHelper.METHOD_PUT;
            case 260:
                return UrlHelper.METHOD_DELETE;
            case 261:
                return UrlHelper.METHOD_HEAD;
            case 262:
                return UrlHelper.METHOD_PATCH;
            case 263:
                return UrlHelper.METHOD_OPTIONS;
            case 264:
                return UrlHelper.METHOD_TRACE;
            default:
                return "GET";
        }
    }

    private InputStream getServerStream(int i2, HttpURLConnection httpURLConnection) throws IOException {
        String headerField = httpURLConnection.getHeaderField("Content-Encoding");
        return i2 >= 400 ? getErrorStream(headerField, httpURLConnection) : getInputStream(headerField, httpURLConnection);
    }

    private InputStream gzipInputStream(String str, InputStream inputStream) throws IOException {
        return isGzipContent(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    private boolean isAllowBody() {
        boolean allowRequestBody = allowRequestBody();
        return Build.VERSION.SDK_INT < 21 ? allowRequestBody && this.mMethod != 260 : allowRequestBody;
    }

    public void addHeader(String str, String str2) {
        if (this.mHeader == null) {
            this.mHeader = new HashMap();
        }
        this.mHeader.put(str, str2);
    }

    public void disconnect() {
        HttpURLConnection httpURLConnection = this.mConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public String getUrl() {
        Map<String, String> map = this.mParams;
        if (map == null || map.size() <= 0) {
            return this.mRequestUrl;
        }
        Uri.Builder buildUpon = Uri.parse(this.mRequestUrl).buildUpon();
        for (String str : this.mParams.keySet()) {
            buildUpon.appendQueryParameter(str, this.isEncodeGetParam ? URLEncoder.encode(this.mParams.get(str)) : this.mParams.get(str));
        }
        return buildUpon.build().toString();
    }

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public boolean isGzipContent(String str) {
        return str != null && str.contains("gzip");
    }

    public abstract void onError(int i2, String str);

    public void onRedirect(int i2, Map<String, List<String>> map, @Nullable String str) {
        onError(i2, "redirect");
    }

    public abstract void onStart();

    public abstract void onSuccess(int i2, Map<String, List<String>> map, int i3, @Nullable InputStream inputStream);

    @Override // java.lang.Runnable
    public void run() {
        try {
            onStart();
            connect(new URL(getUrl()));
        } catch (Exception e2) {
            e2.printStackTrace();
            onError(-1, e2.getMessage());
        }
    }

    public void setAllowRedirect(boolean z) {
        this.mAllowRedirect = z;
    }

    public void setBody(Map<String, String> map) {
        this.mBody = map;
    }

    public void setColorSign(boolean z) {
        this.mColorSign = z;
    }

    public void setConnectTimeout(int i2) {
        this.mConnectTimeout = i2;
    }

    public void setCookies(String str) {
        this.mCookies = str;
    }

    public void setHeader(Map<String, String> map) {
        this.mHeader = map;
    }

    public void setMethod(int i2) {
        this.mMethod = i2;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public void setReadTimeout(int i2) {
        this.mReadTimeout = i2;
    }

    public void setReferer(String str) {
        this.mReferer = str;
    }

    public void setUserAgent(String str) {
        this.mUserAgent = str;
    }

    public void writeBody(HttpURLConnection httpURLConnection) throws IOException {
        Map<String, String> map = this.mBody;
        if (map == null || map.size() <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.mBody.keySet()) {
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(this.mBody.get(str)));
        }
        sb.deleteCharAt(0);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
