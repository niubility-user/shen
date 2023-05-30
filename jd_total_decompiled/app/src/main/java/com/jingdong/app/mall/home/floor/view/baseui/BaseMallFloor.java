package com.jingdong.app.mall.home.floor.view.baseui;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.common.i.r;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon;
import com.jingdong.app.mall.home.floor.view.view.module.ModuleCommonFunc;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.b;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class BaseMallFloor<P extends b> extends BaseMallColorFloor<P> {
    public SparseArray<DarkWhiteBgImageView> mImageViewCache;
    private boolean mIsDisplayInScreen;
    public SparseArray<MallFloorModuleCommon> mModelViewCache;

    public BaseMallFloor(Context context) {
        super(context);
        this.mModelViewCache = new SparseArray<>();
        this.mImageViewCache = new SparseArray<>();
    }

    public void afterInitData(h hVar, @NotNull d dVar) {
    }

    public void beforeInitData(h hVar, @NotNull d dVar, boolean z) {
        this.mPresenter.O(hVar, dVar);
    }

    public void checkAndReportHomeFloorIDExpo() {
        a.i().d(this, this.mPresenter.g(), this.mPresenter.h());
    }

    public void doOtherWithSeparationItemLayout(p.a aVar, p.a.C0288a c0288a, MallFloorModuleCommon mallFloorModuleCommon, int i2) {
    }

    public void floorDisplayInScreen(boolean z) {
        this.mIsDisplayInScreen = z;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public String getFloorId() {
        return this.mPresenter.i();
    }

    public DarkWhiteBgImageView getImgViewByCache(View view, int i2, int i3, int i4) {
        DarkWhiteBgImageView darkWhiteBgImageView = this.mImageViewCache.get(i2);
        if (darkWhiteBgImageView == null) {
            darkWhiteBgImageView = new DarkWhiteBgImageView(getContext());
            this.mImageViewCache.append(i2, darkWhiteBgImageView);
        }
        ViewGroup.LayoutParams layoutParams = darkWhiteBgImageView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i3;
            layoutParams.height = i4;
        } else if (view instanceof RelativeLayout) {
            darkWhiteBgImageView.setLayoutParams(new RelativeLayout.LayoutParams(i3, i4));
        } else if (view instanceof LinearLayout) {
            darkWhiteBgImageView.setLayoutParams(new LinearLayout.LayoutParams(i3, i4));
        }
        return darkWhiteBgImageView;
    }

    public boolean getIsDisplayInScreen() {
        return this.mIsDisplayInScreen;
    }

    public int getRealItemIndex(int i2) {
        return i2;
    }

    public final synchronized void init(d dVar) {
        if (dVar == null) {
            onSetVisible(false);
            return;
        }
        beforeInitData(dVar.mParentModel, dVar, dVar.isCacheData);
        this.mPresenter.t(dVar.mParentModel, dVar);
        afterInitData(dVar.mParentModel, dVar);
    }

    public Object initSeparationFloorViewItem(d dVar, r rVar, f fVar, FloorEntity floorEntity, int i2, int i3, int i4, Object obj) {
        p.a moduleParamsAt = floorEntity.getModuleParamsAt(i4);
        if (moduleParamsAt == null) {
            return null;
        }
        com.jingdong.app.mall.home.a.u.e(moduleParamsAt.Y.p);
        if (unUsePostWaitThread()) {
            initSeparationFloorViewItemOnMainThread(dVar, rVar, fVar, moduleParamsAt, i2, i3, i4, obj);
        } else {
            Class cls = Integer.TYPE;
            postToWaitMainThread("initSeparationFloorViewItemOnMainThread", new Class[]{d.class, r.class, f.class, p.a.class, cls, cls, cls, Object.class}, dVar, rVar, fVar, moduleParamsAt, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), obj);
        }
        return null;
    }

    public Object initSeparationFloorViewItemOnMainThread(d dVar, r rVar, f fVar, p.a aVar, int i2, int i3, int i4, Object obj) {
        return ModuleCommonFunc.initSeparationFloorViewItemOnMainThread(this, dVar, rVar, fVar, aVar, i2, i3, i4, obj);
    }

    public boolean isCache() {
        d dVar = this.mFloorBindElement;
        return dVar != null && dVar.isCacheData;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public final void onCheckMta() {
        floorDisplayInScreen(checkAndReportExpo());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        this.isFloorStatic.set(false);
        checkAndReportExpo();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeRefresh() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        onHomeResume(mallFloorEvent.a(), mallFloorEvent.b());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScroll() {
        this.isFloorStatic.set(false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        this.isFloorStatic.set(true);
        if (i2 == 0 && i3 == 0) {
            return;
        }
        floorDisplayInScreen(checkAndReportExpo());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeSplashClosed(int i2, int i3) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeSplashOpened(int i2, int i3) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeStop() {
        this.isFloorStatic.set(false);
        if (getLayerType() == 1) {
            setLayerType(0, null);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(d dVar) {
        super.onViewBindData(dVar);
        init(dVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void postUrl(String str) {
        postUrl(str, null);
    }

    @Override // android.view.View, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void setBackgroundResource(int i2) {
        if (isMainThread()) {
            setBackgroundResourceOnMainThread(i2);
        } else {
            postToMainThread("setBackgroundResourceOnMainThread", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        }
    }

    protected void setBackgroundResourceOnMainThread(int i2) {
        super.setBackgroundResource(i2);
    }

    public void setBlowByView(View view, View view2) {
        if (view2 == null || view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        }
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(3, view2.getId());
            view.setLayoutParams(layoutParams2);
        }
    }

    public Object setItemPosAndAddItem(View view, f fVar, int i2, int i3, Object obj) {
        return null;
    }

    public void setOnClickJsonListener(View view, f fVar, int i2) {
        l.v(this, view, fVar, i2);
    }

    public void setOnClickListener(View view, f fVar) {
        if (fVar == null) {
            return;
        }
        l.x(this, view, fVar.b0(), fVar.H(), "", fVar);
    }

    public void onHomeResume(int i2, int i3) {
        this.isFloorStatic.set(true);
        floorDisplayInScreen(checkAndReportExpo());
    }

    public void postUrl(String str, HttpGroup.CustomOnAllListener customOnAllListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setType(6000);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        if (customOnAllListener != null) {
            httpSetting.setListener(customOnAllListener);
        }
        com.jingdong.app.mall.home.o.a.f.e(httpSetting);
    }
}
