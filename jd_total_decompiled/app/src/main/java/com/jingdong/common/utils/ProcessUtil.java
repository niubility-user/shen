package com.jingdong.common.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public class ProcessUtil {
    private static final String TAG = "ProcessUtil";
    private static String processName;

    private static String getPackageName() {
        return JdSdk.getInstance().getApplication().getPackageName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.io.BufferedReader] */
    public static String getProcessName(int i2) {
        InputStreamReader inputStreamReader;
        Throwable th;
        IOException e2;
        BufferedReader bufferedReader;
        FileNotFoundException e3;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/" + ((int) i2) + "/cmdline"));
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (FileNotFoundException e4) {
                    e3 = e4;
                    bufferedReader = null;
                } catch (IOException e5) {
                    e2 = e5;
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    i2 = 0;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e6) {
                            OKLog.e(TAG, e6);
                        }
                    }
                    if (i2 != 0) {
                        try {
                            i2.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                inputStreamReader = null;
                e3 = e7;
                bufferedReader = null;
            } catch (IOException e8) {
                inputStreamReader = null;
                e2 = e8;
                bufferedReader = null;
            } catch (Throwable th3) {
                inputStreamReader = null;
                th = th3;
                i2 = 0;
            }
            try {
                char[] cArr = new char[64];
                bufferedReader.read(cArr);
                int i3 = 0;
                for (int i4 = 0; i4 < 64 && cArr[i4] != 0; i4++) {
                    i3++;
                }
                String str = new String(cArr, 0, i3);
                try {
                    inputStreamReader.close();
                } catch (IOException e9) {
                    OKLog.e(TAG, e9);
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                return str;
            } catch (FileNotFoundException e10) {
                e3 = e10;
                OKLog.e(TAG, e3);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e11) {
                        OKLog.e(TAG, e11);
                    }
                }
                if (bufferedReader == null) {
                    return "";
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused3) {
                    return "";
                }
            } catch (IOException e12) {
                e2 = e12;
                OKLog.e(TAG, e2);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e13) {
                        OKLog.e(TAG, e13);
                    }
                }
                if (bufferedReader == null) {
                    return "";
                }
                bufferedReader.close();
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static String getProcessNameInternal(Context context) {
        FileInputStream fileInputStream;
        int read;
        String processName2 = Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName2)) {
            int myPid = Process.myPid();
            if (context != null && myPid > 0) {
                byte[] bArr = new byte[128];
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream("/proc/" + myPid + "/cmdline");
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                }
                try {
                    read = fileInputStream.read(bArr);
                } catch (Throwable unused3) {
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return "";
                }
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
                    } catch (Throwable unused4) {
                    }
                    return str;
                }
                fileInputStream.close();
            }
            return "";
        }
        return processName2;
    }

    public static boolean isCrashReportProcess() {
        return isProcess(getPackageName() + ":jdcrashreport");
    }

    public static boolean isErrorProcess() {
        return isProcess(getPackageName() + ":error");
    }

    public static boolean isForeground() {
        return BackForegroundWatcher.getInstance().isAppForeground();
    }

    public static boolean isMainProcess() {
        return isProcess(getPackageName());
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0013, code lost:
        if (r0.length() == 0) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isMantoProcess() {
        String str = null;
        try {
            str = getProcessName(JdSdk.getInstance().getApplication());
            if (str != null) {
            }
            str = "";
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(str)) {
            str = getProcessName(Process.myPid()).trim();
        }
        if (str != null) {
            return str.contains(":manto");
        }
        return false;
    }

    public static boolean isPatchProcess() {
        return isProcess(getPackageName() + ":patch");
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x001b, code lost:
        if (r0.length() == 0) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isProcess(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = null;
        try {
            str2 = getProcessName(JdSdk.getInstance().getApplication());
            if (str2 != null) {
            }
            str2 = "";
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = getProcessName(Process.myPid()).trim();
        }
        return str.equals(str2);
    }

    public static boolean isPushProcess() {
        if (!isProcess(getPackageName() + ":jdpush")) {
            if (!isProcess(getPackageName() + ":pushservice")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeModeProcess() {
        return isProcess(getPackageName() + ":safeMode");
    }

    public static boolean isWatchDogProcess() {
        return isProcess(getPackageName() + ":WatchDogService");
    }

    public static String getProcessName(Context context) {
        String str = processName;
        if (str != null) {
            return str;
        }
        String processNameInternal = getProcessNameInternal(context);
        processName = processNameInternal;
        return processNameInternal;
    }
}
