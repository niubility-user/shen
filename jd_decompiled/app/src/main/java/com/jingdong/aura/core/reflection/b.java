package com.jingdong.aura.core.reflection;

import android.app.Application;
import android.app.Instrumentation;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.IBinder;
import android.view.ContextThemeWrapper;
import com.jingdong.aura.core.reflection.Hack;
import dalvik.system.DexClassLoader;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class b extends Hack.HackDeclaration implements Hack.a {
    public static Hack.d<ContextThemeWrapper, Resources> A;
    public static Hack.b<DexClassLoader> B;
    public static Hack.b<Object> C;
    public static Hack.b<Object> D;
    public static Hack.b<Object> E;
    public static Hack.d<Object, Application> F;
    public static Hack.d<Object, ClassLoader> G;
    public static Hack.d<Object, Resources> H;
    public static Hack.b<Resources> I;
    public static Hack.d<Resources, Object> J;
    public static Hack.b<Object> K;
    public static Hack.b<Object> L;
    public static Hack.b<Object> M;
    public static Hack.b<Object> N;
    public static Hack.d<Object, Object> O;
    public static Hack.d<Object, Object> P;
    public static Hack.d<Object, Object> Q;
    public static Hack.d<Object, Object> R;
    public static Hack.b<Object> S;
    public static Hack.b<Object> T;
    public static Hack.b<Object> U;
    public static Hack.b<Object> V;
    public static Hack.b<Object> W;
    public static Hack.d<Object, String> X;
    public static Hack.d<Object, ArrayList<Object>> Y;
    public static Hack.d<Object, ApplicationInfo> Z;
    public static Hack.e a0;
    public static Hack.c b0;

    /* renamed from: c */
    public static Hack.b<Object> f12150c;
    public static Hack.b<Object> c0;
    public static Hack.e d;
    public static Hack.c d0;

    /* renamed from: e */
    public static Hack.e f12151e;

    /* renamed from: f */
    public static Hack.d<Object, Application> f12152f;

    /* renamed from: g */
    public static Hack.d<Object, Instrumentation> f12153g;

    /* renamed from: h */
    public static Hack.d<Object, Map<String, Object>> f12154h;

    /* renamed from: i */
    public static Hack.d<Object, Object> f12155i;

    /* renamed from: j */
    public static Hack.b<Application> f12156j;

    /* renamed from: k */
    public static Hack.e f12157k;

    /* renamed from: l */
    public static Hack.b<AssetManager> f12158l;

    /* renamed from: m */
    public static Hack.e f12159m;

    /* renamed from: n */
    public static Hack.e f12160n;
    public static Hack.e o;
    public static Hack.e p;
    public static Hack.b<ClassLoader> q;
    public static Hack.e r;
    public static Hack.b<Object> s;
    public static Hack.d<Object, Resources> t;
    public static Hack.d<Object, Resources.Theme> u;
    public static Hack.d<Object, Integer> v;
    public static Hack.b<ContextWrapper> w;
    public static Hack.d<ContextWrapper, Context> x;
    public static Hack.b<ContextThemeWrapper> y;
    public static Hack.d<ContextThemeWrapper, Context> z;
    private AssertionArrayException a = null;
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("AuraHacks");
    public static boolean f0 = false;
    public static boolean g0 = false;
    public static boolean e0 = false;

    static {
        new ArrayList();
        new ArrayList();
    }

    public static void a() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 8) {
            E = Hack.a("android.app.ActivityThread$PackageInfo");
        } else {
            E = Hack.a("android.app.LoadedApk");
        }
        f12150c = Hack.a("android.app.ActivityThread");
        I = Hack.a(Resources.class);
        f12156j = Hack.a(Application.class);
        f12158l = Hack.a(AssetManager.class);
        C = Hack.a("android.content.pm.IPackageManager");
        Hack.a(Service.class);
        s = Hack.a("android.app.ContextImpl");
        w = Hack.a(ContextWrapper.class);
        y = Hack.a(ContextThemeWrapper.class);
        e0 = true;
        q = Hack.a(ClassLoader.class);
        B = Hack.a(DexClassLoader.class);
        D = Hack.a("dalvik.system.LexFile");
        e0 = false;
        K = Hack.a("android.app.ActivityManagerNative");
        L = Hack.a("android.app.ActivityManager");
        if (i2 >= 29) {
            try {
                M = Hack.a("android.app.ActivityTaskManager");
            } catch (Exception e2) {
                e2.printStackTrace();
                b.d("ActivityTaskManager hook failed");
            }
        }
        N = Hack.a("android.util.Singleton");
        S = Hack.a("android.content.pm.PackageParser");
        T = Hack.a("android.content.pm.PackageParser$Component");
        U = Hack.a("android.content.pm.PackageParser$Activity");
        V = Hack.a("android.content.pm.PackageParser$Package");
        W = Hack.a("android.content.pm.PackageParser$ActivityIntentInfo");
        c0 = Hack.a("android.app.ApplicationPackageManager");
    }

    public static void b() {
        if (Build.VERSION.SDK_INT <= 20) {
            b0 = S.a(String.class);
        } else {
            b0 = S.a(new Class[0]);
        }
        d0 = c0.a(s.a(), C.a());
    }

    public static void c() {
        f12153g = f12150c.a("mInstrumentation").b(Instrumentation.class);
        f12150c.a("mAllApplications").a(ArrayList.class);
        f12152f = f12150c.a("mInitialApplication").b(Application.class);
        f12154h = f12150c.a("mPackages").a(Map.class);
        f12155i = f12150c.b("sPackageManager").b(C.a());
        F = E.a("mApplication").b(Application.class);
        H = E.a("mResources").b(Resources.class);
        E.a("mResDir").b(String.class);
        G = E.a("mClassLoader").b(ClassLoader.class);
        E.a("mBaseClassLoader").b(ClassLoader.class);
        E.a("mAppDir").b(String.class);
        t = s.a("mResources").b(Resources.class);
        u = s.a("mTheme").b(Resources.Theme.class);
        v = s.a("mThemeResource").b(Integer.TYPE);
        e0 = true;
        z = y.a("mBase").b(Context.class);
        e0 = false;
        try {
            if (Build.VERSION.SDK_INT >= 17 && y.a().getDeclaredField("mResources") != null) {
                A = y.a("mResources").b(Resources.class);
            }
        } catch (NoSuchFieldException unused) {
            b.c("Not found ContextThemeWrapper.mResources on VERSION " + Build.VERSION.SDK_INT);
        }
        x = w.a("mBase").b(Context.class);
        J = I.a("mAssets");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 25 && (i2 != 25 || Build.VERSION.PREVIEW_SDK_INT <= 0)) {
            P = K.b("gDefault");
        } else {
            Q = L.b("IActivityManagerSingleton");
        }
        if (i2 >= 29) {
            try {
                R = M.b("IActivityTaskManagerSingleton");
            } catch (Exception unused2) {
                b.c("failed init IActivityTaskManagerSingleton");
            }
        }
        O = N.a("mInstance");
        X = V.a("packageName").a(String.class);
        Y = V.a("activities").a(ArrayList.class);
        Z = V.a("applicationInfo").b(ApplicationInfo.class);
        W.a("activity").b(U.a());
    }

    public static void d() {
        d = f12150c.a("currentActivityThread", new Class[0]);
        try {
            f12151e = f12150c.a("getActivity", IBinder.class);
        } catch (Throwable th) {
            b.d("hack getActivity method fail!" + th);
        }
        f12159m = f12158l.a("addAssetPath", String.class);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 23) {
            f12160n = f12158l.a("addAssetPathAsSharedLibrary", String.class);
        }
        f12157k = f12156j.a("attach", Context.class);
        r = q.a("findLibrary", String.class);
        Hack.b<Object> bVar = D;
        if (bVar != null && bVar.a() != null) {
            D.a("loadLex", String.class, Integer.TYPE);
            D.a("loadClass", String.class, ClassLoader.class);
            D.a("close", new Class[0]);
            B.a("findClass", String.class);
        }
        if (i2 > 20) {
            try {
                p = f12158l.a("getResourceIdentifier", String.class, String.class, String.class);
                o = f12158l.a("ensureStringBlocks", new Class[0]);
            } catch (Throwable unused) {
            }
        }
        a0 = T.a("getComponentName", new Class[0]);
    }

    public static boolean e() {
        if (g0) {
            return f0;
        }
        b bVar = new b();
        try {
            Hack.a(bVar);
            if (Build.VERSION.SDK_INT == 11) {
                bVar.a(new Hack.HackDeclaration.HackAssertionException("Hack Assertion Failed: Android OS Version 11"));
            }
            a();
            b();
            c();
            d();
            AssertionArrayException assertionArrayException = bVar.a;
            if (assertionArrayException == null) {
                f0 = true;
                return true;
            }
            f0 = false;
            throw assertionArrayException;
        } catch (Throwable th) {
            try {
                f0 = false;
                b.d(th.getMessage());
                Hack.a((Hack.a) null);
                g0 = true;
                return e0;
            } finally {
                Hack.a((Hack.a) null);
                g0 = true;
            }
        }
    }

    @Override // com.jingdong.aura.core.reflection.Hack.a
    public boolean a(Hack.HackDeclaration.HackAssertionException hackAssertionException) {
        if (e0) {
            return true;
        }
        if (this.a == null) {
            this.a = new AssertionArrayException("aura hack assert failed");
        }
        this.a.addException(hackAssertionException);
        return true;
    }
}
