package com.jingdong.aura.a.c;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.File;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class c implements l.b.a.e {
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("BundleLifecycleHandler");

    /* loaded from: classes4.dex */
    private class b extends AsyncTask<l.b.a.d, Void, Void> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void doInBackground(l.b.a.d... dVarArr) {
            c.this.c(dVarArr[0]);
            return null;
        }

        private b() {
        }
    }

    private void a(l.b.a.d dVar) {
    }

    private void b(l.b.a.d dVar) {
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) dVar;
        if (g.a(dVar.b()) == null) {
            j b2 = j.b(hVar.e().b());
            a.a("Bundle installation info " + dVar.b() + ":" + b2.f12136e);
            g.a(dVar.b(), b2);
        }
        try {
            if (a(hVar.e().b())) {
                a.a("process resources");
                com.jingdong.aura.core.runing.resource.a.a(l.a, l.d, hVar.e().b().getAbsolutePath());
            } else {
                a.a("public bundle do not process resources");
            }
        } catch (Throwable th) {
            a.a("Could not load resource in bundle " + hVar.b(), th);
        }
        a.a("loaded() spend " + (System.currentTimeMillis() - currentTimeMillis) + " milliseconds");
        com.jingdong.aura.a.b.e.a("AuraMaiDianBundleLoaded", dVar.b(), hVar.l(), String.valueOf(System.currentTimeMillis() - currentTimeMillis), "BundleLifecycleHandler.loaded");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(l.b.a.d dVar) {
        com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) dVar;
        long currentTimeMillis = System.currentTimeMillis();
        j a2 = g.a(hVar.b());
        if (a2 != null) {
            String str = a2.a;
            if (com.jingdong.aura.core.util.h.b(str)) {
                try {
                    a(str, hVar.g()).onCreate();
                } catch (Throwable th) {
                    a.a("Error to start application >>>", th);
                }
            }
        }
        a.a("started() spend " + (System.currentTimeMillis() - currentTimeMillis) + " milliseconds");
    }

    private void d(l.b.a.d dVar) {
        Application application = g.a.get(dVar.b());
        if (application != null) {
            application.onTerminate();
            g.a.remove(dVar.b());
        }
    }

    private void e(l.b.a.d dVar) {
        g.c(dVar.b());
    }

    private void f(l.b.a.d dVar) {
    }

    @Override // l.b.a.e
    @SuppressLint({"NewApi"})
    public void bundleChanged(l.b.a.a aVar) {
        int type = aVar.getType();
        if (type == 0) {
            b(aVar.getBundle());
        } else if (type == 1) {
            a(aVar.getBundle());
        } else if (type != 2) {
            if (type == 4) {
                d(aVar.getBundle());
            } else if (type == 8) {
                f(aVar.getBundle());
            } else if (type != 16) {
            } else {
                e(aVar.getBundle());
            }
        } else if (a()) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            c(aVar.getBundle());
        } else if (com.jingdong.aura.a.b.k.b.k()) {
            b bVar = new b();
            if (Build.VERSION.SDK_INT > 11) {
                bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, aVar.getBundle());
            } else {
                bVar.execute(aVar.getBundle());
            }
        } else {
            c(aVar.getBundle());
        }
    }

    protected static Application a(String str, ClassLoader classLoader) {
        Class<?> loadClass = classLoader.loadClass(str);
        if (loadClass != null) {
            Application application = (Application) loadClass.newInstance();
            com.jingdong.aura.core.reflection.b.f12157k.a(application, l.a);
            return application;
        }
        throw new ClassNotFoundException(str);
    }

    private boolean a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        try {
            return com.jingdong.aura.core.util.h.b((String) Class.forName("android.os.SystemProperties").getDeclaredMethod(IMantoServerRequester.GET, String.class).invoke(null, "ro.lewa.version"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean a(File file) {
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    boolean z = zipFile2.getEntry("resources.arsc") != null;
                    com.jingdong.aura.core.util.d.a(zipFile2);
                    return z;
                } catch (Exception e2) {
                    e = e2;
                    zipFile = zipFile2;
                    a.b(e.getMessage(), e);
                    com.jingdong.aura.core.util.d.a(zipFile);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    com.jingdong.aura.core.util.d.a(zipFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
