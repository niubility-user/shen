package com.jingdong.aura.a.b.l;

import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class h {
    private static final String[] a = {"com.jd.lib.productdetail", "com.jd.lib.coupon", "com.jd.lib.mycoupon", "com.jd.lib.ordercenter", "com.jd.lib.live", "com.jd.mobiledd.sdk", "com.jd.lib.personal", "com.jd.lib.cart", "com.jd.lib.login", "com.jd.lib.settlement", "com.jd.lib.push", "com.jd.lib.category"};

    public static String a(File file, long j2) {
        return b(new File(file, "package_" + String.valueOf(j2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String b(java.io.File r3) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "meta"
            r0.<init>(r3, r1)
            boolean r3 = r0.exists()
            r1 = 0
            if (r3 == 0) goto L4b
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            r3.readUTF()     // Catch: java.io.IOException -> L28 java.lang.Throwable -> L3e
            java.lang.String r0 = r3.readUTF()     // Catch: java.io.IOException -> L28 java.lang.Throwable -> L3e
            r3.close()     // Catch: java.io.IOException -> L23
            goto L27
        L23:
            r3 = move-exception
            r3.printStackTrace()
        L27:
            return r0
        L28:
            r0 = move-exception
            goto L30
        L2a:
            r3 = move-exception
            r0 = r3
            goto L40
        L2d:
            r3 = move-exception
            r0 = r3
            r3 = r1
        L30:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r3 == 0) goto L4b
            r3.close()     // Catch: java.io.IOException -> L39
            goto L4b
        L39:
            r3 = move-exception
            r3.printStackTrace()
            goto L4b
        L3e:
            r0 = move-exception
            r1 = r3
        L40:
            if (r1 == 0) goto L4a
            r1.close()     // Catch: java.io.IOException -> L46
            goto L4a
        L46:
            r3 = move-exception
            r3.printStackTrace()
        L4a:
            throw r0
        L4b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.b.l.h.b(java.io.File):java.lang.String");
    }

    public static long c(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        long j2 = -1;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > j2) {
                    j2 = parseLong;
                }
            }
        }
        return j2;
    }

    private static boolean d(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        long j2 = -1;
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > j2) {
                    j2 = parseLong;
                }
            }
        }
        if (-1 == j2) {
            return false;
        }
        boolean z = false;
        for (File file3 : listFiles) {
            if (file3.getName().startsWith("package") && Long.parseLong(com.jingdong.aura.core.util.h.e(file3.getName(), CartConstant.KEY_YB_INFO_LINK)) != j2) {
                com.jingdong.aura.core.util.f.a(file3);
                z = true;
            }
        }
        return z;
    }

    public static SortedMap<Long, c> e(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        TreeMap treeMap = new TreeMap();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return treeMap;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > 0) {
                    treeMap.put(Long.valueOf(parseLong), null);
                }
            }
        }
        return treeMap;
    }

    public static boolean a(long j2, String str, long j3, String str2) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 > 0) {
            return true;
        }
        if (i2 != 0 || com.jingdong.aura.core.util.h.a(str)) {
            return false;
        }
        return com.jingdong.aura.core.util.h.a(str2) || !str.equals(str2);
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory() && file2.getName().startsWith("com.") && d(file2)) {
                return true;
            }
        }
        int i2 = 0;
        for (File file3 : listFiles) {
            String name = file3.getName();
            if (file3.isDirectory() && name.startsWith("com.") && com.jingdong.aura.a.b.k.b.b(name) == null && a(name)) {
                com.jingdong.aura.core.util.f.a(file3);
                i2++;
                if (i2 >= 2) {
                    return true;
                }
            }
        }
        return i2 > 0;
    }

    private static boolean a(String str) {
        if (com.jingdong.aura.core.util.h.a(str)) {
            return false;
        }
        for (String str2 : AuraConfig.AUTO) {
            if (str.equals(str2)) {
                return false;
            }
        }
        if (com.jingdong.aura.a.a.a.c().i(str)) {
            return false;
        }
        for (String str3 : a) {
            if (str.equals(str3)) {
                return false;
            }
        }
        return true;
    }
}
