package com.jd.stat.security.jma.a.a;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jd.stat.common.EncryptUtil;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/* loaded from: classes18.dex */
public class d {
    private static int a;

    public static int a() {
        if (a("su")) {
            a = 1;
        } else if (b()) {
            a = 2;
        } else if (c()) {
            a = 9;
        } else {
            a = 0;
        }
        return a;
    }

    private static boolean b() {
        int length = a.f7072c.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = a.f7072c[i2] + "su";
        }
        try {
            if (EncryptUtil.a()) {
                return EncryptUtil.checkSu(strArr) > 0;
            }
            return false;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean c() {
        String str;
        boolean z;
        Scanner scanner = null;
        try {
            Scanner g2 = g();
            while (g2.hasNextLine()) {
                try {
                    String[] split = g2.nextLine().split("\\s+");
                    int length = split.length;
                    if (length >= 4) {
                        int i2 = 1;
                        while (true) {
                            if (i2 >= length) {
                                str = null;
                                break;
                            } else if (split[i2].startsWith("/")) {
                                str = split[i2];
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (i2 < length && str != null) {
                            String[] strArr = a.d;
                            int length2 = strArr.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length2) {
                                    z = false;
                                    break;
                                } else if (strArr[i3].equalsIgnoreCase(str)) {
                                    z = true;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (z) {
                                while (i2 < length) {
                                    if (split[i2].contains("rw")) {
                                        for (String str2 : split[i2].split("[(,)]")) {
                                            if ("rw".equalsIgnoreCase(str2)) {
                                                if (g2 != null) {
                                                    g2.close();
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                    i2++;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    scanner = g2;
                    try {
                        th.printStackTrace();
                        return false;
                    } finally {
                        if (scanner != null) {
                            scanner.close();
                        }
                    }
                }
            }
            if (g2 != null) {
                g2.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return false;
    }

    private static boolean d() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod(IMantoServerRequester.GET, String.class);
            for (String str : hashMap.keySet()) {
                if (declaredMethod.invoke(cls, str).equals(hashMap.get(str))) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static Set<String> e() {
        return com.jd.stat.security.d.a().x();
    }

    private static Set<String> f() {
        return com.jd.stat.security.d.a().w();
    }

    private static Scanner g() throws IOException {
        return b("mount");
    }

    public static boolean b(Context context) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(a.a));
        hashSet.addAll(f());
        return a(hashSet, context);
    }

    public static int a(Context context) {
        if (a != 0) {
            return 0;
        }
        if (b(context)) {
            return 1;
        }
        if (c(context)) {
            return 2;
        }
        return d() ? 3 : 0;
    }

    private static Scanner b(String str) throws IOException {
        return new Scanner(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
    }

    private static boolean a(String str) {
        for (String str2 : a.f7072c) {
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(Set<String> set, Context context) {
        PackageManager packageManager = context.getPackageManager();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            try {
                packageManager.getPackageInfo(it.next(), 0);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    private static boolean c(Context context) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(a.b));
        hashSet.addAll(e());
        return a(hashSet, context);
    }
}
