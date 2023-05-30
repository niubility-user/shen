package com.jd.fireeye.a.d;

import android.content.Context;
import com.jd.android.sdk.coreinfo.CoreInfo;

/* loaded from: classes13.dex */
public class f {
    private static long a;
    private static String[] b = {"com.redfinger.tw", "com.photon.hybrid", "com.qi.earthnutproxy", "com.sigma_rt.totalcontrol", "io.va.exposed", "net.s17s.s17ray", "com.qx.qiangziip", "com.fvcorp.android.aijiasuclient", "org.soutaproxy"};

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
                } else if (CoreInfo.System.isPkgInstalled(context, strArr[i2])) {
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
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }
}
