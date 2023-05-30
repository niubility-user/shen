package com.jd.mobile.image.config;

/* loaded from: classes17.dex */
public interface INetworkParameter {
    String getCustomUseAgent();

    boolean isForce2HttpFlag();

    boolean isUseDomainFlag();

    boolean isUseHttps();

    @Deprecated
    boolean isUseOKHttp();

    boolean needReferer();
}
