package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.MallFloorDeploy;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.c.c;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class DBanner2x4Model extends CoreModel<DBanner2x4> {
    private boolean r = false;
    private long s = -1;
    private ArrayList<MallFloorBannerItem> t = new ArrayList<>();
    private final ConcurrentHashMap<Integer, BannerFloorEntity.VariaModel> u = new ConcurrentHashMap<>();
    private final b v = b.c("");
    private CopyOnWriteArrayList<a> w = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<String> x = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> y = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<String> z = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<b> A = new CopyOnWriteArrayList<>();

    private void I0() {
        int size = this.t.size();
        for (int i2 = 0; i2 < size; i2++) {
            BannerFloorEntity.VariaModel variaModel = new BannerFloorEntity.VariaModel();
            variaModel.startDisplayTime = 0L;
            variaModel.displayRatio = 0.0f;
            this.u.put(Integer.valueOf(i2), variaModel);
        }
    }

    private void V0(int i2, float f2) {
        int size = this.u.size();
        if (size > 0) {
            T0(((i2 + size) - 1) % size, f2);
            U0(i2, f2);
        }
    }

    public ArrayList<MallFloorBannerItem> A0() {
        return this.t;
    }

    public String B0() {
        return this.f8920m.s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        ArrayList<MallFloorBannerItem> list = MallFloorBannerItem.toList(this.f8920m.getJsonString("markedImg"), this.f8920m.getJsonArr("content"));
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<b> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> copyOnWriteArrayList3 = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> copyOnWriteArrayList4 = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<a> copyOnWriteArrayList5 = new CopyOnWriteArrayList<>();
        if (list != null) {
            this.t = list;
            for (int i2 = 0; i2 < list.size(); i2++) {
                MallFloorBannerItem mallFloorBannerItem = list.get(i2);
                mallFloorBannerItem.isCache = Boolean.valueOf(this.f8878h.Z);
                JumpEntity jump = mallFloorBannerItem.getJump();
                String str = mallFloorBannerItem.exposalUrl;
                String str2 = mallFloorBannerItem.extension_id;
                String srv = jump == null ? "" : jump.getSrv();
                String srvJson = jump == null ? "" : jump.getSrvJson();
                b bVar = null;
                try {
                    if (!TextUtils.isEmpty(srvJson)) {
                        bVar = b.c(srvJson);
                        bVar.a("cache", mallFloorBannerItem.isCache.booleanValue() ? "1" : "0");
                        String optString = b.c(mallFloorBannerItem.extension_id).optString("uniqid");
                        if (!TextUtils.isEmpty(optString)) {
                            bVar.a("uniqId", b.c(optString));
                        }
                    }
                } catch (Exception e2) {
                    f.s0(this, e2);
                }
                copyOnWriteArrayList2.add(bVar);
                copyOnWriteArrayList.add(srv);
                copyOnWriteArrayList3.add(str);
                copyOnWriteArrayList4.add(str2);
                copyOnWriteArrayList5.add(new a("\u9996\u7126\u66dd\u5149", true, mallFloorBannerItem.expoLog));
            }
        }
        S0(copyOnWriteArrayList2);
        R0(copyOnWriteArrayList);
        Q0(copyOnWriteArrayList3);
        O0(copyOnWriteArrayList4);
        I0();
        com.jingdong.app.mall.home.r.c.a.i().b(B0(), new c("Home_FocusPic_Expo", "", com.jingdong.app.mall.home.r.c.a.f10642k));
        if (this.f8878h.Z) {
            return;
        }
        P0(copyOnWriteArrayList5);
    }

    public int C0() {
        V v = this.f8882l;
        if (v == 0) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = ((DBanner2x4) v).getLayoutParams();
        int i2 = layoutParams instanceof ViewGroup.MarginLayoutParams ? 0 + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin : 0;
        ViewParent parent = ((DBanner2x4) this.f8882l).getParent();
        return parent instanceof MallFloorDeploy ? i2 + ((MallFloorDeploy) parent).getLayoutTop() : i2;
    }

    public String D0(int i2) {
        return (i2 < 0 || i2 >= this.y.size()) ? "" : this.y.get(i2);
    }

    public String E0(int i2) {
        if (i2 >= 0) {
            try {
                return i2 < this.x.size() ? this.x.get(i2) : "";
            } catch (Exception e2) {
                f.s0(this, e2);
                return "";
            }
        }
        return "";
    }

    public String F0(int i2) {
        if (i2 >= 0) {
            try {
                return i2 < this.z.size() ? this.z.get(i2) : "";
            } catch (Exception e2) {
                f.s0(this, e2);
                return "";
            }
        }
        return "";
    }

    public b G0(int i2) {
        if (i2 >= 0) {
            try {
                if (i2 < this.A.size()) {
                    return this.A.get(i2);
                }
                return null;
            } catch (Exception e2) {
                f.s0(this, e2);
                return null;
            }
        }
        return null;
    }

    public int H0() {
        int g2 = com.jingdong.app.mall.home.n.h.c.g(f("slipTime"));
        if (g2 <= 0) {
            g2 = 4000;
        }
        return Math.max(g2, 1000);
    }

    public boolean J0() {
        return this.f8878h.Z;
    }

    public boolean K0() {
        return 1 == this.f8920m.getJsonInt("scrollLoop", 1);
    }

    public void L0(int i2) {
        try {
            if (!J0() && i2 >= 0 && i2 < this.w.size()) {
                this.w.get(i2).b();
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    public void M0(int i2) {
        if (this.r || this.s == -1) {
            return;
        }
        b bVar = new b();
        bVar.a("fgstart", Long.valueOf(this.s));
        bVar.a("fgend", Long.valueOf(System.currentTimeMillis()));
        bVar.a("frame", Integer.valueOf(i2));
        com.jingdong.app.mall.home.r.c.a.y("Home_FocusPic_ExpoTs", "", bVar.toString());
        this.s = -1L;
        this.r = true;
    }

    public void N0() {
        if (this.s == -1) {
            this.s = System.currentTimeMillis();
        }
        if (this.r) {
            this.r = false;
        }
    }

    public void O0(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.y.clear();
        this.y.addAll(copyOnWriteArrayList);
    }

    public void P0(CopyOnWriteArrayList<a> copyOnWriteArrayList) {
        this.w.clear();
        this.w.addAll(copyOnWriteArrayList);
    }

    public void Q0(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.x.clear();
        this.x.addAll(copyOnWriteArrayList);
    }

    public void R0(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.z.clear();
        this.z.addAll(copyOnWriteArrayList);
    }

    public void S0(CopyOnWriteArrayList<b> copyOnWriteArrayList) {
        this.A.clear();
        this.A.addAll(copyOnWriteArrayList);
    }

    public void T0(int i2, float f2) {
        long currentTimeMillis = System.currentTimeMillis();
        BannerFloorEntity.VariaModel variaModel = this.u.get(Integer.valueOf(i2));
        if (variaModel != null) {
            long j2 = variaModel.startDisplayTime;
            if (j2 > 0) {
                variaModel.allDisplayTime += currentTimeMillis - j2;
                variaModel.displayRatio = Math.max(f2, variaModel.displayRatio);
                variaModel.startDisplayTime = 0L;
                String str = i2 + "-----" + variaModel.allDisplayTime;
            }
        }
    }

    public void U0(int i2, float f2) {
        long currentTimeMillis = System.currentTimeMillis();
        BannerFloorEntity.VariaModel variaModel = this.u.get(Integer.valueOf(i2));
        if (variaModel != null) {
            variaModel.startDisplayTime = currentTimeMillis;
            variaModel.displayRatio = Math.max(f2, variaModel.displayRatio);
            String str = i2 + "-----" + variaModel.startDisplayTime;
        }
    }

    public void W0() {
        com.jingdong.app.mall.home.r.c.a.i().F(B0(), this.u, this.A);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(com.jingdong.app.mall.home.p.b.e.b bVar) {
        int b = this.f8879i.b();
        com.jingdong.app.mall.home.floor.common.f fVar = this.f8880j;
        int m2 = m(R2.anim.popdown_anim_feedback, R2.anim.popup_center_enter) << 1;
        if (b <= 0) {
            b = m(226, 226) << 1;
        }
        fVar.R(m2, b);
    }

    public void y0(DBanner2x4 dBanner2x4, int i2) {
        V0(i2, dBanner2x4.C());
        b G0 = G0(i2);
        if (G0 != null) {
            G0.a(DeeplinkProductDetailHelper.LAYER_STYLE, "0");
        }
        com.jingdong.app.mall.home.r.c.a.i().E(B0(), F0(i2) + CartConstant.KEY_YB_INFO_LINK + "0", G0);
    }

    public b z0() {
        return this.v;
    }
}
