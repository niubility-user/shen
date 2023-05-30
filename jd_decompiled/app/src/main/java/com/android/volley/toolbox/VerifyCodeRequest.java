package com.android.volley.toolbox;

import com.android.volley.Response;

/* loaded from: classes.dex */
public class VerifyCodeRequest extends ByteArrayRequest {
    public VerifyCodeRequest(int i2, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i2, str, listener, errorListener);
    }

    public VerifyCodeRequest(String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(str, listener, errorListener);
    }
}
