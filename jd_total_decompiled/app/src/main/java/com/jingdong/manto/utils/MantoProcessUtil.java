package com.jingdong.manto.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.a;
import java.io.FileInputStream;

/* loaded from: classes16.dex */
public class MantoProcessUtil {
    public static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static a.C0232a f14295c;

    public static Context getContext() {
        return f14295c.a();
    }

    public static String getProcessName() {
        if (b == null) {
            initProcessName(f14295c.a());
        }
        String str = b;
        return str == null ? "" : str;
    }

    private static String getProcessNameInternal(Context context) {
        FileInputStream fileInputStream;
        String processName = Build.VERSION.SDK_INT > 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
            int myPid = Process.myPid();
            if (context != null && myPid > 0) {
                byte[] bArr = new byte[128];
                FileInputStream fileInputStream2 = null;
                try {
                    fileInputStream = new FileInputStream("/proc/" + myPid + "/cmdline");
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            for (int i2 = 0; i2 < read; i2++) {
                                if (bArr[i2] <= 128 && bArr[i2] > 0) {
                                }
                                read = i2;
                                break;
                            }
                            String str = new String(bArr, 0, read);
                            try {
                                fileInputStream.close();
                            } catch (Throwable unused) {
                            }
                            return str;
                        }
                    } catch (Throwable unused2) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream = fileInputStream2;
                            fileInputStream.close();
                        }
                        return "";
                    }
                } catch (Throwable unused3) {
                }
                try {
                    fileInputStream.close();
                } catch (Throwable unused4) {
                }
            }
            return "";
        }
        return processName;
    }

    private static void initProcessName(Context context) {
        if (context == null) {
            return;
        }
        try {
            a = context.getPackageName();
            b = getProcessNameInternal(context);
        } catch (Exception unused) {
        }
    }

    public static boolean isMainProcess() {
        if (b == null) {
            initProcessName(f14295c.a());
        }
        return TextUtils.equals(a, b);
    }

    public static boolean isMantoProcess() {
        if (b == null) {
            initProcessName(f14295c.a());
        }
        return !TextUtils.isEmpty(b) && b.contains(":manto");
    }

    public static void setConfig(a.C0232a c0232a) {
        f14295c = c0232a;
    }
}
