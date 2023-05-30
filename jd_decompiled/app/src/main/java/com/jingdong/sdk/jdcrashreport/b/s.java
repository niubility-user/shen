package com.jingdong.sdk.jdcrashreport.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Locale;

@SuppressLint({"NewApi"})
/* loaded from: classes7.dex */
public class s {
    private static ActivityManager a;

    private static long a(Context context) {
        int totalPss;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{Process.myPid()});
            if (processMemoryInfo.length > 0) {
                totalPss = processMemoryInfo[0].getTotalPss();
                return totalPss << 10;
            }
        }
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        totalPss = memoryInfo.getTotalPss();
        return totalPss << 10;
    }

    private static String b(long j2) {
        if (j2 < 1024) {
            return "1024byte";
        } else if (j2 < 1048576) {
            Locale locale = Locale.getDefault();
            double d = j2;
            double d2 = 1024;
            Double.isNaN(d);
            Double.isNaN(d2);
            return String.format(locale, "%.1fKB", Double.valueOf(d / d2));
        } else if (j2 < 1073741824) {
            Locale locale2 = Locale.getDefault();
            double d3 = j2;
            double d4 = 1048576;
            Double.isNaN(d3);
            Double.isNaN(d4);
            return String.format(locale2, "%.1fMB", Double.valueOf(d3 / d4));
        } else {
            Locale locale3 = Locale.getDefault();
            double d5 = j2;
            double d6 = 1073741824;
            Double.isNaN(d5);
            Double.isNaN(d6);
            return String.format(locale3, "%.1fGB", Double.valueOf(d5 / d6));
        }
    }

    public static boolean c() {
        if (a == null) {
            a = (ActivityManager) com.jingdong.sdk.jdcrashreport.d.I().getSystemService("activity");
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            a.getMemoryInfo(memoryInfo);
            return memoryInfo.lowMemory;
        } catch (Throwable th) {
            r.g("", th);
            return false;
        }
    }

    public static String d() {
        return b(BaseInfo.getMemTotalSize() << 10);
    }

    public static String e() {
        return b(BaseInfo.getMemAvailSize() << 10);
    }

    public static String f() {
        return b(BaseInfo.getRomSize());
    }

    public static String g() {
        return b(i());
    }

    public static String h() {
        return b(a(com.jingdong.sdk.jdcrashreport.d.I()));
    }

    private static long i() {
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (n.g() >= 18) {
                availableBlocks = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            } else {
                availableBlocks = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
            return availableBlocks;
        } catch (Throwable th) {
            r.g("[MemUtil]", th);
            return -1L;
        }
    }
}
