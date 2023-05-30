package com.jingdong.app.mall.home.r.e;

import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.view.view.HomeFooterView;

/* loaded from: classes4.dex */
public class e extends d {
    public HomeFooterView z;

    public e(HomeFooterView homeFooterView, t tVar) {
        super(tVar, false);
        this.z = homeFooterView;
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public boolean q() {
        return true;
    }

    public void x(int i2) {
        HomeFooterView homeFooterView = this.z;
        if (homeFooterView != null) {
            homeFooterView.notifyHeightChanged(i2);
        }
    }

    public e(h hVar, t tVar, boolean z) {
        super(hVar, tVar, z);
    }
}
