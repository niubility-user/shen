package com.jd.lib.productdetail.mainimage.bigimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.lib.productdetail.mainimage.R;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isReadyForPullEnd() {
        /*
            r4 = this;
            android.view.View r0 = r4.getRefreshableView()
            androidx.viewpager.widget.ViewPager r0 = (androidx.viewpager.widget.ViewPager) r0
            androidx.viewpager.widget.PagerAdapter r1 = r0.getAdapter()
            r2 = 0
            if (r1 == 0) goto L43
            int r0 = r0.getCurrentItem()
            int r1 = r1.getCount()
            r3 = 1
            int r1 = r1 - r3
            if (r0 != r1) goto L43
            android.view.View r0 = r4.getRefreshableView()
            androidx.viewpager.widget.ViewPager r0 = (androidx.viewpager.widget.ViewPager) r0
            androidx.viewpager.widget.PagerAdapter r1 = r0.getAdapter()
            boolean r1 = r1 instanceof com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity.d
            if (r1 == 0) goto L3f
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity$d r0 = (com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity.d) r0
            com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity r0 = com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity.this
            com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity$ImageFragment r0 = r0.P
            if (r0 == 0) goto L36
            com.jd.lib.productdetail.mainimage.utils.PdMCooTouchImageView r0 = r0.r
            goto L37
        L36:
            r0 = 0
        L37:
            if (r0 == 0) goto L3f
            r1 = 5
            boolean r0 = r0.canScrollHorizontally(r1)
            goto L40
        L3f:
            r0 = 0
        L40:
            if (r0 != 0) goto L43
            return r3
        L43:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.bigimage.PdMPullToRefreshViewPager.isReadyForPullEnd():boolean");
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
