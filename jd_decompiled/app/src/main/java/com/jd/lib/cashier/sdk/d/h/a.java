package com.jd.lib.cashier.sdk.d.h;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.monitor.IExceptionMonitor;

/* loaded from: classes14.dex */
public class a {
    public static void a(String str, String str2, String str3, String str4) {
        try {
            IExceptionMonitor exceptionMonitor = DependInitializer.getExceptionMonitor();
            if (exceptionMonitor != null) {
                exceptionMonitor.sendExceptionData(str, str2, str3, str4);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
