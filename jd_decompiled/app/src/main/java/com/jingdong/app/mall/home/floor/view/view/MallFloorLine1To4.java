package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.b.g.a;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.Line1To4Title;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.r.f.a.p;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorLine1To4 extends MallFloorLineMore {
    private boolean hasTitle;
    private final f mBgSize;
    private SimpleDraweeView mFloorBg;
    private Line1To4Title mRlTitle;
    private final f mSizeTitle;

    public MallFloorLine1To4(Context context, c cVar) {
        super(context, cVar);
        this.mSizeTitle = new f(-1, 70);
        this.mBgSize = new f(-1, 240);
    }

    private void clipBottom(int i2) {
        e.a(this.mFloorBg, i2);
        e.a(this.mFloorContent, i2);
        e.d(this.mRlTitle, 0);
    }

    private void clipRound(int i2) {
        if (this.hasTitle) {
            e.h(this.mRlTitle, i2);
            e.a(this.mFloorBg, i2);
            e.a(this.mFloorContent, i2);
            return;
        }
        e.d(this.mFloorBg, i2);
        e.d(this.mFloorContent, i2);
    }

    private void clipTop(int i2) {
        if (this.hasTitle) {
            e.h(this.mRlTitle, i2);
            e.d(this.mFloorBg, 0);
            e.d(this.mFloorContent, 0);
            return;
        }
        e.d(this.mRlTitle, 0);
        e.h(this.mFloorBg, i2);
        e.h(this.mFloorContent, i2);
    }

    private void initFloorBg() {
        String jsonString = this.mFloorBindElement.mParentModel.getJsonString("floorBgImg");
        if (TextUtils.isEmpty(jsonString)) {
            SimpleDraweeView simpleDraweeView = this.mFloorBg;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
                return;
            }
            return;
        }
        this.mBgSize.E(0, this.hasTitle ? this.mSizeTitle.e() : 0, 0, 0);
        SimpleDraweeView simpleDraweeView2 = this.mFloorBg;
        if (simpleDraweeView2 == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(((MallFloorLineMore) this).mContext);
            this.mFloorBg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView3 = this.mFloorBg;
            addView(simpleDraweeView3, 0, this.mBgSize.u(simpleDraweeView3));
        } else {
            f.d(simpleDraweeView2, this.mBgSize, true);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.mFloorBg, jsonString);
    }

    private void initFloorContent() {
        int d = d.d(com.jingdong.app.mall.home.floor.view.b.g.f.c0(this.mFloorBindElement) ? 19 : 15);
        ViewGroup.LayoutParams layoutParams = this.mFloorContent.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.height = d.d(240);
        layoutParams2.topMargin = this.hasTitle ? this.mSizeTitle.h() : 0;
        this.mFloorContent.setPadding(d, 0, d, 0);
    }

    private void initFloorTitle() {
        a S = ((p) this.mPresenter).S();
        Line1To4Title line1To4Title = this.mRlTitle;
        if (line1To4Title == null) {
            Line1To4Title line1To4Title2 = new Line1To4Title(((MallFloorLineMore) this).mContext);
            this.mRlTitle = line1To4Title2;
            addView(line1To4Title2, this.mSizeTitle.u(line1To4Title2));
        } else {
            f.d(line1To4Title, this.mSizeTitle, true);
        }
        if (!this.hasTitle) {
            this.mRlTitle.setVisibility(4);
            return;
        }
        this.mRlTitle.setVisibility(0);
        this.mRlTitle.b(S);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void checkFloorClip(float[] fArr) {
        int i2 = isFirstLineFloor() ? (int) fArr[0] : 0;
        int i3 = isLastLineFloor() ? (int) fArr[2] : 0;
        if (i2 != 0 && i3 != 0) {
            clipRound(i2);
        } else if (i2 != 0) {
            clipTop(i2);
        } else if (i3 != 0) {
            clipBottom(i3);
        } else {
            clipRound(0);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore
    protected void initLineItem() {
        BaseLineLayout lineViewByCache;
        List<com.jingdong.app.mall.home.floor.view.linefloor.base.a> T = ((p) this.mPresenter).T();
        int size = T.size();
        int size2 = this.mCacheViewList.size();
        boolean z = size == size2;
        if (!z && size2 > 0) {
            cleanUIOnMainThread();
        }
        for (int i2 = 0; i2 < size; i2++) {
            com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar = T.get(i2);
            com.jingdong.app.mall.home.o.a.f.n(aVar);
            com.jingdong.app.mall.home.floor.view.b.g.f fVar = (com.jingdong.app.mall.home.floor.view.b.g.f) aVar;
            if (z) {
                lineViewByCache = this.mCacheViewList.get(i2);
            } else {
                lineViewByCache = fVar.k().getLineViewByCache(((MallFloorLineMore) this).mContext, this);
                this.mCacheViewList.add(lineViewByCache);
                this.mFloorContent.addView(lineViewByCache);
            }
            lineViewByCache.q(fVar, this, i2, 0);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onRefreshViewOnMainThread() {
        this.hasTitle = ((p) this.mPresenter).S() != null;
        initFloorTitle();
        initFloorBg();
        initFloorContent();
        initLineItem();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        return true;
    }
}
