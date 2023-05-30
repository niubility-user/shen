package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class PagerSlidingTabStrip extends HorizontalScrollView {
    protected int currentPosition;
    protected float currentPositionOffset;
    protected LinearLayout.LayoutParams defaultTabLayoutParams;
    private ViewPager.OnPageChangeListener delegatePageListener;
    protected int dividerColor;
    protected int dividerLeftRightMargin;
    protected int dividerPadding;
    protected Paint dividerPaint;
    protected int dividerWidth;
    protected LinearLayout.LayoutParams expandedTabLayoutParams;
    protected Bitmap indicatorBitmap;
    protected int indicatorColor;
    protected int indicatorHeight;
    public boolean isTextSingleLine;
    public boolean isViewPagerSmoothScroll;
    private int lastScrollX;
    private final PageListener pageListener;
    protected ViewPager pager;
    protected ViewGroup parent;
    protected Paint rectPaint;
    private int scrollOffset;
    protected int selectedPosition;
    private boolean selectedTabTextBold;
    private int selectedTabTextColor;
    private int selectedTabTextSize;
    protected boolean shouldExpand;
    protected boolean shouldTabCenter;
    private int tabBackgroundResId;
    protected OnTabClickListener tabClickListener;
    protected int tabCount;
    protected int tabPadding;
    private int tabTextColor;
    private int tabTextSize;
    private Typeface tabTypeface;
    private int tabTypefaceStyle;
    protected LinearLayout tabsContainer;
    private boolean textAllCaps;
    public boolean textSizeAvailable;
    protected int underlineColor;
    protected int underlineHeight;
    private static String TAG = PagerSlidingTabStrip.class.getSimpleName();
    private static final int[] ATTRS = {16842901, 16842904};

    /* loaded from: classes12.dex */
    public interface IconTabProvider {
        int getPageIconResId(int i2);
    }

    /* loaded from: classes12.dex */
    public interface OnTabClickListener {
        void onClick(View view, int i2);
    }

    /* loaded from: classes12.dex */
    private class PageListener implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (i2 == 0) {
                PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
                pagerSlidingTabStrip.scrollToChild(pagerSlidingTabStrip.pager.getCurrentItem(), 0);
            }
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            LinearLayout linearLayout;
            PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip.currentPosition = i2;
            pagerSlidingTabStrip.currentPositionOffset = f2;
            if (i2 >= 0 && i2 <= pagerSlidingTabStrip.tabCount - 1 && (linearLayout = pagerSlidingTabStrip.tabsContainer) != null && linearLayout.getChildAt(i2) != null) {
                PagerSlidingTabStrip.this.scrollToChild(i2, (int) (r0.tabsContainer.getChildAt(i2).getWidth() * f2));
            }
            PagerSlidingTabStrip.this.invalidate();
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrolled(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip.selectedPosition = i2;
            pagerSlidingTabStrip.updateTabStyles();
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageSelected(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.jingdong.common.widget.PagerSlidingTabStrip.SavedState.1
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

        public SavedState(Parcelable parcelable) {
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

    protected void addIconTab(int i2, int i3) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i3);
        addTab(i2, imageButton);
    }

    protected void addTab(final int i2, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.PagerSlidingTabStrip.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OnTabClickListener onTabClickListener = PagerSlidingTabStrip.this.tabClickListener;
                if (onTabClickListener != null) {
                    onTabClickListener.onClick(view2, i2);
                }
                PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
                pagerSlidingTabStrip.pager.setCurrentItem(i2, pagerSlidingTabStrip.isViewPagerSmoothScroll);
            }
        });
        int i3 = this.tabPadding;
        view.setPadding(i3, 0, i3, 0);
        this.tabsContainer.addView(view, i2, this.shouldExpand ? this.expandedTabLayoutParams : this.defaultTabLayoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addTextTab(int i2, CharSequence charSequence) {
        TextView textView = new TextView(getContext());
        textView.setText(charSequence);
        textView.setGravity(17);
        if (this.isTextSingleLine) {
            textView.setSingleLine();
        }
        addTab(i2, textView);
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public Bitmap getIndicatorBitmap() {
        return this.indicatorBitmap;
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public int getSelectedTextColor() {
        return this.selectedTabTextColor;
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public LinearLayout getTabsContainer() {
        return this.tabsContainer;
    }

    public int getTextColor() {
        return this.tabTextColor;
    }

    public int getTextSize() {
        return this.tabTextSize;
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            if (this.pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i2, ((IconTabProvider) this.pager.getAdapter()).getPageIconResId(i2));
            } else {
                addTextTab(i2, this.pager.getAdapter().getPageTitle(i2));
            }
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.widget.PagerSlidingTabStrip.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
                pagerSlidingTabStrip.currentPosition = pagerSlidingTabStrip.pager.getCurrentItem();
                PagerSlidingTabStrip pagerSlidingTabStrip2 = PagerSlidingTabStrip.this;
                pagerSlidingTabStrip2.scrollToChild(pagerSlidingTabStrip2.currentPosition, 0);
            }
        });
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        try {
            if (!isInEditMode() && this.tabCount != 0) {
                int height = getHeight();
                this.rectPaint.setColor(this.underlineColor);
                float f2 = height;
                canvas.drawRect(0.0f, height - this.underlineHeight, this.tabsContainer.getWidth(), f2, this.rectPaint);
                this.rectPaint.setColor(this.indicatorColor);
                View childAt = this.tabsContainer.getChildAt(this.currentPosition);
                float left = childAt.getLeft();
                float right = childAt.getRight();
                if (this.currentPositionOffset > 0.0f && (i2 = this.currentPosition) < this.tabCount - 1) {
                    View childAt2 = this.tabsContainer.getChildAt(i2 + 1);
                    float f3 = this.currentPositionOffset;
                    left = (childAt2.getLeft() * f3) + ((1.0f - f3) * left);
                    right = (childAt2.getRight() * f3) + ((1.0f - f3) * right);
                }
                if (this.indicatorBitmap != null) {
                    int i3 = this.dividerLeftRightMargin;
                    canvas.drawBitmap(this.indicatorBitmap, (Rect) null, new Rect((int) (left + i3), height - this.indicatorHeight, (int) (right - i3), height), this.rectPaint);
                } else {
                    int i4 = this.dividerLeftRightMargin;
                    canvas.drawRect(left + i4, height - this.indicatorHeight, right - i4, f2, this.rectPaint);
                }
                this.dividerPaint.setColor(this.dividerColor);
                for (int i5 = 0; i5 < this.tabCount - 1; i5++) {
                    View childAt3 = this.tabsContainer.getChildAt(i5);
                    canvas.drawLine(childAt3.getRight(), this.dividerPadding, childAt3.getRight(), height - this.dividerPadding, this.dividerPaint);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.currentPosition = savedState.currentPosition;
            requestLayout();
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.currentPosition;
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scrollToChild(int i2, int i3) {
        LinearLayout linearLayout;
        int i4 = this.tabCount;
        if (i4 == 0) {
            return;
        }
        int left = (i2 < 0 || i2 > i4 + (-1) || (linearLayout = this.tabsContainer) == null || linearLayout.getChildAt(i2) == null) ? 0 : this.tabsContainer.getChildAt(i2).getLeft() + i3;
        if (i2 > 0 || i3 > 0) {
            left -= this.scrollOffset;
        }
        if (left != this.lastScrollX) {
            this.lastScrollX = left;
            scrollTo(left, 0);
        }
    }

    public void setDividerColor(int i2) {
        this.dividerColor = i2;
        invalidate();
    }

    public void setDividerColorResource(int i2) {
        this.dividerColor = getResources().getColor(i2);
        invalidate();
    }

    public void setDividerPadding(int i2) {
        this.dividerPadding = i2;
        invalidate();
    }

    public void setIndicatorBitmap(Bitmap bitmap) {
        this.indicatorBitmap = bitmap;
        invalidate();
    }

    public void setIndicatorColor(int i2) {
        this.indicatorColor = i2;
        invalidate();
    }

    public void setIndicatorColorResource(int i2) {
        this.indicatorColor = getResources().getColor(i2);
        invalidate();
    }

    public void setIndicatorHeight(int i2) {
        this.indicatorHeight = i2;
        invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.tabClickListener = onTabClickListener;
    }

    public void setParent(ViewGroup viewGroup) {
        this.parent = viewGroup;
    }

    public void setScrollOffset(int i2) {
        this.scrollOffset = i2;
        invalidate();
    }

    public void setSelectedTextColor(int i2) {
        this.selectedTabTextColor = i2;
        updateTabStyles();
    }

    public void setSelectedTextColorResource(int i2) {
        this.selectedTabTextColor = getResources().getColor(i2);
        updateTabStyles();
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        notifyDataSetChanged();
    }

    public void setTabBackground(int i2) {
        this.tabBackgroundResId = i2;
        updateTabStyles();
    }

    public void setTabPaddingLeftRight(int i2) {
        this.tabPadding = i2;
        updateTabStyles();
    }

    public void setTextColor(int i2) {
        this.tabTextColor = i2;
        updateTabStyles();
    }

    public void setTextColorResource(int i2) {
        this.tabTextColor = getResources().getColor(i2);
        updateTabStyles();
    }

    public void setTextSize(int i2) {
        this.tabTextSize = i2;
        updateTabStyles();
    }

    public void setTypeface(Typeface typeface, int i2) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = i2;
        updateTabStyles();
    }

    public void setUnderlineColor(int i2) {
        this.underlineColor = i2;
        invalidate();
    }

    public void setUnderlineColorResource(int i2) {
        this.underlineColor = getResources().getColor(i2);
        invalidate();
    }

    public void setUnderlineHeight(int i2) {
        this.underlineHeight = i2;
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        if (viewPager.getAdapter() != null) {
            viewPager.setOnPageChangeListener(this.pageListener);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateTabStyles() {
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            View childAt = this.tabsContainer.getChildAt(i2);
            childAt.setBackgroundResource(this.tabBackgroundResId);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (this.textSizeAvailable) {
                    textView.setTextSize(0, this.tabTextSize);
                }
                textView.setTypeface(this.tabTypeface, this.tabTypefaceStyle);
                textView.setTextColor(this.tabTextColor);
                if (this.textAllCaps) {
                    textView.setAllCaps(true);
                }
                if (i2 == this.selectedPosition) {
                    textView.setTextColor(this.selectedTabTextColor);
                    if (this.selectedTabTextBold) {
                        textView.setTypeface(Typeface.defaultFromStyle(1));
                    }
                }
            }
        }
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.pageListener = new PageListener();
        this.currentPosition = 0;
        this.selectedPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.dividerColor = 436207616;
        this.shouldExpand = false;
        this.textAllCaps = false;
        this.shouldTabCenter = false;
        this.scrollOffset = 52;
        this.indicatorHeight = 8;
        this.underlineHeight = 2;
        this.dividerPadding = 12;
        this.dividerLeftRightMargin = 0;
        this.tabPadding = 24;
        this.dividerWidth = 1;
        this.tabTextSize = 12;
        this.tabTextColor = -10066330;
        this.selectedTabTextColor = -10066330;
        this.tabTypeface = Typeface.MONOSPACE;
        this.tabTypefaceStyle = 0;
        this.lastScrollX = 0;
        this.tabBackgroundResId = 17170445;
        this.isViewPagerSmoothScroll = true;
        this.textSizeAvailable = true;
        this.isTextSingleLine = true;
        setFillViewport(true);
        setWillNotDraw(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, this.indicatorHeight, displayMetrics);
        this.underlineHeight = (int) TypedValue.applyDimension(1, this.underlineHeight, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, this.dividerPadding, displayMetrics);
        this.tabPadding = (int) TypedValue.applyDimension(1, this.tabPadding, displayMetrics);
        this.dividerWidth = (int) TypedValue.applyDimension(1, this.dividerWidth, displayMetrics);
        this.tabTextSize = (int) TypedValue.applyDimension(2, this.tabTextSize, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
        this.tabTextColor = obtainStyledAttributes.getColor(1, this.tabTextColor);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        int color = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.indicatorColor);
        this.indicatorColor = color;
        this.selectedTabTextColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_selectedTabTextColor, color);
        this.selectedTabTextSize = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_selectedTabTextSize, this.tabTextSize);
        this.selectedTabTextBold = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_selectedTabTextBold, false);
        this.underlineColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, this.underlineColor);
        this.dividerColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.indicatorHeight);
        this.underlineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.underlineHeight);
        this.dividerWidth = obtainStyledAttributes2.getDimensionPixelOffset(R.styleable.PagerSlidingTabStrip_pstsDividerWidth, this.dividerWidth);
        this.dividerPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.dividerPadding);
        this.dividerLeftRightMargin = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerLeftRightMargin, 0);
        this.tabPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.tabPadding);
        this.tabBackgroundResId = obtainStyledAttributes2.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, this.tabBackgroundResId);
        this.shouldExpand = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.scrollOffset);
        this.textAllCaps = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, this.textAllCaps);
        this.shouldTabCenter = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldTabCenter, this.shouldTabCenter);
        obtainStyledAttributes2.recycle();
        LinearLayout linearLayout = new LinearLayout(context);
        this.tabsContainer = linearLayout;
        linearLayout.setOrientation(0);
        if (this.shouldTabCenter) {
            this.tabsContainer.setGravity(1);
        }
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        Paint paint = new Paint();
        this.rectPaint = paint;
        paint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.dividerPaint = paint2;
        paint2.setAntiAlias(true);
        this.dividerPaint.setStrokeWidth(this.dividerWidth);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
        this.expandedTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addTextTab(int i2, CharSequence charSequence, int i3) {
        TextView textView = new TextView(getContext());
        textView.setText(charSequence);
        textView.setGravity(i3);
        if (this.isTextSingleLine) {
            textView.setSingleLine();
        }
        addTab(i2, textView);
    }
}
