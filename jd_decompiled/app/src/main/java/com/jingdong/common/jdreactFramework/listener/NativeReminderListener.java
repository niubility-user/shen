package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeReminderListener {
    void cancelReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void checkReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getAllRemindersByBusinessType(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getAllRemindersByBusinessTypeAndTime(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getAllRemindersByBusinessTypeDuringTimePeriod(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void setReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
