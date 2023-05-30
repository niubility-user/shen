package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.f.a.t;

/* loaded from: classes4.dex */
public class MallFloorMaiDian extends BaseMallFloor<t> {
    public MallFloorMaiDian(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public t createPresenter() {
        return new t(FloorEntity.class, FloorEngine.class);
    }
}
