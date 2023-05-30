package com.jingdong.app.mall.home.r.c;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: k */
    public static final String f10642k = JDHomeFragment.class.getSimpleName();
    private final ConcurrentHashMap<String, c> a;
    private final ConcurrentHashMap<String, c> b;

    /* renamed from: c */
    private final ConcurrentHashMap<String, Boolean> f10643c;
    private final ConcurrentHashMap<com.jingdong.app.mall.home.r.c.b, Boolean> d;

    /* renamed from: e */
    private final ConcurrentLinkedQueue<Set<String>> f10644e;

    /* renamed from: f */
    private int f10645f;

    /* renamed from: g */
    private String f10646g;

    /* renamed from: h */
    private int f10647h;

    /* renamed from: i */
    private int f10648i;

    /* renamed from: j */
    private int f10649j;

    /* renamed from: com.jingdong.app.mall.home.r.c.a$a */
    /* loaded from: classes4.dex */
    public class C0319a extends com.jingdong.app.mall.home.o.a.b {
        C0319a() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            try {
                Context applicationContext = JdSdk.getInstance().getApplication().getApplicationContext();
                if (applicationContext != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Set<String> set = (Set) a.this.f10644e.poll();
                    if (set != null && !set.isEmpty()) {
                        boolean z = true;
                        for (String str : set) {
                            if (!z) {
                                stringBuffer.append("&&");
                            }
                            stringBuffer.append(str);
                            if (z) {
                                z = false;
                            }
                        }
                        a.w(applicationContext, "Home_FloorIDExpo", stringBuffer.toString(), l.a, RecommendMtaUtils.Home_PageId);
                    }
                }
            } catch (Exception e2) {
                f.s0(this, e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class b {
        static final a a = new a(null);
    }

    /* synthetic */ a(C0319a c0319a) {
        this();
    }

    public static void A(String str, String str2, String str3, String str4, String str5) {
        B(str, str2, str3, str4, str5, null);
    }

    public static void B(String str, String str2, String str3, String str4, String str5, HashMap hashMap) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str2, str4, f10642k, str5, str3, hashMap);
    }

    public static void C(Activity activity, JDHomeFragment jDHomeFragment, String str) {
        JDMtaUtils.sendPagePv(activity, jDHomeFragment, str, RecommendMtaUtils.Home_PageId, "");
    }

    private synchronized void G(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            H(arrayList.get(i2));
        }
    }

    private synchronized void I(int i2, String str, int i3) {
        int i4 = this.f10645f;
        if (i4 < i2) {
            this.f10645f = i2;
            this.f10646g = str;
            this.f10647h = i3;
        } else if (i4 == i2 && this.f10647h < i3) {
            this.f10647h = i3;
        }
    }

    private synchronized void J(ArrayList<com.jingdong.app.mall.home.r.c.b> arrayList) {
        if (arrayList == null) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            K(arrayList.get(i2));
        }
    }

    public static a i() {
        return b.a;
    }

    private void o() {
        this.f10643c.clear();
    }

    public static void s(String str, String str2, String str3) {
        t(str, str2, str3, RecommendMtaUtils.Home_PageId);
    }

    public static void t(String str, String str2, String str3, String str4) {
        u(str, str2, str3, str4, null, "");
    }

    public static void u(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, String str5) {
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str2, "", str4, f10642k, str5, "", str3, hashMap);
    }

    public static void v(Context context, String str, String str2) {
        w(context, str, str2, "", RecommendMtaUtils.Home_PageId);
    }

    public static void w(Context context, String str, String str2, String str3, String str4) {
        x(context, str, str2, str3, str4, f10642k);
    }

    public static void x(Context context, String str, String str2, String str3, String str4, Object obj) {
        JDMtaUtils.sendExposureData(context, obj, str4, str3, str, str2, "", "", "");
    }

    public static void y(String str, String str2, String str3) {
        z(str, str2, str3, RecommendMtaUtils.Home_PageId);
    }

    public static void z(String str, String str2, String str3, String str4) {
        A(str, str2, str3, str4, "");
    }

    public void D(int i2, int i3) {
        this.f10648i = i2;
        this.f10649j = i3;
    }

    public void E(String str, String str2, com.jingdong.app.mall.home.r.c.b bVar) {
        c cVar;
        if (str == null || (cVar = this.a.get(str)) == null) {
            return;
        }
        cVar.a(str2);
        cVar.b(bVar);
    }

    public void F(String str, ConcurrentHashMap<Integer, BannerFloorEntity.VariaModel> concurrentHashMap, CopyOnWriteArrayList<com.jingdong.app.mall.home.r.c.b> copyOnWriteArrayList) {
        c cVar;
        if (TextUtils.isEmpty(str) || (cVar = this.a.get(str)) == null) {
            return;
        }
        cVar.i(concurrentHashMap, copyOnWriteArrayList);
    }

    public synchronized void H(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f10643c.put(str, Boolean.TRUE);
        }
    }

    public synchronized void K(com.jingdong.app.mall.home.r.c.b bVar) {
        if (bVar != null) {
            this.d.put(bVar, Boolean.TRUE);
        }
    }

    public void b(String str, c cVar) {
        if (str != null && this.a.get(str) == null) {
            this.a.put(str, cVar);
        }
    }

    public void c(String str, c cVar) {
        if (str == null || cVar == null || this.b.get(str) != null) {
            return;
        }
        this.b.put(str, cVar);
    }

    public synchronized void d(BaseMallFloor baseMallFloor, ArrayList<String> arrayList, ArrayList<com.jingdong.app.mall.home.r.c.b> arrayList2) {
        if (baseMallFloor != null) {
            e(baseMallFloor.isFloorDisplay(), arrayList, arrayList2);
        }
    }

    public synchronized void e(boolean z, ArrayList<String> arrayList, ArrayList<com.jingdong.app.mall.home.r.c.b> arrayList2) {
        if (z) {
            G(arrayList);
            J(arrayList2);
        }
    }

    public synchronized void f(boolean z, int i2, String str, int i3) {
        if (z) {
            I(i2, str, i3);
        }
    }

    public int g() {
        return this.f10649j;
    }

    public int h() {
        return this.f10648i;
    }

    public synchronized void j() {
        k();
        ConcurrentHashMap<String, Boolean> concurrentHashMap = this.f10643c;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            this.f10644e.add(new HashSet(this.f10643c.keySet()));
            o();
            com.jingdong.app.mall.home.w.a.b(new C0319a(), false);
        }
    }

    public synchronized void k() {
        ConcurrentHashMap<com.jingdong.app.mall.home.r.c.b, Boolean> concurrentHashMap;
        try {
            concurrentHashMap = this.d;
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            HashSet hashSet = new HashSet(this.d.keySet());
            this.d.clear();
            JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                d.put((com.jingdong.app.mall.home.r.c.b) it.next());
            }
            A("Home_FloorIDExpo_Json", "", d.toString(), RecommendMtaUtils.Home_PageId, com.jingdong.app.mall.home.state.old.a.f() ? l.f() : "");
        }
    }

    public synchronized void l() {
        String str = this.f10646g;
        if ((str == null || str.isEmpty()) && this.f10645f == 1) {
            return;
        }
        try {
            Context applicationContext = JdSdk.getInstance().getApplication().getApplicationContext();
            if (applicationContext != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.f10645f);
                stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
                stringBuffer.append(this.f10646g);
                stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
                stringBuffer.append(this.f10647h);
                v(applicationContext, "Home_ScrollDepth", stringBuffer.toString());
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        q();
    }

    public void m() {
        MallFloorCategory.reportExpoData();
        Iterator<Map.Entry<String, c>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            c value = it.next().getValue();
            f.n(value);
            c cVar = value;
            if (cVar != null && cVar.d()) {
                B(cVar.a, cVar.e(), cVar.g(), RecommendMtaUtils.Home_PageId, "", cVar.f());
            }
        }
    }

    public void n() {
        for (Map.Entry<String, c> entry : this.b.entrySet()) {
            f.n(entry);
            c value = entry.getValue();
            f.n(value);
            c cVar = value;
            if (cVar != null) {
                s(cVar.a, cVar.b, "");
            }
        }
    }

    public void p() {
        Iterator<Map.Entry<String, c>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            c value = it.next().getValue();
            f.n(value);
            c cVar = value;
            if (cVar != null) {
                cVar.h();
            }
        }
    }

    public synchronized void q() {
        this.f10645f = 1;
        this.f10646g = "";
        this.f10647h = 1;
    }

    public void r() {
        ConcurrentHashMap<String, c> concurrentHashMap = this.b;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.clear();
    }

    private a() {
        this.a = new ConcurrentHashMap<>();
        this.b = new ConcurrentHashMap<>();
        this.f10643c = new ConcurrentHashMap<>();
        this.d = new ConcurrentHashMap<>();
        this.f10644e = new ConcurrentLinkedQueue<>();
        this.f10645f = 1;
        this.f10646g = "";
        this.f10647h = 1;
        this.f10648i = 0;
        this.f10649j = 0;
    }
}
