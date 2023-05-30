package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.b0;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class MallFloorWithSubFloor extends BaseMallFloor<b0> {
    private ConcurrentHashMap<String, View> mFloorMap;

    public MallFloorWithSubFloor(Context context) {
        super(context);
        this.mFloorMap = new ConcurrentHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void floorDisplayInScreen(boolean z) {
        super.floorDisplayInScreen(z);
    }

    public int initSubFloorView(h hVar, d dVar, d dVar2, int i2, int i3) {
        setContentDescription(this.mContext.getString(R.string.home_obstacle_free));
        if (isMainThread()) {
            initSubFloorViewOnMainThread(hVar, dVar, dVar2, i2, i3);
        } else {
            Class cls = Integer.TYPE;
            postToMainThread("initSubFloorViewOnMainThread", new Class[]{h.class, d.class, d.class, cls, cls, Boolean.TYPE}, hVar, dVar, dVar2, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        return 0;
    }

    protected int initSubFloorViewOnMainThread(h hVar, d dVar, d dVar2, int i2, int i3) {
        if (dVar2 != null && dVar2.mParentModel != null) {
            String P = ((b0) this.mPresenter).P(dVar2);
            View view = this.mFloorMap.get(P);
            if (view == null) {
                view = dVar2.q.getFloorViewByCache(getContext()).getContentView();
                this.mFloorMap.put(P, view);
            }
            m.b(this, view, -1);
            if (view instanceof BaseMallFloor) {
                BaseMallFloor baseMallFloor = view;
                baseMallFloor.setFloorBindElements(dVar2);
                baseMallFloor.init(dVar2);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) m.d(this, view, -1, dVar2.mFloorHeight);
            if (layoutParams != null) {
                layoutParams.addRule(3, i2);
                view.setLayoutParams(layoutParams);
            }
            checkCircularDependencies(findViewById(i2), i3);
            view.setId(i3);
            return i3;
        }
        cleanUI();
        return i2;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public b0 createPresenter() {
        return new b0();
    }
}
