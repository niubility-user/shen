package com.jd.security.jdguard.f;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes17.dex */
public class f {
    private static String a;

    private static String a() {
        return com.jd.security.jdguard.b.d().f().getPackageName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.io.BufferedReader] */
    public static String b(int i2) {
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
                            d.d("ProcessUtil", e6);
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
                    d.d("ProcessUtil", e9);
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                return str;
            } catch (FileNotFoundException e10) {
                e3 = e10;
                d.e(e3);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e11) {
                        d.d("ProcessUtil", e11);
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
                d.e(e2);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e13) {
                        d.d("ProcessUtil", e13);
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

    public static String c(Context context) {
        String str = a;
        if (str != null) {
            return str;
        }
        String d = d(context);
        a = d;
        return d;
    }

    private static String d(Context context) {
        FileInputStream fileInputStream;
        int read;
        String processName = BaseInfo.getAndroidSDKVersion() >= 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
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
        return processName;
    }

    public static boolean e() {
        return f(a());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r0.length() == 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = null;
        try {
            str2 = c(com.jd.security.jdguard.b.d().f());
            if (str2 != null) {
            }
            str2 = "";
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = b(Process.myPid()).trim();
        }
        return str.equals(str2);
    }
}
