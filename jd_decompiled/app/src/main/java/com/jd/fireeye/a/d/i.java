package com.jd.fireeye.a.d;

import android.content.Context;
import android.net.LocalServerSocket;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes13.dex */
public class i {
    private static long a;
    private static LocalServerSocket b;

    /* renamed from: c  reason: collision with root package name */
    private static String[] f2610c = {"com.bly.dkplat", "com.by.chaos", "com.lbe.parallel", "com.excelliance.dualaid", "com.lody.virtual", "com.qihoo.magic", "com.dual.dualgenius", "com.jiubang.commerce.gomultiple"};

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
                    for (String str : f2610c) {
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

    public static String b(Context context) {
        try {
            if (f(context)) {
                a |= 1;
            }
            if (g(context)) {
                a |= 2;
            }
            if (a()) {
                a |= 4;
            }
            if (d(context)) {
                a |= 8;
            }
            if (c(context)) {
                a |= 16;
            }
            if (e(context)) {
                a |= 32;
            }
            if (a(context)) {
                a |= 64;
            }
        } catch (Exception unused) {
        }
        return String.valueOf(a);
    }

    public static boolean c(Context context) {
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

    public static boolean d(Context context) {
        return false;
    }

    public static boolean e(Context context) {
        String packageName = context.getPackageName();
        return !TextUtils.isEmpty(packageName) && (packageName.equals(jd.wjlogin_sdk.util.f.f19954c) || packageName.equals("com.jd.pingou") || packageName.equals("com.jd.jdlite"));
    }

    public static boolean f(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        String packageName = context.getPackageName();
        String str = "/data/data/" + packageName + "/files";
        StringBuilder sb = new StringBuilder();
        sb.append("/data/user/0/");
        sb.append(packageName);
        sb.append("/files");
        return (str.equals(absolutePath) || sb.toString().equals(absolutePath)) ? false : true;
    }

    public static boolean g(Context context) {
        String path = context.getFilesDir().getPath();
        for (String str : f2610c) {
            if (path.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context) {
        try {
            for (String str : com.jd.fireeye.a.c.b.a(context)) {
                for (String str2 : f2610c) {
                    if (str.contains(str2)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
