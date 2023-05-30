package com.tencent.smtt.sdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes9.dex */
public class a {
    public static int a = 600;
    private static int b;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
        r2 = r2.substring(r4 + 9).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r2 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r2.length() == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        if (r2.contains("k") == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        r3 = java.lang.Integer.parseInt(r2.substring(0, r2.indexOf("k")).trim()) / 1024;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0056 -> B:47:0x0073). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int a() {
        String readLine;
        int i2 = b;
        if (i2 > 0) {
            return i2;
        }
        BufferedReader bufferedReader = null;
        int i3 = 0;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                    while (true) {
                        try {
                            readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            int indexOf = readLine.indexOf("MemTotal:");
                            if (-1 != indexOf) {
                                break;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            b = i3;
                            return i3;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            b = i3;
                            return i3;
                        }
                    }
                    bufferedReader2.close();
                    bufferedReader = readLine;
                } catch (IOException e3) {
                    e = e3;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                bufferedReader = bufferedReader;
            }
            b = i3;
            return i3;
        } catch (Throwable th3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th3;
        }
    }
}
