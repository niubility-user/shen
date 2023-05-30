package com.jdjr.risk.jdcn.common.network.httpclient;

/* loaded from: classes18.dex */
public interface INetworkWithJSONCallback<T> extends INetworkCallback<T> {
    T requestParse(String str);
}
