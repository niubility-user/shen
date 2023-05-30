package com.jd.android.sdk.coreinfo.util;

import android.os.Build;
import android.os.Looper;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class CommandUtil {
    public static String handlerCommand(String str) {
        return handlerCommand(str, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004d, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (0 == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String handlerCommand(String str, boolean z) {
        String a;
        String str2 = "";
        Process process = null;
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                process = Runtime.getRuntime().exec(str);
                process.getOutputStream().close();
                c cVar = new c(process.getInputStream(), z);
                cVar.start();
                if (Build.VERSION.SDK_INT >= 26) {
                    if (process.waitFor(2L, TimeUnit.SECONDS)) {
                        a = cVar.a();
                        str2 = a;
                    }
                } else if (process.waitFor() == 0) {
                    a = cVar.a();
                    str2 = a;
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (0 == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String handlerCommand(String[] strArr, boolean z) {
        String a;
        String str = "";
        Process process = null;
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                process = new ProcessBuilder(strArr).start();
                process.getOutputStream().close();
                c cVar = new c(process.getInputStream(), z);
                cVar.start();
                if (Build.VERSION.SDK_INT >= 26) {
                    if (process.waitFor(2L, TimeUnit.SECONDS)) {
                        a = cVar.a();
                        str = a;
                    }
                } else if (process.waitFor() == 0) {
                    a = cVar.a();
                    str = a;
                }
            }
        } catch (Throwable unused) {
        }
    }
}
