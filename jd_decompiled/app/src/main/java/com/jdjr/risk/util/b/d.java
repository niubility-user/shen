package com.jdjr.risk.util.b;

import android.content.Context;
import com.jdjr.wsm4.Wsm4Manager;
import java.io.FileOutputStream;

/* loaded from: classes18.dex */
public class d {
    private static String a = "JDT-RISK-BIOMETRIC";

    public static void a(Context context, String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bytes = Wsm4Manager.newInstance(context).wsm4Encrypt(str).getBytes();
            if (bytes != null) {
                fileOutputStream = context.openFileOutput(str2, 0);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
            }
            if (fileOutputStream == null) {
                return;
            }
        } catch (Throwable unused) {
            if (fileOutputStream == null) {
                return;
            }
        }
        try {
            fileOutputStream.close();
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r4 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
        if (r4 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            java.io.FileInputStream r4 = r3.openFileInput(r4)     // Catch: java.lang.Throwable -> L28
            int r1 = r4.available()     // Catch: java.lang.Throwable -> L29
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L29
            int r2 = r4.read(r1)     // Catch: java.lang.Throwable -> L29
            if (r2 <= 0) goto L22
            com.jdjr.wsm4.Wsm4Manager r3 = com.jdjr.wsm4.Wsm4Manager.newInstance(r3)     // Catch: java.lang.Throwable -> L29
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L29
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L29
            java.lang.String r3 = r3.wsm4Decrypt(r2)     // Catch: java.lang.Throwable -> L29
            byte[] r0 = r3.getBytes()     // Catch: java.lang.Throwable -> L29
        L22:
            if (r4 == 0) goto L2c
        L24:
            r4.close()     // Catch: java.lang.Throwable -> L2c
            goto L2c
        L28:
            r4 = r0
        L29:
            if (r4 == 0) goto L2c
            goto L24
        L2c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.util.b.d.a(android.content.Context, java.lang.String):byte[]");
    }
}
