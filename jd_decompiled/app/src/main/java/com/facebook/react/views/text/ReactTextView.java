package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactTextView extends TextView implements ReactCompoundView {
    private static final ViewGroup.LayoutParams EMPTY_LAYOUT_PARAMS = new ViewGroup.LayoutParams(0, 0);
    private boolean mContainsImages;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private TextUtils.TruncateAt mEllipsizeLocation;
    private int mNumberOfLines;
    private ReactViewBackgroundManager mReactBackgroundManager;
    private Spannable mSpanned;
    private int mTextAlign;

    public ReactTextView(Context context) {
        super(context);
        this.mTextAlign = 0;
        this.mNumberOfLines = Integer.MAX_VALUE;
        this.mEllipsizeLocation = TextUtils.TruncateAt.END;
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mDefaultGravityHorizontal = getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        this.mDefaultGravityVertical = getGravity() & 112;
    }

    public Spannable getSpanned() {
        return this.mSpanned;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onAttachedToWindow();
            }
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onDetachedFromWindow();
            }
        }
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onFinishTemporaryDetach();
            }
        }
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onStartTemporaryDetach();
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float f2, float f3) {
        int i2;
        CharSequence text = getText();
        int id = getId();
        int i3 = (int) f2;
        int i4 = (int) f3;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i4);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i3 >= lineLeft && i3 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, i3);
                ReactTagSpan[] reactTagSpanArr = (ReactTagSpan[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, ReactTagSpan.class);
                if (reactTagSpanArr != null) {
                    int length = text.length();
                    for (int i5 = 0; i5 < reactTagSpanArr.length; i5++) {
                        int spanStart = spanned.getSpanStart(reactTagSpanArr[i5]);
                        int spanEnd = spanned.getSpanEnd(reactTagSpanArr[i5]);
                        if (spanEnd > offsetForHorizontal && (i2 = spanEnd - spanStart) <= length) {
                            id = reactTagSpanArr[i5].getReactTag();
                            length = i2;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                FLog.e(ReactConstants.TAG, "Crash in HorizontalMeasurementProvider: " + e2.getMessage());
            }
        }
        return id;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.mReactBackgroundManager.setBackgroundColor(i2);
    }

    public void setBorderColor(int i2, float f2, float f3) {
        this.mReactBackgroundManager.setBorderColor(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.mReactBackgroundManager.setBorderRadius(f2);
    }

    public void setBorderStyle(@Nullable String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setBorderWidth(int i2, float f2) {
        this.mReactBackgroundManager.setBorderWidth(i2, f2);
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.mEllipsizeLocation = truncateAt;
    }

    void setGravityHorizontal(int i2) {
        if (i2 == 0) {
            i2 = this.mDefaultGravityHorizontal;
        }
        setGravity(i2 | (getGravity() & (-8) & (-8388616)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGravityVertical(int i2) {
        if (i2 == 0) {
            i2 = this.mDefaultGravityVertical;
        }
        setGravity(i2 | (getGravity() & (-113)));
    }

    public void setNumberOfLines(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 == 0) {
            i2 = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = i2;
        setSingleLine(i2 == 1);
        setMaxLines(this.mNumberOfLines);
    }

    public void setSpanned(Spannable spannable) {
        this.mSpanned = spannable;
    }

    public void setText(ReactTextUpdate reactTextUpdate) {
        this.mContainsImages = reactTextUpdate.containsImages();
        if (getLayoutParams() == null) {
            setLayoutParams(EMPTY_LAYOUT_PARAMS);
        }
        setText(reactTextUpdate.getText());
        setPadding((int) Math.floor(reactTextUpdate.getPaddingLeft()), (int) Math.floor(reactTextUpdate.getPaddingTop()), (int) Math.floor(reactTextUpdate.getPaddingRight()), (int) Math.floor(reactTextUpdate.getPaddingBottom()));
        int textAlign = reactTextUpdate.getTextAlign();
        if (this.mTextAlign != textAlign) {
            this.mTextAlign = textAlign;
        }
        setGravityHorizontal(this.mTextAlign);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23 && getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
            setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
        }
        if (i2 < 26 || getJustificationMode() == reactTextUpdate.getJustificationMode()) {
            return;
        }
        setJustificationMode(reactTextUpdate.getJustificationMode());
    }

    public void updateView() {
        setEllipsize(this.mNumberOfLines == Integer.MAX_VALUE ? null : this.mEllipsizeLocation);
    }

    @Override // android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void setBorderRadius(float f2, int i2) {
        this.mReactBackgroundManager.setBorderRadius(f2, i2);
    }
}
