package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class b {
    public static String a = "";
    public static String b = "";

    /* renamed from: c */
    public static String f17908c = "";
    public static String d = "";

    /* renamed from: e */
    public static String f17909e = "";

    /* renamed from: f */
    public static String f17910f = "";

    /* renamed from: g */
    private static boolean f17911g;

    /* renamed from: h */
    private static boolean f17912h;

    /* renamed from: i */
    private static boolean f17913i;

    public static String a() {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        String str = "os.arch";
        if (!f17912h) {
            String str2 = null;
            try {
                inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream());
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                inputStreamReader = null;
            }
            try {
                str = b(bufferedReader.readLine().contains("x86") ? "i686" : System.getProperty("os.arch"));
                if (str != null) {
                    f17908c = str;
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    str2 = b(System.getProperty(str));
                    th.printStackTrace();
                } finally {
                    if (str2 != null) {
                        f17908c = str2;
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                }
            }
            try {
                inputStreamReader.close();
            } catch (IOException unused4) {
                f17912h = true;
                return f17908c;
            }
        }
        return f17908c;
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0026 A[Catch: Exception -> 0x002b, TRY_LEAVE, TryCatch #0 {Exception -> 0x002b, blocks: (B:24:0x0003, B:26:0x0013, B:28:0x0017, B:30:0x001a, B:34:0x0026, B:31:0x001e), top: B:38:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.content.Context r4, java.io.File r5) {
        /*
            java.lang.String r0 = "AppUtil"
            r1 = 0
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch: java.lang.Exception -> L2b
            java.lang.String r2 = r5.getAbsolutePath()     // Catch: java.lang.Exception -> L2b
            r3 = 65
            android.content.pm.PackageInfo r4 = r4.getPackageArchiveInfo(r2, r3)     // Catch: java.lang.Exception -> L2b
            if (r4 == 0) goto L23
            android.content.pm.Signature[] r4 = r4.signatures     // Catch: java.lang.Exception -> L2b
            if (r4 == 0) goto L1e
            int r2 = r4.length     // Catch: java.lang.Exception -> L2b
            if (r2 <= 0) goto L1e
            r2 = 0
            r4 = r4[r2]     // Catch: java.lang.Exception -> L2b
            goto L24
        L1e:
            java.lang.String r4 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r0, r4)     // Catch: java.lang.Exception -> L2b
        L23:
            r4 = r1
        L24:
            if (r4 == 0) goto L44
            java.lang.String r1 = r4.toCharsString()     // Catch: java.lang.Exception -> L2b
            goto L44
        L2b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r2 = "getSign "
            r4.append(r2)
            r4.append(r5)
            java.lang.String r5 = "failed"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r4)
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File):java.lang.String");
    }

    public static String a(Context context, String str) {
        String str2 = null;
        try {
            str2 = String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str));
            return String.valueOf(Integer.toHexString(Integer.parseInt(str2)));
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0066 A[Catch: all -> 0x0077, TryCatch #2 {all -> 0x0077, blocks: (B:100:0x0056, B:102:0x0066, B:104:0x0071), top: B:115:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r5, boolean r6, java.io.File r7) {
        /*
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = ""
            if (r7 == 0) goto Lb4
            boolean r2 = r7.exists()
            if (r2 != 0) goto Le
            goto Lb4
        Le:
            if (r6 == 0) goto L56
            r6 = 0
            r2 = 2
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            java.lang.String r4 = "r"
            r3.<init>(r7, r4)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r3.read(r2)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L4b
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L4b
            r6.<init>(r2)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L4b
            java.lang.String r2 = "PK"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L4b
            if (r6 != 0) goto L34
            r3.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r5 = move-exception
            r5.printStackTrace()
        L33:
            return r1
        L34:
            r3.close()     // Catch: java.io.IOException -> L46
            goto L56
        L38:
            r6 = move-exception
            goto L3f
        L3a:
            r5 = move-exception
            goto L4d
        L3c:
            r1 = move-exception
            r3 = r6
            r6 = r1
        L3f:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            r3.close()     // Catch: java.io.IOException -> L46
            goto L56
        L46:
            r6 = move-exception
            r6.printStackTrace()
            goto L56
        L4b:
            r5 = move-exception
            r6 = r3
        L4d:
            r6.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r6 = move-exception
            r6.printStackTrace()
        L55:
            throw r5
        L56:
            android.content.Context r6 = r5.getApplicationContext()     // Catch: java.lang.Throwable -> L77
            java.lang.String r6 = r6.getPackageName()     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = "com.jd.jrapp"
            boolean r6 = r6.contains(r1)     // Catch: java.lang.Throwable -> L77
            if (r6 == 0) goto L7c
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)     // Catch: java.lang.Throwable -> L77
            java.lang.String r6 = a(r7)     // Catch: java.lang.Throwable -> L77
            if (r6 == 0) goto L7c
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #2"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch: java.lang.Throwable -> L77
            return r6
        L77:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #3"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L7c:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #4"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r5 = a(r5, r7)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  android api signature="
            r6.append(r1)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            if (r5 != 0) goto Lb3
            java.lang.String r5 = a(r7)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "[AppUtil.getSignatureFromApk]  java get signature="
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        Lb3:
            return r5
        Lb4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, boolean, java.io.File):java.lang.String");
    }

    private static String a(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a2 = a(a(jarFile, jarFile.getJarEntry("AndroidManifest.xml"), bArr)[0].getEncoded());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                if (name != null) {
                    Certificate[] a3 = a(jarFile, nextElement, bArr);
                    String a4 = a3 != null ? a(a3[0].getEncoded()) : null;
                    if (a4 == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!a4.equals(a2)) {
                        return null;
                    }
                }
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArr[i2];
            int i3 = (b2 >> 4) & 15;
            int i4 = i2 * 2;
            cArr[i4] = (char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48);
            int i5 = b2 & 15;
            cArr[i4 + 1] = (char) (i5 >= 10 ? (i5 + 97) - 10 : i5 + 48);
        }
        return new String(cArr);
    }

    public static void a(String str, boolean z) {
        if (z) {
            str = k.a(str);
        }
        a = str;
    }

    public static boolean a(String str) {
        Matcher matcher;
        try {
            matcher = Pattern.compile("i686|mips|x86_64|x86").matcher(str);
        } catch (Exception unused) {
            matcher = null;
        }
        return matcher == null || !matcher.find();
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) throws Exception {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    public static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static String b(String str) {
        return str == null ? "" : str;
    }

    public static void b(Context context, String str) {
        try {
            TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_GUID, str);
            tbsDownloadConfig.commit();
        } catch (Exception unused) {
        }
    }

    public static boolean b() {
        Matcher matcher;
        try {
            matcher = Pattern.compile("i686|mips|x86_64|x86").matcher(f17908c);
        } catch (Exception unused) {
            matcher = null;
        }
        return matcher == null || !matcher.find();
    }

    public static String c(Context context) {
        try {
            return TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_GUID, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean c() {
        Class<?> cls;
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 21 || (cls = Class.forName("dalvik.system.VMRuntime")) == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
            return false;
        }
        Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
        if (invoke2 instanceof Boolean) {
            return ((Boolean) invoke2).booleanValue();
        }
        return false;
    }

    public static String d(Context context) {
        if (!f17911g) {
            try {
                if (TextUtils.isEmpty(a)) {
                    a = k.a(context);
                }
            } catch (Exception e2) {
                TbsLog.i(e2);
            }
            f17911g = true;
        }
        return a;
    }

    public static String e(Context context) {
        return "";
    }

    public static String f(Context context) {
        return "02:00:00:00:00:00";
    }

    public static String g(Context context) {
        return "";
    }
}
