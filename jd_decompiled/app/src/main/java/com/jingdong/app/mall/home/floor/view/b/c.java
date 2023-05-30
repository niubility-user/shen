package com.jingdong.app.mall.home.floor.view.b;

import android.graphics.Rect;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.VideoSkuLayout;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class c {
    public static final c FULL;
    public static final c NORMAL;
    public static final c OTHERS;
    public static final c SPECIAL;

    /* renamed from: g */
    private static final /* synthetic */ c[] f9736g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum a extends c {
        int skuSize;

        a(String str, int i2) {
            super(str, i2, null);
            this.skuSize = R2.anim.pickerview_dialog_scale_out;
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public int changeSkuSize(VideoSkuLayout videoSkuLayout, f fVar, boolean z, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar) {
            fVar.R(R2.anim.pickerview_dialog_scale_out, R2.anim.pickerview_dialog_scale_out);
            Rect f2 = fVar.f(true);
            f2.top = 0;
            if (bVar.B()) {
                fVar.R(121, 121);
                f2.top = z ? 23 : 27;
            }
            return 0;
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public void parse900Size(e eVar, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            super.parse900Size(eVar, bVar, i2);
            int i3 = bVar.w() ? 12 : 0;
            int i4 = this.skuSize;
            eVar.a = new f(i4, i4);
            int i5 = this.skuSize;
            eVar.b = new f(i5, i5);
            eVar.f9742c = new f(-2, 56);
            eVar.d = new f(-2, 56);
            eVar.f9743e = new f(-2, R2.anim.lib_cashier_sdk_fragment_right_out);
            eVar.f9744f = new f(-2, R2.anim.lib_cashier_sdk_fragment_right_out);
            int i6 = (bVar.z() ? 92 : 67) + i3;
            if (i2 == 0) {
                eVar.f9742c.F(new Rect(24, i3, 0, 0));
                eVar.f9743e.F(new Rect(24, 0, 0, 0));
                eVar.a.F(new Rect(24, i6, 0, 0));
                eVar.d.F(new Rect(0, i3, 0, 0));
                eVar.b.F(new Rect(0, i6, 5, 0));
            } else if (i2 + bVar.t() == 4) {
                eVar.f9742c.F(new Rect(5, i3, 0, 0));
                eVar.f9743e.F(new Rect(5, 0, 0, 0));
                eVar.a.F(new Rect(5, i6, 0, 0));
                eVar.d.F(new Rect(0, i3, 24, 0));
                eVar.b.F(new Rect(0, i6, 24, 0));
            }
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public void parse911Size(e eVar, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            super.parse900Size(eVar, bVar, i2);
            int i3 = this.skuSize;
            eVar.a = new f(i3, i3);
            int i4 = this.skuSize;
            eVar.b = new f(i4, i4);
            eVar.f9742c = new f(-2, 72);
            eVar.d = new f(-2, 72);
            eVar.f9743e = new f(-2, R2.anim.mtrl_bottom_sheet_slide_in);
            eVar.f9744f = new f(-2, R2.anim.mtrl_bottom_sheet_slide_in);
            int i5 = bVar.z() ? 96 : 64;
            if (i2 == 0) {
                eVar.f9742c.F(new Rect(24, 0, 0, 0));
                eVar.f9743e.F(new Rect(24, 0, 0, 0));
                eVar.a.F(new Rect(24, i5, 0, 0));
                eVar.d.F(new Rect(0, 0, 0, 0));
                eVar.b.F(new Rect(0, i5, 5, 0));
            } else if (i2 + bVar.t() == 4) {
                eVar.f9742c.F(new Rect(5, 0, 0, 0));
                eVar.f9743e.F(new Rect(5, 0, 0, 0));
                eVar.a.F(new Rect(5, i5, 0, 0));
                eVar.d.F(new Rect(0, 0, 24, 0));
                eVar.b.F(new Rect(0, i5, 24, 0));
            }
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public void parseHeight(com.jingdong.app.mall.home.r.e.d dVar, int i2, boolean z) {
            super.parseHeight(dVar, i2, z);
            boolean y = com.jingdong.app.mall.home.floor.view.linefloor.base.a.y(dVar.mParentModel.d0);
            boolean F = m.F(dVar.mParentModel);
            int i3 = y ? 276 : 252;
            if (F) {
                i3 = y ? R2.attr.SimpleEnableHeaderTranslationContent : 251;
            } else if (i2 == 0) {
                i3 = y ? 288 : 263;
            }
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i3);
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public boolean showLiveLottie(int i2, int i3, int i4) {
            return i3 == i4;
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public boolean showSkuLabel(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            if (TextUtils.isEmpty(bVar.T(i2))) {
                return false;
            }
            return TextUtils.isEmpty(bVar.s0(i2)) || !bVar.u0(i2);
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public boolean useSkuBg() {
            return true;
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.c
        public boolean useSkuMask() {
            return true;
        }
    }

    static {
        a aVar = new a("NORMAL", 0);
        NORMAL = aVar;
        c cVar = new c("SPECIAL", 1);
        SPECIAL = cVar;
        c cVar2 = new c("FULL", 2) { // from class: com.jingdong.app.mall.home.floor.view.b.c.b
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.c
            public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar, int i2) {
                super.parseFloorInfo(dVar, 0);
            }
        };
        FULL = cVar2;
        c cVar3 = new c("OTHERS", 3) { // from class: com.jingdong.app.mall.home.floor.view.b.c.c
            {
                a aVar2 = null;
            }
        };
        OTHERS = cVar3;
        f9736g = new c[]{aVar, cVar, cVar2, cVar3};
    }

    /* synthetic */ c(String str, int i2, a aVar) {
        this(str, i2);
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f9736g.clone();
    }

    public void changeSkuLayoutSize(f fVar, boolean z, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar) {
    }

    public int changeSkuSize(VideoSkuLayout videoSkuLayout, f fVar, boolean z, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar) {
        return 0;
    }

    public int getDividerWidth() {
        return 0;
    }

    public void parse900Size(e eVar, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
    }

    public void parse911Size(e eVar, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
    }

    public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar, int i2) {
        int d = com.jingdong.app.mall.home.floor.common.d.d(i2);
        dVar.mPaddingRect = new Rect(d, 0, d, 0);
        h hVar = dVar.mParentModel;
        if (hVar != null) {
            hVar.d = 20;
        }
        dVar.unUsedDivider();
    }

    public void parseHeight(com.jingdong.app.mall.home.r.e.d dVar, int i2, boolean z) {
    }

    public void parseSize(e eVar, com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        if (bVar.F()) {
            parse911Size(eVar, bVar, i2);
        } else {
            parse900Size(eVar, bVar, i2);
        }
    }

    public boolean showLiveLottie(int i2, int i3, int i4) {
        return false;
    }

    public boolean showSkuLabel(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        return false;
    }

    public boolean useOverlay() {
        return false;
    }

    public boolean useSkuBg() {
        return false;
    }

    public boolean useSkuMask() {
        return false;
    }

    public boolean useSubTitle(com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar) {
        return aVar.z() || aVar.B();
    }

    private c(String str, int i2) {
        super(str, i2);
    }
}
