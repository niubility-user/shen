package com.jingdong.aura.a.b.j;

import android.text.TextUtils;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.a.b.h;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends DexClassLoader implements c {

    /* renamed from: c  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12111c = com.jingdong.aura.core.util.l.c.a("BundleClassLoaderV2");
    private h a;
    private String b;

    public a(h hVar) {
        this(hVar.e().e().f().getAbsolutePath(), hVar.e().e().e().getAbsolutePath(), hVar.f(), Object.class.getClassLoader());
        this.b = hVar.f();
        this.a = hVar;
    }

    @Override // com.jingdong.aura.a.b.j.c
    public h a() {
        return this.a;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) {
        return super.findClass(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        com.jingdong.aura.core.util.l.b bVar = f12111c;
        bVar.a("findLibrary: " + str);
        String findLibrary = super.findLibrary(str);
        if (!TextUtils.isEmpty(findLibrary)) {
            File file = new File(findLibrary);
            if (file.exists()) {
                bVar.a("findLibrary result:" + file.getAbsolutePath());
                return findLibrary;
            }
        }
        File file2 = new File(this.b, System.mapLibraryName(str));
        if (file2.exists()) {
            bVar.a("findAgain result: " + file2.getAbsolutePath());
            return file2.getAbsolutePath();
        }
        try {
            String str2 = (String) com.jingdong.aura.core.reflection.b.r.a(com.jingdong.aura.a.b.k.b.i(), str);
            bVar.a("findLibrary from apk:" + str2);
            return str2;
        } catch (Exception e2) {
            f12111c.d("findLibrary: failed:");
            e2.printStackTrace();
            return null;
        }
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) {
        Class<?> cls;
        List<String> d;
        try {
            cls = super.loadClass(str, z);
        } catch (Exception unused) {
            cls = null;
        }
        if (cls != null) {
            return cls;
        }
        if (this.a != null && (d = com.jingdong.aura.a.a.a.c().d(this.a.b())) != null && d.size() > 0) {
            for (int i2 = 0; i2 < d.size(); i2++) {
                try {
                    h hVar = (h) com.jingdong.aura.a.b.k.b.b(d.get(i2));
                    if (hVar != null) {
                        try {
                            hVar.n();
                        } catch (l.b.a.b e2) {
                            e2.printStackTrace();
                            try {
                                e.a(this.a.b(), "BundleClassLoaderV2", str, "startBundle error! load class:" + str + "dependency bundle:" + d.get(i2), "BundleClassLoaderV2_loadClass", e2);
                            } catch (Throwable unused2) {
                            }
                        }
                        cls = hVar.g().loadClass(str);
                    }
                } catch (Exception unused3) {
                }
            }
        }
        if (cls != null) {
            return cls;
        }
        try {
            Class<?> loadClass = com.jingdong.aura.a.b.k.b.i().loadClass(str);
            if (loadClass != null) {
                return loadClass;
            }
            throw new ClassNotFoundException("Can't find class " + str + " in BundleClassLoaderV2: " + this);
        } catch (Exception e3) {
            throw new ClassNotFoundException("Can't find class " + str + " in BundleClassLoaderV2: " + this, e3);
        }
    }

    private a(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, str3, Object.class.getClassLoader());
    }
}
