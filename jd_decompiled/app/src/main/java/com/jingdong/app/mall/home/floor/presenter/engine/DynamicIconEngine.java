package com.jingdong.app.mall.home.floor.presenter.engine;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;

/* loaded from: classes4.dex */
public class DynamicIconEngine extends FloorEngine<DynamicIconEntity> {
    private void k(JDJSONObject jDJSONObject) {
        String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "poz", "");
        if (TextUtils.isEmpty(jSONStringWithDefault)) {
            return;
        }
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("HOMEPOZ", jSONStringWithDefault);
        edit.apply();
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, DynamicIconEntity dynamicIconEntity) {
        if (hVar == null || dynamicIconEntity == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.h.N().l0(hVar.Y, false);
        d(hVar, dynamicIconEntity);
        super.e(hVar, dVar, dynamicIconEntity);
        JDJSONObject a = hVar.a();
        if (a == null) {
            return;
        }
        dynamicIconEntity.setFloorId("dynamicIcon");
        k(a);
        dynamicIconEntity.parseConfigData(a);
        dynamicIconEntity.parseViewData(hVar, a);
        dynamicIconEntity.parseIconData(hVar, a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void f(h hVar, d dVar, DynamicIconEntity dynamicIconEntity, int i2) {
        dynamicIconEntity.setItemDividerWidth(0);
        dynamicIconEntity.setItemDividerColor(0);
        dynamicIconEntity.setLayoutLeftRightMarginBy750Design(0);
    }
}
