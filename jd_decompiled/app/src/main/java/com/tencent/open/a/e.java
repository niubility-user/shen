package com.tencent.open.a;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.open.log.SLog;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class e implements com.tencent.open.a.a {
    private OkHttpClient a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class a implements Interceptor {
        private final String a;

        public a(String str) {
            this.a = str;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder().header("User-Agent", this.a).build());
        }
    }

    public e(String str) throws NoClassDefFoundError {
        a(str);
    }

    private void a(String str) {
        String userAgent = Version.userAgent();
        if (userAgent != null && userAgent.startsWith("okhttp/3")) {
            a aVar = new a(str);
            OkHttpClient.Builder connectionSpecs = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS));
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            OkHttpClient.Builder addInterceptor = connectionSpecs.connectTimeout(15000L, timeUnit).readTimeout(Final.HALF_MINUTE, timeUnit).writeTimeout(Final.HALF_MINUTE, timeUnit).cache(null).addInterceptor(aVar);
            a(addInterceptor);
            this.a = addInterceptor.build();
            return;
        }
        throw new NoClassDefFoundError();
    }

    @Override // com.tencent.open.a.a
    public void a(long j2, long j3) {
        if (j2 <= 0 || j3 <= 0) {
            return;
        }
        if (this.a.connectTimeoutMillis() == j2 && this.a.readTimeoutMillis() == j3) {
            return;
        }
        SLog.i("OkHttpServiceImpl", "setTimeout changed.");
        OkHttpClient.Builder newBuilder = this.a.newBuilder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.a = newBuilder.connectTimeout(j2, timeUnit).readTimeout(j3, timeUnit).writeTimeout(j3, timeUnit).build();
    }

    @Override // com.tencent.open.a.a
    public g a(String str, String str2) throws IOException {
        SLog.i("OkHttpServiceImpl", "get.");
        if (!TextUtils.isEmpty(str2)) {
            int indexOf = str2.indexOf("?");
            if (indexOf == -1) {
                str = str + "?";
            } else if (indexOf != str.length() - 1) {
                str = str + ContainerUtils.FIELD_DELIMITER;
            }
            str = str + str2;
        }
        return new d(this.a.newCall(new Request.Builder().url(str).get().build()).execute(), str2.length());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map) throws IOException {
        SLog.i("OkHttpServiceImpl", "post data");
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builder.add(str2, str3);
                }
            }
        }
        FormBody build = builder.build();
        return new d(this.a.newCall(new Request.Builder().url(str).post(build).build()).execute(), (int) build.contentLength());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 != null && map2.size() != 0) {
            SLog.i("OkHttpServiceImpl", "post data, has byte data");
            MultipartBody.Builder builder = new MultipartBody.Builder();
            if (map != null && map.size() > 0) {
                for (String str2 : map.keySet()) {
                    String str3 = map.get(str2);
                    if (str3 != null) {
                        builder.addFormDataPart(str2, str3);
                    }
                }
            }
            for (String str4 : map2.keySet()) {
                byte[] bArr = map2.get(str4);
                if (bArr != null && bArr.length > 0) {
                    builder.addFormDataPart(str4, str4, RequestBody.create(MediaType.get("content/unknown"), bArr));
                    SLog.w("OkHttpServiceImpl", "post byte data.");
                }
            }
            MultipartBody build = builder.build();
            return new d(this.a.newCall(new Request.Builder().url(str).post(build).build()).execute(), (int) build.contentLength());
        }
        return a(str, map);
    }

    private void a(OkHttpClient.Builder builder) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 16 || i2 >= 21) {
            return;
        }
        try {
            h hVar = new h();
            TrustManager a2 = hVar.a();
            if (a2 == null) {
                return;
            }
            builder.sslSocketFactory(hVar, (X509TrustManager) a2);
            SLog.i("OkHttpServiceImpl", "enableTls2: enabled.");
        } catch (KeyManagementException e2) {
            SLog.e("OkHttpServiceImpl", "enableTls2: failed.", e2);
        } catch (KeyStoreException e3) {
            SLog.e("OkHttpServiceImpl", "enableTls2: failed.", e3);
        } catch (NoSuchAlgorithmException e4) {
            SLog.e("OkHttpServiceImpl", "enableTls2: failed.", e4);
        }
    }
}
