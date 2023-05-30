package com.jingdong.common.widget.custom.pageload;

import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.widget.custom.CustomBasePageLoader;
import com.jingdong.common.widget.custom.CustomTopButton;

/* loaded from: classes12.dex */
public interface ITBasePagingLoadingManager {
    CustomBasePageLoader getNextPagerLoader();

    void loadPageOne();

    void setItemDecoration(RecyclerView.ItemDecoration itemDecoration, int i2);

    void setTopBtnListener(CustomTopButton.ITopButtonListener iTopButtonListener);
}
