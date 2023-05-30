package com.jd.cashier.app.jdlibcutter.protocol.ui.webview;

import android.view.View;

/* loaded from: classes13.dex */
public interface IReminder {
    void addReminder(View view, String str);

    void checkNotificationEnable(View view, String str);

    void checkReminder(View view, String str);

    void getAllRemindersWithTimeSpanAndBusinessType(View view, String str);

    void removeReminder(View view, String str);

    void showPushOpenGuide(View view, String str);
}
