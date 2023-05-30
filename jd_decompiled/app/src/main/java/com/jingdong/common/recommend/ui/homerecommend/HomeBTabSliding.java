package com.jingdong.common.recommend.ui.homerecommend;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class HomeBTabSliding extends PagerSlidingTabStrip implements HomeTabInterface {
    private int BASE_HEIGHT;
    private int MAX_CHANGE_HEIGHT;
    private GradientDrawable bgDrawable;
    private int currentIndex;
    private float mAnimalProgress;
    private Animator mAnimator;
    private boolean mIsSpread;
    private AtomicBoolean reDraw;
    private RecommendHomeTabs recommendHomeTabs;
    private ColorDrawable tabBg;

    public HomeBTabSliding(Context context) {
        super(context);
        this.mIsSpread = true;
        this.mAnimalProgress = 1.0f;
        this.BASE_HEIGHT = DPIUtil.getWidthByDesignValue750(90);
        this.MAX_CHANGE_HEIGHT = DPIUtil.getWidthByDesignValue750(35);
        this.currentIndex = 0;
        this.reDraw = new AtomicBoolean(false);
        this.isEnableUnRegistedObserver = false;
        setIndicatorHeight(0);
        this.BASE_HEIGHT = RecommendUtils.getWidthByDesignValue(RecommendUtils.windowWidthSize, 90, R2.attr.decimalNumber);
        this.MAX_CHANGE_HEIGHT = RecommendUtils.getWidthByDesignValue(RecommendUtils.windowWidthSize, 35, R2.attr.decimalNumber);
    }

    private void changeTabContainerBgColor() {
        if (isDeepDark()) {
            showDarkViewStyle();
        } else {
            showNormalViewStyle();
        }
    }

    private static GradientDrawable getDrawable(View view, int[] iArr) {
        GradientDrawable gradientDrawable;
        if (Build.VERSION.SDK_INT >= 16) {
            if (view != null && view.getBackground() != null) {
                gradientDrawable = view.getBackground() instanceof GradientDrawable ? (GradientDrawable) view.getBackground() : null;
            } else {
                gradientDrawable = new GradientDrawable();
                gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            }
            if (gradientDrawable != null) {
                gradientDrawable.setColors(iArr);
                return gradientDrawable;
            }
            return gradientDrawable;
        }
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
    }

    private int getIntColor(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            return Color.parseColor(str);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    private boolean isRightTabView(int i2) {
        return i2 < this.mTabsContainer.getChildCount() && (this.mTabsContainer.getChildAt(i2) instanceof TabItemViewInterface);
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

    private void showDarkViewStyle() {
        String str;
        RecommendHomeTabs recommendHomeTabs = this.recommendHomeTabs;
        int[] iArr = null;
        if (recommendHomeTabs != null) {
            if (!TextUtils.isEmpty(recommendHomeTabs.slidingSpreadDarkColor)) {
                try {
                    JDJSONArray parseArray = JDJSON.parseArray(this.recommendHomeTabs.slidingSpreadDarkColor);
                    if (parseArray != null && parseArray.size() > 1) {
                        int[] iArr2 = new int[parseArray.size()];
                        for (int i2 = 0; i2 < parseArray.size(); i2++) {
                            iArr2[i2] = Color.parseColor(parseArray.getString(i2));
                        }
                        iArr = iArr2;
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
            str = this.recommendHomeTabs.slidingUnSpreadDarkColor;
        } else {
            str = "";
        }
        if (iArr == null) {
            int intColor = getIntColor("#FF141212");
            iArr = new int[]{intColor, intColor};
        }
        try {
            GradientDrawable drawable = getDrawable(this, iArr);
            this.bgDrawable = drawable;
            setBackgroundDrawable(drawable);
            ColorDrawable colorDrawable = new ColorDrawable();
            this.tabBg = colorDrawable;
            if (TextUtils.isEmpty(str)) {
                str = "#FF141212";
            }
            colorDrawable.setColor(getIntColor(str));
            this.tabBg.setAlpha(0);
            this.mTabsContainer.setBackgroundDrawable(this.tabBg);
        } catch (Exception e3) {
            if (OKLog.D) {
                e3.printStackTrace();
            }
        }
    }

    private void showNormalViewStyle() {
        String str;
        RecommendHomeTabs recommendHomeTabs = this.recommendHomeTabs;
        int[] iArr = null;
        if (recommendHomeTabs != null) {
            if (!TextUtils.isEmpty(recommendHomeTabs.slidingSpreadColor)) {
                try {
                    JDJSONArray parseArray = JDJSON.parseArray(this.recommendHomeTabs.slidingSpreadColor);
                    if (parseArray != null && parseArray.size() > 1) {
                        int[] iArr2 = new int[parseArray.size()];
                        for (int i2 = 0; i2 < parseArray.size(); i2++) {
                            iArr2[i2] = Color.parseColor(parseArray.getString(i2));
                        }
                        iArr = iArr2;
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
            str = this.recommendHomeTabs.slidingUnSpreadColor;
        } else {
            str = "";
        }
        if (iArr == null) {
            int intColor = getIntColor("#FFF6F6F6");
            iArr = new int[]{intColor, intColor};
        }
        try {
            GradientDrawable drawable = getDrawable(this, iArr);
            this.bgDrawable = drawable;
            setBackgroundDrawable(drawable);
            ColorDrawable colorDrawable = new ColorDrawable();
            this.tabBg = colorDrawable;
            if (TextUtils.isEmpty(str)) {
                str = "#FFFFFFFF";
            }
            colorDrawable.setColor(getIntColor(str));
            this.tabBg.setAlpha(0);
            this.mTabsContainer.setBackgroundDrawable(this.tabBg);
        } catch (Exception e3) {
            if (OKLog.D) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTabAni(int i2) {
        if (isRightTabView(this.currentIndex)) {
            ((TabItemViewInterface) this.mTabsContainer.getChildAt(this.currentIndex)).startTabAni(false, true);
        }
        if (isRightTabView(i2)) {
            ((TabItemViewInterface) this.mTabsContainer.getChildAt(i2)).startTabAni(true, true);
        }
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
        if (this.mIsSpread == z) {
            return;
        }
        this.mIsSpread = z;
        float f2 = z ? 1.0f : 0.0f;
        Animator animator = this.mAnimator;
        if (animator != null && animator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (z2) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "animalProgress", getAnimalProgress(), f2);
            this.mAnimator = ofFloat;
            ofFloat.setDuration(300L);
            this.mAnimator.setInterpolator(new LinearInterpolator());
            this.mAnimator.start();
            return;
        }
        setAnimalProgress(f2);
    }

    public float getAnimalProgress() {
        return this.mAnimalProgress;
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public boolean hasSubTitle() {
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter instanceof HomeRecommendContentLayout.HomeRecommendPageAdapter) {
                return ((HomeRecommendContentLayout.HomeRecommendPageAdapter) adapter).mHasSubtitle;
            }
            return false;
        }
        return false;
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.reDraw.set(true);
        if (getParent() == null || this.recommendHomeTabs == null) {
            return;
        }
        if (this.mIsSpread) {
            setAnimalProgress(1.0f);
        } else {
            setAnimalProgress(0.0f);
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onDeepDarkChanged() {
        changeTabContainerBgColor();
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mTabsContainer.getChildAt(i2) instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) this.mTabsContainer.getChildAt(i2)).onDeepDarkChanged(isDeepDark());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.reDraw.getAndSet(false) && isRightTabView(this.currentIndex)) {
            ((TabItemViewInterface) this.mTabsContainer.getChildAt(this.currentIndex)).startTabAni(true, false);
            if (this.mTabsContainer.getChildAt(this.currentIndex) instanceof RecommendTabBView) {
                this.mTabsContainer.getChildAt(this.currentIndex).requestLayout();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onTextScaleModeChanged() {
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mTabsContainer.getChildAt(i2) instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) this.mTabsContainer.getChildAt(i2)).onTextScaleModeChanged();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onWidthSizeChange() {
        this.BASE_HEIGHT = RecommendUtils.getWidthByDesignValue(RecommendUtils.windowWidthSize, 90, R2.attr.decimalNumber);
        this.MAX_CHANGE_HEIGHT = RecommendUtils.getWidthByDesignValue(RecommendUtils.windowWidthSize, 35, R2.attr.decimalNumber);
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mTabsContainer.getChildAt(i2) instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) this.mTabsContainer.getChildAt(i2)).onWidthSizeChange();
                }
            }
        }
        if (this.mIsSpread) {
            setAnimalProgress(1.0f);
        } else {
            setAnimalProgress(0.0f);
        }
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void releaseView() {
        super.releaseView();
        this.currentIndex = 0;
    }

    public void setAnimalProgress(float f2) {
        try {
            this.mAnimalProgress = f2;
            this.tabBg.setAlpha((int) ((1.0f - f2) * 255.0f));
            if (hasSubTitle()) {
                setViewToCurrentHeight((int) (this.BASE_HEIGHT + (this.MAX_CHANGE_HEIGHT * f2)));
                for (int i2 = 0; i2 < this.mTabsContainer.getChildCount(); i2++) {
                    View childAt = this.mTabsContainer.getChildAt(i2);
                    if (childAt != null && (childAt instanceof TabItemViewInterface)) {
                        ((TabItemViewInterface) childAt).setChangeProgress(f2);
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void setHomeTab(RecommendHomeTabs recommendHomeTabs) {
        this.recommendHomeTabs = recommendHomeTabs;
        changeTabContainerBgColor();
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void setViewPager(ViewPager viewPager) {
        super.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeBTabSliding.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                HomeBTabSliding.this.startTabAni(i2);
                HomeBTabSliding.this.currentIndex = i2;
            }
        });
    }

    public HomeBTabSliding(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsSpread = true;
        this.mAnimalProgress = 1.0f;
        this.BASE_HEIGHT = DPIUtil.getWidthByDesignValue750(90);
        this.MAX_CHANGE_HEIGHT = DPIUtil.getWidthByDesignValue750(35);
        this.currentIndex = 0;
        this.reDraw = new AtomicBoolean(false);
    }

    public HomeBTabSliding(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsSpread = true;
        this.mAnimalProgress = 1.0f;
        this.BASE_HEIGHT = DPIUtil.getWidthByDesignValue750(90);
        this.MAX_CHANGE_HEIGHT = DPIUtil.getWidthByDesignValue750(35);
        this.currentIndex = 0;
        this.reDraw = new AtomicBoolean(false);
    }
}
