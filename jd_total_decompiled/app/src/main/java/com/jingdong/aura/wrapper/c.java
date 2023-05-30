package com.jingdong.aura.wrapper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.jd.dynamic.DYConstants;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class c {
    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("lib");
        String str2 = File.separator;
        sb.append(str2);
        sb.append(com.jingdong.aura.a.b.c.l());
        sb.append(str2);
        String sb2 = sb.toString();
        return str.substring(str.indexOf(sb2) + sb2.length());
    }

    public static String b(String str) {
        String str2 = "lib/" + com.jingdong.aura.a.b.c.l() + "/lib";
        return str.substring(str.indexOf(str2) + str2.length(), str.indexOf(".so"));
    }

    public static boolean c(Application application) {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionCode > application.getSharedPreferences("aura_config", 0).getInt("last_version_code", 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void d(Application application) {
        System.setProperty("BUNDLES_INSTALLED", DYConstants.DY_TRUE);
        application.sendBroadcast(new Intent(AuraConfig.ACTION_BROADCAST_BUNDLES_INSTALLED));
    }

    public static void e(Application application) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(b(application).versionName, "dexopt");
        SharedPreferences sharedPreferences = application.getSharedPreferences("aura_config", 0);
        if (sharedPreferences == null) {
            sharedPreferences = application.getSharedPreferences("aura_config", 0);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str : concurrentHashMap.keySet()) {
            edit.putString(str, (String) concurrentHashMap.get(str));
        }
        edit.commit();
    }

    public static void a(Application application) {
        PackageInfo b = b(application);
        SharedPreferences.Editor edit = application.getSharedPreferences("aura_config", 0).edit();
        edit.putInt("last_version_code", b.versionCode);
        edit.putString("last_version_name", b.versionName);
        edit.putString(b.versionName, "dexopt");
        edit.commit();
    }

    public static PackageInfo b(Application application) {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (Throwable unused) {
            return new PackageInfo();
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean a() {
        int i2;
        if (!com.jingdong.aura.a.b.c.Q() && a.b() && (i2 = Build.VERSION.SDK_INT) >= 14 && com.jingdong.aura.a.b.c.g()) {
            if (i2 >= 14) {
                String osBuild_BRAND = com.jingdong.aura.a.b.c.F().getOsBuild_BRAND();
                String osBuild_HARDWARE = com.jingdong.aura.a.b.c.F().getOsBuild_HARDWARE();
                return osBuild_BRAND == null || !osBuild_BRAND.toLowerCase().contains("xiaomi") || osBuild_HARDWARE == null || !osBuild_HARDWARE.toLowerCase().contains("mt65");
            }
            return true;
        }
        return false;
    }
}
