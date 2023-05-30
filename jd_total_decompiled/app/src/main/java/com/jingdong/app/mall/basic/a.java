package com.jingdong.app.mall.basic;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes19.dex */
public class a {
    private static final b a = b.c();
    private static final MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();

    /* renamed from: c */
    private static FragmentManager f8004c;

    public static void a() {
        try {
            b bVar = a;
            bVar.a();
            b.getNavigationFragment().j();
            if (Log.D) {
                Log.d("ApplicationManager", "clearBackStack() -->>> backStackManager.size() -->> " + bVar.f() + " manager.getBackStackEntryCount() = " + f8004c.getBackStackEntryCount());
            }
            if (f8004c.getBackStackEntryCount() > 0) {
                f8004c.popBackStackImmediate((String) null, 1);
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        if (Log.D) {
            Log.d("ApplicationManager", "clearBackStack() -->>> manager.getBackStackEntryCount() = " + f8004c.getBackStackEntryCount());
        }
    }

    private static void b(JDTaskModule jDTaskModule, String str) {
        if (jDTaskModule == null) {
            return;
        }
        try {
            JDTaskModule f2 = jDTaskModule.f();
            StringBuffer stringBuffer = new StringBuffer();
            if (f2 != null) {
                stringBuffer.append("\uff1bprev\uff1a" + jDTaskModule.f().getClass().getSimpleName());
            } else {
                stringBuffer.append("\uff1bprev\uff1anull");
            }
            stringBuffer.append("\uff1bgoOrBack\uff1a" + str);
            stringBuffer.append("\uff1bintent content:");
            Bundle c2 = jDTaskModule.c();
            if (c2 != null) {
                for (String str2 : c2.keySet()) {
                    if (!JumpUtil.VAULE_DES_PRODUCT_LIST.equals(str2)) {
                        stringBuffer.append(str2 + "\uff1a");
                        stringBuffer.append((c2.get(str2) == null ? "<null>" : c2.get(str2).toString()) + "\uff0c");
                    }
                }
            }
            if (Log.D) {
                Log.i("ApplicationManager", stringBuffer.toString());
            }
            com.jingdong.jdsdk.b.a.d(stringBuffer.toString(), jDTaskModule.getClass().getSimpleName());
        } catch (Throwable th) {
            if (Log.D) {
                th.printStackTrace();
            }
        }
    }

    public static void c(JDTaskModule jDTaskModule) {
        if (Log.D) {
            Log.d("ApplicationManager", "go() -->> taskModule:" + jDTaskModule);
        }
        b bVar = a;
        jDTaskModule.l(bVar.b());
        if (jDTaskModule.h()) {
            bVar.e(jDTaskModule);
            if (jDTaskModule.a) {
                if (Log.D) {
                    Log.d("ApplicationManager", "go() -->> taskModule.isNeedClearBackStack:true");
                }
                jDTaskModule.l(null);
                a();
            }
            jDTaskModule.g();
            jDTaskModule.m();
            b(jDTaskModule, "go");
        }
    }

    public static void d(FragmentManager fragmentManager) {
        f8004c = fragmentManager;
    }
}
