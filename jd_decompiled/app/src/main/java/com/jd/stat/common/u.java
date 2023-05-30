package com.jd.stat.common;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.system.Os;
import android.system.StructStatVfs;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.stat.common.b.g;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.UUID;

/* loaded from: classes18.dex */
public class u {
    public static String a() {
        if (Build.VERSION.SDK_INT >= 18 && !com.jd.stat.security.d.a().a(BaseInfo.getDeviceBrand(), BaseInfo.getDeviceModel())) {
            StringBuilder sb = new StringBuilder();
            try {
                Class<?> cls = Class.forName("android.media.MediaDrm");
                Class cls2 = Long.TYPE;
                sb.append(a(cls.getConstructor(UUID.class), cls.getMethod("getPropertyByteArray", String.class), UUID.class.getConstructor(cls2, cls2).newInstance(-1301668207276963122L, -6645017420763422227L)));
                return sb.toString();
            } catch (Throwable unused) {
                return "c";
            }
        }
        return com.jingdong.jdsdk.a.a.a;
    }

    public static String b() {
        try {
            String trim = com.jd.stat.common.b.g.i("cat /proc/sys/kernel/random/boot_id").trim();
            return TextUtils.isEmpty(trim) ? com.jingdong.jdsdk.a.a.a : trim;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String c() {
        try {
            if (Build.VERSION.SDK_INT <= 24) {
                File file = new File("/proc/version");
                if (!file.exists()) {
                    return "c";
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)), 1000);
                String str = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return str;
                    }
                    str = str + readLine;
                }
            } else {
                return com.jd.stat.common.b.g.i("uname -a").trim();
            }
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String[] d() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                StructStatVfs statvfs = Os.statvfs("/");
                return new String[]{Long.toHexString(statvfs.f_fsid), Long.toHexString(statvfs.f_blocks), Long.toHexString(statvfs.f_files)};
            }
            return new String[]{com.jingdong.jdsdk.a.a.a, com.jingdong.jdsdk.a.a.a, com.jingdong.jdsdk.a.a.a};
        } catch (Throwable unused) {
            return new String[]{"c", "c", "c"};
        }
    }

    public static String e() {
        try {
            String d = com.jd.stat.common.b.g.d(com.jd.stat.common.b.g.i("ls -l /system/fonts"));
            return TextUtils.isEmpty(d) ? "c" : d;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String f() {
        try {
            String d = com.jd.stat.common.b.g.d(com.jd.stat.common.b.g.i("ls  -l /system/framework"));
            return TextUtils.isEmpty(d) ? "c" : d;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String g() {
        try {
            String d = com.jd.stat.common.b.g.d(com.jd.stat.common.b.g.i("ls  -l /vendor/lib"));
            return TextUtils.isEmpty(d) ? "c" : d;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String h() {
        if (com.jd.stat.security.d.a().e()) {
            try {
                String a = com.jd.stat.common.b.g.a("ip a", new g.a() { // from class: com.jd.stat.common.u.1
                    @Override // com.jd.stat.common.b.g.a
                    public boolean a(String str) {
                        return !TextUtils.isEmpty(str) && str.trim().startsWith("link/ether");
                    }
                });
                if (!TextUtils.isEmpty(a)) {
                    String[] split = a.trim().split("\\s+");
                    if (split.length >= 2) {
                        String str = split[1];
                        return (TextUtils.isEmpty(str) || !str.matches("[0-9a-fA-F]{2}(:[0-9a-fA-F]{2}){5}")) ? "c" : Base64.encodeToString(str.getBytes("utf-8"), 11);
                    }
                }
                return com.jingdong.jdsdk.a.a.a;
            } catch (Throwable unused) {
                return "c";
            }
        }
        return com.jingdong.jdsdk.a.a.a;
    }

    private static String a(Constructor constructor, Method method, Object obj) throws Exception {
        return Base64.encodeToString((byte[]) method.invoke(constructor.newInstance(obj), "deviceUniqueId"), 11);
    }

    public static String a(Context context) {
        if (context == null) {
            return "c";
        }
        String str = null;
        try {
            if (Build.VERSION.SDK_INT < 23 || context.checkSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                Cursor query = context.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{"android_id"}, null);
                if (query != null && query.moveToFirst() && query.getColumnCount() >= 2) {
                    str = Long.toHexString(Long.parseLong(query.getString(1)));
                    query.close();
                    return str;
                }
                return "c";
            }
            return "b";
        } catch (Throwable unused) {
            return !TextUtils.isEmpty(str) ? str : "c";
        }
    }
}
