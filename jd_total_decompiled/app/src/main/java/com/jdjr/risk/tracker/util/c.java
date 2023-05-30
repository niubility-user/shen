package com.jdjr.risk.tracker.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0028 A[Catch: Exception -> 0x0048, TryCatch #0 {Exception -> 0x0048, blocks: (B:2:0x0000, B:5:0x0010, B:7:0x0016, B:11:0x0028, B:12:0x0038, B:8:0x0022), top: B:17:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(String str, String str2) {
        JSONArray jSONArray;
        try {
            File file = new File(str2);
            JSONObject jSONObject = null;
            if (file.exists()) {
                String b = b(str2, "ISO8859-1");
                if (b != null) {
                    jSONObject = new JSONObject(b);
                    jSONArray = (JSONArray) jSONObject.get("data");
                    if (jSONObject == null) {
                        file.createNewFile();
                        jSONObject = new JSONObject();
                        jSONArray = new JSONArray();
                        jSONObject.put("data", jSONArray);
                    }
                    jSONArray.put(new JSONObject(str));
                    a(str2, jSONObject.toString(), "ISO8859-1");
                }
                file.delete();
            }
            jSONArray = null;
            if (jSONObject == null) {
            }
            jSONArray.put(new JSONObject(str));
            a(str2, jSONObject.toString(), "ISO8859-1");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
