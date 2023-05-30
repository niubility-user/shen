package com.jingdong.app.mall.home.floor.view.floating;

import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class b extends a {
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean e() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public String g() {
        return "-100";
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void h(h hVar, View view, f fVar) {
        fVar.R(30, 30);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(fVar.v(), fVar.h());
        layoutParams.addRule(11);
        fVar.G(new Rect(0, 0, hVar.d, 0), layoutParams);
        fVar.L(new Rect(6, 6, 0, 0), view);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void i(h hVar, FloatLayout floatLayout) {
        int i2 = floatLayout.f9791j + (hVar.d << 1);
        int i3 = floatLayout.f9792k + (floatLayout.A ? 30 : 0);
        floatLayout.x.Q(i2);
        floatLayout.x.C(i3);
        floatLayout.x.F(new Rect(-i2, -i3, 0, 0));
        floatLayout.y.Q(floatLayout.f9791j);
        floatLayout.y.C(floatLayout.f9792k);
        floatLayout.y.F(new Rect(0, floatLayout.A ? 30 : 0, 0, 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public float m(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void n(FloatLayout floatLayout) {
        floatLayout.F();
    }
}
