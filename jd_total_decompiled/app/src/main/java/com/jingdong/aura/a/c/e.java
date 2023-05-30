package com.jingdong.aura.a.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

/* loaded from: classes.dex */
public class e extends ContextWrapper {
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("ContextImplHook");
    private ClassLoader a;

    public e(Context context, ClassLoader classLoader) {
        super(context);
        this.a = null;
        this.a = classLoader;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i2) {
        String str;
        ServiceInfo serviceInfo;
        String str2 = null;
        if (intent.getComponent() != null) {
            str2 = intent.getComponent().getPackageName();
            str = intent.getComponent().getClassName();
        } else {
            ResolveInfo resolveService = getBaseContext().getPackageManager().resolveService(intent, 0);
            if (resolveService == null || (serviceInfo = resolveService.serviceInfo) == null) {
                str = null;
            } else {
                str2 = serviceInfo.packageName;
                str = serviceInfo.name;
            }
        }
        if (!com.jingdong.aura.core.util.h.b(getBaseContext().getPackageName(), str2)) {
            return super.bindService(intent, serviceConnection, i2);
        }
        d.c(str);
        String b2 = g.b(str);
        if (b2 != null) {
            com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(b2);
            if (hVar != null) {
                try {
                    hVar.n();
                } catch (l.b.a.b e2) {
                    b.a(e2.getMessage() + " Caused by: ", e2.getNestedException());
                }
            }
            return super.bindService(intent, serviceConnection, i2);
        }
        try {
            if (com.jingdong.aura.a.b.k.b.i().loadClass(str) != null) {
                return super.bindService(intent, serviceConnection, i2);
            }
        } catch (ClassNotFoundException unused) {
            b.d("Can't find class " + str);
        }
        return false;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return l.d.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        ClassLoader classLoader = this.a;
        return classLoader != null ? classLoader : super.getClassLoader();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public PackageManager getPackageManager() {
        return getApplicationContext().getPackageManager();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return l.d;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ComponentName startService(Intent intent) {
        String str;
        String str2;
        ServiceInfo serviceInfo;
        if (intent.getComponent() != null) {
            str = intent.getComponent().getPackageName();
            str2 = intent.getComponent().getClassName();
        } else {
            ResolveInfo resolveService = getBaseContext().getPackageManager().resolveService(intent, 0);
            if (resolveService == null || (serviceInfo = resolveService.serviceInfo) == null) {
                str = null;
                str2 = null;
            } else {
                String str3 = serviceInfo.packageName;
                str2 = serviceInfo.name;
                str = str3;
            }
        }
        if (!com.jingdong.aura.core.util.h.b(getBaseContext().getPackageName(), str)) {
            return super.startService(intent);
        }
        d.c(str2);
        String b2 = g.b(str2);
        if (b2 != null) {
            com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(b2);
            if (hVar != null) {
                try {
                    hVar.n();
                } catch (l.b.a.b e2) {
                    b.a(e2.getMessage() + " Caused by: ", e2.getNestedException());
                }
            }
            return super.startService(intent);
        }
        try {
            if (com.jingdong.aura.a.b.k.b.i().loadClass(str2) != null) {
                return super.startService(intent);
            }
            return null;
        } catch (ClassNotFoundException unused) {
            b.d("Can't find class " + str2);
            return null;
        }
    }
}
