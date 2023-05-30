package com.jingdong.sdk.baseinfo.c;

import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.Logger;
import java.io.BufferedReader;
import java.io.FileReader;

/* loaded from: classes.dex */
public final class b {
    public static String a(int i2) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i2 + "/cmdline"));
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            String str = TextUtils.isEmpty(readLine) ? "" : readLine;
            try {
                bufferedReader.close();
            } catch (Exception unused) {
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            try {
                Logger.e("BaseInfo.ProcessUtil", "getProcessName failed", th);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception unused2) {
                    }
                }
                return "";
            } catch (Throwable th3) {
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th3;
            }
        }
    }
}
