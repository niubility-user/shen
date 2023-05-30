package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public abstract class JsonRequest<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private Response.Listener<T> mListener;
    private final String mRequestBody;

    public JsonRequest(String str, String str2, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(-1, str, str2, listener, errorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(Response<T> response) {
        this.mListener.onResponse(response);
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        if (getParams() != null && getParams().size() > 0) {
            return super.getBody();
        }
        try {
            String str = this.mRequestBody;
            if (str == null) {
                return null;
            }
            return str.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException unused) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        try {
            if (getParams() != null && getParams().size() > 0) {
                return super.getBodyContentType();
            }
        } catch (AuthFailureError e2) {
            VolleyLog.e(e2, "AuthFailureError exception %s", e2.toString());
        }
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override // com.android.volley.Request
    public byte[] getPostBody() throws AuthFailureError {
        return getBody();
    }

    @Override // com.android.volley.Request
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void onFinish() {
        super.onFinish();
        this.mListener = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    public JsonRequest(int i2, String str, String str2, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(i2, str, errorListener);
        this.mListener = listener;
        this.mRequestBody = str2;
    }
}
