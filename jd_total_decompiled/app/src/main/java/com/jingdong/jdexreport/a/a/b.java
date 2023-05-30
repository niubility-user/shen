package com.jingdong.jdexreport.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes.dex */
public class b {
    public static String a = "JDExReport";

    public static String a(Context context, String str) {
        return context == null ? "" : PreferenceManager.getDefaultSharedPreferences(context).getString(str, "");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(Context context, String str) {
        Exception exc;
        BufferedReader bufferedReader;
        Exception e2;
        String str2;
        String str3 = "";
        if (context == null) {
            return "";
        }
        String absolutePath = e.a(context).getAbsolutePath();
        String packageName = context.getPackageName();
        File file = new File(absolutePath + "/" + packageName + "/" + a);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(absolutePath + "/" + packageName + "/" + a + "/" + str);
        if (!file2.exists()) {
            return "";
        }
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file2));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            str3 = str3 + readLine + a.f12574c;
                        } else {
                            str2 = str3.substring(0, str3.lastIndexOf(a.f12574c));
                            try {
                                bufferedReader.close();
                                try {
                                    bufferedReader.close();
                                    return str2;
                                } catch (IOException e3) {
                                    e = e3;
                                    e.printStackTrace();
                                    return str2;
                                }
                            } catch (Exception e4) {
                                exc = e4;
                                str3 = str2;
                                e2 = exc;
                                e2.printStackTrace();
                                if (bufferedReader != null) {
                                }
                                return str3;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e6) {
                    e2 = e6;
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                            e = e7;
                            str2 = str3;
                            e.printStackTrace();
                            return str2;
                        }
                    }
                    return str3;
                }
            }
        } catch (Exception e8) {
            exc = e8;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void b(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        String absolutePath = e.a(context).getAbsolutePath();
        String packageName = context.getPackageName();
        File file = new File(absolutePath + "/" + packageName + "/" + a);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(absolutePath + "/" + packageName + "/" + a + "/" + str);
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file2);
            fileWriter.write(str2);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
