package com.jingdong.manto.m.n1;

import com.jingdong.manto.utils.MantoLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/* loaded from: classes15.dex */
public class p {
    public static int a(String... strArr) {
        int i2 = 0;
        for (String str : strArr) {
            if (str != null) {
                i2 += str.length();
            }
        }
        return i2;
    }

    private static String a(File file) {
        BufferedReader bufferedReader;
        if (!file.exists()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (Throwable unused) {
        }
        try {
            char[] cArr = new char[1024];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read == -1) {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
                stringBuffer.append(String.valueOf(cArr, 0, read));
            }
        } catch (Throwable unused2) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception unused3) {
                }
            }
            return "";
        }
    }

    public static void a(String str) {
        MantoLog.i("JsApiStorageHelper", "deleteTmpFile: " + str + ":" + new File(com.jingdong.manto.c.a().getCacheDir(), str).delete());
    }

    public static void a(String str, String... strArr) {
        try {
            FileWriter fileWriter = new FileWriter(new File(com.jingdong.manto.c.a().getCacheDir(), str));
            for (String str2 : strArr) {
                fileWriter.write(str2);
            }
            fileWriter.close();
        } catch (Throwable unused) {
        }
    }

    public static String b(String str) {
        return a(new File(com.jingdong.manto.c.a().getCacheDir(), str));
    }
}
