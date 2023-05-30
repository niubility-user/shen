package com.jingdong.aura.core.runing.resource;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.reflection.Hack;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class DelegateResourcesUtils {
    private static final com.jingdong.aura.core.util.l.b log = com.jingdong.aura.core.util.l.c.a("DelegateResourcesUtils");

    public static Resources constructResources(AssetManager assetManager, Resources resources, a aVar) {
        if (resources != null && resources.getClass().getName().equals("android.content.res.MiuiResources") && Build.VERSION.SDK_INT < 29) {
            Constructor<?> declaredConstructor = Class.forName("android.content.res.MiuiResources").getDeclaredConstructor(AssetManager.class, DisplayMetrics.class, Configuration.class);
            declaredConstructor.setAccessible(true);
            return (Resources) declaredConstructor.newInstance(assetManager, com.jingdong.aura.a.b.c.F().getOsDisplayMetrics(resources), com.jingdong.aura.a.b.c.F().getOsConfiguration(resources));
        }
        return aVar.a(assetManager, resources);
    }

    public static void ensureResourcesInjected(Activity activity) {
        updateConfiguration(activity);
        com.jingdong.aura.a.c.e eVar = new com.jingdong.aura.a.c.e(activity.getBaseContext(), activity.getClass().getClassLoader());
        if (com.jingdong.aura.core.reflection.b.A != null) {
            try {
                validateActivityResource(activity);
            } catch (Throwable unused) {
            }
            com.jingdong.aura.core.reflection.b.A.a((Hack.d<ContextThemeWrapper, Resources>) activity, l.d);
        }
        Hack.d<ContextThemeWrapper, Context> dVar = com.jingdong.aura.core.reflection.b.z;
        if (dVar != null && dVar.a() != null) {
            com.jingdong.aura.core.reflection.b.z.a((Hack.d<ContextThemeWrapper, Context>) activity, eVar);
        }
        com.jingdong.aura.core.reflection.b.x.a((Hack.d<ContextWrapper, Context>) activity, eVar);
    }

    private static List<String> getApkAssets(AssetManager assetManager) {
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = Class.forName("android.content.res.ApkAssets").getDeclaredMethod("getAssetPath", new Class[0]);
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = Class.forName("android.content.res.AssetManager").getDeclaredMethod("getApkAssets", new Class[0]);
            declaredMethod2.setAccessible(true);
            Object[] objArr = (Object[]) declaredMethod2.invoke(assetManager, new Object[0]);
            for (Object obj : objArr) {
                String str = (String) declaredMethod.invoke(obj, new Object[0]);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (Exception e2) {
            e2.printStackTrace();
            arrayList.clear();
            return arrayList;
        }
    }

    public static String getAssetHistoryPaths(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return "newDelegateResources []";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("newDelegateResources [");
        for (String str : set) {
            stringBuffer.append(str);
            stringBuffer.append("(");
            stringBuffer.append(getFileLength(str));
            stringBuffer.append("),");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.setLength(stringBuffer.length() - 1);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static List<String> getAssetPathFromResources(Resources resources) {
        try {
            return getOriginAssetsPath((AssetManager) com.jingdong.aura.core.reflection.b.J.a((Hack.d<Resources, Object>) resources));
        } catch (Exception e2) {
            log.b("DelegateResource" + e2.getCause());
            return null;
        }
    }

    public static Configuration getConfigurationRecursive(Context context, int i2) {
        if (i2 >= 15) {
            return null;
        }
        int i3 = i2 + 1;
        if (context instanceof com.jingdong.aura.a.c.e) {
            return getConfigurationRecursive(((com.jingdong.aura.a.c.e) context).getBaseContext(), i3);
        }
        log.b("getConfigurationRecursive successfully with depth " + i3);
        return context.getResources().getConfiguration();
    }

    public static int getFieldValueOfR(Class<?> cls, String str) {
        if (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    return ((Integer) declaredField.get(null)).intValue();
                }
                return 0;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
                return 0;
            }
        }
        return 0;
    }

    public static long getFileLength(String str) {
        if (str == null) {
            return -2L;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return -4L;
    }

    public static List<String> getOriginAssetsPath(AssetManager assetManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return getApkAssets(assetManager);
        }
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = assetManager.getClass().getDeclaredMethod("getStringBlockCount", new Class[0]);
            declaredMethod.setAccessible(true);
            int intValue = ((Integer) declaredMethod.invoke(assetManager, new Object[0])).intValue();
            int i2 = 0;
            while (i2 < intValue) {
                i2++;
                String str = (String) assetManager.getClass().getMethod("getCookieName", Integer.TYPE).invoke(assetManager, Integer.valueOf(i2));
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (Exception e2) {
            e2.printStackTrace();
            arrayList.clear();
            return arrayList;
        }
    }

    public static Configuration getRawConfiguration(Activity activity) {
        long nanoTime = System.nanoTime();
        if (activity != null) {
            try {
                return getConfigurationRecursive(activity.getBaseContext(), 0);
            } catch (Throwable th) {
                try {
                    com.jingdong.aura.core.util.l.b bVar = log;
                    bVar.a("getRawConfiguration", th);
                    bVar.b("getRawConfiguration spend " + (System.nanoTime() - nanoTime) + " nanoSeconds.");
                    return null;
                } finally {
                    long nanoTime2 = System.nanoTime() - nanoTime;
                    log.b("getRawConfiguration spend " + nanoTime2 + " nanoSeconds.");
                }
            }
        }
        return null;
    }

    public static int getResIdentifier(String str, String str2, String str3, Map<String, Integer> map) {
        List<l.b.a.d> c2;
        ClassLoader g2;
        int intValue;
        com.jingdong.aura.core.util.l.b bVar = log;
        bVar.a("getResIdentifier: name:" + str + " defType:" + str2 + " defPackage:" + str3 + " resIdentifierMap:" + map);
        if (str2 == null && str3 == null && isLegalResName(str)) {
            String substring = str.substring(str.indexOf("/") + 1);
            int indexOf = str.indexOf(":") + 1;
            int indexOf2 = str.indexOf("/");
            if (indexOf < indexOf2) {
                str2 = str.substring(indexOf, indexOf2);
                str = substring;
            } else {
                bVar.c("getResIdentifier: startIndex:" + indexOf + " endIndex:" + indexOf2);
            }
            bVar.a("getResIdentifier: name=" + str + " defType:" + str2);
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && (c2 = com.jingdong.aura.a.b.k.b.c()) != null && !c2.isEmpty()) {
            for (l.b.a.d dVar : com.jingdong.aura.a.b.k.b.c()) {
                String b = dVar.b();
                String str4 = b + ":" + str;
                if (map.isEmpty() && map.containsKey(str4) && (intValue = map.get(str4).intValue()) != 0) {
                    return intValue;
                }
                h hVar = (h) dVar;
                if (hVar.e().a() && (g2 = hVar.g()) != null) {
                    try {
                        int fieldValueOfR = getFieldValueOfR(g2.loadClass(b + ".R$" + str2), str);
                        if (fieldValueOfR != 0) {
                            map.put(str4, Integer.valueOf(fieldValueOfR));
                            return fieldValueOfR;
                        }
                        continue;
                    } catch (ClassNotFoundException unused) {
                    }
                }
            }
        }
        return 0;
    }

    public static String getRuntimeAssetHistoryPaths() {
        a b = a.b();
        if (b == null) {
            return "";
        }
        if (b instanceof f) {
            return getAssetHistoryPaths(((f) b).d);
        }
        return b instanceof g ? getAssetHistoryPaths(((g) b).d) : "";
    }

    public static boolean isLegalResName(String str) {
        return !TextUtils.isEmpty(str) && str.contains("/") && str.contains(":");
    }

    public static boolean isLowLevel() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 20) {
            return true;
        }
        return com.jingdong.aura.a.b.c.g() && i2 <= 27 && e.a();
    }

    public static boolean isSharedLibraryPath(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if ((str.toLowerCase().contains("/product/app") || str.toLowerCase().contains("chrome") || str.toLowerCase().contains("webview")) && str.endsWith(".apk") && !str.contains("aura")) {
            String packageName = l.a.getPackageName();
            return packageName == null || packageName.isEmpty() || !str.contains(packageName.replace(OrderISVUtil.MONEY_DECIMAL, "/"));
        }
        return false;
    }

    public static void printAssetPath(String str, Set<String> set, com.jingdong.aura.core.util.l.b bVar) {
        if (bVar != null && bVar.a()) {
            StringBuilder sb = new StringBuilder(getAssetHistoryPaths(set));
            if (str != null) {
                sb.append(" Add new path:" + str);
            }
            bVar.b(sb.toString());
        }
    }

    public static void tryAddAssetPath(AssetManager assetManager, String str) {
        try {
            if (Build.VERSION.SDK_INT > 23 && isSharedLibraryPath(str)) {
                log.a("try add asset path as library: " + str);
                tryAddAssetPathAsSharedLibrary(assetManager, str);
            } else if (Integer.parseInt(com.jingdong.aura.core.reflection.b.f12159m.a(assetManager, str).toString()) == 0) {
                for (int i2 = 0; i2 < 3 && Integer.parseInt(com.jingdong.aura.core.reflection.b.f12159m.a(assetManager, str).toString()) == 0; i2++) {
                    if (i2 == 3) {
                        com.jingdong.aura.a.b.e.a("com.jingdong.aura", "Add asset path failed", "DelegateResources.tryAddAssetPath", null);
                    }
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            com.jingdong.aura.a.b.e.a("com.jingdong.aura", "", "DelegateResources.tryAddAssetPath_2", e2);
        }
    }

    public static void tryAddAssetPathAsSharedLibrary(AssetManager assetManager, String str) {
        try {
            if (Integer.parseInt(com.jingdong.aura.core.reflection.b.f12160n.a(assetManager, str).toString()) == 0) {
                for (int i2 = 0; i2 < 3; i2++) {
                    if (Integer.parseInt(com.jingdong.aura.core.reflection.b.f12160n.a(assetManager, str).toString()) != 0) {
                        return;
                    }
                    if (i2 == 3) {
                        com.jingdong.aura.a.b.e.a("com.jingdong.aura", "Add asset path failed", "DelegateResources.tryAddAssetPath", null);
                    }
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            com.jingdong.aura.a.b.e.a("com.jingdong.aura", "", "DelegateResources.tryAddAssetPathAsSharedLibrary_1", e2);
        }
    }

    public static void updateConfiguration(Activity activity) {
        if (!com.jingdong.aura.a.b.c.K()) {
            log.b("not update configuration!");
            return;
        }
        Resources resources = activity.getBaseContext().getResources();
        Configuration osConfiguration = com.jingdong.aura.a.b.c.F().getOsConfiguration(l.d);
        Configuration osConfiguration2 = com.jingdong.aura.a.b.c.F().getOsConfiguration(resources);
        if (!osConfiguration.equals(osConfiguration2)) {
            log.b("update configuration");
            l.d.updateConfiguration(osConfiguration2, com.jingdong.aura.a.b.c.F().getOsDisplayMetrics(resources));
            return;
        }
        log.b("configuration not changed");
    }

    public static void updateResources(Application application, AssetManager assetManager, Resources resources) {
        Resources resources2 = l.d;
        if (resources2 != null && resources2.getAssets() == assetManager && (l.d instanceof b)) {
            return;
        }
        com.jingdong.aura.core.util.l.b bVar = log;
        bVar.a("updateResources: delegateResources = " + l.d);
        StringBuilder sb = new StringBuilder();
        sb.append("updateResources: assert manager changed:");
        sb.append(l.d.getAssets() != assetManager);
        bVar.a(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updateResources: not delegate resource:");
        sb2.append(!(l.d instanceof b));
        bVar.a(sb2.toString());
        l.d = resources;
        com.jingdong.aura.core.reflection.a.a(application, resources);
    }

    private static boolean validateActivityResource(Activity activity) {
        Resources resources;
        String str;
        h hVar = (h) com.jingdong.aura.a.b.k.b.b(com.jingdong.aura.a.a.a.c().b(activity.getLocalClassName()));
        String absolutePath = hVar != null ? hVar.e().b().getAbsolutePath() : null;
        Hack.d<ContextThemeWrapper, Resources> dVar = com.jingdong.aura.core.reflection.b.A;
        if (dVar != null) {
            resources = dVar.a((Hack.d<ContextThemeWrapper, Resources>) activity);
        } else {
            resources = activity.getResources();
        }
        Resources resources2 = l.d;
        if (resources == resources2) {
            return true;
        }
        List<String> assetPathFromResources = getAssetPathFromResources(resources);
        String runtimeAssetHistoryPaths = getRuntimeAssetHistoryPaths();
        List<String> assetPathFromResources2 = getAssetPathFromResources(resources2);
        if (absolutePath == null || assetPathFromResources == null || assetPathFromResources.contains(absolutePath)) {
            str = null;
        } else {
            String str2 = "Activity Resources path not contains:" + hVar.e().b().getAbsolutePath();
            if (!runtimeAssetHistoryPaths.contains(absolutePath)) {
                str2 = str2 + "paths in history not contains:" + hVar.e().b().getAbsolutePath();
            }
            if (!assetPathFromResources2.contains(absolutePath)) {
                str2 = str2 + "paths in runtime not contains:" + hVar.e().b().getAbsolutePath();
            }
            if (!hVar.e().b().exists()) {
                str2 = str2 + "  Bundle archive file not exist:" + hVar.e().b().getAbsolutePath();
            }
            str = str2 + " Activity Resources paths length:" + assetPathFromResources.size();
        }
        if (str == null) {
            return true;
        }
        com.jingdong.aura.a.b.e.a(hVar.b(), "", "InstrumentationHook.validateActivityResource", null);
        return false;
    }
}
