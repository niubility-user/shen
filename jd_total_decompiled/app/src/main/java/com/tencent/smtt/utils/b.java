package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
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

    /* JADX WARN: Removed duplicated region for block: B:55:0x0026 A[Catch: Exception -> 0x002b, TRY_LEAVE, TryCatch #0 {Exception -> 0x002b, blocks: (B:45:0x0003, B:47:0x0013, B:49:0x0017, B:51:0x001a, B:55:0x0026, B:52:0x001e), top: B:59:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(Context context, File file) {
        Signature signature;
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 65);
            if (packageArchiveInfo != null) {
                Signature[] signatureArr = packageArchiveInfo.signatures;
                if (signatureArr != null && signatureArr.length > 0) {
                    signature = signatureArr[0];
                    if (signature == null) {
                        return signature.toCharsString();
                    }
                    return null;
                }
                TbsLog.w("AppUtil", "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!");
            }
            signature = null;
            if (signature == null) {
            }
        } catch (Exception unused) {
            TbsLog.i("AppUtil", "getSign " + file + JDReactConstant.FAILED);
            return null;
        }
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

    /* JADX WARN: Removed duplicated region for block: B:165:0x0066 A[Catch: all -> 0x0077, TryCatch #2 {all -> 0x0077, blocks: (B:163:0x0056, B:165:0x0066, B:167:0x0071), top: B:178:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:188:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(Context context, boolean z, File file) {
        String a2;
        RandomAccessFile randomAccessFile;
        Exception e2;
        byte[] bArr;
        if (file == null || !file.exists()) {
            return "";
        }
        if (z) {
            RandomAccessFile randomAccessFile2 = null;
            try {
                try {
                    bArr = new byte[2];
                    randomAccessFile = new RandomAccessFile(file, "r");
                } catch (Exception e3) {
                    randomAccessFile = null;
                    e2 = e3;
                } catch (Throwable th) {
                    th = th;
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
                try {
                    try {
                        randomAccessFile.read(bArr);
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile;
                        randomAccessFile2.close();
                        throw th;
                    }
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    randomAccessFile.close();
                    if (context.getApplicationContext().getPackageName().contains("com.jd.jrapp")) {
                    }
                    TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #4");
                    a2 = a(context, file);
                    TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  android api signature=" + a2);
                    if (a2 != null) {
                    }
                }
                if (!new String(bArr).equalsIgnoreCase("PK")) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    return "";
                }
                randomAccessFile.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
        try {
            if (context.getApplicationContext().getPackageName().contains("com.jd.jrapp")) {
                TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #1");
                String a3 = a(file);
                if (a3 != null) {
                    TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #2");
                    return a3;
                }
            }
        } catch (Throwable unused) {
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #3");
        }
        TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #4");
        a2 = a(context, file);
        TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  android api signature=" + a2);
        if (a2 != null) {
            String a4 = a(file);
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  java get signature=" + a4);
            return a4;
        }
        return a2;
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
