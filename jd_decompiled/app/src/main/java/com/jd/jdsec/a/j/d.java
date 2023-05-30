package com.jd.jdsec.a.j;

import java.io.File;

/* loaded from: classes13.dex */
public class d {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e0  */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Boolean a(java.lang.String r5, java.lang.String r6) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.j.d.a(java.lang.String, java.lang.String):java.lang.Boolean");
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                return d(str);
            }
            return c(str);
        }
        return false;
    }

    private static boolean c(String str) {
        String str2 = File.separator;
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            boolean z = true;
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    z = d(file2.getAbsolutePath());
                    if (!z) {
                        break;
                    }
                } else {
                    if (file2.isDirectory() && !(z = c(file2.getAbsolutePath()))) {
                        break;
                    }
                }
            }
            if (z && file.delete()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean d(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }
}
