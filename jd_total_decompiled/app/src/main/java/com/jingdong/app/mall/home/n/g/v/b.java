package com.jingdong.app.mall.home.n.g.v;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class b {
    public static String a = "Category_Main";
    private static Map<String, c> b = new ConcurrentHashMap();

    public static void a(c cVar) {
        if (cVar == null) {
            return;
        }
        String g2 = cVar.g();
        if (TextUtils.isEmpty(g2)) {
            return;
        }
        c cVar2 = b.get(g2);
        if (cVar2 == null) {
            b.put(g2, cVar);
        } else {
            cVar2.b(cVar.n());
        }
    }

    public static void b(CategoryEntity.CaItem caItem) {
        String str = caItem.isTopTab() ? "" : a;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDMtaUtils.sendPagePv(JdSdk.getInstance().getApplicationContext(), "", caItem.getPvParams(), str, "");
    }

    public static void c(String str, String str2) {
        f.r0("CaEventUtil", "CEventClick    " + str + ">>>>>>" + str2);
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "", a, "", "", "", str2, null);
    }

    public static void d() {
        if (b.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<String, c>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().E();
        }
        b.clear();
    }

    public static void e(String str) {
        c cVar = b.get(str);
        if (cVar != null) {
            cVar.F(true);
        }
    }

    public static void f(c cVar) {
        if (cVar == null) {
            return;
        }
        String g2 = cVar.g();
        String i2 = cVar.i();
        f.r0("CEventUtil", cVar.f() + "\u2014\u2014\u66dd\u5149: " + g2 + ">>>>>>" + i2);
        g(g2, i2);
        String l2 = cVar.l();
        if (TextUtils.isEmpty(l2)) {
            return;
        }
        String m2 = cVar.m();
        f.r0("CEventUtil", cVar.f() + "\u2014\u2014\u66dd\u5149: " + g2 + ">>>>>>" + i2);
        g(l2, m2);
    }

    public static void g(String str, String str2) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", a, "", "", str2, null);
    }
}
