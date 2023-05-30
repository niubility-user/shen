package com.jingdong.app.mall.home.floor.view.baseui;

import android.graphics.Bitmap;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.cleanmvp.presenter.IBaseUI;

/* loaded from: classes4.dex */
public interface IMallFloorUI extends IBaseUI {
    void cleanUI();

    int getLayoutTop();

    Object initFloorViewItem(f fVar, int i2, int i3, int i4, Object obj);

    boolean isAttachWindow();

    boolean isCurrentData();

    boolean isFloorDisplay();

    boolean isFloorRecycle();

    void onCheckMta();

    void onFloorInitEnd();

    void onHomePause();

    void onHomeRefresh();

    void onHomeResume(MallFloorEvent mallFloorEvent);

    void onHomeScroll();

    void onHomeScrollStop(int i2, int i3);

    void onHomeSplashClosed(int i2, int i3);

    void onHomeSplashOpened(int i2, int i3);

    void onHomeStop();

    void onLoadingBgComplete(String str, Bitmap bitmap);

    void onLoadingBgFailed(String str, JDFailReason jDFailReason);

    void onParseEnd();

    void onRefreshView();

    void onSetVisible(boolean z);

    void postUrl(String str);

    void postWaitMainThreadQue();

    void setBackgroundResource(int i2);
}
