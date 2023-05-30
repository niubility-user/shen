package com.jd.stat.common;

import android.os.Build;
import android.os.SystemClock;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;

/* loaded from: classes18.dex */
public class s {
    public static String a() {
        return (SystemClock.elapsedRealtime() / 1000) + "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.Closeable] */
    public static String b() {
        BufferedReader bufferedReader;
        Exception e2;
        FileReader fileReader;
        String str = "";
        FileReader fileReader2 = 26;
        if (Build.VERSION.SDK_INT >= 26) {
            return "";
        }
        try {
            try {
                fileReader = new FileReader("/proc/uptime");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Exception e3) {
                    bufferedReader = null;
                    e2 = e3;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                    fileReader2 = fileReader;
                    th = th;
                    com.jd.stat.common.b.g.a((Closeable) fileReader2);
                    com.jd.stat.common.b.g.a(bufferedReader);
                    throw th;
                }
            } catch (Exception e4) {
                bufferedReader = null;
                e2 = e4;
                fileReader = null;
            } catch (Throwable th2) {
                th = th2;
                fileReader2 = null;
                bufferedReader = null;
            }
            try {
                str = (Float.parseFloat(bufferedReader.readLine().split(LangUtils.SINGLE_SPACE)[1]) / Runtime.getRuntime().availableProcessors()) + "";
                fileReader2 = fileReader;
            } catch (Exception e5) {
                e2 = e5;
                e2.printStackTrace();
                fileReader2 = fileReader;
                com.jd.stat.common.b.g.a((Closeable) fileReader2);
                com.jd.stat.common.b.g.a(bufferedReader);
                return str;
            }
            com.jd.stat.common.b.g.a((Closeable) fileReader2);
            com.jd.stat.common.b.g.a(bufferedReader);
            return str;
        } catch (Throwable th3) {
            th = th3;
            com.jd.stat.common.b.g.a((Closeable) fileReader2);
            com.jd.stat.common.b.g.a(bufferedReader);
            throw th;
        }
    }
}
