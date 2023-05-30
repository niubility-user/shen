package com.android.volley.toolbox;

import com.android.volley.JsonArrayNetworkResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.ParseError;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class JsonArrayRequest extends JsonRequest<JSONArray> {
    public JsonArrayRequest(int i2, String str, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(i2, str, null, listener, errorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
    public Response<JSONArray> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse instanceof JsonArrayNetworkResponse) {
            return Response.success(networkResponse.statusCode, ((JsonArrayNetworkResponse) networkResponse).array, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        }
        try {
            return Response.success(networkResponse.statusCode, new JSONArray(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        } catch (UnsupportedEncodingException e2) {
            return Response.error(new ParseError(e2));
        } catch (JSONException e3) {
            return Response.error(new JsonExceptionError(getUrl(), e3, networkResponse, 200, false, true));
        }
    }

    public JsonArrayRequest(int i2, String str, JSONArray jSONArray, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(i2, str, jSONArray == null ? null : jSONArray.toString(), listener, errorListener);
    }
}
