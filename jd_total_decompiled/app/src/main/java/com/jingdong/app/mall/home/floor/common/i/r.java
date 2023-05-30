package com.jingdong.app.mall.home.floor.common.i;

import android.graphics.Point;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.LinearFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleAnimalSku;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleBannerAnim;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleMixed;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public abstract class r {
    public static final r MODULE_ANIMATE_SKU;
    public static final r MODULE_BIG_IMG;
    public static final r MODULE_MIX;
    public static final r MODULE_UNKNOWN;
    public static final r MODULE_V618_1_3;

    /* renamed from: g */
    private static final /* synthetic */ r[] f9356g;
    private int mModelInt;

    /* loaded from: classes4.dex */
    public enum a extends r {
        a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.r
        public MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor) {
            return new MallFloorModuleAnimalSku(baseMallFloor.getContext(), baseMallFloor);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.r
        public void parseModelParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
            if (floorEntity instanceof LinearFloorEntity) {
                LinearFloorEntity linearFloorEntity = (LinearFloorEntity) floorEntity;
                p.a moduleParamsAt = linearFloorEntity.getModuleParamsAt(i2);
                int displayUiStyle = linearFloorEntity.getDisplayUiStyle();
                if (displayUiStyle == 1) {
                    moduleParamsAt.j(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(50)));
                    moduleParamsAt.w(com.jingdong.app.mall.home.floor.common.d.d(140));
                    moduleParamsAt.C(com.jingdong.app.mall.home.floor.common.d.d(150));
                    moduleParamsAt.v(com.jingdong.app.mall.home.floor.common.d.d(22));
                    moduleParamsAt.p(false);
                    if (fVar.b() == 1) {
                        moduleParamsAt.A(f.CENTER_TOP);
                        moduleParamsAt.z(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(7)));
                        com.jingdong.app.mall.home.floor.common.i.e eVar = com.jingdong.app.mall.home.floor.common.i.e.CENTER_HORIZONTAL;
                        moduleParamsAt.x(eVar);
                        moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.CENTER_BOTTOM);
                        moduleParamsAt.t(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(19)));
                        moduleParamsAt.s(eVar);
                        return;
                    }
                    moduleParamsAt.A(f.LEFT_TOP);
                    moduleParamsAt.z(new Point(com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(7)));
                    com.jingdong.app.mall.home.floor.common.i.e eVar2 = com.jingdong.app.mall.home.floor.common.i.e.LEFT;
                    moduleParamsAt.x(eVar2);
                    moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.LEFT_BOTTOM);
                    moduleParamsAt.t(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(19)));
                    moduleParamsAt.s(eVar2);
                } else if (displayUiStyle == 2) {
                    moduleParamsAt.j(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(16)));
                    moduleParamsAt.w(com.jingdong.app.mall.home.floor.common.d.d(150));
                    moduleParamsAt.C(com.jingdong.app.mall.home.floor.common.d.d(150));
                    moduleParamsAt.v(com.jingdong.app.mall.home.floor.common.d.d(22));
                    moduleParamsAt.p(false);
                    if (fVar.b() == 1) {
                        moduleParamsAt.A(f.CENTER_TOP);
                        moduleParamsAt.z(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(R2.anim.slide_in_from_bottom)));
                        com.jingdong.app.mall.home.floor.common.i.e eVar3 = com.jingdong.app.mall.home.floor.common.i.e.CENTER_HORIZONTAL;
                        moduleParamsAt.x(eVar3);
                        moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.CENTER_TOP);
                        moduleParamsAt.t(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(R2.anim.message_center_dialog_out)));
                        moduleParamsAt.s(eVar3);
                        return;
                    }
                    moduleParamsAt.A(f.LEFT_TOP);
                    moduleParamsAt.z(new Point(com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(R2.anim.slide_in_from_bottom)));
                    com.jingdong.app.mall.home.floor.common.i.e eVar4 = com.jingdong.app.mall.home.floor.common.i.e.LEFT;
                    moduleParamsAt.x(eVar4);
                    moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.LEFT_TOP);
                    moduleParamsAt.t(new Point(com.jingdong.app.mall.home.floor.common.d.d(21), com.jingdong.app.mall.home.floor.common.d.d(R2.anim.message_center_dialog_out)));
                    moduleParamsAt.s(eVar4);
                } else if (displayUiStyle != 3) {
                    moduleParamsAt.j(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(16)));
                    moduleParamsAt.w(com.jingdong.app.mall.home.floor.common.d.d(154));
                    moduleParamsAt.v(com.jingdong.app.mall.home.floor.common.d.d(20));
                    moduleParamsAt.p(false);
                    if (fVar.b() == 1) {
                        moduleParamsAt.A(f.CENTER_TOP);
                        moduleParamsAt.z(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(162)));
                        com.jingdong.app.mall.home.floor.common.i.e eVar5 = com.jingdong.app.mall.home.floor.common.i.e.CENTER_HORIZONTAL;
                        moduleParamsAt.x(eVar5);
                        moduleParamsAt.t(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(4)));
                        moduleParamsAt.s(eVar5);
                        return;
                    }
                    moduleParamsAt.A(f.LEFT_TOP);
                    moduleParamsAt.z(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(162)));
                    com.jingdong.app.mall.home.floor.common.i.e eVar6 = com.jingdong.app.mall.home.floor.common.i.e.LEFT;
                    moduleParamsAt.x(eVar6);
                    moduleParamsAt.t(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(4)));
                    moduleParamsAt.s(eVar6);
                } else {
                    moduleParamsAt.j(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(22)));
                    moduleParamsAt.B(com.jingdong.app.mall.home.floor.common.d.d(24));
                    moduleParamsAt.v(com.jingdong.app.mall.home.floor.common.d.d(20));
                    moduleParamsAt.w(com.jingdong.app.mall.home.floor.common.d.d(150));
                    moduleParamsAt.C(com.jingdong.app.mall.home.floor.common.d.d(150));
                    moduleParamsAt.p(false);
                    if (fVar.b() == 1) {
                        moduleParamsAt.A(f.CENTER_TOP);
                        moduleParamsAt.z(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(R2.anim.pop_right_top_out)));
                        com.jingdong.app.mall.home.floor.common.i.e eVar7 = com.jingdong.app.mall.home.floor.common.i.e.CENTER_HORIZONTAL;
                        moduleParamsAt.x(eVar7);
                        moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.CENTER_BOTTOM);
                        moduleParamsAt.t(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(9)));
                        moduleParamsAt.s(eVar7);
                        return;
                    }
                    moduleParamsAt.A(f.LEFT_TOP);
                    moduleParamsAt.z(new Point(com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(170)));
                    com.jingdong.app.mall.home.floor.common.i.e eVar8 = com.jingdong.app.mall.home.floor.common.i.e.LEFT;
                    moduleParamsAt.x(eVar8);
                    moduleParamsAt.u(com.jingdong.app.mall.home.floor.common.i.c.LEFT_BOTTOM);
                    moduleParamsAt.t(new Point(com.jingdong.app.mall.home.floor.common.d.d(21), com.jingdong.app.mall.home.floor.common.d.d(9)));
                    moduleParamsAt.s(eVar8);
                }
            }
        }
    }

    static {
        a aVar = new a("MODULE_ANIMATE_SKU", 0);
        MODULE_ANIMATE_SKU = aVar;
        r rVar = new r("MODULE_V618_1_3", 1) { // from class: com.jingdong.app.mall.home.floor.common.i.r.b
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.r
            public MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor) {
                return new MallFloorModuleBannerAnim(baseMallFloor.getContext(), baseMallFloor);
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.r
            public void parseModelParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
                if (floorEntity instanceof LinearFloorEntity) {
                    p.a moduleParamsAt = ((LinearFloorEntity) floorEntity).getModuleParamsAt(i2);
                    floorEntity.setSeparationSingleBgImgScaleType(ImageView.ScaleType.FIT_XY);
                    moduleParamsAt.A(f.GONE);
                    moduleParamsAt.f(true);
                    moduleParamsAt.p(false);
                    moduleParamsAt.F(true);
                    moduleParamsAt.q(true);
                    if (i2 == 0) {
                        moduleParamsAt.e(2, com.jingdong.app.mall.home.floor.common.d.d(120), com.jingdong.app.mall.home.floor.common.d.d(120), 0);
                        moduleParamsAt.k(com.jingdong.app.mall.home.floor.common.i.a.RIGHT_BOTTOM);
                        moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(30), com.jingdong.app.mall.home.floor.common.d.d(38)));
                    } else if (i2 == 1) {
                        moduleParamsAt.e(2, com.jingdong.app.mall.home.floor.common.d.d(140), com.jingdong.app.mall.home.floor.common.d.d(140), 0);
                        moduleParamsAt.k(com.jingdong.app.mall.home.floor.common.i.a.CENTER_TOP);
                        moduleParamsAt.j(new Point(0, com.jingdong.app.mall.home.floor.common.d.d(28)));
                    } else if (i2 == 2) {
                        moduleParamsAt.e(2, com.jingdong.app.mall.home.floor.common.d.d(120), com.jingdong.app.mall.home.floor.common.d.d(120), 0);
                        moduleParamsAt.k(com.jingdong.app.mall.home.floor.common.i.a.LEFT_BOTTOM);
                        moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(30), com.jingdong.app.mall.home.floor.common.d.d(38)));
                    }
                    if (fVar.c() == 1 && !TextUtils.isEmpty(fVar.v()) && !TextUtils.isEmpty(fVar.u())) {
                        moduleParamsAt.c(2);
                        return;
                    }
                    if (TextUtils.isEmpty(fVar.u())) {
                        fVar.p0(fVar.v());
                    }
                    moduleParamsAt.c(1);
                }
            }
        };
        MODULE_V618_1_3 = rVar;
        r rVar2 = new r("MODULE_MIX", 2) { // from class: com.jingdong.app.mall.home.floor.common.i.r.c
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.r
            public MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor) {
                return new MallFloorModuleMixed(baseMallFloor.getContext(), baseMallFloor);
            }
        };
        MODULE_MIX = rVar2;
        r rVar3 = new r("MODULE_BIG_IMG", 3) { // from class: com.jingdong.app.mall.home.floor.common.i.r.d
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.r
            public MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor) {
                return null;
            }
        };
        MODULE_BIG_IMG = rVar3;
        r rVar4 = new r("MODULE_UNKNOWN", 4) { // from class: com.jingdong.app.mall.home.floor.common.i.r.e
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.r
            public MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor) {
                return new MallFloorModuleCommon(baseMallFloor.getContext(), baseMallFloor);
            }
        };
        MODULE_UNKNOWN = rVar4;
        f9356g = new r[]{aVar, rVar, rVar2, rVar3, rVar4};
    }

    /* synthetic */ r(String str, int i2, a aVar) {
        this(str, i2);
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f9356g.clone();
    }

    public abstract MallFloorModuleCommon getModelView(BaseMallFloor baseMallFloor);

    public int getSaveKey(int i2) {
        return i2 ^ (this.mModelInt << 12);
    }

    public void parseModelParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
    }

    private r(String str, int i2) {
        super(str, i2);
        this.mModelInt = p.a.getAndIncrement();
    }
}
