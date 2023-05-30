package com.jd.lib.cashier.sdk.h.h;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;

/* loaded from: classes14.dex */
public class b {
    public static boolean a() {
        IDynamic dynamic = DependInitializer.getDynamic();
        boolean d = d(dynamic, "BPayChannelView");
        boolean d2 = d(dynamic, "BPayFoldView");
        boolean d3 = d(dynamic, "BPayJDPayChannelView");
        boolean d4 = d(dynamic, "BPayChannelExtView");
        boolean d5 = d(dynamic, "BPayJDPayNewCardView");
        boolean z = d && d2 && d3 && d4 && d5;
        if (!d) {
            com.jd.lib.cashier.sdk.d.h.b.b("CashierDynamicUtil.hasAllLocalTemplate()", "The local bPayChannelView dynamic template is not exist");
        }
        if (!d2) {
            com.jd.lib.cashier.sdk.d.h.b.b("CashierDynamicUtil.hasAllLocalTemplate()", "The local bPayFoldView dynamic template is not exist");
        }
        if (!d3) {
            com.jd.lib.cashier.sdk.d.h.b.b("CashierDynamicUtil.hasAllLocalTemplate()", "The local bPayJDPayChannelView dynamic template is not exist");
        }
        if (!d4) {
            com.jd.lib.cashier.sdk.d.h.b.b("CashierDynamicUtil.hasAllLocalTemplate()", "The local bPayChannelExtView dynamic template is not exist");
        }
        if (!d5) {
            com.jd.lib.cashier.sdk.d.h.b.b("CashierDynamicUtil.hasAllLocalTemplate()", "The local bPayJDPayNewCardView dynamic template is not exist");
        }
        return z;
    }

    public static boolean b(IDynamic iDynamic, String str) {
        return iDynamic != null && iDynamic.haveBackUp("pay", str);
    }

    public static boolean c(IDynamic iDynamic, String str) {
        return iDynamic != null && iDynamic.haveCache("pay", str);
    }

    public static boolean d(IDynamic iDynamic, String str) {
        return c(iDynamic, str) || b(iDynamic, str);
    }
}
