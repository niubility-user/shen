package com.jd.stat.security.jma.a.a;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.stat.common.EncryptUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;

/* loaded from: classes18.dex */
public class b {
    private static List<String> a = Arrays.asList("XposedBridge.jar", "libAndroidCydia.cy.so", "libAndroidBootstrap0.so", "libsubstrate.so", "libDalvikLoader.cy.so", "libAndroidLoader.so", "libsubstrate-dvm.so");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARN: Type inference failed for: r3v14 */
    public static JSONArray a(Context context) {
        String[] split;
        int myPid = Process.myPid();
        String packageName = context.getPackageName();
        Runtime runtime = Runtime.getRuntime();
        HashSet hashSet = new HashSet();
        BufferedReader bufferedReader = 0;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(runtime.exec("cat /proc/" + myPid + "/maps").getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        bufferedReader = a(readLine, packageName);
                        if (bufferedReader != 0 && (bufferedReader = (split = readLine.split("\\s\\s+")).length) == 2) {
                            bufferedReader = 1;
                            hashSet.add(split[1]);
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return new JSONArray((Collection) hashSet);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
            } catch (IOException e5) {
                e = e5;
            }
            return new JSONArray((Collection) hashSet);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean b(String str) {
        Iterator<String> it = e().iterator();
        while (it.hasNext()) {
            if (Pattern.matches(it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(String str) {
        return new File(str).exists();
    }

    public static JSONArray d() {
        String str;
        String[] split;
        JSONArray jSONArray = new JSONArray();
        try {
            str = EncryptUtil.detect();
        } catch (Throwable unused) {
            str = null;
        }
        if (!TextUtils.isEmpty(str) && (split = str.split(DYConstants.DY_REGEX_COMMA)) != null) {
            for (String str2 : split) {
                jSONArray.put(str2);
            }
        }
        return jSONArray;
    }

    private static Set<String> e() {
        return com.jd.stat.security.d.a().y();
    }

    private static ArrayList<c> f() {
        ArrayList<c> arrayList = new ArrayList<>();
        c cVar = new c();
        cVar.a("android.telephony.TelephonyManager");
        cVar.b("getDeviceId");
        cVar.b("getLine1Number");
        arrayList.add(cVar);
        c cVar2 = new c();
        cVar2.a("android.app.ActivityManager");
        cVar2.b("getRunningAppProcesses");
        cVar2.b("getRunningServices");
        arrayList.add(cVar2);
        c cVar3 = new c();
        cVar3.a("android.provider.Settings");
        cVar3.b("getString");
        arrayList.add(cVar3);
        c cVar4 = new c();
        cVar4.a("java.io.File");
        cVar4.b("listFiles");
        cVar4.b("getName");
        arrayList.add(cVar4);
        return arrayList;
    }

    private static boolean g() {
        return b("/system/bin/app_process.orig", "app_process.orig") | c("/system/bin/app_process.orig");
    }

    public static boolean c() {
        FileInputStream fileInputStream;
        String readLine;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream("/system/bin/app_process");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream));
                do {
                    try {
                        readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            try {
                                bufferedReader2.close();
                                fileInputStream.close();
                            } catch (IOException unused) {
                            }
                            return false;
                        }
                    } catch (IOException unused2) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused3) {
                                return false;
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused4) {
                                throw th;
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } while (!readLine.contains("Xposed"));
                bufferedReader2.close();
                fileInputStream.close();
                return true;
            } catch (IOException unused5) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused6) {
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static JSONArray b(Context context) {
        return new JSONArray();
    }

    public static String b() {
        return g() ? "1" : c() ? "2" : "0";
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
        r4.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean b(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r2.<init>()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.String r3 = "ls "
            r2.append(r3)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r2.append(r4)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.Process r4 = r1.exec(r4)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L82
            r2.<init>(r1)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L82
        L28:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            if (r0 == 0) goto L46
            boolean r0 = r0.contains(r5)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            if (r0 == 0) goto L28
            r5 = 1
            r2.close()     // Catch: java.io.IOException -> L41
            r1.close()     // Catch: java.io.IOException -> L41
            if (r4 == 0) goto L45
            r4.close()     // Catch: java.io.IOException -> L41
            goto L45
        L41:
            r4 = move-exception
            r4.printStackTrace()
        L45:
            return r5
        L46:
            r2.close()     // Catch: java.io.IOException -> L70
            r1.close()     // Catch: java.io.IOException -> L70
            if (r4 == 0) goto L80
            r4.close()     // Catch: java.io.IOException -> L70
            goto L80
        L52:
            r5 = move-exception
            r0 = r2
            goto L83
        L55:
            r5 = move-exception
            r0 = r2
            goto L67
        L58:
            r5 = move-exception
            goto L67
        L5a:
            r5 = move-exception
            r1 = r0
            goto L83
        L5d:
            r5 = move-exception
            r1 = r0
            goto L67
        L60:
            r5 = move-exception
            r4 = r0
            r1 = r4
            goto L83
        L64:
            r5 = move-exception
            r4 = r0
            r1 = r4
        L67:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L82
            if (r0 == 0) goto L72
            r0.close()     // Catch: java.io.IOException -> L70
            goto L72
        L70:
            r4 = move-exception
            goto L7d
        L72:
            if (r1 == 0) goto L77
            r1.close()     // Catch: java.io.IOException -> L70
        L77:
            if (r4 == 0) goto L80
            r4.close()     // Catch: java.io.IOException -> L70
            goto L80
        L7d:
            r4.printStackTrace()
        L80:
            r4 = 0
            return r4
        L82:
            r5 = move-exception
        L83:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L89
            goto L8b
        L89:
            r4 = move-exception
            goto L96
        L8b:
            if (r1 == 0) goto L90
            r1.close()     // Catch: java.io.IOException -> L89
        L90:
            if (r4 == 0) goto L99
            r4.close()     // Catch: java.io.IOException -> L89
            goto L99
        L96:
            r4.printStackTrace()
        L99:
            goto L9b
        L9a:
            throw r5
        L9b:
            goto L9a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.security.jma.a.a.b.b(java.lang.String, java.lang.String):boolean");
    }

    public static JSONArray c(Context context) {
        return new JSONArray();
    }

    private static boolean a(String str, String str2) {
        if (b(str) || str.endsWith("ttf") || str.endsWith("(deleted)")) {
            return false;
        }
        return a(str) || (str.contains("/data/data") && !str.contains(str2)) || ((str.contains("/data/app") && !str.contains(str2)) || str.endsWith(".cy.so"));
    }

    private static boolean a(String str) {
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static JSONArray a() {
        ArrayList arrayList = new ArrayList();
        ArrayList<c> f2 = f();
        if (f2 != null) {
            Iterator<c> it = f2.iterator();
            while (it.hasNext()) {
                c next = it.next();
                try {
                    for (Method method : Class.forName(next.a()).getDeclaredMethods()) {
                        if (Modifier.isNative(method.getModifiers()) && next.b().contains(method.getName())) {
                            arrayList.add(method.getName());
                        }
                    }
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return new JSONArray((Collection) arrayList);
    }
}
