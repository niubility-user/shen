package com.huawei.caas.caasservice;

import com.huawei.caas.caasservice.HwCaasUtils;

/* loaded from: classes12.dex */
public abstract class HwCaasHandler {
    public static final int DEFAULT_ILLEGAL_VALUE = -1;
    public static final int DEFAULT_RET_NUM = -1;
    public static final int DEFAULT_VALUE = -1;
    public static final int SERVICE_IS_NULL = 1;

    public boolean hasCaaSContacts(HwCaasUtils.ContactsType contactsType) {
        return false;
    }

    public int initVirtualCamera(String str, String str2) {
        return -1;
    }

    public int makeCall(String str, HwCaasUtils.CallType callType) {
        return -1;
    }

    public int makeCall(String str, HwCaasUtils.CallType callType, CustomDisplayInfo customDisplayInfo) {
        return -1;
    }

    public int queryCallAbility(String str, HwCaasUtils.CallAbilityType callAbilityType) {
        return -1;
    }

    public int releaseVirtualCamera(String str, String str2) {
        return -1;
    }

    public boolean sendEventToCaasService(int i2) {
        return false;
    }

    public boolean setAppMode(int i2) {
        return false;
    }

    public boolean setCallAbilityCallBack(HwCallAbilityCallBack hwCallAbilityCallBack) {
        return false;
    }

    public void setCallStateCallBack(HwCallStateCallBack hwCallStateCallBack) {
    }

    public boolean setCallerAppName(String str) {
        return false;
    }

    public boolean setContactViewSize(int i2, int i3) {
        return false;
    }

    public boolean setContactViewStyle(HwCaasUtils.ContactsViewStyle contactsViewStyle) {
        return false;
    }

    public boolean setFloatViewLocation(int i2, int i3, int i4, int i5) {
        return false;
    }

    public boolean setMakeCallCallBack(HwMakeCallCallBack hwMakeCallCallBack) {
        return false;
    }

    public boolean setStartViewBackgroundColor(String str) {
        return false;
    }
}
