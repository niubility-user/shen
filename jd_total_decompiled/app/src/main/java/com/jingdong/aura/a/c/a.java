package com.jingdong.aura.a.c;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.jingdong.aura.core.reflection.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class a extends c.a {
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("ActivityManagerDelegate");

    private void a(Intent intent, int i2, IBinder iBinder, Object[] objArr) {
        com.jingdong.aura.core.util.l.b bVar = b;
        bVar.a("wrapperIntent for activity result! targetIntent:" + intent + "requestCode:" + i2 + " token:" + iBinder);
        try {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 18 && iBinder != null) {
                bundle.putBinder("mToken", iBinder);
                if (intent != null) {
                    intent.putExtras(bundle);
                    intent.putExtra("requestCode", i2);
                }
                for (int i3 = 0; i3 < objArr.length; i3++) {
                    Object obj = objArr[i3];
                    if (obj instanceof Integer) {
                        b.a("find requestCode:" + obj + " change to:-1");
                        objArr[i3] = -1;
                        return;
                    }
                }
                return;
            }
            bVar.a("wrapperIntent sdk int < 18");
        } catch (Throwable th) {
            b.d("wrapperIntent fail!!\uff01" + th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x02c0 A[EDGE_INSN: B:155:0x02c0->B:135:0x02c0 BREAK  A[LOOP:0: B:97:0x01e2->B:134:0x02bc], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0105 A[Catch: all -> 0x02c0, TryCatch #1 {all -> 0x02c0, blocks: (B:4:0x0017, B:6:0x0021, B:14:0x004a, B:16:0x0056, B:18:0x005b, B:20:0x0064, B:22:0x006c, B:26:0x008d, B:25:0x0071, B:28:0x0092, B:30:0x009c, B:33:0x00a2, B:7:0x0032, B:9:0x003e, B:12:0x0043, B:37:0x00c3, B:39:0x00cd, B:49:0x00f9, B:51:0x0105, B:53:0x010a, B:55:0x0113, B:57:0x011b, B:61:0x013c, B:60:0x0120, B:63:0x0141, B:65:0x014b, B:68:0x0164, B:67:0x0150, B:41:0x00df, B:43:0x00eb, B:46:0x00f0), top: B:138:0x0015, inners: #3, #4, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x010a A[Catch: all -> 0x02c0, TryCatch #1 {all -> 0x02c0, blocks: (B:4:0x0017, B:6:0x0021, B:14:0x004a, B:16:0x0056, B:18:0x005b, B:20:0x0064, B:22:0x006c, B:26:0x008d, B:25:0x0071, B:28:0x0092, B:30:0x009c, B:33:0x00a2, B:7:0x0032, B:9:0x003e, B:12:0x0043, B:37:0x00c3, B:39:0x00cd, B:49:0x00f9, B:51:0x0105, B:53:0x010a, B:55:0x0113, B:57:0x011b, B:61:0x013c, B:60:0x0120, B:63:0x0141, B:65:0x014b, B:68:0x0164, B:67:0x0150, B:41:0x00df, B:43:0x00eb, B:46:0x00f0), top: B:138:0x0015, inners: #3, #4, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01e5  */
    @Override // com.jingdong.aura.core.reflection.c.a, java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object invoke(Object obj, Method method, Object[] objArr) {
        int i2;
        String str;
        ServiceInfo serviceInfo;
        String str2;
        String str3;
        String str4;
        String str5;
        ServiceInfo serviceInfo2;
        String name = method.getName();
        IBinder iBinder = null;
        String str6 = null;
        iBinder = null;
        iBinder = null;
        if (method.getName().equals("startService")) {
            Intent intent = (Intent) objArr[1];
            if (intent.getComponent() != null) {
                str5 = intent.getComponent().getPackageName();
                str4 = intent.getComponent().getClassName();
            } else {
                ResolveInfo resolveService = l.a.getPackageManager().resolveService(intent, 0);
                if (resolveService != null && (serviceInfo2 = resolveService.serviceInfo) != null) {
                    str5 = serviceInfo2.packageName;
                    str4 = serviceInfo2.name;
                }
                str4 = null;
                str5 = null;
            }
            if (!com.jingdong.aura.core.util.h.b(l.a.getPackageName(), str5)) {
                return super.invoke(obj, method, objArr);
            }
            d.c(str4);
            String b2 = g.b(str4);
            if (b2 != null) {
                com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(b2);
                if (hVar != null) {
                    try {
                        hVar.n();
                    } catch (l.b.a.b e2) {
                        b.a(e2.getMessage() + " Caused by: ", e2.getNestedException());
                    }
                }
                return super.invoke(obj, method, objArr);
            }
            try {
                if (com.jingdong.aura.a.b.k.b.i().loadClass(str4) != null) {
                    return super.invoke(obj, method, objArr);
                }
                return null;
            } catch (ClassNotFoundException unused) {
                b.d("Can't find class " + str4);
                return null;
            }
        } else if (method.getName().equals("bindService")) {
            Intent intent2 = (Intent) objArr[1];
            if (intent2.getComponent() != null) {
                str2 = intent2.getComponent().getPackageName();
                str3 = intent2.getComponent().getClassName();
            } else {
                ResolveInfo resolveService2 = l.a.getPackageManager().resolveService(intent2, 0);
                if (resolveService2 != null && (serviceInfo = resolveService2.serviceInfo) != null) {
                    str2 = serviceInfo.packageName;
                    str3 = serviceInfo.name;
                }
                str = null;
                if (com.jingdong.aura.core.util.h.b(l.a.getPackageName(), str6)) {
                    return super.invoke(obj, method, objArr);
                }
                d.c(str);
                String b3 = g.b(str);
                if (b3 != null) {
                    com.jingdong.aura.a.b.h hVar2 = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(b3);
                    if (hVar2 != null) {
                        try {
                            hVar2.n();
                        } catch (l.b.a.b e3) {
                            b.a(e3.getMessage() + " Caused by: ", e3.getNestedException());
                        }
                    }
                    return super.invoke(obj, method, objArr);
                }
                try {
                    if (com.jingdong.aura.a.b.k.b.i().loadClass(str) != null) {
                        return super.invoke(obj, method, objArr);
                    }
                } catch (ClassNotFoundException unused2) {
                    b.d("Can't find class " + str);
                }
                return Boolean.FALSE;
            }
            str = str3;
            str6 = str2;
            if (com.jingdong.aura.core.util.h.b(l.a.getPackageName(), str6)) {
            }
        } else {
            if (name.equals("startActivity")) {
                Instrumentation d = com.jingdong.aura.core.reflection.a.d();
                if (d != null && d.getClass().getName().contains("com.lbe.security.service")) {
                    com.jingdong.aura.core.reflection.a.a((Instrumentation) new i(com.jingdong.aura.core.reflection.a.d(), l.a.getBaseContext()));
                }
                int i3 = -1;
                if (com.jingdong.aura.a.b.c.P()) {
                    try {
                        b.a(Arrays.toString(objArr));
                        int i4 = -1;
                        for (Object obj2 : objArr) {
                            try {
                                if (obj2 instanceof IBinder) {
                                    iBinder = (IBinder) obj2;
                                } else if ((obj2 instanceof Integer) && i4 == -1) {
                                    i4 = ((Integer) obj2).intValue();
                                }
                            } catch (Throwable th) {
                                th = th;
                                i3 = i4;
                                b.d("each startActivity args fail!!!:" + th);
                                i2 = 0;
                                while (true) {
                                    if (i2 < objArr.length) {
                                    }
                                    i2++;
                                }
                                return super.invoke(obj, method, objArr);
                            }
                        }
                        i3 = i4;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                i2 = 0;
                while (true) {
                    if (i2 < objArr.length) {
                        break;
                    }
                    Object obj3 = objArr[i2];
                    if (obj3 instanceof Intent) {
                        Intent intent3 = (Intent) obj3;
                        if (intent3.getComponent() != null) {
                            String className = intent3.getComponent().getClassName();
                            String packageName = intent3.getComponent().getPackageName();
                            String b4 = com.jingdong.aura.a.a.a.c().b(className);
                            ArrayList<String> g2 = com.jingdong.aura.a.b.c.g(b4);
                            if (g2 != null && g2.size() > 0) {
                                String providedBundleNotFoundPageName = com.jingdong.aura.a.b.c.m().getProvidedBundleNotFoundPageName();
                                if (!TextUtils.isEmpty(providedBundleNotFoundPageName)) {
                                    intent3.putExtra("aura_target_classname", className);
                                    intent3.putExtra("aura_target_bundlename", b4);
                                    intent3.putStringArrayListExtra("aura_not_prepared_bundlename", g2);
                                    intent3.setComponent(new ComponentName(packageName, providedBundleNotFoundPageName));
                                }
                                if (com.jingdong.aura.a.b.c.d() && i3 >= 0) {
                                    a(intent3, i3, iBinder, objArr);
                                }
                            }
                            try {
                                l.f12146c.getActivityInfo(intent3.getComponent(), 65536);
                            } catch (PackageManager.NameNotFoundException unused3) {
                                if (!l.a.getApplicationInfo().processName.equals(l.f12147e)) {
                                    objArr[i2] = com.jingdong.aura.core.shadow.c.c(intent3);
                                    return super.invoke(obj, method, objArr);
                                }
                                try {
                                    d.c(className);
                                    ActivityInfo activityInfo = l.a.getPackageManager().getActivityInfo(intent3.getComponent(), 65536);
                                    if (activityInfo != null) {
                                        String className2 = intent3.getComponent().getClassName();
                                        String a = com.jingdong.aura.core.shadow.c.a(activityInfo);
                                        b.a(String.format("dispatchShadowActivity,[%s -> %s]", className2, a));
                                        objArr[i2] = com.jingdong.aura.core.shadow.c.a(intent3, a);
                                        return super.invoke(obj, method, objArr);
                                    }
                                } catch (PackageManager.NameNotFoundException e4) {
                                    e4.printStackTrace();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                    } else {
                        i2++;
                    }
                }
            }
            return super.invoke(obj, method, objArr);
        }
        return super.invoke(obj, method, objArr);
    }
}
