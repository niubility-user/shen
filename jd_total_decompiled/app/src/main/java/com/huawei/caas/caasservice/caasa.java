package com.huawei.caas.caasservice;

import com.huawei.caas.caasservice.HwCaasUtils;

/* loaded from: classes12.dex */
public final class caasa extends HwCaasHandler {
    private HwCaasServiceManager caasa;

    public caasa(HwCaasServiceManager hwCaasServiceManager) {
        this.caasa = hwCaasServiceManager;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean hasCaaSContacts(HwCaasUtils.ContactsType contactsType) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || contactsType == null) {
            return false;
        }
        return hwCaasServiceManager.caasa(contactsType);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final int initVirtualCamera(String str, String str2) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || str == null || str2 == null) {
            return -1;
        }
        return hwCaasServiceManager.caasa(str, str2, 102);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final int releaseVirtualCamera(String str, String str2) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || str == null || str2 == null) {
            return -1;
        }
        return hwCaasServiceManager.caasa(str, str2, 103);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean sendEventToCaasService(int i2) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager != null) {
            if (i2 == 1) {
                return hwCaasServiceManager.caasa(i2);
            }
        }
        return false;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setAppMode(int i2) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null) {
            return false;
        }
        return hwCaasServiceManager.caasb(i2);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setContactViewSize(int i2, int i3) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null) {
            return false;
        }
        return hwCaasServiceManager.caasa(i2, i3);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setFloatViewLocation(int i2, int i3, int i4, int i5) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null) {
            return false;
        }
        return hwCaasServiceManager.caasa(i2, i3, i4, i5);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setStartViewBackgroundColor(String str) {
        return true;
    }
}
