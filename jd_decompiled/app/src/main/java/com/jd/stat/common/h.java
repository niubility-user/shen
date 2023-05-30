package com.jd.stat.common;

import android.text.TextUtils;

/* loaded from: classes18.dex */
public class h {
    public static String a() {
        try {
            String i2 = com.jd.stat.common.b.g.i("cat /proc/self/status|grep NoNewPrivs");
            return TextUtils.isEmpty(i2) ? com.jingdong.jdsdk.a.a.a : i2;
        } catch (Throwable unused) {
            return "c";
        }
    }
}
