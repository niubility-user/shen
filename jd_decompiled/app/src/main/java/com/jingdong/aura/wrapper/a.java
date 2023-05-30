package com.jingdong.aura.wrapper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.jingdong.aura.a.b.e;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: classes4.dex */
public class a {
    private static int a = -1;
    private static final String[] b = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    public static void a(Context context) {
        try {
            b.f12261c = a(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e2) {
            e.a("com.jingdong.aura", "name not fount", "ApkUtils.initHostKey_1", e2);
            e2.printStackTrace();
        } catch (Throwable th) {
            e.a("com.jingdong.aura", "name not fount", "ApkUtils.initHostKey_1", th);
            th.printStackTrace();
        }
    }

    public static boolean b() {
        int i2 = a;
        boolean z = false;
        if (i2 != -1) {
            return i2 == 1;
        }
        try {
            z = a();
            a = z ? 1 : 0;
            return z;
        } catch (Throwable unused) {
            e.a("isRootSystem", "isRootSystem", "isRootSystem", "check root state fail", "isRootSystem", null);
            return z;
        }
    }

    public static final String a(String str) {
        JarFile jarFile;
        JarEntry jarEntry;
        Certificate[] a2;
        try {
            try {
                jarFile = new JarFile(str);
            } catch (IOException unused) {
                return null;
            }
        } catch (Throwable th) {
            th = th;
            jarFile = null;
        }
        try {
            jarEntry = jarFile.getJarEntry("classes.dex");
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                return null;
            } catch (Throwable th3) {
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th3;
            }
        }
        if (jarEntry != null && (a2 = a(jarFile, jarEntry, new byte[4096])) != null) {
            String a3 = a(((X509Certificate) a2[0]).getEncoded());
            try {
                jarFile.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return a3;
        }
        try {
            jarFile.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
            do {
            } while (bufferedInputStream.read(bArr, 0, bArr.length) != -1);
            bufferedInputStream.close();
            if (jarEntry != null) {
                return jarEntry.getCertificates();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static boolean a() {
        boolean z;
        String[] strArr = b;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            } else if (new File(strArr[i2]).exists()) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        String str = Build.TAGS;
        return (str != null && str.contains("test-keys")) || z;
    }
}
