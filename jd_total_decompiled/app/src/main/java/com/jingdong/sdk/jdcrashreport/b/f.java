package com.jingdong.sdk.jdcrashreport.b;

import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.b.w;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.File;
import java.util.Locale;

/* loaded from: classes7.dex */
public class f implements w.d<Boolean, String> {
    private static boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements JDCrashReportListener {
        a(f fVar) {
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onEnd(int i2, String str, CrashInfo crashInfo) {
            o.b(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))));
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onError(int i2, String str, CrashInfo crashInfo) {
            j.d(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))), crashInfo);
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onStart(CrashInfo crashInfo) {
        }
    }

    private f() {
        a = true;
    }

    @Override // com.jingdong.sdk.jdcrashreport.b.w.d
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public String a(Boolean bool) {
        if (!bool.booleanValue()) {
            a = false;
            return "No Crash Files Found";
        }
        File[] h2 = j.h();
        if (h2 == null || h2.length <= 0) {
            return "No Crash Files Found";
        }
        int length = h2.length;
        for (File file : h2) {
            if (file.isFile() && file.getName().startsWith("crash_info_") && file.canRead()) {
                CrashInfo a2 = j.a(file);
                if (a2 == null) {
                    return "crashInfo is null, do not upload!";
                }
                JDCrashReportListener Y = com.jingdong.sdk.jdcrashreport.d.Y();
                if (Y == null) {
                    Y = new a(this);
                }
                j.g(a2, Y);
            }
        }
        a = false;
        return length + " Crash Files Found";
    }

    public static f a() {
        if (a) {
            return null;
        }
        return new f();
    }
}
