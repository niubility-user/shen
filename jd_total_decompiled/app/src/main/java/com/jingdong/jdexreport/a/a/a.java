package com.jingdong.jdexreport.a.a;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class a {
    public static boolean a = true;
    public static String b = "\t:\t";

    /* renamed from: c  reason: collision with root package name */
    public static String f12574c = "\n";
    public static boolean d;

    public static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format("%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    public static String b() {
        return a ? "https://ex.m.jd.com/exception_monitor/report" : "https://ex.m.jd.care/exception_monitor/report";
    }

    public static void a(String str, String str2) {
        boolean z = d;
    }

    public static boolean a(String str) {
        return Pattern.compile("[0-9]*").matcher(str.trim()).matches();
    }

    public static Long a(String str, long j2) {
        Long valueOf;
        if (a(str)) {
            try {
                valueOf = Long.valueOf(Long.parseLong(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                valueOf = Long.valueOf(j2);
            }
            return valueOf.longValue() < 0 ? Long.valueOf(j2) : valueOf;
        }
        return Long.valueOf(j2);
    }

    public static boolean a(Context context) {
        String trim = a(Process.myPid()).trim();
        return (TextUtils.isEmpty(trim) || context == null || !TextUtils.equals(trim, context.getPackageName())) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0090: MOVE (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:54:0x0090 */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(int i2) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        IOException e2;
        BufferedReader bufferedReader2;
        FileNotFoundException e3;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3;
        InputStreamReader inputStreamReader4 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/" + i2 + "/cmdline"));
            } catch (FileNotFoundException e4) {
                inputStreamReader = null;
                e3 = e4;
                bufferedReader2 = null;
            } catch (IOException e5) {
                inputStreamReader = null;
                e2 = e5;
                bufferedReader2 = null;
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
                if (inputStreamReader4 != null) {
                }
                if (bufferedReader != null) {
                }
                throw th;
            }
            try {
                bufferedReader2 = new BufferedReader(inputStreamReader);
            } catch (FileNotFoundException e6) {
                e3 = e6;
                bufferedReader2 = null;
            } catch (IOException e7) {
                e2 = e7;
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader3 = inputStreamReader;
                bufferedReader = null;
                inputStreamReader4 = inputStreamReader3;
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
                    } catch (IOException unused) {
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
                    inputStreamReader.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
                try {
                    bufferedReader2.close();
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
                if (bufferedReader2 == null) {
                    return "";
                }
                try {
                    bufferedReader2.close();
                    return "";
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
                if (bufferedReader2 == null) {
                    return "";
                }
                bufferedReader2.close();
                return "";
            }
        } catch (Throwable th3) {
            inputStreamReader3 = inputStreamReader2;
            bufferedReader = i2;
            th = th3;
        }
    }
}
