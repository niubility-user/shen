package com.jingdong.sdk.jdhttpdns.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.core.HttpDnsImpl;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes7.dex */
public class PackageInfoUtils {
    private static String packName = "";
    private static int versionCode;
    private static String versionName;

    public static String getPackName() {
        String str = packName;
        if (str != null && !str.isEmpty()) {
            return packName;
        }
        try {
            String processName = getProcessName(Process.myPid());
            if (processName != null && !processName.isEmpty()) {
                packName = processName;
            }
        } catch (Throwable th) {
            if (DNSLog.D) {
                th.printStackTrace();
            }
            packName = "";
        }
        return packName;
    }

    private static PackageInfo getPackageInfo() {
        try {
            Context context = HttpDnsImpl.applicationContext;
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static PackageManager getPackageManager() {
        return HttpDnsImpl.applicationContext.getPackageManager();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.BufferedReader] */
    private static String getProcessName(int i2) {
        InputStreamReader inputStreamReader;
        Throwable th;
        IOException e2;
        BufferedReader bufferedReader;
        FileNotFoundException e3;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/" + ((int) i2) + "/cmdline"));
            } catch (FileNotFoundException e4) {
                inputStreamReader = null;
                e3 = e4;
                bufferedReader = null;
            } catch (IOException e5) {
                inputStreamReader = null;
                e2 = e5;
                bufferedReader = null;
            } catch (Throwable th2) {
                inputStreamReader = null;
                th = th2;
                i2 = 0;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (FileNotFoundException e6) {
                e3 = e6;
                bufferedReader = null;
            } catch (IOException e7) {
                e2 = e7;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                i2 = 0;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
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
                    e9.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                return str;
            } catch (FileNotFoundException e10) {
                e3 = e10;
                e3.printStackTrace();
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
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
                e2.printStackTrace();
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e13) {
                        e13.printStackTrace();
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

    public static int getVersionCode() {
        if (versionCode == 0) {
            PackageInfo packageInfo = getPackageInfo();
            versionCode = packageInfo == null ? 0 : packageInfo.versionCode;
        }
        return versionCode;
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            PackageInfo packageInfo = getPackageInfo();
            versionName = packageInfo == null ? "" : packageInfo.versionName;
        }
        return versionName;
    }
}
