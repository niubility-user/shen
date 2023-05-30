package com.jd.framework.network.toolbox;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes13.dex */
public final class AndroidHttpClient implements HttpClient {
    private static final ThreadLocal<Boolean> sThreadBlocked = new ThreadLocal<>();
    private static final HttpRequestInterceptor sThreadCheckInterceptor = new HttpRequestInterceptor() { // from class: com.jd.framework.network.toolbox.AndroidHttpClient.1
        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            if (Boolean.TRUE.equals(AndroidHttpClient.sThreadBlocked.get())) {
                throw new RuntimeException("This thread forbids HTTP requests");
            }
        }
    };
    private final HttpClient delegate;

    private AndroidHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.delegate = new DefaultHttpClient(clientConnectionManager, httpParams) { // from class: com.jd.framework.network.toolbox.AndroidHttpClient.2
            protected HttpContext createHttpContext() {
                BasicHttpContext basicHttpContext = new BasicHttpContext();
                basicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
                basicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
                basicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
                return basicHttpContext;
            }

            protected BasicHttpProcessor createHttpProcessor() {
                BasicHttpProcessor createHttpProcessor = super.createHttpProcessor();
                createHttpProcessor.addRequestInterceptor(AndroidHttpClient.sThreadCheckInterceptor);
                return createHttpProcessor;
            }
        };
    }

    public static AndroidHttpClient newInstance(String str) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        return new AndroidHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    public void close() {
        getConnectionManager().shutdown();
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException {
        return this.delegate.execute(httpUriRequest);
    }

    public ClientConnectionManager getConnectionManager() {
        return this.delegate.getConnectionManager();
    }

    public HttpParams getParams() {
        return this.delegate.getParams();
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        return this.delegate.execute(httpUriRequest, httpContext);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return this.delegate.execute(httpHost, httpRequest);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        return this.delegate.execute(httpHost, httpRequest, httpContext);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) this.delegate.execute(httpUriRequest, responseHandler);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        return (T) this.delegate.execute(httpUriRequest, responseHandler, httpContext);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) this.delegate.execute(httpHost, httpRequest, responseHandler);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        return (T) this.delegate.execute(httpHost, httpRequest, responseHandler, httpContext);
    }
}
