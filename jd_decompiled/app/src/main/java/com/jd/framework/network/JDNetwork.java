package com.jd.framework.network;

import com.jd.framework.network.request.JDRequest;

/* loaded from: classes13.dex */
public interface JDNetwork {
    JDRequestQueue addToJDRequestQueue(JDRequest<?> jDRequest);

    JDRequestQueue newJDRequestQueue();
}
