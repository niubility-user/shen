package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import com.jingdong.app.util.image.assist.JDFailReason;

/* loaded from: classes4.dex */
public class MallFloorIconElder extends MallFloorIcon {
    public MallFloorIconElder(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onLoadingBgCompleteOnMainThread(String str, Bitmap bitmap) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onLoadingBgFailedOnMainThread(String str, JDFailReason jDFailReason) {
    }
}
