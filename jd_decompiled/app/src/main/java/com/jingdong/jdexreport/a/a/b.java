package com.jingdong.jdexreport.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.File;
import java.io.FileWriter;

/* loaded from: classes.dex */
public class b {
    public static String a = "JDExReport";

    public static String a(Context context, String str) {
        return context == null ? "" : PreferenceManager.getDefaultSharedPreferences(context).getString(str, "");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = ""
            if (r7 != 0) goto L5
            return r0
        L5:
            java.io.File r1 = com.jingdong.jdexreport.a.a.e.a(r7)
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r7 = r7.getPackageName()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r4 = "/"
            r3.append(r4)
            r3.append(r7)
            r3.append(r4)
            java.lang.String r5 = com.jingdong.jdexreport.a.a.b.a
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            boolean r3 = r2.exists()
            if (r3 != 0) goto L3b
            r2.mkdirs()
        L3b:
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            r3.append(r4)
            r3.append(r7)
            r3.append(r4)
            java.lang.String r7 = com.jingdong.jdexreport.a.a.b.a
            r3.append(r7)
            r3.append(r4)
            r3.append(r8)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7)
            boolean r7 = r2.exists()
            if (r7 != 0) goto L67
            return r0
        L67:
            r7 = 0
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r1.<init>(r2)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
            r8.<init>(r1)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> La9
        L72:
            java.lang.String r7 = r8.readLine()     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            if (r7 == 0) goto L8d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r1.<init>()     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r1.append(r0)     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r1.append(r7)     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            java.lang.String r7 = com.jingdong.jdexreport.a.a.a.f12574c     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r1.append(r7)     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            goto L72
        L8d:
            java.lang.String r7 = com.jingdong.jdexreport.a.a.a.f12574c     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            int r7 = r0.lastIndexOf(r7)     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r1 = 0
            java.lang.String r7 = r0.substring(r1, r7)     // Catch: java.lang.Exception -> La5 java.lang.Throwable -> Lbe
            r8.close()     // Catch: java.lang.Exception -> La1 java.lang.Throwable -> Lbe
            r8.close()     // Catch: java.io.IOException -> L9f
            goto Lbd
        L9f:
            r8 = move-exception
            goto Lb8
        La1:
            r0 = move-exception
            r6 = r0
            r0 = r7
            goto Lac
        La5:
            r7 = move-exception
            goto Lad
        La7:
            r8 = move-exception
            goto Lc2
        La9:
            r8 = move-exception
            r6 = r8
            r8 = r7
        Lac:
            r7 = r6
        Lad:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> Lbe
            if (r8 == 0) goto Lbc
            r8.close()     // Catch: java.io.IOException -> Lb6
            goto Lbc
        Lb6:
            r8 = move-exception
            r7 = r0
        Lb8:
            r8.printStackTrace()
            goto Lbd
        Lbc:
            r7 = r0
        Lbd:
            return r7
        Lbe:
            r7 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        Lc2:
            if (r7 == 0) goto Lcc
            r7.close()     // Catch: java.io.IOException -> Lc8
            goto Lcc
        Lc8:
            r7 = move-exception
            r7.printStackTrace()
        Lcc:
            goto Lce
        Lcd:
            throw r8
        Lce:
            goto Lcd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.a.a.b.b(android.content.Context, java.lang.String):java.lang.String");
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
