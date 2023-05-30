package com.xiaomi.push;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.xiaomi.push.service.i0;
import java.util.HashMap;

/* loaded from: classes11.dex */
class d5 {
    public static void a(i0.b bVar, String str, o5 o5Var) {
        String b;
        d3 d3Var = new d3();
        if (!TextUtils.isEmpty(bVar.f19096c)) {
            d3Var.k(bVar.f19096c);
        }
        if (!TextUtils.isEmpty(bVar.f19098f)) {
            d3Var.t(bVar.f19098f);
        }
        if (!TextUtils.isEmpty(bVar.f19099g)) {
            d3Var.w(bVar.f19099g);
        }
        d3Var.n(bVar.f19097e ? "1" : "0");
        if (TextUtils.isEmpty(bVar.d)) {
            d3Var.q("XIAOMI-SASL");
        } else {
            d3Var.q(bVar.d);
        }
        e5 e5Var = new e5();
        e5Var.B(bVar.b);
        e5Var.h(Integer.parseInt(bVar.f19100h));
        e5Var.v(bVar.a);
        e5Var.l("BIND", null);
        e5Var.k(e5Var.D());
        g.j.a.a.a.c.o("[Slim]: bind id=" + e5Var.D());
        HashMap hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put("token", bVar.f19096c);
        hashMap.put("chid", bVar.f19100h);
        hashMap.put("from", bVar.b);
        hashMap.put("id", e5Var.D());
        hashMap.put(RemoteMessageConst.TO, "xiaomi.com");
        if (bVar.f19097e) {
            hashMap.put("kick", "1");
        } else {
            hashMap.put("kick", "0");
        }
        if (TextUtils.isEmpty(bVar.f19098f)) {
            hashMap.put("client_attrs", "");
        } else {
            hashMap.put("client_attrs", bVar.f19098f);
        }
        if (TextUtils.isEmpty(bVar.f19099g)) {
            hashMap.put("cloud_attrs", "");
        } else {
            hashMap.put("cloud_attrs", bVar.f19099g);
        }
        if (bVar.d.equals("XIAOMI-PASS") || bVar.d.equals("XMPUSH-PASS")) {
            b = n0.b(bVar.d, null, hashMap, bVar.f19101i);
        } else {
            bVar.d.equals("XIAOMI-SASL");
            b = null;
        }
        d3Var.z(b);
        e5Var.n(d3Var.h(), null);
        o5Var.w(e5Var);
    }

    public static void b(String str, String str2, o5 o5Var) {
        e5 e5Var = new e5();
        e5Var.B(str2);
        e5Var.h(Integer.parseInt(str));
        e5Var.l("UBND", null);
        o5Var.w(e5Var);
    }
}
