package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils;

/* loaded from: classes14.dex */
public class e implements IAdvertUtils {
    private static e a = new e();

    private e() {
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils
    public String getBusinessId() {
        return AdvertUtils.getBusinessId();
    }
}
