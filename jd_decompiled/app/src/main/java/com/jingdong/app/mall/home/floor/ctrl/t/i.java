package com.jingdong.app.mall.home.floor.ctrl.t;

import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.deploy.view.layout.widget.LottieAnimationMask;
import com.jingdong.app.mall.home.floor.bottomfloat.BottomFloatLayout;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class i {
    private SparseArray<j> a;
    public AtomicBoolean b;

    /* renamed from: c */
    private boolean f9566c;
    private boolean d;

    /* renamed from: e */
    private boolean f9567e;

    /* renamed from: f */
    private SparseArray<HomeWebFloorEntity> f9568f;

    /* renamed from: g */
    private SparseArray<HomeWebFloorEntity> f9569g;

    /* renamed from: h */
    private boolean f9570h;

    /* renamed from: i */
    private int f9571i;

    /* loaded from: classes4.dex */
    public static class b {
        static i a = new i();
    }

    private void l(SparseArray<HomeWebFloorEntity> sparseArray) {
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = sparseArray.keyAt(i2);
            HomeWebFloorEntity valueAt = sparseArray.valueAt(i2);
            j n2 = n(keyAt);
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (n2 != null && z0 != null) {
                n2.a(valueAt, z0.thisActivity);
            }
        }
    }

    private j m(int i2) {
        try {
            return this.a.valueAt(i2);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            return null;
        }
    }

    private j o(HomeWebFloorEntity homeWebFloorEntity) {
        int i2 = homeWebFloorEntity.moduleFunction;
        if (i2 == 0 || i2 == 1) {
            com.jingdong.app.mall.home.r.b.c.h();
            BottomFloatLayout.t();
            return new p();
        } else if (i2 != 3) {
            if (i2 != 7) {
                if (i2 != 10) {
                    return null;
                }
                return new g();
            }
            return new o();
        } else {
            com.jingdong.app.mall.home.r.b.c.h();
            BottomFloatLayout.t();
            LottieAnimationMask.e(true);
            int i3 = homeWebFloorEntity.bindModule;
            if (!TextUtils.isEmpty(homeWebFloorEntity.xViewId)) {
                return new l();
            }
            if (i3 <= 0 && homeWebFloorEntity.xViewType != 2) {
                return new n();
            }
            return new k();
        }
    }

    public static i p() {
        return b.a;
    }

    public void A(boolean z) {
        this.d = z;
    }

    public void B(boolean z, int i2) {
        if (!z) {
            if (this.f9566c) {
                l(this.f9569g);
            } else {
                HomeWebFloorEntity homeWebFloorEntity = this.f9569g.get(3);
                if (homeWebFloorEntity != null) {
                    m.e(homeWebFloorEntity.getLaunchEntity(), "4");
                }
                EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("homePageXViewLaunchClose"));
            }
            g();
        }
        this.f9570h = z;
    }

    public void C(boolean z) {
        j n2;
        this.f9566c = z;
        if (z || (n2 = n(3)) == null) {
            return;
        }
        n2.destroy();
    }

    public void D(boolean z) {
        this.f9567e = z;
    }

    public void E(boolean z) {
        B(z, 0);
    }

    public void a(int i2, HomeWebFloorEntity homeWebFloorEntity) {
        this.f9568f.append(i2, homeWebFloorEntity);
    }

    public void b(int i2) {
        if (this.a.get(i2) == null) {
            HomeWebFloorEntity homeWebFloorEntity = new HomeWebFloorEntity(null);
            homeWebFloorEntity.moduleFunction = i2;
            j o = o(homeWebFloorEntity);
            if (o != null) {
                this.a.append(i2, o);
            }
        }
    }

    public void c(HomeWebFloorEntity homeWebFloorEntity) {
        j o;
        if (homeWebFloorEntity == null) {
            return;
        }
        int i2 = homeWebFloorEntity.moduleFunction;
        if (this.a.get(i2) != null || (o = o(homeWebFloorEntity)) == null) {
            return;
        }
        this.a.append(i2, o);
    }

    public void d() {
        this.f9571i++;
    }

    public boolean e(int i2, HomeWebFloorEntity homeWebFloorEntity) {
        this.f9569g.append(i2, homeWebFloorEntity);
        return true;
    }

    public void f() {
        this.f9568f.clear();
    }

    public void g() {
        this.f9569g.clear();
    }

    public void h() {
        j n2 = n(10);
        if (n2 != null) {
            n2.destroy();
        }
    }

    public boolean i(String str, HomeWebFloorViewEntity homeWebFloorViewEntity) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        j n2 = n(10);
        if (n2 == null) {
            j(str, homeWebFloorViewEntity);
            return false;
        }
        if (n2 instanceof g) {
            g gVar = (g) n2;
            if (!TextUtils.equals(gVar.v(), str)) {
                j(str, homeWebFloorViewEntity);
                return false;
            }
            int k2 = gVar.k();
            if (k2 == 3 || k2 == 4) {
                gVar.b();
                return true;
            } else if (k2 != 2) {
                j(str, homeWebFloorViewEntity);
                return false;
            } else if (gVar.s() > 10) {
                gVar.r();
                return false;
            }
        }
        return false;
    }

    public void j(String str, HomeWebFloorViewEntity homeWebFloorViewEntity) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        j n2 = n(10);
        if (n2 == null) {
            b(10);
            n2 = n(10);
        }
        if (n2 instanceof g) {
            g gVar = (g) n2;
            if (TextUtils.equals(gVar.v(), str) && gVar.d()) {
                return;
            }
            gVar.m(str, homeWebFloorViewEntity);
        }
    }

    public void k() {
        for (int i2 = 0; i2 < 11; i2++) {
            j jVar = this.a.get(i2);
            if (jVar != null) {
                jVar.destroy();
            }
        }
    }

    public j n(int i2) {
        return this.a.get(i2);
    }

    public void onEventMainThread(com.jingdong.app.mall.home.floor.common.e eVar) {
        if (TextUtils.equals("homePageXViewLaunchClose", eVar.getType())) {
            l(this.f9568f);
            f();
        }
    }

    public boolean q() {
        return this.f9566c;
    }

    public List<j> r() {
        int size = this.a.size();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            j m2 = m(i2);
            if (m2 != null && m2.isShowing()) {
                arrayList.add(m2);
            }
        }
        return arrayList;
    }

    public boolean s(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        return this.d && homeWebFloorViewEntity != null && homeWebFloorViewEntity.isConflict;
    }

    public boolean t() {
        return this.f9570h;
    }

    public boolean u() {
        return this.f9571i < 2;
    }

    public boolean v() {
        return this.f9567e;
    }

    public boolean w() {
        Iterator<j> it = r().iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= it.next().c();
        }
        return z;
    }

    public void x() {
        this.b.set(false);
    }

    public void y() {
        this.b.set(true);
    }

    public void z(int i2) {
        this.a.delete(i2);
    }

    private i() {
        this.b = new AtomicBoolean(false);
        this.f9566c = true;
        this.d = false;
        this.f9568f = new SparseArray<>();
        this.f9569g = new SparseArray<>();
        this.a = new SparseArray<>();
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }
}
