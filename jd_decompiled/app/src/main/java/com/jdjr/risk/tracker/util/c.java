package com.jdjr.risk.tracker.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes18.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0028 A[Catch: Exception -> 0x0048, TryCatch #0 {Exception -> 0x0048, blocks: (B:2:0x0000, B:5:0x0010, B:7:0x0016, B:11:0x0028, B:12:0x0038, B:8:0x0022), top: B:17:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r5, java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> L48
            r0.<init>(r6)     // Catch: java.lang.Exception -> L48
            boolean r1 = r0.exists()     // Catch: java.lang.Exception -> L48
            java.lang.String r2 = "data"
            java.lang.String r3 = "ISO8859-1"
            r4 = 0
            if (r1 == 0) goto L25
            java.lang.String r1 = b(r6, r3)     // Catch: java.lang.Exception -> L48
            if (r1 == 0) goto L22
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L48
            r4.<init>(r1)     // Catch: java.lang.Exception -> L48
            java.lang.Object r1 = r4.get(r2)     // Catch: java.lang.Exception -> L48
            org.json.JSONArray r1 = (org.json.JSONArray) r1     // Catch: java.lang.Exception -> L48
            goto L26
        L22:
            r0.delete()     // Catch: java.lang.Exception -> L48
        L25:
            r1 = r4
        L26:
            if (r4 != 0) goto L38
            r0.createNewFile()     // Catch: java.lang.Exception -> L48
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L48
            r4.<init>()     // Catch: java.lang.Exception -> L48
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L48
            r1.<init>()     // Catch: java.lang.Exception -> L48
            r4.put(r2, r1)     // Catch: java.lang.Exception -> L48
        L38:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L48
            r0.<init>(r5)     // Catch: java.lang.Exception -> L48
            r1.put(r0)     // Catch: java.lang.Exception -> L48
            java.lang.String r5 = r4.toString()     // Catch: java.lang.Exception -> L48
            a(r6, r5, r3)     // Catch: java.lang.Exception -> L48
            return
        L48:
            r5 = move-exception
            r5.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.tracker.util.c.a(java.lang.String, java.lang.String):void");
    }

    public static void a(String str, String str2, String str3) {
        try {
            a(str, str2.getBytes(str3));
        } catch (Exception unused) {
        }
    }

    public static boolean a(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return file.delete();
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String b(String str) {
        try {
            if (new File(str).exists()) {
                return b(str, "ISO8859-1");
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String b(String str, String str2) {
        try {
            byte[] c2 = c(str);
            if (c2 != null) {
                return new String(c2, str2);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static byte[] c(String str) {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th) {
            th = th;
            try {
                byteArrayOutputStream.close();
                bufferedInputStream2.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                byteArrayOutputStream.close();
                bufferedInputStream2.close();
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            try {
                byteArrayOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return null;
        }
    }
}
