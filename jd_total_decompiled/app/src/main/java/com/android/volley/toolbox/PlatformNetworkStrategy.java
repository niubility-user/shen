package com.android.volley.toolbox;

import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.android.volley.FastJsonArrayNetworkResponse;
import com.android.volley.FastJsonObjectNetworkResponse;
import com.android.volley.JsonArrayNetworkResponse;
import com.android.volley.JsonObjectNetworkResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyLog;
import com.android.volley.error.HttpsError;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.VolleyError;
import com.android.volley.utils.UrlUtil;
import com.jd.framework.json.JDJSON;
import com.jd.framework.network.dialing.DNSManager;
import com.jd.framework.network.dialing.IPEntity;
import com.jd.framework.network.dialing.LocalDNSDailer;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class PlatformNetworkStrategy {
    protected final BasicNetwork mNetwork;

    public PlatformNetworkStrategy(BasicNetwork basicNetwork) {
        this.mNetwork = basicNetwork;
    }

    private boolean isBetaHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals("api.m.jd.care", str) || TextUtils.equals("beta-api.m.jd.com", str);
    }

    protected void deliveryErrorAfterDowngrade(Request<?> request, Exception exc, HttpResponse httpResponse, byte[] bArr, Map<String, String> map, long j2) throws VolleyError {
        int statusCode = httpResponse != null ? httpResponse.getStatusLine().getStatusCode() : -1;
        if (VolleyLog.DEBUG) {
            VolleyLog.e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
        }
        NetworkResponse networkResponse = bArr != null ? new NetworkResponse(statusCode, bArr, map, false, SystemClock.elapsedRealtime() - j2) : null;
        if (!(exc instanceof JSONException) && !(exc instanceof com.alibaba.fastjson.JSONException)) {
            if (!UrlUtil.isHttps(request.getUrl())) {
                BasicNetwork.attemptRetryOnException("network", request, new NetworkError(exc, networkResponse, statusCode, request.getUrl(), true));
                return;
            }
            throw new HttpsError.HttpsDomainError(exc, networkResponse, statusCode, request.getUrl(), true);
        }
        throw new JsonExceptionError(request.getUrl(), exc, networkResponse, statusCode, true, false);
    }

    protected abstract void deliveryErrorBeforeDowngrade(Request<?> request, ResponseDelivery responseDelivery, Exception exc, HttpResponse httpResponse, byte[] bArr, Map<String, String> map, long j2);

    protected NetworkResponse doFormatCheck(Request<?> request, byte[] bArr, Map<String, String> map, int i2, long j2) throws Exception {
        if (request instanceof JsonRequest) {
            String str = new String(bArr, HttpHeaderParser.parseCharset(map, "utf-8"));
            if (request instanceof JsonObjectRequest) {
                return new JsonObjectNetworkResponse(i2, bArr, new JSONObject(str), map, false, SystemClock.elapsedRealtime() - j2, request.getDownGradeType());
            }
            if (request instanceof JsonArrayRequest) {
                return new JsonArrayNetworkResponse(i2, bArr, new JSONArray(str), map, false, SystemClock.elapsedRealtime() - j2, request.getDownGradeType());
            }
            if (request instanceof FastJsonObjectRequest) {
                return new FastJsonObjectNetworkResponse(i2, bArr, JDJSON.parseObject(str), map, false, SystemClock.elapsedRealtime() - j2, request.getDownGradeType());
            }
            if (request instanceof FastJsonArrayRequest) {
                return new FastJsonArrayNetworkResponse(i2, bArr, JDJSON.parseArray(str), map, false, SystemClock.elapsedRealtime() - j2, request.getDownGradeType());
            }
        } else if (request instanceof VerifyCodeRequest) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                throw new Exception("invalid verify code response data!");
            }
        }
        return new NetworkResponse(i2, bArr, map, false, SystemClock.elapsedRealtime() - j2, request.getDownGradeType());
    }

    protected void forgetFailHostOrIp(Request<?> request, String str) {
        if (request.isUseDomainName()) {
            DNSManager.getInstance().removeFromFailList(UrlUtil.getHost(request.getUrl()));
        } else {
            DNSManager.getInstance().removeFromFailList(str, UrlUtil.getHost(request.getUrl()));
        }
    }

    protected void performDowngrade(Request<?> request, String str, Exception exc, ResponseDelivery responseDelivery, HttpResponse httpResponse, byte[] bArr, Map<String, String> map, long j2) throws VolleyError {
        boolean z;
        List<String> codeNoRetryList;
        String str2 = str;
        if (VolleyLog.DEBUG) {
            String str3 = "errorInfo:" + exc.toString();
        }
        boolean needRetryOnNetworkLayer = request.needRetryOnNetworkLayer();
        if (VolleyLog.DEBUG) {
            String str4 = "\u7f51\u7edc\u5c42\u5f02\u5e38\u662f\u5426\u9700\u8981\u91cd\u8bd5: " + needRetryOnNetworkLayer;
        }
        try {
            codeNoRetryList = RuntimeConfigHelper.getCodeNoRetryList(RuntimeConfigHelper.HTTP_CODE_NO_RETRY_LIST);
        } catch (Throwable unused) {
        }
        if (!codeNoRetryList.isEmpty() && httpResponse != null && httpResponse.getStatusLine() != null) {
            String valueOf = String.valueOf(httpResponse.getStatusLine().getStatusCode());
            if (codeNoRetryList.contains(valueOf)) {
                if (VolleyLog.DEBUG) {
                    String str5 = "\u62e6\u622a\u5230Http\u8fd4\u56de\u7801 " + valueOf + " \u5c06\u4e0d\u53d1\u8d77\u91cd\u8bd5";
                }
                z = false;
                if (request.isFinalDowngrade() && z && needRetryOnNetworkLayer) {
                    deliveryErrorBeforeDowngrade(request, responseDelivery, exc, httpResponse, bArr, map, j2);
                    request.setDownGradeType(Request.DownGradeType.DownGrade2Domain);
                    if (UrlUtil.isHttps(request.getUrl())) {
                        if (!UrlUtil.isHttps(str)) {
                            str2 = str.replaceFirst("(?i)http", "https");
                        }
                        request.setUrl(str2);
                    } else {
                        if (UrlUtil.isHttps(str)) {
                            str2 = str.replaceFirst("(?i)https", "http");
                        }
                        request.setUrl(str2);
                    }
                    request.setUseDomainName(true);
                    request.setIsFinalDowngradeFlag(true);
                    return;
                }
                deliveryErrorAfterDowngrade(request, exc, httpResponse, bArr, map, j2);
            }
        }
        z = true;
        if (request.isFinalDowngrade()) {
        }
        deliveryErrorAfterDowngrade(request, exc, httpResponse, bArr, map, j2);
    }

    public NetworkResponse performRequest(Request<?> request, ResponseDelivery responseDelivery) throws VolleyError {
        Exception exc;
        HttpResponse httpResponse;
        byte[] bArr;
        HashMap hashMap;
        HttpResponse performRequest;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String url = request.getUrl();
        String host = UrlUtil.getHost(url);
        try {
            preProcessRequest(request);
        } catch (Throwable unused) {
        }
        if (UrlUtil.isHttps(request.getUrl())) {
            JDNetworkStatisticTool.getInstance().incrTotalHttpsRequestCount();
        }
        while (!request.isCanceled()) {
            HashMap hashMap2 = new HashMap();
            try {
                try {
                    HashMap hashMap3 = new HashMap();
                    hashMap3.putAll(this.mNetwork.getAdditionalHeaders());
                    if (!request.isUseDomainName()) {
                        hashMap3.put("host", host);
                    }
                    BasicNetwork.addCacheHeaders(hashMap3, request.getCacheEntry());
                    performRequest = this.mNetwork.getHttpStackFactory().getHttpStack(request.isUseOkhttp()).performRequest(request, hashMap3);
                } catch (Exception e2) {
                    exc = e2;
                    httpResponse = null;
                    bArr = null;
                }
                try {
                    StatusLine statusLine = performRequest.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    Map<String, String> convertHeaders = BasicNetwork.convertHeaders(performRequest.getAllHeaders());
                    try {
                        if (statusCode == 304) {
                            Cache.Entry cacheEntry = request.getCacheEntry();
                            if (cacheEntry == null) {
                                return new NetworkResponse(304, null, convertHeaders, true, SystemClock.elapsedRealtime() - elapsedRealtime, request.getDownGradeType());
                            }
                            cacheEntry.responseHeaders.putAll(convertHeaders);
                            return new NetworkResponse(304, cacheEntry.data, cacheEntry.responseHeaders, true, SystemClock.elapsedRealtime() - elapsedRealtime, request.getDownGradeType());
                        }
                        byte[] entityToBytes = performRequest.getEntity() != null ? this.mNetwork.entityToBytes(performRequest.getEntity()) : new byte[0];
                        try {
                            this.mNetwork.logSlowRequests(SystemClock.elapsedRealtime() - elapsedRealtime, request, entityToBytes, statusLine);
                            if (statusCode >= 200 && statusCode <= 299) {
                                forgetFailHostOrIp(request, host);
                                return doFormatCheck(request, entityToBytes, convertHeaders, statusCode, elapsedRealtime);
                            }
                            throw new IOException("server response code:" + statusCode);
                        } catch (Exception e3) {
                            exc = e3;
                            httpResponse = performRequest;
                            hashMap = convertHeaders;
                            bArr = entityToBytes;
                            rememberFailHostOrIp(request, host, exc);
                            performDowngrade(request, url, exc, responseDelivery, httpResponse, bArr, hashMap, elapsedRealtime);
                            host = host;
                        }
                    } catch (Exception e4) {
                        exc = e4;
                        bArr = null;
                        httpResponse = performRequest;
                        hashMap = convertHeaders;
                    }
                } catch (Exception e5) {
                    exc = e5;
                    bArr = null;
                    httpResponse = performRequest;
                    hashMap = hashMap2;
                    rememberFailHostOrIp(request, host, exc);
                    performDowngrade(request, url, exc, responseDelivery, httpResponse, bArr, hashMap, elapsedRealtime);
                    host = host;
                }
            } catch (Throwable th) {
                throw new VolleyError("error occurred : " + th.toString() + ", with url : " + url);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Request<?> preProcessRequest(Request<?> request) {
        String str;
        IPEntity dailingHealthIp;
        String bestLocalDnsIp;
        int i2;
        String url = request.getUrl();
        String host = UrlUtil.getHost(url);
        if (!request.isUseDomainName()) {
            IpModel ipModelByHost = JDHttpTookit.getEngine().getHttpDnsControllerImpl().getIpModelByHost(host, false);
            if (ipModelByHost != null && !TextUtils.isEmpty(ipModelByHost.getMaster())) {
                str = ipModelByHost.getMaster();
                request.setUrl(url.replaceFirst(host, str));
                dailingHealthIp = DNSManager.getInstance().getDailingHealthIp(host, str);
                if (dailingHealthIp != null && !TextUtils.isEmpty(dailingHealthIp.key)) {
                    i2 = dailingHealthIp.type;
                    if (i2 != 0) {
                        request.setDownGradeType(Request.DownGradeType.DownGrade2BuildInIP);
                    } else if (i2 == 1) {
                        request.setDownGradeType(Request.DownGradeType.DownGrade2HttpDnsIP);
                    } else if (i2 == 2) {
                        request.setDownGradeType(Request.DownGradeType.DownGrade2HttpDnsBackupIP);
                    }
                    request.setUrl(url.replaceFirst(host, dailingHealthIp.key));
                    request.setUseDialingIP(true);
                    request.setUseDomainName(false);
                }
                if (request.isUseDomainName() && !isBetaHost(host)) {
                    bestLocalDnsIp = LocalDNSDailer.getInstance().getBestLocalDnsIp(host);
                    if (!TextUtils.isEmpty(bestLocalDnsIp)) {
                        request.setUrl(url.replaceFirst(host, bestLocalDnsIp));
                        request.setUseDomainName(false);
                    }
                }
                return setProtocol(request, host);
            }
            request.setUseDomainName(true);
        }
        str = null;
        dailingHealthIp = DNSManager.getInstance().getDailingHealthIp(host, str);
        if (dailingHealthIp != null) {
            i2 = dailingHealthIp.type;
            if (i2 != 0) {
            }
            request.setUrl(url.replaceFirst(host, dailingHealthIp.key));
            request.setUseDialingIP(true);
            request.setUseDomainName(false);
        }
        if (request.isUseDomainName()) {
            bestLocalDnsIp = LocalDNSDailer.getInstance().getBestLocalDnsIp(host);
            if (!TextUtils.isEmpty(bestLocalDnsIp)) {
            }
        }
        return setProtocol(request, host);
    }

    protected void rememberFailHostOrIp(Request<?> request, String str, Exception exc) {
        if (request.isUseDomainName()) {
            DNSManager.getInstance().add2DomainFailList(UrlUtil.getHost(request.getUrl()), exc);
        } else {
            DNSManager.getInstance().add2IPFailList(str, UrlUtil.getHost(request.getUrl()), exc);
        }
    }

    protected abstract Request<?> setProtocol(Request<?> request, String str);
}
