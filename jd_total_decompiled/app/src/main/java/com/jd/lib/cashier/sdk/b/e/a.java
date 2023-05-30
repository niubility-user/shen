package com.jd.lib.cashier.sdk.b.e;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs;

/* loaded from: classes14.dex */
public class a {
    private static volatile a a;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public synchronized String b() {
        ILbs lbs = DependInitializer.getLbs();
        if (lbs != null) {
            return lbs.getLatitude();
        }
        return "-100";
    }

    public synchronized String c() {
        ILbs lbs = DependInitializer.getLbs();
        if (lbs != null) {
            return lbs.getLongitude();
        }
        return "-100";
    }
}
