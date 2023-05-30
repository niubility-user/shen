package com.android.volley.toolbox;

import com.android.volley.JsonObjectNetworkResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.ParseError;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonObjectRequest extends JsonRequest<JSONObject> {
    public JsonObjectRequest(int i2, String str, JSONObject jSONObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i2, str, jSONObject == null ? null : jSONObject.toString(), listener, errorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
    public Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse instanceof JsonObjectNetworkResponse) {
            return Response.success(networkResponse.statusCode, ((JsonObjectNetworkResponse) networkResponse).object, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        }
        try {
            return Response.success(networkResponse.statusCode, new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(this, networkResponse));
        } catch (UnsupportedEncodingException e2) {
            return Response.error(new ParseError(e2));
        } catch (JSONException e3) {
            return Response.error(new JsonExceptionError(getUrl(), e3, networkResponse, 200, false, true));
        }
    }

    public JsonObjectRequest(int i2, String str, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, String str2) {
        super(i2, str, str2, listener, errorListener);
    }

    public JsonObjectRequest(String str, JSONObject jSONObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, listener, errorListener);
    }
}
