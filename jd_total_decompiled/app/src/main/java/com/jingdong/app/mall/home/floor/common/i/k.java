package com.jingdong.app.mall.home.floor.common.i;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.LineFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LineFloorEngine;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.ui.address.entity.PickUpSiteBean;
import com.jingdong.common.utils.PickUpSiteUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class k {
    private static ConcurrentHashMap<String, JDJSONObject> a = new ConcurrentHashMap<>();
    private static final b b = new b();

    /* renamed from: c  reason: collision with root package name */
    public static boolean f9316c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            k.b(k.b.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class b {
        private final List<com.jingdong.app.mall.home.r.e.d> a = new CopyOnWriteArrayList();
        private final List<com.jingdong.app.mall.home.r.e.d> b = new CopyOnWriteArrayList();

        b() {
        }

        void c() {
            this.a.clear();
            this.b.clear();
        }

        boolean d() {
            return this.a.size() > 0;
        }

        boolean e() {
            return this.b.size() > 0;
        }

        void f(List<com.jingdong.app.mall.home.r.e.d> list) {
            this.a.clear();
            this.a.addAll(list);
            this.b.clear();
        }

        void g(List<com.jingdong.app.mall.home.r.e.d> list) {
            List<com.jingdong.app.mall.home.r.e.d> list2 = this.b;
            if (list2 != list) {
                list2.clear();
                this.b.addAll(list);
            }
        }
    }

    public static void b(List<com.jingdong.app.mall.home.r.e.d> list) {
        JDHomeFragment z0;
        if (list == null || list.size() <= 0 || (z0 = JDHomeFragment.z0()) == null) {
            return;
        }
        HomeRecyclerAdapter t0 = z0.t0();
        if (t0.p()) {
            b.g(list);
            return;
        }
        b.f(list);
        if (c(t0.getDataList(), true) >= 0) {
            t0.A();
        }
        MallFloorEvent.i(true);
    }

    public static int c(List<com.jingdong.app.mall.home.r.e.d> list, boolean z) {
        if (!z) {
            b.c();
            return -1;
        } else if (b.d()) {
            Iterator<com.jingdong.app.mall.home.r.e.d> it = list.iterator();
            boolean z2 = false;
            int i2 = 0;
            while (it.hasNext()) {
                if (it.next().isAsyncFloor()) {
                    it.remove();
                    z2 = true;
                }
                if (!z2) {
                    i2++;
                }
            }
            if (z2) {
                list.addAll(i2, b.a);
                s.p(list, false);
                s.o(list);
            }
            if (z2) {
                return i2;
            }
            return -1;
        } else {
            return -1;
        }
    }

    public static boolean d() {
        return f9316c;
    }

    public static void e(boolean z) {
        if (z || !b.e()) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.F0(new a(), 100L);
    }

    public static void f() {
        a.clear();
    }

    public static void g() {
        f9316c = true;
    }

    public static JDJSONObject h(String str) {
        return a.get(str);
    }

    public static String i() {
        PickUpSiteBean pickUpSite = PickUpSiteUtil.getPickUpSite();
        return pickUpSite == null ? "" : String.valueOf(pickUpSite.getSiteId());
    }

    public static String j(com.jingdong.app.mall.home.o.a.i iVar, LineFloorEntity lineFloorEntity) {
        if (iVar == null || lineFloorEntity == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            com.jingdong.app.mall.home.o.a.f.y(jSONObject);
            com.jingdong.app.mall.home.o.a.f.e0(jSONObject, iVar);
            jSONObject.put("floorId", lineFloorEntity.getFloorId());
            jSONObject.put("fQueryStamp", com.jingdong.app.mall.home.b.f8602m + "");
            jSONObject.put("siteId", lineFloorEntity.getSiteId());
            jSONObject.put("userCategory", l.f());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static boolean k(String str) {
        return !i().equals(str);
    }

    public static void l() {
        f9316c = false;
    }

    public static void m(com.jingdong.app.mall.home.o.a.i iVar, LineFloorEntity lineFloorEntity, boolean z) {
    }

    public static void n(String str, JDJSONObject jDJSONObject) {
        a.put(str, jDJSONObject);
    }

    public static void o() {
        LineFloorEngine.r();
    }
}
