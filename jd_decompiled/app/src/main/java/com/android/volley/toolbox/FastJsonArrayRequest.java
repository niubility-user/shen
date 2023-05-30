package com.android.volley.toolbox;

import com.alibaba.fastjson.JSONException;
import com.android.volley.FastJsonArrayNetworkResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.ParseError;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class FastJsonArrayRequest extends JsonRequest<JDJSONArray> {
    public FastJsonArrayRequest(int i2, String str, Response.Listener<JDJSONArray> listener, Response.ErrorListener errorListener) {
        super(i2, str, null, listener, errorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
    public Response<JDJSONArray> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse instanceof FastJsonArrayNetworkResponse) {
            return Response.success(networkResponse.statusCode, ((FastJsonArrayNetworkResponse) networkResponse).array, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        }
        try {
            return Response.success(networkResponse.statusCode, JDJSON.parseArray(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        } catch (JSONException e2) {
            return Response.error(new JsonExceptionError(getUrl(), e2, networkResponse, 200, false, true));
        } catch (UnsupportedEncodingException e3) {
            return Response.error(new ParseError(e3));
        }
    }

    public FastJsonArrayRequest(int i2, String str, JSONArray jSONArray, Response.Listener<JDJSONArray> listener, Response.ErrorListener errorListener) {
        super(i2, str, jSONArray == null ? null : jSONArray.toString(), listener, errorListener);
    }
}
