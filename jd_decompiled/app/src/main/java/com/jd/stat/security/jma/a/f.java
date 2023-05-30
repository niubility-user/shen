package com.jd.stat.security.jma.a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* loaded from: classes18.dex */
public class f {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> a(java.lang.String r6) {
        /*
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            r1.<init>()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            boolean r6 = r2.exists()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            if (r6 == 0) goto L35
            boolean r6 = r2.isFile()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            if (r6 == 0) goto L35
            java.io.FileReader r6 = new java.io.FileReader     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L61
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L55 java.io.FileNotFoundException -> L62
            r2.<init>(r6)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L55 java.io.FileNotFoundException -> L62
        L21:
            java.lang.String r3 = r2.readLine()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L55 java.io.FileNotFoundException -> L62
            if (r3 == 0) goto L31
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L55 java.io.FileNotFoundException -> L62
            if (r4 != 0) goto L21
            r1.add(r3)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L55 java.io.FileNotFoundException -> L62
            goto L21
        L31:
            r0 = r6
            goto L35
        L33:
            r1 = move-exception
            goto L47
        L35:
            if (r0 == 0) goto L3f
            r0.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r6 = move-exception
            r6.printStackTrace()
        L3f:
            return r1
        L40:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L56
        L45:
            r1 = move-exception
            r6 = r0
        L47:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L54
            r6.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r6 = move-exception
            r6.printStackTrace()
        L54:
            return r0
        L55:
            r0 = move-exception
        L56:
            if (r6 == 0) goto L60
            r6.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r6 = move-exception
            r6.printStackTrace()
        L60:
            throw r0
        L61:
            r6 = r0
        L62:
            if (r6 == 0) goto L6c
            r6.close()     // Catch: java.io.IOException -> L68
            goto L6c
        L68:
            r6 = move-exception
            r6.printStackTrace()
        L6c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.security.jma.a.f.a(java.lang.String):java.util.List");
    }

    public static void a(String str, String str2, boolean z) {
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, z)));
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.newLine();
                        bufferedWriter2.close();
                    } catch (Exception e3) {
                        e = e3;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }
}
