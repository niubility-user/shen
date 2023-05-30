package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdma.minterface.JDMABaseInfo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/* loaded from: classes12.dex */
public class i {
    static String a = "";
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static int f12708c;

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (TextUtils.isEmpty(a)) {
            a = a(Process.myPid());
        }
        return !TextUtils.isEmpty(a) && context.getPackageName().equals(a);
    }

    public static String b() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getOsVersionName();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } else if (c.p) {
            if (TextUtils.isEmpty(b)) {
                b = Build.VERSION.RELEASE;
            }
            return b;
        } else {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007d, code lost:
        if (r2 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0097, code lost:
        if (r2 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0099, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009c, code lost:
        return "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
        return "";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(int i2) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3 = null;
        try {
            try {
                InputStreamReader inputStreamReader4 = new InputStreamReader(new FileInputStream("/proc/" + i2 + "/cmdline"));
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader4);
                    try {
                        char[] cArr = new char[64];
                        bufferedReader2.read(cArr);
                        int i3 = 0;
                        for (int i4 = 0; i4 < 64 && cArr[i4] != 0; i4++) {
                            i3++;
                        }
                        String str = new String(cArr, 0, i3);
                        try {
                            inputStreamReader4.close();
                        } catch (Throwable th) {
                            LogUtil.d("OsUtil", th.toString());
                        }
                        try {
                            bufferedReader2.close();
                        } catch (Throwable unused) {
                        }
                        return str;
                    } catch (FileNotFoundException e2) {
                        inputStreamReader2 = inputStreamReader4;
                        bufferedReader = bufferedReader2;
                        e = e2;
                        inputStreamReader3 = inputStreamReader2;
                        LogUtil.d("OsUtil", e.toString());
                        if (inputStreamReader3 != null) {
                            try {
                                inputStreamReader3.close();
                            } catch (Throwable th2) {
                                LogUtil.d("OsUtil", th2.toString());
                            }
                        }
                    } catch (Throwable th3) {
                        inputStreamReader = inputStreamReader4;
                        bufferedReader = bufferedReader2;
                        th = th3;
                        inputStreamReader3 = inputStreamReader;
                        LogUtil.d("OsUtil", th.toString());
                        if (inputStreamReader3 != null) {
                            try {
                                inputStreamReader3.close();
                            } catch (Throwable th4) {
                                LogUtil.d("OsUtil", th4.toString());
                            }
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                    inputStreamReader2 = inputStreamReader4;
                    bufferedReader = null;
                } catch (Throwable th5) {
                    th = th5;
                    inputStreamReader = inputStreamReader4;
                    bufferedReader = null;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                bufferedReader = null;
            } catch (Throwable th6) {
                th = th6;
                bufferedReader = null;
            }
        } catch (Throwable th7) {
            if (inputStreamReader3 != null) {
                try {
                    inputStreamReader3.close();
                } catch (Throwable th8) {
                    LogUtil.d("OsUtil", th8.toString());
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable unused2) {
                }
            }
            throw th7;
        }
    }

    public static int a() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            return jDMABaseInfo.getOsVersionInt();
        }
        if (c.p) {
            if (f12708c == 0) {
                f12708c = Build.VERSION.SDK_INT;
            }
            return f12708c;
        }
        return 0;
    }
}
