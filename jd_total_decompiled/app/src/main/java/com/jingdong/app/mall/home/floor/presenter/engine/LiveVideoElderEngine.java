package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.model.entity.LiveVideoElderEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LiveVideoElderEngine extends FloorEngine<LiveVideoElderEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i */
    public void e(h hVar, d dVar, LiveVideoElderEntity liveVideoElderEntity) {
        ArrayList<f> arrayList;
        f fVar;
        super.e(hVar, dVar, liveVideoElderEntity);
        if (hVar == null || dVar == null || liveVideoElderEntity == null || (arrayList = dVar.f10682c) == null || arrayList.size() <= 0 || (fVar = arrayList.get(0)) == null || TextUtils.isEmpty(fVar.u()) || TextUtils.isEmpty(fVar.v())) {
            return;
        }
        fVar.a = hVar;
        liveVideoElderEntity.initData(fVar);
    }
}
