package com.jd.security.jdguard.d.d.d;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.view.InputDevice;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import dalvik.system.BaseDexClassLoader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
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
    */
    public static String a() {
        BufferedReader bufferedReader;
        String str = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new String(Base64.decode("L3Byb2MvbmV0L2FycA==", 0)))), 1000);
            try {
                StringBuilder sb = new StringBuilder();
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    sb.append(readLine);
                }
                str = sb.toString();
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            bufferedReader = null;
        }
        try {
            bufferedReader.close();
        } catch (Throwable unused3) {
            return str;
        }
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
    */
    public static JSONArray c() {
        BufferedReader bufferedReader;
        JSONArray jSONArray = new JSONArray();
        DataInputStream dataInputStream = null;
        try {
            DataInputStream dataInputStream2 = new DataInputStream(Runtime.getRuntime().exec("cat /proc/net/route").getInputStream());
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream2));
                try {
                    HashSet hashSet = new HashSet();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String w = w(readLine);
                        if (w != null && (w.contains("tun") || w.contains("ppp"))) {
                            hashSet.add(w.split("\t")[0]);
                        }
                    }
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next());
                    }
                    try {
                        dataInputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable unused2) {
                    dataInputStream = dataInputStream2;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                }
            } catch (Throwable unused4) {
                bufferedReader = null;
            }
        } catch (Throwable unused5) {
            bufferedReader = null;
        }
        try {
            bufferedReader.close();
        } catch (IOException unused6) {
            return jSONArray;
        }
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
    */
    public static int i(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Class<?> cls;
        Object obj;
        Class<?> cls2;
        Object obj2;
        Constructor declaredConstructor;
        StringBuilder sb = new StringBuilder();
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (systemClassLoader != null) {
                Method declaredMethod = ClassLoader.class.getDeclaredMethod("loadClass", String.class, Boolean.TYPE);
                try {
                    cls = systemClassLoader.loadClass("de.robv.android.xposed.XposedHelpers");
                } catch (Exception unused) {
                    cls = null;
                }
                try {
                    obj = cls.newInstance();
                } catch (Exception unused2) {
                    obj = null;
                    cls2 = systemClassLoader.loadClass("de.robv.android.xposed.XposedBridge");
                    obj2 = cls2.newInstance();
                    if (cls != null) {
                    }
                    try {
                        declaredMethod.setAccessible(true);
                        Constructor declaredConstructor2 = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedHelpers", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                        if (obj == null) {
                        }
                    } catch (Exception unused3) {
                    }
                    if (cls2 != null) {
                    }
                    try {
                        declaredMethod.setAccessible(true);
                        declaredConstructor = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedBridge", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                        if (declaredConstructor != null) {
                        }
                    } catch (Throwable unused4) {
                    }
                    if (obj != null) {
                    }
                    if (obj2 != null) {
                    }
                    if (!z5) {
                    }
                    if (!z6) {
                    }
                    if (!z) {
                    }
                    if (!z2) {
                    }
                    if (!z3) {
                    }
                    if (!z4) {
                    }
                    Class<?> cls3 = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getClass();
                    method = cls3.getMethod("getDeviceId", new Class[0]);
                    if (method == null) {
                    }
                    sb.append("0");
                    method2 = cls3.getMethod("getSimSerialNumber", new Class[0]);
                    if (method2 == null) {
                    }
                    sb.append("0");
                    method3 = cls3.getMethod("getSubscriberId", new Class[0]);
                    if (method3 == null) {
                    }
                    sb.append("0");
                    method4 = Settings.Secure.class.getMethod("getString", ContentResolver.class, String.class);
                    if (method4 == null) {
                    }
                    sb.append("0");
                    return new BigInteger(sb.toString(), 2).intValue();
                }
                try {
                    cls2 = systemClassLoader.loadClass("de.robv.android.xposed.XposedBridge");
                } catch (Exception unused5) {
                    cls2 = null;
                }
                try {
                    obj2 = cls2.newInstance();
                } catch (Exception unused6) {
                    obj2 = null;
                    if (cls != null) {
                    }
                    declaredMethod.setAccessible(true);
                    Constructor declaredConstructor22 = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedHelpers", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                    if (obj == null) {
                    }
                    if (cls2 != null) {
                    }
                    declaredMethod.setAccessible(true);
                    declaredConstructor = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedBridge", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                    if (declaredConstructor != null) {
                    }
                    if (obj != null) {
                    }
                    if (obj2 != null) {
                    }
                    if (!z5) {
                    }
                    if (!z6) {
                    }
                    if (!z) {
                    }
                    if (!z2) {
                    }
                    if (!z3) {
                    }
                    if (!z4) {
                    }
                    Class<?> cls32 = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getClass();
                    method = cls32.getMethod("getDeviceId", new Class[0]);
                    if (method == null) {
                    }
                    sb.append("0");
                    method2 = cls32.getMethod("getSimSerialNumber", new Class[0]);
                    if (method2 == null) {
                    }
                    sb.append("0");
                    method3 = cls32.getMethod("getSubscriberId", new Class[0]);
                    if (method3 == null) {
                    }
                    sb.append("0");
                    method4 = Settings.Secure.class.getMethod("getString", ContentResolver.class, String.class);
                    if (method4 == null) {
                    }
                    sb.append("0");
                    return new BigInteger(sb.toString(), 2).intValue();
                }
                if ((cls != null || obj == null) && declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    Constructor declaredConstructor222 = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedHelpers", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                    if (obj == null) {
                        declaredConstructor222.setAccessible(true);
                        obj = declaredConstructor222.newInstance(new Object[0]);
                    }
                }
                if ((cls2 != null || obj2 == null) && declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    declaredConstructor = ((Class) declaredMethod.invoke(systemClassLoader, "de.robv.android.xposed.XposedBridge", Boolean.FALSE)).getDeclaredConstructor(new Class[0]);
                    if (declaredConstructor != null) {
                        declaredConstructor.setAccessible(true);
                        obj2 = declaredConstructor.newInstance(new Object[0]);
                    }
                }
                if (obj != null) {
                    try {
                        Field declaredField = obj.getClass().getDeclaredField("methodCache");
                        declaredField.setAccessible(true);
                        z = false;
                        z2 = false;
                        z3 = false;
                        z4 = false;
                        z5 = false;
                        z6 = false;
                        for (String str : ((Map) declaredField.get(obj)).keySet()) {
                            try {
                                if (str.contains("android.telephony.TelephonyManager#getDeviceId")) {
                                    z = true;
                                } else if (str.contains("android.telephony.TelephonyManager#getSimSerialNumber")) {
                                    z2 = true;
                                } else if (str.contains("android.telephony.TelephonyManager#getSubscriberId")) {
                                    z3 = true;
                                } else if (str.contains("android.provider.Settings$Secure#getString")) {
                                    z4 = true;
                                } else if (str.contains("java.lang.StringBuilder#")) {
                                    z5 = true;
                                } else if (str.contains("java.lang.reflect.Method#getModifiers")) {
                                    z6 = true;
                                }
                            } catch (Exception unused7) {
                            }
                        }
                    } catch (Exception unused8) {
                    }
                } else {
                    z = false;
                    z2 = false;
                    z3 = false;
                    z4 = false;
                    z5 = false;
                    z6 = false;
                }
                if (obj2 != null) {
                    Field declaredField2 = obj2.getClass().getDeclaredField("sHookedMethodCallbacks");
                    declaredField2.setAccessible(true);
                    Iterator it = ((Map) declaredField2.get(obj2)).keySet().iterator();
                    while (it.hasNext()) {
                        String obj3 = it.next().toString();
                        if (obj3.contains("android.telephony.TelephonyManager.getDeviceId")) {
                            z = true;
                        } else if (obj3.contains("android.telephony.TelephonyManager.getSimSerialNumber")) {
                            z2 = true;
                        } else if (obj3.contains("android.telephony.TelephonyManager.getSubscriberId")) {
                            z3 = true;
                        }
                    }
                }
                if (!z5) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                if (!z6) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                if (!z) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                if (!z2) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                if (!z3) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                if (!z4) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                Class<?> cls322 = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getClass();
                method = cls322.getMethod("getDeviceId", new Class[0]);
                if (method == null && Modifier.isNative(method.getModifiers())) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                method2 = cls322.getMethod("getSimSerialNumber", new Class[0]);
                if (method2 == null && Modifier.isNative(method2.getModifiers())) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                method3 = cls322.getMethod("getSubscriberId", new Class[0]);
                if (method3 == null && Modifier.isNative(method3.getModifiers())) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                method4 = Settings.Secure.class.getMethod("getString", ContentResolver.class, String.class);
                if (method4 == null && Modifier.isNative(method4.getModifiers())) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                return new BigInteger(sb.toString(), 2).intValue();
            }
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            if (!z5) {
            }
            if (!z6) {
            }
            if (!z) {
            }
            if (!z2) {
            }
            if (!z3) {
            }
            if (!z4) {
            }
            Class<?> cls3222 = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getClass();
            method = cls3222.getMethod("getDeviceId", new Class[0]);
            if (method == null) {
            }
            sb.append("0");
            method2 = cls3222.getMethod("getSimSerialNumber", new Class[0]);
            if (method2 == null) {
            }
            sb.append("0");
            method3 = cls3222.getMethod("getSubscriberId", new Class[0]);
            if (method3 == null) {
            }
            sb.append("0");
            method4 = Settings.Secure.class.getMethod("getString", ContentResolver.class, String.class);
            if (method4 == null) {
            }
            sb.append("0");
            return new BigInteger(sb.toString(), 2).intValue();
        } catch (Throwable unused9) {
            return new BigInteger("0000", 2).intValue();
        }
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
