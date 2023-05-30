package com.jingdong.aura.a.b;

import android.os.Build;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.a.c.l;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public final class f extends BaseDexClassLoader implements com.jingdong.aura.a.b.j.c {

    /* renamed from: c  reason: collision with root package name */
    private static final List<URL> f12101c;
    private static final HashSet<String> d;

    /* renamed from: e  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12102e = com.jingdong.aura.core.util.l.c.a("BundleClassLoader");
    private final com.jingdong.aura.a.b.l.a a;
    private h b;

    static {
        HashSet<String> hashSet = new HashSet<>();
        d = hashSet;
        hashSet.add("com.jingdong.aura.core.framework");
        hashSet.add("org.osgi.framework");
        hashSet.add("org.osgi.service.packageadmin");
        hashSet.add("org.osgi.service.startlevel");
        f12101c = new ArrayList();
    }

    public f(h hVar, String str) {
        super(OrderISVUtil.MONEY_DECIMAL, null, str, Object.class.getClassLoader());
        if (Build.VERSION.SDK_INT >= 25) {
            try {
                Class<?> cls = Class.forName("com.android.internal.os.PathClassLoaderFactory");
                Method declaredMethod = cls.getDeclaredMethod("createClassloaderNamespace", ClassLoader.class, Integer.TYPE, String.class, String.class, Boolean.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(cls, this, Integer.valueOf(l.a.getApplicationInfo().targetSdkVersion), str, str, Boolean.TRUE);
            } catch (Throwable unused) {
            }
        }
        this.b = hVar;
        com.jingdong.aura.a.b.l.a e2 = hVar.e();
        this.a = e2;
        if (e2 != null) {
            return;
        }
        throw new l.b.a.b("Not Component valid bundle: " + hVar.b());
    }

    private Class<?> b(String str) {
        try {
            return this.a.a(str, this);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private static String c(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > -1 ? str.substring(0, lastIndexOf) : "";
    }

    private static String d(String str) {
        return (str.startsWith("/") || str.startsWith("\\")) ? str.substring(1) : str;
    }

    @Override // com.jingdong.aura.a.b.j.c
    public h a() {
        return this.b;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) {
        List<String> d2;
        if (d.contains(c(str))) {
            return com.jingdong.aura.a.b.k.b.i().loadClass(str);
        }
        Class<?> b = b(str);
        if (b != null) {
            return b;
        }
        if (this.b != null && (d2 = com.jingdong.aura.a.a.a.c().d(this.b.b())) != null && d2.size() > 0) {
            for (int i2 = 0; i2 < d2.size(); i2++) {
                try {
                    h hVar = (h) com.jingdong.aura.a.b.k.b.b(d2.get(i2));
                    if (hVar == null) {
                        continue;
                    } else {
                        try {
                            hVar.n();
                        } catch (l.b.a.b e2) {
                            e2.printStackTrace();
                        }
                        Class<?> loadClass = hVar.g().loadClass(str);
                        if (loadClass != null) {
                            return loadClass;
                        }
                    }
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        try {
            Class<?> loadClass2 = com.jingdong.aura.a.b.k.b.i().loadClass(str);
            if (loadClass2 != null) {
                return loadClass2;
            }
            throw new ClassNotFoundException("Can't find class " + str + " in BundleClassLoader: " + this.b.b());
        } catch (Exception e3) {
            throw new ClassNotFoundException("Can't find class " + str + " in BundleClassLoader: " + this.b.b(), e3);
        }
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        com.jingdong.aura.core.util.l.b bVar = f12102e;
        bVar.a("findLibrary: " + str);
        File b = this.a.b(System.mapLibraryName(str));
        if (b != null) {
            bVar.a("findLibrary result:" + b.getAbsolutePath());
            return b.getAbsolutePath();
        }
        try {
            String str2 = (String) com.jingdong.aura.core.reflection.b.r.a(com.jingdong.aura.a.b.k.b.i(), str);
            bVar.a("findLibrary from apk:" + str2);
            return str2;
        } catch (Exception e2) {
            com.jingdong.aura.core.util.l.b bVar2 = f12102e;
            bVar2.d("findLibrary: failed:");
            bVar2.b(e2.getMessage(), e2);
            return null;
        }
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected URL findResource(String str) {
        String d2 = d(str);
        List<URL> b = b(d2, false);
        if (b.size() > 0) {
            return b.get(0);
        }
        List<URL> a = a(d2, false);
        b.addAll(a(d2, true));
        if (a.size() > 0) {
            return a.get(0);
        }
        return null;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) {
        return Collections.enumeration(b(d(str), true));
    }

    @Override // dalvik.system.BaseDexClassLoader
    public String toString() {
        return "BundleClassLoader[Bundle" + this.b + "]";
    }

    public static void a(String str) {
        HashSet<String> hashSet = d;
        if (hashSet == null) {
            return;
        }
        hashSet.add(str);
    }

    private List<URL> b(String str, boolean z) {
        try {
            return this.a.a(str);
        } catch (IOException e2) {
            e2.printStackTrace();
            return f12101c;
        }
    }

    private List<URL> a(String str, boolean z) {
        return f12101c;
    }
}
