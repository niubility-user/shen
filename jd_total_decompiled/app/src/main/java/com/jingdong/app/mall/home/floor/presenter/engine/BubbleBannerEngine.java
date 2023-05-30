package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleBannerEngine extends FloorEngine<BubbleBannerEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, BubbleBannerEntity bubbleBannerEntity) {
        ArrayList<f> arrayList;
        super.e(hVar, dVar, bubbleBannerEntity);
        if (hVar == null || dVar == null || bubbleBannerEntity == null || (arrayList = dVar.f10682c) == null || arrayList.size() <= 0) {
            return;
        }
        int size = arrayList.size();
        if (TextUtils.equals(dVar.a, "08005") || TextUtils.equals(dVar.a, "08009") || TextUtils.equals(dVar.a, "09009")) {
            if (size < 3) {
                return;
            }
        } else if (size < 5) {
            return;
        }
        bubbleBannerEntity.parseItem(dVar, arrayList, hVar.getJsonInt("aInterval", 30) * 100);
    }
}
