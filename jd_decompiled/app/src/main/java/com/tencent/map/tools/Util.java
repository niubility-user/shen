package com.tencent.map.tools;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.dynamic.DYConstants;
import com.tencent.mapsdk.internal.hi;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class Util {
    private static final boolean DEBUG = false;
    private static final int DEFAUlT_GlEsVersion = 65537;
    private static final String DUID_FILE_NAME = "txlbs_duid";
    private static final String DUID_KEY = "txlbs_key_duid";
    public static final String META_NAME_API_KEY = "TencentMapSDK";
    public static final int SMALL_SCREEN_THRESHOLD = 400;
    private static final String SUID_FILE_NAME = "txlbs_suid";
    private static final String SUID_KEY = "txlbs_key_suid";
    private static final String TAG = "Util";

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static String filterBuilder(String... strArr) {
        StringBuilder sb;
        String str;
        if (strArr != null) {
            String str2 = "category=";
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (i2 == 0) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str = strArr[i2];
                } else {
                    sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    str = strArr[i2];
                }
                sb.append(str);
                str2 = sb.toString();
            }
            return str2;
        }
        return "";
    }

    public static <T> Class<? extends T> findClass(String str, Class<T> cls, ClassLoader classLoader) {
        try {
            Class<? extends T> cls2 = (Class<? extends T>) Class.forName(str, false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return cls2;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Class findClass(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Field findField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            if (cls.getSuperclass() != Object.class) {
                return findField(cls.getSuperclass(), str);
            }
            return null;
        }
    }

    public static Method findMethod(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            if (cls.getSuperclass() != Object.class) {
                return findMethod(cls.getSuperclass(), str, clsArr);
            }
            return null;
        }
    }

    public static <T> void foreach(Iterable<T> iterable, Callback<T> callback) {
        if (iterable == null || callback == null) {
            return;
        }
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            callback.callback(it.next());
        }
    }

    public static <T> void foreach(T[] tArr, Callback<T> callback) {
        if (tArr == null || callback == null) {
            return;
        }
        for (T t : tArr) {
            callback.callback(t);
        }
    }

    public static String getAppName(Context context) {
        String rawAppName = getRawAppName(context);
        try {
            return URLEncoder.encode(rawAppName, "utf-8");
        } catch (Exception unused) {
            return rawAppName;
        }
    }

    public static String getAppVersion(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName + packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static float getDensity(Context context) {
        if (context == null) {
            return 1.0f;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getDuid(Context context) {
        return context == null ? "" : getDuidFromSharePrerences(context);
    }

    private static String getDuidFromSharePrerences(Context context) {
        String sharePreference = getSharePreference(context, DUID_FILE_NAME, DUID_KEY);
        if (TextUtils.isEmpty(sharePreference)) {
            String a = hi.a(context);
            saveSharePreference(context, DUID_FILE_NAME, DUID_KEY, a);
            return a;
        }
        return sharePreference;
    }

    public static Object getField(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = obj.getClass();
            if (obj instanceof Class) {
                cls = (Class) obj;
            }
            Field findField = findField(cls, str);
            if (findField != null) {
                findField.setAccessible(true);
                return findField.get(obj);
            }
        } catch (IllegalAccessException unused) {
        }
        return null;
    }

    public static String getMD5String(String str) {
        return TextUtils.isEmpty(str) ? "" : getMD5String(str.getBytes());
    }

    public static String getMD5String(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                byte b = digest[i3];
                int i4 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i4 + 1;
                cArr2[i4] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getMetaKey(Context context, String str) {
        Bundle bundle;
        if (context == null) {
            return "";
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? "" : bundle.getString(str);
    }

    public static int getOpenglesVersion(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        return deviceConfigurationInfo != null ? deviceConfigurationInfo.reqGlEsVersion : DEFAUlT_GlEsVersion;
    }

    public static String getProcessName(int i2) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i2 + "/cmdline"));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return readLine;
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                return null;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getRawAppName(Context context) {
        if (context == null) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return (applicationInfo != null ? applicationInfo.loadLabel(packageManager) : "can't find app name").toString();
    }

    public static String getResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + ProxyConfig.MATCH_ALL_SCHEMES + displayMetrics.heightPixels;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSharePreference(Context context, String str, String str2) {
        return context == null ? "" : context.getSharedPreferences(str, 0).getString(str2, "");
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getSuid(Context context) {
        return context == null ? "" : getSuidFromSharePrerences(context);
    }

    private static String getSuidFromSharePrerences(Context context) {
        String sharePreference = getSharePreference(context, SUID_FILE_NAME, SUID_KEY);
        if (TextUtils.isEmpty(sharePreference)) {
            String uuid = getUUID();
            saveSharePreference(context, SUID_FILE_NAME, SUID_KEY, uuid);
            return uuid;
        }
        return sharePreference;
    }

    public static String getUUID() {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception unused) {
            return System.currentTimeMillis() + "";
        }
    }

    public static int getWindowHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Object invokeMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method findMethod = findMethod(obj.getClass(), str, clsArr);
            if (findMethod != null) {
                findMethod.setAccessible(true);
                return findMethod.invoke(obj, objArr);
            }
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getTargetException());
        }
        return null;
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i2 = 0; i2 < length; i2++) {
            clsArr[i2] = objArr[i2].getClass();
        }
        try {
            Method findMethod = findMethod(obj.getClass(), str, clsArr);
            if (findMethod != null) {
                findMethod.setAccessible(true);
                return findMethod.invoke(obj, objArr);
            }
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getTargetException());
        }
        return null;
    }

    public static Object invokeStaticMethod(Class cls, String str, Class[] clsArr, Object[] objArr) {
        if (cls == null) {
            return null;
        }
        try {
            Method findMethod = findMethod(cls, str, clsArr);
            if (findMethod != null) {
                findMethod.setAccessible(true);
                return findMethod.invoke(cls, objArr);
            }
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getTargetException());
        }
        return null;
    }

    private static boolean isMatchClassTypes(Class<?>[] clsArr, Object[] objArr) {
        if (clsArr.length != objArr.length) {
            return false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < clsArr.length; i3++) {
            if (clsArr[i3] == objArr[i3].getClass() || clsArr[i3].isAssignableFrom(objArr[i3].getClass())) {
                i2++;
            }
        }
        return i2 == clsArr.length;
    }

    public static <T> List<T> listWhere(Iterable<T> iterable, Condition<T> condition) {
        List<T> emptyList = Collections.emptyList();
        if (iterable != null && condition != null) {
            for (T t : iterable) {
                if (condition.callback((Condition<T>) t).booleanValue()) {
                    emptyList.add(t);
                }
            }
        }
        return emptyList;
    }

    public static <T> T newInstance(Class<T> cls, Object... objArr) {
        if (cls != null && !cls.isEnum() && !cls.isInterface() && !cls.isAnnotation() && !cls.isArray()) {
            Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
            for (int i2 = 0; i2 < declaredConstructors.length; i2++) {
                if (isMatchClassTypes(declaredConstructors[i2].getParameterTypes(), objArr)) {
                    try {
                        declaredConstructors[i2].setAccessible(true);
                        return (T) declaredConstructors[i2].newInstance(objArr);
                    } catch (IllegalAccessException | InstantiationException unused) {
                        continue;
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2.getTargetException());
                    }
                }
            }
            try {
                return cls.newInstance();
            } catch (IllegalAccessException unused2) {
            } catch (InstantiationException e3) {
                throw new RuntimeException(e3);
            }
        }
        return null;
    }

    public static void saveSharePreference(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }

    public static void setField(Object obj, String str, Object obj2) {
        if (obj == null) {
            return;
        }
        try {
            Field findField = findField(obj.getClass(), str);
            if (findField != null) {
                findField.setAccessible(true);
                findField.set(obj, obj2);
            }
        } catch (IllegalAccessException unused) {
        }
    }

    public static <T> T singleWhere(Iterable<T> iterable, Condition<T> condition) {
        if (iterable != null && condition != null) {
            for (T t : iterable) {
                if (condition.callback((Condition<T>) t).booleanValue()) {
                    return t;
                }
            }
        }
        return null;
    }

    public static <T> boolean where(Iterable<T> iterable, ReturnCallback<Boolean, T> returnCallback) {
        if (iterable != null && returnCallback != null) {
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                if (returnCallback.callback(it.next()).booleanValue()) {
                    return true;
                }
            }
            returnCallback.callback(null);
        }
        return false;
    }

    public static <T> boolean where(T[] tArr, ReturnCallback<Boolean, T> returnCallback) {
        if (tArr != null && returnCallback != null) {
            for (T t : tArr) {
                if (returnCallback.callback(t).booleanValue()) {
                    return true;
                }
            }
            returnCallback.callback(null);
        }
        return false;
    }
}
