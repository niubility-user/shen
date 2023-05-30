package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/* loaded from: classes.dex */
public class ByteArrayRequest extends Request<byte[]> {
    private Response.Listener<byte[]> mListener;

    public ByteArrayRequest(int i2, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i2, str, errorListener);
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(Response<byte[]> response) {
        this.mListener.onResponse(response);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void onFinish() {
        super.onFinish();
        this.mListener = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(networkResponse.statusCode, networkResponse.data, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
    }

    public ByteArrayRequest(String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(0, str, listener, errorListener);
    }
}
