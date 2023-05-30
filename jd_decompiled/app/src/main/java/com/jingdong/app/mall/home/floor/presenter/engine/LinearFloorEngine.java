package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.n;
import com.jingdong.app.mall.home.floor.ctrl.o;
import com.jingdong.app.mall.home.floor.model.entity.LinearFloorEntity;
import com.jingdong.app.mall.home.r.c.c;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LinearFloorEngine<E extends LinearFloorEntity> extends FloorEngine<E> {
    public static boolean i(h hVar) {
        int i2;
        return hVar != null && ((i2 = hVar.E) == 1 || i2 == 2);
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, E e2) {
        super.e(hVar, dVar, e2);
        if (dVar == null) {
            return;
        }
        if (TextUtils.equals(dVar.a, "06056")) {
            if (i(hVar)) {
                e2.setHasSkuAnimation(true);
                n.i().m(hVar.A, hVar.E, hVar.getJsonInt("aInterval", 10));
            }
            e2.setUseBgMarginColor(true);
            e2.setDisplayUiStyle(dVar.f10689k);
        }
        if (TextUtils.equals(dVar.a, "06018")) {
            e2.setUseBgMarginColor(true);
        }
        if (TextUtils.equals(dVar.a, "08002")) {
            e2.setSubFloorBgImg(dVar.getJsonString("subFloorBgImg"));
            int m2 = m.m(LinearFloorEntity.LAYERSHOWTIMES);
            e2.setSupportLayerBg(dVar.getJsonInt(LinearFloorEntity.LAYERSHOWTIMES) > m2 && !TextUtils.isEmpty(e2.getSubFloorBgImg()));
            e2.setLayerRemain(dVar.getJsonInt("layerRemain"));
            e2.setLayerShowTimes(m2);
            e2.setItemDividerWidth(0);
            o.g().j(hVar.getJsonInt("aInterval", 10));
            StringBuilder sb = new StringBuilder();
            ArrayList<f> a = dVar.a();
            for (int i2 = 0; i2 < a.size(); i2++) {
                f fVar = a.get(i2);
                if (i2 != 0) {
                    sb.append("&&");
                }
                sb.append(fVar.j());
                sb.append("#");
                sb.append(fVar.c());
            }
            e2.setSpecialExpo(new c("Home_TLGrabExpo", sb.toString(), null));
        }
        ArrayList<f> a2 = dVar.a();
        for (int i3 = 0; i3 < a2.size(); i3++) {
            e2.addExpoJson(a2.get(i3).l());
        }
        e2.setFloorItemElements(dVar);
    }
}
