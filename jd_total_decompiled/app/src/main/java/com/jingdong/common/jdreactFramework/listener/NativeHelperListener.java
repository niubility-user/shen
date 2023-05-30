package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeHelperListener {
    void addScheduleToCalendar(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void addScheduleToCalendar2(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void callPhone(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void closePage(JDCallback jDCallback, JDCallback jDCallback2);

    void getAdvertParams(JDCallback jDCallback, JDCallback jDCallback2);

    void getClientVersion(JDCallback jDCallback, JDCallback jDCallback2);

    void getContactByCondition(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getContactName(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void getContactsdata(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getCurrentModuleVersion(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void getDeviceUUID(JDCallback jDCallback, JDCallback jDCallback2);

    void getOSVersion(JDCallback jDCallback, JDCallback jDCallback2);

    void gpsSettings(JDCallback jDCallback, JDCallback jDCallback2);

    void isGpsOpen(JDCallback jDCallback, JDCallback jDCallback2);

    void md5Encode(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void pickContact(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void pickContact2(JDCallback jDCallback, JDCallback jDCallback2);
}
