package com.jdjr.risk.util.httputil;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpClient;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpResponse;
import com.jdpay.net.http.HTTP;

/* loaded from: classes18.dex */
public class c {
    public static String a(String str, String str2, int i2, int i3) {
        String str3 = HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2.toString())) {
                JDCNHttpResponse startRequestSync = JDCNHttpClient.getNetworkClient(17).startRequestSync(new JDCNHttpCaller.NetworkRequest.Builder().setConnectionTimeout(i2).setReadTimeout(i3).setWriteTimeout(0).addHeader("Connection", "Keep-Alive").addHeader(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON).addHeader("Content-type", "application/json;charset=utf-8").setIsPost().setPostContent(str2).setUrl(str).build());
                if (200 == startRequestSync.code()) {
                    str3 = startRequestSync.body();
                    return str3;
                }
                return HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR;
            }
            return HttpInfoConstants.FAIL_ERROR_PARAM_STR;
        } catch (Throwable unused) {
            return str3;
        }
    }
}
