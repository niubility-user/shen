package com.jingdong.app.mall.h.b;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.jdbrotli.BrotliInputStream;
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;

/* loaded from: classes19.dex */
public class a implements Interceptor {
    private Response a(Response response) throws IOException {
        if (response.body() == null) {
            return response;
        }
        String header = response.header("Content-Encoding");
        if (TextUtils.isEmpty(header) || !BrightRemindSetting.BRIGHT_REMIND.equalsIgnoreCase(header)) {
            return response;
        }
        String mediaType = response.body().contentType().toString();
        String str = "start brotli uncompress, contentType" + mediaType;
        return response.newBuilder().removeHeader("Content-Encoding").removeHeader(HttpHeaders.CONTENT_LENGTH).body(new RealResponseBody(mediaType, -1L, Okio.buffer(Okio.source(new BrotliInputStream(response.body().source().inputStream()))))).build();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return a(chain.proceed(chain.request()));
    }
}
