package com.jingdong.sdk.jdupgrade.a.j;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
public class d {
    private static File a;

    public static File a() {
        File file;
        try {
            String a2 = k.a("DOWNLOAD_DIR_SP_2", "");
            if (!TextUtils.isEmpty(a2.trim())) {
                File file2 = new File(a2.trim());
                a = file2;
                if (((file2.exists() && a.isDirectory()) || a.mkdirs()) && a.canWrite()) {
                    return a;
                }
                a = null;
            }
            if (a == null) {
                a = b();
            }
            if (a == null) {
                a = c();
            }
            if (a == null) {
                a = com.jingdong.sdk.jdupgrade.a.c.j().getCacheDir();
            }
            file = a;
        } catch (Throwable unused) {
        }
        if (file == null) {
            return null;
        }
        if ((file.exists() && a.isDirectory()) || a.mkdirs()) {
            k.b("DOWNLOAD_DIR_SP_2", a.getAbsolutePath());
            return a;
        }
        return null;
    }

    public static String a(File file) {
        int i2;
        if (file != null && file.isFile() && file.exists()) {
            FileInputStream fileInputStream = null;
            StringBuilder sb = new StringBuilder();
            byte[] bArr = new byte[8192];
            try {
                try {
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        while (true) {
                            try {
                                int read = fileInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                messageDigest.update(bArr, 0, read);
                            } catch (Exception e2) {
                                e = e2;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                return sb.toString();
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        for (byte b : messageDigest.digest()) {
                            String hexString = Integer.toHexString(b & 255);
                            if (hexString.length() == 1) {
                                hexString = "0" + hexString;
                            }
                            sb.append(hexString);
                        }
                        fileInputStream2.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            return sb.toString();
        }
        return "";
    }

    public static boolean a(String str) {
        h.a("", "delete file:" + str);
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.isFile() && file.delete();
        }
        return true;
    }

    static File b() {
        if (com.jingdong.sdk.jdupgrade.a.c.j() == null) {
            return null;
        }
        return com.jingdong.sdk.jdupgrade.a.c.j().getExternalCacheDir();
    }

    static File c() {
        if (com.jingdong.sdk.jdupgrade.a.c.j() == null) {
            return null;
        }
        return com.jingdong.sdk.jdupgrade.a.c.j().getExternalFilesDir(null);
    }
}
