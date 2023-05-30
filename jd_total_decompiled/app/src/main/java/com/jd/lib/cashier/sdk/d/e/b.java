package com.jd.lib.cashier.sdk.d.e;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IMta;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class b {
    public static void a(Context context, int i2, String str, String str2) {
        c(context, i2, str, "", str2, "");
    }

    public static void b(Context context, int i2, String str, String str2, String str3) {
        c(context, i2, str, str2, str3, "");
    }

    public static void c(Context context, int i2, String str, String str2, String str3, String str4) {
        try {
            IMta mta = DependInitializer.getMta();
            if (context != null && mta != null) {
                String str5 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
                if (i2 == 1000) {
                    mta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, str5, context.getClass().getName(), str4, str3);
                } else if (i2 == 2000) {
                    mta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_Finish", context.getClass().getName(), str4, str3);
                } else if (i2 == 3000) {
                    mta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str4, str3);
                } else if (i2 == 4000) {
                    mta.sendExposureDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_TryHome", context.getClass().getName(), str4, str3);
                }
            }
            r.b("CashierMtaUtil", str + "---expo--->" + str3);
        } catch (Exception e2) {
            r.c("CashierMtaUtil", e2);
        }
    }

    public static void d(Context context, int i2, String str, String str2, String str3) {
        c(context, i2, str, "", str2, str3);
    }

    public static void e(Context context, int i2, String str, String str2) {
        f(context, i2, str, str2, "");
    }

    public static void f(Context context, int i2, String str, String str2, String str3) {
        try {
            IMta mta = DependInitializer.getMta();
            if (context != null && mta != null) {
                String str4 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
                if (i2 == 1000) {
                    mta.sendCommonData(context.getApplicationContext(), str, str2, str4, context.getClass().getName(), str3);
                } else if (i2 == 2000) {
                    mta.sendCommonData(context.getApplicationContext(), str, str2, "JDCashierNew_Finish", context.getClass().getName(), str3);
                } else if (i2 == 3000) {
                    mta.sendCommonData(context.getApplicationContext(), str, str2, "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str3);
                } else if (i2 == 4000) {
                    mta.sendCommonData(context.getApplicationContext(), str, str2, "JDCashierNew_TryHome", context.getClass().getName(), str3);
                }
            }
            r.b("CashierMtaUtil", str + "---click--->" + str2);
        } catch (Exception e2) {
            r.d("CashierMtaUtil", e2.getMessage());
        }
    }

    public static void g(Context context, int i2, String str, String str2) {
        h(context, i2, str, "", str2, "");
    }

    public static void h(Context context, int i2, String str, String str2, String str3, String str4) {
        try {
            IMta mta = DependInitializer.getMta();
            if (context == null || mta == null) {
                return;
            }
            String str5 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
            if (i2 == 1000) {
                mta.sendClickDataWithExt(context.getApplicationContext(), str, str2, str5, context.getClass().getName(), str4, str3);
            } else if (i2 == 2000) {
                mta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_Finish", context.getClass().getName(), str4, str3);
            } else if (i2 == 3000) {
                mta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str4, str3);
            } else if (i2 == 4000) {
                mta.sendClickDataWithExt(context.getApplicationContext(), str, str2, "JDCashierNew_TryHome", context.getClass().getName(), str4, str3);
            }
            r.b("CashierMtaUtil", str + "---click--->" + str3);
        } catch (Exception e2) {
            r.d("CashierMtaUtil", e2.getMessage());
        }
    }

    public static void i(Context context, int i2, String str, String str2, String str3) {
        h(context, i2, str, "", str2, str3);
    }

    public static void j(Context context, int i2, String str) {
        try {
            IMta mta = DependInitializer.getMta();
            if (context != null && mta != null) {
                String str2 = m.f().g() ? "JDCashierNew_PopupLayer" : "JDCashierNew_Home";
                if (i2 == 1000) {
                    mta.sendPagePv(context.getApplicationContext(), str2, context.getClass().getName(), str);
                } else if (i2 == 2000) {
                    mta.sendPagePv(context.getApplicationContext(), "JDCashierNew_Finish", context.getClass().getName(), str);
                } else if (i2 == 3000) {
                    mta.sendPagePv(context.getApplicationContext(), "JDCashierNew_PayBehalfRequest", context.getClass().getName(), str);
                } else if (i2 == 4000) {
                    mta.sendPagePv(context.getApplicationContext(), "JDCashierNew_TryHome", context.getClass().getName(), str);
                }
            }
            r.b("CashierMtaUtil", i2 + "---sendPv--->" + str);
        } catch (Exception e2) {
            r.d("CashierMtaUtil", e2.getMessage());
        }
    }
}
