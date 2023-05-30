package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.TrendEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.k.e;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class TrendEngine extends FloorEngine<TrendEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, TrendEntity trendEntity) {
        ArrayList<f> arrayList;
        super.e(hVar, dVar, trendEntity);
        if (hVar == null || dVar == null || trendEntity == null || (arrayList = dVar.f10682c) == null || arrayList.size() < 4) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            trendEntity.addItem(new e(arrayList.get(i2)));
        }
    }
}
