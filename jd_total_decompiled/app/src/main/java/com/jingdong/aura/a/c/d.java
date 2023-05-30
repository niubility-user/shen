package com.jingdong.aura.a.c;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.aura.a.a.a;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class d {
    private static Hashtable<Integer, String> a = new Hashtable<>();
    private static int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static com.jingdong.aura.core.util.l.b f12133c = com.jingdong.aura.core.util.l.c.a("ClassLoadFromBundle");

    private static Class<?> a(com.jingdong.aura.a.b.h hVar, String str, List<l.b.a.d> list) {
        hVar.d().d();
        ClassLoader g2 = hVar.g();
        try {
            hVar.n();
        } catch (l.b.a.b e2) {
            e2.printStackTrace();
        }
        if (g2 != null) {
            try {
                Class<?> loadClass = g2.loadClass(str);
                if (loadClass != null) {
                    return loadClass;
                }
            } catch (ClassNotFoundException e3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Can't find class ");
                sb.append(str);
                sb.append(" in BundleClassLoader:");
                sb.append(hVar.b());
                sb.append("[");
                sb.append(list == null ? 0 : list.size());
                sb.append("]. classloader = ");
                sb.append(g2 == null ? DYConstants.DY_NULL_STR : "not null");
                sb.append(". packageversion ");
                sb.append(a());
                throw new ClassNotFoundException(sb.toString(), e3);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Can't find class ");
        sb2.append(str);
        sb2.append(" in BundleClassLoader: ");
        sb2.append(hVar.b());
        sb2.append(" [");
        sb2.append(list.size());
        sb2.append("]");
        sb2.append(g2 == null ? "classloader is null" : "classloader not null");
        sb2.append(" packageversion ");
        sb2.append(a());
        throw new ClassNotFoundException(sb2.toString());
    }

    private static void b(String str, String str2) {
        a.put(Integer.valueOf(b), " Not found class " + str + " because " + str2);
        int i2 = b + 1;
        b = i2;
        b = i2 % 10;
    }

    public static void c(String str) {
        if (str == null) {
            return;
        }
        try {
            synchronized (str) {
                com.jingdong.aura.a.a.d.f();
                String a2 = com.jingdong.aura.a.a.c.a().a(str);
                if (com.jingdong.aura.core.util.h.a(a2)) {
                    a2 = com.jingdong.aura.a.a.a.c().b(str);
                }
                if (TextUtils.isEmpty(a2)) {
                    String str2 = a2 == null ? "" : a2;
                    com.jingdong.aura.a.b.e.a(str2, "checkInstallBundleIfNeed", str, "not found bundleName for component :" + str + " mBundleInfoList:" + com.jingdong.aura.a.a.a.c().f(str2), "ClassLoadFromBundle_checkInstallBundleIfNeed", null);
                    com.jingdong.aura.core.util.l.b bVar = f12133c;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to find the bundle in BundleInfoList for component ");
                    sb.append(a2);
                    bVar.d(sb.toString());
                    b(str, "not found in BundleInfoList!");
                    return;
                }
                b(a2);
            }
        } catch (Exception e2) {
            String str3 = "" == 0 ? "" : "";
            if (!com.jingdong.aura.core.util.i.a(str)) {
                com.jingdong.aura.a.b.e.a(str3, "checkInstallBundleIfNeed", str, "checkInstallBundleIfNeed failed", "ClassLoadFromBundle_checkInstallBundleIfNeed", e2);
                com.jingdong.aura.a.b.e.a("ClassNotFound", "checkInstallBundleIfNeed failed " + str, "ClassLoadFromBundle", e2);
                return;
            }
            com.jingdong.aura.a.b.e.a(str3, "checkInstallBundleIfNeed", str, "checkInstallBundleIfNeed activity failed ", "ClassLoadFromBundle_checkInstallBundleIfNeed", e2);
            throw e2;
        }
    }

    private static File d(String str) {
        String concat = "lib".concat(str).concat(".so");
        File f2 = f(concat);
        if (f2 == null) {
            return null;
        }
        if (a(str, f2)) {
            return f2;
        }
        if (com.jingdong.aura.a.a.d.a(str)) {
            return null;
        }
        f12133c.d(" can not find the library " + concat + " for bundle" + str);
        throw new RuntimeException("can not find the library " + concat + " for bundle" + str + " from ClassLoadFromBundle.loadOriginalBundle_3");
    }

    public static String e(String str) {
        for (int i2 = 0; i2 < a.size(); i2++) {
            if ((a.get(Integer.valueOf(i2)) + "").contains(str + "")) {
                return a.get(Integer.valueOf(i2)) + "";
            }
        }
        return "";
    }

    private static File f(String str) {
        Application application;
        if (str == null) {
            return null;
        }
        String c2 = com.jingdong.aura.a.b.k.b.c("com.jingdong.aura.AppDirectory");
        File file = c2 != null ? new File(new File(c2, "lib"), str) : null;
        return ((file != null && !com.jingdong.aura.a.b.c.M() && file.exists()) || (application = l.a) == null || application.getApplicationInfo() == null || l.a.getApplicationInfo().nativeLibraryDir == null) ? file : new File(l.a.getApplicationInfo().nativeLibraryDir, str);
    }

    private static void g(String str) {
        File file;
        String str2;
        if (com.jingdong.aura.a.a.a.c().i(str)) {
            file = null;
        } else {
            file = d(str);
            if (file == null) {
                c(str, "lib".concat(str).concat(".so"));
                return;
            }
        }
        File file2 = file;
        a(str, "disk size not enough from ClassLoadFromBundle.loadOriginalBundle_2 for bundle " + str);
        try {
            a.C0391a a2 = com.jingdong.aura.a.a.a.c().a(str);
            long j2 = 0;
            if (a2 != null) {
                j2 = a2.f12077j;
                str2 = a2.f12079l;
            } else {
                str2 = "";
            }
            com.jingdong.aura.a.b.a.b().a(str, file2, j2, str2);
        } catch (Throwable th) {
            try {
                com.jingdong.aura.a.b.e.a(str, "ClassLoadFromBundle", str, "load bundle error", "ClassLoadFromBundle_loadBundle", th);
            } catch (Throwable unused) {
            }
            f12133c.a("failed to install bundle " + str, th);
            throw new RuntimeException("Aura failed to install bundle " + str + ", from ClassLoadFromBundle.loadOriginalBundle_1 ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> h(String str) {
        ClassLoader g2;
        List<l.b.a.d> c2 = com.jingdong.aura.a.b.k.b.c();
        Class<?> cls = null;
        if (c2 != null && !c2.isEmpty()) {
            Iterator<l.b.a.d> it = c2.iterator();
            while (it.hasNext()) {
                com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) it.next();
                j a2 = g.a(hVar.b());
                if (a2 != null && (a2.f12136e.contains(str) || a2.f12137f.contains(str))) {
                    try {
                        cls = a(hVar, str, c2);
                    } catch (Exception e2) {
                        if (!com.jingdong.aura.core.util.i.a(str)) {
                            com.jingdong.aura.a.b.e.a(hVar.b(), "loadClassFromInstalledBundles", str, "loadClassFromInstalledBundles failed,packageLite:manualComponents:" + a2.f12137f + " components:" + a2.f12136e, "ClassLoadFromBundle_loadClassFromInstalledBundles", e2);
                            StringBuilder sb = new StringBuilder();
                            sb.append("loadClassFromInstalledBundles failed ");
                            sb.append(str);
                            com.jingdong.aura.a.b.e.a("ClassNotFound", sb.toString(), "loadClassFromInstalledBundles", e2);
                        } else {
                            throw e2;
                        }
                    }
                }
                if (cls != null) {
                    return cls;
                }
            }
        }
        if (c2 != null && !c2.isEmpty()) {
            Iterator<l.b.a.d> it2 = com.jingdong.aura.a.b.k.b.c().iterator();
            while (it2.hasNext()) {
                com.jingdong.aura.a.b.h hVar2 = (com.jingdong.aura.a.b.h) it2.next();
                hVar2.d().d();
                try {
                    if (com.jingdong.aura.a.b.c.S() || hVar2.k() < 8) {
                        f12133c.a("start bundle by back up:" + hVar2.b());
                        hVar2.n();
                    }
                } catch (l.b.a.b e3) {
                    e3.printStackTrace();
                }
                if (hVar2.d().a() && (g2 = hVar2.g()) != null) {
                    try {
                        cls = g2.loadClass(str);
                        if (cls != null) {
                            return cls;
                        }
                    } catch (ClassNotFoundException unused) {
                    }
                }
            }
        }
        return cls;
    }

    private static boolean i(String str) {
        if (!com.jingdong.aura.a.b.c.w()) {
            f12133c.b("release host app,can't load hot debug bundle:" + str);
            return false;
        } else if (str == null) {
            f12133c.b("location is null");
            return false;
        } else {
            String concat = "lib".concat(str).concat(".so");
            String c2 = com.jingdong.aura.a.b.k.b.c("com.jingdong.aura.AppDirectory.debug");
            if (c2 == null) {
                f12133c.b("not config bundle hot debug path");
                return false;
            }
            File file = new File(new File(c2, "lib"), concat);
            if (!file.exists()) {
                f12133c.b("hot debug bundle " + str + " not exist");
                return false;
            }
            a(str, "disk size not enough from ClassLoadFromBundle.loadDebugBundleFile for bundle " + str);
            try {
                com.jingdong.aura.a.b.a.b().a(str, file, com.jingdong.aura.core.util.i.b(l.a, file.getAbsolutePath()), com.jingdong.aura.core.util.d.b(file.getAbsolutePath()));
                return true;
            } catch (Throwable th) {
                f12133c.d("load debug bundle fail:" + str);
                th.printStackTrace();
                return false;
            }
        }
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!com.jingdong.aura.a.a.d.a(str) && !com.jingdong.aura.a.a.a.c().i(str)) {
            com.jingdong.aura.a.b.e.a(str, "checkInstallBundleByBundleName", str, str + " not found in mInternalBundles", "ClassLoadFromBundle_checkInstallBundleByBundleName", null);
            b(str, "not found in mInternalBundles!");
            return;
        }
        a(str);
    }

    private static void a(String str) {
        List<String> d = com.jingdong.aura.a.a.a.c().d(str);
        if (d != null && d.size() > 0) {
            for (int i2 = 0; i2 < d.size(); i2++) {
                a(d.get(i2));
            }
        }
        if (com.jingdong.aura.a.b.k.b.b(str) != null) {
            return;
        }
        if (i(str)) {
            f12133c.b("load hot debug bundle:" + str);
        } else if (com.jingdong.aura.a.b.a.b().a(str) == null) {
            f12133c.a("load bundle:" + str);
            g(str);
        }
    }

    private static void c(String str, String str2) {
        String str3;
        a(str, "disk size not enough from ClassLoadFromBundle.installFromApkZip for bundle" + str);
        try {
            a.C0391a a2 = com.jingdong.aura.a.a.a.c().a(str);
            long j2 = 0;
            if (a2 != null) {
                j2 = a2.f12077j;
                str3 = a2.f12079l;
            } else {
                str3 = "";
            }
            com.jingdong.aura.a.b.a.b().a(str, com.jingdong.aura.a.a.d.c(str2), j2, str3);
        } catch (Exception e2) {
            try {
                com.jingdong.aura.a.b.e.a(str, "ClassLoadFromBundle", str, "load bundle error", "ClassLoadFromBundle_installFromApkZip", e2);
            } catch (Throwable unused) {
            }
            f12133c.b("Failed to install bundle " + str2 + " from APK zipfile ", e2);
            throw new RuntimeException("Failed to install bundle " + str2 + " from APK zipfile ", e2);
        }
    }

    private static int a() {
        PackageInfo packageInfo;
        try {
            packageInfo = l.a.getPackageManager().getPackageInfo(l.a.getPackageName(), 0);
        } catch (Throwable th) {
            f12133c.a("Error to get PackageInfo >>>", th);
            packageInfo = new PackageInfo();
        }
        return packageInfo.versionCode;
    }

    private static void a(String str, String str2) {
        if (com.jingdong.aura.a.b.c.a(str)) {
            return;
        }
        f12133c.d("disk size not enough");
        throw new RuntimeException(str2);
    }

    private static boolean a(String str, File file) {
        ZipFile zipFile = null;
        try {
            ZipFile zipFile2 = new ZipFile(file);
            try {
                if (file.exists() && file.isFile() && file.canRead() && file.length() > 0) {
                    com.jingdong.aura.core.util.d.a(zipFile2);
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(file.exists() ? "exist" : "not exit");
                sb.append(file.isFile() ? "isFile" : "not file");
                sb.append(file.canRead() ? "canRead" : "can note read");
                sb.append(file.length());
                sb.toString();
                com.jingdong.aura.core.util.d.a(zipFile2);
                return false;
            } catch (Exception unused) {
                zipFile = zipFile2;
                com.jingdong.aura.core.util.d.a(zipFile);
                return false;
            } catch (Throwable th) {
                th = th;
                zipFile = zipFile2;
                com.jingdong.aura.core.util.d.a(zipFile);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
