package com.jingdong.aura.core.util;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes4.dex */
public class g {
    private static String a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x009d: MOVE (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:61:0x009d */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(int i2) {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader;
        Throwable th;
        InputStreamReader inputStreamReader3;
        IOException e2;
        BufferedReader bufferedReader2;
        FileNotFoundException e3;
        InputStreamReader inputStreamReader4 = null;
        try {
            try {
                try {
                    inputStreamReader3 = new InputStreamReader(new FileInputStream("/proc/" + i2 + "/cmdline"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader3);
                    } catch (FileNotFoundException e4) {
                        e3 = e4;
                        bufferedReader2 = null;
                    } catch (IOException e5) {
                        e2 = e5;
                        bufferedReader2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader2 = inputStreamReader3;
                        bufferedReader = null;
                        inputStreamReader4 = inputStreamReader2;
                        if (inputStreamReader4 != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    inputStreamReader3 = null;
                    e3 = e6;
                    bufferedReader2 = null;
                } catch (IOException e7) {
                    inputStreamReader3 = null;
                    e2 = e7;
                    bufferedReader2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                    if (inputStreamReader4 != null) {
                        try {
                            inputStreamReader4.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    char[] cArr = new char[64];
                    bufferedReader2.read(cArr);
                    int i3 = 0;
                    for (int i4 = 0; i4 < 64 && cArr[i4] != 0; i4++) {
                        i3++;
                    }
                    String str = new String(cArr, 0, i3);
                    try {
                        inputStreamReader3.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                    try {
                        bufferedReader2.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    }
                    return str;
                } catch (FileNotFoundException e12) {
                    e3 = e12;
                    e3.printStackTrace();
                    if (inputStreamReader3 != null) {
                        try {
                            inputStreamReader3.close();
                        } catch (IOException e13) {
                            e13.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        return "";
                    }
                    return "";
                } catch (IOException e14) {
                    e2 = e14;
                    e2.printStackTrace();
                    if (inputStreamReader3 != null) {
                        try {
                            inputStreamReader3.close();
                        } catch (IOException e15) {
                            e15.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        return "";
                    }
                    return "";
                }
            } catch (IOException e16) {
                e16.printStackTrace();
                return "";
            }
        } catch (Throwable th4) {
            inputStreamReader2 = inputStreamReader;
            bufferedReader = i2;
            th = th4;
        }
    }

    public static String b(Application application) {
        String str = a;
        if (str != null) {
            return str;
        }
        String c2 = c(application);
        a = c2;
        return c2;
    }

    private static String c(Application application) {
        FileInputStream fileInputStream;
        int read;
        String processName = Build.VERSION.SDK_INT > 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
            int myPid = Process.myPid();
            if (application == null || myPid <= 0) {
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
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
                return str;
            }
            fileInputStream.close();
            return "";
        }
        return processName;
    }

    public static boolean d(Application application) {
        return a(application, a(application));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0.length() == 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean a(Application application, String str) {
        if (TextUtils.isEmpty(str) || application == null) {
            return false;
        }
        String str2 = null;
        try {
            str2 = b(application);
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

    private static String a(Application application) {
        return application.getPackageName();
    }
}
