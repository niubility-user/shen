package com.jingdong.sdk.jdcrashreport.crash.jni;

import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.o;
import com.jingdong.sdk.jdcrashreport.b.w;
import com.jingdong.sdk.jdcrashreport.b.x;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import com.jingdong.sdk.jdcrashreport.d;
import java.io.File;
import java.util.Locale;

/* loaded from: classes7.dex */
public class a implements w.d<Boolean, Boolean> {
    private static boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.jdcrashreport.crash.jni.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public class C0716a implements JDCrashReportListener {
        C0716a(a aVar) {
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

    private a() {
        a = true;
    }

    private String c(String str) {
        return x.c(Long.parseLong(str.substring(12, str.indexOf(".txt"))));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:(1:126)(19:61|(2:62|(3:66|(3:78|79|80)(3:68|69|(3:75|76|77)(3:71|72|73))|74)(1:125))|83|(2:84|(3:86|(1:96)(3:88|89|91)|92)(0))|98|99|(1:101)(1:121)|102|(1:104)|105|(1:107)|108|(1:110)|111|(1:113)|114|(1:116)(1:120)|117|118)|97|98|99|(0)(0)|102|(0)|105|(0)|108|(0)|111|(0)|114|(0)(0)|117|118) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01ab, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01ac, code lost:
        com.jingdong.sdk.jdcrashreport.b.r.g("NativeCrashHandleTask", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x025e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0202  */
    @Override // com.jingdong.sdk.jdcrashreport.b.w.d
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Boolean a(java.lang.Boolean r22) {
        /*
            Method dump skipped, instructions count: 674
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.crash.jni.a.a(java.lang.Boolean):java.lang.Boolean");
    }

    public static a a() {
        if (d.E() && !a) {
            return new a();
        }
        return null;
    }
}
