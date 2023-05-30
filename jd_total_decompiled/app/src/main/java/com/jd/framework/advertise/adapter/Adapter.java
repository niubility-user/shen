package com.jd.framework.advertise.adapter;

import com.jd.framework.network.JDResponse;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.request.JDRequest;
import java.io.IOException;
import okhttp3.Response;
import org.json.JSONException;

/* loaded from: classes13.dex */
public interface Adapter<T, R> {
    JDError toJDError(JDRequest<?> jDRequest, Exception exc, Response response);

    T toRequest(JDRequest<?> jDRequest);

    JDResponse<?> toResponse(JDRequest<?> jDRequest, R r) throws IOException, JSONException;
}
