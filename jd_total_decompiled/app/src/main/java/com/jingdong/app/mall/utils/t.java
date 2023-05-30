package com.jingdong.app.mall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes4.dex */
public class t {
    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String b(int i2, int i3) {
        if (i2 > i3) {
            return "\u6bcf\u65e5" + i2 + ":00 - \u6b21\u65e5" + i3 + ":00";
        }
        return "\u6bcf\u65e5" + i2 + ":00 - \u6bcf\u65e5" + i3 + ":00";
    }
}
