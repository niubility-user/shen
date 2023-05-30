package com.jingdong.aura.core.util;

import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class e {
    private static final String[] a = {"armeabi-v7a", "armeabi"};
    private static final String[] b = {"arm64-v8a"};

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, String> f12169c = new HashMap();

    private static boolean a(String str, String[] strArr) {
        if (!TextUtils.isEmpty(str) && strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean b() {
        return j.a();
    }

    public static String a(String str, File file) {
        String a2;
        if (str == null) {
            return "armeabi";
        }
        if (f12169c.containsKey(str)) {
            return f12169c.get(str);
        }
        if (b()) {
            a2 = a();
        } else {
            a2 = a(file);
        }
        f12169c.put(str, a2);
        return a2;
    }

    private static String a() {
        return b[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d5, code lost:
        r0 = (java.lang.String) r1.get(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(File file) {
        ZipFile zipFile;
        String[] osBuild_SUPPORTED_32_BIT_ABIS;
        String str = "armeabi";
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        if (Build.VERSION.SDK_INT >= 21 && (osBuild_SUPPORTED_32_BIT_ABIS = com.jingdong.aura.a.b.c.F().getOsBuild_SUPPORTED_32_BIT_ABIS()) != null && osBuild_SUPPORTED_32_BIT_ABIS.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr = a;
                if (i3 >= strArr.length) {
                    break;
                }
                if (a(strArr[i3], osBuild_SUPPORTED_32_BIT_ABIS)) {
                    arrayList.add(strArr[i3]);
                }
                i3++;
            }
        }
        if (arrayList.size() <= 0) {
            int i4 = 0;
            boolean z = false;
            while (true) {
                String[] strArr2 = a;
                if (i4 >= strArr2.length) {
                    break;
                }
                String osBuild_CPU_ABI = com.jingdong.aura.a.b.c.F().getOsBuild_CPU_ABI();
                if (osBuild_CPU_ABI != null && strArr2[i4].equals(osBuild_CPU_ABI)) {
                    z = true;
                }
                if (z) {
                    arrayList.add(strArr2[i4]);
                }
                i4++;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name.indexOf(String.format("%s", "lib/")) != -1) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("lib");
                            String str2 = File.separator;
                            sb.append(str2);
                            int indexOf = name.indexOf(sb.toString()) + 4;
                            int indexOf2 = name.indexOf(str2, indexOf);
                            if (indexOf > 0 && indexOf2 > 0 && indexOf2 > indexOf) {
                                String substring = name.substring(indexOf, indexOf2);
                                if (!arrayList2.contains(substring)) {
                                    arrayList2.add(substring);
                                }
                            }
                        }
                    }
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (arrayList2.contains(arrayList.get(i2))) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    zipFile2 = zipFile;
                    e.printStackTrace();
                    zipFile = zipFile2;
                    d.a(zipFile);
                    return str;
                } catch (Throwable th) {
                    th = th;
                    zipFile2 = zipFile;
                    d.a(zipFile2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        d.a(zipFile);
        return str;
    }
}
