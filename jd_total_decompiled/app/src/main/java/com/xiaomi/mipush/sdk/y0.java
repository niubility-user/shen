package com.xiaomi.mipush.sdk;

import com.xiaomi.push.h7;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class y0 {
    private static HashMap<r0, a> a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class a {
        public String a;
        public String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    static {
        d(r0.ASSEMBLE_PUSH_HUAWEI, new a("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        d(r0.ASSEMBLE_PUSH_FCM, new a("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        d(r0.ASSEMBLE_PUSH_COS, new a("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
        d(r0.ASSEMBLE_PUSH_FTOS, new a("com.xiaomi.assemble.control.FTOSPushManager", "newInstance"));
    }

    public static l0 a(r0 r0Var) {
        int i2 = z0.a[r0Var.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return null;
                    }
                    return l0.UPLOAD_FTOS_TOKEN;
                }
                return l0.UPLOAD_COS_TOKEN;
            }
            return l0.UPLOAD_FCM_TOKEN;
        }
        return l0.UPLOAD_HUAWEI_TOKEN;
    }

    public static a b(r0 r0Var) {
        return a.get(r0Var);
    }

    public static h7 c(r0 r0Var) {
        return h7.AggregatePushSwitch;
    }

    private static void d(r0 r0Var, a aVar) {
        if (aVar != null) {
            a.put(r0Var, aVar);
        }
    }
}
