package com.jingdong.aura.core.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.jingdong.aura.a.c.l;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class i {
    static {
        com.jingdong.aura.core.util.l.c.a("Utils");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0090: MOVE (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:54:0x0090 */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(int r8) {
        /*
            r0 = 0
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            r3.<init>()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            r3.append(r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.lang.String r8 = "/cmdline"
            r3.append(r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62 java.io.FileNotFoundException -> L76
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L5a
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L55 java.io.FileNotFoundException -> L5a
            r0 = 256(0x100, float:3.59E-43)
            char[] r2 = new char[r0]     // Catch: java.io.IOException -> L4d java.io.FileNotFoundException -> L4f java.lang.Throwable -> L8f
            r8.read(r2)     // Catch: java.io.IOException -> L4d java.io.FileNotFoundException -> L4f java.lang.Throwable -> L8f
            r3 = 0
            r4 = 0
            r5 = 0
        L30:
            if (r4 >= r0) goto L3c
            char r6 = r2[r4]     // Catch: java.io.IOException -> L4d java.io.FileNotFoundException -> L4f java.lang.Throwable -> L8f
            if (r6 != 0) goto L37
            goto L3c
        L37:
            int r5 = r5 + 1
            int r4 = r4 + 1
            goto L30
        L3c:
            java.lang.String r0 = new java.lang.String     // Catch: java.io.IOException -> L4d java.io.FileNotFoundException -> L4f java.lang.Throwable -> L8f
            r0.<init>(r2, r3, r5)     // Catch: java.io.IOException -> L4d java.io.FileNotFoundException -> L4f java.lang.Throwable -> L8f
            r1.close()     // Catch: java.io.IOException -> L45
            goto L49
        L45:
            r1 = move-exception
            r1.printStackTrace()
        L49:
            r8.close()     // Catch: java.io.IOException -> L4c
        L4c:
            return r0
        L4d:
            r0 = move-exception
            goto L66
        L4f:
            r0 = move-exception
            goto L7a
        L51:
            r8 = move-exception
            r7 = r1
            r1 = r0
            goto L93
        L55:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
            goto L66
        L5a:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
            goto L7a
        L5f:
            r8 = move-exception
            r1 = r0
            goto L94
        L62:
            r8 = move-exception
            r1 = r0
            r0 = r8
            r8 = r1
        L66:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r0 = move-exception
            r0.printStackTrace()
        L73:
            if (r8 == 0) goto L8c
            goto L89
        L76:
            r8 = move-exception
            r1 = r0
            r0 = r8
            r8 = r1
        L7a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L87
            r1.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r0 = move-exception
            r0.printStackTrace()
        L87:
            if (r8 == 0) goto L8c
        L89:
            r8.close()     // Catch: java.io.IOException -> L8c
        L8c:
            java.lang.String r8 = ""
            return r8
        L8f:
            r0 = move-exception
            r7 = r1
            r1 = r8
            r8 = r0
        L93:
            r0 = r7
        L94:
            if (r0 == 0) goto L9e
            r0.close()     // Catch: java.io.IOException -> L9a
            goto L9e
        L9a:
            r0 = move-exception
            r0.printStackTrace()
        L9e:
            if (r1 == 0) goto La3
            r1.close()     // Catch: java.io.IOException -> La3
        La3:
            goto La5
        La4:
            throw r8
        La5:
            goto La4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.util.i.a(int):java.lang.String");
    }

    public static int b(Context context, String str) {
        PackageInfo a = a(context, str);
        if (a == null) {
            return 0;
        }
        return a.versionCode;
    }

    public static boolean c(String str) {
        try {
            return l.a.getPackageManager().getReceiverInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean d(String str) {
        try {
            return l.a.getPackageManager().getServiceInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            return l.a.getPackageManager().getProviderInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static PackageInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static long a(boolean z) {
        long blockSizeLong;
        long availableBlocksLong;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (z) {
            try {
                if (Build.VERSION.SDK_INT >= 18) {
                    blockSizeLong = statFs.getBlockSizeLong();
                    availableBlocksLong = statFs.getAvailableBlocksLong();
                    return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.aura.a.b.e.a("com.jingdong.aura", "", "getDataDiskFreeSize", th);
                return 400L;
            }
        }
        blockSizeLong = statFs.getBlockSize();
        availableBlocksLong = statFs.getAvailableBlocks();
        return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
    }

    public static void a(Map map, Object obj, Object obj2) {
        List list = (List) map.get(obj);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(obj2);
        map.put(obj, list);
    }

    public static boolean a(String str) {
        try {
            return l.a.getPackageManager().getActivityInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
