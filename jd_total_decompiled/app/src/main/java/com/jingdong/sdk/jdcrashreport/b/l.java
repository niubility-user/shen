package com.jingdong.sdk.jdcrashreport.b;

import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.util.List;

/* loaded from: classes7.dex */
public class l {
    public static void a(CrashInfo crashInfo, JDCrashReportListener jDCrashReportListener) {
        if (jDCrashReportListener != null) {
            jDCrashReportListener.onStart(crashInfo);
        }
        try {
            d.c(new h(crashInfo, jDCrashReportListener));
        } catch (Exception e2) {
            r.c("JDCrashReport", "DefaultCrashReporter report failed", e2);
            if (jDCrashReportListener != null) {
                jDCrashReportListener.onError(0, e2.getMessage(), crashInfo);
            }
        }
    }

    public static void b(List<CrashInfo> list) {
        try {
            d.c(new h(list));
        } catch (Exception e2) {
            r.c("JDCrashReport", "DefaultCrashReporter report failed", e2);
        }
    }
}
