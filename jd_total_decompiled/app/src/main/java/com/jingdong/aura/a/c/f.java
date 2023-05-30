package com.jingdong.aura.a.c;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.core.util.FakeProvider;
import com.jingdong.aura.core.util.FakeReceiver;
import com.jingdong.aura.core.util.FakeService;
import com.jingdong.common.utils.LangUtils;
import dalvik.system.PathClassLoader;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class f extends PathClassLoader {
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("DelegateClassLoader");

    public f(ClassLoader classLoader) {
        super(OrderISVUtil.MONEY_DECIMAL, classLoader);
    }

    private String a() {
        StringBuilder sb = new StringBuilder("installed bundles: ");
        List<l.b.a.d> c2 = com.jingdong.aura.a.b.k.b.c();
        if (c2 != null && !c2.isEmpty()) {
            Iterator<l.b.a.d> it = com.jingdong.aura.a.b.k.b.c().iterator();
            while (it.hasNext()) {
                sb.append(it.next().b());
                sb.append(":");
            }
        }
        return sb.toString();
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) {
        com.jingdong.aura.core.util.l.b bVar = a;
        bVar.a("will find class name = " + str);
        d.c(str);
        Class<?> h2 = d.h(str);
        if (h2 != null) {
            return h2;
        }
        ClassNotFoundException classNotFoundException = new ClassNotFoundException("Can't find class " + str + ". " + a() + LangUtils.SINGLE_SPACE + d.e(str));
        if (com.jingdong.aura.core.util.i.c(str)) {
            com.jingdong.aura.a.b.e.a("ClassNotFound", "findClass receiver failed " + str, "DelegateClassLoader.findClass", classNotFoundException);
            new FakeReceiver();
            return FakeReceiver.class;
        } else if (com.jingdong.aura.core.util.i.d(str)) {
            com.jingdong.aura.a.b.e.a("ClassNotFound", "findClass service failed " + str, "DelegateClassLoader.findClass", classNotFoundException);
            new FakeService();
            return FakeService.class;
        } else if (com.jingdong.aura.core.util.i.b(str)) {
            com.jingdong.aura.a.b.e.a("ClassNotFound", "findClass provider failed " + str, "DelegateClassLoader.findClass", classNotFoundException);
            new FakeProvider();
            return FakeProvider.class;
        } else {
            bVar.a("Can't find class " + str);
            throw classNotFoundException;
        }
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str) {
        a.e("will loadClass class name = " + str);
        return super.loadClass(str);
    }
}
