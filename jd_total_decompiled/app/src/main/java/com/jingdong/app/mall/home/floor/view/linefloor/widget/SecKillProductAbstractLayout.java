package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import com.jingdong.app.mall.home.floor.view.b.d;
import com.jingdong.app.mall.home.floor.view.b.f.g;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseAnimateLayout;

/* loaded from: classes4.dex */
public abstract class SecKillProductAbstractLayout extends BaseAnimateLayout implements g {
    protected Context o;
    protected SecKillBottomProductView p;

    public SecKillProductAbstractLayout(Context context, SecKillBottomProductView secKillBottomProductView) {
        super(context);
        this.o = context;
        this.p = secKillBottomProductView;
    }

    public abstract void q(d.a aVar, b bVar, int i2);

    public abstract int r();

    public boolean s() {
        return true;
    }

    public void t() {
    }

    public void u() {
    }

    public void v(int i2) {
    }
}
