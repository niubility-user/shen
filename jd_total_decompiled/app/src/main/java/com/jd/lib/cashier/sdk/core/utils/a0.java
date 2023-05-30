package com.jd.lib.cashier.sdk.core.utils;

import android.view.View;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder;

/* loaded from: classes14.dex */
public class a0 {
    public static void a(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.addReminder(view, str);
        }
    }

    public static void b(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.checkReminder(view, str);
        }
    }

    public static void c(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.getAllRemindersWithTimeSpanAndBusinessType(view, str);
        }
    }

    public static void d(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.checkNotificationEnable(view, str);
        }
    }

    public static void e(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.removeReminder(view, str);
        }
    }

    public static void f(View view, String str) {
        IReminder reminder = DependInitializer.getReminder();
        if (reminder != null) {
            reminder.showPushOpenGuide(view, str);
        }
    }
}
