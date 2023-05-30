package com.jingdong.app.mall.home.floor.view.view.title;

import android.content.Context;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;

/* loaded from: classes4.dex */
public class HomeTitleNewB extends HomeTitleNew {
    private CategoryTabTitle mCategoryTabTitle;
    private int mCurrentOffset;

    public HomeTitleNewB(Context context) {
        super(context);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew, com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean addCategoryView(CategoryTabTitle categoryTabTitle) {
        if (h.x > 0 && categoryTabTitle.getParent() != this) {
            CategoryTabTitle categoryTabTitle2 = this.mCategoryTabTitle;
            if (categoryTabTitle2 != categoryTabTitle) {
                m.K(categoryTabTitle2);
            }
            this.mCategoryTabTitle = categoryTabTitle;
            m.a(this, categoryTabTitle);
            categoryTabTitle.setLayoutHeight(d.d(84));
        }
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew, com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean afterRefresh() {
        boolean afterRefresh = super.afterRefresh();
        if (h.x <= 0) {
            m.K(this.mCategoryTabTitle);
        } else {
            CategoryTabTitle categoryTabTitle = this.mCategoryTabTitle;
            if (categoryTabTitle != null && categoryTabTitle.getParent() == this) {
                this.mCategoryTabTitle.setLayoutHeight(d.d(84));
            }
        }
        int i2 = h.x > 0 ? 72 : 0;
        if (this.mCurrentOffset != i2 || afterRefresh) {
            this.mCurrentOffset = i2;
            this.mMinSize.C(i2 + 88);
            this.mMaxSize.C(HomeTitleNew.mMaxHeight + i2);
            f.c(this, this.mMinSize);
            f.c(this, this.mMaxSize);
            refreshScrollHeight();
        }
        return afterRefresh;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew
    public void checkTabSelect(boolean z) {
        super.checkTabSelect(z);
        CategoryTabTitle categoryTabTitle = this.mCategoryTabTitle;
        if (categoryTabTitle == null || categoryTabTitle.getParent() != this) {
            return;
        }
        this.mCategoryTabTitle.setAlpha(z ? 1.0f : 0.0f);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getTopHeight() {
        return (this.mCurrentScrollHeight < ((float) d.d(200)) ? this.mMaxSize : this.mMinSize).h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew, com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean needScrollTop() {
        return !isScrollFixed() && this.mCurrentScrollHeight < ((float) d.d(200));
    }
}
