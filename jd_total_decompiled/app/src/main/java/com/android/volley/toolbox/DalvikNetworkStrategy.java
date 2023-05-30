package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyLog;
import com.android.volley.error.IpError;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.NetworkError;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.json.JSONException;

/* loaded from: classes.dex */
public class DalvikNetworkStrategy extends PlatformNetworkStrategy {
    public static final String TAG = "DalvikNetworkStrategy";

    public DalvikNetworkStrategy(BasicNetwork basicNetwork) {
        super(basicNetwork);
    }

    @Override // com.android.volley.toolbox.PlatformNetworkStrategy
    protected void deliveryErrorBeforeDowngrade(Request<?> request, ResponseDelivery responseDelivery, Exception exc, HttpResponse httpResponse, byte[] bArr, Map<String, String> map, long j2) {
        int statusCode = httpResponse != null ? httpResponse.getStatusLine().getStatusCode() : -1;
        if (VolleyLog.DEBUG) {
            VolleyLog.e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
        }
        NetworkResponse networkResponse = bArr != null ? new NetworkResponse(statusCode, bArr, map, false, SystemClock.elapsedRealtime() - j2) : null;
        if (!(exc instanceof JSONException) && !(exc instanceof com.alibaba.fastjson.JSONException)) {
            if (!request.isUseDomainName()) {
                responseDelivery.postOtherError(request, new IpError(request.getUrl(), exc, networkResponse, statusCode, false));
                return;
            } else {
                responseDelivery.postOtherError(request, new NetworkError(exc, networkResponse, statusCode, request.getUrl(), false));
                return;
            }
        }
        responseDelivery.postOtherError(request, new JsonExceptionError(request.getUrl(), exc, networkResponse, statusCode, false, false));
    }

    @Override // com.android.volley.toolbox.PlatformNetworkStrategy
    protected Request<?> setProtocol(Request<?> request, String str) {
        String url = request.getUrl();
        if (url.startsWith("https")) {
            request.setUrl(url.replaceFirst("(?i)https", "http"));
        }
        return request;
    }
}
