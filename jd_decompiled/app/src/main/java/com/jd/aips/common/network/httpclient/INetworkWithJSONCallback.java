package com.jd.aips.common.network.httpclient;

/* loaded from: classes12.dex */
public interface INetworkWithJSONCallback<T> extends INetworkCallback<T> {
    T requestParse(String str);
}
