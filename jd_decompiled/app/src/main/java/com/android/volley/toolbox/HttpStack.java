package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.error.AuthFailureError;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

/* loaded from: classes.dex */
public interface HttpStack {
    public static final int CONNECT_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 10000;

    HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError;
}
