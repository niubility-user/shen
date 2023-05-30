package com.jd.aips.verify.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jd.aips.common.network.OkHttpClientProvider;
import com.jd.aips.common.utils.EnvUtils;
import com.jdpay.net.http.HTTP;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes12.dex */
public final class ApiHelper {
    private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final Headers DEFAULT_HEADERS = new Headers.Builder().add("Connection", "Keep-Alive").add(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON).add("Content-type", "application/json;charset=utf-8").build();

    public static String getApiBaseUrl() {
        int envType = EnvUtils.getEnvType();
        return envType != 0 ? envType != 1 ? envType != 2 ? VerifyApi.BASE_URL_PRODUCT : VerifyApi.BASE_URL_TEST : VerifyApi.BASE_URL_UAT : VerifyApi.BASE_URL_PRODUCT;
    }

    public static String getGatewayUrl() {
        int envType = EnvUtils.getEnvType();
        return envType != 0 ? (envType == 1 || envType == 2) ? "https://facegw-pre.jd.com" : VerifyApi.GATEWAY_URL_PRODUCT : VerifyApi.GATEWAY_URL_PRODUCT;
    }

    public static String toPost(@NonNull String str, @NonNull String str2) throws ApiException {
        return toPost(str, str2, 30);
    }

    public static String toPost(@NonNull String str, @NonNull String str2, int i2) throws ApiException {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Response execute = OkHttpClientProvider.getInstance().provideOkHttpClient(i2).newCall(new Request.Builder().url(str).headers(DEFAULT_HEADERS).post(RequestBody.create(DEFAULT_MEDIA_TYPE, str2)).build()).execute();
                String string = execute.body().string();
                if (execute.isSuccessful()) {
                    return string;
                }
                String str3 = "failed to request server: " + execute;
                throw new ApiException(10003, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
            } catch (IOException unused) {
                throw new ApiException(10003, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
            }
        }
        throw new IllegalArgumentException();
    }
}
