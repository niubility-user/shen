package com.jingdong.common.recommend.ui.homerecommend;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class HomeNTabSliding extends PagerSlidingTabStrip implements HomeTabInterface {
    private SimpleDraweeView bgView;
    private RecommendHomeTabs recommendHomeTabs;
    private int selectedPosition;

    public HomeNTabSliding(Context context) {
        this(context, null);
    }

    private void changeTabBg() {
        String str;
        if (this.recommendHomeTabs == null) {
            return;
        }
        if (isDeepDark()) {
            if (this.selectedPosition == 1) {
                str = this.recommendHomeTabs.rightClickDarkBgImg;
            } else {
                str = this.recommendHomeTabs.leftClickDartBgImg;
            }
        } else if (this.selectedPosition == 1) {
            str = this.recommendHomeTabs.rightClickBgImg;
        } else {
            str = this.recommendHomeTabs.leftClickBgImg;
        }
        downLoadImage(str);
    }

    private void downLoadImage(String str) {
        if (TextUtils.isEmpty(str)) {
            onLoadingFail();
        } else {
            JDImageUtils.displayImage(str, this.bgView, null, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeNTabSliding.2
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    HomeNTabSliding.this.onLoadingFail();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            }, null);
        }
    }

    private int getRightSize() {
        int i2 = RecommendUtils.windowWidthSize;
        if (i2 == 0) {
            return DPIUtil.getWidthByDesignValue750(82);
        }
        return RecommendUtils.getWidthByDesignValue(i2, 82, R2.attr.decimalNumber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadingFail() {
        if (isDeepDark()) {
            if (this.selectedPosition == 1) {
                this.bgView.setImageResource(R.drawable.recom_festival_right_dark);
            } else {
                this.bgView.setImageResource(R.drawable.recom_festival_left_dark);
            }
        } else if (this.selectedPosition == 1) {
            this.bgView.setImageResource(R.drawable.recom_festival_right);
        } else {
            this.bgView.setImageResource(R.drawable.recom_festival_left);
        }
    }

    private void setViewToCurrentHeight(int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i2;
            if (getParent() != null) {
                getParent().requestLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void addFirstIndexView() {
        super.addFirstIndexView();
        this.mRootContainer.addView(this.bgView, 0);
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    protected void addTab(int i2, View view) {
        if (view instanceof TabItemViewInterface) {
            ((TabItemViewInterface) view).onDeepDarkChanged(isDeepDark());
            this.mTabsContainer.addView(view, i2);
            return;
        }
        this.mTabsContainer.addView(view, i2, this.mTabLayoutParams);
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void dealTabDynamicHeight(boolean z, boolean z2) {
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public boolean hasSubTitle() {
        return false;
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onDeepDarkChanged() {
        changeTabBg();
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mTabsContainer.getChildAt(i2);
                if (childAt instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) childAt).onDeepDarkChanged(isDeepDark());
                }
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int size = View.MeasureSpec.getSize(i2);
        SimpleDraweeView simpleDraweeView = this.bgView;
        if (simpleDraweeView == null || simpleDraweeView.getLayoutParams() == null) {
            return;
        }
        this.bgView.getLayoutParams().width = size;
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onTextScaleModeChanged() {
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mTabsContainer.getChildAt(i2);
                if (childAt instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) childAt).onTextScaleModeChanged();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onWidthSizeChange() {
        if (this.mTabsContainer != null) {
            setViewToCurrentHeight(getRightSize());
            int childCount = this.mTabsContainer.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mTabsContainer.getChildAt(i2);
                if (childAt instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) childAt).onWidthSizeChange();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void releaseView() {
        super.releaseView();
        this.selectedPosition = 0;
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void setHomeTab(RecommendHomeTabs recommendHomeTabs) {
        this.recommendHomeTabs = recommendHomeTabs;
        changeTabBg();
        if (this.recommendHomeTabs != null) {
            if (isDeepDark()) {
                JDImageUtils.loadImageToDiskCache(this.recommendHomeTabs.rightClickDarkBgImg, null);
            } else {
                JDImageUtils.loadImageToDiskCache(this.recommendHomeTabs.rightClickBgImg, null);
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void setViewPager(ViewPager viewPager) {
        super.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeNTabSliding.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                HomeNTabSliding.this.setWhichTabClick(i2);
            }
        });
    }

    public void setWhichTabClick(int i2) {
        this.selectedPosition = i2;
        changeTabBg();
    }

    public HomeNTabSliding(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeNTabSliding(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isEnableUnRegistedObserver = false;
        setIndicatorHeight(0);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.bgView = simpleDraweeView;
        simpleDraweeView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.bgView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        this.bgView.setAspectRatio(9.146341f);
    }
}
