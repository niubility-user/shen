package com.jd.lib.cashier.sdk.core.utils;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.push.IPush;
import com.jd.cashier.app.jdlibcutter.protocol.ui.push.SettingPushOpenListener;

/* loaded from: classes14.dex */
public class z {
    public static void a(SettingPushOpenListener settingPushOpenListener) {
        IPush push = DependInitializer.getPush();
        if (push != null) {
            push.setClickOpenButtonListener(settingPushOpenListener);
        }
    }
}
