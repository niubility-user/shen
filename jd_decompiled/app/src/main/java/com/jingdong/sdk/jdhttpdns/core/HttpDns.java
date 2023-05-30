package com.jingdong.sdk.jdhttpdns.core;

import com.jingdong.sdk.jdhttpdns.listener.IResolveListener;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;

/* loaded from: classes7.dex */
public interface HttpDns {
    void clearCache(String str);

    IpModel getIpModelByHost(String str, boolean z);

    void startDomainResolve(IResolveListener iResolveListener, String... strArr);
}
