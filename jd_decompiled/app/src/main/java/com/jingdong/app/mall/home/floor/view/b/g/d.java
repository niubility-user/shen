package com.jingdong.app.mall.home.floor.view.b.g;

import android.graphics.Rect;
import com.jingdong.app.mall.home.floor.view.b.d;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
class d {
    public static void a(com.jingdong.app.mall.home.floor.view.b.d dVar, com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar, int i2) {
        boolean z = aVar.z();
        int i3 = aVar.w() ? 12 : 0;
        int i4 = z ? 152 : 130;
        int i5 = i2 == 0 ? 24 : 20;
        int i6 = aVar.r(0) == 2 ? 8 : 12;
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(-2, 56);
        dVar.b = fVar;
        fVar.F(new Rect(i5, i3, 0, 0));
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_fragment_right_out, 56);
        dVar.f9737c = fVar2;
        fVar2.F(new Rect(i6, 0, 0, 0));
        int i7 = ((z ? 65 : 59) + i3) - (z ? 5 : 2);
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(326, -1);
        dVar.d = fVar3;
        fVar3.F(new Rect(24, i7, 0, 0));
        dVar.a = new d.a[2];
        for (int i8 = 0; i8 < 2; i8++) {
            int i9 = ((z ? 160 : R2.anim.pickerview_dialog_scale_in) * i8) + ((z ? 6 : 10) * i8);
            d.a[] aVarArr = dVar.a;
            aVarArr[i8] = new d.a();
            aVarArr[i8].b = new com.jingdong.app.mall.home.floor.common.f(z ? 160 : R2.anim.pickerview_dialog_scale_in, -1);
            dVar.a[i8].b.F(new Rect(i9, 0, 0, 0));
            dVar.a[i8].f9738c = new com.jingdong.app.mall.home.floor.common.f(i4, i4);
            dVar.a[i8].f9738c.F(new Rect(0, 0, 0, 0));
            int i10 = 36;
            dVar.a[i8].d = new com.jingdong.app.mall.home.floor.common.f(i4, z ? 36 : 38);
            d.a aVar2 = dVar.a[i8];
            if (!z) {
                i10 = 38;
            }
            aVar2.f9741g = new com.jingdong.app.mall.home.floor.common.f(-2, i10);
            dVar.a[i8].f9739e = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
            dVar.a[i8].f9740f = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
        }
    }

    public static void b(com.jingdong.app.mall.home.floor.view.b.d dVar, com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar, int i2) {
        if (aVar.F()) {
            c(dVar, aVar, i2);
        } else {
            a(dVar, aVar, i2);
        }
    }

    public static void c(com.jingdong.app.mall.home.floor.view.b.d dVar, com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar, int i2) {
        boolean z = aVar.z();
        int i3 = z ? 152 : 130;
        int i4 = i2 == 0 ? 24 : 20;
        int i5 = aVar.r(0) == 2 ? 8 : 12;
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(-2, 72);
        dVar.b = fVar;
        fVar.F(new Rect(i4, 0, 0, 0));
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_fragment_right_out, 56);
        dVar.f9737c = fVar2;
        fVar2.F(new Rect(i5, 0, 0, 0));
        int i6 = z ? 76 : 64;
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(326, -1);
        dVar.d = fVar3;
        fVar3.F(new Rect(24, i6 + 0, 0, 0));
        dVar.a = new d.a[2];
        for (int i7 = 0; i7 < 2; i7++) {
            int i8 = ((z ? 160 : R2.anim.pickerview_dialog_scale_in) * i7) + ((z ? 6 : 10) * i7);
            d.a[] aVarArr = dVar.a;
            aVarArr[i7] = new d.a();
            aVarArr[i7].b = new com.jingdong.app.mall.home.floor.common.f(z ? 160 : R2.anim.pickerview_dialog_scale_in, -1);
            dVar.a[i7].b.F(new Rect(i8, 0, 0, 0));
            dVar.a[i7].f9738c = new com.jingdong.app.mall.home.floor.common.f(i3, i3);
            dVar.a[i7].f9738c.F(new Rect(0, 0, 0, 0));
            int i9 = 36;
            dVar.a[i7].d = new com.jingdong.app.mall.home.floor.common.f(i3, z ? 36 : 38);
            d.a aVar2 = dVar.a[i7];
            if (!z) {
                i9 = 38;
            }
            aVar2.f9741g = new com.jingdong.app.mall.home.floor.common.f(-2, i9);
            dVar.a[i7].f9739e = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
            dVar.a[i7].f9740f = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
        }
    }
}
