package com.jingdong.sdk.jdcrashreport.b;

import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.File;
import java.util.Locale;

/* loaded from: classes7.dex */
public class g implements JDCrashReportListener {
    @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
    public void onEnd(int i2, String str, CrashInfo crashInfo) {
        r.b("JDCrashReport", "upload crash finish, code is " + i2 + ", msg is " + str);
        o.b(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))));
        r.b("JDCrashReport", "delete cache file");
    }

    @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
    public void onError(int i2, String str, CrashInfo crashInfo) {
        r.b("JDCrashReport", "upload anr error: code is " + i2 + ", msg is " + str);
        j.d(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))), crashInfo);
        r.b("JDCrashReport", "cache crash");
    }

    @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
    public void onStart(CrashInfo crashInfo) {
    }
}
