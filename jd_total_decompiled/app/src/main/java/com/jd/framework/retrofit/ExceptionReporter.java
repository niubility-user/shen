package com.jd.framework.retrofit;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/* loaded from: classes13.dex */
public class ExceptionReporter {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static boolean bodyEncoded(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY)) ? false : true;
    }

    private static String getBodyStr(Response response) {
        try {
            ResponseBody body = response.body();
            long contentLength = body.contentLength();
            if (!HttpHeaders.hasBody(response) || bodyEncoded(response.headers())) {
                return "";
            }
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException unused) {
                }
            }
            return (!isPlaintext(buffer) || contentLength == 0) ? "" : buffer.clone().readString(charset);
        } catch (Throwable unused2) {
            return "";
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i2 = 0; i2 < 16; i2++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static void reportNetworkError(Request request, Response response, Throwable th, int i2, boolean z) {
        JDHttpTookit.getEngine().getRetrofitExceptionReporter().reportNetworkError(JDNetworkConstant.RETROFIT_NETWORK_ERRCODE, request.url().toString(), request.url().queryParameter("functionId"), response != null ? response.code() : -1, th, getBodyStr(response), i2, z);
    }
}
