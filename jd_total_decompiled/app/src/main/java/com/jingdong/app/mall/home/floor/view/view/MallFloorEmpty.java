package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.f.a.k;

/* loaded from: classes4.dex */
public class MallFloorEmpty extends BaseMallFloor<k> implements IMallFloorUI {
    public MallFloorEmpty(Context context) {
        super(context);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onParseEnd() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(d dVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public k createPresenter() {
        return new k();
    }
}
