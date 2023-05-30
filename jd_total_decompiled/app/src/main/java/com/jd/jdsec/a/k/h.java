package com.jd.jdsec.a.k;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class h {
    private static long a;
    private static String[] b = {"com.yztc.studio.plugin", "de.robv.android.xposed.installer", "com.miui.miuibbs", "com.soft.apk008v", "com.dobe.igrimace", "com.igrimace.nzt", "com.baidu.md", "com.topjohnwu.magisk"};

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(Context context) {
        Boolean bool = Boolean.FALSE;
        int i2 = 0;
        while (true) {
            try {
                String[] strArr = b;
                if (i2 >= strArr.length) {
                    break;
                } else if (BaseInfo.isPkgInstalled(context, strArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            } catch (Exception unused) {
            }
        }
        return bool.booleanValue();
    }

    public static Long b(Context context) {
        try {
            if (a(context)) {
                a |= 1;
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.RiskLabel", "\u6539\u673a\u68c0\u6d4b\u6a21\u5757\u5f02\u5e38" + e2.getMessage());
        }
        return Long.valueOf(a);
    }
}
