package com.laser.open.nfc.model.http;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.laser.open.nfc.model.http.HttpLoggingInterceptor;
import com.laser.utils.common.LogUtil;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes12.dex */
public class OkHttpUtil implements HttpLoggingInterceptor.Logger {
    private static final boolean DEPENDENCY_OKHTTP;
    protected static final MediaType MEDIA_TYPE_JSON = MediaType.parse("text/html; charset=utf-8");
    private static OkHttpClient mClientInstance;
    private static OkHttpUtil mOkHttpHelperInstance;
    protected HttpLoggingInterceptor mHttpLogInterceptor;
    private final String TAG = OkHttpUtil.class.getSimpleName();
    private SSLContext sslContext = null;
    private final int TIMEOUT = 30;
    private X509TrustManager xtm = new b();

    /* loaded from: classes12.dex */
    public class a implements HostnameVerifier {
        final /* synthetic */ String a;

        a(String str) {
            OkHttpUtil.this = r1;
            this.a = str;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            if (str.equals(this.a)) {
                return true;
            }
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.a, sSLSession);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements X509TrustManager {
        b() {
            OkHttpUtil.this = r1;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            LogUtil.d(OkHttpUtil.this.TAG, "checkClientTrusted");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            LogUtil.d(OkHttpUtil.this.TAG, "checkServerTrusted");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Interceptor {
        private c() {
            OkHttpUtil.this = r1;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            String a = com.laser.open.nfc.c.b.c().a();
            LogUtil.d(OkHttpUtil.this.TAG, "headerInfo: " + a);
            return chain.proceed(request.newBuilder().addHeader(HttpHeaders.CONTENT_TYPE, OkHttpUtil.MEDIA_TYPE_JSON.toString()).addHeader("baseInfo", a).build());
        }

        /* synthetic */ c(OkHttpUtil okHttpUtil, a aVar) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Interceptor {
        public int a;
        private int b = 0;

        d(int i2) {
            OkHttpUtil.this = r1;
            this.a = i2;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            while (!proceed.isSuccessful() && this.b < this.a) {
                LogUtil.e(OkHttpUtil.this.TAG, "retryNum=" + this.b);
                this.b = this.b + 1;
                proceed = chain.proceed(request);
            }
            return proceed;
        }
    }

    static {
        boolean z;
        try {
            Class.forName("okhttp3.OkHttpClient");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        DEPENDENCY_OKHTTP = z;
    }

    private OkHttpUtil() {
    }

    public static OkHttpUtil getInstance() {
        if (mOkHttpHelperInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (mOkHttpHelperInstance == null) {
                    mOkHttpHelperInstance = new OkHttpUtil();
                }
            }
        }
        return mOkHttpHelperInstance;
    }

    public void init(String str) {
        if (DEPENDENCY_OKHTTP) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(this);
            this.mHttpLogInterceptor = httpLoggingInterceptor;
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            a aVar = null;
            try {
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                this.sslContext = sSLContext;
                sSLContext.init(null, new TrustManager[]{this.xtm}, new SecureRandom());
            } catch (KeyManagementException | NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            mClientInstance = builder.readTimeout(30L, timeUnit).connectTimeout(30L, timeUnit).addInterceptor(this.mHttpLogInterceptor).addInterceptor(new c(this, aVar)).addInterceptor(new d(1)).sslSocketFactory(this.sslContext.getSocketFactory(), this.xtm).hostnameVerifier(new a(str)).build();
            return;
        }
        throw new IllegalStateException("Must be dependency Okhttp");
    }

    @Override // com.laser.open.nfc.model.http.HttpLoggingInterceptor.Logger
    public void log(String str) {
        LogUtil.d(this.TAG, "log: " + str);
    }

    public Response postSync(String str, String str2) throws IOException {
        RequestBody create;
        if (TextUtils.isEmpty(str2)) {
            create = RequestBody.create((MediaType) null, "");
        } else {
            create = RequestBody.create(MEDIA_TYPE_JSON, str2);
        }
        return mClientInstance.newCall(new Request.Builder().post(create).url(str).build()).execute();
    }

    public Response postSync(String str) throws IOException {
        return postSync(str, null);
    }
}
