package com.jd.lib.cashier.sdk.d.e;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IABMta;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class a extends b {
    public static void k(Context context, int i2, String str, String str2, String str3, String str4, HashMap<String, String> hashMap) {
        try {
            IABMta aBMta = DependInitializer.getABMta();
            if (context != null && aBMta != null) {
                String str5 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
                if (i2 == 1000) {
                    aBMta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, str5, context.getClass().getName(), str4, str3, hashMap);
                } else if (i2 == 2000) {
                    aBMta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_Finish", context.getClass().getName(), str4, str3, hashMap);
                } else if (i2 == 3000) {
                    aBMta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str4, str3, hashMap);
                } else if (i2 == 4000) {
                    aBMta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_TryHome", context.getClass().getName(), str4, str3, hashMap);
                }
            }
            r.b("CashierABMtaUtil", str + "---ab test expo--->" + str3);
        } catch (Exception e2) {
            r.c("CashierABMtaUtil", e2);
        }
    }

    public static void l(Context context, int i2, String str, String str2, String str3, String str4, HashMap<String, String> hashMap) {
        try {
            IABMta aBMta = DependInitializer.getABMta();
            if (context == null || aBMta == null) {
                return;
            }
            String str5 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
            if (i2 == 1000) {
                aBMta.sendClickDataWithExt(context.getApplicationContext(), str, str2, str5, context.getClass().getName(), str4, str3, hashMap);
            } else if (i2 == 2000) {
                aBMta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_Finish", context.getClass().getName(), str4, str3, hashMap);
            } else if (i2 == 3000) {
                aBMta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str4, str3, hashMap);
            } else if (i2 == 4000) {
                aBMta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_TryHome", context.getClass().getName(), str4, str3, hashMap);
            }
            r.b("CashierABMtaUtil", str + "---ab test click--->" + str3);
        } catch (Exception e2) {
            r.d("CashierABMtaUtil", e2.getMessage());
        }
    }

    public static void m(Context context, int i2, String str, String str2, HashMap<String, String> hashMap) {
        l(context, i2, str, "", str2, "", hashMap);
    }

    public static void n(Context context, int i2, String str, String str2, String str3, HashMap<String, String> hashMap) {
        l(context, i2, str, "", str2, str3, hashMap);
    }
}
