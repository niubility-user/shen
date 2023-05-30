package com.jingdong.app.mall.product;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class PullToRefreshViewPager extends PullToRefreshBase<ViewPager> {
    public PullToRefreshViewPager(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        return new CommentPhotoMoreView(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        ViewPager refreshableView = getRefreshableView();
        PagerAdapter adapter = refreshableView.getAdapter();
        return adapter != null && refreshableView.getCurrentItem() == adapter.getCount() - 1;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        ViewPager refreshableView = getRefreshableView();
        return refreshableView.getAdapter() != null && refreshableView.getCurrentItem() == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public void onRefreshing(boolean z) {
        super.onRefreshing(false);
    }

    public PullToRefreshViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public ViewPager createRefreshableView(Context context, AttributeSet attributeSet) {
        ViewPager viewPager = new ViewPager(context, attributeSet);
        viewPager.setId(R.id.comment_photo_ptrviewpager);
        return viewPager;
    }
}
