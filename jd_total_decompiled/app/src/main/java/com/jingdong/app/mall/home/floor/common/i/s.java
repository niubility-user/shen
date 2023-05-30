package com.jingdong.app.mall.home.floor.common.i;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.bottomfloat.BottomFloatLayout;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class s {
    private static final String a = "s";
    public static int b;

    /* renamed from: c */
    public static long f9357c;

    /* renamed from: f */
    private static volatile JDJSONObject f9359f;

    /* renamed from: g */
    private static volatile JDJSONObject f9360g;

    /* renamed from: h */
    private static volatile JDJSONObject f9361h;

    /* renamed from: i */
    private static volatile JDJSONObject f9362i;

    /* renamed from: j */
    private static JDJSONObject f9363j;
    public static AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: e */
    public static boolean f9358e = false;

    /* renamed from: k */
    private static final ConcurrentHashMap<String, JDJSONObject> f9364k = new ConcurrentHashMap<>();

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f9365g;

        a(boolean z) {
            this.f9365g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            s.d.set(this.f9365g);
        }
    }

    private static int a(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar, o oVar, int i2) {
        com.jingdong.app.mall.home.r.e.c cVar = new com.jingdong.app.mall.home.r.e.c(hVar, oVar);
        cVar.mTopParent = i2;
        cVar.G = dVar;
        cVar.x(list);
        return cVar.getFloorHeight();
    }

    private static void b(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.h hVar, int i2, boolean z) {
        com.jingdong.app.mall.home.r.e.e eVar = new com.jingdong.app.mall.home.r.e.e(hVar, t.FLOOR_MAI_DIAN, z);
        eVar.mTopParent = i2;
        list.add(eVar);
    }

    private static int c(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar, t tVar, int i2) {
        if (com.jingdong.app.mall.home.r.e.j.x(hVar)) {
            com.jingdong.app.mall.home.r.e.j jVar = new com.jingdong.app.mall.home.r.e.j(hVar);
            jVar.mTopParent = i2;
            jVar.z = dVar;
            list.add(jVar);
            return jVar.getFloorHeight();
        }
        return 0;
    }

    private static void d(com.jingdong.app.mall.home.r.e.h hVar, t tVar) {
        if (tVar == t.ICON && hVar.f10697i == 2) {
            hVar.b = 0;
        }
    }

    public static JDJSONObject e() {
        return f9360g;
    }

    public static JDJSONObject f() {
        return f9363j;
    }

    public static JDJSONObject g(String str) {
        ConcurrentHashMap<String, JDJSONObject> concurrentHashMap = f9364k;
        JDJSONObject jDJSONObject = concurrentHashMap.get(str);
        if (jDJSONObject != null) {
            return jDJSONObject;
        }
        JDJSONObject optJSONObject = f9362i.optJSONObject(str);
        if (optJSONObject != null) {
            concurrentHashMap.put(str, optJSONObject);
        }
        return optJSONObject;
    }

    public static JDJSONObject h() {
        return f9359f;
    }

    public static String i(String str) {
        return f9359f == null ? "" : f9359f.optString(str);
    }

    public static List<com.jingdong.app.mall.home.r.e.d> j(Map<String, Object> map) {
        return (List) map.get("key_normal_floor");
    }

    public static List<com.jingdong.app.mall.home.r.e.d> k(Map<String, Object> map) {
        return (List) map.get("key_special_floor");
    }

    private static void l(JDJSONObject jDJSONObject) {
        f9359f = jDJSONObject;
        f9361h = f9359f.optJSONObject("homeConfig");
        f9360g = f9359f.optJSONObject("appConfig");
    }

    private static com.jingdong.app.mall.home.r.e.d m(Context context, com.jingdong.app.mall.home.r.e.h hVar, t tVar, List<? super com.jingdong.app.mall.home.r.e.d> list, int i2, boolean z) {
        hVar.b0 = true;
        t tVar2 = t.WITH_BG_FLOOR;
        com.jingdong.app.mall.home.r.e.i iVar = new com.jingdong.app.mall.home.r.e.i(context, hVar, tVar2, z);
        ArrayList<com.jingdong.app.mall.home.r.e.d> arrayList = new ArrayList<>();
        iVar.u = u.b(tVar2);
        iVar.p = arrayList;
        iVar.mSubPosition = 0;
        ArrayList<com.jingdong.app.mall.home.r.e.d> x = com.jingdong.app.mall.home.r.e.i.x(context, hVar, z);
        iVar.mFloorHeight = n(context, hVar, x, arrayList, tVar, i2, z);
        p(x, true);
        list.add(iVar);
        return iVar;
    }

    public static int n(Context context, com.jingdong.app.mall.home.r.e.h hVar, List<com.jingdong.app.mall.home.r.e.d> list, List<? super com.jingdong.app.mall.home.r.e.d> list2, t tVar, int i2, boolean z) {
        hVar.X = i2;
        if (list == null) {
            com.jingdong.app.mall.home.r.e.i iVar = new com.jingdong.app.mall.home.r.e.i(context, hVar, tVar, z);
            if (iVar.p()) {
                int a2 = a(list2, hVar, iVar, o.TOP, 0) + 0;
                int c2 = a2 + c(list2, hVar, iVar, tVar, a2);
                hVar.G = 1;
                iVar.mTopParent = c2;
                iVar.mSubPosition = 1;
                int floorHeight = c2 + iVar.getFloorHeight();
                list2.add(iVar);
                int a3 = floorHeight + a(list2, hVar, iVar, o.BOTTOM, floorHeight);
                q(hVar, a3, i2 + a3, z);
                return a3;
            }
            return 0;
        }
        int size = list.size();
        hVar.G = size;
        com.jingdong.app.mall.home.r.e.d dVar = null;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            dVar = list.get(i5);
            if (dVar.p()) {
                if (!z2) {
                    int a4 = i3 + a(list2, hVar, dVar, o.TOP, i3);
                    i3 = a4 + c(list2, hVar, dVar, tVar, a4);
                    z2 = true;
                }
                i4++;
                dVar.mSubPosition = i4;
                dVar.mTopParent = i3;
                i3 += dVar.getFloorHeight();
                list2.add(dVar);
                if (i5 < size - 1) {
                    i3 += a(list2, hVar, dVar, o.MIDDLE, i3);
                }
            } else {
                hVar.G--;
            }
        }
        if (z2) {
            int a5 = i3 + a(list2, hVar, dVar, o.BOTTOM, i3);
            q(hVar, a5, i2 + a5, z);
            return a5;
        }
        return i3;
    }

    public static void o(List<com.jingdong.app.mall.home.r.e.d> list) {
        com.jingdong.app.mall.home.r.e.h hVar = null;
        int i2 = 0;
        int i3 = 0;
        for (com.jingdong.app.mall.home.r.e.d dVar : list) {
            com.jingdong.app.mall.home.r.e.h hVar2 = dVar.mParentModel;
            int floorHeight = dVar.getFloorHeight();
            if (hVar2 != hVar) {
                hVar2.X = i2;
                hVar = hVar2;
                i3 = 0;
            }
            dVar.mTopParent = i3;
            i2 += floorHeight;
            i3 += floorHeight;
        }
    }

    public static void p(List<com.jingdong.app.mall.home.r.e.d> list, boolean z) {
        com.jingdong.app.mall.home.floor.view.b.b bVar = null;
        com.jingdong.app.mall.home.floor.view.b.c cVar = null;
        loop0: while (true) {
            int i2 = 0;
            for (com.jingdong.app.mall.home.r.e.d dVar : list) {
                com.jingdong.app.mall.home.floor.view.b.c lineType = dVar.q.getLineType();
                if (lineType == null) {
                    if (dVar.getFloorHeight() != 0) {
                        if (bVar != null) {
                            bVar.b();
                            bVar = null;
                        }
                    }
                } else {
                    if (cVar != lineType) {
                        if (bVar != null) {
                            bVar.b();
                            bVar = null;
                        }
                        cVar = lineType;
                        i2 = 0;
                    }
                    if (dVar.p()) {
                        if (bVar == null) {
                            bVar = new com.jingdong.app.mall.home.floor.view.b.b();
                        }
                        bVar.a(dVar, lineType, i2);
                        i2++;
                    }
                }
            }
            break loop0;
        }
        if (bVar != null) {
            bVar.b();
        }
    }

    private static void q(com.jingdong.app.mall.home.r.e.h hVar, int i2, int i3, boolean z) {
        hVar.W = i2;
        if (z || TextUtils.isEmpty(hVar.B)) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.a.f9371e = i3;
    }

    private static void r() {
        com.jingdong.app.mall.home.floor.view.b.f.e.j().g();
        TitleTabManager.getInstance().getTitleTabInfo().reset();
        t(false);
        BottomFloatLayout.t.set(false);
        com.jingdong.app.mall.home.floor.ctrl.a.f9371e = 0;
        com.jingdong.app.mall.home.r.e.i.z.set(0);
        u.j();
    }

    public static void s(JDJSONObject jDJSONObject, boolean z) {
        l(jDJSONObject);
        g.r(f9361h);
        com.jingdong.app.mall.home.o.a.d.e(jDJSONObject, z);
        com.jingdong.app.mall.home.v.b.b(jDJSONObject, z);
        com.jingdong.app.mall.home.v.c.a.d(jDJSONObject, z);
    }

    private static void t(boolean z) {
        com.jingdong.app.mall.home.o.a.f.u0(new a(z));
    }

    private static void u(JDJSONObject jDJSONObject) {
        int optInt = jDJSONObject.optInt("preLoadHours");
        if (optInt <= 0) {
            return;
        }
        long optLong = jDJSONObject.optLong("requestTime");
        if (optLong <= 0) {
            return;
        }
        long j2 = optLong + (optInt * 3600000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM\u6708dd\u65e5 HH", Locale.getDefault());
        ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), "\u9996\u9875\u9884\u89c8: " + simpleDateFormat.format(new Date(j2)) + "\u65f6 \u6807\u7b7e:" + l.a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r15v5 */
    public static Map<String, Object> v(JDHomeFragment jDHomeFragment, JDJSONObject jDJSONObject, Context context, boolean z, boolean z2) {
        int i2;
        int i3;
        int i4;
        int i5;
        ArrayList arrayList;
        ArrayList arrayList2;
        l(jDJSONObject);
        com.jingdong.app.mall.home.o.a.l.h(jDJSONObject);
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("floorList");
        HashMap hashMap = new HashMap(8);
        if (jSONArray != null && jSONArray.size() > 0 && jDHomeFragment != null) {
            ?? r15 = 0;
            try {
                r();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                hashMap.put("key_normal_floor", arrayList3);
                hashMap.put("key_special_floor", arrayList4);
                f9363j = jDJSONObject.optJSONObject("callback");
                JDJSONObject optJSONObject = jDJSONObject.optJSONObject("layOut");
                if (optJSONObject != null) {
                    f9364k.clear();
                    f9362i = optJSONObject;
                }
                if (!z2) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    f9357c = elapsedRealtime;
                    com.jingdong.app.mall.home.i.m(elapsedRealtime);
                }
                if (!z && !z2) {
                    k.f();
                    u(jDJSONObject);
                }
                if (!z) {
                    HomeTitleFactory.setSearchInfo(jDJSONObject);
                }
                com.jingdong.app.mall.home.p.b.d.c.g().h(jDJSONObject, z);
                com.jingdong.app.mall.home.floor.ctrl.h.N().l0(f9357c, true);
                int size = jSONArray.size();
                if (size > 0) {
                    com.jingdong.app.mall.home.r.e.h hVar = new com.jingdong.app.mall.home.r.e.h(jSONArray.getJSONObject(0));
                    hVar.a0 = z2;
                    hVar.Z = z;
                    hVar.Y = f9357c;
                    t e2 = u.e(hVar);
                    JDJSONArray jsonArr = hVar.getJsonArr("content");
                    boolean z3 = !com.jingdong.app.mall.home.state.old.a.f() && e2 == t.FLOOR_CATEGORY && jsonArr != null && jsonArr.size() > 5;
                    f9358e = z3;
                    com.jingdong.app.mall.home.r.e.d dVar = z3 ? new com.jingdong.app.mall.home.r.e.d(hVar, t.FLOOR_CATEGORY, z) : null;
                    i3 = dVar == null ? 0 : dVar.getFloorHeight();
                    com.jingdong.app.mall.home.floor.ctrl.h.N().t0(i3);
                    com.jingdong.app.mall.home.r.e.i iVar = new com.jingdong.app.mall.home.r.e.i(null, z);
                    iVar.f10683e = com.jingdong.app.mall.home.floor.ctrl.h.w;
                    iVar.s(t.FLOOR_TRANSPARENT);
                    arrayList3.add(iVar);
                    i2 = iVar.getFloorHeight() + 0;
                    if (dVar != null) {
                        arrayList3.add(dVar);
                        i2 += dVar.getFloorHeight();
                        hVar.X = iVar.getFloorHeight();
                    }
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                com.jingdong.app.mall.home.floor.ctrl.h.N().t0(i3);
                int i6 = i2;
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    if (i7 >= size) {
                        break;
                    }
                    JDJSONObject jSONObject = jSONArray.getJSONObject(i7);
                    if (jSONObject == null) {
                        i4 = i7;
                    } else {
                        com.jingdong.app.mall.home.r.e.h hVar2 = new com.jingdong.app.mall.home.r.e.h(jSONObject);
                        hVar2.a0 = z2;
                        hVar2.Z = z;
                        i4 = i7;
                        hVar2.Y = f9357c;
                        if (com.jingdong.app.mall.home.a.n(hVar2.A)) {
                            new com.jingdong.app.mall.home.q.a("\u6dfb\u52a0\u9ed1\u540d\u5355", hVar2.J).b();
                        } else {
                            t e3 = u.e(hVar2);
                            if (e3 != t.UNKNOWN && e3 != t.FLOOR_CATEGORY) {
                                if (e3 == t.PRODUCT) {
                                    com.jingdong.app.mall.home.floor.ctrl.h.N().l0(hVar2.Y, r15);
                                    boolean F = m.F(hVar2);
                                    com.jingdong.app.mall.home.r.e.i iVar2 = new com.jingdong.app.mall.home.r.e.i(context, hVar2, t.FLOOR_RECOMMEND, z);
                                    int a2 = a(arrayList3, hVar2, iVar2, o.TOP, r15) + r15;
                                    b(arrayList3, hVar2, a2, z);
                                    int c2 = c(arrayList3, hVar2, iVar2, e3, a2);
                                    com.jingdong.app.mall.home.floor.ctrl.a.f9372f = c2;
                                    int i9 = a2 + c2;
                                    b(arrayList3, hVar2, i9, z);
                                    i6 += i9;
                                    hVar2.X = i6;
                                    q(hVar2, i9, i6, z);
                                    arrayList3.add(iVar2);
                                    com.jingdong.app.mall.home.a.f8564m = i6;
                                    jDHomeFragment.B1(jDJSONObject, z, F);
                                    t(true);
                                    break;
                                } else if (e3.isSpecial()) {
                                    arrayList4.add(new com.jingdong.app.mall.home.r.e.e(hVar2, e3, z));
                                } else if (hVar2.n()) {
                                    i5 = size;
                                    arrayList = arrayList4;
                                    i6 += m(context, hVar2, e3, arrayList3, i6, z).getFloorHeight();
                                    i8++;
                                    arrayList2 = arrayList3;
                                    i7 = i4 + 1;
                                    arrayList3 = arrayList2;
                                    size = i5;
                                    arrayList4 = arrayList;
                                    r15 = 0;
                                } else {
                                    i5 = size;
                                    arrayList = arrayList4;
                                    ArrayList<com.jingdong.app.mall.home.r.e.d> x = com.jingdong.app.mall.home.r.e.i.x(context, hVar2, z);
                                    d(hVar2, e3);
                                    i8++;
                                    arrayList2 = arrayList3;
                                    i6 += n(context, hVar2, x, arrayList3, e3, i6, z);
                                    i7 = i4 + 1;
                                    arrayList3 = arrayList2;
                                    size = i5;
                                    arrayList4 = arrayList;
                                    r15 = 0;
                                }
                            }
                        }
                    }
                    i5 = size;
                    arrayList = arrayList4;
                    arrayList2 = arrayList3;
                    i7 = i4 + 1;
                    arrayList3 = arrayList2;
                    size = i5;
                    arrayList4 = arrayList;
                    r15 = 0;
                }
                ArrayList arrayList5 = arrayList3;
                b = i8;
                com.jingdong.app.mall.home.a.f8564m = i6;
                p(arrayList5, false);
                if (k.c(arrayList5, z2) < 0) {
                    o(arrayList5);
                }
                com.jingdong.app.mall.home.j.b();
                com.jingdong.app.mall.home.o.a.k.e("userCategory = ".concat(jDJSONObject.optString("userCategory")));
            } catch (Exception e4) {
                if (Log.E) {
                    Log.e(a, e4.getMessage());
                }
                com.jingdong.app.mall.home.o.a.f.o(e4.getMessage());
            }
        }
        return hashMap;
    }
}
