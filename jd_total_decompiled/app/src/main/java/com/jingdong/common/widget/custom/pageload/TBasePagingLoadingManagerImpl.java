package com.jingdong.common.widget.custom.pageload;

import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.listui.view.HeadTipView;
import com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView;
import com.jingdong.common.widget.custom.CustomTopButton;
import com.jingdong.common.widget.custom.pageload.entity.IncrementBaseTEntity;

/* loaded from: classes12.dex */
public abstract class TBasePagingLoadingManagerImpl<E extends IncrementBaseTEntity> implements ITBasePagingLoadingManager {
    final Class<E> eClass;
    HeadTipView headTipView;
    public CustomPageLoaderRecyclerView pageLoaderRecyclerView;

    public TBasePagingLoadingManagerImpl(Class<E> cls) {
        this.eClass = cls;
    }

    @Override // com.jingdong.common.widget.custom.pageload.ITBasePagingLoadingManager
    public void loadPageOne() {
        if (getNextPagerLoader() != null) {
            getNextPagerLoader().showPageOne();
        }
    }

    @Override // com.jingdong.common.widget.custom.pageload.ITBasePagingLoadingManager
    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration, int i2) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView == null) {
            return;
        }
        customPageLoaderRecyclerView.getRefreshableView().addItemDecoration(itemDecoration, i2);
    }

    @Override // com.jingdong.common.widget.custom.pageload.ITBasePagingLoadingManager
    public void setTopBtnListener(CustomTopButton.ITopButtonListener iTopButtonListener) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView != null) {
            customPageLoaderRecyclerView.setTopBtnListener(iTopButtonListener);
        }
    }
}
