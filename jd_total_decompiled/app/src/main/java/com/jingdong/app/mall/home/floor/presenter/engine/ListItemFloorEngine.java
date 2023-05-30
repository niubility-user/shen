package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.ListItemFloorEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ListItemFloorEngine<E extends ListItemFloorEntity> extends FloorEngine<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    public void i(f fVar, E e2) {
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, E e2) {
        ArrayList<f> a;
        f fVar;
        super.e(hVar, dVar, e2);
        if (dVar == null || (a = dVar.a()) == null || a.size() < 1 || (fVar = a.get(0)) == null) {
            return;
        }
        e2.tpl = dVar.a;
        e2.mModel = hVar;
        e2.element = fVar;
        i(fVar, e2);
        String O = fVar.O();
        String N = fVar.N();
        e2.setIsShowTitle(!TextUtils.isEmpty(O));
        e2.setTitleText(O);
        e2.setHasRightCorner(!TextUtils.isEmpty(N));
        e2.setRightCornerText(N);
        e2.setMaiDianData(fVar.getJsonString("rcSourceValue"), fVar.b0());
        e2.setShowNameImg(fVar.Q());
        e2.jumpEntity = fVar.getJump();
        double layoutInnerWidth = e2.getLayoutInnerWidth();
        Double.isNaN(layoutInnerWidth);
        e2.setContentWidth((int) Math.floor(layoutInnerWidth / 4.5d));
        JDJSONObject jsonObject = fVar.getJsonObject("advert");
        if (jsonObject != null) {
            e2.setAdvertJump(jsonObject.getString("jump"));
            e2.setAdvertImg(jsonObject.getString("advertImg"));
            return;
        }
        e2.setAdvertImg("");
    }
}
