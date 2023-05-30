package com.jd.cashier.app.jdlibcutter.impl.aura;

import android.app.Application;
import com.jd.cashier.app.jdlibcutter.protocol.aura.IAura;
import com.jingdong.common.auraSetting.AuraGlobalSetting;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;

/* loaded from: classes13.dex */
public class JDAuraImpl implements IAura {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.aura.IAura
    public void initJdMallBaseApplication(Application application) {
        if (application != null) {
            AuraGlobalSetting.initJdMallBaseApplication(application);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.aura.IAura
    public boolean isBundlePrepared(String str) {
        return AuraBundleConfig.getInstance().isBundlePrepared(str);
    }
}
