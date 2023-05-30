package com.android.volley.toolbox;

import com.android.volley.FastJsonObjectNetworkResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.ParseError;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class FastJsonObjectRequest extends JsonRequest<JDJSONObject> {
    public FastJsonObjectRequest(int i2, String str, JDJSONObject jDJSONObject, Response.Listener<JDJSONObject> listener, Response.ErrorListener errorListener) {
        super(i2, str, jDJSONObject == null ? null : jDJSONObject.toString(), listener, errorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
    public Response<JDJSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        Throwable th;
        String str;
        if (networkResponse instanceof FastJsonObjectNetworkResponse) {
            return Response.success(networkResponse.statusCode, ((FastJsonObjectNetworkResponse) networkResponse).object, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        }
        String str2 = null;
        try {
            try {
                str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"));
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                boolean z = VolleyLog.DEBUG;
                return Response.success(networkResponse.statusCode, JDJSON.parseObject(str), HttpHeaderParser.parseCacheHeaders(this, networkResponse));
            } catch (Throwable th3) {
                th = th3;
                str2 = str;
                if (VolleyLog.DEBUG) {
                    String str3 = "Exception occured, jsonStr : " + str2;
                }
                return Response.error(new JsonExceptionError(getUrl(), th, networkResponse, 200, false, true));
            }
        } catch (UnsupportedEncodingException e2) {
            return Response.error(new ParseError(e2));
        }
    }

    public FastJsonObjectRequest(int i2, String str, Response.Listener<JDJSONObject> listener, Response.ErrorListener errorListener, String str2) {
        super(i2, str, str2, listener, errorListener);
    }

    public FastJsonObjectRequest(String str, JDJSONObject jDJSONObject, Response.Listener<JDJSONObject> listener, Response.ErrorListener errorListener) {
        this(jDJSONObject == null ? 0 : 1, str, jDJSONObject, listener, errorListener);
    }
}
