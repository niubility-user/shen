package com.jingdong.sdk.jdcrashreport.e.a;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class c {
    public static CrashInfo a(a aVar) {
        LinkedHashMap<String, String> a;
        if (aVar == null) {
            return null;
        }
        CrashInfo createCrashInfo = CrashInfo.createCrashInfo();
        createCrashInfo.msgType = "2";
        createCrashInfo.busiType = "anr";
        createCrashInfo.crashTime = aVar.d();
        createCrashInfo.crashStack = aVar.f();
        createCrashInfo.crashType = aVar.a();
        createCrashInfo.crashLine = c(aVar.g(), aVar.i());
        createCrashInfo.allThreadStack = null;
        createCrashInfo.processName = aVar.a();
        createCrashInfo.threadName = "main";
        try {
            com.jingdong.sdk.jdcrashreport.a Z = com.jingdong.sdk.jdcrashreport.d.Z();
            if (Z != null && (a = Z.a("anr", createCrashInfo.crashStack)) != null) {
                createCrashInfo.extraInfo = a;
                createCrashInfo.feedback.putAll(a);
            }
        } catch (Throwable unused) {
        }
        return createCrashInfo;
    }

    public static String b() {
        Thread thread = Looper.getMainLooper().getThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append("---- main(");
        sb.append(thread.getId());
        sb.append(") ");
        sb.append(thread.getState());
        sb.append(" ----\n");
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("   ");
            sb.append(stackTraceElement.toString());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    private static String c(String str, boolean z) {
        String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        String str2 = "";
        if (!z) {
            for (Pattern pattern : com.jingdong.sdk.jdcrashreport.d.V()) {
                for (String str3 : split) {
                    Matcher matcher = pattern.matcher(str3.trim());
                    if (matcher.find()) {
                        return matcher.group();
                    }
                }
            }
            return "";
        }
        boolean z2 = false;
        for (Pattern pattern2 : com.jingdong.sdk.jdcrashreport.d.V()) {
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String trim = split[i2].trim();
                if (trim.startsWith("at ")) {
                    Matcher matcher2 = pattern2.matcher(trim.substring(3));
                    if (matcher2.find()) {
                        str2 = matcher2.group();
                        z2 = true;
                        break;
                    }
                }
                i2++;
            }
            if (z2) {
                break;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            for (String str4 : split) {
                String trim2 = str4.trim();
                if (!trim2.startsWith("native: #") && !trim2.startsWith("#")) {
                    if (trim2.startsWith("at ")) {
                        return trim2.substring(3);
                    }
                } else {
                    return trim2.substring(trim2.indexOf("#"));
                }
            }
            return str2;
        }
        return str2;
    }

    public static boolean d(Context context, long j2) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            r.b("JDCrashReport", "checkProcessAnrState, ActivityManager is null");
            return false;
        }
        int myPid = Process.myPid();
        long j3 = 100;
        long j4 = j2 / j3;
        for (int i2 = 0; i2 < j4; i2++) {
            try {
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                        if (processErrorStateInfo.pid == myPid && processErrorStateInfo.condition == 2) {
                            r.b("JDCrashReport", String.format(Locale.getDefault(), "checkProcessAnrState, process %d is anr.", Integer.valueOf(myPid)));
                            return true;
                        }
                    }
                }
                Thread.sleep(j3);
            } catch (Exception unused) {
            }
        }
        r.b("JDCrashReport", String.format(Locale.getDefault(), "checkProcessAnrState, process %d is not anr.", Integer.valueOf(myPid)));
        return false;
    }
}
