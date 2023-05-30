package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/* loaded from: classes9.dex */
public class li {
    public static final String a = "txmapengine";
    public static String b = "release";

    /* renamed from: c  reason: collision with root package name */
    public static String f16848c = "undefined";
    public static boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    public static boolean f16849e = false;

    /* renamed from: f  reason: collision with root package name */
    public static boolean f16850f = false;

    /* renamed from: g  reason: collision with root package name */
    public static boolean f16851g = false;

    /* renamed from: h  reason: collision with root package name */
    public static boolean f16852h = false;

    /* renamed from: i  reason: collision with root package name */
    public static int f16853i = 0;

    /* renamed from: j  reason: collision with root package name */
    public static String f16854j = "";

    /* renamed from: k  reason: collision with root package name */
    public static String f16855k = "";

    /* renamed from: l  reason: collision with root package name */
    public static String f16856l = "";

    /* renamed from: m  reason: collision with root package name */
    public static String f16857m = "";

    /* renamed from: n  reason: collision with root package name */
    public static String f16858n = "tms";
    public static String o = "undefined";
    public static List<b> p = new ArrayList(10);

    /* loaded from: classes9.dex */
    public interface a {
        void a(Context context, String str);

        void close();
    }

    /* loaded from: classes9.dex */
    public enum b {
        PLUGIN("shell.adapter.LocPluginModular"),
        BUGLY("bugly.TMSBugly"),
        BEACON("beacon.TMSBeaconReport");
        
        public String a;

        b(String str) {
            this.a = str;
        }
    }

    /* loaded from: classes9.dex */
    public interface c {
        byte[] a(byte[] bArr);
    }

    public static String a() {
        return f16848c + "-" + b;
    }

    public static String a(String str) {
        return a() + "!" + str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Context context) {
        String message;
        IllegalAccessException illegalAccessException;
        String str;
        try {
            String a2 = f7.a(context, context.getPackageName() + "_ShellConfig");
            if (TextUtils.isEmpty(a2)) {
                a2 = f7.a(context, "TMS_ShellConfig");
                if (TextUtils.isEmpty(a2)) {
                    try {
                        Properties properties = new Properties();
                        properties.load(context.getAssets().open("tencentmap/mapsdk_vector/sdkconfig.dat"));
                        a2 = properties.getProperty("classname");
                    } catch (IOException unused) {
                        a2 = "com.tencent.mapsdk.BuildConfig";
                    }
                }
            }
            Class<?> cls = Class.forName(a2);
            for (Field field : cls.getDeclaredFields()) {
                char c2 = 1;
                field.setAccessible(true);
                String name = field.getName();
                switch (name.hashCode()) {
                    case -2051118828:
                        if (name.equals("VERSION_CODE")) {
                            c2 = '\f';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -2050804302:
                        if (name.equals("VERSION_NAME")) {
                            c2 = 7;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1781919750:
                        if (name.equals("SEARCH_ENABLE")) {
                            c2 = 3;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1256894524:
                        if (name.equals("BEACON_KEY")) {
                            c2 = '\t';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -783990306:
                        if (name.equals("BEACON_ENABLE")) {
                            c2 = '\b';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -758346991:
                        if (name.equals("SHEET_PROJECT_NAME")) {
                            c2 = 5;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -368721951:
                        if (name.equals("BUGLY_KEY")) {
                            c2 = '\n';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 44249739:
                        if (name.equals("BUILD_TYPE")) {
                            c2 = '\r';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 64921139:
                        if (name.equals("DEBUG")) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 196363279:
                        if (name.equals("PLUGIN_ENABLE")) {
                            c2 = 4;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1766588577:
                        if (name.equals("BUGLY_ENABLE")) {
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 2076249758:
                        if (name.equals("FLAVOR")) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 2095911147:
                        if (name.equals("REPO_VERSION")) {
                            c2 = 6;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 2107919841:
                        if (name.equals("BUGLY_KEY_SHARE")) {
                            c2 = 11;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        d = ((Boolean) field.get(cls)).booleanValue();
                        str = "[DEBUG]:" + d;
                        break;
                    case 1:
                        boolean booleanValue = ((Boolean) field.get(cls)).booleanValue();
                        f16849e = booleanValue;
                        if (booleanValue) {
                            p.add(b.BUGLY);
                        }
                        str = "[BUGLY]:" + f16849e;
                        break;
                    case 2:
                        f16848c = (String) field.get(cls);
                        str = "[FLAVOR]:" + f16848c;
                        break;
                    case 3:
                        f16850f = ((Boolean) field.get(cls)).booleanValue();
                        str = "[SEARCH]:" + f16850f;
                        break;
                    case 4:
                        boolean booleanValue2 = ((Boolean) field.get(cls)).booleanValue();
                        f16851g = booleanValue2;
                        if (booleanValue2) {
                            p.add(b.PLUGIN);
                        }
                        str = "[PLUGIN]:" + f16851g;
                        break;
                    case 5:
                        f16858n = (String) field.get(cls);
                        continue;
                    case 6:
                        o = (String) field.get(cls);
                        str = "[REPO]:" + o;
                        break;
                    case 7:
                        f16854j = (String) field.get(cls);
                        str = "[VER]:" + f16854j;
                        break;
                    case '\b':
                        boolean booleanValue3 = ((Boolean) field.get(cls)).booleanValue();
                        f16852h = booleanValue3;
                        if (booleanValue3) {
                            p.add(b.BEACON);
                        }
                        str = "[BEACON]:" + f16852h;
                        break;
                    case '\t':
                        f16855k = (String) field.get(cls);
                        continue;
                    case '\n':
                        f16856l = (String) field.get(cls);
                        continue;
                    case 11:
                        f16857m = (String) field.get(cls);
                        continue;
                    case '\f':
                        f16853i = ((Integer) field.get(cls)).intValue();
                        str = "[VER_CODE]:" + f16853i;
                        break;
                    case '\r':
                        b = (String) field.get(cls);
                        str = "[BUILD_TYPE]:" + b;
                        break;
                    default:
                }
                ma.a(la.w, str);
            }
        } catch (ClassNotFoundException e2) {
            message = e2.getMessage();
            illegalAccessException = e2;
            ma.f(message, illegalAccessException);
        } catch (IllegalAccessException e3) {
            message = e3.getMessage();
            illegalAccessException = e3;
            ma.f(message, illegalAccessException);
        }
    }

    public static String b() {
        return f16854j + "-" + o;
    }
}
