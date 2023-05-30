package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.Banner09018Entity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class Banner09018Engine extends FloorEngine<Banner09018Entity> {
    private void i(d dVar, Banner09018Entity banner09018Entity) {
        ArrayList<f> arrayList = dVar.f10682c;
        if (arrayList != null && arrayList.size() >= 3) {
            banner09018Entity.leftElement = arrayList.get(0);
            banner09018Entity.midElement = arrayList.get(1);
            banner09018Entity.rightElement = arrayList.get(2);
            banner09018Entity.addExpoJson();
            return;
        }
        banner09018Entity.resetData();
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, Banner09018Entity banner09018Entity) {
        super.e(hVar, dVar, banner09018Entity);
        if (hVar == null || dVar == null || banner09018Entity == null) {
            return;
        }
        i(dVar, banner09018Entity);
    }
}
