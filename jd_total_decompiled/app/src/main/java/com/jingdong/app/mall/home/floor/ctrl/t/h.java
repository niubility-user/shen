package com.jingdong.app.mall.home.floor.ctrl.t;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.ctrl.s;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class h {

    /* renamed from: e  reason: collision with root package name */
    private static h f9564e;
    private final e a = new e();
    private final c b = new c();

    /* renamed from: c  reason: collision with root package name */
    private boolean f9565c;
    private long d;

    private h() {
    }

    private void a(HomeWebFloorEntity homeWebFloorEntity) {
        float f2 = homeWebFloorEntity.resultHeightRatio;
        if (f2 > 0.0f && f2 < 1.0f) {
            homeWebFloorEntity.resultHeight = com.jingdong.app.mall.home.floor.common.d.i((int) ((com.jingdong.app.mall.home.floor.common.i.m.u() - com.jingdong.app.mall.home.floor.ctrl.h.N().P()) * homeWebFloorEntity.resultHeightRatio));
        } else if (homeWebFloorEntity.refreshHeight >= homeWebFloorEntity.resultHeight) {
            homeWebFloorEntity.refreshHeight = 130;
            homeWebFloorEntity.resultHeight = 320;
        }
    }

    private void c(List<Integer> list, boolean z) {
        j n2;
        j n3;
        j n4;
        if (z && (n4 = i.p().n(3)) != null) {
            n4.h();
            if (Log.D) {
                Log.d("HomeXviewEngine", "start XView reset...");
            }
        }
        if (!list.contains(1) && (n3 = i.p().n(1)) != null) {
            n3.destroy();
            if (Log.D) {
                Log.d("HomeXviewEngine", "pull XView reset...");
            }
        }
        if (list.contains(7) || (n2 = i.p().n(7)) == null) {
            return;
        }
        n2.destroy();
        if (Log.D) {
            Log.d("HomeXviewEngine", "part XView reset...");
        }
    }

    public static h d() {
        if (f9564e == null) {
            synchronized (h.class) {
                if (f9564e == null) {
                    f9564e = new h();
                }
            }
        }
        return f9564e;
    }

    public void b(Activity activity) {
        this.b.o(activity);
    }

    public boolean e(boolean z) {
        return this.b.v(z) || this.a.i(z);
    }

    public void f(JDJSONObject jDJSONObject) {
        this.f9565c = TextUtils.equals("1", jDJSONObject.optString("backXViewSwitch"));
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.d;
        if (!this.f9565c || elapsedRealtime <= 0 || elapsedRealtime >= Final.HALF_MINUTE) {
            this.b.p();
        }
    }

    public void g() {
        this.b.w();
        this.a.j();
    }

    public void h(boolean z, boolean z2) {
        if (this.f9565c) {
            this.d = SystemClock.elapsedRealtime();
            this.b.x(z, z2);
        }
        this.a.k(z, z2);
    }

    public void i(JDJSONObject jDJSONObject, BaseActivity baseActivity, boolean z) {
        f(jDJSONObject);
        ArrayList arrayList = new ArrayList();
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("webViewFloorList");
        if (jSONArray == null) {
            if (Log.D) {
                Log.i("HomeXviewEngine", "expandXView-parser to removeExpandXView1");
            }
            s.b();
            c(arrayList, z);
            return;
        }
        i.p().d();
        boolean t = i.p().t();
        this.a.f();
        HomeWebFloorEntity homeWebFloorEntity = null;
        HomeWebFloorEntity homeWebFloorEntity2 = null;
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 = 0; i2 < jSONArray.size(); i2++) {
            JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                try {
                    homeWebFloorEntity2 = new HomeWebFloorEntity(jSONObject);
                } catch (Exception e2) {
                    if (Log.D) {
                        e2.printStackTrace();
                    }
                }
                if (homeWebFloorEntity2 == null) {
                    continue;
                } else {
                    int i3 = homeWebFloorEntity2.moduleFunction;
                    if (i3 == 0 || i3 == 1) {
                        if (!com.jingdong.app.mall.home.v.d.a.f()) {
                            i.p().b(1);
                            a(homeWebFloorEntity2);
                            homeWebFloorEntity = homeWebFloorEntity2;
                        }
                    } else if (i3 != 3) {
                        if (i3 == 7) {
                            arrayList.add(7);
                            i.p().c(homeWebFloorEntity2);
                            i.p().n(7).a(homeWebFloorEntity2, baseActivity);
                        } else if (i3 == 26) {
                            this.a.n(homeWebFloorEntity2, baseActivity);
                        }
                    } else if (!com.jingdong.app.mall.home.o.a.f.k0()) {
                        return;
                    } else {
                        if (i.p().u() && i.p().q()) {
                            HomeWebFloorViewEntity launchEntity = homeWebFloorEntity2.getLaunchEntity();
                            if (launchEntity != null) {
                                if (com.jingdong.app.mall.home.floor.common.i.g.f9303m) {
                                    m.e(launchEntity, "2");
                                } else if (i.p().s(launchEntity)) {
                                    m.e(launchEntity, "4");
                                } else if (i.p().v()) {
                                    m.e(launchEntity, "6");
                                } else if ((!t || i.p().e(3, homeWebFloorEntity2)) && !arrayList.contains(3)) {
                                    if (this.b.A(launchEntity)) {
                                        m.e(launchEntity, "5");
                                    } else {
                                        arrayList.add(3);
                                        i.p().c(homeWebFloorEntity2);
                                        if (!homeWebFloorEntity2.isPassthrough()) {
                                            z3 = true;
                                        }
                                        if (!t) {
                                            i.p().n(3).a(homeWebFloorEntity2, baseActivity);
                                        }
                                    }
                                }
                            }
                        } else {
                            j n2 = i.p().n(3);
                            if (n2 != null && n2.d()) {
                                arrayList.add(3);
                            }
                        }
                        z2 = true;
                    }
                }
            }
        }
        if (z2) {
            n.D(z3);
        }
        if (z3) {
            if (homeWebFloorEntity != null) {
                i.p().a(1, homeWebFloorEntity);
                arrayList.add(1);
                if (Log.D) {
                    Log.d("HomeXviewEngine", "pull XView delay start...");
                }
            }
        } else if (homeWebFloorEntity != null) {
            arrayList.add(1);
            if (t) {
                i.p().e(1, homeWebFloorEntity);
            } else {
                j n3 = i.p().n(1);
                if (!z || !n3.isShowing()) {
                    n3.a(homeWebFloorEntity, baseActivity);
                }
            }
            if (Log.D) {
                Log.d("HomeXviewEngine", "pull xview start...");
            }
        }
        c(arrayList, z);
    }
}
