package com.jingdong.app.mall.im.business;

import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMExtension;

/* loaded from: classes4.dex */
public class l extends IMExtension {
    private static final String a = "l";

    @Override // com.jingdong.service.impl.IMExtension, com.jingdong.service.service.ExtensionService
    public boolean audioSwitch() {
        OKLog.d("bundleicssdkservice", a + "--- audioSwitch");
        return true;
    }
}
