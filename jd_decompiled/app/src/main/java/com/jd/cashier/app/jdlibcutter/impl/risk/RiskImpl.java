package com.jd.cashier.app.jdlibcutter.impl.risk;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.RiskControlUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes13.dex */
public class RiskImpl implements IRisk {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk
    public void genRiskToken(final CommonCallBack<String> commonCallBack) {
        try {
            IConfig sdkConfig = DependInitializer.getSdkConfig();
            BiometricManager.getInstance().getToken(JdSdk.getInstance().getApplicationContext(), sdkConfig != null ? sdkConfig.getRiskTokenBusinessId() : "", UserUtil.getWJLoginHelper().getPin(), new LorasHttpCallback() { // from class: com.jd.cashier.app.jdlibcutter.impl.risk.RiskImpl.1
                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onFailInCurentThread(int i2, String str) {
                }

                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onFailInNetThread(int i2, String str) {
                }

                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onSuccess(String str) {
                    CommonCallBack commonCallBack2 = commonCallBack;
                    if (commonCallBack2 != null) {
                        commonCallBack2.onCallBack(str);
                    }
                }
            });
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk
    public String getAId() {
        return DeviceInfoHelper.getAid();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk
    public String getLocalIpAddress() {
        return RiskControlUtils.getLocalIPAddress();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk
    public String getPackageId() {
        return JdSdk.getInstance().getApplication().getPackageName();
    }
}
