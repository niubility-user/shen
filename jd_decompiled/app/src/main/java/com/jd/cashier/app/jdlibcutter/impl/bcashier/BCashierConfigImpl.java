package com.jd.cashier.app.jdlibcutter.impl.bcashier;

import com.jd.cashier.app.jdlibcutter.protocol.bcashier.IBCashierConfig;
import com.jingdong.sdk.bmode.util.JDBModeUtils;

/* loaded from: classes13.dex */
public class BCashierConfigImpl implements IBCashierConfig {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.bcashier.IBCashierConfig
    public String getCurrentMode() {
        try {
            return JDBModeUtils.getCurrentMode();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
