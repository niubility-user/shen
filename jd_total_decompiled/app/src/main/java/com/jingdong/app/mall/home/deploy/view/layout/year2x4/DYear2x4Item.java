package com.jingdong.app.mall.home.deploy.view.layout.year2x4;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class DYear2x4Item extends RelativeLayout {

    /* renamed from: g */
    private IconImageText f9119g;

    /* renamed from: h */
    private SkuLayout f9120h;

    /* renamed from: i */
    private f f9121i;

    /* renamed from: j */
    private f f9122j;

    public DYear2x4Item(Context context) {
        super(context);
        setClipChildren(false);
        this.f9119g = new IconImageText(context);
        f fVar = new f(-2, 44);
        this.f9121i = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.f9119g);
        u.addRule(14);
        addView(this.f9119g, u);
        this.f9120h = new SkuLayout(context, 12);
        f fVar2 = new f(-1, -1);
        this.f9122j = fVar2;
        addView(this.f9120h, fVar2.u(this.f9120h));
    }

    public void a(DYear2x4Model dYear2x4Model, int i2) {
        this.f9119g.i(dYear2x4Model.m0()[i2]);
        this.f9120h.b(dYear2x4Model.k0()[i2]);
        f.c(this.f9119g, this.f9121i);
        f.c(this.f9120h, this.f9122j);
    }
}
