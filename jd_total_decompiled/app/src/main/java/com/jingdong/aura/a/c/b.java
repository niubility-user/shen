package com.jingdong.aura.a.c;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.jingdong.aura.core.reflection.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class b extends c.a {
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("ActivityTaskManagerDelegate");

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

    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0160 A[EDGE_INSN: B:77:0x0160->B:67:0x0160 BREAK  A[LOOP:0: B:29:0x0081->B:66:0x015c], SYNTHETIC] */
    @Override // com.jingdong.aura.core.reflection.c.a, java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object invoke(Object obj, Method method, Object[] objArr) {
        int i2;
        if (method.getName().equals("startActivity")) {
            Instrumentation d = com.jingdong.aura.core.reflection.a.d();
            if (d != null && d.getClass().getName().contains("com.lbe.security.service")) {
                com.jingdong.aura.core.reflection.a.a((Instrumentation) new i(com.jingdong.aura.core.reflection.a.d(), l.a.getBaseContext()));
            }
            IBinder iBinder = null;
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
                    Intent intent = (Intent) obj3;
                    if (intent.getComponent() != null) {
                        String className = intent.getComponent().getClassName();
                        String packageName = intent.getComponent().getPackageName();
                        String b2 = com.jingdong.aura.a.a.a.c().b(className);
                        ArrayList<String> g2 = com.jingdong.aura.a.b.c.g(b2);
                        if (g2 != null && g2.size() > 0) {
                            String providedBundleNotFoundPageName = com.jingdong.aura.a.b.c.m().getProvidedBundleNotFoundPageName();
                            if (!TextUtils.isEmpty(providedBundleNotFoundPageName)) {
                                intent.putExtra("aura_target_classname", className);
                                intent.putExtra("aura_target_bundlename", b2);
                                intent.putStringArrayListExtra("aura_not_prepared_bundlename", g2);
                                intent.setComponent(new ComponentName(packageName, providedBundleNotFoundPageName));
                            }
                            if (com.jingdong.aura.a.b.c.P() && i3 >= 0) {
                                a(intent, i3, iBinder, objArr);
                            }
                        }
                        try {
                            l.f12146c.getActivityInfo(intent.getComponent(), 65536);
                        } catch (PackageManager.NameNotFoundException unused) {
                            if (!l.a.getApplicationInfo().processName.equals(l.f12147e)) {
                                objArr[i2] = com.jingdong.aura.core.shadow.c.c(intent);
                                return super.invoke(obj, method, objArr);
                            }
                            try {
                                d.c(className);
                                ActivityInfo activityInfo = l.a.getPackageManager().getActivityInfo(intent.getComponent(), 65536);
                                if (activityInfo != null) {
                                    String className2 = intent.getComponent().getClassName();
                                    String a = com.jingdong.aura.core.shadow.c.a(activityInfo);
                                    b.a(String.format("dispatchShadowActivity,[%s -> %s]", className2, a));
                                    objArr[i2] = com.jingdong.aura.core.shadow.c.a(intent, a);
                                    return super.invoke(obj, method, objArr);
                                }
                            } catch (PackageManager.NameNotFoundException e2) {
                                e2.printStackTrace();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                } else {
                    i2++;
                }
            }
        }
        return super.invoke(obj, method, objArr);
    }
}
