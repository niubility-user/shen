package com.jd.jdsec.a.k;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.LocalServerSocket;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class j {
    private static long a;
    private static LocalServerSocket b;

    /* renamed from: c  reason: collision with root package name */
    private static String[] f2725c = {"com.bly.dkplat", "com.by.chaos", "com.lbe.parallel", "com.excelliance.dualaid", "com.lody.virtual", "com.qihoo.magic", "com.dual.dualgenius", "com.jiubang.commerce.gomultiple"};

    public static boolean a() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/maps"));
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    for (String str : f2725c) {
                        if (readLine.contains(str)) {
                            try {
                                bufferedReader.close();
                                return true;
                            } catch (IOException unused2) {
                                return true;
                            }
                        }
                    }
                }
            } catch (Exception unused3) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    bufferedReader = bufferedReader2;
                    bufferedReader.close();
                    break;
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
            try {
                bufferedReader.close();
                break;
            } catch (IOException unused5) {
            }
        }
        return false;
    }

    public static boolean b(Context context) {
        try {
            for (String str : com.jd.jdsec.a.c.c(context)) {
                for (String str2 : f2725c) {
                    if (str.contains(str2)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static Long c(Context context) {
        try {
            if (g(context)) {
                a |= 1;
            }
            if (h(context)) {
                a |= 2;
            }
            if (a()) {
                a |= 4;
            }
            if (e(context)) {
                a |= 8;
            }
            if (d(context)) {
                a |= 16;
            }
            if (f(context)) {
                a |= 32;
            }
            if (b(context)) {
                a |= 64;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }

    public static boolean d(Context context) {
        if (b != null) {
            return false;
        }
        try {
            b = new LocalServerSocket(context.getPackageName());
            return false;
        } catch (IOException unused) {
            return true;
        }
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        try {
            String packageName = context.getPackageName();
            Iterator<PackageInfo> it = com.jd.jdsec.a.g.r(context).iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (packageName.equals(it.next().packageName)) {
                    i2++;
                }
            }
            return i2 > 1;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean f(Context context) {
        return context.getPackageName() == jd.wjlogin_sdk.util.f.f19954c;
    }

    public static boolean g(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        String packageName = context.getPackageName();
        String str = "/data/data/" + packageName + "/files";
        StringBuilder sb = new StringBuilder();
        sb.append("/data/user/0/");
        sb.append(packageName);
        sb.append("/files");
        return (str.equals(absolutePath) || sb.toString().equals(absolutePath)) ? false : true;
    }

    public static boolean h(Context context) {
        String path = context.getFilesDir().getPath();
        for (String str : f2725c) {
            if (path.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
