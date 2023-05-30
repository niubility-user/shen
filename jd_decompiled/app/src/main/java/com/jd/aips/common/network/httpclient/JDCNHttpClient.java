package com.jd.aips.common.network.httpclient;

/* loaded from: classes12.dex */
public final class JDCNHttpClient {
    public static final int ORIGINAL_STRATEGY = 17;

    public static JDCNHttpCaller getNetworkClient(int i2) {
        return OriginalJDCNHttpCaller.a();
    }
}
