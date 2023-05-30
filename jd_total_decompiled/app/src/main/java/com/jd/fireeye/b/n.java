package com.jd.fireeye.b;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes13.dex */
public class n {
    private static int a;
    public static final String[] b = {"eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.qihoo.permmgr", "com.wmshua.wmroot", "com.baidu.easyroot", "com.baiyi_mobile.easyroot", "com.mgyun.shua.su", "com.z4mod.z4root", "com.shuame.rootgenius", "com.zhiqupk.root", "com.kingroot.kinguser", "com.apkol.root", "com.corner23.android.universalandroot", "com.roothelper"};

    /* renamed from: c  reason: collision with root package name */
    public static final String[] f2612c = {"de.robv.android.xposed.installer", "com.saurik.substrate", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.amphoras.hidemyrootadfree", "com.formyhm.hiderootPremium", "com.formyhm.hideroot"};
    public static final String[] d = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/"};

    /* renamed from: e  reason: collision with root package name */
    public static final String[] f2613e = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};

    private static boolean a() {
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

    private static boolean b() {
        boolean z;
        try {
            ArrayList<String> c2 = c();
            if (c2 != null && c2.size() > 0) {
                Iterator<String> it = c2.iterator();
                while (it.hasNext()) {
                    String[] split = it.next().split("\\s+");
                    int length = split.length;
                    if (length >= 4) {
                        String str = null;
                        int i2 = 1;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (split[i2].startsWith("/")) {
                                str = split[i2];
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (i2 < length && str != null) {
                            String[] strArr = f2613e;
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
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static int c(Context context) {
        if (a != 0) {
            return 0;
        }
        if (b(context)) {
            return 1;
        }
        if (a(context)) {
            return 2;
        }
        return a() ? 3 : 0;
    }

    public static int d() {
        if (a("su")) {
            a = 1;
        } else if (b()) {
            a = 9;
        } else {
            a = 0;
        }
        return a;
    }

    private static ArrayList<String> c() {
        return b("mount");
    }

    private static boolean a(Context context) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(f2612c));
        hashSet.addAll(com.jd.fireeye.security.c.b);
        return a(hashSet, context);
    }

    private static boolean a(String str) {
        for (String str2 : d) {
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(b));
        hashSet.addAll(com.jd.fireeye.security.c.a);
        return a(hashSet, context);
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

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0044: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:27:0x0044 */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ArrayList<String> b(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            arrayList.add(readLine);
                        } else {
                            try {
                                break;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
                bufferedReader.close();
                return arrayList;
            } catch (IOException e5) {
                e = e5;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader3 != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader2;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }
}
