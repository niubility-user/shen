package com.huawei.emui.hiexperience.hwperf.speedloader;

import com.huawei.emui.hiexperience.hwperf.HwPerfBase;
import com.huawei.emui.hiexperience.hwperf.utils.HwPerfLog;

/* loaded from: classes12.dex */
public class HwPerfSpeedLoader extends HwPerfBase {

    /* loaded from: classes12.dex */
    public interface HwPerfVelocityCallback {
        void HwPerfonVelocityDownToThreshold();

        void HwPerfonVelocityUpToThreshold();
    }

    public String HwPerfGetSystemRefVelocity() {
        HwPerfLog.i("API: HwPerfSpeedLoader HwPerfGetSystemRefVelocity");
        return "-1:-1:-1";
    }

    public boolean HwPerfSetSpeedLoaderListener(Object obj, HwPerfVelocityCallback hwPerfVelocityCallback) {
        HwPerfLog.i("API: HwPerfSpeedLoader HwPerfSetSpeedLoaderListener");
        return false;
    }

    public void HwPerfSetThresholdVelocity(float f2) {
        HwPerfLog.i("API: HwPerfSpeedLoader HwPerfSetThresholdVelocity");
    }
}
