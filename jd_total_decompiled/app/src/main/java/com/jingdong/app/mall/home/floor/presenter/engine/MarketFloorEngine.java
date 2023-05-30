package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jd.framework.json.JDJSONArray;
import com.jingdong.app.mall.home.floor.model.entity.MarketFloorEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class MarketFloorEngine extends FloorEngine<MarketFloorEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, MarketFloorEntity marketFloorEntity) {
        ArrayList<f> arrayList;
        JDJSONArray V;
        super.e(hVar, dVar, marketFloorEntity);
        if (hVar == null || dVar == null || marketFloorEntity == null || (arrayList = dVar.f10682c) == null || arrayList.size() <= 0 || arrayList.get(0) == null || (V = arrayList.get(0).V()) == null || V.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < V.size() && i2 < 3; i2++) {
            marketFloorEntity.parseSku(V.getJSONObject(i2));
        }
        marketFloorEntity.initData(arrayList.get(0));
    }
}
