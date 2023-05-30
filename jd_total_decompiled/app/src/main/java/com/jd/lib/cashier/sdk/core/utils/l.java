package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;

/* loaded from: classes14.dex */
public class l {
    public static float a(Context context, float f2) {
        int px2sp = DpiUtil.px2sp(context, f2);
        float n2 = y.n(px2sp);
        r.b("CashierFontUtil", "textSize = " + px2sp + "\telderTextSize = " + n2);
        return n2;
    }
}
