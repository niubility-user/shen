package com.jd.cashier.app.jdlibcutter.protocol.risk;

import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;

/* loaded from: classes13.dex */
public interface IRisk {
    void genRiskToken(CommonCallBack<String> commonCallBack);

    String getAId();

    String getLocalIpAddress();

    String getPackageId();
}
