package com.jd.lib.un.basewidget.widget.multi.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes16.dex */
public class MultiTagLayout extends LinearLayout implements View.OnClickListener {
    private int mNormalTextColor;
    private int mSelectedIndex;
    private int mSelectedTextColor;
    private int mTagMarginBottom;
    private int mTagMarginLeft;
    private int mTagMarginRight;
    private int mTagMarginTop;
    private int mTextSize;
    private a tagClickListener;

    /* loaded from: classes16.dex */
    public interface a {
        void a(int i2, View view);
    }

    public MultiTagLayout(Context context) {
        this(context, null);
    }

    private int findViewIndex(View view) {
        if (view == null) {
            return -1;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (getChildAt(i2) == view) {
                return i2;
            }
        }
        return -1;
    }

    private void onSelected(int i2, View view, boolean z) {
        a aVar;
        int i3 = this.mSelectedIndex;
        if (i3 == i2) {
            return;
        }
        if (i3 >= 0 && i3 <= getTagSize() - 1) {
            ((TextView) getChildAt(this.mSelectedIndex)).setTextColor(this.mNormalTextColor);
        }
        if (i2 >= 0 && i2 <= getTagSize() - 1) {
            ((TextView) getChildAt(i2)).setTextColor(this.mSelectedTextColor);
        }
        this.mSelectedIndex = i2;
        if (!z || (aVar = this.tagClickListener) == null) {
            return;
        }
        aVar.a(i2, view);
    }

    public void addTag(String str) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setTextSize(0, this.mTextSize);
        textView.setGravity(17);
        textView.setOnClickListener(this);
        textView.setTextColor(this.mNormalTextColor);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.mTagMarginLeft;
        layoutParams.topMargin = this.mTagMarginTop;
        layoutParams.rightMargin = this.mTagMarginRight;
        layoutParams.bottomMargin = this.mTagMarginBottom;
        super.addView(textView, -1, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, int i3) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    public void changeTagState(int i2) {
        onSelected(i2, getTagView(i2), false);
    }

    public int findTagViewIndex(View view) {
        return findViewIndex(view);
    }

    public int getTagSize() {
        return getChildCount();
    }

    public View getTagView(int i2) {
        int tagSize = getTagSize();
        if (i2 < 0 || i2 > tagSize - 1) {
            return null;
        }
        return getChildAt(i2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int findViewIndex = findViewIndex(view);
        onSelected(findViewIndex, getChildAt(findViewIndex), true);
    }

    public void release() {
        this.mSelectedIndex = -1;
    }

    public void removeTag(int i2, int i3) {
        while (i3 >= i2) {
            removeViewAt(i3);
            i3--;
        }
    }

    public void setNormalTextColor(int i2) {
        this.mNormalTextColor = i2;
    }

    public void setSelectedTextColor(int i2) {
        this.mSelectedTextColor = i2;
    }

    public void setTagClickListener(a aVar) {
        this.tagClickListener = aVar;
    }

    public void setTagMargin(int i2, int i3, int i4, int i5) {
        this.mTagMarginLeft = i2;
        this.mTagMarginTop = i3;
        this.mTagMarginRight = i4;
        this.mTagMarginBottom = i5;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i6).getLayoutParams();
            layoutParams.leftMargin = i2;
            layoutParams.topMargin = i3;
            layoutParams.rightMargin = i4;
            layoutParams.bottomMargin = i5;
        }
        requestLayout();
    }

    public void setTextSize(int i2) {
        this.mTextSize = i2;
    }

    public void updateTag(int i2, String str) {
        View tagView = getTagView(i2);
        TextView textView = tagView instanceof TextView ? (TextView) tagView : null;
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(str);
    }

    public MultiTagLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSelectedTextColor = SupportMenu.CATEGORY_MASK;
        this.mNormalTextColor = -16777216;
        this.mTextSize = com.jd.lib.un.basewidget.widget.multi.c.a.b(13.0f);
        this.mTagMarginLeft = com.jd.lib.un.basewidget.widget.multi.c.a.a(5.0f);
        this.mTagMarginTop = com.jd.lib.un.basewidget.widget.multi.c.a.a(2.0f);
        this.mTagMarginRight = com.jd.lib.un.basewidget.widget.multi.c.a.a(5.0f);
        this.mTagMarginBottom = com.jd.lib.un.basewidget.widget.multi.c.a.a(2.0f);
        this.mSelectedIndex = -1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiTagLayout);
            this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiTagLayout_tag_text_size, this.mTextSize);
            this.mSelectedTextColor = obtainStyledAttributes.getColor(R.styleable.MultiTagLayout_tag_selector_color, this.mSelectedTextColor);
            this.mNormalTextColor = obtainStyledAttributes.getColor(R.styleable.MultiTagLayout_tag_normal_color, this.mNormalTextColor);
            this.mTagMarginLeft = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiTagLayout_tag_left_margin, this.mTagMarginLeft);
            this.mTagMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiTagLayout_tag_top_margin, this.mTagMarginTop);
            this.mTagMarginRight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiTagLayout_tag_right_margin, this.mTagMarginRight);
            this.mTagMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiTagLayout_tag_bottom_margin, this.mTagMarginBottom);
            obtainStyledAttributes.recycle();
        }
        setOrientation(0);
    }

    public void removeTag(int i2) {
        removeTag(i2, i2);
    }
}
