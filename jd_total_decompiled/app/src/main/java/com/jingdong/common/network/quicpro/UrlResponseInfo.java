package com.jingdong.common.network.quicpro;

import java.util.Map;

/* loaded from: classes5.dex */
public abstract class UrlResponseInfo {
    public abstract Map<String, String> getAllHeaders();

    public abstract long getContentLength();

    public abstract int getHttpStatusCode();

    public abstract String getNegotiatedProtocol();

    public abstract String getProxyServer();

    public abstract long getReceivedByteCount();

    public abstract byte[] getResponseContent();

    public abstract String getUrl();

    public abstract boolean wasCached();
}
