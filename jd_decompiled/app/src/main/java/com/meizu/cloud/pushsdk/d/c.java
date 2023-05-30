package com.meizu.cloud.pushsdk.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushinternal.DebugLogger;

/* loaded from: classes14.dex */
public class c {
    private static String a;
    private static String b;

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            String c2 = c(context);
            a = c2;
            if (TextUtils.isEmpty(c2)) {
                try {
                    a = (String) Class.forName("com.meizu.cloud.utils.AppDeviceUtils").getDeclaredMethod("getId", Context.class).invoke(null, context);
                } catch (Exception e2) {
                    DebugLogger.e("DeviceUtils", "getDeviceId error " + e2.getMessage());
                    a = "";
                }
                if (a == null) {
                    a = "";
                }
                return a;
            }
            return a;
        }
        return a;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(b)) {
            try {
                b = (String) Class.forName("com.meizu.cloud.utils.AppDeviceUtils").getDeclaredMethod("getFdId", Context.class).invoke(null, context);
            } catch (Exception e2) {
                DebugLogger.e("DeviceUtils", "getFdId error " + e2.getMessage());
                b = "";
            }
            if (b == null) {
                b = "";
            }
            return b;
        }
        return b;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0052 -> B:16:0x0053). Please submit an issue!!! */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String c(Context context) {
        com.meizu.cloud.pushsdk.d.l.d b2;
        try {
            b2 = com.meizu.cloud.pushsdk.d.l.a.b("android.telephony.MzTelephonyManager").d("getDeviceId", new Class[0]).b(new Object[0]);
        } catch (Exception e2) {
            DebugLogger.e("DeviceUtils", "getDeviceId error " + e2.getMessage());
        }
        if (!b2.a || TextUtils.isEmpty((CharSequence) b2.b)) {
            if (((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)) != null) {
                return BaseInfo.getDeviceId();
            }
            return null;
        }
        return (String) b2.b;
    }
}
