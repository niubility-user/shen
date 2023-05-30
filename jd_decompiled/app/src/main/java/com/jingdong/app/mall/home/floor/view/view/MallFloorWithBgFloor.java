package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.i;
import com.jingdong.app.mall.home.r.f.a.a0;
import com.jingdong.app.mall.home.widget.b;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class MallFloorWithBgFloor extends BaseMallFloor<a0> {
    private static final int MAX_CACHE_COUNT = 5;
    private SimpleDraweeView mBgImg;
    private final Map<String, View> mCacheView;

    public MallFloorWithBgFloor(Context context) {
        super(context);
        this.mCacheView = new ConcurrentHashMap();
    }

    private void addBgView(h hVar) {
        String jsonString = hVar == null ? "" : hVar.getJsonString("floorBgImg");
        if (TextUtils.isEmpty(jsonString)) {
            SimpleDraweeView simpleDraweeView = this.mBgImg;
            if (simpleDraweeView != null) {
                removeView(simpleDraweeView);
                return;
            }
            return;
        }
        if (this.mBgImg == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.mBgImg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        this.mBgImg.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        m.b(this, this.mBgImg, 0);
        e.e(this.mBgImg, jsonString, false);
    }

    private void releaseFloor() {
        cleanUI();
        onSetVisible(false);
    }

    protected int initSubFloorView(d dVar, int i2, int i3) {
        if (dVar != null && dVar.mParentModel != null) {
            boolean z = dVar instanceof i;
            String P = z ? ((a0) this.mPresenter).P(dVar, i3) : "";
            View view = z ? this.mCacheView.get(P) : null;
            if (view == null) {
                view = dVar.q.getFloorViewByCache(getContext()).getContentView();
                if (z) {
                    this.mCacheView.put(P, view);
                }
            }
            view.setId(i3);
            if (view instanceof b) {
                ((b) view).onViewBind(dVar);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dVar.mFloorHeight);
            if (i2 > 0) {
                layoutParams.addRule(3, i2);
            }
            view.setLayoutParams(layoutParams);
            addView(view, layoutParams);
            return i3;
        }
        cleanUI();
        return i2;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean isShowItemDivider() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(d dVar) {
        super.onViewBindData(dVar);
        cleanUI();
        ArrayList<d> arrayList = dVar.p;
        if (arrayList != null && arrayList.size() > 0) {
            addBgView(dVar.mParentModel);
            int size = arrayList.size();
            int i2 = R.id.mallfloor_item1;
            if (this.mCacheView.size() > 5) {
                this.mCacheView.clear();
            }
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                d dVar2 = arrayList.get(i5);
                if (dVar2 != null) {
                    if (dVar2 instanceof i) {
                        i3++;
                    }
                    initSubFloorView(dVar2, i4, i2);
                    i4 = i2;
                    i2++;
                }
            }
            if (i3 == 0) {
                releaseFloor();
                return;
            }
            return;
        }
        releaseFloor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useBgMarginColor() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public a0 createPresenter() {
        return new a0();
    }
}
