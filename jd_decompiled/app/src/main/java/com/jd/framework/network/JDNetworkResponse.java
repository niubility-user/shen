package com.jd.framework.network;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class JDNetworkResponse {
    public final Map<String, String> headers;
    public final String responseStr;
    public final int statusCode;

    public JDNetworkResponse(int i2, Map<String, String> map, String str) {
        this.statusCode = i2;
        this.headers = map;
        this.responseStr = str;
    }

    public JDNetworkResponse() {
        this(200, Collections.emptyMap(), null);
    }

    public JDNetworkResponse(Map<String, String> map) {
        this(200, map, null);
    }
}
