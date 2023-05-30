package com.jingdong.app.mall.home.floor.common.i;

import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes4.dex */
public abstract class q {
    public static final q OVERLAY_DRAW_SKU_FIRST;
    public static final q OVERLAY_UN_DRAW_SKU_FIRST;
    public static final q UN_OVERLAY_DRAW_SKU_FIRST;
    public static final q UN_OVERLAY_UN_DRAW_SKU_FIRST;

    /* renamed from: g  reason: collision with root package name */
    private static final /* synthetic */ q[] f9355g;
    private int mLayerInt;

    /* loaded from: classes4.dex */
    enum a extends q {
        a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.q
        public void drawModuleItem(com.jingdong.app.mall.home.r.e.d dVar, MallFloorModuleCommon mallFloorModuleCommon, com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, BaseMallFloor baseMallFloor, r rVar, int i2, int i3, int i4) {
            p.a.C0288a c0288a = aVar.Y;
            mallFloorModuleCommon.addSeparationItemImgs(aVar, fVar, i4, c0288a.f9349i, i2, i3);
            mallFloorModuleCommon.addItemBackgroundImg(dVar, c0288a.q, mallFloorModuleCommon, aVar.w, true);
        }
    }

    static {
        a aVar = new a("OVERLAY_DRAW_SKU_FIRST", 0);
        OVERLAY_DRAW_SKU_FIRST = aVar;
        q qVar = new q("OVERLAY_UN_DRAW_SKU_FIRST", 1) { // from class: com.jingdong.app.mall.home.floor.common.i.q.b
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.q
            public void drawModuleItem(com.jingdong.app.mall.home.r.e.d dVar, MallFloorModuleCommon mallFloorModuleCommon, com.jingdong.app.mall.home.r.e.f fVar, p.a aVar2, BaseMallFloor baseMallFloor, r rVar, int i2, int i3, int i4) {
                p.a.C0288a c0288a = aVar2.Y;
                mallFloorModuleCommon.addItemBackgroundImg(dVar, c0288a.q, mallFloorModuleCommon, aVar2.w, false);
                mallFloorModuleCommon.addSeparationItemImgs(aVar2, fVar, i4, c0288a.f9349i, i2, i3);
            }
        };
        OVERLAY_UN_DRAW_SKU_FIRST = qVar;
        q qVar2 = new q("UN_OVERLAY_DRAW_SKU_FIRST", 2) { // from class: com.jingdong.app.mall.home.floor.common.i.q.c
            {
                a aVar2 = null;
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [com.jingdong.app.mall.home.r.f.a.b] */
            @Override // com.jingdong.app.mall.home.floor.common.i.q
            public void drawModuleItem(com.jingdong.app.mall.home.r.e.d dVar, MallFloorModuleCommon mallFloorModuleCommon, com.jingdong.app.mall.home.r.e.f fVar, p.a aVar2, BaseMallFloor baseMallFloor, r rVar, int i2, int i3, int i4) {
                p.a.C0288a c0288a = aVar2.Y;
                int addSeparationItemImgs = mallFloorModuleCommon.addSeparationItemImgs(aVar2, fVar, i4, c0288a.f9349i, i2, i3);
                c0288a.r = null;
                c0288a.f9348h = false;
                mallFloorModuleCommon.addItemBackgroundImg(dVar, fVar.G(), mallFloorModuleCommon, aVar2.w, true);
                MallFloorModuleCommon.addItemTitleAndSubTitle(fVar, aVar2, baseMallFloor.getPresenter().E(c0288a), mallFloorModuleCommon, i4, addSeparationItemImgs, rVar, i3);
            }
        };
        UN_OVERLAY_DRAW_SKU_FIRST = qVar2;
        q qVar3 = new q("UN_OVERLAY_UN_DRAW_SKU_FIRST", 3) { // from class: com.jingdong.app.mall.home.floor.common.i.q.d
            {
                a aVar2 = null;
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [com.jingdong.app.mall.home.r.f.a.b] */
            @Override // com.jingdong.app.mall.home.floor.common.i.q
            public void drawModuleItem(com.jingdong.app.mall.home.r.e.d dVar, MallFloorModuleCommon mallFloorModuleCommon, com.jingdong.app.mall.home.r.e.f fVar, p.a aVar2, BaseMallFloor baseMallFloor, r rVar, int i2, int i3, int i4) {
                p.a.C0288a c0288a = aVar2.Y;
                if (aVar2.r) {
                    mallFloorModuleCommon.addItemBackgroundImg(dVar, c0288a.q, mallFloorModuleCommon, aVar2.w, false);
                }
                MallFloorModuleCommon.addItemTitleAndSubTitle(fVar, aVar2, baseMallFloor.getPresenter().E(c0288a), mallFloorModuleCommon, i4, mallFloorModuleCommon.addSeparationItemImgs(aVar2, fVar, i4, c0288a.f9349i, i2, i3), rVar, i3);
            }
        };
        UN_OVERLAY_UN_DRAW_SKU_FIRST = qVar3;
        f9355g = new q[]{aVar, qVar, qVar2, qVar3};
    }

    /* synthetic */ q(String str, int i2, a aVar) {
        this(str, i2);
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) f9355g.clone();
    }

    public abstract void drawModuleItem(com.jingdong.app.mall.home.r.e.d dVar, MallFloorModuleCommon mallFloorModuleCommon, com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, BaseMallFloor baseMallFloor, r rVar, int i2, int i3, int i4);

    public int getSaveKey(int i2) {
        return i2 ^ (this.mLayerInt << 6);
    }

    private q(String str, int i2) {
        this.mLayerInt = p.b.getAndIncrement();
    }
}
