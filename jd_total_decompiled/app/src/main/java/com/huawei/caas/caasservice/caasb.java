package com.huawei.caas.caasservice;

import com.huawei.caas.caasservice.HwCaasUtils;

/* loaded from: classes12.dex */
public final class caasb extends HwCaasHandler {
    private HwCaasServiceManager caasa;
    private int caasb;

    public caasb(HwCaasServiceManager hwCaasServiceManager, int i2) {
        this.caasa = hwCaasServiceManager;
        this.caasb = i2;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final int makeCall(String str, HwCaasUtils.CallType callType) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || this.caasb != 3) {
            return -1;
        }
        return hwCaasServiceManager.caasa(str, callType);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final int makeCall(String str, HwCaasUtils.CallType callType, CustomDisplayInfo customDisplayInfo) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || this.caasb != 1) {
            return -1;
        }
        return hwCaasServiceManager.caasa(str, callType, customDisplayInfo);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final int queryCallAbility(String str, HwCaasUtils.CallAbilityType callAbilityType) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager != null) {
            return hwCaasServiceManager.caasa(str, callAbilityType);
        }
        return -1;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setCallAbilityCallBack(HwCallAbilityCallBack hwCallAbilityCallBack) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || hwCallAbilityCallBack == null) {
            return false;
        }
        hwCaasServiceManager.caasa = hwCallAbilityCallBack;
        return false;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setMakeCallCallBack(HwMakeCallCallBack hwMakeCallCallBack) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || hwMakeCallCallBack == null) {
            return false;
        }
        hwCaasServiceManager.caasb = hwMakeCallCallBack;
        return false;
    }
}
