package com.huawei.caas.caasservice;

/* loaded from: classes12.dex */
public interface HwCaasServiceCallBack {
    void initFail(int i2);

    void initSuccess(HwCaasHandler hwCaasHandler);

    void releaseSuccess();
}
