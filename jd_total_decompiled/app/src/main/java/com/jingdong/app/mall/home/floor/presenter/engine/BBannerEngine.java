package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.BBannerEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BBannerEngine extends FloorEngine<BBannerEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, BBannerEntity bBannerEntity) {
        ArrayList<f> arrayList;
        f fVar;
        super.e(hVar, dVar, bBannerEntity);
        if (hVar == null || dVar == null || bBannerEntity == null || (arrayList = dVar.f10682c) == null || arrayList.size() <= 0 || (fVar = arrayList.get(0)) == null) {
            return;
        }
        fVar.a = hVar;
        bBannerEntity.initData(hVar, fVar);
    }
}
