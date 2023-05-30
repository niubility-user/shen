package com.huawei.caas.caasservice;

import android.text.TextUtils;
import com.huawei.caas.caasservice.HwCaasUtils;

/* loaded from: classes12.dex */
public final class caasc extends HwCaasHandler {
    private HwCaasServiceManager caasa;
    private String caasb;

    public caasc(HwCaasServiceManager hwCaasServiceManager) {
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
    public final boolean sendEventToCaasService(int i2) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager != null && this.caasb != null) {
            boolean z = true;
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 3 && i2 != 4 && i2 != 5) {
                z = false;
            }
            if (z) {
                return hwCaasServiceManager.caasa(i2);
            }
        }
        return false;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final void setCallStateCallBack(HwCallStateCallBack hwCallStateCallBack) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null) {
            return;
        }
        hwCaasServiceManager.caasc = hwCallStateCallBack;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setCallerAppName(String str) {
        boolean z;
        if (this.caasa != null) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.replaceAll("\\s+", "")) || str.length() > 40) {
                z = false;
            } else {
                this.caasb = str;
                z = true;
            }
            if (z) {
                return this.caasa.caasa(str);
            }
        }
        return false;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setContactViewSize(int i2, int i3) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager != null) {
            return hwCaasServiceManager.caasa(i2, i3);
        }
        return false;
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setContactViewStyle(HwCaasUtils.ContactsViewStyle contactsViewStyle) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager == null || contactsViewStyle == null) {
            return false;
        }
        return hwCaasServiceManager.caasa(contactsViewStyle);
    }

    @Override // com.huawei.caas.caasservice.HwCaasHandler
    public final boolean setFloatViewLocation(int i2, int i3, int i4, int i5) {
        HwCaasServiceManager hwCaasServiceManager = this.caasa;
        if (hwCaasServiceManager != null) {
            return hwCaasServiceManager.caasa(i2, i3, i4, i5);
        }
        return false;
    }
}
