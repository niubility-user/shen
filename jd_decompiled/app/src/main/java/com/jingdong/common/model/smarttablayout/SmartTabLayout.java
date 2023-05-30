package com.jingdong.common.model.smarttablayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.R;

/* loaded from: classes5.dex */
public class SmartTabLayout extends HorizontalScrollView {
    private static final boolean DEFAULT_DISTRIBUTE_EVENLY = false;
    private static final boolean TAB_CLICKABLE = true;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final boolean TAB_VIEW_TEXT_ALL_CAPS = false;
    private static final int TAB_VIEW_TEXT_COLOR = -67108864;
    private static final int TAB_VIEW_TEXT_MIN_WIDTH = 0;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
    private static final String TAG = "SmartTabLayout";
    private static final int TITLE_OFFSET_AUTO_CENTER = -1;
    private static final int TITLE_OFFSET_DIPS = 24;
    private boolean distributeEvenly;
    private InternalTabClickListener internalTabClickListener;
    private OnScrollChangeListener onScrollChangeListener;
    private OnTabClickListener onTabClickListener;
    private int selectedOffset;
    private TabProvider tabProvider;
    protected final SmartTabStrip tabStrip;
    private int tabViewBackgroundResId;
    private boolean tabViewTextAllCaps;
    private boolean tabViewTextBold;
    private ColorStateList tabViewTextColors;
    private int tabViewTextHorizontalPadding;
    private int tabViewTextMinWidth;
    private float tabViewTextSize;
    private int titleOffset;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    /* loaded from: classes5.dex */
    public class InternalTabClickListener implements View.OnClickListener {
        private InternalTabClickListener() {
            SmartTabLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (int i2 = 0; i2 < SmartTabLayout.this.tabStrip.getChildCount(); i2++) {
                if (view == SmartTabLayout.this.tabStrip.getChildAt(i2)) {
                    if (SmartTabLayout.this.onTabClickListener != null) {
                        SmartTabLayout.this.onTabClickListener.onTabClicked(i2);
                    }
                    SmartTabLayout.this.viewPager.setCurrentItem(i2);
                    return;
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int scrollState;

        private InternalViewPagerListener() {
            SmartTabLayout.this = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            this.scrollState = i2;
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            if (childCount == 0 || i2 < 0 || i2 >= childCount) {
                return;
            }
            SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i2, f2);
            SmartTabLayout.this.scrollToTab(i2, f2);
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrolled(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.scrollState == 0) {
                SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i2, 0.0f);
                SmartTabLayout.this.scrollToTab(i2, 0.0f);
            }
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                SmartTabLayout.this.tabStrip.getChildAt(i3).setSelected(i2 == i3);
                i3++;
            }
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageSelected(i2);
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface OnScrollChangeListener {
        void onScrollChanged(int i2, int i3);
    }

    /* loaded from: classes5.dex */
    public interface OnTabClickListener {
        void onTabClicked(int i2);
    }

    /* loaded from: classes5.dex */
    public static class SimpleTabProvider implements TabProvider {
        private final LayoutInflater inflater;
        private final int tabViewLayoutId;
        private final int tabViewTextViewId;

        @Override // com.jingdong.common.model.smarttablayout.SmartTabLayout.TabProvider
        public View createTabView(ViewGroup viewGroup, int i2, PagerAdapter pagerAdapter) {
            int i3 = this.tabViewLayoutId;
            TextView textView = null;
            View inflate = i3 != -1 ? this.inflater.inflate(i3, viewGroup, false) : null;
            int i4 = this.tabViewTextViewId;
            if (i4 != -1 && inflate != 0) {
                textView = (TextView) inflate.findViewById(i4);
            }
            if (textView == null && TextView.class.isInstance(inflate)) {
                textView = inflate;
            }
            if (textView != null) {
                textView.setText(pagerAdapter.getPageTitle(i2));
            }
            return inflate;
        }

        private SimpleTabProvider(Context context, int i2, int i3) {
            this.inflater = LayoutInflater.from(context);
            this.tabViewLayoutId = i2;
            this.tabViewTextViewId = i3;
        }
    }

    /* loaded from: classes5.dex */
    public interface TabColorizer {
        int getDividerColor(int i2);

        int getIndicatorColor(int i2);
    }

    /* loaded from: classes5.dex */
    public interface TabProvider {
        View createTabView(ViewGroup viewGroup, int i2, PagerAdapter pagerAdapter);
    }

    public SmartTabLayout(Context context) {
        this(context, null);
    }

    private void populateTabStrip() {
        View createTabView;
        PagerAdapter adapter = this.viewPager.getAdapter();
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            TabProvider tabProvider = this.tabProvider;
            if (tabProvider == null) {
                createTabView = createDefaultTabView(adapter.getPageTitle(i2));
            } else {
                createTabView = tabProvider.createTabView(this.tabStrip, i2, adapter);
            }
            if (createTabView != null) {
                if (this.distributeEvenly) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) createTabView.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                }
                InternalTabClickListener internalTabClickListener = this.internalTabClickListener;
                if (internalTabClickListener != null) {
                    createTabView.setOnClickListener(internalTabClickListener);
                }
                this.tabStrip.addView(createTabView);
                if (i2 == this.viewPager.getCurrentItem()) {
                    createTabView.setSelected(true);
                }
            } else {
                throw new IllegalStateException("tabView is null.");
            }
        }
    }

    public void scrollToTab(int i2, float f2) {
        int i3;
        int start;
        int i4;
        int childCount = this.tabStrip.getChildCount();
        if (childCount == 0 || i2 < 0 || i2 >= childCount) {
            return;
        }
        boolean isLayoutRtl = Utils.isLayoutRtl(this);
        View childAt = this.tabStrip.getChildAt(i2);
        int width = (int) ((Utils.getWidth(childAt) + Utils.getMarginHorizontally(childAt)) * f2);
        if (this.tabStrip.isIndicatorAlwaysInCenter()) {
            if (0.0f < f2 && f2 < 1.0f) {
                View childAt2 = this.tabStrip.getChildAt(i2 + 1);
                width = Math.round(f2 * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt2) / 2) + Utils.getMarginStart(childAt2)));
            }
            View childAt3 = this.tabStrip.getChildAt(0);
            if (isLayoutRtl) {
                int width2 = Utils.getWidth(childAt3) + Utils.getMarginEnd(childAt3);
                int width3 = Utils.getWidth(childAt) + Utils.getMarginEnd(childAt);
                start = (Utils.getEnd(childAt) - Utils.getMarginEnd(childAt)) - width;
                i4 = (width2 - width3) / 2;
            } else {
                int width4 = Utils.getWidth(childAt3) + Utils.getMarginStart(childAt3);
                int width5 = Utils.getWidth(childAt) + Utils.getMarginStart(childAt);
                start = (Utils.getStart(childAt) - Utils.getMarginStart(childAt)) + width;
                i4 = (width4 - width5) / 2;
            }
            scrollTo(start - i4, 0);
            return;
        }
        int i5 = this.titleOffset;
        if (i5 == -1) {
            if (0.0f < f2 && f2 < 1.0f) {
                View childAt4 = this.tabStrip.getChildAt(i2 + 1);
                width = Math.round(f2 * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt4) / 2) + Utils.getMarginStart(childAt4)));
            }
            if (isLayoutRtl) {
                i3 = (((-Utils.getWidthWithMargin(childAt)) / 2) + (getWidth() / 2)) - Utils.getPaddingStart(this);
            } else {
                i3 = ((Utils.getWidthWithMargin(childAt) / 2) - (getWidth() / 2)) + Utils.getPaddingStart(this);
            }
        } else if (isLayoutRtl) {
            if (i2 <= 0 && f2 <= 0.0f) {
                i5 = 0;
            }
            i3 = i5;
        } else {
            i3 = (i2 > 0 || f2 > 0.0f) ? -i5 : 0;
        }
        int start2 = Utils.getStart(childAt);
        int marginStart = Utils.getMarginStart(childAt);
        scrollTo(i3 + (isLayoutRtl ? (((start2 + marginStart) - width) - getWidth()) + Utils.getPaddingHorizontally(this) : (start2 - marginStart) + width), 0);
    }

    protected TextView createDefaultTabView(CharSequence charSequence) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(charSequence);
        textView.setTextColor(this.tabViewTextColors);
        textView.setTextSize(0, this.tabViewTextSize);
        textView.setTypeface(Typeface.MONOSPACE, this.tabViewTextBold ? 1 : 0);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        int i2 = this.tabViewBackgroundResId;
        if (i2 != -1) {
            textView.setBackgroundResource(i2);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            textView.setAllCaps(this.tabViewTextAllCaps);
        }
        int i3 = this.tabViewTextHorizontalPadding;
        textView.setPadding(i3, 0, i3, 0);
        int i4 = this.tabViewTextMinWidth;
        if (i4 > 0) {
            textView.setMinWidth(i4);
        }
        return textView;
    }

    public int getSelectedOffset() {
        return this.selectedOffset;
    }

    public View getTabAt(int i2) {
        return this.tabStrip.getChildAt(i2);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        ViewPager viewPager;
        super.onLayout(z, i2, i3, i4, i5);
        if (!z || (viewPager = this.viewPager) == null) {
            return;
        }
        scrollToTab(viewPager.getCurrentItem(), 0.0f);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        OnScrollChangeListener onScrollChangeListener = this.onScrollChangeListener;
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(i2, i4);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (!this.tabStrip.isIndicatorAlwaysInCenter() || this.tabStrip.getChildCount() <= 0) {
            return;
        }
        View childAt = this.tabStrip.getChildAt(0);
        View childAt2 = this.tabStrip.getChildAt(getChildCount() - 1);
        int measuredWidth = ((i2 - Utils.getMeasuredWidth(childAt)) / 2) - Utils.getMarginStart(childAt);
        int measuredWidth2 = ((i2 - Utils.getMeasuredWidth(childAt2)) / 2) - Utils.getMarginEnd(childAt2);
        SmartTabStrip smartTabStrip = this.tabStrip;
        smartTabStrip.setMinimumWidth(smartTabStrip.getMeasuredWidth());
        ViewCompat.setPaddingRelative(this, measuredWidth, getPaddingTop(), measuredWidth2, getPaddingBottom());
        setClipToPadding(false);
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.tabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setCustomTabView(int i2, int i3) {
        this.tabProvider = new SimpleTabProvider(getContext(), i2, i3);
    }

    public void setDefaultTabTextColor(int i2) {
        this.tabViewTextColors = ColorStateList.valueOf(i2);
    }

    public void setDistributeEvenly(boolean z) {
        this.distributeEvenly = z;
    }

    public void setDividerColors(int... iArr) {
        this.tabStrip.setDividerColors(iArr);
    }

    public void setFixedWidth(boolean z) {
        SmartTabStrip smartTabStrip = this.tabStrip;
        if (smartTabStrip != null) {
            smartTabStrip.setFixedWidth(z);
        }
    }

    public void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.tabStrip.setIndicationInterpolator(smartTabIndicationInterpolator);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.viewPagerPageChangeListener = onPageChangeListener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.tabStrip.setSelectedIndicatorColors(iArr);
    }

    public void setSelectedOffset(int i2) {
        this.selectedOffset = i2;
    }

    public void setTabDividerHorizontalPadding(int i2) {
        this.tabStrip.setTabDividerHorizontalPadding(i2);
    }

    public void setTabViewTextHorizontalPadding(int i2) {
        this.tabViewTextHorizontalPadding = i2;
    }

    public void setTitleAutoCenter() {
        this.titleOffset = -1;
    }

    public void setViewPager(ViewPager viewPager) {
        this.tabStrip.removeAllViews();
        this.viewPager = viewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new InternalViewPagerListener());
        populateTabStrip();
    }

    public SmartTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCustomTabView(TabProvider tabProvider) {
        this.tabProvider = tabProvider;
    }

    public void setDefaultTabTextColor(ColorStateList colorStateList) {
        this.tabViewTextColors = colorStateList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.jingdong.common.model.smarttablayout.SmartTabLayout$1] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public SmartTabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setHorizontalScrollBarEnabled(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f2 = displayMetrics.density;
        float applyDimension = TypedValue.applyDimension(2, 12.0f, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.stl_SmartTabLayout, i2, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_defaultTabBackground, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_defaultTabTextAllCaps, false);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.stl_SmartTabLayout_stl_defaultTabTextColor);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.stl_SmartTabLayout_stl_defaultTabTextSize, applyDimension);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextHorizontalPadding, (int) (16.0f * f2));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextMinWidth, (int) (0.0f * f2));
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextLayoutId, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextViewId, -1);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_distributeEvenly, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_clickable, true);
        boolean z4 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_defaultTabTextBold, false);
        boolean z5 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_tabDividerHorizontalPadding, false);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.stl_SmartTabLayout_stl_titleOffset, (int) (f2 * 24.0f));
        obtainStyledAttributes.recycle();
        this.titleOffset = layoutDimension;
        this.tabViewBackgroundResId = resourceId;
        this.tabViewTextAllCaps = z;
        this.tabViewTextColors = colorStateList == null ? ColorStateList.valueOf(TAB_VIEW_TEXT_COLOR) : colorStateList;
        this.tabViewTextSize = dimension;
        this.tabViewTextHorizontalPadding = dimensionPixelSize;
        this.tabViewTextMinWidth = dimensionPixelSize2;
        this.internalTabClickListener = z3 ? new InternalTabClickListener() : 0;
        this.distributeEvenly = z2;
        this.tabViewTextBold = z4;
        if (resourceId2 != -1) {
            setCustomTabView(resourceId2, resourceId3);
        }
        SmartTabStrip smartTabStrip = new SmartTabStrip(context, attributeSet);
        this.tabStrip = smartTabStrip;
        smartTabStrip.setTabDividerHorizontalPadding(z5);
        if (z2 && smartTabStrip.isIndicatorAlwaysInCenter()) {
            throw new UnsupportedOperationException("'distributeEvenly' and 'indicatorAlwaysInCenter' both use does not support");
        }
        setFillViewport(!smartTabStrip.isIndicatorAlwaysInCenter());
        addView(smartTabStrip, -1, -1);
    }

    public void setFixedWidth(int i2) {
        SmartTabStrip smartTabStrip = this.tabStrip;
        if (smartTabStrip != null) {
            smartTabStrip.setFixedWidth(i2);
        }
    }
}
