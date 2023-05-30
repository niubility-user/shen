package com.jingdong.aura.sdk.update.b;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes4.dex */
public final class m {
    private static String a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.BufferedReader] */
    private static String a(int i2) {
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
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    return str;
                } catch (FileNotFoundException e7) {
                    e3 = e7;
                    e3.printStackTrace();
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bufferedReader == null) {
                        return "";
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                        return "";
                    }
                } catch (IOException e9) {
                    e2 = e9;
                    e2.printStackTrace();
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                    if (bufferedReader == null) {
                        return "";
                    }
                    bufferedReader.close();
                }
            } catch (FileNotFoundException e11) {
                e3 = e11;
                bufferedReader = null;
            } catch (IOException e12) {
                e2 = e12;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                i2 = 0;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e13) {
                        e13.printStackTrace();
                    }
                }
                if (i2 != 0) {
                    try {
                        i2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static boolean a(Context context) {
        return a(context, context.getPackageName());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r0.length() == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = null;
        try {
            String str3 = a;
            if (str3 != null) {
                str2 = str3;
            } else {
                String b = b(context);
                a = b;
                str2 = b;
            }
            if (str2 != null) {
            }
            str2 = "";
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = a(Process.myPid()).trim();
        }
        return str.equals(str2);
    }

    private static String b(Context context) {
        FileInputStream fileInputStream;
        int read;
        String processName = Build.VERSION.SDK_INT > 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
            int myPid = Process.myPid();
            if (context == null || myPid <= 0) {
                return "";
            }
            byte[] bArr = new byte[128];
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream("/proc/" + myPid + "/cmdline");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    read = fileInputStream.read(bArr);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    try {
                        th.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return "";
                    } catch (Throwable th3) {
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                            }
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
            if (read <= 0) {
                fileInputStream.close();
                return "";
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (bArr[i2] <= 128 && bArr[i2] > 0) {
                }
                read = i2;
                break;
            }
            String str = new String(bArr, 0, read);
            try {
                fileInputStream.close();
            } catch (Throwable th6) {
                th6.printStackTrace();
            }
            return str;
        }
        return processName;
    }
}
