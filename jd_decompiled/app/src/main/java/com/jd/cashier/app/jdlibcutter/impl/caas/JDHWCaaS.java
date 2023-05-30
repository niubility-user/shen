package com.jd.cashier.app.jdlibcutter.impl.caas;

import com.huawei.caas.caasservice.HwCaasUtils;
import com.jd.cashier.app.jdlibcutter.protocol.caas.IHWCaaS;
import com.jingdong.common.utils.caas.CaasKitHelper;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes13.dex */
public class JDHWCaaS implements IHWCaaS {
    private static final String TAG = "HWCaaSImpl";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.caas.IHWCaaS
    public void activeShare() {
        try {
            HwCaasUtils.CallState currentState = CaasKitHelper.getInstance().getCurrentState();
            if (Log.D) {
                Log.d(TAG, "current state--->" + currentState);
            }
            if (currentState == HwCaasUtils.CallState.ACTIVE_CALL) {
                CaasKitHelper.getInstance().resumeShare();
                if (Log.D) {
                    Log.d(TAG, "resumeShare");
                    return;
                }
                return;
            }
            CaasKitHelper.getInstance().pauseShare();
            if (Log.D) {
                Log.d(TAG, "pauseShare");
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.caas.IHWCaaS
    public void shieldShare() {
        try {
            CaasKitHelper.getInstance().pauseShare();
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }
}
