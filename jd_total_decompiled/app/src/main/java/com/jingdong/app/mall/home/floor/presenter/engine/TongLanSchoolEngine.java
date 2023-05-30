package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.TongLanSchoolEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class TongLanSchoolEngine extends FloorEngine<TongLanSchoolEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, TongLanSchoolEntity tongLanSchoolEntity) {
        super.e(hVar, dVar, tongLanSchoolEntity);
        ArrayList<f> a = dVar.a();
        if (a == null || a.size() <= 0) {
            return;
        }
        f fVar = a.get(0);
        tongLanSchoolEntity.imageType = fVar.t();
        tongLanSchoolEntity.img = fVar.u();
        tongLanSchoolEntity.subtitle = fVar.d0();
        tongLanSchoolEntity.iconImg = fVar.getJsonString("iconImg");
        tongLanSchoolEntity.element = fVar;
    }
}
