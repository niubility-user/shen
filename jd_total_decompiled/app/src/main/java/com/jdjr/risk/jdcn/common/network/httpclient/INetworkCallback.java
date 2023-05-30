package com.jdjr.risk.jdcn.common.network.httpclient;

/* loaded from: classes18.dex */
public interface INetworkCallback<T> {
    public static final int ERR_ICOEXCEPTION = -4;
    public static final int ERR_SECURITY = -3;
    public static final int ERR_SOCKT_TIMEOUT = -2;
    public static final int ERR_THREAD_FAILD = -5;

    void networkError(int i2, int i3, String str);

    void networkResponse(int i2, T t);
}
