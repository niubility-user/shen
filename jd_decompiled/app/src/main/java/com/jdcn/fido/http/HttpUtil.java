package com.jdcn.fido.http;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpClient;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpResponse;
import com.jdpay.net.http.HTTP;
import com.wangyin.platform.CryptoUtils;
import java.util.Arrays;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class HttpUtil {
    private static final int CONNECT_TIMEOUT = 3000;
    public static final String FAIL_CRYPTO_ERROR = "FAIL_ENCODE_ERROR";
    public static final String FAIL_ERROR_PARAM = "FAIL_ERROR_PARAM";
    public static final String FAIL_HTTP_EXCEPTION = "FAIL_HTTP_EXCEPTION";
    private static final int READ_TIMEOUT = 3000;

    private static String decodeData(CryptoUtils cryptoUtils, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = FAIL_HTTP_EXCEPTION;
            } else if (!str.equals(FAIL_HTTP_EXCEPTION) && !str.equals(FAIL_ERROR_PARAM)) {
                JSONObject jSONObject = new JSONObject(str);
                if (!"1".equals(jSONObject.getString("code"))) {
                    return FAIL_HTTP_EXCEPTION;
                }
                String string = jSONObject.getString("data");
                if (TextUtils.isEmpty(string)) {
                    return FAIL_HTTP_EXCEPTION;
                }
                byte[] decodeDataFromServer = cryptoUtils.decodeDataFromServer(string);
                if (!"00000".equals(new String(Arrays.copyOfRange(decodeDataFromServer, 0, 5)))) {
                    return FAIL_CRYPTO_ERROR;
                }
                str = new String(Arrays.copyOfRange(decodeDataFromServer, 5, decodeDataFromServer.length));
            }
            return str;
        } catch (Throwable unused) {
            return FAIL_HTTP_EXCEPTION;
        }
    }

    private static String encodeData(CryptoUtils cryptoUtils, String str) {
        try {
            byte[] encodeDataToServer = cryptoUtils.encodeDataToServer(str, System.currentTimeMillis());
            if ("00000".equals(new String(Arrays.copyOfRange(encodeDataToServer, 0, 5)))) {
                String str2 = new String(Arrays.copyOfRange(encodeDataToServer, 5, encodeDataToServer.length));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", 1);
                jSONObject.put("data", str2);
                jSONObject.put("code", "1");
                return jSONObject.toString();
            }
            return FAIL_CRYPTO_ERROR;
        } catch (Throwable unused) {
            return FAIL_CRYPTO_ERROR;
        }
    }

    public static String httpGet(String str) {
        try {
            JDCNHttpResponse startRequestSync = JDCNHttpClient.getNetworkClient(17).startRequestSync(new JDCNHttpCaller.NetworkRequest.Builder().setConnectionTimeout(3000).setReadTimeout(3000).setWriteTimeout(0).addHeader("Connection", "Keep-Alive").addHeader(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON).setUrl(str).build());
            return 200 == startRequestSync.code() ? startRequestSync.body() : FAIL_HTTP_EXCEPTION;
        } catch (Throwable unused) {
            return FAIL_HTTP_EXCEPTION;
        }
    }

    private static String httpPost(String str, String str2) {
        String str3 = FAIL_HTTP_EXCEPTION;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                JDCNHttpResponse startRequestSync = JDCNHttpClient.getNetworkClient(17).startRequestSync(new JDCNHttpCaller.NetworkRequest.Builder().setConnectionTimeout(3000).setReadTimeout(3000).setWriteTimeout(0).addHeader("Connection", "Keep-Alive").addHeader(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON).addHeader("Content-type", "application/json;charset=utf-8").setIsPost().setPostContent(str2).setUrl(str).build());
                if (200 == startRequestSync.code()) {
                    str3 = startRequestSync.body();
                    return str3;
                }
                return FAIL_HTTP_EXCEPTION;
            }
            return FAIL_ERROR_PARAM;
        } catch (Throwable th) {
            th.printStackTrace();
            return str3;
        }
    }

    public static String httpPost(String str, JSONObject jSONObject) {
        return httpPost(str, jSONObject.toString());
    }

    public static String httpPostEncode(Context context, String str, JSONObject jSONObject) {
        CryptoUtils newInstance = CryptoUtils.newInstance(context);
        String encodeData = encodeData(newInstance, jSONObject.toString());
        return FAIL_CRYPTO_ERROR.equals(encodeData) ? encodeData : decodeData(newInstance, httpPost(str, encodeData));
    }
}
