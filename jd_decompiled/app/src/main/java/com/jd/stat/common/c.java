package com.jd.stat.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c {
    private static String a = null;
    private static String b = "";

    /* loaded from: classes18.dex */
    public static class a {
        public int b = 0;

        /* renamed from: c  reason: collision with root package name */
        public String f6984c = "";
        public String d = "";
        public String a = "";

        public String toString() {
            return this.a + DYConstants.DY_REGEX_COMMA + this.b + DYConstants.DY_REGEX_COMMA + this.f6984c + DYConstants.DY_REGEX_COMMA + this.d;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            context = com.jd.stat.security.c.a;
        }
        if (context == null) {
            return "";
        }
        if (TextUtils.isEmpty(a)) {
            if (!TextUtils.isEmpty(b) && com.jd.stat.security.c.e()) {
                a = b + "_com.jma.track";
            } else {
                a = context.getPackageName() + "_com.jma.track";
            }
        }
        return a;
    }

    public static int b(Context context) {
        PackageInfo h2 = h(context);
        if (h2 == null) {
            return 0;
        }
        return h2.versionCode;
    }

    public static String c() {
        return "2.5.8";
    }

    public static String c(Context context) {
        PackageInfo h2 = h(context);
        if (h2 == null) {
            return "";
        }
        String str = h2.versionName;
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public static String d(Context context) {
        PackageInfo h2 = h(context);
        return h2 == null ? "" : h2.packageName;
    }

    public static List<String> e(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SCREEN_ON");
            Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(intent, 0).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().activityInfo.packageName);
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static a f(Context context) {
        if (context == null) {
            return new a();
        }
        a aVar = new a();
        StringBuilder sb = new StringBuilder();
        sb.append(e());
        sb.append(i(context));
        sb.append(j(context));
        aVar.a += sb.toString();
        a(context, aVar);
        aVar.a += aVar.b;
        return aVar;
    }

    public static Pair<String, String> g(Context context) {
        if (context == null) {
            return new Pair<>("c", "c");
        }
        PackageInfo h2 = h(context);
        if (h2 == null) {
            return new Pair<>("c", "c");
        }
        try {
            return new Pair<>(String.valueOf(h2.firstInstallTime), String.valueOf(h2.lastUpdateTime));
        } catch (Throwable unused) {
            return new Pair<>("c", "c");
        }
    }

    private static PackageInfo h(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    private static int i(Context context) {
        try {
            return TextUtils.equals(context.getPackageName(), jd.wjlogin_sdk.util.f.f19954c) ? 1 : 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static int j(Context context) {
        List<ResolveInfo> arrayList = new ArrayList<>();
        try {
            arrayList = context.getPackageManager().queryIntentActivities(new Intent("com.jingdong.app.mall.service.WatchDogService"), 65536);
            com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", "queryIntentActivities called.");
        } catch (Throwable th) {
            com.jd.stat.common.b.b.a("JDMob.AppBaseUtil", th);
        }
        return arrayList.size() > 1 ? 1 : 0;
    }

    public static String b() {
        return Build.VERSION.RELEASE;
    }

    public static String d() {
        String[] strArr = {"gsm.sim.state", "gsm.operator.alpha", "gsm.network.type", "gsm.operator.numeric", "gsm.operator.iso-country"};
        String[] strArr2 = {"gsm_state", "gsm_operator", "gsm_type", "gsm_num", "gsm_isocountry"};
        JSONObject jSONObject = new JSONObject();
        for (int i2 = 0; i2 < 5; i2++) {
            try {
                try {
                    String a2 = a(strArr[i2]);
                    if (TextUtils.isEmpty(a2)) {
                        jSONObject.put(strArr2[i2], com.jingdong.jdsdk.a.a.a);
                    } else {
                        jSONObject.put(strArr2[i2], a2);
                    }
                } catch (Exception unused) {
                    jSONObject.put(strArr2[i2], "c");
                }
            } catch (Exception unused2) {
            }
        }
        return jSONObject.toString();
    }

    public static Signature[] b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException unused) {
            if (com.jd.stat.common.b.b.a) {
                com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", str + " not installed!");
            }
            return null;
        }
    }

    public static int a() {
        return Build.VERSION.SDK_INT;
    }

    private static int e() {
        String[] split;
        try {
            String a2 = a("ps");
            if (a2 != null && !a2.isEmpty() && (split = a2.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) != null && split.length > 0) {
                int myPid = Process.myPid();
                int i2 = 0;
                for (int i3 = 0; i3 < split.length; i3++) {
                    if (split[i3].contains(Integer.toString(myPid))) {
                        int lastIndexOf = split[i3].lastIndexOf(LangUtils.SINGLE_SPACE);
                        if (new File(String.format("/data/data/%s", split[i3].substring(lastIndexOf <= 0 ? 0 : lastIndexOf + 1, split[i3].length()), Locale.CHINA)).exists()) {
                            i2++;
                        }
                    }
                }
                return i2 > 1 ? 1 : 0;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static a a(Context context, a aVar) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(jd.wjlogin_sdk.util.f.f19954c, R2.attr.jdpay_width);
            if (Build.VERSION.SDK_INT >= 24) {
                String str = applicationInfo.deviceProtectedDataDir;
                String substring = str.substring(0, str.indexOf(OrderISVUtil.MONEY_DECIMAL));
                String substring2 = substring.substring(0, substring.lastIndexOf("/") + 1);
                if (!applicationInfo.deviceProtectedDataDir.startsWith(substring2 + jd.wjlogin_sdk.util.f.f19954c)) {
                    aVar.b = 1;
                    aVar.f6984c = "deviceProtectedDataDir";
                    aVar.d = applicationInfo.deviceProtectedDataDir;
                    return aVar;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!applicationInfo.nativeLibraryDir.startsWith("/data/app/" + jd.wjlogin_sdk.util.f.f19954c)) {
            aVar.b = 1;
            aVar.f6984c = "nativeLibraryDir";
            aVar.d = applicationInfo.nativeLibraryDir;
            return aVar;
        }
        if (!applicationInfo.sourceDir.startsWith("/data/app/" + jd.wjlogin_sdk.util.f.f19954c)) {
            aVar.b = 1;
            aVar.f6984c = "sourceDir";
            aVar.d = applicationInfo.nativeLibraryDir;
            return aVar;
        }
        if (!applicationInfo.publicSourceDir.startsWith("/data/app/" + jd.wjlogin_sdk.util.f.f19954c)) {
            aVar.b = 1;
            aVar.f6984c = "publicSourceDir";
            aVar.d = applicationInfo.publicSourceDir;
            return aVar;
        }
        String path = context.getFilesDir().getPath();
        String substring3 = path.substring(0, path.indexOf(OrderISVUtil.MONEY_DECIMAL));
        String substring4 = substring3.substring(0, substring3.lastIndexOf("/") + 1);
        String str2 = "file dir:" + substring4;
        if (!applicationInfo.dataDir.startsWith(substring4 + jd.wjlogin_sdk.util.f.f19954c)) {
            aVar.b = 1;
            aVar.f6984c = "dataDir";
            aVar.d = applicationInfo.dataDir;
            return aVar;
        }
        return aVar;
    }

    private static String b(byte[] bArr) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(bArr));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            r2.<init>()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            r2.append(r5)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            java.lang.Process r5 = r1.exec(r5)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            java.io.InputStream r3 = r5.getInputStream()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            r3 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L66
            r1.close()     // Catch: java.io.IOException -> L32
            goto L36
        L32:
            r1 = move-exception
            r1.printStackTrace()
        L36:
            if (r5 == 0) goto L65
            r5.destroy()
            goto L65
        L3c:
            r0 = move-exception
            goto L51
        L3e:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L67
        L43:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L51
        L48:
            r5 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
            goto L67
        L4d:
            r5 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
        L51:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r0 = move-exception
            r0.printStackTrace()
        L5e:
            if (r5 == 0) goto L63
            r5.destroy()
        L63:
            java.lang.String r0 = ""
        L65:
            return r0
        L66:
            r0 = move-exception
        L67:
            if (r1 == 0) goto L71
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r1 = move-exception
            r1.printStackTrace()
        L71:
            if (r5 == 0) goto L76
            r5.destroy()
        L76:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.c.a(java.lang.String):java.lang.String");
    }

    public static String a(Context context, String str) {
        Signature[] b2;
        if (TextUtils.isEmpty(str)) {
            com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", "pkName is null");
            return "";
        } else if (context == null) {
            com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", "context is null");
            return "";
        } else {
            try {
                b2 = b(context, str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (b2 != null && b2.length != 0) {
                Signature signature = b2[0];
                if (signature != null) {
                    String b3 = b(signature.toByteArray());
                    if (com.jd.stat.common.b.b.a) {
                        com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", "signatureStr = " + b3);
                    }
                    return b3;
                }
                return "";
            }
            com.jd.stat.common.b.b.b("JDMob.AppBaseUtil", "sign is null");
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toLowerCase());
        }
        return sb.toString();
    }
}
