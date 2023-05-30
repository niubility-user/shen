package com.jd.lib.productdetail.mainimage.bigimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.utils.PdMCooTouchImageView;

/* loaded from: classes15.dex */
public class PdMPullToRefreshViewPager extends PullToRefreshBase<PdMDropDownViewPager> {
    public PdMPullToRefreshViewPager(Context context) {
        super(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        return new PdMBigImageMoreView(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public PdMDropDownViewPager createRefreshableView(Context context, AttributeSet attributeSet) {
        PdMDropDownViewPager pdMDropDownViewPager = new PdMDropDownViewPager(context, attributeSet);
        pdMDropDownViewPager.setId(R.id.lib_pd_big_image_ptrviewpager_bundle);
        return pdMDropDownViewPager;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042 A[RETURN] */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean isReadyForPullEnd() {
        boolean z;
        PdMDropDownViewPager refreshableView = getRefreshableView();
        PagerAdapter adapter = refreshableView.getAdapter();
        if (adapter != null && refreshableView.getCurrentItem() == adapter.getCount() - 1) {
            PdMDropDownViewPager refreshableView2 = getRefreshableView();
            if (refreshableView2.getAdapter() instanceof PdBigImageActivity.d) {
                PdBigImageActivity.ImageFragment imageFragment = PdBigImageActivity.this.P;
                PdMCooTouchImageView pdMCooTouchImageView = imageFragment != null ? imageFragment.r : null;
                if (pdMCooTouchImageView != null) {
                    z = pdMCooTouchImageView.canScrollHorizontally(5);
                    if (z) {
                        return true;
                    }
                }
            }
            z = false;
            if (z) {
            }
        }
        return false;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public boolean isReadyForPullStart() {
        PdMDropDownViewPager refreshableView = getRefreshableView();
        return refreshableView.getAdapter() != null && refreshableView.getCurrentItem() == 0;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public void onRefreshing(boolean z) {
        super.onRefreshing(false);
    }

    public PdMPullToRefreshViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
