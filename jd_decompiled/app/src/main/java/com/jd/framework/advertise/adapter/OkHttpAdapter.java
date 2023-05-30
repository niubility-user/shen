package com.jd.framework.advertise.adapter;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.advertise.utils.ByteArrayPool;
import com.jd.framework.advertise.utils.PoolingByteArrayOutputStream;
import com.jd.framework.network.JDNetworkResponse;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;

/* loaded from: classes13.dex */
public class OkHttpAdapter implements Adapter<Request, Response> {
    private static int DEFAULT_POOL_SIZE = 4096;
    public static String PROTOCOL_CHARSET = "utf-8";
    public static final String TAG = "OkHttpAdapter";
    protected final ByteArrayPool mPool = new ByteArrayPool(DEFAULT_POOL_SIZE);

    private byte[] getBytesFromResponse(Response response) throws IOException {
        InputStream byteStream;
        String header = response.header("Content-Encoding");
        ResponseBody body = response.body();
        if ("gzip".equals(header)) {
            byteStream = new GZIPInputStream(body.byteStream());
        } else {
            byteStream = body.byteStream();
        }
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) body.contentLength());
        byte[] bArr = null;
        try {
            bArr = this.mPool.getBuf(1024);
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException unused) {
                    OKLog.v("Error occured when calling consumingContent", new Object[0]);
                }
            }
            this.mPool.returnBuf(bArr);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException unused2) {
                    OKLog.v("Error occured when calling consumingContent", new Object[0]);
                }
            }
            this.mPool.returnBuf(bArr);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    public static String parseCharset(Response response, String str) {
        String header = response.header(HttpHeaders.CONTENT_TYPE);
        if (header == null) {
            header = response.header("content-type");
        }
        if (header != null) {
            String[] split = header.split(";");
            for (int i2 = 1; i2 < split.length; i2++) {
                String[] split2 = split[i2].trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    private void setupRequestParam(Request.Builder builder, JDRequest jDRequest) throws IOException {
        int method = jDRequest.getMethod();
        if (method == 0) {
            builder.get();
        } else if (method == 3) {
            builder.delete();
        } else {
            throw new IllegalStateException("Unknown method type.");
        }
    }

    private HashMap<String, String> toHeaderMap(Headers headers) {
        if (headers != null || headers.size() > 0) {
            HashMap<String, String> hashMap = new HashMap<>();
            int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!TextUtils.isEmpty(headers.name(i2)) && !TextUtils.isEmpty(headers.value(i2))) {
                    hashMap.put(headers.name(i2), headers.value(i2));
                }
            }
            return hashMap;
        }
        return null;
    }

    @Override // com.jd.framework.advertise.adapter.Adapter
    public JDError toJDError(JDRequest<?> jDRequest, Exception exc, Response response) {
        JDResponse<?> jDResponse;
        try {
            jDResponse = toResponse2(jDRequest, response);
        } catch (Exception e2) {
            e2.printStackTrace();
            jDResponse = null;
        }
        JDError jDError = exc != null ? new JDError(exc) : null;
        if (jDResponse != null) {
            jDError.networkResponse = new JDNetworkResponse(jDResponse.getStatusCode(), jDResponse.getHeaders(), jDResponse.getData() != null ? jDResponse.getData().toString() : "");
        }
        return jDError;
    }

    @Override // com.jd.framework.advertise.adapter.Adapter
    public /* bridge */ /* synthetic */ Request toRequest(JDRequest jDRequest) {
        return toRequest2((JDRequest<?>) jDRequest);
    }

    @Override // com.jd.framework.advertise.adapter.Adapter
    public /* bridge */ /* synthetic */ JDResponse toResponse(JDRequest jDRequest, Response response) throws IOException, JSONException {
        return toResponse2((JDRequest<?>) jDRequest, response);
    }

    @Override // com.jd.framework.advertise.adapter.Adapter
    /* renamed from: toRequest  reason: avoid collision after fix types in other method */
    public Request toRequest2(JDRequest<?> jDRequest) {
        Request.Builder url = new Request.Builder().url(jDRequest.getUrl());
        if (OKLog.D) {
            OKLog.d(TAG, "request Header Fields : ");
        }
        Map<String, String> header = jDRequest.getHeader();
        for (String str : header.keySet()) {
            url.addHeader(str, header.get(str));
            if (OKLog.D) {
                OKLog.d(TAG, str + " : " + header.get(str));
            }
        }
        try {
            String cookie = JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
            if (RuntimeConfigHelper.enableJdv() && !TextUtils.isEmpty(cookie) && !TextUtils.isEmpty(JDHttpTookit.getEngine().getStatInfoConfigImpl().getJdv())) {
                cookie = cookie + JDHttpTookit.getEngine().getStatInfoConfigImpl().getJdv();
                if (OKLog.D) {
                    OKLog.d(TAG, "Cookie-> " + cookie);
                }
            }
            if (!TextUtils.isEmpty(cookie)) {
                url.addHeader("Cookie", cookie);
            }
        } catch (Throwable unused) {
        }
        if (!TextUtils.isEmpty(jDRequest.getTag())) {
            url.tag(jDRequest.getTag());
        }
        try {
            setupRequestParam(url, jDRequest);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return url.build();
    }

    /* renamed from: toResponse  reason: avoid collision after fix types in other method */
    public JDResponse<?> toResponse2(JDRequest<?> jDRequest, Response response) {
        JDResponse<?> jDResponse = new JDResponse<>();
        if (response != null) {
            if (response.body() != null) {
                try {
                    jDResponse.setData(new String(getBytesFromResponse(response), parseCharset(response, PROTOCOL_CHARSET)));
                } catch (IOException unused) {
                }
            }
            jDResponse.setHeaders(toHeaderMap(response.headers()));
        }
        return jDResponse;
    }
}
