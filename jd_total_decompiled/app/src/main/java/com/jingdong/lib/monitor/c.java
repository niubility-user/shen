package com.jingdong.lib.monitor;

import android.os.SystemClock;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes14.dex */
public class c {
    static {
        try {
            new File(PermissionHelper.getExternalFilesDir("crash"), "crash");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("MonitorController", e2);
            }
        }
    }

    public static String a(long j2) {
        long j3 = j2 / 1000;
        long j4 = j3 / 3600;
        long j5 = j3 % 3600;
        return j4 + ":" + (j5 / 60) + ":" + (j5 % 60);
    }

    public static String b() {
        return a(SystemClock.uptimeMillis() - BaseApplication.startTime) + " runRealTime :" + a(SystemClock.elapsedRealtime() - BaseApplication.startRealTime);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0082 -> B:60:0x0085). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean c(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        Process process;
        Exception e2;
        try {
            try {
                process = Runtime.getRuntime().exec("ls -l " + str);
            } catch (Exception e3) {
                bufferedReader = null;
                e2 = e3;
                process = null;
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
                process = null;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            } catch (Exception e4) {
                bufferedReader = null;
                e2 = e4;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                if (process != null) {
                }
                if (bufferedReader != null) {
                }
                throw th;
            }
        } catch (IOException e5) {
            OKLog.e("MonitorController", e5);
        }
        try {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && readLine.length() >= 4) {
                    char charAt = readLine.charAt(3);
                    if (charAt == 's' || charAt == 'x') {
                        if (process != null) {
                            process.destroy();
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            OKLog.e("MonitorController", e6);
                        }
                        return true;
                    }
                }
                if (process != null) {
                    process.destroy();
                }
                bufferedReader.close();
            } catch (Throwable th4) {
                th = th4;
                if (process != null) {
                    process.destroy();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e7) {
                        OKLog.e("MonitorController", e7);
                    }
                }
                throw th;
            }
        } catch (Exception e8) {
            e2 = e8;
            OKLog.e("MonitorController", e2);
            if (process != null) {
                process.destroy();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
                return false;
            }
            return false;
        }
        return false;
    }

    public static boolean d() {
        if (new File("/system/bin/su").exists() && c("/system/bin/su")) {
            return true;
        }
        return new File("/system/xbin/su").exists() && c("/system/xbin/su");
    }
}
