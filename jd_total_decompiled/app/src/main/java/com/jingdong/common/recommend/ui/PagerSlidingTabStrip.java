package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes6.dex */
public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] ANDROID_ATTRS = {16842806, 16842965, 16842966, 16842968};
    public static final int DEF_VALUE_TAB_TEXT_ALPHA = 150;
    private static final int PADDING_INDEX = 1;
    private static final int PADDING_LEFT_INDEX = 2;
    private static final int PADDING_RIGHT_INDEX = 3;
    private static final int TEXT_COLOR_PRIMARY = 0;
    private boolean isCustomTabs;
    protected boolean isEnableUnRegistedObserver;
    private boolean isExpandTabs;
    private boolean isPaddingMiddle;
    private boolean isTabTextAllCaps;
    public boolean isViewPagerSmoothScroll;
    private final PagerAdapterObserver mAdapterObserver;
    private int mCurrentPosition;
    private float mCurrentPositionOffset;
    public ViewPager.OnPageChangeListener mDelegatePageListener;
    private int mDividerColor;
    private int mDividerPadding;
    private Paint mDividerPaint;
    private int mDividerWidth;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private int mIndicatorPadding;
    private int mLastScrollX;
    private int mPaddingLeft;
    private int mPaddingRight;
    private final PageListener mPageListener;
    protected ViewPager mPager;
    private RecommendConfig mRecommendConfig;
    private Paint mRectPaint;
    protected FrameLayout mRootContainer;
    private int mScrollOffset;
    private int mSelectColor;
    private int mTabBackgroundResId;
    protected int mTabCount;
    protected LinearLayout.LayoutParams mTabLayoutParams;
    private int mTabPadding;
    private OnTabReselectedListener mTabReselectedListener;
    private ColorStateList mTabTextColor;
    private int mTabTextSize;
    private Typeface mTabTextTypeface;
    private int mTabTextTypefaceStyle;
    protected LinearLayout mTabsContainer;
    private int mTabunSelectTextSize;
    private int mUnderlineColor;
    private int mUnderlineHeight;
    private int maxScroll;

    /* loaded from: classes6.dex */
    public interface CustomTabProvider {
        View getCustomTabView(ViewGroup viewGroup, int i2);

        void tabSelected(View view);

        void tabUnselected(View view);
    }

    /* loaded from: classes6.dex */
    public interface OnTabReselectedListener {
        void onTabReselected(int i2);

        void onTabSelected(int i2);
    }

    /* loaded from: classes6.dex */
    private class PageListener implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (i2 == 0) {
                PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
                pagerSlidingTabStrip.scrollToChild(pagerSlidingTabStrip.mPager.getCurrentItem(), 0);
            }
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSlidingTabStrip.this.mDelegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            PagerSlidingTabStrip.this.mCurrentPosition = i2;
            PagerSlidingTabStrip.this.mCurrentPositionOffset = f2;
            PagerSlidingTabStrip.this.scrollToChild(i2, PagerSlidingTabStrip.this.mTabCount > 0 ? (int) (r0.mTabsContainer.getChildAt(i2).getWidth() * f2) : 0);
            PagerSlidingTabStrip.this.invalidate();
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSlidingTabStrip.this.mDelegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrolled(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            PagerSlidingTabStrip.this.updateSelection(i2);
            PagerSlidingTabStrip.this.select(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(i2));
            if (i2 > 0) {
                PagerSlidingTabStrip.this.unSelect(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(i2 - 1));
            }
            if (i2 < PagerSlidingTabStrip.this.mPager.getAdapter().getCount() - 1) {
                PagerSlidingTabStrip.this.unSelect(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(i2 + 1));
            }
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSlidingTabStrip.this.mDelegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(i2);
            }
        }
    }

    /* loaded from: classes6.dex */
    private class PagerAdapterObserver extends DataSetObserver {
        private boolean attached;

        private PagerAdapterObserver() {
            this.attached = false;
        }

        boolean isAttached() {
            return this.attached;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerSlidingTabStrip.this.notifyDataSetChanged();
        }

        void setAttached(boolean z) {
            this.attached = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.jingdong.common.recommend.ui.PagerSlidingTabStrip.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        int currentPosition;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.currentPosition);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPosition = parcel.readInt();
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    private ColorStateList createColorStateList(int i2) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i2});
    }

    private void initTab(final int i2, CharSequence charSequence, View view) {
        TextView textView = (TextView) view.findViewById(R.id.psts_tab_title);
        if (textView != null && charSequence != null) {
            textView.setText(charSequence);
        }
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.PagerSlidingTabStrip.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PagerSlidingTabStrip.this.onTabClick(i2);
            }
        });
        addTab(i2, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToChild(int i2, int i3) {
        if (this.mTabCount == 0) {
            return;
        }
        int left = this.mTabsContainer.getChildAt(i2).getLeft() + i3;
        if (i2 > 0 || i3 > 0) {
            int i4 = left - this.mScrollOffset;
            Pair<Float, Float> indicatorCoordinates = getIndicatorCoordinates();
            left = (int) (i4 + ((indicatorCoordinates.second.floatValue() - indicatorCoordinates.first.floatValue()) / 2.0f));
        }
        if (i2 == 0) {
            left = 0;
        }
        if (left != this.mLastScrollX) {
            this.mLastScrollX = left;
            scrollTo(left, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void select(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.psts_tab_title);
            if (textView != null) {
                textView.setSelected(true);
                this.mTabTextTypefaceStyle = 1;
                textView.setTextSize(0, this.mTabTextSize);
                TextPaint paint = textView.getPaint();
                if (paint != null) {
                    paint.setFakeBoldText(true);
                }
            }
            if (this.isCustomTabs) {
                ((CustomTabProvider) this.mPager.getAdapter()).tabSelected(view);
            }
        }
    }

    private void setTabsContainerParentViewPaddings() {
        int i2 = this.mIndicatorHeight;
        int i3 = this.mUnderlineHeight;
        if (i2 < i3) {
            i2 = i3;
        }
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unSelect(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.psts_tab_title);
            if (textView != null) {
                textView.setSelected(false);
                this.mTabTextTypefaceStyle = 0;
                textView.setTypeface(this.mTabTextTypeface, 0);
                textView.setTextSize(0, this.mTabunSelectTextSize);
                TextPaint paint = textView.getPaint();
                if (paint != null) {
                    paint.setFakeBoldText(false);
                }
            }
            if (this.isCustomTabs) {
                ((CustomTabProvider) this.mPager.getAdapter()).tabUnselected(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelection(int i2) {
        int i3 = 0;
        while (i3 < this.mTabCount) {
            View childAt = this.mTabsContainer.getChildAt(i3);
            if (i3 == i2) {
                select(childAt);
            } else {
                unSelect(childAt);
            }
            i3++;
        }
    }

    private void updateTabStyles() {
        for (int i2 = 0; i2 < this.mTabCount; i2++) {
            View childAt = this.mTabsContainer.getChildAt(i2);
            childAt.setBackgroundResource(this.mTabBackgroundResId);
            childAt.setPadding(this.mTabPadding, childAt.getPaddingTop(), this.mTabPadding, childAt.getPaddingBottom());
            TextView textView = (TextView) childAt.findViewById(R.id.psts_tab_title);
            if (textView != null) {
                textView.setTextColor(this.mTabTextColor);
                textView.setTypeface(this.mTabTextTypeface, this.mTabTextTypefaceStyle);
                if (textView.isSelected()) {
                    textView.setTextSize(0, this.mTabunSelectTextSize);
                } else {
                    textView.setTextSize(0, this.mTabTextSize);
                }
                if (this.isTabTextAllCaps) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addFirstIndexView() {
    }

    protected void addTab(int i2, View view) {
        this.mTabsContainer.addView(view, i2, this.mTabLayoutParams);
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    public float getCurrentPositionOffset() {
        return this.mCurrentPositionOffset;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public Pair<Float, Float> getIndicatorCoordinates() {
        int i2;
        View childAt = this.mTabsContainer.getChildAt(this.mCurrentPosition);
        float left = childAt.getLeft();
        float right = childAt.getRight();
        if (this.mCurrentPositionOffset > 0.0f && (i2 = this.mCurrentPosition) < this.mTabCount - 1) {
            View childAt2 = this.mTabsContainer.getChildAt(i2 + 1);
            float f2 = this.mCurrentPositionOffset;
            left = (childAt2.getLeft() * f2) + ((1.0f - f2) * left);
            right = (childAt2.getRight() * f2) + ((1.0f - f2) * right);
        }
        return new Pair<>(Float.valueOf(left), Float.valueOf(right));
    }

    public int getIndicatorHeight() {
        return this.mIndicatorHeight;
    }

    public int getMaxExpoPosition() {
        int measuredWidth = getMeasuredWidth() + this.maxScroll;
        if (this.mTabsContainer != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.mTabsContainer.getChildCount(); i3++) {
                if (this.mTabsContainer.getChildAt(0) != null && (i2 = i2 + this.mTabsContainer.getChildAt(0).getWidth()) >= measuredWidth) {
                    return i3;
                }
            }
            return 0;
        }
        return 0;
    }

    public int getScrollOffset() {
        return this.mScrollOffset;
    }

    public boolean getShouldExpand() {
        return this.isExpandTabs;
    }

    public int getTabBackground() {
        return this.mTabBackgroundResId;
    }

    public int getTabCount() {
        return this.mTabCount;
    }

    public int getTabPaddingLeftRight() {
        return this.mTabPadding;
    }

    public LinearLayout getTabsContainer() {
        return this.mTabsContainer;
    }

    public ColorStateList getTextColor() {
        return this.mTabTextColor;
    }

    public int getTextSize() {
        return this.mTabTextSize;
    }

    public int getUnderlineColor() {
        return this.mUnderlineColor;
    }

    public int getUnderlineHeight() {
        return this.mUnderlineHeight;
    }

    public int getmSelectColor() {
        return this.mSelectColor;
    }

    public int getmTabunSelectTextSize() {
        return this.mTabunSelectTextSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDeepDark() {
        RecommendConfig recommendConfig = this.mRecommendConfig;
        return recommendConfig != null && recommendConfig.isDarkEnable();
    }

    public boolean isTextAllCaps() {
        return this.isTabTextAllCaps;
    }

    public void notifyDataSetChanged() {
        View inflate;
        this.mTabsContainer.removeAllViews();
        this.mRootContainer.removeAllViews();
        addFirstIndexView();
        this.mRootContainer.addView(this.mTabsContainer);
        this.mTabCount = this.mPager.getAdapter().getCount();
        for (int i2 = 0; i2 < this.mTabCount; i2++) {
            if (this.isCustomTabs) {
                inflate = ((CustomTabProvider) this.mPager.getAdapter()).getCustomTabView(this, i2);
            } else {
                inflate = LayoutInflater.from(getContext()).inflate(R.layout.psts_tab, (ViewGroup) this, false);
            }
            initTab(i2, this.mPager.getAdapter().getPageTitle(i2), inflate);
        }
        updateTabStyles();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isEnableUnRegistedObserver || this.mPager == null || this.mAdapterObserver.isAttached()) {
            return;
        }
        this.mPager.getAdapter().registerDataSetObserver(this.mAdapterObserver);
        this.mAdapterObserver.setAttached(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.isEnableUnRegistedObserver && this.mPager != null && this.mAdapterObserver.isAttached()) {
            this.mPager.getAdapter().unregisterDataSetObserver(this.mAdapterObserver);
            this.mAdapterObserver.setAttached(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode() || this.mTabCount == 0) {
            return;
        }
        int height = getHeight();
        int i2 = this.mDividerWidth;
        if (i2 > 0) {
            this.mDividerPaint.setStrokeWidth(i2);
            this.mDividerPaint.setColor(this.mDividerColor);
            for (int i3 = 0; i3 < this.mTabCount - 1; i3++) {
                View childAt = this.mTabsContainer.getChildAt(i3);
                canvas.drawLine(childAt.getRight(), this.mDividerPadding, childAt.getRight(), height - this.mDividerPadding, this.mDividerPaint);
            }
        }
        if (this.mUnderlineHeight > 0) {
            this.mRectPaint.setColor(this.mUnderlineColor);
            canvas.drawRect(this.mPaddingLeft, height - this.mUnderlineHeight, this.mTabsContainer.getWidth() + this.mPaddingRight, height, this.mRectPaint);
        }
        if (this.mIndicatorHeight > 0) {
            this.mRectPaint.setColor(this.mIndicatorColor);
            Pair<Float, Float> indicatorCoordinates = getIndicatorCoordinates();
            canvas.drawRect(indicatorCoordinates.first.floatValue() + this.mIndicatorPadding, height - this.mIndicatorHeight, indicatorCoordinates.second.floatValue() - this.mIndicatorPadding, height, this.mRectPaint);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int width;
        if (this.isPaddingMiddle && this.mTabsContainer.getChildCount() > 0) {
            int width2 = (getWidth() / 2) - (this.mTabsContainer.getChildAt(0).getMeasuredWidth() / 2);
            this.mPaddingRight = width2;
            this.mPaddingLeft = width2;
        }
        boolean z2 = this.isPaddingMiddle;
        if (z2 || this.mPaddingLeft > 0 || this.mPaddingRight > 0) {
            if (z2) {
                width = getWidth();
            } else {
                width = (getWidth() - this.mPaddingLeft) - this.mPaddingRight;
            }
            this.mTabsContainer.setMinimumWidth(width);
            setClipToPadding(false);
        }
        setPadding(this.mPaddingLeft, getPaddingTop(), this.mPaddingRight, getPaddingBottom());
        this.mScrollOffset = (getWidth() / 2) - this.mPaddingLeft;
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            this.mCurrentPosition = viewPager.getCurrentItem();
        }
        this.mCurrentPositionOffset = 0.0f;
        scrollToChild(this.mCurrentPosition, 0);
        updateSelection(this.mCurrentPosition);
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i2 = savedState.currentPosition;
        this.mCurrentPosition = i2;
        if (i2 != 0 && this.mTabsContainer.getChildCount() > 0) {
            unSelect(this.mTabsContainer.getChildAt(0));
            select(this.mTabsContainer.getChildAt(this.mCurrentPosition));
        }
        requestLayout();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.mCurrentPosition;
        return savedState;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (i2 > this.maxScroll) {
            this.maxScroll = i2;
        }
    }

    protected void onTabClick(int i2) {
        OnTabReselectedListener onTabReselectedListener = this.mTabReselectedListener;
        if (onTabReselectedListener != null) {
            onTabReselectedListener.onTabSelected(i2);
        }
        if (this.mPager.getCurrentItem() != i2) {
            unSelect(this.mTabsContainer.getChildAt(this.mPager.getCurrentItem()));
            boolean z = this.isViewPagerSmoothScroll;
            if (z) {
                this.mPager.setCurrentItem(i2);
                return;
            } else {
                this.mPager.setCurrentItem(i2, z);
                return;
            }
        }
        OnTabReselectedListener onTabReselectedListener2 = this.mTabReselectedListener;
        if (onTabReselectedListener2 != null) {
            onTabReselectedListener2.onTabReselected(i2);
        }
    }

    public void releaseView() {
        this.maxScroll = 0;
        scrollTo(0, 0);
    }

    public void resetScroll() {
        this.maxScroll = getScrollX();
    }

    public void setAllCaps(boolean z) {
        this.isTabTextAllCaps = z;
    }

    public void setDividerColor(int i2) {
        this.mDividerColor = i2;
        invalidate();
    }

    public void setDividerColorResource(int i2) {
        this.mDividerColor = ContextCompat.getColor(getContext(), i2);
        invalidate();
    }

    public void setDividerPadding(int i2) {
        this.mDividerPadding = i2;
        invalidate();
    }

    public void setDividerWidth(int i2) {
        this.mDividerWidth = i2;
        invalidate();
    }

    public void setIndicatorColor(int i2) {
        this.mIndicatorColor = i2;
        invalidate();
    }

    public void setIndicatorColorResource(int i2) {
        this.mIndicatorColor = ContextCompat.getColor(getContext(), i2);
        invalidate();
    }

    public void setIndicatorHeight(int i2) {
        this.mIndicatorHeight = i2;
        setTabsContainerParentViewPaddings();
        invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mDelegatePageListener = onPageChangeListener;
    }

    public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener) {
        this.mTabReselectedListener = onTabReselectedListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRecommendConfig(RecommendConfig recommendConfig) {
        this.mRecommendConfig = recommendConfig;
    }

    public void setScrollOffset(int i2) {
        this.mScrollOffset = i2;
        invalidate();
    }

    public void setShouldExpand(boolean z) {
        this.isExpandTabs = z;
        if (this.mPager != null) {
            requestLayout();
        }
    }

    public void setTabBackground(int i2) {
        this.mTabBackgroundResId = i2;
    }

    public void setTabPaddingLeftRight(int i2) {
        this.mTabPadding = i2;
        updateTabStyles();
    }

    public void setTextColor(int i2) {
        setTextColor(createColorStateList(i2));
    }

    public void setTextColorResource(int i2) {
        setTextColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setTextColorStateListResource(int i2) {
        setTextColor(ContextCompat.getColorStateList(getContext(), i2));
    }

    public void setTextSize(int i2) {
        this.mTabTextSize = i2;
        updateTabStyles();
    }

    public void setTypeface(Typeface typeface, int i2) {
        this.mTabTextTypeface = typeface;
        this.mTabTextTypefaceStyle = i2;
        updateTabStyles();
    }

    public void setUnderlineColor(int i2) {
        this.mUnderlineColor = i2;
        invalidate();
    }

    public void setUnderlineColorResource(int i2) {
        this.mUnderlineColor = ContextCompat.getColor(getContext(), i2);
        invalidate();
    }

    public void setUnderlineHeight(int i2) {
        this.mUnderlineHeight = i2;
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.mPager = viewPager;
        if (viewPager.getAdapter() != null) {
            this.isCustomTabs = viewPager.getAdapter() instanceof CustomTabProvider;
            viewPager.addOnPageChangeListener(this.mPageListener);
            viewPager.getAdapter().registerDataSetObserver(this.mAdapterObserver);
            this.mAdapterObserver.setAttached(true);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public void setViewPagerSmoothScroll(boolean z) {
        this.isViewPagerSmoothScroll = z;
    }

    public void setmSelectColor(int i2) {
        this.mSelectColor = i2;
    }

    public void setmTabunSelectTextSize(int i2) {
        this.mTabunSelectTextSize = i2;
        updateTabStyles();
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private ColorStateList createColorStateList(int i2, int i3, int i4) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[0]}, new int[]{i2, i3, i4});
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.mTabTextColor = colorStateList;
        updateTabStyles();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.jingdong.common.recommend.ui.PagerSlidingTabStrip$1] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        String str;
        LinearLayout.LayoutParams layoutParams;
        this.mAdapterObserver = new PagerAdapterObserver();
        this.mPageListener = new PageListener();
        this.mTabReselectedListener = null;
        this.mCurrentPosition = 0;
        this.mCurrentPositionOffset = 0.0f;
        this.mIndicatorHeight = 2;
        this.mIndicatorPadding = 0;
        this.mUnderlineHeight = 0;
        this.mDividerWidth = 0;
        this.mDividerPadding = 0;
        this.mTabPadding = 12;
        this.mTabTextSize = 12;
        this.mTabunSelectTextSize = 12;
        this.mTabTextColor = null;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.isExpandTabs = false;
        this.isPaddingMiddle = false;
        this.isTabTextAllCaps = true;
        this.mTabTextTypeface = null;
        this.mTabTextTypefaceStyle = 1;
        this.mLastScrollX = 0;
        this.mTabBackgroundResId = R.drawable.psts_background_tab;
        this.isViewPagerSmoothScroll = true;
        this.isEnableUnRegistedObserver = true;
        setFillViewport(true);
        setWillNotDraw(false);
        FrameLayout frameLayout = new FrameLayout(context);
        this.mRootContainer = frameLayout;
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mTabsContainer = linearLayout;
        linearLayout.setOrientation(0);
        this.mRootContainer.addView(this.mTabsContainer);
        Paint paint = new Paint();
        this.mRectPaint = paint;
        paint.setAntiAlias(true);
        this.mRectPaint.setStyle(Paint.Style.FILL);
        DisplayMetrics displayMetricsObject = BaseInfo.getDisplayMetricsObject();
        this.mScrollOffset = (int) TypedValue.applyDimension(1, this.mScrollOffset, displayMetricsObject);
        this.mIndicatorHeight = (int) TypedValue.applyDimension(1, this.mIndicatorHeight, displayMetricsObject);
        this.mUnderlineHeight = (int) TypedValue.applyDimension(1, this.mUnderlineHeight, displayMetricsObject);
        this.mDividerPadding = (int) TypedValue.applyDimension(1, this.mDividerPadding, displayMetricsObject);
        this.mTabPadding = (int) TypedValue.applyDimension(1, this.mTabPadding, displayMetricsObject);
        this.mDividerWidth = (int) TypedValue.applyDimension(1, this.mDividerWidth, displayMetricsObject);
        this.mTabTextSize = (int) TypedValue.applyDimension(2, this.mTabTextSize, displayMetricsObject);
        Paint paint2 = new Paint();
        this.mDividerPaint = paint2;
        paint2.setAntiAlias(true);
        this.mDividerPaint.setStrokeWidth(this.mDividerWidth);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ANDROID_ATTRS);
        int color = obtainStyledAttributes.getColor(0, ContextCompat.getColor(context, 17170444));
        this.mUnderlineColor = color;
        this.mDividerColor = color;
        this.mIndicatorColor = color;
        this.mSelectColor = color;
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mPaddingLeft = dimensionPixelSize > 0 ? dimensionPixelSize : obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.mPaddingRight = dimensionPixelSize <= 0 ? obtainStyledAttributes.getDimensionPixelSize(3, 0) : dimensionPixelSize;
        obtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT >= 21) {
            this.mTabTextTypefaceStyle = 0;
            str = "sans-serif-medium";
        } else {
            str = "sans-serif";
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.RePagerSlidingTabStrip);
        this.mIndicatorColor = obtainStyledAttributes2.getColor(R.styleable.RePagerSlidingTabStrip_re_pstsIndicatorColor, this.mIndicatorColor);
        this.mIndicatorHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsIndicatorHeight, this.mIndicatorHeight);
        this.mUnderlineColor = obtainStyledAttributes2.getColor(R.styleable.RePagerSlidingTabStrip_re_pstsUnderlineColor, this.mUnderlineColor);
        this.mUnderlineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsUnderlineHeight, this.mUnderlineHeight);
        this.mDividerColor = obtainStyledAttributes2.getColor(R.styleable.RePagerSlidingTabStrip_re_pstsDividerColor, this.mDividerColor);
        this.mDividerWidth = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsDividerWidth, this.mDividerWidth);
        this.mDividerPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsDividerPadding, this.mDividerPadding);
        this.isExpandTabs = obtainStyledAttributes2.getBoolean(R.styleable.RePagerSlidingTabStrip_re_pstsShouldExpand, this.isExpandTabs);
        this.mScrollOffset = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsScrollOffset, this.mScrollOffset);
        this.isPaddingMiddle = obtainStyledAttributes2.getBoolean(R.styleable.RePagerSlidingTabStrip_re_pstsPaddingMiddle, this.isPaddingMiddle);
        this.mTabPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsTabPaddingLeftRight, this.mTabPadding);
        this.mTabBackgroundResId = obtainStyledAttributes2.getResourceId(R.styleable.RePagerSlidingTabStrip_re_pstsTabBackground, this.mTabBackgroundResId);
        int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsTabTextSize, this.mTabTextSize);
        this.mTabTextSize = dimensionPixelSize2;
        this.mTabunSelectTextSize = dimensionPixelSize2;
        this.mIndicatorPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.RePagerSlidingTabStrip_re_pstsIndicatorPadding, this.mIndicatorPadding);
        int i3 = R.styleable.RePagerSlidingTabStrip_re_pstsTabTextColor;
        this.mTabTextColor = obtainStyledAttributes2.hasValue(i3) ? obtainStyledAttributes2.getColorStateList(i3) : 0;
        this.mTabTextTypefaceStyle = obtainStyledAttributes2.getInt(R.styleable.RePagerSlidingTabStrip_re_pstsTabTextStyle, this.mTabTextTypefaceStyle);
        this.isTabTextAllCaps = obtainStyledAttributes2.getBoolean(R.styleable.RePagerSlidingTabStrip_re_pstsTabTextAllCaps, this.isTabTextAllCaps);
        obtainStyledAttributes2.getInt(R.styleable.RePagerSlidingTabStrip_re_pstsTabTextAlpha, 150);
        String string = obtainStyledAttributes2.getString(R.styleable.RePagerSlidingTabStrip_re_pstsTabTextFontFamily);
        obtainStyledAttributes2.recycle();
        if (this.mTabTextColor == null) {
            int i4 = this.mSelectColor;
            this.mTabTextColor = createColorStateList(i4, i4, color);
        }
        this.mTabTextTypeface = Typeface.create(string != null ? string : str, this.mTabTextTypefaceStyle);
        setTabsContainerParentViewPaddings();
        if (this.isExpandTabs) {
            layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        } else {
            layoutParams = new LinearLayout.LayoutParams(-2, -1);
        }
        this.mTabLayoutParams = layoutParams;
    }
}
