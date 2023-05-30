package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo;

/* loaded from: classes14.dex */
public class k implements IDeviceInfo {
    private static k a;

    private k() {
    }

    public static synchronized k a() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                a = new k();
            }
            kVar = a;
        }
        return kVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo
    public String getUUID() {
        return StatisticsReportUtil.readCartUUID();
    }
}
