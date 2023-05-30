package com.tencent.open.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Environment;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class k {
    private static ConcurrentHashMap<String, com.tencent.a.a.a> a = new ConcurrentHashMap<>();

    public static String a(int i2) {
        if (i2 == 10103) {
            return "shareToQQ";
        }
        if (i2 == 10104) {
            return "shareToQzone";
        }
        if (i2 == 10105) {
            return "addToQQFavorites";
        }
        if (i2 == 10106) {
            return "sendToMyComputer";
        }
        if (i2 == 10107) {
            return "shareToTroopBar";
        }
        if (i2 == 11101) {
            return "action_login";
        }
        if (i2 == 10100) {
            return "action_request";
        }
        if (i2 != 10114) {
            return null;
        }
        return "action_common_channel";
    }

    public static void a() {
        a.clear();
    }

    public static String b(Context context, String str) {
        String str2 = "";
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        try {
            String packageName = context.getPackageName();
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(signatureArr[0].toByteArray());
            String a2 = m.a(messageDigest.digest());
            messageDigest.reset();
            SLog.v("openSDK_LOG.SystemUtils", "-->sign: " + a2);
            messageDigest.update(m.j(packageName + CartConstant.KEY_YB_INFO_LINK + a2 + CartConstant.KEY_YB_INFO_LINK + str + ""));
            str2 = m.a(messageDigest.digest());
            messageDigest.reset();
            StringBuilder sb = new StringBuilder();
            sb.append("-->signEncryped: ");
            sb.append(str2);
            SLog.v("openSDK_LOG.SystemUtils", sb.toString());
            return str2;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e2);
            return str2;
        }
    }

    public static int c(Context context, String str) {
        return a(a(context, "com.tencent.mobileqq"), str);
    }

    public static int d(Context context, String str) {
        return a(a(context, Constants.PACKAGE_TIM), str);
    }

    private static PackageInfo e(Context context, String str) {
        if (context == null || str == null) {
            return null;
        }
        synchronized (k.class) {
            if (a.containsKey(str)) {
                com.tencent.a.a.a aVar = a.get(str);
                if (aVar == null) {
                    SLog.e("openSDK_LOG.SystemUtils", "getTargetPackageInfo wrapper is null");
                    return null;
                }
                PackageInfo packageInfo = aVar.b;
                if (packageInfo == null) {
                    SLog.e("openSDK_LOG.SystemUtils", "getTargetPackageInfo wrapper packageInfo is null");
                }
                return packageInfo;
            }
            PackageInfo f2 = f(context, str);
            a.put(str, new com.tencent.a.a.a(str, f2));
            return f2;
        }
    }

    private static PackageInfo f(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                SLog.e("openSDK_LOG.SystemUtils", "realGetPackageInfo null. packageName= " + str);
            }
            return packageInfo;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SystemUtils", "realGetPackageInfo exception", e2);
            return null;
        }
    }

    private static boolean g(Context context, String str) {
        return (h.a(context, c(), str) == null && e(context, str) == null) ? false : true;
    }

    public static void a(String str) {
        if (str == null) {
            return;
        }
        a.remove(str);
    }

    public static String a(Context context, String str) {
        String a2 = h.a(context, c(), str);
        if (a2 == null || "UNKNOWN".equals(a2)) {
            PackageInfo e2 = e(context, str);
            if (e2 == null) {
                SLog.e("openSDK_LOG.SystemUtils", "getAppVersionName return null. package= " + str);
                return null;
            }
            return e2.versionName;
        }
        return a2;
    }

    public static boolean c(Context context) {
        if (g(context, "com.tencent.mobileqq")) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: qq");
            return true;
        } else if (g(context, Constants.PACKAGE_TIM)) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: tim");
            return true;
        } else if (g(context, Constants.PACKAGE_QQ_PAD)) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: pad");
            return true;
        } else {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: disable speed");
            return false;
        }
    }

    public static boolean d(Context context) {
        return context != null && context.getApplicationInfo().targetSdkVersion >= 29 && Build.VERSION.SDK_INT >= 29 && !b();
    }

    public static int a(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null || str2 != null) {
            if (str != null || str2 == null) {
                String[] split = str.split("\\.");
                String[] split2 = str2.split("\\.");
                int i2 = 0;
                while (i2 < split.length && i2 < split2.length) {
                    try {
                        int parseInt = Integer.parseInt(split[i2]);
                        int parseInt2 = Integer.parseInt(split2[i2]);
                        if (parseInt < parseInt2) {
                            return -1;
                        }
                        if (parseInt > parseInt2) {
                            return 1;
                        }
                        i2++;
                    } catch (NumberFormatException unused) {
                        return str.compareTo(str2);
                    }
                }
                if (split.length > i2) {
                    return 1;
                }
                return split2.length > i2 ? -1 : 0;
            }
            return -1;
        }
        return 1;
    }

    private static String c() {
        String b = com.tencent.open.b.b.b();
        if (b == null || b.isEmpty()) {
            SLog.e("openSDK_LOG.SystemUtils", "getAppId error: " + b);
        }
        return b;
    }

    public static boolean a(Context context, String str, String str2) {
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (m.g(signature.toCharsString()).equals(str2)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static boolean b(Context context, Intent intent) {
        boolean z = false;
        if (context != null && intent != null) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                SLog.i("openSDK_LOG.SystemUtils", "isAgentActivityExist? component null");
                return false;
            }
            String packageName = component.getPackageName();
            String a2 = a(context, packageName);
            if (a2 != null && !a2.isEmpty()) {
                z = true;
            }
            SLog.i("openSDK_LOG.SystemUtils", "isAgentActivityExist? packageName = " + packageName + ", appVersionName= " + a2);
        }
        return z;
    }

    public static String a(Activity activity, String str) {
        if (activity == null) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName activity==null !!!!!!");
            return "";
        }
        try {
            byte[] a2 = e.a(str);
            if (a2 == null) {
                SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName shaBytes==null !!!!!!");
                return "";
            }
            byte[] bArr = new byte[8];
            System.arraycopy(a2, 5, bArr, 0, 8);
            byte[] bArr2 = new byte[16];
            System.arraycopy(a2, 8, bArr2, 0, 16);
            return e.a(activity.getPackageName(), e.a(bArr2), bArr);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName", e2);
            return "";
        }
    }

    public static int b(String str) {
        if ("shareToQQ".equals(str)) {
            return 10103;
        }
        if ("shareToQzone".equals(str)) {
            return 10104;
        }
        if ("addToQQFavorites".equals(str)) {
            return 10105;
        }
        if ("sendToMyComputer".equals(str)) {
            return 10106;
        }
        if ("shareToTroopBar".equals(str)) {
            return 10107;
        }
        if ("action_login".equals(str)) {
            return 11101;
        }
        return "action_request".equals(str) ? 10100 : -1;
    }

    public static boolean a(Context context, Intent intent) {
        if (context != null && intent != null) {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            boolean z = queryIntentActivities != null && queryIntentActivities.size() > 0;
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append("isActivityExist false. result=");
                sb.append(queryIntentActivities == null ? DYConstants.DY_NULL_STR : Integer.valueOf(queryIntentActivities.size()));
                sb.append(" Intent= ");
                sb.append(intent);
                SLog.e("openSDK_LOG.SystemUtils", sb.toString());
            }
            return z;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("isActivityExist params error! [");
        sb2.append(context == null);
        sb2.append(DYConstants.DY_REGEX_COMMA);
        sb2.append(intent == null);
        sb2.append("]");
        SLog.e("openSDK_LOG.SystemUtils", sb2.toString());
        return false;
    }

    public static boolean b(Context context) {
        boolean g2 = g(context, "com.tencent.mobileqq");
        SLog.i("openSDK_LOG.SystemUtils", "isQQInstalled " + g2);
        return g2;
    }

    private static boolean b() {
        try {
            return ((Boolean) Environment.class.getMethod("isExternalStorageLegacy", new Class[0]).invoke(Environment.class, new Object[0])).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public static String a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        try {
            return applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.SystemUtils", "getAppName exception", th);
            try {
                int i2 = applicationInfo.labelRes;
                return i2 <= 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i2);
            } catch (Throwable th2) {
                SLog.e("openSDK_LOG.SystemUtils", "getAppName getLabel exception", th2);
                return "";
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @SuppressLint({"SdCardPath"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean a(String str, String str2, int i2) {
        FileOutputStream fileOutputStream;
        InputStream open;
        SLog.i("openSDK_LOG.SystemUtils", "-->extractSecureLib, libName: " + str);
        Context a2 = g.a();
        if (a2 == null) {
            SLog.i("openSDK_LOG.SystemUtils", "-->extractSecureLib, global context is null. ");
            return false;
        }
        SharedPreferences sharedPreferences = a2.getSharedPreferences("secure_lib", 0);
        File file = new File(a2.getFilesDir(), str2);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && parentFile.mkdirs()) {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            int i3 = sharedPreferences.getInt("version", 0);
            SLog.i("openSDK_LOG.SystemUtils", "-->extractSecureLib, libVersion: " + i2 + " | oldVersion: " + i3);
            if (i2 == i3) {
                return true;
            }
        }
        InputStream inputStream = null;
        r4 = null;
        FileOutputStream fileOutputStream2 = null;
        inputStream = null;
        try {
            open = a2.getAssets().open(str);
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            fileOutputStream2 = a2.openFileOutput(str2, 0);
            a(open, fileOutputStream2);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("version", i2);
            edit.commit();
            if (open != null) {
                try {
                    open.close();
                } catch (IOException unused) {
                }
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            return true;
        } catch (Exception e4) {
            e = e4;
            FileOutputStream fileOutputStream3 = fileOutputStream2;
            inputStream = open;
            fileOutputStream = fileOutputStream3;
            try {
                SLog.e("openSDK_LOG.SystemUtils", "-->extractSecureLib, when copy lib execption.", e);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused6) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            FileOutputStream fileOutputStream4 = fileOutputStream2;
            inputStream = open;
            fileOutputStream = fileOutputStream4;
            if (inputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        }
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, 8192);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j2 += read;
            } else {
                SLog.i("openSDK_LOG.SystemUtils", "-->copy, copyed size is: " + j2);
                return j2;
            }
        }
    }

    public static String a(Activity activity) {
        try {
            ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getApplicationContext().getPackageName(), 128);
            SLog.i("openSDK_LOG.SystemUtils", "apkPath=" + applicationInfo.sourceDir);
            return applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e2) {
            SLog.e("openSDK_LOG.SystemUtils", "NameNotFoundException", e2);
            return null;
        } catch (Exception e3) {
            SLog.e("openSDK_LOG.SystemUtils", "Exception", e3);
            return null;
        }
    }
}
