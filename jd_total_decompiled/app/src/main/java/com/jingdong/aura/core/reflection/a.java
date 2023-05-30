package com.jingdong.aura.core.reflection;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.databinding.DataBinderMapperProxy;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.a.c.f;
import com.jingdong.aura.a.c.k;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.reflection.Hack;
import com.jingdong.aura.core.reflection.c;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class a {
    private static Object b;
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("AndroidHack");

    /* renamed from: c */
    private static Object f12149c = null;

    /* renamed from: com.jingdong.aura.core.reflection.a$a */
    /* loaded from: classes4.dex */
    public static class RunnableC0395a implements Runnable {
        RunnableC0395a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Object unused = a.f12149c = com.jingdong.aura.core.reflection.b.d.a(com.jingdong.aura.core.reflection.b.f12150c.a(), new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            synchronized (com.jingdong.aura.core.reflection.b.d) {
                com.jingdong.aura.core.reflection.b.d.notify();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static final class b implements Handler.Callback {
        final Object a;
        final Handler b;

        b(Handler handler, Object obj) {
            this.b = handler;
            this.a = obj;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            try {
                a.b();
                a.b(message);
                com.jingdong.aura.wrapper.mhCallback.a.a().a(message);
                this.b.handleMessage(message);
                a.b();
                return true;
            } finally {
            }
        }
    }

    public static void b() {
        Object c2 = c();
        if (c2 != null) {
            Application application = l.a;
            Object a2 = a(application, c2, application.getPackageName());
            if (a2 == null && (a2 = a(l.a, c2)) == null) {
                throw new RuntimeException("can't create loadedApk");
            }
            if (com.jingdong.aura.core.reflection.b.G.a((Hack.d<Object, ClassLoader>) a2) instanceof f) {
                return;
            }
            com.jingdong.aura.core.reflection.b.G.a((Hack.d<Object, ClassLoader>) a2, l.b);
            com.jingdong.aura.core.reflection.b.H.a((Hack.d<Object, Resources>) a2, l.d);
            return;
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static Object c() {
        if (f12149c == null) {
            if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                f12149c = com.jingdong.aura.core.reflection.b.d.a(null, new Object[0]);
            } else {
                Handler handler = new Handler(Looper.getMainLooper());
                synchronized (com.jingdong.aura.core.reflection.b.d) {
                    handler.post(new RunnableC0395a());
                    com.jingdong.aura.core.reflection.b.d.wait();
                }
            }
        }
        return f12149c;
    }

    public static Instrumentation d() {
        Object c2 = c();
        if (c2 != null) {
            return com.jingdong.aura.core.reflection.b.f12153g.a((Hack.d<Object, Instrumentation>) c2);
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static DataBinderMapperProxy e() {
        try {
            Class<?> a2 = a();
            if (a2 == null) {
                a.d("create DataBindingUtil class fail");
                return null;
            }
            Hack.d b2 = new Hack.b(a2).b("sMapper");
            DataBinderMapperProxy dataBinderMapperProxy = new DataBinderMapperProxy(b2.a((Hack.d) null));
            b2.a((Hack.d) null, dataBinderMapperProxy);
            return dataBinderMapperProxy;
        } catch (Throwable unused) {
            a.d("hackDataBindingUtil: fail ");
            return null;
        }
    }

    public static Handler f() {
        Object c2 = c();
        if (c2 != null) {
            try {
                Handler handler = (Handler) com.jingdong.aura.core.reflection.b.f12150c.a("mH").b(Hack.a("android.app.ActivityThread$H").a()).a((Hack.d<Object, T2>) c2);
                Field declaredField = Handler.class.getDeclaredField("mCallback");
                declaredField.setAccessible(true);
                declaredField.set(handler, new b(handler, c2));
                return null;
            } catch (Hack.HackDeclaration.HackAssertionException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static void g() {
        Object a2 = com.jingdong.aura.core.reflection.b.f12155i.a((Hack.d<Object, Object>) null);
        l.f12146c = (PackageManager) com.jingdong.aura.core.reflection.b.d0.a(l.a.getBaseContext(), a2);
        com.jingdong.aura.core.reflection.b.f12155i.a((Hack.d<Object, Object>) null, (c.a<?>) new k(l.a.getBaseContext()));
        try {
            PackageManager packageManager = l.a.getPackageManager();
            Field declaredField = Class.forName("android.app.ApplicationPackageManager").getDeclaredField("mPM");
            declaredField.setAccessible(true);
            declaredField.set(packageManager, com.jingdong.aura.core.reflection.b.f12155i.a((Hack.d<Object, Object>) null));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static Object a(Application application, Object obj, String str) {
        WeakReference weakReference = (WeakReference) com.jingdong.aura.core.reflection.b.f12154h.a((Hack.d<Object, Map<String, Object>>) obj).get(str);
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        b = weakReference.get();
        return weakReference.get();
    }

    public static Object a(Application application, Object obj) {
        Method declaredMethod;
        try {
            ApplicationInfo applicationInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), R2.attr.jdpay_width);
            application.getPackageManager();
            Resources resources = application.getResources();
            if (resources instanceof com.jingdong.aura.core.runing.resource.b) {
                declaredMethod = resources.getClass().getSuperclass().getSuperclass().getDeclaredMethod("getCompatibilityInfo", new Class[0]);
            } else if (resources.getClass().getName().equals("android.content.res.MiuiResources")) {
                declaredMethod = resources.getClass().getSuperclass().getDeclaredMethod("getCompatibilityInfo", new Class[0]);
            } else {
                declaredMethod = resources.getClass().getDeclaredMethod("getCompatibilityInfo", new Class[0]);
            }
            declaredMethod.setAccessible(true);
            Class<?> cls = Class.forName("android.content.res.CompatibilityInfo");
            Object invoke = declaredMethod.invoke(application.getResources(), new Object[0]);
            Method declaredMethod2 = com.jingdong.aura.core.reflection.b.f12150c.a().getDeclaredMethod("getPackageInfoNoCheck", ApplicationInfo.class, cls);
            declaredMethod2.setAccessible(true);
            Object invoke2 = declaredMethod2.invoke(obj, applicationInfo, invoke);
            b = invoke2;
            return invoke2;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new RuntimeException(th);
        }
    }

    private static void b(Application application, Resources resources) {
        Context baseContext = application.getBaseContext();
        Resources.Theme theme = baseContext.getTheme();
        com.jingdong.aura.core.util.l.b bVar = a;
        bVar.a("updateContextImplThemeField: oldTheme=" + theme);
        Resources.Theme newTheme = resources.newTheme();
        try {
            int intValue = com.jingdong.aura.core.reflection.b.v.a((Hack.d<Object, Integer>) baseContext).intValue();
            bVar.a("updateContextImplThemeField: mThemeResource=" + intValue);
            newTheme.applyStyle(intValue, true);
        } catch (Throwable th) {
            a.d("updateContextImplThemeField: error:" + th);
        }
        com.jingdong.aura.core.reflection.b.u.a((Hack.d<Object, Resources.Theme>) baseContext, newTheme);
    }

    public static void b(Message message) {
        Intent b2;
        try {
            if (message.what == 100) {
                Object obj = message.obj;
                Field declaredField = Class.forName("android.app.ActivityThread$ActivityClientRecord").getDeclaredField(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK);
                declaredField.setAccessible(true);
                Intent b3 = com.jingdong.aura.core.shadow.c.b((Intent) declaredField.get(obj));
                if (b3 != null) {
                    declaredField.set(obj, b3);
                }
            }
            if (message.what != 159 || Build.VERSION.SDK_INT < 28) {
                return;
            }
            Object obj2 = message.obj;
            Field declaredField2 = Class.forName("android.app.servertransaction.ClientTransaction").getDeclaredField("mActivityCallbacks");
            declaredField2.setAccessible(true);
            Class<?> cls = Class.forName("android.app.servertransaction.LaunchActivityItem");
            Field declaredField3 = cls.getDeclaredField("mIntent");
            declaredField3.setAccessible(true);
            for (Object obj3 : (List) declaredField2.get(obj2)) {
                if (cls.isInstance(obj3) && (b2 = com.jingdong.aura.core.shadow.c.b((Intent) declaredField3.get(obj3))) != null) {
                    declaredField3.set(obj3, b2);
                }
            }
        } catch (Throwable th) {
            e.a("hackLaunchActivityMessage", "hackLaunchActivityMessage failed, message.what: " + message.what, "hackLaunchActivityMessage", th);
        }
    }

    public static void a(String str, ClassLoader classLoader) {
        Object c2 = c();
        if (c2 != null) {
            Object a2 = a(l.a, c2, str);
            if (a2 == null) {
                a2 = a(l.a, c2);
            }
            if (a2 != null) {
                com.jingdong.aura.core.reflection.b.G.a((Hack.d<Object, ClassLoader>) a2, classLoader);
                return;
            }
            throw new Exception("Failed to get ActivityThread.mLoadedApk");
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static void a(String str, Application application) {
        Object c2 = c();
        if (c2 != null) {
            Object a2 = a(application, c2, application.getPackageName());
            if (a2 != null) {
                com.jingdong.aura.core.reflection.b.F.a((Hack.d<Object, Application>) a2, application);
                com.jingdong.aura.core.reflection.b.f12152f.a((Hack.d<Object, Application>) c2, application);
                return;
            }
            throw new Exception("Failed to get ActivityThread.mLoadedApk");
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static void a(Application application, Resources resources) {
        Object c2 = c();
        if (c2 != null) {
            Object a2 = a(application, c2, application.getPackageName());
            if (a2 == null) {
                a2 = a(application, c2);
                if (a2 != null) {
                    if (!(com.jingdong.aura.core.reflection.b.G.a((Hack.d<Object, ClassLoader>) a2) instanceof f)) {
                        com.jingdong.aura.core.reflection.b.G.a((Hack.d<Object, ClassLoader>) a2, l.b);
                    }
                } else {
                    throw new RuntimeException("Failed to get ActivityThread.mLoadedApk");
                }
            }
            com.jingdong.aura.core.reflection.b.H.a((Hack.d<Object, Resources>) a2, resources);
            com.jingdong.aura.core.reflection.b.t.a((Hack.d<Object, Resources>) application.getBaseContext(), resources);
            b(application, resources);
            return;
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    public static void a(Instrumentation instrumentation) {
        Object c2 = c();
        if (c2 != null) {
            com.jingdong.aura.core.reflection.b.f12153g.a((Hack.d<Object, Instrumentation>) c2, instrumentation);
            return;
        }
        throw new Exception("Failed to get ActivityThread.sCurrentActivityThread");
    }

    private static Class<?> a() {
        try {
            Class<?> cls = Class.forName("androidx.databinding.DataBindingUtil");
            a.a("createClass: find androidx.databinding.DataBindingUtil");
            return cls;
        } catch (ClassNotFoundException unused) {
            com.jingdong.aura.core.util.l.b bVar = a;
            bVar.a("createClass: not found androidx.databinding.DataBindingUtil");
            try {
                Class<?> cls2 = Class.forName("androidx.databinding.DataBindingUtil");
                bVar.a("createClass: find android.databinding.DataBindingUtil");
                return cls2;
            } catch (ClassNotFoundException unused2) {
                a.a("createClass: not found android.databinding.DataBindingUtil");
                return null;
            }
        }
    }
}
