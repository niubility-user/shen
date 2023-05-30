package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.f.a.j;

/* loaded from: classes4.dex */
public class MallFloorCategory extends BaseMallFloor<j> {
    private final f mTabSize;
    private final CategoryTabTitle tabTitle;

    public MallFloorCategory(Context context) {
        super(context);
        CategoryTabTitle categoryTabTitle = new CategoryTabTitle(context, (j) this.mPresenter);
        this.tabTitle = categoryTabTitle;
        f fVar = new f(-1, -1);
        this.mTabSize = fVar;
        RelativeLayout.LayoutParams u = fVar.u(categoryTabTitle);
        u.addRule(12);
        addView(categoryTabTitle, u);
    }

    public static void reportExpoData() {
        CategoryTabTitle.reportExpoData();
    }

    public void addAsyncTab(CategoryEntity.CaItem caItem) {
        this.tabTitle.addAsyncTab(caItem);
    }

    public void addContentView() {
        CategoryTabTitle categoryTabTitle = this.tabTitle;
        if (categoryTabTitle == null) {
            return;
        }
        categoryTabTitle.setAlpha(1.0f);
        IHomeTitle titleB = HomeTitleFactory.getTitleB(this.mContext);
        if (titleB == null || !titleB.addCategoryView(this.tabTitle)) {
            m.a(this, this.tabTitle);
            this.tabTitle.setLayoutHeight(this.mTabSize.h());
        }
    }

    public void changeTabName() {
        this.tabTitle.changeTabName();
    }

    public void checkExpoItem() {
        this.tabTitle.checkExpoItem();
    }

    public CategoryTabTitle getCategoryTabTitle() {
        return this.tabTitle;
    }

    public CategoryEntity.CaItem getFirstItem() {
        return this.tabTitle.getFirstItem();
    }

    public void onBackPressed() {
        this.tabTitle.onBackPressed();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        ((j) this.mPresenter).a0(true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        if (m.I(this, i2, i3, true)) {
            ((j) this.mPresenter).a0(true);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(d dVar) {
        super.onViewBindData(dVar);
        a.t.c();
        addContentView();
        this.tabTitle.onViewBindData(dVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public j createPresenter() {
        return new j();
    }
}
