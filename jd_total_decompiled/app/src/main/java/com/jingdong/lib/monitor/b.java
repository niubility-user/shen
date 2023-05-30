package com.jingdong.lib.monitor;

import android.app.ActivityManager;
import android.os.Build;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class b {
    public static String a() {
        StringBuilder sb = new StringBuilder();
        String b = b(Runtime.getRuntime().maxMemory());
        String b2 = b(Runtime.getRuntime().totalMemory());
        String b3 = b(Runtime.getRuntime().freeMemory());
        sb.append("Runtime memory( ");
        sb.append("maxMemory = " + b + ", ");
        sb.append("totalMemory = " + b2 + ", ");
        sb.append("freeMemory = " + b3 + " ) ; ");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        sb.append("MemoryInfo (");
        int i2 = Build.VERSION.SDK_INT;
        ((ActivityManager) JdSdk.getInstance().getApplication().getSystemService("activity")).getMemoryInfo(memoryInfo);
        if (i2 >= 16) {
            sb.append("\u603b\u5185\u5b58\uff1a" + b(memoryInfo.totalMem) + ", ");
        }
        sb.append("\u603b\u53ef\u7528\u5185\u5b58\uff1a" + b(memoryInfo.availMem) + " , ");
        sb.append("lowMemory\uff1a" + memoryInfo.lowMemory + " ) ");
        if (OKLog.D) {
            OKLog.d("MemInfo", sb.toString());
        }
        return sb.toString();
    }

    private static String b(long j2) {
        if (j2 <= 0) {
            return "0byte";
        }
        if (j2 < 1048576) {
            double d = j2;
            Double.isNaN(d);
            return String.format("%.1fKB", Double.valueOf(d / 1024.0d));
        }
        double d2 = j2;
        Double.isNaN(d2);
        return String.format("%.1fMB", Double.valueOf(d2 / 1048576.0d));
    }
}
