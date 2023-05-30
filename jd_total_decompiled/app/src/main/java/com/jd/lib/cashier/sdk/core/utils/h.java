package com.jd.lib.cashier.sdk.core.utils;

import android.app.Application;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.aura.IAura;

/* loaded from: classes.dex */
public class h {
    public static void a(Application application) {
        IAura aura = DependInitializer.getAura();
        if (aura == null || application == null) {
            return;
        }
        aura.initJdMallBaseApplication(application);
    }
}
