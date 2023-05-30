package com.jingdong.jdsdk.network.dependency;

import com.jingdong.common.network.IpModel;

/* loaded from: classes.dex */
public interface IHttpDnsController {
    boolean canUseHttpDns(String str);

    IpModel getIpModelByHost(String str, boolean z);

    boolean isOpenDnsControl();

    void onHttpDnsReceived(IpModel ipModel);
}
