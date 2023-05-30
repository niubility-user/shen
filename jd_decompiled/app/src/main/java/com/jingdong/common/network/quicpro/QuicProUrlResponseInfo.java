package com.jingdong.common.network.quicpro;

import java.util.Map;

/* loaded from: classes5.dex */
public class QuicProUrlResponseInfo extends UrlResponseInfo {
    public long mContentLength;
    public Map<String, String> mHeaderMap;
    public String mNegotiatedProtocol;
    public String mProxyServer;
    public int mReceiveByteCount;
    public byte[] mResponseContent;
    public int mStatusCode;
    public String mUrl;

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public Map<String, String> getAllHeaders() {
        return this.mHeaderMap;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public long getContentLength() {
        return this.mContentLength;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public int getHttpStatusCode() {
        return this.mStatusCode;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public String getNegotiatedProtocol() {
        return this.mNegotiatedProtocol;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public String getProxyServer() {
        return this.mProxyServer;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public long getReceivedByteCount() {
        return this.mReceiveByteCount;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public byte[] getResponseContent() {
        byte[] bArr = this.mResponseContent;
        return bArr != null ? bArr : new byte[0];
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public String getUrl() {
        return this.mUrl;
    }

    @Override // com.jingdong.common.network.quicpro.UrlResponseInfo
    public boolean wasCached() {
        return false;
    }
}
