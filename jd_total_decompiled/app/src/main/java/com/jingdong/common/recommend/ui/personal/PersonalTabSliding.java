package com.jingdong.common.recommend.ui.personal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class PersonalTabSliding extends PagerSlidingTabStrip implements HomeTabInterface {
    private int currentIndex;
    private boolean isSpread;
    private AtomicBoolean reDraw;
    private String spreadColor;
    private String spreadDarkColor;
    private String unSpreadColor;
    private String unSpreadDarkColor;

    public PersonalTabSliding(Context context) {
        super(context);
        this.reDraw = new AtomicBoolean(false);
        this.currentIndex = 0;
        this.isSpread = true;
        this.isEnableUnRegistedObserver = false;
        this.mTabsContainer.setPadding(DPIUtil.dip2px(3.0f), 0, DPIUtil.dip2px(3.0f), 0);
        setIndicatorHeight(0);
    }

    private void changeTabContainerBgColor() {
        if (isDeepDark()) {
            if (this.isSpread) {
                setBackgroundColor(getIntColor(TextUtils.isEmpty(this.spreadDarkColor) ? "#FF141212" : this.spreadDarkColor));
            } else {
                setBackgroundColor(getIntColor(TextUtils.isEmpty(this.unSpreadDarkColor) ? "#FF141212" : this.unSpreadDarkColor));
            }
        } else if (this.isSpread) {
            setBackgroundColor(getIntColor(TextUtils.isEmpty(this.spreadColor) ? "#FFF6F6F6" : this.spreadColor));
        } else {
            setBackgroundColor(getIntColor(TextUtils.isEmpty(this.unSpreadColor) ? "#FFFFFFFF" : this.unSpreadColor));
        }
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
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public boolean hasSubTitle() {
        return false;
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.reDraw.set(true);
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
            if (this.mTabsContainer.getChildAt(this.currentIndex) instanceof PersonalTabBView) {
                this.mTabsContainer.getChildAt(this.currentIndex).requestLayout();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onTextScaleModeChanged() {
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            JDMtaUtils.sendCommonData(getContext(), RecommendMtaUtils.MyJD_GeneTabSlide, "", "", RecommendMtaUtils.MyJD_Page_Name, "", "", "", RecommendMtaUtils.MyJD_PageId);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void onWidthSizeChange() {
        LinearLayout linearLayout = this.mTabsContainer;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.mTabsContainer.getChildAt(i2) instanceof TabItemViewInterface) {
                    ((TabItemViewInterface) this.mTabsContainer.getChildAt(i2)).onWidthSizeChange();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void releaseView() {
        super.releaseView();
        this.currentIndex = 0;
    }

    @Override // com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface
    public void setHomeTab(RecommendHomeTabs recommendHomeTabs) {
        if (recommendHomeTabs != null) {
            this.spreadColor = recommendHomeTabs.slidingSpreadColor;
            this.unSpreadColor = recommendHomeTabs.slidingUnSpreadColor;
            this.spreadDarkColor = recommendHomeTabs.slidingSpreadDarkColor;
            this.unSpreadDarkColor = recommendHomeTabs.slidingUnSpreadDarkColor;
        } else {
            this.spreadColor = "";
            this.unSpreadColor = "";
            this.spreadDarkColor = "";
            this.unSpreadDarkColor = "";
        }
        changeTabContainerBgColor();
    }

    public void setSpread(boolean z) {
        this.isSpread = z;
        changeTabContainerBgColor();
    }

    @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip
    public void setViewPager(ViewPager viewPager) {
        super.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabSliding.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                PersonalTabSliding.this.startTabAni(i2);
                PersonalTabSliding.this.currentIndex = i2;
            }
        });
    }

    public PersonalTabSliding(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.reDraw = new AtomicBoolean(false);
        this.currentIndex = 0;
        this.isSpread = true;
    }

    public PersonalTabSliding(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.reDraw = new AtomicBoolean(false);
        this.currentIndex = 0;
        this.isSpread = true;
    }
}
