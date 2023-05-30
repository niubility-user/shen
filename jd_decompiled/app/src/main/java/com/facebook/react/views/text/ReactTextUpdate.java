package com.facebook.react.views.text;

import android.text.Spannable;

/* loaded from: classes12.dex */
public class ReactTextUpdate {
    private final boolean mContainsImages;
    private final int mJsEventCounter;
    private final int mJustificationMode;
    private final float mPaddingBottom;
    private final float mPaddingLeft;
    private final float mPaddingRight;
    private final float mPaddingTop;
    private final Spannable mText;
    private final int mTextAlign;
    private final int mTextBreakStrategy;

    @Deprecated
    public ReactTextUpdate(Spannable spannable, int i2, boolean z, float f2, float f3, float f4, float f5, int i3) {
        this(spannable, i2, z, f2, f3, f4, f5, i3, 1, 0);
    }

    public boolean containsImages() {
        return this.mContainsImages;
    }

    public int getJsEventCounter() {
        return this.mJsEventCounter;
    }

    public int getJustificationMode() {
        return this.mJustificationMode;
    }

    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public float getPaddingRight() {
        return this.mPaddingRight;
    }

    public float getPaddingTop() {
        return this.mPaddingTop;
    }

    public Spannable getText() {
        return this.mText;
    }

    public int getTextAlign() {
        return this.mTextAlign;
    }

    public int getTextBreakStrategy() {
        return this.mTextBreakStrategy;
    }

    public ReactTextUpdate(Spannable spannable, int i2, boolean z, float f2, float f3, float f4, float f5, int i3, int i4, int i5) {
        this.mText = spannable;
        this.mJsEventCounter = i2;
        this.mContainsImages = z;
        this.mPaddingLeft = f2;
        this.mPaddingTop = f3;
        this.mPaddingRight = f4;
        this.mPaddingBottom = f5;
        this.mTextAlign = i3;
        this.mTextBreakStrategy = i4;
        this.mJustificationMode = i5;
    }
}
