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
    */
    private static boolean b(String str, String str2) {
        InputStreamReader inputStreamReader;
        String readLine;
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    str = Runtime.getRuntime().exec("ls " + str).getInputStream();
                    try {
                        inputStreamReader = new InputStreamReader(str);
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                            do {
                                try {
                                    readLine = bufferedReader2.readLine();
                                    if (readLine == null) {
                                        bufferedReader2.close();
                                        inputStreamReader.close();
                                        if (str != 0) {
                                            str.close();
                                            return false;
                                        }
                                        return false;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    bufferedReader = bufferedReader2;
                                    e.printStackTrace();
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (str != null) {
                                        str.close();
                                        return false;
                                    }
                                    return false;
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedReader = bufferedReader2;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (str != 0) {
                                        str.close();
                                    }
                                    throw th;
                                }
                            } while (!readLine.contains(str2));
                            bufferedReader2.close();
                            inputStreamReader.close();
                            if (str != 0) {
                                str.close();
                            }
                            return true;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        inputStreamReader = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                } catch (IOException e6) {
                    e = e6;
                    str = null;
                    inputStreamReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    str = 0;
                    inputStreamReader = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
            str = 0;
            return false;
        }
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
