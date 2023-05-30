package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerV936Entity;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleBannerV936Engine extends FloorEngine<BubbleBannerV936Entity> {
    private ArrayList<b> i(BubbleBannerV936Entity bubbleBannerV936Entity) {
        ArrayList<b> arrayList = new ArrayList<>();
        ArrayList<f> data = bubbleBannerV936Entity.getData();
        int size = data.size();
        for (int i2 = 0; i2 < size; i2++) {
            f fVar = data.get(i2);
            if (fVar != null && fVar.getJump() != null) {
                b c2 = b.c(fVar.getJump().srvJson);
                c2.a("row", Integer.valueOf(bubbleBannerV936Entity.getItemRowIndex(i2)));
                c2.a(Constant.KEY_COL, Integer.valueOf(bubbleBannerV936Entity.getItemColIndex(i2)));
                arrayList.add(c2);
            } else {
                arrayList.add(new b());
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, BubbleBannerV936Entity bubbleBannerV936Entity) {
        ArrayList<f> arrayList;
        super.e(hVar, dVar, bubbleBannerV936Entity);
        if (hVar == null || dVar == null || bubbleBannerV936Entity == null || (arrayList = dVar.f10682c) == null || arrayList.size() <= 0) {
            return;
        }
        bubbleBannerV936Entity.setShowSubTitle(dVar.getJsonInt("showSubTitle", 0) == 1);
        bubbleBannerV936Entity.setPlayAnimation(dVar.getJsonInt("playAnimation"));
        bubbleBannerV936Entity.setPlayAnimationDelay(dVar.getJsonInt("playAnimationDelay"));
        bubbleBannerV936Entity.setPlayAnimationSpeed(dVar.getJsonInt("playAnimationSpeed"));
        bubbleBannerV936Entity.setIndicatorColor(dVar.getJsonString("indicatorColor"));
        bubbleBannerV936Entity.setBgImg(dVar.getJsonString("subFloorBgImg", "https://emptyUrl"));
        int jsonInt = dVar.getJsonInt("showRow", 1);
        int size = arrayList.size();
        int min = Math.min(Math.min(size / 4, jsonInt), 3);
        bubbleBannerV936Entity.setShowRow(min);
        bubbleBannerV936Entity.setNeedClipBottom(min < jsonInt);
        int i2 = min * 4;
        int i3 = size / i2;
        if (i3 < 1) {
            return;
        }
        bubbleBannerV936Entity.setData(new ArrayList<>(arrayList.subList(0, i2 * i3)));
        bubbleBannerV936Entity.setParseExpoJson(i(bubbleBannerV936Entity));
    }
}
