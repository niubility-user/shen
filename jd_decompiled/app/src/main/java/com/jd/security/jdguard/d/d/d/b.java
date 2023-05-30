package com.jd.security.jdguard.d.d.d;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.view.InputDevice;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import dalvik.system.BaseDexClassLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public class b {
    private static String a = "";
    private static String b = "JEN";

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003a, code lost:
        if (r1 == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L39
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L39
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L39
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = "L3Byb2MvbmV0L2FycA=="
            r6 = 0
            byte[] r5 = android.util.Base64.decode(r5, r6)     // Catch: java.lang.Throwable -> L39
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L39
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L39
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L39
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r1.readLine()     // Catch: java.lang.Throwable -> L3a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3a
            r3.<init>()     // Catch: java.lang.Throwable -> L3a
        L27:
            if (r2 == 0) goto L31
            r3.append(r2)     // Catch: java.lang.Throwable -> L3a
            java.lang.String r2 = r1.readLine()     // Catch: java.lang.Throwable -> L3a
            goto L27
        L31:
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L3a
        L35:
            r1.close()     // Catch: java.lang.Throwable -> L3d
            goto L3d
        L39:
            r1 = r0
        L3a:
            if (r1 == 0) goto L3d
            goto L35
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.security.jdguard.d.d.d.b.a():java.lang.String");
    }

    private static String b() {
        try {
            Thread thread = Looper.getMainLooper().getThread();
            if (thread != null) {
                for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
                    if (stackTraceElement.getClassName().equals(new String(Base64.decode("ZGUucm9idi5hbmRyb2lkLnhwb3NlZC5YcG9zZWRCcmlkZ2U=", 0))) && "main".equals(stackTraceElement.getMethodName())) {
                        return "1";
                    }
                    if (stackTraceElement.getClassName().startsWith(new String(Base64.decode("Y29tLnNhdXJpay5zdWJzdHJhdGUuTVM=", 0))) && "invoked".equals(stackTraceElement.getMethodName())) {
                        return "2";
                    }
                }
                return "0";
            }
            return "0";
        } catch (Throwable unused) {
            return "0";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (r4 == null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONArray c() {
        /*
            java.lang.String r0 = "tun"
            java.lang.String r1 = "ppp"
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            r3 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = "cat /proc/net/route"
            java.lang.Process r4 = r4.exec(r5)     // Catch: java.lang.Throwable -> L6d
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L6d
            java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L6d
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L6d
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6a
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6a
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L6a
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L6a
            java.util.HashSet r3 = new java.util.HashSet     // Catch: java.lang.Throwable -> L6b
            r3.<init>()     // Catch: java.lang.Throwable -> L6b
        L2c:
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L6b
            if (r6 == 0) goto L51
            java.lang.String r6 = w(r6)     // Catch: java.lang.Throwable -> L6b
            if (r6 == 0) goto L2c
            boolean r7 = r6.contains(r0)     // Catch: java.lang.Throwable -> L6b
            if (r7 != 0) goto L44
            boolean r7 = r6.contains(r1)     // Catch: java.lang.Throwable -> L6b
            if (r7 == 0) goto L2c
        L44:
            java.lang.String r7 = "\t"
            java.lang.String[] r6 = r6.split(r7)     // Catch: java.lang.Throwable -> L6b
            r7 = 0
            r6 = r6[r7]     // Catch: java.lang.Throwable -> L6b
            r3.add(r6)     // Catch: java.lang.Throwable -> L6b
            goto L2c
        L51:
            java.util.Iterator r0 = r3.iterator()     // Catch: java.lang.Throwable -> L6b
        L55:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L63
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L6b
            r2.put(r1)     // Catch: java.lang.Throwable -> L6b
            goto L55
        L63:
            r5.close()     // Catch: java.io.IOException -> L66
        L66:
            r4.close()     // Catch: java.io.IOException -> L78
            goto L78
        L6a:
            r4 = r3
        L6b:
            r3 = r5
            goto L6e
        L6d:
            r4 = r3
        L6e:
            if (r3 == 0) goto L75
            r3.close()     // Catch: java.io.IOException -> L74
            goto L75
        L74:
        L75:
            if (r4 == 0) goto L78
            goto L66
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.security.jdguard.d.d.d.b.c():org.json.JSONArray");
    }

    private static boolean d(Context context, String str) {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            bufferedReader2.close();
                            break;
                        }
                        try {
                            if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                                String substring = readLine.substring(readLine.lastIndexOf(LangUtils.SINGLE_SPACE) + 1);
                                if (TextUtils.isEmpty(substring)) {
                                    continue;
                                } else if (substring.contains(new String(Base64.decode("Y29tLnNhdXJpay5zdWJzdHJhdGU=", 0)))) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException unused) {
                                    }
                                    return true;
                                } else if (substring.contains(new String(Base64.decode("WHBvc2VkQnJpZGdlLmphcg==", 0)))) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException unused2) {
                                    }
                                    return true;
                                }
                            }
                        } catch (Throwable unused3) {
                        }
                    } catch (Throwable unused4) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return false;
                    }
                }
            } catch (Throwable unused5) {
            }
        } catch (IOException unused6) {
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            if ("1".equals(f()) || "1".equals(z()) || "1".equals(g()) || !"0".equals(b())) {
                return true;
            }
            return d(context, context.getPackageName());
        } catch (Throwable unused) {
            return false;
        }
    }

    private static String f() {
        try {
            String str = new String(Base64.decode("L2RhdGEvYXBwL2RlLnJvYnYuYW5kcm9pZC54cG9zZWQuaW5zdGFsbGVy", 0));
            if (new File(str + "-1.apk").exists()) {
                return "1";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("-2.apk");
            return new File(sb.toString()).exists() ? "1" : "0";
        } catch (Throwable unused) {
            return "0";
        }
    }

    private static String g() {
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (systemClassLoader == null || !(systemClassLoader instanceof BaseDexClassLoader)) {
                return "0";
            }
            BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) systemClassLoader;
            for (Field field : BaseDexClassLoader.class.getDeclaredFields()) {
                field.setAccessible(true);
                if ("pathList".equals(field.getName()) && field.get(baseDexClassLoader).toString().contains(new String(Base64.decode("WHBvc2VkQnJpZGdl", 0)))) {
                    return "1";
                }
            }
            return "0";
        } catch (Throwable unused) {
            return "0";
        }
    }

    public static String h(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            HashSet<String> hashSet = new HashSet();
            if (BaseInfo.getAndroidSDKVersion() >= 14) {
                Iterator<AccessibilityServiceInfo> it = ((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1).iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().getId());
                }
            }
            boolean z = true;
            for (String str : hashSet) {
                if (!z) {
                    stringBuffer.append("|");
                }
                stringBuffer.append(str);
                z = false;
            }
        } catch (Throwable unused) {
        }
        String stringBuffer2 = stringBuffer.toString();
        a = stringBuffer2;
        return stringBuffer2;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01c3 A[Catch: all -> 0x01d5, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e2 A[Catch: all -> 0x01f4, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0209 A[Catch: all -> 0x021b, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a A[Catch: Exception -> 0x0064, all -> 0x022c, TRY_LEAVE, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084 A[Catch: all -> 0x008f, TRY_LEAVE, TryCatch #5 {all -> 0x008f, blocks: (B:27:0x006b, B:29:0x0084), top: B:132:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0103 A[Catch: Exception -> 0x014a, all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0158 A[Catch: all -> 0x022c, TRY_ENTER, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x015c A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0161 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0165 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x016a A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016e A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0173 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0177 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x017c A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0180 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0185 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0189 A[Catch: all -> 0x022c, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a4 A[Catch: all -> 0x01b6, TryCatch #4 {all -> 0x022c, blocks: (B:3:0x000c, B:5:0x0015, B:6:0x0025, B:7:0x0029, B:11:0x0030, B:12:0x0034, B:33:0x0092, B:35:0x00b3, B:37:0x00b9, B:40:0x00c9, B:43:0x00d3, B:46:0x00dd, B:49:0x00e7, B:52:0x00f1, B:57:0x0103, B:58:0x011e, B:60:0x0124, B:63:0x0136, B:66:0x0140, B:73:0x0158, B:76:0x0161, B:79:0x016a, B:82:0x0173, B:85:0x017c, B:88:0x0185, B:90:0x018c, B:123:0x021e, B:89:0x0189, B:86:0x0180, B:83:0x0177, B:80:0x016e, B:77:0x0165, B:74:0x015c, B:19:0x0041, B:21:0x005a, B:107:0x01d8, B:109:0x01e2, B:111:0x01ec, B:112:0x01f0, B:99:0x01b9, B:101:0x01c3, B:103:0x01cd, B:104:0x01d1, B:91:0x0196, B:93:0x01a4, B:95:0x01ae, B:96:0x01b2, B:115:0x01f7, B:117:0x0209, B:119:0x0213, B:120:0x0217), top: B:130:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int i(android.content.Context r16) {
        /*
            Method dump skipped, instructions count: 568
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.security.jdguard.d.d.d.b.i(android.content.Context):int");
    }

    public static String j(Context context) {
        int i2 = 0;
        try {
            if (context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")) != null) {
                i2 = (int) ((r4.getIntExtra("level", -1) / r4.getIntExtra("scale", -1)) * 100.0f);
            }
            return i2 + "%";
        } catch (Throwable unused) {
            return null;
        }
    }

    public static synchronized String k(Context context) {
        synchronized (b.class) {
            if (!TextUtils.isEmpty(b)) {
                if (!b.equals("JEN")) {
                    return b;
                }
                try {
                    Object invoke = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                    Field declaredField = invoke.getClass().getDeclaredField("mPackages");
                    declaredField.setAccessible(true);
                    Map map = (Map) declaredField.get(invoke);
                    if (map != null && map.size() > 0) {
                        for (Object obj : map.keySet()) {
                            String obj2 = obj.toString();
                            if (obj2.length() > 1 && !obj2.equals(context.getPackageName()) && !obj2.endsWith("android.webview")) {
                                File file = new File("/data/data/" + obj.toString());
                                if (file.exists() && file.canWrite()) {
                                    String obj3 = obj.toString();
                                    b = obj3;
                                    return obj3;
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                b = "";
                return "";
            }
            return b;
        }
    }

    public static String l() {
        try {
            int androidSDKVersion = BaseInfo.getAndroidSDKVersion();
            if (androidSDKVersion <= 8) {
                return "";
            }
            int[] deviceIds = InputDevice.getDeviceIds();
            JSONArray jSONArray = new JSONArray();
            for (int i2 : deviceIds) {
                InputDevice device = InputDevice.getDevice(i2);
                if (device != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("0", device.getName());
                    int i3 = 1;
                    if (androidSDKVersion >= 16) {
                        jSONObject.put("1", device.isVirtual() ? 1 : 0);
                    }
                    if (androidSDKVersion >= 19) {
                        jSONObject.put("2", device.getVendorId());
                    }
                    jSONObject.put("3", device.getSources());
                    String inputDevice = device.toString();
                    if (inputDevice.indexOf("Location: built-in") <= 0) {
                        i3 = inputDevice.indexOf("Location: external") > 0 ? 2 : 0;
                    }
                    jSONObject.put("4", i3);
                    jSONObject.put("5", device.getDescriptor());
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray.toString().replace("   ", "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String m(Context context) {
        String n2;
        String str = "JEN";
        try {
            n2 = n();
        } catch (Throwable unused) {
        }
        try {
            return (TextUtils.isEmpty(n2) || n2.startsWith("JEN")) ? String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).uid) : n2;
        } catch (Throwable unused2) {
            str = n2;
            return str;
        }
    }

    private static String n() {
        try {
            String y = y(String.format(Locale.ENGLISH, "/proc/%d/status", Integer.valueOf(Process.myPid())));
            if (y == null) {
                return "JEN";
            }
            for (String str : y.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
                String[] split = str.trim().split("\\s+");
                if ("Uid:".equals(split[0])) {
                    return String.valueOf(Integer.parseInt(split[1]));
                }
            }
            return "JEN";
        } catch (Throwable unused) {
            return "JEN";
        }
    }

    public static String o() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/sys/class/power_supply/usb/online")), 1000);
        } catch (Throwable unused) {
        }
        try {
            String readLine = bufferedReader.readLine();
            try {
                bufferedReader.close();
            } catch (Throwable unused2) {
            }
            return readLine;
        } catch (Throwable unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                    return "";
                } catch (Throwable unused4) {
                    return "";
                }
            }
            return "";
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean p(Context context) {
        AccessibilityManager accessibilityManager;
        if (BaseInfo.getAndroidSDKVersion() >= 16 && (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) != null) {
            accessibilityManager.getEnabledAccessibilityServiceList(16);
            return Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled") == 1;
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static Pair<Boolean, String> q(Context context, String str) {
        String str2;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            str2 = File.separator;
            sb.append(str2);
            sb.append("jdg8899");
            fileOutputStream = new FileOutputStream(sb.toString());
        } catch (Throwable unused) {
        }
        try {
            FileDescriptor fd = fileOutputStream.getFD();
            Field declaredField = fd.getClass().getDeclaredField("descriptor");
            declaredField.setAccessible(true);
            String format = String.format("/proc/self/fd/%d", Integer.valueOf(((Integer) declaredField.get(fd)).intValue()));
            String str3 = "";
            if (BaseInfo.getAndroidSDKVersion() >= 26) {
                str3 = Files.readSymbolicLink(Paths.get(format, new String[0])).toString();
            } else {
                if (new File(str + str2 + "..").canRead()) {
                    Pair<Boolean, String> create = Pair.create(Boolean.TRUE, "JEN");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                    }
                    return create;
                }
            }
            if (TextUtils.isEmpty(str3)) {
                Pair<Boolean, String> create2 = Pair.create(Boolean.FALSE, "JEN");
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                }
                return create2;
            }
            if (!str3.substring(str3.lastIndexOf(str2)).equals(str2 + "jdg8899")) {
                Pair<Boolean, String> create3 = Pair.create(Boolean.TRUE, str3);
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                }
                return create3;
            } else if (new File(str3.replace("jdg8899", "..")).canRead()) {
                Pair<Boolean, String> create4 = Pair.create(Boolean.TRUE, str3);
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                }
                return create4;
            } else {
                try {
                    fileOutputStream.close();
                } catch (IOException unused6) {
                }
                return Pair.create(Boolean.FALSE, "JEN");
            }
        } catch (Throwable unused7) {
            fileOutputStream2 = fileOutputStream;
            try {
                Pair<Boolean, String> create5 = Pair.create(Boolean.FALSE, "JEPE");
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused8) {
                    }
                }
                return create5;
            } catch (Throwable th) {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused9) {
                    }
                }
                throw th;
            }
        }
    }

    public static boolean r(Context context) {
        if (BaseInfo.getAndroidSDKVersion() >= 16) {
            return t(context);
        }
        return s(context);
    }

    private static boolean s(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.widget.LockPatternUtils");
            return ((Integer) cls.getMethod("getActivePasswordQuality", new Class[0]).invoke(cls.getConstructor(Context.class).newInstance(context), new Object[0])).intValue() != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean t(Context context) {
        try {
            return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int u(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            Intent registerReceiver = context.registerReceiver(null, intentFilter);
            if (registerReceiver.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                return registerReceiver.getIntExtra("plugged", 0);
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static boolean v(Context context) {
        boolean z = false;
        try {
            if (BaseInfo.getAndroidSDKVersion() < 17 ? Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0 : Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) > 0) {
                z = true;
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    private static String w(String str) {
        try {
            return str.replace("      ", ";").replace("     ", ";").replace("    ", ";").replace("   ", ";").replace("  ", ";").replace(LangUtils.SINGLE_SPACE, ";");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String x() {
        String property = System.getProperty("http.proxyHost");
        return TextUtils.isEmpty(property) ? "JEN" : property;
    }

    private static String y(String str) {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                try {
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (z) {
                            z = false;
                        } else {
                            sb.append('\n');
                        }
                        sb.append(readLine);
                    }
                    String sb2 = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                    return sb2;
                } catch (Throwable unused3) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused4) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    return null;
                }
            } catch (Throwable unused6) {
                bufferedReader = null;
            }
        } catch (Throwable unused7) {
            bufferedReader = null;
            fileInputStream = null;
        }
    }

    private static String z() {
        try {
            String str = new String(Base64.decode("ZGUucm9idi5hbmRyb2lkLnhwb3NlZC5YcG9zZWRIZWxwZXJz", 0));
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (systemClassLoader != null) {
                try {
                    if (systemClassLoader.loadClass(str) != null) {
                        return "1";
                    }
                } catch (ClassNotFoundException unused) {
                    return "0";
                }
            }
            Method declaredMethod = ClassLoader.class.getDeclaredMethod("loadClass", String.class, Boolean.TYPE);
            if (declaredMethod != null && systemClassLoader != null) {
                declaredMethod.setAccessible(true);
                if (((Class) declaredMethod.invoke(systemClassLoader, str, Boolean.FALSE)) != null) {
                    return "1";
                }
            }
        } catch (Throwable unused2) {
        }
        return "0";
    }
}
