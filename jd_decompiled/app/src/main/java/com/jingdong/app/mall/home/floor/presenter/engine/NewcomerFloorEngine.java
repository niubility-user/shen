package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class NewcomerFloorEngine extends FloorEngine<NewcomerFloorEntity> {
    private void i(JDJSONArray jDJSONArray, NewcomerFloorEntity newcomerFloorEntity) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0 || newcomerFloorEntity == null) {
            return;
        }
        newcomerFloorEntity.initData(jDJSONArray);
        newcomerFloorEntity.addExpoMta();
    }

    private void l(String str, d dVar, NewcomerFloorEntity newcomerFloorEntity) {
        boolean z = !TextUtils.equals("NC000", str);
        newcomerFloorEntity.setHeightFloor(z);
        dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(z ? 336 : 116);
        c(dVar, newcomerFloorEntity);
    }

    public void j(b bVar, d dVar, NewcomerFloorEntity newcomerFloorEntity) {
        if (bVar == null || dVar == null || newcomerFloorEntity == null) {
            return;
        }
        newcomerFloorEntity.setShow(TextUtils.equals("1", bVar.getJsonString("isFloorExist", "1")));
        l(bVar.getJsonString("tpl"), dVar, newcomerFloorEntity);
        i(bVar.getJsonArr("data"), newcomerFloorEntity);
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, NewcomerFloorEntity newcomerFloorEntity) {
        super.e(hVar, dVar, newcomerFloorEntity);
        if (hVar == null || dVar == null || newcomerFloorEntity == null) {
            return;
        }
        JDJSONArray jDJSONArray = dVar.f10685g;
        if (jDJSONArray != null) {
            newcomerFloorEntity.setRequestAsyncData(new b(jDJSONArray.getJSONObject(0)).getJsonBoolean("isNewCustomerSwitchOn", true));
        }
        l(dVar.a, dVar, newcomerFloorEntity);
        i(dVar.f10685g, newcomerFloorEntity);
    }
}
